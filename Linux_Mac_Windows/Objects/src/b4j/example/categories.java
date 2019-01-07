package b4j.example;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.debug.*;

public class categories extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    public static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new  anywheresoftware.b4j.objects.FxBA("b4j.example", "b4j.example.categories", this);
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            ba.htSubs = htSubs;
             
        }
        if (BA.isShellModeRuntimeCheck(ba))
                this.getClass().getMethod("_class_globals", b4j.example.categories.class).invoke(this, new Object[] {null});
        else
            ba.raiseEvent2(null, true, "class_globals", false);
    }

 public anywheresoftware.b4a.keywords.Common __c = null;
public anywheresoftware.b4j.objects.SQL _sql1 = null;
public anywheresoftware.b4a.agraham.byteconverter.ByteConverter _bc = null;
public b4j.example.httputils2service _httputils2service = null;
public b4j.example.main _main = null;
public b4j.example.actbookmenu _actbookmenu = null;
public b4j.example.actcontent _actcontent = null;
public b4j.example.actdownload _actdownload = null;
public b4j.example.actsitesocial _actsitesocial = null;
public b4j.example.library _library = null;
public b4j.example.actmessage _actmessage = null;
public b4j.example.actsendpoint _actsendpoint = null;
public b4j.example.actpoints _actpoints = null;
public b4j.example.actabout _actabout = null;
public b4j.example.actcontact _actcontact = null;
public b4j.example.dbutils _dbutils = null;
public b4j.example.actbookinformation _actbookinformation = null;
public b4j.example.actsetting _actsetting = null;
public b4j.example.actsearchbook _actsearchbook = null;
public String  _addbook(anywheresoftware.b4a.objects.collections.List _data) throws Exception{
anywheresoftware.b4a.objects.collections.List _ls = null;
int _i = 0;
anywheresoftware.b4a.objects.collections.Map _temp = null;
 //BA.debugLineNum = 32;BA.debugLine="Public Sub AddBook(Data As List)";
 //BA.debugLineNum = 34;BA.debugLine="Dim ls As List";
_ls = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 35;BA.debugLine="ls.Initialize";
_ls.Initialize();
 //BA.debugLineNum = 37;BA.debugLine="For i = 0 To Data.Size - 1";
{
final int step3 = 1;
final int limit3 = (int) (_data.getSize()-1);
for (_i = (int) (0) ; (step3 > 0 && _i <= limit3) || (step3 < 0 && _i >= limit3); _i = ((int)(0 + _i + step3)) ) {
 //BA.debugLineNum = 39;BA.debugLine="Dim temp As Map";
_temp = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 41;BA.debugLine="temp = Data.Get(i)";
_temp.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_data.Get(_i)));
 //BA.debugLineNum = 43;BA.debugLine="ls.Add(temp)";
_ls.Add((Object)(_temp.getObject()));
 }
};
 //BA.debugLineNum = 47;BA.debugLine="DBUtils.InsertMaps(sql1,\"tbl_book\",ls)";
_dbutils._insertmaps(_sql1,"tbl_book",_ls);
 //BA.debugLineNum = 49;BA.debugLine="End Sub";
return "";
}
public String  _addcategory(anywheresoftware.b4a.objects.collections.List _data) throws Exception{
int _i = 0;
anywheresoftware.b4a.objects.collections.Map _temp = null;
String _count = "";
String _parent = "";
String _id = "";
String _title = "";
 //BA.debugLineNum = 13;BA.debugLine="Public Sub AddCategory(Data As List)";
 //BA.debugLineNum = 15;BA.debugLine="For i = 0 To Data.Size - 1";
{
final int step1 = 1;
final int limit1 = (int) (_data.getSize()-1);
for (_i = (int) (0) ; (step1 > 0 && _i <= limit1) || (step1 < 0 && _i >= limit1); _i = ((int)(0 + _i + step1)) ) {
 //BA.debugLineNum = 17;BA.debugLine="Dim temp As Map";
_temp = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 18;BA.debugLine="temp = Data.Get(i)";
_temp.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_data.Get(_i)));
 //BA.debugLineNum = 20;BA.debugLine="Dim count,parent,id,title As String";
_count = "";
_parent = "";
_id = "";
_title = "";
 //BA.debugLineNum = 21;BA.debugLine="parent = temp.Get(\"sParent\")";
_parent = BA.ObjectToString(_temp.Get((Object)("sParent")));
 //BA.debugLineNum = 22;BA.debugLine="id = temp.Get(\"sID\")";
_id = BA.ObjectToString(_temp.Get((Object)("sID")));
 //BA.debugLineNum = 23;BA.debugLine="title = temp.Get(\"sTitle\")";
_title = BA.ObjectToString(_temp.Get((Object)("sTitle")));
 //BA.debugLineNum = 24;BA.debugLine="count = temp.Get(\"sCount\")";
_count = BA.ObjectToString(_temp.Get((Object)("sCount")));
 //BA.debugLineNum = 26;BA.debugLine="sql1.ExecNonQuery2(\"INSERT INTO tbl_category(sID";
_sql1.ExecNonQuery2("INSERT INTO tbl_category(sID,sTitle,sParent,sCount) VALUES(?,?,?,?)",anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{_id,_title,_parent,_count}));
 }
};
 //BA.debugLineNum = 30;BA.debugLine="End Sub";
