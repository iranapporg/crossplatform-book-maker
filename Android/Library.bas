Type=StaticCode
Version=6
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
'Code module
'Subs in this code module will be accessible from all modules.
Sub Process_Globals
	Public BaseUrl As String : BaseUrl = "https://www.iranapp.org/bahonar/"
	Public URL As String : URL = BaseUrl & "index.php/server/"
	Public SpaceLabel As Float : SpaceLabel = 1.5
	Public Manager As AHPreferenceManager
End Sub

Sub Map2String(Data As Map) As String
	
	Dim js As JSONGenerator
	js.Initialize(Data)
	Return js.ToString
	
End Sub

Sub GetFilename(fullpath As String) As String
   Return fullpath.SubString(fullpath.LastIndexOf("/") + 1)
End Sub

Sub String2Json(str As String) As Map
	Dim js As JSONParser
	js.Initialize(str)
	Return js.NextObject
End Sub

Sub SetAnimation(InAnimation As String, OutAnimation As String)
    Dim r As Reflector
    Dim package As String
    Dim In, out As Int
    package = r.GetStaticField("anywheresoftware.b4a.BA", "packageName")
    In = r.GetStaticField(package & ".R$anim", InAnimation)
    out = r.GetStaticField(package & ".R$anim", OutAnimation)
    r.Target = r.GetActivity
    r.RunMethod4("overridePendingTransition", Array As Object(In, out), Array As String("java.lang.int", "java.lang.int"))
End Sub

Sub Call(Command As String)
    Dim i As Intent
    i.Initialize(i.ACTION_CALL, "tel:" & Command.Replace("#", "%23"))
    StartActivity(i)
End Sub

Sub Right2LeftAnimation
	SetAnimation("file2","file1")
End Sub

Sub Left2RightAnimation
	SetAnimation("file3","file4")
End Sub

Sub LastPageVisit(sBookID As String,sTopicID As String,Page As String,Read As Boolean) As Map
	
	Dim m1 As Map
	
	If File.Exists(File.DirInternal,"lastvisitpages") Then
		m1 = File.ReadMap(File.DirInternal,"lastvisitpages")
	Else
		If Read = True Then Return Null
		m1.Initialize
	End If
	
	If Read Then
		If m1.ContainsKey(sBookID) Then
			Dim s,s1() As String
			s = m1.Get(sBookID)
			s1 = Regex.Split(":",s)
			Return CreateMap("TopicID":s1(0),"Page":s1(1))
		Else
			Return Null
		End If
	Else
		m1.Put(sBookID,sTopicID & ":" & Page)
		File.WriteMap(File.DirInternal,"lastvisitpages",m1)
	End If
	
End Sub

Sub DisableLongClickEdittext(TV As EditText)
	Dim ref As Reflector
	ref.Target = TV
	ref.SetOnLongClickListener(Null)
End Sub

Sub ChangeCheckBoxImage(chk As ToggleButton)
	
	Dim c1 As StateListDrawable
	Dim actColor,hoverColor As BitmapDrawable
	
	actColor.Initialize(LoadBitmapSample(File.DirAssets,"check_on.png",32,32))
	hoverColor.Initialize(LoadBitmapSample(File.DirAssets,"check_off.png",32,32))
	
	c1.Initialize
	c1.AddState(c1.State_Checked,actColor)
	c1.AddState(c1.State_Unchecked,hoverColor)
	c1.AddCatchAllState(hoverColor)
	
	chk.Background = c1
	
End Sub

Sub ShareString(sInfo As String,sBody As String,sSubject As String)
	Dim share As Intent
	share.Initialize(share.ACTION_SEND,"")
	share.SetType("text/plain")
	share.PutExtra("android.intent.extra.TEXT",sBody)
	share.PutExtra("android.intent.extra.SUBJECT", sSubject)
	share.WrapAsIntentChooser(sInfo)
	StartActivity(share)
End Sub

