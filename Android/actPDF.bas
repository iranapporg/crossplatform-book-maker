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
	Public filename,BookID As String
	Public BlnBookPDF As Boolean
End Sub

Sub Globals
'	Dim pdf As PDFViewer
End Sub

Sub Activity_Create(FirstTime As Boolean)

'	Dim Intent1 As Intent
'	Intent1.Initialize(Intent1.ACTION_VIEW, "file://" & File.Combine(File.DirRootExternal & "/", filename))
'	Intent1.SetComponent("android/com.android.internal.app.ResolverActivity")
'	Intent1.SetType("application/pdf")
'	StartActivity(Intent1)
'	Activity.Finish
'	Return

	File.WriteString(File.DirRootExternal,"temp_book",filename)
	Dim in1 As Intent
	in1.Initialize(in1.ACTION_MAIN,"")
	in1.SetComponent("com.library.pdfviewer/.main")
	StartActivity(in1)
	Activity.Finish
	Return
	
	If BlnBookPDF = False Then
		
		If File.Exists(File.DirInternal & "/book/" & BookID,filename) = False Then
			Msgbox("فایل الکترونیکی قابل اجرا نیست","خطا")
	        Activity.Finish
	        Return
		End If
		
		File.Copy(File.DirInternal & "/book/" & BookID,filename,File.DirRootExternal,filename)
	
	End If
	
	CallSubDelayed(Me,"AddPDF")
	
End Sub

Sub Activity_Resume
	
End Sub

Sub AddPDF
	
'	pdf.init
'	Activity.AddView(pdf,0,0,-1,-1)
'	
'	Try
'		
'		pdf.getpdf(File.Combine(File.DirRootExternal,filename))
'		
'		If pdf.isValid Then
'	        pdf.scrollToPage(0)
'			
'			If Activity.Width > Activity.Height Then
'	        	pdf.zoom(3)
'			End If
'	        
'	    Else
'	        Msgbox("فایل الکترونیکی قابل اجرا نیست","خطا")
'	        Activity.Finish
'	        Return
'	    End If
'	Catch
'		ToastMessageShow("لطفا دوباره کتاب را باز کنید",False)
'		Activity.Finish
'	End Try
	
End Sub

Sub Activity_Pause (UserClosed As Boolean)
'	pdf.finalize
	Activity.Finish
End Sub
