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
	Public referer As String
End Sub

Sub Globals
	Private slidepanel As SlidingPanels
End Sub

Sub Activity_Create(FirstTime As Boolean)
	
	slidepanel.Initialize("slide",300,Activity,Me,True)
	slidepanel.ModeFullScreen(5,False)
	
	'regsiter
	Dim help1 As Help
	Dim sv1 As ScrollView
	
	Dim c1 As ColorDrawable
	c1.Initialize(Colors.White,50)
	
	#Region Regsiter
	sv1.Initialize(0)
	slidepanel.Panels(0).AddView(sv1,0,0,Activity.Width,Activity.Height)
	help1.Initialize(sv1,Me)
	help1.AddHeader("راهنمای استفاده از برنامه","")
	help1.AddLabel("در این بخش کتاب هایی که اخیرا توسط شما خوانده شده در قسفه های کتاب قرار گرفته است",15,Colors.red)
	help1.AddLabel("شما میتوانید با درگ کردن به سمت راست به بخش کتاب هایی که دانلود کرده اید مراجعه کنید",15,Colors.RGB(80, 80, 80))
	help1.AddLine
	help1.AddImage("menu_1.png","",False)
	help1.AddLine
	help1.AddLabel("در تصویر بالا از سمت چپ با انتخاب آن منوی جستجوی در کتاب ها و مرتب سازی با نویسنده و موضوعات میباشد",14,Colors.Black)
	help1.AddLine
	help1.AddLabel("گزینه بعدی دانلود کتاب ها و دسته بندی ها از سرور میباشد که برای مرور کتاب های موجود کافیست وارد این قسمت شده و لیست کتاب ها رو مشاهده کنید و دانلود کنید",14,Colors.Black)
	help1.AddLine
	help1.AddImage("download_1.png","",False)
	help1.AddLabel("در تصویر بالا لیست کتاب ها با اندازه و عنوان آن قرار گرفته است.میتوانید بروی آن کلیک کنید تا اطلاعات کتاب را ببینید",14,Colors.Black)
	help1.AddLine
	help1.AddLabel("گزینه بعدی در صفحه منو نیز رجوع به آخرین صفحه از آخرین کتابی که توسط شما خوانده شده است میباشد",14,Colors.Black)
	help1.AddLine
	help1.AddLabel("برای خواندن کتاب کافیست روی کتاب مورد نظر کلیک کنید",14,Colors.Black)
	help1.AddLine
	help1.AddLabel("در صورتی که در بخش کتاب های دانلود شده روی کتاب کلیک طولانی کنید قادر به حذف کتاب یا عملیات دیگر خواهید بود",14,Colors.Black)
	help1.AddLine
	help1.AddLabel("و آخرین گزینه از منو نیز بخش منوی مخصوص این صفحه میباشد که با انتخاب آن منویی از سمت راست باز خواهد شد به شکل",14,Colors.Black)
	help1.AddLine
	help1.AddImage("menu_2.png","",False)
	help1.AddLine
	help1.AddLabel("در منو از ابتدا شروع میکنیم به کاربرد گزینه ها",14,Colors.Black)
	help1.AddLabel("1- نمایش کتاب های الکترونیکی که برای کتاب های مورد نظر دریافت کرده اید.قالب این فایل ها pdf میباشد",14,Colors.Black)
	help1.AddLabel("2- نمایش کتاب هایی که اخیرا مطالعه کرده اید",14,Colors.Black)
	help1.AddLabel("3- نمایش کتاب هایی که از بخش دانلود کتاب دریافت کرده اید",14,Colors.Black)
	help1.AddLabel("4- جستجو در کتاب های دانلود شده و لیست کردن آن ها که میتوانید هر کتاب را مرور کنید",14,Colors.Black)
	help1.AddLabel("5- در این بخش لیست پیام ها و اخباریی که در کتابخانه به شما ارسال شده است را مشاهده خواهید کرد.شما قادر به ارسال نظر خود خواهید بود",14,Colors.Black)
	help1.AddLabel("6- در این بخش شبکه های اجتماعی مورد نظر را مشاهده خواهید کرد",14,Colors.Black)
	help1.AddLabel("7- در این بخش وب سایت های مرتبط با کتابخانه قرار گرفته است",14,Colors.Black)
	help1.AddLabel("گزینه های دیگر نیز با توجه به عنوان آن میتوان به کاربرد گزینه پی برد",14,Colors.Black)
	help1.AddLine
	help1.AddLabel("طبق تصویر زیر وقتی شما کتابی را انتخاب کنید وارد سرفصل ها و موضوعات خواهید شد",14,Colors.Black)
	help1.AddImage("chapter_1.png","",False)
	help1.AddLabel("در این بخش شما میتوانید موضوعات را مرور کنید و وارد متن کتاب شوید و یا با درگ کردن صفحه یا لمس به چپ و راست به صفحات نشان شده ها و برجسته ها و یادداشت ها مراجعه کنید",14,Colors.Black)
	help1.AddLabel("در بالای صفحه نیز گزینه ای برای جستجو در موضوعات وجود دارد و نمایش کتاب الکترونیکی pdf مربوط به این کتاب در صورت دانلود و وجود آن میباشد",14,Colors.Black)
	help1.AddLine
	help1.AddLabel("در تصویر زیر نیز وقتی وارد متن کتاب میشوید خواهید توانست عملیات متداولی را انجام دهید",14,Colors.Black)
	help1.AddImage("content_1.png","",False)
	help1.AddLabel("در تصویر بالا شما میتوانید با درگ کردن یا کشیدن صفحه به چپ و راست،متن را مرور کنید",14,Colors.Black)
	help1.AddLabel("با کلیک طولانی بر روی متن بالای کتاب منویی ظاهر میشود که از سمت راست با انتخاب گزینه منوی باز میشود که قادر به نمایش برجسته ها یادداشت ها و دیگر موارد کتاب میشود و همین طور میتوانید کتاب الکترونیکی کتاب فعلی را دانلود و مشاهده کنید  ",14,Colors.Black)
	help1.AddLabel("گزینه بعدی جستجو در متن کتاب میباشد",14,Colors.Black)
	help1.AddLabel("گزینه بعدی نشان کردن صفحه فعلی از متن جهت دسترسی سریع میباشد",14,Colors.Black)
	help1.AddLabel("گزینه بعدی به شما امکان تغییر در اندازه قلم و نوع قلم و روشنایی به شکل تصویر زیر میباشد",14,Colors.Black)
	help1.AddImage("content_2.png","",False)
	help1.AddLabel("و علامت جهت به سمت چپ نیز برای خروج میباشد",14,Colors.Black)
	help1.AddImage("content_3.png","",False)
	help1.AddLabel("طبق تصویر بالا وقتی شما روی کلمه از متن دو بار ضربه بزنید نمایش داده خواهد شد",14,Colors.Black)
	help1.AddLabel("شما میتوانید محدوده ای متن را انتخاب کنید و در منوی باز شده در بالای کتاب از سمت راست به چپ عملیات زیر را انجام دهید",14,Colors.Black)
	help1.AddLabel("بستن منوی باز شده و ادامه خوانده کتاب",14,Colors.Black)
	help1.AddLabel("هایلایت یا برجسته کردن کلمه انتخاب شده و تغییر آن به رنگ انتخاب شده",14,Colors.Black)
	help1.AddLabel("اشتراک متن انتخاب شده",14,Colors.Black)
	help1.AddLabel("کپی کردن متن انتخاب شده به حافظه کلیپ بورد",14,Colors.Black)
	help1.AddLabel("قرار دادن یادداشت بروی متن انتخاب شده جهت یادداوری",14,Colors.Black)
	help1.AddLabel("و آخرین گزینه علامت جهت برای خروج از منوی باز شده میباشد",14,Colors.Black)
	help1.AddLabel("در صورتی که کتاب دارای پاورقی باشد داخل پرانتز ها عددی قرار خواهد گرفت که با انتخاب آن تصویری مشابه زیر نمایش داده خواهد شد",14,Colors.Black)
	help1.AddImage("content_4.png","",False)
	help1.AddButton("",help1.State,"btnStep2")
	help1.ApplyScrollHeight
	#End Region
	
	slidepanel.Start(0)
	
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)
	
	Activity.Finish
	Library.Right2LeftAnimation
	
End Sub

Sub btnStep2_Click
	Library.Manager.SetBoolean("helpfs1",True)
	Activity.Finish
	StartActivity(Main)
End Sub

Sub Activity_KeyPress (KeyCode As Int) As Boolean 'Return True to consume the event
 
End Sub