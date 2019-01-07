package b4j.example;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.debug.*;

public class actsitesocial extends Object{
public static actsitesocial mostCurrent = new actsitesocial();

public static BA ba;
static {
		ba = new  anywheresoftware.b4j.objects.FxBA("b4j.example", "b4j.example.actsitesocial", null);
		ba.loadHtSubs(actsitesocial.class);
        if (ba.getClass().getName().endsWith("ShellBA")) {
			
			ba.raiseEvent2(null, true, "SHELL", false);
			ba.raiseEvent2(null, true, "CREATE", true, "b4j.example.actsitesocial", ba);
		}
	}
    public static Class<?> getObject() {
		return actsitesocial.class;
	}

 public static anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4j.objects.JFX _fx = null;
public static anywheresoftware.b4j.objects.ListViewWrapper _lvlist = null;
public static String _stype = "";
public static anywheresoftware.b4j.objects.Form _frm1 = null;
public static b4j.example.loading _loading = null;
public static anywheresoftware.b4j.objects.ImageViewWrapper _imgicon = null;
public static anywheresoftware.b4j.objects.LabelWrapper _lblcatname = null;
public static anywheresoftware.b4j.objects.PaneWrapper.ConcretePaneWrapper _pnlover = null;
public static b4j.example.httputils2service _httputils2service = null;
public static b4j.example.main _main = null;
public static b4j.example.actbookmenu _actbookmenu = null;
public static b4j.example.actcontent _actcontent = null;
public static b4j.example.actdownload _actdownload = null;
public static b4j.example.library _library = null;
public static b4j.example.actmessage _actmessage = null;
public static b4j.example.actsendpoint _actsendpoint = null;
public static b4j.example.actpoints _actpoints = null;
public static b4j.example.actabout _actabout = null;
public static b4j.example.actcontact _actcontact = null;
public static b4j.example.dbutils _dbutils = null;
public static b4j.example.actbookinformation _actbookinformation = null;
public static b4j.example.actsetting _actsetting = null;
public static b4j.example.actsearchbook _actsearchbook = null;
public static String  _jobdone(b4j.example.httpjob _job) throws Exception{
anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper _ou = null;
 //BA.debugLineNum = 89;BA.debugLine="Sub JobDone(Job As HttpJob)";
 //BA.debugLineNum = 91;BA.debugLine="loading.Hide";
_loading._hide();
 //BA.debugLineNum = 93;BA.debugLine="If Job.Success Then";
if (_job._success) { 
 //BA.debugLineNum = 94;BA.debugLine="If Job.JobName = \"download_base\" Then";
if ((_job._jobname).equals("download_base")) { 
 //BA.debugLineNum = 95;BA.debugLine="Dim ou As OutputStream";
_ou = new anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper();
 //BA.debugLineNum = 96;BA.debugLine="ou = File.OpenOutput(File.DirApp & \"/Data\",\"mai";
_ou = anywheresoftware.b4a.keywords.Common.File.OpenOutput(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data","main_db.db",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 97;BA.debugLine="File.Copy2(Job.GetInputStream,ou)";
anywheresoftware.b4a.keywords.Common.File.Copy2((java.io.InputStream)(_job._getinputstream().getObject()),(java.io.OutputStream)(_ou.getObject()));
 //BA.debugLineNum = 98;BA.debugLine="ou.Close";
_ou.Close();
 //BA.debugLineNum = 99;BA.debugLine="Load";
_load();
 }else {
 //BA.debugLineNum = 101;BA.debugLine="Dim ou As OutputStream";
_ou = new anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper();
 //BA.debugLineNum = 102;BA.debugLine="File.MakeDir(File.DirApp,\"Downloads\")";
anywheresoftware.b4a.keywords.Common.File.MakeDir(anywheresoftware.b4a.keywords.Common.File.getDirApp(),"Downloads");
 //BA.debugLineNum = 103;BA.debugLine="ou = File.OpenOutput(File.DirApp & \"\\Downloads\"";
_ou = anywheresoftware.b4a.keywords.Common.File.OpenOutput(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"\\Downloads",_job._jobname,anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 104;BA.debugLine="File.Copy2(Job.GetInputStream,ou)";
anywheresoftware.b4a.keywords.Common.File.Copy2((java.io.InputStream)(_job._getinputstream().getObject()),(java.io.OutputStream)(_ou.getObject()));
 //BA.debugLineNum = 105;BA.debugLine="ou.Close";
_ou.Close();
 //BA.debugLineNum = 106;BA.debugLine="fx.Msgbox(frm1,\"فایل دانلود شده و در حافظه خارج";
_fx.Msgbox(_frm1,"فایل دانلود شده و در حافظه خارجی شما ذخیره شد","توجه");
 };
 };
 //BA.debugLineNum = 109;BA.debugLine="End Sub";
return "";
}
public static String  _load() throws Exception{
b4j.example.baseinformationapp _dbbase = null;
anywheresoftware.b4a.objects.collections.List _ls = null;
int _i = 0;
anywheresoftware.b4j.objects.PaneWrapper.ConcretePaneWrapper _p1 = null;
anywheresoftware.b4a.objects.collections.Map _temp = null;
 //BA.debugLineNum = 43;BA.debugLine="Sub Load";
 //BA.debugLineNum = 45;BA.debugLine="Dim dbBase As BaseInformationApp";
_dbbase = new b4j.example.baseinformationapp();
 //BA.debugLineNum = 46;BA.debugLine="Dim ls As List";
_ls = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 48;BA.debugLine="ls.Initialize";
_ls.Initialize();
 //BA.debugLineNum = 49;BA.debugLine="dbBase.Initialize";
_dbbase._initialize(ba);
 //BA.debugLineNum = 51;BA.debugLine="ls = dbBase.GetData(sType)";
_ls = _dbbase._getdata(_stype);
 //BA.debugLineNum = 53;BA.debugLine="For i = 0 To ls.Size - 1";
{
final int step6 = 1;
final int limit6 = (int) (_ls.getSize()-1);
for (_i = (int) (0) ; (step6 > 0 && _i <= limit6) || (step6 < 0 && _i >= limit6); _i = ((int)(0 + _i + step6)) ) {
 //BA.debugLineNum = 55;BA.debugLine="Dim p1 As Pane";
_p1 = new anywheresoftware.b4j.objects.PaneWrapper.ConcretePaneWrapper();
 //BA.debugLineNum = 56;BA.debugLine="p1.Initialize(\"\")";
_p1.Initialize(ba,"");
 //BA.debugLineNum = 57;BA.debugLine="p1.LoadLayout(\"frmtemplate_category\")";
_p1.LoadLayout(ba,"frmtemplate_category");
 //BA.debugLineNum = 58;BA.debugLine="p1.SetSize(400,60)";
_p1.SetSize(400,60);
 //BA.debugLineNum = 59;BA.debugLine="lvlist.Items.Add(p1)";
_lvlist.getItems().Add((Object)(_p1.getObject()));
 //BA.debugLineNum = 61;BA.debugLine="Dim temp As Map";
_temp = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 62;BA.debugLine="temp = ls.Get(i)";
_temp.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_ls.Get(_i)));
 //BA.debugLineNum = 64;BA.debugLine="lblcatname.Text = temp.Get(\"sTitle\")";
_lblcatname.setText(BA.ObjectToString(_temp.Get((Object)("sTitle"))));
 //BA.debugLineNum = 65;BA.debugLine="pnlover.Tag = temp";
_pnlover.setTag((Object)(_temp.getObject()));
 //BA.debugLineNum = 67;BA.debugLine="If sType = \"site\" Then";
if ((_stype).equals("site")) { 
 //BA.debugLineNum = 68;BA.debugLine="imgicon.SetImage(fx.LoadImage(File.DirAssets,\"n";
_imgicon.SetImage((javafx.scene.image.Image)(_fx.LoadImage(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"net.png").getObject()));
 }else {
 //BA.debugLineNum = 70;BA.debugLine="imgicon.SetImage(fx.LoadImage(File.DirAssets,\"s";
_imgicon.SetImage((javafx.scene.image.Image)(_fx.LoadImage(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"social.png").getObject()));
 };
 }
};
 //BA.debugLineNum = 75;BA.debugLine="End Sub";
return "";
}
public static String  _pnlover_mouseclicked(anywheresoftware.b4j.objects.NodeWrapper.MouseEventWrapper _eventdata) throws Exception{
anywheresoftware.b4j.objects.PaneWrapper.ConcretePaneWrapper _p = null;
anywheresoftware.b4a.objects.collections.Map _ms = null;
 //BA.debugLineNum = 77;BA.debugLine="Sub pnlover_MouseClicked (EventData As MouseEvent)";
 //BA.debugLineNum = 79;BA.debugLine="Dim p As Pane";
_p = new anywheresoftware.b4j.objects.PaneWrapper.ConcretePaneWrapper();
 //BA.debugLineNum = 80;BA.debugLine="p = Sender";
_p.setObject((javafx.scene.layout.Pane)(anywheresoftware.b4a.keywords.Common.Sender(ba)));
 //BA.debugLineNum = 82;BA.debugLine="Dim ms As Map";
_ms = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 83;BA.debugLine="ms = p.Tag";
_ms.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_p.getTag()));
 //BA.debugLineNum = 85;BA.debugLine="fx.ShowExternalDocument(ms.Get(\"sLink1\"))";
