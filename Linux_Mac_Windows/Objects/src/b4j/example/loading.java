package b4j.example;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.debug.*;

public class loading extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    public static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new  anywheresoftware.b4j.objects.FxBA("b4j.example", "b4j.example.loading", this);
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            ba.htSubs = htSubs;
             
        }
        if (BA.isShellModeRuntimeCheck(ba))
                this.getClass().getMethod("_class_globals", b4j.example.loading.class).invoke(this, new Object[] {null});
        else
            ba.raiseEvent2(null, true, "class_globals", false);
    }

 public anywheresoftware.b4a.keywords.Common __c = null;
public anywheresoftware.b4j.objects.JFX _fx = null;
public anywheresoftware.b4j.objects.Form _frm1 = null;
public anywheresoftware.b4j.objects.LabelWrapper _lbltitle = null;
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
 //BA.debugLineNum = 3;BA.debugLine="Private fx As JFX";
_fx = new anywheresoftware.b4j.objects.JFX();
 //BA.debugLineNum = 4;BA.debugLine="Private frm1 As Form";
_frm1 = new anywheresoftware.b4j.objects.Form();
 //BA.debugLineNum = 5;BA.debugLine="Private lbltitle As Label";
_lbltitle = new anywheresoftware.b4j.objects.LabelWrapper();
 //BA.debugLineNum = 6;BA.debugLine="End Sub";
return "";
}
public String  _hide() throws Exception{
 //BA.debugLineNum = 23;BA.debugLine="Public Sub Hide";
 //BA.debugLineNum = 24;BA.debugLine="frm1.Close";
_frm1.Close();
 //BA.debugLineNum = 25;BA.debugLine="End Sub";
return "";
}
public String  _initialize(anywheresoftware.b4a.BA _ba,anywheresoftware.b4j.objects.Form _owner) throws Exception{
innerInitialize(_ba);
 //BA.debugLineNum = 9;BA.debugLine="Public Sub Initialize(Owner As Form)";
 //BA.debugLineNum = 10;BA.debugLine="frm1.Initialize(\"\",275,107)";
_frm1.Initialize(ba,"",275,107);
 //BA.debugLineNum = 11;BA.debugLine="frm1.RootPane.LoadLayout(\"frmloading\")";
_frm1.getRootPane().LoadLayout(ba,"frmloading");
 //BA.debugLineNum = 12;BA.debugLine="frm1.Title = \"...\"";
_frm1.setTitle("...");
 //BA.debugLineNum = 13;BA.debugLine="frm1.Resizable = False";
_frm1.setResizable(__c.False);
 //BA.debugLineNum = 14;BA.debugLine="Library.CenterFormOnScreen(frm1)";
_library._centerformonscreen(_frm1);
 //BA.debugLineNum = 15;BA.debugLine="frm1.SetOwner(Owner)";
_frm1.SetOwner(_owner);
 //BA.debugLineNum = 16;BA.debugLine="End Sub";
return "";
}
public String  _show(String _title) throws Exception{
 //BA.debugLineNum = 18;BA.debugLine="Public Sub Show(Title As String)";
 //BA.debugLineNum = 19;BA.debugLine="lbltitle.Text = Title";
_lbltitle.setText(_title);
 //BA.debugLineNum = 20;BA.debugLine="frm1.Show";
_frm1.Show();
 //BA.debugLineNum = 21;BA.debugLine="End Sub";
return "";
}
public Object callSub(String sub, Object sender, Object[] args) throws Exception {
BA.senderHolder.set(sender);
return BA.SubDelegator.SubNotFound;
}
}
