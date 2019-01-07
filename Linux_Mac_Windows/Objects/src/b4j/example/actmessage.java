package b4j.example;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.debug.*;

public class actmessage extends Object{
public static actmessage mostCurrent = new actmessage();

public static BA ba;
static {
		ba = new  anywheresoftware.b4j.objects.FxBA("b4j.example", "b4j.example.actmessage", null);
		ba.loadHtSubs(actmessage.class);
        if (ba.getClass().getName().endsWith("ShellBA")) {
			
			ba.raiseEvent2(null, true, "SHELL", false);
			ba.raiseEvent2(null, true, "CREATE", true, "b4j.example.actmessage", ba);
		}
	}
    public static Class<?> getObject() {
		return actmessage.class;
	}

 public static anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4j.objects.JFX _fx = null;
public static anywheresoftware.b4j.objects.Form _frm1 = null;
public static b4j.example.loading _loading = null;
public static anywheresoftware.b4j.objects.ListViewWrapper _lv1 = null;
public static anywheresoftware.b4j.objects.ButtonWrapper _btnpoints = null;
public static anywheresoftware.b4j.objects.ButtonWrapper _btnsendpoint = null;
public static anywheresoftware.b4j.objects.PaneWrapper.ConcretePaneWrapper _pnlpoints = null;
public static anywheresoftware.b4a.objects.collections.Map _currentmessage = null;
public static anywheresoftware.b4j.objects.WebViewWrapper _webview1 = null;
public static b4j.example.httputils2service _httputils2service = null;
public static b4j.example.main _main = null;
public static b4j.example.actbookmenu _actbookmenu = null;
public static b4j.example.actcontent _actcontent = null;
public static b4j.example.actdownload _actdownload = null;
public static b4j.example.actsitesocial _actsitesocial = null;
public static b4j.example.library _library = null;
public static b4j.example.actsendpoint _actsendpoint = null;
public static b4j.example.actpoints _actpoints = null;
public static b4j.example.actabout _actabout = null;
public static b4j.example.actcontact _actcontact = null;
public static b4j.example.dbutils _dbutils = null;
public static b4j.example.actbookinformation _actbookinformation = null;
public static b4j.example.actsetting _actsetting = null;
public static b4j.example.actsearchbook _actsearchbook = null;
public static String  _btnpoints_action() throws Exception{
 //BA.debugLineNum = 94;BA.debugLine="Sub btnpoints_Action";
 //BA.debugLineNum = 95;BA.debugLine="actPoints.currentMessage = currentMessage";
_actpoints._currentmessage = _currentmessage;
 //BA.debugLineNum = 96;BA.debugLine="actPoints.ShowPoint";
_actpoints._showpoint();
 //BA.debugLineNum = 97;BA.debugLine="End Sub";
return "";
}
public static String  _btnsendpoint_action() throws Exception{
 //BA.debugLineNum = 99;BA.debugLine="Sub btnsendpoint_Action";
 //BA.debugLineNum = 100;BA.debugLine="actSendPoint.currentMessage = currentMessage";
_actsendpoint._currentmessage = _currentmessage;
 //BA.debugLineNum = 101;BA.debugLine="actSendPoint.ShowPoint";
_actsendpoint._showpoint();
 //BA.debugLineNum = 102;BA.debugLine="End Sub";
return "";
}
public static String  _frm1_closerequest(anywheresoftware.b4j.objects.NodeWrapper.ConcreteEventWrapper _eventdata) throws Exception{
 //BA.debugLineNum = 104;BA.debugLine="Sub frm1_CloseRequest (EventData As Event)";
 //BA.debugLineNum = 105;BA.debugLine="If WebView1.Visible = True Then";
if (_webview1.getVisible()==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 106;BA.debugLine="EventData.Consume";
_eventdata.Consume();
 //BA.debugLineNum = 107;BA.debugLine="pnlpoints.Visible = False";
_pnlpoints.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 108;BA.debugLine="WebView1.Visible = False";
_webview1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 109;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 111;BA.debugLine="End Sub";
return "";
}
public static String  _jobdone(b4j.example.httpjob _job) throws Exception{
anywheresoftware.b4j.objects.collections.JSONParser _js = null;
anywheresoftware.b4a.objects.collections.List _m1 = null;
int _i = 0;
anywheresoftware.b4a.objects.collections.Map _ms = null;
String _ss = "";
anywheresoftware.b4j.objects.LabelWrapper _lb = null;
anywheresoftware.b4j.objects.LabelWrapper _ls = null;
 //BA.debugLineNum = 33;BA.debugLine="Sub JobDone(Job As HttpJob)";
 //BA.debugLineNum = 35;BA.debugLine="loading.Hide";
_loading._hide();
 //BA.debugLineNum = 37;BA.debugLine="Dim js As JSONParser";
_js = new anywheresoftware.b4j.objects.collections.JSONParser();
 //BA.debugLineNum = 39;BA.debugLine="If Job.Success Then";
if (_job._success) { 
 //BA.debugLineNum = 41;BA.debugLine="js.Initialize(Job.GetString)";
_js.Initialize(_job._getstring());
 //BA.debugLineNum = 43;BA.debugLine="Dim m1 As List";
_m1 = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 44;BA.debugLine="m1 = js.NextArray";
_m1 = _js.NextArray();
 //BA.debugLineNum = 46;BA.debugLine="For i = 0 To m1.Size - 1";
{
final int step7 = 1;
final int limit7 = (int) (_m1.getSize()-1);
for (_i = (int) (0) ; (step7 > 0 && _i <= limit7) || (step7 < 0 && _i >= limit7); _i = ((int)(0 + _i + step7)) ) {
 //BA.debugLineNum = 47;BA.debugLine="Dim ms As Map";
_ms = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 48;BA.debugLine="ms = m1.Get(i)";
_ms.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_m1.Get(_i)));
 //BA.debugLineNum = 50;BA.debugLine="Dim ss As String";
_ss = "";
 //BA.debugLineNum = 51;BA.debugLine="ss = ms.Get(\"sMessage\")";
_ss = BA.ObjectToString(_ms.Get((Object)("sMessage")));
 //BA.debugLineNum = 53;BA.debugLine="If ss.Length > 100 Then";
if (_ss.length()>100) { 
 //BA.debugLineNum = 54;BA.debugLine="ss = ss.SubString2(0,100)";
_ss = _ss.substring((int) (0),(int) (100));
 };
 //BA.debugLineNum = 57;BA.debugLine="Dim lb As Label";
_lb = new anywheresoftware.b4j.objects.LabelWrapper();
 //BA.debugLineNum = 58;BA.debugLine="lb.Initialize(\"lbs\")";
_lb.Initialize(ba,"lbs");
 //BA.debugLineNum = 59;BA.debugLine="lb.TextSize = 14";
_lb.setTextSize(14);
 //BA.debugLineNum = 60;BA.debugLine="lb.Alignment = \"TOP_RIGHT\"";
_lb.setAlignment("TOP_RIGHT");
 //BA.debugLineNum = 61;BA.debugLine="lb.Text = ms.Get(\"sTitle\") & \" : \" & CRLF & ss";
_lb.setText(BA.ObjectToString(_ms.Get((Object)("sTitle")))+" : "+anywheresoftware.b4a.keywords.Common.CRLF+_ss);
 //BA.debugLineNum = 62;BA.debugLine="lb.Font = fx.CreateFont(\"tahoma\",14,False,False";
_lb.setFont(_fx.CreateFont("tahoma",14,anywheresoftware.b4a.keywords.Common.False,anywheresoftware.b4a.keywords.Common.False));
 //BA.debugLineNum = 63;BA.debugLine="lb.Tag = ms";
_lb.setTag((Object)(_ms.getObject()));
 //BA.debugLineNum = 64;BA.debugLine="lb.TextColor = fx.Colors.Black";
_lb.setTextColor(_fx.Colors.Black);
 //BA.debugLineNum = 65;BA.debugLine="lb.SetSize(lv1.Width-30,270)";
_lb.SetSize(_lv1.getWidth()-30,270);
 //BA.debugLineNum = 66;BA.debugLine="lv1.Items.Add(lb)";
_lv1.getItems().Add((Object)(_lb.getObject()));
 //BA.debugLineNum = 68;BA.debugLine="Dim ls As Label";
_ls = new anywheresoftware.b4j.objects.LabelWrapper();
 //BA.debugLineNum = 69;BA.debugLine="ls.Initialize(\"\")";
_ls.Initialize(ba,"");
 //BA.debugLineNum = 70;BA.debugLine="ls.SetSize(lv1.Width,1)";
_ls.SetSize(_lv1.getWidth(),1);
 //BA.debugLineNum = 71;BA.debugLine="lv1.Items.Add(ls)";
_lv1.getItems().Add((Object)(_ls.getObject()));
 }
};
 };
 //BA.debugLineNum = 76;BA.debugLine="End Sub";
return "";
}
public static String  _lbs_mouseclicked(anywheresoftware.b4j.objects.NodeWrapper.MouseEventWrapper _eventdata) throws Exception{
anywheresoftware.b4j.objects.LabelWrapper _lb = null;
anywheresoftware.b4a.objects.collections.Map _m = null;
 //BA.debugLineNum = 78;BA.debugLine="Sub lbs_MouseClicked (EventData As MouseEvent)";
 //BA.debugLineNum = 80;BA.debugLine="Dim lb As Label";
_lb = new anywheresoftware.b4j.objects.LabelWrapper();
 //BA.debugLineNum = 81;BA.debugLine="lb = Sender";
_lb.setObject((javafx.scene.control.Label)(anywheresoftware.b4a.keywords.Common.Sender(ba)));
 //BA.debugLineNum = 83;BA.debugLine="Dim m As Map";
_m = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 84;BA.debugLine="m = lb.Tag";
_m.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_lb.getTag()));
 //BA.debugLineNum = 85;BA.debugLine="currentMessage = m";
