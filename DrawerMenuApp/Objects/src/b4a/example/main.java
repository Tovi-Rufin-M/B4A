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
 //BA.debugLineNum = 131082;BA.debugLine="SetGradient(pnlMenu, Colors.rgb(175, 71, 210), Co";
_setgradient(mostCurrent._pnlmenu,anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (175),(int) (71),(int) (210)),anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (38),(int) (53),(int) (93)));
RDebugUtils.currentLine=131084;
 //BA.debugLineNum = 131084;BA.debugLine="items.Initialize";
mostCurrent._items.Initialize();
RDebugUtils.currentLine=131086;
 //BA.debugLineNum = 131086;BA.debugLine="CreateMenu";
_createmenu();
RDebugUtils.currentLine=131087;
 //BA.debugLineNum = 131087;BA.debugLine="ShowHome";
_showhome();
RDebugUtils.currentLine=131088;
 //BA.debugLineNum = 131088;BA.debugLine="End Sub";
return "";
}
public static String  _setgradient(anywheresoftware.b4a.objects.PanelWrapper _panel,int _color1,int _color2) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "setgradient", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "setgradient", new Object[] {_panel,_color1,_color2}));}
anywheresoftware.b4a.objects.drawable.GradientDrawable _gd = null;
RDebugUtils.currentLine=196608;
 //BA.debugLineNum = 196608;BA.debugLine="Sub SetGradient(Panel As Panel, Color1 As Int, Col";
RDebugUtils.currentLine=196609;
 //BA.debugLineNum = 196609;BA.debugLine="Dim gd As GradientDrawable";
_gd = new anywheresoftware.b4a.objects.drawable.GradientDrawable();
RDebugUtils.currentLine=196610;
 //BA.debugLineNum = 196610;BA.debugLine="gd.Initialize(\"BR_TL\", Array As Int(Color1, Color";
_gd.Initialize(BA.getEnumFromString(android.graphics.drawable.GradientDrawable.Orientation.class,"BR_TL"),new int[]{_color1,_color2});
RDebugUtils.currentLine=196611;
 //BA.debugLineNum = 196611;BA.debugLine="Panel.Background = gd";
_panel.setBackground((android.graphics.drawable.Drawable)(_gd.getObject()));
RDebugUtils.currentLine=196612;
 //BA.debugLineNum = 196612;BA.debugLine="End Sub";
return "";
}
public static String  _createmenu() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "createmenu", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "createmenu", null));}
anywheresoftware.b4a.objects.ButtonWrapper _b = null;
RDebugUtils.currentLine=458752;
 //BA.debugLineNum = 458752;BA.debugLine="Sub CreateMenu";
RDebugUtils.currentLine=458753;
 //BA.debugLineNum = 458753;BA.debugLine="Dim btnHome, btnPage1, btnPage2 As Button";
mostCurrent._btnhome = new anywheresoftware.b4a.objects.ButtonWrapper();
mostCurrent._btnpage1 = new anywheresoftware.b4a.objects.ButtonWrapper();
mostCurrent._btnpage2 = new anywheresoftware.b4a.objects.ButtonWrapper();
RDebugUtils.currentLine=458754;
 //BA.debugLineNum = 458754;BA.debugLine="btnHome.Initialize(\"btnHome\")";
mostCurrent._btnhome.Initialize(mostCurrent.activityBA,"btnHome");
RDebugUtils.currentLine=458755;
 //BA.debugLineNum = 458755;BA.debugLine="btnHome.Text = \"Home\"";
mostCurrent._btnhome.setText(BA.ObjectToCharSequence("Home"));
RDebugUtils.currentLine=458756;
 //BA.debugLineNum = 458756;BA.debugLine="btnPage1.Initialize(\"btnPage1\")";
mostCurrent._btnpage1.Initialize(mostCurrent.activityBA,"btnPage1");
RDebugUtils.currentLine=458757;
 //BA.debugLineNum = 458757;BA.debugLine="btnPage1.Text = \"Length Converter\"";
mostCurrent._btnpage1.setText(BA.ObjectToCharSequence("Length Converter"));
RDebugUtils.currentLine=458758;
 //BA.debugLineNum = 458758;BA.debugLine="btnPage2.Initialize(\"btnPage2\")";
mostCurrent._btnpage2.Initialize(mostCurrent.activityBA,"btnPage2");
RDebugUtils.currentLine=458759;
 //BA.debugLineNum = 458759;BA.debugLine="btnPage2.Text = \"Weight Converter\"";
mostCurrent._btnpage2.setText(BA.ObjectToCharSequence("Weight Converter"));
RDebugUtils.currentLine=458760;
 //BA.debugLineNum = 458760;BA.debugLine="btnPage3.Initialize(\"btnPage3\")";
mostCurrent._btnpage3.Initialize(mostCurrent.activityBA,"btnPage3");
RDebugUtils.currentLine=458761;
 //BA.debugLineNum = 458761;BA.debugLine="btnPage3.Text = \"Volume Converter\"";
mostCurrent._btnpage3.setText(BA.ObjectToCharSequence("Volume Converter"));
RDebugUtils.currentLine=458762;
 //BA.debugLineNum = 458762;BA.debugLine="btnPage4.Initialize(\"btnPage4\")";
mostCurrent._btnpage4.Initialize(mostCurrent.activityBA,"btnPage4");
RDebugUtils.currentLine=458763;
 //BA.debugLineNum = 458763;BA.debugLine="btnPage4.Text = \"Speed Converter\"";
mostCurrent._btnpage4.setText(BA.ObjectToCharSequence("Speed Converter"));
RDebugUtils.currentLine=458764;
 //BA.debugLineNum = 458764;BA.debugLine="btnPage5.Initialize(\"btnPage5\")";
mostCurrent._btnpage5.Initialize(mostCurrent.activityBA,"btnPage5");
RDebugUtils.currentLine=458765;
 //BA.debugLineNum = 458765;BA.debugLine="btnPage5.Text = \"Temperature Converter\"";
mostCurrent._btnpage5.setText(BA.ObjectToCharSequence("Temperature Converter"));
RDebugUtils.currentLine=458766;
 //BA.debugLineNum = 458766;BA.debugLine="btnPage6.Initialize(\"btnPage6\")";
mostCurrent._btnpage6.Initialize(mostCurrent.activityBA,"btnPage6");
RDebugUtils.currentLine=458767;
 //BA.debugLineNum = 458767;BA.debugLine="btnPage6.Text = \"Area Converter\"";
mostCurrent._btnpage6.setText(BA.ObjectToCharSequence("Area Converter"));
RDebugUtils.currentLine=458769;
 //BA.debugLineNum = 458769;BA.debugLine="For Each b As Button In Array(btnHome, btnPage1,";
_b = new anywheresoftware.b4a.objects.ButtonWrapper();
{
final Object[] group16 = new Object[]{(Object)(mostCurrent._btnhome.getObject()),(Object)(mostCurrent._btnpage1.getObject()),(Object)(mostCurrent._btnpage2.getObject()),(Object)(mostCurrent._btnpage3.getObject()),(Object)(mostCurrent._btnpage4.getObject()),(Object)(mostCurrent._btnpage5.getObject()),(Object)(mostCurrent._btnpage6.getObject())};
final int groupLen16 = group16.length
;int index16 = 0;
;
for (; index16 < groupLen16;index16++){
_b = (anywheresoftware.b4a.objects.ButtonWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ButtonWrapper(), (android.widget.Button)(group16[index16]));
RDebugUtils.currentLine=458770;
 //BA.debugLineNum = 458770;BA.debugLine="b.TextSize = 16";
_b.setTextSize((float) (16));
RDebugUtils.currentLine=458771;
 //BA.debugLineNum = 458771;BA.debugLine="b.Gravity = Gravity.LEFT + Gravity.CENTER_VERTIC";
_b.setGravity((int) (anywheresoftware.b4a.keywords.Common.Gravity.LEFT+anywheresoftware.b4a.keywords.Common.Gravity.CENTER_VERTICAL));
RDebugUtils.currentLine=458772;
 //BA.debugLineNum = 458772;BA.debugLine="b.Color = Colors.Transparent";
_b.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
RDebugUtils.currentLine=458773;
 //BA.debugLineNum = 458773;BA.debugLine="b.TextColor = Colors.White";
_b.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=458774;
 //BA.debugLineNum = 458774;BA.debugLine="pnlMenu.AddView(b, 10dip, 0, 240dip, 50dip)";
mostCurrent._pnlmenu.AddView((android.view.View)(_b.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (240)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
 }
};
RDebugUtils.currentLine=458776;
 //BA.debugLineNum = 458776;BA.debugLine="btnHome.Top = 120dip";
mostCurrent._btnhome.setTop(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (120)));
RDebugUtils.currentLine=458777;
 //BA.debugLineNum = 458777;BA.debugLine="btnPage1.Top = 180dip";
mostCurrent._btnpage1.setTop(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (180)));
RDebugUtils.currentLine=458778;
 //BA.debugLineNum = 458778;BA.debugLine="btnPage2.Top = 240dip";
mostCurrent._btnpage2.setTop(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (240)));
RDebugUtils.currentLine=458779;
 //BA.debugLineNum = 458779;BA.debugLine="btnPage3.Top = 300dip";
mostCurrent._btnpage3.setTop(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (300)));
RDebugUtils.currentLine=458780;
 //BA.debugLineNum = 458780;BA.debugLine="btnPage4.Top = 360dip";
mostCurrent._btnpage4.setTop(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (360)));
RDebugUtils.currentLine=458781;
 //BA.debugLineNum = 458781;BA.debugLine="btnPage5.Top = 420dip";