_fx.ShowExternalDocument(BA.ObjectToString(_ms.Get((Object)("sLink1"))));
 //BA.debugLineNum = 87;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 2;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 3;BA.debugLine="Private fx As JFX";
_fx = new anywheresoftware.b4j.objects.JFX();
 //BA.debugLineNum = 4;BA.debugLine="Private lvlist As ListView";
_lvlist = new anywheresoftware.b4j.objects.ListViewWrapper();
 //BA.debugLineNum = 5;BA.debugLine="Public sType As String";
_stype = "";
 //BA.debugLineNum = 6;BA.debugLine="Private frm1 As Form";
_frm1 = new anywheresoftware.b4j.objects.Form();
 //BA.debugLineNum = 7;BA.debugLine="Private loading As Loading";
_loading = new b4j.example.loading();
 //BA.debugLineNum = 8;BA.debugLine="Private imgicon As ImageView";
_imgicon = new anywheresoftware.b4j.objects.ImageViewWrapper();
 //BA.debugLineNum = 9;BA.debugLine="Private lblcatname As Label";
_lblcatname = new anywheresoftware.b4j.objects.LabelWrapper();
 //BA.debugLineNum = 10;BA.debugLine="Private pnlover As Pane";
_pnlover = new anywheresoftware.b4j.objects.PaneWrapper.ConcretePaneWrapper();
 //BA.debugLineNum = 11;BA.debugLine="End Sub";
