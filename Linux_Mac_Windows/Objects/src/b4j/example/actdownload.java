package b4j.example;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.debug.*;

public class actdownload extends Object{
public static actdownload mostCurrent = new actdownload();

public static BA ba;
static {
		ba = new  anywheresoftware.b4j.objects.FxBA("b4j.example", "b4j.example.actdownload", null);
		ba.loadHtSubs(actdownload.class);
        if (ba.getClass().getName().endsWith("ShellBA")) {
			
			ba.raiseEvent2(null, true, "SHELL", false);
			ba.raiseEvent2(null, true, "CREATE", true, "b4j.example.actdownload", ba);
		}
	}
    public static Class<?> getObject() {
		return actdownload.class;
	}

 public static anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4j.objects.JFX _fx = null;
public static anywheresoftware.b4j.objects.Form _frm1 = null;
public static anywheresoftware.b4j.objects.ListViewWrapper _lvbooks = null;
public static b4j.example.categories _dbcat = null;
public static b4j.example.categories _dbbook = null;
public static boolean _forceupdate = false;
public static anywheresoftware.b4j.objects.LabelWrapper _lblbookname = null;
public static anywheresoftware.b4j.objects.ImageViewWrapper _imgicon = null;
public static anywheresoftware.b4j.objects.PaneWrapper.ConcretePaneWrapper _pnlover = null;
public static String _filterlanguageval = "";
public static boolean _filterlangauge = false;
public static anywheresoftware.b4a.objects.collections.List _stack = null;
public static String _currentcatid = "";
public static String _currentfilechoosen = "";
public static anywheresoftware.b4j.objects.LabelWrapper _lblcatname = null;
public static anywheresoftware.b4j.objects.LabelWrapper _lblbookextra = null;
public static anywheresoftware.b4j.objects.ButtonWrapper _btndownload = null;
public static anywheresoftware.b4j.objects.LabelWrapper _lblsize = null;
public static anywheresoftware.b4j.objects.ImageViewWrapper _pb1 = null;
public static anywheresoftware.b4j.objects.ImageViewWrapper _currentpb = null;
public static anywheresoftware.b4j.objects.ButtonWrapper _currentdownloadlabel = null;
public static anywheresoftware.b4j.objects.PaneWrapper.ConcretePaneWrapper _pnloverbook = null;
public static b4j.example.loading _loading = null;
public static boolean _ischange = false;
public static b4j.example.httputils2service _httputils2service = null;
public static b4j.example.main _main = null;
public static b4j.example.actbookmenu _actbookmenu = null;
public static b4j.example.actcontent _actcontent = null;
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
public static String  _addbooks(anywheresoftware.b4a.objects.collections.List _list1) throws Exception{
int _i = 0;
anywheresoftware.b4a.objects.collections.Map _temp = null;
anywheresoftware.b4j.objects.PaneWrapper.ConcretePaneWrapper _p1 = null;
 //BA.debugLineNum = 110;BA.debugLine="Sub AddBooks(List1 As List)";
 //BA.debugLineNum = 112;BA.debugLine="For i = 0 To List1.Size - 1";
{
final int step1 = 1;
final int limit1 = (int) (_list1.getSize()-1);
for (_i = (int) (0) ; (step1 > 0 && _i <= limit1) || (step1 < 0 && _i >= limit1); _i = ((int)(0 + _i + step1)) ) {
 //BA.debugLineNum = 114;BA.debugLine="Dim temp As Map";
_temp = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 115;BA.debugLine="temp = List1.Get(i)";
_temp.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_list1.Get(_i)));
 //BA.debugLineNum = 117;BA.debugLine="Dim p1 As Pane";
_p1 = new anywheresoftware.b4j.objects.PaneWrapper.ConcretePaneWrapper();
 //BA.debugLineNum = 118;BA.debugLine="p1.Initialize(\"\")";
_p1.Initialize(ba,"");
 //BA.debugLineNum = 119;BA.debugLine="p1.LoadLayout(\"frmbook_template\")";
_p1.LoadLayout(ba,"frmbook_template");
 //BA.debugLineNum = 120;BA.debugLine="p1.SetSize(400,120)";
_p1.SetSize(400,120);
 //BA.debugLineNum = 121;BA.debugLine="lvbooks.Items.Add(p1)";
_lvbooks.getItems().Add((Object)(_p1.getObject()));
 //BA.debugLineNum = 123;BA.debugLine="lblbookname.Text = temp.Get(\"sTitle\")";
_lblbookname.setText(BA.ObjectToString(_temp.Get((Object)("sTitle"))));
 //BA.debugLineNum = 124;BA.debugLine="lblbookextra.Text = temp.Get(\"sAuthor\") & \" تعدا";
_lblbookextra.setText(BA.ObjectToString(_temp.Get((Object)("sAuthor")))+" تعداد دانلود : "+BA.ObjectToString(_temp.Get((Object)("sDownload"))));
 //BA.debugLineNum = 125;BA.debugLine="lblsize.Text = Library.FormatSize(temp.Get(\"sFil";
_lblsize.setText(_library._formatsize((float)(BA.ObjectToNumber(_temp.Get((Object)("sFileSize"))))));
 //BA.debugLineNum = 126;BA.debugLine="pnloverbook.Tag = temp";
_pnloverbook.setTag((Object)(_temp.getObject()));
 //BA.debugLineNum = 127;BA.debugLine="btndownload.Tag = temp.Get(\"sID\")";
_btndownload.setTag(_temp.Get((Object)("sID")));
 //BA.debugLineNum = 128;BA.debugLine="lblbookname.Tag = pb1";
_lblbookname.setTag((Object)(_pb1.getObject()));
 //BA.debugLineNum = 130;BA.debugLine="If File.Exists(File.DirApp & \"/Data\" & \"/book/\"";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data"+"/book/"+BA.ObjectToString(_temp.Get((Object)("sID"))),"bank.db")) { 
 //BA.debugLineNum = 131;BA.debugLine="btndownload.Text = \"\"";
_btndownload.setText("");
 //BA.debugLineNum = 132;BA.debugLine="btndownload.Font = fx.LoadFont(File.DirAssets,\"";
_btndownload.setFont(_fx.LoadFont(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"icomoon.ttf",14));
 //BA.debugLineNum = 133;BA.debugLine="btndownload.TextColor = fx.Colors.Green";
_btndownload.setTextColor(_fx.Colors.Green);
 };
 }
};
 //BA.debugLineNum = 138;BA.debugLine="End Sub";
