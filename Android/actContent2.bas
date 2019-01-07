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
	Public Data As Map
	Public BookID As String
	Private timerStartLoadItem As Timer
	Public RefererPage,SearchWord As String
End Sub

Sub Globals
	Private ph As Phone
	Private sGravity As Int
	Private currentFontSize As Int
	Private music As MediaPlayer
	Private pnlfindnext As Panel
	Private pnlfooter As Panel
	Private sk1 As SeekBar
	Dim first As Boolean
	Dim phone1 As Phone
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
	Private SplitedText,SplitedText1,Labels As List
	Private lbltitlefooter As Label
	Private btnbookmark As Button
	Private txtGotFocus As EditText
	Private ime1 As IME
	Private imgbookmark As ImageView
	Private timerBookmark As Timer
	Dim CountPage As Int
	Private Setting As Map
	Private pnlsetting As Panel
	Private spfont As Spinner
	Private lblltr As Label
	Private chkltr As ToggleButton
	Private spbright As Spinner
	Private skbright As SeekBar
	Private lblfontsize As Label
	Dim justify As TeamEightJustify
	Dim timerJustify As Timer
	Private pb1 As ProgressBar
	Private ckhautobright As ToggleButton
	Private linecount,otherLineCount As Int
	Private PopupMenu As RSPopupMenu
End Sub

