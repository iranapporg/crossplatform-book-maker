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
	Dim sType As String
End Sub

Sub Globals
	Private lbltitle As Label
	Private lv1 As ListView
	Private WebView1 As WebView
End Sub

Sub Activity_Create(FirstTime As Boolean)
	
	Activity.LoadLayout("frmlinks")
	
	If sType = "link" Then
		lbltitle.Text = "شبکه های اجتماعی"
	Else
		lbltitle.Text = "آدرس سایت ها"
	End If
	
	If File.Exists(File.DirInternal,"main_db.db") = False Then
		ProgressDialogShow("در حال دریافت داده ها...")
		Dim hu As HttpJob
		hu.Initialize("download_base",Me)
		hu.Download(Library.BaseUrl & "/data/main_db.db")
	Else
		Load
	End If
	
End Sub

Sub Load
	
	Dim dbBase As BaseInformationApp
	Dim ls As List
	
	ls.Initialize
	dbBase.Initialize
	
	ls = dbBase.GetData(sType)
	
	#Region Change listview style
	If Library.GetDevicePhysicalSize < 6 Then
		lv1.TwoLinesAndBitmap.Label.TextSize = 14
		lv1.SingleLineLayout.Label.TextSize = 14
		lv1.TwoLinesAndBitmap.SecondLabel.TextSize = 13
	Else
		lv1.TwoLinesAndBitmap.Label.TextSize = 19
		lv1.SingleLineLayout.Label.TextSize = 19
		lv1.TwoLinesAndBitmap.SecondLabel.TextSize = 22
	End If
	
	'Library.SetDivider(lv3,Colors.Gray,1)
	
	lv1.TwoLinesAndBitmap.Label.TextColor	= Library.ConvertHex2Int("#272727")
	lv1.SingleLineLayout.Label.TextColor = Library.ConvertHex2Int("#272727")
	lv1.TwoLinesAndBitmap.SecondLabel.TextColor	=  Library.ConvertHex2Int("#272727")
	lv1.TwoLinesAndBitmap.Label.Gravity = Gravity.RIGHT
	lv1.TwoLinesAndBitmap.SecondLabel.Gravity = Gravity.RIGHT
	
	Dim c3 As ColorDrawable
	c3.Initialize(Library.ConvertHex2Int("#eaeaea"),0)
	lv1.SingleLineLayout.Background = c3
	
	lv1.TwoLinesAndBitmap.Label.Typeface	= Typeface.LoadFromAssets("tahoma.ttf")
	lv1.SingleLineLayout.Label.Typeface	= Typeface.LoadFromAssets("tahoma.ttf")
	lv1.TwoLinesAndBitmap.SecondLabel.Typeface=  Typeface.LoadFromAssets("icomoon.ttf")
	
	lv1.TwoLinesAndBitmap.SecondLabel.Width	= lv1.Width-17dip
	lv1.TwoLinesAndBitmap.Label.Width			= lv1.Width-50dip
	lv1.SingleLineLayout.Label.Width		= lv1.Width-50dip
	
	lv1.TwoLinesAndBitmap.Label.Gravity = Bit.Or(Gravity.RIGHT,Gravity.CENTER_VERTICAL)
	lv1.TwoLinesAndBitmap.SecondLabel.Gravity = Bit.Or(Gravity.RIGHT,Gravity.CENTER_VERTICAL)
	lv1.SingleLineLayout.Label.Gravity = Bit.Or(Gravity.RIGHT,Gravity.CENTER_VERTICAL)
	
	lv1.TwoLinesAndBitmap.ImageView.Left = 100%x - 60dip
	lv1.TwoLinesAndBitmap.Label.Width = 100%x - 125dip 
	lv1.TwoLinesAndBitmap.SecondLabel.Width = 100%x - 125dip 
	#End Region
	
	For i = 0 To ls.Size - 1
		
		Dim m1 As Map
		m1 = ls.Get(i)
		
		If sType = "link" Then
			lv1.AddTwoLinesAndBitmap2(m1.Get("sTitle"),m1.Get("sLink1"),LoadBitmap(File.DirAssets,"social.png"),m1)
		Else
			lv1.AddTwoLinesAndBitmap2(m1.Get("sTitle"),m1.Get("sLink1"),LoadBitmap(File.DirAssets,"net.png"),m1)
		End If
		
	Next

End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)
	Library.Right2LeftAnimation
	Activity.Finish
End Sub

Sub btnmenu_Click
	Activity.Finish
End Sub

Sub Activity_KeyPress (KeyCode As Int) As Boolean 'Return True to consume the event
	
	If KeyCode = KeyCodes.KEYCODE_BACK Then
		If WebView1.Visible = True Then	
			WebView1.Visible = False
			Return True
		End If
	End If
	
End Sub

Sub lv1_ItemClick (Position As Int, Value As Object)
	
	Dim m As Map
	m = Value
	
	If m.Get("sType") = "link" Then
		WebView1.Visible = True
		WebView1.LoadUrl(m.Get("sLink1"))
		ToastMessageShow("در حال بارگذاری آدرس سایت...",True)
	Else
		Dim id1 As id
		Dim res As Int = id1.InputList1(Array As String("بازدید سایت","دانلود فایل"),"انتخاب کنید")
		
		If res = 0 Then
			WebView1.Visible = True
			WebView1.LoadUrl(m.Get("sLink1"))
			ToastMessageShow("در حال بارگذاری آدرس سایت...",True)
		else if res = 1 Then
			ProgressDialogShow("در حال دانلود فایل...")
			Dim hu As HttpJob
			hu.Initialize(Library.GetFilename(m.Get("sLink2")),Me)
			hu.Download(m.Get("sLink2"))	
		End If
		
	End If
	
End Sub

Sub JobDone(Job As HttpJob)
	
	ProgressDialogHide
	
	If Job.Success Then
		If Job.JobName = "download_base" Then
			Dim ou As OutputStream
			ou = File.OpenOutput(File.DirInternal,"main_db.db",False)
			File.Copy2(Job.GetInputStream,ou)
			ou.Close
			Load
		Else
			Dim ou As OutputStream
			ou = File.OpenOutput(File.DirRootExternal,Job.JobName,False)
			File.Copy2(Job.GetInputStream,ou)
			ou.Close
			ToastMessageShow("فایل دانلود شده و در حافظه خارجی شما ذخیره شد",True)
		End If
	End If
End Sub