return "";
}
public static String  _addcategories(anywheresoftware.b4a.objects.collections.List _list1) throws Exception{
int _i = 0;
anywheresoftware.b4j.objects.PaneWrapper.ConcretePaneWrapper _p1 = null;
anywheresoftware.b4a.objects.collections.Map _temp = null;
 //BA.debugLineNum = 90;BA.debugLine="Sub AddCategories(List1 As List)";
 //BA.debugLineNum = 92;BA.debugLine="For i = 0 To List1.Size - 1";
{
final int step1 = 1;
final int limit1 = (int) (_list1.getSize()-1);
for (_i = (int) (0) ; (step1 > 0 && _i <= limit1) || (step1 < 0 && _i >= limit1); _i = ((int)(0 + _i + step1)) ) {
 //BA.debugLineNum = 93;BA.debugLine="Dim p1 As Pane";
_p1 = new anywheresoftware.b4j.objects.PaneWrapper.ConcretePaneWrapper();
 //BA.debugLineNum = 94;BA.debugLine="p1.Initialize(\"\")";
_p1.Initialize(ba,"");
 //BA.debugLineNum = 95;BA.debugLine="p1.LoadLayout(\"frmtemplate_category\")";
_p1.LoadLayout(ba,"frmtemplate_category");
 //BA.debugLineNum = 96;BA.debugLine="p1.SetSize(400,60)";
_p1.SetSize(400,60);
 //BA.debugLineNum = 97;BA.debugLine="lvbooks.Items.Add(p1)";
_lvbooks.getItems().Add((Object)(_p1.getObject()));
 //BA.debugLineNum = 99;BA.debugLine="Dim temp As Map";
_temp = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 100;BA.debugLine="temp = List1.Get(i)";
_temp.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_list1.Get(_i)));
 //BA.debugLineNum = 102;BA.debugLine="lblcatname.Text = temp.Get(\"sTitle\")";
_lblcatname.setText(BA.ObjectToString(_temp.Get((Object)("sTitle"))));
 //BA.debugLineNum = 104;BA.debugLine="pnlover.Tag = temp";
_pnlover.setTag((Object)(_temp.getObject()));
 }
};
 //BA.debugLineNum = 108;BA.debugLine="End Sub";
