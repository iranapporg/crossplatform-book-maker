package b4j.example;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.debug.*;

public class actbookmenu extends Object{
public static actbookmenu mostCurrent = new actbookmenu();

public static BA ba;
static {
		ba = new  anywheresoftware.b4j.objects.FxBA("b4j.example", "b4j.example.actbookmenu", null);
		ba.loadHtSubs(actbookmenu.class);
        if (ba.getClass().getName().endsWith("ShellBA")) {
			
			ba.raiseEvent2(null, true, "SHELL", false);
			ba.raiseEvent2(null, true, "CREATE", true, "b4j.example.actbookmenu", ba);
		}
	}
    public static Class<?> getObject() {
		return actbookmenu.class;
	}

 public static anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4j.objects.JFX _fx = null;
public static anywheresoftware.b4j.objects.Form _frm1 = null;
public static String _bookid = "";
public static anywheresoftware.b4j.objects.TabPaneWrapper _tab1 = null;
public static b4j.example.database _db = null;
public static anywheresoftware.b4j.objects.ListViewWrapper _lv1 = null;
public static anywheresoftware.b4j.objects.ListViewWrapper _lvsummary = null;
public static anywheresoftware.b4a.objects.collections.List _stack = null;
public static anywheresoftware.b4j.objects.PaneWrapper.ConcretePaneWrapper _pnlover = null;
public static anywheresoftware.b4j.objects.ImageViewWrapper _imgicon = null;
public static anywheresoftware.b4j.objects.LabelWrapper _lblcatname = null;
public static anywheresoftware.b4j.objects.ListViewWrapper _lvnote = null;
public static anywheresoftware.b4j.objects.ListViewWrapper _lvbookmark = null;
public static anywheresoftware.b4a.objects.collections.List _hightlights = null;
public static anywheresoftware.b4a.objects.collections.List _notes = null;
public static anywheresoftware.b4a.objects.collections.List _bookmark = null;
public static anywheresoftware.b4j.objects.TextInputControlWrapper.TextFieldWrapper _txtsearch = null;
public static anywheresoftware.b4j.objects.ListViewWrapper _lvsearch = null;
public static anywheresoftware.b4j.objects.PaneWrapper.ConcretePaneWrapper _pane1 = null;
public static anywheresoftware.b4j.objects.ButtonWrapper _btndelete_remove = null;
public static anywheresoftware.b4j.objects.ButtonWrapper _btnpdf = null;
public static b4j.example.httputils2service _httputils2service = null;
public static b4j.example.main _main = null;
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
public static String  _btndelete_remove_action() throws Exception{
anywheresoftware.b4j.objects.ButtonWrapper _b1 = null;
anywheresoftware.b4a.objects.collections.Map _m2 = null;
 //BA.debugLineNum = 291;BA.debugLine="Sub btndelete_remove_Action";
 //BA.debugLineNum = 293;BA.debugLine="Dim b1 As Button";
_b1 = new anywheresoftware.b4j.objects.ButtonWrapper();
 //BA.debugLineNum = 294;BA.debugLine="b1 = Sender";
_b1.setObject((javafx.scene.control.Button)(anywheresoftware.b4a.keywords.Common.Sender(ba)));
 //BA.debugLineNum = 296;BA.debugLine="Dim m2 As Map";
_m2 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 297;BA.debugLine="m2 = b1.Tag";
_m2.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_b1.getTag()));
 //BA.debugLineNum = 299;BA.debugLine="Library.Deletebookmark(BookID,m2)";
_library._deletebookmark(_bookid,_m2);
 //BA.debugLineNum = 300;BA.debugLine="fx.Msgbox(frm1,\"نشان شده مورد نظر حذف شد\",\"توجه\")";
_fx.Msgbox(_frm1,"نشان شده مورد نظر حذف شد","توجه");
 //BA.debugLineNum = 301;BA.debugLine="LoadTopicAttachment";
_loadtopicattachment();
 //BA.debugLineNum = 303;BA.debugLine="End Sub";
