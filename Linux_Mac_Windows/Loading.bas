Type=Class
Version=4.01
ModulesStructureVersion=1
B4J=true
@EndOfDesignText@
'Class module
Sub Class_Globals
	Private fx As JFX
	Private frm1 As Form
	Private lbltitle As Label
End Sub

'Initializes the object. You can add parameters to this method if needed.
Public Sub Initialize(Owner As Form)
	frm1.Initialize("",275,107)
	frm1.RootPane.LoadLayout("frmloading")
	frm1.Title = "..."
	frm1.Resizable = False
	Library.CenterFormOnScreen(frm1)
	frm1.SetOwner(Owner)
End Sub

Public Sub Show(Title As String)
	lbltitle.Text = Title
	frm1.Show
End Sub

Public Sub Hide
	frm1.Close	
End Sub