package b4j.example;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.debug.*;

public class main extends javafx.application.Application{
public static main mostCurrent = new main();

public static BA ba;
static {
		ba = new  anywheresoftware.b4j.objects.FxBA("b4j.example", "b4j.example.main", null);
		ba.loadHtSubs(main.class);
        if (ba.getClass().getName().endsWith("ShellBA")) {
			
			ba.raiseEvent2(null, true, "SHELL", false);
			ba.raiseEvent2(null, true, "CREATE", true, "b4j.example.main", ba);
		}
	}
    public static Class<?> getObject() {
		return main.class;
	}

 
    public static void main(String[] args) {
    	launch(args);
    }
    public void start (javafx.stage.Stage stage) {
        try {
            anywheresoftware.b4j.objects.FxBA.application = this;
		    anywheresoftware.b4a.keywords.Common.setDensity(javafx.stage.Screen.getPrimary().getDpi());
            BA.Log("Program started.");
            initializeProcessGlobals();
            anywheresoftware.b4j.objects.Form frm = new anywheresoftware.b4j.objects.Form();
            frm.initWithStage(ba, stage, 470, 594);
            ba.raiseEvent(null, "appstart", frm, (String[])getParameters().getRaw().toArray(new String[0]));
        } catch (Throwable t) {
            BA.printException(t, true);
            System.exit(1);
        }
    }
public static anywheresoftware.b4a.keywords.Common __c = null;
public static b4j.example.loading _loading = null;
public static anywheresoftware.b4j.objects.JFX _fx = null;
public static anywheresoftware.b4j.objects.Form _mainform = null;
public static anywheresoftware.b4j.objects.TabPaneWrapper _tab1 = null;
public static anywheresoftware.b4j.objects.ListViewWrapper _lvbooks = null;
public static anywheresoftware.b4j.objects.ImageViewWrapper _img1 = null;
public static anywheresoftware.b4j.objects.ImageViewWrapper _img2 = null;
public static anywheresoftware.b4j.objects.ImageViewWrapper _img3 = null;
public static anywheresoftware.b4j.objects.PaneWrapper.ConcretePaneWrapper _pnlitem = null;
public static anywheresoftware.b4a.objects.collections.List _listtopbook = null;
public static int _topmainpage = 0;
public static anywheresoftware.b4j.objects.ListViewWrapper _lvdownloads = null;
public static anywheresoftware.b4j.objects.MenuItemWrapper.MenuBarWrapper _menubar1 = null;
public static anywheresoftware.b4j.objects.ButtonWrapper _btndeletebook1 = null;
public static anywheresoftware.b4j.objects.ButtonWrapper _btndeletebook2 = null;
public static anywheresoftware.b4j.objects.ButtonWrapper _btndeletebook3 = null;
public static b4j.example.httputils2service _httputils2service = null;
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
public static b4j.example.actsetting _actsetting = null;
public static b4j.example.actsearchbook _actsearchbook = null;
public static String  _appstart(anywheresoftware.b4j.objects.Form _form1,String[] _args) throws Exception{
anywheresoftware.b4j.objects.MenuItemWrapper.ConcreteMenuItemWrapper _mnu = null;
b4j.example.httpjob _hu = null;
 //BA.debugLineNum = 26;BA.debugLine="Sub AppStart (Form1 As Form, Args() As String)";
 //BA.debugLineNum = 28;BA.debugLine="MainForm = Form1";
_mainform = _form1;
 //BA.debugLineNum = 29;BA.debugLine="MainForm.SetFormStyle(\"UNIFIED\")";
_mainform.SetFormStyle("UNIFIED");
 //BA.debugLineNum = 30;BA.debugLine="MainForm.Title = \"کتابخانه\"";
_mainform.setTitle("کتابخانه");
 //BA.debugLineNum = 31;BA.debugLine="MainForm.RootPane.LoadLayout(\"frmmain\")";
_mainform.getRootPane().LoadLayout(ba,"frmmain");
 //BA.debugLineNum = 32;BA.debugLine="MainForm.Resizable = False";
_mainform.setResizable(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 33;BA.debugLine="Library.CenterFormOnScreen(MainForm)";
_library._centerformonscreen(_mainform);
 //BA.debugLineNum = 35;BA.debugLine="File.MakeDir(File.DirApp,\"Data\")";
anywheresoftware.b4a.keywords.Common.File.MakeDir(anywheresoftware.b4a.keywords.Common.File.getDirApp(),"Data");
 //BA.debugLineNum = 37;BA.debugLine="loading.Initialize(MainForm)";
_loading._initialize(ba,_mainform);
 //BA.debugLineNum = 39;BA.debugLine="Dim mnu As MenuItem";
_mnu = new anywheresoftware.b4j.objects.MenuItemWrapper.ConcreteMenuItemWrapper();
 //BA.debugLineNum = 40;BA.debugLine="mnu = MenuBar1.Menus.Get(1)";
_mnu.setObject((javafx.scene.control.MenuItem)(_menubar1.getMenus().Get((int) (1))));
 //BA.debugLineNum = 41;BA.debugLine="mnu.Enabled = False";
_mnu.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 43;BA.debugLine="Library.Manager.Initialize";
_library._manager.Initialize(ba);
 //BA.debugLineNum = 44;BA.debugLine="tab1.LoadLayout(\"frmlastvisit\",\"آخرین کتاب های خو";
_tab1.LoadLayout(ba,"frmlastvisit","آخرین کتاب های خوانده شده");
 //BA.debugLineNum = 45;BA.debugLine="tab1.LoadLayout(\"frmdownloads\",\"کتاب های دانلود ش";
_tab1.LoadLayout(ba,"frmdownloads","کتاب های دانلود شده").setTag((Object)("download"));
 //BA.debugLineNum = 47;BA.debugLine="Try";
try { //BA.debugLineNum = 48;BA.debugLine="Dim hu As HttpJob";
_hu = new b4j.example.httpjob();
 //BA.debugLineNum = 49;BA.debugLine="hu.Initialize(\"book\",Me)";
_hu._initialize(ba,"book",main.getObject());
 //BA.debugLineNum = 50;BA.debugLine="hu.PostString(Library.URL & \"top_book\",\"\")";
_hu._poststring(_library._url+"top_book","");
 } 
       catch (Exception e20) {
			ba.setLastException(e20); };
 //BA.debugLineNum = 54;BA.debugLine="ExtractOfflineBook";
_extractofflinebook();
 //BA.debugLineNum = 55;BA.debugLine="LoadItem";
_loaditem();
 //BA.debugLineNum = 57;BA.debugLine="MainForm.Show";
_mainform.Show();
 //BA.debugLineNum = 59;BA.debugLine="CheckUpdateLibrary";
_checkupdatelibrary();
 //BA.debugLineNum = 61;BA.debugLine="End Sub";
return "";
}
public static String  _btndeletebook_action() throws Exception{
anywheresoftware.b4j.objects.ButtonWrapper _b1 = null;
b4j.example.categories _db = null;
anywheresoftware.b4a.objects.collections.List _sa = null;
int _i = 0;
 //BA.debugLineNum = 695;BA.debugLine="Sub btndeletebook_Action";
 //BA.debugLineNum = 697;BA.debugLine="Dim b1 As Button";
_b1 = new anywheresoftware.b4j.objects.ButtonWrapper();
 //BA.debugLineNum = 698;BA.debugLine="b1 = Sender";
_b1.setObject((javafx.scene.control.Button)(anywheresoftware.b4a.keywords.Common.Sender(ba)));
 //BA.debugLineNum = 700;BA.debugLine="If fx.Msgbox2(MainForm,\"آیا مطمین به حذف کتاب هست";
if (_fx.Msgbox2(_mainform,"آیا مطمین به حذف کتاب هستید؟","توجه","بله","خیر","",_fx.MSGBOX_CONFIRMATION)==_fx.DialogResponse.CANCEL) { 
if (true) return "";};
 //BA.debugLineNum = 702;BA.debugLine="Dim db As Categories";
_db = new b4j.example.categories();
 //BA.debugLineNum = 703;BA.debugLine="db.Initialize";
_db._initialize(ba);
 //BA.debugLineNum = 704;BA.debugLine="db.DownloadedBook(b1.Tag,\"0\")";
_db._downloadedbook(BA.ObjectToString(_b1.getTag()),(int)(Double.parseDouble("0")));
 //BA.debugLineNum = 705;BA.debugLine="db.Close";
_db._close();
 //BA.debugLineNum = 707;BA.debugLine="Dim sa As List";
_sa = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 708;BA.debugLine="sa = File.ListFiles(File.DirApp & \"/Data/book/\" &";
_sa = anywheresoftware.b4a.keywords.Common.File.ListFiles(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data/book/"+BA.ObjectToString(_b1.getTag()));
 //BA.debugLineNum = 710;BA.debugLine="If sa.IsInitialized Then";
if (_sa.IsInitialized()) { 
 //BA.debugLineNum = 711;BA.debugLine="For i = 0 To sa.Size - 1";
{
final int step11 = 1;
final int limit11 = (int) (_sa.getSize()-1);
for (_i = (int) (0) ; (step11 > 0 && _i <= limit11) || (step11 < 0 && _i >= limit11); _i = ((int)(0 + _i + step11)) ) {
 //BA.debugLineNum = 712;BA.debugLine="File.Delete(File.DirApp & \"/Data/book/\" & b1.Ta";
anywheresoftware.b4a.keywords.Common.File.Delete(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data/book/"+BA.ObjectToString(_b1.getTag()),BA.ObjectToString(_sa.Get(_i)));
 }
};
 };
 //BA.debugLineNum = 716;BA.debugLine="Library.ClearBookReaded(b1.Tag)";
_library._clearbookreaded(BA.ObjectToString(_b1.getTag()));
 //BA.debugLineNum = 718;BA.debugLine="Downloads(\"\",\"\")";
_downloads("","");
 //BA.debugLineNum = 719;BA.debugLine="LastVisit";
_lastvisit();
 //BA.debugLineNum = 721;BA.debugLine="End Sub";
return "";
}
public static String  _checkupdatelibrary() throws Exception{
b4j.example.httpjob _hu = null;
 //BA.debugLineNum = 63;BA.debugLine="Sub CheckUpdateLibrary";
 //BA.debugLineNum = 64;BA.debugLine="Dim hu As HttpJob";
_hu = new b4j.example.httpjob();
 //BA.debugLineNum = 65;BA.debugLine="hu.Initialize(\"bookcount\",Me)";
_hu._initialize(ba,"bookcount",main.getObject());
 //BA.debugLineNum = 66;BA.debugLine="hu.PostString(Library.URL & \"count_book\",\"\")";
_hu._poststring(_library._url+"count_book","");
 //BA.debugLineNum = 67;BA.debugLine="End Sub";
return "";
}
public static String  _downloads(String _filtername,String _filtervalue) throws Exception{
anywheresoftware.b4a.objects.collections.List _ls = null;
b4j.example.categories _dbdownload = null;
int _top = 0;
int _i = 0;
anywheresoftware.b4j.objects.PaneWrapper.ConcretePaneWrapper _p = null;
anywheresoftware.b4a.objects.collections.Map _ms = null;
anywheresoftware.b4a.objects.collections.Map _ms1 = null;
anywheresoftware.b4a.objects.collections.Map _ms2 = null;
 //BA.debugLineNum = 153;BA.debugLine="Sub Downloads(FilterName As String,FilterValue As";
 //BA.debugLineNum = 155;BA.debugLine="If File.Exists(File.DirApp & \"/Data/book\",\"\") = F";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data/book","")==anywheresoftware.b4a.keywords.Common.False) { 
if (true) return "";};
 //BA.debugLineNum = 157;BA.debugLine="Dim ls As List";
_ls = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 158;BA.debugLine="Dim dbDownload As Categories";
_dbdownload = new b4j.example.categories();
 //BA.debugLineNum = 159;BA.debugLine="dbDownload.Initialize";
_dbdownload._initialize(ba);
 //BA.debugLineNum = 161;BA.debugLine="ls = dbDownload.GetDownloadedBook(FilterName,Filt";
_ls = _dbdownload._getdownloadedbook(_filtername,_filtervalue);
 //BA.debugLineNum = 162;BA.debugLine="fx.Msgbox(MainForm,\"تعداد کتاب های دانلود شده \" &";
_fx.Msgbox(_mainform,"تعداد کتاب های دانلود شده "+BA.NumberToString(_ls.getSize()),"توجه");
 //BA.debugLineNum = 164;BA.debugLine="dbDownload.Close";
_dbdownload._close();
 //BA.debugLineNum = 166;BA.debugLine="Dim top As Int";
_top = 0;
 //BA.debugLineNum = 167;BA.debugLine="lvdownloads.Items.Clear";
_lvdownloads.getItems().Clear();
 //BA.debugLineNum = 169;BA.debugLine="top = 0";
_top = (int) (0);
 //BA.debugLineNum = 171;BA.debugLine="For i = 0 To ls.Size - 1";
{
final int step11 = 1;
final int limit11 = (int) (_ls.getSize()-1);
for (_i = (int) (0) ; (step11 > 0 && _i <= limit11) || (step11 < 0 && _i >= limit11); _i = ((int)(0 + _i + step11)) ) {
 //BA.debugLineNum = 173;BA.debugLine="Dim p As Pane";
_p = new anywheresoftware.b4j.objects.PaneWrapper.ConcretePaneWrapper();
 //BA.debugLineNum = 174;BA.debugLine="p.Initialize(\"\")";
_p.Initialize(ba,"");
 //BA.debugLineNum = 175;BA.debugLine="p.LoadLayout(\"frmtemplate_box\")";
_p.LoadLayout(ba,"frmtemplate_box");
 //BA.debugLineNum = 176;BA.debugLine="p.SetSize(lvbooks.Width,200)";
_p.SetSize(_lvbooks.getWidth(),200);
 //BA.debugLineNum = 177;BA.debugLine="lvdownloads.Items.Add(p)";
_lvdownloads.getItems().Add((Object)(_p.getObject()));
 //BA.debugLineNum = 179;BA.debugLine="img1.Visible = True";
_img1.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 181;BA.debugLine="Dim ms As Map";
_ms = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 182;BA.debugLine="ms = ls.Get(i)";
_ms.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_ls.Get(_i)));
 //BA.debugLineNum = 184;BA.debugLine="If File.Exists(File.DirApp & \"/Data\" & \"/book/\"";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data"+"/book/"+BA.ObjectToString(_ms.Get((Object)("sID"))),"cover.jpg")) { 
 //BA.debugLineNum = 185;BA.debugLine="img1.SetImage(fx.LoadImage(File.DirApp & \"/Data";
_img1.SetImage((javafx.scene.image.Image)(_fx.LoadImage(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data"+"/book/"+BA.ObjectToString(_ms.Get((Object)("sID"))),"cover.jpg").getObject()));
 }else {
 //BA.debugLineNum = 187;BA.debugLine="img1.SetImage(fx.LoadImage(File.DirAssets,\"no_c";
_img1.SetImage((javafx.scene.image.Image)(_fx.LoadImage(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"no_cover.png").getObject()));
 };
 //BA.debugLineNum = 190;BA.debugLine="btndeletebook1.Visible = True";
_btndeletebook1.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 191;BA.debugLine="btndeletebook1.Tag = ms.Get(\"sID\")";
_btndeletebook1.setTag(_ms.Get((Object)("sID")));
 //BA.debugLineNum = 193;BA.debugLine="img1.Tag = ms.Get(\"sID\")";
_img1.setTag(_ms.Get((Object)("sID")));
 //BA.debugLineNum = 195;BA.debugLine="If i + 1 > ls.Size - 1 Then";
if (_i+1>_ls.getSize()-1) { 
 //BA.debugLineNum = 196;BA.debugLine="top = top + p.Height + 10";
_top = (int) (_top+_p.getHeight()+10);
 //BA.debugLineNum = 197;BA.debugLine="Exit";
if (true) break;
 };
 //BA.debugLineNum = 200;BA.debugLine="img2.Visible = True";
_img2.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 202;BA.debugLine="Dim ms1 As Map";
_ms1 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 203;BA.debugLine="ms1 = ls.Get(i+1)";
_ms1.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_ls.Get((int) (_i+1))));
 //BA.debugLineNum = 205;BA.debugLine="If File.Exists(File.DirApp & \"/Data\" & \"/book/\"";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data"+"/book/"+BA.ObjectToString(_ms1.Get((Object)("sID"))),"cover.jpg")) { 
 //BA.debugLineNum = 206;BA.debugLine="img2.SetImage(fx.LoadImage(File.DirApp & \"/Data";
_img2.SetImage((javafx.scene.image.Image)(_fx.LoadImage(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data"+"/book/"+BA.ObjectToString(_ms1.Get((Object)("sID"))),"cover.jpg").getObject()));
 }else {
 //BA.debugLineNum = 208;BA.debugLine="img2.SetImage(fx.LoadImage(File.DirAssets,\"no_c";
_img2.SetImage((javafx.scene.image.Image)(_fx.LoadImage(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"no_cover.png").getObject()));
 };
 //BA.debugLineNum = 211;BA.debugLine="img2.Tag = ms1.Get(\"sID\")";
_img2.setTag(_ms1.Get((Object)("sID")));
 //BA.debugLineNum = 213;BA.debugLine="btndeletebook2.Visible = True";
_btndeletebook2.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 214;BA.debugLine="btndeletebook2.Tag = ms1.Get(\"sID\")";
_btndeletebook2.setTag(_ms1.Get((Object)("sID")));
 //BA.debugLineNum = 216;BA.debugLine="If i + 2 > ls.Size - 1 Then";
if (_i+2>_ls.getSize()-1) { 
 //BA.debugLineNum = 217;BA.debugLine="top = top + p.Height + 10";
_top = (int) (_top+_p.getHeight()+10);
 //BA.debugLineNum = 218;BA.debugLine="Exit";
if (true) break;
 };
 //BA.debugLineNum = 221;BA.debugLine="img3.Visible = True";
_img3.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 223;BA.debugLine="Dim ms2 As Map";
_ms2 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 224;BA.debugLine="ms2 = ls.Get(i+2)";
_ms2.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_ls.Get((int) (_i+2))));
 //BA.debugLineNum = 226;BA.debugLine="btndeletebook3.Visible = True";
_btndeletebook3.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 227;BA.debugLine="btndeletebook3.Tag = ms2.Get(\"sID\")";
_btndeletebook3.setTag(_ms2.Get((Object)("sID")));
 //BA.debugLineNum = 229;BA.debugLine="If File.Exists(File.DirApp & \"/Data\" & \"/book/\"";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data"+"/book/"+BA.ObjectToString(_ms2.Get((Object)("sID"))),"cover.jpg")) { 
 //BA.debugLineNum = 230;BA.debugLine="img3.SetImage(fx.LoadImage(File.DirApp & \"/Data";
_img3.SetImage((javafx.scene.image.Image)(_fx.LoadImage(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data"+"/book/"+BA.ObjectToString(_ms2.Get((Object)("sID"))),"cover.jpg").getObject()));
 }else {
 //BA.debugLineNum = 232;BA.debugLine="img3.SetImage(fx.LoadImage(File.DirAssets,\"no_c";
_img3.SetImage((javafx.scene.image.Image)(_fx.LoadImage(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"no_cover.png").getObject()));
 };
 //BA.debugLineNum = 235;BA.debugLine="img3.Tag = ms2.Get(\"sID\")";
_img3.setTag(_ms2.Get((Object)("sID")));
 }
};
 //BA.debugLineNum = 239;BA.debugLine="End Sub";
return "";
}
public static String  _extractofflinebook() throws Exception{
anywheresoftware.b4a.objects.collections.List _res = null;
anywheresoftware.b4a.objects.collections.List _books = null;
int _i = 0;
String[] _rs = null;
anywheresoftware.b4a.objects.collections.Map _temp = null;
b4j.example.categories _db = null;
int _j = 0;
anywheresoftware.b4a.objects.collections.Map _ta = null;
b4j.example.categories _db1 = null;
 //BA.debugLineNum = 78;BA.debugLine="Sub ExtractOfflineBook";
 //BA.debugLineNum = 80;BA.debugLine="If Library.Manager.GetBoolean(\"offline\",False) =";
if (_library._manager.GetBoolean("offline",anywheresoftware.b4a.keywords.Common.False)==anywheresoftware.b4a.keywords.Common.True) { 
if (true) return "";};
 //BA.debugLineNum = 82;BA.debugLine="Try";
try { //BA.debugLineNum = 83;BA.debugLine="Dim res,books As List";
_res = new anywheresoftware.b4a.objects.collections.List();
_books = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 84;BA.debugLine="res = File.ReadList(File.DirAssets,\"default.txt\"";
_res = anywheresoftware.b4a.keywords.Common.File.ReadList(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"default.txt");
 } 
       catch (Exception e6) {
			ba.setLastException(e6); //BA.debugLineNum = 86;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 89;BA.debugLine="MainForm.RootPane.Enabled = False";
_mainform.getRootPane().setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 91;BA.debugLine="Library.Manager.PutBoolean(\"offline\",True)";
_library._manager.PutBoolean("offline",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 93;BA.debugLine="Try";
try { //BA.debugLineNum = 94;BA.debugLine="File.ReadString(File.DirAssets,\"default.ttf\")";
anywheresoftware.b4a.keywords.Common.File.ReadString(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"default.ttf");
 //BA.debugLineNum = 95;BA.debugLine="Library.Manager.PutString(\"font\",\"default.ttf\")";
_library._manager.PutString("font","default.ttf");
 } 
       catch (Exception e14) {
			ba.setLastException(e14); //BA.debugLineNum = 97;BA.debugLine="Library.Manager.PutString(\"font\",\"bn.ttf\")";
_library._manager.PutString("font","bn.ttf");
 };
 //BA.debugLineNum = 100;BA.debugLine="books.Initialize";
_books.Initialize();
 //BA.debugLineNum = 102;BA.debugLine="For i = 0 To res.Size - 1";
{
final int step17 = 1;
final int limit17 = (int) (_res.getSize()-1);
for (_i = (int) (0) ; (step17 > 0 && _i <= limit17) || (step17 < 0 && _i >= limit17); _i = ((int)(0 + _i + step17)) ) {
 //BA.debugLineNum = 103;BA.debugLine="Dim rs() As String";
_rs = new String[(int) (0)];
java.util.Arrays.fill(_rs,"");
 //BA.debugLineNum = 104;BA.debugLine="rs = Regex.Split(\"<>\",res.Get(i))";
_rs = anywheresoftware.b4a.keywords.Common.Regex.Split("<>",BA.ObjectToString(_res.Get(_i)));
 //BA.debugLineNum = 106;BA.debugLine="Dim temp As Map";
_temp = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 107;BA.debugLine="temp.Initialize";
_temp.Initialize();
 //BA.debugLineNum = 109;BA.debugLine="temp.Put(\"sID\",rs(0))";
_temp.Put((Object)("sID"),(Object)(_rs[(int) (0)]));
 //BA.debugLineNum = 110;BA.debugLine="temp.Put(\"sCategoryID\",rs(1))";
_temp.Put((Object)("sCategoryID"),(Object)(_rs[(int) (1)]));
 //BA.debugLineNum = 111;BA.debugLine="temp.Put(\"sTitle\",rs(2))";
_temp.Put((Object)("sTitle"),(Object)(_rs[(int) (2)]));
 //BA.debugLineNum = 112;BA.debugLine="temp.Put(\"sCover\",rs(3))";
_temp.Put((Object)("sCover"),(Object)(_rs[(int) (3)]));
 //BA.debugLineNum = 113;BA.debugLine="temp.Put(\"sAuthor\",rs(4))";
_temp.Put((Object)("sAuthor"),(Object)(_rs[(int) (4)]));
 //BA.debugLineNum = 114;BA.debugLine="temp.Put(\"sRate\",rs(5))";
_temp.Put((Object)("sRate"),(Object)(_rs[(int) (5)]));
 //BA.debugLineNum = 115;BA.debugLine="temp.Put(\"sDescription\",rs(6))";
_temp.Put((Object)("sDescription"),(Object)(_rs[(int) (6)]));
 //BA.debugLineNum = 116;BA.debugLine="temp.Put(\"sFileSize\",rs(7))";
_temp.Put((Object)("sFileSize"),(Object)(_rs[(int) (7)]));
 //BA.debugLineNum = 117;BA.debugLine="temp.Put(\"sLanguage\",rs(8))";
_temp.Put((Object)("sLanguage"),(Object)(_rs[(int) (8)]));
 //BA.debugLineNum = 118;BA.debugLine="temp.Put(\"sDownload\",rs(9))";
_temp.Put((Object)("sDownload"),(Object)(_rs[(int) (9)]));
 //BA.debugLineNum = 119;BA.debugLine="temp.Put(\"sPDF\",rs(10))";
_temp.Put((Object)("sPDF"),(Object)(_rs[(int) (10)]));
 //BA.debugLineNum = 120;BA.debugLine="temp.Put(\"sPublishDate\",rs(11))";
_temp.Put((Object)("sPublishDate"),(Object)(_rs[(int) (11)]));
 //BA.debugLineNum = 122;BA.debugLine="books.Add(temp)";
_books.Add((Object)(_temp.getObject()));
 //BA.debugLineNum = 124;BA.debugLine="UnzipBook(rs(0))";
_unzipbook(_rs[(int) (0)]);
 }
};
 //BA.debugLineNum = 128;BA.debugLine="If File.Exists(File.DirApp & \"/Data\",\"visit_book1";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data","visit_book1")==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 129;BA.debugLine="If books.Size > 0 Then";
if (_books.getSize()>0) { 
 //BA.debugLineNum = 130;BA.debugLine="Library.WriteCounterReadBook(temp.Get(\"sID\"))";
_library._writecounterreadbook(BA.ObjectToString(_temp.Get((Object)("sID"))));
 };
 };
 //BA.debugLineNum = 134;BA.debugLine="Dim db As Categories";
_db = new b4j.example.categories();
 //BA.debugLineNum = 135;BA.debugLine="db.Initialize";
_db._initialize(ba);
 //BA.debugLineNum = 136;BA.debugLine="db.AddBook(books)";
_db._addbook(_books);
 //BA.debugLineNum = 138;BA.debugLine="For j = 0 To books.Size - 1";
{
final int step45 = 1;
final int limit45 = (int) (_books.getSize()-1);
for (_j = (int) (0) ; (step45 > 0 && _j <= limit45) || (step45 < 0 && _j >= limit45); _j = ((int)(0 + _j + step45)) ) {
 //BA.debugLineNum = 139;BA.debugLine="Dim ta As Map";
_ta = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 140;BA.debugLine="ta = books.Get(j)";
_ta.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_books.Get(_j)));
 //BA.debugLineNum = 141;BA.debugLine="Dim db1 As Categories";
_db1 = new b4j.example.categories();
 //BA.debugLineNum = 142;BA.debugLine="db1.Initialize";
_db1._initialize(ba);
 //BA.debugLineNum = 143;BA.debugLine="db1.DownloadedBook(ta.Get(\"sID\"),\"1\")";
_db1._downloadedbook(BA.ObjectToString(_ta.Get((Object)("sID"))),(int)(Double.parseDouble("1")));
 //BA.debugLineNum = 144;BA.debugLine="db1.Close";
_db1._close();
 }
};
 //BA.debugLineNum = 147;BA.debugLine="db.Close";
_db._close();
 //BA.debugLineNum = 149;BA.debugLine="MainForm.RootPane.Enabled = True";
_mainform.getRootPane().setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 151;BA.debugLine="End Sub";
return "";
}
public static String  _imgbook_mouseclicked(anywheresoftware.b4j.objects.NodeWrapper.MouseEventWrapper _eventdata) throws Exception{
anywheresoftware.b4j.objects.ImageViewWrapper _im = null;
anywheresoftware.b4a.objects.collections.Map _tem = null;
 //BA.debugLineNum = 677;BA.debugLine="Sub imgbook_MouseClicked (EventData As MouseEvent)";
 //BA.debugLineNum = 679;BA.debugLine="Dim im As ImageView";
_im = new anywheresoftware.b4j.objects.ImageViewWrapper();
 //BA.debugLineNum = 680;BA.debugLine="im = Sender";
_im.setObject((javafx.scene.image.ImageView)(anywheresoftware.b4a.keywords.Common.Sender(ba)));
 //BA.debugLineNum = 682;BA.debugLine="If im.Tag Is Map Then";
if (_im.getTag() instanceof anywheresoftware.b4a.objects.collections.Map.MyMap) { 
 //BA.debugLineNum = 683;BA.debugLine="Dim tem As Map";
_tem = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 684;BA.debugLine="tem = im.Tag";
_tem.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_im.getTag()));
 //BA.debugLineNum = 685;BA.debugLine="actDownload.ShowDownload";
_actdownload._showdownload();
 //BA.debugLineNum = 686;BA.debugLine="actDownload.DownloadBookFromNet(tem.Get(\"sID\"))";
_actdownload._downloadbookfromnet(BA.ObjectToString(_tem.Get((Object)("sID"))));
 //BA.debugLineNum = 687;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 690;BA.debugLine="actBookMenu.BookID = im.Tag";
_actbookmenu._bookid = BA.ObjectToString(_im.getTag());
 //BA.debugLineNum = 691;BA.debugLine="actBookMenu.ShowBookMenu";
_actbookmenu._showbookmenu();
 //BA.debugLineNum = 693;BA.debugLine="End Sub";
return "";
}
public static String  _jobdone(b4j.example.httpjob _job) throws Exception{
anywheresoftware.b4j.objects.collections.JSONParser _js = null;
anywheresoftware.b4a.objects.collections.Map _res = null;
anywheresoftware.b4a.objects.collections.List _ls = null;
anywheresoftware.b4a.objects.collections.Map _md = null;
int _c1 = 0;
int _c2 = 0;
b4j.example.categories _db = null;
 //BA.debugLineNum = 241;BA.debugLine="Sub JobDone(Job As HttpJob)";
 //BA.debugLineNum = 243;BA.debugLine="If Job.Success Then";
if (_job._success) { 
 //BA.debugLineNum = 244;BA.debugLine="If Job.JobName = \"book\" Then";
if ((_job._jobname).equals("book")) { 
 //BA.debugLineNum = 246;BA.debugLine="Dim js As JSONParser";
_js = new anywheresoftware.b4j.objects.collections.JSONParser();
 //BA.debugLineNum = 247;BA.debugLine="js.Initialize(Job.GetString)";
_js.Initialize(_job._getstring());
 //BA.debugLineNum = 248;BA.debugLine="Dim res As Map = js.NextObject";
_res = new anywheresoftware.b4a.objects.collections.Map();
_res = _js.NextObject();
 //BA.debugLineNum = 250;BA.debugLine="If res.IsInitialized Then";
if (_res.IsInitialized()) { 
 //BA.debugLineNum = 251;BA.debugLine="If res.Get(\"success\") = True Then";
if ((_res.Get((Object)("success"))).equals((Object)(anywheresoftware.b4a.keywords.Common.True))) { 
 //BA.debugLineNum = 252;BA.debugLine="Dim ls As List";
_ls = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 253;BA.debugLine="ls = res.Get(\"result\")";
_ls.setObject((java.util.List)(_res.Get((Object)("result"))));
 //BA.debugLineNum = 254;BA.debugLine="listTopBook = ls";
_listtopbook = _ls;
 //BA.debugLineNum = 255;BA.debugLine="TopBooks";
_topbooks();
 };
 };
 }else if((_job._jobname).equals("bookcount")) { 
 //BA.debugLineNum = 260;BA.debugLine="Dim md As Map";
_md = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 261;BA.debugLine="Dim js As JSONParser";
_js = new anywheresoftware.b4j.objects.collections.JSONParser();
 //BA.debugLineNum = 262;BA.debugLine="js.Initialize(Job.GetString)";
_js.Initialize(_job._getstring());
 //BA.debugLineNum = 263;BA.debugLine="md = js.NextObject";
_md = _js.NextObject();
 //BA.debugLineNum = 265;BA.debugLine="If md.ContainsKey(\"result\") Then";
if (_md.ContainsKey((Object)("result"))) { 
 //BA.debugLineNum = 266;BA.debugLine="Dim c1,c2 As Int";
_c1 = 0;
_c2 = 0;
 //BA.debugLineNum = 267;BA.debugLine="Dim db As Categories";
_db = new b4j.example.categories();
 //BA.debugLineNum = 268;BA.debugLine="db.Initialize";
_db._initialize(ba);
 //BA.debugLineNum = 269;BA.debugLine="c1 = db.GetBookCount";
_c1 = _db._getbookcount();
 //BA.debugLineNum = 270;BA.debugLine="db.Close";
_db._close();
 //BA.debugLineNum = 271;BA.debugLine="c2 = md.Get(\"result\")";
_c2 = (int)(BA.ObjectToNumber(_md.Get((Object)("result"))));
 //BA.debugLineNum = 273;BA.debugLine="If c1 <> c2 Then";
if (_c1!=_c2) { 
 //BA.debugLineNum = 274;BA.debugLine="If fx.Msgbox2(MainForm,\"لیست کتاب ها اخیرا تغ";
if (_fx.Msgbox2(_mainform,"لیست کتاب ها اخیرا تغییر کرده است.آیا مایلید بروزرسانی انجام دهید؟","توجه","بله","خیر","",_fx.MSGBOX_CONFIRMATION)==_fx.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 275;BA.debugLine="File.Delete(File.DirApp & \"/Data\",\"data.db\")";
anywheresoftware.b4a.keywords.Common.File.Delete(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data","data.db");
 //BA.debugLineNum = 276;BA.debugLine="actDownload.ForceUpdate = True";
_actdownload._forceupdate = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 277;BA.debugLine="actDownload.ShowDownload";
_actdownload._showdownload();
 };
 };
 };
 }else if((_job._jobname).equals("newsletter")) { 
 //BA.debugLineNum = 283;BA.debugLine="If Job.GetString = \"1\" Then";
if ((_job._getstring()).equals("1")) { 
 //BA.debugLineNum = 284;BA.debugLine="fx.Msgbox(MainForm,\"شما با موفقیت در خبرنامه ع";
_fx.Msgbox(_mainform,"شما با موفقیت در خبرنامه عضو شدید","توجه");
 }else if((_job._getstring()).equals("2")) { 
 //BA.debugLineNum = 286;BA.debugLine="fx.Msgbox(MainForm,\"عضویت شما از خبرنامه حذف ش";
_fx.Msgbox(_mainform,"عضویت شما از خبرنامه حذف شد","توجه");
 };
 };
 };
 //BA.debugLineNum = 292;BA.debugLine="End Sub";
return "";
}
public static String  _lastvisit() throws Exception{
anywheresoftware.b4a.objects.collections.Map _ls = null;
anywheresoftware.b4a.objects.collections.Map _images = null;
int _i = 0;
anywheresoftware.b4j.objects.PaneWrapper.ConcretePaneWrapper _p = null;
 //BA.debugLineNum = 387;BA.debugLine="Sub LastVisit";
 //BA.debugLineNum = 389;BA.debugLine="Dim ls,images As Map";
_ls = new anywheresoftware.b4a.objects.collections.Map();
_images = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 390;BA.debugLine="ls = Library.GetTopReadBook";
_ls = _library._gettopreadbook();
 //BA.debugLineNum = 392;BA.debugLine="lvbooks.Items.Clear";
_lvbooks.getItems().Clear();
 //BA.debugLineNum = 394;BA.debugLine="images.Initialize";
_images.Initialize();
 //BA.debugLineNum = 396;BA.debugLine="If ls = Null Then Return";
if (_ls== null) { 
if (true) return "";};
 //BA.debugLineNum = 397;BA.debugLine="If ls.IsInitialized = False Then Return";
if (_ls.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
if (true) return "";};
 //BA.debugLineNum = 399;BA.debugLine="topMainPage = 0";
_topmainpage = (int) (0);
 //BA.debugLineNum = 401;BA.debugLine="For i = 0 To ls.Size - 1";
{
final int step8 = 1;
final int limit8 = (int) (_ls.getSize()-1);
for (_i = (int) (0) ; (step8 > 0 && _i <= limit8) || (step8 < 0 && _i >= limit8); _i = ((int)(0 + _i + step8)) ) {
 //BA.debugLineNum = 403;BA.debugLine="If i >= 6 Then Exit";
if (_i>=6) { 
if (true) break;};
 //BA.debugLineNum = 405;BA.debugLine="If File.Exists(File.DirApp & \"/Data\" & \"/book/\"";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data"+"/book/"+BA.ObjectToString(_ls.GetKeyAt(_i)),"bank.db")==anywheresoftware.b4a.keywords.Common.False) { 
if (true) continue;};
 //BA.debugLineNum = 407;BA.debugLine="Dim p As Pane";
_p = new anywheresoftware.b4j.objects.PaneWrapper.ConcretePaneWrapper();
 //BA.debugLineNum = 408;BA.debugLine="p.Initialize(\"\")";
_p.Initialize(ba,"");
 //BA.debugLineNum = 409;BA.debugLine="p.LoadLayout(\"frmtemplate_box\")";
_p.LoadLayout(ba,"frmtemplate_box");
 //BA.debugLineNum = 410;BA.debugLine="p.SetSize(lvbooks.Width,200)";
_p.SetSize(_lvbooks.getWidth(),200);
 //BA.debugLineNum = 411;BA.debugLine="lvbooks.Items.Add(p)";
_lvbooks.getItems().Add((Object)(_p.getObject()));
 //BA.debugLineNum = 413;BA.debugLine="img1.Visible = True";
_img1.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 415;BA.debugLine="If File.Exists(File.DirApp & \"/Data\" & \"/book/\"";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data"+"/book/"+BA.ObjectToString(_ls.GetKeyAt(_i)),"cover.jpg")) { 
 //BA.debugLineNum = 416;BA.debugLine="img1.SetImage(fx.LoadImage(File.DirApp & \"/Data";
_img1.SetImage((javafx.scene.image.Image)(_fx.LoadImage(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data"+"/book/"+BA.ObjectToString(_ls.GetKeyAt(_i)),"cover.jpg").getObject()));
 }else {
 //BA.debugLineNum = 418;BA.debugLine="img1.SetImage(fx.LoadImage(File.DirAssets,\"no_c";
_img1.SetImage((javafx.scene.image.Image)(_fx.LoadImage(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"no_cover.png").getObject()));
 };
 //BA.debugLineNum = 421;BA.debugLine="img1.Tag = ls.GetKeyAt(i)";
_img1.setTag(_ls.GetKeyAt(_i));
 //BA.debugLineNum = 423;BA.debugLine="If i + 1 > ls.Size - 1 Then";
if (_i+1>_ls.getSize()-1) { 
 //BA.debugLineNum = 424;BA.debugLine="topMainPage = topMainPage + p.Height + 10";
_topmainpage = (int) (_topmainpage+_p.getHeight()+10);
 //BA.debugLineNum = 425;BA.debugLine="Exit";
if (true) break;
 };
 //BA.debugLineNum = 428;BA.debugLine="img2.Visible = True";
_img2.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 430;BA.debugLine="If File.Exists(File.DirApp & \"/Data\" & \"/book/\"";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data"+"/book/"+BA.ObjectToString(_ls.GetKeyAt((int) (_i+1))),BA.ObjectToString(_ls.GetKeyAt((int) (_i+1)))+".jpg")) { 
 //BA.debugLineNum = 431;BA.debugLine="img2.SetImage(fx.LoadImage(File.DirApp & \"/Data";
_img2.SetImage((javafx.scene.image.Image)(_fx.LoadImage(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data"+"/book/"+BA.ObjectToString(_ls.GetKeyAt((int) (_i+1))),"cover.jpg").getObject()));
 }else {
 //BA.debugLineNum = 433;BA.debugLine="img1.SetImage(fx.LoadImage(File.DirAssets,\"no_c";
_img1.SetImage((javafx.scene.image.Image)(_fx.LoadImage(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"no_cover.png").getObject()));
 };
 //BA.debugLineNum = 436;BA.debugLine="img2.Tag = ls.GetKeyAt(i+1)";
_img2.setTag(_ls.GetKeyAt((int) (_i+1)));
 //BA.debugLineNum = 438;BA.debugLine="If i + 2 > ls.Size - 1 Then";
if (_i+2>_ls.getSize()-1) { 
 //BA.debugLineNum = 439;BA.debugLine="topMainPage = topMainPage + p.Height + 10";
_topmainpage = (int) (_topmainpage+_p.getHeight()+10);
 //BA.debugLineNum = 440;BA.debugLine="Exit";
if (true) break;
 };
 //BA.debugLineNum = 443;BA.debugLine="img3.Visible = True";
_img3.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 445;BA.debugLine="If File.Exists(File.DirApp & \"/Data\" & \"/book/\"";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data"+"/book/"+BA.ObjectToString(_ls.GetKeyAt((int) (_i+2))),BA.ObjectToString(_ls.GetKeyAt((int) (_i+2)))+".jpg")) { 
 //BA.debugLineNum = 446;BA.debugLine="img3.SetImage(fx.LoadImage(File.DirApp & \"/Data";
_img3.SetImage((javafx.scene.image.Image)(_fx.LoadImage(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data"+"/book/"+BA.ObjectToString(_ls.GetKeyAt(_i)),"cover.jpg").getObject()));
 }else {
 //BA.debugLineNum = 448;BA.debugLine="img1.SetImage(fx.LoadImage(File.DirAssets,\"no_c";
_img1.SetImage((javafx.scene.image.Image)(_fx.LoadImage(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"no_cover.png").getObject()));
 };
 //BA.debugLineNum = 451;BA.debugLine="img3.Tag = ls.GetKeyAt(i+2)";
_img3.setTag(_ls.GetKeyAt((int) (_i+2)));
 }
};
 //BA.debugLineNum = 455;BA.debugLine="End Sub";
return "";
}
public static String  _loaditem() throws Exception{
 //BA.debugLineNum = 69;BA.debugLine="Sub LoadItem";
 //BA.debugLineNum = 70;BA.debugLine="Downloads(\"\",\"\")";
_downloads("","");
 //BA.debugLineNum = 71;BA.debugLine="LastVisit";
_lastvisit();
 //BA.debugLineNum = 72;BA.debugLine="End Sub";
return "";
}
public static String  _mnuabout_action() throws Exception{
 //BA.debugLineNum = 633;BA.debugLine="Sub mnuabout_Action";
 //BA.debugLineNum = 634;BA.debugLine="actAbout.ShowAbout";
_actabout._showabout();
 //BA.debugLineNum = 635;BA.debugLine="End Sub";
return "";
}
public static String  _mnucontact_action() throws Exception{
 //BA.debugLineNum = 637;BA.debugLine="Sub mnucontact_Action";
 //BA.debugLineNum = 638;BA.debugLine="actContact.ShowContact";
_actcontact._showcontact();
 //BA.debugLineNum = 639;BA.debugLine="End Sub";
return "";
}
public static String  _mnufilterall_action() throws Exception{
 //BA.debugLineNum = 619;BA.debugLine="Sub mnufilterall_Action";
 //BA.debugLineNum = 620;BA.debugLine="Downloads(\"\",\"\")";
_downloads("","");
 //BA.debugLineNum = 621;BA.debugLine="End Sub";
return "";
}
public static String  _mnufilterauthor_action() throws Exception{
b4j.example.categories _db = null;
anywheresoftware.b4a.objects.collections.List _ls = null;
int _res = 0;
String _auth = "";
 //BA.debugLineNum = 575;BA.debugLine="Sub mnufilterauthor_Action";
 //BA.debugLineNum = 577;BA.debugLine="Dim db As Categories";
_db = new b4j.example.categories();
 //BA.debugLineNum = 578;BA.debugLine="Dim ls As List";
_ls = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 579;BA.debugLine="Dim res As Int";
_res = 0;
 //BA.debugLineNum = 581;BA.debugLine="db.Initialize";
_db._initialize(ba);
 //BA.debugLineNum = 582;BA.debugLine="ls = db.GetBookAuthor";
_ls = _db._getbookauthor();
 //BA.debugLineNum = 583;BA.debugLine="db.Close";
_db._close();
 //BA.debugLineNum = 585;BA.debugLine="If ls.IsInitialized Then";
if (_ls.IsInitialized()) { 
 //BA.debugLineNum = 586;BA.debugLine="res = fx.InputList(MainForm,ls,\"نویسنده را انتخ";
_res = _fx.InputList(_mainform,_ls,"نویسنده را انتخاب کنید","توجه",(int) (0));
 };
 //BA.debugLineNum = 589;BA.debugLine="If res < 0 Then Return";
if (_res<0) { 
if (true) return "";};
 //BA.debugLineNum = 591;BA.debugLine="Dim auth As String";
_auth = "";
 //BA.debugLineNum = 592;BA.debugLine="auth = ls.Get(res)";
_auth = BA.ObjectToString(_ls.Get(_res));
 //BA.debugLineNum = 593;BA.debugLine="Downloads(\"sAuthor\",auth)";
_downloads("sAuthor",_auth);
 //BA.debugLineNum = 595;BA.debugLine="End Sub";
return "";
}
public static String  _mnufiltertopic_action() throws Exception{
b4j.example.categories _db1 = null;
anywheresoftware.b4a.objects.collections.List _ls1 = null;
int _res1 = 0;
String _title = "";
 //BA.debugLineNum = 597;BA.debugLine="Sub mnufiltertopic_Action";
 //BA.debugLineNum = 599;BA.debugLine="Dim db1 As Categories";
_db1 = new b4j.example.categories();
 //BA.debugLineNum = 600;BA.debugLine="Dim ls1 As List";
_ls1 = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 601;BA.debugLine="Dim res1 As Int";
_res1 = 0;
 //BA.debugLineNum = 603;BA.debugLine="db1.Initialize";
_db1._initialize(ba);
 //BA.debugLineNum = 604;BA.debugLine="ls1 = db1.GetBookTopic";
_ls1 = _db1._getbooktopic();
 //BA.debugLineNum = 605;BA.debugLine="db1.Close";
_db1._close();
 //BA.debugLineNum = 607;BA.debugLine="If ls1.IsInitialized Then";
if (_ls1.IsInitialized()) { 
 //BA.debugLineNum = 608;BA.debugLine="res1 = fx.InputList(MainForm,ls1,\"موضوع را انتخا";
_res1 = _fx.InputList(_mainform,_ls1,"موضوع را انتخاب کنید","توجه",(int) (0));
 };
 //BA.debugLineNum = 611;BA.debugLine="If res1 < 0 Then Return";
if (_res1<0) { 
if (true) return "";};
 //BA.debugLineNum = 613;BA.debugLine="Dim title As String";
_title = "";
 //BA.debugLineNum = 614;BA.debugLine="title = ls1.Get(res1)";
_title = BA.ObjectToString(_ls1.Get(_res1));
 //BA.debugLineNum = 615;BA.debugLine="Downloads(\"sTitle\",title)";
_downloads("sTitle",_title);
 //BA.debugLineNum = 617;BA.debugLine="End Sub";
return "";
}
public static String  _mnulibrary_action() throws Exception{
 //BA.debugLineNum = 74;BA.debugLine="Sub mnulibrary_Action";
 //BA.debugLineNum = 75;BA.debugLine="actDownload.ShowDownload";
_actdownload._showdownload();
 //BA.debugLineNum = 76;BA.debugLine="End Sub";
return "";
}
public static String  _mnumessage_action() throws Exception{
 //BA.debugLineNum = 486;BA.debugLine="Sub mnumessage_Action";
 //BA.debugLineNum = 487;BA.debugLine="actMessage.ShowMessage";
_actmessage._showmessage();
 //BA.debugLineNum = 488;BA.debugLine="End Sub";
return "";
}
public static String  _mnunewsletter_action() throws Exception{
b4j.example.dialogs8 _in1 = null;
String _res = "";
 //BA.debugLineNum = 641;BA.debugLine="Sub mnunewsletter_Action";
 //BA.debugLineNum = 643;BA.debugLine="Dim in1 As Dialogs8";
_in1 = new b4j.example.dialogs8();
 //BA.debugLineNum = 644;BA.debugLine="Dim res As String";
_res = "";
 //BA.debugLineNum = 646;BA.debugLine="in1.Initialize";
_in1._initialize(ba);
 //BA.debugLineNum = 647;BA.debugLine="res = in1.TextInputDialog(\"توجه\",\"لطفا ایمیل خود";
_res = _in1._textinputdialog("توجه","لطفا ایمیل خود را برای عضویت در خبرنامه وارد کنید","","");
 //BA.debugLineNum = 649;BA.debugLine="If res.IndexOf(\"@\") > -1 Then";
if (_res.indexOf("@")>-1) { 
 //BA.debugLineNum = 650;BA.debugLine="NewsLetter(res)";
_newsletter(_res);
 }else {
 //BA.debugLineNum = 652;BA.debugLine="fx.Msgbox(MainForm,\"لطفا ایمیل را معتبر وارد کن";
_fx.Msgbox(_mainform,"لطفا ایمیل را معتبر وارد کنید","توجه");
 };
 //BA.debugLineNum = 655;BA.debugLine="End Sub";
return "";
}
public static String  _mnusearch_action() throws Exception{
String _search = "";
b4j.example.dialogs8 _dialog = null;
b4j.example.categories _dbres = null;
anywheresoftware.b4a.objects.collections.List _ls = null;
 //BA.debugLineNum = 490;BA.debugLine="Sub mnusearch_Action";
 //BA.debugLineNum = 492;BA.debugLine="Dim search As String";
_search = "";
 //BA.debugLineNum = 493;BA.debugLine="Dim dialog As Dialogs8";
_dialog = new b4j.example.dialogs8();
 //BA.debugLineNum = 495;BA.debugLine="dialog.Initialize";
_dialog._initialize(ba);
 //BA.debugLineNum = 496;BA.debugLine="search = dialog.TextInputDialog(\"جستجوی\",\"لطفا قس";
_search = _dialog._textinputdialog("جستجوی","لطفا قسمتی از نام کتاب را وارد کنید","","اینجا");
 //BA.debugLineNum = 498;BA.debugLine="If search.Length = 0 Then";
if (_search.length()==0) { 
 //BA.debugLineNum = 499;BA.debugLine="dialog.WarningDialog(\"توجه\",\"\",\"لطفا قسمتی از عب";
_dialog._warningdialog("توجه","","لطفا قسمتی از عبارت را وارد کنید");
 //BA.debugLineNum = 500;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 503;BA.debugLine="Dim DBRes As Categories";
_dbres = new b4j.example.categories();
 //BA.debugLineNum = 504;BA.debugLine="Dim ls As List";
_ls = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 506;BA.debugLine="DBRes.Initialize";
_dbres._initialize(ba);
 //BA.debugLineNum = 507;BA.debugLine="ls = DBRes.SearchWordBooks(search)";
_ls = _dbres._searchwordbooks(_search);
 //BA.debugLineNum = 508;BA.debugLine="DBRes.Close";
_dbres._close();
 //BA.debugLineNum = 510;BA.debugLine="If ls.IsInitialized Then SearchBook(ls)";
if (_ls.IsInitialized()) { 
_searchbook(_ls);};
 //BA.debugLineNum = 512;BA.debugLine="End Sub";
return "";
}
public static String  _mnusearchbook_action() throws Exception{
 //BA.debugLineNum = 671;BA.debugLine="Sub mnusearchbook_Action";
 //BA.debugLineNum = 672;BA.debugLine="actSearchBook.ShowSearchBook";
_actsearchbook._showsearchbook();
 //BA.debugLineNum = 673;BA.debugLine="End Sub";
return "";
}
public static String  _mnusetting_action() throws Exception{
 //BA.debugLineNum = 667;BA.debugLine="Sub mnusetting_Action";
 //BA.debugLineNum = 668;BA.debugLine="actSetting.ShowForm";
_actsetting._showform();
 //BA.debugLineNum = 669;BA.debugLine="End Sub";
return "";
}
public static String  _mnusites_action() throws Exception{
 //BA.debugLineNum = 628;BA.debugLine="Sub mnusites_Action";
 //BA.debugLineNum = 629;BA.debugLine="actSiteSocial.sType = \"site\"";
_actsitesocial._stype = "site";
 //BA.debugLineNum = 630;BA.debugLine="actSiteSocial.ShowList";
_actsitesocial._showlist();
 //BA.debugLineNum = 631;BA.debugLine="End Sub";
return "";
}
public static String  _mnusocial_action() throws Exception{
 //BA.debugLineNum = 623;BA.debugLine="Sub mnusocial_Action";
 //BA.debugLineNum = 624;BA.debugLine="actSiteSocial.sType = \"link\"";
_actsitesocial._stype = "link";
 //BA.debugLineNum = 625;BA.debugLine="actSiteSocial.ShowList";
_actsitesocial._showlist();
 //BA.debugLineNum = 626;BA.debugLine="End Sub";
return "";
}
public static String  _newsletter(String _email) throws Exception{
b4j.example.httpjob _hu = null;
 //BA.debugLineNum = 657;BA.debugLine="Sub NewsLetter(Email As String)";
 //BA.debugLineNum = 659;BA.debugLine="loading.Show(\"در حال بررسی\")";
_loading._show("در حال بررسی");
 //BA.debugLineNum = 661;BA.debugLine="Dim hu As HttpJob";
_hu = new b4j.example.httpjob();
 //BA.debugLineNum = 662;BA.debugLine="hu.Initialize(\"newsletter\",Me)";
_hu._initialize(ba,"newsletter",main.getObject());
 //BA.debugLineNum = 663;BA.debugLine="hu.PostString(Library.URL & \"newsletter\",\"email=\"";
_hu._poststring(_library._url+"newsletter","email="+_email);
 //BA.debugLineNum = 665;BA.debugLine="End Sub";
return "";
}

private static boolean processGlobalsRun;
public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        b4j.example.httputils2service._process_globals();
main._process_globals();
actbookmenu._process_globals();
actcontent._process_globals();
actdownload._process_globals();
actsitesocial._process_globals();
library._process_globals();
actmessage._process_globals();
actsendpoint._process_globals();
actpoints._process_globals();
actabout._process_globals();
actcontact._process_globals();
dbutils._process_globals();
actbookinformation._process_globals();
actsetting._process_globals();
actsearchbook._process_globals();
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 7;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 8;BA.debugLine="Private loading As Loading";
_loading = new b4j.example.loading();
 //BA.debugLineNum = 9;BA.debugLine="Public fx As JFX";
_fx = new anywheresoftware.b4j.objects.JFX();
 //BA.debugLineNum = 10;BA.debugLine="Public MainForm As Form";
_mainform = new anywheresoftware.b4j.objects.Form();
 //BA.debugLineNum = 11;BA.debugLine="Private tab1 As TabPane";
_tab1 = new anywheresoftware.b4j.objects.TabPaneWrapper();
 //BA.debugLineNum = 12;BA.debugLine="Private lvbooks As ListView";
_lvbooks = new anywheresoftware.b4j.objects.ListViewWrapper();
 //BA.debugLineNum = 13;BA.debugLine="Private img1 As ImageView";
_img1 = new anywheresoftware.b4j.objects.ImageViewWrapper();
 //BA.debugLineNum = 14;BA.debugLine="Private img2 As ImageView";
_img2 = new anywheresoftware.b4j.objects.ImageViewWrapper();
 //BA.debugLineNum = 15;BA.debugLine="Private img3 As ImageView";
_img3 = new anywheresoftware.b4j.objects.ImageViewWrapper();
 //BA.debugLineNum = 16;BA.debugLine="Private pnlitem As Pane";
_pnlitem = new anywheresoftware.b4j.objects.PaneWrapper.ConcretePaneWrapper();
 //BA.debugLineNum = 17;BA.debugLine="Private listTopBook As List";
_listtopbook = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 18;BA.debugLine="Private topMainPage As Int";
_topmainpage = 0;
 //BA.debugLineNum = 19;BA.debugLine="Private lvdownloads As ListView";
_lvdownloads = new anywheresoftware.b4j.objects.ListViewWrapper();
 //BA.debugLineNum = 20;BA.debugLine="Private MenuBar1 As MenuBar";
_menubar1 = new anywheresoftware.b4j.objects.MenuItemWrapper.MenuBarWrapper();
 //BA.debugLineNum = 21;BA.debugLine="Private btndeletebook1 As Button";
_btndeletebook1 = new anywheresoftware.b4j.objects.ButtonWrapper();
 //BA.debugLineNum = 22;BA.debugLine="Private btndeletebook2 As Button";
_btndeletebook2 = new anywheresoftware.b4j.objects.ButtonWrapper();
 //BA.debugLineNum = 23;BA.debugLine="Private btndeletebook3 As Button";
_btndeletebook3 = new anywheresoftware.b4j.objects.ButtonWrapper();
 //BA.debugLineNum = 24;BA.debugLine="End Sub";
return "";
}
public static String  _searchbook(anywheresoftware.b4a.objects.collections.List _ls) throws Exception{
int _i = 0;
anywheresoftware.b4j.objects.PaneWrapper.ConcretePaneWrapper _p = null;
 //BA.debugLineNum = 514;BA.debugLine="Sub SearchBook(ls As List)";
 //BA.debugLineNum = 516;BA.debugLine="If File.Exists(File.DirApp & \"/Data\" & \"/book\",\"\"";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data"+"/book","")==anywheresoftware.b4a.keywords.Common.False) { 
if (true) return "";};
 //BA.debugLineNum = 518;BA.debugLine="If ls.Size = 0 Then";
if (_ls.getSize()==0) { 
 //BA.debugLineNum = 519;BA.debugLine="lvdownloads.Items.Clear";
_lvdownloads.getItems().Clear();
 //BA.debugLineNum = 520;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 523;BA.debugLine="lvdownloads.Items.Clear";
_lvdownloads.getItems().Clear();
 //BA.debugLineNum = 525;BA.debugLine="For i = 0 To ls.Size - 1";
{
final int step7 = 1;
final int limit7 = (int) (_ls.getSize()-1);
for (_i = (int) (0) ; (step7 > 0 && _i <= limit7) || (step7 < 0 && _i >= limit7); _i = ((int)(0 + _i + step7)) ) {
 //BA.debugLineNum = 527;BA.debugLine="Dim p As Pane";
_p = new anywheresoftware.b4j.objects.PaneWrapper.ConcretePaneWrapper();
 //BA.debugLineNum = 528;BA.debugLine="p.Initialize(\"\")";
_p.Initialize(ba,"");
 //BA.debugLineNum = 529;BA.debugLine="p.LoadLayout(\"frmtemplate_box\")";
_p.LoadLayout(ba,"frmtemplate_box");
 //BA.debugLineNum = 530;BA.debugLine="p.SetSize(lvbooks.Width,200)";
_p.SetSize(_lvbooks.getWidth(),200);
 //BA.debugLineNum = 531;BA.debugLine="lvdownloads.Items.Add(p)";
_lvdownloads.getItems().Add((Object)(_p.getObject()));
 //BA.debugLineNum = 533;BA.debugLine="img1.Visible = True";
_img1.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 535;BA.debugLine="If File.Exists(File.DirApp & \"/Data\" & \"/book/\"";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data"+"/book/"+BA.ObjectToString(_ls.Get(_i)),"cover.jpg")) { 
 //BA.debugLineNum = 536;BA.debugLine="img1.SetImage(fx.LoadImage(File.DirApp & \"/Data";
_img1.SetImage((javafx.scene.image.Image)(_fx.LoadImage(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data"+"/book/"+BA.ObjectToString(_ls.Get(_i)),"cover.jpg").getObject()));
 }else {
 //BA.debugLineNum = 538;BA.debugLine="img1.SetImage(fx.LoadImage(File.DirAssets,\"no_c";
_img1.SetImage((javafx.scene.image.Image)(_fx.LoadImage(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"no_cover.png").getObject()));
 };
 //BA.debugLineNum = 541;BA.debugLine="img1.Tag = ls.Get(i)";
_img1.setTag(_ls.Get(_i));
 //BA.debugLineNum = 543;BA.debugLine="If i + 1 > ls.Size - 1 Then";
if (_i+1>_ls.getSize()-1) { 
 //BA.debugLineNum = 544;BA.debugLine="Exit";
if (true) break;
 };
 //BA.debugLineNum = 547;BA.debugLine="img2.Visible = True";
_img2.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 549;BA.debugLine="If File.Exists(File.DirApp & \"/Data\" & \"/book/\"";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data"+"/book/"+BA.ObjectToString(_ls.Get((int) (_i+1))),"cover.jpg")) { 
 //BA.debugLineNum = 550;BA.debugLine="img2.SetImage(fx.LoadImage(File.DirApp & \"/Data";
_img2.SetImage((javafx.scene.image.Image)(_fx.LoadImage(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data"+"/book/"+BA.ObjectToString(_ls.Get((int) (_i+1))),"cover.jpg").getObject()));
 }else {
 //BA.debugLineNum = 552;BA.debugLine="img2.SetImage(fx.LoadImage(File.DirAssets,\"no_c";
_img2.SetImage((javafx.scene.image.Image)(_fx.LoadImage(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"no_cover.png").getObject()));
 };
 //BA.debugLineNum = 555;BA.debugLine="img2.Tag = ls.Get(i+1)";
_img2.setTag(_ls.Get((int) (_i+1)));
 //BA.debugLineNum = 557;BA.debugLine="If i + 2 > ls.Size - 1 Then";
if (_i+2>_ls.getSize()-1) { 
 //BA.debugLineNum = 558;BA.debugLine="Exit";
if (true) break;
 };
 //BA.debugLineNum = 561;BA.debugLine="img3.Visible = True";
_img3.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 563;BA.debugLine="If File.Exists(File.DirApp & \"/Data\" & \"/book/\"";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data"+"/book/"+BA.ObjectToString(_ls.Get(_i)),"cover.jpg")) { 
 //BA.debugLineNum = 564;BA.debugLine="img1.SetImage(fx.LoadImage(File.DirApp & \"/Data";
_img1.SetImage((javafx.scene.image.Image)(_fx.LoadImage(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data"+"/book/"+BA.ObjectToString(_ls.Get(_i)),"cover.jpg").getObject()));
 }else {
 //BA.debugLineNum = 566;BA.debugLine="img1.SetImage(fx.LoadImage(File.DirAssets,\"no_c";
_img1.SetImage((javafx.scene.image.Image)(_fx.LoadImage(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"no_cover.png").getObject()));
 };
 //BA.debugLineNum = 569;BA.debugLine="img3.Tag = ls.Get(i+2)";
_img3.setTag(_ls.Get((int) (_i+2)));
 }
};
 //BA.debugLineNum = 573;BA.debugLine="End Sub";
return "";
}
public static String  _tab1_tabchanged(anywheresoftware.b4j.objects.TabPaneWrapper.TabWrapper _selectedtab) throws Exception{
anywheresoftware.b4j.objects.MenuItemWrapper.ConcreteMenuItemWrapper _mnu = null;
 //BA.debugLineNum = 471;BA.debugLine="Sub tab1_TabChanged (SelectedTab As TabPage)";
 //BA.debugLineNum = 473;BA.debugLine="Dim mnu As MenuItem";
_mnu = new anywheresoftware.b4j.objects.MenuItemWrapper.ConcreteMenuItemWrapper();
 //BA.debugLineNum = 474;BA.debugLine="mnu = MenuBar1.Menus.Get(1)";
_mnu.setObject((javafx.scene.control.MenuItem)(_menubar1.getMenus().Get((int) (1))));
 //BA.debugLineNum = 476;BA.debugLine="If SelectedTab.Tag = \"download\" Then";
if ((_selectedtab.getTag()).equals((Object)("download"))) { 
 //BA.debugLineNum = 477;BA.debugLine="mnu.Enabled = True";
_mnu.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 }else {
 //BA.debugLineNum = 479;BA.debugLine="mnu.Enabled = False";
_mnu.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 482;BA.debugLine="End Sub";
return "";
}
public static String  _topbooks() throws Exception{
anywheresoftware.b4a.objects.collections.Map _image = null;
anywheresoftware.b4j.objects.LabelWrapper _lb = null;
anywheresoftware.b4a.objects.collections.Map _images = null;
int _i = 0;
anywheresoftware.b4j.objects.PaneWrapper.ConcretePaneWrapper _p = null;
anywheresoftware.b4a.objects.collections.Map _m1 = null;
anywheresoftware.b4a.objects.collections.Map _m2 = null;
anywheresoftware.b4a.objects.collections.Map _m3 = null;
b4j.example.imagedownloader _im = null;
 //BA.debugLineNum = 294;BA.debugLine="Sub TopBooks";
 //BA.debugLineNum = 296;BA.debugLine="Dim image As Map";
_image = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 297;BA.debugLine="image.Initialize";
_image.Initialize();
 //BA.debugLineNum = 301;BA.debugLine="topMainPage = topMainPage + 20";
_topmainpage = (int) (_topmainpage+20);
 //BA.debugLineNum = 303;BA.debugLine="Dim lb As Label";
_lb = new anywheresoftware.b4j.objects.LabelWrapper();
 //BA.debugLineNum = 304;BA.debugLine="lb.Initialize(\"\")";
_lb.Initialize(ba,"");
 //BA.debugLineNum = 305;BA.debugLine="lb.TextColor = fx.Colors.Black";
_lb.setTextColor(_fx.Colors.Black);
 //BA.debugLineNum = 306;BA.debugLine="lb.Font = fx.LoadFont(File.DirAssets,\"byekan.ttf\"";
_lb.setFont(_fx.LoadFont(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"byekan.ttf",16));
 //BA.debugLineNum = 307;BA.debugLine="lb.Text = \"   کتاب های محبوب\"";
_lb.setText("   کتاب های محبوب");
 //BA.debugLineNum = 308;BA.debugLine="lvbooks.Items.Add(lb)";
_lvbooks.getItems().Add((Object)(_lb.getObject()));
 //BA.debugLineNum = 309;BA.debugLine="topMainPage = topMainPage + 30dip";
_topmainpage = (int) (_topmainpage+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30)));
 //BA.debugLineNum = 311;BA.debugLine="If listTopBook.IsInitialized = False Then Return";
if (_listtopbook.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
if (true) return "";};
 //BA.debugLineNum = 313;BA.debugLine="Dim images As Map";
_images = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 314;BA.debugLine="images.Initialize";
_images.Initialize();
 //BA.debugLineNum = 316;BA.debugLine="For i = 0 To listTopBook.Size - 1";
{
final int step14 = 1;
final int limit14 = (int) (_listtopbook.getSize()-1);
for (_i = (int) (0) ; (step14 > 0 && _i <= limit14) || (step14 < 0 && _i >= limit14); _i = ((int)(0 + _i + step14)) ) {
 //BA.debugLineNum = 318;BA.debugLine="If i >= 6 Then Exit";
if (_i>=6) { 
if (true) break;};
 //BA.debugLineNum = 320;BA.debugLine="Dim p As Pane";
_p = new anywheresoftware.b4j.objects.PaneWrapper.ConcretePaneWrapper();
 //BA.debugLineNum = 321;BA.debugLine="p.Initialize(\"\")";
_p.Initialize(ba,"");
 //BA.debugLineNum = 322;BA.debugLine="p.LoadLayout(\"frmtemplate_box\")";
_p.LoadLayout(ba,"frmtemplate_box");
 //BA.debugLineNum = 323;BA.debugLine="p.SetSize(lvbooks.Width,200)";
_p.SetSize(_lvbooks.getWidth(),200);
 //BA.debugLineNum = 324;BA.debugLine="lvbooks.Items.Add(p)";
_lvbooks.getItems().Add((Object)(_p.getObject()));
 //BA.debugLineNum = 326;BA.debugLine="Dim m1 As Map";
_m1 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 327;BA.debugLine="m1 = listTopBook.Get(i)";
_m1.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_listtopbook.Get(_i)));
 //BA.debugLineNum = 329;BA.debugLine="img1.Visible = True";
_img1.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 330;BA.debugLine="images.Put(img1,Library.BaseUrl & m1.Get(\"sCover";
_images.Put((Object)(_img1.getObject()),(Object)(_library._baseurl+BA.ObjectToString(_m1.Get((Object)("sCover")))));
 //BA.debugLineNum = 332;BA.debugLine="If File.Exists(File.DirApp & \"/Data\" & \"/book/\"";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data"+"/book/"+BA.ObjectToString(_m1.Get((Object)("sID"))),"bank.db")) { 
 //BA.debugLineNum = 333;BA.debugLine="img1.Tag = m1.Get(\"sID\")";
_img1.setTag(_m1.Get((Object)("sID")));
 }else {
 //BA.debugLineNum = 335;BA.debugLine="img1.Tag = m1";
_img1.setTag((Object)(_m1.getObject()));
 };
 //BA.debugLineNum = 338;BA.debugLine="If i + 1 > listTopBook.Size - 1 Then";
if (_i+1>_listtopbook.getSize()-1) { 
 //BA.debugLineNum = 339;BA.debugLine="topMainPage = topMainPage + p.Height + 10";
_topmainpage = (int) (_topmainpage+_p.getHeight()+10);
 //BA.debugLineNum = 340;BA.debugLine="Exit";
if (true) break;
 };
 //BA.debugLineNum = 343;BA.debugLine="img2.Visible = True";
_img2.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 345;BA.debugLine="Dim m2 As Map";
_m2 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 346;BA.debugLine="m2 = listTopBook.Get(i+1)";
_m2.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_listtopbook.Get((int) (_i+1))));
 //BA.debugLineNum = 348;BA.debugLine="images.Put(img2,Library.BaseUrl & m2.Get(\"sCover";
_images.Put((Object)(_img2.getObject()),(Object)(_library._baseurl+BA.ObjectToString(_m2.Get((Object)("sCover")))));
 //BA.debugLineNum = 350;BA.debugLine="If File.Exists(File.DirApp & \"/Data\" & \"/book/\"";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data"+"/book/"+BA.ObjectToString(_m2.Get((Object)("sID"))),"bank.db")) { 
 //BA.debugLineNum = 351;BA.debugLine="img2.Tag = m2.Get(\"sID\")";
_img2.setTag(_m2.Get((Object)("sID")));
 }else {
 //BA.debugLineNum = 353;BA.debugLine="img2.Tag = m2";
_img2.setTag((Object)(_m2.getObject()));
 };
 //BA.debugLineNum = 356;BA.debugLine="img2.Tag = listTopBook.Get(i+1)";
_img2.setTag(_listtopbook.Get((int) (_i+1)));
 //BA.debugLineNum = 358;BA.debugLine="If i + 2 > listTopBook.Size - 1 Then";
if (_i+2>_listtopbook.getSize()-1) { 
 //BA.debugLineNum = 359;BA.debugLine="topMainPage = topMainPage + p.Height + 10";
_topmainpage = (int) (_topmainpage+_p.getHeight()+10);
 //BA.debugLineNum = 360;BA.debugLine="Exit";
if (true) break;
 };
 //BA.debugLineNum = 363;BA.debugLine="img3.Visible = True";
_img3.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 365;BA.debugLine="Dim m3 As Map";
_m3 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 366;BA.debugLine="m3 = listTopBook.Get(i+2)";
_m3.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_listtopbook.Get((int) (_i+2))));
 //BA.debugLineNum = 367;BA.debugLine="images.Put(img3,Library.BaseUrl & m2.Get(\"sCover";
_images.Put((Object)(_img3.getObject()),(Object)(_library._baseurl+BA.ObjectToString(_m2.Get((Object)("sCover")))));
 //BA.debugLineNum = 369;BA.debugLine="If File.Exists(File.DirApp & \"/Data\" & \"/book/\"";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data"+"/book/"+BA.ObjectToString(_m3.Get((Object)("sID"))),"bank.db")) { 
 //BA.debugLineNum = 370;BA.debugLine="img3.Tag = m3.Get(\"sID\")";
_img3.setTag(_m3.Get((Object)("sID")));
 }else {
 //BA.debugLineNum = 372;BA.debugLine="img3.Tag = m3";
_img3.setTag((Object)(_m3.getObject()));
 };
 //BA.debugLineNum = 375;BA.debugLine="img3.Tag = listTopBook.Get(i+2)";
_img3.setTag(_listtopbook.Get((int) (_i+2)));
 }
};
 //BA.debugLineNum = 381;BA.debugLine="Dim im As ImageDownloader";
_im = new b4j.example.imagedownloader();
 //BA.debugLineNum = 382;BA.debugLine="im.Initialize";
_im._initialize(ba);
 //BA.debugLineNum = 383;BA.debugLine="im.Download(images)";
_im._download(_images);
 //BA.debugLineNum = 385;BA.debugLine="End Sub";
return "";
}
public static String  _unzipbook(String _id) throws Exception{
com.AB.ABZipUnzip.ABZipUnzip _zip = null;
 //BA.debugLineNum = 457;BA.debugLine="Sub UnzipBook(ID As String)";
 //BA.debugLineNum = 459;BA.debugLine="File.MakeDir(File.DirApp & \"/Data\",\"book/\" & ID)";
anywheresoftware.b4a.keywords.Common.File.MakeDir(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data","book/"+_id);
 //BA.debugLineNum = 461;BA.debugLine="Dim zip As ABZipUnzip";
_zip = new com.AB.ABZipUnzip.ABZipUnzip();
 //BA.debugLineNum = 462;BA.debugLine="File.Copy(File.DirAssets,\"book_\" & ID & \".zip\",Fi";
anywheresoftware.b4a.keywords.Common.File.Copy(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"book_"+_id+".zip",anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data","book_"+_id+".zip");
 //BA.debugLineNum = 463;BA.debugLine="Log(zip.ABUnzip(File.Combine(File.DirApp & \"/Data";
anywheresoftware.b4a.keywords.Common.Log(BA.ObjectToString(_zip.ABUnzip(anywheresoftware.b4a.keywords.Common.File.Combine(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data","book_"+_id+".zip"),anywheresoftware.b4a.keywords.Common.File.Combine(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data","book/"+_id))));
 //BA.debugLineNum = 464;BA.debugLine="File.Delete(File.DirApp & \"/Data\",\"book_\" & ID &";
anywheresoftware.b4a.keywords.Common.File.Delete(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data","book_"+_id+".zip");
 //BA.debugLineNum = 466;BA.debugLine="DateTime.DateFormat = \"yyyy-mm-dd\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("yyyy-mm-dd");
 //BA.debugLineNum = 467;BA.debugLine="File.WriteString(File.DirApp & \"/Data\" & \"/book/\"";
anywheresoftware.b4a.keywords.Common.File.WriteString(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data"+"/book/"+_id,"date",anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow()));
 //BA.debugLineNum = 469;BA.debugLine="End Sub";
return "";
}
}