return "";
}
public static String  _btnpdf_action() throws Exception{
 //BA.debugLineNum = 305;BA.debugLine="Sub btnpdf_Action";
 //BA.debugLineNum = 306;BA.debugLine="fx.ShowExternalDocument(File.GetUri(File.DirApp &";
_fx.ShowExternalDocument(anywheresoftware.b4a.keywords.Common.File.GetUri(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data"+"/Data"+"/book/"+_bookid,"book.pdf"));
 //BA.debugLineNum = 307;BA.debugLine="End Sub";
return "";
}
public static String  _btnsearch_action() throws Exception{
anywheresoftware.b4a.objects.collections.List _ls = null;
int _i = 0;
anywheresoftware.b4a.objects.collections.Map _t = null;
anywheresoftware.b4j.objects.PaneWrapper.ConcretePaneWrapper _p1 = null;
 //BA.debugLineNum = 250;BA.debugLine="Sub btnsearch_Action";
 //BA.debugLineNum = 252;BA.debugLine="Dim ls As List";
_ls = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 253;BA.debugLine="ls = db.SearchInChapters(txtsearch.Text)";
_ls = _db._searchinchapters(_txtsearch.getText());
 //BA.debugLineNum = 255;BA.debugLine="If ls.Size = 0 Then";
if (_ls.getSize()==0) { 
 //BA.debugLineNum = 256;BA.debugLine="fx.Msgbox(frm1,\"هیچ داده ای پیدا نشد\",\"توجه\")";
_fx.Msgbox(_frm1,"هیچ داده ای پیدا نشد","توجه");
 //BA.debugLineNum = 257;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 260;BA.debugLine="If tab1.Tabs.Size = 5 Then";
if (_tab1.getTabs().getSize()==5) { 
 //BA.debugLineNum = 261;BA.debugLine="tab1.LoadLayout(\"frmsearchinbook\",\"نتیجه\")";
_tab1.LoadLayout(ba,"frmsearchinbook","نتیجه");
 };
 //BA.debugLineNum = 264;BA.debugLine="lvsearch.Items.Clear";
_lvsearch.getItems().Clear();
 //BA.debugLineNum = 266;BA.debugLine="txtsearch.Tag = txtsearch.Text";
_txtsearch.setTag((Object)(_txtsearch.getText()));
 //BA.debugLineNum = 267;BA.debugLine="txtsearch.Text = \"\"";
_txtsearch.setText("");
 //BA.debugLineNum = 268;BA.debugLine="tab1.SelectedIndex = 5";
_tab1.setSelectedIndex((int) (5));
 //BA.debugLineNum = 270;BA.debugLine="For i = 0 To ls.Size - 1";
{
final int step14 = 1;
final int limit14 = (int) (_ls.getSize()-1);
for (_i = (int) (0) ; (step14 > 0 && _i <= limit14) || (step14 < 0 && _i >= limit14); _i = ((int)(0 + _i + step14)) ) {
 //BA.debugLineNum = 271;BA.debugLine="Dim t As Map";
_t = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 272;BA.debugLine="t = ls.Get(i)";
_t.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_ls.Get(_i)));
 //BA.debugLineNum = 273;BA.debugLine="t.Put(\"sType\",\"book\")";
_t.Put((Object)("sType"),(Object)("book"));
 //BA.debugLineNum = 274;BA.debugLine="Dim p1 As Pane";
_p1 = new anywheresoftware.b4j.objects.PaneWrapper.ConcretePaneWrapper();
 //BA.debugLineNum = 275;BA.debugLine="p1.Initialize(\"\")";
_p1.Initialize(ba,"");
 //BA.debugLineNum = 276;BA.debugLine="p1.LoadLayout(\"frmtemplate_category\")";
_p1.LoadLayout(ba,"frmtemplate_category");
 //BA.debugLineNum = 277;BA.debugLine="p1.SetSize(400,60)";
_p1.SetSize(400,60);
 //BA.debugLineNum = 278;BA.debugLine="lvsearch.Items.Add(p1)";
_lvsearch.getItems().Add((Object)(_p1.getObject()));
 //BA.debugLineNum = 279;BA.debugLine="lblcatname.Text = t.Get(\"sTitle\")";
_lblcatname.setText(BA.ObjectToString(_t.Get((Object)("sTitle"))));
 //BA.debugLineNum = 280;BA.debugLine="pnlover.Tag = t";
_pnlover.setTag((Object)(_t.getObject()));
 //BA.debugLineNum = 281;BA.debugLine="imgicon.SetImage(fx.LoadImage(File.DirAssets,\"bo";
_imgicon.SetImage((javafx.scene.image.Image)(_fx.LoadImage(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"book.png").getObject()));
 }
};
 //BA.debugLineNum = 284;BA.debugLine="End Sub";
return "";
}
public static String  _frm1_closed() throws Exception{
 //BA.debugLineNum = 309;BA.debugLine="Sub frm1_Closed";
 //BA.debugLineNum = 310;BA.debugLine="db.CloseDatabase";
_db._closedatabase();
 //BA.debugLineNum = 311;BA.debugLine="End Sub";
return "";
}
public static String  _loaditems(String _sid) throws Exception{
anywheresoftware.b4a.objects.collections.List _cat = null;
anywheresoftware.b4a.objects.collections.List _book = null;
anywheresoftware.b4a.objects.collections.List _summary = null;
String _temp = "";
anywheresoftware.b4j.objects.PaneWrapper.ConcretePaneWrapper _p1 = null;
int _i1 = 0;
anywheresoftware.b4a.objects.collections.Map _m12 = null;
String _te1 = "";
int _i = 0;
anywheresoftware.b4a.objects.collections.Map _m1 = null;
String _te = "";
int _j = 0;
anywheresoftware.b4a.objects.collections.Map _m2 = null;
 //BA.debugLineNum = 55;BA.debugLine="Sub LoadItems(sID As String)";
 //BA.debugLineNum = 57;BA.debugLine="lv1.Items.Clear";
_lv1.getItems().Clear();
 //BA.debugLineNum = 59;BA.debugLine="Dim cat,book,summary As List";
_cat = new anywheresoftware.b4a.objects.collections.List();
_book = new anywheresoftware.b4a.objects.collections.List();
_summary = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 60;BA.debugLine="cat = db.GetCategory(sID)";
_cat = _db._getcategory(_sid);
 //BA.debugLineNum = 61;BA.debugLine="book = db.GetChapters(sID,False)";
_book = _db._getchapters(_sid,anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 62;BA.debugLine="summary = db.GetChapters(sID,True)";
_summary = _db._getchapters(_sid,anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 64;BA.debugLine="If stack.Size > 0 Then";
if (_stack.getSize()>0) { 
 //BA.debugLineNum = 66;BA.debugLine="Dim temp As String";
_temp = "";
 //BA.debugLineNum = 67;BA.debugLine="temp = stack.Get(stack.Size-1)";
_temp = BA.ObjectToString(_stack.Get((int) (_stack.getSize()-1)));
 //BA.debugLineNum = 69;BA.debugLine="Dim p1 As Pane";
_p1 = new anywheresoftware.b4j.objects.PaneWrapper.ConcretePaneWrapper();
 //BA.debugLineNum = 70;BA.debugLine="p1.Initialize(\"\")";
_p1.Initialize(ba,"");
 //BA.debugLineNum = 71;BA.debugLine="p1.LoadLayout(\"frmitembook\")";
_p1.LoadLayout(ba,"frmitembook");
 //BA.debugLineNum = 72;BA.debugLine="p1.SetSize(400,60)";
_p1.SetSize(400,60);
 //BA.debugLineNum = 73;BA.debugLine="lv1.Items.Add(p1)";
_lv1.getItems().Add((Object)(_p1.getObject()));
 //BA.debugLineNum = 74;BA.debugLine="lblcatname.Text = \"برگشت ›\"";
_lblcatname.setText("برگشت ›");
 //BA.debugLineNum = 75;BA.debugLine="pnlover.Tag = CreateMap(\"sID\":temp,\"sType\":\"retu";
_pnlover.setTag((Object)(anywheresoftware.b4a.keywords.Common.createMap(new Object[] {(Object)("sID"),(Object)(_temp),(Object)("sType"),(Object)("return")}).getObject()));
 //BA.debugLineNum = 76;BA.debugLine="imgicon.SetImage(fx.LoadImage(File.DirAssets,\"ba";
_imgicon.SetImage((javafx.scene.image.Image)(_fx.LoadImage(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"back.png").getObject()));
 };
 //BA.debugLineNum = 80;BA.debugLine="For i1 = 0 To summary.Size - 1";
{
final int step18 = 1;
final int limit18 = (int) (_summary.getSize()-1);
for (_i1 = (int) (0) ; (step18 > 0 && _i1 <= limit18) || (step18 < 0 && _i1 >= limit18); _i1 = ((int)(0 + _i1 + step18)) ) {
 //BA.debugLineNum = 82;BA.debugLine="Dim m12 As Map";
_m12 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 83;BA.debugLine="m12 = summary.Get(i1)";
_m12.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_summary.Get(_i1)));
 //BA.debugLineNum = 85;BA.debugLine="Dim te1 As String";
_te1 = "";
 //BA.debugLineNum = 86;BA.debugLine="te1 = m12.Get(\"sTitle\")";
_te1 = BA.ObjectToString(_m12.Get((Object)("sTitle")));
 //BA.debugLineNum = 88;BA.debugLine="Dim p1 As Pane";
_p1 = new anywheresoftware.b4j.objects.PaneWrapper.ConcretePaneWrapper();
 //BA.debugLineNum = 89;BA.debugLine="p1.Initialize(\"\")";
_p1.Initialize(ba,"");
 //BA.debugLineNum = 90;BA.debugLine="p1.LoadLayout(\"frmitembook\")";
_p1.LoadLayout(ba,"frmitembook");
 //BA.debugLineNum = 91;BA.debugLine="p1.SetSize(400,60)";
_p1.SetSize(400,60);
 //BA.debugLineNum = 92;BA.debugLine="lvsummary.Items.Add(p1)";
_lvsummary.getItems().Add((Object)(_p1.getObject()));
 //BA.debugLineNum = 93;BA.debugLine="lblcatname.Text = te1";
_lblcatname.setText(_te1);
 //BA.debugLineNum = 94;BA.debugLine="pnlover.Tag = m12";
_pnlover.setTag((Object)(_m12.getObject()));
 //BA.debugLineNum = 95;BA.debugLine="imgicon.SetImage(fx.LoadImage(File.DirAssets,\"bo";
_imgicon.SetImage((javafx.scene.image.Image)(_fx.LoadImage(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"book.png").getObject()));
 }
};
 //BA.debugLineNum = 99;BA.debugLine="For i = 0 To cat.Size - 1";
{
final int step32 = 1;
final int limit32 = (int) (_cat.getSize()-1);
for (_i = (int) (0) ; (step32 > 0 && _i <= limit32) || (step32 < 0 && _i >= limit32); _i = ((int)(0 + _i + step32)) ) {
 //BA.debugLineNum = 101;BA.debugLine="Dim m1 As Map";
_m1 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 102;BA.debugLine="m1 = cat.Get(i)";
_m1.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_cat.Get(_i)));
 //BA.debugLineNum = 104;BA.debugLine="Dim te As String";
_te = "";
 //BA.debugLineNum = 105;BA.debugLine="te = m1.Get(\"sTitle\")";
_te = BA.ObjectToString(_m1.Get((Object)("sTitle")));
 //BA.debugLineNum = 107;BA.debugLine="If te.IndexOf(\"چکیده\") > -1 Then Continue";
if (_te.indexOf("چکیده")>-1) { 
if (true) continue;};
 //BA.debugLineNum = 109;BA.debugLine="Dim p1 As Pane";
_p1 = new anywheresoftware.b4j.objects.PaneWrapper.ConcretePaneWrapper();
 //BA.debugLineNum = 110;BA.debugLine="p1.Initialize(\"\")";
_p1.Initialize(ba,"");
 //BA.debugLineNum = 111;BA.debugLine="p1.LoadLayout(\"frmitembook\")";
_p1.LoadLayout(ba,"frmitembook");
 //BA.debugLineNum = 112;BA.debugLine="p1.SetSize(lv1.Width,60)";
_p1.SetSize(_lv1.getWidth(),60);
 //BA.debugLineNum = 113;BA.debugLine="lv1.Items.Add(p1)";
_lv1.getItems().Add((Object)(_p1.getObject()));
 //BA.debugLineNum = 114;BA.debugLine="lblcatname.Text = te & \" ›\"";
_lblcatname.setText(_te+" ›");
 //BA.debugLineNum = 115;BA.debugLine="pnlover.Tag = m1";
_pnlover.setTag((Object)(_m1.getObject()));
 //BA.debugLineNum = 116;BA.debugLine="imgicon.SetImage(fx.LoadImage(File.DirAssets,\"bo";
_imgicon.SetImage((javafx.scene.image.Image)(_fx.LoadImage(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"book-icon.png").getObject()));
 }
};
 //BA.debugLineNum = 120;BA.debugLine="For j = 0 To book.Size - 1";
{
final int step47 = 1;
final int limit47 = (int) (_book.getSize()-1);
for (_j = (int) (0) ; (step47 > 0 && _j <= limit47) || (step47 < 0 && _j >= limit47); _j = ((int)(0 + _j + step47)) ) {
 //BA.debugLineNum = 122;BA.debugLine="Dim m2 As Map";
_m2 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 123;BA.debugLine="m2 = book.Get(j)";
_m2.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_book.Get(_j)));
 //BA.debugLineNum = 125;BA.debugLine="If m2.Get(\"sType\") = \"summary\" Then Continue";
