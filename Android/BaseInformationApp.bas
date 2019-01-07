Type=Class
Version=6
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
'Class module
Sub Class_Globals
	Private sql1 As SQL
End Sub

Public Sub Initialize
	
	sql1.Initialize(File.DirInternal,"main_db.db",False)
	
End Sub

public Sub GetData(sType As String) As List
	
	Dim c1 As Cursor
	Dim ls As List
	
	ls.Initialize
	c1 = sql1.ExecQuery("SELECT * FROM tbl_link WHERE sType ='" & sType & "'")
	
	For i = 0 To c1.RowCount - 1
		
		c1.Position = i
		
		Dim m1 As Map
		m1.Initialize
		m1.Put("sTitle",c1.GetString("sTitle"))
		m1.Put("sLink1",c1.GetString("sLink1"))
		m1.Put("sLink2",c1.GetString("sLink2"))
		m1.Put("sType",c1.GetString("sType"))
		ls.Add(m1)
		
	Next
	
	Return ls
	
End Sub