package b4a.example;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class b4xdrawer extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    private static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new anywheresoftware.b4a.ShellBA(_ba, this, htSubs, "b4a.example.b4xdrawer");
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            
        }
        if (BA.isShellModeRuntimeCheck(ba)) 
			   this.getClass().getMethod("_class_globals", b4a.example.b4xdrawer.class).invoke(this, new Object[] {null});
        else
            ba.raiseEvent2(null, true, "class_globals", false);
    }

 
    public void  innerInitializeHelper(anywheresoftware.b4a.BA _ba) throws Exception{
        innerInitialize(_ba);
    }
    public Object callSub(String sub, Object sender, Object[] args) throws Exception {
        return BA.SubDelegator.SubNotFound;
    }
public anywheresoftware.b4a.keywords.Common __c = null;
public String _meventname = "";
public Object _mcallback = null;
public anywheresoftware.b4a.objects.B4XViewWrapper.XUI _xui = null;
public int _msidewidth = 0;
public anywheresoftware.b4a.objects.B4XViewWrapper _mleftpanel = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _mdarkpanel = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _mbasepanel = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _mcenterpanel = null;
public int _extrawidth = 0;
public float _touchxstart = 0f;
public float _touchystart = 0f;
public boolean _isopen = false;
public boolean _handlingswipe = false;
public boolean _startatscrim = false;
public boolean _menabled = false;
public b4a.example.main _main = null;
public b4a.example.starter _starter = null;
public String  _setleftopen(b4a.example.b4xdrawer __ref,boolean _b) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xdrawer";
if (Debug.shouldDelegate(ba, "setleftopen", true))
	 {return ((String) Debug.delegate(ba, "setleftopen", new Object[] {_b}));}
float _x = 0f;
RDebugUtils.currentLine=3866624;
 //BA.debugLineNum = 3866624;BA.debugLine="Public Sub setLeftOpen (b As Boolean)";
RDebugUtils.currentLine=3866625;
 //BA.debugLineNum = 3866625;BA.debugLine="If b = IsOpen Then Return";
if (_b==__ref._isopen /*boolean*/ ) { 
if (true) return "";};
RDebugUtils.currentLine=3866626;
 //BA.debugLineNum = 3866626;BA.debugLine="Dim x As Float";
_x = 0f;
RDebugUtils.currentLine=3866627;
 //BA.debugLineNum = 3866627;BA.debugLine="If b Then x = 0 Else x = -mSideWidth";
if (_b) { 
_x = (float) (0);}
else {
_x = (float) (-__ref._msidewidth /*int*/ );};
RDebugUtils.currentLine=3866628;
 //BA.debugLineNum = 3866628;BA.debugLine="ChangeOffset(x, False, False)";
__ref._changeoffset /*String*/ (null,_x,__c.False,__c.False);
RDebugUtils.currentLine=3866629;
 //BA.debugLineNum = 3866629;BA.debugLine="End Sub";
return "";
}
public boolean  _getleftopen(b4a.example.b4xdrawer __ref) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xdrawer";
if (Debug.shouldDelegate(ba, "getleftopen", true))
	 {return ((Boolean) Debug.delegate(ba, "getleftopen", null));}
RDebugUtils.currentLine=3801088;
 //BA.debugLineNum = 3801088;BA.debugLine="Public Sub getLeftOpen As Boolean";
RDebugUtils.currentLine=3801089;
 //BA.debugLineNum = 3801089;BA.debugLine="Return IsOpen";
if (true) return __ref._isopen /*boolean*/ ;
RDebugUtils.currentLine=3801090;
 //BA.debugLineNum = 3801090;BA.debugLine="End Sub";
