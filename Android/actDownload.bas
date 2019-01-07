Type=Activity
Version=6
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: False
	#IncludeTitle: False
#End Region

Sub Process_Globals
	Public ForceUpdate As Boolean
End Sub

Sub Globals
	Dim dbCat,dbBook As Categories
	Private sv1 As ScrollView
	Private pnlcategories As Panel
	Private pnlbooks As Panel
	Private lblcount_book As Label
	Private lblcategory_name As Label
	Private Progressbar1 As ProgressBar2
	Private top As Int = 0
	Private lblbookname As Label
	Private lbbooklextra As Label
	Private btndownload As Button
	Private lblsize As Label
	Private stack As List
	Private currentCatID,currentFileChoosen As String
	Private pb1 As ProgressBar
	Private currentPB As ProgressBar
	Private CurrentDownloadLabel As Label
	Private btnfilter As Button
	Private pnlsearch As Panel
	Private lbltitle As Label
	Private btnmenu As Button
	Private btnsearch As Button
	Dim rs As RSPopupMenu
	Private txtsearch As EditText
	Private FilterLanguageVal As String
	Private FilterLangauge As Boolean
	Private pnlover As Panel
End Sub

Sub Activity_Create(FirstTime As Boolean)
	
	Activity.LoadLayout("frmdownload")
	Progressbar1.Initialize(Activity)
	stack.Initialize
	
	dbCat.Initialize
	
	If dbCat.GetBookCount = 0 Or ForceUpdate = True Then
		dbCat.Initialize
		ProgressDialogShow("لیست کردن...")
		Dim hu As HttpJob
		hu.Initialize("cat",Me)
		hu.PostString(Library.URL & "list_category","")
		ForceUpdate = False
	Else
		LoadItem
	End If
	
	rs.Initialize("rspopup",btnfilter)
	rs.AddMenuItem(1,1,"فیلتر کتاب های فارسی")
	rs.AddMenuItem(2,2,"فیلتر کتاب های انگلیسی")
	rs.AddMenuItem(3,3,"فیلتر کتاب های عربی")
	rs.AddMenuItem(4,4,"نمایش همه کتاب ها")

End Sub

Sub LoadItem
	AddCategories(dbCat.GetCategory(0))
	AddBooks(dbCat.GetBook(0,FilterLanguageVal,FilterLangauge))
End Sub

Sub LoadCategories(sID As String)
	
	sv1.Panel.RemoveAllViews
	top = 0
			
	If stack.Size > 0 Then
		Dim p1 As Panel
		p1.Initialize("")
		sv1.Panel.AddView(p1,0,top,sv1.Width,0)
		p1.LoadLayout("frmteplate_category")
		p1.Height = pnlcategories.Height
		sv1.Panel.Height = sv1.Panel.Height + p1.Height
		
		top = top + p1.Height + 7
		
		Dim temp As Map
		temp = stack.Get(stack.Size-1)
		
		lblcategory_name.Text = "برگشت به دسته بندی قبلی"
		lblcount_book.Text = "کلیک کنید"
		temp.Put("state","return")
		pnlcategories.Tag = temp
		
	End If
	
	DoEvents
	
	currentCatID = sID
	
	AddCategories(dbCat.GetCategory(currentCatID))
	AddBooks(dbCat.GetBook(currentCatID,FilterLanguageVal,FilterLangauge))
	
End Sub

Sub AddCategories(List1 As List)
	
	For i = 0 To List1.Size - 1
		Dim p1 As Panel
		p1.Initialize("")
		sv1.Panel.AddView(p1,0,top,sv1.Width,0)
		p1.LoadLayout("frmteplate_category")
		p1.Height = pnlcategories.Height
		
		sv1.Panel.Height = sv1.Panel.Height + p1.Height
		
		top = top + p1.Height + 7
		
		Dim temp As Map
		temp = List1.Get(i)
		
		lblcategory_name.Text = temp.Get("sTitle")
		lblcount_book.Text = "تعداد کتاب : " & temp.Get("sCount")
		pnlcategories.Tag = temp
		
	Next
	
End Sub

Sub AddBooks(List1 As List)
	
	For i = 0 To List1.Size - 1
		
		Dim temp As Map
		temp = List1.Get(i)
	
		Dim p1 As Panel
		p1.Initialize("")
		sv1.Panel.AddView(p1,0,top,sv1.Width,0)
		p1.LoadLayout("frmtemplate_book")
		p1.Height = pnlbooks.Height
		top = top + pnlbooks.Height + 7
		
		lblbookname.Text = temp.Get("sTitle")
		lbbooklextra.Text = temp.Get("sAuthor") & " تعداد دانلود : " & temp.Get("sDownload")
		lblsize.Text = Library.FormatSize(temp.Get("sFileSize"))
		pnlbooks.Tag = temp
		pnlover.Tag = temp
		btndownload.Tag = temp.Get("sID")
		pnlbooks.Tag = pb1
		sv1.Panel.Height = sv1.Panel.Height + p1.Height
		
		If File.Exists(File.DirInternal & "/book/" & temp.Get("sID"),"bank.db") Then
			btndownload.Text = ""
			btndownload.TextColor = Colors.Green
		End If
		
	Next
	
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)
	Library.Right2LeftAnimation
End Sub

Sub btndownload_Click
	
	Dim b As Label
	b = Sender
	Dim sID As String
	sID = b.Tag
	
	If File.Exists(File.DirInternal & "/book/" & sID,"bank.db") Then
		ToastMessageShow("این کتاب قبلا دانلود شده است.میتوانید کتاب را مرور کنید",False)
		Return
	End If
	
	currentFileChoosen = sID
	Dim hu As HttpJob
	hu.Initialize(sID,Me)
	pb1.Visible = True
	currentPB = pb1
	CurrentDownloadLabel = b
	b.Visible = False
	hu.Download(Library.URL.Replace("index.php/server/","/") & "books/book_" & sID & ".zip")
	
