Type=StaticCode
Version=4.01
ModulesStructureVersion=1
B4J=true
@EndOfDesignText@
'Static code module
Sub Process_Globals
	Private fx As JFX
	Private frm1 As Form
	Private loading As Loading
	Private wb1 As WebView
End Sub

Sub ShowAbout
	
	If frm1.IsInitialized = False Then
		Dim s As Screen
		s = fx.Screens.Get(0)
		frm1.Initialize("",s.MaxX,s.MaxY)
	End If
	
	frm1.Resizable = False
	frm1.RootPane.LoadLayout("frmabout")
	frm1.Show
	Library.CenterFormOnScreen(frm1)
	
	If File.Exists(File.DirApp & "/Data","about") Then
		wb1.LoadHtml(File.ReadString(File.DirApp & "/Data","about"))
		Return
	End If
	
	Dim hu As HttpJob
	hu.Initialize("about",Me)
	hu.Download(Library.BaseURL & "about.html")
	
	loading.Initialize(frm1)
	loading.Show("در حال نمایش...")
	
End Sub

Sub JobDone(Job As HttpJob)
	
	loading.Hide
	
	If Job.Success Then
		If Job.JobName = "about" Then
			Dim ou As OutputStream
			ou = File.OpenOutput(File.DirApp & "/Data","about",False)
			File.Copy2(Job.GetInputStream,ou)
			ou.Close
			wb1.LoadHtml(File.ReadString(File.DirApp & "/Data","about"))
		End If		
	End If
	
End Sub
