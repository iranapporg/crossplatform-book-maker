Type=Activity
Version=6
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: False
	#IncludeTitle: False
#End Region

Sub Process_Globals
	Public text As String
End Sub

Sub Globals
	Private lbltitle As Label
	Private sv1 As ScrollView
End Sub

Sub Activity_Create(FirstTime As Boolean)
	
	Activity.LoadLayout("frmsummary_content")
	
	sv1.Panel.RemoveAllViews
	
	Dim lb As Label
	lb.Initialize("")
	lb.TextColor = Colors.Black
	lb.Color = Colors.RGB(245,245,245)
	lb.Typeface = Typeface.LoadFromAssets("byekan.ttf")
	lb.TextSize = 14
	lb.Text = text
	lb.Gravity = Gravity.RIGHT
	
	sv1.Panel.AddView(lb,0,0,sv1.Width,0)
	Library.LabelSpace(lb,1.7)
	
	Dim su As StringUtils
	lb.Height = su.MeasureMultilineTextHeight(lb,lb.Text) + 200dip
	sv1.Panel.Height = lb.Height
	
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)
	Library.Right2LeftAnimation
End Sub

Sub btnmenu_Click
	Activity.Finish
End Sub

Sub btnshare_Click
	Library.ShareString("اشتراک",text,"اشتراک گذاری متن")
End Sub