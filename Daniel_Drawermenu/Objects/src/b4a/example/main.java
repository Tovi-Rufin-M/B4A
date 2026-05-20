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
public static String _units = "";
public b4a.example.starter _starter = null;

public static boolean isAnyActivityVisible() {
    boolean vis = false;
vis = vis | (main.mostCurrent != null);
return vis;}
public static String  _activity_create(boolean _firsttime) throws Exception{
 //BA.debugLineNum = 36;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 37;BA.debugLine="Activity.LoadLayout(\"main\")";
mostCurrent._activity.LoadLayout("main",mostCurrent.activityBA);
 //BA.debugLineNum = 39;BA.debugLine="Drawer.Initialize(Me, \"Drawer\", Activity, 260dip)";
mostCurrent._drawer._initialize /*String*/ (mostCurrent.activityBA,main.getObject(),"Drawer",(anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._activity.getObject())),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (260)));
 //BA.debugLineNum = 40;BA.debugLine="Drawer.CenterPanel.BringToFront";
mostCurrent._drawer._getcenterpanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ ().BringToFront();
 //BA.debugLineNum = 41;BA.debugLine="Drawer.LeftPanel.BringToFront";
mostCurrent._drawer._getleftpanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ ().BringToFront();
 //BA.debugLineNum = 43;BA.debugLine="pnlMain = Drawer.CenterPanel";
mostCurrent._pnlmain = (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(mostCurrent._drawer._getcenterpanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ ().getObject()));
 //BA.debugLineNum = 44;BA.debugLine="pnlMenu = Drawer.LeftPanel";
mostCurrent._pnlmenu = (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(mostCurrent._drawer._getleftpanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ ().getObject()));
 //BA.debugLineNum = 46;BA.debugLine="pnlMenu.Color = Colors.RGB(33,150,243)";
mostCurrent._pnlmenu.setColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (33),(int) (150),(int) (243)));
 //BA.debugLineNum = 48;BA.debugLine="CreateMenu";
_createmenu();
 //BA.debugLineNum = 49;BA.debugLine="ShowHome";
_showhome();
 //BA.debugLineNum = 50;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 178;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 179;BA.debugLine="If KeyCode = KeyCodes.KEYCODE_BACK And Drawer.Lef";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK && mostCurrent._drawer._getleftopen /*boolean*/ ()) { 
 //BA.debugLineNum = 180;BA.debugLine="Drawer.LeftOpen = False";
