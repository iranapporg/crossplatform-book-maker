Type=Class
Version=6
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
'Class module
Sub Class_Globals
	Private lsheader As ListView
	Private sm As SlidingMenu
	Private meModule As Object
	Dim imgLogo As Label
	Private price As String
	Private tmrdelay As Timer
	Private value As String
End Sub

Public Sub Initialize(titlebarHeight As Int,Module As Object)
	
	Dim offset As Int = 107
	meModule = Module
	
	sm.Initialize("Menu")
	
	sm.BehindOffset = offset
	
	sm.Mode = sm.RIGHT
	
	lsheader.Initialize("lsheader")
	
	imgLogo.Initialize("")
	imgLogo.Text = ""
	imgLogo.SetBackgroundImage(LoadBitmap(File.DirAssets,"header.jpg"))
	imgLogo.Gravity = Gravity.CENTER
	imgLogo.TextColor = Colors.White
	imgLogo.TextSize = 22
	imgLogo.Typeface = Typeface.LoadFromAssets("byekan.ttf")
	
	sm.Menu.Color = Library.ConvertHex2Int("#f6f5f5")
	
	sm.Menu.AddView(imgLogo,0,0, 100%x-offset, titlebarHeight)
	sm.Menu.AddView(lsheader,0,imgLogo.Height, 100%x-offset, 100%y - imgLogo.Height)
	
	Library.ChangeListviewStyle(lsheader)
	Library.SetDivider(lsheader,Colors.aRGB(70,211,211,211),1)
	
	AddHeader
	
	tmrdelay.Initialize("tmr1",340)
	
End Sub

Public Sub ToggleMenu
	
	If sm.Visible = True Then
		sm.HideMenus
	Else
		sm.ShowMenu
	End If
	
End Sub

Public Sub AddHeader

	lsheader.Clear
	'lsheader.AddTwoLines2("صفحه اصلی","","home")
	'lsheader.AddTwoLines2("کتاب های الکترونیکی دانلود شده","","pdf")
	lsheader.AddTwoLines2("آخرین خوانده شده ها","","latest")
	lsheader.AddTwoLines2("دانلود شده ها","","download")
	lsheader.AddTwoLines2("جستجوی در کتاب ها","","search")
	lsheader.AddTwoLines2("صندوق پیام ها","","message")
	lsheader.AddTwoLines2("شبکه های اجتماعی","","link")
	lsheader.AddTwoLines2("آدرس های سایت","","site")
	lsheader.AddTwoLines2("بروز رسانی","","")
	lsheader.AddTwoLines2("تنظیمات","","setting")
	lsheader.AddTwoLines2("درباره ما","","about")
	lsheader.AddTwoLines2("ارتباط با ما","","contact")
	lsheader.AddTwoLines2("اشتراک برنامه","","share")
	lsheader.AddTwoLines2("خبرنامه","","newsletter")
	
End Sub

Private Sub lsheader_ItemClick (Position As Int, Value1 As Object)
	
	sm.HideMenus
	DoEvents
	value = Value1
	tmrdelay.Enabled = True
	
End Sub

Private Sub tmr1_Tick
	tmrdelay.Enabled = False
	DoAction
End Sub

Private Sub DoAction
	
	If value = "latest" Then
		CallSubDelayed2(actMenu,"GoPage",0)
	
	else if value = "share" Then
		Dim ar As AriaLib
		StartActivity(ar.ShareApplication("com.book.bahonar","اشتراک برنامه"))
		
	else if value = "pdf" Then
		StartActivity(actListPDF)
		
	else if value = "download" Then
		CallSubDelayed2(actMenu,"GoPage",1)
		
	else if value = "search" Then
		StartActivity(actSearchBooks)
		
	else if value = "contact" Then
		StartActivity(actContact)
		
	else if value = "message" Then
		StartActivity(actMessage)
		
	else if value = "link" Or value = "site" Then
		If File.Exists(File.DirInternal,"main_db.db") = False Then
			If Library.InternetState = False Then
				ToastMessageShow("لطفا اینترنت را فعال کرد",False)
				Return	
			End If
		End If
		actLinks.sType = value
		StartActivity(actLinks)
		
	else if value = "about" Then
		StartActivity(actAbout)
		
	else if value = "setting" Then
		Dim se As AHPreferenceScreen
		se.Initialize("تنظیمات","اعمال تغییرات اپ")
		'se.AddCheckBox("show_type","نوع نمایش کتاب ها در قفسه","لیستی","جدولی",False,"")
		se.AddList("show_types","نوع نمایش","نمایش کتاب در قفسه کتابخانه","جدولی","",Array As String("جدولی","لیستی"))
		StartActivity(se.CreateIntent)
	
	else if value = "newsletter" Then
		Dim in1 As InputDialog
		in1.Hint = "example@domain.com"
		in1.HintColor = Colors.Gray
		If in1.Show("لطفا ایمیل خود را برای عضویت در خبرنامه وارد کنید","توجه","تایید","انصراف","",Null) = DialogResponse.POSITIVE Then
			If in1.Input.IndexOf("@") > -1 Then
				NewsLetter(in1.Input)
			Else
				ToastMessageShow("لطفا ایمیل را معتبر وارد کنید",False)
			End If
		End If
		
	End If
	
End Sub

Sub Show
	sm.ShowMenu
End Sub

Sub Hide
	sm.HideMenus
End Sub

Sub NewsLetter(Email As String)
	
	ProgressDialogShow("در حال بررسی")
	
	Dim hu As HttpJob
	hu.Initialize("newsletter",Me)
	hu.PostString(Library.URL & "newsletter","email=" & Email)
	
End Sub

Sub JobDone(Job As HttpJob)
	
	ProgressDialogHide
	
	If Job.Success Then
		If Job.JobName = "newsletter" Then
			If Job.GetString = "1" Then
				ToastMessageShow("شما با موفقیت در خبرنامه عضو شدید",False)
			Else if Job.GetString = "2" Then
				ToastMessageShow("عضویت شما از خبرنامه حذف شد",False)
			End If
		End If		
	End If
	
End Sub