Sub OpenSpinner(S As Spinner)
   Dim r As Reflector
   r.Target = S
   r.RunMethod("performClick")
End Sub

Public Sub getLineCount(TargetView As Label) As Int
 	Dim su As StringUtils	
    Dim OneLineHeight As Long = su.MeasureMultilineTextHeight(TargetView, "T"&Chr(13)&Chr(10)&"T")/2 ' Get the height of two lines, so line spacing is here, divide by 2
	DoEvents
    Dim H As Long = su.MeasureMultilineTextHeight(TargetView, TargetView.Text)
    Return(Ceil(H/OneLineHeight))
End Sub

Sub DeleteFolderRecursive(Folder As String)
   For Each f As String In File.ListFiles(Folder)
     If File.IsDirectory(Folder, f) Then
       DeleteFolderRecursive (File.Combine(Folder, f))
     End If
     File.Delete(Folder, f)
   Next
End Sub

public Sub GetLineHeight(TargetView As Label) As Int
	Dim su As StringUtils
    Dim OneLineHeight As Long = su.MeasureMultilineTextHeight(TargetView, "T"&Chr(13)&Chr(10)&"T")/2 ' Get the height of two lines, so line spacing is here, divide by 2	
	Return OneLineHeight
End Sub

Sub WriteSetting(Setting As String, Value As Int)
    Dim r1 As Reflector
    Dim args(3) As Object
    Dim types(3) As String
    r1.Target = r1.GetContext
    args(0) = r1.RunMethod("getContentResolver")
    types(0) = "android.content.ContentResolver"
    args(1) = Setting
    types(1) = "java.lang.String"
    args(2) = Value
    types(2) = "java.lang.int"
    r1.RunStaticMethod("android.provider.Settings$System", "putInt", args, types) 
End Sub

Sub PaddingView(EditText2 As EditText,Left As Int,Top As Int,Right As Int,Bottom As Int)
	Dim refl As Reflector
	refl.Target= EditText2
	refl.RunMethod4("setPadding", Array As Object(Left, Top, Right, Bottom), _
	Array As String("java.lang.int", "java.lang.int", _
	"java.lang.int","java.lang.int"))
End Sub

Sub FormatSize(Size As Float) As String
	
	Dim unit() As String = Array As String(" بایت", " کیلو بایت", " مگابایت", " گیگابایت", " ترابایت", " PB", " EB", " ZB", " YB")
	
	If (Size == 0) Then
		Return "خالی"
	Else
		
		Dim po,si As Double
		Dim i As Int
		
		i  = Floor(Logarithm(Size, 1024))
		po = Power(1024,i)
		si = Size / po
		
		Try
			If i < 0 Then
				Return Round2(Size,2) & " بایت"
			End If
			Return Round2(si,2) & unit(i)
		Catch
			Return Size
		End Try
		
	End If
	
End Sub

Sub GetParent(v As View) As View
   Dim r As Reflector
   r.Target = v
   Try
   		Return r.RunMethod("getParent")
	Catch
		Return v
	End Try
End Sub

Sub LabelSpace(view1 As View,Space As Float)
	Dim Obj1 As Reflector
	Obj1.Target = view1
	Obj1.RunMethod3("setLineSpacing", 1, "java.lang.float", Space, "java.lang.float")
End Sub

Public Sub GetDate(date1 As String) As String
 
 	Dim res(),month,perDate() As String
	Dim per As PersianDate
	
	Dim pp() As String
	
	pp = Regex.Split(" ",date1)
	
	Try
		
		If pp(0).IndexOf("-") > -1 Then
			res = Regex.Split("-",pp(0))
		Else
			res = Regex.Split("/",pp(0))
		End If
		
		month = res(1)
		
		perDate = Regex.Split("/",per.GetDate(res(0),month,res(2),"/"))
		
		Select Case perDate(1)
			Case 1
				month = "فروردین"
			Case 2
				month = "اردیبهشت"
			Case 3
				month = "خرداد"
			Case 4
				month = "تیر"
			Case 5
				month = "مرداد"
			Case 6
				month = "شهریور"
			Case 7
				month = "مهر"
			Case 8
				month = "آبان"
			Case 9
				month = "آذر"
			Case 10
				month = "دی"
			Case 11
				month = "بهمن"
			Case 12
				month = "اسفند"
		End Select
		
		Return perDate(2) & " " & month & " ماه " & " " & perDate(0) 
	
	Catch
		Return date1
	End Try
	
 End Sub
 