return "";
}
public static String  _btndownload_action() throws Exception{
anywheresoftware.b4j.objects.ButtonWrapper _b = null;
String _sid = "";
b4j.example.httpjob _hu = null;
 //BA.debugLineNum = 332;BA.debugLine="Sub btndownload_Action";
 //BA.debugLineNum = 334;BA.debugLine="Dim b As Button";
_b = new anywheresoftware.b4j.objects.ButtonWrapper();
 //BA.debugLineNum = 335;BA.debugLine="b = Sender";
_b.setObject((javafx.scene.control.Button)(anywheresoftware.b4a.keywords.Common.Sender(ba)));
 //BA.debugLineNum = 336;BA.debugLine="Dim sID As String";
_sid = "";
 //BA.debugLineNum = 337;BA.debugLine="sID = b.Tag";
_sid = BA.ObjectToString(_b.getTag());
 //BA.debugLineNum = 339;BA.debugLine="If File.Exists(File.DirApp & \"/Data\" & \"/book/\" &";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data"+"/book/"+_sid,"bank.db")) { 
 //BA.debugLineNum = 340;BA.debugLine="fx.Msgbox(frm1,\"این کتاب قبلا دانلود شده است.میت";
_fx.Msgbox(_frm1,"این کتاب قبلا دانلود شده است.میتوانید کتاب را مرور کنید","توجه");
 //BA.debugLineNum = 341;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 344;BA.debugLine="currentFileChoosen = sID";
_currentfilechoosen = _sid;
 //BA.debugLineNum = 346;BA.debugLine="Dim hu As HttpJob";
_hu = new b4j.example.httpjob();
 //BA.debugLineNum = 347;BA.debugLine="hu.Initialize(sID,Me)";
_hu._initialize(ba,_sid,actdownload.getObject());
 //BA.debugLineNum = 349;BA.debugLine="pb1.Visible = True";
_pb1.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 350;BA.debugLine="currentPB = pb1";
_currentpb = _pb1;
 //BA.debugLineNum = 351;BA.debugLine="CurrentDownloadLabel = b";
_currentdownloadlabel = _b;
 //BA.debugLineNum = 353;BA.debugLine="b.Visible = False";
_b.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 355;BA.debugLine="hu.Download(Library.URL.Replace(\"index.php/server";
_hu._download(_library._url.replace("index.php/server/","/")+"books/book_"+_sid+".zip");
 //BA.debugLineNum = 357;BA.debugLine="End Sub";
return "";
}
public static String  _downloadbookfromnet(String _sid) throws Exception{
b4j.example.httpjob _hu = null;
 //BA.debugLineNum = 359;BA.debugLine="Sub DownloadBookFromNet(sID As String)";
 //BA.debugLineNum = 361;BA.debugLine="currentFileChoosen = sID";
_currentfilechoosen = _sid;
 //BA.debugLineNum = 363;BA.debugLine="loading.Show(\"در حال دانلود\")";
_loading._show("در حال دانلود");
 //BA.debugLineNum = 365;BA.debugLine="Dim hu As HttpJob";
_hu = new b4j.example.httpjob();
 //BA.debugLineNum = 366;BA.debugLine="hu.Initialize(sID,Me)";
_hu._initialize(ba,_sid,actdownload.getObject());
 //BA.debugLineNum = 367;BA.debugLine="hu.Download(Library.URL.Replace(\"index.php/server";
_hu._download(_library._url.replace("index.php/server/","/")+"books/book_"+_sid+".zip");
 //BA.debugLineNum = 369;BA.debugLine="End Sub";
return "";
}
public static String  _frm1_closerequest(anywheresoftware.b4j.objects.NodeWrapper.ConcreteEventWrapper _eventdata) throws Exception{
 //BA.debugLineNum = 371;BA.debugLine="Sub frm1_CloseRequest (EventData As Event)";
 //BA.debugLineNum = 373;BA.debugLine="If IsChange Then";
if (_ischange) { 
 //BA.debugLineNum = 374;BA.debugLine="IsChange = False";
_ischange = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 375;BA.debugLine="CallSubDelayed(Main,\"LoadItem\")";
anywheresoftware.b4a.keywords.Common.CallSubDelayed(ba,(Object)(_main.getObject()),"LoadItem");
 };
 //BA.debugLineNum = 378;BA.debugLine="End Sub";
return "";
}
public static String  _jobdone(b4j.example.httpjob _job) throws Exception{
anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper _ou = null;
com.AB.ABZipUnzip.ABZipUnzip _zip = null;
b4j.example.httpjob _hu = null;
b4j.example.categories _db1 = null;
anywheresoftware.b4j.objects.collections.JSONParser _js = null;
anywheresoftware.b4a.objects.collections.Map _res = null;
anywheresoftware.b4a.objects.collections.List _ls = null;
 //BA.debugLineNum = 140;BA.debugLine="Sub JobDone(Job As HttpJob)";
 //BA.debugLineNum = 142;BA.debugLine="loading.Hide";
_loading._hide();
 //BA.debugLineNum = 143;BA.debugLine="frm1.RootPane.Enabled = True";
_frm1.getRootPane().setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 145;BA.debugLine="If Job.Success Then";
if (_job._success) { 
 //BA.debugLineNum = 147;BA.debugLine="If IsNumber(Job.JobName) Then";
if (anywheresoftware.b4a.keywords.Common.IsNumber(_job._jobname)) { 
 //BA.debugLineNum = 149;BA.debugLine="Try";
try { //BA.debugLineNum = 150;BA.debugLine="CurrentDownloadLabel.Visible = True";
_currentdownloadlabel.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 151;BA.debugLine="currentPB.Visible = False";
_currentpb.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 152;BA.debugLine="currentPB.SetImage(fx.LoadImage(File.DirAssets";
_currentpb.SetImage((javafx.scene.image.Image)(_fx.LoadImage(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"ok.png").getObject()));
 //BA.debugLineNum = 153;BA.debugLine="CurrentDownloadLabel.Text = \"\"";
_currentdownloadlabel.setText("");
 //BA.debugLineNum = 154;BA.debugLine="btndownload.Font = fx.LoadFont(File.DirAssets,";
_btndownload.setFont(_fx.LoadFont(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"icomoon.ttf",14));
 //BA.debugLineNum = 155;BA.debugLine="CurrentDownloadLabel.TextColor = fx.Colors.Gre";
_currentdownloadlabel.setTextColor(_fx.Colors.Green);
 } 
       catch (Exception e13) {
			ba.setLastException(e13); };
 //BA.debugLineNum = 159;BA.debugLine="Dim ou As OutputStream";
_ou = new anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper();
 //BA.debugLineNum = 160;BA.debugLine="ou = File.OpenOutput(File.DirApp & \"/Data\",\"tem";
_ou = anywheresoftware.b4a.keywords.Common.File.OpenOutput(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data","temp.zip",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 161;BA.debugLine="File.Copy2(Job.GetInputStream,ou)";
anywheresoftware.b4a.keywords.Common.File.Copy2((java.io.InputStream)(_job._getinputstream().getObject()),(java.io.OutputStream)(_ou.getObject()));
 //BA.debugLineNum = 162;BA.debugLine="ou.Close";
_ou.Close();
 //BA.debugLineNum = 164;BA.debugLine="File.MakeDir(File.DirApp & \"/Data/book\",Job.Job";
anywheresoftware.b4a.keywords.Common.File.MakeDir(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data/book",_job._jobname);
 //BA.debugLineNum = 165;BA.debugLine="Dim zip As ABZipUnzip";
_zip = new com.AB.ABZipUnzip.ABZipUnzip();
 //BA.debugLineNum = 166;BA.debugLine="zip.ABUnzip(File.Combine(File.DirApp & \"/Data\",";
_zip.ABUnzip(anywheresoftware.b4a.keywords.Common.File.Combine(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data","temp.zip"),anywheresoftware.b4a.keywords.Common.File.Combine(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data","book/"+_job._jobname));
 //BA.debugLineNum = 167;BA.debugLine="DateTime.DateFormat = \"yyyy-mm-dd\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("yyyy-mm-dd");
 //BA.debugLineNum = 168;BA.debugLine="File.WriteString(File.DirApp & \"/Data/book/\" &";
anywheresoftware.b4a.keywords.Common.File.WriteString(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data/book/"+_job._jobname,"date",anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow()));
 //BA.debugLineNum = 169;BA.debugLine="File.Delete(File.DirApp & \"/Data\",\"temp.zip\")";
anywheresoftware.b4a.keywords.Common.File.Delete(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data","temp.zip");
 //BA.debugLineNum = 171;BA.debugLine="Dim hu As HttpJob";
_hu = new b4j.example.httpjob();
 //BA.debugLineNum = 172;BA.debugLine="hu.Initialize(\"modifybook\",Me)";
_hu._initialize(ba,"modifybook",actdownload.getObject());
 //BA.debugLineNum = 173;BA.debugLine="hu.PostString(Library.URL & \"add_download/\" & c";
_hu._poststring(_library._url+"add_download/"+_currentfilechoosen,"");
 //BA.debugLineNum = 175;BA.debugLine="IsChange = True";
_ischange = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 177;BA.debugLine="fx.Msgbox(frm1,\"کتاب با موفقیت دانلود شد\",\"توجه";
_fx.Msgbox(_frm1,"کتاب با موفقیت دانلود شد","توجه");
 //BA.debugLineNum = 179;BA.debugLine="If File.Exists(File.DirApp & \"/Data\",\"visit_boo";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data","visit_book1")==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 180;BA.debugLine="Library.WriteCounterReadBook(Job.JobName)";
_library._writecounterreadbook(_job._jobname);
 };
 //BA.debugLineNum = 183;BA.debugLine="Dim db1 As Categories";
_db1 = new b4j.example.categories();
 //BA.debugLineNum = 184;BA.debugLine="db1.Initialize";
_db1._initialize(ba);
 //BA.debugLineNum = 185;BA.debugLine="db1.DownloadedBook(currentFileChoosen,\"1\")";
_db1._downloadedbook(_currentfilechoosen,(int)(Double.parseDouble("1")));
 }else if((_job._jobname).equals("modifybook")) { 
 //BA.debugLineNum = 188;BA.debugLine="Dim js As JSONParser";
_js = new anywheresoftware.b4j.objects.collections.JSONParser();
 //BA.debugLineNum = 189;BA.debugLine="js.Initialize(Job.GetString)";
_js.Initialize(_job._getstring());
 //BA.debugLineNum = 190;BA.debugLine="Dim res As Map = js.NextObject";
_res = new anywheresoftware.b4a.objects.collections.Map();
_res = _js.NextObject();
 //BA.debugLineNum = 191;BA.debugLine="If res.IsInitialized Then";
if (_res.IsInitialized()) { 
 //BA.debugLineNum = 192;BA.debugLine="Log(res)";
anywheresoftware.b4a.keywords.Common.Log(BA.ObjectToString(_res));
 };
 }else if((_job._jobname).equals("cat")) { 
 //BA.debugLineNum = 196;BA.debugLine="Dim js As JSONParser";
_js = new anywheresoftware.b4j.objects.collections.JSONParser();
 //BA.debugLineNum = 197;BA.debugLine="js.Initialize(Job.GetString)";
_js.Initialize(_job._getstring());
 //BA.debugLineNum = 198;BA.debugLine="Dim res As Map = js.NextObject";
_res = new anywheresoftware.b4a.objects.collections.Map();
_res = _js.NextObject();
 //BA.debugLineNum = 199;BA.debugLine="If res.IsInitialized Then";
if (_res.IsInitialized()) { 
 //BA.debugLineNum = 200;BA.debugLine="If res.Get(\"success\") = True Then";
if ((_res.Get((Object)("success"))).equals((Object)(anywheresoftware.b4a.keywords.Common.True))) { 
 //BA.debugLineNum = 201;BA.debugLine="Dim ls As List";
_ls = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 202;BA.debugLine="ls = res.Get(\"result\")";
_ls.setObject((java.util.List)(_res.Get((Object)("result"))));
 //BA.debugLineNum = 203;BA.debugLine="dbCat.AddCategory(ls)";
_dbcat._addcategory(_ls);
 //BA.debugLineNum = 205;BA.debugLine="frm1.RootPane.Enabled = False";
_frm1.getRootPane().setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 206;BA.debugLine="Dim hu As HttpJob";
_hu = new b4j.example.httpjob();
 //BA.debugLineNum = 207;BA.debugLine="hu.Initialize(\"book\",Me)";
_hu._initialize(ba,"book",actdownload.getObject());
 //BA.debugLineNum = 208;BA.debugLine="hu.PostString(Library.URL & \"list_book\",\"\")";
_hu._poststring(_library._url+"list_book","");
 };
 };
 }else if((_job._jobname).equals("book")) { 
 //BA.debugLineNum = 213;BA.debugLine="Dim js As JSONParser";
_js = new anywheresoftware.b4j.objects.collections.JSONParser();
 //BA.debugLineNum = 214;BA.debugLine="js.Initialize(Job.GetString)";
_js.Initialize(_job._getstring());
 //BA.debugLineNum = 215;BA.debugLine="Dim res As Map = js.NextObject";
_res = new anywheresoftware.b4a.objects.collections.Map();
_res = _js.NextObject();
 //BA.debugLineNum = 216;BA.debugLine="If res.IsInitialized Then";
if (_res.IsInitialized()) { 
 //BA.debugLineNum = 217;BA.debugLine="If res.Get(\"success\") = True Then";
if ((_res.Get((Object)("success"))).equals((Object)(anywheresoftware.b4a.keywords.Common.True))) { 
 //BA.debugLineNum = 218;BA.debugLine="Dim ls As List";
_ls = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 219;BA.debugLine="ls = res.Get(\"result\")";
_ls.setObject((java.util.List)(_res.Get((Object)("result"))));
 //BA.debugLineNum = 220;BA.debugLine="dbBook.Initialize";
_dbbook._initialize(ba);
 //BA.debugLineNum = 221;BA.debugLine="dbBook.AddBook(ls)";
_dbbook._addbook(_ls);
 //BA.debugLineNum = 222;BA.debugLine="LoadItem";
_loaditem();
 };
 };
 };
 }else {
 //BA.debugLineNum = 228;BA.debugLine="Log(Job.ErrorMessage)";
anywheresoftware.b4a.keywords.Common.Log(_job._errormessage);
 };
 //BA.debugLineNum = 231;BA.debugLine="End Sub";
return "";
}
public static String  _loadcategories(String _sid) throws Exception{
anywheresoftware.b4j.objects.PaneWrapper.ConcretePaneWrapper _p1 = null;
anywheresoftware.b4a.objects.collections.Map _temp = null;
 //BA.debugLineNum = 63;BA.debugLine="Sub LoadCategories(sID As String)";
 //BA.debugLineNum = 65;BA.debugLine="lvbooks.Items.Clear";
_lvbooks.getItems().Clear();
 //BA.debugLineNum = 67;BA.debugLine="If stack.Size > 0 Then";
if (_stack.getSize()>0) { 
 //BA.debugLineNum = 68;BA.debugLine="Dim p1 As Pane";
_p1 = new anywheresoftware.b4j.objects.PaneWrapper.ConcretePaneWrapper();
 //BA.debugLineNum = 69;BA.debugLine="p1.Initialize(\"\")";
_p1.Initialize(ba,"");
 //BA.debugLineNum = 70;BA.debugLine="p1.LoadLayout(\"frmtemplate_category\")";
_p1.LoadLayout(ba,"frmtemplate_category");
 //BA.debugLineNum = 71;BA.debugLine="p1.SetSize(350,60)";
_p1.SetSize(350,60);
 //BA.debugLineNum = 72;BA.debugLine="lvbooks.Items.Add(p1)";
_lvbooks.getItems().Add((Object)(_p1.getObject()));
 //BA.debugLineNum = 74;BA.debugLine="Dim temp As Map";
_temp = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 75;BA.debugLine="temp = stack.Get(stack.Size-1)";
_temp.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_stack.Get((int) (_stack.getSize()-1))));
 //BA.debugLineNum = 77;BA.debugLine="lblcatname.Text = \"برگشت به دسته بندی قبلی\"";
_lblcatname.setText("برگشت به دسته بندی قبلی");
 //BA.debugLineNum = 78;BA.debugLine="temp.Put(\"state\",\"return\")";
_temp.Put((Object)("state"),(Object)("return"));
 //BA.debugLineNum = 79;BA.debugLine="pnlover.Tag = temp";
_pnlover.setTag((Object)(_temp.getObject()));
 };
 //BA.debugLineNum = 83;BA.debugLine="currentCatID = sID";
_currentcatid = _sid;
 //BA.debugLineNum = 85;BA.debugLine="AddCategories(dbCat.GetCategory(currentCatID))";
_addcategories(_dbcat._getcategory((int)(Double.parseDouble(_currentcatid))));
 //BA.debugLineNum = 86;BA.debugLine="AddBooks(dbCat.GetBook(currentCatID,FilterLanguag";
_addbooks(_dbcat._getbook((int)(Double.parseDouble(_currentcatid)),_filterlanguageval,_filterlangauge));
 //BA.debugLineNum = 88;BA.debugLine="End Sub";
return "";
}
public static String  _loaditem() throws Exception{
 //BA.debugLineNum = 58;BA.debugLine="Sub LoadItem";
 //BA.debugLineNum = 59;BA.debugLine="AddCategories(dbCat.GetCategory(0))";
_addcategories(_dbcat._getcategory((int) (0)));
 //BA.debugLineNum = 60;BA.debugLine="AddBooks(dbCat.GetBook(0,FilterLanguageVal,Filter";
_addbooks(_dbcat._getbook((int) (0),_filterlanguageval,_filterlangauge));
 //BA.debugLineNum = 61;BA.debugLine="End Sub";
return "";
}
public static String  _mnuall_action() throws Exception{
 //BA.debugLineNum = 278;BA.debugLine="Sub mnuall_Action";
 //BA.debugLineNum = 280;BA.debugLine="lvbooks.Items.Clear";
_lvbooks.getItems().Clear();
 //BA.debugLineNum = 281;BA.debugLine="FilterLangauge = False";
_filterlangauge = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 282;BA.debugLine="FilterLanguageVal = \"\"";
_filterlanguageval = "";
 //BA.debugLineNum = 283;BA.debugLine="stack.Clear";
_stack.Clear();
 //BA.debugLineNum = 285;BA.debugLine="LoadItem";
_loaditem();
 //BA.debugLineNum = 287;BA.debugLine="End Sub";
return "";
}
public static String  _mnuar_action() throws Exception{
 //BA.debugLineNum = 267;BA.debugLine="Sub mnuar_Action";
 //BA.debugLineNum = 269;BA.debugLine="lvbooks.Items.Clear";
_lvbooks.getItems().Clear();
 //BA.debugLineNum = 270;BA.debugLine="stack.Clear";
_stack.Clear();
 //BA.debugLineNum = 271;BA.debugLine="FilterLangauge = True";
_filterlangauge = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 272;BA.debugLine="FilterLanguageVal = \"ar\"";
_filterlanguageval = "ar";
 //BA.debugLineNum = 274;BA.debugLine="LoadItem";
_loaditem();
 //BA.debugLineNum = 276;BA.debugLine="End Sub";
return "";
}
public static String  _mnuen_action() throws Exception{
 //BA.debugLineNum = 256;BA.debugLine="Sub mnuen_Action";
 //BA.debugLineNum = 258;BA.debugLine="lvbooks.Items.Clear";
_lvbooks.getItems().Clear();
 //BA.debugLineNum = 259;BA.debugLine="stack.Clear";
_stack.Clear();
 //BA.debugLineNum = 260;BA.debugLine="FilterLangauge = True";
_filterlangauge = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 261;BA.debugLine="FilterLanguageVal = \"en\"";
_filterlanguageval = "en";
 //BA.debugLineNum = 263;BA.debugLine="LoadItem";
_loaditem();
 //BA.debugLineNum = 265;BA.debugLine="End Sub";
return "";
}
public static String  _mnufa_action() throws Exception{
 //BA.debugLineNum = 245;BA.debugLine="Sub mnufa_Action";
 //BA.debugLineNum = 247;BA.debugLine="lvbooks.Items.Clear";
_lvbooks.getItems().Clear();
 //BA.debugLineNum = 248;BA.debugLine="stack.Clear";
_stack.Clear();
 //BA.debugLineNum = 249;BA.debugLine="FilterLangauge = True";
_filterlangauge = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 250;BA.debugLine="FilterLanguageVal = \"fa\"";
_filterlanguageval = "fa";
 //BA.debugLineNum = 252;BA.debugLine="LoadItem";
_loaditem();
 //BA.debugLineNum = 254;BA.debugLine="End Sub";
return "";
}
public static String  _mnusearch_action() throws Exception{
String _search = "";
b4j.example.dialogs8 _dialog = null;
b4j.example.categories _dbb = null;
 //BA.debugLineNum = 289;BA.debugLine="Sub mnusearch_Action";
 //BA.debugLineNum = 291;BA.debugLine="Dim search As String";
_search = "";
 //BA.debugLineNum = 292;BA.debugLine="Dim dialog As Dialogs8";
_dialog = new b4j.example.dialogs8();
 //BA.debugLineNum = 293;BA.debugLine="dialog.Initialize";
_dialog._initialize(ba);
 //BA.debugLineNum = 294;BA.debugLine="search = dialog.TextInputDialog(\"جستجوی\",\"لطفا قس";
_search = _dialog._textinputdialog("جستجوی","لطفا قسمتی از نام کتاب را وارد کنید","","اینجا");
 //BA.debugLineNum = 296;BA.debugLine="If search.Length = 0 Then";
if (_search.length()==0) { 
 //BA.debugLineNum = 297;BA.debugLine="dialog.WarningDialog(\"توجه\",\"\",\"لطفا قسمتی از عب";
_dialog._warningdialog("توجه","","لطفا قسمتی از عبارت را وارد کنید");
 //BA.debugLineNum = 298;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 301;BA.debugLine="FilterLangauge = False";
_filterlangauge = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 302;BA.debugLine="FilterLanguageVal = search";
_filterlanguageval = _search;
 //BA.debugLineNum = 304;BA.debugLine="lvbooks.Items.Clear";
_lvbooks.getItems().Clear();
 //BA.debugLineNum = 306;BA.debugLine="Dim dbb As Categories";
_dbb = new b4j.example.categories();
 //BA.debugLineNum = 307;BA.debugLine="dbb.Initialize";
_dbb._initialize(ba);
 //BA.debugLineNum = 308;BA.debugLine="AddBooks(dbb.GetBook(0,search,False))";
_addbooks(_dbb._getbook((int) (0),_search,anywheresoftware.b4a.keywords.Common.False));
 //BA.debugLineNum = 310;BA.debugLine="End Sub";
return "";
}
public static String  _pnlover_mouseclicked(anywheresoftware.b4j.objects.NodeWrapper.MouseEventWrapper _eventdata) throws Exception{
anywheresoftware.b4j.objects.PaneWrapper.ConcretePaneWrapper _v1 = null;
anywheresoftware.b4a.objects.collections.Map _val = null;
 //BA.debugLineNum = 313;BA.debugLine="Sub pnlover_MouseClicked (EventData As MouseEvent)";
 //BA.debugLineNum = 315;BA.debugLine="Dim v1 As Pane";
_v1 = new anywheresoftware.b4j.objects.PaneWrapper.ConcretePaneWrapper();
 //BA.debugLineNum = 316;BA.debugLine="v1 = Sender";
_v1.setObject((javafx.scene.layout.Pane)(anywheresoftware.b4a.keywords.Common.Sender(ba)));
 //BA.debugLineNum = 317;BA.debugLine="Dim val As Map";
_val = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 318;BA.debugLine="val = v1.Tag";
_val.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_v1.getTag()));
 //BA.debugLineNum = 320;BA.debugLine="If val.ContainsKey(\"state\") Then";
if (_val.ContainsKey((Object)("state"))) { 
 //BA.debugLineNum = 321;BA.debugLine="Dim val As Map";
_val = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 322;BA.debugLine="val = stack.Get(stack.Size-1)";
_val.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_stack.Get((int) (_stack.getSize()-1))));
 //BA.debugLineNum = 323;BA.debugLine="stack.RemoveAt(stack.Size-1)";