if ((_m2.Get((Object)("sType"))).equals((Object)("summary"))) { 
if (true) continue;};
 //BA.debugLineNum = 127;BA.debugLine="Dim p1 As Pane";
_p1 = new anywheresoftware.b4j.objects.PaneWrapper.ConcretePaneWrapper();
 //BA.debugLineNum = 128;BA.debugLine="p1.Initialize(\"\")";
_p1.Initialize(ba,"");
 //BA.debugLineNum = 129;BA.debugLine="p1.LoadLayout(\"frmitembook\")";
_p1.LoadLayout(ba,"frmitembook");
 //BA.debugLineNum = 130;BA.debugLine="p1.SetSize(400,60)";
_p1.SetSize(400,60);
 //BA.debugLineNum = 131;BA.debugLine="lv1.Items.Add(p1)";
_lv1.getItems().Add((Object)(_p1.getObject()));
 //BA.debugLineNum = 133;BA.debugLine="lblcatname.Text = m2.Get(\"sTitle\")";
_lblcatname.setText(BA.ObjectToString(_m2.Get((Object)("sTitle"))));
 //BA.debugLineNum = 134;BA.debugLine="pnlover.Tag = m2";
_pnlover.setTag((Object)(_m2.getObject()));
 //BA.debugLineNum = 135;BA.debugLine="imgicon.SetImage(fx.LoadImage(File.DirAssets,\"bo";
