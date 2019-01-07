package b4j.example;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.debug.*;

public class actsearchbook extends Object{
public static actsearchbook mostCurrent = new actsearchbook();

public static BA ba;
static {
		ba = new  anywheresoftware.b4j.objects.FxBA("b4j.example", "b4j.example.actsearchbook", null);
		ba.loadHtSubs(actsearchbook.class);
        if (ba.getClass().getName().endsWith("ShellBA")) {
			
			ba.raiseEvent2(null, true, "SHELL", false);
			ba.raiseEvent2(null, true, "CREATE", true, "b4j.example.actsearchbook", ba);
		}
	}
    public static Class<?> getObject() {
		return actsearchbook.class;
	}

 public static anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4j.objects.JFX _fx = null;
public static anywheresoftware.b4j.objects.LabelWrapper _lblbookname = null;
public static anywheresoftware.b4j.objects.CheckboxWrapper _ck1 = null;
public static b4j.example.categories _books = null;
public static anywheresoftware.b4a.objects.collections.List _lstbook = null;
public static anywheresoftware.b4a.objects.collections.List _checkboxs = null;
public static anywheresoftware.b4j.objects.ListViewWrapper _lv1 = null;
public static anywheresoftware.b4j.objects.Form _frm1 = null;
public static anywheresoftware.b4j.objects.ButtonWrapper _btnselect = null;
public static anywheresoftware.b4j.objects.TextInputControlWrapper.TextFieldWrapper _txtsearch = null;
public static anywheresoftware.b4j.objects.ListViewWrapper _lv2 = null;
public static anywheresoftware.b4j.objects.PaneWrapper.ConcretePaneWrapper _pnlover = null;
public static anywheresoftware.b4j.objects.LabelWrapper _lblcatname = null;
public static anywheresoftware.b4j.objects.ImageViewWrapper _imgicon = null;
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
public static b4j.example.actbookinformation _actbookinformation = null;
public static b4j.example.actsetting _actsetting = null;
public static String  _btnsearch_action() throws Exception{
anywheresoftware.b4a.objects.collections.List _listbook = null;
int _i = 0;
anywheresoftware.b4j.objects.CheckboxWrapper _ckb = null;
b4j.example.categories _cat = null;
anywheresoftware.b4a.objects.collections.List _result = null;
int _j = 0;
anywheresoftware.b4a.objects.collections.List _t = null;
int _k = 0;
anywheresoftware.b4a.objects.collections.Map _t1 = null;
anywheresoftware.b4j.objects.PaneWrapper.ConcretePaneWrapper _p1 = null;
 //BA.debugLineNum = 50;BA.debugLine="Sub btnsearch_Action";
 //BA.debugLineNum = 52;BA.debugLine="If txtsearch.Text.Length = 0 Then";
if (_txtsearch.getText().length()==0) { 
 //BA.debugLineNum = 53;BA.debugLine="fx.Msgbox(frm1,\"لطفا عبارتی را وارد کنید\",\"توجه\"";
_fx.Msgbox(_frm1,"لطفا عبارتی را وارد کنید","توجه");
 //BA.debugLineNum = 54;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 57;BA.debugLine="Dim Listbook As List";
_listbook = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 58;BA.debugLine="Listbook.Initialize";
_listbook.Initialize();
 //BA.debugLineNum = 60;BA.debugLine="For i = 0 To checkboxs.Size - 1";
{
final int step7 = 1;
final int limit7 = (int) (_checkboxs.getSize()-1);
for (_i = (int) (0) ; (step7 > 0 && _i <= limit7) || (step7 < 0 && _i >= limit7); _i = ((int)(0 + _i + step7)) ) {
 //BA.debugLineNum = 61;BA.debugLine="Dim ckb As CheckBox";
_ckb = new anywheresoftware.b4j.objects.CheckboxWrapper();
 //BA.debugLineNum = 62;BA.debugLine="ckb = checkboxs.Get(i)";
_ckb.setObject((javafx.scene.control.CheckBox)(_checkboxs.Get(_i)));
 //BA.debugLineNum = 63;BA.debugLine="If ckb.Checked = True Then Listbook.Add(ckb.Tag)";
if (_ckb.getChecked()==anywheresoftware.b4a.keywords.Common.True) { 
_listbook.Add(_ckb.getTag());};
 }
};
 //BA.debugLineNum = 66;BA.debugLine="If Listbook.Size = 0 Then";
if (_listbook.getSize()==0) { 
 //BA.debugLineNum = 67;BA.debugLine="fx.Msgbox(frm1,\"شما هیچ کتابی را انتخاب نکرده ای";
_fx.Msgbox(_frm1,"شما هیچ کتابی را انتخاب نکرده اید","توجه");
 //BA.debugLineNum = 68;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 71;BA.debugLine="Dim cat As Categories";
_cat = new b4j.example.categories();
 //BA.debugLineNum = 72;BA.debugLine="Dim Result As List";
_result = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 73;BA.debugLine="cat.Initialize";
_cat._initialize(ba);
 //BA.debugLineNum = 74;BA.debugLine="Result.Initialize";
_result.Initialize();
 //BA.debugLineNum = 76;BA.debugLine="Result = cat.SearchInBooks(txtsearch.Text,Listboo";
_result = _cat._searchinbooks(_txtsearch.getText(),_listbook);
 //BA.debugLineNum = 78;BA.debugLine="lv2.Visible = True";
_lv2.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 79;BA.debugLine="lv2.Items.Clear";
_lv2.getItems().Clear();
 //BA.debugLineNum = 81;BA.debugLine="For j = 0 To Result.Size - 1";
{
final int step23 = 1;
final int limit23 = (int) (_result.getSize()-1);
for (_j = (int) (0) ; (step23 > 0 && _j <= limit23) || (step23 < 0 && _j >= limit23); _j = ((int)(0 + _j + step23)) ) {
 //BA.debugLineNum = 82;BA.debugLine="Dim t As List";
_t = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 83;BA.debugLine="t = Result.Get(j)";
_t.setObject((java.util.List)(_result.Get(_j)));
 //BA.debugLineNum = 85;BA.debugLine="For k = 0 To t.Size - 1";
{
final int step26 = 1;
final int limit26 = (int) (_t.getSize()-1);
for (_k = (int) (0) ; (step26 > 0 && _k <= limit26) || (step26 < 0 && _k >= limit26); _k = ((int)(0 + _k + step26)) ) {
 //BA.debugLineNum = 87;BA.debugLine="Dim t1 As Map";
_t1 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 88;BA.debugLine="t1 = t.Get(i)";
_t1.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_t.Get(_i)));
 //BA.debugLineNum = 90;BA.debugLine="Dim p1 As Pane";
_p1 = new anywheresoftware.b4j.objects.PaneWrapper.ConcretePaneWrapper();
 //BA.debugLineNum = 91;BA.debugLine="p1.Initialize(\"\")";
_p1.Initialize(ba,"");
 //BA.debugLineNum = 92;BA.debugLine="p1.LoadLayout(\"frmtemplate_category\")";
_p1.LoadLayout(ba,"frmtemplate_category");
 //BA.debugLineNum = 93;BA.debugLine="p1.SetSize(400,60)";
_p1.SetSize(400,60);
 //BA.debugLineNum = 94;BA.debugLine="lv2.Items.Add(p1)";
_lv2.getItems().Add((Object)(_p1.getObject()));
 //BA.debugLineNum = 95;BA.debugLine="lblcatname.Text = t1.Get(\"sTitle\")";
_lblcatname.setText(BA.ObjectToString(_t1.Get((Object)("sTitle"))));
 //BA.debugLineNum = 96;BA.debugLine="pnlover.Tag = t1";
_pnlover.setTag((Object)(_t1.getObject()));
 //BA.debugLineNum = 97;BA.debugLine="imgicon.SetImage(fx.LoadImage(File.DirAssets,\"b";
_imgicon.SetImage((javafx.scene.image.Image)(_fx.LoadImage(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"book.png").getObject()));
 }
};
 }
};
 //BA.debugLineNum = 102;BA.debugLine="End Sub";
return "";
}
public static String  _btnselect_action() throws Exception{
int _i = 0;
anywheresoftware.b4j.objects.CheckboxWrapper _ck = null;
 //BA.debugLineNum = 104;BA.debugLine="Sub btnselect_Action";
 //BA.debugLineNum = 106;BA.debugLine="For i = 0 To checkboxs.Size - 1";
{
final int step1 = 1;
final int limit1 = (int) (_checkboxs.getSize()-1);
for (_i = (int) (0) ; (step1 > 0 && _i <= limit1) || (step1 < 0 && _i >= limit1); _i = ((int)(0 + _i + step1)) ) {
 //BA.debugLineNum = 107;BA.debugLine="Dim ck As CheckBox";
_ck = new anywheresoftware.b4j.objects.CheckboxWrapper();
 //BA.debugLineNum = 108;BA.debugLine="ck = checkboxs.Get(i)";
_ck.setObject((javafx.scene.control.CheckBox)(_checkboxs.Get(_i)));
 //BA.debugLineNum = 110;BA.debugLine="If ck.Checked = True Then";
if (_ck.getChecked()==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 111;BA.debugLine="ck.Checked = False";
_ck.setChecked(anywheresoftware.b4a.keywords.Common.False);
 }else {
 //BA.debugLineNum = 113;BA.debugLine="ck.Checked = True";
_ck.setChecked(anywheresoftware.b4a.keywords.Common.True);
 };
 }
};
 //BA.debugLineNum = 117;BA.debugLine="End Sub";
return "";
}
public static String  _frm1_closerequest(anywheresoftware.b4j.objects.NodeWrapper.ConcreteEventWrapper _eventdata) throws Exception{
 //BA.debugLineNum = 142;BA.debugLine="Sub frm1_CloseRequest (EventData As Event)";
 //BA.debugLineNum = 143;BA.debugLine="If lv2.Visible = True Then";
if (_lv2.getVisible()==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 144;BA.debugLine="lv2.Visible = False";
_lv2.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 145;BA.debugLine="EventData.Consume";
_eventdata.Consume();
 };
 //BA.debugLineNum = 147;BA.debugLine="End Sub";
return "";
}
public static String  _pnlover_mouseclicked(anywheresoftware.b4j.objects.NodeWrapper.MouseEventWrapper _eventdata) throws Exception{
anywheresoftware.b4j.objects.PaneWrapper.ConcretePaneWrapper _ps = null;
anywheresoftware.b4a.objects.collections.Map _ms = null;
b4j.example.database _db = null;
anywheresoftware.b4a.objects.collections.Map _temp = null;
 //BA.debugLineNum = 119;BA.debugLine="Sub pnlover_MouseClicked (EventData As MouseEvent)";
 //BA.debugLineNum = 121;BA.debugLine="Dim ps As Pane";
_ps = new anywheresoftware.b4j.objects.PaneWrapper.ConcretePaneWrapper();
 //BA.debugLineNum = 122;BA.debugLine="ps = Sender";
_ps.setObject((javafx.scene.layout.Pane)(anywheresoftware.b4a.keywords.Common.Sender(ba)));
 //BA.debugLineNum = 124;BA.debugLine="Dim ms As Map";
_ms = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 125;BA.debugLine="ms = ps.Tag";
_ms.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_ps.getTag()));
 //BA.debugLineNum = 127;BA.debugLine="Dim db As Database";