Sub Activity_Create(FirstTime As Boolean)
	
	Activity.LoadLayout("frmcontent")
	Activity.AddMenuItem("تنظیمات","btnFont")
	
	If Activity.Width > Activity.Height Then
		Dim p As Phone
		p.SetScreenOrientation(1)
	End If
	
	Dim m2 As Map
	Dim du As Categories
	du.Initialize
	m2 = du.GetBookInformation(BookID)
	
	If m2.Get("sLanguage") = "انگلیسی" Then
		sGravity = Gravity.LEFT
	Else
		sGravity = Gravity.RIGHT
	End If
	
	music.Initialize2("")
	
	If ph.SdkVersion > 10 Then
		Library.ChangeCheckBoxImage(chkltr)
		Library.ChangeCheckBoxImage(ckhautobright)
	End If
	
	spfont.Color = Colors.Transparent
	spfont.DropdownBackgroundColor = Colors.White
	spfont.DropdownTextColor = Colors.Black
	spbright.Color = Colors.Transparent
	spbright.DropdownBackgroundColor = Colors.White
	spbright.DropdownTextColor = Colors.Black
	
	first = True
	
	Setting.Initialize
	AHContainer.Initialize
	DB.Initialize(BookID)
	ime1.Initialize("IMEME")
	
	Dim r As Reflector
	r.Target = r.GetActivity
	r.Target = r.RunMethod("getWindow")
	r.Target = r.RunMethod("getAttributes")
	skbright.Value = r.GetField("screenBrightness")
	
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
	
	Dim lb As EditText
	lb.Initialize("")
	lb.TextSize = 18
	If Library.Manager.getString("font").Length = 0 Then Library.Manager.SetString("font","bn.ttf")
	lb.Typeface = Typeface.LoadFromAssets(Library.Manager.getString("font"))
	Library.LabelSpace(lb,Library.SpaceLabel)
	
	currentFontSize = 18
	
	Activity.AddView(lb,0,0,100%x,100%y)
	
	#region Detect device size
	Dim device_size As Float
	device_size = Library.GetDevicePhysicalSize
	
	If File.Exists(File.DirInternal,"count_page" & Data.Get("sID")) = True Then 
		CountPage = File.ReadString(File.DirInternal,"count_page" & Data.Get("sID"))
	Else
		Dim cont As String
		cont = content
		
		If device_size > 6 Then ' above 6 inch
			CountPage = su.MeasureMultilineTextHeight(lb,cont) / Activity.Height * 2
			DoEvents
			Library.SpaceLabel = 1.6
		else If device_size > 5.5 Then And device_size < 7 Then 'between 5.5 and 7
			CountPage = su.MeasureMultilineTextHeight(lb,cont) / Activity.Height * 2.8
			DoEvents
			Library.SpaceLabel = 1.5
		Else if device_size < 4.7 And device_size > 4.5 Then 'between 5.5 and 4
			CountPage = su.MeasureMultilineTextHeight(lb,cont) / Activity.Height * 2.1
			DoEvents
			Library.SpaceLabel = 1.4
		Else if device_size < 5.5 And device_size > 4 Then 'between 5.5 and 4
			CountPage = su.MeasureMultilineTextHeight(lb,cont) / Activity.Height * 1.7
			DoEvents
			Library.SpaceLabel = 1.5
		Else if device_size < 4 Then ' below 4 inch
			CountPage = su.MeasureMultilineTextHeight(lb,cont) / Activity.Height * 1.3
			DoEvents
			Library.SpaceLabel = 1.3
		End If
		File.WriteString(File.DirInternal,"count_page" & Data.Get("sID"),CountPage)
	End If
	#end region
	
	If CountPage = 0 Then CountPage = 1
	
	Dim offset,textCount As Int : offset = 0
	textCount = content.Length / CountPage
	sk1.Max = CountPage-1
	sk1.Value = 0
	
	SplitedText.Initialize
	
	If File.Exists(File.DirInternal,"list_" & Data.Get("sID")) = True Then
		SplitedText = Library.ReadData(Data.Get("sID"))
	Else
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
		SplitedText1 = SplitedText
		Library.WriteData(SplitedText,Data.Get("sID"))
	End If

	justify.Initialize
	
	Dim ps As List
	ps.Initialize
	
	Dim t1 As String
	t1 = SplitedText.Get(0)
	ps.Add(justify.JustifyByAddSpace(lb,t1,False,0.5))
	DoEvents
	
	lb.RemoveView
	
	For i = 1 To CountPage - 1
		
		Dim lbl As EditText
		lbl.Initialize("txtcontent")
		lbl.TextSize = 18
		lbl.inputType=Bit.Or(lbl.InputType, 524288)
		lbl.TextColor = Colors.Black
		lbl.Color = Colors.White
		lbl.Typeface = Typeface.LoadFromAssets(Library.Manager.getString("font"))
		Library.LabelSpace(lbl,Library.SpaceLabel)
		lbl.Visible = False
		lbl.Gravity = sGravity
		
		If ph.SdkVersion < 11 Then
			Library.DisableLongClickEdittext(lbl)	
		End If
		
		Labels.Add(lbl)
		AHContainer.AddPage(lbl,"")
		
		If i = CountPage Or RefererPage = i-1 Then
			lbl.Text = ClickableLabel(justify.JustifyByAddSpace(lb,SplitedText.Get(i-1),False,0.5),i-1)
			lbl.Visible = True
			currentEditText = lbl
		End If
		
		DoEvents
			
	Next

	AHPager.Initialize(AHContainer,"Pagers")

	Activity.AddView(AHPager,0,0,100%x,100%y)
	AHPager.GotoPage(CountPage-1,True)
	Pagers_PageChanged (CountPage-1)
	
	If RefererPage <> "" Then
		AHPager.GotoPage(RefererPage,True)
	End If
	
	If SearchWord.Length > 0 Then
		pnlfindnext.Visible = True
		pnlfindnext.BringToFront
	End If
	
	pb1.Visible = False
	
	#region menu
	If ph.SdkVersion > 10 Then
		PopupMenu.Initialize("popup",btnmenu)
		PopupMenu.AddMenuItem(1,1,"یادداشت ها")
		PopupMenu.AddMenuItem(2,2,"برجسته ها")
		PopupMenu.AddMenuItem(3,3,"نشان شده ها")
		PopupMenu.AddMenuItem(4,4,"نسخه الکترونیکی کتاب")
		PopupMenu.AddMenuItem(5,5,"چکیده های کتاب")
	Else
		Activity.AddMenuItem("یادداشت ها","btnnotesshow")
		Activity.AddMenuItem("برجسته ها","btnhighlightshow")
		Activity.AddMenuItem("نشان شده ها","btnbookmarkshow")
		Activity.AddMenuItem("نسخه الکترونیکی کتاب","btnpdfversion")
		Activity.AddMenuItem("چکیده های کتاب","btnsummaryshow")
		If ph.SdkVersion < 11 Then
			Activity.AddMenuItem("منوی ابزار","btnToolsMenu")
		End If
	End If
	#end region
	
End Sub

#Region Activity menus
Sub btnnotesshow_Click
	popup_MenuItemClick(1)
End Sub

Sub btnhighlightshow_Click
	popup_MenuItemClick(2)
End Sub

Sub btnbookmarkshow_Click
	popup_MenuItemClick(3)
End Sub

Sub btnpdfversion_Click
	popup_MenuItemClick(4)
End Sub

Sub btnsummaryshow_Click
	popup_MenuItemClick(5)
End Sub
#End Region

