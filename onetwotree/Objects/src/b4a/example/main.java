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
public anywheresoftware.b4a.objects.PanelWrapper _pnlbackground = null;
public b4a.example.starter _starter = null;
public static String  _activity_create(boolean _firsttime) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_create", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_create", new Object[] {_firsttime}));}
RDebugUtils.currentLine=65536;
 //BA.debugLineNum = 65536;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
RDebugUtils.currentLine=65538;
 //BA.debugLineNum = 65538;BA.debugLine="pnlBackground.Initialize(\"\")";
mostCurrent._pnlbackground.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=65539;
 //BA.debugLineNum = 65539;BA.debugLine="pnlBackground.Color = Colors.RGB(173, 226, 244)";
mostCurrent._pnlbackground.setColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (173),(int) (226),(int) (244)));
RDebugUtils.currentLine=65540;
 //BA.debugLineNum = 65540;BA.debugLine="Activity.AddView(pnlBackground, 0, 0, 100%x, 100%";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._pnlbackground.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA));
RDebugUtils.currentLine=65542;
 //BA.debugLineNum = 65542;BA.debugLine="BuildHeader";
_buildheader();
RDebugUtils.currentLine=65543;
 //BA.debugLineNum = 65543;BA.debugLine="BuildGrid";
_buildgrid();
RDebugUtils.currentLine=65544;
 //BA.debugLineNum = 65544;BA.debugLine="BuildBottomNav";
_buildbottomnav();
RDebugUtils.currentLine=65545;
 //BA.debugLineNum = 65545;BA.debugLine="End Sub";
return "";
}
public static String  _buildheader() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "buildheader", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "buildheader", null));}
anywheresoftware.b4a.objects.PanelWrapper _pnlheader = null;
anywheresoftware.b4a.objects.PanelWrapper _pnlheaderborder = null;
anywheresoftware.b4a.objects.LabelWrapper _lbltitle = null;
anywheresoftware.b4a.objects.LabelWrapper _lblwelcome = null;
RDebugUtils.currentLine=131072;
 //BA.debugLineNum = 131072;BA.debugLine="Sub BuildHeader";
RDebugUtils.currentLine=131074;
 //BA.debugLineNum = 131074;BA.debugLine="Dim pnlHeader As Panel";
_pnlheader = new anywheresoftware.b4a.objects.PanelWrapper();
RDebugUtils.currentLine=131075;
 //BA.debugLineNum = 131075;BA.debugLine="pnlHeader.Initialize(\"\")";
_pnlheader.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=131076;
 //BA.debugLineNum = 131076;BA.debugLine="pnlHeader.Color = Colors.RGB(255, 204, 51) ' Yell";
_pnlheader.setColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (255),(int) (204),(int) (51)));
RDebugUtils.currentLine=131077;
 //BA.debugLineNum = 131077;BA.debugLine="pnlBackground.AddView(pnlHeader, 5%x, 5%y, 90%x,";
mostCurrent._pnlbackground.AddView((android.view.View)(_pnlheader.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (5),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (5),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (90),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (80)));
RDebugUtils.currentLine=131080;
 //BA.debugLineNum = 131080;BA.debugLine="Dim pnlHeaderBorder As Panel";
_pnlheaderborder = new anywheresoftware.b4a.objects.PanelWrapper();
RDebugUtils.currentLine=131081;
 //BA.debugLineNum = 131081;BA.debugLine="pnlHeaderBorder.Initialize(\"\")";
_pnlheaderborder.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=131082;
 //BA.debugLineNum = 131082;BA.debugLine="pnlHeaderBorder.Color = Colors.Black";
_pnlheaderborder.setColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
RDebugUtils.currentLine=131083;
 //BA.debugLineNum = 131083;BA.debugLine="pnlBackground.AddView(pnlHeaderBorder, pnlHeader.";
mostCurrent._pnlbackground.AddView((android.view.View)(_pnlheaderborder.getObject()),(int) (_pnlheader.getLeft()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2))),(int) (_pnlheader.getTop()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2))),(int) (_pnlheader.getWidth()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (4))),(int) (_pnlheader.getHeight()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (4))));
RDebugUtils.currentLine=131084;
 //BA.debugLineNum = 131084;BA.debugLine="pnlHeaderBorder.SendToBack";
