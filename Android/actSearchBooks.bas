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

End Sub

Sub Globals
	Private sv1 As ScrollView
	Private txtsearch As EditText
	Private lblbookname As Label
	Private ck1 As CheckBox
	Private books As Categories
	Private lstBook,checkboxs As List
	Private lv3 As ListView2
	Private pnlheader As Panel
End Sub

Sub Activity_Create(FirstTime As Boolean)
	
	Activity.LoadLayout("frmsearchbooks")
	
	books.Initialize
	checkboxs.Initialize
	
	lv3.Initialize("lv3")
	Activity.AddView(lv3,0,pnlheader.Height,sv1.Width,sv1.Height)
	lv3.Color = Colors.White
	lv3.Visible = False
	
	lstBook = books.GetDownloadedBook("","")
	
	Dim top As Int
	
	For i = 0 To lstBook.Size - 1
		
		Dim temp As Map
		temp = lstBook.Get(i)
		
		Dim p1 As Panel
		p1.Initialize("")
		sv1.Panel.AddView(p1,0,top,sv1.Width,55dip)
		p1.LoadLayout("frmtemplate_search_booklist")
		checkboxs.Add(ck1)
		lblbookname.Text = 	temp.Get("sTitle")
		ck1.Tag = temp.Get("sID")
		
		top = top + p1.Height
		
	Next
	
	sv1.Panel.Height = top
	
	#Region Change listview style
	If Library.GetDevicePhysicalSize < 6 Then
		lv3.TwoLinesAndBitmap.Label.TextSize = 14
		lv3.SingleLineLayout.Label.TextSize = 14
		lv3.TwoLinesAndBitmap.SecondLabel.TextSize = 13
	Else
		lv3.TwoLinesAndBitmap.Label.TextSize = 19
		lv3.SingleLineLayout.Label.TextSize = 19
		lv3.TwoLinesAndBitmap.SecondLabel.TextSize = 22
	End If
	
	'Library.SetDivider(lv3,Colors.Gray,1)
	
	lv3.TwoLinesAndBitmap.Label.TextColor	= Library.ConvertHex2Int("#272727")
	lv3.TwoLinesAndBitmap.SecondLabel.TextColor	=  Library.ConvertHex2Int("#272727")
	lv3.TwoLinesAndBitmap.Label.Gravity = Gravity.RIGHT
	lv3.TwoLinesAndBitmap.SecondLabel.Gravity = Gravity.RIGHT
	
	lv3.TwoLinesAndBitmap.Label.Typeface	= Typeface.LoadFromAssets("tahoma.ttf")
	lv3.TwoLinesAndBitmap.SecondLabel.Typeface=  Typeface.LoadFromAssets("icomoon.ttf")
	
	lv3.TwoLinesAndBitmap.SecondLabel.Width	= lv3.Width-17dip
	lv3.TwoLinesAndBitmap.Label.Width			= lv3.Width-50dip
	
	
	lv3.TwoLinesAndBitmap.Label.Gravity = Bit.Or(Gravity.RIGHT,Gravity.CENTER_VERTICAL)
	lv3.TwoLinesAndBitmap.SecondLabel.Gravity = Bit.Or(Gravity.RIGHT,Gravity.CENTER_VERTICAL)
	
	lv3.TwoLinesAndBitmap.ImageView.Left = 100%x - 60dip
	lv3.TwoLinesAndBitmap.Label.Width = 100%x - 125dip 
	lv3.TwoLinesAndBitmap.SecondLabel.Left = 0
	lv3.TwoLinesAndBitmap.SecondLabel.Width = lv3.TwoLinesAndBitmap.ImageView.Left
	#End Region
	
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)
	Library.Right2LeftAnimation
End Sub

Sub btnckall_Click
	
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

Sub btnmenu_Click
	Activity.Finish	
End Sub

Sub btnsearch_Click
	
	If txtsearch.Text.Length = 0 Then
		ToastMessageShow("لطفا عبارتی را وارد کنید",False)
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
		ToastMessageShow("شما هیچ کتابی را انتخاب نکرده اید",False)
		Return	
	End If
	
	Dim cat As Categories
	Dim Result As List
	cat.Initialize
	Result.Initialize
	
	Result = cat.SearchInBooks(txtsearch.Text,Listbook)
	
	lv3.Visible = True
	lv3.Clear
	
	For j = 0 To Result.Size - 1
		Dim t As List
		t = Result.Get(j)
		
		For k = 0 To t.Size - 1
			
			Dim temp As Map
			temp = t.Get(k)
			temp.Put("sType","book")
			
			Dim s As RichString
			Dim rs As String
			rs = temp.Get("Str")
			s.Initialize(rs.Replace(txtsearch.Text,"{b} " & txtsearch.Text & " {b}") & " ...")
			s.BackColor2(Colors.Yellow,"{b}")
			
			Dim s1 As RichString
			s1.Initialize("{b}" & temp.Get("BookName") & "{b} : " & temp.Get("sTitle"))
			s1.Style2(s1.STYLE_BOLD,"{b}")
			
			lv3.AddTwoLinesAndBitmap2(s1,s,LoadBitmap(File.DirAssets,"book.png"),temp)
			
		Next
	Next
	
End Sub

Sub Activity_KeyPress (KeyCode As Int) As Boolean 'Return True to consume the event
	If KeyCode = KeyCodes.KEYCODE_BACK Then
		If lv3.Visible = True Then
			lv3.Visible = False
			Return True
		End If
		Activity.Finish
		Return True
	End If
End Sub

Sub lv3_ItemClick (Position As Int, Value As Object)
	
	Dim ms As Map
	ms = Value
	
	Dim db As Database
	db.Initialize(ms.Get("BookID"))
	
	Dim temp As Map
	temp = db.ChapterContent(ms.Get("sID"))
	
	actContent2.BookID = ms.Get("BookID")
	actContent2.Data = temp
	actContent2.SearchWord = txtsearch.Text
	StartActivity(actContent2)
	
End Sub