_stack.RemoveAt((int) (_stack.getSize()-1));
 //BA.debugLineNum = 324;BA.debugLine="LoadCategories(val.Get(\"sParent\"))";
_loadcategories(BA.ObjectToString(_val.Get((Object)("sParent"))));
 }else {
 //BA.debugLineNum = 326;BA.debugLine="stack.Add(val)";
_stack.Add((Object)(_val.getObject()));
 //BA.debugLineNum = 327;BA.debugLine="LoadCategories(val.Get(\"sID\"))";
_loadcategories(BA.ObjectToString(_val.Get((Object)("sID"))));
 };
 //BA.debugLineNum = 330;BA.debugLine="End Sub";
return "";
}
public static String  _pnloverbook_mouseclicked(anywheresoftware.b4j.objects.NodeWrapper.MouseEventWrapper _eventdata) throws Exception{
anywheresoftware.b4j.objects.PaneWrapper.ConcretePaneWrapper _v1 = null;
anywheresoftware.b4a.objects.collections.Map _m = null;
 //BA.debugLineNum = 233;BA.debugLine="Sub pnloverbook_MouseClicked (EventData As MouseEv";
 //BA.debugLineNum = 235;BA.debugLine="Dim v1 As Pane";
_v1 = new anywheresoftware.b4j.objects.PaneWrapper.ConcretePaneWrapper();
 //BA.debugLineNum = 236;BA.debugLine="v1 = Sender";
_v1.setObject((javafx.scene.layout.Pane)(anywheresoftware.b4a.keywords.Common.Sender(ba)));
 //BA.debugLineNum = 237;BA.debugLine="Dim m As Map";
_m = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 238;BA.debugLine="m = v1.Tag";
_m.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_v1.getTag()));
 //BA.debugLineNum = 239;BA.debugLine="actBookInformation.mu = m";
