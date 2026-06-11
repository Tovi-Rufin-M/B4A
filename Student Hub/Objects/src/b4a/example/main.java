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


public static class _rowcol{
public boolean IsInitialized;
public int Row;
public int Col;
public void Initialize() {
IsInitialized = true;
Row = 0;
Col = 0;
}
@Override
		public String toString() {
			return BA.TypeToString(this, false);
		}}

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
public static anywheresoftware.b4a.objects.StringUtils _stringutils1 = null;
public anywheresoftware.b4a.objects.ScrollViewWrapper _sv = null;
public anywheresoftware.b4a.objects.PanelWrapper _table = null;
public anywheresoftware.b4a.objects.collections.List _list1 = null;
public static int _numberofcolumns = 0;
public static int _rowheight = 0;
public static int _columnwidth = 0;
public static int _alignment = 0;
public static int _headercolor = 0;
public static int _fontcolor = 0;
public static int _headerfontcolor = 0;
public static float _fontsize = 0f;
public b4a.example.b4xdrawer _drawer = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnlmain = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnlmenu = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel36 = null;
public b4a.example.starter _starter = null;
public static String  _activity_create(boolean _firsttime) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_create", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_create", new Object[] {_firsttime}));}
RDebugUtils.currentLine=131072;
 //BA.debugLineNum = 131072;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
RDebugUtils.currentLine=131074;
 //BA.debugLineNum = 131074;BA.debugLine="Activity.LoadLayout(\"main\")";
mostCurrent._activity.LoadLayout("main",mostCurrent.activityBA);
RDebugUtils.currentLine=131076;
 //BA.debugLineNum = 131076;BA.debugLine="initdrawer";
_initdrawer();
RDebugUtils.currentLine=131078;
 //BA.debugLineNum = 131078;BA.debugLine="HeaderColor = Colors.Transparent";
_headercolor = anywheresoftware.b4a.keywords.Common.Colors.Transparent;
RDebugUtils.currentLine=131079;
 //BA.debugLineNum = 131079;BA.debugLine="NumberOfColumns = 4 'will be overwritten when loa";
_numberofcolumns = (int) (4);
RDebugUtils.currentLine=131080;
 //BA.debugLineNum = 131080;BA.debugLine="RowHeight = 30dip";
_rowheight = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30));
RDebugUtils.currentLine=131082;
 //BA.debugLineNum = 131082;BA.debugLine="FontColor = Colors.Black";
_fontcolor = anywheresoftware.b4a.keywords.Common.Colors.Black;
RDebugUtils.currentLine=131083;
 //BA.debugLineNum = 131083;BA.debugLine="HeaderFontColor = Colors.Black";
_headerfontcolor = anywheresoftware.b4a.keywords.Common.Colors.Black;
RDebugUtils.currentLine=131084;
 //BA.debugLineNum = 131084;BA.debugLine="FontSize = 14";
_fontsize = (float) (14);
RDebugUtils.currentLine=131085;
 //BA.debugLineNum = 131085;BA.debugLine="Alignment = Gravity.LEFT 'change to Gravity.LEFT";
_alignment = anywheresoftware.b4a.keywords.Common.Gravity.LEFT;
RDebugUtils.currentLine=131086;
 //BA.debugLineNum = 131086;BA.debugLine="End Sub";
return "";
}
public static String  _initdrawer() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "initdrawer", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "initdrawer", null));}
RDebugUtils.currentLine=6356992;
 //BA.debugLineNum = 6356992;BA.debugLine="Sub initdrawer";
RDebugUtils.currentLine=6356993;
 //BA.debugLineNum = 6356993;BA.debugLine="Drawer.Initialize(Me, \"Drawer\", Activity, 260dip)";
mostCurrent._drawer._initialize /*String*/ (null,mostCurrent.activityBA,main.getObject(),"Drawer",(anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._activity.getObject())),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (260)));
RDebugUtils.currentLine=6356994;
 //BA.debugLineNum = 6356994;BA.debugLine="Drawer.CenterPanel.BringToFront";
