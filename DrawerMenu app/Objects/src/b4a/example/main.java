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
RDebugUtils.currentLine=7208960;
 //BA.debugLineNum = 7208960;BA.debugLine="Sub SetGradient(Panel As Panel, Color1 As Int, Col";
RDebugUtils.currentLine=7208961;
 //BA.debugLineNum = 7208961;BA.debugLine="Dim gd As GradientDrawable";
_gd = new anywheresoftware.b4a.objects.drawable.GradientDrawable();
RDebugUtils.currentLine=7208962;
 //BA.debugLineNum = 7208962;BA.debugLine="gd.Initialize(\"BR_TL\", Array As Int(Color1, Color";
_gd.Initialize(BA.getEnumFromString(android.graphics.drawable.GradientDrawable.Orientation.class,"BR_TL"),new int[]{_color1,_color2});
RDebugUtils.currentLine=7208963;
 //BA.debugLineNum = 7208963;BA.debugLine="Panel.Background = gd";
_panel.setBackground((android.graphics.drawable.Drawable)(_gd.getObject()));
RDebugUtils.currentLine=7208964;
 //BA.debugLineNum = 7208964;BA.debugLine="End Sub";
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
 //BA.debugLineNum = 393221;BA.debugLine="btnPage1.Text = \"Length Converter\"";
mostCurrent._btnpage1.setText(BA.ObjectToCharSequence("Length Converter"));
RDebugUtils.currentLine=393222;
 //BA.debugLineNum = 393222;BA.debugLine="btnPage2.Initialize(\"btnPage2\")";
mostCurrent._btnpage2.Initialize(mostCurrent.activityBA,"btnPage2");
RDebugUtils.currentLine=393223;
 //BA.debugLineNum = 393223;BA.debugLine="btnPage2.Text = \"Weight Converter\"";
mostCurrent._btnpage2.setText(BA.ObjectToCharSequence("Weight Converter"));
RDebugUtils.currentLine=393224;
 //BA.debugLineNum = 393224;BA.debugLine="btnPage3.Initialize(\"btnPage3\")";
mostCurrent._btnpage3.Initialize(mostCurrent.activityBA,"btnPage3");
RDebugUtils.currentLine=393225;
 //BA.debugLineNum = 393225;BA.debugLine="btnPage3.Text = \"Volume Converter\"";
mostCurrent._btnpage3.setText(BA.ObjectToCharSequence("Volume Converter"));
RDebugUtils.currentLine=393226;
 //BA.debugLineNum = 393226;BA.debugLine="btnPage4.Initialize(\"btnPage4\")";
mostCurrent._btnpage4.Initialize(mostCurrent.activityBA,"btnPage4");
RDebugUtils.currentLine=393227;
 //BA.debugLineNum = 393227;BA.debugLine="btnPage4.Text = \"Speed Converter\"";
mostCurrent._btnpage4.setText(BA.ObjectToCharSequence("Speed Converter"));
RDebugUtils.currentLine=393228;
 //BA.debugLineNum = 393228;BA.debugLine="btnPage5.Initialize(\"btnPage5\")";
mostCurrent._btnpage5.Initialize(mostCurrent.activityBA,"btnPage5");
RDebugUtils.currentLine=393229;
 //BA.debugLineNum = 393229;BA.debugLine="btnPage5.Text = \"Temperature Converter\"";
mostCurrent._btnpage5.setText(BA.ObjectToCharSequence("Temperature Converter"));
RDebugUtils.currentLine=393230;
 //BA.debugLineNum = 393230;BA.debugLine="btnPage6.Initialize(\"btnPage6\")";
mostCurrent._btnpage6.Initialize(mostCurrent.activityBA,"btnPage6");
RDebugUtils.currentLine=393231;
 //BA.debugLineNum = 393231;BA.debugLine="btnPage6.Text = \"Area Converter\"";
mostCurrent._btnpage6.setText(BA.ObjectToCharSequence("Area Converter"));
RDebugUtils.currentLine=393233;
 //BA.debugLineNum = 393233;BA.debugLine="For Each b As Button In Array(btnHome, btnPage1,";
_b = new anywheresoftware.b4a.objects.ButtonWrapper();
{
final Object[] group16 = new Object[]{(Object)(mostCurrent._btnhome.getObject()),(Object)(mostCurrent._btnpage1.getObject()),(Object)(mostCurrent._btnpage2.getObject()),(Object)(mostCurrent._btnpage3.getObject()),(Object)(mostCurrent._btnpage4.getObject()),(Object)(mostCurrent._btnpage5.getObject()),(Object)(mostCurrent._btnpage6.getObject())};
final int groupLen16 = group16.length
;int index16 = 0;
;
for (; index16 < groupLen16;index16++){
_b = (anywheresoftware.b4a.objects.ButtonWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ButtonWrapper(), (android.widget.Button)(group16[index16]));
RDebugUtils.currentLine=393234;
 //BA.debugLineNum = 393234;BA.debugLine="b.TextSize = 16";
_b.setTextSize((float) (16));
RDebugUtils.currentLine=393235;
 //BA.debugLineNum = 393235;BA.debugLine="b.Gravity = Gravity.LEFT + Gravity.CENTER_VERTIC";
_b.setGravity((int) (anywheresoftware.b4a.keywords.Common.Gravity.LEFT+anywheresoftware.b4a.keywords.Common.Gravity.CENTER_VERTICAL));
RDebugUtils.currentLine=393236;
 //BA.debugLineNum = 393236;BA.debugLine="b.Color = Colors.Transparent";
_b.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
RDebugUtils.currentLine=393237;
 //BA.debugLineNum = 393237;BA.debugLine="b.TextColor = Colors.White";
_b.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=393238;
 //BA.debugLineNum = 393238;BA.debugLine="pnlMenu.AddView(b, 10dip, 0, 240dip, 50dip)";
mostCurrent._pnlmenu.AddView((android.view.View)(_b.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (240)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
 }
};
RDebugUtils.currentLine=393240;
 //BA.debugLineNum = 393240;BA.debugLine="btnHome.Top = 120dip";
mostCurrent._btnhome.setTop(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (120)));
RDebugUtils.currentLine=393241;
 //BA.debugLineNum = 393241;BA.debugLine="btnPage1.Top = 180dip";
mostCurrent._btnpage1.setTop(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (180)));
RDebugUtils.currentLine=393242;
 //BA.debugLineNum = 393242;BA.debugLine="btnPage2.Top = 240dip";
mostCurrent._btnpage2.setTop(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (240)));
RDebugUtils.currentLine=393243;
 //BA.debugLineNum = 393243;BA.debugLine="btnPage3.Top = 300dip";
mostCurrent._btnpage3.setTop(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (300)));
RDebugUtils.currentLine=393244;
 //BA.debugLineNum = 393244;BA.debugLine="btnPage4.Top = 360dip";
mostCurrent._btnpage4.setTop(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (360)));
RDebugUtils.currentLine=393245;
 //BA.debugLineNum = 393245;BA.debugLine="btnPage5.Top = 420dip";
mostCurrent._btnpage5.setTop(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (420)));
RDebugUtils.currentLine=393246;
 //BA.debugLineNum = 393246;BA.debugLine="btnPage6.Top = 480dip";
mostCurrent._btnpage6.setTop(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (480)));
RDebugUtils.currentLine=393247;
 //BA.debugLineNum = 393247;BA.debugLine="End Sub";
return "";
}
public static String  _showhome() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "showhome", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "showhome", null));}
RDebugUtils.currentLine=917504;
 //BA.debugLineNum = 917504;BA.debugLine="Sub ShowHome";
RDebugUtils.currentLine=917505;
 //BA.debugLineNum = 917505;BA.debugLine="pnlMain.RemoveAllViews";
mostCurrent._pnlmain.RemoveAllViews();
RDebugUtils.currentLine=917506;
 //BA.debugLineNum = 917506;BA.debugLine="pnlMain.LoadLayout(\"home\")";
mostCurrent._pnlmain.LoadLayout("home",mostCurrent.activityBA);
RDebugUtils.currentLine=917507;
 //BA.debugLineNum = 917507;BA.debugLine="lblTitle.Text = \"Home\"";
mostCurrent._lbltitle.setText(BA.ObjectToCharSequence("Home"));
RDebugUtils.currentLine=917508;
 //BA.debugLineNum = 917508;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_keypress", false))
	 {return ((Boolean) Debug.delegate(mostCurrent.activityBA, "activity_keypress", new Object[] {_keycode}));}
RDebugUtils.currentLine=1376256;
 //BA.debugLineNum = 1376256;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
RDebugUtils.currentLine=1376257;
 //BA.debugLineNum = 1376257;BA.debugLine="If KeyCode = KeyCodes.KEYCODE_BACK And Drawer.Lef";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK && mostCurrent._drawer._getleftopen /*boolean*/ (null)) { 
