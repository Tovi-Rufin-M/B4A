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
public b4a.example.b4xdrawer _drawer = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnlmain = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnlmenu = null;
public anywheresoftware.b4a.objects.PanelWrapper _profilebtn = null;
public anywheresoftware.b4a.objects.PanelWrapper _dashbtn = null;
public anywheresoftware.b4a.objects.PanelWrapper _grdbtn = null;
public anywheresoftware.b4a.objects.PanelWrapper _editbtn = null;
public anywheresoftware.b4a.objects.PanelWrapper _datapnl = null;
public anywheresoftware.b4a.objects.PanelWrapper _gradepnl = null;
public anywheresoftware.b4a.objects.ScrollViewWrapper _sv1 = null;
public anywheresoftware.b4a.objects.ScrollViewWrapper _sv = null;
public anywheresoftware.b4a.objects.PanelWrapper _table = null;
public static int _numberofcolumns = 0;
public static int _rowheight = 0;
public static int _columnwidth = 0;
public static int _headercolor = 0;
public static int _fontcolor = 0;
public static int _headerfontcolor = 0;
public static float _fontsize = 0f;
public static int _alignment = 0;
public anywheresoftware.b4a.objects.PanelWrapper _panel1 = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _checkbox1 = null;
public anywheresoftware.b4a.objects.EditTextWrapper _edittext = null;
public anywheresoftware.b4a.objects.EditTextWrapper _edittext1 = null;
public anywheresoftware.b4a.objects.EditTextWrapper _edittext2 = null;
public anywheresoftware.b4a.objects.EditTextWrapper _edittext3 = null;
public anywheresoftware.b4a.objects.collections.List _studentlist = null;
public anywheresoftware.b4a.objects.collections.List _csvheaders = null;
public static int _editingrowindex = 0;
public static int _col_name = 0;
public static int _col_id = 0;
public static int _col_subject = 0;
public static int _col_activity = 0;
public static int _col_attendance = 0;
public static int _col_rate = 0;
public b4a.example.starter _starter = null;
public static String  _activity_create(boolean _firsttime) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_create", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_create", new Object[] {_firsttime}));}
RDebugUtils.currentLine=262144;
 //BA.debugLineNum = 262144;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
RDebugUtils.currentLine=262145;
 //BA.debugLineNum = 262145;BA.debugLine="Activity.LoadLayout(\"main\")";
mostCurrent._activity.LoadLayout("main",mostCurrent.activityBA);
RDebugUtils.currentLine=262147;
 //BA.debugLineNum = 262147;BA.debugLine="initdrawer";
_initdrawer();
RDebugUtils.currentLine=262150;
 //BA.debugLineNum = 262150;BA.debugLine="HeaderColor = Colors.Transparent";
_headercolor = anywheresoftware.b4a.keywords.Common.Colors.Transparent;
RDebugUtils.currentLine=262151;
 //BA.debugLineNum = 262151;BA.debugLine="NumberOfColumns = 6";
_numberofcolumns = (int) (6);
RDebugUtils.currentLine=262152;
 //BA.debugLineNum = 262152;BA.debugLine="RowHeight = 30dip";
_rowheight = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30));
RDebugUtils.currentLine=262153;
 //BA.debugLineNum = 262153;BA.debugLine="FontColor = Colors.Black";
_fontcolor = anywheresoftware.b4a.keywords.Common.Colors.Black;
RDebugUtils.currentLine=262154;
 //BA.debugLineNum = 262154;BA.debugLine="HeaderFontColor = Colors.Black";
_headerfontcolor = anywheresoftware.b4a.keywords.Common.Colors.Black;
RDebugUtils.currentLine=262155;
 //BA.debugLineNum = 262155;BA.debugLine="FontSize = 14";
_fontsize = (float) (14);
RDebugUtils.currentLine=262156;
 //BA.debugLineNum = 262156;BA.debugLine="Alignment = Gravity.LEFT";
_alignment = anywheresoftware.b4a.keywords.Common.Gravity.LEFT;
RDebugUtils.currentLine=262159;
 //BA.debugLineNum = 262159;BA.debugLine="LoadStudentData";
_loadstudentdata();
RDebugUtils.currentLine=262162;
 //BA.debugLineNum = 262162;BA.debugLine="showgrades";
_showgrades();
RDebugUtils.currentLine=262163;
 //BA.debugLineNum = 262163;BA.debugLine="End Sub";
return "";
}
public static String  _initdrawer() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "initdrawer", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "initdrawer", null));}
RDebugUtils.currentLine=393216;
 //BA.debugLineNum = 393216;BA.debugLine="Sub initdrawer";
RDebugUtils.currentLine=393217;
 //BA.debugLineNum = 393217;BA.debugLine="Drawer.Initialize(Me, \"Drawer\", Activity, 260dip)";
mostCurrent._drawer._initialize /*String*/ (null,mostCurrent.activityBA,main.getObject(),"Drawer",(anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._activity.getObject())),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (260)));
RDebugUtils.currentLine=393218;
 //BA.debugLineNum = 393218;BA.debugLine="Drawer.CenterPanel.BringToFront";
mostCurrent._drawer._getcenterpanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ (null).BringToFront();
RDebugUtils.currentLine=393219;
 //BA.debugLineNum = 393219;BA.debugLine="Drawer.LeftPanel.BringToFront";
mostCurrent._drawer._getleftpanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ (null).BringToFront();
RDebugUtils.currentLine=393221;
 //BA.debugLineNum = 393221;BA.debugLine="pnlmain = Drawer.CenterPanel";
mostCurrent._pnlmain = (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(mostCurrent._drawer._getcenterpanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ (null).getObject()));
RDebugUtils.currentLine=393222;
 //BA.debugLineNum = 393222;BA.debugLine="pnlmenu = Drawer.LeftPanel";
mostCurrent._pnlmenu = (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(mostCurrent._drawer._getleftpanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ (null).getObject()));
RDebugUtils.currentLine=393224;
 //BA.debugLineNum = 393224;BA.debugLine="SetGradient(pnlmenu, Colors.rgb(175, 71, 210), Co";
_setgradient(mostCurrent._pnlmenu,anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (175),(int) (71),(int) (210)),anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (38),(int) (53),(int) (93)));
RDebugUtils.currentLine=393225;
 //BA.debugLineNum = 393225;BA.debugLine="CreateMenu";
_createmenu();
RDebugUtils.currentLine=393226;
 //BA.debugLineNum = 393226;BA.debugLine="End Sub";
return "";
}
public static String  _loadstudentdata() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "loadstudentdata", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "loadstudentdata", null));}
RDebugUtils.currentLine=16515072;
 //BA.debugLineNum = 16515072;BA.debugLine="Sub LoadStudentData";
RDebugUtils.currentLine=16515073;
 //BA.debugLineNum = 16515073;BA.debugLine="CSVHeaders.Initialize";
mostCurrent._csvheaders.Initialize();
RDebugUtils.currentLine=16515074;
 //BA.debugLineNum = 16515074;BA.debugLine="StudentList = StringUtils1.LoadCSV2(File.DirInter";
mostCurrent._studentlist = _stringutils1.LoadCSV2(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"citylist.csv",BA.ObjectToChar(","),mostCurrent._csvheaders);
RDebugUtils.currentLine=16515075;
 //BA.debugLineNum = 16515075;BA.debugLine="Log(\"Loaded \" & StudentList.Size & \" student reco";
anywheresoftware.b4a.keywords.Common.LogImpl("516515075","Loaded "+BA.NumberToString(mostCurrent._studentlist.getSize())+" student records",0);
RDebugUtils.currentLine=16515076;
 //BA.debugLineNum = 16515076;BA.debugLine="End Sub";
return "";
}
public static String  _showgrades() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "showgrades", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "showgrades", null));}
anywheresoftware.b4a.objects.collections.List _names = null;
int _studentsize = 0;
RDebugUtils.currentLine=917504;
 //BA.debugLineNum = 917504;BA.debugLine="Sub showgrades";
RDebugUtils.currentLine=917505;
 //BA.debugLineNum = 917505;BA.debugLine="pnlmain.RemoveAllViews";
mostCurrent._pnlmain.RemoveAllViews();
RDebugUtils.currentLine=917506;
 //BA.debugLineNum = 917506;BA.debugLine="pnlmain.LoadLayout(\"grades\")";
mostCurrent._pnlmain.LoadLayout("grades",mostCurrent.activityBA);
RDebugUtils.currentLine=917508;
 //BA.debugLineNum = 917508;BA.debugLine="gradepnl = SV1.Panel";
mostCurrent._gradepnl = mostCurrent._sv1.getPanel();
RDebugUtils.currentLine=917509;
 //BA.debugLineNum = 917509;BA.debugLine="gradepnl.Width = SV1.Width";
mostCurrent._gradepnl.setWidth(mostCurrent._sv1.getWidth());
RDebugUtils.currentLine=917511;
 //BA.debugLineNum = 917511;BA.debugLine="Dim names As List = GetUniqueStudentNames";
_names = new anywheresoftware.b4a.objects.collections.List();
_names = _getuniquestudentnames();
RDebugUtils.currentLine=917512;
 //BA.debugLineNum = 917512;BA.debugLine="Dim studentSize As Int = names.Size";
_studentsize = _names.getSize();
RDebugUtils.currentLine=917514;
 //BA.debugLineNum = 917514;BA.debugLine="gradepnl.Height = (studentSize * 108dip) + 20dip";
mostCurrent._gradepnl.setHeight((int) ((_studentsize*anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (108)))+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))));
RDebugUtils.currentLine=917515;
 //BA.debugLineNum = 917515;BA.debugLine="makeshadow(studentSize, names)";
_makeshadow(_studentsize,_names);
RDebugUtils.currentLine=917516;
 //BA.debugLineNum = 917516;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
RDebugUtils.currentModule="main";
RDebugUtils.currentLine=196608;
 //BA.debugLineNum = 196608;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
RDebugUtils.currentLine=196609;
 //BA.debugLineNum = 196609;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_resume", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_resume", null));}
RDebugUtils.currentLine=131072;
 //BA.debugLineNum = 131072;BA.debugLine="Sub Activity_Resume";
RDebugUtils.currentLine=131073;
 //BA.debugLineNum = 131073;BA.debugLine="End Sub";
return "";
}
public static String  _addtablerow(String[] _values,int _rowindex) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "addtablerow", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "addtablerow", new Object[] {_values,_rowindex}));}
int _i = 0;
anywheresoftware.b4a.objects.LabelWrapper _l = null;
b4a.example.main._rowcol _rc = null;
RDebugUtils.currentLine=17432576;
 //BA.debugLineNum = 17432576;BA.debugLine="Sub AddTableRow(Values() As String, rowIndex As In";
RDebugUtils.currentLine=17432577;
 //BA.debugLineNum = 17432577;BA.debugLine="If Values.Length <> NumberOfColumns Then";
if (_values.length!=_numberofcolumns) { 
RDebugUtils.currentLine=17432578;
 //BA.debugLineNum = 17432578;BA.debugLine="Log(\"Wrong number of values.\")";
anywheresoftware.b4a.keywords.Common.LogImpl("517432578","Wrong number of values.",0);
RDebugUtils.currentLine=17432579;
 //BA.debugLineNum = 17432579;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=17432581;
 //BA.debugLineNum = 17432581;BA.debugLine="For i = 0 To NumberOfColumns - 1";
{
final int step5 = 1;
final int limit5 = (int) (_numberofcolumns-1);
_i = (int) (0) ;
for (;_i <= limit5 ;_i = _i + step5 ) {
RDebugUtils.currentLine=17432582;
 //BA.debugLineNum = 17432582;BA.debugLine="Dim l As Label";
_l = new anywheresoftware.b4a.objects.LabelWrapper();
RDebugUtils.currentLine=17432583;
 //BA.debugLineNum = 17432583;BA.debugLine="l.Initialize(\"cell\")";
_l.Initialize(mostCurrent.activityBA,"cell");
RDebugUtils.currentLine=17432584;
 //BA.debugLineNum = 17432584;BA.debugLine="l.Text = Values(i)";
_l.setText(BA.ObjectToCharSequence(_values[_i]));
RDebugUtils.currentLine=17432585;
 //BA.debugLineNum = 17432585;BA.debugLine="l.Gravity = Alignment";
_l.setGravity(_alignment);
RDebugUtils.currentLine=17432586;
 //BA.debugLineNum = 17432586;BA.debugLine="l.TextSize = FontSize";
_l.setTextSize(_fontsize);
RDebugUtils.currentLine=17432587;
 //BA.debugLineNum = 17432587;BA.debugLine="l.TextColor = FontColor";
_l.setTextColor(_fontcolor);
RDebugUtils.currentLine=17432588;
 //BA.debugLineNum = 17432588;BA.debugLine="l.Padding = Array As Int(10dip, 0, 0, 0)";
_l.setPadding(new int[]{anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (0),(int) (0),(int) (0)});
RDebugUtils.currentLine=17432589;
 //BA.debugLineNum = 17432589;BA.debugLine="Dim rc As RowCol";
_rc = new b4a.example.main._rowcol();
RDebugUtils.currentLine=17432590;
 //BA.debugLineNum = 17432590;BA.debugLine="rc.Initialize";
_rc.Initialize();
RDebugUtils.currentLine=17432591;
 //BA.debugLineNum = 17432591;BA.debugLine="rc.Col = i";
_rc.Col /*int*/  = _i;
RDebugUtils.currentLine=17432592;
 //BA.debugLineNum = 17432592;BA.debugLine="rc.Row = rowIndex";
_rc.Row /*int*/  = _rowindex;
RDebugUtils.currentLine=17432593;
 //BA.debugLineNum = 17432593;BA.debugLine="l.Tag = rc";
_l.setTag((Object)(_rc));
RDebugUtils.currentLine=17432594;
 //BA.debugLineNum = 17432594;BA.debugLine="Table.AddView(l, ColumnWidth * i, RowHeight * ro";
mostCurrent._table.AddView((android.view.View)(_l.getObject()),(int) (_columnwidth*_i),(int) (_rowheight*_rowindex),_columnwidth,_rowheight);
 }
};
RDebugUtils.currentLine=17432596;
 //BA.debugLineNum = 17432596;BA.debugLine="End Sub";
return "";
}
public static String  _btnaddnew_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btnaddnew_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btnaddnew_click", null));}
RDebugUtils.currentLine=17104896;
 //BA.debugLineNum = 17104896;BA.debugLine="Sub btnAddNew_Click";