mostCurrent._drawer._getcenterpanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ (null).BringToFront();
RDebugUtils.currentLine=6356995;
 //BA.debugLineNum = 6356995;BA.debugLine="Drawer.LeftPanel.BringToFront";
mostCurrent._drawer._getleftpanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ (null).BringToFront();
RDebugUtils.currentLine=6356997;
 //BA.debugLineNum = 6356997;BA.debugLine="pnlmain = Drawer.CenterPanel";
mostCurrent._pnlmain = (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(mostCurrent._drawer._getcenterpanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ (null).getObject()));
RDebugUtils.currentLine=6356998;
 //BA.debugLineNum = 6356998;BA.debugLine="pnlmenu = Drawer.LeftPanel";
mostCurrent._pnlmenu = (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(mostCurrent._drawer._getleftpanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ (null).getObject()));
RDebugUtils.currentLine=6357000;
 //BA.debugLineNum = 6357000;BA.debugLine="SetGradient(pnlmenu, Colors.rgb(175, 71, 210), Co";
_setgradient(mostCurrent._pnlmenu,anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (175),(int) (71),(int) (210)),anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (38),(int) (53),(int) (93)));
RDebugUtils.currentLine=6357001;
 //BA.debugLineNum = 6357001;BA.debugLine="CreateMenu";
_createmenu();
RDebugUtils.currentLine=6357002;
 //BA.debugLineNum = 6357002;BA.debugLine="End Sub";
return "";
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
public static String  _addrow(String[] _values) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "addrow", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "addrow", new Object[] {_values}));}
int _lastrow = 0;
int _i = 0;
anywheresoftware.b4a.objects.LabelWrapper _l = null;
b4a.example.main._rowcol _rc = null;
RDebugUtils.currentLine=2359296;
 //BA.debugLineNum = 2359296;BA.debugLine="Sub AddRow(Values() As String)";
RDebugUtils.currentLine=2359297;
 //BA.debugLineNum = 2359297;BA.debugLine="If Values.Length <> NumberOfColumns Then";