_imgicon.SetImage((javafx.scene.image.Image)(_fx.LoadImage(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"book.png").getObject()));
 }
};
 //BA.debugLineNum = 139;BA.debugLine="End Sub";
return "";
}
public static String  _loadtopicattachment() throws Exception{
int _i1 = 0;
anywheresoftware.b4a.objects.collections.Map _map1 = null;
anywheresoftware.b4j.objects.PaneWrapper.ConcretePaneWrapper _p1 = null;
int _i3 = 0;
anywheresoftware.b4a.objects.collections.Map _m3 = null;
 //BA.debugLineNum = 204;BA.debugLine="Sub LoadTopicAttachment";
 //BA.debugLineNum = 206;BA.debugLine="Hightlights.Initialize";
_hightlights.Initialize();
 //BA.debugLineNum = 207;BA.debugLine="Notes.Initialize";
_notes.Initialize();
 //BA.debugLineNum = 208;BA.debugLine="Bookmark.Initialize";
_bookmark.Initialize();
 //BA.debugLineNum = 210;BA.debugLine="Notes = Library.ListNote(BookID)";
_notes = _library._listnote(_bookid);
 //BA.debugLineNum = 211;BA.debugLine="Bookmark = Library.Listbookmark(BookID)";
_bookmark = _library._listbookmark(_bookid);
 //BA.debugLineNum = 213;BA.debugLine="lvnote.Items.Clear";
_lvnote.getItems().Clear();
 //BA.debugLineNum = 214;BA.debugLine="lvbookmark.Items.Clear";
_lvbookmark.getItems().Clear();
 //BA.debugLineNum = 216;BA.debugLine="If Notes.IsInitialized Then";
if (_notes.IsInitialized()) { 
 //BA.debugLineNum = 217;BA.debugLine="For i1 = 0 To Notes.Size - 1";
{
final int step9 = 1;
final int limit9 = (int) (_notes.getSize()-1);
for (_i1 = (int) (0) ; (step9 > 0 && _i1 <= limit9) || (step9 < 0 && _i1 >= limit9); _i1 = ((int)(0 + _i1 + step9)) ) {
 //BA.debugLineNum = 218;BA.debugLine="Dim map1 As Map";
_map1 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 219;BA.debugLine="map1 = Notes.Get(i1)";
_map1.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_notes.Get(_i1)));
 //BA.debugLineNum = 220;BA.debugLine="Dim p1 As Pane";
_p1 = new anywheresoftware.b4j.objects.PaneWrapper.ConcretePaneWrapper();
 //BA.debugLineNum = 221;BA.debugLine="p1.Initialize(\"\")";
_p1.Initialize(ba,"");
 //BA.debugLineNum = 222;BA.debugLine="p1.LoadLayout(\"frmtemplate_book_item\")";
_p1.LoadLayout(ba,"frmtemplate_book_item");
 //BA.debugLineNum = 223;BA.debugLine="p1.SetSize(500,60)";
_p1.SetSize(500,60);
 //BA.debugLineNum = 224;BA.debugLine="lvnote.Items.Add(p1)";
_lvnote.getItems().Add((Object)(_p1.getObject()));
 //BA.debugLineNum = 225;BA.debugLine="btndelete_remove.Visible = False";
_btndelete_remove.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 226;BA.debugLine="lblcatname.Text = db.GetTopicID(map1.Get(\"Topic";
_lblcatname.setText(_db._gettopicid(BA.ObjectToString(_map1.Get((Object)("TopicID")))));
 //BA.debugLineNum = 227;BA.debugLine="pnlover.Tag = map1";
_pnlover.setTag((Object)(_map1.getObject()));
 //BA.debugLineNum = 228;BA.debugLine="imgicon.SetImage(fx.LoadImage(File.DirAssets,\"n";
_imgicon.SetImage((javafx.scene.image.Image)(_fx.LoadImage(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"note.png").getObject()));
 }
};
 };
 //BA.debugLineNum = 232;BA.debugLine="If Bookmark.IsInitialized Then";
