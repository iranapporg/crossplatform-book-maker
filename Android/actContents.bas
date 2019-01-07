Type=Activity
Version=5.5
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: False
	#IncludeTitle: False
#End Region

Sub Process_Globals
	Public Data As Map
	Public BookID As String
	Public RefererPage,SearchWord As String
End Sub

Sub Globals
	Private pnlfooter As Panel
	Private sk1 As SeekBar
	Private btnback As Button
	Private btncopy As Button
	Private btnhighlight As Button
	Private btnmenu As Button
	Private btnnote As Button
	Private btnsearch As Button
	Private btnshare As Button
	Private lbltitle As Label
	Private pnlheader As Panel
	Private pnlheader1 As Panel
	Private AHContainer As AHPageContainer
	Private AHPager As AHViewPager
	Private su As StringUtils
	Private lblfooter As Label
	Private pnlfootershow As Panel
	Private DB As Database
	Private currentEditText As Label
	Private CurrentChoosenWord As String
	Private CurrentStartSelection,CurrentEndSelection As Int
	Private btndelete As Button
	Private SplitedText,Labels As List
	Private lbltitlefooter As Label
	Private btnbookmark As Button
	Private txtGotFocus As EditText
	Private ime1 As IME
	Private imgbookmark As ImageView
	Private timerBookmark As Timer
	Private Setting As Map
End Sub

Sub Activity_Create(FirstTime As Boolean)

	Activity.LoadLayout("frmcontent")
	
	Setting.Initialize
	AHContainer.Initialize
	DB.Initialize(BookID)
	ime1.Initialize("IMEME")
	
	LoadSetting
	
	Dim c1,c2 As ColorDrawable
	c1.Initialize(Colors.RGB(171,171,171),0)
	c2.Initialize(Colors.RGB(72,72,72),0)
	Library.SetSeekbarDrawable(sk1,c1,c2)
	
	txtGotFocus.Initialize("")
	Labels.Initialize
	Activity.AddView(txtGotFocus,-100dip,0,1dip,1dip)

	Dim content As String
	content = Data.Get("sText")
	
	Dim lb As Label
	lb.Initialize("")
	lb.TextSize = Setting.Get("FontSize")
	lb.Typeface = Typeface.LoadFromAssets("byekan.ttf")
	Library.LabelSpace(lb,Library.SpaceLabel)
	Activity.AddView(lb,0,0,100%x,100%y)
	
	Dim CountPage As Int
	
	#region Detect device size
	Dim device_size As Float
	device_size = Library.GetDevicePhysicalSize
	
	If device_size > 6 Then ' above 6 inch
		CountPage = su.MeasureMultilineTextHeight(lb,content) / Activity.Height
		Library.SpaceLabel = 0.95
	else If device_size > 5.5 Then And device_size < 7 Then 'between 5.5 and 7
		CountPage = su.MeasureMultilineTextHeight(lb,content) / Activity.Height * 2.8
		Library.SpaceLabel = 1.5
	Else if device_size < 4.7 And device_size > 4.5 Then 'between 5.5 and 4
		CountPage = su.MeasureMultilineTextHeight(lb,content) / Activity.Height * 2.1
		Library.SpaceLabel = 1.2
	Else if device_size < 5.5 And device_size > 4 Then 'between 5.5 and 4
		CountPage = su.MeasureMultilineTextHeight(lb,content) / Activity.Height * 2
		Library.SpaceLabel = 1.5
	Else if device_size < 4 Then ' below 4 inch
		CountPage = su.MeasureMultilineTextHeight(lb,content) / Activity.Height * 1.3
		Library.SpaceLabel = 1.3
	End If
	#end region
	
	If CountPage = 0 Then CountPage = 1
	
	Dim offset,textCount As Int : offset = 0
	textCount = content.Length / CountPage
	sk1.Max = CountPage-1
	sk1.Value = 0
	lb.RemoveView
	
	SplitedText.Initialize
	
	For k = 1 To CountPage
		
		Dim rs As String

		If offset + textCount > content.Length Then
			rs = content.SubString(offset)
		Else
			rs = content.SubString2(offset,offset + textCount)
		End If
		
		SplitedText.Add(rs)
		offset = offset + textCount
		
	Next
	
	SplitedText = Library.ReverseList(SplitedText)
	
	For i = 1 To CountPage
		
		Dim lbl As EditText
		lbl.Initialize("txtcontent")
		'lbl.InputType = lbl.INPUT_TYPE_NONE
		lbl.SingleLine = False
		lbl.TextSize = Setting.Get("FontSize")
		lbl.TextColor = Colors.Black
		lbl.Color = Colors.White
		lbl.Typeface = Typeface.LoadFromAssets("bn.ttf")
		Library.LabelSpace(lbl,Library.SpaceLabel)
		lbl.Visible = False
		
