Type=Service
Version=6
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region  Service Attributes 
	#StartAtBoot: False
#End Region

Sub Process_Globals
	Dim data As Object
	Dim event As String
End Sub

Sub Service_Create
	
End Sub

Sub Service_Start (StartingIntent As Intent)
	CallSub(Me,event)
End Sub

Sub Service_Destroy

End Sub

Sub ModifyBookDownloads
	Dim hu As HttpJob
	hu.Initialize("modifybook",Me)
	hu.PostString(Library.URL & "add_download/" & data,"")
End Sub

Sub JobDone(Job As HttpJob)
	CancelScheduledService("")
	StopService("")
	Log(Job.Success)
End Sub