return false;
}
public String  _initialize(b4a.example.b4xdrawer __ref,anywheresoftware.b4a.BA _ba,Object _callback,String _eventname,anywheresoftware.b4a.objects.B4XViewWrapper _parent,int _sidewidth) throws Exception{
__ref = this;
innerInitialize(_ba);
RDebugUtils.currentModule="b4xdrawer";
if (Debug.shouldDelegate(ba, "initialize", true))
	 {return ((String) Debug.delegate(ba, "initialize", new Object[] {_ba,_callback,_eventname,_parent,_sidewidth}));}
anywheresoftware.b4a.objects.TouchPanelCreator _creator = null;
anywheresoftware.b4a.objects.PanelWrapper _p = null;
RDebugUtils.currentLine=3342336;
 //BA.debugLineNum = 3342336;BA.debugLine="Public Sub Initialize (Callback As Object, EventNa";
RDebugUtils.currentLine=3342337;
 //BA.debugLineNum = 3342337;BA.debugLine="mEventName = EventName";
__ref._meventname /*String*/  = _eventname;
RDebugUtils.currentLine=3342338;
 //BA.debugLineNum = 3342338;BA.debugLine="mCallBack = Callback";
__ref._mcallback /*Object*/  = _callback;
RDebugUtils.currentLine=3342339;
 //BA.debugLineNum = 3342339;BA.debugLine="mSideWidth = SideWidth";
__ref._msidewidth /*int*/  = _sidewidth;
RDebugUtils.currentLine=3342341;
 //BA.debugLineNum = 3342341;BA.debugLine="Dim creator As TouchPanelCreator";
_creator = new anywheresoftware.b4a.objects.TouchPanelCreator();
RDebugUtils.currentLine=3342342;
 //BA.debugLineNum = 3342342;BA.debugLine="mBasePanel = creator.CreateTouchPanel(\"base\")";
__ref._mbasepanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/  = (anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_creator.CreateTouchPanel(ba,"base").getObject()));
RDebugUtils.currentLine=3342351;
 //BA.debugLineNum = 3342351;BA.debugLine="Parent.AddView(mBasePanel, 0, 0, Parent.Width, Pa";
_parent.AddView((android.view.View)(__ref._mbasepanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .getObject()),(int) (0),(int) (0),_parent.getWidth(),_parent.getHeight());
RDebugUtils.currentLine=3342352;
 //BA.debugLineNum = 3342352;BA.debugLine="mCenterPanel = xui.CreatePanel(\"\")";
__ref._mcenterpanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/  = __ref._xui /*anywheresoftware.b4a.objects.B4XViewWrapper.XUI*/ .CreatePanel(ba,"");
RDebugUtils.currentLine=3342353;
 //BA.debugLineNum = 3342353;BA.debugLine="mBasePanel.AddView(mCenterPanel, 0, 0, mBasePanel";
__ref._mbasepanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .AddView((android.view.View)(__ref._mcenterpanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .getObject()),(int) (0),(int) (0),__ref._mbasepanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .getWidth(),__ref._mbasepanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .getHeight());
RDebugUtils.currentLine=3342354;
 //BA.debugLineNum = 3342354;BA.debugLine="mDarkPanel = xui.CreatePanel(\"dark\")";
__ref._mdarkpanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/  = __ref._xui /*anywheresoftware.b4a.objects.B4XViewWrapper.XUI*/ .CreatePanel(ba,"dark");
RDebugUtils.currentLine=3342355;
 //BA.debugLineNum = 3342355;BA.debugLine="mBasePanel.AddView(mDarkPanel, 0, 0, mBasePanel.W";
__ref._mbasepanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .AddView((android.view.View)(__ref._mdarkpanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .getObject()),(int) (0),(int) (0),__ref._mbasepanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .getWidth(),__ref._mbasepanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .getHeight());
RDebugUtils.currentLine=3342356;
 //BA.debugLineNum = 3342356;BA.debugLine="mLeftPanel = xui.CreatePanel(\"LeftPanel\")";
__ref._mleftpanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/  = __ref._xui /*anywheresoftware.b4a.objects.B4XViewWrapper.XUI*/ .CreatePanel(ba,"LeftPanel");
RDebugUtils.currentLine=3342357;
 //BA.debugLineNum = 3342357;BA.debugLine="mBasePanel.AddView(mLeftPanel, -SideWidth, 0, Sid";
__ref._mbasepanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .AddView((android.view.View)(__ref._mleftpanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .getObject()),(int) (-_sidewidth),(int) (0),_sidewidth,__ref._mbasepanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .getHeight());
RDebugUtils.currentLine=3342358;
 //BA.debugLineNum = 3342358;BA.debugLine="mLeftPanel.Color = xui.Color_Red";
__ref._mleftpanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .setColor(__ref._xui /*anywheresoftware.b4a.objects.B4XViewWrapper.XUI*/ .Color_Red);
RDebugUtils.currentLine=3342360;
 //BA.debugLineNum = 3342360;BA.debugLine="Dim p As Panel = mLeftPanel";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
_p = (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(__ref._mleftpanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .getObject()));
RDebugUtils.currentLine=3342361;
 //BA.debugLineNum = 3342361;BA.debugLine="p.Elevation = 4dip";
