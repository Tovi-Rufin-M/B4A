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
	public static final boolean includeTitle = true;
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
public anywheresoftware.b4a.objects.PanelWrapper _pnltable = null;
public b4a.example.starter _starter = null;
public static String  _activity_create(boolean _firsttime) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_create", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_create", new Object[] {_firsttime}));}
int _numcols = 0;
int _numrows = 0;
int _cellwidth = 0;
int _cellheight = 0;
int _bordersize = 0;
String[][] _tabledata = null;
int _r = 0;
int _c = 0;
anywheresoftware.b4a.objects.PanelWrapper _pnlcell = null;
anywheresoftware.b4a.objects.LabelWrapper _lblcell = null;
RDebugUtils.currentLine=131072;
 //BA.debugLineNum = 131072;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
RDebugUtils.currentLine=131073;
 //BA.debugLineNum = 131073;BA.debugLine="Activity.LoadLayout(\"main\")";
mostCurrent._activity.LoadLayout("main",mostCurrent.activityBA);
RDebugUtils.currentLine=131075;
 //BA.debugLineNum = 131075;BA.debugLine="Dim NumCols As Int = 4";
_numcols = (int) (4);
RDebugUtils.currentLine=131076;
 //BA.debugLineNum = 131076;BA.debugLine="Dim NumRows As Int = 3";
_numrows = (int) (3);
RDebugUtils.currentLine=131077;
 //BA.debugLineNum = 131077;BA.debugLine="Dim CellWidth As Int = Activity.Width / NumCols";
_cellwidth = (int) (mostCurrent._activity.getWidth()/(double)_numcols);
RDebugUtils.currentLine=131078;
 //BA.debugLineNum = 131078;BA.debugLine="Dim CellHeight As Int = 80dip";
_cellheight = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (80));
RDebugUtils.currentLine=131079;
 //BA.debugLineNum = 131079;BA.debugLine="Dim BorderSize As Int = 1dip";
_bordersize = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1));
RDebugUtils.currentLine=131082;
 //BA.debugLineNum = 131082;BA.debugLine="Dim TableData(NumRows, NumCols) As String";
_tabledata = new String[_numrows][];
{
int d0 = _tabledata.length;
int d1 = _numcols;
for (int i0 = 0;i0 < d0;i0++) {
_tabledata[i0] = new String[d1];
java.util.Arrays.fill(_tabledata[i0],"");
}
}
;
RDebugUtils.currentLine=131083;
 //BA.debugLineNum = 131083;BA.debugLine="TableData(0, 0) = \"Column 1\"  : TableData(0, 1) =";
_tabledata[(int) (0)][(int) (0)] = "Column 1";
RDebugUtils.currentLine=131083;
 //BA.debugLineNum = 131083;BA.debugLine="TableData(0, 0) = \"Column 1\"  : TableData(0, 1) =";
_tabledata[(int) (0)][(int) (1)] = "Column 2";
RDebugUtils.currentLine=131083;
 //BA.debugLineNum = 131083;BA.debugLine="TableData(0, 0) = \"Column 1\"  : TableData(0, 1) =";
_tabledata[(int) (0)][(int) (2)] = "Column 3";
RDebugUtils.currentLine=131083;
 //BA.debugLineNum = 131083;BA.debugLine="TableData(0, 0) = \"Column 1\"  : TableData(0, 1) =";
_tabledata[(int) (0)][(int) (3)] = "Column 4";
RDebugUtils.currentLine=131084;
 //BA.debugLineNum = 131084;BA.debugLine="TableData(1, 0) = \"Row 1 C1\"  : TableData(1, 1) =";
_tabledata[(int) (1)][(int) (0)] = "Row 1 C1";
RDebugUtils.currentLine=131084;
 //BA.debugLineNum = 131084;BA.debugLine="TableData(1, 0) = \"Row 1 C1\"  : TableData(1, 1) =";
_tabledata[(int) (1)][(int) (1)] = "Row 1 C2";
RDebugUtils.currentLine=131084;
 //BA.debugLineNum = 131084;BA.debugLine="TableData(1, 0) = \"Row 1 C1\"  : TableData(1, 1) =";
_tabledata[(int) (1)][(int) (2)] = "Row 1 C3";
RDebugUtils.currentLine=131084;
 //BA.debugLineNum = 131084;BA.debugLine="TableData(1, 0) = \"Row 1 C1\"  : TableData(1, 1) =";
_tabledata[(int) (1)][(int) (3)] = "Row 1 C4";
RDebugUtils.currentLine=131085;
 //BA.debugLineNum = 131085;BA.debugLine="TableData(2, 0) = \"Row 2 C1\"  : TableData(2, 1) =";
_tabledata[(int) (2)][(int) (0)] = "Row 2 C1";
RDebugUtils.currentLine=131085;
 //BA.debugLineNum = 131085;BA.debugLine="TableData(2, 0) = \"Row 2 C1\"  : TableData(2, 1) =";
_tabledata[(int) (2)][(int) (1)] = "Row 2 C2";
RDebugUtils.currentLine=131085;
 //BA.debugLineNum = 131085;BA.debugLine="TableData(2, 0) = \"Row 2 C1\"  : TableData(2, 1) =";
_tabledata[(int) (2)][(int) (2)] = "Row 2 C3";
RDebugUtils.currentLine=131085;
 //BA.debugLineNum = 131085;BA.debugLine="TableData(2, 0) = \"Row 2 C1\"  : TableData(2, 1) =";
_tabledata[(int) (2)][(int) (3)] = "Row 2 C4";
RDebugUtils.currentLine=131088;
 //BA.debugLineNum = 131088;BA.debugLine="pnlTable.Initialize(\"pnlTable\")";
