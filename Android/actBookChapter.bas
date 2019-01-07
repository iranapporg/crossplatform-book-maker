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
	Public sBookID,Search As String
	Public ChoosenTab As Int
End Sub

Sub Globals
	Private lbltitle As Label
	Private db As Database
	Private lv1,lv2,lvnote,lvhighlight,lvsummary As ListView
	Private stack As List
	Private AHContain As AHPageContainer
	Private AHPager As AHViewPager
	Private AHTab As AHViewPagerTabs
	Private pnlview As Panel
	Dim Hightlights,Notes,Bookmark As List
	Private pnlsearch As Panel
	Private btnmenu As Button
	Private txtsearch As EditText
	Dim Normal,Selected As ColorDrawable
	Private pnlheader As Panel
	Private Panel1 As Panel
	Private lv3 As ListView2
	
	Private btnpdf As Button
End Sub

Sub Activity_Create(FirstTime As Boolean)

	Activity.LoadLayout("frmbookchapter")
	
	Normal.Initialize2(Colors.White,0,1,Library.ConvertHex2Int("#FFDADADA"))
	Selected.Initialize2(Colors.RGB(226,226,266),0,1,Library.ConvertHex2Int("#FFDADADA"))
	
	lv3.Initialize("lv3")
	Activity.AddView(lv3,0,pnlheader.Height+Panel1.Height,100%x,100%y - pnlheader.Height+Panel1.Height)
	lv3.Color = Colors.White
	lv3.Visible = False
	
	db.Initialize(sBookID)
	stack.Initialize
	
	AHContain.Initialize
	lv1.Initialize("lv1")
	lv2.Initialize("lv2")
	lvnote.Initialize("lv2")
	lvsummary.Initialize("lvsummary")
	lvhighlight.Initialize("lv2")

	AHContain.AddPage(lv1,"فهرست بندی")
	AHContain.AddPage(lv2,"نشان شده ها")
	AHContain.AddPage(lvnote,"یادداشت ها")
	AHContain.AddPage(lvhighlight,"برجسته ها")
	AHContain.AddPage(lvsummary,"چکیده کتاب")
	AHPager.Initialize(AHContain,"Pager")
	pnlview.AddView(AHPager,0,0,pnlview.Width,pnlview.Height)
	DoEvents
	
	AHTab.Initialize(AHPager)
	AHTab.BackgroundColorPressed = Colors.White
	AHTab.TextColor = Colors.Gray
	AHTab.TextColorCenter = Colors.Black
	Panel1.AddView(AHTab,0,11dip,100%x,90dip)
	lbltitle.Text = "مرور کتاب"
	
	#Region Change listview style
	If Library.GetDevicePhysicalSize > 5 Then
		lv1.TwoLinesAndBitmap.ItemHeight = 130   'ertefa har menu
	End If
	
	If Library.GetDevicePhysicalSize < 6 Then
		lv1.TwoLinesAndBitmap.Label.TextSize = 14
		lv1.SingleLineLayout.Label.TextSize = 14
		lv1.TwoLinesAndBitmap.SecondLabel.TextSize = 19
	Else
		lv1.TwoLinesAndBitmap.Label.TextSize = 19
		lv1.SingleLineLayout.Label.TextSize = 19
		lv1.TwoLinesAndBitmap.SecondLabel.TextSize = 22
	End If
	
	lv1.TwoLinesAndBitmap.Label.Height = lv1.TwoLinesAndBitmap.ItemHeight
	lv1.TwoLinesAndBitmap.SecondLabel.Height = lv1.TwoLinesAndBitmap.Label.Height
	
	Library.SetDivider(lv1,Colors.Gray,1)
	
	lv1.TwoLinesAndBitmap.Label.TextColor	= Library.ConvertHex2Int("#272727")
	lv1.SingleLineLayout.Label.TextColor = Library.ConvertHex2Int("#272727")
	lv1.TwoLinesAndBitmap.SecondLabel.TextColor	=  Library.ConvertHex2Int("#272727")
	lv1.TwoLinesAndBitmap.Label.Gravity = Gravity.RIGHT
	lv1.TwoLinesAndBitmap.SecondLabel.Gravity = Gravity.RIGHT
	
	Dim c1 As ColorDrawable
	c1.Initialize(Library.ConvertHex2Int("#eaeaea"),0)
	lv1.SingleLineLayout.Background = c1
	
	lv1.TwoLinesAndBitmap.Label.Typeface	= Typeface.LoadFromAssets("tahoma.ttf")
	lv1.SingleLineLayout.Label.Typeface	= Typeface.LoadFromAssets("tahoma.ttf")
	lv1.TwoLinesAndBitmap.SecondLabel.Typeface=  Typeface.LoadFromAssets("icomoon.ttf")
	
	lv1.TwoLinesAndBitmap.SecondLabel.Top		= lv1.TwoLinesAndBitmap.Label.Top
	lv1.SingleLineLayout.Label.Top			= lv1.TwoLinesAndBitmap.Label.Top
	
	lv1.TwoLinesAndBitmap.SecondLabel.Width	= lv1.Width-17dip
	lv1.TwoLinesAndBitmap.Label.Width			= lv1.Width-50dip
	lv1.SingleLineLayout.Label.Width		= lv1.TwoLinesAndBitmap.SecondLabel.Width
	
	lv1.TwoLinesAndBitmap.Label.Gravity = Bit.Or(Gravity.RIGHT,Gravity.CENTER_VERTICAL)
	lv1.TwoLinesAndBitmap.SecondLabel.Gravity = Bit.Or(Gravity.RIGHT,Gravity.CENTER_VERTICAL)
	lv1.SingleLineLayout.Label.Gravity = Bit.Or(Gravity.RIGHT,Gravity.CENTER_VERTICAL)
	
	lv1.TwoLinesAndBitmap.ImageView.Left = 100%x - 60dip
	lv1.TwoLinesAndBitmap.Label.Width = 100%x - 125dip 
	lv1.TwoLinesAndBitmap.SecondLabel.Width = 100%x - 55dip 
	#End Region
	
	#Region Change listview style
	If Library.GetDevicePhysicalSize > 5 Then
		lvsummary.TwoLinesAndBitmap.ItemHeight = 130   'ertefa har menu
	End If
	
	If Library.GetDevicePhysicalSize < 6 Then
		lvsummary.TwoLinesAndBitmap.Label.TextSize = 14
		lvsummary.SingleLineLayout.Label.TextSize = 14
		lvsummary.TwoLinesAndBitmap.SecondLabel.TextSize = 19
	Else
		lvsummary.TwoLinesAndBitmap.Label.TextSize = 19
		lvsummary.SingleLineLayout.Label.TextSize = 19
		lvsummary.TwoLinesAndBitmap.SecondLabel.TextSize = 22
	End If
	
	lvsummary.TwoLinesAndBitmap.Label.Height = lv1.TwoLinesAndBitmap.ItemHeight
	lvsummary.TwoLinesAndBitmap.SecondLabel.Height = lv1.TwoLinesAndBitmap.Label.Height
	
	Library.SetDivider(lvsummary,Colors.Gray,1)
	
	lvsummary.TwoLinesAndBitmap.Label.TextColor	= Library.ConvertHex2Int("#272727")
	lvsummary.SingleLineLayout.Label.TextColor = Library.ConvertHex2Int("#272727")
	lvsummary.TwoLinesAndBitmap.SecondLabel.TextColor	=  Library.ConvertHex2Int("#272727")
	lvsummary.TwoLinesAndBitmap.Label.Gravity = Gravity.RIGHT
	lvsummary.TwoLinesAndBitmap.SecondLabel.Gravity = Gravity.RIGHT
	
	Dim c1 As ColorDrawable
	c1.Initialize(Library.ConvertHex2Int("#eaeaea"),0)
	lvsummary.SingleLineLayout.Background = c1
	
	lvsummary.TwoLinesAndBitmap.Label.Typeface	= Typeface.LoadFromAssets("tahoma.ttf")
	lvsummary.SingleLineLayout.Label.Typeface	= Typeface.LoadFromAssets("tahoma.ttf")
	lvsummary.TwoLinesAndBitmap.SecondLabel.Typeface=  Typeface.LoadFromAssets("icomoon.ttf")
	
	lvsummary.TwoLinesAndBitmap.SecondLabel.Top		= lv1.TwoLinesAndBitmap.Label.Top
	lvsummary.SingleLineLayout.Label.Top			= lv1.TwoLinesAndBitmap.Label.Top
	
	lvsummary.TwoLinesAndBitmap.SecondLabel.Width	= lv1.Width-17dip
	lvsummary.TwoLinesAndBitmap.Label.Width			= lv1.Width-50dip
	lvsummary.SingleLineLayout.Label.Width		= lv1.TwoLinesAndBitmap.SecondLabel.Width
	
	lvsummary.TwoLinesAndBitmap.Label.Gravity = Bit.Or(Gravity.RIGHT,Gravity.CENTER_VERTICAL)
	lvsummary.TwoLinesAndBitmap.SecondLabel.Gravity = Bit.Or(Gravity.RIGHT,Gravity.CENTER_VERTICAL)
	lvsummary.SingleLineLayout.Label.Gravity = Bit.Or(Gravity.RIGHT,Gravity.CENTER_VERTICAL)
	
	lvsummary.TwoLinesAndBitmap.ImageView.Left = 100%x - 60dip
	lvsummary.TwoLinesAndBitmap.Label.Width = 100%x - 125dip 
	lvsummary.TwoLinesAndBitmap.SecondLabel.Width = 100%x - 55dip 
	#End Region
	
	Changelistview(lv2)
	Changelistview(lvnote)
	Changelistview(lvhighlight)
	
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
	lv3.SingleLineLayout.Label.TextColor = Library.ConvertHex2Int("#272727")
	lv3.TwoLinesAndBitmap.SecondLabel.TextColor	=  Library.ConvertHex2Int("#272727")
	lv3.TwoLinesAndBitmap.Label.Gravity = Gravity.RIGHT
	lv3.TwoLinesAndBitmap.SecondLabel.Gravity = Gravity.RIGHT
	
	Dim c3 As ColorDrawable
	c3.Initialize(Library.ConvertHex2Int("#eaeaea"),0)
	lv3.SingleLineLayout.Background = c3
	
	lv3.TwoLinesAndBitmap.Label.Typeface	= Typeface.LoadFromAssets("tahoma.ttf")
	lv3.SingleLineLayout.Label.Typeface	= Typeface.LoadFromAssets("tahoma.ttf")
	lv3.TwoLinesAndBitmap.SecondLabel.Typeface=  Typeface.LoadFromAssets("icomoon.ttf")
	
	lv3.TwoLinesAndBitmap.SecondLabel.Width	= lv3.Width-17dip
	lv3.TwoLinesAndBitmap.Label.Width			= lv3.Width-50dip
	lv3.SingleLineLayout.Label.Width		= lv3.Width-50dip
	
	lv3.TwoLinesAndBitmap.Label.Gravity = Bit.Or(Gravity.RIGHT,Gravity.CENTER_VERTICAL)
	lv3.TwoLinesAndBitmap.SecondLabel.Gravity = Bit.Or(Gravity.RIGHT,Gravity.CENTER_VERTICAL)
	lv3.SingleLineLayout.Label.Gravity = Bit.Or(Gravity.RIGHT,Gravity.CENTER_VERTICAL)
	
	lv3.TwoLinesAndBitmap.ImageView.Left = 100%x - 60dip
	lv3.TwoLinesAndBitmap.Label.Width = 100%x - 125dip 
	lv3.TwoLinesAndBitmap.SecondLabel.Width = 100%x - 125dip 
	#End Region
	
	LoadItems("0")
	
	stack.Add("0")
	
