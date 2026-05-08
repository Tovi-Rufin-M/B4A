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
			processBA = new anywheresoftware.b4a.ShellBA(this.getApplicationContext(), null, null, "b4a.example", "b4a.example.main");
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



public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}
public static boolean isAnyActivityVisible() {
    boolean vis = false;
vis = vis | (main.mostCurrent != null);
return vis;}

private static BA killProgramHelper(BA ba) {
    if (ba == null)
        return null;
    anywheresoftware.b4a.BA.SharedProcessBA sharedProcessBA = ba.sharedProcessBA;
    if (sharedProcessBA == null || sharedProcessBA.activityBA == null)
        return null;
    return sharedProcessBA.activityBA.get();
}
public static void killProgram() {
     {
            Activity __a = null;
            if (main.previousOne != null) {
				__a = main.previousOne.get();
			}
            else {
                BA ba = killProgramHelper(main.mostCurrent == null ? null : main.mostCurrent.processBA);
                if (ba != null) __a = ba.activity;
            }
            if (__a != null)
				__a.finish();}

BA.applicationContext.stopService(new android.content.Intent(BA.applicationContext, starter.class));
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
public b4a.example.starter _starter = null;
public static String  _activity_create(boolean _firsttime) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_create", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_create", new Object[] {_firsttime}));}
RDebugUtils.currentLine=131072;
 //BA.debugLineNum = 131072;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
RDebugUtils.currentLine=131073;
 //BA.debugLineNum = 131073;BA.debugLine="Activity.LoadLayout(\"main\")";
mostCurrent._activity.LoadLayout("main",mostCurrent.activityBA);
RDebugUtils.currentLine=131075;
 //BA.debugLineNum = 131075;BA.debugLine="Drawer.Initialize(Me, \"Drawer\", Activity, 260dip)";
mostCurrent._drawer._initialize /*String*/ (null,mostCurrent.activityBA,main.getObject(),"Drawer",(anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._activity.getObject())),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (260)));
RDebugUtils.currentLine=131076;
 //BA.debugLineNum = 131076;BA.debugLine="Drawer.CenterPanel.BringToFront";
mostCurrent._drawer._getcenterpanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ (null).BringToFront();
RDebugUtils.currentLine=131077;
 //BA.debugLineNum = 131077;BA.debugLine="Drawer.LeftPanel.BringToFront";
mostCurrent._drawer._getleftpanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ (null).BringToFront();
RDebugUtils.currentLine=131079;
 //BA.debugLineNum = 131079;BA.debugLine="pnlMain = Drawer.CenterPanel";
mostCurrent._pnlmain = (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(mostCurrent._drawer._getcenterpanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ (null).getObject()));
RDebugUtils.currentLine=131080;
 //BA.debugLineNum = 131080;BA.debugLine="pnlMenu = Drawer.LeftPanel";
mostCurrent._pnlmenu = (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(mostCurrent._drawer._getleftpanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ (null).getObject()));
RDebugUtils.currentLine=131082;
 //BA.debugLineNum = 131082;BA.debugLine="pnlMenu.Color = Colors.RGB(33,150,243)";
mostCurrent._pnlmenu.setColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (33),(int) (150),(int) (243)));
RDebugUtils.currentLine=131084;
 //BA.debugLineNum = 131084;BA.debugLine="CreateMenu";
_createmenu();
RDebugUtils.currentLine=131085;
 //BA.debugLineNum = 131085;BA.debugLine="ShowHome";
_showhome();
RDebugUtils.currentLine=131086;
 //BA.debugLineNum = 131086;BA.debugLine="End Sub";
return "";
}
public static String  _createmenu() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "createmenu", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "createmenu", null));}
anywheresoftware.b4a.objects.ButtonWrapper _b = null;
RDebugUtils.currentLine=393216;
 //BA.debugLineNum = 393216;BA.debugLine="Sub CreateMenu";
