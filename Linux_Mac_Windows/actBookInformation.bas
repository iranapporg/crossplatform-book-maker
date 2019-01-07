Type=StaticCode
Version=4.01
ModulesStructureVersion=1
B4J=true
@EndOfDesignText@
'Static code module
Sub Process_Globals
	Private fx As JFX
	Private frm1 As Form
	Private lbltitlebook As Label
	Private lblauthor As Label
	Private lbldate As Label
	Private lbldownload As Label
	Private btnshare As Button
	Private btndownload As Button
	Private lblcontent As TextArea
	Private imgicon As ImageView
	Private ls1 As Label
	Private ls2 As Label
	Private ls3 As Label
	Private ls5 As Label
	Public mu As Map
	Private ls4 As Label
	Private loading As Loading
End Sub

Sub ShowInformation
	
	If frm1.IsInitialized = False Then
		frm1.Initialize("",370,590)
	End If
	
	frm1.Title = "جزییات کتاب " & mu.Get("sTitle")
	frm1.Resizable = False
	frm1.RootPane.LoadLayout("frmbookinformation")
	frm1.Show
	Library.CenterFormOnScreen(frm1)
	loading.Initialize(frm1)
	
	lbltitlebook.Text = mu.Get("sTitle")
	lblauthor.Text = mu.Get("sAuthor")
	
	lblcontent.Text = mu.Get("sDescription")
	
	lbldate.Text = Library.GetDate(mu.Get("sPublishDate"))
	lbldownload.Text = "تعداد دانلود : " & mu.Get("sDownload")
	
	Dim rating As Int
	rating = mu.Get("sRate")
	
	ls1.Font = fx.LoadFont(File.DirAssets,"icomoon.ttf",14)
	ls2.Font = fx.LoadFont(File.DirAssets,"icomoon.ttf",14)
	ls3.Font = fx.LoadFont(File.DirAssets,"icomoon.ttf",14)
	ls4.Font = fx.LoadFont(File.DirAssets,"icomoon.ttf",14)
	ls5.Font = fx.LoadFont(File.DirAssets,"icomoon.ttf",14)
	
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
	
	Dim im As Map
	im.Initialize
	
	If File.Exists(File.DirApp & "/Data" & "/book/" & mu.Get("sID"),mu.Get("sID") & ".jpg") Then
		imgicon.SetImage(fx.LoadImage(File.DirApp & "/Data" & "/book/" & mu.Get("sID"),mu.Get("sID") & ".jpg"))
	Else		
		Try
			im.Put(imgicon,Library.URL & "/" & mu.Get("sCover"))
			Dim download As ImageDownloader
			download.Initialize
			download.Download(im)
		Catch
			imgicon.SetImage(fx.LoadImage(File.DirAssets,"book.png"))
		End Try
	End If
	
	If File.Exists(File.DirApp & "/Data" & "/book/" & mu.Get("sID"),"bank.db") Then
		fx.Msgbox(frm1,"این کتاب قبلا دانلود شده است.میتوانید کتاب را مرور کنید","توجه")
		btndownload.Enabled = False
		Return
	End If
	
End Sub

Sub btnshare_Action
	
End Sub

Sub btndownload_Action
	
	loading.Show("در حال دانلود...")
	
	Dim hu As HttpJob
	hu.Initialize("downloadbook",Me)
	hu.Download(Library.URL.Replace("index.php/server/","/") & "books/book_" & mu.Get("sID") & ".zip")
	
End Sub

Sub JobDone(Job As HttpJob)
	
	loading.Hide
	
	If Job.Success Then

		If Job.JobName = "downloadbook" Then
	
			Dim ou As OutputStream
			ou = File.OpenOutput(File.DirApp & "/Data","temp.zip",False)
			File.Copy2(Job.GetInputStream,ou)
			ou.Close
			
			File.MakeDir(File.DirApp & "/Data","book/" & Job.JobName)
			Dim zip As ABZipUnzip
			zip.ABUnzip(File.Combine(File.DirApp & "/Data","temp.zip"),File.Combine(File.DirApp & "/Data","book/" & Job.JobName))
			DateTime.DateFormat = "yyyy-mm-dd"
			File.WriteString(File.DirApp & "/Data" & "/book/" & Job.JobName,"date",DateTime.Date(DateTime.Now))
			
			Dim hu As HttpJob
			hu.Initialize("modifybook",Me)
			hu.PostString(Library.URL & "add_download/" & mu.Get("sID"),"")
			
			If File.Exists(File.DirApp & "/Data","visit_book1") = False Then
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