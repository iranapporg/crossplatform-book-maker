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
	Public DownloadedBook As Boolean
End Sub

Sub Globals
	Dim ph As Phone
	Private IME1 As IME
	Private sv1,sv3 As ScrollView
	Private img2 As ImageView
	Private img3 As ImageView
	Private img1 As ImageView
	Private menu As SlideMenu
	Private topMainPage As Int
	Private currentBookDownloaded As View
	Private pnlheader As Panel
	Private pnlitem As Panel
	Private pnl As Panel
	Private AHContain As AHPageContainer
	Private AHPager As AHViewPager
	Dim pMain,pDownload As Panel
	Private lbltitle As Label
	Private lblname As Label
	Private lblcomment As Label
	Dim type_list As String
	Dim listTopBook As List
	Private pnlinfbook As Panel
	Private lblbookname As Label
	Private btndelete As Button
	Private btninformation As Button
	Private btnmore As Button
	Dim rs As RSPopupMenu
	Dim filter_download As String
	Private svsearch As ScrollView
	Private btnclosesearch As Button
End Sub

Sub Activity_Create(FirstTime As Boolean)
	
	Activity.LoadLayout("frmmenu")
	menu.Initialize(pnlheader.Height,Me)
	
	IME1.Initialize("")
	AHContain.Initialize
	
	Try
		If ph.SdkVersion > 10 Then
			rs.Initialize("btnmore",btnmore)
			rs.AddMenuItem(1,1,"جستجوی")
		Else
			Activity.AddMenuItem("جستجوی","btnsearch")
			Activity.AddMenuItem("فیلتر بر اساس نویسنده","btnfilter_author")
			Activity.AddMenuItem("فیلتر بر اساس موضوع","btnfilter_topic")
			Activity.AddMenuItem("نمایش همه کتاب ها","btnfilter_all")
		End If
	Catch
		
	End Try
	
	pMain.Initialize("")
	pDownload.Initialize("")
	
	AHContain.AddPage(pMain,"")
	AHContain.AddPage(pDownload,"")
	AHPager.Initialize(AHContain,"Pagers")
	pnl.AddView(AHPager,0,0,pnl.Width,pnl.Height)
	
	Dim w,h As Int
	w = pnl.Width
	h = pnl.Height
	
	sv1.Initialize(0)
	sv3.Initialize(0)
	
	pMain.AddView(sv1,0,0,w,h)
	sv1.Panel.Color = Colors.Transparent
	sv1.SetBackgroundImage(LoadBitmap(File.DirAssets,"wooden-panels-texture.jpg"))
	
	pDownload.AddView(sv3,0,0,w,h)
	sv3.Panel.Color = Colors.Transparent
	sv3.SetBackgroundImage(LoadBitmap(File.DirAssets,"wooden-panels-texture.jpg"))
	
	svsearch.Panel.Color = Colors.Transparent
	svsearch.SetBackgroundImage(LoadBitmap(File.DirAssets,"wooden-panels-texture.jpg"))
	
	Dim hu As HttpJob
	hu.Initialize("book",Me)
	hu.PostString(Library.URL & "top_book","")
	
	type_list = Library.Manager.GetString("show_types")
	
	Downloads("","")
	LastVisit
	
End Sub

Sub btnfilter_author_Click
	btnmore_MenuItemClick(2)
End Sub

Sub btnfilter_topic_Click
	btnmore_MenuItemClick(3)
End Sub

Sub btnfilter_all_Click
	btnmore_MenuItemClick(4)	
End Sub

Sub btnsearch_Click
	btnmore_MenuItemClick(1)
End Sub

Sub Pagers_PageChanged (Position As Int)
	
	If Position = 0 Then
		lbltitle.Text = "کتاب ها"
		rs.Initialize("btnmore",btnmore)
		rs.AddMenuItem(1,1,"جستجوی")
	else if Position = 1 Then
		lbltitle.Text = "دانلود شده ها"
		rs.Initialize("btnmore",btnmore)
		rs.AddMenuItem(1,1,"جستجوی")
		rs.AddMenuItem(2,2,"فیلتر بر اساس نویسنده")
		rs.AddMenuItem(3,3,"فیلتر بر اساس موضوع")
		rs.AddMenuItem(4,4,"نمایش همه کتاب ها")
	End If
	
End Sub

