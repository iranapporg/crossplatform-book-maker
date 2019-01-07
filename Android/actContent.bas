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
End Sub

Sub Globals
	Private pnlfooter As Panel
	Private sk1 As SeekBar
	Private btnback As Button
	Private btnback1 As Button
	Private btnbackground As Button
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
	Private currentEditText As EditText
	Private FindWord As Boolean
	Private CurrentChoosenWord As String
End Sub

Sub Activity_Create(FirstTime As Boolean)

	Activity.LoadLayout("frmcontent")
	AHContainer.Initialize
	DB.Initialize(Data.Get("sBookID"))
	
	Dim content As String
	content = Data.Get("sText")
	
	Dim lb As Label
	lb.Initialize("")
	lb.TextSize = 16
	lb.Typeface = Typeface.LoadFromAssets("byekan.ttf")
	Library.LabelSpace(lb,1.5)
	Activity.AddView(lb,0,0,100%x,100%y)
	
	Dim CountPage As Int
	CountPage = su.MeasureMultilineTextHeight(lb,content) / Activity.Height * 2.7
	
	If CountPage = 0 Then CountPage = 1
	
	Dim offset,textCount As Int : offset = 0
	textCount = content.Length / CountPage
	sk1.Max = CountPage
	sk1.Value = 0
	lb.RemoveView
	
	For i = 1 To CountPage
		
		Dim lbl As EditText
		lbl.Initialize("txtcontent")
		lbl.InputType = lbl.INPUT_TYPE_NONE
		lbl.SingleLine = False
		lbl.TextSize = 17
		lbl.TextColor = Colors.Black
		lbl.Color = Colors.White
		lbl.Typeface = Typeface.LoadFromAssets("bn.ttf")
		Library.LabelSpace(lbl,1.5)
	
		Dim justify As justify
		justify.justify(lbl)
		justify.run(lbl,100%x)
		lbl.Gravity = Gravity.RIGHT
   
		AHContainer.AddPage(lbl,"")
		
		Dim rs As String

		If offset + textCount > content.Length Then
			rs = content.SubString(offset)
		Else
			rs = content.SubString2(offset,offset + textCount)
		End If
		
		ClickableLabel(rs,lbl)
		
		offset = offset + textCount
		
		If i = 1 Then currentEditText = lbl
		
	Next
	
	AHPager.Initialize(AHContainer,"Pagers")
	Activity.AddView(AHPager,0,0,100%x,100%y)
	
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)
	Library.Right2LeftAnimation
End Sub

Sub ClickableText_ClickableLink(Text As String)

	Dim rs As Map
	rs = DB.GetFooters(Data.Get("sID"),Text.Replace(" ",""))
	
	If rs.ContainsKey("success") Then
		If currentEditText.IsInitialized Then
			currentEditText.SelectionStart = 0
		End If
		lblfooter.Text = rs.Get("sText")
		pnlfootershow.BringToFront
		pnlfootershow.Top = Activity.Height
		pnlheader.BringToFront
		pnlheader.Top = -pnlheader.Height
		pnlfooter.BringToFront
		pnlfooter.Top = Activity.Height
		pnlfootershow.SetLayoutAnimated(500,0,100%y-pnlfootershow.Height,100%x,pnlfootershow.Height)
		pnlheader1.SetLayoutAnimated(500,0,-pnlheader1.Height,100%x,pnlheader1.Height)
		FindWord = True
	Else
		CurrentChoosenWord = Text
		FindWord = True
		pnlheader.BringToFront
		pnlheader.SetLayoutAnimated(500,0,-pnlheader.Height,100%x,pnlheader.Height)
		pnlfooter.BringToFront
		pnlfooter.SetLayoutAnimated(500,0,100%y+pnlfooter.Height,100%x,pnlfooter.Height)
		pnlheader1.BringToFront
		pnlheader1.SetLayoutAnimated(500,0,0,100%x,pnlheader1.Height)
	End If
	
End Sub

Sub txtcontent_Click
	 
	If FindWord = True Then
		FindWord = False
		Return
	End If
	
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

Sub txtcontent_LongClick

End Sub

Sub ClickableLabel(text As String,Lbl As EditText) As String
	
	For i = 1 To 100
		text = text.Replace($"(${i})"$,$" (${i}) "$)
	Next
	
	Dim r() As String
	r = Regex.Split(" ",text)
	
	Dim ctv As ClickableTV
	
	For i = 0 To r.Length - 1
		ctv.AddLinkText(Lbl,r(i) & " ",Colors.Black,"ClickableText")
	Next
	
End Sub

Sub pnlfootershow_Click
	pnlfootershow.BringToFront
	pnlfootershow.SetLayoutAnimated(500,0,100%y,100%x,pnlfootershow.Height)
End Sub

Sub btnback_Click
	Activity.Finish
	StartActivity(actMenu)
End Sub

Sub btnsearch_Click
	
	Dim in1 As InputDialog
	in1.InputType = in1.INPUT_TYPE_TEXT
	in1.Hint = "کلید واژه"
	in1.HintColor = Colors.Gray
	
	If in1.Show("لطفا کلید واژه جستجو را وارد کنید","توجه","بگرد","انصراف","",Null) = DialogResponse.POSITIVE Then
		If in1.Input.Length = 0 Then Return
		
	End If
	
End Sub

Sub btnmenu_Click
	actBookChapter.sBookID = Data.Get("sBookID")
	Activity.Finish
	StartActivity(actBookChapter)
End Sub

Sub btncopy_Click
	Dim c1 As BClipboard
	c1.setText(CurrentChoosenWord)
	CurrentChoosenWord = ""
	ToastMessageShow("متن انتخاب شده به حافظه کپی شد",False)
End Sub

Sub btnshare_Click
	Library.ShareString("اشتراک گذاری",CurrentChoosenWord,"اشتراک گذاری")
End Sub

Sub btnbackme_Click
	pnlheader1.BringToFront
	pnlheader1.SetLayoutAnimated(500,0,-pnlheader1.Height,100%x,pnlheader1.Height)
End Sub

Sub btnhighlight_Click

	 Dim start,length As Int
	 start = currentEditText.Text.IndexOf(CurrentChoosenWord)
	 length = CurrentChoosenWord.length
	 
	 Dim rich As RichString
	 rich.Initialize(currentEditText.Text)
	 rich.BackColor(Colors.Red,Library.getSelectionStart(currentEditText),Library.getSelectionEnd(currentEditText))
	 currentEditText.Text = rich
	 
End Sub

Sub btnbackground_Click
	
End Sub

Sub btnnote_Click

	Dim in1 As InputDialog
	in1.InputType = in1.INPUT_TYPE_TEXT
	in1.Hint = "یادداشت"
	in1.HintColor = Colors.Gray
	
	If in1.Show("یادداشت خود را وارد کنید","توجه","ذخیره","انصراف","",Null) = DialogResponse.POSITIVE Then
		If in1.Input.Length = 0 Then Return
		
	End If
	
End Sub

Sub sk1_ValueChanged (Value As Int, UserChanged As Boolean)
	If UserChanged = False Then
		sk1.Value = 0
		Return
	End If
	AHPager.CurrentPage = Value
End Sub