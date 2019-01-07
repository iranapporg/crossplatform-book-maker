Type=Class
Version=4.01
ModulesStructureVersion=1
B4J=true
@EndOfDesignText@
'Class module
Private Sub Class_Globals
	Private sql1 As SQL
	Private BC As ByteConverter
End Sub

'Initializes the object. You can add parameters to this method if needed.
Public Sub Initialize
	If File.Exists(File.DirApp & "/Data","data.db") = False Then File.Copy(File.DirAssets,"category.db",File.DirApp & "/Data","data.db")
	sql1.InitializeSQLite(File.DirApp & "/Data","data.db",False)
End Sub

Public Sub AddCategory(Data As List)
	
	For i = 0 To Data.Size - 1
		
		Dim temp As Map
		temp = Data.Get(i)
		
		Dim count,parent,id,title As String
		parent = temp.Get("sParent")
		id = temp.Get("sID")
		title = temp.Get("sTitle")
		count = temp.Get("sCount")
		
		sql1.ExecNonQuery2("INSERT INTO tbl_category(sID,sTitle,sParent,sCount) VALUES(?,?,?,?)",Array As String(id,title,parent,count))
		
	Next
	
End Sub

Public Sub AddBook(Data As List)
	
	Dim ls As List
	ls.Initialize
	
	For i = 0 To Data.Size - 1
		
		Dim temp As Map

		temp = Data.Get(i)
		
		ls.Add(temp)
		
	Next
	
	DBUtils.InsertMaps(sql1,"tbl_book",ls)
	
End Sub

public Sub GetBook(Parent As Int,Search As String,FilterLanguage As Boolean) As List
	
	Dim c As ResultSet
	Dim ls As List
	
	ls.Initialize
	 
	If Search.Length = 0 Then
		If FilterLanguage = False Then
			c = sql1.ExecQuery2("SELECT * FROM tbl_book WHERE sCategoryID =?",Array As String(Parent))
		Else
			c = sql1.ExecQuery2("SELECT * FROM tbl_book WHERE sLanguage =?",Array As String(Search))
		End If
		
	Else if Search.Length > 0 Then
		If FilterLanguage = False Then
			c = sql1.ExecQuery("SELECT * FROM tbl_book WHERE sTitle LIKE '%" & Search & "%' OR sDescription LIKE '%" & Search & "%'")
		Else
			c = sql1.ExecQuery2("SELECT * FROM tbl_book WHERE sLanguage =?",Array As String(Search))
		End If
	End If
	
	Do While c.NextRow
		
		Dim temp As Map
		temp.Initialize
		
		temp.Put("sID",c.GetInt("sID"))
		temp.Put("sCategoryID",c.GetString("sCategoryID"))
		temp.Put("sTitle",c.GetString("sTitle"))
		temp.Put("sCover",c.GetInt("sCover"))
		temp.Put("sAuthor",c.GetString("sAuthor"))
		temp.Put("sRate",c.GetString("sRate"))
		temp.Put("sDescription",c.GetString("sDescription"))
		temp.Put("sFileSize",c.GetString("sFileSize"))
		temp.Put("sLanguage",c.GetString("sLanguage"))
		temp.Put("sDownload",c.GetString("sDownload"))
		temp.Put("sPublishDate",c.GetString("sPublishDate"))
		temp.Put("sPDF",c.GetString("sPDF"))
		ls.Add(temp)
		
	Loop
	
	Return ls
	
End Sub

public Sub GetDownloadedBook(FN As String,FV As String) As List
	
	Dim c As ResultSet
	Dim ls As List
	
	ls.Initialize
	Dim s As String
	s = "SELECT * FROM tbl_book WHERE sDownloaded ='1' "
	
	If FN.Length > 0 Then
		s = s & "AND " & FN & " ='" & FV & "'"	
	End If
	
	c = sql1.ExecQuery(s)
	
	Do While c.NextRow
		
		Dim temp As Map
		temp.Initialize
		
		temp.Put("sID",c.GetInt("sID"))
		temp.Put("sCategoryID",c.GetString("sCategoryID"))
		temp.Put("sTitle",c.GetString("sTitle"))
		temp.Put("sCover",c.GetInt("sCover"))
		temp.Put("sAuthor",c.GetString("sAuthor"))
		temp.Put("sRate",c.GetString("sRate"))
		temp.Put("sDescription",c.GetString("sDescription"))
		temp.Put("sFileSize",c.GetString("sFileSize"))
		temp.Put("sLanguage",c.GetString("sLanguage"))
		temp.Put("sDownload",c.GetString("sDownload"))
		temp.Put("sPublishDate",c.GetString("sPublishDate"))
		temp.Put("sPDF",c.GetString("sPDF"))
		ls.Add(temp)
		
	Loop
	
	Return ls
	
