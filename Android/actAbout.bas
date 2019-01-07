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
	Private WebView1 As WebView
End Sub

Sub Activity_Create(FirstTime As Boolean)
	
	Activity.LoadLayout("frmabout")
	
	If File.Exists(File.DirInternal,"about") Then
		WebView1.LoadHtml(File.ReadString(File.DirInternal,"about"))
		Return
	End If
	
	If Library.InternetState = False Then
		ToastMessageShow("لطفا اینترنت را فعال کرد",False)
		Activity.Finish
		Return	
	End If
	
	ProgressDialogShow("لطفا منتظر بمانید")
			
	Dim hu As HttpJob
	hu.Initialize("about",Me)
	hu.Download(Library.BaseURL & "about.html")
	
End Sub

Sub JobDone(Job As HttpJob)
	
	ProgressDialogHide
	
	If Job.Success Then
		If Job.JobName = "about" Then
			Dim ou As OutputStream
			ou = File.OpenOutput(File.DirInternal,"about",False)
			File.Copy2(Job.GetInputStream,ou)
			ou.Close
			WebView1.LoadHtml(File.ReadString(File.DirInternal,"about"))
		End If		
	End If
	
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)
	Activity.Finish
	Library.Right2LeftAnimation
End Sub

Sub btnmenu_Click
	Activity.Finish
End Sub