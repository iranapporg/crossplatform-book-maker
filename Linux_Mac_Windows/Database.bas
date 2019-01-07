Type=Class
Version=4.01
ModulesStructureVersion=1
B4J=true
@EndOfDesignText@
'Class module
Private Sub Class_Globals
	Private sql1 As SQL
	Private BC As ByteConverter
	Public BookDetails As Map
End Sub

'Initializes the object. You can add parameters to this method if needed.
Public Sub Initialize(BookName As String)
	Try
		sql1.InitializeSQLite(File.DirApp & "/Data" & "/book/" & BookName,"bank.db",False)
		
		Dim cat As Categories
		cat.Initialize
		BookDetails = cat.GetBookInformation(BookName)
		cat.Close
		
	Catch
		Main.fx.Msgbox(Main.MainForm,"دیتای مربوط به این کتاب قابل شناسایی نیست","توجه")
	End Try
End Sub

Public Sub GetCategory(sID As String) As List
	
	Dim c1 As ResultSet
	c1 = sql1.ExecQuery("SELECT * FROM tbl_category WHERE sParent = '" & sID & "'")
	
	Dim ls As List
	ls.Initialize
	
	Do While c1.NextRow
		
		Dim m1 As Map
		m1.Initialize
		m1.Put("sID",c1.GetString("sID"))
		m1.Put("sTitle",c1.GetString("sTitle"))
		m1.Put("sType","cat")
		ls.Add(m1)
		
	Loop
	
	Return ls
	
End Sub

public Sub UpdateContent(sID As String,Content As String)
	sql1.ExecNonQuery("UPDATE tbl_topic SET sText ='" & Content & "' WHERE sID =" & sID)
End Sub

Public Sub GetChapters(sCategoryID As String,isSummary As Boolean) As List
	
	Dim c1 As ResultSet
	
	If isSummary = False Then
		c1 = sql1.ExecQuery("SELECT sID,sTitle,sParent,sType FROM tbl_topic WHERE sParent = '" & sCategoryID & "'")
	Else
		c1 = sql1.ExecQuery("SELECT sID,sTitle,sParent,sType FROM tbl_topic WHERE sType = 'summary'")
	End If
	
	Dim ls As List
	ls.Initialize
	
	
	Do While c1.NextRow
		
		Dim m1 As Map
		m1.Initialize
		m1.Put("sID",c1.GetString("sID"))
		m1.Put("sTitle",c1.GetString("sTitle"))
		m1.Put("sParent",c1.GetString("sParent"))
		m1.Put("sType","book")
				
		If c1.GetString("sType") = "summary" Then
			m1.Put("sType","summary")	
		End If
		
		ls.Add(m1)
		
	Loop
	
	Return ls
	
End Sub

Public Sub SearchInChapters(str As String) As List
	
	Dim c1 As ResultSet
	c1 = sql1.ExecQuery("SELECT * FROM tbl_topic WHERE sText LIKE '%" & str & "%'")
	
	Dim ls As List
	ls.Initialize
	
	Dim cat As Categories
	cat.Initialize
	
	Do While c1.NextRow
		
		Dim m1 As Map
		m1.Initialize
		m1.Put("sID",c1.GetString("sID"))
		m1.Put("sTitle",c1.GetString("sTitle"))
		m1.Put("sParent",c1.GetString("sParent"))
		m1.Put("BookName",BookDetails.Get("sTitle"))
		m1.Put("BookID",BookDetails.Get("sID"))
		m1.Put("sType","book")
		
		Dim offset,temp,start,ends As Int
		offset = c1.GetString("sText").IndexOf(str)
		
		If offset > 1 Then
			If offset > 20 Then	 start = 5 Else start = 0
		End If
		
		temp = c1.GetString("sText").Length
		
		If temp - offset > 10 Then
			ends = offset + str.Length + 10
		Else if temp - offset > 1 Then
			ends = offset + str.Length
		End If
		
		m1.Put("Str",c1.GetString("sText").SubString2(start,ends))
		
		ls.Add(m1)
		
	Loop
	
	Return ls
	
End Sub

Public Sub ChapterContent(sID As String) As Map
	
	Dim c1 As ResultSet
	c1 = sql1.ExecQuery("SELECT sID,sText,sTitle,sType,sRate,sParent FROM tbl_topic WHERE sID = '" & sID & "'")
	
	Dim m1 As Map
	m1.Initialize
		
	If c1.NextRow Then
		
		
		m1.Put("sID",c1.GetString("sID"))
		m1.Put("sTitle",c1.GetString("sTitle"))
		
		If c1.GetString("sType") <> "pdf" Then
			m1.Put("sText",BC.StringFromBytes(c1.GetBlob("sText"),"UTF8"))
		Else
			m1.Put("sText",c1.GetString("sText"))
		End If
		
		m1.Put("sRate",c1.GetString("sRate"))
		m1.Put("sTypeTopic",c1.GetString("sType"))
		m1.Put("sParent",c1.GetString("sParent"))
		
	End If
	
	Return m1
	
End Sub

Sub GetText(sID As String)
	
	
End Sub

public Sub GetTopicID(sID As String) As String
	
	Dim c1 As ResultSet
	c1 = sql1.ExecQuery("SELECT * FROM tbl_topic WHERE sID = " & sID)

	Dim m1 As Map
	m1.Initialize
		
	If c1.NextRow Then
		Return c1.GetString("sTitle")
	End If
	
	Return ""
			
End Sub

Public Sub GetFooters(sTopicID As String,sID As String) As Map
	
	Dim c1 As ResultSet
	c1 = sql1.ExecQuery("SELECT * FROM tbl_footer WHERE sTopicID = '" & sTopicID & "' AND sFooterID = '" & sID & "'")

	Dim m1 As Map
	m1.Initialize
		
	If c1.NextRow Then
		
		m1.Put("sID",c1.GetString("sID"))
		m1.Put("sFooterID",c1.GetString("sFooterID"))
		m1.Put("sPositionFooter",c1.GetString("sPositionFooter"))
		m1.Put("sText",c1.GetString("sText"))
		m1.Put("sTopicID",c1.GetString("sTopicID"))
		m1.Put("success",True)
		
	End If
	
	Return m1
	
End Sub

Public Sub GetMultimedia(sTopicID As String,Key As String) As Map
	
	Dim c1 As ResultSet
	c1 = sql1.ExecQuery("SELECT * FROM tbl_multimedia WHERE sTopicID = '" & sTopicID & "' AND sKey ='" & Key & "'")
	
	Dim ls As List
	ls.Initialize
	
	If c1.NextRow Then Return Null
	
	Return CreateMap("Value":c1.GetString("sFilename"),"Type":c1.GetString("sType"))
	
End Sub

public Sub CloseDatabase
	sql1.Close
End Sub