mostCurrent._btnpage5.setTop(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (420)));
RDebugUtils.currentLine=458782;
 //BA.debugLineNum = 458782;BA.debugLine="btnPage6.Top = 480dip";
mostCurrent._btnpage6.setTop(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (480)));
RDebugUtils.currentLine=458783;
 //BA.debugLineNum = 458783;BA.debugLine="End Sub";
return "";
}
public static String  _showhome() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "showhome", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "showhome", null));}
RDebugUtils.currentLine=1376256;
 //BA.debugLineNum = 1376256;BA.debugLine="Sub ShowHome";
RDebugUtils.currentLine=1376257;
 //BA.debugLineNum = 1376257;BA.debugLine="pnlMain.RemoveAllViews";
mostCurrent._pnlmain.RemoveAllViews();
RDebugUtils.currentLine=1376258;
 //BA.debugLineNum = 1376258;BA.debugLine="pnlMain.LoadLayout(\"home\")";
mostCurrent._pnlmain.LoadLayout("home",mostCurrent.activityBA);
RDebugUtils.currentLine=1376259;
 //BA.debugLineNum = 1376259;BA.debugLine="lblTitle.Text = \"Home\"";
mostCurrent._lbltitle.setText(BA.ObjectToCharSequence("Home"));
RDebugUtils.currentLine=1376260;
 //BA.debugLineNum = 1376260;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_keypress", false))
	 {return ((Boolean) Debug.delegate(mostCurrent.activityBA, "activity_keypress", new Object[] {_keycode}));}
RDebugUtils.currentLine=2031616;
 //BA.debugLineNum = 2031616;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
RDebugUtils.currentLine=2031617;
 //BA.debugLineNum = 2031617;BA.debugLine="If KeyCode = KeyCodes.KEYCODE_BACK And Drawer.Lef";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK && mostCurrent._drawer._getleftopen /*boolean*/ (null)) { 
RDebugUtils.currentLine=2031618;
 //BA.debugLineNum = 2031618;BA.debugLine="Drawer.LeftOpen = False";
mostCurrent._drawer._setleftopen /*boolean*/ (null,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2031619;
 //BA.debugLineNum = 2031619;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 }else 
{RDebugUtils.currentLine=2031620;
 //BA.debugLineNum = 2031620;BA.debugLine="Else If KeyCode = KeyCodes.KEYCODE_BACK And Not(D";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK && anywheresoftware.b4a.keywords.Common.Not(mostCurrent._drawer._getleftopen /*boolean*/ (null))) { 
RDebugUtils.currentLine=2031621;
 //BA.debugLineNum = 2031621;BA.debugLine="Drawer.LeftOpen = True";
mostCurrent._drawer._setleftopen /*boolean*/ (null,anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=2031622;
 //BA.debugLineNum = 2031622;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 }}
;
RDebugUtils.currentLine=2031625;
 //BA.debugLineNum = 2031625;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
RDebugUtils.currentLine=2031626;
 //BA.debugLineNum = 2031626;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
RDebugUtils.currentModule="main";
RDebugUtils.currentLine=327680;
 //BA.debugLineNum = 327680;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
RDebugUtils.currentLine=327682;
 //BA.debugLineNum = 327682;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_resume", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_resume", null));}
RDebugUtils.currentLine=262144;
 //BA.debugLineNum = 262144;BA.debugLine="Sub Activity_Resume";
RDebugUtils.currentLine=262146;
 //BA.debugLineNum = 262146;BA.debugLine="End Sub";
return "";
}
public static String  _btnhome_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btnhome_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btnhome_click", null));}
RDebugUtils.currentLine=917504;
 //BA.debugLineNum = 917504;BA.debugLine="Sub btnHome_Click";
RDebugUtils.currentLine=917505;
 //BA.debugLineNum = 917505;BA.debugLine="Drawer.LeftOpen = False";
mostCurrent._drawer._setleftopen /*boolean*/ (null,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917506;
 //BA.debugLineNum = 917506;BA.debugLine="ShowHome";
_showhome();
RDebugUtils.currentLine=917507;
 //BA.debugLineNum = 917507;BA.debugLine="End Sub";
return "";
}
public static String  _btnmenu_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btnmenu_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btnmenu_click", null));}
RDebugUtils.currentLine=393216;
 //BA.debugLineNum = 393216;BA.debugLine="Sub btnMenu_Click";
RDebugUtils.currentLine=393217;
 //BA.debugLineNum = 393217;BA.debugLine="Drawer.LeftOpen = Not(Drawer.LeftOpen)";
mostCurrent._drawer._setleftopen /*boolean*/ (null,anywheresoftware.b4a.keywords.Common.Not(mostCurrent._drawer._getleftopen /*boolean*/ (null)));
RDebugUtils.currentLine=393218;
 //BA.debugLineNum = 393218;BA.debugLine="End Sub";
return "";
}
public static String  _btnpage1_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btnpage1_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btnpage1_click", null));}
RDebugUtils.currentLine=983040;
 //BA.debugLineNum = 983040;BA.debugLine="Sub btnPage1_Click";
RDebugUtils.currentLine=983041;
 //BA.debugLineNum = 983041;BA.debugLine="Drawer.LeftOpen = False";
mostCurrent._drawer._setleftopen /*boolean*/ (null,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=983042;
 //BA.debugLineNum = 983042;BA.debugLine="ShowPage1";
_showpage1();
RDebugUtils.currentLine=983043;
 //BA.debugLineNum = 983043;BA.debugLine="End Sub";
return "";
}
public static String  _showpage1() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "showpage1", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "showpage1", null));}
RDebugUtils.currentLine=1507328;
 //BA.debugLineNum = 1507328;BA.debugLine="Sub ShowPage1";
RDebugUtils.currentLine=1507329;
 //BA.debugLineNum = 1507329;BA.debugLine="pnlMain.RemoveAllViews";
mostCurrent._pnlmain.RemoveAllViews();
RDebugUtils.currentLine=1507330;
 //BA.debugLineNum = 1507330;BA.debugLine="pnlMain.LoadLayout(\"page1\")";
mostCurrent._pnlmain.LoadLayout("page1",mostCurrent.activityBA);
RDebugUtils.currentLine=1507331;
 //BA.debugLineNum = 1507331;BA.debugLine="lblTitle.Text = \"Length Converter\"";
mostCurrent._lbltitle.setText(BA.ObjectToCharSequence("Length Converter"));
RDebugUtils.currentLine=1507333;
 //BA.debugLineNum = 1507333;BA.debugLine="items.Clear";
mostCurrent._items.Clear();
RDebugUtils.currentLine=1507334;
 //BA.debugLineNum = 1507334;BA.debugLine="items.AddAll(Array As String(\"mm\",\"cm\",\"m\",\"km\",\"";
mostCurrent._items.AddAll(anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{"mm","cm","m","km","in","ft","yd","mi","µm"}));
RDebugUtils.currentLine=1507335;
 //BA.debugLineNum = 1507335;BA.debugLine="setspinner(items, Colors.ARGB(64, 255, 255, 255))";
_setspinner(mostCurrent._items,(Object)(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (64),(int) (255),(int) (255),(int) (255))));
RDebugUtils.currentLine=1507336;
 //BA.debugLineNum = 1507336;BA.debugLine="End Sub";
return "";
}
public static String  _btnpage2_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btnpage2_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btnpage2_click", null));}
RDebugUtils.currentLine=1048576;
 //BA.debugLineNum = 1048576;BA.debugLine="Sub btnPage2_Click";
RDebugUtils.currentLine=1048577;
 //BA.debugLineNum = 1048577;BA.debugLine="Drawer.LeftOpen = False";
mostCurrent._drawer._setleftopen /*boolean*/ (null,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1048578;
 //BA.debugLineNum = 1048578;BA.debugLine="ShowPage2";
_showpage2();
RDebugUtils.currentLine=1048579;
 //BA.debugLineNum = 1048579;BA.debugLine="End Sub";
return "";
}
public static String  _showpage2() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "showpage2", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "showpage2", null));}
RDebugUtils.currentLine=1572864;
 //BA.debugLineNum = 1572864;BA.debugLine="Sub ShowPage2";
RDebugUtils.currentLine=1572865;
 //BA.debugLineNum = 1572865;BA.debugLine="pnlMain.RemoveAllViews";
mostCurrent._pnlmain.RemoveAllViews();
RDebugUtils.currentLine=1572866;
 //BA.debugLineNum = 1572866;BA.debugLine="pnlMain.LoadLayout(\"page2\")";
mostCurrent._pnlmain.LoadLayout("page2",mostCurrent.activityBA);
RDebugUtils.currentLine=1572867;
 //BA.debugLineNum = 1572867;BA.debugLine="lblTitle.Text = \"Wight Converter\"";
mostCurrent._lbltitle.setText(BA.ObjectToCharSequence("Wight Converter"));
RDebugUtils.currentLine=1572869;
 //BA.debugLineNum = 1572869;BA.debugLine="items.Clear";
mostCurrent._items.Clear();
RDebugUtils.currentLine=1572870;
 //BA.debugLineNum = 1572870;BA.debugLine="items.AddAll(Array As String(\"mg\",\"g\",\"kg\",\"t\",\"o";
mostCurrent._items.AddAll(anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{"mg","g","kg","t","oz","lb","st","ct","µg","ton"}));
RDebugUtils.currentLine=1572871;
 //BA.debugLineNum = 1572871;BA.debugLine="setspinner(items, Colors.ARGB(64, 255, 255, 255))";
_setspinner(mostCurrent._items,(Object)(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (64),(int) (255),(int) (255),(int) (255))));
RDebugUtils.currentLine=1572872;
 //BA.debugLineNum = 1572872;BA.debugLine="End Sub";
