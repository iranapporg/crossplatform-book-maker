package b4j.example;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.debug.*;

public class imagedownloader extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    public static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new  anywheresoftware.b4j.objects.FxBA("b4j.example", "b4j.example.imagedownloader", this);
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            ba.htSubs = htSubs;
             
        }
        if (BA.isShellModeRuntimeCheck(ba))
                this.getClass().getMethod("_class_globals", b4j.example.imagedownloader.class).invoke(this, new Object[] {null});
        else
            ba.raiseEvent2(null, true, "class_globals", false);
    }

 public anywheresoftware.b4a.keywords.Common __c = null;
public anywheresoftware.b4a.objects.collections.Map _tasks = null;
public anywheresoftware.b4a.objects.collections.Map _ongoingtasks = null;
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
 //BA.debugLineNum = 3;BA.debugLine="Private tasks As Map";
_tasks = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 4;BA.debugLine="Private ongoingTasks As Map";
_ongoingtasks = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 5;BA.debugLine="End Sub";
return "";
}
public String  _download(anywheresoftware.b4a.objects.collections.Map _imageviewsmap) throws Exception{
anywheresoftware.b4j.objects.ImageViewWrapper _iv = null;
String _link = "";
b4j.example.httpjob _j = null;
 //BA.debugLineNum = 12;BA.debugLine="Public Sub Download (ImageViewsMap As Map)";
 //BA.debugLineNum = 13;BA.debugLine="For Each iv As ImageView In ImageViewsMap.Keys";
_iv = new anywheresoftware.b4j.objects.ImageViewWrapper();
final anywheresoftware.b4a.BA.IterableList group1 = _imageviewsmap.Keys();
final int groupLen1 = group1.getSize();
for (int index1 = 0;index1 < groupLen1 ;index1++){
_iv.setObject((javafx.scene.image.ImageView)(group1.Get(index1)));
 //BA.debugLineNum = 14;BA.debugLine="Dim link As String = ImageViewsMap.Get(iv)";
_link = BA.ObjectToString(_imageviewsmap.Get((Object)(_iv.getObject())));
 //BA.debugLineNum = 15;BA.debugLine="tasks.Put(iv, link)";
_tasks.Put((Object)(_iv.getObject()),(Object)(_link));
 //BA.debugLineNum = 16;BA.debugLine="If ongoingTasks.ContainsKey(link) = False Then";
if (_ongoingtasks.ContainsKey((Object)(_link))==__c.False) { 
 //BA.debugLineNum = 17;BA.debugLine="ongoingTasks.Put(link, \"\")";
_ongoingtasks.Put((Object)(_link),(Object)(""));
 //BA.debugLineNum = 18;BA.debugLine="Dim j As HttpJob";
_j = new b4j.example.httpjob();
 //BA.debugLineNum = 19;BA.debugLine="j.Initialize(link, Me)";
_j._initialize(ba,_link,this);
 //BA.debugLineNum = 20;BA.debugLine="j.Download(link)";
_j._download(_link);
 };
 }
;
 //BA.debugLineNum = 23;BA.debugLine="End Sub";
return "";
}
public String  _initialize(anywheresoftware.b4a.BA _ba) throws Exception{
innerInitialize(_ba);
 //BA.debugLineNum = 7;BA.debugLine="Public Sub Initialize";
 //BA.debugLineNum = 8;BA.debugLine="tasks.Initialize";
_tasks.Initialize();
 //BA.debugLineNum = 9;BA.debugLine="ongoingTasks.Initialize";
_ongoingtasks.Initialize();
 //BA.debugLineNum = 10;BA.debugLine="End Sub";
return "";
}
public String  _jobdone(b4j.example.httpjob _job) throws Exception{
anywheresoftware.b4j.objects.ImageViewWrapper.ImageWrapper _bmp = null;
anywheresoftware.b4j.objects.ImageViewWrapper _iv = null;
String _link = "";
 //BA.debugLineNum = 25;BA.debugLine="Private Sub JobDone(Job As HttpJob)";
 //BA.debugLineNum = 26;BA.debugLine="ongoingTasks.Remove(Job.JobName)";
_ongoingtasks.Remove((Object)(_job._jobname));
 //BA.debugLineNum = 27;BA.debugLine="If Job.Success Then";
if (_job._success) { 
 //BA.debugLineNum = 28;BA.debugLine="Dim bmp As Image = Job.GetBitmap";
_bmp = new anywheresoftware.b4j.objects.ImageViewWrapper.ImageWrapper();
_bmp = _job._getbitmap();
 //BA.debugLineNum = 29;BA.debugLine="If tasks.IsInitialized Then";
if (_tasks.IsInitialized()) { 
 //BA.debugLineNum = 30;BA.debugLine="For Each iv As ImageView In tasks.Keys";
_iv = new anywheresoftware.b4j.objects.ImageViewWrapper();
final anywheresoftware.b4a.BA.IterableList group5 = _tasks.Keys();
final int groupLen5 = group5.getSize();
for (int index5 = 0;index5 < groupLen5 ;index5++){
_iv.setObject((javafx.scene.image.ImageView)(group5.Get(index5)));
 //BA.debugLineNum = 31;BA.debugLine="Dim link As String = tasks.Get(iv)";
_link = BA.ObjectToString(_tasks.Get((Object)(_iv.getObject())));
 //BA.debugLineNum = 32;BA.debugLine="If link = Job.JobName Then";
if ((_link).equals(_job._jobname)) { 
 //BA.debugLineNum = 33;BA.debugLine="tasks.Remove(iv)";
_tasks.Remove((Object)(_iv.getObject()));
 //BA.debugLineNum = 34;BA.debugLine="iv.SetImage(bmp)";
_iv.SetImage((javafx.scene.image.Image)(_bmp.getObject()));
 };
 }
;
 };
 }else {
 //BA.debugLineNum = 39;BA.debugLine="Log(\"Error downloading image: \" & Job.JobName &";
__c.Log("Error downloading image: "+_job._jobname+__c.CRLF+_job._errormessage);
 };
 //BA.debugLineNum = 41;BA.debugLine="Job.Release";
_job._release();
 //BA.debugLineNum = 42;BA.debugLine="End Sub";
return "";
}
public Object callSub(String sub, Object sender, Object[] args) throws Exception {
BA.senderHolder.set(sender);
return BA.SubDelegator.SubNotFound;
}
}