RDebugUtils.currentLine=17104897;
 //BA.debugLineNum = 17104897;BA.debugLine="EditingRowIndex = -1";
_editingrowindex = (int) (-1);
RDebugUtils.currentLine=17104898;
 //BA.debugLineNum = 17104898;BA.debugLine="ShowCREATForm(\"\", \"\", \"\", \"\", False)";
_showcreatform("","","","",anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=17104899;
 //BA.debugLineNum = 17104899;BA.debugLine="End Sub";
return "";
}
public static String  _showcreatform(String _name,String _id,String _activitytitle,String _rate,boolean _attendance) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "showcreatform", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "showcreatform", new Object[] {_name,_id,_activitytitle,_rate,_attendance}));}
anywheresoftware.b4a.objects.ButtonWrapper _btnsave = null;
RDebugUtils.currentLine=17301504;
 //BA.debugLineNum = 17301504;BA.debugLine="Sub ShowCREATForm(name As String, id As String, ac";
RDebugUtils.currentLine=17301505;
 //BA.debugLineNum = 17301505;BA.debugLine="pnlmain.RemoveAllViews";
mostCurrent._pnlmain.RemoveAllViews();
RDebugUtils.currentLine=17301506;
 //BA.debugLineNum = 17301506;BA.debugLine="pnlmain.LoadLayout(\"CREAT\")";
mostCurrent._pnlmain.LoadLayout("CREAT",mostCurrent.activityBA);
RDebugUtils.currentLine=17301509;
 //BA.debugLineNum = 17301509;BA.debugLine="EditText.Text = name         ' Student Name";
mostCurrent._edittext.setText(BA.ObjectToCharSequence(_name));
RDebugUtils.currentLine=17301510;
 //BA.debugLineNum = 17301510;BA.debugLine="EditText3.Text = id          ' Student ID";
mostCurrent._edittext3.setText(BA.ObjectToCharSequence(_id));
RDebugUtils.currentLine=17301511;
 //BA.debugLineNum = 17301511;BA.debugLine="EditText1.Text = activityTitle    ' Activity Titl";
mostCurrent._edittext1.setText(BA.ObjectToCharSequence(_activitytitle));
RDebugUtils.currentLine=17301512;
 //BA.debugLineNum = 17301512;BA.debugLine="EditText2.Text = rate        ' Result";
mostCurrent._edittext2.setText(BA.ObjectToCharSequence(_rate));
RDebugUtils.currentLine=17301513;
 //BA.debugLineNum = 17301513;BA.debugLine="CheckBox1.Checked = attendance";
mostCurrent._checkbox1.setChecked(_attendance);
RDebugUtils.currentLine=17301516;
 //BA.debugLineNum = 17301516;BA.debugLine="Dim btnSave As Button";
_btnsave = new anywheresoftware.b4a.objects.ButtonWrapper();
RDebugUtils.currentLine=17301517;
 //BA.debugLineNum = 17301517;BA.debugLine="btnSave.Initialize(\"btnSave\")";
_btnsave.Initialize(mostCurrent.activityBA,"btnSave");
RDebugUtils.currentLine=17301518;
 //BA.debugLineNum = 17301518;BA.debugLine="If EditingRowIndex >= 0 Then";
if (_editingrowindex>=0) { 
RDebugUtils.currentLine=17301519;
 //BA.debugLineNum = 17301519;BA.debugLine="btnSave.Text = \"UPDATE RECORD\"";
_btnsave.setText(BA.ObjectToCharSequence("UPDATE RECORD"));
 }else {
RDebugUtils.currentLine=17301521;
 //BA.debugLineNum = 17301521;BA.debugLine="btnSave.Text = \"SAVE RECORD\"";
_btnsave.setText(BA.ObjectToCharSequence("SAVE RECORD"));
 };
RDebugUtils.currentLine=17301523;
 //BA.debugLineNum = 17301523;BA.debugLine="btnSave.TextSize = 16";
_btnsave.setTextSize((float) (16));
RDebugUtils.currentLine=17301524;
 //BA.debugLineNum = 17301524;BA.debugLine="btnSave.TextColor = Colors.Black";
_btnsave.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
RDebugUtils.currentLine=17301525;
 //BA.debugLineNum = 17301525;BA.debugLine="btnSave.Color = 0xFFFFD400";
_btnsave.setColor(((int)0xffffd400));
RDebugUtils.currentLine=17301526;
 //BA.debugLineNum = 17301526;BA.debugLine="btnSave.Typeface = Typeface.DEFAULT_BOLD";
_btnsave.setTypeface(anywheresoftware.b4a.keywords.Common.Typeface.DEFAULT_BOLD);
RDebugUtils.currentLine=17301529;
 //BA.debugLineNum = 17301529;BA.debugLine="pnlmain.AddView(btnSave, 20dip, pnlmain.Height -";
mostCurrent._pnlmain.AddView((android.view.View)(_btnsave.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20)),(int) (mostCurrent._pnlmain.getHeight()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (120))),(int) (mostCurrent._pnlmain.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
RDebugUtils.currentLine=17301530;
 //BA.debugLineNum = 17301530;BA.debugLine="End Sub";
return "";
}
public static String  _btndashboard_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btndashboard_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btndashboard_click", null));}
RDebugUtils.currentLine=524288;
 //BA.debugLineNum = 524288;BA.debugLine="Sub btnDashBoard_Click";
RDebugUtils.currentLine=524289;
 //BA.debugLineNum = 524289;BA.debugLine="Drawer.LeftOpen = False";
mostCurrent._drawer._setleftopen /*boolean*/ (null,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=524290;
 //BA.debugLineNum = 524290;BA.debugLine="showdashboard";
_showdashboard();
RDebugUtils.currentLine=524291;
 //BA.debugLineNum = 524291;BA.debugLine="End Sub";
return "";
}
public static String  _showdashboard() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "showdashboard", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "showdashboard", null));}
RDebugUtils.currentLine=786432;
 //BA.debugLineNum = 786432;BA.debugLine="Sub showdashboard";
RDebugUtils.currentLine=786433;
 //BA.debugLineNum = 786433;BA.debugLine="pnlmain.RemoveAllViews";
mostCurrent._pnlmain.RemoveAllViews();
RDebugUtils.currentLine=786434;
 //BA.debugLineNum = 786434;BA.debugLine="pnlmain.LoadLayout(\"dashboard\")";
mostCurrent._pnlmain.LoadLayout("dashboard",mostCurrent.activityBA);
RDebugUtils.currentLine=786435;
 //BA.debugLineNum = 786435;BA.debugLine="generategraph";
_generategraph();
RDebugUtils.currentLine=786436;
 //BA.debugLineNum = 786436;BA.debugLine="End Sub";
return "";
}
public static String  _btndeleterow_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btndeleterow_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btndeleterow_click", null));}
anywheresoftware.b4a.objects.ButtonWrapper _btn = null;
int _deleteindex = 0;
String[] _row = null;
String _msg = "";
int _result = 0;
RDebugUtils.currentLine=17235968;
 //BA.debugLineNum = 17235968;BA.debugLine="Sub btnDeleteRow_Click";
RDebugUtils.currentLine=17235969;
 //BA.debugLineNum = 17235969;BA.debugLine="Dim btn As Button = Sender";
_btn = new anywheresoftware.b4a.objects.ButtonWrapper();
_btn = (anywheresoftware.b4a.objects.ButtonWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ButtonWrapper(), (android.widget.Button)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
RDebugUtils.currentLine=17235970;
 //BA.debugLineNum = 17235970;BA.debugLine="Dim deleteIndex As Int = btn.Tag";
_deleteindex = (int)(BA.ObjectToNumber(_btn.getTag()));
RDebugUtils.currentLine=17235971;
 //BA.debugLineNum = 17235971;BA.debugLine="Dim row() As String = StudentList.Get(deleteIndex";
_row = (String[])(mostCurrent._studentlist.Get(_deleteindex));
RDebugUtils.currentLine=17235972;
 //BA.debugLineNum = 17235972;BA.debugLine="Dim msg As String = \"Delete record for \" & row(CO";
_msg = "Delete record for "+_row[_col_name]+" ("+_row[_col_subject]+")?";
RDebugUtils.currentLine=17235974;
 //BA.debugLineNum = 17235974;BA.debugLine="Dim result As Int = Msgbox2(msg, \"Confirm Delete\"";
_result = anywheresoftware.b4a.keywords.Common.Msgbox2(BA.ObjectToCharSequence(_msg),BA.ObjectToCharSequence("Confirm Delete"),"Delete","","Cancel",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null),mostCurrent.activityBA);
RDebugUtils.currentLine=17235975;
 //BA.debugLineNum = 17235975;BA.debugLine="If result = DialogResponse.POSITIVE Then";
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
RDebugUtils.currentLine=17235976;
 //BA.debugLineNum = 17235976;BA.debugLine="StudentList.RemoveAt(deleteIndex)";
mostCurrent._studentlist.RemoveAt(_deleteindex);
RDebugUtils.currentLine=17235977;
 //BA.debugLineNum = 17235977;BA.debugLine="SaveStudentData";
_savestudentdata();
RDebugUtils.currentLine=17235978;
 //BA.debugLineNum = 17235978;BA.debugLine="showedit ' refresh list";
_showedit();
 };
RDebugUtils.currentLine=17235980;
 //BA.debugLineNum = 17235980;BA.debugLine="End Sub";
return "";
}
public static String  _savestudentdata() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "savestudentdata", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "savestudentdata", null));}
RDebugUtils.currentLine=16580608;
 //BA.debugLineNum = 16580608;BA.debugLine="Sub SaveStudentData";
RDebugUtils.currentLine=16580609;
 //BA.debugLineNum = 16580609;BA.debugLine="StringUtils1.SaveCSV2(File.DirInternal, \"citylist";
_stringutils1.SaveCSV2(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"citylist.csv",BA.ObjectToChar(","),mostCurrent._studentlist,mostCurrent._csvheaders);
RDebugUtils.currentLine=16580610;
 //BA.debugLineNum = 16580610;BA.debugLine="Log(\"Saved \" & StudentList.Size & \" student recor";
anywheresoftware.b4a.keywords.Common.LogImpl("516580610","Saved "+BA.NumberToString(mostCurrent._studentlist.getSize())+" student records",0);
RDebugUtils.currentLine=16580611;
 //BA.debugLineNum = 16580611;BA.debugLine="End Sub";
return "";
}
public static String  _showedit() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "showedit", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "showedit", null));}
RDebugUtils.currentLine=3342336;
 //BA.debugLineNum = 3342336;BA.debugLine="Sub showedit";
RDebugUtils.currentLine=3342337;
 //BA.debugLineNum = 3342337;BA.debugLine="pnlmain.RemoveAllViews";
mostCurrent._pnlmain.RemoveAllViews();
RDebugUtils.currentLine=3342338;
 //BA.debugLineNum = 3342338;BA.debugLine="pnlmain.LoadLayout(\"edit\")";
mostCurrent._pnlmain.LoadLayout("edit",mostCurrent.activityBA);
RDebugUtils.currentLine=3342339;
 //BA.debugLineNum = 3342339;BA.debugLine="BuildEditList";
_buildeditlist();
RDebugUtils.currentLine=3342340;
 //BA.debugLineNum = 3342340;BA.debugLine="End Sub";
return "";
}
public static String  _btnedit_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btnedit_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btnedit_click", null));}
RDebugUtils.currentLine=3604480;
 //BA.debugLineNum = 3604480;BA.debugLine="Sub btnedit_Click";
RDebugUtils.currentLine=3604481;
 //BA.debugLineNum = 3604481;BA.debugLine="Drawer.LeftOpen = False";
mostCurrent._drawer._setleftopen /*boolean*/ (null,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3604482;
 //BA.debugLineNum = 3604482;BA.debugLine="showedit";
_showedit();
RDebugUtils.currentLine=3604483;
 //BA.debugLineNum = 3604483;BA.debugLine="End Sub";
return "";
}
public static String  _btneditrow_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btneditrow_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btneditrow_click", null));}
anywheresoftware.b4a.objects.ButtonWrapper _btn = null;
String[] _row = null;
boolean _ispresent = false;
RDebugUtils.currentLine=17170432;
 //BA.debugLineNum = 17170432;BA.debugLine="Sub btnEditRow_Click";