'		Dim justify As justify
'		justify.Initialize
'		justify.JustifyBySpace(Activity,lbl,0.6,False)
''		justify.justify(lbl)
'		justify.run(lbl,100%x)
'		lbl.Gravity = Gravity.RIGHT
   
		AHContainer.AddPage(lbl,"")
		Labels.Add(lbl)
		
'		Dim rs As String
'
'		If offset + textCount > content.Length Then
'			rs = content.SubString(offset)
'		Else
'			rs = content.SubString2(offset,offset + textCount)
'		End If
'		
'		SplitedText.Add(rs)
		
		'If i = 1 Or RefererPage = i-1 Then
		If i = CountPage Or RefererPage = i-1 Then
			'lbl.Text = ClickableLabel(rs,i-1)
			lbl.Text = ClickableLabel(SplitedText.Get(i-1),i-1)
			lbl.Visible = True
			currentEditText = lbl
		End If
		
		offset = offset + textCount
		
	Next
	
	AHPager.Initialize(AHContainer,"Pagers")

	Activity.AddView(AHPager,0,0,100%x,100%y)
	'Pagers_PageChanged (0)
	AHPager.GotoPage(CountPage-1,True)
	Pagers_PageChanged (CountPage-1)
	
	If RefererPage <> "" Then
		AHPager.GotoPage(RefererPage,True)
	End If
	
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)
	RefererPage = ""
	SearchWord = ""
	Library.Right2LeftAnimation
End Sub

Sub txtcontent_Click
		
End Sub

Sub txtcontent_LongClick
	
	If pnlheader.Top < 0 Then
		pnlfootershow_Click
		pnlheader.BringToFront
		pnlheader.SetLayoutAnimated(500,0,0,100%x,pnlheader.Height)
		pnlfooter.BringToFront
		pnlfooter.SetLayoutAnimated(500,0,100%y-pnlfooter.Height,100%x,pnlfooter.Height)
		pnlheader1.SetLayoutAnimated(500,0,-pnlheader1.Height,100%x,pnlheader1.Height)
	Else
		pnlheader.BringToFront
		pnlheader.SetLayoutAnimated(500,0,-pnlheader.Height,100%x,pnlheader.Height)
		pnlfooter.BringToFront
		pnlfooter.SetLayoutAnimated(500,0,100%y+pnlfooter.Height,100%x,pnlfooter.Height)
		pnlheader1.SetLayoutAnimated(500,0,-pnlheader1.Height,100%x,pnlheader1.Height)
	End If
	
End Sub

Sub pnlfootershow_Click
	pnlfootershow.BringToFront
	pnlfootershow.SetLayoutAnimated(500,0,100%y,100%x,pnlfootershow.Height)
	txtGotFocus.RequestFocus
	ime1.HideKeyboard
End Sub

Sub btnback_Click
	Activity.Finish
	StartActivity(actMenu)
End Sub

Sub btnsearch_Click
	
	Activity.Finish
	CallSubDelayed(actBookChapter,"ShowSearch")

End Sub

Sub btnmenu_Click
	actBookChapter.sBookID = Data.Get("sBookID")
	Activity.Finish
	StartActivity(actBookChapter)
