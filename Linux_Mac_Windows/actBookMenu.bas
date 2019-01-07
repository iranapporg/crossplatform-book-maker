Type=StaticCode
Version=4.01
ModulesStructureVersion=1
B4J=true
@EndOfDesignText@
'Static code module
Sub Process_Globals
	Private fx As JFX
	Private frm1 As Form
	Public BookID As String
	Private tab1 As TabPane
	Private db As Database
	Private lv1 As ListView
	Private lvsummary As ListView
	Private stack As List
	Private pnlover As Pane
	Private imgicon As ImageView
	Private lblcatname As Label
	Private lvnote As ListView
	Private lvbookmark As ListView
	Dim Hightlights,Notes,Bookmark As List
	Private txtsearch As TextField
	Private lvsearch As ListView
	Private Pane1 As Pane
	Private btndelete_remove As Button
	Private btnpdf As Button
End Sub

Sub ShowBookMenu
	
	frm1.Initialize("frm1",510,594)
	frm1.SetFormStyle("UNIFIED")
	frm1.Title = "منوی کتاب"
	frm1.RootPane.LoadLayout("frmbook_menu")
	frm1.Resizable = False
	Library.CenterFormOnScreen(frm1)
	
	tab1.LoadLayout("frmchapters","سرفصل ها")
	tab1.LoadLayout("frmbookmark","نشان شده ها")
	tab1.LoadLayout("frmnote","یادداشت ها")
	'tab1.LoadLayout("frmhighlight","برجسته ها")
	tab1.LoadLayout("frmsummary","چکیده ها")
	frm1.Show
	
	db.Initialize(BookID)
	stack.Initialize
	
	LoadItems(0)
	stack.Add("0")
	
	If File.Exists(File.DirApp & "/Data" & "/Data" & "/book/" & BookID,"book.pdf") Then
		btnpdf.Top = frm1.Height
		btnpdf.SetLayoutAnimated(600,btnpdf.Left,frm1.Height - btnpdf.Height + 10,btnpdf.Width,btnpdf.Height)	
	End If
	
	LoadTopicAttachment
	
End Sub

Sub LoadItems(sID As String)
	
	lv1.Items.Clear
	
	Dim cat,book,summary As List
	cat = db.GetCategory(sID)
	book = db.GetChapters(sID,False)
	summary = db.GetChapters(sID,True)
	
	If stack.Size > 0 Then
		
		Dim temp As String
		temp = stack.Get(stack.Size-1)
		
		Dim p1 As Pane
		p1.Initialize("")
		p1.LoadLayout("frmitembook")
		p1.SetSize(400,60)
		lv1.Items.Add(p1)
		lblcatname.Text = "برگشت ›"
		pnlover.Tag = CreateMap("sID":temp,"sType":"return")
		imgicon.SetImage(fx.LoadImage(File.DirAssets,"back.png"))
		
	End If
	
	For i1 = 0 To summary.Size - 1
		
		Dim m12 As Map
		m12 = summary.Get(i1)
		
		Dim te1 As String
		te1 = m12.Get("sTitle")
		
		Dim p1 As Pane
		p1.Initialize("")
		p1.LoadLayout("frmitembook")
		p1.SetSize(400,60)
		lvsummary.Items.Add(p1)
		lblcatname.Text = te1
		pnlover.Tag = m12
		imgicon.SetImage(fx.LoadImage(File.DirAssets,"book.png"))
		
	Next
	
	For i = 0 To cat.Size - 1
		
		Dim m1 As Map
		m1 = cat.Get(i)
		
		Dim te As String
		te = m1.Get("sTitle")
		
		If te.IndexOf("چکیده") > -1 Then Continue
		
		Dim p1 As Pane
		p1.Initialize("")
		p1.LoadLayout("frmitembook")
		p1.SetSize(lv1.Width,60)
		lv1.Items.Add(p1)
		lblcatname.Text = te & " ›"
		pnlover.Tag = m1
		imgicon.SetImage(fx.LoadImage(File.DirAssets,"book-icon.png"))
		
	Next
	
	For j = 0 To book.Size - 1
		
		Dim m2 As Map
		m2 = book.Get(j)
		
		If m2.Get("sType") = "summary" Then Continue
		
		Dim p1 As Pane
		p1.Initialize("")
		p1.LoadLayout("frmitembook")
		p1.SetSize(400,60)
		lv1.Items.Add(p1)
		
		lblcatname.Text = m2.Get("sTitle")
		pnlover.Tag = m2
		imgicon.SetImage(fx.LoadImage(File.DirAssets,"book.png"))
		
	Next
	
End Sub