End Sub

Sub Pager_PageChanged (Position As Int)
	
'	If Position = 0 Then
'		btnlist.Background = Selected
'		btnbookmarks.Background = Normal
'		btnhighlight.Background = Normal
'		btnnote.Background = Normal
'	Else if Position = 1 Then
'		btnlist.Background = Normal
'		btnbookmarks.Background = Selected
'		btnhighlight.Background = Normal
'		btnnote.Background = Normal
'	Else if Position = 2 Then
'		btnlist.Background = Normal	
'		btnbookmarks.Background = Normal
'		btnhighlight.Background = Normal
'		btnnote.Background = Selected
'	Else if Position = 3 Then
'		btnlist.Background = Normal	
'		btnbookmarks.Background = Normal
'		btnhighlight.Background = Selected
'		btnnote.Background = Normal
'			
'	End If
	
End Sub

Sub Changelistview(lv As ListView)
	
	#Region Change listview style
	If Library.GetDevicePhysicalSize > 5 Then
		lv.TwoLinesAndBitmap.ItemHeight = 130   'ertefa har menu
	End If
	If Library.GetDevicePhysicalSize < 6 Then
		lv.TwoLinesAndBitmap.Label.TextSize = 16
		lv.TwoLinesAndBitmap.SecondLabel.TextSize = 12
	Else
		lv.TwoLinesAndBitmap.Label.TextSize = 19
		lv.TwoLinesAndBitmap.SecondLabel.TextSize = 14
	End If
	
	Library.SetDivider(lv,Library.ConvertHex2Int("#f3f3f3"),1)
	
	lv.TwoLinesAndBitmap.Label.TextColor	= Library.ConvertHex2Int("#272727")
	lv.TwoLinesAndBitmap.SecondLabel.TextColor	=  Library.ConvertHex2Int("#272727")
	lv.TwoLinesAndBitmap.Label.Gravity = Gravity.RIGHT
	lv.TwoLinesAndBitmap.SecondLabel.Gravity = Gravity.RIGHT
	
	lv.TwoLinesAndBitmap.Label.Typeface	= Typeface.LoadFromAssets("tahoma.ttf")
	lv.TwoLinesAndBitmap.SecondLabel.Typeface=  Typeface.LoadFromAssets("tahoma.ttf")
	
	lv.TwoLinesAndBitmap.SecondLabel.Width		= 100%x-17dip
	lv.TwoLinesAndBitmap.Label.Width			= 100%x-150dip
	
	lv.TwoLinesAndBitmap.Label.Gravity = Bit.Or(Gravity.RIGHT,Gravity.CENTER_VERTICAL)
	lv.TwoLinesAndBitmap.SecondLabel.Gravity = Bit.Or(Gravity.RIGHT,Gravity.CENTER_VERTICAL)
	
	lv.TwoLinesAndBitmap.ImageView.Left = 100%x - 60dip
	lv.TwoLinesAndBitmap.Label.Width = 100%x - 125dip 
	lv.TwoLinesAndBitmap.SecondLabel.Width = 100%x - 125dip 
	#End Region
		
