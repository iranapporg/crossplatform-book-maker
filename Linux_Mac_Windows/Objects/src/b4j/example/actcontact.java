package b4j.example;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.debug.*;

public class actcontact extends Object{
public static actcontact mostCurrent = new actcontact();

public static BA ba;
static {
		ba = new  anywheresoftware.b4j.objects.FxBA("b4j.example", "b4j.example.actcontact", null);
		ba.loadHtSubs(actcontact.class);
        if (ba.getClass().getName().endsWith("ShellBA")) {
			
			ba.raiseEvent2(null, true, "SHELL", false);
			ba.raiseEvent2(null, true, "CREATE", true, "b4j.example.actcontact", ba);
		}
	}
    public static Class<?> getObject() {
		return actcontact.class;
	}

 public static anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4j.objects.JFX _fx = null;
public static anywheresoftware.b4j.objects.Form _frm1 = null;
public static anywheresoftware.b4j.objects.TextInputControlWrapper.TextAreaWrapper _txtcomment = null;
public static anywheresoftware.b4j.objects.TextInputControlWrapper.TextFieldWrapper _txtemail = null;
public static anywheresoftware.b4j.objects.TextInputControlWrapper.TextFieldWrapper _txtname = null;
public static b4j.example.loading _loading = null;
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
public static b4j.example.dbutils _dbutils = null;
public static b4j.example.actbookinformation _actbookinformation = null;
public static b4j.example.actsetting _actsetting = null;
public static b4j.example.actsearchbook _actsearchbook = null;
public static String  _btnsend_action() throws Exception{
b4j.example.httpjob _hhu = null;
 //BA.debugLineNum = 29;BA.debugLine="Sub btnsend_Action";
 //BA.debugLineNum = 31;BA.debugLine="If txtname.Text.Length < 3 Or txtemail.Text.Index";
if (_txtname.getText().length()<3 || _txtemail.getText().indexOf("@")==-1 || _txtcomment.getText().length()<3) { 
 //BA.debugLineNum = 32;BA.debugLine="fx.Msgbox(frm1,\"لطفا اطلاعات را درست وارد کنید\",";
_fx.Msgbox(_frm1,"لطفا اطلاعات را درست وارد کنید","توجه");
 //BA.debugLineNum = 33;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 36;BA.debugLine="loading.Show(\"در حال ارسال\")";
_loading._show("در حال ارسال");
 //BA.debugLineNum = 37;BA.debugLine="Dim hhu As HttpJob";
_hhu = new b4j.example.httpjob();
 //BA.debugLineNum = 38;BA.debugLine="hhu.Initialize(\"send\",Me)";
_hhu._initialize(ba,"send",actcontact.getObject());
 //BA.debugLineNum = 39;BA.debugLine="hhu.PostString(Library.URL & \"/contact\",\"name=\" &";
_hhu._poststring(_library._url+"/contact","name="+_txtname.getText()+"&email="+_txtemail.getText()+"&comment="+_txtcomment.getText());
 //BA.debugLineNum = 41;BA.debugLine="End Sub";
return "";
}
public static String  _jobdone(b4j.example.httpjob _job) throws Exception{
 //BA.debugLineNum = 43;BA.debugLine="Sub JobDone(Job As HttpJob)";
 //BA.debugLineNum = 45;BA.debugLine="loading.Hide";
_loading._hide();
 //BA.debugLineNum = 47;BA.debugLine="If Job.Success Then";
if (_job._success) { 
 //BA.debugLineNum = 48;BA.debugLine="If Job.GetString = \"1\" Then";
if ((_job._getstring()).equals("1")) { 
 //BA.debugLineNum = 49;BA.debugLine="fx.Msgbox(frm1,\"پیام شما با موفقیت ارسال شد\",\"ت";
_fx.Msgbox(_frm1,"پیام شما با موفقیت ارسال شد","توجه");
 //BA.debugLineNum = 50;BA.debugLine="frm1.Close";
_frm1.Close();
 }else {
 //BA.debugLineNum = 52;BA.debugLine="fx.Msgbox(frm1,\"برنامه با خطا مواجه شد دوباره ت";
_fx.Msgbox(_frm1,"برنامه با خطا مواجه شد دوباره تلاش کنید","توجه");
 };
 };
 //BA.debugLineNum = 56;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 2;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 3;BA.debugLine="Private fx As JFX";
_fx = new anywheresoftware.b4j.objects.JFX();
 //BA.debugLineNum = 4;BA.debugLine="Private frm1 As Form";
_frm1 = new anywheresoftware.b4j.objects.Form();
 //BA.debugLineNum = 5;BA.debugLine="Private txtcomment As TextArea";
_txtcomment = new anywheresoftware.b4j.objects.TextInputControlWrapper.TextAreaWrapper();
 //BA.debugLineNum = 6;BA.debugLine="Private txtemail As TextField";
_txtemail = new anywheresoftware.b4j.objects.TextInputControlWrapper.TextFieldWrapper();
 //BA.debugLineNum = 7;BA.debugLine="Private txtname As TextField";
_txtname = new anywheresoftware.b4j.objects.TextInputControlWrapper.TextFieldWrapper();
 //BA.debugLineNum = 8;BA.debugLine="Private loading As Loading";
_loading = new b4j.example.loading();
 //BA.debugLineNum = 9;BA.debugLine="End Sub";
return "";
}
public static String  _showcontact() throws Exception{
anywheresoftware.b4j.objects.JFX.ScreenWrapper _s = null;
 //BA.debugLineNum = 11;BA.debugLine="Sub ShowContact";
 //BA.debugLineNum = 13;BA.debugLine="If frm1.IsInitialized = False Then";
if (_frm1.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 14;BA.debugLine="Dim s As Screen";
_s = new anywheresoftware.b4j.objects.JFX.ScreenWrapper();
 //BA.debugLineNum = 15;BA.debugLine="s = fx.Screens.Get(0)";
_s.setObject((javafx.stage.Screen)(_fx.getScreens().Get((int) (0))));
 //BA.debugLineNum = 16;BA.debugLine="frm1.Initialize(\"\",470,460)";
_frm1.Initialize(ba,"",470,460);
 };
 //BA.debugLineNum = 19;BA.debugLine="frm1.Resizable = False";
_frm1.setResizable(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 20;BA.debugLine="frm1.RootPane.LoadLayout(\"frmcontact\")";
_frm1.getRootPane().LoadLayout(ba,"frmcontact");
 //BA.debugLineNum = 21;BA.debugLine="frm1.Show";
_frm1.Show();
 //BA.debugLineNum = 23;BA.debugLine="Library.CenterFormOnScreen(frm1)";
_library._centerformonscreen(_frm1);
 //BA.debugLineNum = 25;BA.debugLine="loading.Initialize(frm1)";
_loading._initialize(ba,_frm1);
 //BA.debugLineNum = 27;BA.debugLine="End Sub";
return "";
}
}