Sub JobDone(Job As HttpJob)
	
	ProgressDialogHide
	
	If Job.Success Then
		If Job.JobName = "book" Then
			
			Dim js As JSONParser
			js.Initialize(Job.GetString)
			Dim res As Map = js.NextObject
	
			If res.IsInitialized Then
				If res.Get("success") = True Then
					Dim ls As List
					ls = res.Get("result")
					listTopBook = ls
					TopBooks
				End If
			End If
		
		else If IsNumber(Job.JobName) Then
			
			currentBookDownloaded.Tag = Job.JobName
			
			Dim ou As OutputStream
			ou = File.OpenOutput(File.DirInternalCache,"temp.zip",False)
			File.Copy2(Job.GetInputStream,ou)
			ou.Close
			
			File.MakeDir(File.DirInternal,"book/" & Job.JobName)
			Dim zip As ABZipUnzip
			zip.ABUnzip(File.Combine(File.DirInternalCache,"temp.zip"),File.Combine(File.DirInternal,"book/" & Job.JobName))
			DateTime.DateFormat = "yyyy-mm-dd"
			File.WriteString(File.DirInternal & "/book/" & Job.JobName,"date",DateTime.Date(DateTime.Now))
			ToastMessageShow("کتاب با موفقیت دانلود شد",False)
			Dim db1 As Categories
			db1.Initialize
			db1.DownloadedBook(Job.JobName,"1")
			
		End If
	End If
	
End Sub

Sub Activity_Resume
	
	IME1.HideKeyboard
	
	If ResultSearch.SearchState = ResultSearch.SS_RESULT_SUCCESS Then
		Dim ressearch As String
		ressearch = ResultSearch.SearchResult
		ResultSearch.SearchState = ResultSearch.SS_NONE
		Dim DBRes As Categories
		Dim ls As List
		DBRes.Initialize
		ls = DBRes.SearchWordBooks(ressearch)
		If ls.IsInitialized Then SearchBook(ls)
	End If
	
	If DownloadedBook = True Then
		DownloadedBook = False
		Downloads("","")
		topMainPage = 0
		LastVisit
		TopBooks
	End If
	
	Dim temp As String
	temp = Library.Manager.GetString("show_types")
	
	If temp <> type_list Then
		type_list = temp
		Downloads("","")
		LastVisit
		If listTopBook.IsInitialized Then TopBooks
	End If
	
End Sub

Sub Activity_Pause (UserClosed As Boolean)
	
	If UserClosed = False Then
		Library.Left2RightAnimation
	End If
	
End Sub

Sub btndownload_Click
	StartActivity(actDownload)
End Sub