Sub InternetState As Boolean 

	Dim mylan As ServerSocket
	mylan.Initialize(0,"")
	
	If mylan.GetMyIP = "127.0.0.1" Then  
		Return False
	Else
		Return True
	End If
	
End Sub

Sub ConvertHex2Int(hex As String) As String
    Dim R,G,b As Int
    R = Bit.ParseInt(hex.SubString2(1,3), 16)
    G = Bit.ParseInt(hex.SubString2(3,5), 16)
    b = Bit.ParseInt(hex.SubString2(5,7), 16)
    Return Colors.RGB(R, G, b)
End Sub

Sub SetDivider(lv As ListView, Color As Int, Height As Int)
   Dim R As Reflector
   R.Target = lv
   Dim CD As ColorDrawable
   CD.Initialize(Color, 0)
   R.RunMethod4("setDivider", Array As Object(CD), Array As String("android.graphics.drawable.Drawable"))
   R.RunMethod2("setDividerHeight", Height, "java.lang.int")
End Sub

Sub ChangeListviewStyle(lv1 As ListView)
	
	If GetDevicePhysicalSize > 5 Then
		lv1.TwoLinesLayout.ItemHeight = 130   'ertefa har menu
	End If
	
	If GetDevicePhysicalSize < 6 Then
		lv1.TwoLinesLayout.Label.TextSize = 14
		lv1.SingleLineLayout.Label.TextSize = 14
		lv1.TwoLinesLayout.SecondLabel.TextSize = 19
	Else
		lv1.TwoLinesLayout.Label.TextSize = 19
		lv1.SingleLineLayout.Label.TextSize = 19
		lv1.TwoLinesLayout.SecondLabel.TextSize = 22
	End If
	
	lv1.TwoLinesLayout.Label.Height = lv1.TwoLinesLayout.ItemHeight
	lv1.TwoLinesLayout.SecondLabel.Height = lv1.TwoLinesLayout.Label.Height
	
	SetDivider(lv1,Colors.RGB(82,160,218),1)
	
	lv1.TwoLinesLayout.Label.TextColor	= ConvertHex2Int("#272727")
	lv1.SingleLineLayout.Label.TextColor = ConvertHex2Int("#272727")
	lv1.TwoLinesLayout.SecondLabel.TextColor	=  ConvertHex2Int("#272727")
	
	Dim c1 As ColorDrawable
	c1.Initialize(ConvertHex2Int("#eaeaea"),0)
	lv1.SingleLineLayout.Background = c1
	
	lv1.TwoLinesLayout.Label.Typeface	= Typeface.LoadFromAssets("tahoma.ttf")
	lv1.SingleLineLayout.Label.Typeface	= Typeface.LoadFromAssets("tahoma.ttf")
	lv1.TwoLinesLayout.SecondLabel.Typeface=  Typeface.LoadFromAssets("icomoon.ttf")
	
	lv1.TwoLinesLayout.SecondLabel.Top		= lv1.TwoLinesLayout.Label.Top
	lv1.SingleLineLayout.Label.Top			= lv1.TwoLinesLayout.Label.Top
	
	lv1.TwoLinesLayout.SecondLabel.Width	= lv1.Width-17dip
	lv1.TwoLinesLayout.Label.Width			= lv1.Width-50dip
	lv1.SingleLineLayout.Label.Width		= lv1.TwoLinesLayout.SecondLabel.Width
	
	lv1.TwoLinesLayout.Label.Gravity = Bit.Or(Gravity.RIGHT,Gravity.CENTER_VERTICAL)
	lv1.TwoLinesLayout.SecondLabel.Gravity = Bit.Or(Gravity.RIGHT,Gravity.CENTER_VERTICAL)
	lv1.SingleLineLayout.Label.Gravity = Bit.Or(Gravity.RIGHT,Gravity.CENTER_VERTICAL)
	
