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

	Private lv1 As ListView
End Sub

Sub Activity_Create(FirstTime As Boolean)
	
	Activity.LoadLayout("frmlistbooks")
	
	If File.Exists(File.DirInternal,"listpdf") = False Then
		ToastMessageShow("هیچ کتاب الکترونیکی در کتابخانه وجود ندارد",True)
		Activity.Finish
		Return		
	End If
	
	Dim m2 As Map
	m2 = File.ReadMap(File.DirInternal,"listpdf")
	
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
	
	For i = 0 To m2.Size - 1
		
		Dim cat As Categories
		Dim temp As Map
		cat.Initialize
		temp = cat.GetBookInformation(m2.GetKeyAt(i))
		lv1.AddTwoLinesAndBitmap2(temp.Get("sTitle"),"",LoadBitmap(File.DirAssets,"book.png"),m2.GetKeyAt(i))
			
	Next
	
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)
	Library.Right2LeftAnimation
End Sub

Sub btnmenu_Click
	
End Sub

Sub lv1_ItemClick (Position As Int, Value As Object)
	actPDF.filename = "book_" & Value & ".pdf"
	actPDF.BlnBookPDF = True
	Library.AddPDFBook(Value,"book_" & Value & ".pdf")
	StartActivity(actPDF)	
End Sub