Sub TopBooks
	
	Dim image As Map
	image.Initialize

	Dim pic As Picasso
	pic.Initialize

	#region AddBook
	Dim book As Categories
	book.Initialize
	
	topMainPage = topMainPage + 20
	
	Dim lb As Label
	lb.Initialize("")
	lb.TextColor = Colors.Black
	lb.Typeface = Typeface.LoadFromAssets("byekan.ttf")
	lb.Text = "   کتاب های محبوب"
	sv1.Panel.AddView(lb,0,topMainPage,sv1.Width,30dip)
	topMainPage = topMainPage + 30dip
	
	If listTopBook.IsInitialized = False Then Return
	
	For i = 0 To listTopBook.Size - 1

		If i >= 6 Then Exit
		
		Dim p As Panel
		p.Initialize("")
		sv1.Panel.AddView(p,0,topMainPage,sv1.Width,0)
		If type_list = "جدولی" Or type_list = "" Then 
			p.LoadLayout("frmtemplate_box")
		Else
			p.LoadLayout("frmtemplate_book_list")
		End If
		p.Height = pnlitem.Height + pnlitem.top
		
		Dim m1 As Map
		m1 = listTopBook.Get(i)
		
		img1.Visible = True
		pic.LoadUrl(Library.BaseUrl & m1.Get("sCover")).Resize(img1.Width,img1.Height).CenterCrop.IntoImageView(img1)
		
		If File.Exists(File.DirInternal & "/book/" & m1.Get("sID"),"bank.db") Then
			img1.Tag = m1.Get("sID")
		Else
			img1.Tag = listTopBook.Get(i)
		End If
		
		If type_list = "لیستی" Then 
			Dim ms As Map
			ms = book.GetBookInformation(m1.Get("sID"))
			lblname.Text = ms.Get("sTitle")
			Dim desc As String
			desc = ms.Get("sDescription")
			If File.Exists(File.DirInternal & "/book/" & m1.Get("sID"),"bank.db") Then
				pnlitem.Tag = m1.Get("sID")
			Else
				pnlitem.Tag = listTopBook.Get(i)
			End If
			If desc.Length > 40 Then desc = desc.SubString2(0,40) & "..."
			lblcomment.Text = desc
			topMainPage = topMainPage + p.Height + 10
			Continue	
		End If

		If i + 1 > listTopBook.Size - 1 Then
			topMainPage = topMainPage + p.Height + 10
			Exit
		End If
		
		img2.Visible = True
		
		Dim m2 As Map
		m2 = listTopBook.Get(i+1)
		pic.LoadUrl(Library.BaseUrl & m2.Get("sCover")).Resize(img2.Width,img2.Height).CenterCrop.IntoImageView(img2)
		
		If File.Exists(File.DirInternal & "/book/" & m2.Get("sID"),"bank.db") Then
			img2.Tag = m2.Get("sID")
		Else
			img2.Tag = listTopBook.Get(i)
		End If
		
		img2.Tag = listTopBook.Get(i+1)
		
		If i + 2 > listTopBook.Size - 1 Then
			topMainPage = topMainPage + p.Height + 10
			Exit
		End If
		
		img3.Visible = True
		
		Dim m3 As Map
		m3 = listTopBook.Get(i+2)
		pic.LoadUrl(Library.BaseUrl & m3.Get("sCover")).Resize(img3.Width,img3.Height).CenterCrop.IntoImageView(img3)
		
		If File.Exists(File.DirInternal & "/book/" & m3.Get("sID"),"bank.db") Then
			img3.Tag = m3.Get("sID")
		Else	
			img1.Tag = listTopBook.Get(i)
		End If
		
		img3.Tag = listTopBook.Get(i+2)
		
	Next

	sv1.Panel.Height = topMainPage
	#end region	
	
End Sub

Sub SearchBook(ls As List)
	
	If File.Exists(File.DirInternal & "/book","") = False Then Return
	
	Dim top As Int
	svsearch.Panel.RemoveAllViews
	top = 0
	
	svsearch.SetVisibleAnimated(500,True)
	btnclosesearch.Visible = True
	btnmore.Visible = False
	
	Dim book As Categories
	book.Initialize
	
	For i = 0 To ls.Size - 1
		
		Dim p As Panel
		p.Initialize("")
		svsearch.Panel.AddView(p,0,top,svsearch.Width,0)
		
		If type_list = "جدولی" Or type_list = "" Then 
			p.LoadLayout("frmtemplate_box")
		Else
			p.LoadLayout("frmtemplate_book_list")
		End If
		
		p.Height = pnlitem.Height + pnlitem.top
		
		img1.Visible = True
	
		
		If File.Exists(File.DirInternal & "/book/" & ls.Get(i),ls.Get(i) & ".jpg") Then
			img1.SetBackgroundImage(LoadBitmap(File.DirInternal & "/book/" & ls.Get(i),ls.Get(i) & ".jpg"))
		Else
			img1.SetBackgroundImage(LoadBitmap(File.DirAssets,"no_cover.png"))
		End If
		
		img1.Tag = ls.Get(i)
		
		If type_list = "لیستی" Then 
			Dim ms As Map
			ms = book.GetBookInformation(ls.Get(i))
			lblname.Text = ms.Get("sTitle")
			Dim desc As String
			desc = ms.Get("sDescription")
			If desc.Length > 40 Then desc = desc.SubString2(0,40) & "..."
			lblcomment.Text = desc
			pnlitem.Tag = ls.Get(i)
			top = top + p.Height + 10
			Continue	
		End If
		
		If i + 1 > ls.Size - 1 Then
			top = top + p.Height + 10
			Exit
		End If
		
		img2.Visible = True
		
		If File.Exists(File.DirInternal & "/book/" & ls.Get(i+1),ls.Get(i+1) & ".jpg") Then
			img2.SetBackgroundImage(LoadBitmap(File.DirInternal & "/book/" & ls.Get(i+1),ls.Get(i+1) & ".jpg"))
		Else
			img2.SetBackgroundImage(LoadBitmap(File.DirAssets,"no_cover.png"))
		End If
		
		img2.Tag = ls.Get(i+1)
		
		If i + 2 > ls.Size - 1 Then
			top = top + p.Height + 10
			Exit
		End If
		
		img3.Visible = True
		
		If File.Exists(File.DirInternal & "/book/" & ls.Get(i+2),ls.Get(i+2) & ".jpg") Then
			img3.SetBackgroundImage(LoadBitmap(File.DirInternal & "/book/" & ls.Get(i+2),ls.Get(i+2) & ".jpg"))
		Else
			img3.SetBackgroundImage(LoadBitmap(File.DirAssets,"no_cover.png"))
		End If
		
		img3.Tag = ls.Get(i+2)
		
	Next
	
	svsearch.Panel.Height = top
	