End Sub

Sub GetDevicePhysicalSize As Float
    Dim lv As LayoutValues
    lv = GetDeviceLayoutValues
    Return Sqrt(Power(lv.Height / lv.Scale / 160, 2) + Power(lv.Width / lv.Scale / 160, 2))
End Sub

Sub getFileType(strExtension As String) As String
	Select Case strExtension.ToLowerCase
	    Case "txt": Return "text/plain"
	    Case "rtf": Return "text/rtf"
	    Case "csv": Return "text/csv"
	    Case "txt": Return "text/plain"
	    Case "png", "jpg", "jpeg", "bmp", "gif", "tif", "tiff": Return "image/*"
	    Case "xml", "fb2": Return "text/*"
	    Case "pdf": Return "application/pdf"
	    Case "mp4", "mpg", "avi", "mov", "mkv": Return "video/*"
	    Case "mp3", "m4a": Return "audio/*"
	    Case "zip": Return "application/zip"
	    Case Else: Return "text/*"
	End Select
End Sub

Sub getFileExtension(sFilename As String) As String
	Dim s2 As Int
	s2 = sFilename.LastIndexOf(".")
	Dim p As String
	p = sFilename.SubString2(s2+1,sFilename.Length)
	Return p
End Sub

Sub WriteCounterReadBook(BookName As String)
	
	Dim m As Map
	m.Initialize
	
	If File.Exists(File.DirInternal,"visit_book1") Then
		m = File.ReadMap(File.DirInternal,"visit_book1")
	End If
	
	If m.ContainsKey(BookName) Then
		Dim i As Int
		i = m.Get(BookName)
		i = i + 1
		m.Put(BookName,i)
	Else
		m.Put(BookName,"1")
	End If
	
	File.WriteMap(File.DirInternal,"visit_book1",m)
	
End Sub

Sub GetTopReadBook As Map
	
	Dim m,m3 As Map
	m.Initialize
	m3.Initialize
	
	If File.Exists(File.DirInternal,"visit_book1") Then
		m = File.ReadMap(File.DirInternal,"visit_book1")
	Else
		Return Null
	End If
	
	SortMapKeys(m,True)
	
	Return m
	
End Sub

Sub SortMapKeys (m As Map, SortAsc As Boolean)
   Private KeysList As List:KeysList.Initialize
   Private m2 As Map:m2.Initialize
  
   For i = 0 To m.Size - 1
      Private key As String = m.GetKeyAt(i)
      KeysList.Add(key)
   Next
  
   KeysList.Sort(SortAsc)
  
   For x=0 To KeysList.Size - 1
       Private key As String = KeysList.Get(x)
       Private val As String = m.Get(key)
       m2.Put(key, val)
   Next
  m.Clear
  For Each m2Key As String In m2.Keys
      m.Put(m2Key, m2.Get(m2Key))
  Next
End Sub

Sub GetNewBook As List
	
	If File.Exists(File.DirInternal & "/book","") = False Then Return Null
	
	Dim ls,date As List
	date.Initialize
	ls = File.ListFiles(File.DirInternal & "/book")
	
	For i = 0 To ls.Size - 1
		
		If File.Exists(File.DirInternal & "/book/" & ls.Get(i),"date") Then
			Dim d As String
			d = File.ReadString(File.DirInternal & "/book/" & ls.Get(i),"date")
			DateTime.DateFormat = "yyyy-mm-dd"
			Dim pe As Period = DateUtils.PeriodBetween(d,DateTime.date(DateTime.Now))
			If pe.Days < 3 Then
				date.Add(ls.Get(i))
			End If
		End If
		
	Next
	
	Return date
	