End Sub

Sub btncopy_Click
	
	CurrentStartSelection = Library.getSelectionStart(currentEditText)
	CurrentEndSelection = Library.getSelectionEnd(currentEditText)
	
	pnlheader1.SetLayoutAnimated(500,0,-pnlheader1.Height,100%x,pnlheader1.Height)
	
	If CurrentEndSelection = CurrentStartSelection Then
		ToastMessageShow("خطا : شما باید متنی را انتخاب کنید",False)
		ime1.HideKeyboard
		txtGotFocus.RequestFocus
		Return		
	End If
	
	Dim rs As String
	rs = currentEditText.Text.SubString2(CurrentStartSelection,CurrentEndSelection)
	
	txtGotFocus.RequestFocus
	ime1.HideKeyboard
	
	Dim c1 As BClipboard
	c1.setText(rs)
	CurrentChoosenWord = ""
	ToastMessageShow("متن انتخاب شده به حافظه کپی شد",False)
	
End Sub

Sub btnshare_Click

	CurrentStartSelection = Library.getSelectionStart(currentEditText)
	CurrentEndSelection = Library.getSelectionEnd(currentEditText)
	
	pnlheader1.SetLayoutAnimated(500,0,-pnlheader1.Height,100%x,pnlheader1.Height)
	
	If CurrentEndSelection = CurrentStartSelection Then
		ToastMessageShow("خطا : شما باید متنی را انتخاب کنید",False)
		ime1.HideKeyboard
		txtGotFocus.RequestFocus
		Return		
	End If
	
	Dim rs As String
	rs = currentEditText.Text.SubString2(CurrentStartSelection,CurrentEndSelection)
	
	txtGotFocus.RequestFocus
	ime1.HideKeyboard
	
	Library.ShareString("اشتراک گذاری",rs,"اشتراک گذاری")
	
End Sub

Sub btnbackme_Click
	txtGotFocus.RequestFocus
	ime1.HideKeyboard
	
	pnlheader1.BringToFront
	pnlheader1.SetLayoutAnimated(500,0,-pnlheader1.Height,100%x,pnlheader1.Height)
End Sub

Sub btnhighlight_Click

	CurrentStartSelection = Library.getSelectionStart(currentEditText)
	CurrentEndSelection = Library.getSelectionEnd(currentEditText)
	
	pnlheader1.SetLayoutAnimated(500,0,-pnlheader1.Height,100%x,pnlheader1.Height)
	
	If CurrentEndSelection = CurrentStartSelection Then
		ToastMessageShow("خطا : شما باید متنی را انتخاب کنید",False)
		ime1.HideKeyboard
		txtGotFocus.RequestFocus
		Return		
	End If
	
	Dim text As String
	text = currentEditText.Text.SubString2(CurrentStartSelection,CurrentEndSelection)
	
	Dim exist As Boolean
	exist = Library.ExistHightlight(BookID,Data.Get("sID"),AHPager.CurrentPage,CurrentStartSelection,CurrentEndSelection,False)
	
	If exist = True Then
		Library.ExistHightlight(BookID,Data.Get("sID"),AHPager.CurrentPage,CurrentStartSelection,CurrentEndSelection,True)
	Else
		Dim color As ColorPickerDialog
		color.Show("رنگ هایلات را انتخاب کنید","تایید","انصراف","",Null)
		Library.WriteHighLight(BookID,Data.Get("sID"),AHPager.CurrentPage,CurrentStartSelection,CurrentEndSelection,color.RGB,text)
	End If
	
	currentEditText.Text = ClickableLabel(SplitedText.Get(AHPager.CurrentPage),AHPager.CurrentPage)
	
	ime1.HideKeyboard
	txtGotFocus.RequestFocus

End Sub

Sub btnbackground_Click
	
End Sub

