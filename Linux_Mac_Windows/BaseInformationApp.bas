Type=Class
Version=4.01
ModulesStructureVersion=1
B4J=true
@EndOfDesignText@
'Class module
Sub Class_Globals
	Private sql1 As SQL
End Sub

Public Sub Initialize
	
	sql1.InitializeSqlite(File.DirApp & "/Data","main_db.db",False)
	
End Sub

public Sub GetData(sType As String) As List
	
	Dim c1 As ResultSet
	Dim ls As List
	
	ls.Initialize
	c1 = sql1.ExecQuery("SELECT * FROM tbl_link WHERE sType ='" & sType & "'")
	
	Do While c1.NextRow
		
		Dim m1 As Map
		m1.Initialize
		m1.Put("sTitle",c1.GetString("sTitle"))
		m1.Put("sLink1",c1.GetString("sLink1"))
		m1.Put("sLink2",c1.GetString("sLink2"))
		m1.Put("sType",c1.GetString("sType"))
		ls.Add(m1)
		
	Loop
	
	Return ls
	
End Sub