return "";
}
public static String  _btnpage3_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btnpage3_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btnpage3_click", null));}
RDebugUtils.currentLine=1114112;
 //BA.debugLineNum = 1114112;BA.debugLine="Sub btnPage3_Click";
RDebugUtils.currentLine=1114113;
 //BA.debugLineNum = 1114113;BA.debugLine="Drawer.LeftOpen = False";
mostCurrent._drawer._setleftopen /*boolean*/ (null,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114114;
 //BA.debugLineNum = 1114114;BA.debugLine="ShowPage3";
_showpage3();
RDebugUtils.currentLine=1114115;
 //BA.debugLineNum = 1114115;BA.debugLine="End Sub";
return "";
}
public static String  _showpage3() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "showpage3", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "showpage3", null));}
RDebugUtils.currentLine=1638400;
 //BA.debugLineNum = 1638400;BA.debugLine="Sub ShowPage3";
RDebugUtils.currentLine=1638401;
 //BA.debugLineNum = 1638401;BA.debugLine="pnlMain.RemoveAllViews";
mostCurrent._pnlmain.RemoveAllViews();
RDebugUtils.currentLine=1638402;
 //BA.debugLineNum = 1638402;BA.debugLine="pnlMain.LoadLayout(\"page3\")";
mostCurrent._pnlmain.LoadLayout("page3",mostCurrent.activityBA);
RDebugUtils.currentLine=1638403;
 //BA.debugLineNum = 1638403;BA.debugLine="lblTitle.Text = \"Volume Converter\"";
mostCurrent._lbltitle.setText(BA.ObjectToCharSequence("Volume Converter"));
RDebugUtils.currentLine=1638405;
 //BA.debugLineNum = 1638405;BA.debugLine="items.Clear";
mostCurrent._items.Clear();
RDebugUtils.currentLine=1638406;
 //BA.debugLineNum = 1638406;BA.debugLine="items.AddAll(Array As String(\"mL\",\"L\",\"kL\",\"cm³\",";
mostCurrent._items.AddAll(anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{"mL","L","kL","cm³","m³","in³","ft³"}));
RDebugUtils.currentLine=1638407;
 //BA.debugLineNum = 1638407;BA.debugLine="setspinner(items, Colors.ARGB(64, 255, 255, 255))";
_setspinner(mostCurrent._items,(Object)(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (64),(int) (255),(int) (255),(int) (255))));
RDebugUtils.currentLine=1638408;
 //BA.debugLineNum = 1638408;BA.debugLine="End Sub";
return "";
}
public static String  _btnpage4_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btnpage4_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btnpage4_click", null));}
RDebugUtils.currentLine=1179648;
 //BA.debugLineNum = 1179648;BA.debugLine="Sub btnPage4_Click";
RDebugUtils.currentLine=1179649;
 //BA.debugLineNum = 1179649;BA.debugLine="Drawer.LeftOpen = False";
mostCurrent._drawer._setleftopen /*boolean*/ (null,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1179650;
 //BA.debugLineNum = 1179650;BA.debugLine="ShowPage4";
_showpage4();
RDebugUtils.currentLine=1179651;
 //BA.debugLineNum = 1179651;BA.debugLine="End Sub";
return "";
}
public static String  _showpage4() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "showpage4", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "showpage4", null));}
RDebugUtils.currentLine=1703936;
 //BA.debugLineNum = 1703936;BA.debugLine="Sub ShowPage4";
RDebugUtils.currentLine=1703937;
 //BA.debugLineNum = 1703937;BA.debugLine="pnlMain.RemoveAllViews";
mostCurrent._pnlmain.RemoveAllViews();
RDebugUtils.currentLine=1703938;
 //BA.debugLineNum = 1703938;BA.debugLine="pnlMain.LoadLayout(\"page4\")";
mostCurrent._pnlmain.LoadLayout("page4",mostCurrent.activityBA);
RDebugUtils.currentLine=1703939;
 //BA.debugLineNum = 1703939;BA.debugLine="lblTitle.Text = \"Speed Converter\"";
mostCurrent._lbltitle.setText(BA.ObjectToCharSequence("Speed Converter"));
RDebugUtils.currentLine=1703941;
 //BA.debugLineNum = 1703941;BA.debugLine="items.Clear";
mostCurrent._items.Clear();
RDebugUtils.currentLine=1703942;
 //BA.debugLineNum = 1703942;BA.debugLine="items.AddAll(Array As String(\"m/s\",\"km/h\",\"mph\",\"";
mostCurrent._items.AddAll(anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{"m/s","km/h","mph","knot","ft/s"}));
RDebugUtils.currentLine=1703943;
 //BA.debugLineNum = 1703943;BA.debugLine="setspinner(items, Colors.ARGB(64, 255, 255, 255))";
_setspinner(mostCurrent._items,(Object)(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (64),(int) (255),(int) (255),(int) (255))));
RDebugUtils.currentLine=1703944;
 //BA.debugLineNum = 1703944;BA.debugLine="End Sub";
return "";
}
public static String  _btnpage5_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btnpage5_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btnpage5_click", null));}
RDebugUtils.currentLine=1245184;
 //BA.debugLineNum = 1245184;BA.debugLine="Sub btnPage5_Click";
RDebugUtils.currentLine=1245185;
 //BA.debugLineNum = 1245185;BA.debugLine="Drawer.LeftOpen = False";
mostCurrent._drawer._setleftopen /*boolean*/ (null,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1245186;
 //BA.debugLineNum = 1245186;BA.debugLine="ShowPage5";
_showpage5();
RDebugUtils.currentLine=1245187;
 //BA.debugLineNum = 1245187;BA.debugLine="End Sub";
return "";
}
public static String  _showpage5() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "showpage5", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "showpage5", null));}
RDebugUtils.currentLine=1769472;
 //BA.debugLineNum = 1769472;BA.debugLine="Sub ShowPage5";
RDebugUtils.currentLine=1769473;
 //BA.debugLineNum = 1769473;BA.debugLine="pnlMain.RemoveAllViews";
mostCurrent._pnlmain.RemoveAllViews();
RDebugUtils.currentLine=1769474;
 //BA.debugLineNum = 1769474;BA.debugLine="pnlMain.LoadLayout(\"page5\")";
mostCurrent._pnlmain.LoadLayout("page5",mostCurrent.activityBA);
RDebugUtils.currentLine=1769475;
 //BA.debugLineNum = 1769475;BA.debugLine="lblTitle.Text = \"Temperature Converter\"";
mostCurrent._lbltitle.setText(BA.ObjectToCharSequence("Temperature Converter"));
RDebugUtils.currentLine=1769477;
 //BA.debugLineNum = 1769477;BA.debugLine="items.Clear";
mostCurrent._items.Clear();
RDebugUtils.currentLine=1769478;
 //BA.debugLineNum = 1769478;BA.debugLine="items.AddAll(Array As String(\"°C\",\"°F\",\"K\",\"°R\"))";
mostCurrent._items.AddAll(anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{"°C","°F","K","°R"}));
RDebugUtils.currentLine=1769479;
 //BA.debugLineNum = 1769479;BA.debugLine="setspinner(items, Colors.ARGB(64, 255, 255, 255))";
_setspinner(mostCurrent._items,(Object)(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (64),(int) (255),(int) (255),(int) (255))));
RDebugUtils.currentLine=1769480;
 //BA.debugLineNum = 1769480;BA.debugLine="End Sub";
return "";
}
public static String  _btnpage6_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btnpage6_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btnpage6_click", null));}
RDebugUtils.currentLine=1310720;
 //BA.debugLineNum = 1310720;BA.debugLine="Sub btnPage6_Click";
RDebugUtils.currentLine=1310721;
 //BA.debugLineNum = 1310721;BA.debugLine="Drawer.LeftOpen = False";
mostCurrent._drawer._setleftopen /*boolean*/ (null,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1310722;
 //BA.debugLineNum = 1310722;BA.debugLine="ShowPage6";
_showpage6();
RDebugUtils.currentLine=1310723;
 //BA.debugLineNum = 1310723;BA.debugLine="End Sub";
return "";
}
public static String  _showpage6() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "showpage6", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "showpage6", null));}
RDebugUtils.currentLine=1835008;
 //BA.debugLineNum = 1835008;BA.debugLine="Sub ShowPage6";
RDebugUtils.currentLine=1835009;
 //BA.debugLineNum = 1835009;BA.debugLine="pnlMain.RemoveAllViews";
mostCurrent._pnlmain.RemoveAllViews();
RDebugUtils.currentLine=1835010;
 //BA.debugLineNum = 1835010;BA.debugLine="pnlMain.LoadLayout(\"page6\")";
mostCurrent._pnlmain.LoadLayout("page6",mostCurrent.activityBA);
RDebugUtils.currentLine=1835011;
 //BA.debugLineNum = 1835011;BA.debugLine="lblTitle.Text = \"Area Converter\"";
mostCurrent._lbltitle.setText(BA.ObjectToCharSequence("Area Converter"));
RDebugUtils.currentLine=1835013;
 //BA.debugLineNum = 1835013;BA.debugLine="items.Clear";
mostCurrent._items.Clear();
RDebugUtils.currentLine=1835014;
 //BA.debugLineNum = 1835014;BA.debugLine="items.AddAll(Array As String(\"mm²\",\"cm²\",\"m²\",\"km";
mostCurrent._items.AddAll(anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{"mm²","cm²","m²","km²","in²","ft²","yd²"}));
RDebugUtils.currentLine=1835015;
 //BA.debugLineNum = 1835015;BA.debugLine="setspinner(items, Colors.ARGB(64, 255, 255, 255))";
_setspinner(mostCurrent._items,(Object)(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (64),(int) (255),(int) (255),(int) (255))));
RDebugUtils.currentLine=1835016;
 //BA.debugLineNum = 1835016;BA.debugLine="End Sub";