_db = new b4j.example.database();
 //BA.debugLineNum = 128;BA.debugLine="db.Initialize(ms.Get(\"BookID\"))";
_db._initialize(ba,BA.ObjectToString(_ms.Get((Object)("BookID"))));
 //BA.debugLineNum = 130;BA.debugLine="Dim temp As Map";
_temp = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 131;BA.debugLine="temp = db.ChapterContent(ms.Get(\"sID\"))";
_temp = _db._chaptercontent(BA.ObjectToString(_ms.Get((Object)("sID"))));
 //BA.debugLineNum = 133;BA.debugLine="temp.Put(\"sBookID\",ms.Get(\"BookID\"))";
_temp.Put((Object)("sBookID"),_ms.Get((Object)("BookID")));
 //BA.debugLineNum = 134;BA.debugLine="actContent.Data = temp";
_actcontent._data = _temp;
 //BA.debugLineNum = 135;BA.debugLine="actContent.offset = -1";
_actcontent._offset = (int) (-1);
 //BA.debugLineNum = 136;BA.debugLine="actContent.SearchWord = txtsearch.Text";
_actcontent._searchword = _txtsearch.getText();
 //BA.debugLineNum = 137;BA.debugLine="actContent.BookID = ms.Get(\"BookID\")";
_actcontent._bookid = BA.ObjectToString(_ms.Get((Object)("BookID")));
 //BA.debugLineNum = 138;BA.debugLine="actContent.ShowContent";