_actbookinformation._mu = _m;
 //BA.debugLineNum = 240;BA.debugLine="actBookInformation.ShowInformation";
_actbookinformation._showinformation();
 //BA.debugLineNum = 242;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 2;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 3;BA.debugLine="Private fx As JFX";
_fx = new anywheresoftware.b4j.objects.JFX();
 //BA.debugLineNum = 4;BA.debugLine="Private frm1 As Form";
_frm1 = new anywheresoftware.b4j.objects.Form();
 //BA.debugLineNum = 5;BA.debugLine="Private lvbooks As ListView";
_lvbooks = new anywheresoftware.b4j.objects.ListViewWrapper();
 //BA.debugLineNum = 6;BA.debugLine="Dim dbCat,dbBook As Categories";
_dbcat = new b4j.example.categories();
_dbbook = new b4j.example.categories();
 //BA.debugLineNum = 7;BA.debugLine="Public ForceUpdate As Boolean";
_forceupdate = false;
 //BA.debugLineNum = 8;BA.debugLine="Private lblbookname As Label";
_lblbookname = new anywheresoftware.b4j.objects.LabelWrapper();
 //BA.debugLineNum = 9;BA.debugLine="Private imgicon As ImageView";
_imgicon = new anywheresoftware.b4j.objects.ImageViewWrapper();
 //BA.debugLineNum = 10;BA.debugLine="Private pnlover As Pane";
