Type=StaticCode
Version=4.01
ModulesStructureVersion=1
B4J=true
@EndOfDesignText@
'Static code module
Sub Process_Globals
	Private fx As JFX
	Private frm1 As Form
	Private lvbooks As ListView
	Dim dbCat,dbBook As Categories
	Public ForceUpdate As Boolean
	Private lblbookname As Label
	Private imgicon As ImageView
	Private pnlover As Pane
	Private FilterLanguageVal As String
	Private FilterLangauge As Boolean
	Private stack As List
	Private currentCatID,currentFileChoosen As String
	Private lblcatname As Label
	Private lblbookextra As Label
	Private btndownload As Button
	Private lblsize As Label
	Private pb1 As ImageView
	Private currentPB As ImageView
	Private CurrentDownloadLabel As Button
	Private pnloverbook As Pane
	Private loading As Loading
	Private IsChange As Boolean
End Sub

Sub ShowDownload
	
	If frm1.IsInitialized = False Then
		frm1.Initialize("frm1",470,594)
	End If
	
	frm1.Title = "دانلود کتاب"
	frm1.Resizable = False
	frm1.RootPane.LoadLayout("frmdownloadbook")
	frm1.SetOwner(Main.MainForm)
	frm1.Show
	Library.CenterFormOnScreen(frm1)
	
	dbCat.Initialize
	stack.Initialize
	
	If dbCat.GetBookCount = 0 Or ForceUpdate = True Then
		dbCat.Initialize
		frm1.RootPane.Enabled = False
		Dim hu As HttpJob
		hu.Initialize("cat",Me)
		hu.PostString(Library.URL & "list_category","")
		ForceUpdate = False
	Else
		LoadItem
	End If
	
	loading.Initialize(frm1)
	
End Sub

Sub LoadItem
	AddCategories(dbCat.GetCategory(0))
	AddBooks(dbCat.GetBook(0,FilterLanguageVal,FilterLangauge))
End Sub

Sub LoadCategories(sID As String)
	
	lvbooks.Items.Clear
			
	If stack.Size > 0 Then
		Dim p1 As Pane
		p1.Initialize("")
		p1.LoadLayout("frmtemplate_category")
		p1.SetSize(350,60)
		lvbooks.Items.Add(p1)
		
		Dim temp As Map
		temp = stack.Get(stack.Size-1)
		
		lblcatname.Text = "برگشت به دسته بندی قبلی"
		temp.Put("state","return")
		pnlover.Tag = temp
		
	End If
	
	currentCatID = sID
	
	AddCategories(dbCat.GetCategory(currentCatID))
	AddBooks(dbCat.GetBook(currentCatID,FilterLanguageVal,FilterLangauge))
	
End Sub

Sub AddCategories(List1 As List)
	
	For i = 0 To List1.Size - 1
		Dim p1 As Pane
		p1.Initialize("")
		p1.LoadLayout("frmtemplate_category")
		p1.SetSize(400,60)
		lvbooks.Items.Add(p1)
		
		Dim temp As Map
		temp = List1.Get(i)
		
		lblcatname.Text = temp.Get("sTitle")
		'lblcount_book.Text = "تعداد کتاب : " & temp.Get("sCount")
		pnlover.Tag = temp
		
	Next
	
End Sub

Sub AddBooks(List1 As List)
	
	For i = 0 To List1.Size - 1
		
		Dim temp As Map
		temp = List1.Get(i)
	
		Dim p1 As Pane
		p1.Initialize("")
		p1.LoadLayout("frmbook_template")
		p1.SetSize(400,120)
		lvbooks.Items.Add(p1)
		
		lblbookname.Text = temp.Get("sTitle")
		lblbookextra.Text = temp.Get("sAuthor") & " تعداد دانلود : " & temp.Get("sDownload")
		lblsize.Text = Library.FormatSize(temp.Get("sFileSize"))
		pnloverbook.Tag = temp
		btndownload.Tag = temp.Get("sID")
		lblbookname.Tag = pb1
		
		If File.Exists(File.DirApp & "/Data" & "/book/" & temp.Get("sID"),"bank.db") Then
			btndownload.Text = ""
			btndownload.Font = fx.LoadFont(File.DirAssets,"icomoon.ttf",14)
			btndownload.TextColor = fx.Colors.Green
		End If
		
	Next
	
End Sub