RDebugUtils.currentLine=1376258;
 //BA.debugLineNum = 1376258;BA.debugLine="Drawer.LeftOpen = False";
mostCurrent._drawer._setleftopen /*boolean*/ (null,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1376259;
 //BA.debugLineNum = 1376259;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 }else 
{RDebugUtils.currentLine=1376260;
 //BA.debugLineNum = 1376260;BA.debugLine="Else If KeyCode = KeyCodes.KEYCODE_BACK And Not(D";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK && anywheresoftware.b4a.keywords.Common.Not(mostCurrent._drawer._getleftopen /*boolean*/ (null))) { 
RDebugUtils.currentLine=1376261;
 //BA.debugLineNum = 1376261;BA.debugLine="Drawer.LeftOpen = True";
mostCurrent._drawer._setleftopen /*boolean*/ (null,anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=1376262;
 //BA.debugLineNum = 1376262;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 }}
;
RDebugUtils.currentLine=1376265;
 //BA.debugLineNum = 1376265;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
RDebugUtils.currentLine=1376266;
 //BA.debugLineNum = 1376266;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=983040;
 //BA.debugLineNum = 983040;BA.debugLine="Sub ShowPage1";
RDebugUtils.currentLine=983041;
 //BA.debugLineNum = 983041;BA.debugLine="pnlMain.RemoveAllViews";
mostCurrent._pnlmain.RemoveAllViews();
RDebugUtils.currentLine=983042;
 //BA.debugLineNum = 983042;BA.debugLine="pnlMain.LoadLayout(\"page1\")";
mostCurrent._pnlmain.LoadLayout("page1",mostCurrent.activityBA);
RDebugUtils.currentLine=983043;
 //BA.debugLineNum = 983043;BA.debugLine="lblTitle.Text = \"Length Converter\"";
mostCurrent._lbltitle.setText(BA.ObjectToCharSequence("Length Converter"));
RDebugUtils.currentLine=983045;
 //BA.debugLineNum = 983045;BA.debugLine="items.Clear";
mostCurrent._items.Clear();
RDebugUtils.currentLine=983046;
 //BA.debugLineNum = 983046;BA.debugLine="items.AddAll(Array As String(\"mm\",\"cm\",\"m\",\"km\",\"";
mostCurrent._items.AddAll(anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{"mm","cm","m","km","in","ft","yd","mi","µm"}));
RDebugUtils.currentLine=983047;
 //BA.debugLineNum = 983047;BA.debugLine="setspinner(items, Colors.ARGB(64, 255, 255, 255))";
_setspinner(mostCurrent._items,(Object)(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (64),(int) (255),(int) (255),(int) (255))));
RDebugUtils.currentLine=983048;
 //BA.debugLineNum = 983048;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=1048576;
 //BA.debugLineNum = 1048576;BA.debugLine="Sub ShowPage2";
RDebugUtils.currentLine=1048577;
 //BA.debugLineNum = 1048577;BA.debugLine="pnlMain.RemoveAllViews";
mostCurrent._pnlmain.RemoveAllViews();
RDebugUtils.currentLine=1048578;
 //BA.debugLineNum = 1048578;BA.debugLine="pnlMain.LoadLayout(\"page2\")";
mostCurrent._pnlmain.LoadLayout("page2",mostCurrent.activityBA);
RDebugUtils.currentLine=1048579;
 //BA.debugLineNum = 1048579;BA.debugLine="lblTitle.Text = \"Wight Converter\"";
mostCurrent._lbltitle.setText(BA.ObjectToCharSequence("Wight Converter"));
RDebugUtils.currentLine=1048581;
 //BA.debugLineNum = 1048581;BA.debugLine="items.Clear";
mostCurrent._items.Clear();
RDebugUtils.currentLine=1048582;
 //BA.debugLineNum = 1048582;BA.debugLine="items.AddAll(Array As String(\"mg\",\"g\",\"kg\",\"t\",\"o";
mostCurrent._items.AddAll(anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{"mg","g","kg","t","oz","lb","st","ct","µg","ton"}));
RDebugUtils.currentLine=1048583;
 //BA.debugLineNum = 1048583;BA.debugLine="setspinner(items, Colors.ARGB(64, 255, 255, 255))";
_setspinner(mostCurrent._items,(Object)(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (64),(int) (255),(int) (255),(int) (255))));
RDebugUtils.currentLine=1048584;
 //BA.debugLineNum = 1048584;BA.debugLine="End Sub";
return "";
}
public static String  _btnpage3_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btnpage3_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btnpage3_click", null));}
RDebugUtils.currentLine=655360;
 //BA.debugLineNum = 655360;BA.debugLine="Sub btnPage3_Click";
RDebugUtils.currentLine=655361;
 //BA.debugLineNum = 655361;BA.debugLine="Drawer.LeftOpen = False";
mostCurrent._drawer._setleftopen /*boolean*/ (null,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=655362;
 //BA.debugLineNum = 655362;BA.debugLine="ShowPage3";
_showpage3();
RDebugUtils.currentLine=655363;
 //BA.debugLineNum = 655363;BA.debugLine="End Sub";
return "";
}
public static String  _showpage3() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "showpage3", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "showpage3", null));}
RDebugUtils.currentLine=1114112;
 //BA.debugLineNum = 1114112;BA.debugLine="Sub ShowPage3";
RDebugUtils.currentLine=1114113;
 //BA.debugLineNum = 1114113;BA.debugLine="pnlMain.RemoveAllViews";
mostCurrent._pnlmain.RemoveAllViews();
RDebugUtils.currentLine=1114114;
 //BA.debugLineNum = 1114114;BA.debugLine="pnlMain.LoadLayout(\"page3\")";
mostCurrent._pnlmain.LoadLayout("page3",mostCurrent.activityBA);
RDebugUtils.currentLine=1114115;
 //BA.debugLineNum = 1114115;BA.debugLine="lblTitle.Text = \"Volume Converter\"";
mostCurrent._lbltitle.setText(BA.ObjectToCharSequence("Volume Converter"));
RDebugUtils.currentLine=1114117;
 //BA.debugLineNum = 1114117;BA.debugLine="items.Clear";
mostCurrent._items.Clear();
RDebugUtils.currentLine=1114118;
 //BA.debugLineNum = 1114118;BA.debugLine="items.AddAll(Array As String(\"mL\",\"L\",\"kL\",\"cm³\",";
mostCurrent._items.AddAll(anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{"mL","L","kL","cm³","m³","in³","ft³"}));
RDebugUtils.currentLine=1114119;
 //BA.debugLineNum = 1114119;BA.debugLine="setspinner(items, Colors.ARGB(64, 255, 255, 255))";
_setspinner(mostCurrent._items,(Object)(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (64),(int) (255),(int) (255),(int) (255))));
RDebugUtils.currentLine=1114120;
 //BA.debugLineNum = 1114120;BA.debugLine="End Sub";
return "";
}
public static String  _btnpage4_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btnpage4_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btnpage4_click", null));}
RDebugUtils.currentLine=720896;
 //BA.debugLineNum = 720896;BA.debugLine="Sub btnPage4_Click";
RDebugUtils.currentLine=720897;
 //BA.debugLineNum = 720897;BA.debugLine="Drawer.LeftOpen = False";
mostCurrent._drawer._setleftopen /*boolean*/ (null,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=720898;
 //BA.debugLineNum = 720898;BA.debugLine="ShowPage4";
_showpage4();
RDebugUtils.currentLine=720899;
 //BA.debugLineNum = 720899;BA.debugLine="End Sub";
return "";
}
public static String  _showpage4() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "showpage4", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "showpage4", null));}
RDebugUtils.currentLine=1179648;
 //BA.debugLineNum = 1179648;BA.debugLine="Sub ShowPage4";
RDebugUtils.currentLine=1179649;
 //BA.debugLineNum = 1179649;BA.debugLine="pnlMain.RemoveAllViews";
mostCurrent._pnlmain.RemoveAllViews();
RDebugUtils.currentLine=1179650;
 //BA.debugLineNum = 1179650;BA.debugLine="pnlMain.LoadLayout(\"page4\")";
mostCurrent._pnlmain.LoadLayout("page4",mostCurrent.activityBA);
RDebugUtils.currentLine=1179651;
 //BA.debugLineNum = 1179651;BA.debugLine="lblTitle.Text = \"Speed Converter\"";
mostCurrent._lbltitle.setText(BA.ObjectToCharSequence("Speed Converter"));
RDebugUtils.currentLine=1179653;
 //BA.debugLineNum = 1179653;BA.debugLine="items.Clear";
mostCurrent._items.Clear();
RDebugUtils.currentLine=1179654;
 //BA.debugLineNum = 1179654;BA.debugLine="items.AddAll(Array As String(\"m/s\",\"km/h\",\"mph\",\"";
