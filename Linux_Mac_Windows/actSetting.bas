Type=StaticCode
Version=4.01
ModulesStructureVersion=1
B4J=true
@EndOfDesignText@
'Static code module
Sub Process_Globals
	Private fx As JFX
	Private frm1 As Form
	Private cmfontname As ComboBox
	Private cmfontsize As ComboBox
	Private fontname As String
	Private fontsize As Float
End Sub

Sub ShowForm
	
	frm1.Initialize("",330,410)
	frm1.Resizable = False
	frm1.RootPane.LoadLayout("frmsetting")
	frm1.SetOwner(Main.MainForm)
	frm1.Show
	
	cmfontname.Items.Add("بی یکان")
	cmfontname.Items.Add("نازنین")
	cmfontname.Items.Add("tahoma")
	cmfontname.SelectedIndex = 0
	
	For i = 14 To 50
		cmfontsize.Items.Add(i)
	Next

	cmfontsize.SelectedIndex = 0
	
End Sub

Sub btnsave_Action
	Library.manager.PutFloat("fontsize",fontsize)
	Library.manager.PutString("fontname",fontname)
	frm1.Close
End Sub

Sub cmfontname_SelectedIndexChanged(Index As Int, Value As Object)
	If Index = 0 Then
		fontname = "byekan.ttf"
	else if Index = 1 Then
		fontname = "bn.ttf"
	else if Index = 2 Then
		fontname = "tahoma.ttf"
	End If
End Sub

Sub cmfontsize_SelectedIndexChanged(Index As Int, Value As Object)
	
	fontsize = Value
	
End Sub