End Sub

Sub setSelection(edt As EditText, StartIndex As Int, EndIndex As Int)
    Dim jo = edt As JavaObject
    jo.RunMethod("setSelection", Array As Object(StartIndex, EndIndex))
End Sub

Sub getSelectionStart(edt As EditText) As Int
    Dim jo = edt As JavaObject
    Return jo.RunMethod("getSelectionStart", Null)
End Sub

Sub getSelectionEnd(edt As EditText) As Int
    Dim jo = edt As JavaObject
    Return jo.RunMethod("getSelectionEnd", Null)
End Sub

Sub RegexReplace(Pattern As String, Text As String, Replacement As String) As String
    Dim m As Matcher
    m = Regex.Matcher(Pattern, Text)
    Dim r As Reflector
    r.Target = m
    Return r.RunMethod2("replaceAll", Replacement, "java.lang.String")
End Sub

Sub WriteNote(BookID As String,sTopicID As String,sPageNumber As Int,sNote As String,sSelectionStart As Int,sSelectionEnd As Int,text As String)
	
	Dim m1 As Map
	m1.Initialize
	
	If File.Exists(File.DirInternal,"noteo_" & BookID & "_" & sTopicID) Then
		m1 = File.ReadMap(File.DirInternal,"noteo_" & BookID & "_" & sTopicID)
	End If
	
	Dim count As Int
	count = m1.Size
	If count = 0 Then count = 1 Else count = count + 1
	
	Dim j As JSONGenerator
	j.Initialize(CreateMap("Text":text,"Note":sNote,"SelectionStart":sSelectionStart,"SelectionEnd":sSelectionEnd,"PageNumber":sPageNumber,"ID" :count,"TopicID":sTopicID))
	m1.Put(count,j.ToString)
	
	File.WriteMap(File.DirInternal,"noteo_" & BookID & "_" & sTopicID,m1)
	
End Sub

Sub Bookmark(BookID As String,sTopicID As String,sPageNumber As Int,CheckExist As Boolean) As Boolean
	
	Dim m1 As Map
	m1.Initialize
	
	If File.Exists(File.DirInternal,"bookmark_" & BookID & "_" & sTopicID) Then
		m1 = File.ReadMap(File.DirInternal,"bookmark_" & BookID & "_" & sTopicID)
	End If
	
	If m1.ContainsKey(sTopicID & ":" & sPageNumber) Then
		If CheckExist Then Return True
		m1.Remove(sTopicID & ":" & sPageNumber)
		File.WriteMap(File.DirInternal,"bookmark_" & BookID & "_" & sTopicID,m1)
		Return False
	Else
		If CheckExist Then Return False
		m1.Put(sTopicID & ":" & sPageNumber,"")
		File.WriteMap(File.DirInternal,"bookmark_" & BookID & "_" & sTopicID,m1)
		Return True
	End If
	
End Sub

Sub ReadNotes(BookID As String,sTopicID As String,sPageNumber As Int) As List
	
	Dim m1 As Map
	m1.Initialize

	If File.Exists(File.DirInternal,"noteo_" & BookID & "_" & sTopicID) Then
		m1 = File.ReadMap(File.DirInternal,"noteo_" & BookID & "_" & sTopicID)
	Else
		Return Null
	End If
	
	Dim res As List
	res.Initialize
	
	For i = 0 To m1.Size - 1
		
		Dim m2 As Map
		Dim js As JSONParser
		js.Initialize(m1.GetValueAt(i))
		m2 = js.NextObject
		
		If sPageNumber = m2.Get("PageNumber") Then
			Dim result As Map
			result.Initialize
			result.Put("SelectionStart",m2.Get("SelectionStart"))
			result.Put("SelectionEnd",m2.Get("SelectionEnd"))
			result.Put("PageNumber",m2.Get("PageNumber"))
			result.Put("Note",m2.Get("Note"))
			result.Put("TopicID",sTopicID)
			result.Put("ID",m2.Get("ID"))
			res.Add(result)
		End If
 
	Next
	
	Return res
	