if (_bookmark.IsInitialized()) { 
 //BA.debugLineNum = 233;BA.debugLine="For i3 = 0 To Bookmark.Size - 1";
{
final int step24 = 1;
final int limit24 = (int) (_bookmark.getSize()-1);
for (_i3 = (int) (0) ; (step24 > 0 && _i3 <= limit24) || (step24 < 0 && _i3 >= limit24); _i3 = ((int)(0 + _i3 + step24)) ) {
 //BA.debugLineNum = 234;BA.debugLine="Dim m3 As Map";
_m3 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 235;BA.debugLine="m3 = Bookmark.Get(i3)";
_m3.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_bookmark.Get(_i3)));
 //BA.debugLineNum = 236;BA.debugLine="Dim p1 As Pane";
_p1 = new anywheresoftware.b4j.objects.PaneWrapper.ConcretePaneWrapper();
 //BA.debugLineNum = 237;BA.debugLine="p1.Initialize(\"\")";
_p1.Initialize(ba,"");
 //BA.debugLineNum = 238;BA.debugLine="p1.LoadLayout(\"frmtemplate_book_item\")";
_p1.LoadLayout(ba,"frmtemplate_book_item");
 //BA.debugLineNum = 239;BA.debugLine="p1.SetSize(490,60)";
_p1.SetSize(490,60);
 //BA.debugLineNum = 240;BA.debugLine="lvbookmark.Items.Add(p1)";
_lvbookmark.getItems().Add((Object)(_p1.getObject()));
 //BA.debugLineNum = 241;BA.debugLine="lblcatname.Text = \"فصل \" & db.GetTopicID(m3.Get";
_lblcatname.setText("فصل "+_db._gettopicid(BA.ObjectToString(_m3.Get((Object)("TopicID")))));
 //BA.debugLineNum = 242;BA.debugLine="btndelete_remove.Tag = CreateMap(\"id\":m3.Get(\"O";
_btndelete_remove.setTag((Object)(anywheresoftware.b4a.keywords.Common.createMap(new Object[] {(Object)("id"),_m3.Get((Object)("Offset")),(Object)("topicid"),_m3.Get((Object)("TopicID"))}).getObject()));
 //BA.debugLineNum = 243;BA.debugLine="pnlover.Tag = m3";
_pnlover.setTag((Object)(_m3.getObject()));
 //BA.debugLineNum = 244;BA.debugLine="imgicon.SetImage(fx.LoadImage(File.DirAssets,\"b";
_imgicon.SetImage((javafx.scene.image.Image)(_fx.LoadImage(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bookmark.png").getObject()));
 }
};
 };
 //BA.debugLineNum = 248;BA.debugLine="End Sub";
return "";
}
public static String  _pnlover_mouseclicked(anywheresoftware.b4j.objects.NodeWrapper.MouseEventWrapper _eventdata) throws Exception{
anywheresoftware.b4j.objects.PaneWrapper.ConcretePaneWrapper _pn = null;
anywheresoftware.b4a.objects.collections.Map _m1 = null;
anywheresoftware.b4a.objects.collections.Map _temp = null;
String _val = "";
 //BA.debugLineNum = 141;BA.debugLine="Sub pnlover_MouseClicked (EventData As MouseEvent)";
 //BA.debugLineNum = 143;BA.debugLine="Dim pn As Pane";
_pn = new anywheresoftware.b4j.objects.PaneWrapper.ConcretePaneWrapper();
 //BA.debugLineNum = 144;BA.debugLine="pn = Sender";
_pn.setObject((javafx.scene.layout.Pane)(anywheresoftware.b4a.keywords.Common.Sender(ba)));
 //BA.debugLineNum = 145;BA.debugLine="Dim m1 As Map";
_m1 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 146;BA.debugLine="m1 = pn.Tag";
_m1.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_pn.getTag()));
 //BA.debugLineNum = 148;BA.debugLine="If m1.Get(\"sType\") = \"cat\" Then";