_pnlover = new anywheresoftware.b4j.objects.PaneWrapper.ConcretePaneWrapper();
 //BA.debugLineNum = 11;BA.debugLine="Private FilterLanguageVal As String";
_filterlanguageval = "";
 //BA.debugLineNum = 12;BA.debugLine="Private FilterLangauge As Boolean";
_filterlangauge = false;
 //BA.debugLineNum = 13;BA.debugLine="Private stack As List";
_stack = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 14;BA.debugLine="Private currentCatID,currentFileChoosen As String";
_currentcatid = "";
_currentfilechoosen = "";
 //BA.debugLineNum = 15;BA.debugLine="Private lblcatname As Label";
_lblcatname = new anywheresoftware.b4j.objects.LabelWrapper();
 //BA.debugLineNum = 16;BA.debugLine="Private lblbookextra As Label";
_lblbookextra = new anywheresoftware.b4j.objects.LabelWrapper();
 //BA.debugLineNum = 17;BA.debugLine="Private btndownload As Button";
_btndownload = new anywheresoftware.b4j.objects.ButtonWrapper();
 //BA.debugLineNum = 18;BA.debugLine="Private lblsize As Label";
_lblsize = new anywheresoftware.b4j.objects.LabelWrapper();
 //BA.debugLineNum = 19;BA.debugLine="Private pb1 As ImageView";