mostCurrent._items.AddAll(anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{"m/s","km/h","mph","knot","ft/s"}));
RDebugUtils.currentLine=1179655;
 //BA.debugLineNum = 1179655;BA.debugLine="setspinner(items, Colors.ARGB(64, 255, 255, 255))";
_setspinner(mostCurrent._items,(Object)(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (64),(int) (255),(int) (255),(int) (255))));
RDebugUtils.currentLine=1179656;
 //BA.debugLineNum = 1179656;BA.debugLine="End Sub";
return "";
}
public static String  _btnpage5_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btnpage5_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btnpage5_click", null));}
RDebugUtils.currentLine=786432;
 //BA.debugLineNum = 786432;BA.debugLine="Sub btnPage5_Click";
RDebugUtils.currentLine=786433;
 //BA.debugLineNum = 786433;BA.debugLine="Drawer.LeftOpen = False";
mostCurrent._drawer._setleftopen /*boolean*/ (null,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=786434;
 //BA.debugLineNum = 786434;BA.debugLine="ShowPage5";
_showpage5();
RDebugUtils.currentLine=786435;
 //BA.debugLineNum = 786435;BA.debugLine="End Sub";
return "";
}
public static String  _showpage5() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "showpage5", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "showpage5", null));}
RDebugUtils.currentLine=1245184;
 //BA.debugLineNum = 1245184;BA.debugLine="Sub ShowPage5";
RDebugUtils.currentLine=1245185;
 //BA.debugLineNum = 1245185;BA.debugLine="pnlMain.RemoveAllViews";
mostCurrent._pnlmain.RemoveAllViews();
RDebugUtils.currentLine=1245186;
 //BA.debugLineNum = 1245186;BA.debugLine="pnlMain.LoadLayout(\"page5\")";
mostCurrent._pnlmain.LoadLayout("page5",mostCurrent.activityBA);
RDebugUtils.currentLine=1245187;
 //BA.debugLineNum = 1245187;BA.debugLine="lblTitle.Text = \"Temperature Converter\"";
mostCurrent._lbltitle.setText(BA.ObjectToCharSequence("Temperature Converter"));
RDebugUtils.currentLine=1245189;
 //BA.debugLineNum = 1245189;BA.debugLine="items.Clear";
mostCurrent._items.Clear();
RDebugUtils.currentLine=1245190;
 //BA.debugLineNum = 1245190;BA.debugLine="items.AddAll(Array As String(\"°C\",\"°F\",\"K\",\"°R\"))";
mostCurrent._items.AddAll(anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{"°C","°F","K","°R"}));
RDebugUtils.currentLine=1245191;
 //BA.debugLineNum = 1245191;BA.debugLine="setspinner(items, Colors.ARGB(64, 255, 255, 255))";
_setspinner(mostCurrent._items,(Object)(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (64),(int) (255),(int) (255),(int) (255))));
RDebugUtils.currentLine=1245192;
 //BA.debugLineNum = 1245192;BA.debugLine="End Sub";
return "";
}
public static String  _btnpage6_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btnpage6_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btnpage6_click", null));}
RDebugUtils.currentLine=851968;
 //BA.debugLineNum = 851968;BA.debugLine="Sub btnPage6_Click";
RDebugUtils.currentLine=851969;
 //BA.debugLineNum = 851969;BA.debugLine="Drawer.LeftOpen = False";
mostCurrent._drawer._setleftopen /*boolean*/ (null,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=851970;
 //BA.debugLineNum = 851970;BA.debugLine="ShowPage6";
_showpage6();
RDebugUtils.currentLine=851971;
 //BA.debugLineNum = 851971;BA.debugLine="End Sub";
return "";
}
public static String  _showpage6() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "showpage6", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "showpage6", null));}
RDebugUtils.currentLine=1310720;
 //BA.debugLineNum = 1310720;BA.debugLine="Sub ShowPage6";
RDebugUtils.currentLine=1310721;
 //BA.debugLineNum = 1310721;BA.debugLine="pnlMain.RemoveAllViews";
mostCurrent._pnlmain.RemoveAllViews();
RDebugUtils.currentLine=1310722;
 //BA.debugLineNum = 1310722;BA.debugLine="pnlMain.LoadLayout(\"page6\")";
mostCurrent._pnlmain.LoadLayout("page6",mostCurrent.activityBA);
RDebugUtils.currentLine=1310723;
 //BA.debugLineNum = 1310723;BA.debugLine="lblTitle.Text = \"Area Converter\"";
mostCurrent._lbltitle.setText(BA.ObjectToCharSequence("Area Converter"));
RDebugUtils.currentLine=1310725;
 //BA.debugLineNum = 1310725;BA.debugLine="items.Clear";
mostCurrent._items.Clear();
RDebugUtils.currentLine=1310726;
 //BA.debugLineNum = 1310726;BA.debugLine="items.AddAll(Array As String(\"mm²\",\"cm²\",\"m²\",\"km";
mostCurrent._items.AddAll(anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{"mm²","cm²","m²","km²","in²","ft²","yd²"}));
RDebugUtils.currentLine=1310727;
 //BA.debugLineNum = 1310727;BA.debugLine="setspinner(items, Colors.ARGB(64, 255, 255, 255))";
_setspinner(mostCurrent._items,(Object)(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (64),(int) (255),(int) (255),(int) (255))));
RDebugUtils.currentLine=1310728;
 //BA.debugLineNum = 1310728;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=5832704;
 //BA.debugLineNum = 5832704;BA.debugLine="Sub convert";
RDebugUtils.currentLine=5832705;
 //BA.debugLineNum = 5832705;BA.debugLine="Dim fromUnit, toUnit As String";
_fromunit = "";
_tounit = "";
RDebugUtils.currentLine=5832706;
 //BA.debugLineNum = 5832706;BA.debugLine="Dim result As String";
_result = "";
RDebugUtils.currentLine=5832707;
 //BA.debugLineNum = 5832707;BA.debugLine="Dim ans As Double";
_ans = 0;
RDebugUtils.currentLine=5832708;
 //BA.debugLineNum = 5832708;BA.debugLine="Dim input As Double";
_input = 0;
RDebugUtils.currentLine=5832709;
 //BA.debugLineNum = 5832709;BA.debugLine="Dim choice As String = lblTitle.Text";
_choice = mostCurrent._lbltitle.getText();
RDebugUtils.currentLine=5832711;
 //BA.debugLineNum = 5832711;BA.debugLine="If EditText2.Text = \"\" Then";
if ((mostCurrent._edittext2.getText()).equals("")) { 
RDebugUtils.currentLine=5832712;
 //BA.debugLineNum = 5832712;BA.debugLine="input = 0";
_input = 0;
 }else {
RDebugUtils.currentLine=5832714;
 //BA.debugLineNum = 5832714;BA.debugLine="input = EditText2.Text";
_input = (double)(Double.parseDouble(mostCurrent._edittext2.getText()));
 };
RDebugUtils.currentLine=5832717;
 //BA.debugLineNum = 5832717;BA.debugLine="fromUnit = Spinner2.SelectedItem";
_fromunit = mostCurrent._spinner2.getSelectedItem();
RDebugUtils.currentLine=5832718;
 //BA.debugLineNum = 5832718;BA.debugLine="toUnit = Spinner3.SelectedItem";
_tounit = mostCurrent._spinner3.getSelectedItem();
RDebugUtils.currentLine=5832720;
 //BA.debugLineNum = 5832720;BA.debugLine="Select choice";
