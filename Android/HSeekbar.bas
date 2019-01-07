Type=Class
Version=5.5
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
'Events declaration
#Event: ValueChanged(Value As Int, UserChanged As Boolean)

'Class module
Private Sub Class_Globals
Private dr1,dr2,dr3 As GradientDrawable
Private slider, caret As ImageView
Private Vpos, H, W, MaxV, D As Int
Private Hmodule As Object
Private Hbase, cover As Panel
Private BarName As String
Private mybase As Panel
End Sub

'Initializes the object. No need to use if the object is defined by the designer. 
'Module is the launching module.
Public Sub Initialize(Module As Object, Eventname As String)
Hmodule = Module
BarName = Eventname
End Sub

'If you create the object by code, not by the designer, use this sub after initialization.
Public Sub CodeCreateView( width As Int ,height As Int, MaxValue As Int)
H = height
W = width
MaxV = MaxValue
Hbase.Initialize("")
ContinueCreation
End Sub

Private Sub DesignerCreateView(base As Panel, Lbl As Label, Props As Map) 'ignore
mybase = base
mybase.Left = Lbl.Left
mybase.Top = Lbl.top
mybase.Width = Lbl.Width
mybase.Height = Lbl.Height
Hbase.Initialize("")
base.AddView(Hbase,0, 0, Lbl.Width, Lbl.Height)

H = Lbl.Height 
W = Lbl.Width
MaxV = Lbl.text
ContinueCreation
End Sub

Private Sub ContinueCreation
D = 20dip ' default caret width
slider.Initialize("")
Hbase.AddView(slider,0,0,W,H)
caret.Initialize("")
Hbase.AddView(caret,W/2-D/2,0,D,H)
cover.Initialize("cover")
Hbase.AddView(cover,0,0,W,H)
									' These are the default colors
setColors(Colors.Black,Colors.white, Colors.Yellow, Colors.white)
End Sub

'Sets the colors of the bar
Sub setColors(BackGround As Int,basecolor As Int,slidercolor As Int, caretcolor As Int)
Dim clr(3) As Int
clr = Array  As Int(BackGround,basecolor,BackGround)
dr1.Initialize("TOP_BOTTOM",clr)
Hbase.BackGround =   dr1  ' .Initialize(basecolor,BackGround,False)
clr = Array  As Int(BackGround,slidercolor,BackGround) 
dr2.Initialize("TOP_BOTTOM",clr)
slider.BackGround = dr2
clr = Array  As Int(BackGround,caretcolor,BackGround) 
dr3.Initialize("LEFT_RIGHT",clr)
caret.BackGround =  dr3
End Sub

Sub AsView As View 'ignore
Return Hbase
End Sub

' Sets the Max value of the bar 
Sub setMaxValue(Value As Int)
MaxV = Value
End Sub

'Sets the width of the caret.
'The default is 20dip.
Sub setCaretWidth(value As Int)
D = value
caret.Width = D
caret.left = Max(Min(W-D,slider.width-D/2),0)
End Sub

Private Sub setPosition(Value As Int, userchanged As Boolean)
slider.width = Max(Min(W * Value/MaxV, W ),0) 
caret.left = Max(Min(W-D,slider.width-D/2),0) 
Hbase.Invalidate
If SubExists(Hmodule, BarName & "_ValueChanged") Then
  CallSub3(Hmodule,BarName & "_ValueChanged", Value , userchanged )
End If
End Sub

' Sets the value of the bar 
' ValueChanged event will be raised with Userchanged = false
' This function must be performed by code at least once prior to use of the bar.
Sub setValue(Value As Int)
Vpos = Value
W = Hbase.Width
setPosition(Vpos,False)
End Sub

' Returns the current value of the bar
Sub getValue As Int
Return Vpos
End Sub


Private Sub cover_Touch(Action As Int, X As Float, Y As Float) As Boolean 
Dim k As Int
k = X * MaxV / Hbase.Width
k = Max(Min(k, MaxV),0)
setPosition(k, True)
Return True
End Sub

'Public Sub RemoveMe
'mybase.RemoveView
'End Sub
'
'Public Sub SetLayout(Left As Int,Top As Int,Width As Int,Height As Int)
'mybase.SetLayout(Left,Top,Width,Height)
'Hbase.SetLayout(0, 0, Width,Height)
'H = Height 
'W = Width
'ContinueCreation
'End Sub