RDebugUtils.currentLine=17170433;
 //BA.debugLineNum = 17170433;BA.debugLine="Dim btn As Button = Sender";
_btn = new anywheresoftware.b4a.objects.ButtonWrapper();
_btn = (anywheresoftware.b4a.objects.ButtonWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ButtonWrapper(), (android.widget.Button)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
RDebugUtils.currentLine=17170434;
 //BA.debugLineNum = 17170434;BA.debugLine="EditingRowIndex = btn.Tag";
_editingrowindex = (int)(BA.ObjectToNumber(_btn.getTag()));
RDebugUtils.currentLine=17170435;
 //BA.debugLineNum = 17170435;BA.debugLine="Dim row() As String = StudentList.Get(EditingRowI";
_row = (String[])(mostCurrent._studentlist.Get(_editingrowindex));
RDebugUtils.currentLine=17170436;
 //BA.debugLineNum = 17170436;BA.debugLine="Dim isPresent As Boolean = (row(COL_ATTENDANCE).T";
_ispresent = ((_row[_col_attendance].toLowerCase(anywheresoftware.b4a.keywords.Common.stringLocale)).equals("present"));
RDebugUtils.currentLine=17170437;
 //BA.debugLineNum = 17170437;BA.debugLine="ShowCREATForm(row(COL_NAME), row(COL_ID), row(COL";
_showcreatform(_row[_col_name],_row[_col_id],_row[_col_activity],_row[_col_rate],_ispresent);
RDebugUtils.currentLine=17170438;
 //BA.debugLineNum = 17170438;BA.debugLine="End Sub";
return "";
}
public static String  _btngrades_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btngrades_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btngrades_click", null));}
RDebugUtils.currentLine=589824;
 //BA.debugLineNum = 589824;BA.debugLine="Sub btnGrades_Click";
RDebugUtils.currentLine=589825;
 //BA.debugLineNum = 589825;BA.debugLine="Drawer.LeftOpen = False";
mostCurrent._drawer._setleftopen /*boolean*/ (null,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=589826;
 //BA.debugLineNum = 589826;BA.debugLine="showgrades";
_showgrades();
RDebugUtils.currentLine=589827;
 //BA.debugLineNum = 589827;BA.debugLine="End Sub";
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
public static String  _btnprofile_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btnprofile_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btnprofile_click", null));}
RDebugUtils.currentLine=720896;
 //BA.debugLineNum = 720896;BA.debugLine="Sub btnProfile_Click";
RDebugUtils.currentLine=720897;
 //BA.debugLineNum = 720897;BA.debugLine="Drawer.LeftOpen = False";
mostCurrent._drawer._setleftopen /*boolean*/ (null,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=720898;
 //BA.debugLineNum = 720898;BA.debugLine="showprofile";
_showprofile();
RDebugUtils.currentLine=720899;
 //BA.debugLineNum = 720899;BA.debugLine="End Sub";
return "";
}
public static String  _showprofile() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "showprofile", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "showprofile", null));}
RDebugUtils.currentLine=1179648;
 //BA.debugLineNum = 1179648;BA.debugLine="Sub showprofile";
RDebugUtils.currentLine=1179649;
 //BA.debugLineNum = 1179649;BA.debugLine="HighlightTab(profilebtn)";
_highlighttab(mostCurrent._profilebtn);
RDebugUtils.currentLine=1179650;
 //BA.debugLineNum = 1179650;BA.debugLine="ShowTable";
_showtable();
RDebugUtils.currentLine=1179651;
 //BA.debugLineNum = 1179651;BA.debugLine="End Sub";
return "";
}
public static String  _btnsave_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btnsave_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btnsave_click", null));}
String _sname = "";
String _sid = "";
String _sactivity = "";
String _srate = "";
String _sattendance = "";
String _ssubject = "";
String[] _existingrow = null;
String[] _newrow = null;
RDebugUtils.currentLine=17367040;
 //BA.debugLineNum = 17367040;BA.debugLine="Sub btnSave_Click";
RDebugUtils.currentLine=17367042;
 //BA.debugLineNum = 17367042;BA.debugLine="Dim sName As String = EditText.Text.Trim";
_sname = mostCurrent._edittext.getText().trim();
RDebugUtils.currentLine=17367043;
 //BA.debugLineNum = 17367043;BA.debugLine="Dim sID As String = EditText3.Text.Trim";
_sid = mostCurrent._edittext3.getText().trim();
RDebugUtils.currentLine=17367044;
 //BA.debugLineNum = 17367044;BA.debugLine="Dim sActivity As String = EditText1.Text.Trim";
_sactivity = mostCurrent._edittext1.getText().trim();
RDebugUtils.currentLine=17367045;
 //BA.debugLineNum = 17367045;BA.debugLine="Dim sRate As String = EditText2.Text.Trim";
_srate = mostCurrent._edittext2.getText().trim();
RDebugUtils.currentLine=17367047;
 //BA.debugLineNum = 17367047;BA.debugLine="If sName.Length = 0 Then";
if (_sname.length()==0) { 
RDebugUtils.currentLine=17367048;
 //BA.debugLineNum = 17367048;BA.debugLine="Msgbox(\"Please enter a Student Name.\", \"Validati";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Please enter a Student Name."),BA.ObjectToCharSequence("Validation Error"),mostCurrent.activityBA);
RDebugUtils.currentLine=17367049;
 //BA.debugLineNum = 17367049;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=17367051;
 //BA.debugLineNum = 17367051;BA.debugLine="If sID.Length = 0 Then";
if (_sid.length()==0) { 
RDebugUtils.currentLine=17367052;
 //BA.debugLineNum = 17367052;BA.debugLine="Msgbox(\"Please enter a Student ID.\", \"Validation";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Please enter a Student ID."),BA.ObjectToCharSequence("Validation Error"),mostCurrent.activityBA);
RDebugUtils.currentLine=17367053;
 //BA.debugLineNum = 17367053;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=17367055;
 //BA.debugLineNum = 17367055;BA.debugLine="If sActivity.Length = 0 Then";
if (_sactivity.length()==0) { 
RDebugUtils.currentLine=17367056;
 //BA.debugLineNum = 17367056;BA.debugLine="Msgbox(\"Please enter an Activity Title.\", \"Valid";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Please enter an Activity Title."),BA.ObjectToCharSequence("Validation Error"),mostCurrent.activityBA);
RDebugUtils.currentLine=17367057;
 //BA.debugLineNum = 17367057;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=17367059;
 //BA.debugLineNum = 17367059;BA.debugLine="If sRate.Length = 0 Or sRate.Contains(\"/\") = Fals";
if (_srate.length()==0 || _srate.contains("/")==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=17367060;
 //BA.debugLineNum = 17367060;BA.debugLine="Msgbox(\"Please enter a valid Result (e.g. 11/20)";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Please enter a valid Result (e.g. 11/20)."),BA.ObjectToCharSequence("Validation Error"),mostCurrent.activityBA);
RDebugUtils.currentLine=17367061;
 //BA.debugLineNum = 17367061;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=17367065;
 //BA.debugLineNum = 17367065;BA.debugLine="Dim sAttendance As String";
_sattendance = "";
RDebugUtils.currentLine=17367066;
 //BA.debugLineNum = 17367066;BA.debugLine="If CheckBox1.Checked Then";
if (mostCurrent._checkbox1.getChecked()) { 
RDebugUtils.currentLine=17367067;
 //BA.debugLineNum = 17367067;BA.debugLine="sAttendance = \"present\"";
_sattendance = "present";
 }else {
RDebugUtils.currentLine=17367069;
 //BA.debugLineNum = 17367069;BA.debugLine="sAttendance = \"absent\"";
_sattendance = "absent";
 };
RDebugUtils.currentLine=17367073;
 //BA.debugLineNum = 17367073;BA.debugLine="Dim sSubject As String";
_ssubject = "";
RDebugUtils.currentLine=17367074;
 //BA.debugLineNum = 17367074;BA.debugLine="If EditingRowIndex >= 0 Then";
if (_editingrowindex>=0) { 
RDebugUtils.currentLine=17367075;
 //BA.debugLineNum = 17367075;BA.debugLine="Dim existingRow() As String = StudentList.Get(Ed";
_existingrow = (String[])(mostCurrent._studentlist.Get(_editingrowindex));
RDebugUtils.currentLine=17367076;
 //BA.debugLineNum = 17367076;BA.debugLine="sSubject = existingRow(COL_SUBJECT)";
_ssubject = _existingrow[_col_subject];
 }else {
RDebugUtils.currentLine=17367079;
 //BA.debugLineNum = 17367079;BA.debugLine="sSubject = \"general\"";
_ssubject = "general";
 };
RDebugUtils.currentLine=17367083;
 //BA.debugLineNum = 17367083;BA.debugLine="Dim newRow(6) As String";
_newrow = new String[(int) (6)];
java.util.Arrays.fill(_newrow,"");
RDebugUtils.currentLine=17367084;
 //BA.debugLineNum = 17367084;BA.debugLine="newRow(COL_NAME) = sName";
_newrow[_col_name] = _sname;
RDebugUtils.currentLine=17367085;
 //BA.debugLineNum = 17367085;BA.debugLine="newRow(COL_ID) = sID";
_newrow[_col_id] = _sid;
RDebugUtils.currentLine=17367086;
 //BA.debugLineNum = 17367086;BA.debugLine="newRow(COL_SUBJECT) = sSubject";
_newrow[_col_subject] = _ssubject;
RDebugUtils.currentLine=17367087;
 //BA.debugLineNum = 17367087;BA.debugLine="newRow(COL_ACTIVITY) = sActivity";
_newrow[_col_activity] = _sactivity;
RDebugUtils.currentLine=17367088;
 //BA.debugLineNum = 17367088;BA.debugLine="newRow(COL_ATTENDANCE) = sAttendance";
_newrow[_col_attendance] = _sattendance;
RDebugUtils.currentLine=17367089;
 //BA.debugLineNum = 17367089;BA.debugLine="newRow(COL_RATE) = sRate";
_newrow[_col_rate] = _srate;
RDebugUtils.currentLine=17367091;
 //BA.debugLineNum = 17367091;BA.debugLine="If EditingRowIndex >= 0 Then";
if (_editingrowindex>=0) { 
RDebugUtils.currentLine=17367093;
 //BA.debugLineNum = 17367093;BA.debugLine="StudentList.Set(EditingRowIndex, newRow)";
mostCurrent._studentlist.Set(_editingrowindex,(Object)(_newrow));
RDebugUtils.currentLine=17367094;
 //BA.debugLineNum = 17367094;BA.debugLine="Log(\"Updated row at index \" & EditingRowIndex)";
anywheresoftware.b4a.keywords.Common.LogImpl("517367094","Updated row at index "+BA.NumberToString(_editingrowindex),0);
 }else {
RDebugUtils.currentLine=17367097;
 //BA.debugLineNum = 17367097;BA.debugLine="StudentList.Add(newRow)";
mostCurrent._studentlist.Add((Object)(_newrow));
RDebugUtils.currentLine=17367098;
 //BA.debugLineNum = 17367098;BA.debugLine="Log(\"Added new row, total: \" & StudentList.Size)";
anywheresoftware.b4a.keywords.Common.LogImpl("517367098","Added new row, total: "+BA.NumberToString(mostCurrent._studentlist.getSize()),0);
 };
RDebugUtils.currentLine=17367102;
 //BA.debugLineNum = 17367102;BA.debugLine="SaveStudentData";
_savestudentdata();
RDebugUtils.currentLine=17367103;
 //BA.debugLineNum = 17367103;BA.debugLine="EditingRowIndex = -1";
_editingrowindex = (int) (-1);
RDebugUtils.currentLine=17367104;
 //BA.debugLineNum = 17367104;BA.debugLine="showedit";
_showedit();
RDebugUtils.currentLine=17367105;
 //BA.debugLineNum = 17367105;BA.debugLine="End Sub";
return "";
}
public static String  _buildeditlist() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "buildeditlist", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "buildeditlist", null));}
anywheresoftware.b4a.objects.ScrollViewWrapper _sv2 = null;
anywheresoftware.b4a.objects.PanelWrapper _listpanel = null;
int _rowh = 0;
int _spacing = 0;
int _currenty = 0;
anywheresoftware.b4a.objects.ButtonWrapper _btnadd = null;
int _i = 0;
String[] _row = null;
anywheresoftware.b4a.objects.PanelWrapper _pnlrow = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _cd = null;
anywheresoftware.b4a.objects.LabelWrapper _lblinfo = null;
anywheresoftware.b4a.objects.LabelWrapper _lbldetail = null;
anywheresoftware.b4a.objects.ButtonWrapper _btnedit = null;
anywheresoftware.b4a.objects.ButtonWrapper _btndel = null;
RDebugUtils.currentLine=17039360;
 //BA.debugLineNum = 17039360;BA.debugLine="Sub BuildEditList";
RDebugUtils.currentLine=17039364;
 //BA.debugLineNum = 17039364;BA.debugLine="Dim sv2 As ScrollView";
_sv2 = new anywheresoftware.b4a.objects.ScrollViewWrapper();
RDebugUtils.currentLine=17039365;
 //BA.debugLineNum = 17039365;BA.debugLine="sv2.Initialize(0)";