End Sub

Sub LoadItems(sID As String)
	
	lv1.Clear
	
	Dim cat,book,summary As List
	cat = db.GetCategory(sID)
	book = db.GetChapters(sID,False)
	summary = db.GetChapters(sID,True)
	
	If stack.Size > 0 Then
		
		Dim temp As String
		temp = stack.Get(stack.Size-1)
		
		lv1.AddTwoLinesAndBitmap2("برگشت ›","",LoadBitmap(File.DirAssets,"back.png"),CreateMap("sID":temp,"sType":"return"))
		
	End If
	
	For i1 = 0 To summary.Size - 1
		
		Dim m12 As Map
		m12 = summary.Get(i1)
		
		Dim te1 As String
		te1 = m12.Get("sTitle")
		
		lvsummary.AddTwoLinesAndBitmap2(te1 & " ›","",LoadBitmap(File.DirAssets,"book-icon.png"),m12)
		
	Next
	
	For i = 0 To cat.Size - 1
		
		Dim m1 As Map
		m1 = cat.Get(i)
		
		Dim te As String
		te = m1.Get("sTitle")
		
		If te.IndexOf("چکیده") > -1 Then Continue
		
		lv1.AddTwoLinesAndBitmap2(te & " ›","",LoadBitmap(File.DirAssets,"book-icon.png"),m1)
		
	Next
	
	For j = 0 To book.Size - 1
		
		Dim m2 As Map
		m2 = book.Get(j)
		
		lv1.AddTwoLinesAndBitmap2(m2.Get("sTitle"),"",LoadBitmap(File.DirAssets,"book.png"),m2)
		
	Next
	