if (_values.length!=_numberofcolumns) { 
RDebugUtils.currentLine=2359298;
 //BA.debugLineNum = 2359298;BA.debugLine="Log(\"Wrong number of values.\")";
anywheresoftware.b4a.keywords.Common.LogImpl("42359298","Wrong number of values.",0);
RDebugUtils.currentLine=2359299;
 //BA.debugLineNum = 2359299;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=2359301;
 //BA.debugLineNum = 2359301;BA.debugLine="Dim lastRow As Int";
_lastrow = 0;
RDebugUtils.currentLine=2359302;
 //BA.debugLineNum = 2359302;BA.debugLine="lastRow = NumberOfRows";
_lastrow = _numberofrows();
RDebugUtils.currentLine=2359303;
 //BA.debugLineNum = 2359303;BA.debugLine="For i = 0 To NumberOfColumns - 1";
{
final int step7 = 1;
final int limit7 = (int) (_numberofcolumns-1);
_i = (int) (0) ;
for (;_i <= limit7 ;_i = _i + step7 ) {
RDebugUtils.currentLine=2359304;
 //BA.debugLineNum = 2359304;BA.debugLine="Dim l As Label";
_l = new anywheresoftware.b4a.objects.LabelWrapper();
RDebugUtils.currentLine=2359305;
 //BA.debugLineNum = 2359305;BA.debugLine="l.Initialize(\"cell\")";
_l.Initialize(mostCurrent.activityBA,"cell");
RDebugUtils.currentLine=2359306;
 //BA.debugLineNum = 2359306;BA.debugLine="l.Text = Values(i)";
_l.setText(BA.ObjectToCharSequence(_values[_i]));
RDebugUtils.currentLine=2359307;
 //BA.debugLineNum = 2359307;BA.debugLine="l.Gravity = Alignment";
_l.setGravity(_alignment);
RDebugUtils.currentLine=2359308;
 //BA.debugLineNum = 2359308;BA.debugLine="l.TextSize = FontSize";
_l.setTextSize(_fontsize);
RDebugUtils.currentLine=2359309;
 //BA.debugLineNum = 2359309;BA.debugLine="l.TextColor = FontColor";
_l.setTextColor(_fontcolor);
RDebugUtils.currentLine=2359310;
 //BA.debugLineNum = 2359310;BA.debugLine="l.Padding = Array As Int (10dip, 0, 0, 0)";
_l.setPadding(new int[]{anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (0),(int) (0),(int) (0)});
RDebugUtils.currentLine=2359311;
 //BA.debugLineNum = 2359311;BA.debugLine="Dim rc As RowCol";
_rc = new b4a.example.main._rowcol();
RDebugUtils.currentLine=2359312;
 //BA.debugLineNum = 2359312;BA.debugLine="rc.Initialize";
_rc.Initialize();
RDebugUtils.currentLine=2359313;
 //BA.debugLineNum = 2359313;BA.debugLine="rc.Col = i";
_rc.Col /*int*/  = _i;
RDebugUtils.currentLine=2359314;
 //BA.debugLineNum = 2359314;BA.debugLine="rc.Row = lastRow";
_rc.Row /*int*/  = _lastrow;
RDebugUtils.currentLine=2359315;
 //BA.debugLineNum = 2359315;BA.debugLine="l.Tag = rc";
_l.setTag((Object)(_rc));
RDebugUtils.currentLine=2359316;
 //BA.debugLineNum = 2359316;BA.debugLine="Table.AddView(l, ColumnWidth * i, RowHeight * la";
mostCurrent._table.AddView((android.view.View)(_l.getObject()),(int) (_columnwidth*_i),(int) (_rowheight*_lastrow),_columnwidth,_rowheight);
 }
};
RDebugUtils.currentLine=2359318;
 //BA.debugLineNum = 2359318;BA.debugLine="Table.Height = NumberOfRows * RowHeight";
mostCurrent._table.setHeight((int) (_numberofrows()*_rowheight));
RDebugUtils.currentLine=2359319;
 //BA.debugLineNum = 2359319;BA.debugLine="End Sub";
return "";
}
public static int  _numberofrows() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "numberofrows", false))
	 {return ((Integer) Debug.delegate(mostCurrent.activityBA, "numberofrows", null));}
RDebugUtils.currentLine=2490368;
 //BA.debugLineNum = 2490368;BA.debugLine="Sub NumberOfRows As Int";
RDebugUtils.currentLine=2490369;
 //BA.debugLineNum = 2490369;BA.debugLine="Return Table.NumberOfViews / NumberOfColumns";
if (true) return (int) (mostCurrent._table.getNumberOfViews()/(double)_numberofcolumns);
RDebugUtils.currentLine=2490370;
 //BA.debugLineNum = 2490370;BA.debugLine="End Sub";
return 0;
}
public static String  _btnhome_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btnhome_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btnhome_click", null));}
RDebugUtils.currentLine=6553600;
 //BA.debugLineNum = 6553600;BA.debugLine="Sub btnHome_Click";
RDebugUtils.currentLine=6553601;
 //BA.debugLineNum = 6553601;BA.debugLine="Drawer.LeftOpen = False";
mostCurrent._drawer._setleftopen /*boolean*/ (null,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6553602;
 //BA.debugLineNum = 6553602;BA.debugLine="ShowHome";
_showhome();
RDebugUtils.currentLine=6553603;
 //BA.debugLineNum = 6553603;BA.debugLine="End Sub";
return "";
}
public static String  _showhome() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "showhome", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "showhome", null));}
RDebugUtils.currentLine=6750208;
 //BA.debugLineNum = 6750208;BA.debugLine="Sub ShowHome";