Sub JobDone(Job As HttpJob)
	
	loading.Hide
	frm1.RootPane.Enabled = True
	
	If Job.Success Then

		If IsNumber(Job.JobName) Then
			
			Try
				CurrentDownloadLabel.Visible = True
				currentPB.Visible = False
				currentPB.SetImage(fx.LoadImage(File.DirAssets,"ok.png"))
				CurrentDownloadLabel.Text = ""
				btndownload.Font = fx.LoadFont(File.DirAssets,"icomoon.ttf",14)
				CurrentDownloadLabel.TextColor = fx.Colors.Green
			Catch
			End Try
			
			Dim ou As OutputStream
			ou = File.OpenOutput(File.DirApp & "/Data","temp.zip",False)
			File.Copy2(Job.GetInputStream,ou)
			ou.Close
			
			File.MakeDir(File.DirApp & "/Data/book",Job.JobName)
			Dim zip As ABZipUnzip
			zip.ABUnzip(File.Combine(File.DirApp & "/Data","temp.zip"),File.Combine(File.DirApp & "/Data","book/" & Job.JobName))
			DateTime.DateFormat = "yyyy-mm-dd"
			File.WriteString(File.DirApp & "/Data/book/" & Job.JobName,"date",DateTime.Date(DateTime.Now))
			File.Delete(File.DirApp & "/Data","temp.zip")
			
			Dim hu As HttpJob
			hu.Initialize("modifybook",Me)
			hu.PostString(Library.URL & "add_download/" & currentFileChoosen,"")
			
			IsChange = True
			
			fx.Msgbox(frm1,"کتاب با موفقیت دانلود شد","توجه")
			
			If File.Exists(File.DirApp & "/Data","visit_book1") = False Then
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
					frm1.RootPane.Enabled = False
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
	End If

End Sub

Sub pnloverbook_MouseClicked (EventData As MouseEvent)
	
	Dim v1 As Pane
	v1 = Sender
	Dim m As Map
	m = v1.Tag
	actBookInformation.mu = m
	actBookInformation.ShowInformation
	
End Sub

#Region Menu Filter
Sub mnufa_Action
	
	lvbooks.Items.Clear
	stack.Clear
	FilterLangauge = True
	FilterLanguageVal = "fa"
	
	LoadItem
	
End Sub

Sub mnuen_Action
	
	lvbooks.Items.Clear
	stack.Clear
	FilterLangauge = True
	FilterLanguageVal = "en"
	
	LoadItem
	
End Sub

Sub mnuar_Action
	
	lvbooks.Items.Clear
	stack.Clear
	FilterLangauge = True
	FilterLanguageVal = "ar"
	
	LoadItem
	
End Sub

Sub mnuall_Action
	
	lvbooks.Items.Clear
	FilterLangauge = False
	FilterLanguageVal = ""
	stack.Clear
	
	LoadItem
	
End Sub

Sub mnusearch_Action
	
	Dim search As String
	Dim dialog As Dialogs8
	dialog.Initialize
	search = dialog.TextInputDialog("جستجوی","لطفا قسمتی از نام کتاب را وارد کنید","","اینجا")
	
	If search.Length = 0 Then
		dialog.WarningDialog("توجه","","لطفا قسمتی از عبارت را وارد کنید")
		Return
	End If
	
	FilterLangauge = False
	FilterLanguageVal = search
	
	lvbooks.Items.Clear
	
	Dim dbb As Categories
	dbb.Initialize
	AddBooks(dbb.GetBook(0,search,False))
	
End Sub
#End Region

Sub pnlover_MouseClicked (EventData As MouseEvent)
	
	Dim v1 As Pane
	v1 = Sender
	Dim val As Map
	val = v1.Tag
	
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

Sub btndownload_Action
		
	Dim b As Button
	b = Sender
	Dim sID As String
	sID = b.Tag

	If File.Exists(File.DirApp & "/Data" & "/book/" & sID,"bank.db") Then
		fx.Msgbox(frm1,"این کتاب قبلا دانلود شده است.میتوانید کتاب را مرور کنید","توجه")
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

Sub DownloadBookFromNet(sID As String)
	
	currentFileChoosen = sID
	
	loading.Show("در حال دانلود")
	
	Dim hu As HttpJob
	hu.Initialize(sID,Me)
	hu.Download(Library.URL.Replace("index.php/server/","/") & "books/book_" & sID & ".zip")
	
End Sub

Sub frm1_CloseRequest (EventData As Event)
	
	If IsChange Then
		IsChange = False
		CallSubDelayed(Main,"LoadItem")
	End If
	
End Sub