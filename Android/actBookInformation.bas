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
	Dim mu As Map
End Sub

Sub Globals
	Private sv1 As ScrollView
	Private btnmenu As Button
	Private lbltitle As Label
	Private pnlrate As Panel
	Private imgicon As ImageView
	Private lbldate As Label
	Private lbldownload As Label
	Private ls5 As Label
	Private ls4 As Label
	Private ls3 As Label
	Private ls2 As Label
	Private ls1 As Label
	Private lbltitlebook As Label
	Private lblauthor As Label
	Private btnsearch As Button
	Private btnfilter As Button
	Private btnshare As Button
	Dim lb1 As Label
	Private btndownload As Button
End Sub

Sub Activity_Create(FirstTime As Boolean)
	
	Activity.LoadLayout("frmdownload")
	sv1.Panel.LoadLayout("frmbookinformatio")
	lbltitle.Text = "جزییات کتاب " & mu.Get("sTitle")
	lbltitlebook.Text = mu.Get("sTitle")
	lblauthor.Text = mu.Get("sAuthor")
	
	btnfilter.Visible = False
	btnsearch.Visible = False
	
	Dim su As StringUtils
	lb1.Initialize("")
	lb1.Text = mu.Get("sDescription")
	lb1.TextSize = 14
	lb1.TextColor = Colors.Black
	lb1.Typeface = Typeface.LoadFromAssets("tahoma.ttf")
	lb1.Gravity = Gravity.RIGHT
	Library.LabelSpace(lb1,1.5)
	sv1.Panel.AddView(lb1,10,btnshare.Top + btnshare.Height + 10,sv1.Width-20,0)
	lb1.Height = su.MeasureMultilineTextHeight(lb1,mu.Get("sDescription"))
	sv1.Panel.Height = btnshare.Top + btnshare.Height + 10 + lb1.Height + 100
	
	lbldate.Text = Library.GetDate(mu.Get("sPublishDate"))
	lbldownload.Text = "تعداد دانلود : " & mu.Get("sDownload")
	
	Dim rating As Int
	rating = mu.Get("sRate")
	
	#Region Rating
		
	If rating = "0" Then
		ls1.Text = ""
		ls2.Text = ""
		ls3.Text = ""
		ls4.Text = ""
		ls5.Text = ""
		
	Else If rating = "1" Then
		ls1.Text = ""
		ls2.Text = ""
		ls3.Text = ""
		ls4.Text = ""
		ls5.Text = ""
		
	Else If rating = "2" Then
		ls1.Text = ""
		ls2.Text = ""
		ls3.Text = ""
		ls4.Text = ""
		ls5.Text = ""
		
	Else If rating = "3" Then
		ls1.Text = ""
		ls2.Text = ""
		ls3.Text = ""
		ls4.Text = ""
		ls5.Text = ""

	Else If rating = "4" Then
		ls1.Text = ""
		ls2.Text = ""
		ls3.Text = ""
		ls4.Text = ""
		ls5.Text = ""
		
	Else If rating = "5" Or rating > 5 Then
		ls1.Text = ""
		ls2.Text = ""
		ls3.Text = ""
		ls4.Text = ""
		ls5.Text = ""
	End If

	#End Region
	
	Dim pi As Picasso
	pi.Initialize
			
	If File.Exists(File.DirInternal & "/book/" & mu.Get("sID"),mu.Get("sID") & ".jpg") Then
		pi.LoadFile(File.Combine(File.DirInternal & "/book/" & mu.Get("sID"),mu.Get("sID") & ".jpg")).Resize(imgicon.Width,imgicon.Height).CenterCrop.IntoImageView(imgicon)
	Else		
		Try
			pi.LoadUrl(Library.URL & "/" & mu.Get("sCover")).Resize(imgicon.Width,imgicon.Height).CenterCrop.IntoImageView(imgicon)
		Catch
			imgicon.Gravity = Gravity.CENTER
			imgicon.SetBackgroundImage(LoadBitmap(File.DirAssets,"book.png"))
		End Try
	End If
	
	If File.Exists(File.DirInternal & "/book/" & mu.Get("sID"),"bank.db") Then
		ToastMessageShow("این کتاب قبلا دانلود شده است.میتوانید کتاب را مرور کنید",False)
		btndownload.Enabled = False
		Return
	End If
	
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)
	Library.Right2LeftAnimation
End Sub

Sub btnshare_Click
	Library.ShareString("اشتراک کتاب",lb1.Text,"اشتراک گذاری")
End Sub

Sub btndownload_Click
	
	ProgressDialogShow("در حال دریافت کتاب...")
	
	Dim hu As HttpJob
	hu.Initialize("downloadbook",Me)
	hu.Download(Library.URL.Replace("index.php/server/","/") & "books/book_" & mu.Get("sID") & ".zip")
	
End Sub

Sub JobDone(Job As HttpJob)
	
	ProgressDialogHide
	
	If Job.Success Then

		If Job.JobName = "downloadbook" Then
	
			Dim ou As OutputStream
			ou = File.OpenOutput(File.DirInternalCache,"temp.zip",False)
			File.Copy2(Job.GetInputStream,ou)
			ou.Close
			
			File.MakeDir(File.DirInternal,"book/" & Job.JobName)
			Dim zip As ABZipUnzip
			zip.ABUnzip(File.Combine(File.DirInternalCache,"temp.zip"),File.Combine(File.DirInternal,"book/" & Job.JobName))
			'zip.ABUnzip(File.Combine(File.DirInternalCache,"temp.zip"),File.DirRootExternal)
			DateTime.DateFormat = "yyyy-mm-dd"
			File.WriteString(File.DirInternal & "/book/" & Job.JobName,"date",DateTime.Date(DateTime.Now))
			
			send_request.data = mu.Get("sID")
			send_request.event = "ModifyBookDownloads"
			StartService(send_request)
		
			ToastMessageShow("کتاب با موفقیت دانلود شد",False)
			actMenu.DownloadedBook = True
			
			If File.Exists(File.DirInternal,"visit_book1") = False Then
				Library.WriteCounterReadBook(Job.JobName)
			End If
			
			Dim db1 As Categories
			db1.Initialize
			db1.DownloadedBook(mu.Get("sID"),"1")
			
			btndownload.Enabled = False
		
		Else If Job.JobName = "modifybook" Then
			Dim js As JSONParser
			js.Initialize(Job.GetString)
			Dim res As Map = js.NextObject
			If res.IsInitialized Then
				Log(res)
			End If
		End If
	
	Else
		Log(Job.ErrorMessage)
	End If

End Sub