Type=Service
Version=5.5
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region  Service Attributes 
	#StartAtBoot: False
	
#End Region

Sub Process_Globals

End Sub

Sub Service_Create

End Sub

Sub Service_Start (StartingIntent As Intent)

End Sub

Sub Service_Destroy

End Sub

Sub ExtractBooks
	
	If Library.Manager.GetBoolean("extract") = False Then
		
		If File.Exists(File.DirAssets,"default.txt") Then
			Dim ls As List
			ls = File.ReadList(File.DirAssets,"default.txt")
			
			For i = 0 To ls.Size - 1
				
				Dim temp As String
				temp = ls.Get(i)
				temp = temp.Replace("book_","").Replace(".zip","")
				
				File.MakeDir(File.DirInternal,"book/" & temp)
				Dim zip As ABZipUnzip
				zip.ABUnzip(File.Combine(File.DirAssets,ls.Get(i)),File.Combine(File.DirInternal,"book/" & temp))
				
			Next
			
		End If
		
	End If
	
	Library.Manager.SetBoolean("extract",True)
	
End Sub