mostCurrent._drawer._setleftopen /*boolean*/ (anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 181;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 183;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 184;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 56;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 58;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 52;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 54;BA.debugLine="End Sub";
return "";
}
public static String  _btnhome_click() throws Exception{
 //BA.debugLineNum = 99;BA.debugLine="Sub btnHome_Click";
 //BA.debugLineNum = 100;BA.debugLine="Drawer.LeftOpen = False";
mostCurrent._drawer._setleftopen /*boolean*/ (anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 101;BA.debugLine="ShowHome";
_showhome();
 //BA.debugLineNum = 102;BA.debugLine="End Sub";
return "";
}
public static String  _btnmenu_click() throws Exception{
 //BA.debugLineNum = 60;BA.debugLine="Sub btnMenu_Click";
 //BA.debugLineNum = 61;BA.debugLine="Drawer.LeftOpen = Not(Drawer.LeftOpen)";
mostCurrent._drawer._setleftopen /*boolean*/ (anywheresoftware.b4a.keywords.Common.Not(mostCurrent._drawer._getleftopen /*boolean*/ ()));
 //BA.debugLineNum = 62;BA.debugLine="End Sub";
return "";
}
public static String  _btnpage1_click() throws Exception{
 //BA.debugLineNum = 104;BA.debugLine="Sub btnPage1_Click";
 //BA.debugLineNum = 105;BA.debugLine="Drawer.LeftOpen = False";
mostCurrent._drawer._setleftopen /*boolean*/ (anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 106;BA.debugLine="ShowPage1";
_showpage1();
 //BA.debugLineNum = 107;BA.debugLine="End Sub";
return "";
}
public static String  _btnpage2_click() throws Exception{
 //BA.debugLineNum = 109;BA.debugLine="Sub btnPage2_Click";
 //BA.debugLineNum = 110;BA.debugLine="Drawer.LeftOpen = False";
mostCurrent._drawer._setleftopen /*boolean*/ (anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 111;BA.debugLine="ShowPage2";
_showpage2();
 //BA.debugLineNum = 112;BA.debugLine="End Sub";
return "";
}
public static String  _btnpage3_click() throws Exception{
 //BA.debugLineNum = 114;BA.debugLine="Sub btnPage3_Click";
 //BA.debugLineNum = 115;BA.debugLine="Drawer.LeftOpen = False";
mostCurrent._drawer._setleftopen /*boolean*/ (anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 116;BA.debugLine="ShowPage3";
_showpage3();
 //BA.debugLineNum = 117;BA.debugLine="End Sub";
return "";
}
public static String  _btnpage4_click() throws Exception{
 //BA.debugLineNum = 119;BA.debugLine="Sub btnPage4_Click";
 //BA.debugLineNum = 120;BA.debugLine="Drawer.LeftOpen = False";
mostCurrent._drawer._setleftopen /*boolean*/ (anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 121;BA.debugLine="ShowPage4";
_showpage4();
 //BA.debugLineNum = 122;BA.debugLine="End Sub";
return "";
}
public static String  _btnpage5_click() throws Exception{
 //BA.debugLineNum = 124;BA.debugLine="Sub btnPage5_Click";
 //BA.debugLineNum = 125;BA.debugLine="Drawer.LeftOpen = False";
mostCurrent._drawer._setleftopen /*boolean*/ (anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 126;BA.debugLine="ShowPage5";
_showpage5();
 //BA.debugLineNum = 127;BA.debugLine="End Sub";
return "";
}
public static String  _btnpage6_click() throws Exception{
 //BA.debugLineNum = 129;BA.debugLine="Sub btnPage6_Click";
 //BA.debugLineNum = 130;BA.debugLine="Drawer.LeftOpen = False";
mostCurrent._drawer._setleftopen /*boolean*/ (anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 131;BA.debugLine="ShowPage6";
_showpage6();
 //BA.debugLineNum = 132;BA.debugLine="End Sub";
return "";
}
public static String  _button1_click() throws Exception{
double _ans = 0;
 //BA.debugLineNum = 210;BA.debugLine="Private Sub Button1_Click";
 //BA.debugLineNum = 212;BA.debugLine="If EditText1.Text = \"\" Then";
if ((mostCurrent._edittext1.getText()).equals("")) { 
 //BA.debugLineNum = 213;BA.debugLine="MsgboxAsync(\"Please enter a value\", \"Input Error";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Please enter a value"),BA.ObjectToCharSequence("Input Error"),processBA);
 //BA.debugLineNum = 214;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 217;BA.debugLine="Dim Ans As Double";
_ans = 0;
 //BA.debugLineNum = 218;BA.debugLine="Dim units As String ' Ensure this is declared if";
mostCurrent._units = "";
 //BA.debugLineNum = 220;BA.debugLine="Select lblTitle.Text";
switch (BA.switchObjectToInt(mostCurrent._lbltitle.getText(),"Inches To Centimeter","Centimeter To Inches","Inches To Feet","Feet To Inches","Centimeter To Meter","Meter To Centimeter")) {
case 0: {
 //BA.debugLineNum = 222;BA.debugLine="Ans = (EditText1.Text) * 2.54";
_ans = (double)(Double.parseDouble((mostCurrent._edittext1.getText())))*2.54;
 //BA.debugLineNum = 223;BA.debugLine="units = \" cm\"";
mostCurrent._units = " cm";
 break; }
case 1: {
 //BA.debugLineNum = 226;BA.debugLine="Ans = (EditText1.Text) / 2.54";
_ans = (double)(Double.parseDouble((mostCurrent._edittext1.getText())))/(double)2.54;
 //BA.debugLineNum = 227;BA.debugLine="units = \" in\"";
mostCurrent._units = " in";
 break; }
case 2: {
 //BA.debugLineNum = 230;BA.debugLine="Ans = (EditText1.Text) / 12";
_ans = (double)(Double.parseDouble((mostCurrent._edittext1.getText())))/(double)12;
 //BA.debugLineNum = 231;BA.debugLine="units = \" ft\"";
mostCurrent._units = " ft";
 break; }
case 3: {
 //BA.debugLineNum = 234;BA.debugLine="Ans = (EditText1.Text) * 12";
_ans = (double)(Double.parseDouble((mostCurrent._edittext1.getText())))*12;
 //BA.debugLineNum = 235;BA.debugLine="units = \" in\"";
mostCurrent._units = " in";
 break; }
case 4: {
 //BA.debugLineNum = 238;BA.debugLine="Ans = (EditText1.Text) / 100";
_ans = (double)(Double.parseDouble((mostCurrent._edittext1.getText())))/(double)100;
 //BA.debugLineNum = 239;BA.debugLine="units = \" m\"";
mostCurrent._units = " m";
 break; }
case 5: {
 //BA.debugLineNum = 242;BA.debugLine="Ans = (EditText1.Text) * 100";
_ans = (double)(Double.parseDouble((mostCurrent._edittext1.getText())))*100;
 //BA.debugLineNum = 243;BA.debugLine="units = \" cm\"";
mostCurrent._units = " cm";
 break; }
default: {
 //BA.debugLineNum = 246;BA.debugLine="Ans = 0";
_ans = 0;
 //BA.debugLineNum = 247;BA.debugLine="units = \"\"";
mostCurrent._units = "";
 break; }
}
;
 //BA.debugLineNum = 252;BA.debugLine="Label1.Text = NumberFormat(Ans, 1, 2) & units";
mostCurrent._label1.setText(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.NumberFormat(_ans,(int) (1),(int) (2))+mostCurrent._units));
 //BA.debugLineNum = 253;BA.debugLine="End Sub";
return "";
}
public static String  _createmenu() throws Exception{
anywheresoftware.b4a.objects.ButtonWrapper _b = null;
 //BA.debugLineNum = 64;BA.debugLine="Sub CreateMenu";
 //BA.debugLineNum = 65;BA.debugLine="Dim btnHome, btnPage1, btnPage2 As Button";
mostCurrent._btnhome = new anywheresoftware.b4a.objects.ButtonWrapper();
mostCurrent._btnpage1 = new anywheresoftware.b4a.objects.ButtonWrapper();
mostCurrent._btnpage2 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 66;BA.debugLine="btnHome.Initialize(\"btnHome\")";
mostCurrent._btnhome.Initialize(mostCurrent.activityBA,"btnHome");
 //BA.debugLineNum = 67;BA.debugLine="btnHome.Text = \"Home\"";
mostCurrent._btnhome.setText(BA.ObjectToCharSequence("Home"));
 //BA.debugLineNum = 68;BA.debugLine="btnPage1.Initialize(\"btnPage1\")";
mostCurrent._btnpage1.Initialize(mostCurrent.activityBA,"btnPage1");
 //BA.debugLineNum = 69;BA.debugLine="btnPage1.Text = \"Inches To Centimeter\"";
mostCurrent._btnpage1.setText(BA.ObjectToCharSequence("Inches To Centimeter"));
 //BA.debugLineNum = 70;BA.debugLine="btnPage2.Initialize(\"btnPage2\")";
mostCurrent._btnpage2.Initialize(mostCurrent.activityBA,"btnPage2");
 //BA.debugLineNum = 71;BA.debugLine="btnPage2.Text = \"Centimeter To Inches\"";
mostCurrent._btnpage2.setText(BA.ObjectToCharSequence("Centimeter To Inches"));
 //BA.debugLineNum = 72;BA.debugLine="btnPage3.Initialize(\"btnPage3\")";
mostCurrent._btnpage3.Initialize(mostCurrent.activityBA,"btnPage3");
 //BA.debugLineNum = 73;BA.debugLine="btnPage3.Text = \"Inches To Feet\"";
mostCurrent._btnpage3.setText(BA.ObjectToCharSequence("Inches To Feet"));
 //BA.debugLineNum = 74;BA.debugLine="btnPage4.Initialize(\"btnPage4\")";
mostCurrent._btnpage4.Initialize(mostCurrent.activityBA,"btnPage4");
 //BA.debugLineNum = 75;BA.debugLine="btnPage4.Text = \"Feet To Inches\"";
mostCurrent._btnpage4.setText(BA.ObjectToCharSequence("Feet To Inches"));
 //BA.debugLineNum = 76;BA.debugLine="btnPage5.Initialize(\"btnPage5\")";
mostCurrent._btnpage5.Initialize(mostCurrent.activityBA,"btnPage5");
 //BA.debugLineNum = 77;BA.debugLine="btnPage5.Text = \"Centimeter To Meter\"";
mostCurrent._btnpage5.setText(BA.ObjectToCharSequence("Centimeter To Meter"));
 //BA.debugLineNum = 78;BA.debugLine="btnPage6.Initialize(\"btnPage6\")";
mostCurrent._btnpage6.Initialize(mostCurrent.activityBA,"btnPage6");
 //BA.debugLineNum = 79;BA.debugLine="btnPage6.Text = \"Meter To Centimeter\"";
mostCurrent._btnpage6.setText(BA.ObjectToCharSequence("Meter To Centimeter"));
 //BA.debugLineNum = 81;BA.debugLine="For Each b As Button In Array(btnHome, btnPage1,";
_b = new anywheresoftware.b4a.objects.ButtonWrapper();
{
final Object[] group16 = new Object[]{(Object)(mostCurrent._btnhome.getObject()),(Object)(mostCurrent._btnpage1.getObject()),(Object)(mostCurrent._btnpage2.getObject()),(Object)(mostCurrent._btnpage3.getObject()),(Object)(mostCurrent._btnpage4.getObject()),(Object)(mostCurrent._btnpage5.getObject()),(Object)(mostCurrent._btnpage6.getObject())};
final int groupLen16 = group16.length
;int index16 = 0;
;
for (; index16 < groupLen16;index16++){
_b = (anywheresoftware.b4a.objects.ButtonWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ButtonWrapper(), (android.widget.Button)(group16[index16]));
 //BA.debugLineNum = 82;BA.debugLine="b.TextSize = 16";
_b.setTextSize((float) (16));
 //BA.debugLineNum = 83;BA.debugLine="b.Gravity = Gravity.LEFT + Gravity.CENTER_VERTIC";
_b.setGravity((int) (anywheresoftware.b4a.keywords.Common.Gravity.LEFT+anywheresoftware.b4a.keywords.Common.Gravity.CENTER_VERTICAL));
 //BA.debugLineNum = 84;BA.debugLine="b.Color = Colors.Transparent";
_b.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 85;BA.debugLine="b.TextColor = Colors.White";
_b.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 86;BA.debugLine="pnlMenu.AddView(b, 10dip, 0, 240dip, 50dip)";
mostCurrent._pnlmenu.AddView((android.view.View)(_b.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (240)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
 }
};
 //BA.debugLineNum = 88;BA.debugLine="btnHome.Top = 120dip";
mostCurrent._btnhome.setTop(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (120)));
 //BA.debugLineNum = 89;BA.debugLine="btnPage1.Top = 180dip";
mostCurrent._btnpage1.setTop(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (180)));
 //BA.debugLineNum = 90;BA.debugLine="btnPage2.Top = 240dip";
mostCurrent._btnpage2.setTop(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (240)));
 //BA.debugLineNum = 91;BA.debugLine="btnPage3.Top = 300dip";
mostCurrent._btnpage3.setTop(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (300)));
 //BA.debugLineNum = 92;BA.debugLine="btnPage4.Top = 360dip";
mostCurrent._btnpage4.setTop(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (360)));
 //BA.debugLineNum = 93;BA.debugLine="btnPage5.Top = 420dip";
mostCurrent._btnpage5.setTop(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (420)));
 //BA.debugLineNum = 94;BA.debugLine="btnPage6.Top = 480dip";
mostCurrent._btnpage6.setTop(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (480)));
 //BA.debugLineNum = 95;BA.debugLine="End Sub";
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
 //BA.debugLineNum = 33;BA.debugLine="Private units As String";
mostCurrent._units = "";
 //BA.debugLineNum = 34;BA.debugLine="End Sub";
return "";
}
public static String  _pnl1_click() throws Exception{
 //BA.debugLineNum = 206;BA.debugLine="Private Sub pnl1_Click";
 //BA.debugLineNum = 207;BA.debugLine="ShowPage1";
_showpage1();
 //BA.debugLineNum = 208;BA.debugLine="End Sub";
return "";
}
public static String  _pnl2_click() throws Exception{
 //BA.debugLineNum = 202;BA.debugLine="Private Sub pnl2_Click";
 //BA.debugLineNum = 203;BA.debugLine="ShowPage2";
_showpage2();
 //BA.debugLineNum = 204;BA.debugLine="End Sub";
return "";
}
public static String  _pnl3_click() throws Exception{
 //BA.debugLineNum = 198;BA.debugLine="Private Sub pnl3_Click";
 //BA.debugLineNum = 199;BA.debugLine="ShowPage3";
_showpage3();
 //BA.debugLineNum = 200;BA.debugLine="End Sub";
return "";
}
public static String  _pnl4_click() throws Exception{
 //BA.debugLineNum = 194;BA.debugLine="Private Sub pnl4_Click";
 //BA.debugLineNum = 195;BA.debugLine="ShowPage4";
_showpage4();
 //BA.debugLineNum = 196;BA.debugLine="End Sub";
return "";
}
public static String  _pnl5_click() throws Exception{
 //BA.debugLineNum = 190;BA.debugLine="Private Sub pnl5_Click";
 //BA.debugLineNum = 191;BA.debugLine="ShowPage5";
_showpage5();
 //BA.debugLineNum = 192;BA.debugLine="End Sub";
return "";
}
public static String  _pnl6_click() throws Exception{
 //BA.debugLineNum = 186;BA.debugLine="Private Sub pnl6_Click";
 //BA.debugLineNum = 187;BA.debugLine="ShowPage6";
_showpage6();
 //BA.debugLineNum = 188;BA.debugLine="End Sub";
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
public static String  _showhome() throws Exception{
 //BA.debugLineNum = 136;BA.debugLine="Sub ShowHome";
 //BA.debugLineNum = 137;BA.debugLine="pnlMain.RemoveAllViews";
mostCurrent._pnlmain.RemoveAllViews();
 //BA.debugLineNum = 138;BA.debugLine="pnlMain.LoadLayout(\"home\")";
mostCurrent._pnlmain.LoadLayout("home",mostCurrent.activityBA);
 //BA.debugLineNum = 139;BA.debugLine="lblTitle.Text = \"Home\"";
mostCurrent._lbltitle.setText(BA.ObjectToCharSequence("Home"));
 //BA.debugLineNum = 140;BA.debugLine="End Sub";
return "";
}
public static String  _showpage1() throws Exception{
 //BA.debugLineNum = 142;BA.debugLine="Sub ShowPage1";
 //BA.debugLineNum = 143;BA.debugLine="pnlMain.RemoveAllViews";
mostCurrent._pnlmain.RemoveAllViews();
 //BA.debugLineNum = 144;BA.debugLine="pnlMain.LoadLayout(\"page1\")";
mostCurrent._pnlmain.LoadLayout("page1",mostCurrent.activityBA);
 //BA.debugLineNum = 145;BA.debugLine="lblTitle.Text = \"Inches To Centimeter\"";
mostCurrent._lbltitle.setText(BA.ObjectToCharSequence("Inches To Centimeter"));
 //BA.debugLineNum = 146;BA.debugLine="End Sub";
return "";
}
public static String  _showpage2() throws Exception{
 //BA.debugLineNum = 148;BA.debugLine="Sub ShowPage2";
 //BA.debugLineNum = 149;BA.debugLine="pnlMain.RemoveAllViews";
mostCurrent._pnlmain.RemoveAllViews();
 //BA.debugLineNum = 150;BA.debugLine="pnlMain.LoadLayout(\"page2\")";
mostCurrent._pnlmain.LoadLayout("page2",mostCurrent.activityBA);
 //BA.debugLineNum = 151;BA.debugLine="lblTitle.Text = \"Centimeter To Inches\"";
mostCurrent._lbltitle.setText(BA.ObjectToCharSequence("Centimeter To Inches"));
 //BA.debugLineNum = 152;BA.debugLine="End Sub";
return "";
}
public static String  _showpage3() throws Exception{
 //BA.debugLineNum = 154;BA.debugLine="Sub ShowPage3";
 //BA.debugLineNum = 155;BA.debugLine="pnlMain.RemoveAllViews";
mostCurrent._pnlmain.RemoveAllViews();
 //BA.debugLineNum = 156;BA.debugLine="pnlMain.LoadLayout(\"page3\")";
mostCurrent._pnlmain.LoadLayout("page3",mostCurrent.activityBA);
 //BA.debugLineNum = 157;BA.debugLine="lblTitle.Text = \"Inches To Feet\"";
mostCurrent._lbltitle.setText(BA.ObjectToCharSequence("Inches To Feet"));
 //BA.debugLineNum = 158;BA.debugLine="End Sub";
return "";
}
public static String  _showpage4() throws Exception{
 //BA.debugLineNum = 160;BA.debugLine="Sub ShowPage4";
 //BA.debugLineNum = 161;BA.debugLine="pnlMain.RemoveAllViews";
mostCurrent._pnlmain.RemoveAllViews();
 //BA.debugLineNum = 162;BA.debugLine="pnlMain.LoadLayout(\"page4\")";
mostCurrent._pnlmain.LoadLayout("page4",mostCurrent.activityBA);
 //BA.debugLineNum = 163;BA.debugLine="lblTitle.Text = \"Feet To Inches\"";
mostCurrent._lbltitle.setText(BA.ObjectToCharSequence("Feet To Inches"));
 //BA.debugLineNum = 164;BA.debugLine="End Sub";
return "";
}
public static String  _showpage5() throws Exception{
 //BA.debugLineNum = 166;BA.debugLine="Sub ShowPage5";
 //BA.debugLineNum = 167;BA.debugLine="pnlMain.RemoveAllViews";
mostCurrent._pnlmain.RemoveAllViews();
 //BA.debugLineNum = 168;BA.debugLine="pnlMain.LoadLayout(\"page5\")";
mostCurrent._pnlmain.LoadLayout("page5",mostCurrent.activityBA);
 //BA.debugLineNum = 169;BA.debugLine="lblTitle.Text = \"Centimeter To Meter\"";
mostCurrent._lbltitle.setText(BA.ObjectToCharSequence("Centimeter To Meter"));
 //BA.debugLineNum = 170;BA.debugLine="End Sub";
return "";
}
public static String  _showpage6() throws Exception{
 //BA.debugLineNum = 172;BA.debugLine="Sub ShowPage6";
 //BA.debugLineNum = 173;BA.debugLine="pnlMain.RemoveAllViews";
mostCurrent._pnlmain.RemoveAllViews();
 //BA.debugLineNum = 174;BA.debugLine="pnlMain.LoadLayout(\"page6\")";
mostCurrent._pnlmain.LoadLayout("page6",mostCurrent.activityBA);
 //BA.debugLineNum = 175;BA.debugLine="lblTitle.Text = \"Meter To Centimeter\"";
mostCurrent._lbltitle.setText(BA.ObjectToCharSequence("Meter To Centimeter"));
 //BA.debugLineNum = 176;BA.debugLine="End Sub";
return "";
}
}