End Sub

Sub Downloads(FilterName As String,FilterValue As String)
	
	If File.Exists(File.DirInternal & "/book","") = False Then Return
	
	Dim ls As List
	Dim dbDownload As Categories
	dbDownload.Initialize
	
	ls = dbDownload.GetDownloadedBook(FilterName,FilterValue)
	
	Dim top As Int
	sv3.Panel.RemoveAllViews
	top = 0
	
	For i = 0 To ls.Size - 1
		
		Dim p As Panel
		p.Initialize("")
		sv3.Panel.AddView(p,0,top,sv1.Width,0)
		
		If type_list = "جدولی" Or type_list = "" Then 
			p.LoadLayout("frmtemplate_box")
		Else
			p.LoadLayout("frmtemplate_book_list")
		End If
		
		p.Height = pnlitem.Height + pnlitem.top
		
		img1.Visible = True
		
		Dim ms As Map
		ms = ls.Get(i)
		
		If File.Exists(File.DirInternal & "/book/" & ms.Get("sID"),ms.Get("sID") & ".jpg") Then
			img1.SetBackgroundImage(LoadBitmap(File.DirInternal & "/book/" & ms.Get("sID"),ms.Get("sID") & ".jpg"))
		Else
			img1.SetBackgroundImage(LoadBitmap(File.DirAssets,"no_cover.png"))
		End If
		
		img1.Tag = ms.Get("sID")
		
		If type_list = "لیستی" Then 
			lblname.Text = ms.Get("sTitle")
			Dim desc As String
			desc = ms.Get("sDescription")
			If desc.Length > 40 Then desc = desc.SubString2(0,40) & "..."
			lblcomment.Text = desc
			pnlitem.Tag = ms.Get("sID")
			top = top + p.Height + 10
			Continue	
		End If
		
		If i + 1 > ls.Size - 1 Then
			top = top + p.Height + 10
			Exit
		End If
		
		img2.Visible = True
		
		Dim ms1 As Map
		ms1 = ls.Get(i+1)
		
		If File.Exists(File.DirInternal & "/book/" & ms1.Get("sID"),ms1.Get("sID") & ".jpg") Then
			img2.SetBackgroundImage(LoadBitmap(File.DirInternal & "/book/" & ms1.Get("sID"),ms1.Get("sID") & ".jpg"))
		Else
			img2.SetBackgroundImage(LoadBitmap(File.DirAssets,"no_cover.png"))
		End If
		
		img2.Tag = ms1.Get("sID")
		
		If i + 2 > ls.Size - 1 Then
			top = top + p.Height + 10
			Exit
		End If
		
		img3.Visible = True
		
		Dim ms2 As Map
		ms2 = ls.Get(i+2)
		
		If File.Exists(File.DirInternal & "/book/" & ms2.Get("sID"),ms2.Get("sID") & ".jpg") Then
			img3.SetBackgroundImage(LoadBitmap(File.DirInternal & "/book/" & ms2.Get("sID"),ms2.Get("sID") & ".jpg"))
		Else
			img3.SetBackgroundImage(LoadBitmap(File.DirAssets,"no_cover.png"))
		End If
		
		img3.Tag = ms2.Get("sID")
		
	Next
	
	sv3.Panel.Height = top
	
End Sub