_sv2.Initialize(mostCurrent.activityBA,(int) (0));
RDebugUtils.currentLine=17039366;
 //BA.debugLineNum = 17039366;BA.debugLine="pnlmain.AddView(sv2, 0, 50dip, pnlmain.Width, pnl";
mostCurrent._pnlmain.AddView((android.view.View)(_sv2.getObject()),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)),mostCurrent._pnlmain.getWidth(),(int) (mostCurrent._pnlmain.getHeight()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (110))));
RDebugUtils.currentLine=17039368;
 //BA.debugLineNum = 17039368;BA.debugLine="Dim listPanel As Panel = sv2.Panel";
_listpanel = new anywheresoftware.b4a.objects.PanelWrapper();
_listpanel = _sv2.getPanel();
RDebugUtils.currentLine=17039369;
 //BA.debugLineNum = 17039369;BA.debugLine="listPanel.Width = sv2.Width";
_listpanel.setWidth(_sv2.getWidth());
RDebugUtils.currentLine=17039371;
 //BA.debugLineNum = 17039371;BA.debugLine="Dim rowH As Int = 80dip";
_rowh = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (80));
RDebugUtils.currentLine=17039372;
 //BA.debugLineNum = 17039372;BA.debugLine="Dim spacing As Int = 5dip";
_spacing = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5));
RDebugUtils.currentLine=17039373;
 //BA.debugLineNum = 17039373;BA.debugLine="Dim currentY As Int = 10dip";
_currenty = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10));
RDebugUtils.currentLine=17039376;
 //BA.debugLineNum = 17039376;BA.debugLine="Dim btnAdd As Button";
_btnadd = new anywheresoftware.b4a.objects.ButtonWrapper();
RDebugUtils.currentLine=17039377;
 //BA.debugLineNum = 17039377;BA.debugLine="btnAdd.Initialize(\"btnAddNew\")";
_btnadd.Initialize(mostCurrent.activityBA,"btnAddNew");
RDebugUtils.currentLine=17039378;
 //BA.debugLineNum = 17039378;BA.debugLine="btnAdd.Text = \"+ Add New Student Record\"";
_btnadd.setText(BA.ObjectToCharSequence("+ Add New Student Record"));
RDebugUtils.currentLine=17039379;
 //BA.debugLineNum = 17039379;BA.debugLine="btnAdd.TextSize = 14";
_btnadd.setTextSize((float) (14));
RDebugUtils.currentLine=17039380;
 //BA.debugLineNum = 17039380;BA.debugLine="btnAdd.TextColor = Colors.White";
_btnadd.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=17039381;
 //BA.debugLineNum = 17039381;BA.debugLine="btnAdd.Color = Colors.RGB(52, 168, 83)";
_btnadd.setColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (52),(int) (168),(int) (83)));
RDebugUtils.currentLine=17039382;
 //BA.debugLineNum = 17039382;BA.debugLine="listPanel.AddView(btnAdd, 10dip, currentY, sv2.Wi";
_listpanel.AddView((android.view.View)(_btnadd.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),_currenty,(int) (_sv2.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (45)));
RDebugUtils.currentLine=17039383;
 //BA.debugLineNum = 17039383;BA.debugLine="currentY = currentY + 55dip";
_currenty = (int) (_currenty+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (55)));
RDebugUtils.currentLine=17039386;
 //BA.debugLineNum = 17039386;BA.debugLine="For i = 0 To StudentList.Size - 1";
{
final int step17 = 1;
final int limit17 = (int) (mostCurrent._studentlist.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit17 ;_i = _i + step17 ) {
RDebugUtils.currentLine=17039387;
 //BA.debugLineNum = 17039387;BA.debugLine="Dim row() As String = StudentList.Get(i)";
_row = (String[])(mostCurrent._studentlist.Get(_i));
RDebugUtils.currentLine=17039390;
 //BA.debugLineNum = 17039390;BA.debugLine="Dim pnlRow As Panel";
_pnlrow = new anywheresoftware.b4a.objects.PanelWrapper();
RDebugUtils.currentLine=17039391;
 //BA.debugLineNum = 17039391;BA.debugLine="pnlRow.Initialize(\"pnlRow\")";
_pnlrow.Initialize(mostCurrent.activityBA,"pnlRow");
RDebugUtils.currentLine=17039392;
 //BA.debugLineNum = 17039392;BA.debugLine="Dim cd As ColorDrawable";
_cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
RDebugUtils.currentLine=17039393;
 //BA.debugLineNum = 17039393;BA.debugLine="cd.Initialize2(Colors.White, 4dip, 1dip, Colors.";
_cd.Initialize2(anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (4)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1)),anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (200),(int) (200),(int) (200)));
RDebugUtils.currentLine=17039394;
 //BA.debugLineNum = 17039394;BA.debugLine="pnlRow.Background = cd";
_pnlrow.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
RDebugUtils.currentLine=17039395;
 //BA.debugLineNum = 17039395;BA.debugLine="pnlRow.Tag = i";
_pnlrow.setTag((Object)(_i));
RDebugUtils.currentLine=17039396;
 //BA.debugLineNum = 17039396;BA.debugLine="listPanel.AddView(pnlRow, 10dip, currentY, sv2.W";
_listpanel.AddView((android.view.View)(_pnlrow.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),_currenty,(int) (_sv2.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),_rowh);
RDebugUtils.currentLine=17039399;
 //BA.debugLineNum = 17039399;BA.debugLine="Dim lblInfo As Label";
_lblinfo = new anywheresoftware.b4a.objects.LabelWrapper();
RDebugUtils.currentLine=17039400;
 //BA.debugLineNum = 17039400;BA.debugLine="lblInfo.Initialize(\"\")";
_lblinfo.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=17039401;
 //BA.debugLineNum = 17039401;BA.debugLine="lblInfo.Text = row(COL_NAME) & \" (\" & row(COL_ID";
_lblinfo.setText(BA.ObjectToCharSequence(_row[_col_name]+" ("+_row[_col_id]+")"));
RDebugUtils.currentLine=17039402;
 //BA.debugLineNum = 17039402;BA.debugLine="lblInfo.TextSize = 13";
_lblinfo.setTextSize((float) (13));
RDebugUtils.currentLine=17039403;
 //BA.debugLineNum = 17039403;BA.debugLine="lblInfo.TextColor = Colors.Black";
_lblinfo.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
RDebugUtils.currentLine=17039404;
 //BA.debugLineNum = 17039404;BA.debugLine="lblInfo.Gravity = Gravity.CENTER_VERTICAL";
_lblinfo.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_VERTICAL);
RDebugUtils.currentLine=17039405;
 //BA.debugLineNum = 17039405;BA.debugLine="lblInfo.Typeface = Typeface.DEFAULT_BOLD";
_lblinfo.setTypeface(anywheresoftware.b4a.keywords.Common.Typeface.DEFAULT_BOLD);
RDebugUtils.currentLine=17039406;
 //BA.debugLineNum = 17039406;BA.debugLine="lblInfo.Padding = Array As Int(10dip, 0, 0, 0)";
_lblinfo.setPadding(new int[]{anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (0),(int) (0),(int) (0)});
RDebugUtils.currentLine=17039407;
 //BA.debugLineNum = 17039407;BA.debugLine="pnlRow.AddView(lblInfo, 0, 0, sv2.Width - 180dip";
_pnlrow.AddView((android.view.View)(_lblinfo.getObject()),(int) (0),(int) (0),(int) (_sv2.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (180))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
RDebugUtils.currentLine=17039409;
 //BA.debugLineNum = 17039409;BA.debugLine="Dim lblDetail As Label";
_lbldetail = new anywheresoftware.b4a.objects.LabelWrapper();
RDebugUtils.currentLine=17039410;
 //BA.debugLineNum = 17039410;BA.debugLine="lblDetail.Initialize(\"\")";
_lbldetail.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=17039411;
 //BA.debugLineNum = 17039411;BA.debugLine="lblDetail.Text = row(COL_SUBJECT) & \" | \" & row(";
_lbldetail.setText(BA.ObjectToCharSequence(_row[_col_subject]+" | "+_row[_col_activity]+" | "+_row[_col_attendance]+" | "+_row[_col_rate]+" ("+_computegrade(_row[_col_rate])+")"));
RDebugUtils.currentLine=17039412;
 //BA.debugLineNum = 17039412;BA.debugLine="lblDetail.TextSize = 11";
_lbldetail.setTextSize((float) (11));
RDebugUtils.currentLine=17039413;
 //BA.debugLineNum = 17039413;BA.debugLine="lblDetail.TextColor = Colors.DarkGray";
_lbldetail.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.DarkGray);
RDebugUtils.currentLine=17039414;
 //BA.debugLineNum = 17039414;BA.debugLine="lblDetail.Gravity = Gravity.CENTER_VERTICAL";
_lbldetail.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_VERTICAL);
RDebugUtils.currentLine=17039415;
 //BA.debugLineNum = 17039415;BA.debugLine="lblDetail.Padding = Array As Int(10dip, 0, 0, 0)";
_lbldetail.setPadding(new int[]{anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (0),(int) (0),(int) (0)});
RDebugUtils.currentLine=17039416;
 //BA.debugLineNum = 17039416;BA.debugLine="pnlRow.AddView(lblDetail, 0, 38dip, sv2.Width -";
_pnlrow.AddView((android.view.View)(_lbldetail.getObject()),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (38)),(int) (_sv2.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (180))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
RDebugUtils.currentLine=17039419;
 //BA.debugLineNum = 17039419;BA.debugLine="Dim btnEdit As Button";
_btnedit = new anywheresoftware.b4a.objects.ButtonWrapper();
RDebugUtils.currentLine=17039420;
 //BA.debugLineNum = 17039420;BA.debugLine="btnEdit.Initialize(\"btnEditRow\")";
_btnedit.Initialize(mostCurrent.activityBA,"btnEditRow");
RDebugUtils.currentLine=17039421;
 //BA.debugLineNum = 17039421;BA.debugLine="btnEdit.Text = \"Edit\"";
_btnedit.setText(BA.ObjectToCharSequence("Edit"));
RDebugUtils.currentLine=17039422;
 //BA.debugLineNum = 17039422;BA.debugLine="btnEdit.TextSize = 12";
_btnedit.setTextSize((float) (12));
RDebugUtils.currentLine=17039423;
 //BA.debugLineNum = 17039423;BA.debugLine="btnEdit.TextColor = Colors.White";
_btnedit.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=17039424;
 //BA.debugLineNum = 17039424;BA.debugLine="btnEdit.Color = Colors.RGB(66, 133, 244)";
_btnedit.setColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (66),(int) (133),(int) (244)));
RDebugUtils.currentLine=17039425;
 //BA.debugLineNum = 17039425;BA.debugLine="btnEdit.Tag = i";
_btnedit.setTag((Object)(_i));
RDebugUtils.currentLine=17039426;
 //BA.debugLineNum = 17039426;BA.debugLine="pnlRow.AddView(btnEdit, sv2.Width - 175dip, 15di";
_pnlrow.AddView((android.view.View)(_btnedit.getObject()),(int) (_sv2.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (175))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (15)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (70)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (45)));
RDebugUtils.currentLine=17039429;
 //BA.debugLineNum = 17039429;BA.debugLine="Dim btnDel As Button";
_btndel = new anywheresoftware.b4a.objects.ButtonWrapper();
RDebugUtils.currentLine=17039430;
 //BA.debugLineNum = 17039430;BA.debugLine="btnDel.Initialize(\"btnDeleteRow\")";
_btndel.Initialize(mostCurrent.activityBA,"btnDeleteRow");
RDebugUtils.currentLine=17039431;
 //BA.debugLineNum = 17039431;BA.debugLine="btnDel.Text = \"Del\"";
_btndel.setText(BA.ObjectToCharSequence("Del"));
RDebugUtils.currentLine=17039432;
 //BA.debugLineNum = 17039432;BA.debugLine="btnDel.TextSize = 12";
_btndel.setTextSize((float) (12));
RDebugUtils.currentLine=17039433;
 //BA.debugLineNum = 17039433;BA.debugLine="btnDel.TextColor = Colors.White";
_btndel.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=17039434;
 //BA.debugLineNum = 17039434;BA.debugLine="btnDel.Color = Colors.RGB(234, 67, 53)";
_btndel.setColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (234),(int) (67),(int) (53)));
RDebugUtils.currentLine=17039435;
 //BA.debugLineNum = 17039435;BA.debugLine="btnDel.Tag = i";
_btndel.setTag((Object)(_i));
RDebugUtils.currentLine=17039436;
 //BA.debugLineNum = 17039436;BA.debugLine="pnlRow.AddView(btnDel, sv2.Width - 95dip, 15dip,";
_pnlrow.AddView((android.view.View)(_btndel.getObject()),(int) (_sv2.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (95))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (15)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (70)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (45)));
RDebugUtils.currentLine=17039438;
 //BA.debugLineNum = 17039438;BA.debugLine="currentY = currentY + rowH + spacing";
_currenty = (int) (_currenty+_rowh+_spacing);
 }
};
RDebugUtils.currentLine=17039441;
 //BA.debugLineNum = 17039441;BA.debugLine="listPanel.Height = currentY + 20dip";
_listpanel.setHeight((int) (_currenty+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))));
RDebugUtils.currentLine=17039442;
 //BA.debugLineNum = 17039442;BA.debugLine="End Sub";
