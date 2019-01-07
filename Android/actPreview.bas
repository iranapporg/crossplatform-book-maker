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
	Public sType,Filename,BookID As String
End Sub

Sub Globals
	Private pnl1 As Panel
	Private BookPath As String
End Sub

Sub Activity_Create(FirstTime As Boolean)
	
	Activity.LoadLayout("frmpreview")
	
	BookPath = File.DirInternal & "/book/" & BookID
	
	If sType = "image" Then
		Dim img As ImageView
		img.Initialize("")
		pnl1.AddView(img,0,0,pnl1.Width,pnl1.Height)
		
		Dim pica As Picasso
		pica.Initialize
		pica.LoadFile(File.Combine(BookPath,Filename)).Resize(pnl1.Width,pnl1.Height).CenterCrop.IntoImageView(img)
	
	Else if sType = "film" Then
	
	Else if sType = "link" Then
		Dim wb1 As WebView
		wb1.Initialize("")
		pnl1.AddView(wb1,0,0,pnl1.Width,pnl1.Height)
		wb1.LoadUrl(Filename)
		
	End If
	
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)
	Activity.Finish
	Library.Left2RightAnimation
End Sub

Sub btnmenu_Click
	Activity.Finish	
End Sub