Sub LastVisit
	
	Dim ls As Map
	ls = Library.GetTopReadBook
	
	If ls = Null Then Return
	If ls.IsInitialized = False Then Return
	
	Dim book As Categories
	book.Initialize
	
	sv1.Panel.RemoveAllViews
	topMainPage = 0
	
	For i = 0 To ls.Size - 1
		
		If i >= 6 Then Exit
		
		If File.Exists(File.DirInternal & "/book/" & ls.GetKeyAt(i),"bank.db") = False Then Continue
		
		Dim p As Panel
		p.Initialize("")
		sv1.Panel.AddView(p,0,topMainPage,sv1.Width,0)
		
		If type_list = "لیستی" Then
			p.LoadLayout("frmtemplate_book_list")
		Else
			p.LoadLayout("frmtemplate_box")
		End If
		
		p.Height = pnlitem.Height + pnlitem.top
		
		img1.Visible = True
		
		If File.Exists(File.DirInternal & "/book/" & ls.GetKeyAt(i),"cover.jpg") Then
			img1.SetBackgroundImage(LoadBitmap(File.DirInternal & "/book/" & ls.GetKeyAt(i),"cover.jpg"))
		Else
			img1.SetBackgroundImage(LoadBitmap(File.DirAssets,"no_cover.png"))
		End If
		
		img1.Tag = ls.GetKeyAt(i)
		
		If type_list = "لیستی" Then 
			Dim ms As Map
			ms = book.GetBookInformation(ls.GetKeyAt(i))
			lblname.Text = ms.Get("sTitle")
			Dim desc As String
			desc = ms.Get("sDescription")
			pnlitem.Tag = ls.GetKeyAt(i)
			If desc.Length > 40 Then desc = desc.SubString2(0,40) & "..."
			lblcomment.Text = desc
			topMainPage = topMainPage + p.Height + 10
			Continue	
		End If
		
		If i + 1 > ls.Size - 1 Then
			topMainPage = topMainPage + p.Height + 10
			Exit
		End If
		
		img2.Visible = True
		
		If File.Exists(File.DirInternal & "/book/" & ls.GetKeyAt(i+1),ls.GetKeyAt(i+1) & ".jpg") Then
			img2.SetBackgroundImage(LoadBitmap(File.DirInternal & "/book/" & ls.GetKeyAt(i+1),ls.GetKeyAt(i+1) & ".jpg"))
		Else
			img2.SetBackgroundImage(LoadBitmap(File.DirAssets,"no_cover.png"))
		End If
		
		img2.Tag = ls.GetKeyAt(i+1)
		
		If i + 2 > ls.Size - 1 Then
			topMainPage = topMainPage + p.Height + 10
			Exit
		End If
		
		img3.Visible = True
		
		If File.Exists(File.DirInternal & "/book/" &ls.GetKeyAt(i+2),ls.GetKeyAt(i+2) & ".jpg") Then
			img3.SetBackgroundImage(LoadBitmap(File.DirInternal & "/book/" & ls.GetKeyAt(i+2),ls.GetKeyAt(i+2) & ".jpg"))
		Else
			img3.SetBackgroundImage(LoadBitmap(File.DirAssets,"no_cover.png"))
		End If
		
		img3.Tag = ls.GetKeyAt(i+2)
		
	Next
	
	sv1.Panel.Height = topMainPage
	
End Sub

Sub img1_Click
	
	Dim im As View
	im = Sender
	
	If im.Tag Is Map Then
		Dim tem As Map
		tem = im.Tag
		currentBookDownloaded = im
		Dim hu As HttpJob
		hu.Initialize(tem.Get("sID"),Me)
		ProgressDialogShow("در حال دانلود کتاب...")
		hu.Download(Library.BaseURL & "books/book_" & tem.Get("sID") & ".zip")
	Else
		Library.WriteCounterReadBook(im.Tag)
		actBookChapter.sBookID = im.Tag
		StartActivity(actBookChapter)	
	End If
	
End Sub

Sub img1_LongClick
	
	Dim im As View
	im = Sender
	
	If AHPager.CurrentPage <> 1 Then
		Return
	End If
	
	pnlinfbook.SetLayoutAnimated(500,pnlinfbook.Left,0,pnlinfbook.Width,pnlinfbook.Height)
	
	Dim data As Map
	Dim db As Categories
	db.Initialize
	
	data = db.GetBookInformation(im.Tag)
	
	lblbookname.Text = data.Get("sTitle")
	btndelete.Tag = im.Tag
	btninformation.Tag = im.Tag
	
End Sub

Sub btnmenu_Click
	
	If ph.SdkVersion > 10 Then
		menu.Show
	Else
		Activity.OpenMenu
	End If
	