return "";
}
public static String  _convert() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "convert", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "convert", null));}
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
RDebugUtils.currentLine=1966080;
 //BA.debugLineNum = 1966080;BA.debugLine="Sub convert";
RDebugUtils.currentLine=1966081;
 //BA.debugLineNum = 1966081;BA.debugLine="Dim fromUnit, toUnit As String";
_fromunit = "";
_tounit = "";
RDebugUtils.currentLine=1966082;
 //BA.debugLineNum = 1966082;BA.debugLine="Dim result As String";
_result = "";
RDebugUtils.currentLine=1966083;
 //BA.debugLineNum = 1966083;BA.debugLine="Dim ans As Double";
_ans = 0;
RDebugUtils.currentLine=1966084;
 //BA.debugLineNum = 1966084;BA.debugLine="Dim input As Double";
_input = 0;
RDebugUtils.currentLine=1966085;
 //BA.debugLineNum = 1966085;BA.debugLine="Dim choice As String = lblTitle.Text";
_choice = mostCurrent._lbltitle.getText();
RDebugUtils.currentLine=1966087;
 //BA.debugLineNum = 1966087;BA.debugLine="If EditText2.Text = \"\" Then";
if ((mostCurrent._edittext2.getText()).equals("")) { 
RDebugUtils.currentLine=1966088;
 //BA.debugLineNum = 1966088;BA.debugLine="input = 0";
_input = 0;
 }else {
RDebugUtils.currentLine=1966090;
 //BA.debugLineNum = 1966090;BA.debugLine="input = EditText2.Text";
_input = (double)(Double.parseDouble(mostCurrent._edittext2.getText()));
 };
RDebugUtils.currentLine=1966093;
 //BA.debugLineNum = 1966093;BA.debugLine="fromUnit = Spinner2.SelectedItem";
_fromunit = mostCurrent._spinner2.getSelectedItem();
RDebugUtils.currentLine=1966094;
 //BA.debugLineNum = 1966094;BA.debugLine="toUnit = Spinner3.SelectedItem";
_tounit = mostCurrent._spinner3.getSelectedItem();
RDebugUtils.currentLine=1966096;
 //BA.debugLineNum = 1966096;BA.debugLine="Select choice";