_p.setElevation((float) (__c.DipToCurrent((int) (4))));
RDebugUtils.currentLine=3342375;
 //BA.debugLineNum = 3342375;BA.debugLine="End Sub";
return "";
}
public anywheresoftware.b4a.objects.B4XViewWrapper  _getcenterpanel(b4a.example.b4xdrawer __ref) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xdrawer";
if (Debug.shouldDelegate(ba, "getcenterpanel", true))
	 {return ((anywheresoftware.b4a.objects.B4XViewWrapper) Debug.delegate(ba, "getcenterpanel", null));}
RDebugUtils.currentLine=3997696;
 //BA.debugLineNum = 3997696;BA.debugLine="Public Sub getCenterPanel As B4XView";
RDebugUtils.currentLine=3997697;
 //BA.debugLineNum = 3997697;BA.debugLine="Return mCenterPanel";
if (true) return __ref._mcenterpanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ ;
RDebugUtils.currentLine=3997698;
 //BA.debugLineNum = 3997698;BA.debugLine="End Sub";
return null;
}
public anywheresoftware.b4a.objects.B4XViewWrapper  _getleftpanel(b4a.example.b4xdrawer __ref) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xdrawer";
if (Debug.shouldDelegate(ba, "getleftpanel", true))
	 {return ((anywheresoftware.b4a.objects.B4XViewWrapper) Debug.delegate(ba, "getleftpanel", null));}
RDebugUtils.currentLine=3932160;
 //BA.debugLineNum = 3932160;BA.debugLine="Public Sub getLeftPanel As B4XView";
RDebugUtils.currentLine=3932161;
 //BA.debugLineNum = 3932161;BA.debugLine="Return mLeftPanel";
if (true) return __ref._mleftpanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ ;
RDebugUtils.currentLine=3932162;
 //BA.debugLineNum = 3932162;BA.debugLine="End Sub";
return null;
}
public boolean  _base_onintercepttouchevent(b4a.example.b4xdrawer __ref,int _action,float _x,float _y,Object _motionevent) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xdrawer";
if (Debug.shouldDelegate(ba, "base_onintercepttouchevent", true))
	 {return ((Boolean) Debug.delegate(ba, "base_onintercepttouchevent", new Object[] {_action,_x,_y,_motionevent}));}
float _dx = 0f;
float _dy = 0f;
RDebugUtils.currentLine=3538944;
 //BA.debugLineNum = 3538944;BA.debugLine="Private Sub Base_OnInterceptTouchEvent (Action As";
RDebugUtils.currentLine=3538945;
 //BA.debugLineNum = 3538945;BA.debugLine="If mEnabled = False Then Return False";
if (__ref._menabled /*boolean*/ ==__c.False) { 
if (true) return __c.False;};
RDebugUtils.currentLine=3538946;
 //BA.debugLineNum = 3538946;BA.debugLine="If IsOpen = False And x > mLeftPanel.Left + mLeft";
if (__ref._isopen /*boolean*/ ==__c.False && _x>__ref._mleftpanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .getLeft()+__ref._mleftpanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .getWidth()+__ref._extrawidth /*int*/ ) { 
if (true) return __c.False;};
RDebugUtils.currentLine=3538947;
 //BA.debugLineNum = 3538947;BA.debugLine="If IsOpen And x > mLeftPanel.Left + mLeftPanel.Wi";
if (__ref._isopen /*boolean*/  && _x>__ref._mleftpanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .getLeft()+__ref._mleftpanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .getWidth()) { 
RDebugUtils.currentLine=3538949;
 //BA.debugLineNum = 3538949;BA.debugLine="Return True";
if (true) return __c.True;
 };
RDebugUtils.currentLine=3538951;
 //BA.debugLineNum = 3538951;BA.debugLine="If HandlingSwipe Then Return True";
if (__ref._handlingswipe /*boolean*/ ) { 
if (true) return __c.True;};
RDebugUtils.currentLine=3538952;
 //BA.debugLineNum = 3538952;BA.debugLine="Select Action";