RDebugUtils.currentLine=6750209;
 //BA.debugLineNum = 6750209;BA.debugLine="ShowTable";
_showtable();
RDebugUtils.currentLine=6750210;
 //BA.debugLineNum = 6750210;BA.debugLine="Panel36.Color = 0xFF1AEA00";
mostCurrent._panel36.setColor(((int)0xff1aea00));
RDebugUtils.currentLine=6750211;
 //BA.debugLineNum = 6750211;BA.debugLine="Msgboxasync(\"run\",\"run\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("run"),BA.ObjectToCharSequence("run"),processBA);
RDebugUtils.currentLine=6750212;
 //BA.debugLineNum = 6750212;BA.debugLine="End Sub";
return "";
}
public static String  _btnmenu_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btnmenu_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btnmenu_click", null));}
RDebugUtils.currentLine=6946816;
 //BA.debugLineNum = 6946816;BA.debugLine="Sub btnMenu_Click";
RDebugUtils.currentLine=6946817;
 //BA.debugLineNum = 6946817;BA.debugLine="Drawer.LeftOpen = Not(Drawer.LeftOpen)";
mostCurrent._drawer._setleftopen /*boolean*/ (null,anywheresoftware.b4a.keywords.Common.Not(mostCurrent._drawer._getleftopen /*boolean*/ (null)));
RDebugUtils.currentLine=6946818;
 //BA.debugLineNum = 6946818;BA.debugLine="End Sub";
return "";
}
public static String  _btnpage1_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btnpage1_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btnpage1_click", null));}
RDebugUtils.currentLine=6619136;
 //BA.debugLineNum = 6619136;BA.debugLine="Sub btnPage1_Click";
RDebugUtils.currentLine=6619137;
 //BA.debugLineNum = 6619137;BA.debugLine="Drawer.LeftOpen = False";
mostCurrent._drawer._setleftopen /*boolean*/ (null,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6619138;
 //BA.debugLineNum = 6619138;BA.debugLine="ShowPage1";
_showpage1();
RDebugUtils.currentLine=6619139;
 //BA.debugLineNum = 6619139;BA.debugLine="End Sub";
return "";
}
public static String  _showpage1() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "showpage1", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "showpage1", null));}
RDebugUtils.currentLine=6815744;
 //BA.debugLineNum = 6815744;BA.debugLine="Sub ShowPage1";
RDebugUtils.currentLine=6815745;
 //BA.debugLineNum = 6815745;BA.debugLine="pnlmain.RemoveAllViews";
mostCurrent._pnlmain.RemoveAllViews();
RDebugUtils.currentLine=6815746;
 //BA.debugLineNum = 6815746;BA.debugLine="pnlmain.LoadLayout(\"dashboard\")";
mostCurrent._pnlmain.LoadLayout("dashboard",mostCurrent.activityBA);
RDebugUtils.currentLine=6815747;
 //BA.debugLineNum = 6815747;BA.debugLine="End Sub";
return "";
}
public static String  _btnpage2_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btnpage2_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btnpage2_click", null));}
RDebugUtils.currentLine=6684672;
 //BA.debugLineNum = 6684672;BA.debugLine="Sub btnPage2_Click";
RDebugUtils.currentLine=6684673;
 //BA.debugLineNum = 6684673;BA.debugLine="Drawer.LeftOpen = False";
mostCurrent._drawer._setleftopen /*boolean*/ (null,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6684674;
 //BA.debugLineNum = 6684674;BA.debugLine="ShowPage2";
_showpage2();
RDebugUtils.currentLine=6684675;
 //BA.debugLineNum = 6684675;BA.debugLine="End Sub";
return "";
}
public static String  _showpage2() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "showpage2", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "showpage2", null));}
RDebugUtils.currentLine=6881280;
 //BA.debugLineNum = 6881280;BA.debugLine="Sub ShowPage2";