_actcontent._showcontent();
 //BA.debugLineNum = 140;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 2;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 3;BA.debugLine="Private fx As JFX";
_fx = new anywheresoftware.b4j.objects.JFX();
 //BA.debugLineNum = 4;BA.debugLine="Private lblbookname As Label";
_lblbookname = new anywheresoftware.b4j.objects.LabelWrapper();
 //BA.debugLineNum = 5;BA.debugLine="Private ck1 As CheckBox";
_ck1 = new anywheresoftware.b4j.objects.CheckboxWrapper();
 //BA.debugLineNum = 6;BA.debugLine="Private books As Categories";
_books = new b4j.example.categories();
 //BA.debugLineNum = 7;BA.debugLine="Private lstBook,checkboxs As List";
_lstbook = new anywheresoftware.b4a.objects.collections.List();
_checkboxs = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 8;BA.debugLine="Private lv1 As ListView";
_lv1 = new anywheresoftware.b4j.objects.ListViewWrapper();
 //BA.debugLineNum = 9;BA.debugLine="Private frm1 As Form";
_frm1 = new anywheresoftware.b4j.objects.Form();
 //BA.debugLineNum = 10;BA.debugLine="Private btnselect As Button";
_btnselect = new anywheresoftware.b4j.objects.ButtonWrapper();
 //BA.debugLineNum = 11;BA.debugLine="Private txtsearch As TextField";