return "";
}
public static String  _computegrade(String _rate) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "computegrade", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "computegrade", new Object[] {_rate}));}
RDebugUtils.currentLine=16777216;
 //BA.debugLineNum = 16777216;BA.debugLine="Sub ComputeGrade(rate As String) As String";
RDebugUtils.currentLine=16777217;
 //BA.debugLineNum = 16777217;BA.debugLine="Return PercentageToGrade(ComputePercentage(rate))";
if (true) return _percentagetograde(_computepercentage(_rate));
RDebugUtils.currentLine=16777218;
 //BA.debugLineNum = 16777218;BA.debugLine="End Sub";
return "";
}
public static String  _button1_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "button1_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "button1_click", null));}
RDebugUtils.currentLine=5308416;
 //BA.debugLineNum = 5308416;BA.debugLine="Private Sub Button1_Click";
RDebugUtils.currentLine=5308418;
 //BA.debugLineNum = 5308418;BA.debugLine="showedit";
_showedit();
RDebugUtils.currentLine=5308419;
 //BA.debugLineNum = 5308419;BA.debugLine="End Sub";
return "";
}
public static String  _percentagetograde(double _pct) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "percentagetograde", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "percentagetograde", new Object[] {_pct}));}
RDebugUtils.currentLine=16711680;
 //BA.debugLineNum = 16711680;BA.debugLine="Sub PercentageToGrade(pct As Double) As String";
RDebugUtils.currentLine=16711681;
 //BA.debugLineNum = 16711681;BA.debugLine="If pct >= 90 Then Return \"A\"";
if (_pct>=90) { 
if (true) return "A";};
RDebugUtils.currentLine=16711682;
 //BA.debugLineNum = 16711682;BA.debugLine="If pct >= 80 Then Return \"B\"";
if (_pct>=80) { 
if (true) return "B";};
RDebugUtils.currentLine=16711683;
 //BA.debugLineNum = 16711683;BA.debugLine="If pct >= 70 Then Return \"C\"";
if (_pct>=70) { 
if (true) return "C";};
RDebugUtils.currentLine=16711684;
 //BA.debugLineNum = 16711684;BA.debugLine="If pct >= 60 Then Return \"D\"";
if (_pct>=60) { 
if (true) return "D";};
RDebugUtils.currentLine=16711685;
 //BA.debugLineNum = 16711685;BA.debugLine="Return \"F\"";
if (true) return "F";
RDebugUtils.currentLine=16711686;
 //BA.debugLineNum = 16711686;BA.debugLine="End Sub";
return "";
}
public static double  _computepercentage(String _rate) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "computepercentage", false))
	 {return ((Double) Debug.delegate(mostCurrent.activityBA, "computepercentage", new Object[] {_rate}));}
String[] _parts = null;
double _numerator = 0;
double _denominator = 0;
RDebugUtils.currentLine=16646144;
 //BA.debugLineNum = 16646144;BA.debugLine="Sub ComputePercentage(rate As String) As Double";
RDebugUtils.currentLine=16646145;
 //BA.debugLineNum = 16646145;BA.debugLine="If rate.Contains(\"/\") = False Then Return 0";
if (_rate.contains("/")==anywheresoftware.b4a.keywords.Common.False) { 
if (true) return 0;};
RDebugUtils.currentLine=16646146;
 //BA.debugLineNum = 16646146;BA.debugLine="Dim parts() As String = Regex.Split(\"/\", rate)";
_parts = anywheresoftware.b4a.keywords.Common.Regex.Split("/",_rate);
RDebugUtils.currentLine=16646147;
 //BA.debugLineNum = 16646147;BA.debugLine="If parts.Length <> 2 Then Return 0";
if (_parts.length!=2) { 
if (true) return 0;};
RDebugUtils.currentLine=16646148;
 //BA.debugLineNum = 16646148;BA.debugLine="Dim numerator As Double = parts(0)";
_numerator = (double)(Double.parseDouble(_parts[(int) (0)]));
RDebugUtils.currentLine=16646149;
 //BA.debugLineNum = 16646149;BA.debugLine="Dim denominator As Double = parts(1)";
_denominator = (double)(Double.parseDouble(_parts[(int) (1)]));
RDebugUtils.currentLine=16646150;
 //BA.debugLineNum = 16646150;BA.debugLine="If denominator = 0 Then Return 0";
if (_denominator==0) { 
if (true) return 0;};
RDebugUtils.currentLine=16646151;
 //BA.debugLineNum = 16646151;BA.debugLine="Return (numerator / denominator) * 100";
if (true) return (_numerator/(double)_denominator)*100;
RDebugUtils.currentLine=16646152;
 //BA.debugLineNum = 16646152;BA.debugLine="End Sub";
return 0;
}
public static String  _creat_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "creat_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "creat_click", null));}
RDebugUtils.currentLine=5373952;
 //BA.debugLineNum = 5373952;BA.debugLine="Private Sub CREAT_Click";
RDebugUtils.currentLine=5373954;
 //BA.debugLineNum = 5373954;BA.debugLine="EditingRowIndex = -1";
_editingrowindex = (int) (-1);
RDebugUtils.currentLine=5373955;
 //BA.debugLineNum = 5373955;BA.debugLine="ShowCREATForm(\"\", \"\", \"\", \"\", False)";
_showcreatform("","","","",anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=5373956;
 //BA.debugLineNum = 5373956;BA.debugLine="End Sub";
return "";
}
public static String  _createmenu() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "createmenu", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "createmenu", null));}
anywheresoftware.b4a.objects.ButtonWrapper _btndashboard = null;
anywheresoftware.b4a.objects.ButtonWrapper _btngrades = null;
anywheresoftware.b4a.objects.ButtonWrapper _btnedit = null;
anywheresoftware.b4a.objects.ButtonWrapper _btnprofile = null;
anywheresoftware.b4a.objects.ButtonWrapper _b = null;
RDebugUtils.currentLine=458752;
 //BA.debugLineNum = 458752;BA.debugLine="Sub CreateMenu";
RDebugUtils.currentLine=458753;
 //BA.debugLineNum = 458753;BA.debugLine="Dim btnDashBoard, btnGrades, btnedit, btnProfile";
_btndashboard = new anywheresoftware.b4a.objects.ButtonWrapper();
_btngrades = new anywheresoftware.b4a.objects.ButtonWrapper();
_btnedit = new anywheresoftware.b4a.objects.ButtonWrapper();
_btnprofile = new anywheresoftware.b4a.objects.ButtonWrapper();
RDebugUtils.currentLine=458754;
 //BA.debugLineNum = 458754;BA.debugLine="btnDashBoard.Initialize(\"btnDashBoard\")";
_btndashboard.Initialize(mostCurrent.activityBA,"btnDashBoard");
RDebugUtils.currentLine=458755;
 //BA.debugLineNum = 458755;BA.debugLine="btnDashBoard.Text = \"Dashboard\"";
_btndashboard.setText(BA.ObjectToCharSequence("Dashboard"));
RDebugUtils.currentLine=458756;
 //BA.debugLineNum = 458756;BA.debugLine="btnGrades.Initialize(\"btnGrades\")";
_btngrades.Initialize(mostCurrent.activityBA,"btnGrades");
RDebugUtils.currentLine=458757;
 //BA.debugLineNum = 458757;BA.debugLine="btnGrades.Text = \"Grades\"";
_btngrades.setText(BA.ObjectToCharSequence("Grades"));
RDebugUtils.currentLine=458758;
 //BA.debugLineNum = 458758;BA.debugLine="btnedit.Initialize(\"btnedit\")";
_btnedit.Initialize(mostCurrent.activityBA,"btnedit");
RDebugUtils.currentLine=458759;
 //BA.debugLineNum = 458759;BA.debugLine="btnedit.Text = \"Edit\"";
_btnedit.setText(BA.ObjectToCharSequence("Edit"));
RDebugUtils.currentLine=458760;
 //BA.debugLineNum = 458760;BA.debugLine="btnProfile.Initialize(\"btnProfile\")";
_btnprofile.Initialize(mostCurrent.activityBA,"btnProfile");
RDebugUtils.currentLine=458761;
 //BA.debugLineNum = 458761;BA.debugLine="btnProfile.Text = \"Profile\"";
_btnprofile.setText(BA.ObjectToCharSequence("Profile"));
RDebugUtils.currentLine=458763;
 //BA.debugLineNum = 458763;BA.debugLine="For Each b As Button In Array(btnDashBoard, btnGr";
_b = new anywheresoftware.b4a.objects.ButtonWrapper();
{
final Object[] group10 = new Object[]{(Object)(_btndashboard.getObject()),(Object)(_btngrades.getObject()),(Object)(_btnedit.getObject()),(Object)(_btnprofile.getObject())};
final int groupLen10 = group10.length
;int index10 = 0;
;
for (; index10 < groupLen10;index10++){
_b = (anywheresoftware.b4a.objects.ButtonWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ButtonWrapper(), (android.widget.Button)(group10[index10]));
RDebugUtils.currentLine=458764;
 //BA.debugLineNum = 458764;BA.debugLine="b.TextSize = 16";
_b.setTextSize((float) (16));
RDebugUtils.currentLine=458765;
 //BA.debugLineNum = 458765;BA.debugLine="b.Gravity = Gravity.LEFT + Gravity.CENTER_VERTIC";
_b.setGravity((int) (anywheresoftware.b4a.keywords.Common.Gravity.LEFT+anywheresoftware.b4a.keywords.Common.Gravity.CENTER_VERTICAL));
RDebugUtils.currentLine=458766;
 //BA.debugLineNum = 458766;BA.debugLine="b.Color = Colors.Transparent";
_b.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
RDebugUtils.currentLine=458767;
 //BA.debugLineNum = 458767;BA.debugLine="b.TextColor = Colors.White";
_b.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=458768;
 //BA.debugLineNum = 458768;BA.debugLine="pnlmenu.AddView(b, 10dip, 0, 240dip, 50dip)";
mostCurrent._pnlmenu.AddView((android.view.View)(_b.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (240)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
 }
};
RDebugUtils.currentLine=458770;
 //BA.debugLineNum = 458770;BA.debugLine="btnDashBoard.Top = 120dip";
_btndashboard.setTop(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (120)));
RDebugUtils.currentLine=458771;
 //BA.debugLineNum = 458771;BA.debugLine="btnGrades.Top = 180dip";
_btngrades.setTop(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (180)));
RDebugUtils.currentLine=458772;
 //BA.debugLineNum = 458772;BA.debugLine="btnedit.Top = 240dip";
_btnedit.setTop(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (240)));
RDebugUtils.currentLine=458773;
 //BA.debugLineNum = 458773;BA.debugLine="btnProfile.Top = 300dip";
_btnprofile.setTop(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (300)));
RDebugUtils.currentLine=458774;
 //BA.debugLineNum = 458774;BA.debugLine="End Sub";
return "";
}
public static String  _dashbtn_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "dashbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "dashbtn_click", null));}
RDebugUtils.currentLine=851968;
 //BA.debugLineNum = 851968;BA.debugLine="Private Sub dashbtn_Click";
RDebugUtils.currentLine=851969;
 //BA.debugLineNum = 851969;BA.debugLine="HighlightTab(dashbtn)";
_highlighttab(mostCurrent._dashbtn);
RDebugUtils.currentLine=851970;
 //BA.debugLineNum = 851970;BA.debugLine="showdashboard";
_showdashboard();
RDebugUtils.currentLine=851971;
 //BA.debugLineNum = 851971;BA.debugLine="End Sub";
return "";
}
public static String  _highlighttab(anywheresoftware.b4a.objects.PanelWrapper _activebtn) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "highlighttab", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "highlighttab", new Object[] {_activebtn}));}
RDebugUtils.currentLine=16449536;
 //BA.debugLineNum = 16449536;BA.debugLine="Sub HighlightTab(activeBtn As Panel)";
RDebugUtils.currentLine=16449537;
 //BA.debugLineNum = 16449537;BA.debugLine="dashbtn.Color = Colors.White";
mostCurrent._dashbtn.setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=16449538;
 //BA.debugLineNum = 16449538;BA.debugLine="grdbtn.Color = Colors.White";
mostCurrent._grdbtn.setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=16449539;
 //BA.debugLineNum = 16449539;BA.debugLine="editbtn.Color = Colors.White";
mostCurrent._editbtn.setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=16449540;
 //BA.debugLineNum = 16449540;BA.debugLine="profilebtn.Color = Colors.White";
mostCurrent._profilebtn.setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=16449541;
 //BA.debugLineNum = 16449541;BA.debugLine="activeBtn.Color = 0xFF1AEA00";
_activebtn.setColor(((int)0xff1aea00));
RDebugUtils.currentLine=16449542;
 //BA.debugLineNum = 16449542;BA.debugLine="End Sub";
return "";
}
public static String  _editbtn_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "editbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "editbtn_click", null));}
RDebugUtils.currentLine=3538944;
 //BA.debugLineNum = 3538944;BA.debugLine="Private Sub editbtn_Click";
RDebugUtils.currentLine=3538945;
 //BA.debugLineNum = 3538945;BA.debugLine="HighlightTab(editbtn)";
_highlighttab(mostCurrent._editbtn);
RDebugUtils.currentLine=3538946;
 //BA.debugLineNum = 3538946;BA.debugLine="showedit";
_showedit();
RDebugUtils.currentLine=3538947;
 //BA.debugLineNum = 3538947;BA.debugLine="End Sub";