mostCurrent._pnltable.Initialize(mostCurrent.activityBA,"pnlTable");
RDebugUtils.currentLine=131089;
 //BA.debugLineNum = 131089;BA.debugLine="pnlTable.Color = Colors.Black ' border color";
mostCurrent._pnltable.setColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
RDebugUtils.currentLine=131090;
 //BA.debugLineNum = 131090;BA.debugLine="Activity.AddView(pnlTable, 0, 50dip, Activity.Wid";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._pnltable.getObject()),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)),mostCurrent._activity.getWidth(),(int) (_numrows*_cellheight+_bordersize));
RDebugUtils.currentLine=131092;
 //BA.debugLineNum = 131092;BA.debugLine="Dim r As Int";
_r = 0;
RDebugUtils.currentLine=131093;
 //BA.debugLineNum = 131093;BA.debugLine="Dim c As Int";
_c = 0;
RDebugUtils.currentLine=131095;
 //BA.debugLineNum = 131095;BA.debugLine="For r = 0 To NumRows - 1";
{
final int step25 = 1;
final int limit25 = (int) (_numrows-1);
_r = (int) (0) ;
for (;_r <= limit25 ;_r = _r + step25 ) {
RDebugUtils.currentLine=131096;
 //BA.debugLineNum = 131096;BA.debugLine="For c = 0 To NumCols - 1";
{
final int step26 = 1;
final int limit26 = (int) (_numcols-1);
_c = (int) (0) ;
for (;_c <= limit26 ;_c = _c + step26 ) {
RDebugUtils.currentLine=131099;
 //BA.debugLineNum = 131099;BA.debugLine="Dim pnlCell As Panel";
_pnlcell = new anywheresoftware.b4a.objects.PanelWrapper();
RDebugUtils.currentLine=131100;
 //BA.debugLineNum = 131100;BA.debugLine="pnlCell.Initialize(\"pnlCell\")";
_pnlcell.Initialize(mostCurrent.activityBA,"pnlCell");
RDebugUtils.currentLine=131101;
 //BA.debugLineNum = 131101;BA.debugLine="pnlCell.Color = Colors.Black";
_pnlcell.setColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
RDebugUtils.currentLine=131102;
 //BA.debugLineNum = 131102;BA.debugLine="pnlTable.AddView(pnlCell, c * CellWidth, r * Ce";
mostCurrent._pnltable.AddView((android.view.View)(_pnlcell.getObject()),(int) (_c*_cellwidth),(int) (_r*_cellheight),_cellwidth,_cellheight);
RDebugUtils.currentLine=131105;
 //BA.debugLineNum = 131105;BA.debugLine="Dim lblCell As Label";
_lblcell = new anywheresoftware.b4a.objects.LabelWrapper();
RDebugUtils.currentLine=131106;
 //BA.debugLineNum = 131106;BA.debugLine="lblCell.Initialize(\"lblCell\")";
_lblcell.Initialize(mostCurrent.activityBA,"lblCell");
RDebugUtils.currentLine=131107;
 //BA.debugLineNum = 131107;BA.debugLine="lblCell.Text = TableData(r, c)";
_lblcell.setText(BA.ObjectToCharSequence(_tabledata[_r][_c]));
RDebugUtils.currentLine=131108;
 //BA.debugLineNum = 131108;BA.debugLine="lblCell.Gravity = Gravity.CENTER";
_lblcell.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
RDebugUtils.currentLine=131109;
 //BA.debugLineNum = 131109;BA.debugLine="lblCell.TextSize = 14";
_lblcell.setTextSize((float) (14));
RDebugUtils.currentLine=131112;
 //BA.debugLineNum = 131112;BA.debugLine="If r = 0 Then";
if (_r==0) { 
RDebugUtils.currentLine=131113;
 //BA.debugLineNum = 131113;BA.debugLine="lblCell.Color = Colors.RGB(63, 81, 181)  ' Blu";
_lblcell.setColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (63),(int) (81),(int) (181)));
RDebugUtils.currentLine=131114;
 //BA.debugLineNum = 131114;BA.debugLine="lblCell.TextColor = Colors.White";
_lblcell.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 }else {
RDebugUtils.currentLine=131116;
 //BA.debugLineNum = 131116;BA.debugLine="If r Mod 2 = 0 Then";
if (_r%2==0) { 
RDebugUtils.currentLine=131117;
 //BA.debugLineNum = 131117;BA.debugLine="lblCell.Color = Colors.RGB(232, 234, 246)  '";
_lblcell.setColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (232),(int) (234),(int) (246)));
 }else {
RDebugUtils.currentLine=131119;
 //BA.debugLineNum = 131119;BA.debugLine="lblCell.Color = Colors.White";
_lblcell.setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 };
RDebugUtils.currentLine=131121;
 //BA.debugLineNum = 131121;BA.debugLine="lblCell.TextColor = Colors.Black";
_lblcell.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 };
RDebugUtils.currentLine=131124;
 //BA.debugLineNum = 131124;BA.debugLine="pnlCell.AddView(lblCell, BorderSize, BorderSize";
_pnlcell.AddView((android.view.View)(_lblcell.getObject()),_bordersize,_bordersize,(int) (_cellwidth-_bordersize*2),(int) (_cellheight-_bordersize*2));
 }
};
 }
};
RDebugUtils.currentLine=131129;
 //BA.debugLineNum = 131129;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
RDebugUtils.currentModule="main";
RDebugUtils.currentLine=262144;
 //BA.debugLineNum = 262144;BA.debugLine="Sub Activity_Pause(UserClosed As Boolean)";
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
}