switch (BA.switchObjectToInt(_choice,"Length Converter","Weight Converter","Volume Converter","Speed Converter","Temperature Converter","Area Converter")) {
case 0: {
RDebugUtils.currentLine=1966098;
 //BA.debugLineNum = 1966098;BA.debugLine="Dim toMeter As Map";
_tometer = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=1966099;
 //BA.debugLineNum = 1966099;BA.debugLine="toMeter.Initialize";
_tometer.Initialize();
RDebugUtils.currentLine=1966100;
 //BA.debugLineNum = 1966100;BA.debugLine="toMeter.Put(\"mm\", 0.001)      : toMeter.Put(\"cm";
_tometer.Put((Object)("mm"),(Object)(0.001));
RDebugUtils.currentLine=1966100;
 //BA.debugLineNum = 1966100;BA.debugLine="toMeter.Put(\"mm\", 0.001)      : toMeter.Put(\"cm";
_tometer.Put((Object)("cm"),(Object)(0.01));
RDebugUtils.currentLine=1966101;
 //BA.debugLineNum = 1966101;BA.debugLine="toMeter.Put(\"m\", 1)           : toMeter.Put(\"km";
_tometer.Put((Object)("m"),(Object)(1));
RDebugUtils.currentLine=1966101;
 //BA.debugLineNum = 1966101;BA.debugLine="toMeter.Put(\"m\", 1)           : toMeter.Put(\"km";
_tometer.Put((Object)("km"),(Object)(1000));
RDebugUtils.currentLine=1966102;
 //BA.debugLineNum = 1966102;BA.debugLine="toMeter.Put(\"in\", 0.0254)     : toMeter.Put(\"ft";
_tometer.Put((Object)("in"),(Object)(0.0254));
RDebugUtils.currentLine=1966102;
 //BA.debugLineNum = 1966102;BA.debugLine="toMeter.Put(\"in\", 0.0254)     : toMeter.Put(\"ft";
_tometer.Put((Object)("ft"),(Object)(0.3048));
RDebugUtils.currentLine=1966103;
 //BA.debugLineNum = 1966103;BA.debugLine="toMeter.Put(\"yd\", 0.9144)     : toMeter.Put(\"mi";
_tometer.Put((Object)("yd"),(Object)(0.9144));
RDebugUtils.currentLine=1966103;
 //BA.debugLineNum = 1966103;BA.debugLine="toMeter.Put(\"yd\", 0.9144)     : toMeter.Put(\"mi";
_tometer.Put((Object)("mi"),(Object)(1609.344));
RDebugUtils.currentLine=1966104;
 //BA.debugLineNum = 1966104;BA.debugLine="toMeter.Put(\"µm\", 0.000001)";
_tometer.Put((Object)("µm"),(Object)(0.000001));
RDebugUtils.currentLine=1966105;
 //BA.debugLineNum = 1966105;BA.debugLine="ans = input * toMeter.Get(fromUnit) / toMeter.G";
_ans = _input*(double)(BA.ObjectToNumber(_tometer.Get((Object)(_fromunit))))/(double)(double)(BA.ObjectToNumber(_tometer.Get((Object)(_tounit))));
 break; }
case 1: {
RDebugUtils.currentLine=1966108;
 //BA.debugLineNum = 1966108;BA.debugLine="Dim toGram As Map";
_togram = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=1966109;
 //BA.debugLineNum = 1966109;BA.debugLine="toGram.Initialize";
_togram.Initialize();
RDebugUtils.currentLine=1966110;
 //BA.debugLineNum = 1966110;BA.debugLine="toGram.Put(\"µg\", 0.000001)    : toGram.Put(\"mg\"";
_togram.Put((Object)("µg"),(Object)(0.000001));
RDebugUtils.currentLine=1966110;
 //BA.debugLineNum = 1966110;BA.debugLine="toGram.Put(\"µg\", 0.000001)    : toGram.Put(\"mg\"";
_togram.Put((Object)("mg"),(Object)(0.001));
RDebugUtils.currentLine=1966111;
 //BA.debugLineNum = 1966111;BA.debugLine="toGram.Put(\"g\", 1)            : toGram.Put(\"kg\"";
_togram.Put((Object)("g"),(Object)(1));
RDebugUtils.currentLine=1966111;
 //BA.debugLineNum = 1966111;BA.debugLine="toGram.Put(\"g\", 1)            : toGram.Put(\"kg\"";
_togram.Put((Object)("kg"),(Object)(1000));
RDebugUtils.currentLine=1966112;
 //BA.debugLineNum = 1966112;BA.debugLine="toGram.Put(\"t\", 1000000)      : toGram.Put(\"oz\"";
_togram.Put((Object)("t"),(Object)(1000000));
RDebugUtils.currentLine=1966112;
 //BA.debugLineNum = 1966112;BA.debugLine="toGram.Put(\"t\", 1000000)      : toGram.Put(\"oz\"";
_togram.Put((Object)("oz"),(Object)(28.3495));
RDebugUtils.currentLine=1966113;
 //BA.debugLineNum = 1966113;BA.debugLine="toGram.Put(\"lb\", 453.592)     : toGram.Put(\"st\"";
_togram.Put((Object)("lb"),(Object)(453.592));
RDebugUtils.currentLine=1966113;
 //BA.debugLineNum = 1966113;BA.debugLine="toGram.Put(\"lb\", 453.592)     : toGram.Put(\"st\"";
_togram.Put((Object)("st"),(Object)(6350.29));
RDebugUtils.currentLine=1966114;
 //BA.debugLineNum = 1966114;BA.debugLine="toGram.Put(\"ct\", 0.2)         : toGram.Put(\"ton";
_togram.Put((Object)("ct"),(Object)(0.2));
RDebugUtils.currentLine=1966114;
 //BA.debugLineNum = 1966114;BA.debugLine="toGram.Put(\"ct\", 0.2)         : toGram.Put(\"ton";
_togram.Put((Object)("ton"),(Object)(907185));
RDebugUtils.currentLine=1966115;
 //BA.debugLineNum = 1966115;BA.debugLine="ans = input * toGram.Get(fromUnit) / toGram.Get";
_ans = _input*(double)(BA.ObjectToNumber(_togram.Get((Object)(_fromunit))))/(double)(double)(BA.ObjectToNumber(_togram.Get((Object)(_tounit))));
 break; }
case 2: {
RDebugUtils.currentLine=1966118;
 //BA.debugLineNum = 1966118;BA.debugLine="Dim toML As Map";
_toml = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=1966119;
 //BA.debugLineNum = 1966119;BA.debugLine="toML.Initialize";
_toml.Initialize();
RDebugUtils.currentLine=1966120;
 //BA.debugLineNum = 1966120;BA.debugLine="toML.Put(\"mL\", 1)             : toML.Put(\"L\", 1";
_toml.Put((Object)("mL"),(Object)(1));
RDebugUtils.currentLine=1966120;
 //BA.debugLineNum = 1966120;BA.debugLine="toML.Put(\"mL\", 1)             : toML.Put(\"L\", 1";
_toml.Put((Object)("L"),(Object)(1000));
RDebugUtils.currentLine=1966121;
 //BA.debugLineNum = 1966121;BA.debugLine="toML.Put(\"kL\", 1000000)       : toML.Put(\"cm³\",";
_toml.Put((Object)("kL"),(Object)(1000000));
RDebugUtils.currentLine=1966121;
 //BA.debugLineNum = 1966121;BA.debugLine="toML.Put(\"kL\", 1000000)       : toML.Put(\"cm³\",";
_toml.Put((Object)("cm³"),(Object)(1));
RDebugUtils.currentLine=1966122;
 //BA.debugLineNum = 1966122;BA.debugLine="toML.Put(\"m³\", 1000000)       : toML.Put(\"in³\",";
_toml.Put((Object)("m³"),(Object)(1000000));
RDebugUtils.currentLine=1966122;
 //BA.debugLineNum = 1966122;BA.debugLine="toML.Put(\"m³\", 1000000)       : toML.Put(\"in³\",";
_toml.Put((Object)("in³"),(Object)(16.3871));
RDebugUtils.currentLine=1966123;
 //BA.debugLineNum = 1966123;BA.debugLine="toML.Put(\"ft³\", 28316.8)";
_toml.Put((Object)("ft³"),(Object)(28316.8));
RDebugUtils.currentLine=1966124;
 //BA.debugLineNum = 1966124;BA.debugLine="ans = input * toML.Get(fromUnit) / toML.Get(toU";
_ans = _input*(double)(BA.ObjectToNumber(_toml.Get((Object)(_fromunit))))/(double)(double)(BA.ObjectToNumber(_toml.Get((Object)(_tounit))));
 break; }
case 3: {
RDebugUtils.currentLine=1966127;
 //BA.debugLineNum = 1966127;BA.debugLine="Dim toMS As Map";
_toms = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=1966128;
 //BA.debugLineNum = 1966128;BA.debugLine="toMS.Initialize";
_toms.Initialize();
RDebugUtils.currentLine=1966129;
 //BA.debugLineNum = 1966129;BA.debugLine="toMS.Put(\"m/s\", 1)            : toMS.Put(\"km/h\"";
_toms.Put((Object)("m/s"),(Object)(1));
RDebugUtils.currentLine=1966129;
 //BA.debugLineNum = 1966129;BA.debugLine="toMS.Put(\"m/s\", 1)            : toMS.Put(\"km/h\"";
_toms.Put((Object)("km/h"),(Object)(0.277778));
RDebugUtils.currentLine=1966130;
 //BA.debugLineNum = 1966130;BA.debugLine="toMS.Put(\"mph\", 0.44704)      : toMS.Put(\"knot\"";
_toms.Put((Object)("mph"),(Object)(0.44704));
RDebugUtils.currentLine=1966130;
 //BA.debugLineNum = 1966130;BA.debugLine="toMS.Put(\"mph\", 0.44704)      : toMS.Put(\"knot\"";
_toms.Put((Object)("knot"),(Object)(0.514444));
RDebugUtils.currentLine=1966131;
 //BA.debugLineNum = 1966131;BA.debugLine="toMS.Put(\"ft/s\", 0.3048)";
_toms.Put((Object)("ft/s"),(Object)(0.3048));
RDebugUtils.currentLine=1966132;
 //BA.debugLineNum = 1966132;BA.debugLine="ans = input * toMS.Get(fromUnit) / toMS.Get(toU";
_ans = _input*(double)(BA.ObjectToNumber(_toms.Get((Object)(_fromunit))))/(double)(double)(BA.ObjectToNumber(_toms.Get((Object)(_tounit))));
 break; }
case 4: {
RDebugUtils.currentLine=1966135;
 //BA.debugLineNum = 1966135;BA.debugLine="Dim celsius As Double";
_celsius = 0;
RDebugUtils.currentLine=1966136;
 //BA.debugLineNum = 1966136;BA.debugLine="Select fromUnit";
switch (BA.switchObjectToInt(_fromunit,"°C","°F","K","°R")) {
case 0: {
RDebugUtils.currentLine=1966137;
 //BA.debugLineNum = 1966137;BA.debugLine="Case \"°C\" : celsius = input";
_celsius = _input;
 break; }
case 1: {
RDebugUtils.currentLine=1966138;
 //BA.debugLineNum = 1966138;BA.debugLine="Case \"°F\" : celsius = (input - 32) / 1.8";
_celsius = (_input-32)/(double)1.8;
 break; }
case 2: {
RDebugUtils.currentLine=1966139;
 //BA.debugLineNum = 1966139;BA.debugLine="Case \"K\"  : celsius = input - 273.15";
_celsius = _input-273.15;
 break; }
case 3: {
RDebugUtils.currentLine=1966140;
 //BA.debugLineNum = 1966140;BA.debugLine="Case \"°R\" : celsius = (input - 491.67) / 1.8";
_celsius = (_input-491.67)/(double)1.8;
 break; }
}
;
RDebugUtils.currentLine=1966142;
 //BA.debugLineNum = 1966142;BA.debugLine="Select toUnit";
switch (BA.switchObjectToInt(_tounit,"°C","°F","K","°R")) {
case 0: {
RDebugUtils.currentLine=1966143;
 //BA.debugLineNum = 1966143;BA.debugLine="Case \"°C\" : ans = celsius";
_ans = _celsius;
 break; }
case 1: {
RDebugUtils.currentLine=1966144;
 //BA.debugLineNum = 1966144;BA.debugLine="Case \"°F\" : ans = (celsius * 1.8) + 32";
_ans = (_celsius*1.8)+32;
 break; }
case 2: {
RDebugUtils.currentLine=1966145;
 //BA.debugLineNum = 1966145;BA.debugLine="Case \"K\"  : ans = celsius + 273.15";
_ans = _celsius+273.15;
 break; }
case 3: {
RDebugUtils.currentLine=1966146;
 //BA.debugLineNum = 1966146;BA.debugLine="Case \"°R\" : ans = (celsius + 273.15) * 1.8";
_ans = (_celsius+273.15)*1.8;
 break; }
}
;
 break; }
case 5: {
RDebugUtils.currentLine=1966150;
 //BA.debugLineNum = 1966150;BA.debugLine="Dim toM2 As Map";
_tom2 = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=1966151;
 //BA.debugLineNum = 1966151;BA.debugLine="toM2.Initialize";
_tom2.Initialize();
RDebugUtils.currentLine=1966152;
 //BA.debugLineNum = 1966152;BA.debugLine="toM2.Put(\"mm²\", 0.000001)     : toM2.Put(\"cm²\",";
_tom2.Put((Object)("mm²"),(Object)(0.000001));
RDebugUtils.currentLine=1966152;
 //BA.debugLineNum = 1966152;BA.debugLine="toM2.Put(\"mm²\", 0.000001)     : toM2.Put(\"cm²\",";
_tom2.Put((Object)("cm²"),(Object)(0.0001));
RDebugUtils.currentLine=1966153;
 //BA.debugLineNum = 1966153;BA.debugLine="toM2.Put(\"m²\", 1)             : toM2.Put(\"km²\",";
_tom2.Put((Object)("m²"),(Object)(1));
RDebugUtils.currentLine=1966153;
 //BA.debugLineNum = 1966153;BA.debugLine="toM2.Put(\"m²\", 1)             : toM2.Put(\"km²\",";
_tom2.Put((Object)("km²"),(Object)(1000000));
RDebugUtils.currentLine=1966154;
 //BA.debugLineNum = 1966154;BA.debugLine="toM2.Put(\"in²\", 0.00064516)   : toM2.Put(\"ft²\",";
_tom2.Put((Object)("in²"),(Object)(0.00064516));
RDebugUtils.currentLine=1966154;
 //BA.debugLineNum = 1966154;BA.debugLine="toM2.Put(\"in²\", 0.00064516)   : toM2.Put(\"ft²\",";
_tom2.Put((Object)("ft²"),(Object)(0.092903));
RDebugUtils.currentLine=1966155;
 //BA.debugLineNum = 1966155;BA.debugLine="toM2.Put(\"yd²\", 0.836127)";
_tom2.Put((Object)("yd²"),(Object)(0.836127));
RDebugUtils.currentLine=1966156;
 //BA.debugLineNum = 1966156;BA.debugLine="ans = input * toM2.Get(fromUnit) / toM2.Get(toU";
_ans = _input*(double)(BA.ObjectToNumber(_tom2.Get((Object)(_fromunit))))/(double)(double)(BA.ObjectToNumber(_tom2.Get((Object)(_tounit))));
 break; }
default: {
RDebugUtils.currentLine=1966159;
 //BA.debugLineNum = 1966159;BA.debugLine="ans = 0";
_ans = 0;
 break; }
}
;
RDebugUtils.currentLine=1966162;
 //BA.debugLineNum = 1966162;BA.debugLine="result = NumberFormat(ans, 1, 4) & \" \" & toUnit";
_result = anywheresoftware.b4a.keywords.Common.NumberFormat(_ans,(int) (1),(int) (4))+" "+_tounit;
RDebugUtils.currentLine=1966163;
 //BA.debugLineNum = 1966163;BA.debugLine="Label6.Text = result";
mostCurrent._label6.setText(BA.ObjectToCharSequence(_result));
RDebugUtils.currentLine=1966164;
 //BA.debugLineNum = 1966164;BA.debugLine="End Sub";
return "";
}
public static String  _edittext1_enterpressed() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "edittext1_enterpressed", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "edittext1_enterpressed", null));}
RDebugUtils.currentLine=2359296;
 //BA.debugLineNum = 2359296;BA.debugLine="Private Sub EditText1_EnterPressed";
RDebugUtils.currentLine=2359297;
 //BA.debugLineNum = 2359297;BA.debugLine="qick";
_qick();
RDebugUtils.currentLine=2359298;
 //BA.debugLineNum = 2359298;BA.debugLine="End Sub";
return "";
}
public static String  _qick() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "qick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "qick", null));}
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
RDebugUtils.currentLine=1900544;
 //BA.debugLineNum = 1900544;BA.debugLine="Sub qick";