End Sub

Sub NotesContent(BookID As String,sTopicID As String,sPageNumber As Int,CurrentStart As Int,CurrentEnd As Int) As String
	
	Dim m1 As Map
	m1.Initialize
 
	
	If File.Exists(File.DirInternal,"noteo_" & BookID & "_" & sTopicID) Then
		m1 = File.ReadMap(File.DirInternal,"noteo_" & BookID & "_" & sTopicID)
	Else
		Return ""
	End If
	
	Dim res As List
	res.Initialize
	
	For i = 0 To m1.Size - 1
		
		Dim m2 As Map
		Dim js As JSONParser
		js.Initialize(m1.GetValueAt(i))
		m2 = js.NextObject
		
		If sPageNumber = m2.Get("PageNumber") And CurrentStart = m2.Get("SelectionStart") And m2.Get("SelectionEnd") = CurrentEnd Then
 			Return m2.Get("Note")
		Else If sPageNumber = m2.Get("PageNumber") And CurrentStart >= m2.Get("SelectionStart") And CurrentEnd-1 <= m2.Get("SelectionEnd") Then
			Return m2.Get("Note")
		End If
 
	Next
	
	Return ""
	
End Sub

Sub DeleteNote(BookID As String,sTopicID As String,sPageNumber As Int,sNoteID As String,sStart As Int,sEnd As Int) As Boolean
	
	Dim m1 As Map
	m1.Initialize
 
	
	If File.Exists(File.DirInternal,"noteo_" & BookID & "_" & sTopicID) Then
		m1 = File.ReadMap(File.DirInternal,"noteo_" & BookID & "_" & sTopicID)
	Else
		Return False
	End If
	
	Dim res As List
	res.Initialize
	
	For i = 0 To m1.Size - 1
		
		Dim m2 As Map
		Dim js As JSONParser
		js.Initialize(m1.GetValueAt(i))
		m2 = js.NextObject
		
		If sPageNumber = m2.Get("PageNumber") And m2.Get("SelectionStart") = sStart And m2.Get("SelectionEnd") = sEnd Then
			m1.Remove(m1.GetKeyAt(i))
			File.WriteMap(File.DirInternal,"noteo_" & BookID & "_" & sTopicID,m1)
			Return True
		
		Else If sPageNumber = m2.Get("PageNumber") And sStart >= m2.Get("SelectionStart") And sEnd-1 <= m2.Get("SelectionEnd") Then
			m1.Remove(m1.GetKeyAt(i))
			File.WriteMap(File.DirInternal,"noteo_" & BookID & "_" & sTopicID,m1)
			Return True
		End If
 
	Next
	
	Return False
	
End Sub

Sub GetNoteCount(BookID As String,sTopicID As String,sPageNumber As Int) As Int
	
	Dim m1 As Map
	m1.Initialize
 
	
	If File.Exists(File.DirInternal,"noteo_" & BookID & "_" & sTopicID) Then
		m1 = File.ReadMap(File.DirInternal,"noteo_" & BookID & "_" & sTopicID)
	Else
		Return 1
	End If
	
	Dim count As Int
	
	For i = 0 To m1.Size - 1
		
		Dim m2 As Map
		Dim js As JSONParser
		js.Initialize(m1.GetValueAt(i))
		m2 = js.NextObject
	
		If sPageNumber = m2.Get("PageNumber") Then
			count = count + 1
		End If
 
	Next
	
	Return count
	
End Sub

Sub StrReverse(Text As String) As String 
   Dim tempstr As StringBuilder,temp As Int
   tempstr.Initialize 
   For temp = Text.Length-1 To 0 Step -1
      tempstr.Append( Text.CharAt(temp) )
   Next
   Return tempstr.ToString 
End Sub

