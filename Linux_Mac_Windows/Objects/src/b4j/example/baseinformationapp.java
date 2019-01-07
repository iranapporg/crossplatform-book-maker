package b4j.example;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.debug.*;

public class baseinformationapp extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    public static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new  anywheresoftware.b4j.objects.FxBA("b4j.example", "b4j.example.baseinformationapp", this);
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            ba.htSubs = htSubs;
             
        }
        if (BA.isShellModeRuntimeCheck(ba))
                this.getClass().getMethod("_class_globals", b4j.example.baseinformationapp.class).invoke(this, new Object[] {null});
        else
            ba.raiseEvent2(null, true, "class_globals", false);
    }

 public anywheresoftware.b4a.keywords.Common __c = null;
public anywheresoftware.b4j.objects.SQL _sql1 = null;
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
public String  _class_globals() throws Exception{
 //BA.debugLineNum = 2;BA.debugLine="Sub Class_Globals";
 //BA.debugLineNum = 3;BA.debugLine="Private sql1 As SQL";
_sql1 = new anywheresoftware.b4j.objects.SQL();
 //BA.debugLineNum = 4;BA.debugLine="End Sub";
return "";
}
public anywheresoftware.b4a.objects.collections.List  _getdata(String _stype) throws Exception{
anywheresoftware.b4j.objects.SQL.ResultSetWrapper _c1 = null;
anywheresoftware.b4a.objects.collections.List _ls = null;
anywheresoftware.b4a.objects.collections.Map _m1 = null;
 //BA.debugLineNum = 12;BA.debugLine="public Sub GetData(sType As String) As List";
 //BA.debugLineNum = 14;BA.debugLine="Dim c1 As ResultSet";
_c1 = new anywheresoftware.b4j.objects.SQL.ResultSetWrapper();
 //BA.debugLineNum = 15;BA.debugLine="Dim ls As List";
_ls = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 17;BA.debugLine="ls.Initialize";
_ls.Initialize();
 //BA.debugLineNum = 18;BA.debugLine="c1 = sql1.ExecQuery(\"SELECT * FROM tbl_link WHERE";
_c1 = _sql1.ExecQuery("SELECT * FROM tbl_link WHERE sType ='"+_stype+"'");
 //BA.debugLineNum = 20;BA.debugLine="Do While c1.NextRow";
while (_c1.NextRow()) {
 //BA.debugLineNum = 22;BA.debugLine="Dim m1 As Map";
_m1 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 23;BA.debugLine="m1.Initialize";
_m1.Initialize();
 //BA.debugLineNum = 24;BA.debugLine="m1.Put(\"sTitle\",c1.GetString(\"sTitle\"))";
_m1.Put((Object)("sTitle"),(Object)(_c1.GetString("sTitle")));
 //BA.debugLineNum = 25;BA.debugLine="m1.Put(\"sLink1\",c1.GetString(\"sLink1\"))";
_m1.Put((Object)("sLink1"),(Object)(_c1.GetString("sLink1")));
 //BA.debugLineNum = 26;BA.debugLine="m1.Put(\"sLink2\",c1.GetString(\"sLink2\"))";
_m1.Put((Object)("sLink2"),(Object)(_c1.GetString("sLink2")));
 //BA.debugLineNum = 27;BA.debugLine="m1.Put(\"sType\",c1.GetString(\"sType\"))";
_m1.Put((Object)("sType"),(Object)(_c1.GetString("sType")));
 //BA.debugLineNum = 28;BA.debugLine="ls.Add(m1)";
_ls.Add((Object)(_m1.getObject()));
 }
;
 //BA.debugLineNum = 32;BA.debugLine="Return ls";
if (true) return _ls;
 //BA.debugLineNum = 34;BA.debugLine="End Sub";
return null;
}
public String  _initialize(anywheresoftware.b4a.BA _ba) throws Exception{
innerInitialize(_ba);
 //BA.debugLineNum = 6;BA.debugLine="Public Sub Initialize";
 //BA.debugLineNum = 8;BA.debugLine="sql1.InitializeSqlite(File.DirApp & \"/Data\",\"main";
_sql1.InitializeSQLite(__c.File.getDirApp()+"/Data","main_db.db",__c.False);
 //BA.debugLineNum = 10;BA.debugLine="End Sub";
return "";
}
public Object callSub(String sub, Object sender, Object[] args) throws Exception {
BA.senderHolder.set(sender);
return BA.SubDelegator.SubNotFound;
}
}