switch (BA.switchObjectToInt(_choice,"Length Converter","Weight Converter","Volume Converter","Speed Converter","Temperature Converter","Area Converter")) {
case 0: {
RDebugUtils.currentLine=5832722;
 //BA.debugLineNum = 5832722;BA.debugLine="Dim toMeter As Map";
_tometer = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=5832723;
 //BA.debugLineNum = 5832723;BA.debugLine="toMeter.Initialize";
_tometer.Initialize();
RDebugUtils.currentLine=5832724;
 //BA.debugLineNum = 5832724;BA.debugLine="toMeter.Put(\"mm\", 0.001)      : toMeter.Put(\"cm";
_tometer.Put((Object)("mm"),(Object)(0.001));
RDebugUtils.currentLine=5832724;
 //BA.debugLineNum = 5832724;BA.debugLine="toMeter.Put(\"mm\", 0.001)      : toMeter.Put(\"cm";
_tometer.Put((Object)("cm"),(Object)(0.01));
RDebugUtils.currentLine=5832725;
 //BA.debugLineNum = 5832725;BA.debugLine="toMeter.Put(\"m\", 1)           : toMeter.Put(\"km";
_tometer.Put((Object)("m"),(Object)(1));
RDebugUtils.currentLine=5832725;
 //BA.debugLineNum = 5832725;BA.debugLine="toMeter.Put(\"m\", 1)           : toMeter.Put(\"km";
_tometer.Put((Object)("km"),(Object)(1000));
RDebugUtils.currentLine=5832726;
 //BA.debugLineNum = 5832726;BA.debugLine="toMeter.Put(\"in\", 0.0254)     : toMeter.Put(\"ft";
_tometer.Put((Object)("in"),(Object)(0.0254));
RDebugUtils.currentLine=5832726;
 //BA.debugLineNum = 5832726;BA.debugLine="toMeter.Put(\"in\", 0.0254)     : toMeter.Put(\"ft";
_tometer.Put((Object)("ft"),(Object)(0.3048));
RDebugUtils.currentLine=5832727;
 //BA.debugLineNum = 5832727;BA.debugLine="toMeter.Put(\"yd\", 0.9144)     : toMeter.Put(\"mi";
_tometer.Put((Object)("yd"),(Object)(0.9144));
RDebugUtils.currentLine=5832727;
 //BA.debugLineNum = 5832727;BA.debugLine="toMeter.Put(\"yd\", 0.9144)     : toMeter.Put(\"mi";
_tometer.Put((Object)("mi"),(Object)(1609.344));
RDebugUtils.currentLine=5832728;
 //BA.debugLineNum = 5832728;BA.debugLine="toMeter.Put(\"µm\", 0.000001)";
_tometer.Put((Object)("µm"),(Object)(0.000001));
RDebugUtils.currentLine=5832729;
 //BA.debugLineNum = 5832729;BA.debugLine="ans = input * toMeter.Get(fromUnit) / toMeter.G";
_ans = _input*(double)(BA.ObjectToNumber(_tometer.Get((Object)(_fromunit))))/(double)(double)(BA.ObjectToNumber(_tometer.Get((Object)(_tounit))));
 break; }
case 1: {
RDebugUtils.currentLine=5832732;
 //BA.debugLineNum = 5832732;BA.debugLine="Dim toGram As Map";
_togram = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=5832733;
 //BA.debugLineNum = 5832733;BA.debugLine="toGram.Initialize";
_togram.Initialize();
RDebugUtils.currentLine=5832734;
 //BA.debugLineNum = 5832734;BA.debugLine="toGram.Put(\"µg\", 0.000001)    : toGram.Put(\"mg\"";
_togram.Put((Object)("µg"),(Object)(0.000001));
RDebugUtils.currentLine=5832734;
 //BA.debugLineNum = 5832734;BA.debugLine="toGram.Put(\"µg\", 0.000001)    : toGram.Put(\"mg\"";
_togram.Put((Object)("mg"),(Object)(0.001));
RDebugUtils.currentLine=5832735;
 //BA.debugLineNum = 5832735;BA.debugLine="toGram.Put(\"g\", 1)            : toGram.Put(\"kg\"";
_togram.Put((Object)("g"),(Object)(1));
RDebugUtils.currentLine=5832735;
 //BA.debugLineNum = 5832735;BA.debugLine="toGram.Put(\"g\", 1)            : toGram.Put(\"kg\"";
_togram.Put((Object)("kg"),(Object)(1000));
RDebugUtils.currentLine=5832736;
 //BA.debugLineNum = 5832736;BA.debugLine="toGram.Put(\"t\", 1000000)      : toGram.Put(\"oz\"";
_togram.Put((Object)("t"),(Object)(1000000));
RDebugUtils.currentLine=5832736;
 //BA.debugLineNum = 5832736;BA.debugLine="toGram.Put(\"t\", 1000000)      : toGram.Put(\"oz\"";
_togram.Put((Object)("oz"),(Object)(28.3495));
RDebugUtils.currentLine=5832737;
 //BA.debugLineNum = 5832737;BA.debugLine="toGram.Put(\"lb\", 453.592)     : toGram.Put(\"st\"";
_togram.Put((Object)("lb"),(Object)(453.592));
RDebugUtils.currentLine=5832737;
 //BA.debugLineNum = 5832737;BA.debugLine="toGram.Put(\"lb\", 453.592)     : toGram.Put(\"st\"";
_togram.Put((Object)("st"),(Object)(6350.29));
RDebugUtils.currentLine=5832738;
 //BA.debugLineNum = 5832738;BA.debugLine="toGram.Put(\"ct\", 0.2)         : toGram.Put(\"ton";
_togram.Put((Object)("ct"),(Object)(0.2));
RDebugUtils.currentLine=5832738;
 //BA.debugLineNum = 5832738;BA.debugLine="toGram.Put(\"ct\", 0.2)         : toGram.Put(\"ton";
_togram.Put((Object)("ton"),(Object)(907185));
RDebugUtils.currentLine=5832739;
 //BA.debugLineNum = 5832739;BA.debugLine="ans = input * toGram.Get(fromUnit) / toGram.Get";
_ans = _input*(double)(BA.ObjectToNumber(_togram.Get((Object)(_fromunit))))/(double)(double)(BA.ObjectToNumber(_togram.Get((Object)(_tounit))));
 break; }
case 2: {
RDebugUtils.currentLine=5832742;
 //BA.debugLineNum = 5832742;BA.debugLine="Dim toML As Map";
_toml = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=5832743;
 //BA.debugLineNum = 5832743;BA.debugLine="toML.Initialize";
_toml.Initialize();
RDebugUtils.currentLine=5832744;
 //BA.debugLineNum = 5832744;BA.debugLine="toML.Put(\"mL\", 1)             : toML.Put(\"L\", 1";
_toml.Put((Object)("mL"),(Object)(1));
RDebugUtils.currentLine=5832744;
 //BA.debugLineNum = 5832744;BA.debugLine="toML.Put(\"mL\", 1)             : toML.Put(\"L\", 1";
_toml.Put((Object)("L"),(Object)(1000));
RDebugUtils.currentLine=5832745;
 //BA.debugLineNum = 5832745;BA.debugLine="toML.Put(\"kL\", 1000000)       : toML.Put(\"cm³\",";
_toml.Put((Object)("kL"),(Object)(1000000));
RDebugUtils.currentLine=5832745;
 //BA.debugLineNum = 5832745;BA.debugLine="toML.Put(\"kL\", 1000000)       : toML.Put(\"cm³\",";
_toml.Put((Object)("cm³"),(Object)(1));
RDebugUtils.currentLine=5832746;
 //BA.debugLineNum = 5832746;BA.debugLine="toML.Put(\"m³\", 1000000)       : toML.Put(\"in³\",";
_toml.Put((Object)("m³"),(Object)(1000000));
RDebugUtils.currentLine=5832746;
 //BA.debugLineNum = 5832746;BA.debugLine="toML.Put(\"m³\", 1000000)       : toML.Put(\"in³\",";
_toml.Put((Object)("in³"),(Object)(16.3871));
RDebugUtils.currentLine=5832747;
 //BA.debugLineNum = 5832747;BA.debugLine="toML.Put(\"ft³\", 28316.8)";
_toml.Put((Object)("ft³"),(Object)(28316.8));
RDebugUtils.currentLine=5832748;
 //BA.debugLineNum = 5832748;BA.debugLine="ans = input * toML.Get(fromUnit) / toML.Get(toU";
_ans = _input*(double)(BA.ObjectToNumber(_toml.Get((Object)(_fromunit))))/(double)(double)(BA.ObjectToNumber(_toml.Get((Object)(_tounit))));
 break; }
case 3: {
RDebugUtils.currentLine=5832751;
 //BA.debugLineNum = 5832751;BA.debugLine="Dim toMS As Map";
_toms = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=5832752;
 //BA.debugLineNum = 5832752;BA.debugLine="toMS.Initialize";
_toms.Initialize();
RDebugUtils.currentLine=5832753;
 //BA.debugLineNum = 5832753;BA.debugLine="toMS.Put(\"m/s\", 1)            : toMS.Put(\"km/h\"";
_toms.Put((Object)("m/s"),(Object)(1));
RDebugUtils.currentLine=5832753;
 //BA.debugLineNum = 5832753;BA.debugLine="toMS.Put(\"m/s\", 1)            : toMS.Put(\"km/h\"";
_toms.Put((Object)("km/h"),(Object)(0.277778));
RDebugUtils.currentLine=5832754;
 //BA.debugLineNum = 5832754;BA.debugLine="toMS.Put(\"mph\", 0.44704)      : toMS.Put(\"knot\"";
_toms.Put((Object)("mph"),(Object)(0.44704));
RDebugUtils.currentLine=5832754;
 //BA.debugLineNum = 5832754;BA.debugLine="toMS.Put(\"mph\", 0.44704)      : toMS.Put(\"knot\"";
_toms.Put((Object)("knot"),(Object)(0.514444));
RDebugUtils.currentLine=5832755;
 //BA.debugLineNum = 5832755;BA.debugLine="toMS.Put(\"ft/s\", 0.3048)";
_toms.Put((Object)("ft/s"),(Object)(0.3048));
RDebugUtils.currentLine=5832756;
 //BA.debugLineNum = 5832756;BA.debugLine="ans = input * toMS.Get(fromUnit) / toMS.Get(toU";
_ans = _input*(double)(BA.ObjectToNumber(_toms.Get((Object)(_fromunit))))/(double)(double)(BA.ObjectToNumber(_toms.Get((Object)(_tounit))));
 break; }
case 4: {
RDebugUtils.currentLine=5832759;
 //BA.debugLineNum = 5832759;BA.debugLine="Dim celsius As Double";
_celsius = 0;
RDebugUtils.currentLine=5832760;
 //BA.debugLineNum = 5832760;BA.debugLine="Select fromUnit";
switch (BA.switchObjectToInt(_fromunit,"°C","°F","K","°R")) {
case 0: {
RDebugUtils.currentLine=5832761;
 //BA.debugLineNum = 5832761;BA.debugLine="Case \"°C\" : celsius = input";
_celsius = _input;
 break; }
case 1: {
RDebugUtils.currentLine=5832762;
 //BA.debugLineNum = 5832762;BA.debugLine="Case \"°F\" : celsius = (input - 32) / 1.8";
_celsius = (_input-32)/(double)1.8;
 break; }
case 2: {
RDebugUtils.currentLine=5832763;
 //BA.debugLineNum = 5832763;BA.debugLine="Case \"K\"  : celsius = input - 273.15";
_celsius = _input-273.15;
 break; }
case 3: {
RDebugUtils.currentLine=5832764;
 //BA.debugLineNum = 5832764;BA.debugLine="Case \"°R\" : celsius = (input - 491.67) / 1.8";
_celsius = (_input-491.67)/(double)1.8;
 break; }
}
;
RDebugUtils.currentLine=5832766;
 //BA.debugLineNum = 5832766;BA.debugLine="Select toUnit";
switch (BA.switchObjectToInt(_tounit,"°C","°F","K","°R")) {
case 0: {
RDebugUtils.currentLine=5832767;
 //BA.debugLineNum = 5832767;BA.debugLine="Case \"°C\" : ans = celsius";
_ans = _celsius;
 break; }
case 1: {
RDebugUtils.currentLine=5832768;
 //BA.debugLineNum = 5832768;BA.debugLine="Case \"°F\" : ans = (celsius * 1.8) + 32";
_ans = (_celsius*1.8)+32;
 break; }
case 2: {
RDebugUtils.currentLine=5832769;
 //BA.debugLineNum = 5832769;BA.debugLine="Case \"K\"  : ans = celsius + 273.15";
_ans = _celsius+273.15;
 break; }
case 3: {
RDebugUtils.currentLine=5832770;
 //BA.debugLineNum = 5832770;BA.debugLine="Case \"°R\" : ans = (celsius + 273.15) * 1.8";
_ans = (_celsius+273.15)*1.8;
 break; }
}
;
 break; }
case 5: {
RDebugUtils.currentLine=5832774;
 //BA.debugLineNum = 5832774;BA.debugLine="Dim toM2 As Map";
_tom2 = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=5832775;
 //BA.debugLineNum = 5832775;BA.debugLine="toM2.Initialize";
_tom2.Initialize();
RDebugUtils.currentLine=5832776;
 //BA.debugLineNum = 5832776;BA.debugLine="toM2.Put(\"mm²\", 0.000001)     : toM2.Put(\"cm²\",";
_tom2.Put((Object)("mm²"),(Object)(0.000001));
RDebugUtils.currentLine=5832776;
 //BA.debugLineNum = 5832776;BA.debugLine="toM2.Put(\"mm²\", 0.000001)     : toM2.Put(\"cm²\",";
_tom2.Put((Object)("cm²"),(Object)(0.0001));
RDebugUtils.currentLine=5832777;
 //BA.debugLineNum = 5832777;BA.debugLine="toM2.Put(\"m²\", 1)             : toM2.Put(\"km²\",";
_tom2.Put((Object)("m²"),(Object)(1));
RDebugUtils.currentLine=5832777;
 //BA.debugLineNum = 5832777;BA.debugLine="toM2.Put(\"m²\", 1)             : toM2.Put(\"km²\",";
_tom2.Put((Object)("km²"),(Object)(1000000));
RDebugUtils.currentLine=5832778;
 //BA.debugLineNum = 5832778;BA.debugLine="toM2.Put(\"in²\", 0.00064516)   : toM2.Put(\"ft²\",";
_tom2.Put((Object)("in²"),(Object)(0.00064516));
RDebugUtils.currentLine=5832778;
 //BA.debugLineNum = 5832778;BA.debugLine="toM2.Put(\"in²\", 0.00064516)   : toM2.Put(\"ft²\",";
_tom2.Put((Object)("ft²"),(Object)(0.092903));
RDebugUtils.currentLine=5832779;
 //BA.debugLineNum = 5832779;BA.debugLine="toM2.Put(\"yd²\", 0.836127)";
_tom2.Put((Object)("yd²"),(Object)(0.836127));
RDebugUtils.currentLine=5832780;
 //BA.debugLineNum = 5832780;BA.debugLine="ans = input * toM2.Get(fromUnit) / toM2.Get(toU";
_ans = _input*(double)(BA.ObjectToNumber(_tom2.Get((Object)(_fromunit))))/(double)(double)(BA.ObjectToNumber(_tom2.Get((Object)(_tounit))));
 break; }
default: {
RDebugUtils.currentLine=5832783;
 //BA.debugLineNum = 5832783;BA.debugLine="ans = 0";
_ans = 0;
 break; }
}
;
RDebugUtils.currentLine=5832786;
 //BA.debugLineNum = 5832786;BA.debugLine="result = NumberFormat(ans, 1, 4) & \" \" & toUnit";
