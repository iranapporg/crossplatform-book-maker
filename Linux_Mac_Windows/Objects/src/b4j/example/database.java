package b4j.example;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.debug.*;

public class database extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    public static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new  anywheresoftware.b4j.objects.FxBA("b4j.example", "b4j.example.database", this);
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            ba.htSubs = htSubs;
             
        }
        if (BA.isShellModeRuntimeCheck(ba))
                this.getClass().getMethod("_class_globals", b4j.example.database.class).invoke(this, new Object[] {null});
        else
            ba.raiseEvent2(null, true, "class_globals", false);
    }

 public anywheresoftware.b4a.keywords.Common __c = null;
public anywheresoftware.b4j.objects.SQL _sql1 = null;
public anywheresoftware.b4a.agraham.byteconverter.ByteConverter _bc = null;
public anywheresoftware.b4a.objects.collections.Map _bookdetails = null;
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
public anywheresoftware.b4a.objects.collections.Map  _chaptercontent(String _sid) throws Exception{
anywheresoftware.b4j.objects.SQL.ResultSetWrapper _c1 = null;
anywheresoftware.b4a.objects.collections.Map _m1 = null;
 //BA.debugLineNum = 132;BA.debugLine="Public Sub ChapterContent(sID As String) As Map";
 //BA.debugLineNum = 134;BA.debugLine="Dim c1 As ResultSet";
_c1 = new anywheresoftware.b4j.objects.SQL.ResultSetWrapper();
 //BA.debugLineNum = 135;BA.debugLine="c1 = sql1.ExecQuery(\"SELECT sID,sText,sTitle,sTyp";
_c1 = _sql1.ExecQuery("SELECT sID,sText,sTitle,sType,sRate,sParent FROM tbl_topic WHERE sID = '"+_sid+"'");
 //BA.debugLineNum = 137;BA.debugLine="Dim m1 As Map";
_m1 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 138;BA.debugLine="m1.Initialize";
_m1.Initialize();
 //BA.debugLineNum = 140;BA.debugLine="If c1.NextRow Then";
if (_c1.NextRow()) { 
 //BA.debugLineNum = 143;BA.debugLine="m1.Put(\"sID\",c1.GetString(\"sID\"))";
_m1.Put((Object)("sID"),(Object)(_c1.GetString("sID")));
 //BA.debugLineNum = 144;BA.debugLine="m1.Put(\"sTitle\",c1.GetString(\"sTitle\"))";
_m1.Put((Object)("sTitle"),(Object)(_c1.GetString("sTitle")));
 //BA.debugLineNum = 146;BA.debugLine="If c1.GetString(\"sType\") <> \"pdf\" Then";
if ((_c1.GetString("sType")).equals("pdf") == false) { 
 //BA.debugLineNum = 147;BA.debugLine="m1.Put(\"sText\",BC.StringFromBytes(c1.GetBlob(\"s";
_m1.Put((Object)("sText"),(Object)(_bc.StringFromBytes(_c1.GetBlob("sText"),"UTF8")));
 }else {
 //BA.debugLineNum = 149;BA.debugLine="m1.Put(\"sText\",c1.GetString(\"sText\"))";
_m1.Put((Object)("sText"),(Object)(_c1.GetString("sText")));
 };
 //BA.debugLineNum = 152;BA.debugLine="m1.Put(\"sRate\",c1.GetString(\"sRate\"))";
_m1.Put((Object)("sRate"),(Object)(_c1.GetString("sRate")));
 //BA.debugLineNum = 153;BA.debugLine="m1.Put(\"sTypeTopic\",c1.GetString(\"sType\"))";
_m1.Put((Object)("sTypeTopic"),(Object)(_c1.GetString("sType")));
 //BA.debugLineNum = 154;BA.debugLine="m1.Put(\"sParent\",c1.GetString(\"sParent\"))";
_m1.Put((Object)("sParent"),(Object)(_c1.GetString("sParent")));
 };
 //BA.debugLineNum = 158;BA.debugLine="Return m1";
if (true) return _m1;
 //BA.debugLineNum = 160;BA.debugLine="End Sub";
return null;
}
public String  _class_globals() throws Exception{
 //BA.debugLineNum = 2;BA.debugLine="Private Sub Class_Globals";
 //BA.debugLineNum = 3;BA.debugLine="Private sql1 As SQL";
_sql1 = new anywheresoftware.b4j.objects.SQL();
 //BA.debugLineNum = 4;BA.debugLine="Private BC As ByteConverter";
_bc = new anywheresoftware.b4a.agraham.byteconverter.ByteConverter();
 //BA.debugLineNum = 5;BA.debugLine="Public BookDetails As Map";
_bookdetails = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 6;BA.debugLine="End Sub";
return "";
}
public String  _closedatabase() throws Exception{
 //BA.debugLineNum = 220;BA.debugLine="public Sub CloseDatabase";
 //BA.debugLineNum = 221;BA.debugLine="sql1.Close";
_sql1.Close();
 //BA.debugLineNum = 222;BA.debugLine="End Sub";
return "";
}
public anywheresoftware.b4a.objects.collections.List  _getcategory(String _sid) throws Exception{
anywheresoftware.b4j.objects.SQL.ResultSetWrapper _c1 = null;
anywheresoftware.b4a.objects.collections.List _ls = null;
anywheresoftware.b4a.objects.collections.Map _m1 = null;
 //BA.debugLineNum = 23;BA.debugLine="Public Sub GetCategory(sID As String) As List";
 //BA.debugLineNum = 25;BA.debugLine="Dim c1 As ResultSet";
_c1 = new anywheresoftware.b4j.objects.SQL.ResultSetWrapper();
 //BA.debugLineNum = 26;BA.debugLine="c1 = sql1.ExecQuery(\"SELECT * FROM tbl_category W";
_c1 = _sql1.ExecQuery("SELECT * FROM tbl_category WHERE sParent = '"+_sid+"'");
 //BA.debugLineNum = 28;BA.debugLine="Dim ls As List";
_ls = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 29;BA.debugLine="ls.Initialize";
_ls.Initialize();
 //BA.debugLineNum = 31;BA.debugLine="Do While c1.NextRow";
while (_c1.NextRow()) {
 //BA.debugLineNum = 33;BA.debugLine="Dim m1 As Map";
_m1 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 34;BA.debugLine="m1.Initialize";
_m1.Initialize();
 //BA.debugLineNum = 35;BA.debugLine="m1.Put(\"sID\",c1.GetString(\"sID\"))";
_m1.Put((Object)("sID"),(Object)(_c1.GetString("sID")));
 //BA.debugLineNum = 36;BA.debugLine="m1.Put(\"sTitle\",c1.GetString(\"sTitle\"))";
_m1.Put((Object)("sTitle"),(Object)(_c1.GetString("sTitle")));
 //BA.debugLineNum = 37;BA.debugLine="m1.Put(\"sType\",\"cat\")";
_m1.Put((Object)("sType"),(Object)("cat"));
 //BA.debugLineNum = 38;BA.debugLine="ls.Add(m1)";
_ls.Add((Object)(_m1.getObject()));
 }
;
 //BA.debugLineNum = 42;BA.debugLine="Return ls";
if (true) return _ls;
 //BA.debugLineNum = 44;BA.debugLine="End Sub";
return null;
}
public anywheresoftware.b4a.objects.collections.List  _getchapters(String _scategoryid,boolean _issummary) throws Exception{
anywheresoftware.b4j.objects.SQL.ResultSetWrapper _c1 = null;
anywheresoftware.b4a.objects.collections.List _ls = null;
anywheresoftware.b4a.objects.collections.Map _m1 = null;
 //BA.debugLineNum = 50;BA.debugLine="Public Sub GetChapters(sCategoryID As String,isSum";
 //BA.debugLineNum = 52;BA.debugLine="Dim c1 As ResultSet";
_c1 = new anywheresoftware.b4j.objects.SQL.ResultSetWrapper();
 //BA.debugLineNum = 54;BA.debugLine="If isSummary = False Then";
if (_issummary==__c.False) { 
 //BA.debugLineNum = 55;BA.debugLine="c1 = sql1.ExecQuery(\"SELECT sID,sTitle,sParent,s";
_c1 = _sql1.ExecQuery("SELECT sID,sTitle,sParent,sType FROM tbl_topic WHERE sParent = '"+_scategoryid+"'");
 }else {
 //BA.debugLineNum = 57;BA.debugLine="c1 = sql1.ExecQuery(\"SELECT sID,sTitle,sParent,s";
_c1 = _sql1.ExecQuery("SELECT sID,sTitle,sParent,sType FROM tbl_topic WHERE sType = 'summary'");
 };
 //BA.debugLineNum = 60;BA.debugLine="Dim ls As List";
_ls = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 61;BA.debugLine="ls.Initialize";
_ls.Initialize();
 //BA.debugLineNum = 64;BA.debugLine="Do While c1.NextRow";
while (_c1.NextRow()) {
 //BA.debugLineNum = 66;BA.debugLine="Dim m1 As Map";
_m1 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 67;BA.debugLine="m1.Initialize";
_m1.Initialize();
 //BA.debugLineNum = 68;BA.debugLine="m1.Put(\"sID\",c1.GetString(\"sID\"))";
_m1.Put((Object)("sID"),(Object)(_c1.GetString("sID")));
 //BA.debugLineNum = 69;BA.debugLine="m1.Put(\"sTitle\",c1.GetString(\"sTitle\"))";
_m1.Put((Object)("sTitle"),(Object)(_c1.GetString("sTitle")));
 //BA.debugLineNum = 70;BA.debugLine="m1.Put(\"sParent\",c1.GetString(\"sParent\"))";
_m1.Put((Object)("sParent"),(Object)(_c1.GetString("sParent")));
 //BA.debugLineNum = 71;BA.debugLine="m1.Put(\"sType\",\"book\")";
_m1.Put((Object)("sType"),(Object)("book"));
 //BA.debugLineNum = 73;BA.debugLine="If c1.GetString(\"sType\") = \"summary\" Then";
if ((_c1.GetString("sType")).equals("summary")) { 
 //BA.debugLineNum = 74;BA.debugLine="m1.Put(\"sType\",\"summary\")";
_m1.Put((Object)("sType"),(Object)("summary"));
 };
 //BA.debugLineNum = 77;BA.debugLine="ls.Add(m1)";
_ls.Add((Object)(_m1.getObject()));
 }
;
 //BA.debugLineNum = 81;BA.debugLine="Return ls";
if (true) return _ls;
 //BA.debugLineNum = 83;BA.debugLine="End Sub";
return null;
}
public anywheresoftware.b4a.objects.collections.Map  _getfooters(String _stopicid,String _sid) throws Exception{
anywheresoftware.b4j.objects.SQL.ResultSetWrapper _c1 = null;
anywheresoftware.b4a.objects.collections.Map _m1 = null;
 //BA.debugLineNum = 183;BA.debugLine="Public Sub GetFooters(sTopicID As String,sID As St";
 //BA.debugLineNum = 185;BA.debugLine="Dim c1 As ResultSet";
_c1 = new anywheresoftware.b4j.objects.SQL.ResultSetWrapper();
 //BA.debugLineNum = 186;BA.debugLine="c1 = sql1.ExecQuery(\"SELECT * FROM tbl_footer WHE";
_c1 = _sql1.ExecQuery("SELECT * FROM tbl_footer WHERE sTopicID = '"+_stopicid+"' AND sFooterID = '"+_sid+"'");
 //BA.debugLineNum = 188;BA.debugLine="Dim m1 As Map";
_m1 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 189;BA.debugLine="m1.Initialize";
_m1.Initialize();
 //BA.debugLineNum = 191;BA.debugLine="If c1.NextRow Then";
if (_c1.NextRow()) { 
 //BA.debugLineNum = 193;BA.debugLine="m1.Put(\"sID\",c1.GetString(\"sID\"))";
_m1.Put((Object)("sID"),(Object)(_c1.GetString("sID")));
 //BA.debugLineNum = 194;BA.debugLine="m1.Put(\"sFooterID\",c1.GetString(\"sFooterID\"))";
_m1.Put((Object)("sFooterID"),(Object)(_c1.GetString("sFooterID")));
 //BA.debugLineNum = 195;BA.debugLine="m1.Put(\"sPositionFooter\",c1.GetString(\"sPosition";
_m1.Put((Object)("sPositionFooter"),(Object)(_c1.GetString("sPositionFooter")));
 //BA.debugLineNum = 196;BA.debugLine="m1.Put(\"sText\",c1.GetString(\"sText\"))";
_m1.Put((Object)("sText"),(Object)(_c1.GetString("sText")));
 //BA.debugLineNum = 197;BA.debugLine="m1.Put(\"sTopicID\",c1.GetString(\"sTopicID\"))";
_m1.Put((Object)("sTopicID"),(Object)(_c1.GetString("sTopicID")));
 //BA.debugLineNum = 198;BA.debugLine="m1.Put(\"success\",True)";
_m1.Put((Object)("success"),(Object)(__c.True));
 };
 //BA.debugLineNum = 202;BA.debugLine="Return m1";
if (true) return _m1;
 //BA.debugLineNum = 204;BA.debugLine="End Sub";
return null;
}
public anywheresoftware.b4a.objects.collections.Map  _getmultimedia(String _stopicid,String _key) throws Exception{
anywheresoftware.b4j.objects.SQL.ResultSetWrapper _c1 = null;
anywheresoftware.b4a.objects.collections.List _ls = null;
 //BA.debugLineNum = 206;BA.debugLine="Public Sub GetMultimedia(sTopicID As String,Key As";
 //BA.debugLineNum = 208;BA.debugLine="Dim c1 As ResultSet";
_c1 = new anywheresoftware.b4j.objects.SQL.ResultSetWrapper();
 //BA.debugLineNum = 209;BA.debugLine="c1 = sql1.ExecQuery(\"SELECT * FROM tbl_multimedia";
_c1 = _sql1.ExecQuery("SELECT * FROM tbl_multimedia WHERE sTopicID = '"+_stopicid+"' AND sKey ='"+_key+"'");
 //BA.debugLineNum = 211;BA.debugLine="Dim ls As List";
_ls = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 212;BA.debugLine="ls.Initialize";
_ls.Initialize();
 //BA.debugLineNum = 214;BA.debugLine="If c1.NextRow Then Return Null";
if (_c1.NextRow()) { 
if (true) return (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (anywheresoftware.b4a.objects.collections.Map.MyMap)(__c.Null));};
 //BA.debugLineNum = 216;BA.debugLine="Return CreateMap(\"Value\":c1.GetString(\"sFilename\"";
if (true) return __c.createMap(new Object[] {(Object)("Value"),(Object)(_c1.GetString("sFilename")),(Object)("Type"),(Object)(_c1.GetString("sType"))});
 //BA.debugLineNum = 218;BA.debugLine="End Sub";
return null;
}
public String  _gettext(String _sid) throws Exception{
 //BA.debugLineNum = 162;BA.debugLine="Sub GetText(sID As String)";
 //BA.debugLineNum = 165;BA.debugLine="End Sub";
return "";
}
public String  _gettopicid(String _sid) throws Exception{
anywheresoftware.b4j.objects.SQL.ResultSetWrapper _c1 = null;
anywheresoftware.b4a.objects.collections.Map _m1 = null;
 //BA.debugLineNum = 167;BA.debugLine="public Sub GetTopicID(sID As String) As String";
 //BA.debugLineNum = 169;BA.debugLine="Dim c1 As ResultSet";
_c1 = new anywheresoftware.b4j.objects.SQL.ResultSetWrapper();
 //BA.debugLineNum = 170;BA.debugLine="c1 = sql1.ExecQuery(\"SELECT * FROM tbl_topic WHER";
_c1 = _sql1.ExecQuery("SELECT * FROM tbl_topic WHERE sID = "+_sid);
 //BA.debugLineNum = 172;BA.debugLine="Dim m1 As Map";
_m1 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 173;BA.debugLine="m1.Initialize";
_m1.Initialize();
 //BA.debugLineNum = 175;BA.debugLine="If c1.NextRow Then";
if (_c1.NextRow()) { 
 //BA.debugLineNum = 176;BA.debugLine="Return c1.GetString(\"sTitle\")";
if (true) return _c1.GetString("sTitle");
 };
 //BA.debugLineNum = 179;BA.debugLine="Return \"\"";
if (true) return "";
 //BA.debugLineNum = 181;BA.debugLine="End Sub";
return "";
}
public String  _initialize(anywheresoftware.b4a.BA _ba,String _bookname) throws Exception{
innerInitialize(_ba);
b4j.example.categories _cat = null;
 //BA.debugLineNum = 9;BA.debugLine="Public Sub Initialize(BookName As String)";
 //BA.debugLineNum = 10;BA.debugLine="Try";
try { //BA.debugLineNum = 11;BA.debugLine="sql1.InitializeSQLite(File.DirApp & \"/Data\" & \"/";
_sql1.InitializeSQLite(__c.File.getDirApp()+"/Data"+"/book/"+_bookname,"bank.db",__c.False);
 //BA.debugLineNum = 13;BA.debugLine="Dim cat As Categories";
_cat = new b4j.example.categories();
 //BA.debugLineNum = 14;BA.debugLine="cat.Initialize";
_cat._initialize(ba);
 //BA.debugLineNum = 15;BA.debugLine="BookDetails = cat.GetBookInformation(BookName)";
_bookdetails = _cat._getbookinformation(_bookname);
 //BA.debugLineNum = 16;BA.debugLine="cat.Close";
_cat._close();
 } 
       catch (Exception e8) {
			ba.setLastException(e8); //BA.debugLineNum = 19;BA.debugLine="Main.fx.Msgbox(Main.MainForm,\"دیتای مربوط به این";
_main._fx.Msgbox(_main._mainform,"دیتای مربوط به این کتاب قابل شناسایی نیست","توجه");
 };
 //BA.debugLineNum = 21;BA.debugLine="End Sub";
return "";
}
public anywheresoftware.b4a.objects.collections.List  _searchinchapters(String _str) throws Exception{
anywheresoftware.b4j.objects.SQL.ResultSetWrapper _c1 = null;
anywheresoftware.b4a.objects.collections.List _ls = null;
b4j.example.categories _cat = null;
anywheresoftware.b4a.objects.collections.Map _m1 = null;
int _offset = 0;
int _temp = 0;
int _start = 0;
int _ends = 0;
 //BA.debugLineNum = 85;BA.debugLine="Public Sub SearchInChapters(str As String) As List";
 //BA.debugLineNum = 87;BA.debugLine="Dim c1 As ResultSet";
_c1 = new anywheresoftware.b4j.objects.SQL.ResultSetWrapper();
 //BA.debugLineNum = 88;BA.debugLine="c1 = sql1.ExecQuery(\"SELECT * FROM tbl_topic WHER";
_c1 = _sql1.ExecQuery("SELECT * FROM tbl_topic WHERE sText LIKE '%"+_str+"%'");
 //BA.debugLineNum = 90;BA.debugLine="Dim ls As List";
_ls = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 91;BA.debugLine="ls.Initialize";
_ls.Initialize();
 //BA.debugLineNum = 93;BA.debugLine="Dim cat As Categories";
_cat = new b4j.example.categories();
 //BA.debugLineNum = 94;BA.debugLine="cat.Initialize";
_cat._initialize(ba);
 //BA.debugLineNum = 96;BA.debugLine="Do While c1.NextRow";
while (_c1.NextRow()) {
 //BA.debugLineNum = 98;BA.debugLine="Dim m1 As Map";
_m1 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 99;BA.debugLine="m1.Initialize";
_m1.Initialize();
 //BA.debugLineNum = 100;BA.debugLine="m1.Put(\"sID\",c1.GetString(\"sID\"))";
_m1.Put((Object)("sID"),(Object)(_c1.GetString("sID")));
 //BA.debugLineNum = 101;BA.debugLine="m1.Put(\"sTitle\",c1.GetString(\"sTitle\"))";
_m1.Put((Object)("sTitle"),(Object)(_c1.GetString("sTitle")));
 //BA.debugLineNum = 102;BA.debugLine="m1.Put(\"sParent\",c1.GetString(\"sParent\"))";
_m1.Put((Object)("sParent"),(Object)(_c1.GetString("sParent")));
 //BA.debugLineNum = 103;BA.debugLine="m1.Put(\"BookName\",BookDetails.Get(\"sTitle\"))";
_m1.Put((Object)("BookName"),_bookdetails.Get((Object)("sTitle")));
 //BA.debugLineNum = 104;BA.debugLine="m1.Put(\"BookID\",BookDetails.Get(\"sID\"))";
_m1.Put((Object)("BookID"),_bookdetails.Get((Object)("sID")));
 //BA.debugLineNum = 105;BA.debugLine="m1.Put(\"sType\",\"book\")";
_m1.Put((Object)("sType"),(Object)("book"));
 //BA.debugLineNum = 107;BA.debugLine="Dim offset,temp,start,ends As Int";
_offset = 0;
_temp = 0;
_start = 0;
_ends = 0;
 //BA.debugLineNum = 108;BA.debugLine="offset = c1.GetString(\"sText\").IndexOf(str)";
_offset = _c1.GetString("sText").indexOf(_str);
 //BA.debugLineNum = 110;BA.debugLine="If offset > 1 Then";
if (_offset>1) { 
 //BA.debugLineNum = 111;BA.debugLine="If offset > 20 Then	 start = 5 Else start = 0";
if (_offset>20) { 
_start = (int) (5);}
else {
_start = (int) (0);};
 };
 //BA.debugLineNum = 114;BA.debugLine="temp = c1.GetString(\"sText\").Length";
_temp = _c1.GetString("sText").length();
 //BA.debugLineNum = 116;BA.debugLine="If temp - offset > 10 Then";
if (_temp-_offset>10) { 
 //BA.debugLineNum = 117;BA.debugLine="ends = offset + str.Length + 10";
_ends = (int) (_offset+_str.length()+10);
 }else if(_temp-_offset>1) { 
 //BA.debugLineNum = 119;BA.debugLine="ends = offset + str.Length";
_ends = (int) (_offset+_str.length());
 };
 //BA.debugLineNum = 122;BA.debugLine="m1.Put(\"Str\",c1.GetString(\"sText\").SubString2(st";
_m1.Put((Object)("Str"),(Object)(_c1.GetString("sText").substring(_start,_ends)));
 //BA.debugLineNum = 124;BA.debugLine="ls.Add(m1)";
_ls.Add((Object)(_m1.getObject()));
 }
;
 //BA.debugLineNum = 128;BA.debugLine="Return ls";
if (true) return _ls;
 //BA.debugLineNum = 130;BA.debugLine="End Sub";
return null;
}
public String  _updatecontent(String _sid,String _content) throws Exception{
 //BA.debugLineNum = 46;BA.debugLine="public Sub UpdateContent(sID As String,Content As";
 //BA.debugLineNum = 47;BA.debugLine="sql1.ExecNonQuery(\"UPDATE tbl_topic SET sText ='\"";
_sql1.ExecNonQuery("UPDATE tbl_topic SET sText ='"+_content+"' WHERE sID ="+_sid);
 //BA.debugLineNum = 48;BA.debugLine="End Sub";
return "";
}
public Object callSub(String sub, Object sender, Object[] args) throws Exception {
BA.senderHolder.set(sender);
return BA.SubDelegator.SubNotFound;
}
}