Sub WriteHighLight(BookID As String,sTopicID As String,sPageNumber As Int,sSelectionStart As Int,sSelectionEnd As Int,Color As Int,text As String)
	
	Dim m1 As Map
	m1.Initialize
	
	If File.Exists(File.DirInternal,"highlight_" & BookID & "_" & sTopicID) Then
		m1 = File.ReadMap(File.DirInternal,"highlight_" & BookID & "_" & sTopicID)
	End If
	
	Dim count As Int
	count = m1.Size
	If count = 0 Then count = 1 Else count = count + 1
	
	Dim j As JSONGenerator
	j.Initialize(CreateMap("Text":text,"SelectionStart":sSelectionStart,"TopicID":sTopicID,"SelectionEnd":sSelectionEnd,"PageNumber":sPageNumber,"ID" :count,"Color":Color))
	m1.Put(count,j.ToString)
	
	File.WriteMap(File.DirInternal,"highlight_" & BookID & "_" & sTopicID,m1)
	
End Sub

Sub ReadHightlight(BookID As String,sTopicID As String,sPageNumber As Int) As List
	
	Dim m1 As Map
	m1.Initialize
 
	
	If File.Exists(File.DirInternal,"highlight_" & BookID & "_" & sTopicID) Then
		m1 = File.ReadMap(File.DirInternal,"highlight_" & BookID & "_" & sTopicID)
	Else
		Return Null
	End If
	
	Dim res As List
	res.Initialize
	
	For i = 0 To m1.Size - 1
		
		Dim m2 As Map
		Dim js As JSONParser
		js.Initialize(m1.GetValueAt(i))
		m2 = js.NextObject
		
		If sPageNumber = m2.Get("PageNumber") Then
			Dim result As Map
			result.Initialize
			result.Put("SelectionStart",m2.Get("SelectionStart"))
			result.Put("SelectionEnd",m2.Get("SelectionEnd"))
			result.Put("Color",m2.Get("Color"))
			result.Put("TopicID",sTopicID)
			res.Add(result)
		End If
 
	Next
	
	Return res
	
End Sub

Sub ExistHightlight(BookID As String,sTopicID As String,sPageNumber As Int,sStart As Int,sEnd As Int,Delete As Boolean) As Boolean
	
	Dim m1 As Map
	m1.Initialize
 
	
	If File.Exists(File.DirInternal,"highlight_" & BookID & "_" & sTopicID) Then
		m1 = File.ReadMap(File.DirInternal,"highlight_" & BookID & "_" & sTopicID)
	Else
		Return False
	End If
	
	Dim res As List
	res.Initialize
	
	For i = 0 To m1.Size - 1
		
		Dim m2 As Map
		Dim js As JSONParser
		js.Initialize(m1.GetValueAt(i))
		m2 = js.NextObject
		
		If sPageNumber = m2.Get("PageNumber") And sStart = m2.Get("SelectionStart") And sEnd = m2.Get("SelectionEnd") Then
			If Delete = True Then
				m1.Remove(m1.GetKeyAt(i))
				File.WriteMap(File.DirInternal,"highlight_" & BookID & "_" & sTopicID,m1)
				Return True
			Else
				Return True
			End If
		End If
 
	Next
	
	Return False
	
End Sub

Sub HighlightSelection(BookID As String,sTopicID As String,sPageNumber As Int,CurrentStart As Int,CurrentEnd As Int) As Int()
	
	Dim m1 As Map
	m1.Initialize
 
	
	If File.Exists(File.DirInternal,"highlight_" & BookID & "_" & sTopicID) Then
		m1 = File.ReadMap(File.DirInternal,"highlight_" & BookID & "_" & sTopicID)
	Else
		Return Null
	End If
	
	Dim res As List
	res.Initialize
	
	For i = 0 To m1.Size - 1
		
		Dim m2 As Map
		Dim js As JSONParser
		js.Initialize(m1.GetValueAt(i))
		m2 = js.NextObject
		
		If sPageNumber = m2.Get("PageNumber") And CurrentStart = m2.Get("SelectionStart") And m2.Get("SelectionEnd") = CurrentEnd Then
 			Return Array As Int(m2.Get("SelectionStart"),m2.Get("SelectionEnd"))
		Else If sPageNumber = m2.Get("PageNumber") And CurrentStart >= m2.Get("SelectionStart") And CurrentEnd-1 <= m2.Get("SelectionEnd") Then
			Return Array As Int(m2.Get("SelectionStart"),m2.Get("SelectionEnd"))
		End If
 
	Next
	
	Return Null
	