return "";
}
public static String  _showlist() throws Exception{
b4j.example.httpjob _hu = null;
 //BA.debugLineNum = 13;BA.debugLine="Sub ShowList";
 //BA.debugLineNum = 15;BA.debugLine="If frm1.IsInitialized = False Then";
if (_frm1.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 16;BA.debugLine="frm1.Initialize(\"\",470,594)";
_frm1.Initialize(ba,"",470,594);
 };
 //BA.debugLineNum = 19;BA.debugLine="frm1.Title = \"لیست نتایج\"";
_frm1.setTitle("لیست نتایج");
 //BA.debugLineNum = 20;BA.debugLine="frm1.Resizable = False";
_frm1.setResizable(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 21;BA.debugLine="frm1.RootPane.LoadLayout(\"frmlist_site_social\")";
_frm1.getRootPane().LoadLayout(ba,"frmlist_site_social");
 //BA.debugLineNum = 22;BA.debugLine="frm1.Show";
_frm1.Show();
 //BA.debugLineNum = 23;BA.debugLine="Library.CenterFormOnScreen(frm1)";
_library._centerformonscreen(_frm1);
 //BA.debugLineNum = 24;BA.debugLine="loading.Initialize(frm1)";
_loading._initialize(ba,_frm1);
 //BA.debugLineNum = 26;BA.debugLine="If sType = \"link\" Then";
if ((_stype).equals("link")) { 
 //BA.debugLineNum = 27;BA.debugLine="frm1.Title = \"شبکه های اجتماعی\"";
_frm1.setTitle("شبکه های اجتماعی");
 }else {
 //BA.debugLineNum = 29;BA.debugLine="frm1.Title = \"آدرس سایت ها\"";
_frm1.setTitle("آدرس سایت ها");
 };
 //BA.debugLineNum = 32;BA.debugLine="If File.Exists(File.DirApp & \"/Data\",\"main_db.db\"";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data","main_db.db")==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 33;BA.debugLine="loading.Show(\"در حال دریافت داده ها...\")";
_loading._show("در حال دریافت داده ها...");
 //BA.debugLineNum = 34;BA.debugLine="Dim hu As HttpJob";
_hu = new b4j.example.httpjob();
 //BA.debugLineNum = 35;BA.debugLine="hu.Initialize(\"download_base\",Me)";
_hu._initialize(ba,"download_base",actsitesocial.getObject());
 //BA.debugLineNum = 36;BA.debugLine="hu.Download(Library.BaseUrl & \"/data/main_db.db\"";
_hu._download(_library._baseurl+"/data/main_db.db");
 }else {
 //BA.debugLineNum = 38;BA.debugLine="Load";
_load();
 };
 //BA.debugLineNum = 41;BA.debugLine="End Sub";
return "";
}
}
