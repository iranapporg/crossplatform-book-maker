package b4j.example;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.debug.*;

public class actabout extends Object{
public static actabout mostCurrent = new actabout();

public static BA ba;
static {
		ba = new  anywheresoftware.b4j.objects.FxBA("b4j.example", "b4j.example.actabout", null);
		ba.loadHtSubs(actabout.class);
        if (ba.getClass().getName().endsWith("ShellBA")) {
			
			ba.raiseEvent2(null, true, "SHELL", false);
			ba.raiseEvent2(null, true, "CREATE", true, "b4j.example.actabout", ba);
		}
	}
    public static Class<?> getObject() {
		return actabout.class;
	}

 public static anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4j.objects.JFX _fx = null;
public static anywheresoftware.b4j.objects.Form _frm1 = null;
public static b4j.example.loading _loading = null;
public static anywheresoftware.b4j.objects.WebViewWrapper _wb1 = null;
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
public static b4j.example.actcontact _actcontact = null;
public static b4j.example.dbutils _dbutils = null;
public static b4j.example.actbookinformation _actbookinformation = null;
public static b4j.example.actsetting _actsetting = null;
public static b4j.example.actsearchbook _actsearchbook = null;
public static String  _jobdone(b4j.example.httpjob _job) throws Exception{
anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper _ou = null;
 //BA.debugLineNum = 36;BA.debugLine="Sub JobDone(Job As HttpJob)";
 //BA.debugLineNum = 38;BA.debugLine="loading.Hide";
_loading._hide();
 //BA.debugLineNum = 40;BA.debugLine="If Job.Success Then";
if (_job._success) { 
 //BA.debugLineNum = 41;BA.debugLine="If Job.JobName = \"about\" Then";
if ((_job._jobname).equals("about")) { 
 //BA.debugLineNum = 42;BA.debugLine="Dim ou As OutputStream";
_ou = new anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper();
 //BA.debugLineNum = 43;BA.debugLine="ou = File.OpenOutput(File.DirApp & \"/Data\",\"abo";
_ou = anywheresoftware.b4a.keywords.Common.File.OpenOutput(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data","about",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 44;BA.debugLine="File.Copy2(Job.GetInputStream,ou)";
anywheresoftware.b4a.keywords.Common.File.Copy2((java.io.InputStream)(_job._getinputstream().getObject()),(java.io.OutputStream)(_ou.getObject()));
 //BA.debugLineNum = 45;BA.debugLine="ou.Close";
_ou.Close();
 //BA.debugLineNum = 46;BA.debugLine="wb1.LoadHtml(File.ReadString(File.DirApp & \"/Da";
_wb1.LoadHtml(anywheresoftware.b4a.keywords.Common.File.ReadString(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data","about"));
 };
 };
 //BA.debugLineNum = 50;BA.debugLine="End Sub";
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
 //BA.debugLineNum = 6;BA.debugLine="Private wb1 As WebView";
_wb1 = new anywheresoftware.b4j.objects.WebViewWrapper();
 //BA.debugLineNum = 7;BA.debugLine="End Sub";
return "";
}
public static String  _showabout() throws Exception{
anywheresoftware.b4j.objects.JFX.ScreenWrapper _s = null;
b4j.example.httpjob _hu = null;
 //BA.debugLineNum = 9;BA.debugLine="Sub ShowAbout";
 //BA.debugLineNum = 11;BA.debugLine="If frm1.IsInitialized = False Then";
if (_frm1.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 12;BA.debugLine="Dim s As Screen";
_s = new anywheresoftware.b4j.objects.JFX.ScreenWrapper();
 //BA.debugLineNum = 13;BA.debugLine="s = fx.Screens.Get(0)";
_s.setObject((javafx.stage.Screen)(_fx.getScreens().Get((int) (0))));
 //BA.debugLineNum = 14;BA.debugLine="frm1.Initialize(\"\",s.MaxX,s.MaxY)";
_frm1.Initialize(ba,"",_s.getMaxX(),_s.getMaxY());
 };
 //BA.debugLineNum = 17;BA.debugLine="frm1.Resizable = False";
_frm1.setResizable(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 18;BA.debugLine="frm1.RootPane.LoadLayout(\"frmabout\")";
_frm1.getRootPane().LoadLayout(ba,"frmabout");
 //BA.debugLineNum = 19;BA.debugLine="frm1.Show";
_frm1.Show();
 //BA.debugLineNum = 20;BA.debugLine="Library.CenterFormOnScreen(frm1)";
_library._centerformonscreen(_frm1);
 //BA.debugLineNum = 22;BA.debugLine="If File.Exists(File.DirApp & \"/Data\",\"about\") The";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data","about")) { 
 //BA.debugLineNum = 23;BA.debugLine="wb1.LoadHtml(File.ReadString(File.DirApp & \"/Dat";
_wb1.LoadHtml(anywheresoftware.b4a.keywords.Common.File.ReadString(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data","about"));
 //BA.debugLineNum = 24;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 27;BA.debugLine="Dim hu As HttpJob";
_hu = new b4j.example.httpjob();
 //BA.debugLineNum = 28;BA.debugLine="hu.Initialize(\"about\",Me)";
_hu._initialize(ba,"about",actabout.getObject());
 //BA.debugLineNum = 29;BA.debugLine="hu.Download(Library.BaseURL & \"about.html\")";
_hu._download(_library._baseurl+"about.html");
 //BA.debugLineNum = 31;BA.debugLine="loading.Initialize(frm1)";
_loading._initialize(ba,_frm1);
 //BA.debugLineNum = 32;BA.debugLine="loading.Show(\"در حال نمایش...\")";
_loading._show("در حال نمایش...");
 //BA.debugLineNum = 34;BA.debugLine="End Sub";
return "";
}
}
