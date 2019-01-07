Type=StaticCode
Version=4.01
ModulesStructureVersion=1
B4J=true
@EndOfDesignText@
'Static code module
Sub Process_Globals
	Private fx As JFX
	Public data As Map
	Public BookID As String
	Private frmmdi As Form
	Private wb1 As WebView
	Private we,temp,doc As JavaObject
	Private js As jScriptEngine
	Private lis As List
	Private isChanged As Boolean
	Private Pane1 As Pane
	Private imghighlight As ImageView
	Private imgremovehighlight As ImageView
	Private imgnote As ImageView
	Private imgcopy As ImageView
	Private loading As Loading
	Private scale As Object = 1.0
	Private lblnote As Label
	Private pnnote As Pane
	Private fontsize As Float
	Private fontfamily As String
	Private Slider1 As Slider
	Private imgbookmark As ImageView
	Public offset,RefererNoteID As Int
	Private btnremove As Button
	Private currentNoteChoose As Int
	Public SearchWord As String
End Sub

Sub ShowContent
	
	Dim sc As Screen
	sc = fx.Screens.Get(0)
	
	GetSetting
	
	frmmdi.Initialize("frm1",sc.MaxX / 2,sc.MaxY-50)
	frmmdi.Title = "کتاب"
	frmmdi.Icon = fx.LoadImage(File.DirAssets,"icon.png")
	frmmdi.RootPane.LoadLayout("frmcontent")
	frmmdi.WindowLeft = 0
	Library.CenterFormOnScreen(frmmdi)
	frmmdi.WindowTop = 0
	frmmdi.Show
	
	imghighlight.MouseCursor = fx.Cursors.HAND
	imgcopy.MouseCursor = fx.Cursors.HAND
	imgnote.MouseCursor = fx.Cursors.HAND
	imgremovehighlight.MouseCursor = fx.Cursors.HAND
	imgbookmark.MouseCursor = fx.Cursors.HAND
	
	lis.Initialize
	
	we.InitializeNewInstance("javafx.scene.web.WebEngine",Null)
	doc.InitializeStatic("org.w3c.dom.Document")

	temp = wb1
	we = temp.RunMethod("getEngine",Null)
	
	Dim js1 As String
	js1 = File.ReadString(File.DirAssets,"js.txt")
	js1 = js1.Replace("{content}",data.Get("sText"))
	js1 = js1.Replace("{url}",File.GetUri(File.DirAssets,fontfamily))
	js1 = js1.Replace("{url_js}",File.GetUri(File.DirAssets,"jquery.js"))
	js1 = js1.Replace("40px",fontsize & "px")

	loading.Initialize(frmmdi)
	loading.Show("بارگذاری...")
	wb1.Visible = False
	wb1.LoadHtml(js1)
	
End Sub