End Sub

Sub Activity_Resume
	
	lv2.Clear
	LoadTopicAttachment
	
	If ChoosenTab <> -1 Then
		AHPager.GotoPage(ChoosenTab,True)
		ChoosenTab = -1
	End If
	
End Sub

Sub Activity_Pause (UserClosed As Boolean)
	
	If UserClosed Then
		Library.Right2LeftAnimation
	Else
		Library.Left2RightAnimation
	End If
	
End Sub

Sub btnmenu_Click
	Activity.Finish
End Sub

Sub lvsummary_ItemClick (Position As Int, Value As Object)
	
	Dim m1 As Map
	m1 = Value
	
	Dim db As Database
	db.Initialize(sBookID)
	
	Dim temp As Map
	temp = db.ChapterContent(m1.Get("sID"))
	
	actSummaryContent.text = temp.Get("sText")
	StartActivity(actSummaryContent)
	Return
	
End Sub

Sub lv1_ItemClick (Position As Int, Value As Object)
	
	Dim m1 As Map
	m1 = Value
	
	If m1.Get("sType") = "cat" Then
		stack.Add(m1.Get("sID"))
		LoadItems(m1.Get("sID"))
	
	Else if m1.Get("sType") = "summary" Then
			Dim db As Database
			db.Initialize(sBookID)
			
			Dim temp As Map
			temp = db.ChapterContent(m1.Get("sID"))
			
			actSummaryContent.text = temp.Get("sText")
			StartActivity(actSummaryContent)
			Return
		
	Else If m1.Get("sType") = "book" Then
		
		Dim temp As Map
		temp = db.ChapterContent(m1.Get("sID"))
		
		If temp.Get("sTypeTopic") = "pdf" Then
			actPDF.BookID = sBookID
			actPDF.filename = temp.Get("sText")
			StartActivity(actPDF)
			Return	
		End If
		
		Dim lastpage As Map
		lastpage = Library.LastPageVisit(sBookID,m1.Get("sID"),"",True)
		
		If temp.IsInitialized Then
			temp.Put("sBookID",sBookID)
			actContent2.Data = temp
			actContent2.BookID = sBookID
			
			If lastpage.IsInitialized Then actContent2.RefererPage = lastpage.Get("Page")
			
			File.WriteString(File.DirInternal,"lasttopic",temp.Get("sID"))
			File.WriteString(File.DirInternal,"lastbook",sBookID)
			
			StartActivity(actContent2)
			
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

