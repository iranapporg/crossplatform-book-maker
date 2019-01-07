Type=StaticCode
Version=4.01
ModulesStructureVersion=1
B4J=true
@EndOfDesignText@
'Static code module
Sub Process_Globals
	Private fx As JFX	
	Private frm1 As Form
	Private loading As Loading
	Private lv1 As ListView
	Private btnpoints As Button
	Private btnsendpoint As Button
	Private pnlpoints As Pane
	Private currentMessage As Map
	Private WebView1 As WebView
End Sub

Sub ShowMessage
	
	frm1.Initialize("frm1",470,610)
	frm1.Resizable = False
	frm1.RootPane.LoadLayout("frmmessage")
	frm1.Show

	Library.CenterFormOnScreen(frm1)

	loading.Initialize(frm1)
	
	loading.Show("در حال دریافت...")
	
	Dim hu As HttpJob
	hu.Initialize("getmessage",Me)
	hu.Download(Library.URL & "get_message")
	
End Sub

Sub JobDone(Job As HttpJob)
	
	loading.Hide
	
	Dim js As JSONParser
	
	If Job.Success Then
			
		js.Initialize(Job.GetString)
		
		Dim m1 As List
		m1 = js.NextArray
		
		For i = 0 To m1.Size - 1
			Dim ms As Map
			ms = m1.Get(i)	
			
			Dim ss As String
			ss = ms.Get("sMessage")
			
			If ss.Length > 100 Then
				ss = ss.SubString2(0,100)
			End If
			
			Dim lb As Label
			lb.Initialize("lbs")
			lb.TextSize = 14
			lb.Alignment = "TOP_RIGHT"
			lb.Text = ms.Get("sTitle") & " : " & CRLF & ss
			lb.Font = fx.CreateFont("tahoma",14,False,False)
			lb.Tag = ms
			lb.TextColor = fx.Colors.Black
			lb.SetSize(lv1.Width-30,270)
			lv1.Items.Add(lb)
			
			Dim ls As Label
			ls.Initialize("")
			ls.SetSize(lv1.Width,1)
			lv1.Items.Add(ls)
			
		Next
	End If
	
End Sub

Sub lbs_MouseClicked (EventData As MouseEvent)
	
	Dim lb As Label
	lb = Sender
	
	Dim m As Map
	m = lb.Tag
	currentMessage = m
	
	WebView1.Visible = True
	WebView1.LoadHtml(m.Get("sMessage"))
	
	pnlpoints.Visible = True
	
End Sub

Sub btnpoints_Action
	actPoints.currentMessage = currentMessage
	actPoints.ShowPoint
End Sub

Sub btnsendpoint_Action
	actSendPoint.currentMessage = currentMessage
	actSendPoint.ShowPoint
End Sub

Sub frm1_CloseRequest (EventData As Event)
	If WebView1.Visible = True Then
		EventData.Consume
		pnlpoints.Visible = False
		WebView1.Visible = False
		Return
	End If
End Sub