RDebugUtils.currentLine=6881281;
 //BA.debugLineNum = 6881281;BA.debugLine="pnlmain.RemoveAllViews";
mostCurrent._pnlmain.RemoveAllViews();
RDebugUtils.currentLine=6881282;
 //BA.debugLineNum = 6881282;BA.debugLine="pnlmain.LoadLayout(\"dashboard\")";
mostCurrent._pnlmain.LoadLayout("dashboard",mostCurrent.activityBA);
RDebugUtils.currentLine=6881283;
 //BA.debugLineNum = 6881283;BA.debugLine="End Sub";
return "";
}
public static String  _btntable_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btntable_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btntable_click", null));}
RDebugUtils.currentLine=7208960;
 //BA.debugLineNum = 7208960;BA.debugLine="Sub btnTable_Click";
RDebugUtils.currentLine=7208961;
 //BA.debugLineNum = 7208961;BA.debugLine="Drawer.LeftOpen = False";
mostCurrent._drawer._setleftopen /*boolean*/ (null,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=7208962;
 //BA.debugLineNum = 7208962;BA.debugLine="ShowTable";
_showtable();
RDebugUtils.currentLine=7208963;
 //BA.debugLineNum = 7208963;BA.debugLine="End Sub";
return "";
}
public static String  _showtable() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "showtable", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "showtable", null));}
RDebugUtils.currentLine=196608;
 //BA.debugLineNum = 196608;BA.debugLine="Sub ShowTable";
RDebugUtils.currentLine=196609;
 //BA.debugLineNum = 196609;BA.debugLine="pnlmain.RemoveAllViews";
mostCurrent._pnlmain.RemoveAllViews();
RDebugUtils.currentLine=196610;
 //BA.debugLineNum = 196610;BA.debugLine="pnlmain.LoadLayout(\"profile\") 'layout contains th";
mostCurrent._pnlmain.LoadLayout("profile",mostCurrent.activityBA);
RDebugUtils.currentLine=196611;
 //BA.debugLineNum = 196611;BA.debugLine="Table = SV.Panel";
mostCurrent._table = mostCurrent._sv.getPanel();
RDebugUtils.currentLine=196612;
 //BA.debugLineNum = 196612;BA.debugLine="ColumnWidth = SV.Width / NumberOfColumns";
_columnwidth = (int) (mostCurrent._sv.getWidth()/(double)_numberofcolumns);
RDebugUtils.currentLine=196613;
 //BA.debugLineNum = 196613;BA.debugLine="loadtable(File.DirAssets, \"citylist.csv\")";
_loadtable(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"citylist.csv");
RDebugUtils.currentLine=196614;
 //BA.debugLineNum = 196614;BA.debugLine="End Sub";
return "";
}
public static String  _createmenu() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "createmenu", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "createmenu", null));}
anywheresoftware.b4a.objects.ButtonWrapper _btnhome = null;
anywheresoftware.b4a.objects.ButtonWrapper _btntable = null;
anywheresoftware.b4a.objects.ButtonWrapper _btnpage1 = null;
anywheresoftware.b4a.objects.ButtonWrapper _btnpage2 = null;
anywheresoftware.b4a.objects.ButtonWrapper _b = null;
RDebugUtils.currentLine=6488064;
 //BA.debugLineNum = 6488064;BA.debugLine="Sub CreateMenu";
RDebugUtils.currentLine=6488065;
 //BA.debugLineNum = 6488065;BA.debugLine="Dim btnHome, btnTable, btnPage1, btnPage2 As Butt";
_btnhome = new anywheresoftware.b4a.objects.ButtonWrapper();
_btntable = new anywheresoftware.b4a.objects.ButtonWrapper();
_btnpage1 = new anywheresoftware.b4a.objects.ButtonWrapper();
_btnpage2 = new anywheresoftware.b4a.objects.ButtonWrapper();
RDebugUtils.currentLine=6488066;
 //BA.debugLineNum = 6488066;BA.debugLine="btnHome.Initialize(\"btnHome\")";
