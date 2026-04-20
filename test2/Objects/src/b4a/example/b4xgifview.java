package b4a.example;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class b4xgifview extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    private static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new BA(_ba, this, htSubs, "b4a.example.b4xgifview");
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            
        }
        if (BA.isShellModeRuntimeCheck(ba)) 
			   this.getClass().getMethod("_class_globals", b4a.example.b4xgifview.class).invoke(this, new Object[] {null});
        else
            ba.raiseEvent2(null, true, "class_globals", false);
    }

 public anywheresoftware.b4a.keywords.Common __c = null;
public String _vv1 = "";
public Object _vv2 = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _vv3 = null;
public anywheresoftware.b4a.objects.B4XViewWrapper.XUI _vv4 = null;
public Object _vv5 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _vv6 = null;
public anywheresoftware.b4j.object.JavaObject _vv7 = null;
public b4a.example.main _vv0 = null;
public b4a.example.starter _vvv1 = null;
public String  _base_resize(double _width,double _height) throws Exception{
 //BA.debugLineNum = 106;BA.debugLine="Public Sub Base_Resize (Width As Double, Height As";
 //BA.debugLineNum = 108;BA.debugLine="iv.SetLayoutAnimated(0, 0, 0, Width, Height)";
_vv6.SetLayoutAnimated((int) (0),(int) (0),(int) (0),(int) (_width),(int) (_height));
 //BA.debugLineNum = 117;BA.debugLine="End Sub";
return "";
}
public String  _class_globals() throws Exception{
 //BA.debugLineNum = 1;BA.debugLine="Sub Class_Globals";
 //BA.debugLineNum = 2;BA.debugLine="Private mEventName As String 'ignore";
_vv1 = "";
 //BA.debugLineNum = 3;BA.debugLine="Private mCallBack As Object 'ignore";
_vv2 = new Object();
 //BA.debugLineNum = 4;BA.debugLine="Public mBase As B4XView";
_vv3 = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 5;BA.debugLine="Private xui As XUI 'ignore";
_vv4 = new anywheresoftware.b4a.objects.B4XViewWrapper.XUI();
 //BA.debugLineNum = 6;BA.debugLine="Public Tag As Object";
_vv5 = new Object();
 //BA.debugLineNum = 10;BA.debugLine="Private iv As ImageView";
_vv6 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 11;BA.debugLine="Public GifDrawable As JavaObject";
_vv7 = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 15;BA.debugLine="End Sub";
return "";
}
public String  _designercreateview(Object _base,anywheresoftware.b4a.objects.LabelWrapper _lbl,anywheresoftware.b4a.objects.collections.Map _props) throws Exception{
 //BA.debugLineNum = 22;BA.debugLine="Public Sub DesignerCreateView (Base As Object, Lbl";
 //BA.debugLineNum = 23;BA.debugLine="mBase = Base";
_vv3 = (anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_base));
 //BA.debugLineNum = 24;BA.debugLine="Tag = mBase.Tag";
_vv5 = _vv3.getTag();
 //BA.debugLineNum = 25;BA.debugLine="mBase.Tag = Me";
_vv3.setTag(this);
 //BA.debugLineNum = 36;BA.debugLine="iv.Initialize(\"\")";
_vv6.Initialize(ba,"");
 //BA.debugLineNum = 37;BA.debugLine="mBase.AddView(iv, 0, 0, mBase.Width, mBase.Height";
_vv3.AddView((android.view.View)(_vv6.getObject()),(int) (0),(int) (0),_vv3.getWidth(),_vv3.getHeight());
 //BA.debugLineNum = 39;BA.debugLine="End Sub";
