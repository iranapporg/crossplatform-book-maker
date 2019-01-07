package b4j.example;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.debug.*;

public class actcontent extends Object{
public static actcontent mostCurrent = new actcontent();

public static BA ba;
static {
		ba = new  anywheresoftware.b4j.objects.FxBA("b4j.example", "b4j.example.actcontent", null);
		ba.loadHtSubs(actcontent.class);
        if (ba.getClass().getName().endsWith("ShellBA")) {
			
			ba.raiseEvent2(null, true, "SHELL", false);
			ba.raiseEvent2(null, true, "CREATE", true, "b4j.example.actcontent", ba);
		}
	}
    public static Class<?> getObject() {
		return actcontent.class;
	}

 public static anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4j.objects.JFX _fx = null;
public static anywheresoftware.b4a.objects.collections.Map _data = null;
public static String _bookid = "";
public static anywheresoftware.b4j.objects.Form _frmmdi = null;
public static anywheresoftware.b4j.objects.WebViewWrapper _wb1 = null;
public static anywheresoftware.b4j.object.JavaObject _we = null;
public static anywheresoftware.b4j.object.JavaObject _temp = null;
public static anywheresoftware.b4j.object.JavaObject _doc = null;
public static jScriptEngine.jScriptEngine _js = null;
public static anywheresoftware.b4a.objects.collections.List _lis = null;
public static boolean _ischanged = false;
public static anywheresoftware.b4j.objects.PaneWrapper.ConcretePaneWrapper _pane1 = null;
public static anywheresoftware.b4j.objects.ImageViewWrapper _imghighlight = null;
public static anywheresoftware.b4j.objects.ImageViewWrapper _imgremovehighlight = null;
public static anywheresoftware.b4j.objects.ImageViewWrapper _imgnote = null;
public static anywheresoftware.b4j.objects.ImageViewWrapper _imgcopy = null;
public static b4j.example.loading _loading = null;
public static Object _scale = null;
public static anywheresoftware.b4j.objects.LabelWrapper _lblnote = null;
public static anywheresoftware.b4j.objects.PaneWrapper.ConcretePaneWrapper _pnnote = null;
public static float _fontsize = 0f;
public static String _fontfamily = "";
public static anywheresoftware.b4j.objects.SliderWrapper _slider1 = null;
public static anywheresoftware.b4j.objects.ImageViewWrapper _imgbookmark = null;
public static int _offset = 0;
public static int _referernoteid = 0;
public static anywheresoftware.b4j.objects.ButtonWrapper _btnremove = null;
public static int _currentnotechoose = 0;
public static String _searchword = "";
public static b4j.example.httputils2service _httputils2service = null;
public static b4j.example.main _main = null;
public static b4j.example.actbookmenu _actbookmenu = null;
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
public static String  _addnote() throws Exception{
b4j.example.dialogs8 _inp = null;
String _res = "";
String _note_id = "";
 //BA.debugLineNum = 167;BA.debugLine="Sub AddNote";
 //BA.debugLineNum = 169;BA.debugLine="Dim inp As Dialogs8";
_inp = new b4j.example.dialogs8();
 //BA.debugLineNum = 170;BA.debugLine="Dim res As String";
_res = "";
 //BA.debugLineNum = 172;BA.debugLine="inp.Initialize";
_inp._initialize(ba);
 //BA.debugLineNum = 173;BA.debugLine="res = inp.TextInputDialog(\"یادداشت گذاری\",\"\",\"لطف";
_res = _inp._textinputdialog("یادداشت گذاری","","لطفا یادداشت را وارد کنید","");
 //BA.debugLineNum = 175;BA.debugLine="If res.Length > 0 Then";
if (_res.length()>0) { 
 //BA.debugLineNum = 176;BA.debugLine="Dim note_id As String";
_note_id = "";
 //BA.debugLineNum = 177;BA.debugLine="note_id = Library.GetNoteID(BookID)";
_note_id = BA.NumberToString(_library._getnoteid(_bookid));
 //BA.debugLineNum = 178;BA.debugLine="Library.WriteNote(BookID,data.Get(\"sID\"),note_id";
_library._writenote(_bookid,BA.ObjectToString(_data.Get((Object)("sID"))),_note_id,_res);
 //BA.debugLineNum = 179;BA.debugLine="ExecureJS(\"AddLink(\"\"\" & note_id & \"\"\");\")";
_execurejs("AddLink(\""+_note_id+"\");");
 };
 //BA.debugLineNum = 182;BA.debugLine="End Sub";
return "";
}
public static String  _btnok_action() throws Exception{
 //BA.debugLineNum = 230;BA.debugLine="Sub btnok_Action";
 //BA.debugLineNum = 231;BA.debugLine="pnnote.SetLayoutAnimated(600,pnnote.Left,-pnnote.";
_pnnote.SetLayoutAnimated((int) (600),_pnnote.getLeft(),-_pnnote.getHeight(),_pnnote.getWidth(),_pnnote.getHeight());
 //BA.debugLineNum = 232;BA.debugLine="End Sub";
return "";
}
public static String  _btnremove_action() throws Exception{
 //BA.debugLineNum = 261;BA.debugLine="Sub btnremove_Action";
 //BA.debugLineNum = 263;BA.debugLine="pnnote.SetLayoutAnimated(600,pnnote.Left,-pnnote.";
_pnnote.SetLayoutAnimated((int) (600),_pnnote.getLeft(),-_pnnote.getHeight(),_pnnote.getWidth(),_pnnote.getHeight());
 //BA.debugLineNum = 265;BA.debugLine="If currentNoteChoose = 0 Then Return";
if (_currentnotechoose==0) { 
if (true) return "";};
 //BA.debugLineNum = 267;BA.debugLine="ExecureJS(\"removeLink(\" & currentNoteChoose & \");";
_execurejs("removeLink("+BA.NumberToString(_currentnotechoose)+");");
 //BA.debugLineNum = 268;BA.debugLine="Library.DeleteNote(BookID,currentNoteChoose)";
_library._deletenote(_bookid,BA.NumberToString(_currentnotechoose));
 //BA.debugLineNum = 269;BA.debugLine="currentNoteChoose = 0";
_currentnotechoose = (int) (0);
 //BA.debugLineNum = 270;BA.debugLine="isChanged = True";
_ischanged = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 272;BA.debugLine="End Sub";
return "";
}
public static String  _execurejs(String _str) throws Exception{
anywheresoftware.b4j.object.JavaObject _jowv = null;
 //BA.debugLineNum = 159;BA.debugLine="Sub ExecureJS(str As String)";
 //BA.debugLineNum = 160;BA.debugLine="Try";
try { //BA.debugLineNum = 161;BA.debugLine="Dim joWV As JavaObject = wb1";
_jowv = new anywheresoftware.b4j.object.JavaObject();
_jowv.setObject((java.lang.Object)(_wb1.getObject()));
 //BA.debugLineNum = 162;BA.debugLine="joWV.RunMethodJO(\"getEngine\", Null).RunMethod(\"e";
_jowv.RunMethodJO("getEngine",(Object[])(anywheresoftware.b4a.keywords.Common.Null)).RunMethod("executeScript",(Object[])(new String[]{_str}));
 } 
       catch (Exception e5) {
			ba.setLastException(e5); };
 //BA.debugLineNum = 165;BA.debugLine="End Sub";
return "";
}
public static String  _frm1_closerequest(anywheresoftware.b4j.objects.NodeWrapper.ConcreteEventWrapper _eventdata) throws Exception{
 //BA.debugLineNum = 139;BA.debugLine="Sub frm1_CloseRequest (EventData As Event)";
 //BA.debugLineNum = 141;BA.debugLine="offset = -1";
_offset = (int) (-1);
 //BA.debugLineNum = 142;BA.debugLine="RefererNoteID = -1";
_referernoteid = (int) (-1);
 //BA.debugLineNum = 143;BA.debugLine="SearchWord = \"\"";
_searchword = "";
 //BA.debugLineNum = 145;BA.debugLine="If isChanged = True Then";
if (_ischanged==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 146;BA.debugLine="isChanged = False";
_ischanged = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 147;BA.debugLine="CallSubDelayed3(actBookMenu,\"SaveChangingContent";
anywheresoftware.b4a.keywords.Common.CallSubDelayed3(ba,(Object)(_actbookmenu.getObject()),"SaveChangingContent",_data.Get((Object)("sID")),(Object)(_getcontentwebview()));
 };
 //BA.debugLineNum = 150;BA.debugLine="End Sub";
return "";
}
public static String  _getcontentwebview() throws Exception{
anywheresoftware.b4j.object.JavaObject _jowv = null;
String _res = "";
 //BA.debugLineNum = 152;BA.debugLine="Sub GetContentWebView As String";
 //BA.debugLineNum = 153;BA.debugLine="Dim joWV As JavaObject = wb1";
_jowv = new anywheresoftware.b4j.object.JavaObject();
_jowv.setObject((java.lang.Object)(_wb1.getObject()));
 //BA.debugLineNum = 154;BA.debugLine="Dim res As String";
_res = "";
 //BA.debugLineNum = 155;BA.debugLine="res = joWV.RunMethodJO(\"getEngine\", Null).RunMeth";
_res = BA.ObjectToString(_jowv.RunMethodJO("getEngine",(Object[])(anywheresoftware.b4a.keywords.Common.Null)).RunMethod("executeScript",new Object[]{(Object)("document.getElementsByTagName('p')[0].innerHTML")}));
 //BA.debugLineNum = 156;BA.debugLine="Return res";
if (true) return _res;
 //BA.debugLineNum = 157;BA.debugLine="End Sub";
return "";
}
public static String  _getcurrentscrollposition() throws Exception{
anywheresoftware.b4j.object.JavaObject _jowv = null;
String _res = "";
 //BA.debugLineNum = 246;BA.debugLine="Sub GetCurrentScrollPosition As String";
 //BA.debugLineNum = 247;BA.debugLine="Dim joWV As JavaObject = wb1";
_jowv = new anywheresoftware.b4j.object.JavaObject();
_jowv.setObject((java.lang.Object)(_wb1.getObject()));
 //BA.debugLineNum = 248;BA.debugLine="Dim res As String";
_res = "";
 //BA.debugLineNum = 249;BA.debugLine="res = joWV.RunMethodJO(\"getEngine\", Null).RunMeth";
_res = BA.ObjectToString(_jowv.RunMethodJO("getEngine",(Object[])(anywheresoftware.b4a.keywords.Common.Null)).RunMethod("executeScript",new Object[]{(Object)("document.body.scrollTop")}));
 //BA.debugLineNum = 250;BA.debugLine="Return res";
if (true) return _res;
 //BA.debugLineNum = 251;BA.debugLine="End Sub";
return "";
}
public static String  _getsetting() throws Exception{
 //BA.debugLineNum = 234;BA.debugLine="Sub GetSetting";
 //BA.debugLineNum = 235;BA.debugLine="fontsize = Library.manager.GetFloat(\"fontsize\",14";
_fontsize = _library._manager.GetFloat("fontsize",(float) (14));
 //BA.debugLineNum = 236;BA.debugLine="fontfamily = Library.manager.GetString(\"fontfamil";
_fontfamily = _library._manager.GetString("fontfamily","byekan.ttf");
 //BA.debugLineNum = 237;BA.debugLine="End Sub";
return "";
}
public static String  _imgbookmark_mouseclicked(anywheresoftware.b4j.objects.NodeWrapper.MouseEventWrapper _eventdata) throws Exception{
 //BA.debugLineNum = 253;BA.debugLine="Sub imgbookmark_MouseClicked (EventData As MouseEv";
 //BA.debugLineNum = 255;BA.debugLine="isChanged = True";
_ischanged = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 256;BA.debugLine="Library.WriteBookmark(BookID,data.Get(\"sID\"),GetC";
_library._writebookmark(_bookid,BA.ObjectToString(_data.Get((Object)("sID"))),_getcurrentscrollposition());
 //BA.debugLineNum = 257;BA.debugLine="fx.Msgbox(frmmdi,\"این بخش از صفحه نشان شد\",\"توجه\"";
_fx.Msgbox(_frmmdi,"این بخش از صفحه نشان شد","توجه");
 //BA.debugLineNum = 259;BA.debugLine="End Sub";
return "";
}
public static String  _imgcopy_mouseclicked(anywheresoftware.b4j.objects.NodeWrapper.MouseEventWrapper _eventdata) throws Exception{
 //BA.debugLineNum = 194;BA.debugLine="Sub imgcopy_MouseClicked (EventData As MouseEvent)";
 //BA.debugLineNum = 195;BA.debugLine="js.enginePut(\"doc\",we.RunMethod(\"getDocument\",Nul";
_js.enginePut("doc",_we.RunMethod("getDocument",(Object[])(anywheresoftware.b4a.keywords.Common.Null)));
 //BA.debugLineNum = 196;BA.debugLine="js.evalString(\"var data = doc.getSelection()\") '";
_js.evalString("var data = doc.getSelection()");
 //BA.debugLineNum = 197;BA.debugLine="fx.Clipboard.SetString(js.engineGet(\"data\"))";
_fx.Clipboard.SetString(BA.ObjectToString(_js.engineGet("data")));
 //BA.debugLineNum = 198;BA.debugLine="fx.Msgbox(frmmdi,\"متن انتخاب شده به حافظه کپی شد\"";
_fx.Msgbox(_frmmdi,"متن انتخاب شده به حافظه کپی شد","توجه");
 //BA.debugLineNum = 199;BA.debugLine="End Sub";
return "";
}
public static String  _imgdownfont_mouseclicked(anywheresoftware.b4j.objects.NodeWrapper.MouseEventWrapper _eventdata) throws Exception{
anywheresoftware.b4j.object.JavaObject _wv = null;
 //BA.debugLineNum = 223;BA.debugLine="Sub imgdownfont_MouseClicked (EventData As MouseEv";
 //BA.debugLineNum = 224;BA.debugLine="isChanged = True";
_ischanged = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 225;BA.debugLine="Dim wv As JavaObject = wb1   ' webview1 = your we";
_wv = new anywheresoftware.b4j.object.JavaObject();
_wv.setObject((java.lang.Object)(_wb1.getObject()));
 //BA.debugLineNum = 226;BA.debugLine="scale = scale - 0.5";
_scale = (Object)((double)(BA.ObjectToNumber(_scale))-0.5);
 //BA.debugLineNum = 227;BA.debugLine="wv.RunMethod(\"setFontScale\",Array(scale))";
_wv.RunMethod("setFontScale",new Object[]{_scale});
 //BA.debugLineNum = 228;BA.debugLine="End Sub";
return "";
}
public static String  _imghighlight_mouseclicked(anywheresoftware.b4j.objects.NodeWrapper.MouseEventWrapper _eventdata) throws Exception{
 //BA.debugLineNum = 184;BA.debugLine="Sub imghighlight_MouseClicked (EventData As MouseE";
 //BA.debugLineNum = 185;BA.debugLine="isChanged = True";
_ischanged = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 186;BA.debugLine="ExecureJS(\"highlight(\"\"yellow\"\");\")";
_execurejs("highlight(\"yellow\");");
 //BA.debugLineNum = 187;BA.debugLine="End Sub";
return "";
}
public static String  _imgnote_mouseclicked(anywheresoftware.b4j.objects.NodeWrapper.MouseEventWrapper _eventdata) throws Exception{
 //BA.debugLineNum = 189;BA.debugLine="Sub imgnote_MouseClicked (EventData As MouseEvent)";
 //BA.debugLineNum = 190;BA.debugLine="isChanged = True";
_ischanged = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 191;BA.debugLine="AddNote";
_addnote();
 //BA.debugLineNum = 192;BA.debugLine="End Sub";
return "";
}
public static String  _imgremovehighlight_mouseclicked(anywheresoftware.b4j.objects.NodeWrapper.MouseEventWrapper _eventdata) throws Exception{
 //BA.debugLineNum = 201;BA.debugLine="Sub imgremovehighlight_MouseClicked (EventData As";
 //BA.debugLineNum = 202;BA.debugLine="isChanged = True";
_ischanged = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 203;BA.debugLine="ExecureJS(\"highlight(\"\"white\"\");\")";
_execurejs("highlight(\"white\");");
 //BA.debugLineNum = 204;BA.debugLine="End Sub";
return "";
}
public static String  _imgupfont_mouseclicked(anywheresoftware.b4j.objects.NodeWrapper.MouseEventWrapper _eventdata) throws Exception{
anywheresoftware.b4j.object.JavaObject _wv = null;
 //BA.debugLineNum = 216;BA.debugLine="Sub imgupfont_MouseClicked (EventData As MouseEven";
 //BA.debugLineNum = 217;BA.debugLine="isChanged = True";
_ischanged = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 218;BA.debugLine="Dim wv As JavaObject = wb1   ' webview1 = your we";
_wv = new anywheresoftware.b4j.object.JavaObject();
_wv.setObject((java.lang.Object)(_wb1.getObject()));
 //BA.debugLineNum = 219;BA.debugLine="scale = scale + 0.5";
_scale = (Object)((double)(BA.ObjectToNumber(_scale))+0.5);
 //BA.debugLineNum = 220;BA.debugLine="wv.RunMethod(\"setFontScale\",Array(scale))";
_wv.RunMethod("setFontScale",new Object[]{_scale});
 //BA.debugLineNum = 221;BA.debugLine="End Sub";
return "";
}
public static String  _pane1_mousemoved(anywheresoftware.b4j.objects.NodeWrapper.MouseEventWrapper _eventdata) throws Exception{
 //BA.debugLineNum = 206;BA.debugLine="Sub Pane1_MouseMoved (EventData As MouseEvent)";
 //BA.debugLineNum = 207;BA.debugLine="Pane1.SetAlphaAnimated(400,1)";
_pane1.SetAlphaAnimated((int) (400),1);
 //BA.debugLineNum = 208;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 2;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 3;BA.debugLine="Private fx As JFX";
_fx = new anywheresoftware.b4j.objects.JFX();
 //BA.debugLineNum = 4;BA.debugLine="Public data As Map";
_data = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 5;BA.debugLine="Public BookID As String";
_bookid = "";
 //BA.debugLineNum = 6;BA.debugLine="Private frmmdi As Form";
_frmmdi = new anywheresoftware.b4j.objects.Form();
 //BA.debugLineNum = 7;BA.debugLine="Private wb1 As WebView";
_wb1 = new anywheresoftware.b4j.objects.WebViewWrapper();
 //BA.debugLineNum = 8;BA.debugLine="Private we,temp,doc As JavaObject";
_we = new anywheresoftware.b4j.object.JavaObject();
_temp = new anywheresoftware.b4j.object.JavaObject();
_doc = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 9;BA.debugLine="Private js As jScriptEngine";
_js = new jScriptEngine.jScriptEngine();
 //BA.debugLineNum = 10;BA.debugLine="Private lis As List";
_lis = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 11;BA.debugLine="Private isChanged As Boolean";
_ischanged = false;
 //BA.debugLineNum = 12;BA.debugLine="Private Pane1 As Pane";
_pane1 = new anywheresoftware.b4j.objects.PaneWrapper.ConcretePaneWrapper();
 //BA.debugLineNum = 13;BA.debugLine="Private imghighlight As ImageView";
_imghighlight = new anywheresoftware.b4j.objects.ImageViewWrapper();
 //BA.debugLineNum = 14;BA.debugLine="Private imgremovehighlight As ImageView";
_imgremovehighlight = new anywheresoftware.b4j.objects.ImageViewWrapper();
 //BA.debugLineNum = 15;BA.debugLine="Private imgnote As ImageView";
_imgnote = new anywheresoftware.b4j.objects.ImageViewWrapper();
 //BA.debugLineNum = 16;BA.debugLine="Private imgcopy As ImageView";
_imgcopy = new anywheresoftware.b4j.objects.ImageViewWrapper();
 //BA.debugLineNum = 17;BA.debugLine="Private loading As Loading";
_loading = new b4j.example.loading();
 //BA.debugLineNum = 18;BA.debugLine="Private scale As Object = 1.0";
_scale = (Object)(1.0);
 //BA.debugLineNum = 19;BA.debugLine="Private lblnote As Label";
_lblnote = new anywheresoftware.b4j.objects.LabelWrapper();
 //BA.debugLineNum = 20;BA.debugLine="Private pnnote As Pane";
_pnnote = new anywheresoftware.b4j.objects.PaneWrapper.ConcretePaneWrapper();
 //BA.debugLineNum = 21;BA.debugLine="Private fontsize As Float";
_fontsize = 0f;
 //BA.debugLineNum = 22;BA.debugLine="Private fontfamily As String";
_fontfamily = "";
 //BA.debugLineNum = 23;BA.debugLine="Private Slider1 As Slider";
_slider1 = new anywheresoftware.b4j.objects.SliderWrapper();
 //BA.debugLineNum = 24;BA.debugLine="Private imgbookmark As ImageView";
_imgbookmark = new anywheresoftware.b4j.objects.ImageViewWrapper();
 //BA.debugLineNum = 25;BA.debugLine="Public offset,RefererNoteID As Int";
_offset = 0;
_referernoteid = 0;
 //BA.debugLineNum = 26;BA.debugLine="Private btnremove As Button";
_btnremove = new anywheresoftware.b4j.objects.ButtonWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Private currentNoteChoose As Int";
_currentnotechoose = 0;
 //BA.debugLineNum = 28;BA.debugLine="Public SearchWord As String";
_searchword = "";
 //BA.debugLineNum = 29;BA.debugLine="End Sub";
return "";
}
public static String  _showcontent() throws Exception{
anywheresoftware.b4j.objects.JFX.ScreenWrapper _sc = null;
String _js1 = "";
 //BA.debugLineNum = 31;BA.debugLine="Sub ShowContent";
 //BA.debugLineNum = 33;BA.debugLine="Dim sc As Screen";
_sc = new anywheresoftware.b4j.objects.JFX.ScreenWrapper();
 //BA.debugLineNum = 34;BA.debugLine="sc = fx.Screens.Get(0)";
_sc.setObject((javafx.stage.Screen)(_fx.getScreens().Get((int) (0))));
 //BA.debugLineNum = 36;BA.debugLine="GetSetting";
_getsetting();
 //BA.debugLineNum = 38;BA.debugLine="frmmdi.Initialize(\"frm1\",sc.MaxX / 2,sc.MaxY-50)";
_frmmdi.Initialize(ba,"frm1",_sc.getMaxX()/(double)2,_sc.getMaxY()-50);
 //BA.debugLineNum = 39;BA.debugLine="frmmdi.Title = \"کتاب\"";
_frmmdi.setTitle("کتاب");
 //BA.debugLineNum = 40;BA.debugLine="frmmdi.Icon = fx.LoadImage(File.DirAssets,\"icon.p";
_frmmdi.setIcon((javafx.scene.image.Image)(_fx.LoadImage(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"icon.png").getObject()));
 //BA.debugLineNum = 41;BA.debugLine="frmmdi.RootPane.LoadLayout(\"frmcontent\")";
_frmmdi.getRootPane().LoadLayout(ba,"frmcontent");
 //BA.debugLineNum = 42;BA.debugLine="frmmdi.WindowLeft = 0";
_frmmdi.setWindowLeft(0);
 //BA.debugLineNum = 43;BA.debugLine="Library.CenterFormOnScreen(frmmdi)";
_library._centerformonscreen(_frmmdi);
 //BA.debugLineNum = 44;BA.debugLine="frmmdi.WindowTop = 0";
_frmmdi.setWindowTop(0);
 //BA.debugLineNum = 45;BA.debugLine="frmmdi.Show";
_frmmdi.Show();
 //BA.debugLineNum = 47;BA.debugLine="imghighlight.MouseCursor = fx.Cursors.HAND";
_imghighlight.setMouseCursor(_fx.Cursors.HAND);
 //BA.debugLineNum = 48;BA.debugLine="imgcopy.MouseCursor = fx.Cursors.HAND";
_imgcopy.setMouseCursor(_fx.Cursors.HAND);
 //BA.debugLineNum = 49;BA.debugLine="imgnote.MouseCursor = fx.Cursors.HAND";
_imgnote.setMouseCursor(_fx.Cursors.HAND);
 //BA.debugLineNum = 50;BA.debugLine="imgremovehighlight.MouseCursor = fx.Cursors.HAND";
_imgremovehighlight.setMouseCursor(_fx.Cursors.HAND);
 //BA.debugLineNum = 51;BA.debugLine="imgbookmark.MouseCursor = fx.Cursors.HAND";
_imgbookmark.setMouseCursor(_fx.Cursors.HAND);
 //BA.debugLineNum = 53;BA.debugLine="lis.Initialize";
_lis.Initialize();
 //BA.debugLineNum = 55;BA.debugLine="we.InitializeNewInstance(\"javafx.scene.web.WebEng";
_we.InitializeNewInstance("javafx.scene.web.WebEngine",(Object[])(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 56;BA.debugLine="doc.InitializeStatic(\"org.w3c.dom.Document\")";
_doc.InitializeStatic("org.w3c.dom.Document");
 //BA.debugLineNum = 58;BA.debugLine="temp = wb1";
_temp.setObject((java.lang.Object)(_wb1.getObject()));
 //BA.debugLineNum = 59;BA.debugLine="we = temp.RunMethod(\"getEngine\",Null)";
_we.setObject((java.lang.Object)(_temp.RunMethod("getEngine",(Object[])(anywheresoftware.b4a.keywords.Common.Null))));
 //BA.debugLineNum = 61;BA.debugLine="Dim js1 As String";
_js1 = "";
 //BA.debugLineNum = 62;BA.debugLine="js1 = File.ReadString(File.DirAssets,\"js.txt\")";
_js1 = anywheresoftware.b4a.keywords.Common.File.ReadString(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"js.txt");
 //BA.debugLineNum = 63;BA.debugLine="js1 = js1.Replace(\"{content}\",data.Get(\"sText\"))";
_js1 = _js1.replace("{content}",BA.ObjectToString(_data.Get((Object)("sText"))));
 //BA.debugLineNum = 64;BA.debugLine="js1 = js1.Replace(\"{url}\",File.GetUri(File.DirAss";
_js1 = _js1.replace("{url}",anywheresoftware.b4a.keywords.Common.File.GetUri(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),_fontfamily));
 //BA.debugLineNum = 65;BA.debugLine="js1 = js1.Replace(\"{url_js}\",File.GetUri(File.Dir";
_js1 = _js1.replace("{url_js}",anywheresoftware.b4a.keywords.Common.File.GetUri(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"jquery.js"));
 //BA.debugLineNum = 66;BA.debugLine="js1 = js1.Replace(\"40px\",fontsize & \"px\")";
_js1 = _js1.replace("40px",BA.NumberToString(_fontsize)+"px");
 //BA.debugLineNum = 68;BA.debugLine="loading.Initialize(frmmdi)";
_loading._initialize(ba,_frmmdi);
 //BA.debugLineNum = 69;BA.debugLine="loading.Show(\"بارگذاری...\")";
_loading._show("بارگذاری...");
 //BA.debugLineNum = 70;BA.debugLine="wb1.Visible = False";
_wb1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 71;BA.debugLine="wb1.LoadHtml(js1)";
_wb1.LoadHtml(_js1);
 //BA.debugLineNum = 73;BA.debugLine="End Sub";
return "";
}
public static String  _slider1_valuechange(double _value) throws Exception{
anywheresoftware.b4j.object.JavaObject _jowv = null;
int _top = 0;
 //BA.debugLineNum = 239;BA.debugLine="Sub Slider1_ValueChange (Value As Double)";
 //BA.debugLineNum = 240;BA.debugLine="Dim joWV As JavaObject = wb1";
_jowv = new anywheresoftware.b4j.object.JavaObject();
_jowv.setObject((java.lang.Object)(_wb1.getObject()));
 //BA.debugLineNum = 241;BA.debugLine="Dim top As Int";
_top = 0;
 //BA.debugLineNum = 242;BA.debugLine="top = Value * (frmmdi.Width +frmmdi.Height)";
_top = (int) (_value*(_frmmdi.getWidth()+_frmmdi.getHeight()));
 //BA.debugLineNum = 243;BA.debugLine="joWV.RunMethodJO(\"getEngine\", Null).RunMethod(\"ex";
_jowv.RunMethodJO("getEngine",(Object[])(anywheresoftware.b4a.keywords.Common.Null)).RunMethod("executeScript",(Object[])(new String[]{"window.scrollTo(1, "+BA.NumberToString(_top)+");"}));
 //BA.debugLineNum = 244;BA.debugLine="End Sub";
return "";
}
public static String  _wb1_locationchanged(String _location) throws Exception{
 //BA.debugLineNum = 135;BA.debugLine="Sub wb1_LocationChanged (Location As String)";
 //BA.debugLineNum = 136;BA.debugLine="Log(Location)";
anywheresoftware.b4a.keywords.Common.Log(_location);
 //BA.debugLineNum = 137;BA.debugLine="End Sub";
return "";
}
public static String  _wb1_mouseclicked(anywheresoftware.b4j.objects.NodeWrapper.MouseEventWrapper _eventdata) throws Exception{
int _mousex = 0;
int _mousey = 0;
String _id = "";
String _note = "";
anywheresoftware.b4j.objects.JFX.ScreenWrapper _ps = null;
 //BA.debugLineNum = 100;BA.debugLine="Sub wb1_MouseClicked (EventData As MouseEvent)";
 //BA.debugLineNum = 102;BA.debugLine="Dim mousex,mousey As Int";
_mousex = 0;
_mousey = 0;
 //BA.debugLineNum = 103;BA.debugLine="mousex = EventData.X    ' get the link that was c";
_mousex = (int) (_eventdata.getX());
 //BA.debugLineNum = 104;BA.debugLine="mousey = EventData.Y";
_mousey = (int) (_eventdata.getY());
 //BA.debugLineNum = 106;BA.debugLine="js.evalString(\"var element = doc.elementFromPoint";
_js.evalString("var element = doc.elementFromPoint("+BA.NumberToString(_mousex)+","+BA.NumberToString(_mousey)+");");
 //BA.debugLineNum = 108;BA.debugLine="If js.engineGet(\"element\") <> Null Then";
if (_js.engineGet("element")!= null) { 
 //BA.debugLineNum = 109;BA.debugLine="Dim id As String";
_id = "";
 //BA.debugLineNum = 110;BA.debugLine="id = js.engineGet(\"element\")";
_id = BA.ObjectToString(_js.engineGet("element"));
 //BA.debugLineNum = 112;BA.debugLine="If id.StartsWith(\"[object\") = True Then";
if (_id.startsWith("[object")==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 113;BA.debugLine="pnnote.SetLayoutAnimated(600,pnnote.Left,-pnnot";
_pnnote.SetLayoutAnimated((int) (600),_pnnote.getLeft(),-_pnnote.getHeight(),_pnnote.getWidth(),_pnnote.getHeight());
 //BA.debugLineNum = 114;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 117;BA.debugLine="Dim note As String";
_note = "";
 //BA.debugLineNum = 118;BA.debugLine="note = Library.GetNoteContent(BookID,id)";
_note = _library._getnotecontent(_bookid,_id);
 //BA.debugLineNum = 120;BA.debugLine="If note.Length = 0 Or note = \"null\" Then";
if (_note.length()==0 || (_note).equals("null")) { 
 //BA.debugLineNum = 121;BA.debugLine="pnnote.SetLayoutAnimated(600,pnnote.Left,-pnnot";
_pnnote.SetLayoutAnimated((int) (600),_pnnote.getLeft(),-_pnnote.getHeight(),_pnnote.getWidth(),_pnnote.getHeight());
 //BA.debugLineNum = 122;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 125;BA.debugLine="currentNoteChoose = id";
_currentnotechoose = (int)(Double.parseDouble(_id));
 //BA.debugLineNum = 127;BA.debugLine="lblnote.Text = note";
_lblnote.setText(_note);
 //BA.debugLineNum = 128;BA.debugLine="Dim ps As Screen = fx.PrimaryScreen";
_ps = new anywheresoftware.b4j.objects.JFX.ScreenWrapper();
_ps = _fx.getPrimaryScreen();
 //BA.debugLineNum = 129;BA.debugLine="pnnote.Left = (frmmdi.Width - ps.MinX) / 2 - pnn";
_pnnote.setLeft((_frmmdi.getWidth()-_ps.getMinX())/(double)2-_pnnote.getWidth()/(double)2);
 //BA.debugLineNum = 130;BA.debugLine="pnnote.SetLayoutAnimated(600,pnnote.Left,-11,pnn";
_pnnote.SetLayoutAnimated((int) (600),_pnnote.getLeft(),-11,_pnnote.getWidth(),_pnnote.getHeight());
 };
 //BA.debugLineNum = 133;BA.debugLine="End Sub";
return "";
}
public static String  _wb1_mousemoved(anywheresoftware.b4j.objects.NodeWrapper.MouseEventWrapper _eventdata) throws Exception{
 //BA.debugLineNum = 210;BA.debugLine="Sub wb1_MouseMoved (EventData As MouseEvent)";
 //BA.debugLineNum = 211;BA.debugLine="If Pane1.Alpha = 1 Then";
if (_pane1.getAlpha()==1) { 
 //BA.debugLineNum = 212;BA.debugLine="Pane1.SetAlphaAnimated(400,0.7)";
_pane1.SetAlphaAnimated((int) (400),0.7);
 };
 //BA.debugLineNum = 214;BA.debugLine="End Sub";
return "";
}
public static String  _wb1_pagefinished(String _url) throws Exception{
anywheresoftware.b4j.object.JavaObject _jowv = null;
 //BA.debugLineNum = 75;BA.debugLine="Sub wb1_PageFinished (Url As String)";
 //BA.debugLineNum = 77;BA.debugLine="loading.Hide";
_loading._hide();
 //BA.debugLineNum = 78;BA.debugLine="wb1.Visible = True";
_wb1.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 80;BA.debugLine="js.enginePut(\"doc\",we.RunMethod(\"getDocument\",Nul";
_js.enginePut("doc",_we.RunMethod("getDocument",(Object[])(anywheresoftware.b4a.keywords.Common.Null)));
 //BA.debugLineNum = 81;BA.debugLine="lis.Clear";
_lis.Clear();
 //BA.debugLineNum = 82;BA.debugLine="js.enginePut(\"list\",lis) ' pass lis to js";
_js.enginePut("list",(Object)(_lis.getObject()));
 //BA.debugLineNum = 83;BA.debugLine="js.evalString(\"var linkindoc = doc.links;for (var";
_js.evalString("var linkindoc = doc.links;for (var x = 0;x < linkindoc.length;x++){ list.add(linkindoc.item(x));}");
 //BA.debugLineNum = 85;BA.debugLine="If offset > -1 Then";
if (_offset>-1) { 
 //BA.debugLineNum = 86;BA.debugLine="Dim joWV As JavaObject = wb1";
_jowv = new anywheresoftware.b4j.object.JavaObject();
_jowv.setObject((java.lang.Object)(_wb1.getObject()));
 //BA.debugLineNum = 87;BA.debugLine="joWV.RunMethodJO(\"getEngine\", Null).RunMethod(\"e";
_jowv.RunMethodJO("getEngine",(Object[])(anywheresoftware.b4a.keywords.Common.Null)).RunMethod("executeScript",(Object[])(new String[]{"window.scrollTo(1, "+BA.NumberToString(_offset)+");"}));
 };
 //BA.debugLineNum = 90;BA.debugLine="If RefererNoteID > 0 Then";
if (_referernoteid>0) { 
 //BA.debugLineNum = 91;BA.debugLine="ExecureJS(\"jump(\" & RefererNoteID & \");\")";
_execurejs("jump("+BA.NumberToString(_referernoteid)+");");
 };
 //BA.debugLineNum = 94;BA.debugLine="If SearchWord.Length > 0 Then";
if (_searchword.length()>0) { 
 //BA.debugLineNum = 95;BA.debugLine="ExecureJS(\"highlight_keyword(\"\"\" & SearchWord &";
_execurejs("highlight_keyword(\""+_searchword+"\");");
 };
 //BA.debugLineNum = 98;BA.debugLine="End Sub";
return "";
}
}
