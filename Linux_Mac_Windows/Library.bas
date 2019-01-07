Type=StaticCode
Version=4.01
ModulesStructureVersion=1
B4J=true
@EndOfDesignText@
'Static code module
Sub Process_Globals
	Private fx As JFX
	Public BaseUrl As String : BaseUrl = "http://www.iranapp.org/bahonar/"
	Public URL As String : URL = BaseUrl & "index.php/server/"
	Public manager As SLPreferences
End Sub

Sub CenterFormOnScreen(Frm As Form)
'Centers a form on the primary screen
   Dim ps As Screen = fx.PrimaryScreen
   Frm.WindowTop = (ps.MaxY - ps.MinY) / 2 - Frm.Height / 2
   Frm.WindowLeft = (ps.MaxX - ps.MinX) / 2 - Frm.Width / 2
End Sub 

Sub LastPageVisit(sBookID As String,sTopicID As String,Page As String,Read As Boolean) As Map
	
	Dim m1 As Map
	
	If File.Exists(File.DirApp & "/Data","lastvisitpages") Then
		m1 = File.ReadMap(File.DirApp & "/Data","lastvisitpages")
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
		File.WriteMap(File.DirApp & "/Data","lastvisitpages",m1)
	End If
	
End Sub

Public Sub Julian2Persian(Year As Int,Month As Int,Day As Int,seprator As String) As String

Dim DayNumber As Int
Dim Kabiseh As Byte
Dim S As String
	
	If Year = 0 And Month = 0 And Day = 0 Then
		Year = DateTime.GetYear(DateTime.Now)
		Month = DateTime.GetMonth(DateTime.Now)
		Day   = DateTime.GetDayOfMonth(DateTime.Now)
	End If
	
	DayNumber = (Year - 622 Mod 1000) Mod 100

	If (((Year Mod 1000) Mod 100) = DayNumber) Or _
	(((Year Mod 1000) Mod 100) = DayNumber + 1) Then
		Year = 1300 + ((Year Mod 1000) Mod 100)
		Return Year & seprator & Month & seprator & Day
	End If

	If Year Mod 4 = 0 Then Kabiseh = 1 Else Kabiseh = 0
	
	Select Month
		Case 1: DayNumber = Day
		Case 2: DayNumber = 31 + Day
		Case 3: DayNumber = 31 + 28 + Kabiseh + Day
		Case 4: DayNumber = 31 + 28 + Kabiseh + 31 + Day
		Case 5: DayNumber = 31 + 28 + Kabiseh + 31 + 30 + Day
		Case 6: DayNumber = 31 + 28 + Kabiseh + 31 + 30 + 31 + Day
		Case 7: DayNumber = 31 + 28 + Kabiseh + 31 + 30 + 31 + 30 + Day
		Case 8: DayNumber = 31 + 28 + Kabiseh + 31 + 30 + 31 + 30 + 31 + Day
		Case 9: DayNumber = 31 + 28 + Kabiseh + 31 + 30 + 31 + 30 + 31 + 31 + Day
		Case 10: DayNumber = 31 + 28 + Kabiseh + 31 + 30 + 31 + 30 + 31 + 31 + 30 + Day
		Case 11: DayNumber = 31 + 28 + Kabiseh + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31 + Day
		Case 12: DayNumber = 31 + 28 + Kabiseh + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31 + 30 + Day
	End Select
	
	Year = Year - 622
	Month = Month + 9

	If DayNumber > 79 Then
		DayNumber = DayNumber - (79 - Kabiseh)
		Year = Year + 1
	Else
		DayNumber = DayNumber + (286 + Kabiseh)
	End If
	
	If Month > 12 Then Month = Month - 11
	
	If DayNumber <= 186 Then
		Month = (DayNumber / 31) + 1
			If (DayNumber Mod 31) = 0 Then Month = Month - 1
				Day = (DayNumber Mod 31)
				If Day = 0 Then Day = 31
			Else
				Month = 7 + ((DayNumber - 186) / 30)
				If Month > 12 Then Month = 12
				If ((DayNumber - 186) Mod 30) = 0 Then Month = Month - 1
				Day = (DayNumber - 186) Mod 30
				If Day = 0 Then Day = 30
	End If
	
	Return Year & seprator & Month & seprator & Day
	
End Sub

Public Sub GetDate(date1 As String) As String
 
 	Dim res(),month,perDate() As String
	
	Dim pp() As String
	
	pp = Regex.Split(" ",date1)
	
	Try
		
		If pp(0).IndexOf("-") > -1 Then
			res = Regex.Split("-",pp(0))
		Else
			res = Regex.Split("/",pp(0))
		End If
		
		month = res(1)
		
		perDate = Regex.Split("/",Julian2Persian(res(0),month,res(2),"/"))
		
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
 
Sub GetParent(v As Button) As Button
	Return Null
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

Sub ClearBookReaded(ID As String)
	
	Dim m As Map
	m.Initialize
	
	If File.Exists(File.DirApp & "/Data","visit_book1") Then
		m = File.ReadMap(File.DirApp & "/Data","visit_book1")
	Else
		Return
	End If
	
	If m.ContainsKey(ID) Then
		m.Remove(ID)
		File.WriteMap(File.DirApp & "/Data","visit_book1",m)
	End If
	
End Sub

