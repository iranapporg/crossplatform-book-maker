Type=StaticCode
Version=4.01
ModulesStructureVersion=1
B4J=true
@EndOfDesignText@
'Static code module
Sub Process_Globals
	Private fx As JFX
	Private frm1 As Form
	Private txtname As TextField
	Private txtcomment As TextField
	Private loading As Loading
	Public currentMessage As Map
End Sub

Sub ShowPoint
	
	frm1.Initialize("",440,590)
	frm1.Resizable = False
	frm1.RootPane.LoadLayout("frmsendpoint")
	frm1.Show

	Library.CenterFormOnScreen(frm1)
	
	loading.Initialize(frm1)
	
End Sub

Sub btnsend_Action
		
	If txtname.Text.Length < 3 Or txtcomment.Text.Length < 5 Then
		fx.Msgbox(frm1,"لطفا اطلاعات را درست وارد کنید|","توجه")
		Return	
	End If
	
	loading.Show("در حال ارسال...")
	
	Dim hu As HttpJob
	hu.Initialize("sendpoint",Me)
	hu.PostString(Library.URL & "add_points",$"name=${txtname.Text}&comment=${txtcomment.Text}&device=mobile&id=${currentMessage.Get("sID")}"$)
	
	txtname.Text = ""
	txtcomment.Text = ""
	
End Sub

Sub JobDone(Job As HttpJob)
	
	loading.Hide
	
	If Job.Success Then
		
		If Job.JobName = "sendpoint" Then
			If Job.GetString = "1" Then
				fx.Msgbox(frm1,"نظر شما با موفقیت ارسال شد","توجه")
				frm1.Close
				Return
			Else
				fx.Msgbox(frm1,"متاسفانه نظر شما ارسال نشد دوباره تلاش کنید","توجه")
			End If
		End If
		
	End If
End Sub