return "";
}
public String  _initialize(anywheresoftware.b4a.BA _ba,Object _callback,String _eventname) throws Exception{
innerInitialize(_ba);
 //BA.debugLineNum = 17;BA.debugLine="Public Sub Initialize (Callback As Object, EventNa";
 //BA.debugLineNum = 18;BA.debugLine="mEventName = EventName";
_vv1 = _eventname;
 //BA.debugLineNum = 19;BA.debugLine="mCallBack = Callback";
_vv2 = _callback;
 //BA.debugLineNum = 20;BA.debugLine="End Sub";
return "";
}
public String  _v5(anywheresoftware.b4a.objects.B4XViewWrapper _xiv,float _bmpratio) throws Exception{
float _viewratio = 0f;
int _height = 0;
int _width = 0;
 //BA.debugLineNum = 93;BA.debugLine="Private Sub ResizeBasedOnImage(xiv As B4XView, Bmp";
 //BA.debugLineNum = 94;BA.debugLine="Dim viewRatio As Float = mBase.Width / mBase.Heig";
_viewratio = (float) (_vv3.getWidth()/(double)_vv3.getHeight());
 //BA.debugLineNum = 95;BA.debugLine="Dim Height, Width As Int";
_height = 0;
_width = 0;
 //BA.debugLineNum = 96;BA.debugLine="If viewRatio > BmpRatio Then";
if (_viewratio>_bmpratio) { 
 //BA.debugLineNum = 97;BA.debugLine="Height = mBase.Height";
_height = _vv3.getHeight();
 //BA.debugLineNum = 98;BA.debugLine="Width = mBase.Height * BmpRatio";
_width = (int) (_vv3.getHeight()*_bmpratio);
 }else {
 //BA.debugLineNum = 100;BA.debugLine="Width = mBase.Width";
_width = _vv3.getWidth();
 //BA.debugLineNum = 101;BA.debugLine="Height = mBase.Width / BmpRatio";
_height = (int) (_vv3.getWidth()/(double)_bmpratio);
 };
 //BA.debugLineNum = 103;BA.debugLine="xiv.SetLayoutAnimated(0, mBase.Width / 2 - Width";
_xiv.SetLayoutAnimated((int) (0),(int) (_vv3.getWidth()/(double)2-_width/(double)2),(int) (_vv3.getHeight()/(double)2-_height/(double)2),_width,_height);
 //BA.debugLineNum = 104;BA.debugLine="End Sub";
return "";
}
public String  _v6(Object _obj) throws Exception{
anywheresoftware.b4j.object.JavaObject _jo = null;
int _w = 0;
int _h = 0;
 //BA.debugLineNum = 81;BA.debugLine="Private Sub SetBitmap(obj As Object)";
 //BA.debugLineNum = 82;BA.debugLine="Dim GifDrawable As JavaObject";
_vv7 = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 83;BA.debugLine="GifDrawable.InitializeNewInstance(\"pl.droidsonroi";
_vv7.InitializeNewInstance("pl.droidsonroids.gif.GifDrawable",new Object[]{_obj});
 //BA.debugLineNum = 84;BA.debugLine="iv.Background = GifDrawable";
_vv6.setBackground((android.graphics.drawable.Drawable)(_vv7.getObject()));
 //BA.debugLineNum = 85;BA.debugLine="Dim jo As JavaObject = GifDrawable";
_jo = new anywheresoftware.b4j.object.JavaObject();
_jo = _vv7;
 //BA.debugLineNum = 86;BA.debugLine="Dim w As Int = jo.RunMethod(\"getIntrinsicWidth\",";
_w = (int)(BA.ObjectToNumber(_jo.RunMethod("getIntrinsicWidth",(Object[])(__c.Null))));
 //BA.debugLineNum = 87;BA.debugLine="Dim h As Int = jo.RunMethod(\"getIntrinsicHeight\",";
_h = (int)(BA.ObjectToNumber(_jo.RunMethod("getIntrinsicHeight",(Object[])(__c.Null))));
 //BA.debugLineNum = 88;BA.debugLine="ResizeBasedOnImage(iv, w / h)";
_v5((anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_vv6.getObject())),(float) (_w/(double)_h));
 //BA.debugLineNum = 89;BA.debugLine="End Sub";
return "";
}
public String  _v7(String _dir,String _filename) throws Exception{
 //BA.debugLineNum = 41;BA.debugLine="Public Sub SetGif(Dir As String, FileName As Strin";
 //BA.debugLineNum = 47;BA.debugLine="If Dir = File.DirAssets Then";
if ((_dir).equals(__c.File.getDirAssets())) { 
 //BA.debugLineNum = 48;BA.debugLine="SetGif2(File.ReadBytes(Dir, FileName))";
_v0(__c.File.ReadBytes(_dir,_filename));
 }else {
 //BA.debugLineNum = 50;BA.debugLine="SetBitmap(File.Combine(Dir, FileName))";
_v6((Object)(__c.File.Combine(_dir,_filename)));
 };
 //BA.debugLineNum = 53;BA.debugLine="End Sub";
return "";
}
public String  _v0(byte[] _data) throws Exception{
 //BA.debugLineNum = 55;BA.debugLine="Public Sub SetGif2 (Data() As Byte)";
 //BA.debugLineNum = 69;BA.debugLine="SetBitmap(Data)";
_v6((Object)(_data));
 //BA.debugLineNum = 71;BA.debugLine="End Sub";
return "";
}
public Object callSub(String sub, Object sender, Object[] args) throws Exception {
BA.senderHolder.set(sender);
return BA.SubDelegator.SubNotFound;
}
}
