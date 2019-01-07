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
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.

End Sub

Sub Globals
	Private txtname As EditText
	Private txtemail As EditText
	Private txtcomment As EditText
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Activity.LoadLayout("frmcontact")
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)
	Library.Left2RightAnimation
	Activity.Finish
End Sub

Sub btnmenu_Click
	Activity.Finish
End Sub

Sub btnsend_Click
	
	If txtname.Text.Length < 3 Or txtemail.Text.IndexOf("@") = -1 Or txtcomment.Text.Length < 3 Then
		ToastMessageShow("لطفا اطلاعات را درست وارد کنید",False)
		Return	
	End If
	
	If Library.InternetState = False Then
		ToastMessageShow("لطفا اینترنت را فعال کرد",False)
		Return	
	End If
	
	ProgressDialogShow("در حال ارسال")
	Dim hhu As HttpJob
	hhu.Initialize("send",Me)
	hhu.PostString(Library.URL & "/contact","name=" & txtname.Text & "&email=" & txtemail.Text & "&comment=" & txtcomment.Text)
	
End Sub

Sub JobDone(Job As HttpJob)
	If Job.Success Then
		If Job.GetString = "1" Then
			ToastMessageShow("پیام شما با موفقیت ارسال شد",True)
			Activity.Finish
		Else
			ToastMessageShow("برنامه با خطا مواجه شد دوباره تلاش کنید",False)	
		End If
	End If
End Sub