RDebugUtils.currentLine=393217;
 //BA.debugLineNum = 393217;BA.debugLine="Dim btnHome, btnPage1, btnPage2 As Button";
mostCurrent._btnhome = new anywheresoftware.b4a.objects.ButtonWrapper();
mostCurrent._btnpage1 = new anywheresoftware.b4a.objects.ButtonWrapper();
mostCurrent._btnpage2 = new anywheresoftware.b4a.objects.ButtonWrapper();
RDebugUtils.currentLine=393218;
 //BA.debugLineNum = 393218;BA.debugLine="btnHome.Initialize(\"btnHome\")";
mostCurrent._btnhome.Initialize(mostCurrent.activityBA,"btnHome");
RDebugUtils.currentLine=393219;
 //BA.debugLineNum = 393219;BA.debugLine="btnHome.Text = \"Home\"";
mostCurrent._btnhome.setText(BA.ObjectToCharSequence("Home"));
RDebugUtils.currentLine=393220;
 //BA.debugLineNum = 393220;BA.debugLine="btnPage1.Initialize(\"btnPage1\")";
mostCurrent._btnpage1.Initialize(mostCurrent.activityBA,"btnPage1");
RDebugUtils.currentLine=393221;
 //BA.debugLineNum = 393221;BA.debugLine="btnPage1.Text = \"Page 1\"";
mostCurrent._btnpage1.setText(BA.ObjectToCharSequence("Page 1"));
RDebugUtils.currentLine=393222;
 //BA.debugLineNum = 393222;BA.debugLine="btnPage2.Initialize(\"btnPage2\")";
mostCurrent._btnpage2.Initialize(mostCurrent.activityBA,"btnPage2");
RDebugUtils.currentLine=393223;
 //BA.debugLineNum = 393223;BA.debugLine="btnPage2.Text = \"Page 2\"";
mostCurrent._btnpage2.setText(BA.ObjectToCharSequence("Page 2"));
RDebugUtils.currentLine=393224;
 //BA.debugLineNum = 393224;BA.debugLine="For Each b As Button In Array(btnHome, btnPage1,";
_b = new anywheresoftware.b4a.objects.ButtonWrapper();
{
final Object[] group8 = new Object[]{(Object)(mostCurrent._btnhome.getObject()),(Object)(mostCurrent._btnpage1.getObject()),(Object)(mostCurrent._btnpage2.getObject())};
final int groupLen8 = group8.length
;int index8 = 0;
;
for (; index8 < groupLen8;index8++){
_b = (anywheresoftware.b4a.objects.ButtonWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ButtonWrapper(), (android.widget.Button)(group8[index8]));
RDebugUtils.currentLine=393225;
 //BA.debugLineNum = 393225;BA.debugLine="b.TextSize = 16";
_b.setTextSize((float) (16));
RDebugUtils.currentLine=393226;
 //BA.debugLineNum = 393226;BA.debugLine="b.Gravity = Gravity.LEFT + Gravity.CENTER_VERTIC";
_b.setGravity((int) (anywheresoftware.b4a.keywords.Common.Gravity.LEFT+anywheresoftware.b4a.keywords.Common.Gravity.CENTER_VERTICAL));
RDebugUtils.currentLine=393227;
 //BA.debugLineNum = 393227;BA.debugLine="b.Color = Colors.Transparent";
_b.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
RDebugUtils.currentLine=393228;
 //BA.debugLineNum = 393228;BA.debugLine="b.TextColor = Colors.White";
_b.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=393229;
 //BA.debugLineNum = 393229;BA.debugLine="pnlMenu.AddView(b, 10dip, 0, 240dip, 50dip)";
mostCurrent._pnlmenu.AddView((android.view.View)(_b.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (240)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
 }
};
RDebugUtils.currentLine=393231;
 //BA.debugLineNum = 393231;BA.debugLine="btnHome.Top = 120dip";
mostCurrent._btnhome.setTop(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (120)));
RDebugUtils.currentLine=393232;
 //BA.debugLineNum = 393232;BA.debugLine="btnPage1.Top = 180dip";
mostCurrent._btnpage1.setTop(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (180)));
RDebugUtils.currentLine=393233;
 //BA.debugLineNum = 393233;BA.debugLine="btnPage2.Top = 240dip";