_pnlheaderborder.SendToBack();
RDebugUtils.currentLine=131087;
 //BA.debugLineNum = 131087;BA.debugLine="Dim lblTitle As Label";
_lbltitle = new anywheresoftware.b4a.objects.LabelWrapper();
RDebugUtils.currentLine=131088;
 //BA.debugLineNum = 131088;BA.debugLine="lblTitle.Initialize(\"\")";
_lbltitle.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=131089;
 //BA.debugLineNum = 131089;BA.debugLine="lblTitle.Text = \"STUDENT HUB\"";
_lbltitle.setText(BA.ObjectToCharSequence("STUDENT HUB"));
RDebugUtils.currentLine=131090;
 //BA.debugLineNum = 131090;BA.debugLine="lblTitle.Typeface = Typeface.DEFAULT_BOLD ' Using";
_lbltitle.setTypeface(anywheresoftware.b4a.keywords.Common.Typeface.DEFAULT_BOLD);
RDebugUtils.currentLine=131091;
 //BA.debugLineNum = 131091;BA.debugLine="lblTitle.TextSize = 18";
_lbltitle.setTextSize((float) (18));
RDebugUtils.currentLine=131092;
 //BA.debugLineNum = 131092;BA.debugLine="lblTitle.TextColor = Colors.Black";
_lbltitle.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
RDebugUtils.currentLine=131093;
 //BA.debugLineNum = 131093;BA.debugLine="lblTitle.Gravity = Gravity.CENTER_VERTICAL";
_lbltitle.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_VERTICAL);
RDebugUtils.currentLine=131094;
 //BA.debugLineNum = 131094;BA.debugLine="pnlHeader.AddView(lblTitle, 80dip, 10dip, pnlHead";
_pnlheader.AddView((android.view.View)(_lbltitle.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (80)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (_pnlheader.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (90))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30)));
RDebugUtils.currentLine=131097;
 //BA.debugLineNum = 131097;BA.debugLine="Dim lblWelcome As Label";
_lblwelcome = new anywheresoftware.b4a.objects.LabelWrapper();
RDebugUtils.currentLine=131098;
 //BA.debugLineNum = 131098;BA.debugLine="lblWelcome.Initialize(\"\")";
_lblwelcome.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=131099;
 //BA.debugLineNum = 131099;BA.debugLine="lblWelcome.Text = \"Welcome, Alex!\" & CRLF & \"SEP";
_lblwelcome.setText(BA.ObjectToCharSequence("Welcome, Alex!"+anywheresoftware.b4a.keywords.Common.CRLF+"SEP 24, 2024"));
RDebugUtils.currentLine=131100;
 //BA.debugLineNum = 131100;BA.debugLine="lblWelcome.Typeface = Typeface.DEFAULT_BOLD ' Usi";
_lblwelcome.setTypeface(anywheresoftware.b4a.keywords.Common.Typeface.DEFAULT_BOLD);
RDebugUtils.currentLine=131101;
 //BA.debugLineNum = 131101;BA.debugLine="lblWelcome.TextSize = 14";
_lblwelcome.setTextSize((float) (14));
RDebugUtils.currentLine=131102;
 //BA.debugLineNum = 131102;BA.debugLine="lblWelcome.TextColor = Colors.Black";
_lblwelcome.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
RDebugUtils.currentLine=131103;
 //BA.debugLineNum = 131103;BA.debugLine="pnlBackground.AddView(lblWelcome, 5%x, pnlHeader.";
mostCurrent._pnlbackground.AddView((android.view.View)(_lblwelcome.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (5),mostCurrent.activityBA),(int) (_pnlheader.getTop()+_pnlheader.getHeight()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (90),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
RDebugUtils.currentLine=131104;
 //BA.debugLineNum = 131104;BA.debugLine="End Sub";
return "";
}
public static String  _buildgrid() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "buildgrid", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "buildgrid", null));}
int _starty = 0;
int _btnwidth = 0;
int _btnheight = 0;
int _spacing = 0;
int _leftcol = 0;
int _rightcol = 0;
RDebugUtils.currentLine=196608;
 //BA.debugLineNum = 196608;BA.debugLine="Sub BuildGrid";