Sub btnFont_Click
	
	spfont.Clear
	spbright.Clear
	
	spbright.Add("حالت روز")
	spbright.Add("حالت شب")
	spbright.Add("حالت سیپا")
	
	spfont.Add("پیش فرض")
	spfont.Add("یکان")
	spfont.Add("tahoma")
	spfont.Add("کودک")
	
	pnlsetting.BringToFront
	pnlsetting.SetVisibleAnimated(500,True)
	
End Sub

Sub Activity_Resume
	actPDF.BlnBookPDF = False
End Sub

Sub Activity_Pause (UserClosed As Boolean)
	RefererPage = ""
	SearchWord = ""
	Library.LastPageVisit(BookID,Data.Get("sID"),AHPager.CurrentPage,False)
	Library.Right2LeftAnimation
End Sub

Sub txtcontent_Click
		
End Sub

Sub txtcontent_LongClick
	
	If ph.SdkVersion > 10 Then
		ShowToolsMenu
	Else
		PopupMenu.Show
	End If
	
End Sub

Sub btnToolsMenu_Click
	ShowToolsMenu
End Sub

Sub ShowToolsMenu
	
	If pnlsetting.Visible = True Then	pnlsetting.SetVisibleAnimated(500,False)
			
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
	pnlsetting.SetVisibleAnimated(500,False)
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
	Activity.OpenMenu
End Sub

Sub popup_MenuItemClick (ItemId As Int) As Boolean
	
	pnlheader.BringToFront
	pnlheader.SetLayoutAnimated(500,0,-pnlheader.Height,100%x,pnlheader.Height)
	pnlfooter.BringToFront
	pnlfooter.SetLayoutAnimated(500,0,100%y+pnlfooter.Height,100%x,pnlfooter.Height)
	pnlheader1.SetLayoutAnimated(500,0,-pnlheader1.Height,100%x,pnlheader1.Height)
		
	If ItemId = 1 Then
		actBookChapter.ChoosenTab = 2
		StartActivity(actBookChapter)
	
	Else if ItemId = 2 Then
		actBookChapter.ChoosenTab = 2
		StartActivity(actBookChapter)
	
	Else if ItemId = 3 Then
		actBookChapter.ChoosenTab = 1
		StartActivity(actBookChapter)
	
	Else if ItemId = 4 Then
		
		If File.Exists(File.DirInternal & "/book","book.pdf") Then
			If Library.CheckExistPdfViewer = False Then Return True
			'Library.AddPDFBook(BookID,"book_" & BookID & ".pdf")
			File.WriteString(File.DirRootExternal,"temp_book","book_" & BookID & ".pdf")
			Dim in1 As Intent
			in1.Initialize(in1.ACTION_MAIN,"")
			in1.SetComponent("com.library.pdfviewer/.main")
			StartActivity(in1)
			Return True
		Else
			Msgbox("این کتاب دارای نسخه الکترونیکی نمیباشد","توجه")
		End If
		
	Else if ItemId = 5 Then
'		actSummaryBook.BookID = BookID
'		StartActivity(actSummaryBook)
		actBookChapter.ChoosenTab = 4
		StartActivity(actBookChapter)
	
	Else
		actBookChapter.ChoosenTab = 0
		StartActivity(actBookChapter)
	End If
	
End Sub

Sub JobDone(Job As HttpJob)
	
	ProgressDialogHide
	
	If Job.Success Then
		If Job.JobName = "download_pdf" Then
			Dim ou As OutputStream
			ou = File.OpenOutput(File.DirRootExternal,"book_" & BookID & ".pdf",False)
			File.Copy2(Job.GetInputStream,ou)
			ou.Close
			
			Library.AddPDFBook(BookID,"book_" & BookID & ".pdf")
			
'			actPDF.filename = "book_" & BookID & ".pdf"
'			actPDF.BlnBookPDF = True
'			StartActivity(actPDF)
			
			Library.CheckExistPdfViewer
			
			File.WriteString(File.DirRootExternal,"temp_book","book_" & BookID & ".pdf")
			Dim in1 As Intent
			in1.Initialize(in1.ACTION_MAIN,"")
			in1.SetComponent("com.library.pdfviewer/.main")
			StartActivity(in1)
			
		End If
	Else
		ToastMessageShow("دانلود با خطا مواجه شد" & CRLF & "لطفا اینترنت خود را بررسی کنید",False)
	End If
	
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
	
	CallSubDelayed2(Me,"JustifyMe",currentEditText)
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
			Library.WriteNote(BookID,Data.Get("sID"),AHPager.CurrentPage,in1.Input,CurrentStartSelection,CurrentEndSelection,text)
			currentEditText.Text = ClickableLabel(SplitedText.Get(AHPager.CurrentPage),AHPager.CurrentPage)
			CallSubDelayed2(Me,"JustifyMe",currentEditText)
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
				