mostCurrent._btnpage2.setTop(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (240)));
RDebugUtils.currentLine=393234;
 //BA.debugLineNum = 393234;BA.debugLine="End Sub";
return "";
}
public static String  _showhome() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "showhome", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "showhome", null));}
RDebugUtils.currentLine=655360;
 //BA.debugLineNum = 655360;BA.debugLine="Sub ShowHome";
RDebugUtils.currentLine=655361;
 //BA.debugLineNum = 655361;BA.debugLine="pnlMain.RemoveAllViews";
mostCurrent._pnlmain.RemoveAllViews();
RDebugUtils.currentLine=655362;
 //BA.debugLineNum = 655362;BA.debugLine="pnlMain.LoadLayout(\"home\")";
mostCurrent._pnlmain.LoadLayout("home",mostCurrent.activityBA);
RDebugUtils.currentLine=655363;
 //BA.debugLineNum = 655363;BA.debugLine="lblTitle.Text = \"Home\"";
mostCurrent._lbltitle.setText(BA.ObjectToCharSequence("Home"));
RDebugUtils.currentLine=655365;
 //BA.debugLineNum = 655365;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_keypress", false))
	 {return ((Boolean) Debug.delegate(mostCurrent.activityBA, "activity_keypress", new Object[] {_keycode}));}
RDebugUtils.currentLine=851968;
 //BA.debugLineNum = 851968;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
RDebugUtils.currentLine=851969;
 //BA.debugLineNum = 851969;BA.debugLine="If KeyCode = KeyCodes.KEYCODE_BACK And Drawer.Lef";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK && mostCurrent._drawer._getleftopen /*boolean*/ (null)) { 
RDebugUtils.currentLine=851970;
 //BA.debugLineNum = 851970;BA.debugLine="Drawer.LeftOpen = False";
mostCurrent._drawer._setleftopen /*boolean*/ (null,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=851971;
 //BA.debugLineNum = 851971;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
RDebugUtils.currentLine=851973;
 //BA.debugLineNum = 851973;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
RDebugUtils.currentLine=851974;
 //BA.debugLineNum = 851974;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
RDebugUtils.currentModule="main";
RDebugUtils.currentLine=262144;
 //BA.debugLineNum = 262144;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
RDebugUtils.currentLine=262146;
 //BA.debugLineNum = 262146;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_resume", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_resume", null));}
RDebugUtils.currentLine=196608;
 //BA.debugLineNum = 196608;BA.debugLine="Sub Activity_Resume";
RDebugUtils.currentLine=196610;
 //BA.debugLineNum = 196610;BA.debugLine="End Sub";
return "";
}
public static String  _btnhome_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btnhome_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btnhome_click", null));}
RDebugUtils.currentLine=458752;
 //BA.debugLineNum = 458752;BA.debugLine="Sub btnHome_Click";
RDebugUtils.currentLine=458753;
 //BA.debugLineNum = 458753;BA.debugLine="Drawer.LeftOpen = False";
mostCurrent._drawer._setleftopen /*boolean*/ (null,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=458754;
 //BA.debugLineNum = 458754;BA.debugLine="ShowHome";
_showhome();
RDebugUtils.currentLine=458755;
 //BA.debugLineNum = 458755;BA.debugLine="End Sub";
return "";
}
public static String  _btnmenu_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btnmenu_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btnmenu_click", null));}
RDebugUtils.currentLine=327680;
 //BA.debugLineNum = 327680;BA.debugLine="Sub btnMenu_Click";