Sub lv2_ItemClick (Position As Int, Value As Object)
	
	Dim m1 As Map
	m1 = Value
	
	actContent2.Data = db.ChapterContent(m1.Get("TopicID"))
	actContent2.BookID = sBookID
	actContent2.RefererPage = m1.Get("PageNumber")
	StartActivity(actContent2)
	
End Sub

Sub btnlist_Click
	AHPager.GotoPage(0,True)
End Sub

Sub btnbookmarks_Click
	AHPager.GotoPage(1,True)
End Sub

Sub LoadTopicAttachment
	
	Hightlights.Initialize
	Notes.Initialize
	Bookmark.Initialize
	
	Dim ls As List
	ls = File.ListFiles(File.DirInternal)
	
	For i = 0 To ls.Size - 1
		Dim temp As String
		temp = ls.Get(i)
		
		If temp.StartsWith("highlight_" & sBookID & "_") Then
			ReadHightlight(temp)
			
		Else if temp.StartsWith("noteo_" & sBookID & "_") Then
			ReadNotes(temp)
		
		Else if temp.StartsWith("bookmark_" & sBookID & "_") Then
			ReadBookmarks(temp)
			 
		End If
		
'		File.Delete(File.DirInternal,temp)
		
	Next
	
	lvnote.Clear
	lvhighlight.Clear
	lv2.Clear
	
	For i1 = 0 To Notes.Size - 1
		Dim map1 As Map
		map1 = Notes.Get(i1)
		lvnote.AddTwoLinesAndBitmap2("فصل " & db.GetTopicID(map1.Get("TopicID")),"یادداشت : " & map1.Get("Note"),LoadBitmap(File.DirAssets,"note.png"),map1)
	Next
	
	For i2 = 0 To Hightlights.Size - 1
		Dim map2 As Map
		map2 = Hightlights.Get(i2)
		lvhighlight.AddTwoLinesAndBitmap2("فصل " & db.GetTopicID(map2.Get("TopicID")),"متن : " & map2.Get("Text"),LoadBitmap(File.DirAssets,"highlight.png"),map2)
	Next
	
	For i3 = 0 To Bookmark.Size - 1
		Dim m3 As Map
		m3 = Bookmark.Get(i3)
		lv2.AddTwoLinesAndBitmap2("فصل " & db.GetTopicID(m3.Get("TopicID")),"صفحه ی " & (m3.Get("PageNumber")+1),LoadBitmap(File.DirAssets,"bookmark.png"),m3)
	Next
	