_result = anywheresoftware.b4a.keywords.Common.NumberFormat(_ans,(int) (1),(int) (4))+" "+_tounit;
RDebugUtils.currentLine=5832787;
 //BA.debugLineNum = 5832787;BA.debugLine="Label6.Text = result";
mostCurrent._label6.setText(BA.ObjectToCharSequence(_result));
RDebugUtils.currentLine=5832788;
 //BA.debugLineNum = 5832788;BA.debugLine="End Sub";
return "";
}
public static String  _edittext1_enterpressed() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "edittext1_enterpressed", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "edittext1_enterpressed", null));}
RDebugUtils.currentLine=7143424;
 //BA.debugLineNum = 7143424;BA.debugLine="Private Sub EditText1_EnterPressed";
RDebugUtils.currentLine=7143425;
 //BA.debugLineNum = 7143425;BA.debugLine="qick";
_qick();
RDebugUtils.currentLine=7143426;
 //BA.debugLineNum = 7143426;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=7012352;
 //BA.debugLineNum = 7012352;BA.debugLine="Sub qick";
RDebugUtils.currentLine=7012353;
 //BA.debugLineNum = 7012353;BA.debugLine="Dim spin As String = Spinner1.SelectedItem";
_spin = mostCurrent._spinner1.getSelectedItem();
RDebugUtils.currentLine=7012354;
 //BA.debugLineNum = 7012354;BA.debugLine="Dim choice As String = lblTitle.Text";
_choice = mostCurrent._lbltitle.getText();
RDebugUtils.currentLine=7012355;
 //BA.debugLineNum = 7012355;BA.debugLine="Dim firstinput As Double";
_firstinput = 0;
RDebugUtils.currentLine=7012356;
 //BA.debugLineNum = 7012356;BA.debugLine="If EditText1.Text = \"\" Then";
if ((mostCurrent._edittext1.getText()).equals("")) { 
RDebugUtils.currentLine=7012357;
 //BA.debugLineNum = 7012357;BA.debugLine="firstinput = 0";
_firstinput = 0;
 }else {
RDebugUtils.currentLine=7012359;
 //BA.debugLineNum = 7012359;BA.debugLine="firstinput = EditText1.Text";
_firstinput = (double)(Double.parseDouble(mostCurrent._edittext1.getText()));
 };
RDebugUtils.currentLine=7012361;
 //BA.debugLineNum = 7012361;BA.debugLine="Dim result As String";
_result = "";
RDebugUtils.currentLine=7012362;
 //BA.debugLineNum = 7012362;BA.debugLine="Dim parts() As String = Regex.Split(\" to \", spin)";
_parts = anywheresoftware.b4a.keywords.Common.Regex.Split(" to ",_spin);
RDebugUtils.currentLine=7012363;
 //BA.debugLineNum = 7012363;BA.debugLine="Dim fromUnit As String = parts(0)";
_fromunit = _parts[(int) (0)];
RDebugUtils.currentLine=7012364;
 //BA.debugLineNum = 7012364;BA.debugLine="Dim toUnit As String = parts(1)";
_tounit = _parts[(int) (1)];
RDebugUtils.currentLine=7012365;
 //BA.debugLineNum = 7012365;BA.debugLine="Dim ans As Double";
_ans = 0;
RDebugUtils.currentLine=7012367;
 //BA.debugLineNum = 7012367;BA.debugLine="Select choice";
