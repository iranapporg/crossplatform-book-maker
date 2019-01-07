package b4j.example;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.debug.*;

public class actbookinformation extends Object{
public static actbookinformation mostCurrent = new actbookinformation();

public static BA ba;
static {
		ba = new  anywheresoftware.b4j.objects.FxBA("b4j.example", "b4j.example.actbookinformation", null);
		ba.loadHtSubs(actbookinformation.class);
        if (ba.getClass().getName().endsWith("ShellBA")) {
			
			ba.raiseEvent2(null, true, "SHELL", false);
			ba.raiseEvent2(null, true, "CREATE", true, "b4j.example.actbookinformation", ba);
		}
	}
    public static Class<?> getObject() {
		return actbookinformation.class;
	}

 public static anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4j.objects.JFX _fx = null;
public static anywheresoftware.b4j.objects.Form _frm1 = null;
public static anywheresoftware.b4j.objects.LabelWrapper _lbltitlebook = null;
public static anywheresoftware.b4j.objects.LabelWrapper _lblauthor = null;
public static anywheresoftware.b4j.objects.LabelWrapper _lbldate = null;
public static anywheresoftware.b4j.objects.LabelWrapper _lbldownload = null;
public static anywheresoftware.b4j.objects.ButtonWrapper _btnshare = null;
public static anywheresoftware.b4j.objects.ButtonWrapper _btndownload = null;
public static anywheresoftware.b4j.objects.TextInputControlWrapper.TextAreaWrapper _lblcontent = null;
public static anywheresoftware.b4j.objects.ImageViewWrapper _imgicon = null;
public static anywheresoftware.b4j.objects.LabelWrapper _ls1 = null;
public static anywheresoftware.b4j.objects.LabelWrapper _ls2 = null;
public static anywheresoftware.b4j.objects.LabelWrapper _ls3 = null;
public static anywheresoftware.b4j.objects.LabelWrapper _ls5 = null;
public static anywheresoftware.b4a.objects.collections.Map _mu = null;
public static anywheresoftware.b4j.objects.LabelWrapper _ls4 = null;
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
public static b4j.example.actcontact _actcontact = null;
public static b4j.example.dbutils _dbutils = null;
public static b4j.example.actsetting _actsetting = null;
public static b4j.example.actsearchbook _actsearchbook = null;
public static String  _btndownload_action() throws Exception{
b4j.example.httpjob _hu = null;
 //BA.debugLineNum = 127;BA.debugLine="Sub btndownload_Action";
 //BA.debugLineNum = 129;BA.debugLine="loading.Show(\"در حال دانلود...\")";
_loading._show("در حال دانلود...");
 //BA.debugLineNum = 131;BA.debugLine="Dim hu As HttpJob";
_hu = new b4j.example.httpjob();
 //BA.debugLineNum = 132;BA.debugLine="hu.Initialize(\"downloadbook\",Me)";
_hu._initialize(ba,"downloadbook",actbookinformation.getObject());
 //BA.debugLineNum = 133;BA.debugLine="hu.Download(Library.URL.Replace(\"index.php/server";
_hu._download(_library._url.replace("index.php/server/","/")+"books/book_"+BA.ObjectToString(_mu.Get((Object)("sID")))+".zip");
 //BA.debugLineNum = 135;BA.debugLine="End Sub";
return "";
}
public static String  _btnshare_action() throws Exception{
 //BA.debugLineNum = 123;BA.debugLine="Sub btnshare_Action";
 //BA.debugLineNum = 125;BA.debugLine="End Sub";
return "";
}
public static String  _jobdone(b4j.example.httpjob _job) throws Exception{
anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper _ou = null;
com.AB.ABZipUnzip.ABZipUnzip _zip = null;
b4j.example.httpjob _hu = null;
b4j.example.categories _db1 = null;
anywheresoftware.b4j.objects.collections.JSONParser _js = null;
anywheresoftware.b4a.objects.collections.Map _res = null;
 //BA.debugLineNum = 137;BA.debugLine="Sub JobDone(Job As HttpJob)";
 //BA.debugLineNum = 139;BA.debugLine="loading.Hide";
_loading._hide();
 //BA.debugLineNum = 141;BA.debugLine="If Job.Success Then";
if (_job._success) { 
 //BA.debugLineNum = 143;BA.debugLine="If Job.JobName = \"downloadbook\" Then";
if ((_job._jobname).equals("downloadbook")) { 
 //BA.debugLineNum = 145;BA.debugLine="Dim ou As OutputStream";
_ou = new anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper();
 //BA.debugLineNum = 146;BA.debugLine="ou = File.OpenOutput(File.DirApp & \"/Data\",\"tem";
_ou = anywheresoftware.b4a.keywords.Common.File.OpenOutput(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data","temp.zip",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 147;BA.debugLine="File.Copy2(Job.GetInputStream,ou)";
anywheresoftware.b4a.keywords.Common.File.Copy2((java.io.InputStream)(_job._getinputstream().getObject()),(java.io.OutputStream)(_ou.getObject()));
 //BA.debugLineNum = 148;BA.debugLine="ou.Close";
_ou.Close();
 //BA.debugLineNum = 150;BA.debugLine="File.MakeDir(File.DirApp & \"/Data\",\"book/\" & Jo";
anywheresoftware.b4a.keywords.Common.File.MakeDir(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data","book/"+_job._jobname);
 //BA.debugLineNum = 151;BA.debugLine="Dim zip As ABZipUnzip";
_zip = new com.AB.ABZipUnzip.ABZipUnzip();
 //BA.debugLineNum = 152;BA.debugLine="zip.ABUnzip(File.Combine(File.DirApp & \"/Data\",";
_zip.ABUnzip(anywheresoftware.b4a.keywords.Common.File.Combine(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data","temp.zip"),anywheresoftware.b4a.keywords.Common.File.Combine(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data","book/"+_job._jobname));
 //BA.debugLineNum = 153;BA.debugLine="DateTime.DateFormat = \"yyyy-mm-dd\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("yyyy-mm-dd");
 //BA.debugLineNum = 154;BA.debugLine="File.WriteString(File.DirApp & \"/Data\" & \"/book";
anywheresoftware.b4a.keywords.Common.File.WriteString(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data"+"/book/"+_job._jobname,"date",anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow()));
 //BA.debugLineNum = 156;BA.debugLine="Dim hu As HttpJob";
_hu = new b4j.example.httpjob();
 //BA.debugLineNum = 157;BA.debugLine="hu.Initialize(\"modifybook\",Me)";
_hu._initialize(ba,"modifybook",actbookinformation.getObject());
 //BA.debugLineNum = 158;BA.debugLine="hu.PostString(Library.URL & \"add_download/\" & m";
_hu._poststring(_library._url+"add_download/"+BA.ObjectToString(_mu.Get((Object)("sID"))),"");
 //BA.debugLineNum = 160;BA.debugLine="If File.Exists(File.DirApp & \"/Data\",\"visit_boo";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data","visit_book1")==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 161;BA.debugLine="Library.WriteCounterReadBook(Job.JobName)";
_library._writecounterreadbook(_job._jobname);
 };
 //BA.debugLineNum = 164;BA.debugLine="Dim db1 As Categories";
_db1 = new b4j.example.categories();
 //BA.debugLineNum = 165;BA.debugLine="db1.Initialize";
_db1._initialize(ba);
 //BA.debugLineNum = 166;BA.debugLine="db1.DownloadedBook(mu.Get(\"sID\"),\"1\")";
_db1._downloadedbook(BA.ObjectToString(_mu.Get((Object)("sID"))),(int)(Double.parseDouble("1")));
 //BA.debugLineNum = 168;BA.debugLine="btndownload.Enabled = False";
_btndownload.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 }else if((_job._jobname).equals("modifybook")) { 
 //BA.debugLineNum = 171;BA.debugLine="Dim js As JSONParser";
_js = new anywheresoftware.b4j.objects.collections.JSONParser();
 //BA.debugLineNum = 172;BA.debugLine="js.Initialize(Job.GetString)";
_js.Initialize(_job._getstring());
 //BA.debugLineNum = 173;BA.debugLine="Dim res As Map = js.NextObject";
_res = new anywheresoftware.b4a.objects.collections.Map();
_res = _js.NextObject();
 //BA.debugLineNum = 174;BA.debugLine="If res.IsInitialized Then";
if (_res.IsInitialized()) { 
 //BA.debugLineNum = 175;BA.debugLine="Log(res)";
anywheresoftware.b4a.keywords.Common.Log(BA.ObjectToString(_res));
 };
 };
 }else {
 //BA.debugLineNum = 180;BA.debugLine="Log(Job.ErrorMessage)";
anywheresoftware.b4a.keywords.Common.Log(_job._errormessage);
 };
 //BA.debugLineNum = 183;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 2;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 3;BA.debugLine="Private fx As JFX";
_fx = new anywheresoftware.b4j.objects.JFX();
 //BA.debugLineNum = 4;BA.debugLine="Private frm1 As Form";
_frm1 = new anywheresoftware.b4j.objects.Form();
 //BA.debugLineNum = 5;BA.debugLine="Private lbltitlebook As Label";
_lbltitlebook = new anywheresoftware.b4j.objects.LabelWrapper();
 //BA.debugLineNum = 6;BA.debugLine="Private lblauthor As Label";
_lblauthor = new anywheresoftware.b4j.objects.LabelWrapper();
 //BA.debugLineNum = 7;BA.debugLine="Private lbldate As Label";
_lbldate = new anywheresoftware.b4j.objects.LabelWrapper();
 //BA.debugLineNum = 8;BA.debugLine="Private lbldownload As Label";
_lbldownload = new anywheresoftware.b4j.objects.LabelWrapper();
 //BA.debugLineNum = 9;BA.debugLine="Private btnshare As Button";
_btnshare = new anywheresoftware.b4j.objects.ButtonWrapper();
 //BA.debugLineNum = 10;BA.debugLine="Private btndownload As Button";
_btndownload = new anywheresoftware.b4j.objects.ButtonWrapper();
 //BA.debugLineNum = 11;BA.debugLine="Private lblcontent As TextArea";
_lblcontent = new anywheresoftware.b4j.objects.TextInputControlWrapper.TextAreaWrapper();
 //BA.debugLineNum = 12;BA.debugLine="Private imgicon As ImageView";
_imgicon = new anywheresoftware.b4j.objects.ImageViewWrapper();
 //BA.debugLineNum = 13;BA.debugLine="Private ls1 As Label";
_ls1 = new anywheresoftware.b4j.objects.LabelWrapper();
 //BA.debugLineNum = 14;BA.debugLine="Private ls2 As Label";
_ls2 = new anywheresoftware.b4j.objects.LabelWrapper();
 //BA.debugLineNum = 15;BA.debugLine="Private ls3 As Label";
_ls3 = new anywheresoftware.b4j.objects.LabelWrapper();
 //BA.debugLineNum = 16;BA.debugLine="Private ls5 As Label";
_ls5 = new anywheresoftware.b4j.objects.LabelWrapper();
 //BA.debugLineNum = 17;BA.debugLine="Public mu As Map";
_mu = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 18;BA.debugLine="Private ls4 As Label";
_ls4 = new anywheresoftware.b4j.objects.LabelWrapper();
 //BA.debugLineNum = 19;BA.debugLine="Private loading As Loading";
_loading = new b4j.example.loading();
 //BA.debugLineNum = 20;BA.debugLine="End Sub";
return "";
}
public static String  _showinformation() throws Exception{
int _rating = 0;
anywheresoftware.b4a.objects.collections.Map _im = null;
b4j.example.imagedownloader _download = null;
 //BA.debugLineNum = 22;BA.debugLine="Sub ShowInformation";
 //BA.debugLineNum = 24;BA.debugLine="If frm1.IsInitialized = False Then";
if (_frm1.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 25;BA.debugLine="frm1.Initialize(\"\",370,590)";
_frm1.Initialize(ba,"",370,590);
 };
 //BA.debugLineNum = 28;BA.debugLine="frm1.Title = \"جزییات کتاب \" & mu.Get(\"sTitle\")";
_frm1.setTitle("جزییات کتاب "+BA.ObjectToString(_mu.Get((Object)("sTitle"))));
 //BA.debugLineNum = 29;BA.debugLine="frm1.Resizable = False";
_frm1.setResizable(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 30;BA.debugLine="frm1.RootPane.LoadLayout(\"frmbookinformation\")";
_frm1.getRootPane().LoadLayout(ba,"frmbookinformation");
 //BA.debugLineNum = 31;BA.debugLine="frm1.Show";
_frm1.Show();
 //BA.debugLineNum = 32;BA.debugLine="Library.CenterFormOnScreen(frm1)";
_library._centerformonscreen(_frm1);
 //BA.debugLineNum = 33;BA.debugLine="loading.Initialize(frm1)";
_loading._initialize(ba,_frm1);
 //BA.debugLineNum = 35;BA.debugLine="lbltitlebook.Text = mu.Get(\"sTitle\")";
_lbltitlebook.setText(BA.ObjectToString(_mu.Get((Object)("sTitle"))));
 //BA.debugLineNum = 36;BA.debugLine="lblauthor.Text = mu.Get(\"sAuthor\")";
_lblauthor.setText(BA.ObjectToString(_mu.Get((Object)("sAuthor"))));
 //BA.debugLineNum = 38;BA.debugLine="lblcontent.Text = mu.Get(\"sDescription\")";
_lblcontent.setText(BA.ObjectToString(_mu.Get((Object)("sDescription"))));
 //BA.debugLineNum = 40;BA.debugLine="lbldate.Text = Library.GetDate(mu.Get(\"sPublishDa";
_lbldate.setText(_library._getdate(BA.ObjectToString(_mu.Get((Object)("sPublishDate")))));
 //BA.debugLineNum = 41;BA.debugLine="lbldownload.Text = \"تعداد دانلود : \" & mu.Get(\"sD";
_lbldownload.setText("تعداد دانلود : "+BA.ObjectToString(_mu.Get((Object)("sDownload"))));
 //BA.debugLineNum = 43;BA.debugLine="Dim rating As Int";
_rating = 0;
 //BA.debugLineNum = 44;BA.debugLine="rating = mu.Get(\"sRate\")";
_rating = (int)(BA.ObjectToNumber(_mu.Get((Object)("sRate"))));
 //BA.debugLineNum = 46;BA.debugLine="ls1.Font = fx.LoadFont(File.DirAssets,\"icomoon.tt";
_ls1.setFont(_fx.LoadFont(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"icomoon.ttf",14));
 //BA.debugLineNum = 47;BA.debugLine="ls2.Font = fx.LoadFont(File.DirAssets,\"icomoon.tt";
_ls2.setFont(_fx.LoadFont(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"icomoon.ttf",14));
 //BA.debugLineNum = 48;BA.debugLine="ls3.Font = fx.LoadFont(File.DirAssets,\"icomoon.tt";
_ls3.setFont(_fx.LoadFont(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"icomoon.ttf",14));
 //BA.debugLineNum = 49;BA.debugLine="ls4.Font = fx.LoadFont(File.DirAssets,\"icomoon.tt";
_ls4.setFont(_fx.LoadFont(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"icomoon.ttf",14));
 //BA.debugLineNum = 50;BA.debugLine="ls5.Font = fx.LoadFont(File.DirAssets,\"icomoon.tt";
_ls5.setFont(_fx.LoadFont(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"icomoon.ttf",14));
 //BA.debugLineNum = 54;BA.debugLine="If rating = \"0\" Then";
if (_rating==(double)(Double.parseDouble("0"))) { 
 //BA.debugLineNum = 55;BA.debugLine="ls1.Text = \"\"";
_ls1.setText("");
 //BA.debugLineNum = 56;BA.debugLine="ls2.Text = \"\"";
_ls2.setText("");
 //BA.debugLineNum = 57;BA.debugLine="ls3.Text = \"\"";
_ls3.setText("");
 //BA.debugLineNum = 58;BA.debugLine="ls4.Text = \"\"";
_ls4.setText("");
 //BA.debugLineNum = 59;BA.debugLine="ls5.Text = \"\"";
_ls5.setText("");
 }else if(_rating==(double)(Double.parseDouble("1"))) { 
 //BA.debugLineNum = 62;BA.debugLine="ls1.Text = \"\"";
_ls1.setText("");
 //BA.debugLineNum = 63;BA.debugLine="ls2.Text = \"\"";
_ls2.setText("");
 //BA.debugLineNum = 64;BA.debugLine="ls3.Text = \"\"";
_ls3.setText("");
 //BA.debugLineNum = 65;BA.debugLine="ls4.Text = \"\"";
_ls4.setText("");
 //BA.debugLineNum = 66;BA.debugLine="ls5.Text = \"\"";
_ls5.setText("");
 }else if(_rating==(double)(Double.parseDouble("2"))) { 
 //BA.debugLineNum = 69;BA.debugLine="ls1.Text = \"\"";
_ls1.setText("");
 //BA.debugLineNum = 70;BA.debugLine="ls2.Text = \"\"";
_ls2.setText("");
 //BA.debugLineNum = 71;BA.debugLine="ls3.Text = \"\"";
_ls3.setText("");
 //BA.debugLineNum = 72;BA.debugLine="ls4.Text = \"\"";
_ls4.setText("");
 //BA.debugLineNum = 73;BA.debugLine="ls5.Text = \"\"";
_ls5.setText("");
 }else if(_rating==(double)(Double.parseDouble("3"))) { 
 //BA.debugLineNum = 76;BA.debugLine="ls1.Text = \"\"";
_ls1.setText("");
 //BA.debugLineNum = 77;BA.debugLine="ls2.Text = \"\"";
_ls2.setText("");
 //BA.debugLineNum = 78;BA.debugLine="ls3.Text = \"\"";
_ls3.setText("");
 //BA.debugLineNum = 79;BA.debugLine="ls4.Text = \"\"";
_ls4.setText("");
 //BA.debugLineNum = 80;BA.debugLine="ls5.Text = \"\"";
_ls5.setText("");
 }else if(_rating==(double)(Double.parseDouble("4"))) { 
 //BA.debugLineNum = 83;BA.debugLine="ls1.Text = \"\"";
_ls1.setText("");
 //BA.debugLineNum = 84;BA.debugLine="ls2.Text = \"\"";
_ls2.setText("");
 //BA.debugLineNum = 85;BA.debugLine="ls3.Text = \"\"";
_ls3.setText("");
 //BA.debugLineNum = 86;BA.debugLine="ls4.Text = \"\"";
_ls4.setText("");
 //BA.debugLineNum = 87;BA.debugLine="ls5.Text = \"\"";
_ls5.setText("");
 }else if(_rating==(double)(Double.parseDouble("5")) || _rating>5) { 
 //BA.debugLineNum = 90;BA.debugLine="ls1.Text = \"\"";
_ls1.setText("");
 //BA.debugLineNum = 91;BA.debugLine="ls2.Text = \"\"";
_ls2.setText("");
 //BA.debugLineNum = 92;BA.debugLine="ls3.Text = \"\"";
_ls3.setText("");
 //BA.debugLineNum = 93;BA.debugLine="ls4.Text = \"\"";
_ls4.setText("");
 //BA.debugLineNum = 94;BA.debugLine="ls5.Text = \"\"";
_ls5.setText("");
 };
 //BA.debugLineNum = 99;BA.debugLine="Dim im As Map";
_im = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 100;BA.debugLine="im.Initialize";
_im.Initialize();
 //BA.debugLineNum = 102;BA.debugLine="If File.Exists(File.DirApp & \"/Data\" & \"/book/\" &";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data"+"/book/"+BA.ObjectToString(_mu.Get((Object)("sID"))),BA.ObjectToString(_mu.Get((Object)("sID")))+".jpg")) { 
 //BA.debugLineNum = 103;BA.debugLine="imgicon.SetImage(fx.LoadImage(File.DirApp & \"/Da";
_imgicon.SetImage((javafx.scene.image.Image)(_fx.LoadImage(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data"+"/book/"+BA.ObjectToString(_mu.Get((Object)("sID"))),BA.ObjectToString(_mu.Get((Object)("sID")))+".jpg").getObject()));
 }else {
 //BA.debugLineNum = 105;BA.debugLine="Try";
try { //BA.debugLineNum = 106;BA.debugLine="im.Put(imgicon,Library.URL & \"/\" & mu.Get(\"sCov";
_im.Put((Object)(_imgicon.getObject()),(Object)(_library._url+"/"+BA.ObjectToString(_mu.Get((Object)("sCover")))));
 //BA.debugLineNum = 107;BA.debugLine="Dim download As ImageDownloader";
_download = new b4j.example.imagedownloader();
 //BA.debugLineNum = 108;BA.debugLine="download.Initialize";
_download._initialize(ba);
 //BA.debugLineNum = 109;BA.debugLine="download.Download(im)";
_download._download(_im);
 } 
       catch (Exception e70) {
			ba.setLastException(e70); //BA.debugLineNum = 111;BA.debugLine="imgicon.SetImage(fx.LoadImage(File.DirAssets,\"b";
_imgicon.SetImage((javafx.scene.image.Image)(_fx.LoadImage(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"book.png").getObject()));
 };
 };
 //BA.debugLineNum = 115;BA.debugLine="If File.Exists(File.DirApp & \"/Data\" & \"/book/\" &";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data"+"/book/"+BA.ObjectToString(_mu.Get((Object)("sID"))),"bank.db")) { 
 //BA.debugLineNum = 116;BA.debugLine="fx.Msgbox(frm1,\"این کتاب قبلا دانلود شده است.میت";
_fx.Msgbox(_frm1,"این کتاب قبلا دانلود شده است.میتوانید کتاب را مرور کنید","توجه");
 //BA.debugLineNum = 117;BA.debugLine="btndownload.Enabled = False";
_btndownload.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 118;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 121;BA.debugLine="End Sub";
return "";
}
}