_txtsearch = new anywheresoftware.b4j.objects.TextInputControlWrapper.TextFieldWrapper();
 //BA.debugLineNum = 12;BA.debugLine="Private lv2 As ListView";
_lv2 = new anywheresoftware.b4j.objects.ListViewWrapper();
 //BA.debugLineNum = 13;BA.debugLine="Private pnlover As Pane";
_pnlover = new anywheresoftware.b4j.objects.PaneWrapper.ConcretePaneWrapper();
 //BA.debugLineNum = 14;BA.debugLine="Private lblcatname As Label";
_lblcatname = new anywheresoftware.b4j.objects.LabelWrapper();
 //BA.debugLineNum = 15;BA.debugLine="Private imgicon As ImageView";
_imgicon = new anywheresoftware.b4j.objects.ImageViewWrapper();
 //BA.debugLineNum = 16;BA.debugLine="End Sub";
return "";
}
public static String  _showsearchbook() throws Exception{
int _i = 0;
anywheresoftware.b4a.objects.collections.Map _temp = null;
anywheresoftware.b4j.objects.PaneWrapper.ConcretePaneWrapper _p1 = null;
 //BA.debugLineNum = 18;BA.debugLine="Sub ShowSearchBook";
 //BA.debugLineNum = 20;BA.debugLine="frm1.Initialize(\"frm1\",510,594)";
_frm1.Initialize(ba,"frm1",510,594);
 //BA.debugLineNum = 21;BA.debugLine="frm1.SetFormStyle(\"UNIFIED\")";
_frm1.SetFormStyle("UNIFIED");
 //BA.debugLineNum = 22;BA.debugLine="frm1.Title = \"جستجو در کتاب ها\"";
_frm1.setTitle("جستجو در کتاب ها");
 //BA.debugLineNum = 23;BA.debugLine="frm1.RootPane.LoadLayout(\"frmsearchbook\")";
_frm1.getRootPane().LoadLayout(ba,"frmsearchbook");
 //BA.debugLineNum = 24;BA.debugLine="frm1.Resizable = False";
_frm1.setResizable(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 25;BA.debugLine="frm1.Show";
_frm1.Show();
 //BA.debugLineNum = 26;BA.debugLine="Library.CenterFormOnScreen(frm1)";
_library._centerformonscreen(_frm1);
 //BA.debugLineNum = 28;BA.debugLine="books.Initialize";
_books._initialize(ba);
 //BA.debugLineNum = 29;BA.debugLine="checkboxs.Initialize";
_checkboxs.Initialize();
 //BA.debugLineNum = 30;BA.debugLine="lstBook = books.GetDownloadedBook(\"\",\"\")";
_lstbook = _books._getdownloadedbook("","");
 //BA.debugLineNum = 32;BA.debugLine="For i = 0 To lstBook.Size - 1";
{
final int step11 = 1;
final int limit11 = (int) (_lstbook.getSize()-1);
for (_i = (int) (0) ; (step11 > 0 && _i <= limit11) || (step11 < 0 && _i >= limit11); _i = ((int)(0 + _i + step11)) ) {
 //BA.debugLineNum = 34;BA.debugLine="Dim temp As Map";
_temp = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 35;BA.debugLine="temp = lstBook.Get(i)";
_temp.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_lstbook.Get(_i)));
 //BA.debugLineNum = 37;BA.debugLine="Dim p1 As Pane";
_p1 = new anywheresoftware.b4j.objects.PaneWrapper.ConcretePaneWrapper();
 //BA.debugLineNum = 38;BA.debugLine="p1.Initialize(\"\")";
_p1.Initialize(ba,"");
 //BA.debugLineNum = 39;BA.debugLine="p1.LoadLayout(\"frmtemplate_search_booklist\")";
_p1.LoadLayout(ba,"frmtemplate_search_booklist");
 //BA.debugLineNum = 40;BA.debugLine="p1.SetSize(480,65)";
_p1.SetSize(480,65);
 //BA.debugLineNum = 41;BA.debugLine="lv1.Items.Add(p1)";
_lv1.getItems().Add((Object)(_p1.getObject()));
 //BA.debugLineNum = 42;BA.debugLine="checkboxs.Add(ck1)";
_checkboxs.Add((Object)(_ck1.getObject()));
 //BA.debugLineNum = 43;BA.debugLine="lblbookname.Text = 	temp.Get(\"sTitle\")";
_lblbookname.setText(BA.ObjectToString(_temp.Get((Object)("sTitle"))));
 //BA.debugLineNum = 44;BA.debugLine="ck1.Tag = temp.Get(\"sID\")";
_ck1.setTag(_temp.Get((Object)("sID")));
 }
};
 //BA.debugLineNum = 48;BA.debugLine="End Sub";
return "";
}
}