Sub wb1_PageFinished (Url As String)
	
	loading.Hide
	wb1.Visible = True
	
	js.enginePut("doc",we.RunMethod("getDocument",Null))
	lis.Clear
	js.enginePut("list",lis) ' pass lis to js
	js.evalString("var linkindoc = doc.links;for (var x = 0;x < linkindoc.length;x++){ list.add(linkindoc.item(x));}")
	
	If offset > -1 Then
		Dim joWV As JavaObject = wb1
		joWV.RunMethodJO("getEngine", Null).RunMethod("executeScript", Array As String("window.scrollTo(1, " & offset & ");"))
	End If
	
	If RefererNoteID > 0 Then
		ExecureJS("jump(" & RefererNoteID & ");")
	End If
	
	If SearchWord.Length > 0 Then
		ExecureJS("highlight_keyword(""" & SearchWord & """);")
	End If
	
End Sub

Sub wb1_MouseClicked (EventData As MouseEvent)
	
	Dim mousex,mousey As Int	
	mousex = EventData.X    ' get the link that was clicked on page
	mousey = EventData.Y
	
	js.evalString("var element = doc.elementFromPoint(" & mousex &","& mousey & ");") ' get the hyperlink
	
	If js.engineGet("element") <> Null Then
		Dim id As String
		id = js.engineGet("element")
		
		If id.StartsWith("[object") = True Then
			pnnote.SetLayoutAnimated(600,pnnote.Left,-pnnote.Height,pnnote.Width,pnnote.Height)
			Return
		End If
		
		Dim note As String
		note = Library.GetNoteContent(BookID,id)
	
		If note.Length = 0 Or note = "null" Then
			pnnote.SetLayoutAnimated(600,pnnote.Left,-pnnote.Height,pnnote.Width,pnnote.Height)
			Return
		End If
		
		currentNoteChoose = id
		
		lblnote.Text = note
		Dim ps As Screen = fx.PrimaryScreen
		pnnote.Left = (frmmdi.Width - ps.MinX) / 2 - pnnote.Width / 2
		pnnote.SetLayoutAnimated(600,pnnote.Left,-11,pnnote.Width,pnnote.Height)
	End If
	
End Sub

Sub wb1_LocationChanged (Location As String)
	Log(Location)
End Sub

Sub frm1_CloseRequest (EventData As Event)
	
	offset = -1
	RefererNoteID = -1
	SearchWord = ""
	
	If isChanged = True Then
		isChanged = False
		CallSubDelayed3(actBookMenu,"SaveChangingContent",data.Get("sID"),GetContentWebView)
	End If
	
End Sub

Sub GetContentWebView As String
	Dim joWV As JavaObject = wb1
	Dim res As String
	res = joWV.RunMethodJO("getEngine", Null).RunMethod("executeScript", Array("document.getElementsByTagName('p')[0].innerHTML"))
	Return res
End Sub

Sub ExecureJS(str As String)
	Try
		Dim joWV As JavaObject = wb1
		joWV.RunMethodJO("getEngine", Null).RunMethod("executeScript", Array As String(str))
	Catch
	End Try
End Sub

Sub AddNote
	
	Dim inp As Dialogs8
	Dim res As String
	
	inp.Initialize
	res = inp.TextInputDialog("یادداشت گذاری","","لطفا یادداشت را وارد کنید","")
	
	If res.Length > 0 Then
		Dim note_id As String
		note_id = Library.GetNoteID(BookID)
		Library.WriteNote(BookID,data.Get("sID"),note_id,res)
		ExecureJS("AddLink(""" & note_id & """);")
	End If
	
End Sub

Sub imghighlight_MouseClicked (EventData As MouseEvent)
	isChanged = True
	ExecureJS("highlight(""yellow"");")
End Sub

Sub imgnote_MouseClicked (EventData As MouseEvent)
	isChanged = True
	AddNote
End Sub

Sub imgcopy_MouseClicked (EventData As MouseEvent)
	js.enginePut("doc",we.RunMethod("getDocument",Null))
	js.evalString("var data = doc.getSelection()") ' get the height of the div
	fx.Clipboard.SetString(js.engineGet("data"))
	fx.Msgbox(frmmdi,"متن انتخاب شده به حافظه کپی شد","توجه")
End Sub

Sub imgremovehighlight_MouseClicked (EventData As MouseEvent)
	isChanged = True
	ExecureJS("highlight(""white"");")
End Sub

Sub Pane1_MouseMoved (EventData As MouseEvent)
	Pane1.SetAlphaAnimated(400,1)
End Sub

Sub wb1_MouseMoved (EventData As MouseEvent)
	If Pane1.Alpha = 1 Then
		Pane1.SetAlphaAnimated(400,0.7)
	End If
End Sub

Sub imgupfont_MouseClicked (EventData As MouseEvent)
	isChanged = True
	Dim wv As JavaObject = wb1   ' webview1 = your webview
	scale = scale + 0.5
	wv.RunMethod("setFontScale",Array(scale))
End Sub

Sub imgdownfont_MouseClicked (EventData As MouseEvent)
	isChanged = True
	Dim wv As JavaObject = wb1   ' webview1 = your webview
	scale = scale - 0.5
	wv.RunMethod("setFontScale",Array(scale))
End Sub

Sub btnok_Action
	pnnote.SetLayoutAnimated(600,pnnote.Left,-pnnote.Height,pnnote.Width,pnnote.Height)
End Sub

Sub GetSetting
	fontsize = Library.manager.GetFloat("fontsize",14)
	fontfamily = Library.manager.GetString("fontfamily","byekan.ttf")
End Sub

Sub Slider1_ValueChange (Value As Double)
	Dim joWV As JavaObject = wb1
	Dim top As Int
	top = Value * (frmmdi.Width +frmmdi.Height)
	joWV.RunMethodJO("getEngine", Null).RunMethod("executeScript", Array As String("window.scrollTo(1, " & top & ");"))
End Sub

Sub GetCurrentScrollPosition As String
	Dim joWV As JavaObject = wb1
	Dim res As String
	res = joWV.RunMethodJO("getEngine", Null).RunMethod("executeScript", Array("document.body.scrollTop"))
	Return res
End Sub

Sub imgbookmark_MouseClicked (EventData As MouseEvent)
	
	isChanged = True
	Library.WriteBookmark(BookID,data.Get("sID"),GetCurrentScrollPosition)
	fx.Msgbox(frmmdi,"این بخش از صفحه نشان شد","توجه")
	
End Sub

Sub btnremove_Action
	
	pnnote.SetLayoutAnimated(600,pnnote.Left,-pnnote.Height,pnnote.Width,pnnote.Height)
	
	If currentNoteChoose = 0 Then Return
	
	ExecureJS("removeLink(" & currentNoteChoose & ");")
	Library.DeleteNote(BookID,currentNoteChoose)
	currentNoteChoose = 0
	isChanged = True
	
End Sub