End Sub

Sub ReadNotes(Filename As String)
	
	Dim m1 As Map
	m1.Initialize
	
	m1 = File.ReadMap(File.DirInternal,Filename)
	
	Dim res As List
	res.Initialize
	
	For i = 0 To m1.Size - 1
		
		Dim m2 As Map
		Dim js As JSONParser
		js.Initialize(m1.GetValueAt(i))
		m2 = js.NextObject
	
		Dim result As Map
		result.Initialize
		result.Put("SelectionStart",m2.Get("SelectionStart"))
		result.Put("SelectionEnd",m2.Get("SelectionEnd"))
		result.Put("PageNumber",m2.Get("PageNumber"))
		result.Put("Note",m2.Get("Note"))
		result.Put("ID",m2.Get("ID"))
		result.Put("TopicID",m2.Get("TopicID"))		
		Notes.Add(result)
 
	Next

End Sub

Sub ReadHightlight(Filename As String)
	
	Dim m1 As Map
	m1.Initialize
 
	m1 = File.ReadMap(File.DirInternal,Filename)
	
	Dim res As List
	res.Initialize
	
	For i = 0 To m1.Size - 1
		
		Dim m2 As Map
		Dim js As JSONParser
		js.Initialize(m1.GetValueAt(i))
		m2 = js.NextObject
		
		Dim result As Map
		result.Initialize
		result.Put("SelectionStart",m2.Get("SelectionStart"))
		result.Put("SelectionEnd",m2.Get("SelectionEnd"))
		result.Put("Color",m2.Get("Color"))
		result.Put("TopicID",m2.Get("TopicID"))
		result.Put("Text",m2.Get("Text"))
		result.Put("PageNumber",m2.Get("PageNumber"))
		Hightlights.Add(result)
 
	Next
	
End Sub

Sub ReadBookmarks(Filename As String)
	
	Dim m1 As Map
	m1.Initialize
 
	m1 = File.ReadMap(File.DirInternal,Filename)
	
	For i = 0 To m1.Size - 1
		Dim temp As Map
		temp.Initialize
		
		Dim ps() As String
		ps = ParseBookmark(m1.GetKeyAt(i))
		
		temp.Put("TopicID",ps(0))
		temp.Put("PageNumber",ps(1))
		Bookmark.Add(temp)
		
	Next
	
End Sub

Sub ParseBookmark(ID As String) As String()
	
	Dim s() As String
	s = Regex.Split(":",ID)
	Return Array As String(s(0),s(1))
	
End Sub

Sub btnsearch1_Click
	
	If pnlsearch.Top < 0 Then
		btnmenu.Visible = False
		btnpdf.Visible = False
		lbltitle.Visible = False
		pnlsearch.SetLayoutAnimated(500,pnlsearch.Left,0,pnlsearch.Width,pnlsearch.Height)
		Dim ime1 As IME
		ime1.Initialize("")
		ime1.ShowKeyboard(txtsearch)
	Else
		pnlsearch.SetLayoutAnimated(500,pnlsearch.Left,-pnlsearch.Height,pnlsearch.Width,pnlsearch.Height)
		btnmenu.Visible = True
		btnpdf.Visible = True
		lbltitle.Visible = True
		lv3.Visible = False
	End If
	