'				Dim multimedia As Map
'				multimedia = DB.GetMultimedia(Data.Get("sID"),text.Replace("،",""))
'				
'				If multimedia.IsInitialized Then
'					If multimedia.Get("Type") = "phone" Then
'						Library.Call(multimedia.Get("Value"))
'					else if multimedia.Get("Type") = "music" Then
'						If music.IsPlaying = False Then
'							music.Load(File.DirInternal & "/book/" & BookID,multimedia.Get("Value"))
'							music.Play
'						Else
'							music.Stop
'						End If
'					Else
'						actPreview.sType = multimedia.Get("Type")
'						actPreview.Filename = multimedia.Get("Value")
'						actPreview.BookID = BookID
'						StartActivity(actPreview)
'						
'					End If
'					
'					Return
'	
'				Else
'					btnhighlight.TextColor = Colors.RGB(128,128,128)
'					pnlfootershow_Click
'					Return
'				End If
				
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
	
	Log(Position)
	
	If first = True Then
		first = False
		CallSubDelayed2(Me,"getLineCount_Click","1")
		Return
	End If
	
	currentEditText = Labels.Get(Position)
	
	sk1.Value = Position
	
	If currentEditText.Visible = False Or currentEditText.TextSize <> currentFontSize Then
		
		currentEditText.TextSize = currentFontSize
		currentEditText.Text = ClickableLabel(SplitedText.Get(Position),Position)
		CallSubDelayed2(Me,"JustifyMe",currentEditText)
		CallSubDelayed2(Me,"getLineCount_Click","0")
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

Sub JustifyMe(Label As Label)
	Label.Text = justify.JustifyByAddSpace(Label,Label.Text,False,0.5)
	Label.Text = ClickableLabel(Label.Text,AHPager.CurrentPage)
End Sub

Public Sub getLineCount_Click(id As String)  As Int
 	
	Dim su As StringUtils
	
	Try
	    Dim OneLineHeight As Long = su.MeasureMultilineTextHeight(currentEditText, "T"&Chr(13)&Chr(10)&"T")/2 ' Get the height of two lines, so line spacing is here, divide by 2
		DoEvents
	    Dim H As Long = su.MeasureMultilineTextHeight(currentEditText, currentEditText.Text)
		If id = "1" Then
	    	linecount = Ceil(H/OneLineHeight)
		Else
			Dim countExtraLine As Int
			countExtraLine = linecount - Ceil(H/OneLineHeight)
			
			If countExtraLine < 0 Then
				Library.LabelSpace(currentEditText,1.5)
			Else if countExtraLine > 0 Then
				Library.LabelSpace(currentEditText,1.6)
			End If
			
		End If
	
	Catch
	End Try
	
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
		
		Log(result)
		Return result
	
	Catch
		Return ""
	End Try
	
End Sub

Sub txtcontent_TextChanged (Old As String, New As String)
Return
	If Old <> New Then
		currentEditText.Text = ClickableLabel(SplitedText.Get(AHPager.CurrentPage),AHPager.CurrentPage)
	End If
End Sub

Sub txtcontent_FocusChanged (HasFocus As Boolean)
	
	If pnlsetting.Visible = True Then	pnlsetting.SetVisibleAnimated(500,False)
	
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
		
		pnlfindnext.Visible = True
		
		Dim offset As Int
		offset = text.IndexOf(SearchWord)
	
		Do While offset <> -1
			rc.BackColor(Colors.Red,offset,offset+SearchWord.Length)
			offset = text.IndexOf2(SearchWord,offset+SearchWord.Length)	
		Loop
		
	End If
	
	Return rc

End Sub

Sub Activity_KeyPress (KeyCode As Int) As Boolean 'Return True to consume the event
	
	If KeyCode = KeyCodes.KEYCODE_BACK Then
	
	Else If KeyCode = KeyCodes.KEYCODE_MENU Then
		Activity.OpenMenu	
	End If
	
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

Sub chkltr_CheckedChange(Checked As Boolean)
	
	If Checked Then
		For i = 0 To Labels.Size - 1
			Dim lb As EditText
			lb = Labels.Get(i)
			lb.Gravity = Gravity.LEFT
		Next
	Else
		For i = 0 To Labels.Size - 1
			Dim lb As EditText
			lb = Labels.Get(i)
			lb.Gravity = Gravity.RIGHT
		Next
	End If
	
End Sub