RDebugUtils.currentLine=196609;
 //BA.debugLineNum = 196609;BA.debugLine="Dim startY As Int = 230dip";
_starty = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (230));
RDebugUtils.currentLine=196610;
 //BA.debugLineNum = 196610;BA.debugLine="Dim btnWidth As Int = 42%x";
_btnwidth = anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (42),mostCurrent.activityBA);
RDebugUtils.currentLine=196611;
 //BA.debugLineNum = 196611;BA.debugLine="Dim btnHeight As Int = 80dip";
_btnheight = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (80));
RDebugUtils.currentLine=196612;
 //BA.debugLineNum = 196612;BA.debugLine="Dim spacing As Int = 4%x";
_spacing = anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (4),mostCurrent.activityBA);
RDebugUtils.currentLine=196613;
 //BA.debugLineNum = 196613;BA.debugLine="Dim leftCol As Int = 6%x";
_leftcol = anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (6),mostCurrent.activityBA);
RDebugUtils.currentLine=196614;
 //BA.debugLineNum = 196614;BA.debugLine="Dim rightCol As Int = leftCol + btnWidth + spacin";
_rightcol = (int) (_leftcol+_btnwidth+_spacing);
RDebugUtils.currentLine=196617;
 //BA.debugLineNum = 196617;BA.debugLine="CreatePixelButton(\"My Grades\", Colors.RGB(102, 20";
_createpixelbutton("My Grades",anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (102),(int) (204),(int) (255)),_leftcol,_starty,_btnwidth,_btnheight);
RDebugUtils.currentLine=196618;
 //BA.debugLineNum = 196618;BA.debugLine="CreatePixelButton(\"Schedule\", Colors.RGB(153, 204";
_createpixelbutton("Schedule",anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (153),(int) (204),(int) (102)),_rightcol,_starty,_btnwidth,_btnheight);
RDebugUtils.currentLine=196621;
 //BA.debugLineNum = 196621;BA.debugLine="CreatePixelButton(\"Assignments\", Colors.RGB(255,";
_createpixelbutton("Assignments",anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (255),(int) (204),(int) (102)),_leftcol,(int) (_starty+_btnheight+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (15))),_btnwidth,_btnheight);
RDebugUtils.currentLine=196622;
 //BA.debugLineNum = 196622;BA.debugLine="CreatePixelButton(\"Attendance\", Colors.RGB(153, 2";
_createpixelbutton("Attendance",anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (153),(int) (204),(int) (102)),_rightcol,(int) (_starty+_btnheight+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (15))),_btnwidth,_btnheight);
RDebugUtils.currentLine=196625;
 //BA.debugLineNum = 196625;BA.debugLine="CreatePixelButton(\"Profile\", Colors.RGB(255, 204,";
_createpixelbutton("Profile",anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (255),(int) (204),(int) (102)),_leftcol,(int) (_starty+(_btnheight*2)+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30))),_btnwidth,_btnheight);
RDebugUtils.currentLine=196626;
 //BA.debugLineNum = 196626;BA.debugLine="CreatePixelButton(\"Library\", Colors.RGB(255, 128,";
_createpixelbutton("Library",anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (255),(int) (128),(int) (64)),_rightcol,(int) (_starty+(_btnheight*2)+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30))),_btnwidth,_btnheight);
RDebugUtils.currentLine=196627;
 //BA.debugLineNum = 196627;BA.debugLine="End Sub";
return "";
}
public static String  _buildbottomnav() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "buildbottomnav", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "buildbottomnav", null));}
anywheresoftware.b4a.objects.PanelWrapper _pnlnav = null;
anywheresoftware.b4a.objects.PanelWrapper _topborder = null;
RDebugUtils.currentLine=262144;
 //BA.debugLineNum = 262144;BA.debugLine="Sub BuildBottomNav";
RDebugUtils.currentLine=262145;
 //BA.debugLineNum = 262145;BA.debugLine="Dim pnlNav As Panel";
