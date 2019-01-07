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
	Private WebView1 As WebView
	Private pnlpoints As Panel
	Private pnllistpoints As Panel
	Private svpoints As ScrollView
	Private pnlsendpoint As Panel
	Private txtname As EditText
	Private txtcomment As EditText
	Private currentMessage As Map
	Private lblucomment As Label
	Private lbluname As Label
	Private lbludate As Label
	Private pnlpoint As Panel
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Activity.LoadLayout("frmmessage")
	
	ProgressDialogShow("در حال دریافت پیام ها")
	Dim hu As HttpJob
	hu.Initialize("getmessage",Me)
	hu.Download(Library.URL & "get_message")

End Sub

Sub JobDone(Job As HttpJob)
	
	ProgressDialogHide
	
	Dim js As JSONParser
	
	If Job.Success Then
		
		If Job.JobName = "sendpoint" Then
			If Job.GetString = "1" Then
				ToastMessageShow("نظر شما با موفقیت ارسال شد",False)
				Return
			Else
				ToastMessageShow("متاسفانه نظر شما ارسال نشد دوباره تلاش کنید",False)
			End If
			pnlsendpoint.Visible = False
		
		Else if Job.JobName = "getpoints" Then
			Dim l1 As List
			l1.Initialize
			js.Initialize(Job.GetString)
			l1 = js.NextArray
			
			pnllistpoints.SetVisibleAnimated(600,True)
			
			Dim top As Int
			
			For i = 0 To l1.Size - 1
				Dim m2 As Map
				m2 = l1.Get(i)
				
				Dim p2 As Panel
				p2.Initialize("")
				svpoints.Panel.AddView(p2,0,top,svpoints.Width,0)
				p2.LoadLayout("frmpoints")
				p2.Height = pnlpoint.Height
				
				lblucomment.Text = m2.Get("sComment")
				lbluname.Text = m2.Get("sName")
				lbludate.Text = library.GetDate(m2.Get("sDate"))
				
				top = top + p2.Height
				
			Next
			
			svpoints.Panel.Height = top
			
		Else
			
			js.Initialize(Job.GetString)
			
			Dim m1 As List
			m1 = js.NextArray
			
			Dim su As StringUtils
			Dim html As Html
			Dim top As Int = 20
			
			For i = 0 To m1.Size - 1
				Dim ms As Map
				ms = m1.Get(i)	
				
				Dim ss As String
				ss = html.FromHtml(ms.Get("sMessage"))
				
				If ss.Length > 100 Then
					ss = ss.SubString2(0,100)
				End If
				
				Dim lb As Label
				lb.Initialize("lbs")
				lb.TextSize = 14
				lb.Gravity = Gravity.RIGHT
				Dim rc As RichString
				rc.Initialize("{b}" & ms.Get("sTitle") & "{b}" & CRLF & ss)
				rc.Style2(rc.STYLE_BOLD,"{b}")
				lb.Text = rc
				lb.Tag = ms
				lb.TextColor = Colors.Black
				sv1.Panel.AddView(lb,0,top,sv1.Width,0)
				
				lb.Height = su.MeasureMultilineTextHeight(lb,lb.Text)
				top = top + lb.Height + 30
				
				Dim ls As Label
				ls.Initialize("")
				ls.Color = Colors.Gray
				sv1.Panel.AddView(ls,10,top,100%x-30,1)
				top = top + 20
				
			Next
			
			sv1.Panel.Height = top
		End If
	End If
End Sub

Sub Activity_Resume

End Sub

Sub lbs_Click
	
	Dim lb As Label
	lb = Sender
	
	Dim m As Map
	m = lb.Tag
	currentMessage = m
	
	lb.SetColorAnimated(300,Colors.Gray,Colors.Transparent)
	
	WebView1.Visible = True
	WebView1.LoadHtml(m.Get("sMessage"))
	
	pnlpoints.SetVisibleAnimated(500,True)
	
End Sub

Sub Activity_Pause (UserClosed As Boolean)
	Activity.Finish
	Library.Right2LeftAnimation
End Sub

Sub btnmenu_Click
	If WebView1.Visible = True Then
		WebView1.Visible = False
		Return
	End If
	Activity.Finish	
End Sub

Sub Activity_KeyPress (KeyCode As Int) As Boolean 'Return True to consume the event
	If KeyCode = KeyCodes.KEYCODE_BACK Then
		If WebView1.Visible = True Then
			WebView1.Visible = False
			pnlpoints.Visible = False
			Return True
		End If
	End If
End Sub

Sub btnpoints_Click
	
	If svpoints.Panel.NumberOfViews > 3 Then
		pnllistpoints.SetVisibleAnimated(600,True)
		Return	
	End If
	
	ProgressDialogShow("در حال دریافت نظرات...")
	
	Dim hu As HttpJob
	hu.Initialize("getpoints",Me)
	hu.Download(Library.URL & "get_message_points/" & currentMessage.Get("sID"))
	
End Sub

Sub btnsendpoint_Click
	pnlsendpoint.Visible = True
End Sub

Sub btnclose_Click
	pnllistpoints.Visible = False
End Sub

Sub btnsend_Click
	
	If txtname.Text.Length < 3 Or txtcomment.Text.Length < 5 Then
		ToastMessageShow("لطفا اطلاعات را درست وارد کنید|",False)
		Return	
	End If
	
	If Library.InternetState = False Then
		ToastMessageShow("لطفا اینترنت را فعال کرد",False)
		Return	
	End If
	
	ProgressDialogShow("در حال ارسال...")
	
	pnlsendpoint.Visible = False
	
	Dim hu As HttpJob
	hu.Initialize("sendpoint",Me)
	hu.PostString(Library.URL & "add_points",$"name=${txtname.Text}&comment=${txtcomment.Text}&device=mobile&id=${currentMessage.Get("sID")}"$)
	
	txtname.Text = ""
	txtcomment.Text = ""
	
End Sub

Sub btnclosesendpoint_Click
	pnlsendpoint.Visible = False
End Sub