RDebugUtils.currentLine=1900545;
 //BA.debugLineNum = 1900545;BA.debugLine="Dim spin As String = Spinner1.SelectedItem";
_spin = mostCurrent._spinner1.getSelectedItem();
RDebugUtils.currentLine=1900546;
 //BA.debugLineNum = 1900546;BA.debugLine="Dim choice As String = lblTitle.Text";
_choice = mostCurrent._lbltitle.getText();
RDebugUtils.currentLine=1900547;
 //BA.debugLineNum = 1900547;BA.debugLine="Dim firstinput As Double";
_firstinput = 0;
RDebugUtils.currentLine=1900548;
 //BA.debugLineNum = 1900548;BA.debugLine="If EditText1.Text = \"\" Then";
if ((mostCurrent._edittext1.getText()).equals("")) { 
RDebugUtils.currentLine=1900549;
 //BA.debugLineNum = 1900549;BA.debugLine="firstinput = 0";
_firstinput = 0;
 }else {
RDebugUtils.currentLine=1900551;
 //BA.debugLineNum = 1900551;BA.debugLine="firstinput = EditText1.Text";
_firstinput = (double)(Double.parseDouble(mostCurrent._edittext1.getText()));
 };
RDebugUtils.currentLine=1900553;
 //BA.debugLineNum = 1900553;BA.debugLine="Dim result As String";
_result = "";
RDebugUtils.currentLine=1900554;
 //BA.debugLineNum = 1900554;BA.debugLine="Dim parts() As String = Regex.Split(\" to \", spin)";
_parts = anywheresoftware.b4a.keywords.Common.Regex.Split(" to ",_spin);
RDebugUtils.currentLine=1900555;
 //BA.debugLineNum = 1900555;BA.debugLine="Dim fromUnit As String = parts(0)";
_fromunit = _parts[(int) (0)];
RDebugUtils.currentLine=1900556;
 //BA.debugLineNum = 1900556;BA.debugLine="Dim toUnit As String = parts(1)";
_tounit = _parts[(int) (1)];
RDebugUtils.currentLine=1900557;
 //BA.debugLineNum = 1900557;BA.debugLine="Dim ans As Double";
_ans = 0;
RDebugUtils.currentLine=1900559;
 //BA.debugLineNum = 1900559;BA.debugLine="Select choice";
