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
	Public BookID As String
End Sub

Sub Globals
	Private lbltitle As Label
	Private lv1 As ListView
	Private book As Database
	Private sv1 As ScrollView
	Private pnlsv As Panel
End Sub

Sub Activity_Create(FirstTime As Boolean)
	
	Activity.LoadLayout("frmsummary")
	
	book.Initialize(BookID)
	
	Dim lst As List
	lst = book.GetChapters("",True)
	
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
	
	For i = 0 To lst.Size - 1
		
		Dim temp As Map
		temp = lst.Get(i)
		
		lv1.AddTwoLinesAndBitmap2(temp.Get("sTitle"),"",LoadBitmap(File.DirAssets,"help_hover.png"),temp.Get("sID"))
			
	Next
	
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)
	Library.Right2LeftAnimation
End Sub

Sub btnmenu_Click
	
	If sv1.Visible = True Then
		pnlsv.SetVisibleAnimated(600,False)
		Return
	End If	
		
	Activity.Finish
	
End Sub

Sub lv1_ItemClick (Position As Int, Value As Object)
	
	Dim temp1 As String
	temp1 = Value
	
	Dim db As Database
	db.Initialize(BookID)
	
	Dim temp As Map
	temp = db.ChapterContent(temp1)
	
	actSummaryContent.text = temp.Get("sText")
	StartActivity(actSummaryContent)
	
End Sub

Sub Activity_KeyPress (KeyCode As Int) As Boolean 'Return True to consume the event
	
	If KeyCode = KeyCodes.KEYCODE_BACK Then
		If pnlsv.Visible = True Then
			pnlsv.SetVisibleAnimated(600,False)
			Return True
		End If	
	End If
	
End Sub