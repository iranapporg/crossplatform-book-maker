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
	Public currentMessage As Map
	Private lblucomment As Label
	Private lbluname As Label
	Private lbludate As Label
	Private sv1 As ListView
End Sub

Sub ShowPoint
	
	frm1.Initialize("",400,570)
	frm1.Resizable = False
	frm1.RootPane.LoadLayout("frmlistpoints")
	frm1.Show

	Library.CenterFormOnScreen(frm1)
	
	loading.Initialize(frm1)
	loading.Show("در حال دریافت...")
	
	Dim hu As HttpJob
	hu.Initialize("getpoints",Me)
	hu.Download(Library.URL & "get_message_points/" & currentMessage.Get("sID"))
	
End Sub

Sub JobDone(Job As HttpJob)
	
	loading.Hide
	
	Dim js As JSONParser
	
	If Job.Success Then
		
		If Job.JobName = "getpoints" Then
			Dim l1 As List
			l1.Initialize
			js.Initialize(Job.GetString)
			l1 = js.NextArray
		
			If l1.Size = 0 Then
				fx.Msgbox(frm1,"هیچ نظری برای این خبر وجود ندارد","توجه")
				frm1.Close
				Return	
			End If
			
			For i = 0 To l1.Size - 1
				Dim m2 As Map
				m2 = l1.Get(i)
				
				Dim p2 As Pane
				p2.Initialize("")
				p2.LoadLayout("frmpoints")
				p2.SetSize(400,240)
				sv1.Items.Add(p2)
				
				lblucomment.Text = m2.Get("sComment")
				lbluname.Text = m2.Get("sName")
				lbludate.Text = Library.GetDate(m2.Get("sDate"))
				
			Next
		
		End If
	End If
End Sub