return "";
}
public String  _class_globals() throws Exception{
 //BA.debugLineNum = 2;BA.debugLine="Private Sub Class_Globals";
 //BA.debugLineNum = 3;BA.debugLine="Private sql1 As SQL";
_sql1 = new anywheresoftware.b4j.objects.SQL();
 //BA.debugLineNum = 4;BA.debugLine="Private BC As ByteConverter";
_bc = new anywheresoftware.b4a.agraham.byteconverter.ByteConverter();
 //BA.debugLineNum = 5;BA.debugLine="End Sub";
return "";
}
public String  _close() throws Exception{
 //BA.debugLineNum = 297;BA.debugLine="public Sub Close";
 //BA.debugLineNum = 298;BA.debugLine="sql1.Close";
_sql1.Close();
 //BA.debugLineNum = 299;BA.debugLine="End Sub";
return "";
}
public String  _downloadedbook(String _id,int _state) throws Exception{
 //BA.debugLineNum = 225;BA.debugLine="public Sub DownloadedBook(ID As String,State As In";
 //BA.debugLineNum = 226;BA.debugLine="sql1.ExecNonQuery(\"UPDATE tbl_book SET sDownloade";
_sql1.ExecNonQuery("UPDATE tbl_book SET sDownloaded ='"+BA.NumberToString(_state)+"' WHERE sID = '"+_id+"'");
 //BA.debugLineNum = 227;BA.debugLine="End Sub";
return "";
}
public anywheresoftware.b4a.objects.collections.List  _getbook(int _parent,String _search,boolean _filterlanguage) throws Exception{
anywheresoftware.b4j.objects.SQL.ResultSetWrapper _c = null;
anywheresoftware.b4a.objects.collections.List _ls = null;
anywheresoftware.b4a.objects.collections.Map _temp = null;
 //BA.debugLineNum = 51;BA.debugLine="public Sub GetBook(Parent As Int,Search As String,";
 //BA.debugLineNum = 53;BA.debugLine="Dim c As ResultSet";
_c = new anywheresoftware.b4j.objects.SQL.ResultSetWrapper();
 //BA.debugLineNum = 54;BA.debugLine="Dim ls As List";
_ls = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 56;BA.debugLine="ls.Initialize";
_ls.Initialize();
 //BA.debugLineNum = 58;BA.debugLine="If Search.Length = 0 Then";
if (_search.length()==0) { 
 //BA.debugLineNum = 59;BA.debugLine="If FilterLanguage = False Then";
if (_filterlanguage==__c.False) { 
 //BA.debugLineNum = 60;BA.debugLine="c = sql1.ExecQuery2(\"SELECT * FROM tbl_book WHE";
_c = _sql1.ExecQuery2("SELECT * FROM tbl_book WHERE sCategoryID =?",anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{BA.NumberToString(_parent)}));
 }else {
 //BA.debugLineNum = 62;BA.debugLine="c = sql1.ExecQuery2(\"SELECT * FROM tbl_book WHE";
_c = _sql1.ExecQuery2("SELECT * FROM tbl_book WHERE sLanguage =?",anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{_search}));
 };
 }else if(_search.length()>0) { 
 //BA.debugLineNum = 66;BA.debugLine="If FilterLanguage = False Then";
if (_filterlanguage==__c.False) { 
 //BA.debugLineNum = 67;BA.debugLine="c = sql1.ExecQuery(\"SELECT * FROM tbl_book WHER";
_c = _sql1.ExecQuery("SELECT * FROM tbl_book WHERE sTitle LIKE '%"+_search+"%' OR sDescription LIKE '%"+_search+"%'");
 }else {
 //BA.debugLineNum = 69;BA.debugLine="c = sql1.ExecQuery2(\"SELECT * FROM tbl_book WHE";
_c = _sql1.ExecQuery2("SELECT * FROM tbl_book WHERE sLanguage =?",anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{_search}));
 };
 };
 //BA.debugLineNum = 73;BA.debugLine="Do While c.NextRow";