Sub WriteCounterReadBook(BookName As String)
	
	Dim m As Map
	m.Initialize
	
	If File.Exists(File.DirApp & "/Data","visit_book1") Then
		m = File.ReadMap(File.DirApp & "/Data","visit_book1")
	End If
	
	If m.ContainsKey(BookName) Then
		Dim i As Int
		i = m.Get(BookName)
		i = i + 1
		m.Put(BookName,i)
	Else
		m.Put(BookName,"1")
	End If
	
	File.WriteMap(File.DirApp & "/Data","visit_book1",m)
	
End Sub

Sub GetTopReadBook As Map
	
	Dim m,m3 As Map
	m.Initialize
	m3.Initialize
	
	If File.Exists(File.DirApp & "/Data","visit_book1") Then
		m = File.ReadMap(File.DirApp & "/Data","visit_book1")
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

Sub WriteNote(BookID As String,TopicID As String,ID As String,Val As String)
	
	Dim m As Map
	m.Initialize
	
	If File.Exists(File.DirApp & "/Data","note" & BookID) Then
		m = File.ReadMap(File.DirApp & "/Data","note" & BookID)
	End If
	
	Dim js As JSONGenerator
	js.Initialize(CreateMap("TopicID":TopicID,"ID":ID,"Content":Val))
	m.Put(ID,js.ToString)
	File.WriteMap(File.DirApp & "/Data","note" & BookID,m)
	
End Sub

Sub GetNoteID(BookID As String) As Int
	
	Dim m As Map
	m.Initialize
	
	If File.Exists(File.DirApp & "/Data","note" & BookID) Then
		m = File.ReadMap(File.DirApp & "/Data","note" & BookID)
	Else
		Return 1
	End If
	
	Return m.Size + 1
	
End Sub

Sub GetNoteContent(BookID As String,sID As String) As String
	
	Dim m As Map
	m.Initialize
	
	If File.Exists(File.DirApp & "/Data","note" & BookID) Then
		m = File.ReadMap(File.DirApp & "/Data","note" & BookID)
	Else
		Return ""
	End If
	
	If m.ContainsKey(sID) Then
		Dim m2 As Map
		Dim te As String
		te = m.Get(sID)
		
		Dim js As JSONParser
		js.Initialize(te)
		m2 = js.NextObject
		Return m2.Get("Content")
	Else
		Return ""
	End If
	
End Sub

Sub ListNote(BookID As String) As List
	
	Dim m As Map
	m.Initialize
	
	If File.Exists(File.DirApp & "/Data","note" & BookID) Then
		m = File.ReadMap(File.DirApp & "/Data","note" & BookID)
	Else
		Return Null
	End If
	
	Dim ls As List
	ls.Initialize
	
	For i = 0 To m.Size - 1
		Dim js As JSONParser
		Dim m2 As Map
		js.Initialize(m.GetValueAt(i))
		m2 = js.NextObject
		m2.Put("sType","note")
		ls.Add(m2)
	Next
	
	Return ls
	
End Sub

Sub DeleteNote(BookID As String,ID As String)
	
	Dim m As Map
	m.Initialize
	
	If File.Exists(File.DirApp & "/Data","note" & BookID) Then
		m = File.ReadMap(File.DirApp & "/Data","note" & BookID)
	Else
		Return
	End If
	
	If m.ContainsKey(ID) Then
		m.Remove(ID)
		File.WriteMap(File.DirApp & "/Data","note" & BookID,m)
	End If
	
End Sub

Sub WriteBookmark(BookID As String,TopicID As String,Offset As String)
	
	Dim m As Map
	m.Initialize
	
	If File.Exists(File.DirApp & "/Data","bookmark" & BookID) Then
		m = File.ReadMap(File.DirApp & "/Data","bookmark" & BookID)
	End If
	
	Dim js As JSONGenerator
	js.Initialize(CreateMap("TopicID":TopicID,"Offset":Offset))
	
	m.Put(Offset,js.ToString)
	File.WriteMap(File.DirApp & "/Data","bookmark" & BookID,m)
	
End Sub

Sub Listbookmark(BookID As String) As List
	
	Dim m As Map
	m.Initialize
	
	If File.Exists(File.DirApp & "/Data","bookmark" & BookID) Then
		m = File.ReadMap(File.DirApp & "/Data","bookmark" & BookID)
	Else
		Return Null
	End If
	
	Dim ls As List
	ls.Initialize
	
	For i = 0 To m.Size - 1
		Dim js As JSONParser
		Dim m2 As Map
		js.Initialize(m.GetValueAt(i))
		m2 = js.NextObject
		m2.Put("sType","bookmark")
		ls.Add(m2)
	Next
	
	Return ls
	
End Sub

Sub Deletebookmark(BookID As String,Data As Map)
	
	Dim m As Map
	m.Initialize
	
	If File.Exists(File.DirApp & "/Data","bookmark" & BookID) Then
		m = File.ReadMap(File.DirApp & "/Data","bookmark" & BookID)
	Else
		Return
	End If
	
	Dim ls As List
	ls.Initialize
	
	For i = 0 To m.Size - 1
		
		Dim js As JSONParser
		Dim m2 As Map
		
		js.Initialize(m.GetValueAt(i))
		m2 = js.NextObject
		
		If m2.Get("Offset") = Data.Get("id") And m2.Get("TopicID") = Data.Get("topicid") Then
			m.Remove(m.GetKeyAt(i))
			File.WriteMap(File.DirApp & "/Data","bookmark" & BookID,m)
			Return
		End If
		
	Next
	
End Sub