RDebugUtils.currentLine=327681;
 //BA.debugLineNum = 327681;BA.debugLine="Drawer.LeftOpen = Not(Drawer.LeftOpen)";
mostCurrent._drawer._setleftopen /*boolean*/ (null,anywheresoftware.b4a.keywords.Common.Not(mostCurrent._drawer._getleftopen /*boolean*/ (null)));
RDebugUtils.currentLine=327682;
 //BA.debugLineNum = 327682;BA.debugLine="End Sub";
return "";
}
public static String  _btnpage1_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btnpage1_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btnpage1_click", null));}
RDebugUtils.currentLine=524288;
 //BA.debugLineNum = 524288;BA.debugLine="Sub btnPage1_Click";
RDebugUtils.currentLine=524289;
 //BA.debugLineNum = 524289;BA.debugLine="Drawer.LeftOpen = False";
mostCurrent._drawer._setleftopen /*boolean*/ (null,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=524290;
 //BA.debugLineNum = 524290;BA.debugLine="ShowPage1";
_showpage1();
RDebugUtils.currentLine=524291;
 //BA.debugLineNum = 524291;BA.debugLine="End Sub";
return "";
}
public static String  _showpage1() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "showpage1", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "showpage1", null));}
RDebugUtils.currentLine=720896;
 //BA.debugLineNum = 720896;BA.debugLine="Sub ShowPage1";
RDebugUtils.currentLine=720897;
 //BA.debugLineNum = 720897;BA.debugLine="pnlMain.RemoveAllViews";
mostCurrent._pnlmain.RemoveAllViews();
RDebugUtils.currentLine=720898;
 //BA.debugLineNum = 720898;BA.debugLine="pnlMain.LoadLayout(\"page1\")";
mostCurrent._pnlmain.LoadLayout("page1",mostCurrent.activityBA);
RDebugUtils.currentLine=720899;
 //BA.debugLineNum = 720899;BA.debugLine="lblTitle.Text = \"Page 1\"";
mostCurrent._lbltitle.setText(BA.ObjectToCharSequence("Page 1"));
RDebugUtils.currentLine=720900;
 //BA.debugLineNum = 720900;BA.debugLine="End Sub";
return "";
}
public static String  _btnpage2_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btnpage2_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btnpage2_click", null));}
RDebugUtils.currentLine=589824;
 //BA.debugLineNum = 589824;BA.debugLine="Sub btnPage2_Click";
RDebugUtils.currentLine=589825;
 //BA.debugLineNum = 589825;BA.debugLine="Drawer.LeftOpen = False";
mostCurrent._drawer._setleftopen /*boolean*/ (null,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=589826;
 //BA.debugLineNum = 589826;BA.debugLine="ShowPage2";
_showpage2();
RDebugUtils.currentLine=589827;
 //BA.debugLineNum = 589827;BA.debugLine="End Sub";
return "";
}
public static String  _showpage2() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "showpage2", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "showpage2", null));}
RDebugUtils.currentLine=786432;
 //BA.debugLineNum = 786432;BA.debugLine="Sub ShowPage2";
RDebugUtils.currentLine=786433;
 //BA.debugLineNum = 786433;BA.debugLine="pnlMain.RemoveAllViews";
mostCurrent._pnlmain.RemoveAllViews();
RDebugUtils.currentLine=786434;
 //BA.debugLineNum = 786434;BA.debugLine="pnlMain.LoadLayout(\"page2\")";
mostCurrent._pnlmain.LoadLayout("page2",mostCurrent.activityBA);
RDebugUtils.currentLine=786435;
 //BA.debugLineNum = 786435;BA.debugLine="lblTitle.Text = \"Page 2\"";
mostCurrent._lbltitle.setText(BA.ObjectToCharSequence("Page 2"));
RDebugUtils.currentLine=786436;
 //BA.debugLineNum = 786436;BA.debugLine="End Sub";
return "";
}
}