Sub lblminus_Click
	
	lblfontsize.Text = NumberFormat2(lblfontsize.Text - 1,0,0,0,False)
	currentFontSize = currentFontSize - 1
	currentEditText.TextSize = currentFontSize
	CallSubDelayed(Me,"JustifyMe2")
	DoEvents
	
End Sub

Sub lblplus_Click
	
	lblfontsize.Text = NumberFormat2(lblfontsize.Text + 1,0,0,0,False)
	currentFontSize = currentFontSize + 1
	currentEditText.TextSize = currentFontSize
	CallSubDelayed(Me,"JustifyMe2")
	DoEvents
	
End Sub

Sub JustifyMe2
	currentEditText.Text = justify.JustifyByAddSpace(currentEditText,SplitedText.Get(AHPager.CurrentPage),False,0.5)
	currentEditText.Text = ClickableLabel(currentEditText.Text,AHPager.CurrentPage)
End Sub

Sub spfont_ItemClick (Position As Int, Value As Object)
	
	If Position = 1 Then
		'currentEditText.Typeface = Typeface.LoadFromAssets("byekan.ttf")
		ChangeFonts("byekan.ttf")
	else if Position = 2 Then
		'currentEditText.Typeface = Typeface.LoadFromAssets("tahoma.ttf")
		ChangeFonts("tahoma.ttf")
	else if Position = 3 Then
		'currentEditText.Typeface = Typeface.LoadFromAssets("kodak.ttf")
		ChangeFonts("kodak.ttf")
	else if Position = 0 Then
		'currentEditText.Typeface = Typeface.LoadFromAssets("bnazanin.ttf")
		ChangeFonts("bn.ttf")
	End If
	
End Sub

Private Sub ChangeFonts(name As String)
		
	For i = 0 To Labels.Size - 1
		Dim lb As EditText
		lb = Labels.Get(i)
		lb.Typeface = Typeface.LoadFromAssets(name)
	Next
	
End Sub

Sub chkjustify_CheckedChange(Checked As Boolean)
	
	If Checked Then
		CallSubDelayed2(Me,"ChangeJustify",CreateMap("view":currentEditText))
	Else
		currentEditText.Text = ClickableLabel(SplitedText.Get(AHPager.CurrentPage),AHPager.CurrentPage)
	End If
	
End Sub

Sub spbright_ItemClick (Position As Int, Value As Object)
	
	If Position = 1 Then ' for night
		For i = 0 To Labels.Size - 1
			Dim lb As EditText
			lb = Labels.Get(i)
			lb.TextColor = Colors.White
			lb.Color = Colors.Black
		Next
	Else if Position = 0 Then ' for day
		For i = 0 To Labels.Size - 1
			Dim lb As EditText
			lb = Labels.Get(i)
			lb.TextColor = Colors.Black
			lb.Color = Colors.White
		Next
	Else if Position = 2 Then ' for sipa
		For i = 0 To Labels.Size - 1
			Dim lb As EditText
			lb = Labels.Get(i)
			lb.TextColor = Colors.RGB(170,102,23)
			lb.Color = Colors.RGB(215,164,164)
		Next
	End If
	
End Sub

Sub ckhautobright_CheckedChange(Checked As Boolean)
	
	If Checked Then
		Library.WriteSetting("screen_brightness_mode", 0)
	Else
		Library.WriteSetting("screen_brightness_mode", 1)
	End If
	
End Sub

Sub skbright_ValueChanged (Value As Int, UserChanged As Boolean)

	phone1.SetScreenBrightness(Max(Value,5)/100)
	
End Sub

Sub lblshowlistfont_Click
	Library.OpenSpinner(spfont)
End Sub

Sub lblbright_Click
	Library.OpenSpinner(spbright)
End Sub

Sub btnbackfind_Click
	
	Dim s As String
	
	If AHPager.CurrentPage = 0 Then Return
	
	For i = AHPager.CurrentPage-1 To 0 Step -1
		s = SplitedText.Get(i)
		
		If s.IndexOf(SearchWord) > -1 Then
			AHPager.GotoPage(i,True)
			Exit
		End If
	Next
	
End Sub

Sub btnnextfind_Click
	
	Dim s As String
	
	If AHPager.CurrentPage = AHContainer.Count -1 Then Return
	
	For i = AHPager.CurrentPage+1 To AHContainer.Count - 1
		s = SplitedText.Get(i)
		
		If s.IndexOf(SearchWord) > -1 Then
			AHPager.GotoPage(i,True)
			Exit
		End If
	Next
	
End Sub