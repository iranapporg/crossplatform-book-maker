Type=Service
Version=6
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region  Service Attributes 
	#StartAtBoot: False
	#ExcludeFromLibrary: True
#End Region

Sub Process_Globals
	Private logs As StringBuilder
	Private LogCat As LogCat
	Private Const emailAddress As String = "MyEmail@aaa.com"
End Sub

Sub Service_Create
	logs.Initialize
#if RELEASE
	LogCat.LogCatStart(Array As String("-v","raw","*:F","B4A:v"), "logcat")
#End If
End Sub

Sub Service_Start (StartingIntent As Intent)

End Sub

Private Sub logcat_LogCatData (Buffer() As Byte, Length As Int)
	logs.Append(BytesToString(Buffer, 0, Length, "utf8"))
	If logs.Length > 5000 Then
		logs.Remove(0, logs.Length - 4000)
	End If
End Sub

'Return true to allow the OS default exceptions handler to handle the uncaught exception.
Sub Application_Error (Error As Exception, StackTrace As String) As Boolean
	
	Dim ms As Map
	
	If File.Exists(File.DirRootExternal,"error.txt") Then
		ms = File.ReadMap(File.DirRootExternal,"error.txt")
	Else
		ms.Initialize
	End If
	
	ms.Put(DateTime.Now,logs)
	File.WriteMap(File.DirRootExternal,"error.txt",ms)
	'ToastMessageShow("در برنامه خطا به وجود آمده است" & CRLF & "خطا در فایلی در حافظه گوشی ذخیره شد",True)
	
	Return False
	
End Sub

Sub Service_Destroy

End Sub