Sub btnnote_Click

	CurrentStartSelection = Library.getSelectionStart(currentEditText)
	CurrentEndSelection = Library.getSelectionEnd(currentEditText)
	
	pnlheader1.SetLayoutAnimated(500,0,-pnlheader1.Height,100%x,pnlheader1.Height)
	
	If CurrentEndSelection = CurrentStartSelection Then
		ToastMessageShow("خطا : شما باید متنی را انتخاب کنید",False)
		ime1.HideKeyboard
		txtGotFocus.RequestFocus
		Return		
	End If
	
	ime1.HideKeyboard
	txtGotFocus.RequestFocus
	
	Dim in1 As InputDialog
	in1.InputType = in1.INPUT_TYPE_TEXT
	in1.Hint = "یادداشت"
	in1.HintColor = Colors.Gray
	
	Dim text As String
	text = currentEditText.Text.SubString2(CurrentStartSelection,CurrentEndSelection)
	
	ime1.HideKeyboard
	DoEvents
	
	If in1.Show("یادداشت خود را وارد کنید","توجه","ذخیره","انصراف","",Null) = DialogResponse.POSITIVE Then
		If in1.Input.Length = 0 Then
			Return
		Else
			Library.WriteNote(BookID,Data.Get("sBookID"),AHPager.CurrentPage,in1.Input,CurrentStartSelection,CurrentEndSelection,text)
			currentEditText.Text = ClickableLabel(SplitedText.Get(AHPager.CurrentPage),AHPager.CurrentPage)
			
			ToastMessageShow("یادداشت ذخیره شد",False)
		End If
		
	End If
	
End Sub

Sub sk1_ValueChanged (Value As Int, UserChanged As Boolean)
	
	If UserChanged = False Then
		'sk1.Value = 0
		Return
	End If
	
	AHPager.CurrentPage = Value
	txtGotFocus.RequestFocus
	ime1.HideKeyboard
		
End Sub

Sub ClickableSpan1_Click(SelectionStart As Int, SelectionEnd As Int)
	
	CurrentStartSelection	= SelectionStart
	CurrentEndSelection		= SelectionEnd
	
	Dim text As String
	text = currentEditText.text.SubString2(SelectionStart,SelectionEnd)
	
	CurrentChoosenWord = text
	
End Sub

Sub GetDataFromChoosenWord(text As String)
	
	Dim rs As Map
	rs = DB.GetFooters(Data.Get("sID"),text)
	
	If rs.ContainsKey("success") Then
		lbltitlefooter.text = "پاورقی"
		lbltitlefooter.Left = 0
		lbltitlefooter.Width = 100%x
		ShowPanelText(rs.Get("sText"))
	Else
		
		Dim note As String
		note = Library.NotesContent(BookID,Data.Get("sID"),AHPager.CurrentPage,CurrentStartSelection,CurrentEndSelection)
		
		If note.Length > 0 Then
			lbltitlefooter.text = "یادداشت " & CurrentChoosenWord
			lbltitlefooter.Left = btndelete.Width + btndelete.Left
			lbltitlefooter.Width = 100%x - btndelete.Width
			ShowPanelText(note)
			btndelete.Visible = True
			Return
		Else
			
			Dim pos() As Int
			pos = Library.HighlightSelection(BookID,Data.Get("sID"),AHPager.CurrentPage,CurrentStartSelection,CurrentEndSelection)
			
			If pos <> Null Then
				Dim s As String
				s= currentEditText.Text.SubString2(pos(0),pos(1))
				Library.SelectionEdittext(currentEditText,pos(0),s.Length)
				btnhighlight.TextColor = Colors.Red
			
			Else
				btnhighlight.TextColor = Colors.RGB(128,128,128)
				pnlfootershow_Click
				Return
			End If
		
		End If
		
		HidePanelText
		
	End If
	
End Sub

