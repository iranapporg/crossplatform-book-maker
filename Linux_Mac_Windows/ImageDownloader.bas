Type=Class
Version=4.01
ModulesStructureVersion=1
B4J=true
@EndOfDesignText@
'Class module
Sub Class_Globals
	Private tasks As Map
	Private ongoingTasks As Map
End Sub

Public Sub Initialize
	tasks.Initialize
	ongoingTasks.Initialize
End Sub

Public Sub Download (ImageViewsMap As Map)
	For Each iv As ImageView In ImageViewsMap.Keys
		Dim link As String = ImageViewsMap.Get(iv)
		tasks.Put(iv, link)
		If ongoingTasks.ContainsKey(link) = False Then
			ongoingTasks.Put(link, "")
			Dim j As HttpJob
			j.Initialize(link, Me)
			j.Download(link)
		End If
	Next
End Sub

Private Sub JobDone(Job As HttpJob)
	ongoingTasks.Remove(Job.JobName)
	If Job.Success Then
		Dim bmp As Image = Job.GetBitmap
		If tasks.IsInitialized Then
			For Each iv As ImageView In tasks.Keys
				Dim link As String = tasks.Get(iv)
				If link = Job.JobName Then
					tasks.Remove(iv)
					iv.SetImage(bmp)
				End If
			Next
		End If
	Else
		Log("Error downloading image: " & Job.JobName & CRLF & Job.ErrorMessage)
	End If
	Job.Release
End Sub