while (_c.NextRow()) {
 //BA.debugLineNum = 75;BA.debugLine="Dim temp As Map";
_temp = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 76;BA.debugLine="temp.Initialize";
_temp.Initialize();
 //BA.debugLineNum = 78;BA.debugLine="temp.Put(\"sID\",c.GetInt(\"sID\"))";
_temp.Put((Object)("sID"),(Object)(_c.GetInt("sID")));
 //BA.debugLineNum = 79;BA.debugLine="temp.Put(\"sCategoryID\",c.GetString(\"sCategoryID\"";
_temp.Put((Object)("sCategoryID"),(Object)(_c.GetString("sCategoryID")));
 //BA.debugLineNum = 80;BA.debugLine="temp.Put(\"sTitle\",c.GetString(\"sTitle\"))";
_temp.Put((Object)("sTitle"),(Object)(_c.GetString("sTitle")));
 //BA.debugLineNum = 81;BA.debugLine="temp.Put(\"sCover\",c.GetInt(\"sCover\"))";
_temp.Put((Object)("sCover"),(Object)(_c.GetInt("sCover")));
 //BA.debugLineNum = 82;BA.debugLine="temp.Put(\"sAuthor\",c.GetString(\"sAuthor\"))";
_temp.Put((Object)("sAuthor"),(Object)(_c.GetString("sAuthor")));
 //BA.debugLineNum = 83;BA.debugLine="temp.Put(\"sRate\",c.GetString(\"sRate\"))";
_temp.Put((Object)("sRate"),(Object)(_c.GetString("sRate")));
 //BA.debugLineNum = 84;BA.debugLine="temp.Put(\"sDescription\",c.GetString(\"sDescriptio";
_temp.Put((Object)("sDescription"),(Object)(_c.GetString("sDescription")));
 //BA.debugLineNum = 85;BA.debugLine="temp.Put(\"sFileSize\",c.GetString(\"sFileSize\"))";
_temp.Put((Object)("sFileSize"),(Object)(_c.GetString("sFileSize")));
 //BA.debugLineNum = 86;BA.debugLine="temp.Put(\"sLanguage\",c.GetString(\"sLanguage\"))";
_temp.Put((Object)("sLanguage"),(Object)(_c.GetString("sLanguage")));
 //BA.debugLineNum = 87;BA.debugLine="temp.Put(\"sDownload\",c.GetString(\"sDownload\"))";
_temp.Put((Object)("sDownload"),(Object)(_c.GetString("sDownload")));
 //BA.debugLineNum = 88;BA.debugLine="temp.Put(\"sPublishDate\",c.GetString(\"sPublishDat";
_temp.Put((Object)("sPublishDate"),(Object)(_c.GetString("sPublishDate")));
 //BA.debugLineNum = 89;BA.debugLine="temp.Put(\"sPDF\",c.GetString(\"sPDF\"))";
_temp.Put((Object)("sPDF"),(Object)(_c.GetString("sPDF")));
 //BA.debugLineNum = 90;BA.debugLine="ls.Add(temp)";
_ls.Add((Object)(_temp.getObject()));
 }
;
 //BA.debugLineNum = 94;BA.debugLine="Return ls";
if (true) return _ls;
 //BA.debugLineNum = 96;BA.debugLine="End Sub";