_currentmessage = _m;
 //BA.debugLineNum = 87;BA.debugLine="WebView1.Visible = True";
_webview1.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 88;BA.debugLine="WebView1.LoadHtml(m.Get(\"sMessage\"))";
_webview1.LoadHtml(BA.ObjectToString(_m.Get((Object)("sMessage"))));
 //BA.debugLineNum = 90;BA.debugLine="pnlpoints.Visible = True";
_pnlpoints.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 92;BA.debugLine="End Sub";
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
 //BA.debugLineNum = 6;BA.debugLine="Private lv1 As ListView";
_lv1 = new anywheresoftware.b4j.objects.ListViewWrapper();
 //BA.debugLineNum = 7;BA.debugLine="Private btnpoints As Button";
_btnpoints = new anywheresoftware.b4j.objects.ButtonWrapper();
 //BA.debugLineNum = 8;BA.debugLine="Private btnsendpoint As Button";
_btnsendpoint = new anywheresoftware.b4j.objects.ButtonWrapper();
 //BA.debugLineNum = 9;BA.debugLine="Private pnlpoints As Pane";
_pnlpoints = new anywheresoftware.b4j.objects.PaneWrapper.ConcretePaneWrapper();
 //BA.debugLineNum = 10;BA.debugLine="Private currentMessage As Map";
