Type=Service
Version=6
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region  Service Attributes 
	#StartAtBoot: True
	
#End Region

Sub Process_Globals

End Sub

Sub Service_Create

End Sub

Sub Service_Start (StartingIntent As Intent)
	
	If Library.InternetState = True Then
		Dim hu As HttpJob
		hu.Initialize("bookcount",Me)
		hu.PostString(Library.URL & "count_book","")
	End If
	
	StartServiceAt("",DateTime.Now + 177000,True)
	
End Sub

Sub JobDone(Job As HttpJob)
	
	If Job.Success Then
		If Job.JobName = "bookcount" Then
			Dim md As Map
			Dim js As JSONParser
			js.Initialize(Job.GetString)
			md = js.NextObject
			If md.ContainsKey("result") Then
				Dim c1,c2 As Int
				Dim db As Categories
				db.Initialize
				c1 = db.GetBookCount
				c2 = md.Get("result")
				If c1 <> c2 Then
					CallSubDelayed(actMenu,"NewBookFound")
				End If
			End If
			
		End If		
	End If
	
End Sub

Sub Service_Destroy

End Sub