return null;
}
public anywheresoftware.b4a.objects.collections.List  _getbookauthor() throws Exception{
anywheresoftware.b4j.objects.SQL.ResultSetWrapper _c = null;
anywheresoftware.b4a.objects.collections.List _ls = null;
 //BA.debugLineNum = 138;BA.debugLine="public Sub GetBookAuthor As List";
 //BA.debugLineNum = 140;BA.debugLine="Dim c As ResultSet";
_c = new anywheresoftware.b4j.objects.SQL.ResultSetWrapper();
 //BA.debugLineNum = 141;BA.debugLine="Dim ls As List";
_ls = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 143;BA.debugLine="ls.Initialize";
_ls.Initialize();
 //BA.debugLineNum = 144;BA.debugLine="c = sql1.ExecQuery(\"SELECT sAuthor FROM tbl_book";
_c = _sql1.ExecQuery("SELECT sAuthor FROM tbl_book GROUP BY sAuthor");
 //BA.debugLineNum = 146;BA.debugLine="Do While c.NextRow";
while (_c.NextRow()) {
 //BA.debugLineNum = 147;BA.debugLine="ls.Add(c.GetString(\"sAuthor\"))";
_ls.Add((Object)(_c.GetString("sAuthor")));
 }
;
 //BA.debugLineNum = 150;BA.debugLine="Return ls";
if (true) return _ls;
 //BA.debugLineNum = 152;BA.debugLine="End Sub";
return null;
}
public int  _getbookcount() throws Exception{
anywheresoftware.b4j.objects.SQL.ResultSetWrapper _c = null;
anywheresoftware.b4a.objects.collections.List _ls = null;
int _i = 0;
 //BA.debugLineNum = 229;BA.debugLine="public Sub GetBookCount As Int";
 //BA.debugLineNum = 231;BA.debugLine="Dim c As ResultSet";
_c = new anywheresoftware.b4j.objects.SQL.ResultSetWrapper();
 //BA.debugLineNum = 232;BA.debugLine="Dim ls As List";
_ls = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 234;BA.debugLine="ls.Initialize";
_ls.Initialize();
 //BA.debugLineNum = 236;BA.debugLine="c = sql1.ExecQuery(\"SELECT sAuthor FROM tbl_book\"";
_c = _sql1.ExecQuery("SELECT sAuthor FROM tbl_book");
 //BA.debugLineNum = 238;BA.debugLine="Dim i As Int = 0";
_i = (int) (0);
 //BA.debugLineNum = 240;BA.debugLine="Do While c.NextRow";
while (_c.NextRow()) {
 //BA.debugLineNum = 241;BA.debugLine="i = i + 1";
_i = (int) (_i+1);
 }
;
 //BA.debugLineNum = 244;BA.debugLine="Return i";
if (true) return _i;
 //BA.debugLineNum = 246;BA.debugLine="End Sub";
return 0;
}
public anywheresoftware.b4a.objects.collections.Map  _getbookinformation(String _id) throws Exception{
anywheresoftware.b4j.objects.SQL.ResultSetWrapper _c = null;
anywheresoftware.b4a.objects.collections.List _ls = null;
anywheresoftware.b4a.objects.collections.Map _temp = null;
 //BA.debugLineNum = 170;BA.debugLine="public Sub GetBookInformation(id As String) As Map";
 //BA.debugLineNum = 172;BA.debugLine="Dim c As ResultSet";
_c = new anywheresoftware.b4j.objects.SQL.ResultSetWrapper();
 //BA.debugLineNum = 173;BA.debugLine="Dim ls As List";
_ls = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 175;BA.debugLine="ls.Initialize";
_ls.Initialize();
 //BA.debugLineNum = 176;BA.debugLine="c = sql1.ExecQuery(\"SELECT * FROM tbl_book WHERE";
_c = _sql1.ExecQuery("SELECT * FROM tbl_book WHERE sID ='"+_id+"'");
 //BA.debugLineNum = 178;BA.debugLine="If c.NextRow = False Then Return Null";
if (_c.NextRow()==__c.False) { 
if (true) return (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (anywheresoftware.b4a.objects.collections.Map.MyMap)(__c.Null));};
 //BA.debugLineNum = 180;BA.debugLine="Dim temp As Map";
_temp = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 181;BA.debugLine="temp.Initialize";
_temp.Initialize();
 //BA.debugLineNum = 183;BA.debugLine="temp.Put(\"sID\",c.GetInt(\"sID\"))";
_temp.Put((Object)("sID"),(Object)(_c.GetInt("sID")));
 //BA.debugLineNum = 184;BA.debugLine="temp.Put(\"sCategoryID\",c.GetString(\"sCategoryID\")";
_temp.Put((Object)("sCategoryID"),(Object)(_c.GetString("sCategoryID")));
 //BA.debugLineNum = 185;BA.debugLine="temp.Put(\"sTitle\",c.GetString(\"sTitle\"))";
_temp.Put((Object)("sTitle"),(Object)(_c.GetString("sTitle")));
 //BA.debugLineNum = 186;BA.debugLine="temp.Put(\"sCover\",c.GetInt(\"sCover\"))";
_temp.Put((Object)("sCover"),(Object)(_c.GetInt("sCover")));
 //BA.debugLineNum = 187;BA.debugLine="temp.Put(\"sAuthor\",c.GetString(\"sAuthor\"))";
_temp.Put((Object)("sAuthor"),(Object)(_c.GetString("sAuthor")));
 //BA.debugLineNum = 188;BA.debugLine="temp.Put(\"sRate\",c.GetString(\"sRate\"))";
_temp.Put((Object)("sRate"),(Object)(_c.GetString("sRate")));
 //BA.debugLineNum = 189;BA.debugLine="temp.Put(\"sDescription\",c.GetString(\"sDescription";
_temp.Put((Object)("sDescription"),(Object)(_c.GetString("sDescription")));
 //BA.debugLineNum = 190;BA.debugLine="temp.Put(\"sFileSize\",c.GetString(\"sFileSize\"))";
_temp.Put((Object)("sFileSize"),(Object)(_c.GetString("sFileSize")));
 //BA.debugLineNum = 191;BA.debugLine="temp.Put(\"sLanguage\",c.GetString(\"sLanguage\"))";
_temp.Put((Object)("sLanguage"),(Object)(_c.GetString("sLanguage")));
 //BA.debugLineNum = 192;BA.debugLine="temp.Put(\"sDownload\",c.GetString(\"sDownload\"))";
_temp.Put((Object)("sDownload"),(Object)(_c.GetString("sDownload")));
 //BA.debugLineNum = 193;BA.debugLine="temp.Put(\"sPDF\",c.GetString(\"sPDF\"))";
_temp.Put((Object)("sPDF"),(Object)(_c.GetString("sPDF")));
 //BA.debugLineNum = 194;BA.debugLine="temp.Put(\"sPublishDate\",c.GetString(\"sPublishDate";
_temp.Put((Object)("sPublishDate"),(Object)(_c.GetString("sPublishDate")));
 //BA.debugLineNum = 196;BA.debugLine="Return temp";
if (true) return _temp;
 //BA.debugLineNum = 198;BA.debugLine="End Sub";
return null;
}
public anywheresoftware.b4a.objects.collections.List  _getbooktopic() throws Exception{
anywheresoftware.b4j.objects.SQL.ResultSetWrapper _c = null;
anywheresoftware.b4a.objects.collections.List _ls = null;
 //BA.debugLineNum = 154;BA.debugLine="public Sub GetBookTopic As List";
 //BA.debugLineNum = 156;BA.debugLine="Dim c As ResultSet";
_c = new anywheresoftware.b4j.objects.SQL.ResultSetWrapper();
 //BA.debugLineNum = 157;BA.debugLine="Dim ls As List";
_ls = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 159;BA.debugLine="ls.Initialize";
_ls.Initialize();
 //BA.debugLineNum = 160;BA.debugLine="c = sql1.ExecQuery(\"SELECT sTitle FROM tbl_book G";
_c = _sql1.ExecQuery("SELECT sTitle FROM tbl_book GROUP BY sTitle");
 //BA.debugLineNum = 162;BA.debugLine="Do While c.NextRow";
while (_c.NextRow()) {
 //BA.debugLineNum = 163;BA.debugLine="ls.Add(c.GetString(\"sTitle\"))";
_ls.Add((Object)(_c.GetString("sTitle")));
 }
;
 //BA.debugLineNum = 166;BA.debugLine="Return ls";
if (true) return _ls;
 //BA.debugLineNum = 168;BA.debugLine="End Sub";
return null;
}
public anywheresoftware.b4a.objects.collections.List  _getcategory(int _cat) throws Exception{
anywheresoftware.b4j.objects.SQL.ResultSetWrapper _c = null;
anywheresoftware.b4a.objects.collections.List _ls = null;
anywheresoftware.b4a.objects.collections.Map _temp = null;
 //BA.debugLineNum = 200;BA.debugLine="public Sub GetCategory(Cat As Int) As List";
 //BA.debugLineNum = 202;BA.debugLine="Dim c As ResultSet";
_c = new anywheresoftware.b4j.objects.SQL.ResultSetWrapper();
 //BA.debugLineNum = 203;BA.debugLine="Dim ls As List";
_ls = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 205;BA.debugLine="ls.Initialize";
_ls.Initialize();
 //BA.debugLineNum = 206;BA.debugLine="c = sql1.ExecQuery2(\"SELECT * FROM tbl_category W";
_c = _sql1.ExecQuery2("SELECT * FROM tbl_category WHERE sParent =?",anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{BA.NumberToString(_cat)}));
 //BA.debugLineNum = 208;BA.debugLine="Do While c.NextRow";
