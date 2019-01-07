Type=Class
Version=4.3
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
'Class module
Private Sub Class_Globals
	Private temp As Panel
	Private lblprogress As Label
End Sub

'Initializes the object. You can add parameters to this method if needed.
Public Sub Initialize(panel1 As Panel)
	
	Dim p As Panel
	p.Initialize("")
	panel1.AddView(p,0,0,panel1.Width,panel1.Height)
	p.LoadLayout("frmtemplate_progress")
	p.Visible = False
	temp = p
	
End Sub

Public Sub ShowProgress(ProgressTitle As String)
	lblprogress.Text = ProgressTitle
	temp.Visible = True
End Sub

Public Sub HideProgress
	temp.Visible = False
End Sub