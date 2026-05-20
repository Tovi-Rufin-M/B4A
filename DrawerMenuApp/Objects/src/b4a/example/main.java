package b4a.example;


import anywheresoftware.b4a.B4AMenuItem;
import android.app.Activity;
import android.os.Bundle;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.B4AActivity;
import anywheresoftware.b4a.ObjectWrapper;
import anywheresoftware.b4a.objects.ActivityWrapper;
import java.lang.reflect.InvocationTargetException;
import anywheresoftware.b4a.B4AUncaughtException;
import anywheresoftware.b4a.debug.*;
import java.lang.ref.WeakReference;

public class main extends Activity implements B4AActivity{
	public static main mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = false;
	public static final boolean includeTitle = false;
    public static WeakReference<Activity> previousOne;
    public static boolean dontPause;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        mostCurrent = this;
		if (processBA == null) {
			processBA = new BA(this.getApplicationContext(), null, null, "b4a.example", "b4a.example.main");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (main).");
				p.finish();
			}
		}
        processBA.setActivityPaused(true);
        processBA.runHook("oncreate", this, null);
		if (!includeTitle) {
        	this.getWindow().requestFeature(android.view.Window.FEATURE_NO_TITLE);
        }
        if (fullScreen) {
        	getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,   
        			android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
		
        processBA.sharedProcessBA.activityBA = null;
		layout = new BALayout(this);
		setContentView(layout);
		afterFirstLayout = false;
        WaitForLayout wl = new WaitForLayout();
        if (anywheresoftware.b4a.objects.ServiceHelper.StarterHelper.startFromActivity(this, processBA, wl, false))
		    BA.handler.postDelayed(wl, 5);

	}
	static class WaitForLayout implements Runnable {
		public void run() {
			if (afterFirstLayout)
				return;
			if (mostCurrent == null)
				return;
            
			if (mostCurrent.layout.getWidth() == 0) {
				BA.handler.postDelayed(this, 5);
				return;
			}
			mostCurrent.layout.getLayoutParams().height = mostCurrent.layout.getHeight();
			mostCurrent.layout.getLayoutParams().width = mostCurrent.layout.getWidth();
			afterFirstLayout = true;
			mostCurrent.afterFirstLayout();
		}
	}
	private void afterFirstLayout() {
        if (this != mostCurrent)
			return;
		activityBA = new BA(this, layout, processBA, "b4a.example", "b4a.example.main");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.example.main", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (main) Create " + (isFirst ? "(first time)" : "") + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (main) Resume **");
        processBA.raiseEvent(null, "activity_resume");
        if (android.os.Build.VERSION.SDK_INT >= 11) {
			try {
				android.app.Activity.class.getMethod("invalidateOptionsMenu").invoke(this,(Object[]) null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	public void addMenuItem(B4AMenuItem item) {
		if (menuItems == null)
			menuItems = new java.util.ArrayList<B4AMenuItem>();
		menuItems.add(item);
	}
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
        try {
            if (processBA.subExists("activity_actionbarhomeclick")) {
                Class.forName("android.app.ActionBar").getMethod("setHomeButtonEnabled", boolean.class).invoke(
                    getClass().getMethod("getActionBar").invoke(this), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (processBA.runHook("oncreateoptionsmenu", this, new Object[] {menu}))
            return true;
		if (menuItems == null)
			return false;
		for (B4AMenuItem bmi : menuItems) {
			android.view.MenuItem mi = menu.add(bmi.title);
			if (bmi.drawable != null)
				mi.setIcon(bmi.drawable);
            if (android.os.Build.VERSION.SDK_INT >= 11) {
				try {
                    if (bmi.addToBar) {
				        android.view.MenuItem.class.getMethod("setShowAsAction", int.class).invoke(mi, 1);
                    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mi.setOnMenuItemClickListener(new B4AMenuItemsClickListener(bmi.eventName.toLowerCase(BA.cul)));
		}
        
		return true;
	}   
 @Override
 public boolean onOptionsItemSelected(android.view.MenuItem item) {
    if (item.getItemId() == 16908332) {
        processBA.raiseEvent(null, "activity_actionbarhomeclick");
        return true;
    }
    else
        return super.onOptionsItemSelected(item); 
}
@Override
 public boolean onPrepareOptionsMenu(android.view.Menu menu) {
    super.onPrepareOptionsMenu(menu);
    processBA.runHook("onprepareoptionsmenu", this, new Object[] {menu});
    return true;
    
 }
 protected void onStart() {
    super.onStart();
    processBA.runHook("onstart", this, null);
}
 protected void onStop() {
    super.onStop();
    processBA.runHook("onstop", this, null);
}
    public void onWindowFocusChanged(boolean hasFocus) {
       super.onWindowFocusChanged(hasFocus);
       if (processBA.subExists("activity_windowfocuschanged"))
           processBA.raiseEvent2(null, true, "activity_windowfocuschanged", false, hasFocus);
    }
	private class B4AMenuItemsClickListener implements android.view.MenuItem.OnMenuItemClickListener {
		private final String eventName;
		public B4AMenuItemsClickListener(String eventName) {
			this.eventName = eventName;
		}
		public boolean onMenuItemClick(android.view.MenuItem item) {
			processBA.raiseEventFromUI(item.getTitle(), eventName + "_click");
			return true;
		}
	}
    public static Class<?> getObject() {
		return main.class;
	}
    private Boolean onKeySubExist = null;
    private Boolean onKeyUpSubExist = null;
	@Override
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeydown", this, new Object[] {keyCode, event}))
            return true;
		if (onKeySubExist == null)
			onKeySubExist = processBA.subExists("activity_keypress");
		if (onKeySubExist) {
			if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK &&
					android.os.Build.VERSION.SDK_INT >= 18) {
				HandleKeyDelayed hk = new HandleKeyDelayed();
				hk.kc = keyCode;
				BA.handler.post(hk);
				return true;
			}
			else {
				boolean res = new HandleKeyDelayed().runDirectly(keyCode);
				if (res)
					return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	private class HandleKeyDelayed implements Runnable {
		int kc;
		public void run() {
			runDirectly(kc);
		}
		public boolean runDirectly(int keyCode) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keypress", false, keyCode);
			if (res == null || res == true) {
                return true;
            }
            else if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK) {
				finish();
				return true;
			}
            return false;
		}
		
	}
    @Override
	public boolean onKeyUp(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeyup", this, new Object[] {keyCode, event}))
            return true;
		if (onKeyUpSubExist == null)
			onKeyUpSubExist = processBA.subExists("activity_keyup");
		if (onKeyUpSubExist) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keyup", false, keyCode);
			if (res == null || res == true)
				return true;
		}
		return super.onKeyUp(keyCode, event);
	}
	@Override
	public void onNewIntent(android.content.Intent intent) {
        super.onNewIntent(intent);
		this.setIntent(intent);
        processBA.runHook("onnewintent", this, new Object[] {intent});
	}
    @Override 
	public void onPause() {
		super.onPause();
        if (_activity == null)
            return;
        if (this != mostCurrent)
			return;
		anywheresoftware.b4a.Msgbox.dismiss(true);
        if (!dontPause)
            BA.LogInfo("** Activity (main) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (main) Pause event (activity is not paused). **");
        if (mostCurrent != null)
            processBA.raiseEvent2(_activity, true, "activity_pause", false, activityBA.activity.isFinishing());		
        if (!dontPause) {
            processBA.setActivityPaused(true);
            mostCurrent = null;
        }

        if (!activityBA.activity.isFinishing())
			previousOne = new WeakReference<Activity>(this);
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        processBA.runHook("onpause", this, null);
	}

	@Override
	public void onDestroy() {
        super.onDestroy();
		previousOne = null;
        processBA.runHook("ondestroy", this, null);
	}
    @Override 
	public void onResume() {
		super.onResume();
        mostCurrent = this;
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (activityBA != null) { //will be null during activity create (which waits for AfterLayout).
        	ResumeMessage rm = new ResumeMessage(mostCurrent);
        	BA.handler.post(rm);
        }
        processBA.runHook("onresume", this, null);
	}
    private static class ResumeMessage implements Runnable {
    	private final WeakReference<Activity> activity;
    	public ResumeMessage(Activity activity) {
    		this.activity = new WeakReference<Activity>(activity);
    	}
		public void run() {
            main mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (main) Resume **");
            if (mc != mostCurrent)
                return;
		    processBA.raiseEvent(mc._activity, "activity_resume", (Object[])null);
		}
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
	      android.content.Intent data) {
		processBA.onActivityResult(requestCode, resultCode, data);
        processBA.runHook("onactivityresult", this, new Object[] {requestCode, resultCode});
	}
	private static void initializeGlobals() {
		processBA.raiseEvent2(null, true, "globals", false, (Object[])null);
	}
    public void onRequestPermissionsResult(int requestCode,
        String permissions[], int[] grantResults) {
        for (int i = 0;i < permissions.length;i++) {
            Object[] o = new Object[] {permissions[i], grantResults[i] == 0};
            processBA.raiseEventFromDifferentThread(null,null, 0, "activity_permissionresult", true, o);
        }
            
    }

public anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4a.objects.B4XViewWrapper.XUI _xui = null;
public b4a.example.b4xdrawer _drawer = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnlmain = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnlmenu = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbltitle = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnhome = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnpage1 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnpage2 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnpage3 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnpage4 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnpage5 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnpage6 = null;
public anywheresoftware.b4a.objects.EditTextWrapper _edittext1 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label1 = null;
public anywheresoftware.b4a.objects.collections.List _items = null;
public anywheresoftware.b4a.objects.SpinnerWrapper _spinner1 = null;
public anywheresoftware.b4a.objects.SpinnerWrapper _spinner2 = null;
public anywheresoftware.b4a.objects.SpinnerWrapper _spinner3 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label3 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label6 = null;
public anywheresoftware.b4a.objects.EditTextWrapper _edittext2 = null;
public b4a.example.starter _starter = null;

public static boolean isAnyActivityVisible() {
    boolean vis = false;
vis = vis | (main.mostCurrent != null);
return vis;}
public static String  _activity_create(boolean _firsttime) throws Exception{
 //BA.debugLineNum = 41;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 42;BA.debugLine="Activity.LoadLayout(\"main\")";
mostCurrent._activity.LoadLayout("main",mostCurrent.activityBA);
 //BA.debugLineNum = 44;BA.debugLine="Drawer.Initialize(Me, \"Drawer\", Activity, 260dip)";
mostCurrent._drawer._initialize /*String*/ (mostCurrent.activityBA,main.getObject(),"Drawer",(anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._activity.getObject())),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (260)));
 //BA.debugLineNum = 45;BA.debugLine="Drawer.CenterPanel.BringToFront";
mostCurrent._drawer._getcenterpanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ ().BringToFront();
 //BA.debugLineNum = 46;BA.debugLine="Drawer.LeftPanel.BringToFront";
mostCurrent._drawer._getleftpanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ ().BringToFront();
 //BA.debugLineNum = 48;BA.debugLine="pnlMain = Drawer.CenterPanel";
mostCurrent._pnlmain = (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(mostCurrent._drawer._getcenterpanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ ().getObject()));
 //BA.debugLineNum = 49;BA.debugLine="pnlMenu = Drawer.LeftPanel";
mostCurrent._pnlmenu = (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(mostCurrent._drawer._getleftpanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ ().getObject()));
 //BA.debugLineNum = 51;BA.debugLine="SetGradient(pnlMenu, Colors.rgb(175, 71, 210), Co";
_setgradient(mostCurrent._pnlmenu,anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (175),(int) (71),(int) (210)),anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (38),(int) (53),(int) (93)));
 //BA.debugLineNum = 53;BA.debugLine="items.Initialize";
mostCurrent._items.Initialize();
 //BA.debugLineNum = 55;BA.debugLine="CreateMenu";
_createmenu();
 //BA.debugLineNum = 56;BA.debugLine="ShowHome";
_showhome();
 //BA.debugLineNum = 57;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 457;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 458;BA.debugLine="If KeyCode = KeyCodes.KEYCODE_BACK And Drawer.Lef";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK && mostCurrent._drawer._getleftopen /*boolean*/ ()) { 
 //BA.debugLineNum = 459;BA.debugLine="Drawer.LeftOpen = False";
mostCurrent._drawer._setleftopen /*boolean*/ (anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 460;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 }else if(_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK && anywheresoftware.b4a.keywords.Common.Not(mostCurrent._drawer._getleftopen /*boolean*/ ())) { 
 //BA.debugLineNum = 462;BA.debugLine="Drawer.LeftOpen = True";
mostCurrent._drawer._setleftopen /*boolean*/ (anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 463;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 466;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 467;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 70;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 72;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 66;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 68;BA.debugLine="End Sub";
return "";
}
public static String  _btnhome_click() throws Exception{
 //BA.debugLineNum = 143;BA.debugLine="Sub btnHome_Click";
 //BA.debugLineNum = 144;BA.debugLine="Drawer.LeftOpen = False";
mostCurrent._drawer._setleftopen /*boolean*/ (anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 145;BA.debugLine="ShowHome";
_showhome();
 //BA.debugLineNum = 146;BA.debugLine="End Sub";
return "";
}
public static String  _btnmenu_click() throws Exception{
 //BA.debugLineNum = 74;BA.debugLine="Sub btnMenu_Click";
 //BA.debugLineNum = 75;BA.debugLine="Drawer.LeftOpen = Not(Drawer.LeftOpen)";
mostCurrent._drawer._setleftopen /*boolean*/ (anywheresoftware.b4a.keywords.Common.Not(mostCurrent._drawer._getleftopen /*boolean*/ ()));
 //BA.debugLineNum = 76;BA.debugLine="End Sub";
return "";
}
public static String  _btnpage1_click() throws Exception{
 //BA.debugLineNum = 148;BA.debugLine="Sub btnPage1_Click";
 //BA.debugLineNum = 149;BA.debugLine="Drawer.LeftOpen = False";
mostCurrent._drawer._setleftopen /*boolean*/ (anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 150;BA.debugLine="ShowPage1";
_showpage1();
 //BA.debugLineNum = 151;BA.debugLine="End Sub";
return "";
}
public static String  _btnpage2_click() throws Exception{
 //BA.debugLineNum = 153;BA.debugLine="Sub btnPage2_Click";
 //BA.debugLineNum = 154;BA.debugLine="Drawer.LeftOpen = False";
mostCurrent._drawer._setleftopen /*boolean*/ (anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 155;BA.debugLine="ShowPage2";
_showpage2();
 //BA.debugLineNum = 156;BA.debugLine="End Sub";
return "";
}
public static String  _btnpage3_click() throws Exception{
 //BA.debugLineNum = 158;BA.debugLine="Sub btnPage3_Click";
 //BA.debugLineNum = 159;BA.debugLine="Drawer.LeftOpen = False";
mostCurrent._drawer._setleftopen /*boolean*/ (anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 160;BA.debugLine="ShowPage3";
_showpage3();
 //BA.debugLineNum = 161;BA.debugLine="End Sub";
return "";
}
public static String  _btnpage4_click() throws Exception{
 //BA.debugLineNum = 163;BA.debugLine="Sub btnPage4_Click";
 //BA.debugLineNum = 164;BA.debugLine="Drawer.LeftOpen = False";
mostCurrent._drawer._setleftopen /*boolean*/ (anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 165;BA.debugLine="ShowPage4";
_showpage4();
 //BA.debugLineNum = 166;BA.debugLine="End Sub";
return "";
}
public static String  _btnpage5_click() throws Exception{
 //BA.debugLineNum = 168;BA.debugLine="Sub btnPage5_Click";
 //BA.debugLineNum = 169;BA.debugLine="Drawer.LeftOpen = False";
mostCurrent._drawer._setleftopen /*boolean*/ (anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 170;BA.debugLine="ShowPage5";
_showpage5();
 //BA.debugLineNum = 171;BA.debugLine="End Sub";
return "";
}
public static String  _btnpage6_click() throws Exception{
 //BA.debugLineNum = 173;BA.debugLine="Sub btnPage6_Click";
 //BA.debugLineNum = 174;BA.debugLine="Drawer.LeftOpen = False";
mostCurrent._drawer._setleftopen /*boolean*/ (anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 175;BA.debugLine="ShowPage6";
_showpage6();
 //BA.debugLineNum = 176;BA.debugLine="End Sub";
return "";
}
public static String  _convert() throws Exception{
String _fromunit = "";
String _tounit = "";
String _result = "";
double _ans = 0;
double _input = 0;
String _choice = "";
anywheresoftware.b4a.objects.collections.Map _tometer = null;
anywheresoftware.b4a.objects.collections.Map _togram = null;
anywheresoftware.b4a.objects.collections.Map _toml = null;
anywheresoftware.b4a.objects.collections.Map _toms = null;
double _celsius = 0;
anywheresoftware.b4a.objects.collections.Map _tom2 = null;
 //BA.debugLineNum = 371;BA.debugLine="Sub convert";
 //BA.debugLineNum = 372;BA.debugLine="Dim fromUnit, toUnit As String";
_fromunit = "";
_tounit = "";
 //BA.debugLineNum = 373;BA.debugLine="Dim result As String";
_result = "";
 //BA.debugLineNum = 374;BA.debugLine="Dim ans As Double";
_ans = 0;
 //BA.debugLineNum = 375;BA.debugLine="Dim input As Double";
_input = 0;
 //BA.debugLineNum = 376;BA.debugLine="Dim choice As String = lblTitle.Text";
_choice = mostCurrent._lbltitle.getText();
 //BA.debugLineNum = 378;BA.debugLine="If EditText2.Text = \"\" Then";
if ((mostCurrent._edittext2.getText()).equals("")) { 
 //BA.debugLineNum = 379;BA.debugLine="input = 0";
_input = 0;
 }else {
 //BA.debugLineNum = 381;BA.debugLine="input = EditText2.Text";
_input = (double)(Double.parseDouble(mostCurrent._edittext2.getText()));
 };
 //BA.debugLineNum = 384;BA.debugLine="fromUnit = Spinner2.SelectedItem";
_fromunit = mostCurrent._spinner2.getSelectedItem();
 //BA.debugLineNum = 385;BA.debugLine="toUnit = Spinner3.SelectedItem";
_tounit = mostCurrent._spinner3.getSelectedItem();
 //BA.debugLineNum = 387;BA.debugLine="Select choice";
switch (BA.switchObjectToInt(_choice,"Length Converter","Weight Converter","Volume Converter","Speed Converter","Temperature Converter","Area Converter")) {
case 0: {
 //BA.debugLineNum = 389;BA.debugLine="Dim toMeter As Map";
_tometer = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 390;BA.debugLine="toMeter.Initialize";
_tometer.Initialize();
 //BA.debugLineNum = 391;BA.debugLine="toMeter.Put(\"mm\", 0.001)      : toMeter.Put(\"cm";
_tometer.Put((Object)("mm"),(Object)(0.001));
 //BA.debugLineNum = 391;BA.debugLine="toMeter.Put(\"mm\", 0.001)      : toMeter.Put(\"cm";
_tometer.Put((Object)("cm"),(Object)(0.01));
 //BA.debugLineNum = 392;BA.debugLine="toMeter.Put(\"m\", 1)           : toMeter.Put(\"km";
_tometer.Put((Object)("m"),(Object)(1));
 //BA.debugLineNum = 392;BA.debugLine="toMeter.Put(\"m\", 1)           : toMeter.Put(\"km";
_tometer.Put((Object)("km"),(Object)(1000));
 //BA.debugLineNum = 393;BA.debugLine="toMeter.Put(\"in\", 0.0254)     : toMeter.Put(\"ft";
_tometer.Put((Object)("in"),(Object)(0.0254));
 //BA.debugLineNum = 393;BA.debugLine="toMeter.Put(\"in\", 0.0254)     : toMeter.Put(\"ft";
_tometer.Put((Object)("ft"),(Object)(0.3048));
 //BA.debugLineNum = 394;BA.debugLine="toMeter.Put(\"yd\", 0.9144)     : toMeter.Put(\"mi";
_tometer.Put((Object)("yd"),(Object)(0.9144));
 //BA.debugLineNum = 394;BA.debugLine="toMeter.Put(\"yd\", 0.9144)     : toMeter.Put(\"mi";
_tometer.Put((Object)("mi"),(Object)(1609.344));
 //BA.debugLineNum = 395;BA.debugLine="toMeter.Put(\"µm\", 0.000001)";
_tometer.Put((Object)("µm"),(Object)(0.000001));
 //BA.debugLineNum = 396;BA.debugLine="ans = input * toMeter.Get(fromUnit) / toMeter.G";
_ans = _input*(double)(BA.ObjectToNumber(_tometer.Get((Object)(_fromunit))))/(double)(double)(BA.ObjectToNumber(_tometer.Get((Object)(_tounit))));
 break; }
case 1: {
 //BA.debugLineNum = 399;BA.debugLine="Dim toGram As Map";
_togram = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 400;BA.debugLine="toGram.Initialize";
_togram.Initialize();
 //BA.debugLineNum = 401;BA.debugLine="toGram.Put(\"µg\", 0.000001)    : toGram.Put(\"mg\"";
_togram.Put((Object)("µg"),(Object)(0.000001));
 //BA.debugLineNum = 401;BA.debugLine="toGram.Put(\"µg\", 0.000001)    : toGram.Put(\"mg\"";
_togram.Put((Object)("mg"),(Object)(0.001));
 //BA.debugLineNum = 402;BA.debugLine="toGram.Put(\"g\", 1)            : toGram.Put(\"kg\"";
_togram.Put((Object)("g"),(Object)(1));
 //BA.debugLineNum = 402;BA.debugLine="toGram.Put(\"g\", 1)            : toGram.Put(\"kg\"";
_togram.Put((Object)("kg"),(Object)(1000));
 //BA.debugLineNum = 403;BA.debugLine="toGram.Put(\"t\", 1000000)      : toGram.Put(\"oz\"";
_togram.Put((Object)("t"),(Object)(1000000));
 //BA.debugLineNum = 403;BA.debugLine="toGram.Put(\"t\", 1000000)      : toGram.Put(\"oz\"";
_togram.Put((Object)("oz"),(Object)(28.3495));
 //BA.debugLineNum = 404;BA.debugLine="toGram.Put(\"lb\", 453.592)     : toGram.Put(\"st\"";
_togram.Put((Object)("lb"),(Object)(453.592));
 //BA.debugLineNum = 404;BA.debugLine="toGram.Put(\"lb\", 453.592)     : toGram.Put(\"st\"";
_togram.Put((Object)("st"),(Object)(6350.29));
 //BA.debugLineNum = 405;BA.debugLine="toGram.Put(\"ct\", 0.2)         : toGram.Put(\"ton";
_togram.Put((Object)("ct"),(Object)(0.2));
 //BA.debugLineNum = 405;BA.debugLine="toGram.Put(\"ct\", 0.2)         : toGram.Put(\"ton";
_togram.Put((Object)("ton"),(Object)(907185));
 //BA.debugLineNum = 406;BA.debugLine="ans = input * toGram.Get(fromUnit) / toGram.Get";
_ans = _input*(double)(BA.ObjectToNumber(_togram.Get((Object)(_fromunit))))/(double)(double)(BA.ObjectToNumber(_togram.Get((Object)(_tounit))));
 break; }
case 2: {
 //BA.debugLineNum = 409;BA.debugLine="Dim toML As Map";
_toml = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 410;BA.debugLine="toML.Initialize";
_toml.Initialize();
 //BA.debugLineNum = 411;BA.debugLine="toML.Put(\"mL\", 1)             : toML.Put(\"L\", 1";
_toml.Put((Object)("mL"),(Object)(1));
 //BA.debugLineNum = 411;BA.debugLine="toML.Put(\"mL\", 1)             : toML.Put(\"L\", 1";
_toml.Put((Object)("L"),(Object)(1000));
 //BA.debugLineNum = 412;BA.debugLine="toML.Put(\"kL\", 1000000)       : toML.Put(\"cm³\",";
_toml.Put((Object)("kL"),(Object)(1000000));
 //BA.debugLineNum = 412;BA.debugLine="toML.Put(\"kL\", 1000000)       : toML.Put(\"cm³\",";
_toml.Put((Object)("cm³"),(Object)(1));
 //BA.debugLineNum = 413;BA.debugLine="toML.Put(\"m³\", 1000000)       : toML.Put(\"in³\",";
_toml.Put((Object)("m³"),(Object)(1000000));
 //BA.debugLineNum = 413;BA.debugLine="toML.Put(\"m³\", 1000000)       : toML.Put(\"in³\",";
_toml.Put((Object)("in³"),(Object)(16.3871));
 //BA.debugLineNum = 414;BA.debugLine="toML.Put(\"ft³\", 28316.8)";
_toml.Put((Object)("ft³"),(Object)(28316.8));
 //BA.debugLineNum = 415;BA.debugLine="ans = input * toML.Get(fromUnit) / toML.Get(toU";
_ans = _input*(double)(BA.ObjectToNumber(_toml.Get((Object)(_fromunit))))/(double)(double)(BA.ObjectToNumber(_toml.Get((Object)(_tounit))));
 break; }
case 3: {
 //BA.debugLineNum = 418;BA.debugLine="Dim toMS As Map";
_toms = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 419;BA.debugLine="toMS.Initialize";
_toms.Initialize();
 //BA.debugLineNum = 420;BA.debugLine="toMS.Put(\"m/s\", 1)            : toMS.Put(\"km/h\"";
_toms.Put((Object)("m/s"),(Object)(1));
 //BA.debugLineNum = 420;BA.debugLine="toMS.Put(\"m/s\", 1)            : toMS.Put(\"km/h\"";
_toms.Put((Object)("km/h"),(Object)(0.277778));
 //BA.debugLineNum = 421;BA.debugLine="toMS.Put(\"mph\", 0.44704)      : toMS.Put(\"knot\"";
_toms.Put((Object)("mph"),(Object)(0.44704));
 //BA.debugLineNum = 421;BA.debugLine="toMS.Put(\"mph\", 0.44704)      : toMS.Put(\"knot\"";
_toms.Put((Object)("knot"),(Object)(0.514444));
 //BA.debugLineNum = 422;BA.debugLine="toMS.Put(\"ft/s\", 0.3048)";
_toms.Put((Object)("ft/s"),(Object)(0.3048));
 //BA.debugLineNum = 423;BA.debugLine="ans = input * toMS.Get(fromUnit) / toMS.Get(toU";
_ans = _input*(double)(BA.ObjectToNumber(_toms.Get((Object)(_fromunit))))/(double)(double)(BA.ObjectToNumber(_toms.Get((Object)(_tounit))));
 break; }
case 4: {
 //BA.debugLineNum = 426;BA.debugLine="Dim celsius As Double";
_celsius = 0;
 //BA.debugLineNum = 427;BA.debugLine="Select fromUnit";
switch (BA.switchObjectToInt(_fromunit,"°C","°F","K","°R")) {
case 0: {
 //BA.debugLineNum = 428;BA.debugLine="Case \"°C\" : celsius = input";
_celsius = _input;
 break; }
case 1: {
 //BA.debugLineNum = 429;BA.debugLine="Case \"°F\" : celsius = (input - 32) / 1.8";
_celsius = (_input-32)/(double)1.8;
 break; }
case 2: {
 //BA.debugLineNum = 430;BA.debugLine="Case \"K\"  : celsius = input - 273.15";
_celsius = _input-273.15;
 break; }
case 3: {
 //BA.debugLineNum = 431;BA.debugLine="Case \"°R\" : celsius = (input - 491.67) / 1.8";
_celsius = (_input-491.67)/(double)1.8;
 break; }
}
;
 //BA.debugLineNum = 433;BA.debugLine="Select toUnit";
switch (BA.switchObjectToInt(_tounit,"°C","°F","K","°R")) {
case 0: {
 //BA.debugLineNum = 434;BA.debugLine="Case \"°C\" : ans = celsius";
_ans = _celsius;
 break; }
case 1: {
 //BA.debugLineNum = 435;BA.debugLine="Case \"°F\" : ans = (celsius * 1.8) + 32";
_ans = (_celsius*1.8)+32;
 break; }
case 2: {
 //BA.debugLineNum = 436;BA.debugLine="Case \"K\"  : ans = celsius + 273.15";
_ans = _celsius+273.15;
 break; }
case 3: {
 //BA.debugLineNum = 437;BA.debugLine="Case \"°R\" : ans = (celsius + 273.15) * 1.8";
_ans = (_celsius+273.15)*1.8;
 break; }
}
;
 break; }
case 5: {
 //BA.debugLineNum = 441;BA.debugLine="Dim toM2 As Map";
_tom2 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 442;BA.debugLine="toM2.Initialize";
_tom2.Initialize();
 //BA.debugLineNum = 443;BA.debugLine="toM2.Put(\"mm²\", 0.000001)     : toM2.Put(\"cm²\",";
_tom2.Put((Object)("mm²"),(Object)(0.000001));
 //BA.debugLineNum = 443;BA.debugLine="toM2.Put(\"mm²\", 0.000001)     : toM2.Put(\"cm²\",";
_tom2.Put((Object)("cm²"),(Object)(0.0001));
 //BA.debugLineNum = 444;BA.debugLine="toM2.Put(\"m²\", 1)             : toM2.Put(\"km²\",";
_tom2.Put((Object)("m²"),(Object)(1));
 //BA.debugLineNum = 444;BA.debugLine="toM2.Put(\"m²\", 1)             : toM2.Put(\"km²\",";
_tom2.Put((Object)("km²"),(Object)(1000000));
 //BA.debugLineNum = 445;BA.debugLine="toM2.Put(\"in²\", 0.00064516)   : toM2.Put(\"ft²\",";
_tom2.Put((Object)("in²"),(Object)(0.00064516));
 //BA.debugLineNum = 445;BA.debugLine="toM2.Put(\"in²\", 0.00064516)   : toM2.Put(\"ft²\",";
_tom2.Put((Object)("ft²"),(Object)(0.092903));
 //BA.debugLineNum = 446;BA.debugLine="toM2.Put(\"yd²\", 0.836127)";
_tom2.Put((Object)("yd²"),(Object)(0.836127));
 //BA.debugLineNum = 447;BA.debugLine="ans = input * toM2.Get(fromUnit) / toM2.Get(toU";
_ans = _input*(double)(BA.ObjectToNumber(_tom2.Get((Object)(_fromunit))))/(double)(double)(BA.ObjectToNumber(_tom2.Get((Object)(_tounit))));
 break; }
default: {
 //BA.debugLineNum = 450;BA.debugLine="ans = 0";
_ans = 0;
 break; }
}
;
 //BA.debugLineNum = 453;BA.debugLine="result = NumberFormat(ans, 1, 4) & \" \" & toUnit";
_result = anywheresoftware.b4a.keywords.Common.NumberFormat(_ans,(int) (1),(int) (4))+" "+_tounit;
 //BA.debugLineNum = 454;BA.debugLine="Label6.Text = result";
mostCurrent._label6.setText(BA.ObjectToCharSequence(_result));
 //BA.debugLineNum = 455;BA.debugLine="End Sub";
return "";
}
public static String  _createmenu() throws Exception{
anywheresoftware.b4a.objects.ButtonWrapper _b = null;
 //BA.debugLineNum = 78;BA.debugLine="Sub CreateMenu";
 //BA.debugLineNum = 79;BA.debugLine="Dim btnHome, btnPage1, btnPage2 As Button";
mostCurrent._btnhome = new anywheresoftware.b4a.objects.ButtonWrapper();
mostCurrent._btnpage1 = new anywheresoftware.b4a.objects.ButtonWrapper();
mostCurrent._btnpage2 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 80;BA.debugLine="btnHome.Initialize(\"btnHome\")";
mostCurrent._btnhome.Initialize(mostCurrent.activityBA,"btnHome");
 //BA.debugLineNum = 81;BA.debugLine="btnHome.Text = \"Home\"";
mostCurrent._btnhome.setText(BA.ObjectToCharSequence("Home"));
 //BA.debugLineNum = 82;BA.debugLine="btnPage1.Initialize(\"btnPage1\")";
mostCurrent._btnpage1.Initialize(mostCurrent.activityBA,"btnPage1");
 //BA.debugLineNum = 83;BA.debugLine="btnPage1.Text = \"Length Converter\"";
mostCurrent._btnpage1.setText(BA.ObjectToCharSequence("Length Converter"));
 //BA.debugLineNum = 84;BA.debugLine="btnPage2.Initialize(\"btnPage2\")";
mostCurrent._btnpage2.Initialize(mostCurrent.activityBA,"btnPage2");
 //BA.debugLineNum = 85;BA.debugLine="btnPage2.Text = \"Weight Converter\"";
mostCurrent._btnpage2.setText(BA.ObjectToCharSequence("Weight Converter"));
 //BA.debugLineNum = 86;BA.debugLine="btnPage3.Initialize(\"btnPage3\")";
mostCurrent._btnpage3.Initialize(mostCurrent.activityBA,"btnPage3");
 //BA.debugLineNum = 87;BA.debugLine="btnPage3.Text = \"Volume Converter\"";
mostCurrent._btnpage3.setText(BA.ObjectToCharSequence("Volume Converter"));
 //BA.debugLineNum = 88;BA.debugLine="btnPage4.Initialize(\"btnPage4\")";
mostCurrent._btnpage4.Initialize(mostCurrent.activityBA,"btnPage4");
 //BA.debugLineNum = 89;BA.debugLine="btnPage4.Text = \"Speed Converter\"";
mostCurrent._btnpage4.setText(BA.ObjectToCharSequence("Speed Converter"));
 //BA.debugLineNum = 90;BA.debugLine="btnPage5.Initialize(\"btnPage5\")";
mostCurrent._btnpage5.Initialize(mostCurrent.activityBA,"btnPage5");
 //BA.debugLineNum = 91;BA.debugLine="btnPage5.Text = \"Temperature Converter\"";
mostCurrent._btnpage5.setText(BA.ObjectToCharSequence("Temperature Converter"));
 //BA.debugLineNum = 92;BA.debugLine="btnPage6.Initialize(\"btnPage6\")";
mostCurrent._btnpage6.Initialize(mostCurrent.activityBA,"btnPage6");
 //BA.debugLineNum = 93;BA.debugLine="btnPage6.Text = \"Area Converter\"";
mostCurrent._btnpage6.setText(BA.ObjectToCharSequence("Area Converter"));
 //BA.debugLineNum = 95;BA.debugLine="For Each b As Button In Array(btnHome, btnPage1,";
_b = new anywheresoftware.b4a.objects.ButtonWrapper();
{
final Object[] group16 = new Object[]{(Object)(mostCurrent._btnhome.getObject()),(Object)(mostCurrent._btnpage1.getObject()),(Object)(mostCurrent._btnpage2.getObject()),(Object)(mostCurrent._btnpage3.getObject()),(Object)(mostCurrent._btnpage4.getObject()),(Object)(mostCurrent._btnpage5.getObject()),(Object)(mostCurrent._btnpage6.getObject())};
final int groupLen16 = group16.length
;int index16 = 0;
;
for (; index16 < groupLen16;index16++){
_b = (anywheresoftware.b4a.objects.ButtonWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ButtonWrapper(), (android.widget.Button)(group16[index16]));
 //BA.debugLineNum = 96;BA.debugLine="b.TextSize = 16";
_b.setTextSize((float) (16));
 //BA.debugLineNum = 97;BA.debugLine="b.Gravity = Gravity.LEFT + Gravity.CENTER_VERTIC";
_b.setGravity((int) (anywheresoftware.b4a.keywords.Common.Gravity.LEFT+anywheresoftware.b4a.keywords.Common.Gravity.CENTER_VERTICAL));
 //BA.debugLineNum = 98;BA.debugLine="b.Color = Colors.Transparent";
_b.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 99;BA.debugLine="b.TextColor = Colors.White";
_b.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 100;BA.debugLine="pnlMenu.AddView(b, 10dip, 0, 240dip, 50dip)";
mostCurrent._pnlmenu.AddView((android.view.View)(_b.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (240)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
 }
};
 //BA.debugLineNum = 102;BA.debugLine="btnHome.Top = 120dip";
mostCurrent._btnhome.setTop(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (120)));
 //BA.debugLineNum = 103;BA.debugLine="btnPage1.Top = 180dip";
mostCurrent._btnpage1.setTop(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (180)));
 //BA.debugLineNum = 104;BA.debugLine="btnPage2.Top = 240dip";
mostCurrent._btnpage2.setTop(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (240)));
 //BA.debugLineNum = 105;BA.debugLine="btnPage3.Top = 300dip";
mostCurrent._btnpage3.setTop(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (300)));
 //BA.debugLineNum = 106;BA.debugLine="btnPage4.Top = 360dip";
mostCurrent._btnpage4.setTop(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (360)));
 //BA.debugLineNum = 107;BA.debugLine="btnPage5.Top = 420dip";
mostCurrent._btnpage5.setTop(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (420)));
 //BA.debugLineNum = 108;BA.debugLine="btnPage6.Top = 480dip";
mostCurrent._btnpage6.setTop(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (480)));
 //BA.debugLineNum = 109;BA.debugLine="End Sub";
return "";
}
public static String  _edittext1_enterpressed() throws Exception{
 //BA.debugLineNum = 485;BA.debugLine="Private Sub EditText1_EnterPressed";
 //BA.debugLineNum = 486;BA.debugLine="qick";
_qick();
 //BA.debugLineNum = 487;BA.debugLine="End Sub";
return "";
}
public static String  _edittext2_enterpressed() throws Exception{
 //BA.debugLineNum = 481;BA.debugLine="Private Sub EditText2_EnterPressed";
 //BA.debugLineNum = 482;BA.debugLine="convert";
_convert();
 //BA.debugLineNum = 483;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 21;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 23;BA.debugLine="Private Drawer As B4XDrawer";
mostCurrent._drawer = new b4a.example.b4xdrawer();
 //BA.debugLineNum = 24;BA.debugLine="Private pnlMain As Panel";
mostCurrent._pnlmain = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 25;BA.debugLine="Private pnlMenu As Panel";
mostCurrent._pnlmenu = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 28;BA.debugLine="Private lblTitle As Label";
mostCurrent._lbltitle = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Private btnHome, btnPage1, btnPage2, btnPage3, bt";
mostCurrent._btnhome = new anywheresoftware.b4a.objects.ButtonWrapper();
mostCurrent._btnpage1 = new anywheresoftware.b4a.objects.ButtonWrapper();
mostCurrent._btnpage2 = new anywheresoftware.b4a.objects.ButtonWrapper();
mostCurrent._btnpage3 = new anywheresoftware.b4a.objects.ButtonWrapper();
mostCurrent._btnpage4 = new anywheresoftware.b4a.objects.ButtonWrapper();
mostCurrent._btnpage5 = new anywheresoftware.b4a.objects.ButtonWrapper();
mostCurrent._btnpage6 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 31;BA.debugLine="Private EditText1 As EditText";
mostCurrent._edittext1 = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 32;BA.debugLine="Private Label1 As Label";
mostCurrent._label1 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 34;BA.debugLine="Private items As List";
mostCurrent._items = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 35;BA.debugLine="Private Spinner1, Spinner2, Spinner3 As Spinner";
mostCurrent._spinner1 = new anywheresoftware.b4a.objects.SpinnerWrapper();
mostCurrent._spinner2 = new anywheresoftware.b4a.objects.SpinnerWrapper();
mostCurrent._spinner3 = new anywheresoftware.b4a.objects.SpinnerWrapper();
 //BA.debugLineNum = 36;BA.debugLine="Private Label3 As Label";
mostCurrent._label3 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 37;BA.debugLine="Private Label6 As Label";
mostCurrent._label6 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 38;BA.debugLine="Private EditText2 As EditText";
mostCurrent._edittext2 = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 39;BA.debugLine="End Sub";
return "";
}
public static String  _pnl1_click() throws Exception{
 //BA.debugLineNum = 138;BA.debugLine="Private Sub pnl1_Click";
 //BA.debugLineNum = 139;BA.debugLine="Drawer.LeftOpen = False";
mostCurrent._drawer._setleftopen /*boolean*/ (anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 140;BA.debugLine="ShowPage1";
_showpage1();
 //BA.debugLineNum = 141;BA.debugLine="End Sub";
return "";
}
public static String  _pnl2_click() throws Exception{
 //BA.debugLineNum = 133;BA.debugLine="Private Sub pnl2_Click";
 //BA.debugLineNum = 134;BA.debugLine="Drawer.LeftOpen = False";
mostCurrent._drawer._setleftopen /*boolean*/ (anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 135;BA.debugLine="ShowPage2";
_showpage2();
 //BA.debugLineNum = 136;BA.debugLine="End Sub";
return "";
}
public static String  _pnl3_click() throws Exception{
 //BA.debugLineNum = 128;BA.debugLine="Private Sub pnl3_Click";
 //BA.debugLineNum = 129;BA.debugLine="Drawer.LeftOpen = False";
mostCurrent._drawer._setleftopen /*boolean*/ (anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 130;BA.debugLine="ShowPage3";
_showpage3();
 //BA.debugLineNum = 131;BA.debugLine="End Sub";
return "";
}
public static String  _pnl4_click() throws Exception{
 //BA.debugLineNum = 123;BA.debugLine="Private Sub pnl4_Click";
 //BA.debugLineNum = 124;BA.debugLine="Drawer.LeftOpen = False";
mostCurrent._drawer._setleftopen /*boolean*/ (anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 125;BA.debugLine="ShowPage4";
_showpage4();
 //BA.debugLineNum = 126;BA.debugLine="End Sub";
return "";
}
public static String  _pnl5_click() throws Exception{
 //BA.debugLineNum = 118;BA.debugLine="Private Sub pnl5_Click";
 //BA.debugLineNum = 119;BA.debugLine="Drawer.LeftOpen = False";
mostCurrent._drawer._setleftopen /*boolean*/ (anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 120;BA.debugLine="ShowPage5";
_showpage5();
 //BA.debugLineNum = 121;BA.debugLine="End Sub";
return "";
}
public static String  _pnl6_click() throws Exception{
 //BA.debugLineNum = 113;BA.debugLine="Private Sub pnl6_Click";
 //BA.debugLineNum = 114;BA.debugLine="Drawer.LeftOpen = False";
mostCurrent._drawer._setleftopen /*boolean*/ (anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 115;BA.debugLine="ShowPage6";
_showpage6();
 //BA.debugLineNum = 116;BA.debugLine="End Sub";
return "";
}

public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        main._process_globals();
starter._process_globals();
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 15;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 18;BA.debugLine="Private xui As XUI";
_xui = new anywheresoftware.b4a.objects.B4XViewWrapper.XUI();
 //BA.debugLineNum = 19;BA.debugLine="End Sub";
return "";
}
public static String  _qick() throws Exception{
String _spin = "";
String _choice = "";
double _firstinput = 0;
String _result = "";
String[] _parts = null;
String _fromunit = "";
String _tounit = "";
double _ans = 0;
anywheresoftware.b4a.objects.collections.Map _tometer = null;
anywheresoftware.b4a.objects.collections.Map _togram = null;
anywheresoftware.b4a.objects.collections.Map _toml = null;
anywheresoftware.b4a.objects.collections.Map _toms = null;
double _celsius = 0;
anywheresoftware.b4a.objects.collections.Map _tom2 = null;
 //BA.debugLineNum = 264;BA.debugLine="Sub qick";
 //BA.debugLineNum = 265;BA.debugLine="Dim spin As String = Spinner1.SelectedItem";
_spin = mostCurrent._spinner1.getSelectedItem();
 //BA.debugLineNum = 266;BA.debugLine="Dim choice As String = lblTitle.Text";
_choice = mostCurrent._lbltitle.getText();
 //BA.debugLineNum = 267;BA.debugLine="Dim firstinput As Double";
_firstinput = 0;
 //BA.debugLineNum = 268;BA.debugLine="If EditText1.Text = \"\" Then";
if ((mostCurrent._edittext1.getText()).equals("")) { 
 //BA.debugLineNum = 269;BA.debugLine="firstinput = 0";
_firstinput = 0;
 }else {
 //BA.debugLineNum = 271;BA.debugLine="firstinput = EditText1.Text";
_firstinput = (double)(Double.parseDouble(mostCurrent._edittext1.getText()));
 };
 //BA.debugLineNum = 273;BA.debugLine="Dim result As String";
_result = "";
 //BA.debugLineNum = 274;BA.debugLine="Dim parts() As String = Regex.Split(\" to \", spin)";
_parts = anywheresoftware.b4a.keywords.Common.Regex.Split(" to ",_spin);
 //BA.debugLineNum = 275;BA.debugLine="Dim fromUnit As String = parts(0)";
_fromunit = _parts[(int) (0)];
 //BA.debugLineNum = 276;BA.debugLine="Dim toUnit As String = parts(1)";
_tounit = _parts[(int) (1)];
 //BA.debugLineNum = 277;BA.debugLine="Dim ans As Double";
_ans = 0;
 //BA.debugLineNum = 279;BA.debugLine="Select choice";
switch (BA.switchObjectToInt(_choice,"Length Converter","Weight Converter","Volume Converter","Speed Converter","Temperature Converter","Area Converter")) {
case 0: {
 //BA.debugLineNum = 281;BA.debugLine="Dim toMeter As Map";
_tometer = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 282;BA.debugLine="toMeter.Initialize";
_tometer.Initialize();
 //BA.debugLineNum = 283;BA.debugLine="toMeter.Put(\"mm\", 0.001)";
_tometer.Put((Object)("mm"),(Object)(0.001));
 //BA.debugLineNum = 284;BA.debugLine="toMeter.Put(\"cm\", 0.01)";
_tometer.Put((Object)("cm"),(Object)(0.01));
 //BA.debugLineNum = 285;BA.debugLine="toMeter.Put(\"m\", 1)";
_tometer.Put((Object)("m"),(Object)(1));
 //BA.debugLineNum = 286;BA.debugLine="toMeter.Put(\"km\", 1000)";
_tometer.Put((Object)("km"),(Object)(1000));
 //BA.debugLineNum = 287;BA.debugLine="toMeter.Put(\"in\", 0.0254)";
_tometer.Put((Object)("in"),(Object)(0.0254));
 //BA.debugLineNum = 288;BA.debugLine="toMeter.Put(\"ft\", 0.3048)";
_tometer.Put((Object)("ft"),(Object)(0.3048));
 //BA.debugLineNum = 289;BA.debugLine="toMeter.Put(\"yd\", 0.9144)";
_tometer.Put((Object)("yd"),(Object)(0.9144));
 //BA.debugLineNum = 290;BA.debugLine="toMeter.Put(\"mi\", 1609.344)";
_tometer.Put((Object)("mi"),(Object)(1609.344));
 //BA.debugLineNum = 291;BA.debugLine="toMeter.Put(\"µm\", 0.000001)";
_tometer.Put((Object)("µm"),(Object)(0.000001));
 //BA.debugLineNum = 292;BA.debugLine="ans = firstinput * toMeter.Get(fromUnit) / toMe";
_ans = _firstinput*(double)(BA.ObjectToNumber(_tometer.Get((Object)(_fromunit))))/(double)(double)(BA.ObjectToNumber(_tometer.Get((Object)(_tounit))));
 //BA.debugLineNum = 293;BA.debugLine="result = NumberFormat(ans, 1, 4) & \" \" & toUnit";
_result = anywheresoftware.b4a.keywords.Common.NumberFormat(_ans,(int) (1),(int) (4))+" "+_tounit;
 break; }
case 1: {
 //BA.debugLineNum = 296;BA.debugLine="Dim toGram As Map";
_togram = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 297;BA.debugLine="toGram.Initialize";
_togram.Initialize();
 //BA.debugLineNum = 298;BA.debugLine="toGram.Put(\"µg\", 0.000001)";
_togram.Put((Object)("µg"),(Object)(0.000001));
 //BA.debugLineNum = 299;BA.debugLine="toGram.Put(\"mg\", 0.001)";
_togram.Put((Object)("mg"),(Object)(0.001));
 //BA.debugLineNum = 300;BA.debugLine="toGram.Put(\"g\", 1)";
_togram.Put((Object)("g"),(Object)(1));
 //BA.debugLineNum = 301;BA.debugLine="toGram.Put(\"kg\", 1000)";
_togram.Put((Object)("kg"),(Object)(1000));
 //BA.debugLineNum = 302;BA.debugLine="toGram.Put(\"t\", 1000000)";
_togram.Put((Object)("t"),(Object)(1000000));
 //BA.debugLineNum = 303;BA.debugLine="toGram.Put(\"oz\", 28.3495)";
_togram.Put((Object)("oz"),(Object)(28.3495));
 //BA.debugLineNum = 304;BA.debugLine="toGram.Put(\"lb\", 453.592)";
_togram.Put((Object)("lb"),(Object)(453.592));
 //BA.debugLineNum = 305;BA.debugLine="toGram.Put(\"st\", 6350.29)";
_togram.Put((Object)("st"),(Object)(6350.29));
 //BA.debugLineNum = 306;BA.debugLine="toGram.Put(\"ct\", 0.2)";
_togram.Put((Object)("ct"),(Object)(0.2));
 //BA.debugLineNum = 307;BA.debugLine="toGram.Put(\"ton\", 907185)";
_togram.Put((Object)("ton"),(Object)(907185));
 //BA.debugLineNum = 308;BA.debugLine="ans = firstinput * toGram.Get(fromUnit) / toGra";
_ans = _firstinput*(double)(BA.ObjectToNumber(_togram.Get((Object)(_fromunit))))/(double)(double)(BA.ObjectToNumber(_togram.Get((Object)(_tounit))));
 //BA.debugLineNum = 309;BA.debugLine="result = NumberFormat(ans, 1, 4) & \" \" & toUnit";
_result = anywheresoftware.b4a.keywords.Common.NumberFormat(_ans,(int) (1),(int) (4))+" "+_tounit;
 break; }
case 2: {
 //BA.debugLineNum = 312;BA.debugLine="Dim toML As Map";
_toml = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 313;BA.debugLine="toML.Initialize";
_toml.Initialize();
 //BA.debugLineNum = 314;BA.debugLine="toML.Put(\"mL\", 1)";
_toml.Put((Object)("mL"),(Object)(1));
 //BA.debugLineNum = 315;BA.debugLine="toML.Put(\"L\", 1000)";
_toml.Put((Object)("L"),(Object)(1000));
 //BA.debugLineNum = 316;BA.debugLine="toML.Put(\"kL\", 1000000)";
_toml.Put((Object)("kL"),(Object)(1000000));
 //BA.debugLineNum = 317;BA.debugLine="toML.Put(\"cm³\", 1)";
_toml.Put((Object)("cm³"),(Object)(1));
 //BA.debugLineNum = 318;BA.debugLine="toML.Put(\"m³\", 1000000)";
_toml.Put((Object)("m³"),(Object)(1000000));
 //BA.debugLineNum = 319;BA.debugLine="toML.Put(\"in³\", 16.3871)";
_toml.Put((Object)("in³"),(Object)(16.3871));
 //BA.debugLineNum = 320;BA.debugLine="toML.Put(\"ft³\", 28316.8)";
_toml.Put((Object)("ft³"),(Object)(28316.8));
 //BA.debugLineNum = 321;BA.debugLine="ans = firstinput * toML.Get(fromUnit) / toML.Ge";
_ans = _firstinput*(double)(BA.ObjectToNumber(_toml.Get((Object)(_fromunit))))/(double)(double)(BA.ObjectToNumber(_toml.Get((Object)(_tounit))));
 //BA.debugLineNum = 322;BA.debugLine="result = NumberFormat(ans, 1, 4) & \" \" & toUnit";
_result = anywheresoftware.b4a.keywords.Common.NumberFormat(_ans,(int) (1),(int) (4))+" "+_tounit;
 break; }
case 3: {
 //BA.debugLineNum = 325;BA.debugLine="Dim toMS As Map";
_toms = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 326;BA.debugLine="toMS.Initialize";
_toms.Initialize();
 //BA.debugLineNum = 327;BA.debugLine="toMS.Put(\"m/s\", 1)";
_toms.Put((Object)("m/s"),(Object)(1));
 //BA.debugLineNum = 328;BA.debugLine="toMS.Put(\"km/h\", 0.277778)";
_toms.Put((Object)("km/h"),(Object)(0.277778));
 //BA.debugLineNum = 329;BA.debugLine="toMS.Put(\"mph\", 0.44704)";
_toms.Put((Object)("mph"),(Object)(0.44704));
 //BA.debugLineNum = 330;BA.debugLine="toMS.Put(\"knot\", 0.514444)";
_toms.Put((Object)("knot"),(Object)(0.514444));
 //BA.debugLineNum = 331;BA.debugLine="toMS.Put(\"ft/s\", 0.3048)";
_toms.Put((Object)("ft/s"),(Object)(0.3048));
 //BA.debugLineNum = 332;BA.debugLine="ans = firstinput * toMS.Get(fromUnit) / toMS.Ge";
_ans = _firstinput*(double)(BA.ObjectToNumber(_toms.Get((Object)(_fromunit))))/(double)(double)(BA.ObjectToNumber(_toms.Get((Object)(_tounit))));
 //BA.debugLineNum = 333;BA.debugLine="result = NumberFormat(ans, 1, 4) & \" \" & toUnit";
_result = anywheresoftware.b4a.keywords.Common.NumberFormat(_ans,(int) (1),(int) (4))+" "+_tounit;
 break; }
case 4: {
 //BA.debugLineNum = 336;BA.debugLine="Dim celsius As Double";
_celsius = 0;
 //BA.debugLineNum = 337;BA.debugLine="Select fromUnit";
switch (BA.switchObjectToInt(_fromunit,"°C","°F","K","°R")) {
case 0: {
 //BA.debugLineNum = 338;BA.debugLine="Case \"°C\" : celsius = firstinput";
_celsius = _firstinput;
 break; }
case 1: {
 //BA.debugLineNum = 339;BA.debugLine="Case \"°F\" : celsius = (firstinput - 32) / 1.8";
_celsius = (_firstinput-32)/(double)1.8;
 break; }
case 2: {
 //BA.debugLineNum = 340;BA.debugLine="Case \"K\"  : celsius = firstinput - 273.15";
_celsius = _firstinput-273.15;
 break; }
case 3: {
 //BA.debugLineNum = 341;BA.debugLine="Case \"°R\" : celsius = (firstinput - 491.67) /";
_celsius = (_firstinput-491.67)/(double)1.8;
 break; }
}
;
 //BA.debugLineNum = 343;BA.debugLine="Select toUnit";
switch (BA.switchObjectToInt(_tounit,"°C","°F","K","°R")) {
case 0: {
 //BA.debugLineNum = 344;BA.debugLine="Case \"°C\" : ans = celsius";
_ans = _celsius;
 break; }
case 1: {
 //BA.debugLineNum = 345;BA.debugLine="Case \"°F\" : ans = (celsius * 1.8) + 32";
_ans = (_celsius*1.8)+32;
 break; }
case 2: {
 //BA.debugLineNum = 346;BA.debugLine="Case \"K\"  : ans = celsius + 273.15";
_ans = _celsius+273.15;
 break; }
case 3: {
 //BA.debugLineNum = 347;BA.debugLine="Case \"°R\" : ans = (celsius + 273.15) * 1.8";
_ans = (_celsius+273.15)*1.8;
 break; }
}
;
 //BA.debugLineNum = 349;BA.debugLine="result = NumberFormat(ans, 1, 4) & \" \" & toUnit";
_result = anywheresoftware.b4a.keywords.Common.NumberFormat(_ans,(int) (1),(int) (4))+" "+_tounit;
 break; }
case 5: {
 //BA.debugLineNum = 352;BA.debugLine="Dim toM2 As Map";
_tom2 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 353;BA.debugLine="toM2.Initialize";
_tom2.Initialize();
 //BA.debugLineNum = 354;BA.debugLine="toM2.Put(\"mm²\", 0.000001)";
_tom2.Put((Object)("mm²"),(Object)(0.000001));
 //BA.debugLineNum = 355;BA.debugLine="toM2.Put(\"cm²\", 0.0001)";
_tom2.Put((Object)("cm²"),(Object)(0.0001));
 //BA.debugLineNum = 356;BA.debugLine="toM2.Put(\"m²\", 1)";
_tom2.Put((Object)("m²"),(Object)(1));
 //BA.debugLineNum = 357;BA.debugLine="toM2.Put(\"km²\", 1000000)";
_tom2.Put((Object)("km²"),(Object)(1000000));
 //BA.debugLineNum = 358;BA.debugLine="toM2.Put(\"in²\", 0.00064516)";
_tom2.Put((Object)("in²"),(Object)(0.00064516));
 //BA.debugLineNum = 359;BA.debugLine="toM2.Put(\"ft²\", 0.092903)";
_tom2.Put((Object)("ft²"),(Object)(0.092903));
 //BA.debugLineNum = 360;BA.debugLine="toM2.Put(\"yd²\", 0.836127)";
_tom2.Put((Object)("yd²"),(Object)(0.836127));
 //BA.debugLineNum = 361;BA.debugLine="ans = firstinput * toM2.Get(fromUnit) / toM2.Ge";
_ans = _firstinput*(double)(BA.ObjectToNumber(_tom2.Get((Object)(_fromunit))))/(double)(double)(BA.ObjectToNumber(_tom2.Get((Object)(_tounit))));
 //BA.debugLineNum = 362;BA.debugLine="result = NumberFormat(ans, 1, 4) & \" \" & toUnit";
_result = anywheresoftware.b4a.keywords.Common.NumberFormat(_ans,(int) (1),(int) (4))+" "+_tounit;
 break; }
default: {
 //BA.debugLineNum = 365;BA.debugLine="result = \"0\"";
_result = "0";
 break; }
}
;
 //BA.debugLineNum = 368;BA.debugLine="Label3.Text = result";
mostCurrent._label3.setText(BA.ObjectToCharSequence(_result));
 //BA.debugLineNum = 369;BA.debugLine="End Sub";
return "";
}
public static String  _setgradient(anywheresoftware.b4a.objects.PanelWrapper _panel,int _color1,int _color2) throws Exception{
anywheresoftware.b4a.objects.drawable.GradientDrawable _gd = null;
 //BA.debugLineNum = 60;BA.debugLine="Sub SetGradient(Panel As Panel, Color1 As Int, Col";
 //BA.debugLineNum = 61;BA.debugLine="Dim gd As GradientDrawable";
_gd = new anywheresoftware.b4a.objects.drawable.GradientDrawable();
 //BA.debugLineNum = 62;BA.debugLine="gd.Initialize(\"BR_TL\", Array As Int(Color1, Color";
_gd.Initialize(BA.getEnumFromString(android.graphics.drawable.GradientDrawable.Orientation.class,"BR_TL"),new int[]{_color1,_color2});
 //BA.debugLineNum = 63;BA.debugLine="Panel.Background = gd";
_panel.setBackground((android.graphics.drawable.Drawable)(_gd.getObject()));
 //BA.debugLineNum = 64;BA.debugLine="End Sub";
return "";
}
public static String  _setspinner(anywheresoftware.b4a.objects.collections.List _item,Object _color) throws Exception{
anywheresoftware.b4a.objects.SpinnerWrapper _s = null;
anywheresoftware.b4a.objects.collections.List _pairitems = null;
int _i = 0;
anywheresoftware.b4a.objects.drawable.ColorDrawable _cd = null;
 //BA.debugLineNum = 186;BA.debugLine="Sub setspinner(item As List, color As Object)";
 //BA.debugLineNum = 187;BA.debugLine="Spinner2.AddAll(item)";
mostCurrent._spinner2.AddAll(_item);
 //BA.debugLineNum = 188;BA.debugLine="Spinner3.AddAll(item)";
mostCurrent._spinner3.AddAll(_item);
 //BA.debugLineNum = 189;BA.debugLine="For Each s As Spinner In Array(Spinner1, Spinner2";
_s = new anywheresoftware.b4a.objects.SpinnerWrapper();
{
final Object[] group3 = new Object[]{(Object)(mostCurrent._spinner1.getObject()),(Object)(mostCurrent._spinner2.getObject()),(Object)(mostCurrent._spinner3.getObject())};
final int groupLen3 = group3.length
;int index3 = 0;
;
for (; index3 < groupLen3;index3++){
_s = (anywheresoftware.b4a.objects.SpinnerWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.SpinnerWrapper(), (anywheresoftware.b4a.objects.SpinnerWrapper.B4ASpinner)(group3[index3]));
 //BA.debugLineNum = 190;BA.debugLine="s.TextSize = 14";
_s.setTextSize((float) (14));
 //BA.debugLineNum = 191;BA.debugLine="Dim pairItems As List";
_pairitems = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 192;BA.debugLine="pairItems.Initialize";
_pairitems.Initialize();
 //BA.debugLineNum = 193;BA.debugLine="For i = 0 To item.Size - 2";
{
final int step7 = 1;
final int limit7 = (int) (_item.getSize()-2);
_i = (int) (0) ;
for (;_i <= limit7 ;_i = _i + step7 ) {
 //BA.debugLineNum = 194;BA.debugLine="pairItems.Add(item.Get(i) & \" to \" & item.Get(i";
_pairitems.Add((Object)(BA.ObjectToString(_item.Get(_i))+" to "+BA.ObjectToString(_item.Get((int) (_i+1)))));
 }
};
 //BA.debugLineNum = 197;BA.debugLine="Dim cd As ColorDrawable";
_cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 198;BA.debugLine="cd.Initialize(color, 10dip)";
_cd.Initialize((int)(BA.ObjectToNumber(_color)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)));
 //BA.debugLineNum = 199;BA.debugLine="s.Background = cd";
_s.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
 }
};
 //BA.debugLineNum = 201;BA.debugLine="Spinner1.AddAll(pairItems)";
mostCurrent._spinner1.AddAll(_pairitems);
 //BA.debugLineNum = 202;BA.debugLine="End Sub";
return "";
}
public static String  _showhome() throws Exception{
 //BA.debugLineNum = 180;BA.debugLine="Sub ShowHome";
 //BA.debugLineNum = 181;BA.debugLine="pnlMain.RemoveAllViews";
mostCurrent._pnlmain.RemoveAllViews();
 //BA.debugLineNum = 182;BA.debugLine="pnlMain.LoadLayout(\"home\")";
mostCurrent._pnlmain.LoadLayout("home",mostCurrent.activityBA);
 //BA.debugLineNum = 183;BA.debugLine="lblTitle.Text = \"Home\"";
mostCurrent._lbltitle.setText(BA.ObjectToCharSequence("Home"));
 //BA.debugLineNum = 184;BA.debugLine="End Sub";
return "";
}
public static String  _showpage1() throws Exception{
 //BA.debugLineNum = 204;BA.debugLine="Sub ShowPage1";
 //BA.debugLineNum = 205;BA.debugLine="pnlMain.RemoveAllViews";
mostCurrent._pnlmain.RemoveAllViews();
 //BA.debugLineNum = 206;BA.debugLine="pnlMain.LoadLayout(\"page1\")";
mostCurrent._pnlmain.LoadLayout("page1",mostCurrent.activityBA);
 //BA.debugLineNum = 207;BA.debugLine="lblTitle.Text = \"Length Converter\"";
mostCurrent._lbltitle.setText(BA.ObjectToCharSequence("Length Converter"));
 //BA.debugLineNum = 209;BA.debugLine="items.Clear";
mostCurrent._items.Clear();
 //BA.debugLineNum = 210;BA.debugLine="items.AddAll(Array As String(\"mm\",\"cm\",\"m\",\"km\",\"";
mostCurrent._items.AddAll(anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{"mm","cm","m","km","in","ft","yd","mi","µm"}));
 //BA.debugLineNum = 211;BA.debugLine="setspinner(items, Colors.ARGB(64, 255, 255, 255))";
_setspinner(mostCurrent._items,(Object)(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (64),(int) (255),(int) (255),(int) (255))));
 //BA.debugLineNum = 212;BA.debugLine="End Sub";
return "";
}
public static String  _showpage2() throws Exception{
 //BA.debugLineNum = 214;BA.debugLine="Sub ShowPage2";
 //BA.debugLineNum = 215;BA.debugLine="pnlMain.RemoveAllViews";
mostCurrent._pnlmain.RemoveAllViews();
 //BA.debugLineNum = 216;BA.debugLine="pnlMain.LoadLayout(\"page2\")";
mostCurrent._pnlmain.LoadLayout("page2",mostCurrent.activityBA);
 //BA.debugLineNum = 217;BA.debugLine="lblTitle.Text = \"Wight Converter\"";
mostCurrent._lbltitle.setText(BA.ObjectToCharSequence("Wight Converter"));
 //BA.debugLineNum = 219;BA.debugLine="items.Clear";
mostCurrent._items.Clear();
 //BA.debugLineNum = 220;BA.debugLine="items.AddAll(Array As String(\"mg\",\"g\",\"kg\",\"t\",\"o";
mostCurrent._items.AddAll(anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{"mg","g","kg","t","oz","lb","st","ct","µg","ton"}));
 //BA.debugLineNum = 221;BA.debugLine="setspinner(items, Colors.ARGB(64, 255, 255, 255))";
_setspinner(mostCurrent._items,(Object)(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (64),(int) (255),(int) (255),(int) (255))));
 //BA.debugLineNum = 222;BA.debugLine="End Sub";
return "";
}
public static String  _showpage3() throws Exception{
 //BA.debugLineNum = 224;BA.debugLine="Sub ShowPage3";
 //BA.debugLineNum = 225;BA.debugLine="pnlMain.RemoveAllViews";
mostCurrent._pnlmain.RemoveAllViews();
 //BA.debugLineNum = 226;BA.debugLine="pnlMain.LoadLayout(\"page3\")";
mostCurrent._pnlmain.LoadLayout("page3",mostCurrent.activityBA);
 //BA.debugLineNum = 227;BA.debugLine="lblTitle.Text = \"Volume Converter\"";
mostCurrent._lbltitle.setText(BA.ObjectToCharSequence("Volume Converter"));
 //BA.debugLineNum = 229;BA.debugLine="items.Clear";
mostCurrent._items.Clear();
 //BA.debugLineNum = 230;BA.debugLine="items.AddAll(Array As String(\"mL\",\"L\",\"kL\",\"cm³\",";
mostCurrent._items.AddAll(anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{"mL","L","kL","cm³","m³","in³","ft³"}));
 //BA.debugLineNum = 231;BA.debugLine="setspinner(items, Colors.ARGB(64, 255, 255, 255))";
_setspinner(mostCurrent._items,(Object)(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (64),(int) (255),(int) (255),(int) (255))));
 //BA.debugLineNum = 232;BA.debugLine="End Sub";
return "";
}
public static String  _showpage4() throws Exception{
 //BA.debugLineNum = 234;BA.debugLine="Sub ShowPage4";
 //BA.debugLineNum = 235;BA.debugLine="pnlMain.RemoveAllViews";
mostCurrent._pnlmain.RemoveAllViews();
 //BA.debugLineNum = 236;BA.debugLine="pnlMain.LoadLayout(\"page4\")";
mostCurrent._pnlmain.LoadLayout("page4",mostCurrent.activityBA);
 //BA.debugLineNum = 237;BA.debugLine="lblTitle.Text = \"Speed Converter\"";
mostCurrent._lbltitle.setText(BA.ObjectToCharSequence("Speed Converter"));
 //BA.debugLineNum = 239;BA.debugLine="items.Clear";
mostCurrent._items.Clear();
 //BA.debugLineNum = 240;BA.debugLine="items.AddAll(Array As String(\"m/s\",\"km/h\",\"mph\",\"";
mostCurrent._items.AddAll(anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{"m/s","km/h","mph","knot","ft/s"}));
 //BA.debugLineNum = 241;BA.debugLine="setspinner(items, Colors.ARGB(64, 255, 255, 255))";
_setspinner(mostCurrent._items,(Object)(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (64),(int) (255),(int) (255),(int) (255))));
 //BA.debugLineNum = 242;BA.debugLine="End Sub";
return "";
}
public static String  _showpage5() throws Exception{
 //BA.debugLineNum = 244;BA.debugLine="Sub ShowPage5";
 //BA.debugLineNum = 245;BA.debugLine="pnlMain.RemoveAllViews";
mostCurrent._pnlmain.RemoveAllViews();
 //BA.debugLineNum = 246;BA.debugLine="pnlMain.LoadLayout(\"page5\")";
mostCurrent._pnlmain.LoadLayout("page5",mostCurrent.activityBA);
 //BA.debugLineNum = 247;BA.debugLine="lblTitle.Text = \"Temperature Converter\"";
mostCurrent._lbltitle.setText(BA.ObjectToCharSequence("Temperature Converter"));
 //BA.debugLineNum = 249;BA.debugLine="items.Clear";
mostCurrent._items.Clear();
 //BA.debugLineNum = 250;BA.debugLine="items.AddAll(Array As String(\"°C\",\"°F\",\"K\",\"°R\"))";
mostCurrent._items.AddAll(anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{"°C","°F","K","°R"}));
 //BA.debugLineNum = 251;BA.debugLine="setspinner(items, Colors.ARGB(64, 255, 255, 255))";
_setspinner(mostCurrent._items,(Object)(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (64),(int) (255),(int) (255),(int) (255))));
 //BA.debugLineNum = 252;BA.debugLine="End Sub";
return "";
}
public static String  _showpage6() throws Exception{
 //BA.debugLineNum = 254;BA.debugLine="Sub ShowPage6";
 //BA.debugLineNum = 255;BA.debugLine="pnlMain.RemoveAllViews";
mostCurrent._pnlmain.RemoveAllViews();
 //BA.debugLineNum = 256;BA.debugLine="pnlMain.LoadLayout(\"page6\")";
mostCurrent._pnlmain.LoadLayout("page6",mostCurrent.activityBA);
 //BA.debugLineNum = 257;BA.debugLine="lblTitle.Text = \"Area Converter\"";
mostCurrent._lbltitle.setText(BA.ObjectToCharSequence("Area Converter"));
 //BA.debugLineNum = 259;BA.debugLine="items.Clear";
mostCurrent._items.Clear();
 //BA.debugLineNum = 260;BA.debugLine="items.AddAll(Array As String(\"mm²\",\"cm²\",\"m²\",\"km";
mostCurrent._items.AddAll(anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{"mm²","cm²","m²","km²","in²","ft²","yd²"}));
 //BA.debugLineNum = 261;BA.debugLine="setspinner(items, Colors.ARGB(64, 255, 255, 255))";
_setspinner(mostCurrent._items,(Object)(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (64),(int) (255),(int) (255),(int) (255))));
 //BA.debugLineNum = 262;BA.debugLine="End Sub";
return "";
}
public static String  _spinner1_itemclick(int _position,Object _value) throws Exception{
 //BA.debugLineNum = 477;BA.debugLine="Private Sub Spinner1_ItemClick (Position As Int, V";
 //BA.debugLineNum = 478;BA.debugLine="qick";
_qick();
 //BA.debugLineNum = 479;BA.debugLine="End Sub";
return "";
}
public static String  _spinner2_itemclick(int _position,Object _value) throws Exception{
 //BA.debugLineNum = 473;BA.debugLine="Private Sub Spinner2_ItemClick (Position As Int, V";
 //BA.debugLineNum = 474;BA.debugLine="convert";
_convert();
 //BA.debugLineNum = 475;BA.debugLine="End Sub";
return "";
}
public static String  _spinner3_itemclick(int _position,Object _value) throws Exception{
 //BA.debugLineNum = 469;BA.debugLine="Private Sub Spinner3_ItemClick (Position As Int, V";
 //BA.debugLineNum = 470;BA.debugLine="convert";
_convert();
 //BA.debugLineNum = 471;BA.debugLine="End Sub";
return "";
}
}