while (_c.NextRow()) {
 //BA.debugLineNum = 210;BA.debugLine="Dim temp As Map";
_temp = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 211;BA.debugLine="temp.Initialize";
_temp.Initialize();
 //BA.debugLineNum = 213;BA.debugLine="temp.Put(\"sID\",c.GetInt(\"sID\"))";
_temp.Put((Object)("sID"),(Object)(_c.GetInt("sID")));
 //BA.debugLineNum = 214;BA.debugLine="temp.Put(\"sTitle\",c.GetString(\"sTitle\"))";
_temp.Put((Object)("sTitle"),(Object)(_c.GetString("sTitle")));
 //BA.debugLineNum = 215;BA.debugLine="temp.Put(\"sParent\",c.GetInt(\"sParent\"))";
_temp.Put((Object)("sParent"),(Object)(_c.GetInt("sParent")));
 //BA.debugLineNum = 216;BA.debugLine="temp.Put(\"sCount\",c.GetInt(\"sCount\"))";
_temp.Put((Object)("sCount"),(Object)(_c.GetInt("sCount")));
 //BA.debugLineNum = 217;BA.debugLine="ls.Add(temp)";
_ls.Add((Object)(_temp.getObject()));
 }
;
 //BA.debugLineNum = 221;BA.debugLine="Return ls";