switch (BA.switchObjectToInt(_choice,"Length Converter","Weight Converter","Volume Converter","Speed Converter","Temperature Converter","Area Converter")) {
case 0: {
RDebugUtils.currentLine=1900561;
 //BA.debugLineNum = 1900561;BA.debugLine="Dim toMeter As Map";
_tometer = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=1900562;
 //BA.debugLineNum = 1900562;BA.debugLine="toMeter.Initialize";
_tometer.Initialize();
RDebugUtils.currentLine=1900563;
 //BA.debugLineNum = 1900563;BA.debugLine="toMeter.Put(\"mm\", 0.001)";
_tometer.Put((Object)("mm"),(Object)(0.001));
RDebugUtils.currentLine=1900564;
 //BA.debugLineNum = 1900564;BA.debugLine="toMeter.Put(\"cm\", 0.01)";
_tometer.Put((Object)("cm"),(Object)(0.01));
RDebugUtils.currentLine=1900565;
 //BA.debugLineNum = 1900565;BA.debugLine="toMeter.Put(\"m\", 1)";
_tometer.Put((Object)("m"),(Object)(1));
RDebugUtils.currentLine=1900566;
 //BA.debugLineNum = 1900566;BA.debugLine="toMeter.Put(\"km\", 1000)";
_tometer.Put((Object)("km"),(Object)(1000));
RDebugUtils.currentLine=1900567;
 //BA.debugLineNum = 1900567;BA.debugLine="toMeter.Put(\"in\", 0.0254)";
_tometer.Put((Object)("in"),(Object)(0.0254));
RDebugUtils.currentLine=1900568;
 //BA.debugLineNum = 1900568;BA.debugLine="toMeter.Put(\"ft\", 0.3048)";
_tometer.Put((Object)("ft"),(Object)(0.3048));
RDebugUtils.currentLine=1900569;
 //BA.debugLineNum = 1900569;BA.debugLine="toMeter.Put(\"yd\", 0.9144)";
_tometer.Put((Object)("yd"),(Object)(0.9144));
RDebugUtils.currentLine=1900570;
 //BA.debugLineNum = 1900570;BA.debugLine="toMeter.Put(\"mi\", 1609.344)";
_tometer.Put((Object)("mi"),(Object)(1609.344));
RDebugUtils.currentLine=1900571;
 //BA.debugLineNum = 1900571;BA.debugLine="toMeter.Put(\"µm\", 0.000001)";
_tometer.Put((Object)("µm"),(Object)(0.000001));
RDebugUtils.currentLine=1900572;
 //BA.debugLineNum = 1900572;BA.debugLine="ans = firstinput * toMeter.Get(fromUnit) / toMe";
_ans = _firstinput*(double)(BA.ObjectToNumber(_tometer.Get((Object)(_fromunit))))/(double)(double)(BA.ObjectToNumber(_tometer.Get((Object)(_tounit))));
RDebugUtils.currentLine=1900573;
 //BA.debugLineNum = 1900573;BA.debugLine="result = NumberFormat(ans, 1, 4) & \" \" & toUnit";
_result = anywheresoftware.b4a.keywords.Common.NumberFormat(_ans,(int) (1),(int) (4))+" "+_tounit;
 break; }
case 1: {
RDebugUtils.currentLine=1900576;
 //BA.debugLineNum = 1900576;BA.debugLine="Dim toGram As Map";
_togram = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=1900577;
 //BA.debugLineNum = 1900577;BA.debugLine="toGram.Initialize";
_togram.Initialize();
RDebugUtils.currentLine=1900578;
 //BA.debugLineNum = 1900578;BA.debugLine="toGram.Put(\"µg\", 0.000001)";
_togram.Put((Object)("µg"),(Object)(0.000001));
RDebugUtils.currentLine=1900579;
 //BA.debugLineNum = 1900579;BA.debugLine="toGram.Put(\"mg\", 0.001)";
_togram.Put((Object)("mg"),(Object)(0.001));
RDebugUtils.currentLine=1900580;
 //BA.debugLineNum = 1900580;BA.debugLine="toGram.Put(\"g\", 1)";
_togram.Put((Object)("g"),(Object)(1));
RDebugUtils.currentLine=1900581;
 //BA.debugLineNum = 1900581;BA.debugLine="toGram.Put(\"kg\", 1000)";
_togram.Put((Object)("kg"),(Object)(1000));
RDebugUtils.currentLine=1900582;
 //BA.debugLineNum = 1900582;BA.debugLine="toGram.Put(\"t\", 1000000)";
_togram.Put((Object)("t"),(Object)(1000000));
RDebugUtils.currentLine=1900583;
 //BA.debugLineNum = 1900583;BA.debugLine="toGram.Put(\"oz\", 28.3495)";
_togram.Put((Object)("oz"),(Object)(28.3495));
RDebugUtils.currentLine=1900584;
 //BA.debugLineNum = 1900584;BA.debugLine="toGram.Put(\"lb\", 453.592)";
_togram.Put((Object)("lb"),(Object)(453.592));
RDebugUtils.currentLine=1900585;
 //BA.debugLineNum = 1900585;BA.debugLine="toGram.Put(\"st\", 6350.29)";
_togram.Put((Object)("st"),(Object)(6350.29));
RDebugUtils.currentLine=1900586;
 //BA.debugLineNum = 1900586;BA.debugLine="toGram.Put(\"ct\", 0.2)";
_togram.Put((Object)("ct"),(Object)(0.2));
RDebugUtils.currentLine=1900587;
 //BA.debugLineNum = 1900587;BA.debugLine="toGram.Put(\"ton\", 907185)";
_togram.Put((Object)("ton"),(Object)(907185));
RDebugUtils.currentLine=1900588;
 //BA.debugLineNum = 1900588;BA.debugLine="ans = firstinput * toGram.Get(fromUnit) / toGra";
_ans = _firstinput*(double)(BA.ObjectToNumber(_togram.Get((Object)(_fromunit))))/(double)(double)(BA.ObjectToNumber(_togram.Get((Object)(_tounit))));
RDebugUtils.currentLine=1900589;
 //BA.debugLineNum = 1900589;BA.debugLine="result = NumberFormat(ans, 1, 4) & \" \" & toUnit";
_result = anywheresoftware.b4a.keywords.Common.NumberFormat(_ans,(int) (1),(int) (4))+" "+_tounit;
 break; }
case 2: {
RDebugUtils.currentLine=1900592;
 //BA.debugLineNum = 1900592;BA.debugLine="Dim toML As Map";
_toml = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=1900593;
 //BA.debugLineNum = 1900593;BA.debugLine="toML.Initialize";
_toml.Initialize();
RDebugUtils.currentLine=1900594;
 //BA.debugLineNum = 1900594;BA.debugLine="toML.Put(\"mL\", 1)";
_toml.Put((Object)("mL"),(Object)(1));
RDebugUtils.currentLine=1900595;
 //BA.debugLineNum = 1900595;BA.debugLine="toML.Put(\"L\", 1000)";
_toml.Put((Object)("L"),(Object)(1000));
RDebugUtils.currentLine=1900596;
 //BA.debugLineNum = 1900596;BA.debugLine="toML.Put(\"kL\", 1000000)";
_toml.Put((Object)("kL"),(Object)(1000000));
RDebugUtils.currentLine=1900597;
 //BA.debugLineNum = 1900597;BA.debugLine="toML.Put(\"cm³\", 1)";
_toml.Put((Object)("cm³"),(Object)(1));
RDebugUtils.currentLine=1900598;
 //BA.debugLineNum = 1900598;BA.debugLine="toML.Put(\"m³\", 1000000)";
_toml.Put((Object)("m³"),(Object)(1000000));
RDebugUtils.currentLine=1900599;
 //BA.debugLineNum = 1900599;BA.debugLine="toML.Put(\"in³\", 16.3871)";
_toml.Put((Object)("in³"),(Object)(16.3871));
RDebugUtils.currentLine=1900600;
 //BA.debugLineNum = 1900600;BA.debugLine="toML.Put(\"ft³\", 28316.8)";
_toml.Put((Object)("ft³"),(Object)(28316.8));
RDebugUtils.currentLine=1900601;
 //BA.debugLineNum = 1900601;BA.debugLine="ans = firstinput * toML.Get(fromUnit) / toML.Ge";
_ans = _firstinput*(double)(BA.ObjectToNumber(_toml.Get((Object)(_fromunit))))/(double)(double)(BA.ObjectToNumber(_toml.Get((Object)(_tounit))));
RDebugUtils.currentLine=1900602;
 //BA.debugLineNum = 1900602;BA.debugLine="result = NumberFormat(ans, 1, 4) & \" \" & toUnit";
_result = anywheresoftware.b4a.keywords.Common.NumberFormat(_ans,(int) (1),(int) (4))+" "+_tounit;
 break; }
case 3: {
RDebugUtils.currentLine=1900605;
 //BA.debugLineNum = 1900605;BA.debugLine="Dim toMS As Map";
_toms = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=1900606;
 //BA.debugLineNum = 1900606;BA.debugLine="toMS.Initialize";
_toms.Initialize();
RDebugUtils.currentLine=1900607;
 //BA.debugLineNum = 1900607;BA.debugLine="toMS.Put(\"m/s\", 1)";
_toms.Put((Object)("m/s"),(Object)(1));
RDebugUtils.currentLine=1900608;
 //BA.debugLineNum = 1900608;BA.debugLine="toMS.Put(\"km/h\", 0.277778)";
_toms.Put((Object)("km/h"),(Object)(0.277778));
RDebugUtils.currentLine=1900609;
 //BA.debugLineNum = 1900609;BA.debugLine="toMS.Put(\"mph\", 0.44704)";
_toms.Put((Object)("mph"),(Object)(0.44704));
RDebugUtils.currentLine=1900610;
 //BA.debugLineNum = 1900610;BA.debugLine="toMS.Put(\"knot\", 0.514444)";
_toms.Put((Object)("knot"),(Object)(0.514444));
RDebugUtils.currentLine=1900611;
 //BA.debugLineNum = 1900611;BA.debugLine="toMS.Put(\"ft/s\", 0.3048)";
_toms.Put((Object)("ft/s"),(Object)(0.3048));
RDebugUtils.currentLine=1900612;
 //BA.debugLineNum = 1900612;BA.debugLine="ans = firstinput * toMS.Get(fromUnit) / toMS.Ge";
_ans = _firstinput*(double)(BA.ObjectToNumber(_toms.Get((Object)(_fromunit))))/(double)(double)(BA.ObjectToNumber(_toms.Get((Object)(_tounit))));
RDebugUtils.currentLine=1900613;
 //BA.debugLineNum = 1900613;BA.debugLine="result = NumberFormat(ans, 1, 4) & \" \" & toUnit";
_result = anywheresoftware.b4a.keywords.Common.NumberFormat(_ans,(int) (1),(int) (4))+" "+_tounit;
 break; }
case 4: {
RDebugUtils.currentLine=1900616;
 //BA.debugLineNum = 1900616;BA.debugLine="Dim celsius As Double";
_celsius = 0;
RDebugUtils.currentLine=1900617;
 //BA.debugLineNum = 1900617;BA.debugLine="Select fromUnit";
switch (BA.switchObjectToInt(_fromunit,"°C","°F","K","°R")) {
case 0: {
RDebugUtils.currentLine=1900618;
 //BA.debugLineNum = 1900618;BA.debugLine="Case \"°C\" : celsius = firstinput";
_celsius = _firstinput;
 break; }
case 1: {
RDebugUtils.currentLine=1900619;
 //BA.debugLineNum = 1900619;BA.debugLine="Case \"°F\" : celsius = (firstinput - 32) / 1.8";
_celsius = (_firstinput-32)/(double)1.8;
 break; }
case 2: {
RDebugUtils.currentLine=1900620;
 //BA.debugLineNum = 1900620;BA.debugLine="Case \"K\"  : celsius = firstinput - 273.15";
_celsius = _firstinput-273.15;
 break; }
case 3: {
RDebugUtils.currentLine=1900621;
 //BA.debugLineNum = 1900621;BA.debugLine="Case \"°R\" : celsius = (firstinput - 491.67) /";
_celsius = (_firstinput-491.67)/(double)1.8;
 break; }
}
;
RDebugUtils.currentLine=1900623;
 //BA.debugLineNum = 1900623;BA.debugLine="Select toUnit";
switch (BA.switchObjectToInt(_tounit,"°C","°F","K","°R")) {
case 0: {
RDebugUtils.currentLine=1900624;
 //BA.debugLineNum = 1900624;BA.debugLine="Case \"°C\" : ans = celsius";
_ans = _celsius;
 break; }
case 1: {
RDebugUtils.currentLine=1900625;
 //BA.debugLineNum = 1900625;BA.debugLine="Case \"°F\" : ans = (celsius * 1.8) + 32";
_ans = (_celsius*1.8)+32;
 break; }
case 2: {
RDebugUtils.currentLine=1900626;
 //BA.debugLineNum = 1900626;BA.debugLine="Case \"K\"  : ans = celsius + 273.15";
_ans = _celsius+273.15;
 break; }
case 3: {
RDebugUtils.currentLine=1900627;
 //BA.debugLineNum = 1900627;BA.debugLine="Case \"°R\" : ans = (celsius + 273.15) * 1.8";
_ans = (_celsius+273.15)*1.8;
 break; }
}
;
RDebugUtils.currentLine=1900629;
 //BA.debugLineNum = 1900629;BA.debugLine="result = NumberFormat(ans, 1, 4) & \" \" & toUnit";
_result = anywheresoftware.b4a.keywords.Common.NumberFormat(_ans,(int) (1),(int) (4))+" "+_tounit;
 break; }
case 5: {
RDebugUtils.currentLine=1900632;
 //BA.debugLineNum = 1900632;BA.debugLine="Dim toM2 As Map";
_tom2 = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=1900633;
 //BA.debugLineNum = 1900633;BA.debugLine="toM2.Initialize";
_tom2.Initialize();
RDebugUtils.currentLine=1900634;
 //BA.debugLineNum = 1900634;BA.debugLine="toM2.Put(\"mm²\", 0.000001)";
_tom2.Put((Object)("mm²"),(Object)(0.000001));
RDebugUtils.currentLine=1900635;
 //BA.debugLineNum = 1900635;BA.debugLine="toM2.Put(\"cm²\", 0.0001)";
_tom2.Put((Object)("cm²"),(Object)(0.0001));
RDebugUtils.currentLine=1900636;
 //BA.debugLineNum = 1900636;BA.debugLine="toM2.Put(\"m²\", 1)";
_tom2.Put((Object)("m²"),(Object)(1));
RDebugUtils.currentLine=1900637;
 //BA.debugLineNum = 1900637;BA.debugLine="toM2.Put(\"km²\", 1000000)";
_tom2.Put((Object)("km²"),(Object)(1000000));
RDebugUtils.currentLine=1900638;
 //BA.debugLineNum = 1900638;BA.debugLine="toM2.Put(\"in²\", 0.00064516)";
_tom2.Put((Object)("in²"),(Object)(0.00064516));
RDebugUtils.currentLine=1900639;
 //BA.debugLineNum = 1900639;BA.debugLine="toM2.Put(\"ft²\", 0.092903)";
_tom2.Put((Object)("ft²"),(Object)(0.092903));
RDebugUtils.currentLine=1900640;
 //BA.debugLineNum = 1900640;BA.debugLine="toM2.Put(\"yd²\", 0.836127)";
_tom2.Put((Object)("yd²"),(Object)(0.836127));
RDebugUtils.currentLine=1900641;
 //BA.debugLineNum = 1900641;BA.debugLine="ans = firstinput * toM2.Get(fromUnit) / toM2.Ge";
_ans = _firstinput*(double)(BA.ObjectToNumber(_tom2.Get((Object)(_fromunit))))/(double)(double)(BA.ObjectToNumber(_tom2.Get((Object)(_tounit))));
RDebugUtils.currentLine=1900642;
 //BA.debugLineNum = 1900642;BA.debugLine="result = NumberFormat(ans, 1, 4) & \" \" & toUnit";
_result = anywheresoftware.b4a.keywords.Common.NumberFormat(_ans,(int) (1),(int) (4))+" "+_tounit;
 break; }
default: {
RDebugUtils.currentLine=1900645;
 //BA.debugLineNum = 1900645;BA.debugLine="result = \"0\"";
_result = "0";
 break; }
}
;
RDebugUtils.currentLine=1900648;
 //BA.debugLineNum = 1900648;BA.debugLine="Label3.Text = result";
mostCurrent._label3.setText(BA.ObjectToCharSequence(_result));
RDebugUtils.currentLine=1900649;
 //BA.debugLineNum = 1900649;BA.debugLine="End Sub";
return "";
}
public static String  _edittext2_enterpressed() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "edittext2_enterpressed", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "edittext2_enterpressed", null));}
RDebugUtils.currentLine=2293760;
 //BA.debugLineNum = 2293760;BA.debugLine="Private Sub EditText2_EnterPressed";