Sub ShowPanelText(Text As String)
	btndelete.Visible = False
	lblfooter.Text = Text
	pnlfootershow.BringToFront
	pnlfootershow.Top = Activity.Height
	pnlheader.BringToFront
	pnlheader.Top = -pnlheader.Height
	pnlfooter.BringToFront
	pnlfooter.Top = Activity.Height
	pnlfootershow.SetLayoutAnimated(500,0,100%y-pnlfootershow.Height,100%x,pnlfootershow.Height)
	pnlheader1.SetLayoutAnimated(500,0,-pnlheader1.Height,100%x,pnlheader1.Height)
End Sub

Sub HidePanelText
	
'	Dim cStart,cEnd As Int
'	cStart = Library.getSelectionStart(currentEditText)
'	cEnd = Library.getSelectionEnd(currentEditText)
'	
'	If cStart <> cEnd Then
'		Dim exist As Boolean
'		exist = Library.ExistHightlight(BookID,Data.Get("sID"),AHPager.CurrentPage,CurrentStartSelection,CurrentEndSelection,False)
'		If exist Then
'			btnhighlight.TextColor = Colors.Red
'		Else
'			btnhighlight.TextColor = Colors.RGB(128,128,128)
'		End If
'	Else
'		btnhighlight.TextColor = Colors.RGB(128,128,128)
'	End If
	
	pnlheader.BringToFront
	pnlheader.SetLayoutAnimated(500,0,-pnlheader.Height,100%x,pnlheader.Height)
	pnlfooter.BringToFront
	pnlfooter.SetLayoutAnimated(500,0,100%y+pnlfooter.Height,100%x,pnlfooter.Height)
	pnlheader1.BringToFront
	pnlheader1.SetLayoutAnimated(500,0,0,100%x,pnlheader1.Height)
	ime1.HideKeyboard
	
End Sub

Sub btndelete_Click
	Library.DeleteNote(BookID,Data.Get("sID"),AHPager.CurrentPage,CurrentChoosenWord,CurrentStartSelection,CurrentEndSelection)
	ToastMessageShow("یادداشت شما حذف شد",False)
	currentEditText.Text = ClickableLabel(SplitedText.Get(AHPager.CurrentPage),AHPager.CurrentPage)
	txtGotFocus.RequestFocus
	ime1.HideKeyboard
	pnlfootershow_Click
End Sub

Sub btnbookmark_Click
	
	If Library.Bookmark(BookID,Data.Get("sID"),AHPager.CurrentPage,False) Then
		ToastMessageShow("این صفحه از کتاب نشان شد",False)
	 	btnbookmark.TextColor = Colors.Red
	Else
		ToastMessageShow("این صفحه از کتاب حذف نشان شد",False)
		btnbookmark.TextColor = Colors.RGB(128, 128, 128)
	End If
	
	ime1.HideKeyboard
	txtGotFocus.RequestFocus
	
End Sub

Sub Pagers_PageChanged (Position As Int)
		
	currentEditText = Labels.Get(Position)
	sk1.Value = Position
	
	If currentEditText.Visible = False Then
		currentEditText.Text = ClickableLabel(SplitedText.Get(Position),Position)
		currentEditText.Visible = True
	End If
	
	If Library.Bookmark(BookID,Data.Get("sID"),AHPager.CurrentPage,True) Then
		btnbookmark.TextColor = Colors.Red
		imgbookmark.BringToFront
		imgbookmark.SetVisibleAnimated(500,True)
		timerBookmark.Initialize("tmrBookmark",2000)
		timerBookmark.Enabled = True
	Else
		btnbookmark.TextColor = Colors.RGB(128, 128, 128)
		timerBookmark.Enabled = False
		imgbookmark.SetVisibleAnimated(400,False)
	End If
	
End Sub

Sub tmrBookmark_Tick
	timerBookmark.Enabled = False
	imgbookmark.SetVisibleAnimated(400,False)	
End Sub

Sub btnmenus_Click
	
	pnlfooter.SetLayoutAnimated(500,0,100%y+pnlfooter.Height,100%x,pnlfooter.Height)
	pnlheader1.SetLayoutAnimated(500,0,-pnlheader1.Height,100%x,pnlheader1.Height)
	txtGotFocus.RequestFocus
	ime1.HideKeyboard
	