_btnhome.Initialize(mostCurrent.activityBA,"btnHome");
RDebugUtils.currentLine=6488067;
 //BA.debugLineNum = 6488067;BA.debugLine="btnHome.Text = \"Home\"";
_btnhome.setText(BA.ObjectToCharSequence("Home"));
RDebugUtils.currentLine=6488068;
 //BA.debugLineNum = 6488068;BA.debugLine="btnTable.Initialize(\"btnTable\")";
_btntable.Initialize(mostCurrent.activityBA,"btnTable");
RDebugUtils.currentLine=6488069;
 //BA.debugLineNum = 6488069;BA.debugLine="btnTable.Text = \"City Table\"";
_btntable.setText(BA.ObjectToCharSequence("City Table"));
RDebugUtils.currentLine=6488070;
 //BA.debugLineNum = 6488070;BA.debugLine="btnPage1.Initialize(\"btnPage1\")";
_btnpage1.Initialize(mostCurrent.activityBA,"btnPage1");
RDebugUtils.currentLine=6488071;
 //BA.debugLineNum = 6488071;BA.debugLine="btnPage1.Text = \"Length Converter\" 'temporary tex";
_btnpage1.setText(BA.ObjectToCharSequence("Length Converter"));
RDebugUtils.currentLine=6488072;
 //BA.debugLineNum = 6488072;BA.debugLine="btnPage2.Initialize(\"btnPage2\")";
_btnpage2.Initialize(mostCurrent.activityBA,"btnPage2");
RDebugUtils.currentLine=6488073;
 //BA.debugLineNum = 6488073;BA.debugLine="btnPage2.Text = \"Weight Converter\" 'temporary tex";
_btnpage2.setText(BA.ObjectToCharSequence("Weight Converter"));
RDebugUtils.currentLine=6488075;
 //BA.debugLineNum = 6488075;BA.debugLine="For Each b As Button In Array(btnHome, btnTable,";
_b = new anywheresoftware.b4a.objects.ButtonWrapper();
{
final Object[] group10 = new Object[]{(Object)(_btnhome.getObject()),(Object)(_btntable.getObject()),(Object)(_btnpage1.getObject()),(Object)(_btnpage2.getObject())};
final int groupLen10 = group10.length
;int index10 = 0;
;
for (; index10 < groupLen10;index10++){
_b = (anywheresoftware.b4a.objects.ButtonWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ButtonWrapper(), (android.widget.Button)(group10[index10]));
RDebugUtils.currentLine=6488076;
 //BA.debugLineNum = 6488076;BA.debugLine="b.TextSize = 16";
_b.setTextSize((float) (16));
RDebugUtils.currentLine=6488077;
 //BA.debugLineNum = 6488077;BA.debugLine="b.Gravity = Gravity.LEFT + Gravity.CENTER_VERTIC";
_b.setGravity((int) (anywheresoftware.b4a.keywords.Common.Gravity.LEFT+anywheresoftware.b4a.keywords.Common.Gravity.CENTER_VERTICAL));
RDebugUtils.currentLine=6488078;
 //BA.debugLineNum = 6488078;BA.debugLine="b.Color = Colors.Transparent";
_b.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
RDebugUtils.currentLine=6488079;
 //BA.debugLineNum = 6488079;BA.debugLine="b.TextColor = Colors.White";
_b.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=6488080;
 //BA.debugLineNum = 6488080;BA.debugLine="pnlmenu.AddView(b, 10dip, 0, 240dip, 50dip)";
mostCurrent._pnlmenu.AddView((android.view.View)(_b.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (240)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
 }
};
RDebugUtils.currentLine=6488082;
 //BA.debugLineNum = 6488082;BA.debugLine="btnHome.Top = 120dip";
_btnhome.setTop(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (120)));
RDebugUtils.currentLine=6488083;
 //BA.debugLineNum = 6488083;BA.debugLine="btnTable.Top = 180dip";
_btntable.setTop(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (180)));
RDebugUtils.currentLine=6488084;
 //BA.debugLineNum = 6488084;BA.debugLine="btnPage1.Top = 240dip";