switch (BA.switchObjectToInt(_choice,"Length Converter","Weight Converter","Volume Converter","Speed Converter","Temperature Converter","Area Converter")) {
case 0: {
RDebugUtils.currentLine=7012369;
 //BA.debugLineNum = 7012369;BA.debugLine="Dim toMeter As Map";
_tometer = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=7012370;
 //BA.debugLineNum = 7012370;BA.debugLine="toMeter.Initialize";
_tometer.Initialize();
RDebugUtils.currentLine=7012371;
 //BA.debugLineNum = 7012371;BA.debugLine="toMeter.Put(\"mm\", 0.001)";
_tometer.Put((Object)("mm"),(Object)(0.001));
RDebugUtils.currentLine=7012372;
 //BA.debugLineNum = 7012372;BA.debugLine="toMeter.Put(\"cm\", 0.01)";
_tometer.Put((Object)("cm"),(Object)(0.01));
RDebugUtils.currentLine=7012373;
 //BA.debugLineNum = 7012373;BA.debugLine="toMeter.Put(\"m\", 1)";
_tometer.Put((Object)("m"),(Object)(1));
RDebugUtils.currentLine=7012374;
 //BA.debugLineNum = 7012374;BA.debugLine="toMeter.Put(\"km\", 1000)";
_tometer.Put((Object)("km"),(Object)(1000));
RDebugUtils.currentLine=7012375;
 //BA.debugLineNum = 7012375;BA.debugLine="toMeter.Put(\"in\", 0.0254)";
_tometer.Put((Object)("in"),(Object)(0.0254));
RDebugUtils.currentLine=7012376;
 //BA.debugLineNum = 7012376;BA.debugLine="toMeter.Put(\"ft\", 0.3048)";
_tometer.Put((Object)("ft"),(Object)(0.3048));
RDebugUtils.currentLine=7012377;
 //BA.debugLineNum = 7012377;BA.debugLine="toMeter.Put(\"yd\", 0.9144)";
_tometer.Put((Object)("yd"),(Object)(0.9144));
RDebugUtils.currentLine=7012378;
 //BA.debugLineNum = 7012378;BA.debugLine="toMeter.Put(\"mi\", 1609.344)";
_tometer.Put((Object)("mi"),(Object)(1609.344));
RDebugUtils.currentLine=7012379;
 //BA.debugLineNum = 7012379;BA.debugLine="toMeter.Put(\"µm\", 0.000001)";
_tometer.Put((Object)("µm"),(Object)(0.000001));
RDebugUtils.currentLine=7012380;
 //BA.debugLineNum = 7012380;BA.debugLine="ans = firstinput * toMeter.Get(fromUnit) / toMe";
_ans = _firstinput*(double)(BA.ObjectToNumber(_tometer.Get((Object)(_fromunit))))/(double)(double)(BA.ObjectToNumber(_tometer.Get((Object)(_tounit))));
RDebugUtils.currentLine=7012381;
 //BA.debugLineNum = 7012381;BA.debugLine="result = NumberFormat(ans, 1, 4) & \" \" & toUnit";
_result = anywheresoftware.b4a.keywords.Common.NumberFormat(_ans,(int) (1),(int) (4))+" "+_tounit;
 break; }
case 1: {
RDebugUtils.currentLine=7012384;
 //BA.debugLineNum = 7012384;BA.debugLine="Dim toGram As Map";
_togram = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=7012385;
 //BA.debugLineNum = 7012385;BA.debugLine="toGram.Initialize";
_togram.Initialize();
RDebugUtils.currentLine=7012386;
 //BA.debugLineNum = 7012386;BA.debugLine="toGram.Put(\"µg\", 0.000001)";
_togram.Put((Object)("µg"),(Object)(0.000001));
RDebugUtils.currentLine=7012387;
 //BA.debugLineNum = 7012387;BA.debugLine="toGram.Put(\"mg\", 0.001)";
_togram.Put((Object)("mg"),(Object)(0.001));
RDebugUtils.currentLine=7012388;
 //BA.debugLineNum = 7012388;BA.debugLine="toGram.Put(\"g\", 1)";
_togram.Put((Object)("g"),(Object)(1));
RDebugUtils.currentLine=7012389;
 //BA.debugLineNum = 7012389;BA.debugLine="toGram.Put(\"kg\", 1000)";
_togram.Put((Object)("kg"),(Object)(1000));
RDebugUtils.currentLine=7012390;
 //BA.debugLineNum = 7012390;BA.debugLine="toGram.Put(\"t\", 1000000)";
_togram.Put((Object)("t"),(Object)(1000000));
RDebugUtils.currentLine=7012391;
 //BA.debugLineNum = 7012391;BA.debugLine="toGram.Put(\"oz\", 28.3495)";
_togram.Put((Object)("oz"),(Object)(28.3495));
RDebugUtils.currentLine=7012392;
 //BA.debugLineNum = 7012392;BA.debugLine="toGram.Put(\"lb\", 453.592)";
_togram.Put((Object)("lb"),(Object)(453.592));
RDebugUtils.currentLine=7012393;
 //BA.debugLineNum = 7012393;BA.debugLine="toGram.Put(\"st\", 6350.29)";
_togram.Put((Object)("st"),(Object)(6350.29));
RDebugUtils.currentLine=7012394;
 //BA.debugLineNum = 7012394;BA.debugLine="toGram.Put(\"ct\", 0.2)";
_togram.Put((Object)("ct"),(Object)(0.2));
RDebugUtils.currentLine=7012395;
 //BA.debugLineNum = 7012395;BA.debugLine="toGram.Put(\"ton\", 907185)";
_togram.Put((Object)("ton"),(Object)(907185));
RDebugUtils.currentLine=7012396;
 //BA.debugLineNum = 7012396;BA.debugLine="ans = firstinput * toGram.Get(fromUnit) / toGra";
_ans = _firstinput*(double)(BA.ObjectToNumber(_togram.Get((Object)(_fromunit))))/(double)(double)(BA.ObjectToNumber(_togram.Get((Object)(_tounit))));
RDebugUtils.currentLine=7012397;
 //BA.debugLineNum = 7012397;BA.debugLine="result = NumberFormat(ans, 1, 4) & \" \" & toUnit";
_result = anywheresoftware.b4a.keywords.Common.NumberFormat(_ans,(int) (1),(int) (4))+" "+_tounit;
 break; }
case 2: {
RDebugUtils.currentLine=7012400;
 //BA.debugLineNum = 7012400;BA.debugLine="Dim toML As Map";
_toml = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=7012401;
 //BA.debugLineNum = 7012401;BA.debugLine="toML.Initialize";
_toml.Initialize();
RDebugUtils.currentLine=7012402;
 //BA.debugLineNum = 7012402;BA.debugLine="toML.Put(\"mL\", 1)";
_toml.Put((Object)("mL"),(Object)(1));
RDebugUtils.currentLine=7012403;
 //BA.debugLineNum = 7012403;BA.debugLine="toML.Put(\"L\", 1000)";
_toml.Put((Object)("L"),(Object)(1000));
RDebugUtils.currentLine=7012404;
 //BA.debugLineNum = 7012404;BA.debugLine="toML.Put(\"kL\", 1000000)";
_toml.Put((Object)("kL"),(Object)(1000000));
RDebugUtils.currentLine=7012405;
 //BA.debugLineNum = 7012405;BA.debugLine="toML.Put(\"cm³\", 1)";
_toml.Put((Object)("cm³"),(Object)(1));
RDebugUtils.currentLine=7012406;
 //BA.debugLineNum = 7012406;BA.debugLine="toML.Put(\"m³\", 1000000)";
_toml.Put((Object)("m³"),(Object)(1000000));
RDebugUtils.currentLine=7012407;
 //BA.debugLineNum = 7012407;BA.debugLine="toML.Put(\"in³\", 16.3871)";
_toml.Put((Object)("in³"),(Object)(16.3871));
RDebugUtils.currentLine=7012408;
 //BA.debugLineNum = 7012408;BA.debugLine="toML.Put(\"ft³\", 28316.8)";
_toml.Put((Object)("ft³"),(Object)(28316.8));
RDebugUtils.currentLine=7012409;
 //BA.debugLineNum = 7012409;BA.debugLine="ans = firstinput * toML.Get(fromUnit) / toML.Ge";
_ans = _firstinput*(double)(BA.ObjectToNumber(_toml.Get((Object)(_fromunit))))/(double)(double)(BA.ObjectToNumber(_toml.Get((Object)(_tounit))));
RDebugUtils.currentLine=7012410;
 //BA.debugLineNum = 7012410;BA.debugLine="result = NumberFormat(ans, 1, 4) & \" \" & toUnit";
_result = anywheresoftware.b4a.keywords.Common.NumberFormat(_ans,(int) (1),(int) (4))+" "+_tounit;
 break; }
case 3: {
RDebugUtils.currentLine=7012413;
 //BA.debugLineNum = 7012413;BA.debugLine="Dim toMS As Map";
_toms = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=7012414;
 //BA.debugLineNum = 7012414;BA.debugLine="toMS.Initialize";
_toms.Initialize();
RDebugUtils.currentLine=7012415;
 //BA.debugLineNum = 7012415;BA.debugLine="toMS.Put(\"m/s\", 1)";
_toms.Put((Object)("m/s"),(Object)(1));
RDebugUtils.currentLine=7012416;
 //BA.debugLineNum = 7012416;BA.debugLine="toMS.Put(\"km/h\", 0.277778)";
_toms.Put((Object)("km/h"),(Object)(0.277778));
RDebugUtils.currentLine=7012417;
 //BA.debugLineNum = 7012417;BA.debugLine="toMS.Put(\"mph\", 0.44704)";
_toms.Put((Object)("mph"),(Object)(0.44704));
RDebugUtils.currentLine=7012418;
 //BA.debugLineNum = 7012418;BA.debugLine="toMS.Put(\"knot\", 0.514444)";
_toms.Put((Object)("knot"),(Object)(0.514444));
RDebugUtils.currentLine=7012419;
 //BA.debugLineNum = 7012419;BA.debugLine="toMS.Put(\"ft/s\", 0.3048)";
_toms.Put((Object)("ft/s"),(Object)(0.3048));
RDebugUtils.currentLine=7012420;
 //BA.debugLineNum = 7012420;BA.debugLine="ans = firstinput * toMS.Get(fromUnit) / toMS.Ge";
_ans = _firstinput*(double)(BA.ObjectToNumber(_toms.Get((Object)(_fromunit))))/(double)(double)(BA.ObjectToNumber(_toms.Get((Object)(_tounit))));
RDebugUtils.currentLine=7012421;
 //BA.debugLineNum = 7012421;BA.debugLine="result = NumberFormat(ans, 1, 4) & \" \" & toUnit";
_result = anywheresoftware.b4a.keywords.Common.NumberFormat(_ans,(int) (1),(int) (4))+" "+_tounit;
 break; }
case 4: {
RDebugUtils.currentLine=7012424;
 //BA.debugLineNum = 7012424;BA.debugLine="Dim celsius As Double";
_celsius = 0;
RDebugUtils.currentLine=7012425;
 //BA.debugLineNum = 7012425;BA.debugLine="Select fromUnit";
switch (BA.switchObjectToInt(_fromunit,"°C","°F","K","°R")) {
case 0: {
RDebugUtils.currentLine=7012426;
 //BA.debugLineNum = 7012426;BA.debugLine="Case \"°C\" : celsius = firstinput";
_celsius = _firstinput;
 break; }
case 1: {
RDebugUtils.currentLine=7012427;
 //BA.debugLineNum = 7012427;BA.debugLine="Case \"°F\" : celsius = (firstinput - 32) / 1.8";
_celsius = (_firstinput-32)/(double)1.8;
 break; }
case 2: {
RDebugUtils.currentLine=7012428;
 //BA.debugLineNum = 7012428;BA.debugLine="Case \"K\"  : celsius = firstinput - 273.15";
_celsius = _firstinput-273.15;
 break; }
case 3: {
RDebugUtils.currentLine=7012429;
 //BA.debugLineNum = 7012429;BA.debugLine="Case \"°R\" : celsius = (firstinput - 491.67) /";
_celsius = (_firstinput-491.67)/(double)1.8;
 break; }
}
;
RDebugUtils.currentLine=7012431;
 //BA.debugLineNum = 7012431;BA.debugLine="Select toUnit";
switch (BA.switchObjectToInt(_tounit,"°C","°F","K","°R")) {
case 0: {
RDebugUtils.currentLine=7012432;
 //BA.debugLineNum = 7012432;BA.debugLine="Case \"°C\" : ans = celsius";
_ans = _celsius;
 break; }
case 1: {
RDebugUtils.currentLine=7012433;
 //BA.debugLineNum = 7012433;BA.debugLine="Case \"°F\" : ans = (celsius * 1.8) + 32";
_ans = (_celsius*1.8)+32;
 break; }
case 2: {
RDebugUtils.currentLine=7012434;
 //BA.debugLineNum = 7012434;BA.debugLine="Case \"K\"  : ans = celsius + 273.15";
_ans = _celsius+273.15;
 break; }
case 3: {
RDebugUtils.currentLine=7012435;
 //BA.debugLineNum = 7012435;BA.debugLine="Case \"°R\" : ans = (celsius + 273.15) * 1.8";
_ans = (_celsius+273.15)*1.8;
 break; }
}
;
RDebugUtils.currentLine=7012437;
 //BA.debugLineNum = 7012437;BA.debugLine="result = NumberFormat(ans, 1, 4) & \" \" & toUnit";
_result = anywheresoftware.b4a.keywords.Common.NumberFormat(_ans,(int) (1),(int) (4))+" "+_tounit;
 break; }
case 5: {
RDebugUtils.currentLine=7012440;
 //BA.debugLineNum = 7012440;BA.debugLine="Dim toM2 As Map";
_tom2 = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=7012441;
 //BA.debugLineNum = 7012441;BA.debugLine="toM2.Initialize";
_tom2.Initialize();
RDebugUtils.currentLine=7012442;
 //BA.debugLineNum = 7012442;BA.debugLine="toM2.Put(\"mm²\", 0.000001)";
_tom2.Put((Object)("mm²"),(Object)(0.000001));
RDebugUtils.currentLine=7012443;
 //BA.debugLineNum = 7012443;BA.debugLine="toM2.Put(\"cm²\", 0.0001)";
_tom2.Put((Object)("cm²"),(Object)(0.0001));
RDebugUtils.currentLine=7012444;
 //BA.debugLineNum = 7012444;BA.debugLine="toM2.Put(\"m²\", 1)";
_tom2.Put((Object)("m²"),(Object)(1));
RDebugUtils.currentLine=7012445;
 //BA.debugLineNum = 7012445;BA.debugLine="toM2.Put(\"km²\", 1000000)";
_tom2.Put((Object)("km²"),(Object)(1000000));
RDebugUtils.currentLine=7012446;
 //BA.debugLineNum = 7012446;BA.debugLine="toM2.Put(\"in²\", 0.00064516)";
_tom2.Put((Object)("in²"),(Object)(0.00064516));
RDebugUtils.currentLine=7012447;
 //BA.debugLineNum = 7012447;BA.debugLine="toM2.Put(\"ft²\", 0.092903)";
_tom2.Put((Object)("ft²"),(Object)(0.092903));
RDebugUtils.currentLine=7012448;
 //BA.debugLineNum = 7012448;BA.debugLine="toM2.Put(\"yd²\", 0.836127)";
_tom2.Put((Object)("yd²"),(Object)(0.836127));
RDebugUtils.currentLine=7012449;
 //BA.debugLineNum = 7012449;BA.debugLine="ans = firstinput * toM2.Get(fromUnit) / toM2.Ge";
_ans = _firstinput*(double)(BA.ObjectToNumber(_tom2.Get((Object)(_fromunit))))/(double)(double)(BA.ObjectToNumber(_tom2.Get((Object)(_tounit))));
RDebugUtils.currentLine=7012450;
 //BA.debugLineNum = 7012450;BA.debugLine="result = NumberFormat(ans, 1, 4) & \" \" & toUnit";
_result = anywheresoftware.b4a.keywords.Common.NumberFormat(_ans,(int) (1),(int) (4))+" "+_tounit;
 break; }
default: {
RDebugUtils.currentLine=7012453;
 //BA.debugLineNum = 7012453;BA.debugLine="result = \"0\"";
_result = "0";
 break; }
}
;
RDebugUtils.currentLine=7012456;
 //BA.debugLineNum = 7012456;BA.debugLine="Label3.Text = result";