if ((_m1.Get((Object)("sType"))).equals((Object)("cat"))) { 
 //BA.debugLineNum = 149;BA.debugLine="stack.Add(m1.Get(\"sID\"))";
_stack.Add(_m1.Get((Object)("sID")));
 //BA.debugLineNum = 150;BA.debugLine="LoadItems(m1.Get(\"sID\"))";
_loaditems(BA.ObjectToString(_m1.Get((Object)("sID"))));
 }else if((_m1.Get((Object)("sType"))).equals((Object)("bookmark"))) { 
 //BA.debugLineNum = 153;BA.debugLine="Dim temp As Map";
_temp = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 154;BA.debugLine="temp = db.ChapterContent(m1.Get(\"TopicID\"))";
_temp = _db._chaptercontent(BA.ObjectToString(_m1.Get((Object)("TopicID"))));
 //BA.debugLineNum = 155;BA.debugLine="If temp.IsInitialized Then";
if (_temp.IsInitialized()) { 
 //BA.debugLineNum = 156;BA.debugLine="temp.Put(\"sBookID\",BookID)";
_temp.Put((Object)("sBookID"),(Object)(_bookid));
 //BA.debugLineNum = 157;BA.debugLine="actContent.Data = temp";
_actcontent._data = _temp;
 //BA.debugLineNum = 158;BA.debugLine="actContent.offset = m1.Get(\"Offset\")";
_actcontent._offset = (int)(BA.ObjectToNumber(_m1.Get((Object)("Offset"))));
 //BA.debugLineNum = 159;BA.debugLine="actContent.BookID = BookID";
_actcontent._bookid = _bookid;
 //BA.debugLineNum = 160;BA.debugLine="actContent.ShowContent";
_actcontent._showcontent();
 };
 }else if((_m1.Get((Object)("sType"))).equals((Object)("note"))) { 
 //BA.debugLineNum = 164;BA.debugLine="Dim temp As Map";
_temp = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 165;BA.debugLine="temp = db.ChapterContent(m1.Get(\"TopicID\"))";
_temp = _db._chaptercontent(BA.ObjectToString(_m1.Get((Object)("TopicID"))));
 //BA.debugLineNum = 166;BA.debugLine="If temp.IsInitialized Then";
if (_temp.IsInitialized()) { 
 //BA.debugLineNum = 167;BA.debugLine="temp.Put(\"sBookID\",BookID)";
_temp.Put((Object)("sBookID"),(Object)(_bookid));
 //BA.debugLineNum = 168;BA.debugLine="actContent.Data = temp";
_actcontent._data = _temp;
 //BA.debugLineNum = 169;BA.debugLine="actContent.RefererNoteID = m1.Get(\"ID\")";
_actcontent._referernoteid = (int)(BA.ObjectToNumber(_m1.Get((Object)("ID"))));
 //BA.debugLineNum = 170;BA.debugLine="actContent.BookID = BookID";
_actcontent._bookid = _bookid;
 //BA.debugLineNum = 171;BA.debugLine="actContent.ShowContent";
_actcontent._showcontent();
 };
 }else if((_m1.Get((Object)("sType"))).equals((Object)("book")) || (_m1.Get((Object)("sType"))).equals((Object)("summary"))) { 
 //BA.debugLineNum = 176;BA.debugLine="Dim temp As Map";
_temp = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 177;BA.debugLine="temp = db.ChapterContent(m1.Get(\"sID\"))";
_temp = _db._chaptercontent(BA.ObjectToString(_m1.Get((Object)("sID"))));
 //BA.debugLineNum = 179;BA.debugLine="If temp.Get(\"sTypeTopic\") = \"pdf\" Then";
if ((_temp.Get((Object)("sTypeTopic"))).equals((Object)("pdf"))) { 
 //BA.debugLineNum = 180;BA.debugLine="fx.ShowExternalDocument(File.GetUri(File.DirApp";
_fx.ShowExternalDocument(anywheresoftware.b4a.keywords.Common.File.GetUri(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data"+"/Data"+"/book/"+_bookid,BA.ObjectToString(_temp.Get((Object)("sText")))));
 //BA.debugLineNum = 181;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 184;BA.debugLine="If temp.IsInitialized Then";
if (_temp.IsInitialized()) { 
 //BA.debugLineNum = 185;BA.debugLine="temp.Put(\"sBookID\",BookID)";
_temp.Put((Object)("sBookID"),(Object)(_bookid));
 //BA.debugLineNum = 186;BA.debugLine="actContent.Data = temp";
_actcontent._data = _temp;
 //BA.debugLineNum = 187;BA.debugLine="actContent.offset = -1";
_actcontent._offset = (int) (-1);
 //BA.debugLineNum = 188;BA.debugLine="If txtsearch.Tag <> \"\" Then actContent.SearchWo";
if ((_txtsearch.getTag()).equals((Object)("")) == false) { 
_actcontent._searchword = BA.ObjectToString(_txtsearch.getTag());};
 //BA.debugLineNum = 189;BA.debugLine="actContent.BookID = BookID";
_actcontent._bookid = _bookid;
 //BA.debugLineNum = 190;BA.debugLine="actContent.ShowContent";
_actcontent._showcontent();
 };
 }else if((_m1.Get((Object)("sType"))).equals((Object)("return"))) { 
 //BA.debugLineNum = 194;BA.debugLine="Dim val As String";
_val = "";
 //BA.debugLineNum = 195;BA.debugLine="stack.RemoveAt(stack.Size-1)";
_stack.RemoveAt((int) (_stack.getSize()-1));
 //BA.debugLineNum = 196;BA.debugLine="val = stack.Get(stack.Size-1)";
_val = BA.ObjectToString(_stack.Get((int) (_stack.getSize()-1)));
 //BA.debugLineNum = 197;BA.debugLine="If stack.Size = 1 Then stack.Clear";
if (_stack.getSize()==1) { 
_stack.Clear();};
 //BA.debugLineNum = 198;BA.debugLine="LoadItems(val)";
_loaditems(_val);
 //BA.debugLineNum = 199;BA.debugLine="If stack.Size = 0 Then stack.Add(\"0\")";
if (_stack.getSize()==0) { 
_stack.Add((Object)("0"));};
 };
 //BA.debugLineNum = 202;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 2;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 3;BA.debugLine="Private fx As JFX";
_fx = new anywheresoftware.b4j.objects.JFX();
 //BA.debugLineNum = 4;BA.debugLine="Private frm1 As Form";
_frm1 = new anywheresoftware.b4j.objects.Form();
 //BA.debugLineNum = 5;BA.debugLine="Public BookID As String";
_bookid = "";
 //BA.debugLineNum = 6;BA.debugLine="Private tab1 As TabPane";
_tab1 = new anywheresoftware.b4j.objects.TabPaneWrapper();
 //BA.debugLineNum = 7;BA.debugLine="Private db As Database";
_db = new b4j.example.database();
 //BA.debugLineNum = 8;BA.debugLine="Private lv1 As ListView";
_lv1 = new anywheresoftware.b4j.objects.ListViewWrapper();
 //BA.debugLineNum = 9;BA.debugLine="Private lvsummary As ListView";
_lvsummary = new anywheresoftware.b4j.objects.ListViewWrapper();
 //BA.debugLineNum = 10;BA.debugLine="Private stack As List";
_stack = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 11;BA.debugLine="Private pnlover As Pane";
_pnlover = new anywheresoftware.b4j.objects.PaneWrapper.ConcretePaneWrapper();
 //BA.debugLineNum = 12;BA.debugLine="Private imgicon As ImageView";
_imgicon = new anywheresoftware.b4j.objects.ImageViewWrapper();
 //BA.debugLineNum = 13;BA.debugLine="Private lblcatname As Label";
_lblcatname = new anywheresoftware.b4j.objects.LabelWrapper();
 //BA.debugLineNum = 14;BA.debugLine="Private lvnote As ListView";
_lvnote = new anywheresoftware.b4j.objects.ListViewWrapper();
 //BA.debugLineNum = 15;BA.debugLine="Private lvbookmark As ListView";
_lvbookmark = new anywheresoftware.b4j.objects.ListViewWrapper();
 //BA.debugLineNum = 16;BA.debugLine="Dim Hightlights,Notes,Bookmark As List";
_hightlights = new anywheresoftware.b4a.objects.collections.List();
_notes = new anywheresoftware.b4a.objects.collections.List();
_bookmark = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 17;BA.debugLine="Private txtsearch As TextField";
_txtsearch = new anywheresoftware.b4j.objects.TextInputControlWrapper.TextFieldWrapper();
 //BA.debugLineNum = 18;BA.debugLine="Private lvsearch As ListView";
_lvsearch = new anywheresoftware.b4j.objects.ListViewWrapper();
 //BA.debugLineNum = 19;BA.debugLine="Private Pane1 As Pane";
_pane1 = new anywheresoftware.b4j.objects.PaneWrapper.ConcretePaneWrapper();
 //BA.debugLineNum = 20;BA.debugLine="Private btndelete_remove As Button";
_btndelete_remove = new anywheresoftware.b4j.objects.ButtonWrapper();
 //BA.debugLineNum = 21;BA.debugLine="Private btnpdf As Button";
_btnpdf = new anywheresoftware.b4j.objects.ButtonWrapper();
 //BA.debugLineNum = 22;BA.debugLine="End Sub";
return "";
}
public static String  _savechangingcontent(String _sid,String _scontent) throws Exception{
 //BA.debugLineNum = 286;BA.debugLine="Sub SaveChangingContent(sID As String,sContent As";
 //BA.debugLineNum = 287;BA.debugLine="db.UpdateContent(sID,sContent)";
_db._updatecontent(_sid,_scontent);
 //BA.debugLineNum = 288;BA.debugLine="LoadTopicAttachment";
_loadtopicattachment();
 //BA.debugLineNum = 289;BA.debugLine="End Sub";
return "";
}
public static String  _showbookmenu() throws Exception{
 //BA.debugLineNum = 24;BA.debugLine="Sub ShowBookMenu";
 //BA.debugLineNum = 26;BA.debugLine="frm1.Initialize(\"frm1\",510,594)";
_frm1.Initialize(ba,"frm1",510,594);
 //BA.debugLineNum = 27;BA.debugLine="frm1.SetFormStyle(\"UNIFIED\")";
_frm1.SetFormStyle("UNIFIED");
 //BA.debugLineNum = 28;BA.debugLine="frm1.Title = \"منوی کتاب\"";
_frm1.setTitle("منوی کتاب");
 //BA.debugLineNum = 29;BA.debugLine="frm1.RootPane.LoadLayout(\"frmbook_menu\")";
_frm1.getRootPane().LoadLayout(ba,"frmbook_menu");
 //BA.debugLineNum = 30;BA.debugLine="frm1.Resizable = False";
_frm1.setResizable(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 31;BA.debugLine="Library.CenterFormOnScreen(frm1)";
_library._centerformonscreen(_frm1);
 //BA.debugLineNum = 33;BA.debugLine="tab1.LoadLayout(\"frmchapters\",\"سرفصل ها\")";
_tab1.LoadLayout(ba,"frmchapters","سرفصل ها");
 //BA.debugLineNum = 34;BA.debugLine="tab1.LoadLayout(\"frmbookmark\",\"نشان شده ها\")";
_tab1.LoadLayout(ba,"frmbookmark","نشان شده ها");
 //BA.debugLineNum = 35;BA.debugLine="tab1.LoadLayout(\"frmnote\",\"یادداشت ها\")";
_tab1.LoadLayout(ba,"frmnote","یادداشت ها");
 //BA.debugLineNum = 37;BA.debugLine="tab1.LoadLayout(\"frmsummary\",\"چکیده ها\")";
_tab1.LoadLayout(ba,"frmsummary","چکیده ها");
 //BA.debugLineNum = 38;BA.debugLine="frm1.Show";
_frm1.Show();
 //BA.debugLineNum = 40;BA.debugLine="db.Initialize(BookID)";
_db._initialize(ba,_bookid);
 //BA.debugLineNum = 41;BA.debugLine="stack.Initialize";
_stack.Initialize();
 //BA.debugLineNum = 43;BA.debugLine="LoadItems(0)";
_loaditems(BA.NumberToString(0));
 //BA.debugLineNum = 44;BA.debugLine="stack.Add(\"0\")";
_stack.Add((Object)("0"));
 //BA.debugLineNum = 46;BA.debugLine="If File.Exists(File.DirApp & \"/Data\" & \"/Data\" &";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirApp()+"/Data"+"/Data"+"/book/"+_bookid,"book.pdf")) { 
 //BA.debugLineNum = 47;BA.debugLine="btnpdf.Top = frm1.Height";
_btnpdf.setTop(_frm1.getHeight());
 //BA.debugLineNum = 48;BA.debugLine="btnpdf.SetLayoutAnimated(600,btnpdf.Left,frm1.He";
_btnpdf.SetLayoutAnimated((int) (600),_btnpdf.getLeft(),_frm1.getHeight()-_btnpdf.getHeight()+10,_btnpdf.getWidth(),_btnpdf.getHeight());
 };
 //BA.debugLineNum = 51;BA.debugLine="LoadTopicAttachment";
_loadtopicattachment();
 //BA.debugLineNum = 53;BA.debugLine="End Sub";
return "";
}
}