switch (BA.switchObjectToInt(_action,__ref._mbasepanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .TOUCH_ACTION_DOWN,__ref._mbasepanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .TOUCH_ACTION_MOVE)) {
case 0: {
RDebugUtils.currentLine=3538954;
 //BA.debugLineNum = 3538954;BA.debugLine="TouchXStart = X";
__ref._touchxstart /*float*/  = _x;
RDebugUtils.currentLine=3538955;
 //BA.debugLineNum = 3538955;BA.debugLine="TouchYStart = Y";
__ref._touchystart /*float*/  = _y;
RDebugUtils.currentLine=3538956;
 //BA.debugLineNum = 3538956;BA.debugLine="HandlingSwipe = False";
__ref._handlingswipe /*boolean*/  = __c.False;
 break; }
case 1: {
RDebugUtils.currentLine=3538958;
 //BA.debugLineNum = 3538958;BA.debugLine="Dim dx As Float = Abs(x - TouchXStart)";
_dx = (float) (__c.Abs(_x-__ref._touchxstart /*float*/ ));
RDebugUtils.currentLine=3538959;
 //BA.debugLineNum = 3538959;BA.debugLine="Dim dy As Float = Abs(y - TouchYStart)";
_dy = (float) (__c.Abs(_y-__ref._touchystart /*float*/ ));
RDebugUtils.currentLine=3538960;
 //BA.debugLineNum = 3538960;BA.debugLine="If dy < 20dip And dx > 10dip Then";
if (_dy<__c.DipToCurrent((int) (20)) && _dx>__c.DipToCurrent((int) (10))) { 
RDebugUtils.currentLine=3538961;
 //BA.debugLineNum = 3538961;BA.debugLine="HandlingSwipe = True";
__ref._handlingswipe /*boolean*/  = __c.True;
 };
 break; }
}
;
RDebugUtils.currentLine=3538964;
 //BA.debugLineNum = 3538964;BA.debugLine="Return HandlingSwipe";
if (true) return __ref._handlingswipe /*boolean*/ ;
RDebugUtils.currentLine=3538965;
 //BA.debugLineNum = 3538965;BA.debugLine="End Sub";
return false;
}
public boolean  _base_ontouchevent(b4a.example.b4xdrawer __ref,int _action,float _x,float _y,Object _motionevent) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xdrawer";
if (Debug.shouldDelegate(ba, "base_ontouchevent", true))
	 {return ((Boolean) Debug.delegate(ba, "base_ontouchevent", new Object[] {_action,_x,_y,_motionevent}));}
int _leftpanelrightside = 0;
float _dx = 0f;
RDebugUtils.currentLine=3473408;
 //BA.debugLineNum = 3473408;BA.debugLine="Private Sub Base_OnTouchEvent (Action As Int, X As";
RDebugUtils.currentLine=3473409;
 //BA.debugLineNum = 3473409;BA.debugLine="If mEnabled = False Then Return False";
if (__ref._menabled /*boolean*/ ==__c.False) { 
if (true) return __c.False;};
RDebugUtils.currentLine=3473410;
 //BA.debugLineNum = 3473410;BA.debugLine="Dim LeftPanelRightSide As Int = mLeftPanel.Left +";
_leftpanelrightside = (int) (__ref._mleftpanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .getLeft()+__ref._mleftpanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .getWidth());
RDebugUtils.currentLine=3473411;
 //BA.debugLineNum = 3473411;BA.debugLine="If HandlingSwipe = False And x > LeftPanelRightSi";
if (__ref._handlingswipe /*boolean*/ ==__c.False && _x>_leftpanelrightside) { 
RDebugUtils.currentLine=3473412;
 //BA.debugLineNum = 3473412;BA.debugLine="If IsOpen Then";
if (__ref._isopen /*boolean*/ ) { 
RDebugUtils.currentLine=3473413;
 //BA.debugLineNum = 3473413;BA.debugLine="TouchXStart = X";
__ref._touchxstart /*float*/  = _x;
RDebugUtils.currentLine=3473414;
 //BA.debugLineNum = 3473414;BA.debugLine="If Action = mBasePanel.TOUCH_ACTION_UP Then set";
if (_action==__ref._mbasepanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .TOUCH_ACTION_UP) { 
__ref._setleftopen /*String*/ (null,__c.False);};
RDebugUtils.currentLine=3473415;
 //BA.debugLineNum = 3473415;BA.debugLine="Return True";
if (true) return __c.True;
 };
RDebugUtils.currentLine=3473417;
 //BA.debugLineNum = 3473417;BA.debugLine="If IsOpen = False And x > LeftPanelRightSide + E";
if (__ref._isopen /*boolean*/ ==__c.False && _x>_leftpanelrightside+__ref._extrawidth /*int*/ ) { 
RDebugUtils.currentLine=3473418;
 //BA.debugLineNum = 3473418;BA.debugLine="Return False";
if (true) return __c.False;
 };
 };
