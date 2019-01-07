Type=StaticCode
Version=4.01
ModulesStructureVersion=1
B4J=true
@EndOfDesignText@
'Static code module
Sub Process_Globals
	Private fx As JFX
	Private lvlist As ListView
	Public sType As String
	Private frm1 As Form
	Private loading As Loading
	Private imgicon As ImageView
	Private lblcatname As Label
	Private pnlover As Pane
End Sub

Sub ShowList
	
	If frm1.IsInitialized = False Then
		frm1.Initialize("",470,594)
	End If
	
	frm1.Title = "لیست نتایج"
	frm1.Resizable = False
	frm1.RootPane.LoadLayout("frmlist_site_social")
	frm1.Show
	Library.CenterFormOnScreen(frm1)
	loading.Initialize(frm1)
	
	If sType = "link" Then
		frm1.Title = "شبکه های اجتماعی"
	Else
		frm1.Title = "آدرس سایت ها"
	End If
	
	If File.Exists(File.DirApp & "/Data","main_db.db") = False Then
		loading.Show("در حال دریافت داده ها...")
		Dim hu As HttpJob
		hu.Initialize("download_base",Me)
		hu.Download(Library.BaseUrl & "/data/main_db.db")
	Else
		Load
	End If
	
End Sub

Sub Load
	
	Dim dbBase As BaseInformationApp
	Dim ls As List
	
	ls.Initialize
	dbBase.Initialize
	
	ls = dbBase.GetData(sType)
	
	For i = 0 To ls.Size - 1
		
		Dim p1 As Pane
		p1.Initialize("")
		p1.LoadLayout("frmtemplate_category")
		p1.SetSize(400,60)
		lvlist.Items.Add(p1)
		
		Dim temp As Map
		temp = ls.Get(i)
		
		lblcatname.Text = temp.Get("sTitle")
		pnlover.Tag = temp
		
		If sType = "site" Then
			imgicon.SetImage(fx.LoadImage(File.DirAssets,"net.png"))
		Else
			imgicon.SetImage(fx.LoadImage(File.DirAssets,"social.png"))
		End If
		
	Next

End Sub

Sub pnlover_MouseClicked (EventData As MouseEvent)
	
	Dim p As Pane
	p = Sender
	
	Dim ms As Map
	ms = p.Tag
	
	fx.ShowExternalDocument(ms.Get("sLink1"))
	
End Sub

Sub JobDone(Job As HttpJob)
	
	loading.Hide
	
	If Job.Success Then
		If Job.JobName = "download_base" Then
			Dim ou As OutputStream
			ou = File.OpenOutput(File.DirApp & "/Data","main_db.db",False)
			File.Copy2(Job.GetInputStream,ou)
			ou.Close
			Load
		Else
			Dim ou As OutputStream
			File.MakeDir(File.DirApp,"Downloads")
			ou = File.OpenOutput(File.DirApp & "\Downloads",Job.JobName,False)
			File.Copy2(Job.GetInputStream,ou)
			ou.Close
			fx.Msgbox(frm1,"فایل دانلود شده و در حافظه خارجی شما ذخیره شد","توجه")
		End If
	End If
End Sub