End Sub

Sub JobDone(Job As HttpJob)
	
	ProgressDialogHide
	
	If Job.Success Then

		If IsNumber(Job.JobName) Then
			
			CurrentDownloadLabel.Visible = True
			currentPB.Visible = False
	
			Dim ou As OutputStream
			ou = File.OpenOutput(File.DirInternalCache,"temp.zip",False)
			File.Copy2(Job.GetInputStream,ou)
			ou.Close
			
			CurrentDownloadLabel.Text = ""
			CurrentDownloadLabel.TextColor = Colors.Green
			
			File.MakeDir(File.DirInternal,"book/" & Job.JobName)
			Dim zip As ABZipUnzip
			zip.ABUnzip(File.Combine(File.DirInternalCache,"temp.zip"),File.Combine(File.DirInternal,"book/" & Job.JobName))
			'zip.ABUnzip(File.Combine(File.DirInternalCache,"temp.zip"),File.DirRootExternal)
			DateTime.DateFormat = "yyyy-mm-dd"
			File.WriteString(File.DirInternal & "/book/" & Job.JobName,"date",DateTime.Date(DateTime.Now))
			
			send_request.data = currentFileChoosen
			send_request.event = "ModifyBookDownloads"
			StartService(send_request)
			
			Progressbar1.HideProgress
			ToastMessageShow("کتاب با موفقیت دانلود شد",False)
			actMenu.DownloadedBook = True
			
			If File.Exists(File.DirInternal,"visit_book1") = False Then
				Library.WriteCounterReadBook(Job.JobName)
			End If
			
			Dim db1 As Categories
			db1.Initialize
			db1.DownloadedBook(currentFileChoosen,"1")
		
		Else If Job.JobName = "modifybook" Then
			Dim js As JSONParser
			js.Initialize(Job.GetString)
			Dim res As Map = js.NextObject
			If res.IsInitialized Then
				Log(res)
			End If
		
		Else If Job.JobName = "cat" Then
			Dim js As JSONParser
			js.Initialize(Job.GetString)
			Dim res As Map = js.NextObject
			If res.IsInitialized Then
				If res.Get("success") = True Then
					Dim ls As List
					ls = res.Get("result")
					dbCat.AddCategory(ls)
					'AddCategories(dbCat.GetCategory(0))
					ProgressDialogShow("لیست کردن...")
					Dim hu As HttpJob
					hu.Initialize("book",Me)
					hu.PostString(Library.URL & "list_book","")
				End If
			End If
		
		Else If Job.JobName = "book" Then
			Dim js As JSONParser
			js.Initialize(Job.GetString)
			Dim res As Map = js.NextObject
			If res.IsInitialized Then
				If res.Get("success") = True Then
					Dim ls As List
					ls = res.Get("result")
					dbBook.Initialize
					dbBook.AddBook(ls)
					LoadItem
				End If
			End If
		End If
	
	Else
		Log(Job.ErrorMessage)
		Progressbar1.HideProgress
	End If

End Sub

Sub btnbook_Click
	
End Sub

Sub btncategory_Click
	
	Dim v1 As View
	v1 = Sender
	Dim val As Map
	val = Library.GetParent(v1).Tag
	
	If val.ContainsKey("state") Then
		Dim val As Map
		val = stack.Get(stack.Size-1)
		stack.RemoveAt(stack.Size-1)
		LoadCategories(val.Get("sParent"))
	Else
		stack.Add(val)
		LoadCategories(val.Get("sID"))
	End If
	
End Sub

Sub btnsearch_Click
	
	If pnlsearch.Top < 0 Then
		btnmenu.Visible = False
		lbltitle.Visible = False
		pnlsearch.SetLayoutAnimated(500,pnlsearch.Left,0,pnlsearch.Width,pnlsearch.Height)
	Else
		pnlsearch.SetLayoutAnimated(500,pnlsearch.Left,-pnlsearch.Height,pnlsearch.Width,pnlsearch.Height)
		btnmenu.Visible = True
		lbltitle.Visible = True
	End If
	
End Sub

Sub btnfilter_Click
	rs.Show
End Sub

Sub rspopup_MenuItemClick (ItemId As Int) As Boolean
	
	sv1.Panel.RemoveAllViews
	top = 0
	
	Dim lang As String
	Select ItemId
		Case 1
			lang = "fa"
		Case 2
			lang = "en"
		Case 3
			lang = "ar"
		Case 4
			FilterLangauge = False
			FilterLanguageVal = ""
			LoadItem
			Return
	End Select
	
	FilterLangauge = True
	FilterLanguageVal = lang
	
	LoadItem
	
End Sub

Sub txtsearch_EnterPressed
	
	If txtsearch.Text.Length = 0 Then
		ToastMessageShow("لطفا قسمتی از عبارت را وارد کنید",False)
		Return
	End If
	
	FilterLangauge = False
	FilterLanguageVal = txtsearch.Text
	
	sv1.Panel.RemoveAllViews
	top = 0
	
	Dim dbb As Categories
	dbb.Initialize
	AddBooks(dbb.GetBook(0,txtsearch.Text,False))
	
End Sub

Sub pnlover_Click
	
	Dim v1 As View
	v1 = Sender
	Dim m As Map
	m = v1.Tag
	actBookInformation.mu = m
	StartActivity(actBookInformation)
	
End Sub