Sub pnlover_MouseClicked (EventData As MouseEvent)
	
	Dim pn As Pane
	pn = Sender
	Dim m1 As Map
	m1 = pn.Tag
	
	If m1.Get("sType") = "cat" Then
		stack.Add(m1.Get("sID"))
		LoadItems(m1.Get("sID"))
	
	Else if m1.Get("sType") = "bookmark" Then
			Dim temp As Map
			temp = db.ChapterContent(m1.Get("TopicID"))
			If temp.IsInitialized Then
				temp.Put("sBookID",BookID)
				actContent.Data = temp
				actContent.offset = m1.Get("Offset")
				actContent.BookID = BookID
				actContent.ShowContent
			End If
	
	Else if m1.Get("sType") = "note" Then
			Dim temp As Map
			temp = db.ChapterContent(m1.Get("TopicID"))
			If temp.IsInitialized Then
				temp.Put("sBookID",BookID)
				actContent.Data = temp
				actContent.RefererNoteID = m1.Get("ID")
				actContent.BookID = BookID
				actContent.ShowContent
			End If
		
	Else If m1.Get("sType") = "book" Or m1.Get("sType") = "summary" Then
		
		Dim temp As Map
		temp = db.ChapterContent(m1.Get("sID"))
		
		If temp.Get("sTypeTopic") = "pdf" Then
			fx.ShowExternalDocument(File.GetUri(File.DirApp & "/Data" & "/Data" & "/book/" & BookID,temp.Get("sText")))
			Return	
		End If
		
		If temp.IsInitialized Then
			temp.Put("sBookID",BookID)
			actContent.Data = temp
			actContent.offset = -1
			If txtsearch.Tag <> "" Then actContent.SearchWord = txtsearch.Tag
			actContent.BookID = BookID
			actContent.ShowContent
		End If
	
	Else If m1.Get("sType") = "return" Then
		Dim val As String
		stack.RemoveAt(stack.Size-1)
		val = stack.Get(stack.Size-1)
		If stack.Size = 1 Then stack.Clear
		LoadItems(val)
		If stack.Size = 0 Then stack.Add("0")
	End If
	
End Sub

Sub LoadTopicAttachment
	
	Hightlights.Initialize
	Notes.Initialize
	Bookmark.Initialize
	
	Notes = Library.ListNote(BookID)
	Bookmark = Library.Listbookmark(BookID)
	
	lvnote.Items.Clear
	lvbookmark.Items.Clear
	
	If Notes.IsInitialized Then
		For i1 = 0 To Notes.Size - 1
			Dim map1 As Map
			map1 = Notes.Get(i1)
			Dim p1 As Pane
			p1.Initialize("")
			p1.LoadLayout("frmtemplate_book_item")
			p1.SetSize(500,60)
			lvnote.Items.Add(p1)
			btndelete_remove.Visible = False
			lblcatname.Text = db.GetTopicID(map1.Get("TopicID"))
			pnlover.Tag = map1
			imgicon.SetImage(fx.LoadImage(File.DirAssets,"note.png"))
		Next
	End If
	
	If Bookmark.IsInitialized Then
		For i3 = 0 To Bookmark.Size - 1
			Dim m3 As Map
			m3 = Bookmark.Get(i3)
			Dim p1 As Pane
			p1.Initialize("")
			p1.LoadLayout("frmtemplate_book_item")
			p1.SetSize(490,60)
			lvbookmark.Items.Add(p1)
			lblcatname.Text = "فصل " & db.GetTopicID(m3.Get("TopicID"))
			btndelete_remove.Tag = CreateMap("id":m3.Get("Offset"),"topicid":m3.Get("TopicID"))
			pnlover.Tag = m3
			imgicon.SetImage(fx.LoadImage(File.DirAssets,"bookmark.png"))
		Next
	End If
	
End Sub

Sub btnsearch_Action
	
	Dim ls As List
	ls = db.SearchInChapters(txtsearch.Text)
	
	If ls.Size = 0 Then
		fx.Msgbox(frm1,"هیچ داده ای پیدا نشد","توجه")
		Return	
	End If
	
	If tab1.Tabs.Size = 5 Then
		tab1.LoadLayout("frmsearchinbook","نتیجه")
	End If
	
	lvsearch.Items.Clear
	
	txtsearch.Tag = txtsearch.Text
	txtsearch.Text = ""
	tab1.SelectedIndex = 5
	
	For i = 0 To ls.Size - 1
		Dim t As Map
		t = ls.Get(i)
		t.Put("sType","book")
		Dim p1 As Pane
		p1.Initialize("")
		p1.LoadLayout("frmtemplate_category")
		p1.SetSize(400,60)
		lvsearch.Items.Add(p1)
		lblcatname.Text = t.Get("sTitle")
		pnlover.Tag = t
		imgicon.SetImage(fx.LoadImage(File.DirAssets,"book.png"))
	Next
	
End Sub

Sub SaveChangingContent(sID As String,sContent As String)
	db.UpdateContent(sID,sContent)
	LoadTopicAttachment
End Sub

Sub btndelete_remove_Action
	
	Dim b1 As Button
	b1 = Sender
	
	Dim m2 As Map
	m2 = b1.Tag
	
	Library.Deletebookmark(BookID,m2)
	fx.Msgbox(frm1,"نشان شده مورد نظر حذف شد","توجه")
	LoadTopicAttachment
	
End Sub

Sub btnpdf_Action
	fx.ShowExternalDocument(File.GetUri(File.DirApp & "/Data" & "/Data" & "/book/" & BookID,"book.pdf"))
End Sub

Sub frm1_Closed
	db.CloseDatabase
End Sub