End Sub

Sub SelectionEdittext(txt As EditText,selstart As Int,sellength As Int)
	Dim obj1 As Reflector
	obj1.target = txt
	obj1.runmethod3("setSelection", selstart, "java.lang.int", selstart + sellength, "java.lang.int")  'Note: setSelection is case sensitive!
End Sub

Sub ReverseList(Ls As List) As List
	
	Dim temp As List
	temp.Initialize
	
	For i = Ls.Size - 1 To 0 Step -1
		temp.Add(Ls.Get(i))	
	Next
	
	Return temp
	
End Sub

Sub SetSeekbarDrawable(p As SeekBar, drawable As Object, backgroundDrawable As Object)
   Dim r As Reflector
   Dim clipDrawable As Object
   clipDrawable = r.CreateObject2("android.graphics.drawable.ClipDrawable", _
      Array As Object(drawable, Gravity.LEFT, 1), _
      Array As String("android.graphics.drawable.Drawable", "java.lang.int", "java.lang.int"))
   r.Target = p
   r.Target = r.RunMethod("getProgressDrawable") 'Gets the layerDrawable
   r.RunMethod4("setDrawableByLayerId", _
      Array As Object(16908288, backgroundDrawable), _
      Array As String("java.lang.int", "android.graphics.drawable.Drawable"))
   r.RunMethod4("setDrawableByLayerId", _
      Array As Object(r.GetStaticField("android.R$id", "progress"), clipDrawable), _
      Array As String("java.lang.int", "android.graphics.drawable.Drawable"))
End Sub

Sub AddPDFBook(BookID As String,Filename As String)
	
	Dim m1 As Map
	m1.Initialize
	
	If File.Exists(File.DirInternal,"listpdf") Then
		m1 = File.ReadMap(File.DirInternal,"listpdf")
	End If
	
	m1.Put(BookID,Filename)
	File.WriteMap(File.DirInternal,"listpdf",m1)
	
End Sub

Sub CheckExistPdfViewer As Boolean
	
	Dim p1 As PackageManager
	Dim in1 As Intent
	
	in1 = p1.GetApplicationIntent("com.library.pdfviewer")
	
	If in1.IsInitialized = False Then
		ToastMessageShow("شما برنامه ی اجراکننده ی کتاب های الکترونیکی این اپ را نصب نکردید.لطفا نصب کنید",False)
		File.Copy(File.DirAssets,"pdfviewer.apk",File.DirRootExternal,"pdfviewer.apk")
		InstallApp(File.DirRootExternal,"pdfviewer.apk")
		Return False
	End If
	
	Return True
	
End Sub

Sub InstallApp(sDir As String,sFilename As String)
	Dim Uri As String
	Dim i2 As Intent
	Uri = "file://" & File.Combine(sDir, sFilename)
	i2.Initialize(i2.ACTION_VIEW, Uri)
	i2.SetType("application/vnd.android.package-archive")
	StartActivity(i2)
End Sub

Sub WriteData(ls As List,id As String)
	Dim rn As RandomAccessFile
	rn.Initialize(File.DirInternal,"list_" & id,False)
	rn.WriteObject(ls,False,0)
	rn.Close
End Sub

Sub ReadData(id As String) As List
	Dim rn As RandomAccessFile
	rn.Initialize(File.DirInternal,"list_" & id,True)
	Dim ls1 As List
	ls1 = rn.readObject(0)
	rn.Close
	Return ls1
End Sub