return "";
}
public static String  _generategraph() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "generategraph", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "generategraph", null));}
anywheresoftware.b4a.objects.collections.List _names = null;
int _numcols = 0;
int _colwidth = 0;
int[] _barcolors = null;
double _maxval = 0;
int _i = 0;
double _avg = 0;
int _valuelabelheight = 0;
int _chartareaheight = 0;
int _barpadding = 0;
String _studentname = "";
double _avgpct = 0;
int _barheight = 0;
int _bartop = 0;
anywheresoftware.b4a.objects.PanelWrapper _pnlbar = null;
anywheresoftware.b4a.objects.LabelWrapper _lblvalue = null;
anywheresoftware.b4a.objects.LabelWrapper _l = null;
String _displayname = "";
String[] _nameparts = null;
RDebugUtils.currentLine=3145728;
 //BA.debugLineNum = 3145728;BA.debugLine="Sub generategraph";
RDebugUtils.currentLine=3145729;
 //BA.debugLineNum = 3145729;BA.debugLine="Dim names As List = GetUniqueStudentNames";
_names = new anywheresoftware.b4a.objects.collections.List();
_names = _getuniquestudentnames();
RDebugUtils.currentLine=3145730;
 //BA.debugLineNum = 3145730;BA.debugLine="If names.Size = 0 Then Return";
if (_names.getSize()==0) { 
if (true) return "";};
RDebugUtils.currentLine=3145732;
 //BA.debugLineNum = 3145732;BA.debugLine="Dim numCols As Int = names.Size";
_numcols = _names.getSize();
RDebugUtils.currentLine=3145733;
 //BA.debugLineNum = 3145733;BA.debugLine="Dim colWidth As Int = datapnl.Width / numCols";
_colwidth = (int) (mostCurrent._datapnl.getWidth()/(double)_numcols);
RDebugUtils.currentLine=3145736;
 //BA.debugLineNum = 3145736;BA.debugLine="Dim barColors() As Int = Array As Int( _ 		Colors";
_barcolors = new int[]{anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (66),(int) (133),(int) (244)),anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (234),(int) (67),(int) (53)),anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (251),(int) (188),(int) (4)),anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (52),(int) (168),(int) (83)),anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (156),(int) (39),(int) (176))};
RDebugUtils.currentLine=3145744;
 //BA.debugLineNum = 3145744;BA.debugLine="Dim maxVal As Double = 0";
_maxval = 0;
RDebugUtils.currentLine=3145745;
 //BA.debugLineNum = 3145745;BA.debugLine="For i = 0 To names.Size - 1";
{
final int step7 = 1;
final int limit7 = (int) (_names.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit7 ;_i = _i + step7 ) {
RDebugUtils.currentLine=3145746;
 //BA.debugLineNum = 3145746;BA.debugLine="Dim avg As Double = GetStudentAvgPercentage(name";
_avg = _getstudentavgpercentage(BA.ObjectToString(_names.Get(_i)));
RDebugUtils.currentLine=3145747;
 //BA.debugLineNum = 3145747;BA.debugLine="If avg > maxVal Then maxVal = avg";
if (_avg>_maxval) { 
_maxval = _avg;};
 }
};
RDebugUtils.currentLine=3145749;
 //BA.debugLineNum = 3145749;BA.debugLine="If maxVal = 0 Then maxVal = 100";
if (_maxval==0) { 
_maxval = 100;};
RDebugUtils.currentLine=3145751;
 //BA.debugLineNum = 3145751;BA.debugLine="Dim rowHeight As Int = 40dip";
_rowheight = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40));
RDebugUtils.currentLine=3145752;
 //BA.debugLineNum = 3145752;BA.debugLine="Dim valueLabelHeight As Int = 30dip";
_valuelabelheight = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30));
RDebugUtils.currentLine=3145753;
 //BA.debugLineNum = 3145753;BA.debugLine="Dim chartAreaHeight As Int = datapnl.Height - row";
_chartareaheight = (int) (mostCurrent._datapnl.getHeight()-_rowheight-_valuelabelheight);
RDebugUtils.currentLine=3145754;
 //BA.debugLineNum = 3145754;BA.debugLine="Dim barPadding As Int = 10dip";
_barpadding = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10));
RDebugUtils.currentLine=3145756;
 //BA.debugLineNum = 3145756;BA.debugLine="For i = 0 To numCols - 1";
{
final int step16 = 1;
final int limit16 = (int) (_numcols-1);
_i = (int) (0) ;
for (;_i <= limit16 ;_i = _i + step16 ) {
RDebugUtils.currentLine=3145757;
 //BA.debugLineNum = 3145757;BA.debugLine="Dim studentName As String = names.Get(i)";
_studentname = BA.ObjectToString(_names.Get(_i));
RDebugUtils.currentLine=3145758;
 //BA.debugLineNum = 3145758;BA.debugLine="Dim avgPct As Double = GetStudentAvgPercentage(s";
_avgpct = _getstudentavgpercentage(_studentname);
RDebugUtils.currentLine=3145761;
 //BA.debugLineNum = 3145761;BA.debugLine="Dim barHeight As Int";
_barheight = 0;
RDebugUtils.currentLine=3145762;
 //BA.debugLineNum = 3145762;BA.debugLine="If maxVal > 0 Then";
if (_maxval>0) { 
RDebugUtils.currentLine=3145763;
 //BA.debugLineNum = 3145763;BA.debugLine="barHeight = (avgPct / maxVal) * chartAreaHeight";
_barheight = (int) ((_avgpct/(double)_maxval)*_chartareaheight);
 }else {
RDebugUtils.currentLine=3145765;
 //BA.debugLineNum = 3145765;BA.debugLine="barHeight = 0";
_barheight = (int) (0);
 };
RDebugUtils.currentLine=3145768;
 //BA.debugLineNum = 3145768;BA.debugLine="Dim barTop As Int = datapnl.Height - rowHeight -";
_bartop = (int) (mostCurrent._datapnl.getHeight()-_rowheight-_barheight);
RDebugUtils.currentLine=3145770;
 //BA.debugLineNum = 3145770;BA.debugLine="Dim pnlBar As Panel";
_pnlbar = new anywheresoftware.b4a.objects.PanelWrapper();
RDebugUtils.currentLine=3145771;
 //BA.debugLineNum = 3145771;BA.debugLine="pnlBar.Initialize(\"pnlBar\")";
_pnlbar.Initialize(mostCurrent.activityBA,"pnlBar");
RDebugUtils.currentLine=3145772;
 //BA.debugLineNum = 3145772;BA.debugLine="pnlBar.Color = barColors(i Mod barColors.Length)";
_pnlbar.setColor(_barcolors[(int) (_i%_barcolors.length)]);
RDebugUtils.currentLine=3145773;
 //BA.debugLineNum = 3145773;BA.debugLine="pnlBar.Tag = i";
_pnlbar.setTag((Object)(_i));
RDebugUtils.currentLine=3145774;
 //BA.debugLineNum = 3145774;BA.debugLine="datapnl.AddView(pnlBar, _ 			(colWidth * i) + ba";
mostCurrent._datapnl.AddView((android.view.View)(_pnlbar.getObject()),(int) ((_colwidth*_i)+_barpadding),_bartop,(int) (_colwidth-(_barpadding*2)),_barheight);
RDebugUtils.currentLine=3145781;
 //BA.debugLineNum = 3145781;BA.debugLine="Dim lblValue As Label";
_lblvalue = new anywheresoftware.b4a.objects.LabelWrapper();
RDebugUtils.currentLine=3145782;
 //BA.debugLineNum = 3145782;BA.debugLine="lblValue.Initialize(\"lblValue\")";
_lblvalue.Initialize(mostCurrent.activityBA,"lblValue");
RDebugUtils.currentLine=3145783;
 //BA.debugLineNum = 3145783;BA.debugLine="lblValue.Text = Round2(avgPct, 0) & \"%\"";
_lblvalue.setText(BA.ObjectToCharSequence(BA.NumberToString(anywheresoftware.b4a.keywords.Common.Round2(_avgpct,(int) (0)))+"%"));
RDebugUtils.currentLine=3145784;
 //BA.debugLineNum = 3145784;BA.debugLine="lblValue.Gravity = Gravity.CENTER";
_lblvalue.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
RDebugUtils.currentLine=3145785;
 //BA.debugLineNum = 3145785;BA.debugLine="lblValue.TextSize = 12";
_lblvalue.setTextSize((float) (12));
RDebugUtils.currentLine=3145786;
 //BA.debugLineNum = 3145786;BA.debugLine="lblValue.TextColor = Colors.Black";
_lblvalue.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
RDebugUtils.currentLine=3145787;
 //BA.debugLineNum = 3145787;BA.debugLine="datapnl.AddView(lblValue, _ 			colWidth * i, _";
mostCurrent._datapnl.AddView((android.view.View)(_lblvalue.getObject()),(int) (_colwidth*_i),(int) (_bartop-_valuelabelheight),_colwidth,_valuelabelheight);
RDebugUtils.currentLine=3145794;
 //BA.debugLineNum = 3145794;BA.debugLine="Dim l As Label";
_l = new anywheresoftware.b4a.objects.LabelWrapper();
RDebugUtils.currentLine=3145795;
 //BA.debugLineNum = 3145795;BA.debugLine="l.Initialize(\"labels\")";
_l.Initialize(mostCurrent.activityBA,"labels");
RDebugUtils.currentLine=3145797;
 //BA.debugLineNum = 3145797;BA.debugLine="Dim displayName As String = studentName";
_displayname = _studentname;
RDebugUtils.currentLine=3145798;
 //BA.debugLineNum = 3145798;BA.debugLine="If displayName.Length > 8 Then";
if (_displayname.length()>8) { 
RDebugUtils.currentLine=3145799;
 //BA.debugLineNum = 3145799;BA.debugLine="Dim nameParts() As String = Regex.Split(\" \", di";
_nameparts = anywheresoftware.b4a.keywords.Common.Regex.Split(" ",_displayname);
RDebugUtils.currentLine=3145800;
 //BA.debugLineNum = 3145800;BA.debugLine="displayName = nameParts(0)";
_displayname = _nameparts[(int) (0)];
 };
RDebugUtils.currentLine=3145802;
 //BA.debugLineNum = 3145802;BA.debugLine="l.Text = displayName";
_l.setText(BA.ObjectToCharSequence(_displayname));
RDebugUtils.currentLine=3145803;
 //BA.debugLineNum = 3145803;BA.debugLine="l.Gravity = Gravity.CENTER";
_l.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
RDebugUtils.currentLine=3145804;
 //BA.debugLineNum = 3145804;BA.debugLine="l.TextSize = 10";
_l.setTextSize((float) (10));
RDebugUtils.currentLine=3145805;
 //BA.debugLineNum = 3145805;BA.debugLine="l.Color = 0x00ffffff";
_l.setColor(((int)0x00ffffff));
RDebugUtils.currentLine=3145806;
 //BA.debugLineNum = 3145806;BA.debugLine="l.TextColor = Colors.Black";
_l.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
RDebugUtils.currentLine=3145807;
 //BA.debugLineNum = 3145807;BA.debugLine="l.Padding = Array As Int(0dip, 5dip, 0dip, 5dip)";
_l.setPadding(new int[]{anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5))});
RDebugUtils.currentLine=3145808;
 //BA.debugLineNum = 3145808;BA.debugLine="l.Tag = i";
_l.setTag((Object)(_i));
RDebugUtils.currentLine=3145809;
 //BA.debugLineNum = 3145809;BA.debugLine="datapnl.AddView(l, _ 			colWidth * i, _ 			datap";
mostCurrent._datapnl.AddView((android.view.View)(_l.getObject()),(int) (_colwidth*_i),(int) (mostCurrent._datapnl.getHeight()-_rowheight),_colwidth,_rowheight);
 }
};
RDebugUtils.currentLine=3145815;
 //BA.debugLineNum = 3145815;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.collections.List  _getuniquestudentnames() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "getuniquestudentnames", false))
	 {return ((anywheresoftware.b4a.objects.collections.List) Debug.delegate(mostCurrent.activityBA, "getuniquestudentnames", null));}
anywheresoftware.b4a.objects.collections.List _names = null;
int _i = 0;
String[] _row = null;
String _name = "";
RDebugUtils.currentLine=16842752;
 //BA.debugLineNum = 16842752;BA.debugLine="Sub GetUniqueStudentNames As List";
RDebugUtils.currentLine=16842753;
 //BA.debugLineNum = 16842753;BA.debugLine="Dim names As List";
_names = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=16842754;
 //BA.debugLineNum = 16842754;BA.debugLine="names.Initialize";
_names.Initialize();
RDebugUtils.currentLine=16842755;
 //BA.debugLineNum = 16842755;BA.debugLine="For i = 0 To StudentList.Size - 1";
{
final int step3 = 1;
final int limit3 = (int) (mostCurrent._studentlist.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit3 ;_i = _i + step3 ) {
RDebugUtils.currentLine=16842756;
 //BA.debugLineNum = 16842756;BA.debugLine="Dim row() As String = StudentList.Get(i)";
_row = (String[])(mostCurrent._studentlist.Get(_i));
RDebugUtils.currentLine=16842757;
 //BA.debugLineNum = 16842757;BA.debugLine="Dim name As String = row(COL_NAME)";
_name = _row[_col_name];
RDebugUtils.currentLine=16842758;
 //BA.debugLineNum = 16842758;BA.debugLine="If names.IndexOf(name) = -1 Then";
if (_names.IndexOf((Object)(_name))==-1) { 
RDebugUtils.currentLine=16842759;
 //BA.debugLineNum = 16842759;BA.debugLine="names.Add(name)";
_names.Add((Object)(_name));
 };
 }
};
RDebugUtils.currentLine=16842762;
 //BA.debugLineNum = 16842762;BA.debugLine="Return names";