RDebugUtils.currentLine=3473421;
 //BA.debugLineNum = 3473421;BA.debugLine="Select Action";
switch (BA.switchObjectToInt(_action,__ref._mbasepanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .TOUCH_ACTION_MOVE,__ref._mbasepanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .TOUCH_ACTION_UP)) {
case 0: {
RDebugUtils.currentLine=3473423;
 //BA.debugLineNum = 3473423;BA.debugLine="Dim dx As Float = x - TouchXStart";
_dx = (float) (_x-__ref._touchxstart /*float*/ );
RDebugUtils.currentLine=3473424;
 //BA.debugLineNum = 3473424;BA.debugLine="TouchXStart = X";
__ref._touchxstart /*float*/  = _x;
RDebugUtils.currentLine=3473425;
 //BA.debugLineNum = 3473425;BA.debugLine="If HandlingSwipe Or Abs(dx) > 3dip Then";
if (__ref._handlingswipe /*boolean*/  || __c.Abs(_dx)>__c.DipToCurrent((int) (3))) { 
RDebugUtils.currentLine=3473426;
 //BA.debugLineNum = 3473426;BA.debugLine="HandlingSwipe = True";
__ref._handlingswipe /*boolean*/  = __c.True;
RDebugUtils.currentLine=3473427;
 //BA.debugLineNum = 3473427;BA.debugLine="ChangeOffset(mLeftPanel.Left + dx, True, False";
__ref._changeoffset /*String*/ (null,(float) (__ref._mleftpanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .getLeft()+_dx),__c.True,__c.False);
 };
 break; }
case 1: {
RDebugUtils.currentLine=3473430;
 //BA.debugLineNum = 3473430;BA.debugLine="If HandlingSwipe Then";
if (__ref._handlingswipe /*boolean*/ ) { 
RDebugUtils.currentLine=3473431;
 //BA.debugLineNum = 3473431;BA.debugLine="ChangeOffset(mLeftPanel.Left, False, False)";
__ref._changeoffset /*String*/ (null,(float) (__ref._mleftpanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .getLeft()),__c.False,__c.False);
 };
RDebugUtils.currentLine=3473433;
 //BA.debugLineNum = 3473433;BA.debugLine="HandlingSwipe = False";
__ref._handlingswipe /*boolean*/  = __c.False;
 break; }
}
;
RDebugUtils.currentLine=3473435;
 //BA.debugLineNum = 3473435;BA.debugLine="Return True";
if (true) return __c.True;
RDebugUtils.currentLine=3473436;
 //BA.debugLineNum = 3473436;BA.debugLine="End Sub";
return false;
}
public String  _changeoffset(b4a.example.b4xdrawer __ref,float _x,boolean _currentlytouching,boolean _noanimation) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xdrawer";
if (Debug.shouldDelegate(ba, "changeoffset", true))
	 {return ((String) Debug.delegate(ba, "changeoffset", new Object[] {_x,_currentlytouching,_noanimation}));}
int _visibleoffset = 0;
int _dx = 0;
int _duration = 0;
RDebugUtils.currentLine=3604480;
 //BA.debugLineNum = 3604480;BA.debugLine="Private Sub ChangeOffset (x As Float, CurrentlyTou";
RDebugUtils.currentLine=3604481;
 //BA.debugLineNum = 3604481;BA.debugLine="x = Max(-mSideWidth, Min(0, x))";
_x = (float) (__c.Max(-__ref._msidewidth /*int*/ ,__c.Min(0,_x)));
RDebugUtils.currentLine=3604482;
 //BA.debugLineNum = 3604482;BA.debugLine="Dim VisibleOffset As Int = mSideWidth + x";
_visibleoffset = (int) (__ref._msidewidth /*int*/ +_x);
RDebugUtils.currentLine=3604491;
 //BA.debugLineNum = 3604491;BA.debugLine="If CurrentlyTouching = False Then";
