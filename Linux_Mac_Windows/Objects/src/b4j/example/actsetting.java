package b4j.example;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.debug.*;

public class actsetting extends Object{
public static actsetting mostCurrent = new actsetting();

public static BA ba;
static {
		ba = new  anywheresoftware.b4j.objects.FxBA("b4j.example", "b4j.example.actsetting", null);
		ba.loadHtSubs(actsetting.class);
        if (ba.getClass().getName().endsWith("ShellBA")) {
			
			ba.raiseEvent2(null, true, "SHELL", false);
			ba.raiseEvent2(null, true, "CREATE", true, "b4j.example.actsetting", ba);
		}
	}
    public static Class<?> getObject() {
		return actsetting.class;
	}

 public static anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4j.objects.JFX _fx = null;
public static anywheresoftware.b4j.objects.Form _frm1 = null;
public static anywheresoftware.b4j.objects.ComboBoxWrapper _cmfontname = null;
public static anywheresoftware.b4j.objects.ComboBoxWrapper _cmfontsize = null;
public static String _fontname = "";
public static float _fontsize = 0f;
public static b4j.example.httputils2service _httputils2service = null;
public static b4j.example.main _main = null;
public static b4j.example.actbookmenu _actbookmenu = null;
public static b4j.example.actcontent _actcontent = null;
public static b4j.example.actdownload _actdownload = null;
public static b4j.example.actsitesocial _actsitesocial = null;
public static b4j.example.library _library = null;
public static b4j.example.actmessage _actmessage = null;
public static b4j.example.actsendpoint _actsendpoint = null;
public static b4j.example.actpoints _actpoints = null;
public static b4j.example.actabout _actabout = null;
public static b4j.example.actcontact _actcontact = null;
public static b4j.example.dbutils _dbutils = null;
public static b4j.example.actbookinformation _actbookinformation = null;
public static b4j.example.actsearchbook _actsearchbook = null;
public static String  _btnsave_action() throws Exception{
 //BA.debugLineNum = 32;BA.debugLine="Sub btnsave_Action";
 //BA.debugLineNum = 33;BA.debugLine="Library.manager.PutFloat(\"fontsize\",fontsize)";
_library._manager.PutFloat("fontsize",_fontsize);
 //BA.debugLineNum = 34;BA.debugLine="Library.manager.PutString(\"fontname\",fontname)";
_library._manager.PutString("fontname",_fontname);
 //BA.debugLineNum = 35;BA.debugLine="frm1.Close";
_frm1.Close();
 //BA.debugLineNum = 36;BA.debugLine="End Sub";
return "";
}
public static String  _cmfontname_selectedindexchanged(int _index,Object _value) throws Exception{
 //BA.debugLineNum = 38;BA.debugLine="Sub cmfontname_SelectedIndexChanged(Index As Int,";
 //BA.debugLineNum = 39;BA.debugLine="If Index = 0 Then";
if (_index==0) { 
 //BA.debugLineNum = 40;BA.debugLine="fontname = \"byekan.ttf\"";
_fontname = "byekan.ttf";
 }else if(_index==1) { 
 //BA.debugLineNum = 42;BA.debugLine="fontname = \"bn.ttf\"";
_fontname = "bn.ttf";
 }else if(_index==2) { 
 //BA.debugLineNum = 44;BA.debugLine="fontname = \"tahoma.ttf\"";
_fontname = "tahoma.ttf";
 };
 //BA.debugLineNum = 46;BA.debugLine="End Sub";
return "";
}
public static String  _cmfontsize_selectedindexchanged(int _index,Object _value) throws Exception{
 //BA.debugLineNum = 48;BA.debugLine="Sub cmfontsize_SelectedIndexChanged(Index As Int,";
 //BA.debugLineNum = 50;BA.debugLine="fontsize = Value";
_fontsize = (float)(BA.ObjectToNumber(_value));
 //BA.debugLineNum = 52;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 2;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 3;BA.debugLine="Private fx As JFX";
_fx = new anywheresoftware.b4j.objects.JFX();
 //BA.debugLineNum = 4;BA.debugLine="Private frm1 As Form";
_frm1 = new anywheresoftware.b4j.objects.Form();
 //BA.debugLineNum = 5;BA.debugLine="Private cmfontname As ComboBox";
_cmfontname = new anywheresoftware.b4j.objects.ComboBoxWrapper();
 //BA.debugLineNum = 6;BA.debugLine="Private cmfontsize As ComboBox";
_cmfontsize = new anywheresoftware.b4j.objects.ComboBoxWrapper();
 //BA.debugLineNum = 7;BA.debugLine="Private fontname As String";
_fontname = "";
 //BA.debugLineNum = 8;BA.debugLine="Private fontsize As Float";
_fontsize = 0f;
 //BA.debugLineNum = 9;BA.debugLine="End Sub";
return "";
}
public static String  _showform() throws Exception{
int _i = 0;
 //BA.debugLineNum = 11;BA.debugLine="Sub ShowForm";
 //BA.debugLineNum = 13;BA.debugLine="frm1.Initialize(\"\",330,410)";
_frm1.Initialize(ba,"",330,410);
 //BA.debugLineNum = 14;BA.debugLine="frm1.Resizable = False";
_frm1.setResizable(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 15;BA.debugLine="frm1.RootPane.LoadLayout(\"frmsetting\")";
_frm1.getRootPane().LoadLayout(ba,"frmsetting");
 //BA.debugLineNum = 16;BA.debugLine="frm1.SetOwner(Main.MainForm)";
_frm1.SetOwner(_main._mainform);
 //BA.debugLineNum = 17;BA.debugLine="frm1.Show";
_frm1.Show();
 //BA.debugLineNum = 19;BA.debugLine="cmfontname.Items.Add(\"بی یکان\")";
_cmfontname.getItems().Add((Object)("بی یکان"));
 //BA.debugLineNum = 20;BA.debugLine="cmfontname.Items.Add(\"نازنین\")";
_cmfontname.getItems().Add((Object)("نازنین"));
 //BA.debugLineNum = 21;BA.debugLine="cmfontname.Items.Add(\"tahoma\")";
_cmfontname.getItems().Add((Object)("tahoma"));
 //BA.debugLineNum = 22;BA.debugLine="cmfontname.SelectedIndex = 0";
_cmfontname.setSelectedIndex((int) (0));
 //BA.debugLineNum = 24;BA.debugLine="For i = 14 To 50";
{
final int step10 = 1;
final int limit10 = (int) (50);
for (_i = (int) (14) ; (step10 > 0 && _i <= limit10) || (step10 < 0 && _i >= limit10); _i = ((int)(0 + _i + step10)) ) {
 //BA.debugLineNum = 25;BA.debugLine="cmfontsize.Items.Add(i)";
_cmfontsize.getItems().Add((Object)(_i));
 }
};
 //BA.debugLineNum = 28;BA.debugLine="cmfontsize.SelectedIndex = 0";
_cmfontsize.setSelectedIndex((int) (0));
 //BA.debugLineNum = 30;BA.debugLine="End Sub";
return "";
}
}