_pb1 = new anywheresoftware.b4j.objects.ImageViewWrapper();
 //BA.debugLineNum = 20;BA.debugLine="Private currentPB As ImageView";
_currentpb = new anywheresoftware.b4j.objects.ImageViewWrapper();
 //BA.debugLineNum = 21;BA.debugLine="Private CurrentDownloadLabel As Button";
_currentdownloadlabel = new anywheresoftware.b4j.objects.ButtonWrapper();
 //BA.debugLineNum = 22;BA.debugLine="Private pnloverbook As Pane";
_pnloverbook = new anywheresoftware.b4j.objects.PaneWrapper.ConcretePaneWrapper();
 //BA.debugLineNum = 23;BA.debugLine="Private loading As Loading";
_loading = new b4j.example.loading();
 //BA.debugLineNum = 24;BA.debugLine="Private IsChange As Boolean";
_ischange = false;
 //BA.debugLineNum = 25;BA.debugLine="End Sub";
return "";
}
public static String  _showdownload() throws Exception{
b4j.example.httpjob _hu = null;
 //BA.debugLineNum = 27;BA.debugLine="Sub ShowDownload";
 //BA.debugLineNum = 29;BA.debugLine="If frm1.IsInitialized = False Then";
if (_frm1.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 30;BA.debugLine="frm1.Initialize(\"frm1\",470,594)";
_frm1.Initialize(ba,"frm1",470,594);
 };
 //BA.debugLineNum = 33;BA.debugLine="frm1.Title = \"دانلود کتاب\"";
_frm1.setTitle("دانلود کتاب");
 //BA.debugLineNum = 34;BA.debugLine="frm1.Resizable = False";
_frm1.setResizable(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 35;BA.debugLine="frm1.RootPane.LoadLayout(\"frmdownloadbook\")";
_frm1.getRootPane().LoadLayout(ba,"frmdownloadbook");
 //BA.debugLineNum = 36;BA.debugLine="frm1.SetOwner(Main.MainForm)";
_frm1.SetOwner(_main._mainform);
 //BA.debugLineNum = 37;BA.debugLine="frm1.Show";
_frm1.Show();
 //BA.debugLineNum = 38;BA.debugLine="Library.CenterFormOnScreen(frm1)";
_library._centerformonscreen(_frm1);
 //BA.debugLineNum = 40;BA.debugLine="dbCat.Initialize";
_dbcat._initialize(ba);
 //BA.debugLineNum = 41;BA.debugLine="stack.Initialize";
_stack.Initialize();
 //BA.debugLineNum = 43;BA.debugLine="If dbCat.GetBookCount = 0 Or ForceUpdate = True T";
if (_dbcat._getbookcount()==0 || _forceupdate==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 44;BA.debugLine="dbCat.Initialize";
_dbcat._initialize(ba);
 //BA.debugLineNum = 45;BA.debugLine="frm1.RootPane.Enabled = False";
_frm1.getRootPane().setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 46;BA.debugLine="Dim hu As HttpJob";
_hu = new b4j.example.httpjob();
 //BA.debugLineNum = 47;BA.debugLine="hu.Initialize(\"cat\",Me)";
_hu._initialize(ba,"cat",actdownload.getObject());
 //BA.debugLineNum = 48;BA.debugLine="hu.PostString(Library.URL & \"list_category\",\"\")";
_hu._poststring(_library._url+"list_category","");
 //BA.debugLineNum = 49;BA.debugLine="ForceUpdate = False";
_forceupdate = anywheresoftware.b4a.keywords.Common.False;
 }else {
 //BA.debugLineNum = 51;BA.debugLine="LoadItem";
_loaditem();
 };
 //BA.debugLineNum = 54;BA.debugLine="loading.Initialize(frm1)";
_loading._initialize(ba,_frm1);
 //BA.debugLineNum = 56;BA.debugLine="End Sub";
return "";
}
}