mostCurrent._label3.setText(BA.ObjectToCharSequence(_result));
RDebugUtils.currentLine=7012457;
 //BA.debugLineNum = 7012457;BA.debugLine="End Sub";
return "";
}
public static String  _edittext2_enterpressed() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "edittext2_enterpressed", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "edittext2_enterpressed", null));}
RDebugUtils.currentLine=5242880;
 //BA.debugLineNum = 5242880;BA.debugLine="Private Sub EditText2_EnterPressed";
RDebugUtils.currentLine=5242881;
 //BA.debugLineNum = 5242881;BA.debugLine="convert";
_convert();
RDebugUtils.currentLine=5242882;
 //BA.debugLineNum = 5242882;BA.debugLine="End Sub";
return "";
}
public static String  _pnl1_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "pnl1_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "pnl1_click", null));}
RDebugUtils.currentLine=1769472;
 //BA.debugLineNum = 1769472;BA.debugLine="Private Sub pnl1_Click";
RDebugUtils.currentLine=1769473;
 //BA.debugLineNum = 1769473;BA.debugLine="Drawer.LeftOpen = False";
mostCurrent._drawer._setleftopen /*boolean*/ (null,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1769474;
 //BA.debugLineNum = 1769474;BA.debugLine="ShowPage1";
_showpage1();
RDebugUtils.currentLine=1769475;
 //BA.debugLineNum = 1769475;BA.debugLine="End Sub";
return "";
}
public static String  _pnl2_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "pnl2_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "pnl2_click", null));}
RDebugUtils.currentLine=1703936;
 //BA.debugLineNum = 1703936;BA.debugLine="Private Sub pnl2_Click";
