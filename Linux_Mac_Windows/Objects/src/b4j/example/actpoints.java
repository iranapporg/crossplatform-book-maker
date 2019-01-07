package b4j.example;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.debug.*;

public class actpoints extends Object{
public static actpoints mostCurrent = new actpoints();

public static BA ba;
static {
		ba = new  anywheresoftware.b4j.objects.FxBA("b4j.example", "b4j.example.actpoints", null);
		ba.loadHtSubs(actpoints.class);
        if (ba.getClass().getName().endsWith("ShellBA")) {
			
			ba.raiseEvent2(null, true, "SHELL", false);
			ba.raiseEvent2(null, true, "CREATE", true, "b4j.example.actpoints", ba);
		}
	}
    public static Class<?> getObject() {
		return actpoints.class;
	}

 public static anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4j.objects.JFX _fx = null;
public static anywheresoftware.b4j.objects.Form _frm1 = null;
public static b4j.example.loading _loading = null;
public static anywheresoftware.b4a.objects.collections.Map _currentmessage = null;
public static anywheresoftware.b4j.objects.LabelWrapper _lblucomment = null;
public static anywheresoftware.b4j.objects.LabelWrapper _lbluname = null;
public static anywheresoftware.b4j.objects.LabelWrapper _lbludate = null;
public static anywheresoftware.b4j.objects.ListViewWrapper _sv1 = null;
public static b4j.example.httputils2service _httputils2service = null;
public static b4j.example.main _main = null;
public static b4j.example.actbookmenu _actbookmenu = null;
public static b4j.example.actcontent _actcontent = null;
public static b4j.example.actdownload _actdownload = null;
public static b4j.example.actsitesocial _actsitesocial = null;
public static b4j.example.library _library = null;
public static b4j.example.actmessage _actmessage = null;
public static b4j.example.actsendpoint _actsendpoint = null;
public static b4j.example.actabout _actabout = null;
public static b4j.example.actcontact _actcontact = null;
public static b4j.example.dbutils _dbutils = null;
public static b4j.example.actbookinformation _actbookinformation = null;
public static b4j.example.actsetting _actsetting = null;
public static b4j.example.actsearchbook _actsearchbook = null;
public static String  _jobdone(b4j.example.httpjob _job) throws Exception{
anywheresoftware.b4j.objects.collections.JSONParser _js = null;
anywheresoftware.b4a.objects.collections.List _l1 = null;
int _i = 0;
anywheresoftware.b4a.objects.collections.Map _m2 = null;
anywheresoftware.b4j.objects.PaneWrapper.ConcretePaneWrapper _p2 = null;
 //BA.debugLineNum = 31;BA.debugLine="Sub JobDone(Job As HttpJob)";
 //BA.debugLineNum = 33;BA.debugLine="loading.Hide";
_loading._hide();
 //BA.debugLineNum = 35;BA.debugLine="Dim js As JSONParser";
_js = new anywheresoftware.b4j.objects.collections.JSONParser();
 //BA.debugLineNum = 37;BA.debugLine="If Job.Success Then";
if (_job._success) { 
 //BA.debugLineNum = 39;BA.debugLine="If Job.JobName = \"getpoints\" Then";
if ((_job._jobname).equals("getpoints")) { 
 //BA.debugLineNum = 40;BA.debugLine="Dim l1 As List";
_l1 = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 41;BA.debugLine="l1.Initialize";
_l1.Initialize();
 //BA.debugLineNum = 42;BA.debugLine="js.Initialize(Job.GetString)";
_js.Initialize(_job._getstring());
 //BA.debugLineNum = 43;BA.debugLine="l1 = js.NextArray";
_l1 = _js.NextArray();
 //BA.debugLineNum = 45;BA.debugLine="If l1.Size = 0 Then";
if (_l1.getSize()==0) { 
 //BA.debugLineNum = 46;BA.debugLine="fx.Msgbox(frm1,\"هیچ نظری برای این خبر وجود ندا";
_fx.Msgbox(_frm1,"هیچ نظری برای این خبر وجود ندارد","توجه");
 //BA.debugLineNum = 47;BA.debugLine="frm1.Close";
_frm1.Close();
 //BA.debugLineNum = 48;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 51;BA.debugLine="For i = 0 To l1.Size - 1";
{
final int step14 = 1;
final int limit14 = (int) (_l1.getSize()-1);
for (_i = (int) (0) ; (step14 > 0 && _i <= limit14) || (step14 < 0 && _i >= limit14); _i = ((int)(0 + _i + step14)) ) {
 //BA.debugLineNum = 52;BA.debugLine="Dim m2 As Map";
_m2 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 53;BA.debugLine="m2 = l1.Get(i)";
_m2.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_l1.Get(_i)));
 //BA.debugLineNum = 55;BA.debugLine="Dim p2 As Pane";
_p2 = new anywheresoftware.b4j.objects.PaneWrapper.ConcretePaneWrapper();
 //BA.debugLineNum = 56;BA.debugLine="p2.Initialize(\"\")";
_p2.Initialize(ba,"");
 //BA.debugLineNum = 57;BA.debugLine="p2.LoadLayout(\"frmpoints\")";
_p2.LoadLayout(ba,"frmpoints");
 //BA.debugLineNum = 58;BA.debugLine="p2.SetSize(400,240)";
_p2.SetSize(400,240);
 //BA.debugLineNum = 59;BA.debugLine="sv1.Items.Add(p2)";
_sv1.getItems().Add((Object)(_p2.getObject()));
 //BA.debugLineNum = 61;BA.debugLine="lblucomment.Text = m2.Get(\"sComment\")";
_lblucomment.setText(BA.ObjectToString(_m2.Get((Object)("sComment"))));
 //BA.debugLineNum = 62;BA.debugLine="lbluname.Text = m2.Get(\"sName\")";
_lbluname.setText(BA.ObjectToString(_m2.Get((Object)("sName"))));
 //BA.debugLineNum = 63;BA.debugLine="lbludate.Text = Library.GetDate(m2.Get(\"sDate\"";
_lbludate.setText(_library._getdate(BA.ObjectToString(_m2.Get((Object)("sDate")))));
 }
};
 };
 };
 //BA.debugLineNum = 69;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 2;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 3;BA.debugLine="Private fx As JFX";
_fx = new anywheresoftware.b4j.objects.JFX();
 //BA.debugLineNum = 4;BA.debugLine="Private frm1 As Form";
_frm1 = new anywheresoftware.b4j.objects.Form();
 //BA.debugLineNum = 5;BA.debugLine="Private loading As Loading";
_loading = new b4j.example.loading();
 //BA.debugLineNum = 6;BA.debugLine="Public currentMessage As Map";
_currentmessage = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 7;BA.debugLine="Private lblucomment As Label";
_lblucomment = new anywheresoftware.b4j.objects.LabelWrapper();
 //BA.debugLineNum = 8;BA.debugLine="Private lbluname As Label";
_lbluname = new anywheresoftware.b4j.objects.LabelWrapper();
 //BA.debugLineNum = 9;BA.debugLine="Private lbludate As Label";
_lbludate = new anywheresoftware.b4j.objects.LabelWrapper();
 //BA.debugLineNum = 10;BA.debugLine="Private sv1 As ListView";
_sv1 = new anywheresoftware.b4j.objects.ListViewWrapper();
 //BA.debugLineNum = 11;BA.debugLine="End Sub";
return "";
}
public static String  _showpoint() throws Exception{
b4j.example.httpjob _hu = null;
 //BA.debugLineNum = 13;BA.debugLine="Sub ShowPoint";
 //BA.debugLineNum = 15;BA.debugLine="frm1.Initialize(\"\",400,570)";
_frm1.Initialize(ba,"",400,570);
 //BA.debugLineNum = 16;BA.debugLine="frm1.Resizable = False";
_frm1.setResizable(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 17;BA.debugLine="frm1.RootPane.LoadLayout(\"frmlistpoints\")";
_frm1.getRootPane().LoadLayout(ba,"frmlistpoints");
 //BA.debugLineNum = 18;BA.debugLine="frm1.Show";
_frm1.Show();
 //BA.debugLineNum = 20;BA.debugLine="Library.CenterFormOnScreen(frm1)";
_library._centerformonscreen(_frm1);
 //BA.debugLineNum = 22;BA.debugLine="loading.Initialize(frm1)";
_loading._initialize(ba,_frm1);
 //BA.debugLineNum = 23;BA.debugLine="loading.Show(\"در حال دریافت...\")";
_loading._show("در حال دریافت...");
 //BA.debugLineNum = 25;BA.debugLine="Dim hu As HttpJob";
_hu = new b4j.example.httpjob();
 //BA.debugLineNum = 26;BA.debugLine="hu.Initialize(\"getpoints\",Me)";
_hu._initialize(ba,"getpoints",actpoints.getObject());
 //BA.debugLineNum = 27;BA.debugLine="hu.Download(Library.URL & \"get_message_points/\" &";
_hu._download(_library._url+"get_message_points/"+BA.ObjectToString(_currentmessage.Get((Object)("sID"))));
 //BA.debugLineNum = 29;BA.debugLine="End Sub";
return "";
}
}