if (_currentlytouching==__c.False) { 
RDebugUtils.currentLine=3604492;
 //BA.debugLineNum = 3604492;BA.debugLine="If (IsOpen And VisibleOffset < 0.8 * mSideWidth)";
if ((__ref._isopen /*boolean*/  && _visibleoffset<0.8*__ref._msidewidth /*int*/ ) || (__ref._isopen /*boolean*/ ==__c.False && _visibleoffset<0.2*__ref._msidewidth /*int*/ )) { 
RDebugUtils.currentLine=3604493;
 //BA.debugLineNum = 3604493;BA.debugLine="x = -mSideWidth";
_x = (float) (-__ref._msidewidth /*int*/ );
RDebugUtils.currentLine=3604494;
 //BA.debugLineNum = 3604494;BA.debugLine="SetIsOpen(False)";
__ref._setisopen /*String*/ (null,__c.False);
 }else {
RDebugUtils.currentLine=3604496;
 //BA.debugLineNum = 3604496;BA.debugLine="x = 0";
_x = (float) (0);
RDebugUtils.currentLine=3604497;
 //BA.debugLineNum = 3604497;BA.debugLine="SetIsOpen(True)";
__ref._setisopen /*String*/ (null,__c.True);
 };
RDebugUtils.currentLine=3604499;
 //BA.debugLineNum = 3604499;BA.debugLine="Dim dx As Int = Abs(mLeftPanel.Left - x)";
_dx = (int) (__c.Abs(__ref._mleftpanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .getLeft()-_x));
RDebugUtils.currentLine=3604500;
 //BA.debugLineNum = 3604500;BA.debugLine="Dim duration As Int = Max(0, 200 * dx / mSideWid";
_duration = (int) (__c.Max(0,200*_dx/(double)__ref._msidewidth /*int*/ ));
RDebugUtils.currentLine=3604501;
 //BA.debugLineNum = 3604501;BA.debugLine="If NoAnimation Then duration = 0";
if (_noanimation) { 
_duration = (int) (0);};
RDebugUtils.currentLine=3604502;
 //BA.debugLineNum = 3604502;BA.debugLine="mLeftPanel.SetLayoutAnimated(duration, x, 0, mLe";
__ref._mleftpanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .SetLayoutAnimated(_duration,(int) (_x),(int) (0),__ref._mleftpanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .getWidth(),__ref._mleftpanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .getHeight());
RDebugUtils.currentLine=3604503;
 //BA.debugLineNum = 3604503;BA.debugLine="mDarkPanel.SetColorAnimated(duration, mDarkPanel";
__ref._mdarkpanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .SetColorAnimated(_duration,__ref._mdarkpanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .getColor(),__ref._offsettocolor /*int*/ (null,(int) (_x)));
 }else {
RDebugUtils.currentLine=3604513;
 //BA.debugLineNum = 3604513;BA.debugLine="mDarkPanel.Color = OffsetToColor(x)";
__ref._mdarkpanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .setColor(__ref._offsettocolor /*int*/ (null,(int) (_x)));
RDebugUtils.currentLine=3604514;
 //BA.debugLineNum = 3604514;BA.debugLine="mLeftPanel.Left = x";
__ref._mleftpanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .setLeft((int) (_x));
 };
RDebugUtils.currentLine=3604516;
 //BA.debugLineNum = 3604516;BA.debugLine="End Sub";
return "";
}
public String  _setisopen(b4a.example.b4xdrawer __ref,boolean _newstate) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xdrawer";
if (Debug.shouldDelegate(ba, "setisopen", true))
	 {return ((String) Debug.delegate(ba, "setisopen", new Object[] {_newstate}));}
RDebugUtils.currentLine=3670016;
 //BA.debugLineNum = 3670016;BA.debugLine="Private Sub SetIsOpen (NewState As Boolean)";
RDebugUtils.currentLine=3670017;
 //BA.debugLineNum = 3670017;BA.debugLine="If IsOpen = NewState Then Return";
if (__ref._isopen /*boolean*/ ==_newstate) { 
if (true) return "";};
RDebugUtils.currentLine=3670018;
 //BA.debugLineNum = 3670018;BA.debugLine="IsOpen = NewState";
__ref._isopen /*boolean*/  = _newstate;
RDebugUtils.currentLine=3670019;
 //BA.debugLineNum = 3670019;BA.debugLine="If xui.SubExists(mCallBack, mEventName & \"_StateC";
if (__ref._xui /*anywheresoftware.b4a.objects.B4XViewWrapper.XUI*/ .SubExists(ba,__ref._mcallback /*Object*/ ,__ref._meventname /*String*/ +"_StateChanged",(int) (1))) { 
RDebugUtils.currentLine=3670020;
 //BA.debugLineNum = 3670020;BA.debugLine="CallSubDelayed2(mCallBack,  mEventName & \"_State";
__c.CallSubDelayed2(ba,__ref._mcallback /*Object*/ ,__ref._meventname /*String*/ +"_StateChanged",(Object)(__ref._isopen /*boolean*/ ));
 };
