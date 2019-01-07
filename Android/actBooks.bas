Type=Activity
Version=4.3
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: False
	#IncludeTitle: False
#End Region

Sub Process_Globals

End Sub

Sub Globals
	Private sv1 As ScrollView
	Private pnlcategories As Panel
	Private pnlbooks As Panel
	Private lblcount_book As Label
	Private lblcategory_name As Label
End Sub

Sub Activity_Create(FirstTime As Boolean)
	
	Activity.LoadLayout("frmdownload")
	
	ProgressDialogShow("لیست کردن...")
	Dim res As Map
	res = Library.ExecuteRequest(CreateMap("parent_id":"0"),"ListCategory")
	
	If res.IsInitialized Then
		If res.Get("success") = True Then
			Dim ls As List
			ls = res.Get("result")
			AddCategories(ls)
		End If
	End If
	
End Sub

Sub AddCategories(List1 As List)
	
	Dim top As Int = 0
	
	For i = 0 To List1.Size - 1
		
		Dim p1 As Panel
		p1.Initialize("")
		sv1.Panel.AddView(p1,0,top,sv1.Width,0)
		p1.LoadLayout("frmteplate_category")
		p1.Height = pnlcategories.Height
		
		top = top + p1.Height + 3
		
		Dim temp As Map
		temp = List1.Get(i)
		Log(temp)
		
	Next
	
	sv1.Panel.Height = top
	
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub


