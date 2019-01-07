package b4j.example;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.debug.*;

public class library extends Object{
public static library mostCurrent = new library();

public static BA ba;
static {
		ba = new  anywheresoftware.b4j.objects.FxBA("b4j.example", "b4j.example.library", null);
		ba.loadHtSubs(library.class);
        if (ba.getClass().getName().endsWith("ShellBA")) {
			
			ba.raiseEvent2(null, true, "SHELL", false);
			ba.raiseEvent2(null, true, "CREATE", true, "b4j.example.library", ba);
		}
	}
    public static Class<?> getObject() {
		return library.class;
	}

 public static anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4j.objects.JFX _fx = null;
public static String _baseurl = "";
public static String _url = "";
public static com.stevel05.Prefs _manager = null;
public static b4j.example.httputils2service _httputils2service = null;
public static b4j.example.main _main = null;
public static b4j.example.actbookmenu _actbookmenu = null;
public static b4j.example.actcontent _actcontent = null;
public static b4j.example.actdownload _actdownload = null;
public static b4j.example.actsitesocial _actsitesocial = null;
public static b4j.example.actmessage _actmessage = null;
public static b4j.example.actsendpoint _actsendpoint = null;
public static b4j.example.actpoints _actpoints = null;
public static b4j.example.actabout _actabout = null;
public static b4j.example.actcontact _actcontact = null;
public static b4j.example.dbutils _dbutils = null;
public static b4j.example.actbookinformation _actbookinformation = null;
public static b4j.example.actsetting _actsetting = null;
public static b4j.example.actsearchbook _actsearchbook = null;
public static String  _centerformonscreen(anywheresoftware.b4j.objects.Form _frm) throws Exception{
anywheresoftware.b4j.objects.JFX.ScreenWrapper _ps = null;
 //BA.debugLineNum = 9;BA.debugLine="Sub CenterFormOnScreen(Frm As Form)";
 //BA.debugLineNum = 11;BA.debugLine="Dim ps As Screen = fx.PrimaryScreen";
_ps = new anywheresoftware.b4j.objects.JFX.ScreenWrapper();
_ps = _fx.getPrimaryScreen();
 //BA.debugLineNum = 12;BA.debugLine="Frm.WindowTop = (ps.MaxY - ps.MinY) / 2 - Frm.H";
_frm.setWindowTop((_ps.getMaxY()-_ps.getMinY())/(double)2-_frm.getHeight()/(double)2);
 //BA.debugLineNum = 13;BA.debugLine="Frm.WindowLeft = (ps.MaxX - ps.MinX) / 2 - Frm.";
_frm.setWindowLeft((_ps.getMaxX()-_ps.getMinX())/(double)2-_frm.getWidth()/(double)2);
 //BA.debugLineNum = 14;BA.debugLine="End Sub";
return "";
}
public static String  _clearbookreaded(String _id) throws Exception{
anywheresoftware.b4a.objects.collections.Map _m = null;
 //BA.debugLineNum = 196;BA.debugLine="Sub ClearBookReaded(ID As String)";
 //BA.debugLineNum = 198;BA.debugLine="Dim m As Map";
_m = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 199;BA.debugLine="m.Initialize";
_m.Initialize();
 //BA.debugLineNum = 201;BA.debugLine="If File.Exists(File.DirApp & \"/Data\",\"visit_book1";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data","visit_book1")) { 
 //BA.debugLineNum = 202;BA.debugLine="m = File.ReadMap(File.DirApp & \"/Data\",\"visit_bo";
_m = anywheresoftware.b4a.keywords.Common.File.ReadMap(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data","visit_book1");
 }else {
 //BA.debugLineNum = 204;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 207;BA.debugLine="If m.ContainsKey(ID) Then";
if (_m.ContainsKey((Object)(_id))) { 
 //BA.debugLineNum = 208;BA.debugLine="m.Remove(ID)";
_m.Remove((Object)(_id));
 //BA.debugLineNum = 209;BA.debugLine="File.WriteMap(File.DirApp & \"/Data\",\"visit_book1";
anywheresoftware.b4a.keywords.Common.File.WriteMap(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data","visit_book1",_m);
 };
 //BA.debugLineNum = 212;BA.debugLine="End Sub";
return "";
}
public static String  _deletebookmark(String _bookid,anywheresoftware.b4a.objects.collections.Map _data) throws Exception{
anywheresoftware.b4a.objects.collections.Map _m = null;
anywheresoftware.b4a.objects.collections.List _ls = null;
int _i = 0;
anywheresoftware.b4j.objects.collections.JSONParser _js = null;
anywheresoftware.b4a.objects.collections.Map _m2 = null;
 //BA.debugLineNum = 422;BA.debugLine="Sub Deletebookmark(BookID As String,Data As Map)";
 //BA.debugLineNum = 424;BA.debugLine="Dim m As Map";
_m = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 425;BA.debugLine="m.Initialize";
_m.Initialize();
 //BA.debugLineNum = 427;BA.debugLine="If File.Exists(File.DirApp & \"/Data\",\"bookmark\" &";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data","bookmark"+_bookid)) { 
 //BA.debugLineNum = 428;BA.debugLine="m = File.ReadMap(File.DirApp & \"/Data\",\"bookmark";
_m = anywheresoftware.b4a.keywords.Common.File.ReadMap(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data","bookmark"+_bookid);
 }else {
 //BA.debugLineNum = 430;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 433;BA.debugLine="Dim ls As List";
_ls = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 434;BA.debugLine="ls.Initialize";
_ls.Initialize();
 //BA.debugLineNum = 436;BA.debugLine="For i = 0 To m.Size - 1";
{
final int step10 = 1;
final int limit10 = (int) (_m.getSize()-1);
for (_i = (int) (0) ; (step10 > 0 && _i <= limit10) || (step10 < 0 && _i >= limit10); _i = ((int)(0 + _i + step10)) ) {
 //BA.debugLineNum = 438;BA.debugLine="Dim js As JSONParser";
_js = new anywheresoftware.b4j.objects.collections.JSONParser();
 //BA.debugLineNum = 439;BA.debugLine="Dim m2 As Map";
_m2 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 441;BA.debugLine="js.Initialize(m.GetValueAt(i))";
_js.Initialize(BA.ObjectToString(_m.GetValueAt(_i)));
 //BA.debugLineNum = 442;BA.debugLine="m2 = js.NextObject";
_m2 = _js.NextObject();
 //BA.debugLineNum = 444;BA.debugLine="If m2.Get(\"Offset\") = Data.Get(\"id\") And m2.Get(";
if ((_m2.Get((Object)("Offset"))).equals(_data.Get((Object)("id"))) && (_m2.Get((Object)("TopicID"))).equals(_data.Get((Object)("topicid")))) { 
 //BA.debugLineNum = 445;BA.debugLine="m.Remove(m.GetKeyAt(i))";
_m.Remove(_m.GetKeyAt(_i));
 //BA.debugLineNum = 446;BA.debugLine="File.WriteMap(File.DirApp & \"/Data\",\"bookmark\"";
anywheresoftware.b4a.keywords.Common.File.WriteMap(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data","bookmark"+_bookid,_m);
 //BA.debugLineNum = 447;BA.debugLine="Return";
if (true) return "";
 };
 }
};
 //BA.debugLineNum = 452;BA.debugLine="End Sub";
return "";
}
public static String  _deletenote(String _bookid,String _id) throws Exception{
anywheresoftware.b4a.objects.collections.Map _m = null;
 //BA.debugLineNum = 360;BA.debugLine="Sub DeleteNote(BookID As String,ID As String)";
 //BA.debugLineNum = 362;BA.debugLine="Dim m As Map";
_m = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 363;BA.debugLine="m.Initialize";
_m.Initialize();
 //BA.debugLineNum = 365;BA.debugLine="If File.Exists(File.DirApp & \"/Data\",\"note\" & Boo";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data","note"+_bookid)) { 
 //BA.debugLineNum = 366;BA.debugLine="m = File.ReadMap(File.DirApp & \"/Data\",\"note\" &";
_m = anywheresoftware.b4a.keywords.Common.File.ReadMap(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data","note"+_bookid);
 }else {
 //BA.debugLineNum = 368;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 371;BA.debugLine="If m.ContainsKey(ID) Then";
if (_m.ContainsKey((Object)(_id))) { 
 //BA.debugLineNum = 372;BA.debugLine="m.Remove(ID)";
_m.Remove((Object)(_id));
 //BA.debugLineNum = 373;BA.debugLine="File.WriteMap(File.DirApp & \"/Data\",\"note\" & Boo";
anywheresoftware.b4a.keywords.Common.File.WriteMap(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data","note"+_bookid,_m);
 };
 //BA.debugLineNum = 376;BA.debugLine="End Sub";
return "";
}
public static String  _formatsize(float _size) throws Exception{
String[] _unit = null;
double _po = 0;
double _si = 0;
int _i = 0;
 //BA.debugLineNum = 168;BA.debugLine="Sub FormatSize(Size As Float) As String";
 //BA.debugLineNum = 170;BA.debugLine="Dim unit() As String = Array As String(\" بایت\", \"";
_unit = new String[]{" بایت"," کیلو بایت"," مگابایت"," گیگابایت"," ترابایت"," PB"," EB"," ZB"," YB"};
 //BA.debugLineNum = 172;BA.debugLine="If (Size == 0) Then";
if ((_size==0)) { 
 //BA.debugLineNum = 173;BA.debugLine="Return \"خالی\"";
if (true) return "خالی";
 }else {
 //BA.debugLineNum = 176;BA.debugLine="Dim po,si As Double";
_po = 0;
_si = 0;
 //BA.debugLineNum = 177;BA.debugLine="Dim i As Int";
_i = 0;
 //BA.debugLineNum = 179;BA.debugLine="i  = Floor(Logarithm(Size, 1024))";
_i = (int) (anywheresoftware.b4a.keywords.Common.Floor(anywheresoftware.b4a.keywords.Common.Logarithm(_size,1024)));
 //BA.debugLineNum = 180;BA.debugLine="po = Power(1024,i)";
_po = anywheresoftware.b4a.keywords.Common.Power(1024,_i);
 //BA.debugLineNum = 181;BA.debugLine="si = Size / po";
_si = _size/(double)_po;
 //BA.debugLineNum = 183;BA.debugLine="Try";
try { //BA.debugLineNum = 184;BA.debugLine="If i < 0 Then";
if (_i<0) { 
 //BA.debugLineNum = 185;BA.debugLine="Return Round2(Size,2) & \" بایت\"";
if (true) return BA.NumberToString(anywheresoftware.b4a.keywords.Common.Round2(_size,(int) (2)))+" بایت";
 };
 //BA.debugLineNum = 187;BA.debugLine="Return Round2(si,2) & unit(i)";
if (true) return BA.NumberToString(anywheresoftware.b4a.keywords.Common.Round2(_si,(int) (2)))+_unit[_i];
 } 
       catch (Exception e16) {
			ba.setLastException(e16); //BA.debugLineNum = 189;BA.debugLine="Return Size";
if (true) return BA.NumberToString(_size);
 };
 };
 //BA.debugLineNum = 194;BA.debugLine="End Sub";
return "";
}
public static String  _getdate(String _date1) throws Exception{
String[] _res = null;
String _month = "";
String[] _perdate = null;
String[] _pp = null;
 //BA.debugLineNum = 109;BA.debugLine="Public Sub GetDate(date1 As String) As String";
 //BA.debugLineNum = 111;BA.debugLine="Dim res(),month,perDate() As String";
_res = new String[(int) (0)];
java.util.Arrays.fill(_res,"");
_month = "";
_perdate = new String[(int) (0)];
java.util.Arrays.fill(_perdate,"");
 //BA.debugLineNum = 113;BA.debugLine="Dim pp() As String";
_pp = new String[(int) (0)];
java.util.Arrays.fill(_pp,"");
 //BA.debugLineNum = 115;BA.debugLine="pp = Regex.Split(\" \",date1)";
_pp = anywheresoftware.b4a.keywords.Common.Regex.Split(" ",_date1);
 //BA.debugLineNum = 117;BA.debugLine="Try";
try { //BA.debugLineNum = 119;BA.debugLine="If pp(0).IndexOf(\"-\") > -1 Then";
if (_pp[(int) (0)].indexOf("-")>-1) { 
 //BA.debugLineNum = 120;BA.debugLine="res = Regex.Split(\"-\",pp(0))";
_res = anywheresoftware.b4a.keywords.Common.Regex.Split("-",_pp[(int) (0)]);
 }else {
 //BA.debugLineNum = 122;BA.debugLine="res = Regex.Split(\"/\",pp(0))";
_res = anywheresoftware.b4a.keywords.Common.Regex.Split("/",_pp[(int) (0)]);
 };
 //BA.debugLineNum = 125;BA.debugLine="month = res(1)";
_month = _res[(int) (1)];
 //BA.debugLineNum = 127;BA.debugLine="perDate = Regex.Split(\"/\",Julian2Persian(res(0),";
_perdate = anywheresoftware.b4a.keywords.Common.Regex.Split("/",_julian2persian((int)(Double.parseDouble(_res[(int) (0)])),(int)(Double.parseDouble(_month)),(int)(Double.parseDouble(_res[(int) (2)])),"/"));
 //BA.debugLineNum = 129;BA.debugLine="Select Case perDate(1)";
switch (BA.switchObjectToInt(_perdate[(int) (1)],BA.NumberToString(1),BA.NumberToString(2),BA.NumberToString(3),BA.NumberToString(4),BA.NumberToString(5),BA.NumberToString(6),BA.NumberToString(7),BA.NumberToString(8),BA.NumberToString(9),BA.NumberToString(10),BA.NumberToString(11),BA.NumberToString(12))) {
case 0: {
 //BA.debugLineNum = 131;BA.debugLine="month = \"فروردین\"";
_month = "فروردین";
 break; }
case 1: {
 //BA.debugLineNum = 133;BA.debugLine="month = \"اردیبهشت\"";
_month = "اردیبهشت";
 break; }
case 2: {
 //BA.debugLineNum = 135;BA.debugLine="month = \"خرداد\"";
_month = "خرداد";
 break; }
case 3: {
 //BA.debugLineNum = 137;BA.debugLine="month = \"تیر\"";
_month = "تیر";
 break; }
case 4: {
 //BA.debugLineNum = 139;BA.debugLine="month = \"مرداد\"";
_month = "مرداد";
 break; }
case 5: {
 //BA.debugLineNum = 141;BA.debugLine="month = \"شهریور\"";
_month = "شهریور";
 break; }
case 6: {
 //BA.debugLineNum = 143;BA.debugLine="month = \"مهر\"";
_month = "مهر";
 break; }
case 7: {
 //BA.debugLineNum = 145;BA.debugLine="month = \"آبان\"";
_month = "آبان";
 break; }
case 8: {
 //BA.debugLineNum = 147;BA.debugLine="month = \"آذر\"";
_month = "آذر";
 break; }
case 9: {
 //BA.debugLineNum = 149;BA.debugLine="month = \"دی\"";
_month = "دی";
 break; }
case 10: {
 //BA.debugLineNum = 151;BA.debugLine="month = \"بهمن\"";
_month = "بهمن";
 break; }
case 11: {
 //BA.debugLineNum = 153;BA.debugLine="month = \"اسفند\"";
_month = "اسفند";
 break; }
}
;
 //BA.debugLineNum = 156;BA.debugLine="Return perDate(2) & \" \" & month & \" ماه \" & \" \"";
if (true) return _perdate[(int) (2)]+" "+_month+" ماه "+" "+_perdate[(int) (0)];
 } 
       catch (Exception e40) {
			ba.setLastException(e40); //BA.debugLineNum = 159;BA.debugLine="Return date1";
if (true) return _date1;
 };
 //BA.debugLineNum = 162;BA.debugLine="End Sub";
return "";
}
public static String  _getnotecontent(String _bookid,String _sid) throws Exception{
anywheresoftware.b4a.objects.collections.Map _m = null;
anywheresoftware.b4a.objects.collections.Map _m2 = null;
String _te = "";
anywheresoftware.b4j.objects.collections.JSONParser _js = null;
 //BA.debugLineNum = 307;BA.debugLine="Sub GetNoteContent(BookID As String,sID As String)";
 //BA.debugLineNum = 309;BA.debugLine="Dim m As Map";
_m = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 310;BA.debugLine="m.Initialize";
_m.Initialize();
 //BA.debugLineNum = 312;BA.debugLine="If File.Exists(File.DirApp & \"/Data\",\"note\" & Boo";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data","note"+_bookid)) { 
 //BA.debugLineNum = 313;BA.debugLine="m = File.ReadMap(File.DirApp & \"/Data\",\"note\" &";
_m = anywheresoftware.b4a.keywords.Common.File.ReadMap(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data","note"+_bookid);
 }else {
 //BA.debugLineNum = 315;BA.debugLine="Return \"\"";
if (true) return "";
 };
 //BA.debugLineNum = 318;BA.debugLine="If m.ContainsKey(sID) Then";
if (_m.ContainsKey((Object)(_sid))) { 
 //BA.debugLineNum = 319;BA.debugLine="Dim m2 As Map";
_m2 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 320;BA.debugLine="Dim te As String";
_te = "";
 //BA.debugLineNum = 321;BA.debugLine="te = m.Get(sID)";
_te = BA.ObjectToString(_m.Get((Object)(_sid)));
 //BA.debugLineNum = 323;BA.debugLine="Dim js As JSONParser";
_js = new anywheresoftware.b4j.objects.collections.JSONParser();
 //BA.debugLineNum = 324;BA.debugLine="js.Initialize(te)";
_js.Initialize(_te);
 //BA.debugLineNum = 325;BA.debugLine="m2 = js.NextObject";
_m2 = _js.NextObject();
 //BA.debugLineNum = 326;BA.debugLine="Return m2.Get(\"Content\")";
if (true) return BA.ObjectToString(_m2.Get((Object)("Content")));
 }else {
 //BA.debugLineNum = 328;BA.debugLine="Return \"\"";
if (true) return "";
 };
 //BA.debugLineNum = 331;BA.debugLine="End Sub";
return "";
}
public static int  _getnoteid(String _bookid) throws Exception{
anywheresoftware.b4a.objects.collections.Map _m = null;
 //BA.debugLineNum = 292;BA.debugLine="Sub GetNoteID(BookID As String) As Int";
 //BA.debugLineNum = 294;BA.debugLine="Dim m As Map";
_m = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 295;BA.debugLine="m.Initialize";
_m.Initialize();
 //BA.debugLineNum = 297;BA.debugLine="If File.Exists(File.DirApp & \"/Data\",\"note\" & Boo";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data","note"+_bookid)) { 
 //BA.debugLineNum = 298;BA.debugLine="m = File.ReadMap(File.DirApp & \"/Data\",\"note\" &";
_m = anywheresoftware.b4a.keywords.Common.File.ReadMap(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data","note"+_bookid);
 }else {
 //BA.debugLineNum = 300;BA.debugLine="Return 1";
if (true) return (int) (1);
 };
 //BA.debugLineNum = 303;BA.debugLine="Return m.Size + 1";
if (true) return (int) (_m.getSize()+1);
 //BA.debugLineNum = 305;BA.debugLine="End Sub";
return 0;
}
public static anywheresoftware.b4j.objects.ButtonWrapper  _getparent(anywheresoftware.b4j.objects.ButtonWrapper _v) throws Exception{
 //BA.debugLineNum = 164;BA.debugLine="Sub GetParent(v As Button) As Button";
 //BA.debugLineNum = 165;BA.debugLine="Return Null";
if (true) return (anywheresoftware.b4j.objects.ButtonWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.objects.ButtonWrapper(), (javafx.scene.control.Button)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 166;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.collections.Map  _gettopreadbook() throws Exception{
anywheresoftware.b4a.objects.collections.Map _m = null;
anywheresoftware.b4a.objects.collections.Map _m3 = null;
 //BA.debugLineNum = 236;BA.debugLine="Sub GetTopReadBook As Map";
 //BA.debugLineNum = 238;BA.debugLine="Dim m,m3 As Map";
_m = new anywheresoftware.b4a.objects.collections.Map();
_m3 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 239;BA.debugLine="m.Initialize";
_m.Initialize();
 //BA.debugLineNum = 240;BA.debugLine="m3.Initialize";
_m3.Initialize();
 //BA.debugLineNum = 242;BA.debugLine="If File.Exists(File.DirApp & \"/Data\",\"visit_book1";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data","visit_book1")) { 
 //BA.debugLineNum = 243;BA.debugLine="m = File.ReadMap(File.DirApp & \"/Data\",\"visit_bo";
_m = anywheresoftware.b4a.keywords.Common.File.ReadMap(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data","visit_book1");
 }else {
 //BA.debugLineNum = 245;BA.debugLine="Return Null";
if (true) return (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (anywheresoftware.b4a.objects.collections.Map.MyMap)(anywheresoftware.b4a.keywords.Common.Null));
 };
 //BA.debugLineNum = 248;BA.debugLine="SortMapKeys(m,True)";
_sortmapkeys(_m,anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 250;BA.debugLine="Return m";
if (true) return _m;
 //BA.debugLineNum = 252;BA.debugLine="End Sub";
return null;
}
public static String  _julian2persian(int _year,int _month,int _day,String _seprator) throws Exception{
int _daynumber = 0;
byte _kabiseh = (byte)0;
String _s = "";
 //BA.debugLineNum = 43;BA.debugLine="Public Sub Julian2Persian(Year As Int,Month As Int";
 //BA.debugLineNum = 45;BA.debugLine="Dim DayNumber As Int";
_daynumber = 0;
 //BA.debugLineNum = 46;BA.debugLine="Dim Kabiseh As Byte";
_kabiseh = (byte)0;
 //BA.debugLineNum = 47;BA.debugLine="Dim S As String";
_s = "";
 //BA.debugLineNum = 49;BA.debugLine="If Year = 0 And Month = 0 And Day = 0 Then";
if (_year==0 && _month==0 && _day==0) { 
 //BA.debugLineNum = 50;BA.debugLine="Year = DateTime.GetYear(DateTime.Now)";
_year = anywheresoftware.b4a.keywords.Common.DateTime.GetYear(anywheresoftware.b4a.keywords.Common.DateTime.getNow());
 //BA.debugLineNum = 51;BA.debugLine="Month = DateTime.GetMonth(DateTime.Now)";
_month = anywheresoftware.b4a.keywords.Common.DateTime.GetMonth(anywheresoftware.b4a.keywords.Common.DateTime.getNow());
 //BA.debugLineNum = 52;BA.debugLine="Day   = DateTime.GetDayOfMonth(DateTime.Now)";
_day = anywheresoftware.b4a.keywords.Common.DateTime.GetDayOfMonth(anywheresoftware.b4a.keywords.Common.DateTime.getNow());
 };
 //BA.debugLineNum = 55;BA.debugLine="DayNumber = (Year - 622 Mod 1000) Mod 100";
_daynumber = (int) ((_year-622%1000)%100);
 //BA.debugLineNum = 57;BA.debugLine="If (((Year Mod 1000) Mod 100) = DayNumber) Or _";
if ((((_year%1000)%100)==_daynumber) || (((_year%1000)%100)==_daynumber+1)) { 
 //BA.debugLineNum = 59;BA.debugLine="Year = 1300 + ((Year Mod 1000) Mod 100)";
_year = (int) (1300+((_year%1000)%100));
 //BA.debugLineNum = 60;BA.debugLine="Return Year & seprator & Month & seprator & Day";
if (true) return BA.NumberToString(_year)+_seprator+BA.NumberToString(_month)+_seprator+BA.NumberToString(_day);
 };
 //BA.debugLineNum = 63;BA.debugLine="If Year Mod 4 = 0 Then Kabiseh = 1 Else Kabiseh =";
if (_year%4==0) { 
_kabiseh = (byte) (1);}
else {
_kabiseh = (byte) (0);};
 //BA.debugLineNum = 65;BA.debugLine="Select Month";
switch (_month) {
case 1: {
 //BA.debugLineNum = 66;BA.debugLine="Case 1: DayNumber = Day";
_daynumber = _day;
 break; }
case 2: {
 //BA.debugLineNum = 67;BA.debugLine="Case 2: DayNumber = 31 + Day";
_daynumber = (int) (31+_day);
 break; }
case 3: {
 //BA.debugLineNum = 68;BA.debugLine="Case 3: DayNumber = 31 + 28 + Kabiseh + Day";
_daynumber = (int) (31+28+_kabiseh+_day);
 break; }
case 4: {
 //BA.debugLineNum = 69;BA.debugLine="Case 4: DayNumber = 31 + 28 + Kabiseh + 31 + Day";
_daynumber = (int) (31+28+_kabiseh+31+_day);
 break; }
case 5: {
 //BA.debugLineNum = 70;BA.debugLine="Case 5: DayNumber = 31 + 28 + Kabiseh + 31 + 30";
_daynumber = (int) (31+28+_kabiseh+31+30+_day);
 break; }
case 6: {
 //BA.debugLineNum = 71;BA.debugLine="Case 6: DayNumber = 31 + 28 + Kabiseh + 31 + 30";
_daynumber = (int) (31+28+_kabiseh+31+30+31+_day);
 break; }
case 7: {
 //BA.debugLineNum = 72;BA.debugLine="Case 7: DayNumber = 31 + 28 + Kabiseh + 31 + 30";
_daynumber = (int) (31+28+_kabiseh+31+30+31+30+_day);
 break; }
case 8: {
 //BA.debugLineNum = 73;BA.debugLine="Case 8: DayNumber = 31 + 28 + Kabiseh + 31 + 30";
_daynumber = (int) (31+28+_kabiseh+31+30+31+30+31+_day);
 break; }
case 9: {
 //BA.debugLineNum = 74;BA.debugLine="Case 9: DayNumber = 31 + 28 + Kabiseh + 31 + 30";
_daynumber = (int) (31+28+_kabiseh+31+30+31+30+31+31+_day);
 break; }
case 10: {
 //BA.debugLineNum = 75;BA.debugLine="Case 10: DayNumber = 31 + 28 + Kabiseh + 31 + 30";
_daynumber = (int) (31+28+_kabiseh+31+30+31+30+31+31+30+_day);
 break; }
case 11: {
 //BA.debugLineNum = 76;BA.debugLine="Case 11: DayNumber = 31 + 28 + Kabiseh + 31 + 30";
_daynumber = (int) (31+28+_kabiseh+31+30+31+30+31+31+30+31+_day);
 break; }
case 12: {
 //BA.debugLineNum = 77;BA.debugLine="Case 12: DayNumber = 31 + 28 + Kabiseh + 31 + 30";
_daynumber = (int) (31+28+_kabiseh+31+30+31+30+31+31+30+31+30+_day);
 break; }
}
;
 //BA.debugLineNum = 80;BA.debugLine="Year = Year - 622";
_year = (int) (_year-622);
 //BA.debugLineNum = 81;BA.debugLine="Month = Month + 9";
_month = (int) (_month+9);
 //BA.debugLineNum = 83;BA.debugLine="If DayNumber > 79 Then";
if (_daynumber>79) { 
 //BA.debugLineNum = 84;BA.debugLine="DayNumber = DayNumber - (79 - Kabiseh)";
_daynumber = (int) (_daynumber-(79-_kabiseh));
 //BA.debugLineNum = 85;BA.debugLine="Year = Year + 1";
_year = (int) (_year+1);
 }else {
 //BA.debugLineNum = 87;BA.debugLine="DayNumber = DayNumber + (286 + Kabiseh)";
_daynumber = (int) (_daynumber+(286+_kabiseh));
 };
 //BA.debugLineNum = 90;BA.debugLine="If Month > 12 Then Month = Month - 11";
if (_month>12) { 
_month = (int) (_month-11);};
 //BA.debugLineNum = 92;BA.debugLine="If DayNumber <= 186 Then";
if (_daynumber<=186) { 
 //BA.debugLineNum = 93;BA.debugLine="Month = (DayNumber / 31) + 1";
_month = (int) ((_daynumber/(double)31)+1);
 //BA.debugLineNum = 94;BA.debugLine="If (DayNumber Mod 31) = 0 Then Month = Month -";
if ((_daynumber%31)==0) { 
_month = (int) (_month-1);};
 //BA.debugLineNum = 95;BA.debugLine="Day = (DayNumber Mod 31)";
_day = (int) ((_daynumber%31));
 //BA.debugLineNum = 96;BA.debugLine="If Day = 0 Then Day = 31";
if (_day==0) { 
_day = (int) (31);};
 }else {
 //BA.debugLineNum = 98;BA.debugLine="Month = 7 + ((DayNumber - 186) / 30)";
_month = (int) (7+((_daynumber-186)/(double)30));
 //BA.debugLineNum = 99;BA.debugLine="If Month > 12 Then Month = 12";
if (_month>12) { 
_month = (int) (12);};
 //BA.debugLineNum = 100;BA.debugLine="If ((DayNumber - 186) Mod 30) = 0 Then Month =";
if (((_daynumber-186)%30)==0) { 
_month = (int) (_month-1);};
 //BA.debugLineNum = 101;BA.debugLine="Day = (DayNumber - 186) Mod 30";
_day = (int) ((_daynumber-186)%30);
 //BA.debugLineNum = 102;BA.debugLine="If Day = 0 Then Day = 30";
if (_day==0) { 
_day = (int) (30);};
 };
 //BA.debugLineNum = 105;BA.debugLine="Return Year & seprator & Month & seprator & Day";
if (true) return BA.NumberToString(_year)+_seprator+BA.NumberToString(_month)+_seprator+BA.NumberToString(_day);
 //BA.debugLineNum = 107;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.collections.Map  _lastpagevisit(String _sbookid,String _stopicid,String _page,boolean _read) throws Exception{
anywheresoftware.b4a.objects.collections.Map _m1 = null;
String _s = "";
String[] _s1 = null;
 //BA.debugLineNum = 16;BA.debugLine="Sub LastPageVisit(sBookID As String,sTopicID As St";
 //BA.debugLineNum = 18;BA.debugLine="Dim m1 As Map";
_m1 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 20;BA.debugLine="If File.Exists(File.DirApp & \"/Data\",\"lastvisitpa";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data","lastvisitpages")) { 
 //BA.debugLineNum = 21;BA.debugLine="m1 = File.ReadMap(File.DirApp & \"/Data\",\"lastvis";
_m1 = anywheresoftware.b4a.keywords.Common.File.ReadMap(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data","lastvisitpages");
 }else {
 //BA.debugLineNum = 23;BA.debugLine="If Read = True Then Return Null";
if (_read==anywheresoftware.b4a.keywords.Common.True) { 
if (true) return (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (anywheresoftware.b4a.objects.collections.Map.MyMap)(anywheresoftware.b4a.keywords.Common.Null));};
 //BA.debugLineNum = 24;BA.debugLine="m1.Initialize";
_m1.Initialize();
 };
 //BA.debugLineNum = 27;BA.debugLine="If Read Then";
if (_read) { 
 //BA.debugLineNum = 28;BA.debugLine="If m1.ContainsKey(sBookID) Then";
if (_m1.ContainsKey((Object)(_sbookid))) { 
 //BA.debugLineNum = 29;BA.debugLine="Dim s,s1() As String";
_s = "";
_s1 = new String[(int) (0)];
java.util.Arrays.fill(_s1,"");
 //BA.debugLineNum = 30;BA.debugLine="s = m1.Get(sBookID)";
_s = BA.ObjectToString(_m1.Get((Object)(_sbookid)));
 //BA.debugLineNum = 31;BA.debugLine="s1 = Regex.Split(\":\",s)";
_s1 = anywheresoftware.b4a.keywords.Common.Regex.Split(":",_s);
 //BA.debugLineNum = 32;BA.debugLine="Return CreateMap(\"TopicID\":s1(0),\"Page\":s1(1))";
if (true) return anywheresoftware.b4a.keywords.Common.createMap(new Object[] {(Object)("TopicID"),(Object)(_s1[(int) (0)]),(Object)("Page"),(Object)(_s1[(int) (1)])});
 }else {
 //BA.debugLineNum = 34;BA.debugLine="Return Null";
if (true) return (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (anywheresoftware.b4a.objects.collections.Map.MyMap)(anywheresoftware.b4a.keywords.Common.Null));
 };
 }else {
 //BA.debugLineNum = 37;BA.debugLine="m1.Put(sBookID,sTopicID & \":\" & Page)";
_m1.Put((Object)(_sbookid),(Object)(_stopicid+":"+_page));
 //BA.debugLineNum = 38;BA.debugLine="File.WriteMap(File.DirApp & \"/Data\",\"lastvisitpa";
anywheresoftware.b4a.keywords.Common.File.WriteMap(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data","lastvisitpages",_m1);
 };
 //BA.debugLineNum = 41;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.collections.List  _listbookmark(String _bookid) throws Exception{
anywheresoftware.b4a.objects.collections.Map _m = null;
anywheresoftware.b4a.objects.collections.List _ls = null;
int _i = 0;
anywheresoftware.b4j.objects.collections.JSONParser _js = null;
anywheresoftware.b4a.objects.collections.Map _m2 = null;
 //BA.debugLineNum = 395;BA.debugLine="Sub Listbookmark(BookID As String) As List";
 //BA.debugLineNum = 397;BA.debugLine="Dim m As Map";
_m = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 398;BA.debugLine="m.Initialize";
_m.Initialize();
 //BA.debugLineNum = 400;BA.debugLine="If File.Exists(File.DirApp & \"/Data\",\"bookmark\" &";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data","bookmark"+_bookid)) { 
 //BA.debugLineNum = 401;BA.debugLine="m = File.ReadMap(File.DirApp & \"/Data\",\"bookmark";
_m = anywheresoftware.b4a.keywords.Common.File.ReadMap(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data","bookmark"+_bookid);
 }else {
 //BA.debugLineNum = 403;BA.debugLine="Return Null";
if (true) return (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(anywheresoftware.b4a.keywords.Common.Null));
 };
 //BA.debugLineNum = 406;BA.debugLine="Dim ls As List";
_ls = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 407;BA.debugLine="ls.Initialize";
_ls.Initialize();
 //BA.debugLineNum = 409;BA.debugLine="For i = 0 To m.Size - 1";
{
final int step10 = 1;
final int limit10 = (int) (_m.getSize()-1);
for (_i = (int) (0) ; (step10 > 0 && _i <= limit10) || (step10 < 0 && _i >= limit10); _i = ((int)(0 + _i + step10)) ) {
 //BA.debugLineNum = 410;BA.debugLine="Dim js As JSONParser";
_js = new anywheresoftware.b4j.objects.collections.JSONParser();
 //BA.debugLineNum = 411;BA.debugLine="Dim m2 As Map";
_m2 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 412;BA.debugLine="js.Initialize(m.GetValueAt(i))";
_js.Initialize(BA.ObjectToString(_m.GetValueAt(_i)));
 //BA.debugLineNum = 413;BA.debugLine="m2 = js.NextObject";
_m2 = _js.NextObject();
 //BA.debugLineNum = 414;BA.debugLine="m2.Put(\"sType\",\"bookmark\")";
_m2.Put((Object)("sType"),(Object)("bookmark"));
 //BA.debugLineNum = 415;BA.debugLine="ls.Add(m2)";
_ls.Add((Object)(_m2.getObject()));
 }
};
 //BA.debugLineNum = 418;BA.debugLine="Return ls";
if (true) return _ls;
 //BA.debugLineNum = 420;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.collections.List  _listnote(String _bookid) throws Exception{
anywheresoftware.b4a.objects.collections.Map _m = null;
anywheresoftware.b4a.objects.collections.List _ls = null;
int _i = 0;
anywheresoftware.b4j.objects.collections.JSONParser _js = null;
anywheresoftware.b4a.objects.collections.Map _m2 = null;
 //BA.debugLineNum = 333;BA.debugLine="Sub ListNote(BookID As String) As List";
 //BA.debugLineNum = 335;BA.debugLine="Dim m As Map";
_m = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 336;BA.debugLine="m.Initialize";
_m.Initialize();
 //BA.debugLineNum = 338;BA.debugLine="If File.Exists(File.DirApp & \"/Data\",\"note\" & Boo";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data","note"+_bookid)) { 
 //BA.debugLineNum = 339;BA.debugLine="m = File.ReadMap(File.DirApp & \"/Data\",\"note\" &";
_m = anywheresoftware.b4a.keywords.Common.File.ReadMap(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data","note"+_bookid);
 }else {
 //BA.debugLineNum = 341;BA.debugLine="Return Null";
if (true) return (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(anywheresoftware.b4a.keywords.Common.Null));
 };
 //BA.debugLineNum = 344;BA.debugLine="Dim ls As List";
_ls = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 345;BA.debugLine="ls.Initialize";
_ls.Initialize();
 //BA.debugLineNum = 347;BA.debugLine="For i = 0 To m.Size - 1";
{
final int step10 = 1;
final int limit10 = (int) (_m.getSize()-1);
for (_i = (int) (0) ; (step10 > 0 && _i <= limit10) || (step10 < 0 && _i >= limit10); _i = ((int)(0 + _i + step10)) ) {
 //BA.debugLineNum = 348;BA.debugLine="Dim js As JSONParser";
_js = new anywheresoftware.b4j.objects.collections.JSONParser();
 //BA.debugLineNum = 349;BA.debugLine="Dim m2 As Map";
_m2 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 350;BA.debugLine="js.Initialize(m.GetValueAt(i))";
_js.Initialize(BA.ObjectToString(_m.GetValueAt(_i)));
 //BA.debugLineNum = 351;BA.debugLine="m2 = js.NextObject";
_m2 = _js.NextObject();
 //BA.debugLineNum = 352;BA.debugLine="m2.Put(\"sType\",\"note\")";
_m2.Put((Object)("sType"),(Object)("note"));
 //BA.debugLineNum = 353;BA.debugLine="ls.Add(m2)";
_ls.Add((Object)(_m2.getObject()));
 }
};
 //BA.debugLineNum = 356;BA.debugLine="Return ls";
if (true) return _ls;
 //BA.debugLineNum = 358;BA.debugLine="End Sub";
return null;
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 2;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 3;BA.debugLine="Private fx As JFX";
_fx = new anywheresoftware.b4j.objects.JFX();
 //BA.debugLineNum = 4;BA.debugLine="Public BaseUrl As String : BaseUrl = \"http://www.";
_baseurl = "";
 //BA.debugLineNum = 4;BA.debugLine="Public BaseUrl As String : BaseUrl = \"http://www.";
_baseurl = "http://www.iranapp.org/bahonar/";
 //BA.debugLineNum = 5;BA.debugLine="Public URL As String : URL = BaseUrl & \"index.php";
_url = "";
 //BA.debugLineNum = 5;BA.debugLine="Public URL As String : URL = BaseUrl & \"index.php";
_url = _baseurl+"index.php/server/";
 //BA.debugLineNum = 6;BA.debugLine="Public manager As SLPreferences";
_manager = new com.stevel05.Prefs();
 //BA.debugLineNum = 7;BA.debugLine="End Sub";
return "";
}
public static String  _sortmapkeys(anywheresoftware.b4a.objects.collections.Map _m,boolean _sortasc) throws Exception{
anywheresoftware.b4a.objects.collections.List _keyslist = null;
anywheresoftware.b4a.objects.collections.Map _m2 = null;
int _i = 0;
String _key = "";
int _x = 0;
String _val = "";
String _m2key = "";
 //BA.debugLineNum = 254;BA.debugLine="Sub SortMapKeys (m As Map, SortAsc As Boolean)";
 //BA.debugLineNum = 255;BA.debugLine="Private KeysList As List:KeysList.Initialize";
_keyslist = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 255;BA.debugLine="Private KeysList As List:KeysList.Initialize";
_keyslist.Initialize();
 //BA.debugLineNum = 256;BA.debugLine="Private m2 As Map:m2.Initialize";
_m2 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 256;BA.debugLine="Private m2 As Map:m2.Initialize";
_m2.Initialize();
 //BA.debugLineNum = 258;BA.debugLine="For i = 0 To m.Size - 1";
{
final int step5 = 1;
final int limit5 = (int) (_m.getSize()-1);
for (_i = (int) (0) ; (step5 > 0 && _i <= limit5) || (step5 < 0 && _i >= limit5); _i = ((int)(0 + _i + step5)) ) {
 //BA.debugLineNum = 259;BA.debugLine="Private key As String = m.GetKeyAt(i)";
_key = BA.ObjectToString(_m.GetKeyAt(_i));
 //BA.debugLineNum = 260;BA.debugLine="KeysList.Add(key)";
_keyslist.Add((Object)(_key));
 }
};
 //BA.debugLineNum = 263;BA.debugLine="KeysList.Sort(SortAsc)";
_keyslist.Sort(_sortasc);
 //BA.debugLineNum = 265;BA.debugLine="For x=0 To KeysList.Size - 1";
{
final int step10 = 1;
final int limit10 = (int) (_keyslist.getSize()-1);
for (_x = (int) (0) ; (step10 > 0 && _x <= limit10) || (step10 < 0 && _x >= limit10); _x = ((int)(0 + _x + step10)) ) {
 //BA.debugLineNum = 266;BA.debugLine="Private key As String = KeysList.Get(x)";
_key = BA.ObjectToString(_keyslist.Get(_x));
 //BA.debugLineNum = 267;BA.debugLine="Private val As String = m.Get(key)";
_val = BA.ObjectToString(_m.Get((Object)(_key)));
 //BA.debugLineNum = 268;BA.debugLine="m2.Put(key, val)";
_m2.Put((Object)(_key),(Object)(_val));
 }
};
 //BA.debugLineNum = 270;BA.debugLine="m.Clear";
_m.Clear();
 //BA.debugLineNum = 271;BA.debugLine="For Each m2Key As String In m2.Keys";
final anywheresoftware.b4a.BA.IterableList group16 = _m2.Keys();
final int groupLen16 = group16.getSize();
for (int index16 = 0;index16 < groupLen16 ;index16++){
_m2key = BA.ObjectToString(group16.Get(index16));
 //BA.debugLineNum = 272;BA.debugLine="m.Put(m2Key, m2.Get(m2Key))";
_m.Put((Object)(_m2key),_m2.Get((Object)(_m2key)));
 }
;
 //BA.debugLineNum = 274;BA.debugLine="End Sub";
return "";
}
public static String  _writebookmark(String _bookid,String _topicid,String _offset) throws Exception{
anywheresoftware.b4a.objects.collections.Map _m = null;
anywheresoftware.b4j.objects.collections.JSONParser.JSONGenerator _js = null;
 //BA.debugLineNum = 378;BA.debugLine="Sub WriteBookmark(BookID As String,TopicID As Stri";
 //BA.debugLineNum = 380;BA.debugLine="Dim m As Map";
_m = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 381;BA.debugLine="m.Initialize";
_m.Initialize();
 //BA.debugLineNum = 383;BA.debugLine="If File.Exists(File.DirApp & \"/Data\",\"bookmark\" &";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data","bookmark"+_bookid)) { 
 //BA.debugLineNum = 384;BA.debugLine="m = File.ReadMap(File.DirApp & \"/Data\",\"bookmark";
_m = anywheresoftware.b4a.keywords.Common.File.ReadMap(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data","bookmark"+_bookid);
 };
 //BA.debugLineNum = 387;BA.debugLine="Dim js As JSONGenerator";
_js = new anywheresoftware.b4j.objects.collections.JSONParser.JSONGenerator();
 //BA.debugLineNum = 388;BA.debugLine="js.Initialize(CreateMap(\"TopicID\":TopicID,\"Offset";
_js.Initialize(anywheresoftware.b4a.keywords.Common.createMap(new Object[] {(Object)("TopicID"),(Object)(_topicid),(Object)("Offset"),(Object)(_offset)}));
 //BA.debugLineNum = 390;BA.debugLine="m.Put(Offset,js.ToString)";
_m.Put((Object)(_offset),(Object)(_js.ToString()));
 //BA.debugLineNum = 391;BA.debugLine="File.WriteMap(File.DirApp & \"/Data\",\"bookmark\" &";
anywheresoftware.b4a.keywords.Common.File.WriteMap(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data","bookmark"+_bookid,_m);
 //BA.debugLineNum = 393;BA.debugLine="End Sub";
return "";
}
public static String  _writecounterreadbook(String _bookname) throws Exception{
anywheresoftware.b4a.objects.collections.Map _m = null;
int _i = 0;
 //BA.debugLineNum = 214;BA.debugLine="Sub WriteCounterReadBook(BookName As String)";
 //BA.debugLineNum = 216;BA.debugLine="Dim m As Map";
_m = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 217;BA.debugLine="m.Initialize";
_m.Initialize();
 //BA.debugLineNum = 219;BA.debugLine="If File.Exists(File.DirApp & \"/Data\",\"visit_book1";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data","visit_book1")) { 
 //BA.debugLineNum = 220;BA.debugLine="m = File.ReadMap(File.DirApp & \"/Data\",\"visit_bo";
_m = anywheresoftware.b4a.keywords.Common.File.ReadMap(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data","visit_book1");
 };
 //BA.debugLineNum = 223;BA.debugLine="If m.ContainsKey(BookName) Then";
if (_m.ContainsKey((Object)(_bookname))) { 
 //BA.debugLineNum = 224;BA.debugLine="Dim i As Int";
_i = 0;
 //BA.debugLineNum = 225;BA.debugLine="i = m.Get(BookName)";
_i = (int)(BA.ObjectToNumber(_m.Get((Object)(_bookname))));
 //BA.debugLineNum = 226;BA.debugLine="i = i + 1";
_i = (int) (_i+1);
 //BA.debugLineNum = 227;BA.debugLine="m.Put(BookName,i)";
_m.Put((Object)(_bookname),(Object)(_i));
 }else {
 //BA.debugLineNum = 229;BA.debugLine="m.Put(BookName,\"1\")";
_m.Put((Object)(_bookname),(Object)("1"));
 };
 //BA.debugLineNum = 232;BA.debugLine="File.WriteMap(File.DirApp & \"/Data\",\"visit_book1\"";
anywheresoftware.b4a.keywords.Common.File.WriteMap(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data","visit_book1",_m);
 //BA.debugLineNum = 234;BA.debugLine="End Sub";
return "";
}
public static String  _writenote(String _bookid,String _topicid,String _id,String _val) throws Exception{
anywheresoftware.b4a.objects.collections.Map _m = null;
anywheresoftware.b4j.objects.collections.JSONParser.JSONGenerator _js = null;
 //BA.debugLineNum = 276;BA.debugLine="Sub WriteNote(BookID As String,TopicID As String,I";
 //BA.debugLineNum = 278;BA.debugLine="Dim m As Map";
_m = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 279;BA.debugLine="m.Initialize";
_m.Initialize();
 //BA.debugLineNum = 281;BA.debugLine="If File.Exists(File.DirApp & \"/Data\",\"note\" & Boo";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data","note"+_bookid)) { 
 //BA.debugLineNum = 282;BA.debugLine="m = File.ReadMap(File.DirApp & \"/Data\",\"note\" &";
_m = anywheresoftware.b4a.keywords.Common.File.ReadMap(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data","note"+_bookid);
 };
 //BA.debugLineNum = 285;BA.debugLine="Dim js As JSONGenerator";
_js = new anywheresoftware.b4j.objects.collections.JSONParser.JSONGenerator();
 //BA.debugLineNum = 286;BA.debugLine="js.Initialize(CreateMap(\"TopicID\":TopicID,\"ID\":ID";
_js.Initialize(anywheresoftware.b4a.keywords.Common.createMap(new Object[] {(Object)("TopicID"),(Object)(_topicid),(Object)("ID"),(Object)(_id),(Object)("Content"),(Object)(_val)}));
 //BA.debugLineNum = 287;BA.debugLine="m.Put(ID,js.ToString)";
_m.Put((Object)(_id),(Object)(_js.ToString()));
 //BA.debugLineNum = 288;BA.debugLine="File.WriteMap(File.DirApp & \"/Data\",\"note\" & Book";
anywheresoftware.b4a.keywords.Common.File.WriteMap(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data","note"+_bookid,_m);
 //BA.debugLineNum = 290;BA.debugLine="End Sub";
return "";
}
}
