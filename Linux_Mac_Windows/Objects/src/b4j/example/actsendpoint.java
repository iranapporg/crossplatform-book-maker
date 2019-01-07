package b4j.example;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.debug.*;

public class actsendpoint extends Object{
public static actsendpoint mostCurrent = new actsendpoint();

public static BA ba;
static {
		ba = new  anywheresoftware.b4j.objects.FxBA("b4j.example", "b4j.example.actsendpoint", null);
		ba.loadHtSubs(actsendpoint.class);
        if (ba.getClass().getName().endsWith("ShellBA")) {
			
			ba.raiseEvent2(null, true, "SHELL", false);
			ba.raiseEvent2(null, true, "CREATE", true, "b4j.example.actsendpoint", ba);
		}
	}
    public static Class<?> getObject() {
		return actsendpoint.class;
	}

 public static anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4j.objects.JFX _fx = null;
public static anywheresoftware.b4j.objects.Form _frm1 = null;
public static anywheresoftware.b4j.objects.TextInputControlWrapper.TextFieldWrapper _txtname = null;
public static anywheresoftware.b4j.objects.TextInputControlWrapper.TextFieldWrapper _txtcomment = null;
public static b4j.example.loading _loading = null;
public static anywheresoftware.b4a.objects.collections.Map _currentmessage = null;
public static b4j.example.httputils2service _httputils2service = null;
public static b4j.example.main _main = null;
public static b4j.example.actbookmenu _actbookmenu = null;
public static b4j.example.actcontent _actcontent = null;
public static b4j.example.actdownload _actdownload = null;
public static b4j.example.actsitesocial _actsitesocial = null;
public static b4j.example.library _library = null;
public static b4j.example.actmessage _actmessage = null;
public static b4j.example.actpoints _actpoints = null;
public static b4j.example.actabout _actabout = null;
public static b4j.example.actcontact _actcontact = null;
public static b4j.example.dbutils _dbutils = null;
public static b4j.example.actbookinformation _actbookinformation = null;
public static b4j.example.actsetting _actsetting = null;
public static b4j.example.actsearchbook _actsearchbook = null;
public static String  _btnsend_action() throws Exception{
b4j.example.httpjob _hu = null;
 //BA.debugLineNum = 24;BA.debugLine="Sub btnsend_Action";
 //BA.debugLineNum = 26;BA.debugLine="If txtname.Text.Length < 3 Or txtcomment.Text.Len";
if (_txtname.getText().length()<3 || _txtcomment.getText().length()<5) { 
 //BA.debugLineNum = 27;BA.debugLine="fx.Msgbox(frm1,\"لطفا اطلاعات را درست وارد کنید|\"";
_fx.Msgbox(_frm1,"لطفا اطلاعات را درست وارد کنید|","توجه");
 //BA.debugLineNum = 28;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 31;BA.debugLine="loading.Show(\"در حال ارسال...\")";
_loading._show("در حال ارسال...");
 //BA.debugLineNum = 33;BA.debugLine="Dim hu As HttpJob";
_hu = new b4j.example.httpjob();
 //BA.debugLineNum = 34;BA.debugLine="hu.Initialize(\"sendpoint\",Me)";
_hu._initialize(ba,"sendpoint",actsendpoint.getObject());
 //BA.debugLineNum = 35;BA.debugLine="hu.PostString(Library.URL & \"add_points\",$\"name=$";
_hu._poststring(_library._url+"add_points",("name="+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_txtname.getText()))+"&comment="+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_txtcomment.getText()))+"&device=mobile&id="+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",_currentmessage.Get((Object)("sID")))+""));
 //BA.debugLineNum = 37;BA.debugLine="txtname.Text = \"\"";
_txtname.setText("");
 //BA.debugLineNum = 38;BA.debugLine="txtcomment.Text = \"\"";
_txtcomment.setText("");
 //BA.debugLineNum = 40;BA.debugLine="End Sub";
return "";
}
public static String  _jobdone(b4j.example.httpjob _job) throws Exception{
 //BA.debugLineNum = 42;BA.debugLine="Sub JobDone(Job As HttpJob)";
 //BA.debugLineNum = 44;BA.debugLine="loading.Hide";
_loading._hide();
 //BA.debugLineNum = 46;BA.debugLine="If Job.Success Then";
if (_job._success) { 
 //BA.debugLineNum = 48;BA.debugLine="If Job.JobName = \"sendpoint\" Then";
if ((_job._jobname).equals("sendpoint")) { 
 //BA.debugLineNum = 49;BA.debugLine="If Job.GetString = \"1\" Then";
if ((_job._getstring()).equals("1")) { 
 //BA.debugLineNum = 50;BA.debugLine="fx.Msgbox(frm1,\"نظر شما با موفقیت ارسال شد\",\"ت";
_fx.Msgbox(_frm1,"نظر شما با موفقیت ارسال شد","توجه");
 //BA.debugLineNum = 51;BA.debugLine="frm1.Close";
_frm1.Close();
 //BA.debugLineNum = 52;BA.debugLine="Return";
if (true) return "";
 }else {
 //BA.debugLineNum = 54;BA.debugLine="fx.Msgbox(frm1,\"متاسفانه نظر شما ارسال نشد دوب";
_fx.Msgbox(_frm1,"متاسفانه نظر شما ارسال نشد دوباره تلاش کنید","توجه");
 };
 };
 };
 //BA.debugLineNum = 59;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 2;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 3;BA.debugLine="Private fx As JFX";
_fx = new anywheresoftware.b4j.objects.JFX();
 //BA.debugLineNum = 4;BA.debugLine="Private frm1 As Form";
_frm1 = new anywheresoftware.b4j.objects.Form();
 //BA.debugLineNum = 5;BA.debugLine="Private txtname As TextField";
_txtname = new anywheresoftware.b4j.objects.TextInputControlWrapper.TextFieldWrapper();
 //BA.debugLineNum = 6;BA.debugLine="Private txtcomment As TextField";
_txtcomment = new anywheresoftware.b4j.objects.TextInputControlWrapper.TextFieldWrapper();
 //BA.debugLineNum = 7;BA.debugLine="Private loading As Loading";
_loading = new b4j.example.loading();
 //BA.debugLineNum = 8;BA.debugLine="Public currentMessage As Map";
_currentmessage = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 9;BA.debugLine="End Sub";
return "";
}
public static String  _showpoint() throws Exception{
 //BA.debugLineNum = 11;BA.debugLine="Sub ShowPoint";
 //BA.debugLineNum = 13;BA.debugLine="frm1.Initialize(\"\",440,590)";
_frm1.Initialize(ba,"",440,590);
 //BA.debugLineNum = 14;BA.debugLine="frm1.Resizable = False";
_frm1.setResizable(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 15;BA.debugLine="frm1.RootPane.LoadLayout(\"frmsendpoint\")";
_frm1.getRootPane().LoadLayout(ba,"frmsendpoint");
 //BA.debugLineNum = 16;BA.debugLine="frm1.Show";
_frm1.Show();
 //BA.debugLineNum = 18;BA.debugLine="Library.CenterFormOnScreen(frm1)";
_library._centerformonscreen(_frm1);
 //BA.debugLineNum = 20;BA.debugLine="loading.Initialize(frm1)";
_loading._initialize(ba,_frm1);
 //BA.debugLineNum = 22;BA.debugLine="End Sub";
return "";
}
}