_btnpage1.setTop(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (240)));
RDebugUtils.currentLine=6488085;
 //BA.debugLineNum = 6488085;BA.debugLine="btnPage2.Top = 300dip";
_btnpage2.setTop(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (300)));
RDebugUtils.currentLine=6488086;
 //BA.debugLineNum = 6488086;BA.debugLine="End Sub";
return "";
}
public static String  _header(String _dir,String _filename) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "header", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "header", new Object[] {_dir,_filename}));}
anywheresoftware.b4a.objects.collections.List _headers = null;
int _i = 0;
anywheresoftware.b4a.objects.LabelWrapper _l = null;
RDebugUtils.currentLine=4325376;
 //BA.debugLineNum = 4325376;BA.debugLine="Sub header(Dir As String, Filename As String)";
RDebugUtils.currentLine=4325377;
 //BA.debugLineNum = 4325377;BA.debugLine="Dim headers As List";
_headers = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=4325378;
 //BA.debugLineNum = 4325378;BA.debugLine="headers.Initialize ' FIX 1: Initialize the lis";
_headers.Initialize();
RDebugUtils.currentLine=4325380;
 //BA.debugLineNum = 4325380;BA.debugLine="List1 = StringUtils1.LoadCSV2(Dir, Filename, \"";
mostCurrent._list1 = _stringutils1.LoadCSV2(_dir,_filename,BA.ObjectToChar(","),_headers);
RDebugUtils.currentLine=4325381;
 //BA.debugLineNum = 4325381;BA.debugLine="NumberOfColumns = headers.Size";
_numberofcolumns = _headers.getSize();
RDebugUtils.currentLine=4325382;
 //BA.debugLineNum = 4325382;BA.debugLine="ColumnWidth = SV.Width / NumberOfColumns ' upd";
_columnwidth = (int) (mostCurrent._sv.getWidth()/(double)_numberofcolumns);
RDebugUtils.currentLine=4325383;
 //BA.debugLineNum = 4325383;BA.debugLine="For i = 0 To NumberOfColumns - 1";
{
final int step6 = 1;
final int limit6 = (int) (_numberofcolumns-1);
_i = (int) (0) ;
for (;_i <= limit6 ;_i = _i + step6 ) {
RDebugUtils.currentLine=4325384;
 //BA.debugLineNum = 4325384;BA.debugLine="Dim l As Label";
_l = new anywheresoftware.b4a.objects.LabelWrapper();
RDebugUtils.currentLine=4325385;
 //BA.debugLineNum = 4325385;BA.debugLine="l.Initialize(\"header\")";
_l.Initialize(mostCurrent.activityBA,"header");
RDebugUtils.currentLine=4325386;
 //BA.debugLineNum = 4325386;BA.debugLine="l.Text = headers.Get(i) ' Read directly fr";
_l.setText(BA.ObjectToCharSequence(_headers.Get(_i)));
RDebugUtils.currentLine=4325387;
 //BA.debugLineNum = 4325387;BA.debugLine="l.Gravity = Gravity.LEFT";
_l.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.LEFT);
RDebugUtils.currentLine=4325388;
 //BA.debugLineNum = 4325388;BA.debugLine="l.TextSize = FontSize";
_l.setTextSize(_fontsize);
RDebugUtils.currentLine=4325389;
 //BA.debugLineNum = 4325389;BA.debugLine="l.Color = HeaderColor";
_l.setColor(_headercolor);
RDebugUtils.currentLine=4325390;
 //BA.debugLineNum = 4325390;BA.debugLine="l.TextColor = HeaderFontColor";
_l.setTextColor(_headerfontcolor);
RDebugUtils.currentLine=4325391;
 //BA.debugLineNum = 4325391;BA.debugLine="l.Padding = Array As Int (10dip, 5dip, 0dip, 5di";
_l.setPadding(new int[]{anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5))});
RDebugUtils.currentLine=4325392;
 //BA.debugLineNum = 4325392;BA.debugLine="l.Tag = i";