if (true) return _names;
RDebugUtils.currentLine=16842763;
 //BA.debugLineNum = 16842763;BA.debugLine="End Sub";
return null;
}
public static double  _getstudentavgpercentage(String _studentname) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "getstudentavgpercentage", false))
	 {return ((Double) Debug.delegate(mostCurrent.activityBA, "getstudentavgpercentage", new Object[] {_studentname}));}
double _total = 0;
int _count = 0;
int _i = 0;
String[] _row = null;
RDebugUtils.currentLine=16908288;
 //BA.debugLineNum = 16908288;BA.debugLine="Sub GetStudentAvgPercentage(studentName As String)";
RDebugUtils.currentLine=16908289;
 //BA.debugLineNum = 16908289;BA.debugLine="Dim total As Double = 0";
_total = 0;
RDebugUtils.currentLine=16908290;
 //BA.debugLineNum = 16908290;BA.debugLine="Dim count As Int = 0";
_count = (int) (0);
RDebugUtils.currentLine=16908291;
 //BA.debugLineNum = 16908291;BA.debugLine="For i = 0 To StudentList.Size - 1";
{
final int step3 = 1;
final int limit3 = (int) (mostCurrent._studentlist.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit3 ;_i = _i + step3 ) {
RDebugUtils.currentLine=16908292;
 //BA.debugLineNum = 16908292;BA.debugLine="Dim row() As String = StudentList.Get(i)";
_row = (String[])(mostCurrent._studentlist.Get(_i));
RDebugUtils.currentLine=16908293;
 //BA.debugLineNum = 16908293;BA.debugLine="If row(COL_NAME) = studentName Then";
if ((_row[_col_name]).equals(_studentname)) { 
RDebugUtils.currentLine=16908294;
 //BA.debugLineNum = 16908294;BA.debugLine="total = total + ComputePercentage(row(COL_RATE)";
_total = _total+_computepercentage(_row[_col_rate]);
RDebugUtils.currentLine=16908295;
 //BA.debugLineNum = 16908295;BA.debugLine="count = count + 1";
_count = (int) (_count+1);
 };
 }
};
RDebugUtils.currentLine=16908298;
 //BA.debugLineNum = 16908298;BA.debugLine="If count = 0 Then Return 0";
if (_count==0) { 
if (true) return 0;};
RDebugUtils.currentLine=16908299;
 //BA.debugLineNum = 16908299;BA.debugLine="Return total / count";
if (true) return _total/(double)_count;
RDebugUtils.currentLine=16908300;
 //BA.debugLineNum = 16908300;BA.debugLine="End Sub";
return 0;
}
public static String  _getstudentgrade(String _studentname) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "getstudentgrade", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "getstudentgrade", new Object[] {_studentname}));}
RDebugUtils.currentLine=16973824;
 //BA.debugLineNum = 16973824;BA.debugLine="Sub GetStudentGrade(studentName As String) As Stri";
RDebugUtils.currentLine=16973825;
 //BA.debugLineNum = 16973825;BA.debugLine="Return PercentageToGrade(GetStudentAvgPercentage(";
if (true) return _percentagetograde(_getstudentavgpercentage(_studentname));
RDebugUtils.currentLine=16973826;
 //BA.debugLineNum = 16973826;BA.debugLine="End Sub";
return "";
}
public static String  _grdbtn_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "grdbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "grdbtn_click", null));}
RDebugUtils.currentLine=983040;
 //BA.debugLineNum = 983040;BA.debugLine="Private Sub grdbtn_Click";
RDebugUtils.currentLine=983041;
 //BA.debugLineNum = 983041;BA.debugLine="HighlightTab(grdbtn)";
_highlighttab(mostCurrent._grdbtn);
RDebugUtils.currentLine=983042;
 //BA.debugLineNum = 983042;BA.debugLine="showgrades";
_showgrades();
RDebugUtils.currentLine=983043;
 //BA.debugLineNum = 983043;BA.debugLine="End Sub";
return "";
}
public static String  _setgradient(anywheresoftware.b4a.objects.PanelWrapper _pnl,int _color1,int _color2) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "setgradient", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "setgradient", new Object[] {_pnl,_color1,_color2}));}
anywheresoftware.b4a.objects.drawable.GradientDrawable _gd = null;
RDebugUtils.currentLine=1638400;
 //BA.debugLineNum = 1638400;BA.debugLine="Sub SetGradient(pnl As Panel, Color1 As Int, Color";
RDebugUtils.currentLine=1638401;
 //BA.debugLineNum = 1638401;BA.debugLine="Dim gd As GradientDrawable";
_gd = new anywheresoftware.b4a.objects.drawable.GradientDrawable();
RDebugUtils.currentLine=1638402;
 //BA.debugLineNum = 1638402;BA.debugLine="gd.Initialize(\"BR_TL\", Array As Int(Color1, Color";
_gd.Initialize(BA.getEnumFromString(android.graphics.drawable.GradientDrawable.Orientation.class,"BR_TL"),new int[]{_color1,_color2});
RDebugUtils.currentLine=1638403;
 //BA.debugLineNum = 1638403;BA.debugLine="pnl.Background = gd";
_pnl.setBackground((android.graphics.drawable.Drawable)(_gd.getObject()));
RDebugUtils.currentLine=1638404;
 //BA.debugLineNum = 1638404;BA.debugLine="End Sub";
return "";
}
public static String  _loadtable() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "loadtable", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "loadtable", null));}
anywheresoftware.b4a.objects.collections.List _list1 = null;
anywheresoftware.b4a.objects.collections.List _headers = null;
int _i = 0;
anywheresoftware.b4a.objects.LabelWrapper _l = null;
String[] _row = null;
RDebugUtils.currentLine=1376256;
 //BA.debugLineNum = 1376256;BA.debugLine="Sub loadtable";
RDebugUtils.currentLine=1376258;
 //BA.debugLineNum = 1376258;BA.debugLine="Dim List1 As List";
_list1 = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=1376259;
 //BA.debugLineNum = 1376259;BA.debugLine="Dim headers As List";
_headers = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=1376260;
 //BA.debugLineNum = 1376260;BA.debugLine="headers.Initialize";
_headers.Initialize();
RDebugUtils.currentLine=1376261;
 //BA.debugLineNum = 1376261;BA.debugLine="List1 = StringUtils1.LoadCSV2(File.DirInternal, \"";
_list1 = _stringutils1.LoadCSV2(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"citylist.csv",BA.ObjectToChar(","),_headers);
RDebugUtils.currentLine=1376262;
 //BA.debugLineNum = 1376262;BA.debugLine="NumberOfColumns = headers.Size";
_numberofcolumns = _headers.getSize();
RDebugUtils.currentLine=1376263;
 //BA.debugLineNum = 1376263;BA.debugLine="ColumnWidth = SV.Width / NumberOfColumns";
_columnwidth = (int) (mostCurrent._sv.getWidth()/(double)_numberofcolumns);
RDebugUtils.currentLine=1376265;
 //BA.debugLineNum = 1376265;BA.debugLine="For i = 0 To NumberOfColumns - 1";
{
final int step7 = 1;
final int limit7 = (int) (_numberofcolumns-1);
_i = (int) (0) ;
for (;_i <= limit7 ;_i = _i + step7 ) {
RDebugUtils.currentLine=1376266;
 //BA.debugLineNum = 1376266;BA.debugLine="Dim l As Label";
_l = new anywheresoftware.b4a.objects.LabelWrapper();
RDebugUtils.currentLine=1376267;
 //BA.debugLineNum = 1376267;BA.debugLine="l.Initialize(\"header\")";
_l.Initialize(mostCurrent.activityBA,"header");
RDebugUtils.currentLine=1376268;
 //BA.debugLineNum = 1376268;BA.debugLine="l.Text = headers.Get(i)";
_l.setText(BA.ObjectToCharSequence(_headers.Get(_i)));
RDebugUtils.currentLine=1376269;
 //BA.debugLineNum = 1376269;BA.debugLine="l.Gravity = Gravity.LEFT";
_l.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.LEFT);
RDebugUtils.currentLine=1376270;
 //BA.debugLineNum = 1376270;BA.debugLine="l.TextSize = FontSize";
_l.setTextSize(_fontsize);
RDebugUtils.currentLine=1376271;
 //BA.debugLineNum = 1376271;BA.debugLine="l.Color = HeaderColor";
_l.setColor(_headercolor);
RDebugUtils.currentLine=1376272;
 //BA.debugLineNum = 1376272;BA.debugLine="l.TextColor = HeaderFontColor";
_l.setTextColor(_headerfontcolor);
RDebugUtils.currentLine=1376273;
 //BA.debugLineNum = 1376273;BA.debugLine="l.Padding = Array As Int(10dip, 5dip, 0dip, 5dip";
_l.setPadding(new int[]{anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5))});
RDebugUtils.currentLine=1376274;
 //BA.debugLineNum = 1376274;BA.debugLine="l.Tag = i";
_l.setTag((Object)(_i));
RDebugUtils.currentLine=1376275;
 //BA.debugLineNum = 1376275;BA.debugLine="Table.AddView(l, ColumnWidth * i, 0, ColumnWidth";
mostCurrent._table.AddView((android.view.View)(_l.getObject()),(int) (_columnwidth*_i),(int) (0),_columnwidth,_rowheight);
 }
};
RDebugUtils.currentLine=1376279;
 //BA.debugLineNum = 1376279;BA.debugLine="For i = 0 To List1.Size - 1";
{
final int step19 = 1;
final int limit19 = (int) (_list1.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit19 ;_i = _i + step19 ) {
RDebugUtils.currentLine=1376280;
 //BA.debugLineNum = 1376280;BA.debugLine="Dim row() As String = List1.Get(i)";
_row = (String[])(_list1.Get(_i));
RDebugUtils.currentLine=1376281;
 //BA.debugLineNum = 1376281;BA.debugLine="AddTableRow(row, i + 1)  ' +1 to skip header row";
_addtablerow(_row,(int) (_i+1));
 }
};
RDebugUtils.currentLine=1376284;
 //BA.debugLineNum = 1376284;BA.debugLine="Table.Height = (List1.Size + 1) * RowHeight";
mostCurrent._table.setHeight((int) ((_list1.getSize()+1)*_rowheight));
RDebugUtils.currentLine=1376285;
 //BA.debugLineNum = 1376285;BA.debugLine="End Sub";
return "";
}
public static String  _makeshadow(int _numstudent,anywheresoftware.b4a.objects.collections.List _studentnames) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "makeshadow", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "makeshadow", new Object[] {_numstudent,_studentnames}));}
int _itemspacing = 0;
int _starttopmargin = 0;
int _i = 0;
int _currenttop = 0;
anywheresoftware.b4a.objects.PanelWrapper _pnlshadow = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _cd = null;
int _pnlwidth = 0;
int _pnlheight = 0;
int _shadowleftpos = 0;
int _shadowtoppos = 0;
String _name = "";
String _grade = "";
RDebugUtils.currentLine=5242880;
 //BA.debugLineNum = 5242880;BA.debugLine="Sub makeshadow(numstudent As Int, studentNames As";
RDebugUtils.currentLine=5242881;
 //BA.debugLineNum = 5242881;BA.debugLine="Dim itemSpacing As Int = 108dip";
_itemspacing = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (108));
RDebugUtils.currentLine=5242882;
 //BA.debugLineNum = 5242882;BA.debugLine="Dim startTopMargin As Int = 20dip";
_starttopmargin = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20));
RDebugUtils.currentLine=5242884;
 //BA.debugLineNum = 5242884;BA.debugLine="For i = 0 To numstudent - 1";
{
final int step3 = 1;
final int limit3 = (int) (_numstudent-1);
_i = (int) (0) ;
for (;_i <= limit3 ;_i = _i + step3 ) {
RDebugUtils.currentLine=5242885;
 //BA.debugLineNum = 5242885;BA.debugLine="Dim currentTop As Int = startTopMargin + (i * it";
_currenttop = (int) (_starttopmargin+(_i*_itemspacing));
RDebugUtils.currentLine=5242888;
 //BA.debugLineNum = 5242888;BA.debugLine="Dim pnlShadow As Panel";
_pnlshadow = new anywheresoftware.b4a.objects.PanelWrapper();
RDebugUtils.currentLine=5242889;
 //BA.debugLineNum = 5242889;BA.debugLine="pnlShadow.Initialize(\"pnlShadow\")";
_pnlshadow.Initialize(mostCurrent.activityBA,"pnlShadow");
RDebugUtils.currentLine=5242891;
 //BA.debugLineNum = 5242891;BA.debugLine="Dim cd As ColorDrawable";
_cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
RDebugUtils.currentLine=5242892;
 //BA.debugLineNum = 5242892;BA.debugLine="cd.Initialize2(0xFF000000, 4dip, 0dip, 0xFF00000";
_cd.Initialize2(((int)0xff000000),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (4)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0)),((int)0xff000000));
RDebugUtils.currentLine=5242893;
 //BA.debugLineNum = 5242893;BA.debugLine="pnlShadow.Background = cd";