RDebugUtils.currentLine=3670022;
 //BA.debugLineNum = 3670022;BA.debugLine="End Sub";
return "";
}
public int  _offsettocolor(b4a.example.b4xdrawer __ref,int _x) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xdrawer";
if (Debug.shouldDelegate(ba, "offsettocolor", true))
	 {return ((Integer) Debug.delegate(ba, "offsettocolor", new Object[] {_x}));}
float _visible = 0f;
RDebugUtils.currentLine=3735552;
 //BA.debugLineNum = 3735552;BA.debugLine="Private Sub OffsetToColor (x As Int) As Int";
RDebugUtils.currentLine=3735553;
 //BA.debugLineNum = 3735553;BA.debugLine="Dim Visible As Float = (mSideWidth + x) / mSideWi";
_visible = (float) ((__ref._msidewidth /*int*/ +_x)/(double)__ref._msidewidth /*int*/ );
RDebugUtils.currentLine=3735554;
 //BA.debugLineNum = 3735554;BA.debugLine="Return xui.Color_ARGB(100 * Visible, 0, 0, 0)";
if (true) return __ref._xui /*anywheresoftware.b4a.objects.B4XViewWrapper.XUI*/ .Color_ARGB((int) (100*_visible),(int) (0),(int) (0),(int) (0));
RDebugUtils.currentLine=3735555;
 //BA.debugLineNum = 3735555;BA.debugLine="End Sub";
return 0;
}
public String  _class_globals(b4a.example.b4xdrawer __ref) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xdrawer";
RDebugUtils.currentLine=3276800;
 //BA.debugLineNum = 3276800;BA.debugLine="Sub Class_Globals";
RDebugUtils.currentLine=3276801;
 //BA.debugLineNum = 3276801;BA.debugLine="Private mEventName As String 'ignore";
_meventname = "";
RDebugUtils.currentLine=3276802;
 //BA.debugLineNum = 3276802;BA.debugLine="Private mCallBack As Object 'ignore";
_mcallback = new Object();
RDebugUtils.currentLine=3276803;
 //BA.debugLineNum = 3276803;BA.debugLine="Private xui As XUI 'ignore";
_xui = new anywheresoftware.b4a.objects.B4XViewWrapper.XUI();
RDebugUtils.currentLine=3276804;
 //BA.debugLineNum = 3276804;BA.debugLine="Private mSideWidth As Int";
_msidewidth = 0;
RDebugUtils.currentLine=3276805;
 //BA.debugLineNum = 3276805;BA.debugLine="Private mLeftPanel As B4XView";
_mleftpanel = new anywheresoftware.b4a.objects.B4XViewWrapper();
RDebugUtils.currentLine=3276806;
 //BA.debugLineNum = 3276806;BA.debugLine="Private mDarkPanel As B4XView";
_mdarkpanel = new anywheresoftware.b4a.objects.B4XViewWrapper();
RDebugUtils.currentLine=3276807;
 //BA.debugLineNum = 3276807;BA.debugLine="Private mBasePanel As B4XView";
_mbasepanel = new anywheresoftware.b4a.objects.B4XViewWrapper();
RDebugUtils.currentLine=3276808;
 //BA.debugLineNum = 3276808;BA.debugLine="Private mCenterPanel As B4XView";
_mcenterpanel = new anywheresoftware.b4a.objects.B4XViewWrapper();
RDebugUtils.currentLine=3276809;
 //BA.debugLineNum = 3276809;BA.debugLine="Public ExtraWidth As Int = 50dip";
_extrawidth = __c.DipToCurrent((int) (50));
RDebugUtils.currentLine=3276810;
 //BA.debugLineNum = 3276810;BA.debugLine="Private TouchXStart, TouchYStart As Float 'ignore";
_touchxstart = 0f;
_touchystart = 0f;
RDebugUtils.currentLine=3276811;
 //BA.debugLineNum = 3276811;BA.debugLine="Private IsOpen As Boolean";
_isopen = false;
RDebugUtils.currentLine=3276812;
 //BA.debugLineNum = 3276812;BA.debugLine="Private HandlingSwipe As Boolean";
_handlingswipe = false;
RDebugUtils.currentLine=3276813;
 //BA.debugLineNum = 3276813;BA.debugLine="Private StartAtScrim As Boolean 'ignore";
_startatscrim = false;
RDebugUtils.currentLine=3276814;
 //BA.debugLineNum = 3276814;BA.debugLine="Private mEnabled As Boolean = True";