_l.setTag((Object)(_i));
RDebugUtils.currentLine=4325393;
 //BA.debugLineNum = 4325393;BA.debugLine="Table.AddView(l, ColumnWidth * i, 0, ColumnWidth";
mostCurrent._table.AddView((android.view.View)(_l.getObject()),(int) (_columnwidth*_i),(int) (0),_columnwidth,_rowheight);
 }
};
RDebugUtils.currentLine=4325395;
 //BA.debugLineNum = 4325395;BA.debugLine="End Sub";
return "";
}
public static String  _setgradient(anywheresoftware.b4a.objects.PanelWrapper _panel,int _color1,int _color2) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "setgradient", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "setgradient", new Object[] {_panel,_color1,_color2}));}
anywheresoftware.b4a.objects.drawable.GradientDrawable _gd = null;
RDebugUtils.currentLine=6422528;
 //BA.debugLineNum = 6422528;BA.debugLine="Sub SetGradient(Panel As Panel, Color1 As Int, Col";
RDebugUtils.currentLine=6422529;
 //BA.debugLineNum = 6422529;BA.debugLine="Dim gd As GradientDrawable";
_gd = new anywheresoftware.b4a.objects.drawable.GradientDrawable();
RDebugUtils.currentLine=6422530;
 //BA.debugLineNum = 6422530;BA.debugLine="gd.Initialize(\"BR_TL\", Array As Int(Color1, Color";
_gd.Initialize(BA.getEnumFromString(android.graphics.drawable.GradientDrawable.Orientation.class,"BR_TL"),new int[]{_color1,_color2});
RDebugUtils.currentLine=6422531;
 //BA.debugLineNum = 6422531;BA.debugLine="Panel.Background = gd";
_panel.setBackground((android.graphics.drawable.Drawable)(_gd.getObject()));
RDebugUtils.currentLine=6422532;
 //BA.debugLineNum = 6422532;BA.debugLine="End Sub";
return "";
}
public static String  _loadtable(String _dir,String _filename) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "loadtable", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "loadtable", new Object[] {_dir,_filename}));}
int _i = 0;
String[] _row = null;
RDebugUtils.currentLine=3342336;
 //BA.debugLineNum = 3342336;BA.debugLine="Sub loadtable(Dir As String, Filename As String)";
RDebugUtils.currentLine=3342337;
 //BA.debugLineNum = 3342337;BA.debugLine="header(Dir, Filename)";
_header(_dir,_filename);
RDebugUtils.currentLine=3342338;
 //BA.debugLineNum = 3342338;BA.debugLine="For i = 0 To List1.Size - 1";
{
final int step2 = 1;
final int limit2 = (int) (mostCurrent._list1.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit2 ;_i = _i + step2 ) {
RDebugUtils.currentLine=3342339;
 //BA.debugLineNum = 3342339;BA.debugLine="Dim row() As String";
_row = new String[(int) (0)];
java.util.Arrays.fill(_row,"");
RDebugUtils.currentLine=3342340;
 //BA.debugLineNum = 3342340;BA.debugLine="row = List1.Get(i)";
_row = (String[])(mostCurrent._list1.Get(_i));
RDebugUtils.currentLine=3342341;
 //BA.debugLineNum = 3342341;BA.debugLine="AddRow(row)";
_addrow(_row);
 }
};
RDebugUtils.currentLine=3342343;
 //BA.debugLineNum = 3342343;BA.debugLine="End Sub";
return "";
}
}