_currentmessage = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 11;BA.debugLine="Private WebView1 As WebView";
_webview1 = new anywheresoftware.b4j.objects.WebViewWrapper();
 //BA.debugLineNum = 12;BA.debugLine="End Sub";
return "";
}
public static String  _showmessage() throws Exception{
b4j.example.httpjob _hu = null;
 //BA.debugLineNum = 14;BA.debugLine="Sub ShowMessage";
 //BA.debugLineNum = 16;BA.debugLine="frm1.Initialize(\"frm1\",470,610)";
_frm1.Initialize(ba,"frm1",470,610);
 //BA.debugLineNum = 17;BA.debugLine="frm1.Resizable = False";
_frm1.setResizable(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 18;BA.debugLine="frm1.RootPane.LoadLayout(\"frmmessage\")";
_frm1.getRootPane().LoadLayout(ba,"frmmessage");
 //BA.debugLineNum = 19;BA.debugLine="frm1.Show";
_frm1.Show();
 //BA.debugLineNum = 21;BA.debugLine="Library.CenterFormOnScreen(frm1)";
_library._centerformonscreen(_frm1);
 //BA.debugLineNum = 23;BA.debugLine="loading.Initialize(frm1)";
_loading._initialize(ba,_frm1);
 //BA.debugLineNum = 25;BA.debugLine="loading.Show(\"در حال دریافت...\")";
_loading._show("در حال دریافت...");
 //BA.debugLineNum = 27;BA.debugLine="Dim hu As HttpJob";
_hu = new b4j.example.httpjob();
 //BA.debugLineNum = 28;BA.debugLine="hu.Initialize(\"getmessage\",Me)";
_hu._initialize(ba,"getmessage",actmessage.getObject());
 //BA.debugLineNum = 29;BA.debugLine="hu.Download(Library.URL & \"get_message\")";
_hu._download(_library._url+"get_message");
 //BA.debugLineNum = 31;BA.debugLine="End Sub";
return "";
}
}
