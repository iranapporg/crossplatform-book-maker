Type=Activity
Version=6
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region Module Attributes
	#FullScreen: False
	#IncludeTitle: False
#End Region

'Activity module
Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.
	
	Dim SearchResult As String
	
	Dim SS_NONE As Int : SS_NONE = 0
	Dim SS_RESULT_SUCCESS As Int : SS_RESULT_SUCCESS = 1

	Dim SearchState As Int : SearchState = SS_NONE
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.
	Dim searchIntent As Intent
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Activity.Color = Colors.Transparent
	
	searchIntent = Activity.GetStartingIntent
	
	If searchIntent.Action = "android.intent.action.SEARCH" Then
		Dim SearchString As String
		
		SearchString = searchIntent.GetExtra("query")
		PerformSearch(SearchString)
	Else
		ToastMessageShow("No search action!", False)
    End If	

	'We exit the Activity after performing the search
	Activity.Finish
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub PerformSearch(Query As String)
	'For this simple example we just return the query string as result
	SearchState = SS_RESULT_SUCCESS
	SearchResult = Query	
End Sub