End Sub

Sub btnlastvisit_Click
	
	If File.Exists(File.DirInternal,"lastbook") Then
		If File.Exists(File.DirInternal,"lasttopic") Then
			Dim BookID,TopicId As String
			BookID = File.ReadString(File.DirInternal,"lastbook")
			TopicId = File.ReadString(File.DirInternal,"lasttopic")
			
			Dim db As Database
			Dim temp As Map
			db.Initialize(BookID)
			temp = db.ChapterContent(TopicId)
			
			Dim lastpage As Map
			lastpage = Library.LastPageVisit(BookID,TopicId,"",True)
		
			If temp.IsInitialized Then
				
				temp.Put("sBookID",BookID)
				actContent2.Data = temp
				actContent2.BookID = BookID
				If lastpage.IsInitialized Then actContent2.RefererPage = lastpage.Get("Page")
				StartActivity(actContent2)
				
			End If
			
		End If
	End If
	
End Sub

Sub GoPage(Page As Int)
	AHPager.GotoPage(Page,True)
End Sub

Sub btninformation_Click
	
	btnback_Click
	
	Dim db As Categories
	db.Initialize
	Dim m As Map
	m = db.GetBookInformation(btninformation.Tag)
	actBookInformation.mu = m
	StartActivity(actBookInformation)
	
End Sub

Sub btndelete_Click
	
	btnback_Click
	
	Dim db As Categories
	db.Initialize
	db.DownloadedBook(btndelete.Tag,"0")
	
	Dim sa As List
	sa = File.ListFiles(File.DirInternal & "/book/" & btndelete.Tag)
	
	If sa.IsInitialized Then
		For i = 0 To sa.Size - 1
			File.Delete(File.DirInternal & "/book/" & btndelete.Tag,sa.Get(i))
		Next
	End If
	
	Downloads("","")
	LastVisit
	
End Sub

Sub btnback_Click
	pnlinfbook.SetLayoutAnimated(500,pnlinfbook.Left,-pnlinfbook.Height,pnlinfbook.Width,pnlinfbook.Height)
End Sub

Sub btnmore_Click
	
	If ph.SdkVersion > 10 Then
		rs.Show
	Else
		Activity.OpenMenu
	End If
	
End Sub

Sub btnmore_MenuItemClick (ItemId As Int) As Boolean
	
	If ItemId = 1 Then
		Search
		
	Else if ItemId = 2 Then
		Dim db As Categories
		Dim id As id
		Dim ls As List
		Dim res As Int
		
		db.Initialize
		ls = db.GetBookAuthor
		
		If ls.IsInitialized Then
			res = id.InputList1(ls,"نویسنده را انتخاب کنید")
		End If
		
		If res < 0 Then Return True
		
		Dim auth As String
		auth = ls.Get(res)
		Downloads("sAuthor",auth)
		
	Else if ItemId = 3 Then
		Dim db1 As Categories
		Dim id1 As id
		Dim ls1 As List
		Dim res1 As Int
		
		db1.Initialize
		ls1 = db1.GetBookTopic
		
		If ls1.IsInitialized Then
			res1 = id1.InputList1(ls1,"موضوع را انتخاب کنید")
		End If
		
		If res1 < 0 Then Return True
		
		Dim title As String
		title = ls1.Get(res1)
		Downloads("sTitle",title)
		
	Else if ItemId = 4 Then
		Downloads("","")
	End If
	
End Sub

Sub NewBookFound
	
	If Msgbox2("لیست کتاب ها اخیرا تغییر کرده است.آیا مایلید بروزرسانی انجام دهید؟","توجه","بله","خیر","",Null) = DialogResponse.POSITIVE Then
		File.Delete(File.DirInternal,"data.db")
		actDownload.ForceUpdate = True
		StartActivity(actDownload)
	End If
	
End Sub

Sub Search
	RequestSearchBar
End Sub

Sub RequestSearchBar
	Dim ref As Reflector
	ref.Target = ref.GetActivity
	ref.RunPublicmethod("onSearchRequested", Null, Null)
End Sub

Sub btnclosesearch_Click
	svsearch.SetVisibleAnimated(500,False)
	btnclosesearch.Visible = False
	svsearch.Panel.RemoveAllViews
	btnmore.Visible = True
End Sub