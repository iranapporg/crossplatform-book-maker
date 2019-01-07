Type=StaticCode
Version=4.01
ModulesStructureVersion=1
B4J=true
@EndOfDesignText@
'Static code module
Sub Process_Globals
	Private fx As JFX
	Private frm1 As Form
	Private txtcomment As TextArea
	Private txtemail As TextField
	Private txtname As TextField
	Private loading As Loading
End Sub

Sub ShowContact
	
	If frm1.IsInitialized = False Then
		Dim s As Screen
		s = fx.Screens.Get(0)
		frm1.Initialize("",470,460)
	End If
	
	frm1.Resizable = False
	frm1.RootPane.LoadLayout("frmcontact")
	frm1.Show
	
	Library.CenterFormOnScreen(frm1)
	
	loading.Initialize(frm1)
	
End Sub

Sub btnsend_Action
	
	If txtname.Text.Length < 3 Or txtemail.Text.IndexOf("@") = -1 Or txtcomment.Text.Length < 3 Then
		fx.Msgbox(frm1,"لطفا اطلاعات را درست وارد کنید","توجه")
		Return	
	End If
	
	loading.Show("در حال ارسال")
	Dim hhu As HttpJob
	hhu.Initialize("send",Me)
	hhu.PostString(Library.URL & "/contact","name=" & txtname.Text & "&email=" & txtemail.Text & "&comment=" & txtcomment.Text)
	
End Sub

Sub JobDone(Job As HttpJob)
	
	loading.Hide
	
	If Job.Success Then
		If Job.GetString = "1" Then
			fx.Msgbox(frm1,"پیام شما با موفقیت ارسال شد","توجه")
			frm1.Close
		Else
			fx.Msgbox(frm1,"برنامه با خطا مواجه شد دوباره تلاش کنید","توجه")	
		End If
	End If
	
End Sub