_pnlshadow.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
RDebugUtils.currentLine=5242894;
 //BA.debugLineNum = 5242894;BA.debugLine="pnlShadow.Elevation = 0";
_pnlshadow.setElevation((float) (0));
RDebugUtils.currentLine=5242896;
 //BA.debugLineNum = 5242896;BA.debugLine="Dim pnlWidth As Int = 330dip";
_pnlwidth = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (330));
RDebugUtils.currentLine=5242897;
 //BA.debugLineNum = 5242897;BA.debugLine="Dim pnlHeight As Int = 88dip";
_pnlheight = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (88));
RDebugUtils.currentLine=5242899;
 //BA.debugLineNum = 5242899;BA.debugLine="Dim shadowLeftPos As Int = gradepnl.Width - 13di";
_shadowleftpos = (int) (mostCurrent._gradepnl.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (13))-_pnlwidth);
RDebugUtils.currentLine=5242900;
 //BA.debugLineNum = 5242900;BA.debugLine="Dim shadowTopPos As Int = currentTop + 7dip";
_shadowtoppos = (int) (_currenttop+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (7)));
RDebugUtils.currentLine=5242902;
 //BA.debugLineNum = 5242902;BA.debugLine="gradepnl.AddView(pnlShadow, shadowLeftPos, shado";
mostCurrent._gradepnl.AddView((android.view.View)(_pnlshadow.getObject()),_shadowleftpos,_shadowtoppos,_pnlwidth,_pnlheight);
RDebugUtils.currentLine=5242904;
 //BA.debugLineNum = 5242904;BA.debugLine="Dim name As String = studentNames.Get(i)";
_name = BA.ObjectToString(_studentnames.Get(_i));
RDebugUtils.currentLine=5242905;
 //BA.debugLineNum = 5242905;BA.debugLine="Dim grade As String = GetStudentGrade(name)";
_grade = _getstudentgrade(_name);
RDebugUtils.currentLine=5242906;
 //BA.debugLineNum = 5242906;BA.debugLine="studentgrade(name, grade, currentTop)";
_studentgrade(_name,_grade,_currenttop);
 }
};
RDebugUtils.currentLine=5242908;
 //BA.debugLineNum = 5242908;BA.debugLine="End Sub";
return "";
}
public static String  _studentgrade(String _studentname,String _grade,int _currenttop) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "studentgrade", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "studentgrade", new Object[] {_studentname,_grade,_currenttop}));}
anywheresoftware.b4a.objects.PanelWrapper _pnlcard = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _cd = null;
int _pnlwidth = 0;
int _pnlheight = 0;
int _rightedgedistance = 0;
int _leftpos = 0;
int _toppos = 0;
anywheresoftware.b4a.objects.LabelWrapper _lblgrade = null;
int _graderightedge = 0;
int _gradewidth = 0;
anywheresoftware.b4a.objects.LabelWrapper _lbl = null;
RDebugUtils.currentLine=4587520;
 //BA.debugLineNum = 4587520;BA.debugLine="Sub studentgrade(studentName As String, grade As S";
RDebugUtils.currentLine=4587521;
 //BA.debugLineNum = 4587521;BA.debugLine="Dim pnlCard As Panel";
_pnlcard = new anywheresoftware.b4a.objects.PanelWrapper();
RDebugUtils.currentLine=4587522;
 //BA.debugLineNum = 4587522;BA.debugLine="pnlCard.Initialize(\"pnlCard\")";
_pnlcard.Initialize(mostCurrent.activityBA,"pnlCard");
RDebugUtils.currentLine=4587524;
 //BA.debugLineNum = 4587524;BA.debugLine="Dim cd As ColorDrawable";
_cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
RDebugUtils.currentLine=4587525;
 //BA.debugLineNum = 4587525;BA.debugLine="cd.Initialize2(0xFFFFD400, 2dip, 2dip, 0xFF000000";
_cd.Initialize2(((int)0xffffd400),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)),((int)0xff000000));
RDebugUtils.currentLine=4587526;
 //BA.debugLineNum = 4587526;BA.debugLine="pnlCard.Background = cd";
_pnlcard.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
RDebugUtils.currentLine=4587527;
 //BA.debugLineNum = 4587527;BA.debugLine="pnlCard.Elevation = 0";
_pnlcard.setElevation((float) (0));
RDebugUtils.currentLine=4587529;
 //BA.debugLineNum = 4587529;BA.debugLine="Dim pnlWidth As Int = 330dip";
_pnlwidth = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (330));
RDebugUtils.currentLine=4587530;
 //BA.debugLineNum = 4587530;BA.debugLine="Dim pnlHeight As Int = 88dip";
_pnlheight = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (88));
RDebugUtils.currentLine=4587531;
 //BA.debugLineNum = 4587531;BA.debugLine="Dim rightEdgeDistance As Int = 20dip";
_rightedgedistance = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20));
RDebugUtils.currentLine=4587533;
 //BA.debugLineNum = 4587533;BA.debugLine="Dim leftPos As Int = gradepnl.Width - rightEdgeDi";
_leftpos = (int) (mostCurrent._gradepnl.getWidth()-_rightedgedistance-_pnlwidth);
RDebugUtils.currentLine=4587534;
 //BA.debugLineNum = 4587534;BA.debugLine="Dim topPos As Int = currentTop";
_toppos = _currenttop;
RDebugUtils.currentLine=4587536;
 //BA.debugLineNum = 4587536;BA.debugLine="gradepnl.AddView(pnlCard, leftPos, topPos, pnlWid";
mostCurrent._gradepnl.AddView((android.view.View)(_pnlcard.getObject()),_leftpos,_toppos,_pnlwidth,_pnlheight);
RDebugUtils.currentLine=4587539;
 //BA.debugLineNum = 4587539;BA.debugLine="Dim lblGrade As Label";
_lblgrade = new anywheresoftware.b4a.objects.LabelWrapper();
RDebugUtils.currentLine=4587540;
 //BA.debugLineNum = 4587540;BA.debugLine="lblGrade.Initialize(\"lblGrade\")";
_lblgrade.Initialize(mostCurrent.activityBA,"lblGrade");
RDebugUtils.currentLine=4587541;
 //BA.debugLineNum = 4587541;BA.debugLine="lblGrade.Text = grade";
_lblgrade.setText(BA.ObjectToCharSequence(_grade));
RDebugUtils.currentLine=4587542;
 //BA.debugLineNum = 4587542;BA.debugLine="lblGrade.TextSize = 30";
_lblgrade.setTextSize((float) (30));
RDebugUtils.currentLine=4587543;
 //BA.debugLineNum = 4587543;BA.debugLine="lblGrade.TextColor = Colors.Black";
_lblgrade.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
RDebugUtils.currentLine=4587544;
 //BA.debugLineNum = 4587544;BA.debugLine="lblGrade.Gravity = Bit.Or(Gravity.CENTER_HORIZONT";
_lblgrade.setGravity(anywheresoftware.b4a.keywords.Common.Bit.Or(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_HORIZONTAL,anywheresoftware.b4a.keywords.Common.Gravity.CENTER_VERTICAL));
RDebugUtils.currentLine=4587545;
 //BA.debugLineNum = 4587545;BA.debugLine="lblGrade.Typeface = Typeface.CreateNew(Typeface.S";
_lblgrade.setTypeface(anywheresoftware.b4a.keywords.Common.Typeface.CreateNew(anywheresoftware.b4a.keywords.Common.Typeface.SERIF,anywheresoftware.b4a.keywords.Common.Typeface.STYLE_BOLD_ITALIC));
RDebugUtils.currentLine=4587546;
 //BA.debugLineNum = 4587546;BA.debugLine="lblGrade.SingleLine = False";
_lblgrade.setSingleLine(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=4587547;
 //BA.debugLineNum = 4587547;BA.debugLine="lblGrade.Enabled = True";
_lblgrade.setEnabled(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=4587548;
 //BA.debugLineNum = 4587548;BA.debugLine="lblGrade.Visible = True";
_lblgrade.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=4587550;
 //BA.debugLineNum = 4587550;BA.debugLine="Dim gradeRightEdge As Int = 264dip";
_graderightedge = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (264));
RDebugUtils.currentLine=4587551;
 //BA.debugLineNum = 4587551;BA.debugLine="Dim gradeWidth As Int = pnlWidth - gradeRightEdge";
_gradewidth = (int) (_pnlwidth-_graderightedge);
RDebugUtils.currentLine=4587552;
 //BA.debugLineNum = 4587552;BA.debugLine="pnlCard.AddView(lblGrade, 0, 0, gradeWidth, pnlHe";
_pnlcard.AddView((android.view.View)(_lblgrade.getObject()),(int) (0),(int) (0),_gradewidth,_pnlheight);
RDebugUtils.currentLine=4587555;
 //BA.debugLineNum = 4587555;BA.debugLine="Dim lbl As Label";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
RDebugUtils.currentLine=4587556;
 //BA.debugLineNum = 4587556;BA.debugLine="lbl.Initialize(\"lbl\")";
_lbl.Initialize(mostCurrent.activityBA,"lbl");
RDebugUtils.currentLine=4587557;
 //BA.debugLineNum = 4587557;BA.debugLine="lbl.Text = studentName";
_lbl.setText(BA.ObjectToCharSequence(_studentname));
RDebugUtils.currentLine=4587558;
 //BA.debugLineNum = 4587558;BA.debugLine="lbl.Gravity = Gravity.CENTER_VERTICAL";
_lbl.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_VERTICAL);
RDebugUtils.currentLine=4587559;
 //BA.debugLineNum = 4587559;BA.debugLine="lbl.Typeface = Typeface.CreateNew(Typeface.SERIF,";
_lbl.setTypeface(anywheresoftware.b4a.keywords.Common.Typeface.CreateNew(anywheresoftware.b4a.keywords.Common.Typeface.SERIF,anywheresoftware.b4a.keywords.Common.Typeface.STYLE_BOLD_ITALIC));
RDebugUtils.currentLine=4587560;
 //BA.debugLineNum = 4587560;BA.debugLine="lbl.TextSize = 20";
_lbl.setTextSize((float) (20));
RDebugUtils.currentLine=4587561;
 //BA.debugLineNum = 4587561;BA.debugLine="lbl.TextColor = Colors.Black";
_lbl.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
RDebugUtils.currentLine=4587562;
 //BA.debugLineNum = 4587562;BA.debugLine="lbl.Padding = Array As Int(10dip, 5dip, 10dip, 5d";
_lbl.setPadding(new int[]{anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5))});
RDebugUtils.currentLine=4587563;
 //BA.debugLineNum = 4587563;BA.debugLine="pnlCard.AddView(lbl, gradeWidth, 0, pnlWidth - gr";
_pnlcard.AddView((android.view.View)(_lbl.getObject()),_gradewidth,(int) (0),(int) (_pnlwidth-_gradewidth),_pnlheight);
RDebugUtils.currentLine=4587564;
 //BA.debugLineNum = 4587564;BA.debugLine="End Sub";
return "";
}
public static String  _profilebtn_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "profilebtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "profilebtn_click", null));}
RDebugUtils.currentLine=1245184;
 //BA.debugLineNum = 1245184;BA.debugLine="Private Sub profilebtn_Click";
RDebugUtils.currentLine=1245185;
 //BA.debugLineNum = 1245185;BA.debugLine="HighlightTab(profilebtn)";
_highlighttab(mostCurrent._profilebtn);
RDebugUtils.currentLine=1245186;
 //BA.debugLineNum = 1245186;BA.debugLine="showprofile";
_showprofile();
RDebugUtils.currentLine=1245187;
 //BA.debugLineNum = 1245187;BA.debugLine="End Sub";
return "";
}
public static String  _showtable() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "showtable", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "showtable", null));}
RDebugUtils.currentLine=1310720;
 //BA.debugLineNum = 1310720;BA.debugLine="Sub ShowTable";
RDebugUtils.currentLine=1310721;
 //BA.debugLineNum = 1310721;BA.debugLine="pnlmain.RemoveAllViews";
mostCurrent._pnlmain.RemoveAllViews();
RDebugUtils.currentLine=1310722;
 //BA.debugLineNum = 1310722;BA.debugLine="pnlmain.LoadLayout(\"profile\")";
mostCurrent._pnlmain.LoadLayout("profile",mostCurrent.activityBA);
RDebugUtils.currentLine=1310723;
 //BA.debugLineNum = 1310723;BA.debugLine="Table = SV.Panel";
mostCurrent._table = mostCurrent._sv.getPanel();
RDebugUtils.currentLine=1310724;
 //BA.debugLineNum = 1310724;BA.debugLine="NumberOfColumns = 6";
_numberofcolumns = (int) (6);
RDebugUtils.currentLine=1310725;
 //BA.debugLineNum = 1310725;BA.debugLine="ColumnWidth = SV.Width / NumberOfColumns";
_columnwidth = (int) (mostCurrent._sv.getWidth()/(double)_numberofcolumns);
RDebugUtils.currentLine=1310726;
 //BA.debugLineNum = 1310726;BA.debugLine="loadtable";
_loadtable();
RDebugUtils.currentLine=1310727;
 //BA.debugLineNum = 1310727;BA.debugLine="End Sub";
return "";
}
}