End Sub

Sub DetectWord(txt1 As EditText,StartSelection As Int,EndSelection As Int) As String
	
	If StartSelection <> EndSelection Then Return ""
	
	Dim o,p As String
	
	Try
		For i = StartSelection To txt1.Text.Length-1
			
			If txt1.Text.SubString2(i,i+1) = " " Then
				CurrentEndSelection = i
				Exit
			Else
				o = o & txt1.Text.SubString2(i,i+1)
			End If
		Next
		
		For j = StartSelection-1 To 0 Step -1
			If txt1.Text.SubString2(j,j+1) = " " Then
				CurrentStartSelection = j+1
				Exit
			Else
				p = p & txt1.Text.SubString2(j,j+1)
			End If
		Next
		
		Dim result As String
		result = Library.StrReverse(p) & o

		Return result
	
	Catch
		Return ""
	End Try
	
End Sub

Sub txtcontent_FocusChanged (HasFocus As Boolean)
	
	If HasFocus Then
		Dim res As String
		CurrentStartSelection = Library.getSelectionStart(currentEditText)
		CurrentEndSelection = Library.getSelectionEnd(currentEditText)
		res = DetectWord(currentEditText,CurrentStartSelection,CurrentEndSelection)
		
		If res.Length > 0 Then
			ime1.HideKeyboard
			txtGotFocus.RequestFocus
			GetDataFromChoosenWord(res)
		
		Else
			HidePanelText
		End If
		
	End If
	
End Sub

Sub ClickableLabel(text As String,PageNumber As Int) As RichString
	
	Dim note,highlight As List
	note = Library.ReadNotes(BookID,Data.Get("sID"),PageNumber)
	highlight = Library.ReadHightlight(BookID,Data.Get("sID"),PageNumber)
	
'	For i = 1 To 100
'		text = text.Replace($"(${i})"$,$" (${i}) "$)
'	Next
	
	Dim rc As RichString
	rc.Initialize(text)
	
	If note.IsInitialized Then
		For i = 0 To note.Size - 1
			Dim m As Map
			m = note.Get(i)
			Dim color As Int
			rc.BackColor(Colors.Yellow,m.Get("SelectionStart"),m.Get("SelectionEnd"))
		Next
	End If
	
	If highlight.IsInitialized Then
		For i = 0 To highlight.Size - 1
			Dim m As Map
			m = highlight.Get(i)
			Dim color As Int
			color = m.Get("Color")
			rc.BackColor(color,m.Get("SelectionStart"),m.Get("SelectionEnd"))
		Next
	End If
	
	If SearchWord.Length > 0 Then
		Dim offset As Int
		offset = text.IndexOf(SearchWord)
	
		Do While offset <> -1
			rc.BackColor(Colors.Red,offset,offset+SearchWord.Length)
			offset = text.IndexOf2(SearchWord,offset+SearchWord.Length)	
		Loop
	End If
	
	'currentRichString = rc
	Return rc

End Sub

Sub txtcontent_TextChanged (Old As String, New As String)
	If Old <> New Then
		currentEditText.Text = ClickableLabel(SplitedText.Get(AHPager.CurrentPage),AHPager.CurrentPage)
	End If
End Sub

Sub Activity_KeyPress (KeyCode As Int) As Boolean 'Return True to consume the event

End Sub

Sub imgbookmark_Click
	btnbookmark_Click
End Sub

Sub LoadSetting
	
	Dim font_size,font_family As String
	font_size = Library.Manager.GetString("fontsize")
	font_family = Library.Manager.GetString("fontfamily")
	
	If IsNumber(font_size) = False Then font_size = "14"
	If font_family.Length = 0 Then font_family = "byekan"
	
	Dim blnState As Boolean
	blnState = Library.Manager.GetBoolean("state")
	
	Setting.Put("FontSize",font_size)
	Setting.Put("FontFamily",font_family)
	Setting.Put("State",blnState)
	
End Sub