RDebugUtils.currentLine=2293761;
 //BA.debugLineNum = 2293761;BA.debugLine="convert";
_convert();
RDebugUtils.currentLine=2293762;
 //BA.debugLineNum = 2293762;BA.debugLine="End Sub";
return "";
}
public static String  _pnl1_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "pnl1_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "pnl1_click", null));}
RDebugUtils.currentLine=851968;
 //BA.debugLineNum = 851968;BA.debugLine="Private Sub pnl1_Click";
RDebugUtils.currentLine=851969;
 //BA.debugLineNum = 851969;BA.debugLine="Drawer.LeftOpen = False";
mostCurrent._drawer._setleftopen /*boolean*/ (null,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=851970;
 //BA.debugLineNum = 851970;BA.debugLine="ShowPage1";
_showpage1();
RDebugUtils.currentLine=851971;
 //BA.debugLineNum = 851971;BA.debugLine="End Sub";
return "";
}
public static String  _pnl2_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "pnl2_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "pnl2_click", null));}
RDebugUtils.currentLine=786432;
 //BA.debugLineNum = 786432;BA.debugLine="Private Sub pnl2_Click";
RDebugUtils.currentLine=786433;
 //BA.debugLineNum = 786433;BA.debugLine="Drawer.LeftOpen = False";
mostCurrent._drawer._setleftopen /*boolean*/ (null,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=786434;
 //BA.debugLineNum = 786434;BA.debugLine="ShowPage2";
_showpage2();
RDebugUtils.currentLine=786435;
 //BA.debugLineNum = 786435;BA.debugLine="End Sub";
return "";
}
public static String  _pnl3_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "pnl3_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "pnl3_click", null));}
RDebugUtils.currentLine=720896;
 //BA.debugLineNum = 720896;BA.debugLine="Private Sub pnl3_Click";
RDebugUtils.currentLine=720897;
 //BA.debugLineNum = 720897;BA.debugLine="Drawer.LeftOpen = False";
mostCurrent._drawer._setleftopen /*boolean*/ (null,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=720898;
 //BA.debugLineNum = 720898;BA.debugLine="ShowPage3";
_showpage3();
RDebugUtils.currentLine=720899;
 //BA.debugLineNum = 720899;BA.debugLine="End Sub";
return "";
}
public static String  _pnl4_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "pnl4_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "pnl4_click", null));}
RDebugUtils.currentLine=655360;
 //BA.debugLineNum = 655360;BA.debugLine="Private Sub pnl4_Click";
RDebugUtils.currentLine=655361;
 //BA.debugLineNum = 655361;BA.debugLine="Drawer.LeftOpen = False";
mostCurrent._drawer._setleftopen /*boolean*/ (null,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=655362;
 //BA.debugLineNum = 655362;BA.debugLine="ShowPage4";
_showpage4();
RDebugUtils.currentLine=655363;
 //BA.debugLineNum = 655363;BA.debugLine="End Sub";
return "";
}
public static String  _pnl5_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "pnl5_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "pnl5_click", null));}
RDebugUtils.currentLine=589824;
 //BA.debugLineNum = 589824;BA.debugLine="Private Sub pnl5_Click";
RDebugUtils.currentLine=589825;
 //BA.debugLineNum = 589825;BA.debugLine="Drawer.LeftOpen = False";
mostCurrent._drawer._setleftopen /*boolean*/ (null,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=589826;
 //BA.debugLineNum = 589826;BA.debugLine="ShowPage5";
_showpage5();
RDebugUtils.currentLine=589827;
 //BA.debugLineNum = 589827;BA.debugLine="End Sub";
return "";
}
public static String  _pnl6_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "pnl6_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "pnl6_click", null));}
RDebugUtils.currentLine=524288;
 //BA.debugLineNum = 524288;BA.debugLine="Private Sub pnl6_Click";
RDebugUtils.currentLine=524289;
 //BA.debugLineNum = 524289;BA.debugLine="Drawer.LeftOpen = False";
mostCurrent._drawer._setleftopen /*boolean*/ (null,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=524290;
 //BA.debugLineNum = 524290;BA.debugLine="ShowPage6";
_showpage6();
RDebugUtils.currentLine=524291;
 //BA.debugLineNum = 524291;BA.debugLine="End Sub";
return "";
}
public static String  _setspinner(anywheresoftware.b4a.objects.collections.List _item,Object _color) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "setspinner", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "setspinner", new Object[] {_item,_color}));}
anywheresoftware.b4a.objects.SpinnerWrapper _s = null;
anywheresoftware.b4a.objects.collections.List _pairitems = null;
int _i = 0;
anywheresoftware.b4a.objects.drawable.ColorDrawable _cd = null;
RDebugUtils.currentLine=1441792;
 //BA.debugLineNum = 1441792;BA.debugLine="Sub setspinner(item As List, color As Object)";
RDebugUtils.currentLine=1441793;
 //BA.debugLineNum = 1441793;BA.debugLine="Spinner2.AddAll(item)";
mostCurrent._spinner2.AddAll(_item);
RDebugUtils.currentLine=1441794;
 //BA.debugLineNum = 1441794;BA.debugLine="Spinner3.AddAll(item)";
mostCurrent._spinner3.AddAll(_item);
RDebugUtils.currentLine=1441795;
 //BA.debugLineNum = 1441795;BA.debugLine="For Each s As Spinner In Array(Spinner1, Spinner2";
_s = new anywheresoftware.b4a.objects.SpinnerWrapper();
{
final Object[] group3 = new Object[]{(Object)(mostCurrent._spinner1.getObject()),(Object)(mostCurrent._spinner2.getObject()),(Object)(mostCurrent._spinner3.getObject())};
final int groupLen3 = group3.length
;int index3 = 0;
;
for (; index3 < groupLen3;index3++){
_s = (anywheresoftware.b4a.objects.SpinnerWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.SpinnerWrapper(), (anywheresoftware.b4a.objects.SpinnerWrapper.B4ASpinner)(group3[index3]));
RDebugUtils.currentLine=1441796;
 //BA.debugLineNum = 1441796;BA.debugLine="s.TextSize = 14";
_s.setTextSize((float) (14));
RDebugUtils.currentLine=1441797;
 //BA.debugLineNum = 1441797;BA.debugLine="Dim pairItems As List";
_pairitems = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=1441798;
 //BA.debugLineNum = 1441798;BA.debugLine="pairItems.Initialize";
_pairitems.Initialize();
RDebugUtils.currentLine=1441799;
 //BA.debugLineNum = 1441799;BA.debugLine="For i = 0 To item.Size - 2";
{
final int step7 = 1;
final int limit7 = (int) (_item.getSize()-2);
_i = (int) (0) ;
for (;_i <= limit7 ;_i = _i + step7 ) {
RDebugUtils.currentLine=1441800;
 //BA.debugLineNum = 1441800;BA.debugLine="pairItems.Add(item.Get(i) & \" to \" & item.Get(i";
_pairitems.Add((Object)(BA.ObjectToString(_item.Get(_i))+" to "+BA.ObjectToString(_item.Get((int) (_i+1)))));
 }
};
RDebugUtils.currentLine=1441803;
 //BA.debugLineNum = 1441803;BA.debugLine="Dim cd As ColorDrawable";
_cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
RDebugUtils.currentLine=1441804;
 //BA.debugLineNum = 1441804;BA.debugLine="cd.Initialize(color, 10dip)";
_cd.Initialize((int)(BA.ObjectToNumber(_color)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)));
RDebugUtils.currentLine=1441805;
 //BA.debugLineNum = 1441805;BA.debugLine="s.Background = cd";
_s.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
 }
};
RDebugUtils.currentLine=1441807;
 //BA.debugLineNum = 1441807;BA.debugLine="Spinner1.AddAll(pairItems)";
mostCurrent._spinner1.AddAll(_pairitems);
RDebugUtils.currentLine=1441808;
 //BA.debugLineNum = 1441808;BA.debugLine="End Sub";
return "";
}
public static String  _spinner1_itemclick(int _position,Object _value) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "spinner1_itemclick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "spinner1_itemclick", new Object[] {_position,_value}));}
RDebugUtils.currentLine=2228224;
 //BA.debugLineNum = 2228224;BA.debugLine="Private Sub Spinner1_ItemClick (Position As Int, V";
RDebugUtils.currentLine=2228225;
 //BA.debugLineNum = 2228225;BA.debugLine="qick";
_qick();
RDebugUtils.currentLine=2228226;
 //BA.debugLineNum = 2228226;BA.debugLine="End Sub";
return "";
}
public static String  _spinner2_itemclick(int _position,Object _value) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "spinner2_itemclick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "spinner2_itemclick", new Object[] {_position,_value}));}
RDebugUtils.currentLine=2162688;
 //BA.debugLineNum = 2162688;BA.debugLine="Private Sub Spinner2_ItemClick (Position As Int, V";
RDebugUtils.currentLine=2162689;
 //BA.debugLineNum = 2162689;BA.debugLine="convert";
_convert();
RDebugUtils.currentLine=2162690;
 //BA.debugLineNum = 2162690;BA.debugLine="End Sub";
return "";
}
public static String  _spinner3_itemclick(int _position,Object _value) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "spinner3_itemclick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "spinner3_itemclick", new Object[] {_position,_value}));}
RDebugUtils.currentLine=2097152;
 //BA.debugLineNum = 2097152;BA.debugLine="Private Sub Spinner3_ItemClick (Position As Int, V";
RDebugUtils.currentLine=2097153;
 //BA.debugLineNum = 2097153;BA.debugLine="convert";
_convert();
RDebugUtils.currentLine=2097154;
 //BA.debugLineNum = 2097154;BA.debugLine="End Sub";
return "";
}
}