End Sub

public Sub GetBookAuthor As List
	
	Dim c As ResultSet
	Dim ls As List
	
	ls.Initialize
	c = sql1.ExecQuery("SELECT sAuthor FROM tbl_book GROUP BY sAuthor")
	
	Do While c.NextRow
		ls.Add(c.GetString("sAuthor"))
	Loop
	
	Return ls
	
End Sub

public Sub GetBookTopic As List
	
	Dim c As ResultSet
	Dim ls As List
	
	ls.Initialize
	c = sql1.ExecQuery("SELECT sTitle FROM tbl_book GROUP BY sTitle")
	
	Do While c.NextRow
		ls.Add(c.GetString("sTitle"))
	Loop
	
	Return ls
	
End Sub

public Sub GetBookInformation(id As String) As Map
	
	Dim c As ResultSet
	Dim ls As List
	
	ls.Initialize
	c = sql1.ExecQuery("SELECT * FROM tbl_book WHERE sID ='" & id & "'")
	
	If c.NextRow = False Then Return Null
	
	Dim temp As Map
	temp.Initialize
	
	temp.Put("sID",c.GetInt("sID"))
	temp.Put("sCategoryID",c.GetString("sCategoryID"))
	temp.Put("sTitle",c.GetString("sTitle"))
	temp.Put("sCover",c.GetInt("sCover"))
	temp.Put("sAuthor",c.GetString("sAuthor"))
	temp.Put("sRate",c.GetString("sRate"))
	temp.Put("sDescription",c.GetString("sDescription"))
	temp.Put("sFileSize",c.GetString("sFileSize"))
	temp.Put("sLanguage",c.GetString("sLanguage"))
	temp.Put("sDownload",c.GetString("sDownload"))
	temp.Put("sPDF",c.GetString("sPDF"))
	temp.Put("sPublishDate",c.GetString("sPublishDate"))
	
	Return temp
	
End Sub

public Sub GetCategory(Cat As Int) As List
	
	Dim c As ResultSet
	Dim ls As List
	
	ls.Initialize
	c = sql1.ExecQuery2("SELECT * FROM tbl_category WHERE sParent =?",Array As String(Cat))
	
	Do While c.NextRow	
		
		Dim temp As Map
		temp.Initialize
		
		temp.Put("sID",c.GetInt("sID"))
		temp.Put("sTitle",c.GetString("sTitle"))
		temp.Put("sParent",c.GetInt("sParent"))
		temp.Put("sCount",c.GetInt("sCount"))
		ls.Add(temp)
		
	Loop
	
	Return ls
	
End Sub

public Sub DownloadedBook(ID As String,State As Int)
	sql1.ExecNonQuery("UPDATE tbl_book SET sDownloaded ='" & State & "' WHERE sID = '" & ID & "'")
End Sub

public Sub GetBookCount As Int
	
	Dim c As ResultSet
	Dim ls As List
	
	ls.Initialize
	'c = sql1.ExecQuery("SELECT sAuthor FROM tbl_book GROUP BY sAuthor")
	c = sql1.ExecQuery("SELECT sAuthor FROM tbl_book")
	
	Dim i As Int = 0
	
	Do While c.NextRow
		i = i + 1
	Loop
	
	Return i
	
End Sub

Public Sub SearchWordBooks(str As String) As List
	
	Dim ls,AllResult As List
	AllResult.Initialize
	
	ls = GetDownloadedBook("","")
	
	For i = 0 To ls.Size - 1
		
		Dim m As Map
		m = ls.Get(i)
		
		Dim dbBook As Database
		Dim res As List
		dbBook.Initialize(m.Get("sID"))
		res = dbBook.SearchInChapters(str)
		
		If res.IsInitialized Then
			If res.Size > 0 Then
				AllResult.Add(m.Get("sID"))
			End If
		End If
		
	Next
	
	Return AllResult
	
End Sub

Public Sub SearchInBooks(str As String,Books As List) As List
	
	Dim result As List
	result.Initialize
	
	For i = 0 To Books.Size - 1
		
		Dim d1 As Database
		Dim res As List
		d1.Initialize(Books.Get(i))
		res = d1.SearchInChapters(str)
		
		result.Add(res)	
		
	Next
	
	Return result
	
End Sub

public Sub Close
	sql1.Close
End Sub