if (true) return _ls;
 //BA.debugLineNum = 223;BA.debugLine="End Sub";
return null;
}
public anywheresoftware.b4a.objects.collections.List  _getdownloadedbook(String _fn,String _fv) throws Exception{
anywheresoftware.b4j.objects.SQL.ResultSetWrapper _c = null;
anywheresoftware.b4a.objects.collections.List _ls = null;
String _s = "";
anywheresoftware.b4a.objects.collections.Map _temp = null;
 //BA.debugLineNum = 98;BA.debugLine="public Sub GetDownloadedBook(FN As String,FV As St";
 //BA.debugLineNum = 100;BA.debugLine="Dim c As ResultSet";
_c = new anywheresoftware.b4j.objects.SQL.ResultSetWrapper();
 //BA.debugLineNum = 101;BA.debugLine="Dim ls As List";
_ls = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 103;BA.debugLine="ls.Initialize";
_ls.Initialize();
 //BA.debugLineNum = 104;BA.debugLine="Dim s As String";
_s = "";
 //BA.debugLineNum = 105;BA.debugLine="s = \"SELECT * FROM tbl_book WHERE sDownloaded ='1";
_s = "SELECT * FROM tbl_book WHERE sDownloaded ='1' ";
 //BA.debugLineNum = 107;BA.debugLine="If FN.Length > 0 Then";
if (_fn.length()>0) { 
 //BA.debugLineNum = 108;BA.debugLine="s = s & \"AND \" & FN & \" ='\" & FV & \"'\"";
_s = _s+"AND "+_fn+" ='"+_fv+"'";
 };
 //BA.debugLineNum = 111;BA.debugLine="c = sql1.ExecQuery(s)";
_c = _sql1.ExecQuery(_s);
 //BA.debugLineNum = 113;BA.debugLine="Do While c.NextRow";
while (_c.NextRow()) {
 //BA.debugLineNum = 115;BA.debugLine="Dim temp As Map";
_temp = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 116;BA.debugLine="temp.Initialize";
_temp.Initialize();
 //BA.debugLineNum = 118;BA.debugLine="temp.Put(\"sID\",c.GetInt(\"sID\"))";
_temp.Put((Object)("sID"),(Object)(_c.GetInt("sID")));
 //BA.debugLineNum = 119;BA.debugLine="temp.Put(\"sCategoryID\",c.GetString(\"sCategoryID\"";
_temp.Put((Object)("sCategoryID"),(Object)(_c.GetString("sCategoryID")));
 //BA.debugLineNum = 120;BA.debugLine="temp.Put(\"sTitle\",c.GetString(\"sTitle\"))";
_temp.Put((Object)("sTitle"),(Object)(_c.GetString("sTitle")));
 //BA.debugLineNum = 121;BA.debugLine="temp.Put(\"sCover\",c.GetInt(\"sCover\"))";
_temp.Put((Object)("sCover"),(Object)(_c.GetInt("sCover")));
 //BA.debugLineNum = 122;BA.debugLine="temp.Put(\"sAuthor\",c.GetString(\"sAuthor\"))";
_temp.Put((Object)("sAuthor"),(Object)(_c.GetString("sAuthor")));
 //BA.debugLineNum = 123;BA.debugLine="temp.Put(\"sRate\",c.GetString(\"sRate\"))";
_temp.Put((Object)("sRate"),(Object)(_c.GetString("sRate")));
 //BA.debugLineNum = 124;BA.debugLine="temp.Put(\"sDescription\",c.GetString(\"sDescriptio";
_temp.Put((Object)("sDescription"),(Object)(_c.GetString("sDescription")));
 //BA.debugLineNum = 125;BA.debugLine="temp.Put(\"sFileSize\",c.GetString(\"sFileSize\"))";
_temp.Put((Object)("sFileSize"),(Object)(_c.GetString("sFileSize")));
 //BA.debugLineNum = 126;BA.debugLine="temp.Put(\"sLanguage\",c.GetString(\"sLanguage\"))";
_temp.Put((Object)("sLanguage"),(Object)(_c.GetString("sLanguage")));
 //BA.debugLineNum = 127;BA.debugLine="temp.Put(\"sDownload\",c.GetString(\"sDownload\"))";
_temp.Put((Object)("sDownload"),(Object)(_c.GetString("sDownload")));
 //BA.debugLineNum = 128;BA.debugLine="temp.Put(\"sPublishDate\",c.GetString(\"sPublishDat";
_temp.Put((Object)("sPublishDate"),(Object)(_c.GetString("sPublishDate")));
 //BA.debugLineNum = 129;BA.debugLine="temp.Put(\"sPDF\",c.GetString(\"sPDF\"))";
_temp.Put((Object)("sPDF"),(Object)(_c.GetString("sPDF")));
 //BA.debugLineNum = 130;BA.debugLine="ls.Add(temp)";
_ls.Add((Object)(_temp.getObject()));
 }
;
 //BA.debugLineNum = 134;BA.debugLine="Return ls";
if (true) return _ls;
 //BA.debugLineNum = 136;BA.debugLine="End Sub";
return null;
}
public String  _initialize(anywheresoftware.b4a.BA _ba) throws Exception{
innerInitialize(_ba);
 //BA.debugLineNum = 8;BA.debugLine="Public Sub Initialize";
 //BA.debugLineNum = 9;BA.debugLine="If File.Exists(File.DirApp & \"/Data\",\"data.db\") =";
if (__c.File.Exists(__c.File.getDirApp()+"/Data","data.db")==__c.False) { 
__c.File.Copy(__c.File.getDirAssets(),"category.db",__c.File.getDirApp()+"/Data","data.db");};
 //BA.debugLineNum = 10;BA.debugLine="sql1.InitializeSQLite(File.DirApp & \"/Data\",\"data";
_sql1.InitializeSQLite(__c.File.getDirApp()+"/Data","data.db",__c.False);
 //BA.debugLineNum = 11;BA.debugLine="End Sub";
return "";
}
public anywheresoftware.b4a.objects.collections.List  _searchinbooks(String _str,anywheresoftware.b4a.objects.collections.List _books) throws Exception{
anywheresoftware.b4a.objects.collections.List _result = null;
int _i = 0;
b4j.example.database _d1 = null;
anywheresoftware.b4a.objects.collections.List _res = null;
 //BA.debugLineNum = 277;BA.debugLine="Public Sub SearchInBooks(str As String,Books As Li";
 //BA.debugLineNum = 279;BA.debugLine="Dim result As List";
_result = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 280;BA.debugLine="result.Initialize";
_result.Initialize();
 //BA.debugLineNum = 282;BA.debugLine="For i = 0 To Books.Size - 1";
{
final int step3 = 1;
final int limit3 = (int) (_books.getSize()-1);
for (_i = (int) (0) ; (step3 > 0 && _i <= limit3) || (step3 < 0 && _i >= limit3); _i = ((int)(0 + _i + step3)) ) {
 //BA.debugLineNum = 284;BA.debugLine="Dim d1 As Database";
_d1 = new b4j.example.database();
 //BA.debugLineNum = 285;BA.debugLine="Dim res As List";
_res = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 286;BA.debugLine="d1.Initialize(Books.Get(i))";
_d1._initialize(ba,BA.ObjectToString(_books.Get(_i)));
 //BA.debugLineNum = 287;BA.debugLine="res = d1.SearchInChapters(str)";
_res = _d1._searchinchapters(_str);
 //BA.debugLineNum = 289;BA.debugLine="result.Add(res)";
_result.Add((Object)(_res.getObject()));
 }
};
 //BA.debugLineNum = 293;BA.debugLine="Return result";
if (true) return _result;
 //BA.debugLineNum = 295;BA.debugLine="End Sub";
return null;
}
public anywheresoftware.b4a.objects.collections.List  _searchwordbooks(String _str) throws Exception{
anywheresoftware.b4a.objects.collections.List _ls = null;
anywheresoftware.b4a.objects.collections.List _allresult = null;
int _i = 0;
anywheresoftware.b4a.objects.collections.Map _m = null;
b4j.example.database _dbbook = null;
anywheresoftware.b4a.objects.collections.List _res = null;
 //BA.debugLineNum = 248;BA.debugLine="Public Sub SearchWordBooks(str As String) As List";
 //BA.debugLineNum = 250;BA.debugLine="Dim ls,AllResult As List";
_ls = new anywheresoftware.b4a.objects.collections.List();
_allresult = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 251;BA.debugLine="AllResult.Initialize";
_allresult.Initialize();
 //BA.debugLineNum = 253;BA.debugLine="ls = GetDownloadedBook(\"\",\"\")";
_ls = _getdownloadedbook("","");
 //BA.debugLineNum = 255;BA.debugLine="For i = 0 To ls.Size - 1";
{
final int step4 = 1;
final int limit4 = (int) (_ls.getSize()-1);
for (_i = (int) (0) ; (step4 > 0 && _i <= limit4) || (step4 < 0 && _i >= limit4); _i = ((int)(0 + _i + step4)) ) {
 //BA.debugLineNum = 257;BA.debugLine="Dim m As Map";
_m = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 258;BA.debugLine="m = ls.Get(i)";
_m.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_ls.Get(_i)));
 //BA.debugLineNum = 260;BA.debugLine="Dim dbBook As Database";
_dbbook = new b4j.example.database();
 //BA.debugLineNum = 261;BA.debugLine="Dim res As List";
_res = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 262;BA.debugLine="dbBook.Initialize(m.Get(\"sID\"))";
_dbbook._initialize(ba,BA.ObjectToString(_m.Get((Object)("sID"))));
 //BA.debugLineNum = 263;BA.debugLine="res = dbBook.SearchInChapters(str)";
_res = _dbbook._searchinchapters(_str);
 //BA.debugLineNum = 265;BA.debugLine="If res.IsInitialized Then";
if (_res.IsInitialized()) { 
 //BA.debugLineNum = 266;BA.debugLine="If res.Size > 0 Then";
if (_res.getSize()>0) { 
 //BA.debugLineNum = 267;BA.debugLine="AllResult.Add(m.Get(\"sID\"))";
_allresult.Add(_m.Get((Object)("sID")));
 };
 };
 }
};
 //BA.debugLineNum = 273;BA.debugLine="Return AllResult";
if (true) return _allresult;
 //BA.debugLineNum = 275;BA.debugLine="End Sub";
return null;
}
public Object callSub(String sub, Object sender, Object[] args) throws Exception {
BA.senderHolder.set(sender);
return BA.SubDelegator.SubNotFound;
}
}
