Type=StaticCode
Version=4.01
ModulesStructureVersion=1
B4J=true
@EndOfDesignText@
'Static code module
Sub Process_Globals
	Private fx As JFX
	Private lblbookname As Label
	Private ck1 As CheckBox
	Private books As Categories
	Private lstBook,checkboxs As List
	Private lv1 As ListView
	Private frm1 As Form
	Private btnselect As Button
	Private txtsearch As TextField
	Private lv2 As ListView
	Private pnlover As Pane
	Private lblcatname As Label
	Private imgicon As ImageView
End Sub

Sub ShowSearchBook
	
	frm1.Initialize("frm1",510,594)
	frm1.SetFormStyle("UNIFIED")
	frm1.Title = "جستجو در کتاب ها"
	frm1.RootPane.LoadLayout("frmsearchbook")
	frm1.Resizable = False
	frm1.Show
	Library.CenterFormOnScreen(frm1)
	
	books.Initialize
	checkboxs.Initialize
	lstBook = books.GetDownloadedBook("","")
	
	For i = 0 To lstBook.Size - 1
		
		Dim temp As Map
		temp = lstBook.Get(i)
		
		Dim p1 As Pane
		p1.Initialize("")
		p1.LoadLayout("frmtemplate_search_booklist")
		p1.SetSize(480,65)
		lv1.Items.Add(p1)
		checkboxs.Add(ck1)
		lblbookname.Text = 	temp.Get("sTitle")
		ck1.Tag = temp.Get("sID")
		
	Next
	
End Sub

Sub btnsearch_Action
	
	If txtsearch.Text.Length = 0 Then
		fx.Msgbox(frm1,"لطفا عبارتی را وارد کنید","توجه")
		Return
	End If
	
	Dim Listbook As List
	Listbook.Initialize
	
	For i = 0 To checkboxs.Size - 1
		Dim ckb As CheckBox
		ckb = checkboxs.Get(i)
		If ckb.Checked = True Then Listbook.Add(ckb.Tag)	
	Next
	
	If Listbook.Size = 0 Then
		fx.Msgbox(frm1,"شما هیچ کتابی را انتخاب نکرده اید","توجه")
		Return	
	End If
	
	Dim cat As Categories
	Dim Result As List
	cat.Initialize
	Result.Initialize
	
	Result = cat.SearchInBooks(txtsearch.Text,Listbook)
	
	lv2.Visible = True
	lv2.Items.Clear
	
	For j = 0 To Result.Size - 1
		Dim t As List
		t = Result.Get(j)
		
		For k = 0 To t.Size - 1
			
			Dim t1 As Map
			t1 = t.Get(i)

			Dim p1 As Pane
			p1.Initialize("")
			p1.LoadLayout("frmtemplate_category")
			p1.SetSize(400,60)
			lv2.Items.Add(p1)
			lblcatname.Text = t1.Get("sTitle")
			pnlover.Tag = t1
			imgicon.SetImage(fx.LoadImage(File.DirAssets,"book.png"))
				
		Next
	Next
	
End Sub

Sub btnselect_Action
	
	For i = 0 To checkboxs.Size - 1
		Dim ck As CheckBox
		ck = checkboxs.Get(i)
		
		If ck.Checked = True Then
			ck.Checked = False
		Else
			ck.Checked = True	
		End If
	Next
	
End Sub

Sub pnlover_MouseClicked (EventData As MouseEvent)
	
	Dim ps As Pane
	ps = Sender
	
	Dim ms As Map
	ms = ps.Tag
	
	Dim db As Database
	db.Initialize(ms.Get("BookID"))
	
	Dim temp As Map
	temp = db.ChapterContent(ms.Get("sID"))
	
	temp.Put("sBookID",ms.Get("BookID"))
	actContent.Data = temp
	actContent.offset = -1
	actContent.SearchWord = txtsearch.Text
	actContent.BookID = ms.Get("BookID")
	actContent.ShowContent
	
End Sub

Sub frm1_CloseRequest (EventData As Event)
	If lv2.Visible = True Then
		lv2.Visible = False
		EventData.Consume
	End If
End Sub