_menabled = __c.True;
RDebugUtils.currentLine=3276815;
 //BA.debugLineNum = 3276815;BA.debugLine="End Sub";
return "";
}
public boolean  _getgestureenabled(b4a.example.b4xdrawer __ref) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xdrawer";
if (Debug.shouldDelegate(ba, "getgestureenabled", true))
	 {return ((Boolean) Debug.delegate(ba, "getgestureenabled", null));}
RDebugUtils.currentLine=4128768;
 //BA.debugLineNum = 4128768;BA.debugLine="Public Sub getGestureEnabled As Boolean";
RDebugUtils.currentLine=4128769;
 //BA.debugLineNum = 4128769;BA.debugLine="Return mEnabled";
if (true) return __ref._menabled /*boolean*/ ;
RDebugUtils.currentLine=4128770;
 //BA.debugLineNum = 4128770;BA.debugLine="End Sub";
return false;
}
public String  _leftpanel_click(b4a.example.b4xdrawer __ref) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xdrawer";
if (Debug.shouldDelegate(ba, "leftpanel_click", true))
	 {return ((String) Debug.delegate(ba, "leftpanel_click", null));}
RDebugUtils.currentLine=3407872;
 //BA.debugLineNum = 3407872;BA.debugLine="Private Sub LeftPanel_Click";
RDebugUtils.currentLine=3407874;
 //BA.debugLineNum = 3407874;BA.debugLine="End Sub";
return "";
}
public String  _resize(b4a.example.b4xdrawer __ref,int _width,int _height) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xdrawer";
if (Debug.shouldDelegate(ba, "resize", true))
	 {return ((String) Debug.delegate(ba, "resize", new Object[] {_width,_height}));}
RDebugUtils.currentLine=4063232;
 //BA.debugLineNum = 4063232;BA.debugLine="Public Sub Resize(Width As Int, Height As Int)";
RDebugUtils.currentLine=4063233;
 //BA.debugLineNum = 4063233;BA.debugLine="If IsOpen Then ChangeOffset(-mSideWidth, False, T";
if (__ref._isopen /*boolean*/ ) { 
__ref._changeoffset /*String*/ (null,(float) (-__ref._msidewidth /*int*/ ),__c.False,__c.True);};
RDebugUtils.currentLine=4063234;
 //BA.debugLineNum = 4063234;BA.debugLine="mBasePanel.SetLayoutAnimated(0, 0, 0, Width, Heig";
__ref._mbasepanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .SetLayoutAnimated((int) (0),(int) (0),(int) (0),_width,_height);
RDebugUtils.currentLine=4063235;
 //BA.debugLineNum = 4063235;BA.debugLine="mLeftPanel.SetLayoutAnimated(0, mLeftPanel.Left,";
__ref._mleftpanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .SetLayoutAnimated((int) (0),__ref._mleftpanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .getLeft(),(int) (0),__ref._mleftpanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .getWidth(),__ref._mbasepanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .getHeight());
RDebugUtils.currentLine=4063236;
 //BA.debugLineNum = 4063236;BA.debugLine="mDarkPanel.SetLayoutAnimated(0, 0, 0, Width, Heig";
__ref._mdarkpanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .SetLayoutAnimated((int) (0),(int) (0),(int) (0),_width,_height);
RDebugUtils.currentLine=4063237;
 //BA.debugLineNum = 4063237;BA.debugLine="mCenterPanel.SetLayoutAnimated(0, 0, 0, Width, He";
__ref._mcenterpanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .SetLayoutAnimated((int) (0),(int) (0),(int) (0),_width,_height);
RDebugUtils.currentLine=4063238;
 //BA.debugLineNum = 4063238;BA.debugLine="End Sub";
return "";
}
public String  _setgestureenabled(b4a.example.b4xdrawer __ref,boolean _b) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xdrawer";
if (Debug.shouldDelegate(ba, "setgestureenabled", true))
	 {return ((String) Debug.delegate(ba, "setgestureenabled", new Object[] {_b}));}
RDebugUtils.currentLine=4194304;
 //BA.debugLineNum = 4194304;BA.debugLine="Public Sub setGestureEnabled (b As Boolean)";
RDebugUtils.currentLine=4194305;
 //BA.debugLineNum = 4194305;BA.debugLine="mEnabled = b";
__ref._menabled /*boolean*/  = _b;
RDebugUtils.currentLine=4194306;
 //BA.debugLineNum = 4194306;BA.debugLine="End Sub";
return "";
}
}