_pnlnav = new anywheresoftware.b4a.objects.PanelWrapper();
RDebugUtils.currentLine=262146;
 //BA.debugLineNum = 262146;BA.debugLine="pnlNav.Initialize(\"\")";
_pnlnav.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=262147;
 //BA.debugLineNum = 262147;BA.debugLine="pnlNav.Color = Colors.RGB(51, 102, 153) ' Dark bl";
_pnlnav.setColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (51),(int) (102),(int) (153)));
RDebugUtils.currentLine=262148;
 //BA.debugLineNum = 262148;BA.debugLine="pnlBackground.AddView(pnlNav, 0, 100%y - 70dip, 1";
mostCurrent._pnlbackground.AddView((android.view.View)(_pnlnav.getObject()),(int) (0),(int) (anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (70))),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (70)));
RDebugUtils.currentLine=262151;
 //BA.debugLineNum = 262151;BA.debugLine="Dim topBorder As Panel";
_topborder = new anywheresoftware.b4a.objects.PanelWrapper();
RDebugUtils.currentLine=262152;
 //BA.debugLineNum = 262152;BA.debugLine="topBorder.Initialize(\"\")";
_topborder.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=262153;
 //BA.debugLineNum = 262153;BA.debugLine="topBorder.Color = Colors.Black";
_topborder.setColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
RDebugUtils.currentLine=262154;
 //BA.debugLineNum = 262154;BA.debugLine="pnlBackground.AddView(topBorder, 0, pnlNav.Top -";
mostCurrent._pnlbackground.AddView((android.view.View)(_topborder.getObject()),(int) (0),(int) (_pnlnav.getTop()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (3))),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (3)));
RDebugUtils.currentLine=262155;
 //BA.debugLineNum = 262155;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
RDebugUtils.currentModule="main";
RDebugUtils.currentLine=11993088;
 //BA.debugLineNum = 11993088;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
RDebugUtils.currentLine=11993089;
 //BA.debugLineNum = 11993089;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_resume", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_resume", null));}
RDebugUtils.currentLine=11927552;
 //BA.debugLineNum = 11927552;BA.debugLine="Sub Activity_Resume";
RDebugUtils.currentLine=11927553;
 //BA.debugLineNum = 11927553;BA.debugLine="End Sub";
return "";
}
public static String  _createpixelbutton(String _text,int _innercolor,int _left,int _top,int _width,int _height) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "createpixelbutton", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "createpixelbutton", new Object[] {_text,_innercolor,_left,_top,_width,_height}));}
anywheresoftware.b4a.objects.PanelWrapper _pnlborder = null;
anywheresoftware.b4a.objects.PanelWrapper _pnlinner = null;
anywheresoftware.b4a.objects.PanelWrapper _pnlhighlight = null;
anywheresoftware.b4a.objects.LabelWrapper _lbl = null;
RDebugUtils.currentLine=327680;
 //BA.debugLineNum = 327680;BA.debugLine="Sub CreatePixelButton(Text As String, InnerColor A";
RDebugUtils.currentLine=327682;
 //BA.debugLineNum = 327682;BA.debugLine="Dim pnlBorder As Panel";
_pnlborder = new anywheresoftware.b4a.objects.PanelWrapper();
RDebugUtils.currentLine=327683;
 //BA.debugLineNum = 327683;BA.debugLine="pnlBorder.Initialize(\"PixelButton\")";
_pnlborder.Initialize(mostCurrent.activityBA,"PixelButton");
RDebugUtils.currentLine=327684;
 //BA.debugLineNum = 327684;BA.debugLine="pnlBorder.Color = Colors.Black";
_pnlborder.setColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
RDebugUtils.currentLine=327685;
 //BA.debugLineNum = 327685;BA.debugLine="pnlBackground.AddView(pnlBorder, Left, Top, Width";
mostCurrent._pnlbackground.AddView((android.view.View)(_pnlborder.getObject()),_left,_top,_width,_height);
RDebugUtils.currentLine=327688;
 //BA.debugLineNum = 327688;BA.debugLine="Dim pnlInner As Panel";