End Sub

Sub txtsearch_EnterPressed
	
	lv3.Clear
	
	If txtsearch.Text.Length = 0 Then
		ToastMessageShow("لطفا عبارتی را وارد کنید",False)
		Return
	End If
	
	Dim ls As List
	ls = db.SearchInChapters(txtsearch.Text)
	
	If ls.Size = 0 Then
		ToastMessageShow("هیچ داده ای پیدا نشد",False)
		Return	
	End If
	
	lv3.Visible = True
	
	txtsearch.Tag = txtsearch.Text
	txtsearch.Text = ""
	
	For i = 0 To ls.Size - 1
		Dim t As Map
		t = ls.Get(i)
		t.Put("sType","book")
		Dim s As RichString
		Dim rs As String
		rs = t.Get("Str")
		s.Initialize(rs.Replace(txtsearch.Tag,"{b}" & txtsearch.Tag & "{b}") & " ...")
		s.BackColor2(Colors.Yellow,"{b}")
		lv3.AddTwoLinesAndBitmap2(t.Get("sTitle"),s,LoadBitmap(File.DirAssets,"book.png"),t)
	Next
	
End Sub

Sub lv3_ItemClick (Position As Int, Value As Object)
	
	Dim m1 As Map
	m1 = Value
	
	Dim temp As Map
	temp = db.ChapterContent(m1.Get("sID"))
	
	If temp.IsInitialized Then
		temp.Put("sBookID",sBookID)
		actContent2.Data = temp
		actContent2.SearchWord = txtsearch.Tag
		actContent2.BookID = sBookID
		StartActivity(actContent2)
	End If
		
End Sub

Sub ShowSearch
	btnmenu.Visible = False
	lbltitle.Visible = False
	pnlsearch.SetLayoutAnimated(500,pnlsearch.Left,0,pnlsearch.Width,pnlsearch.Height)
End Sub

Sub btnhighlight_Click
	
End Sub

Sub btnnote_Click
	
End Sub

Sub btnpdf_Click

	Dim m2 As Map
	Dim du As Categories
	du.Initialize
	m2 = du.GetBookInformation(sBookID)
	
	If m2.Get("sPDF") = "" Or m2.Get("sPDF") = Null Then
		ToastMessageShow("این کتاب نسخه PDF ندارد",False)
		Return
	End If
	
	If File.Exists(File.DirRootExternal,"book_" & sBookID & ".pdf") Then
		If Library.CheckExistPdfViewer = False Then Return
'		actPDF.filename = "book_" & sBookID & ".pdf"
'		actPDF.BlnBookPDF = True
		Library.AddPDFBook(sBookID,"book_" & sBookID & ".pdf")
'		StartActivity(actPDF)
		File.WriteString(File.DirRootExternal,"temp_book","book_" & sBookID & ".pdf")
		Dim in1 As Intent
		in1.Initialize(in1.ACTION_MAIN,"")
		in1.SetComponent("com.library.pdfviewer/.main")
		StartActivity(in1)
		Return 
	End If
	
	ProgressDialogShow2("در حال دانلود نسخه pdf...",False)
	
	Dim hu As HttpJob
	hu.Initialize("download_pdf",Me)
	hu.Download(Library.BaseUrl & "books/" & m2.Get("sPDF"))
		
End Sub

Sub JobDone(Job As HttpJob)
	
	ProgressDialogHide
	
	If Job.Success Then
		If Job.JobName = "download_pdf" Then
			Dim ou As OutputStream
			ou = File.OpenOutput(File.DirRootExternal,"book_" & sBookID & ".pdf",False)
			File.Copy2(Job.GetInputStream,ou)
			ou.Close
			
			Library.AddPDFBook(sBookID,"book_" & sBookID & ".pdf")
			
			actPDF.filename = "book_" & sBookID & ".pdf"
			actPDF.BlnBookPDF = True
			StartActivity(actPDF)
			
		End If
	Else
		ToastMessageShow("دانلود با خطا مواجه شد" & CRLF & "لطفا اینترنت خود را بررسی کنید",False)
	End If
	
End Sub