package b4j.example;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.debug.*;

public class dbutils extends Object{
public static dbutils mostCurrent = new dbutils();

public static BA ba;
static {
		ba = new  anywheresoftware.b4j.objects.FxBA("b4j.example", "b4j.example.dbutils", null);
		ba.loadHtSubs(dbutils.class);
        if (ba.getClass().getName().endsWith("ShellBA")) {
			
			ba.raiseEvent2(null, true, "SHELL", false);
			ba.raiseEvent2(null, true, "CREATE", true, "b4j.example.dbutils", ba);
		}
	}
    public static Class<?> getObject() {
		return dbutils.class;
	}

 public static anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4j.objects.JFX _fx = null;
public static String _db_real = "";
public static String _db_integer = "";
public static String _db_blob = "";
public static String _db_text = "";
public static String _htmlcss = "";
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
public static b4j.example.actbookinformation _actbookinformation = null;
public static b4j.example.actsetting _actsetting = null;
public static b4j.example.actsearchbook _actsearchbook = null;
public static String  _createtable(anywheresoftware.b4j.objects.SQL _sql,String _tablename,anywheresoftware.b4a.objects.collections.Map _fieldsandtypes,String _primarykey) throws Exception{
anywheresoftware.b4a.keywords.StringBuilderWrapper _sb = null;
int _i = 0;
String _field = "";
String _ftype = "";
String _query = "";
 //BA.debugLineNum = 24;BA.debugLine="Public Sub CreateTable(SQL As SQL, TableName As St";
 //BA.debugLineNum = 25;BA.debugLine="Dim sb As StringBuilder";
_sb = new anywheresoftware.b4a.keywords.StringBuilderWrapper();
 //BA.debugLineNum = 26;BA.debugLine="sb.Initialize";
_sb.Initialize();
 //BA.debugLineNum = 27;BA.debugLine="sb.Append(\"(\")";
_sb.Append("(");
 //BA.debugLineNum = 28;BA.debugLine="For i = 0 To FieldsAndTypes.Size - 1";
{
final int step4 = 1;
final int limit4 = (int) (_fieldsandtypes.getSize()-1);
for (_i = (int) (0) ; (step4 > 0 && _i <= limit4) || (step4 < 0 && _i >= limit4); _i = ((int)(0 + _i + step4)) ) {
 //BA.debugLineNum = 29;BA.debugLine="Dim field, ftype As String";
_field = "";
_ftype = "";
 //BA.debugLineNum = 30;BA.debugLine="field = FieldsAndTypes.GetKeyAt(i)";
_field = BA.ObjectToString(_fieldsandtypes.GetKeyAt(_i));
 //BA.debugLineNum = 31;BA.debugLine="ftype = FieldsAndTypes.GetValueAt(i)";
_ftype = BA.ObjectToString(_fieldsandtypes.GetValueAt(_i));
 //BA.debugLineNum = 32;BA.debugLine="If i > 0 Then sb.Append(\", \")";
if (_i>0) { 
_sb.Append(", ");};
 //BA.debugLineNum = 33;BA.debugLine="sb.Append(EscapeField(field)).Append(\" \").Append";
_sb.Append(_escapefield(_field)).Append(" ").Append(_ftype);
 //BA.debugLineNum = 34;BA.debugLine="If field = PrimaryKey Then sb.Append(\" PRIMARY K";
if ((_field).equals(_primarykey)) { 
_sb.Append(" PRIMARY KEY");};
 }
};
 //BA.debugLineNum = 36;BA.debugLine="sb.Append(\")\")";
_sb.Append(")");
 //BA.debugLineNum = 37;BA.debugLine="Dim query As String";
_query = "";
 //BA.debugLineNum = 38;BA.debugLine="query = \"CREATE TABLE IF NOT EXISTS \" & EscapeFie";
_query = "CREATE TABLE IF NOT EXISTS "+_escapefield(_tablename)+" "+_sb.ToString();
 //BA.debugLineNum = 39;BA.debugLine="Log(\"CreateTable: \" & query)";
anywheresoftware.b4a.keywords.Common.Log("CreateTable: "+_query);
 //BA.debugLineNum = 40;BA.debugLine="SQL.ExecNonQuery(query)";
_sql.ExecNonQuery(_query);
 //BA.debugLineNum = 41;BA.debugLine="End Sub";
return "";
}
public static String  _deleterecord(anywheresoftware.b4j.objects.SQL _sql,String _tablename,anywheresoftware.b4a.objects.collections.Map _wherefieldequals) throws Exception{
anywheresoftware.b4a.keywords.StringBuilderWrapper _sb = null;
anywheresoftware.b4a.objects.collections.List _args = null;
int _i = 0;
 //BA.debugLineNum = 299;BA.debugLine="Public Sub DeleteRecord(SQL As SQL, TableName As S";
 //BA.debugLineNum = 300;BA.debugLine="Dim sb As StringBuilder";
_sb = new anywheresoftware.b4a.keywords.StringBuilderWrapper();
 //BA.debugLineNum = 301;BA.debugLine="sb.Initialize";
_sb.Initialize();
 //BA.debugLineNum = 302;BA.debugLine="sb.Append(\"DELETE FROM [\").Append(TableName).Ap";
_sb.Append("DELETE FROM [").Append(_tablename).Append("] WHERE ");
 //BA.debugLineNum = 303;BA.debugLine="If WhereFieldEquals.Size = 0 Then";
if (_wherefieldequals.getSize()==0) { 
 //BA.debugLineNum = 304;BA.debugLine="Log(\"WhereFieldEquals map empty!\")";
anywheresoftware.b4a.keywords.Common.Log("WhereFieldEquals map empty!");
 //BA.debugLineNum = 305;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 307;BA.debugLine="Dim args As List";
_args = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 308;BA.debugLine="args.Initialize";
_args.Initialize();
 //BA.debugLineNum = 309;BA.debugLine="For i = 0 To WhereFieldEquals.Size - 1";
{
final int step10 = 1;
final int limit10 = (int) (_wherefieldequals.getSize()-1);
for (_i = (int) (0) ; (step10 > 0 && _i <= limit10) || (step10 < 0 && _i >= limit10); _i = ((int)(0 + _i + step10)) ) {
 //BA.debugLineNum = 310;BA.debugLine="If i > 0 Then sb.Append(\" AND \")";
if (_i>0) { 
_sb.Append(" AND ");};
 //BA.debugLineNum = 311;BA.debugLine="sb.Append(\"[\").Append(WhereFieldEquals.GetKe";
_sb.Append("[").Append(BA.ObjectToString(_wherefieldequals.GetKeyAt(_i))).Append("] = ?");
 //BA.debugLineNum = 312;BA.debugLine="args.Add(WhereFieldEquals.GetValueAt(i))";
_args.Add(_wherefieldequals.GetValueAt(_i));
 }
};
 //BA.debugLineNum = 314;BA.debugLine="Log(\"DeleteRecord: \" & sb.ToString)";
anywheresoftware.b4a.keywords.Common.Log("DeleteRecord: "+_sb.ToString());
 //BA.debugLineNum = 315;BA.debugLine="SQL.ExecNonQuery2(sb.ToString, args)";
_sql.ExecNonQuery2(_sb.ToString(),_args);
 //BA.debugLineNum = 316;BA.debugLine="End Sub";
return "";
}
public static String  _droptable(anywheresoftware.b4j.objects.SQL _sql,String _tablename) throws Exception{
String _query = "";
 //BA.debugLineNum = 44;BA.debugLine="Public Sub DropTable(SQL As SQL, TableName As Stri";
 //BA.debugLineNum = 45;BA.debugLine="Dim query As String";
_query = "";
 //BA.debugLineNum = 46;BA.debugLine="query = \"DROP TABLE IF EXISTS \" & EscapeField(Tab";
_query = "DROP TABLE IF EXISTS "+_escapefield(_tablename);
 //BA.debugLineNum = 47;BA.debugLine="Log(\"DropTable3: \" & query)";
anywheresoftware.b4a.keywords.Common.Log("DropTable3: "+_query);
 //BA.debugLineNum = 48;BA.debugLine="SQL.ExecNonQuery(query)";
_sql.ExecNonQuery(_query);
 //BA.debugLineNum = 49;BA.debugLine="End Sub";
return "";
}
public static String  _escapefield(String _f) throws Exception{
 //BA.debugLineNum = 16;BA.debugLine="Private Sub EscapeField(f As String) As String";
 //BA.debugLineNum = 17;BA.debugLine="Return \"[\" & f & \"]\"";
if (true) return "["+_f+"]";
 //BA.debugLineNum = 18;BA.debugLine="End Sub";
return "";
}
public static String  _executehtml(anywheresoftware.b4j.objects.SQL _sql,String _query,String[] _stringargs,int _limit) throws Exception{
anywheresoftware.b4j.objects.SQL.ResultSetWrapper _cur = null;
anywheresoftware.b4a.keywords.StringBuilderWrapper _sb = null;
int _i = 0;
int _row = 0;
 //BA.debugLineNum = 252;BA.debugLine="Public Sub ExecuteHtml(SQL As SQL, Query As String";
 //BA.debugLineNum = 253;BA.debugLine="Dim cur As ResultSet";
_cur = new anywheresoftware.b4j.objects.SQL.ResultSetWrapper();
 //BA.debugLineNum = 254;BA.debugLine="If StringArgs <> Null Then";
if (_stringargs!= null) { 
 //BA.debugLineNum = 255;BA.debugLine="cur = SQL.ExecQuery2(Query, StringArgs)";
_cur = _sql.ExecQuery2(_query,anywheresoftware.b4a.keywords.Common.ArrayToList(_stringargs));
 }else {
 //BA.debugLineNum = 257;BA.debugLine="cur = SQL.ExecQuery(Query)";
_cur = _sql.ExecQuery(_query);
 };
 //BA.debugLineNum = 259;BA.debugLine="Log(\"ExecuteHtml: \" & Query)";
anywheresoftware.b4a.keywords.Common.Log("ExecuteHtml: "+_query);
 //BA.debugLineNum = 260;BA.debugLine="Dim sb As StringBuilder";
_sb = new anywheresoftware.b4a.keywords.StringBuilderWrapper();
 //BA.debugLineNum = 261;BA.debugLine="sb.Initialize";
_sb.Initialize();
 //BA.debugLineNum = 262;BA.debugLine="sb.Append(\"<html><body>\").Append(CRLF)";
_sb.Append("<html><body>").Append(anywheresoftware.b4a.keywords.Common.CRLF);
 //BA.debugLineNum = 263;BA.debugLine="sb.Append(\"<style type='text/css'>\").Append(HtmlC";
_sb.Append("<style type='text/css'>").Append(_htmlcss).Append("</style>").Append(anywheresoftware.b4a.keywords.Common.CRLF);
 //BA.debugLineNum = 264;BA.debugLine="sb.Append(\"<table><thead><tr>\").Append(CRLF)";
_sb.Append("<table><thead><tr>").Append(anywheresoftware.b4a.keywords.Common.CRLF);
 //BA.debugLineNum = 265;BA.debugLine="For i = 0 To cur.ColumnCount - 1";
{
final int step13 = 1;
final int limit13 = (int) (_cur.getColumnCount()-1);
for (_i = (int) (0) ; (step13 > 0 && _i <= limit13) || (step13 < 0 && _i >= limit13); _i = ((int)(0 + _i + step13)) ) {
 //BA.debugLineNum = 266;BA.debugLine="sb.Append(\"<th>\").Append(cur.GetColumnName(i)).A";
_sb.Append("<th>").Append(_cur.GetColumnName(_i)).Append("</th>");
 }
};
 //BA.debugLineNum = 268;BA.debugLine="sb.Append(\"</thead>\")";
_sb.Append("</thead>");
 //BA.debugLineNum = 278;BA.debugLine="sb.Append(\"</tr>\").Append(CRLF)";
_sb.Append("</tr>").Append(anywheresoftware.b4a.keywords.Common.CRLF);
 //BA.debugLineNum = 279;BA.debugLine="Dim row As Int";
_row = 0;
 //BA.debugLineNum = 280;BA.debugLine="Do While cur.NextRow";
while (_cur.NextRow()) {
 //BA.debugLineNum = 281;BA.debugLine="If row Mod 2 = 0 Then";
if (_row%2==0) { 
 //BA.debugLineNum = 282;BA.debugLine="sb.Append(\"<tr>\")";
_sb.Append("<tr>");
 }else {
 //BA.debugLineNum = 284;BA.debugLine="sb.Append(\"<tr class='odd'>\")";
_sb.Append("<tr class='odd'>");
 };
 //BA.debugLineNum = 286;BA.debugLine="For i = 0 To cur.ColumnCount - 1";
{
final int step25 = 1;
final int limit25 = (int) (_cur.getColumnCount()-1);
for (_i = (int) (0) ; (step25 > 0 && _i <= limit25) || (step25 < 0 && _i >= limit25); _i = ((int)(0 + _i + step25)) ) {
 //BA.debugLineNum = 287;BA.debugLine="sb.Append(\"<td>\")";
_sb.Append("<td>");
 //BA.debugLineNum = 288;BA.debugLine="sb.Append(cur.GetString2(i))";
_sb.Append(_cur.GetString2(_i));
 //BA.debugLineNum = 289;BA.debugLine="sb.Append(\"</td>\")";
_sb.Append("</td>");
 }
};
 //BA.debugLineNum = 291;BA.debugLine="sb.Append(\"</tr>\").Append(CRLF)";
_sb.Append("</tr>").Append(anywheresoftware.b4a.keywords.Common.CRLF);
 //BA.debugLineNum = 292;BA.debugLine="row = row + 1";
_row = (int) (_row+1);
 }
;
 //BA.debugLineNum = 294;BA.debugLine="cur.Close";
_cur.Close();
 //BA.debugLineNum = 295;BA.debugLine="sb.Append(\"</table></body></html>\")";
_sb.Append("</table></body></html>");
 //BA.debugLineNum = 296;BA.debugLine="Return sb.ToString";
if (true) return _sb.ToString();
 //BA.debugLineNum = 297;BA.debugLine="End Sub";
return "";
}
public static String  _executelist(anywheresoftware.b4j.objects.SQL _sql,String _query,String[] _stringargs,int _limit,anywheresoftware.b4a.objects.collections.List _list1) throws Exception{
anywheresoftware.b4a.objects.collections.List _table = null;
String[] _cols = null;
int _i = 0;
 //BA.debugLineNum = 213;BA.debugLine="Public Sub ExecuteList(SQL As SQL, Query As String";
 //BA.debugLineNum = 214;BA.debugLine="List1.Clear";
_list1.Clear();
 //BA.debugLineNum = 215;BA.debugLine="Dim Table As List";
_table = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 216;BA.debugLine="Table = ExecuteMemoryTable(SQL, Query, StringArgs";
_table = _executememorytable(_sql,_query,_stringargs,_limit);
 //BA.debugLineNum = 217;BA.debugLine="If Table.Size = 0 Then Return";
if (_table.getSize()==0) { 
if (true) return "";};
 //BA.debugLineNum = 218;BA.debugLine="Dim Cols() As String";
_cols = new String[(int) (0)];
java.util.Arrays.fill(_cols,"");
 //BA.debugLineNum = 219;BA.debugLine="For i = 0 To Table.Size - 1";
{
final int step6 = 1;
final int limit6 = (int) (_table.getSize()-1);
for (_i = (int) (0) ; (step6 > 0 && _i <= limit6) || (step6 < 0 && _i >= limit6); _i = ((int)(0 + _i + step6)) ) {
 //BA.debugLineNum = 220;BA.debugLine="Cols = Table.Get(i)";
_cols = (String[])(_table.Get(_i));
 //BA.debugLineNum = 221;BA.debugLine="List1.Add(Cols(0))";
_list1.Add((Object)(_cols[(int) (0)]));
 }
};
 //BA.debugLineNum = 223;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.collections.Map  _executemap(anywheresoftware.b4j.objects.SQL _sql,String _query,String[] _stringargs) throws Exception{
anywheresoftware.b4a.objects.collections.Map _res = null;
anywheresoftware.b4j.objects.SQL.ResultSetWrapper _cur = null;
int _i = 0;
 //BA.debugLineNum = 191;BA.debugLine="Public Sub ExecuteMap(SQL As SQL, Query As String,";
 //BA.debugLineNum = 192;BA.debugLine="Dim res As Map";
_res = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 193;BA.debugLine="Dim cur As ResultSet";
_cur = new anywheresoftware.b4j.objects.SQL.ResultSetWrapper();
 //BA.debugLineNum = 194;BA.debugLine="If StringArgs <> Null Then";
if (_stringargs!= null) { 
 //BA.debugLineNum = 195;BA.debugLine="cur = SQL.ExecQuery2(Query, StringArgs)";
_cur = _sql.ExecQuery2(_query,anywheresoftware.b4a.keywords.Common.ArrayToList(_stringargs));
 }else {
 //BA.debugLineNum = 197;BA.debugLine="cur = SQL.ExecQuery(Query)";
_cur = _sql.ExecQuery(_query);
 };
 //BA.debugLineNum = 199;BA.debugLine="Log(\"ExecuteMap: \" & Query)";
anywheresoftware.b4a.keywords.Common.Log("ExecuteMap: "+_query);
 //BA.debugLineNum = 200;BA.debugLine="If cur.NextRow = False Then";
if (_cur.NextRow()==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 201;BA.debugLine="Log(\"No records found.\")";
anywheresoftware.b4a.keywords.Common.Log("No records found.");
 //BA.debugLineNum = 202;BA.debugLine="Return res";
if (true) return _res;
 };
 //BA.debugLineNum = 204;BA.debugLine="res.Initialize";
_res.Initialize();
 //BA.debugLineNum = 205;BA.debugLine="For i = 0 To cur.ColumnCount - 1";
{
final int step14 = 1;
final int limit14 = (int) (_cur.getColumnCount()-1);
for (_i = (int) (0) ; (step14 > 0 && _i <= limit14) || (step14 < 0 && _i >= limit14); _i = ((int)(0 + _i + step14)) ) {
 //BA.debugLineNum = 206;BA.debugLine="res.Put(cur.GetColumnName(i).ToLowerCase, cur.Ge";
_res.Put((Object)(_cur.GetColumnName(_i).toLowerCase()),(Object)(_cur.GetString2(_i)));
 }
};
 //BA.debugLineNum = 208;BA.debugLine="cur.Close";
_cur.Close();
 //BA.debugLineNum = 209;BA.debugLine="Return res";
if (true) return _res;
 //BA.debugLineNum = 210;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.collections.List  _executememorytable(anywheresoftware.b4j.objects.SQL _sql,String _query,String[] _stringargs,int _limit) throws Exception{
anywheresoftware.b4j.objects.SQL.ResultSetWrapper _cur = null;
anywheresoftware.b4a.objects.collections.List _table = null;
String[] _values = null;
int _col = 0;
 //BA.debugLineNum = 166;BA.debugLine="Public Sub ExecuteMemoryTable(SQL As SQL, Query As";
 //BA.debugLineNum = 167;BA.debugLine="Dim cur As ResultSet";
_cur = new anywheresoftware.b4j.objects.SQL.ResultSetWrapper();
 //BA.debugLineNum = 168;BA.debugLine="If StringArgs = Null Then";
if (_stringargs== null) { 
 //BA.debugLineNum = 169;BA.debugLine="Dim StringArgs(0) As String";
_stringargs = new String[(int) (0)];
java.util.Arrays.fill(_stringargs,"");
 };
 //BA.debugLineNum = 171;BA.debugLine="cur = SQL.ExecQuery2(Query, StringArgs)";
_cur = _sql.ExecQuery2(_query,anywheresoftware.b4a.keywords.Common.ArrayToList(_stringargs));
 //BA.debugLineNum = 172;BA.debugLine="Log(\"ExecuteMemoryTable: \" & Query)";
anywheresoftware.b4a.keywords.Common.Log("ExecuteMemoryTable: "+_query);
 //BA.debugLineNum = 173;BA.debugLine="Dim table As List";
_table = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 174;BA.debugLine="table.Initialize";
_table.Initialize();
 //BA.debugLineNum = 175;BA.debugLine="Do While cur.NextRow";
while (_cur.NextRow()) {
 //BA.debugLineNum = 176;BA.debugLine="Dim values(cur.ColumnCount) As String";
_values = new String[_cur.getColumnCount()];
java.util.Arrays.fill(_values,"");
 //BA.debugLineNum = 177;BA.debugLine="For col = 0 To cur.ColumnCount - 1";
{
final int step11 = 1;
final int limit11 = (int) (_cur.getColumnCount()-1);
for (_col = (int) (0) ; (step11 > 0 && _col <= limit11) || (step11 < 0 && _col >= limit11); _col = ((int)(0 + _col + step11)) ) {
 //BA.debugLineNum = 178;BA.debugLine="values(col) = cur.GetString2(col)";
_values[_col] = _cur.GetString2(_col);
 }
};
 //BA.debugLineNum = 180;BA.debugLine="table.Add(values)";
_table.Add((Object)(_values));
 //BA.debugLineNum = 181;BA.debugLine="If Limit > 0 AND table.Size >= Limit Then Exit";
if (_limit>0 && _table.getSize()>=_limit) { 
if (true) break;};
 }
;
 //BA.debugLineNum = 183;BA.debugLine="cur.Close";
_cur.Close();
 //BA.debugLineNum = 184;BA.debugLine="Return table";
if (true) return _table;
 //BA.debugLineNum = 185;BA.debugLine="End Sub";
return null;
}
public static String  _executetableview(anywheresoftware.b4j.objects.SQL _sql,String _query,String[] _stringargs,int _limit,anywheresoftware.b4j.objects.TableViewWrapper _tableview1) throws Exception{
anywheresoftware.b4j.objects.SQL.ResultSetWrapper _cur = null;
anywheresoftware.b4a.objects.collections.List _cols = null;
int _i = 0;
String[] _values = null;
int _col = 0;
 //BA.debugLineNum = 225;BA.debugLine="Public Sub ExecuteTableView(SQL As SQL, Query As S";
 //BA.debugLineNum = 227;BA.debugLine="TableView1.Items.Clear";
_tableview1.getItems().Clear();
 //BA.debugLineNum = 228;BA.debugLine="Dim cur As ResultSet";
_cur = new anywheresoftware.b4j.objects.SQL.ResultSetWrapper();
 //BA.debugLineNum = 229;BA.debugLine="If StringArgs = Null Then";
if (_stringargs== null) { 
 //BA.debugLineNum = 230;BA.debugLine="Dim StringArgs(0) As String";
_stringargs = new String[(int) (0)];
java.util.Arrays.fill(_stringargs,"");
 };
 //BA.debugLineNum = 232;BA.debugLine="cur = SQL.ExecQuery2(Query, StringArgs)";
_cur = _sql.ExecQuery2(_query,anywheresoftware.b4a.keywords.Common.ArrayToList(_stringargs));
 //BA.debugLineNum = 233;BA.debugLine="Dim cols As List";
_cols = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 234;BA.debugLine="cols.Initialize";
_cols.Initialize();
 //BA.debugLineNum = 235;BA.debugLine="For i = 0 To cur.ColumnCount - 1";
{
final int step9 = 1;
final int limit9 = (int) (_cur.getColumnCount()-1);
for (_i = (int) (0) ; (step9 > 0 && _i <= limit9) || (step9 < 0 && _i >= limit9); _i = ((int)(0 + _i + step9)) ) {
 //BA.debugLineNum = 236;BA.debugLine="cols.Add(cur.GetColumnName(i))";
_cols.Add((Object)(_cur.GetColumnName(_i)));
 }
};
 //BA.debugLineNum = 238;BA.debugLine="TableView1.SetColumns(cols)";
_tableview1.SetColumns(_cols);
 //BA.debugLineNum = 239;BA.debugLine="Do While cur.NextRow";
while (_cur.NextRow()) {
 //BA.debugLineNum = 240;BA.debugLine="Dim values(cur.ColumnCount) As String";
_values = new String[_cur.getColumnCount()];
java.util.Arrays.fill(_values,"");
 //BA.debugLineNum = 241;BA.debugLine="For col = 0 To cur.ColumnCount - 1";
{
final int step15 = 1;
final int limit15 = (int) (_cur.getColumnCount()-1);
for (_col = (int) (0) ; (step15 > 0 && _col <= limit15) || (step15 < 0 && _col >= limit15); _col = ((int)(0 + _col + step15)) ) {
 //BA.debugLineNum = 242;BA.debugLine="values(col) = cur.GetString2(col)";
_values[_col] = _cur.GetString2(_col);
 }
};
 //BA.debugLineNum = 244;BA.debugLine="TableView1.Items.Add(values)";
_tableview1.getItems().Add((Object)(_values));
 //BA.debugLineNum = 245;BA.debugLine="If Limit > 0 AND TableView1.Items.Size >= Limit";
if (_limit>0 && _tableview1.getItems().getSize()>=_limit) { 
if (true) break;};
 }
;
 //BA.debugLineNum = 247;BA.debugLine="cur.Close";
_cur.Close();
 //BA.debugLineNum = 248;BA.debugLine="End Sub";
return "";
}
public static String  _insertmaps(anywheresoftware.b4j.objects.SQL _sql,String _tablename,anywheresoftware.b4a.objects.collections.List _listofmaps) throws Exception{
anywheresoftware.b4a.keywords.StringBuilderWrapper _sb = null;
anywheresoftware.b4a.keywords.StringBuilderWrapper _columns = null;
anywheresoftware.b4a.keywords.StringBuilderWrapper _values = null;
int _i1 = 0;
anywheresoftware.b4a.objects.collections.List _listofvalues = null;
anywheresoftware.b4a.objects.collections.Map _m = null;
int _i2 = 0;
String _col = "";
Object _value = null;
 //BA.debugLineNum = 55;BA.debugLine="Public Sub InsertMaps(SQL As SQL, TableName As Str";
 //BA.debugLineNum = 56;BA.debugLine="Dim sb, columns, values As StringBuilder";
_sb = new anywheresoftware.b4a.keywords.StringBuilderWrapper();
_columns = new anywheresoftware.b4a.keywords.StringBuilderWrapper();
_values = new anywheresoftware.b4a.keywords.StringBuilderWrapper();
 //BA.debugLineNum = 58;BA.debugLine="If ListOfMaps.Size > 1 AND ListOfMaps.Get(0) = Li";
if (_listofmaps.getSize()>1 && (_listofmaps.Get((int) (0))).equals(_listofmaps.Get((int) (1)))) { 
 //BA.debugLineNum = 59;BA.debugLine="Log(\"Same Map found twice in list. Each item in";
anywheresoftware.b4a.keywords.Common.Log("Same Map found twice in list. Each item in the list should include a different map object.");
 //BA.debugLineNum = 60;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 62;BA.debugLine="SQL.BeginTransaction";
_sql.BeginTransaction();
 //BA.debugLineNum = 63;BA.debugLine="Try";
try { //BA.debugLineNum = 64;BA.debugLine="For i1 = 0 To ListOfMaps.Size - 1";
{
final int step8 = 1;
final int limit8 = (int) (_listofmaps.getSize()-1);
for (_i1 = (int) (0) ; (step8 > 0 && _i1 <= limit8) || (step8 < 0 && _i1 >= limit8); _i1 = ((int)(0 + _i1 + step8)) ) {
 //BA.debugLineNum = 65;BA.debugLine="sb.Initialize";
_sb.Initialize();
 //BA.debugLineNum = 66;BA.debugLine="columns.Initialize";
_columns.Initialize();
 //BA.debugLineNum = 67;BA.debugLine="values.Initialize";
_values.Initialize();
 //BA.debugLineNum = 68;BA.debugLine="Dim listOfValues As List";
_listofvalues = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 69;BA.debugLine="listOfValues.Initialize";
_listofvalues.Initialize();
 //BA.debugLineNum = 70;BA.debugLine="sb.Append(\"INSERT INTO [\" & TableName & \"] (\")";
_sb.Append("INSERT INTO ["+_tablename+"] (");
 //BA.debugLineNum = 71;BA.debugLine="Dim m As Map";
_m = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 72;BA.debugLine="m = ListOfMaps.Get(i1)";
_m.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_listofmaps.Get(_i1)));
 //BA.debugLineNum = 73;BA.debugLine="For i2 = 0 To m.Size - 1";
{
final int step17 = 1;
final int limit17 = (int) (_m.getSize()-1);
for (_i2 = (int) (0) ; (step17 > 0 && _i2 <= limit17) || (step17 < 0 && _i2 >= limit17); _i2 = ((int)(0 + _i2 + step17)) ) {
 //BA.debugLineNum = 74;BA.debugLine="Dim col As String";
_col = "";
 //BA.debugLineNum = 75;BA.debugLine="Dim value As Object";
_value = new Object();
 //BA.debugLineNum = 76;BA.debugLine="col = m.GetKeyAt(i2)";
_col = BA.ObjectToString(_m.GetKeyAt(_i2));
 //BA.debugLineNum = 77;BA.debugLine="value = m.GetValueAt(i2)";
_value = _m.GetValueAt(_i2);
 //BA.debugLineNum = 78;BA.debugLine="If i2 > 0 Then";
if (_i2>0) { 
 //BA.debugLineNum = 79;BA.debugLine="columns.Append(\", \")";
_columns.Append(", ");
 //BA.debugLineNum = 80;BA.debugLine="values.Append(\", \")";
_values.Append(", ");
 };
 //BA.debugLineNum = 82;BA.debugLine="columns.Append(EscapeField(col))";
_columns.Append(_escapefield(_col));
 //BA.debugLineNum = 84;BA.debugLine="values.Append(\"?\")";
_values.Append("?");
 //BA.debugLineNum = 85;BA.debugLine="listOfValues.Add(value)";
_listofvalues.Add(_value);
 }
};
 //BA.debugLineNum = 87;BA.debugLine="sb.Append(columns.ToString)";
_sb.Append(_columns.ToString());
 //BA.debugLineNum = 88;BA.debugLine="sb.Append(\") VALUES (\")";
_sb.Append(") VALUES (");
 //BA.debugLineNum = 89;BA.debugLine="sb.Append(values.ToString)";
_sb.Append(_values.ToString());
 //BA.debugLineNum = 90;BA.debugLine="sb.Append(\")\")";
_sb.Append(")");
 //BA.debugLineNum = 91;BA.debugLine="If i1 = 0 Then Log(\"InsertMaps (first query out";
if (_i1==0) { 
anywheresoftware.b4a.keywords.Common.Log("InsertMaps (first query out of "+BA.NumberToString(_listofmaps.getSize())+"): "+_sb.ToString());};
 //BA.debugLineNum = 92;BA.debugLine="SQL.ExecNonQuery2(sb.ToString, listOfValues)";
_sql.ExecNonQuery2(_sb.ToString(),_listofvalues);
 }
};
 //BA.debugLineNum = 94;BA.debugLine="SQL.TransactionSuccessful";
_sql.TransactionSuccessful();
 } 
       catch (Exception e39) {
			ba.setLastException(e39); //BA.debugLineNum = 96;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.Log(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(ba)));
 //BA.debugLineNum = 97;BA.debugLine="SQL.Rollback";
_sql.Rollback();
 };
 //BA.debugLineNum = 99;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 2;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 3;BA.debugLine="Private fx As JFX";
_fx = new anywheresoftware.b4j.objects.JFX();
 //BA.debugLineNum = 4;BA.debugLine="Public DB_REAL, DB_INTEGER, DB_BLOB, DB_TEXT As S";
_db_real = "";
_db_integer = "";
_db_blob = "";
_db_text = "";
 //BA.debugLineNum = 5;BA.debugLine="DB_REAL = \"REAL\"";
_db_real = "REAL";
 //BA.debugLineNum = 6;BA.debugLine="DB_INTEGER = \"INTEGER\"";
_db_integer = "INTEGER";
 //BA.debugLineNum = 7;BA.debugLine="DB_BLOB = \"BLOB\"";
_db_blob = "BLOB";
 //BA.debugLineNum = 8;BA.debugLine="DB_TEXT = \"TEXT\"";
_db_text = "TEXT";
 //BA.debugLineNum = 9;BA.debugLine="Dim HtmlCSS As String = \"table {width: 100%;borde";
_htmlcss = "table {width: 100%;border: 1px solid #cef;text-align: left; }"+" th { font-weight: bold;	background-color: #acf;	border-bottom: 1px solid #cef; }"+"td,th {	padding: 4px 5px; }"+".odd {background-color: #def; } .odd td {border-bottom: 1px solid #cef; }"+"a { text-decoration:none; color: #000;}";
 //BA.debugLineNum = 14;BA.debugLine="End Sub";
return "";
}
public static String  _updaterecord(anywheresoftware.b4j.objects.SQL _sql,String _tablename,String _field,Object _newvalue,anywheresoftware.b4a.objects.collections.Map _wherefieldequals) throws Exception{
anywheresoftware.b4a.keywords.StringBuilderWrapper _sb = null;
anywheresoftware.b4a.objects.collections.List _args = null;
int _i = 0;
 //BA.debugLineNum = 103;BA.debugLine="Public Sub UpdateRecord(SQL As SQL, TableName As S";
 //BA.debugLineNum = 105;BA.debugLine="Dim sb As StringBuilder";
_sb = new anywheresoftware.b4a.keywords.StringBuilderWrapper();
 //BA.debugLineNum = 106;BA.debugLine="sb.Initialize";
_sb.Initialize();
 //BA.debugLineNum = 107;BA.debugLine="sb.Append(\"UPDATE \").Append(EscapeField(TableName";
_sb.Append("UPDATE ").Append(_escapefield(_tablename)).Append(" SET ").Append(_escapefield(_field)).Append(" = ? WHERE ");
 //BA.debugLineNum = 109;BA.debugLine="If WhereFieldEquals.Size = 0 Then";
if (_wherefieldequals.getSize()==0) { 
 //BA.debugLineNum = 110;BA.debugLine="Log(\"WhereFieldEquals map empty!\")";
anywheresoftware.b4a.keywords.Common.Log("WhereFieldEquals map empty!");
 //BA.debugLineNum = 111;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 113;BA.debugLine="Dim args As List";
_args = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 114;BA.debugLine="args.Initialize";
_args.Initialize();
 //BA.debugLineNum = 115;BA.debugLine="args.Add(NewValue)";
_args.Add(_newvalue);
 //BA.debugLineNum = 116;BA.debugLine="For i = 0 To WhereFieldEquals.Size - 1";
{
final int step11 = 1;
final int limit11 = (int) (_wherefieldequals.getSize()-1);
for (_i = (int) (0) ; (step11 > 0 && _i <= limit11) || (step11 < 0 && _i >= limit11); _i = ((int)(0 + _i + step11)) ) {
 //BA.debugLineNum = 117;BA.debugLine="If i > 0 Then sb.Append(\" AND \")";
if (_i>0) { 
_sb.Append(" AND ");};
 //BA.debugLineNum = 118;BA.debugLine="sb.Append(EscapeField(WhereFieldEquals.GetKeyAt(";
_sb.Append(_escapefield(BA.ObjectToString(_wherefieldequals.GetKeyAt(_i)))).Append(" = ?");
 //BA.debugLineNum = 119;BA.debugLine="args.Add(WhereFieldEquals.GetValueAt(i))";
_args.Add(_wherefieldequals.GetValueAt(_i));
 }
};
 //BA.debugLineNum = 121;BA.debugLine="Log(\"UpdateRecord: \" & sb.ToString)";
anywheresoftware.b4a.keywords.Common.Log("UpdateRecord: "+_sb.ToString());
 //BA.debugLineNum = 122;BA.debugLine="SQL.ExecNonQuery2(sb.ToString, args)";
_sql.ExecNonQuery2(_sb.ToString(),_args);
 //BA.debugLineNum = 123;BA.debugLine="End Sub";
return "";
}
public static String  _updaterecord2(anywheresoftware.b4j.objects.SQL _sql,String _tablename,anywheresoftware.b4a.objects.collections.Map _fields,anywheresoftware.b4a.objects.collections.Map _wherefieldequals) throws Exception{
anywheresoftware.b4a.keywords.StringBuilderWrapper _sb = null;
anywheresoftware.b4a.objects.collections.List _args = null;
int _i = 0;
 //BA.debugLineNum = 127;BA.debugLine="Public Sub UpdateRecord2(SQL As SQL, TableName As";
 //BA.debugLineNum = 128;BA.debugLine="If WhereFieldEquals.Size = 0 Then";
if (_wherefieldequals.getSize()==0) { 
 //BA.debugLineNum = 129;BA.debugLine="Log(\"WhereFieldEquals map empty!\")";
anywheresoftware.b4a.keywords.Common.Log("WhereFieldEquals map empty!");
 //BA.debugLineNum = 130;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 132;BA.debugLine="If Fields.Size = 0 Then";
if (_fields.getSize()==0) { 
 //BA.debugLineNum = 133;BA.debugLine="Log(\"Fields empty\")";
anywheresoftware.b4a.keywords.Common.Log("Fields empty");
 //BA.debugLineNum = 134;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 136;BA.debugLine="Dim sb As StringBuilder";
_sb = new anywheresoftware.b4a.keywords.StringBuilderWrapper();
 //BA.debugLineNum = 137;BA.debugLine="sb.Initialize";
_sb.Initialize();
 //BA.debugLineNum = 138;BA.debugLine="sb.Append(\"UPDATE \").Append(EscapeField(TableName";
_sb.Append("UPDATE ").Append(_escapefield(_tablename)).Append(" SET ");
 //BA.debugLineNum = 139;BA.debugLine="Dim args As List";
_args = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 140;BA.debugLine="args.Initialize";
_args.Initialize();
 //BA.debugLineNum = 141;BA.debugLine="For i=0 To Fields.Size-1";
{
final int step14 = 1;
final int limit14 = (int) (_fields.getSize()-1);
for (_i = (int) (0) ; (step14 > 0 && _i <= limit14) || (step14 < 0 && _i >= limit14); _i = ((int)(0 + _i + step14)) ) {
 //BA.debugLineNum = 142;BA.debugLine="If i<>Fields.Size-1 Then";
if (_i!=_fields.getSize()-1) { 
 //BA.debugLineNum = 143;BA.debugLine="sb.Append(EscapeField(Fields.GetKeyAt(i))).Appe";
_sb.Append(_escapefield(BA.ObjectToString(_fields.GetKeyAt(_i)))).Append("=?,");
 }else {
 //BA.debugLineNum = 145;BA.debugLine="sb.Append(EscapeField(Fields.GetKeyAt(i))).Appe";
_sb.Append(_escapefield(BA.ObjectToString(_fields.GetKeyAt(_i)))).Append("=?");
 };
 //BA.debugLineNum = 147;BA.debugLine="args.Add(Fields.GetValueAt(i))";
_args.Add(_fields.GetValueAt(_i));
 }
};
 //BA.debugLineNum = 150;BA.debugLine="sb.Append(\" WHERE \")";
_sb.Append(" WHERE ");
 //BA.debugLineNum = 151;BA.debugLine="For i = 0 To WhereFieldEquals.Size - 1";
{
final int step23 = 1;
final int limit23 = (int) (_wherefieldequals.getSize()-1);
for (_i = (int) (0) ; (step23 > 0 && _i <= limit23) || (step23 < 0 && _i >= limit23); _i = ((int)(0 + _i + step23)) ) {
 //BA.debugLineNum = 152;BA.debugLine="If i > 0 Then";
if (_i>0) { 
 //BA.debugLineNum = 153;BA.debugLine="sb.Append(\" AND \")";
_sb.Append(" AND ");
 };
 //BA.debugLineNum = 155;BA.debugLine="sb.Append(EscapeField(WhereFieldEquals.GetKeyAt(";
_sb.Append(_escapefield(BA.ObjectToString(_wherefieldequals.GetKeyAt(_i)))).Append(" = ?");
 //BA.debugLineNum = 156;BA.debugLine="args.Add(WhereFieldEquals.GetValueAt(i))";
_args.Add(_wherefieldequals.GetValueAt(_i));
 }
};
 //BA.debugLineNum = 158;BA.debugLine="Log(\"UpdateRecord: \" & sb.ToString)";
anywheresoftware.b4a.keywords.Common.Log("UpdateRecord: "+_sb.ToString());
 //BA.debugLineNum = 159;BA.debugLine="SQL.ExecNonQuery2(sb.ToString, args)";
_sql.ExecNonQuery2(_sb.ToString(),_args);
 //BA.debugLineNum = 160;BA.debugLine="End Sub";
return "";
}
}