_pnlinner = new anywheresoftware.b4a.objects.PanelWrapper();
RDebugUtils.currentLine=327689;
 //BA.debugLineNum = 327689;BA.debugLine="pnlInner.Initialize(\"\")";
_pnlinner.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=327690;
 //BA.debugLineNum = 327690;BA.debugLine="pnlInner.Color = InnerColor";
_pnlinner.setColor(_innercolor);
RDebugUtils.currentLine=327691;
 //BA.debugLineNum = 327691;BA.debugLine="pnlBorder.AddView(pnlInner, 3dip, 3dip, Width - 6";
_pnlborder.AddView((android.view.View)(_pnlinner.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (3)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (3)),(int) (_width-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (6))),(int) (_height-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (6))));
RDebugUtils.currentLine=327694;
 //BA.debugLineNum = 327694;BA.debugLine="Dim pnlHighlight As Panel";
_pnlhighlight = new anywheresoftware.b4a.objects.PanelWrapper();
RDebugUtils.currentLine=327695;
 //BA.debugLineNum = 327695;BA.debugLine="pnlHighlight.Initialize(\"\")";
_pnlhighlight.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=327696;
 //BA.debugLineNum = 327696;BA.debugLine="pnlHighlight.Color = Colors.ARGB(80, 255, 255, 25";
_pnlhighlight.setColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (80),(int) (255),(int) (255),(int) (255)));
RDebugUtils.currentLine=327697;
 //BA.debugLineNum = 327697;BA.debugLine="pnlInner.AddView(pnlHighlight, 0, 0, pnlInner.Wid";
_pnlinner.AddView((android.view.View)(_pnlhighlight.getObject()),(int) (0),(int) (0),_pnlinner.getWidth(),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (4)));
RDebugUtils.currentLine=327700;
 //BA.debugLineNum = 327700;BA.debugLine="Dim lbl As Label";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
RDebugUtils.currentLine=327701;
 //BA.debugLineNum = 327701;BA.debugLine="lbl.Initialize(\"\")";
_lbl.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=327702;
 //BA.debugLineNum = 327702;BA.debugLine="lbl.Text = Text";
_lbl.setText(BA.ObjectToCharSequence(_text));
RDebugUtils.currentLine=327703;
 //BA.debugLineNum = 327703;BA.debugLine="lbl.Typeface = Typeface.DEFAULT_BOLD ' Using defa";
_lbl.setTypeface(anywheresoftware.b4a.keywords.Common.Typeface.DEFAULT_BOLD);
RDebugUtils.currentLine=327704;
 //BA.debugLineNum = 327704;BA.debugLine="lbl.TextSize = 14";
_lbl.setTextSize((float) (14));
RDebugUtils.currentLine=327705;
 //BA.debugLineNum = 327705;BA.debugLine="lbl.TextColor = Colors.Black";
_lbl.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
RDebugUtils.currentLine=327706;
 //BA.debugLineNum = 327706;BA.debugLine="lbl.Gravity = Gravity.CENTER";
_lbl.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
RDebugUtils.currentLine=327707;
 //BA.debugLineNum = 327707;BA.debugLine="pnlInner.AddView(lbl, 0, pnlInner.Height - 30dip,";
_pnlinner.AddView((android.view.View)(_lbl.getObject()),(int) (0),(int) (_pnlinner.getHeight()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30))),_pnlinner.getWidth(),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30)));
RDebugUtils.currentLine=327708;
 //BA.debugLineNum = 327708;BA.debugLine="End Sub";
return "";
}
public static String  _pixelbutton_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "pixelbutton_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "pixelbutton_click", null));}
RDebugUtils.currentLine=393216;
 //BA.debugLineNum = 393216;BA.debugLine="Sub PixelButton_Click";
RDebugUtils.currentLine=393217;
 //BA.debugLineNum = 393217;BA.debugLine="Log(\"A pure B4A Pixel Button was clicked!\")";
anywheresoftware.b4a.keywords.Common.LogImpl("0393217","A pure B4A Pixel Button was clicked!",0);
RDebugUtils.currentLine=393218;
 //BA.debugLineNum = 393218;BA.debugLine="End Sub";
return "";
}
}