RDebugUtils.currentLine=1703937;
 //BA.debugLineNum = 1703937;BA.debugLine="Drawer.LeftOpen = False";
mostCurrent._drawer._setleftopen /*boolean*/ (null,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1703938;
 //BA.debugLineNum = 1703938;BA.debugLine="ShowPage2";
_showpage2();
RDebugUtils.currentLine=1703939;
 //BA.debugLineNum = 1703939;BA.debugLine="End Sub";
return "";
}
public static String  _pnl3_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "pnl3_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "pnl3_click", null));}
RDebugUtils.currentLine=1638400;
 //BA.debugLineNum = 1638400;BA.debugLine="Private Sub pnl3_Click";
RDebugUtils.currentLine=1638401;
 //BA.debugLineNum = 1638401;BA.debugLine="Drawer.LeftOpen = False";
mostCurrent._drawer._setleftopen /*boolean*/ (null,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1638402;
 //BA.debugLineNum = 1638402;BA.debugLine="ShowPage3";
_showpage3();
RDebugUtils.currentLine=1638403;
 //BA.debugLineNum = 1638403;BA.debugLine="End Sub";
return "";
}
public static String  _pnl4_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "pnl4_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "pnl4_click", null));}
RDebugUtils.currentLine=1572864;
 //BA.debugLineNum = 1572864;BA.debugLine="Private Sub pnl4_Click";
RDebugUtils.currentLine=1572865;
 //BA.debugLineNum = 1572865;BA.debugLine="Drawer.LeftOpen = False";
mostCurrent._drawer._setleftopen /*boolean*/ (null,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1572866;
 //BA.debugLineNum = 1572866;BA.debugLine="ShowPage4";
_showpage4();
RDebugUtils.currentLine=1572867;
 //BA.debugLineNum = 1572867;BA.debugLine="End Sub";
return "";
}
public static String  _pnl5_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "pnl5_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "pnl5_click", null));}
RDebugUtils.currentLine=1507328;
 //BA.debugLineNum = 1507328;BA.debugLine="Private Sub pnl5_Click";
RDebugUtils.currentLine=1507329;
 //BA.debugLineNum = 1507329;BA.debugLine="Drawer.LeftOpen = False";
mostCurrent._drawer._setleftopen /*boolean*/ (null,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1507330;
 //BA.debugLineNum = 1507330;BA.debugLine="ShowPage5";
_showpage5();
RDebugUtils.currentLine=1507331;
 //BA.debugLineNum = 1507331;BA.debugLine="End Sub";
return "";
}
public static String  _pnl6_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "pnl6_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "pnl6_click", null));}
RDebugUtils.currentLine=1441792;
 //BA.debugLineNum = 1441792;BA.debugLine="Private Sub pnl6_Click";
RDebugUtils.currentLine=1441793;
 //BA.debugLineNum = 1441793;BA.debugLine="Drawer.LeftOpen = False";
mostCurrent._drawer._setleftopen /*boolean*/ (null,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1441794;
 //BA.debugLineNum = 1441794;BA.debugLine="ShowPage6";
_showpage6();
RDebugUtils.currentLine=1441795;
 //BA.debugLineNum = 1441795;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=4587520;
 //BA.debugLineNum = 4587520;BA.debugLine="Sub setspinner(item As List, color As Object)";
RDebugUtils.currentLine=4587521;
 //BA.debugLineNum = 4587521;BA.debugLine="Spinner2.AddAll(item)";
mostCurrent._spinner2.AddAll(_item);
RDebugUtils.currentLine=4587522;
 //BA.debugLineNum = 4587522;BA.debugLine="Spinner3.AddAll(item)";
mostCurrent._spinner3.AddAll(_item);
RDebugUtils.currentLine=4587523;
 //BA.debugLineNum = 4587523;BA.debugLine="For Each s As Spinner In Array(Spinner1, Spinner2";
_s = new anywheresoftware.b4a.objects.SpinnerWrapper();
{
final Object[] group3 = new Object[]{(Object)(mostCurrent._spinner1.getObject()),(Object)(mostCurrent._spinner2.getObject()),(Object)(mostCurrent._spinner3.getObject())};
final int groupLen3 = group3.length
;int index3 = 0;
;
for (; index3 < groupLen3;index3++){
_s = (anywheresoftware.b4a.objects.SpinnerWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.SpinnerWrapper(), (anywheresoftware.b4a.objects.SpinnerWrapper.B4ASpinner)(group3[index3]));
RDebugUtils.currentLine=4587524;
 //BA.debugLineNum = 4587524;BA.debugLine="s.TextSize = 14";
_s.setTextSize((float) (14));
RDebugUtils.currentLine=4587525;
 //BA.debugLineNum = 4587525;BA.debugLine="Dim pairItems As List";
_pairitems = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=4587526;
 //BA.debugLineNum = 4587526;BA.debugLine="pairItems.Initialize";
_pairitems.Initialize();
RDebugUtils.currentLine=4587527;
 //BA.debugLineNum = 4587527;BA.debugLine="For i = 0 To item.Size - 2";
{
final int step7 = 1;
final int limit7 = (int) (_item.getSize()-2);
_i = (int) (0) ;
for (;_i <= limit7 ;_i = _i + step7 ) {
RDebugUtils.currentLine=4587528;
 //BA.debugLineNum = 4587528;BA.debugLine="pairItems.Add(item.Get(i) & \" to \" & item.Get(i";
_pairitems.Add((Object)(BA.ObjectToString(_item.Get(_i))+" to "+BA.ObjectToString(_item.Get((int) (_i+1)))));
 }
};
RDebugUtils.currentLine=4587531;
 //BA.debugLineNum = 4587531;BA.debugLine="Dim cd As ColorDrawable";
_cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
RDebugUtils.currentLine=4587532;
 //BA.debugLineNum = 4587532;BA.debugLine="cd.Initialize(color, 10dip)";
_cd.Initialize((int)(BA.ObjectToNumber(_color)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)));
RDebugUtils.currentLine=4587533;
 //BA.debugLineNum = 4587533;BA.debugLine="s.Background = cd";
_s.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
 }
};
RDebugUtils.currentLine=4587535;
 //BA.debugLineNum = 4587535;BA.debugLine="Spinner1.AddAll(pairItems)";
mostCurrent._spinner1.AddAll(_pairitems);
RDebugUtils.currentLine=4587536;
 //BA.debugLineNum = 4587536;BA.debugLine="End Sub";
return "";
}
public static String  _spinner1_itemclick(int _position,Object _value) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "spinner1_itemclick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "spinner1_itemclick", new Object[] {_position,_value}));}
RDebugUtils.currentLine=4653056;
 //BA.debugLineNum = 4653056;BA.debugLine="Private Sub Spinner1_ItemClick (Position As Int, V";
RDebugUtils.currentLine=4653057;
 //BA.debugLineNum = 4653057;BA.debugLine="qick";
_qick();
RDebugUtils.currentLine=4653058;
 //BA.debugLineNum = 4653058;BA.debugLine="End Sub";
return "";
}
public static String  _spinner2_itemclick(int _position,Object _value) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "spinner2_itemclick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "spinner2_itemclick", new Object[] {_position,_value}));}
RDebugUtils.currentLine=5308416;
 //BA.debugLineNum = 5308416;BA.debugLine="Private Sub Spinner2_ItemClick (Position As Int, V";
RDebugUtils.currentLine=5308417;
 //BA.debugLineNum = 5308417;BA.debugLine="convert";
_convert();
RDebugUtils.currentLine=5308418;
 //BA.debugLineNum = 5308418;BA.debugLine="End Sub";
return "";
}
public static String  _spinner3_itemclick(int _position,Object _value) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "spinner3_itemclick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "spinner3_itemclick", new Object[] {_position,_value}));}
RDebugUtils.currentLine=5373952;
 //BA.debugLineNum = 5373952;BA.debugLine="Private Sub Spinner3_ItemClick (Position As Int, V";
RDebugUtils.currentLine=5373953;
 //BA.debugLineNum = 5373953;BA.debugLine="convert";
_convert();
RDebugUtils.currentLine=5373954;
 //BA.debugLineNum = 5373954;BA.debugLine="End Sub";
return "";
}
}