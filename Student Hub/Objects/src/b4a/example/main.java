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
public anywheresoftware.b4a.objects.collections.List _student_names = null;
public anywheresoftware.b4a.objects.collections.List _student_ids = null;
public anywheresoftware.b4a.objects.collections.List _student_subjects = null;
public anywheresoftware.b4a.objects.collections.List _student_activitys = null;
public anywheresoftware.b4a.objects.collections.List _student_attendance = null;
public anywheresoftware.b4a.objects.collections.List _student_rate = null;
public static boolean _isselectingid = false;
public static boolean _showselectid = false;
public static boolean _todelete = false;
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
public anywheresoftware.b4a.objects.collections.List _studentlist = null;
public anywheresoftware.b4a.objects.collections.List _csvheaders = null;
public static int _col_name = 0;
public static int _col_rate = 0;
public static int _col_activity = 0;
public anywheresoftware.b4a.objects.LabelWrapper _name1 = null;
public anywheresoftware.b4a.objects.LabelWrapper _name2 = null;
public anywheresoftware.b4a.objects.LabelWrapper _name3 = null;
public anywheresoftware.b4a.objects.LabelWrapper _rate1 = null;
public anywheresoftware.b4a.objects.LabelWrapper _rate2 = null;
public anywheresoftware.b4a.objects.LabelWrapper _rate3 = null;
public anywheresoftware.b4a.objects.LabelWrapper _activity1 = null;
public anywheresoftware.b4a.objects.LabelWrapper _activity2 = null;
public anywheresoftware.b4a.objects.LabelWrapper _activity3 = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _checkbox1 = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _checkbox2 = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _checkbox3 = null;
public anywheresoftware.b4a.objects.EditTextWrapper _edittext1 = null;
public anywheresoftware.b4a.objects.EditTextWrapper _edittext2 = null;
public anywheresoftware.b4a.objects.SpinnerWrapper _selectsubjects = null;
public anywheresoftware.b4a.objects.EditTextWrapper _student_id = null;
public anywheresoftware.b4a.objects.EditTextWrapper _student_name = null;
public anywheresoftware.b4a.objects.SpinnerWrapper _student_activity = null;
public anywheresoftware.b4a.objects.PanelWrapper _update = null;
public anywheresoftware.b4a.objects.PanelWrapper _bak = null;
public anywheresoftware.b4a.objects.SpinnerWrapper _spinner = null;
public static int _editrowindex = 0;
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
 //BA.debugLineNum = 131075;BA.debugLine="initdrawer";
_initdrawer();
RDebugUtils.currentLine=131078;
 //BA.debugLineNum = 131078;BA.debugLine="HeaderColor = Colors.Transparent";
_headercolor = anywheresoftware.b4a.keywords.Common.Colors.Transparent;
RDebugUtils.currentLine=131079;
 //BA.debugLineNum = 131079;BA.debugLine="NumberOfColumns = 6";
_numberofcolumns = (int) (6);
RDebugUtils.currentLine=131080;
 //BA.debugLineNum = 131080;BA.debugLine="RowHeight = 30dip";
_rowheight = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30));
RDebugUtils.currentLine=131081;
 //BA.debugLineNum = 131081;BA.debugLine="FontColor = Colors.Black";
_fontcolor = anywheresoftware.b4a.keywords.Common.Colors.Black;
RDebugUtils.currentLine=131082;
 //BA.debugLineNum = 131082;BA.debugLine="HeaderFontColor = Colors.Black";
_headerfontcolor = anywheresoftware.b4a.keywords.Common.Colors.Black;
RDebugUtils.currentLine=131083;
 //BA.debugLineNum = 131083;BA.debugLine="FontSize = 14";
_fontsize = (float) (14);
RDebugUtils.currentLine=131084;
 //BA.debugLineNum = 131084;BA.debugLine="Alignment = Gravity.LEFT";
_alignment = anywheresoftware.b4a.keywords.Common.Gravity.LEFT;
RDebugUtils.currentLine=131087;
 //BA.debugLineNum = 131087;BA.debugLine="LoadStudentData";
_loadstudentdata();
RDebugUtils.currentLine=131089;
 //BA.debugLineNum = 131089;BA.debugLine="showedit";
_showedit();
RDebugUtils.currentLine=131090;
 //BA.debugLineNum = 131090;BA.debugLine="End Sub";
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
int _i = 0;
String[] _row = null;
RDebugUtils.currentLine=1114112;
 //BA.debugLineNum = 1114112;BA.debugLine="Sub LoadStudentData";
RDebugUtils.currentLine=1114113;
 //BA.debugLineNum = 1114113;BA.debugLine="CSVHeaders.Initialize";
mostCurrent._csvheaders.Initialize();
RDebugUtils.currentLine=1114114;
 //BA.debugLineNum = 1114114;BA.debugLine="StudentList = StringUtils1.LoadCSV2(File.DirInter";
mostCurrent._studentlist = _stringutils1.LoadCSV2(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"student.csv",BA.ObjectToChar(","),mostCurrent._csvheaders);
RDebugUtils.currentLine=1114117;
 //BA.debugLineNum = 1114117;BA.debugLine="student_names.Initialize";
mostCurrent._student_names.Initialize();
RDebugUtils.currentLine=1114118;
 //BA.debugLineNum = 1114118;BA.debugLine="student_ids.Initialize";
mostCurrent._student_ids.Initialize();
RDebugUtils.currentLine=1114119;
 //BA.debugLineNum = 1114119;BA.debugLine="student_subjects.Initialize";
mostCurrent._student_subjects.Initialize();
RDebugUtils.currentLine=1114120;
 //BA.debugLineNum = 1114120;BA.debugLine="student_activitys.Initialize";
mostCurrent._student_activitys.Initialize();
RDebugUtils.currentLine=1114121;
 //BA.debugLineNum = 1114121;BA.debugLine="student_attendance.Initialize";
mostCurrent._student_attendance.Initialize();
RDebugUtils.currentLine=1114122;
 //BA.debugLineNum = 1114122;BA.debugLine="student_rate.Initialize";
mostCurrent._student_rate.Initialize();
RDebugUtils.currentLine=1114125;
 //BA.debugLineNum = 1114125;BA.debugLine="For i = 0 To StudentList.Size - 1";
{
final int step9 = 1;
final int limit9 = (int) (mostCurrent._studentlist.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit9 ;_i = _i + step9 ) {
RDebugUtils.currentLine=1114126;
 //BA.debugLineNum = 1114126;BA.debugLine="Dim row() As String = StudentList.Get(i)";
_row = (String[])(mostCurrent._studentlist.Get(_i));
RDebugUtils.currentLine=1114127;
 //BA.debugLineNum = 1114127;BA.debugLine="student_names.Add(row(0))";
mostCurrent._student_names.Add((Object)(_row[(int) (0)]));
RDebugUtils.currentLine=1114128;
 //BA.debugLineNum = 1114128;BA.debugLine="student_ids.Add(row(1))";
mostCurrent._student_ids.Add((Object)(_row[(int) (1)]));
RDebugUtils.currentLine=1114129;
 //BA.debugLineNum = 1114129;BA.debugLine="student_subjects.Add(row(2))";
mostCurrent._student_subjects.Add((Object)(_row[(int) (2)]));
RDebugUtils.currentLine=1114130;
 //BA.debugLineNum = 1114130;BA.debugLine="student_activitys.Add(row(3))";
mostCurrent._student_activitys.Add((Object)(_row[(int) (3)]));
RDebugUtils.currentLine=1114131;
 //BA.debugLineNum = 1114131;BA.debugLine="student_attendance.Add(row(4))";
mostCurrent._student_attendance.Add((Object)(_row[(int) (4)]));
RDebugUtils.currentLine=1114132;
 //BA.debugLineNum = 1114132;BA.debugLine="student_rate.Add(row(5))";
mostCurrent._student_rate.Add((Object)(_row[(int) (5)]));
 }
};
RDebugUtils.currentLine=1114134;
 //BA.debugLineNum = 1114134;BA.debugLine="End Sub";
return "";
}
public static String  _showedit() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "showedit", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "showedit", null));}
RDebugUtils.currentLine=2031616;
 //BA.debugLineNum = 2031616;BA.debugLine="Sub showedit";
RDebugUtils.currentLine=2031617;
 //BA.debugLineNum = 2031617;BA.debugLine="pnlmain.RemoveAllViews";
mostCurrent._pnlmain.RemoveAllViews();
RDebugUtils.currentLine=2031618;
 //BA.debugLineNum = 2031618;BA.debugLine="pnlmain.LoadLayout(\"edit\")";
mostCurrent._pnlmain.LoadLayout("edit",mostCurrent.activityBA);
RDebugUtils.currentLine=2031619;
 //BA.debugLineNum = 2031619;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
RDebugUtils.currentModule="main";
RDebugUtils.currentLine=262144;
 //BA.debugLineNum = 262144;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
RDebugUtils.currentLine=262145;
 //BA.debugLineNum = 262145;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_resume", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_resume", null));}
RDebugUtils.currentLine=196608;
 //BA.debugLineNum = 196608;BA.debugLine="Sub Activity_Resume";
RDebugUtils.currentLine=196609;
 //BA.debugLineNum = 196609;BA.debugLine="End Sub";
return "";
}
public static String  _add_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "add_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "add_click", null));}
RDebugUtils.currentLine=4390912;
 //BA.debugLineNum = 4390912;BA.debugLine="Private Sub add_Click";
RDebugUtils.currentLine=4390913;
 //BA.debugLineNum = 4390913;BA.debugLine="editRowIndex = -1";
_editrowindex = (int) (-1);
RDebugUtils.currentLine=4390914;
 //BA.debugLineNum = 4390914;BA.debugLine="todelete = False";
_todelete = anywheresoftware.b4a.keywords.Common.False;
RDebugUtils.currentLine=4390915;
 //BA.debugLineNum = 4390915;BA.debugLine="pnlmain.RemoveAllViews";
mostCurrent._pnlmain.RemoveAllViews();
RDebugUtils.currentLine=4390916;
 //BA.debugLineNum = 4390916;BA.debugLine="pnlmain.LoadLayout(\"CREAT\")";
mostCurrent._pnlmain.LoadLayout("CREAT",mostCurrent.activityBA);
RDebugUtils.currentLine=4390917;
 //BA.debugLineNum = 4390917;BA.debugLine="loadspinner";
_loadspinner();
RDebugUtils.currentLine=4390920;
 //BA.debugLineNum = 4390920;BA.debugLine="STUDENT_NAME.Text = \"\"";
mostCurrent._student_name.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=4390921;
 //BA.debugLineNum = 4390921;BA.debugLine="STUDENT_ID.Text = \"\"";
mostCurrent._student_id.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=4390922;
 //BA.debugLineNum = 4390922;BA.debugLine="EditText1.Text = \"\"";
mostCurrent._edittext1.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=4390923;
 //BA.debugLineNum = 4390923;BA.debugLine="EditText2.Text = \"\"";
mostCurrent._edittext2.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=4390924;
 //BA.debugLineNum = 4390924;BA.debugLine="CheckBox1.Checked = True";
mostCurrent._checkbox1.setChecked(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=4390925;
 //BA.debugLineNum = 4390925;BA.debugLine="CheckBox2.Checked = False";
mostCurrent._checkbox2.setChecked(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=4390926;
 //BA.debugLineNum = 4390926;BA.debugLine="CheckBox3.Checked = False";
mostCurrent._checkbox3.setChecked(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=4390927;
 //BA.debugLineNum = 4390927;BA.debugLine="End Sub";
return "";
}
public static String  _loadspinner() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "loadspinner", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "loadspinner", null));}
anywheresoftware.b4a.objects.collections.List _unique_subjects = null;
int _i = 0;
anywheresoftware.b4a.objects.collections.List _unique_activities = null;
RDebugUtils.currentLine=10616832;
 //BA.debugLineNum = 10616832;BA.debugLine="Sub loadspinner";
RDebugUtils.currentLine=10616833;
 //BA.debugLineNum = 10616833;BA.debugLine="If student_subjects.IsInitialized = False Then Re";
if (mostCurrent._student_subjects.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
if (true) return "";};
RDebugUtils.currentLine=10616834;
 //BA.debugLineNum = 10616834;BA.debugLine="SELECTSUBJECTS.Clear";
mostCurrent._selectsubjects.Clear();
RDebugUtils.currentLine=10616835;
 //BA.debugLineNum = 10616835;BA.debugLine="Dim unique_subjects As List = GetUnique(student_s";
_unique_subjects = new anywheresoftware.b4a.objects.collections.List();
_unique_subjects = _getunique(mostCurrent._student_subjects);
RDebugUtils.currentLine=10616836;
 //BA.debugLineNum = 10616836;BA.debugLine="For i = 0 To unique_subjects.Size - 1";
{
final int step4 = 1;
final int limit4 = (int) (_unique_subjects.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit4 ;_i = _i + step4 ) {
RDebugUtils.currentLine=10616837;
 //BA.debugLineNum = 10616837;BA.debugLine="SELECTSUBJECTS.Add(unique_subjects.Get(i))";
mostCurrent._selectsubjects.Add(BA.ObjectToString(_unique_subjects.Get(_i)));
 }
};
RDebugUtils.currentLine=10616839;
 //BA.debugLineNum = 10616839;BA.debugLine="If student_activitys.IsInitialized = False Then R";
if (mostCurrent._student_activitys.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
if (true) return "";};
RDebugUtils.currentLine=10616840;
 //BA.debugLineNum = 10616840;BA.debugLine="STUDENT_ACTIVITY.Clear";
mostCurrent._student_activity.Clear();
RDebugUtils.currentLine=10616841;
 //BA.debugLineNum = 10616841;BA.debugLine="Dim unique_activities As List = GetUnique(student";
_unique_activities = new anywheresoftware.b4a.objects.collections.List();
_unique_activities = _getunique(mostCurrent._student_activitys);
RDebugUtils.currentLine=10616842;
 //BA.debugLineNum = 10616842;BA.debugLine="For i = 0 To unique_activities.Size - 1";
{
final int step10 = 1;
final int limit10 = (int) (_unique_activities.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit10 ;_i = _i + step10 ) {
RDebugUtils.currentLine=10616843;
 //BA.debugLineNum = 10616843;BA.debugLine="STUDENT_ACTIVITY.Add(unique_activities.Get(i))";
mostCurrent._student_activity.Add(BA.ObjectToString(_unique_activities.Get(_i)));
 }
};
RDebugUtils.currentLine=10616845;
 //BA.debugLineNum = 10616845;BA.debugLine="End Sub";
return "";
}
public static String  _addtablerow(String[] _values,int _rowindex) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "addtablerow", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "addtablerow", new Object[] {_values,_rowindex}));}
int _i = 0;
anywheresoftware.b4a.objects.LabelWrapper _l = null;
b4a.example.main._rowcol _rc = null;
RDebugUtils.currentLine=2818048;
 //BA.debugLineNum = 2818048;BA.debugLine="Sub AddTableRow(Values() As String, rowIndex As In";
RDebugUtils.currentLine=2818049;
 //BA.debugLineNum = 2818049;BA.debugLine="If Values.Length <> NumberOfColumns Then";
if (_values.length!=_numberofcolumns) { 
RDebugUtils.currentLine=2818050;
 //BA.debugLineNum = 2818050;BA.debugLine="Log(\"Wrong number of values.\")";
anywheresoftware.b4a.keywords.Common.LogImpl("82818050","Wrong number of values.",0);
RDebugUtils.currentLine=2818051;
 //BA.debugLineNum = 2818051;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=2818053;
 //BA.debugLineNum = 2818053;BA.debugLine="For i = 0 To NumberOfColumns - 1";
{
final int step5 = 1;
final int limit5 = (int) (_numberofcolumns-1);
_i = (int) (0) ;
for (;_i <= limit5 ;_i = _i + step5 ) {
RDebugUtils.currentLine=2818054;
 //BA.debugLineNum = 2818054;BA.debugLine="Dim l As Label";
_l = new anywheresoftware.b4a.objects.LabelWrapper();
RDebugUtils.currentLine=2818055;
 //BA.debugLineNum = 2818055;BA.debugLine="l.Initialize(\"cell\")";
_l.Initialize(mostCurrent.activityBA,"cell");
RDebugUtils.currentLine=2818056;
 //BA.debugLineNum = 2818056;BA.debugLine="l.Text = Values(i)";
_l.setText(BA.ObjectToCharSequence(_values[_i]));
RDebugUtils.currentLine=2818057;
 //BA.debugLineNum = 2818057;BA.debugLine="l.Gravity = Alignment";
_l.setGravity(_alignment);
RDebugUtils.currentLine=2818058;
 //BA.debugLineNum = 2818058;BA.debugLine="l.TextSize = FontSize";
_l.setTextSize(_fontsize);
RDebugUtils.currentLine=2818059;
 //BA.debugLineNum = 2818059;BA.debugLine="l.TextColor = FontColor";
_l.setTextColor(_fontcolor);
RDebugUtils.currentLine=2818060;
 //BA.debugLineNum = 2818060;BA.debugLine="l.Padding = Array As Int(10dip, 0, 0, 0)";
_l.setPadding(new int[]{anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (0),(int) (0),(int) (0)});
RDebugUtils.currentLine=2818061;
 //BA.debugLineNum = 2818061;BA.debugLine="Dim rc As RowCol";
_rc = new b4a.example.main._rowcol();
RDebugUtils.currentLine=2818062;
 //BA.debugLineNum = 2818062;BA.debugLine="rc.Initialize";
_rc.Initialize();
RDebugUtils.currentLine=2818063;
 //BA.debugLineNum = 2818063;BA.debugLine="rc.Col = i";
_rc.Col /*int*/  = _i;
RDebugUtils.currentLine=2818064;
 //BA.debugLineNum = 2818064;BA.debugLine="rc.Row = rowIndex";
_rc.Row /*int*/  = _rowindex;
RDebugUtils.currentLine=2818065;
 //BA.debugLineNum = 2818065;BA.debugLine="l.Tag = rc";
_l.setTag((Object)(_rc));
RDebugUtils.currentLine=2818066;
 //BA.debugLineNum = 2818066;BA.debugLine="Table.AddView(l, ColumnWidth * i, RowHeight * ro";
mostCurrent._table.AddView((android.view.View)(_l.getObject()),(int) (_columnwidth*_i),(int) (_rowheight*_rowindex),_columnwidth,_rowheight);
 }
};
RDebugUtils.currentLine=2818068;
 //BA.debugLineNum = 2818068;BA.debugLine="End Sub";
return "";
}
public static String  _bak_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "bak_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "bak_click", null));}
RDebugUtils.currentLine=12255232;
 //BA.debugLineNum = 12255232;BA.debugLine="Private Sub bak_Click";
RDebugUtils.currentLine=12255233;
 //BA.debugLineNum = 12255233;BA.debugLine="If isselectingid Then";
if (_isselectingid) { 
RDebugUtils.currentLine=12255234;
 //BA.debugLineNum = 12255234;BA.debugLine="showselectid = False";
_showselectid = anywheresoftware.b4a.keywords.Common.False;
RDebugUtils.currentLine=12255235;
 //BA.debugLineNum = 12255235;BA.debugLine="isselectingid = False";
_isselectingid = anywheresoftware.b4a.keywords.Common.False;
RDebugUtils.currentLine=12255236;
 //BA.debugLineNum = 12255236;BA.debugLine="update.Enabled = True";
mostCurrent._update.setEnabled(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=12255237;
 //BA.debugLineNum = 12255237;BA.debugLine="showselectids";
_showselectids();
 }else {
RDebugUtils.currentLine=12255239;
 //BA.debugLineNum = 12255239;BA.debugLine="showselectids";
_showselectids();
RDebugUtils.currentLine=12255240;
 //BA.debugLineNum = 12255240;BA.debugLine="MsgboxAsync(\"run\", \"works\") ' Fixed warning #34";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("run"),BA.ObjectToCharSequence("works"),processBA);
RDebugUtils.currentLine=12255241;
 //BA.debugLineNum = 12255241;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=12255243;
 //BA.debugLineNum = 12255243;BA.debugLine="End Sub";
return "";
}
public static String  _showselectids() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "showselectids", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "showselectids", null));}
anywheresoftware.b4a.objects.PanelWrapper _pnlcard = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _cdcard = null;
int _pnlleft = 0;
int _pnltop = 0;
int _pnlwidth = 0;
int _pnlheight = 0;
anywheresoftware.b4a.objects.LabelWrapper _lbl = null;
anywheresoftware.b4a.objects.ButtonWrapper _btn = null;
RDebugUtils.currentLine=12124160;
 //BA.debugLineNum = 12124160;BA.debugLine="Sub showselectids";
RDebugUtils.currentLine=12124161;
 //BA.debugLineNum = 12124161;BA.debugLine="If showselectid Then";
if (_showselectid) { 
RDebugUtils.currentLine=12124162;
 //BA.debugLineNum = 12124162;BA.debugLine="isselectingid = True";
_isselectingid = anywheresoftware.b4a.keywords.Common.True;
RDebugUtils.currentLine=12124163;
 //BA.debugLineNum = 12124163;BA.debugLine="update.Enabled = False";
mostCurrent._update.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=12124166;
 //BA.debugLineNum = 12124166;BA.debugLine="If bak.IsInitialized Then";
if (mostCurrent._bak.IsInitialized()) { 
RDebugUtils.currentLine=12124167;
 //BA.debugLineNum = 12124167;BA.debugLine="bak.RemoveView";
mostCurrent._bak.RemoveView();
 };
RDebugUtils.currentLine=12124171;
 //BA.debugLineNum = 12124171;BA.debugLine="bak.Initialize(\"bak\")";
mostCurrent._bak.Initialize(mostCurrent.activityBA,"bak");
RDebugUtils.currentLine=12124172;
 //BA.debugLineNum = 12124172;BA.debugLine="bak.Color = 0x68000000";
mostCurrent._bak.setColor(((int)0x68000000));
RDebugUtils.currentLine=12124174;
 //BA.debugLineNum = 12124174;BA.debugLine="Dim pnlCard As Panel";
_pnlcard = new anywheresoftware.b4a.objects.PanelWrapper();
RDebugUtils.currentLine=12124175;
 //BA.debugLineNum = 12124175;BA.debugLine="pnlCard.Initialize(\"pnlCard\")";
_pnlcard.Initialize(mostCurrent.activityBA,"pnlCard");
RDebugUtils.currentLine=12124178;
 //BA.debugLineNum = 12124178;BA.debugLine="Dim cdCard As ColorDrawable";
_cdcard = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
RDebugUtils.currentLine=12124179;
 //BA.debugLineNum = 12124179;BA.debugLine="cdCard.Initialize2(0xFF2C2C2C, 40dip, 0dip, 0xFF";
_cdcard.Initialize2(((int)0xff2c2c2c),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0)),((int)0xff000000));
RDebugUtils.currentLine=12124180;
 //BA.debugLineNum = 12124180;BA.debugLine="pnlCard.Background = cdCard";
_pnlcard.setBackground((android.graphics.drawable.Drawable)(_cdcard.getObject()));
RDebugUtils.currentLine=12124181;
 //BA.debugLineNum = 12124181;BA.debugLine="pnlCard.Elevation = 4dip";
_pnlcard.setElevation((float) (anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (4))));
RDebugUtils.currentLine=12124184;
 //BA.debugLineNum = 12124184;BA.debugLine="Dim pnlLeft As Int = 30dip";
_pnlleft = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30));
RDebugUtils.currentLine=12124185;
 //BA.debugLineNum = 12124185;BA.debugLine="Dim pnlTop As Int = 220dip";
_pnltop = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (220));
RDebugUtils.currentLine=12124186;
 //BA.debugLineNum = 12124186;BA.debugLine="Dim pnlWidth As Int = 300dip";
_pnlwidth = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (300));
RDebugUtils.currentLine=12124187;
 //BA.debugLineNum = 12124187;BA.debugLine="Dim pnlHeight As Int = 280dip";
_pnlheight = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (280));
RDebugUtils.currentLine=12124190;
 //BA.debugLineNum = 12124190;BA.debugLine="Dim lbl As Label";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
RDebugUtils.currentLine=12124191;
 //BA.debugLineNum = 12124191;BA.debugLine="lbl.Initialize(\"lbl\")";
_lbl.Initialize(mostCurrent.activityBA,"lbl");
RDebugUtils.currentLine=12124192;
 //BA.debugLineNum = 12124192;BA.debugLine="lbl.Text = \"SELECT ID\"";
_lbl.setText(BA.ObjectToCharSequence("SELECT ID"));
RDebugUtils.currentLine=12124193;
 //BA.debugLineNum = 12124193;BA.debugLine="lbl.Gravity = Bit.Or(Gravity.CENTER_HORIZONTAL,";
_lbl.setGravity(anywheresoftware.b4a.keywords.Common.Bit.Or(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_HORIZONTAL,anywheresoftware.b4a.keywords.Common.Gravity.CENTER_VERTICAL));
RDebugUtils.currentLine=12124194;
 //BA.debugLineNum = 12124194;BA.debugLine="lbl.Typeface = Typeface.CreateNew(Typeface.SERIF";
_lbl.setTypeface(anywheresoftware.b4a.keywords.Common.Typeface.CreateNew(anywheresoftware.b4a.keywords.Common.Typeface.SERIF,anywheresoftware.b4a.keywords.Common.Typeface.STYLE_BOLD_ITALIC));
RDebugUtils.currentLine=12124195;
 //BA.debugLineNum = 12124195;BA.debugLine="lbl.TextSize = 20";
_lbl.setTextSize((float) (20));
RDebugUtils.currentLine=12124196;
 //BA.debugLineNum = 12124196;BA.debugLine="lbl.TextColor = Colors.White";
_lbl.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=12124197;
 //BA.debugLineNum = 12124197;BA.debugLine="pnlCard.AddView(lbl, 35dip, 30dip, 230dip, 40dip";
_pnlcard.AddView((android.view.View)(_lbl.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (35)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (230)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
RDebugUtils.currentLine=12124200;
 //BA.debugLineNum = 12124200;BA.debugLine="spinner.Initialize(\"spinner\")";
mostCurrent._spinner.Initialize(mostCurrent.activityBA,"spinner");
RDebugUtils.currentLine=12124201;
 //BA.debugLineNum = 12124201;BA.debugLine="spinner.TextColor = Colors.Black";
mostCurrent._spinner.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
RDebugUtils.currentLine=12124202;
 //BA.debugLineNum = 12124202;BA.debugLine="spinner.AddAll(GetUnique(student_ids))";
mostCurrent._spinner.AddAll(_getunique(mostCurrent._student_ids));
RDebugUtils.currentLine=12124203;
 //BA.debugLineNum = 12124203;BA.debugLine="spinner.Color = Colors.White";
mostCurrent._spinner.setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=12124204;
 //BA.debugLineNum = 12124204;BA.debugLine="pnlCard.AddView(spinner, 75dip, 115dip, 150dip,";
_pnlcard.AddView((android.view.View)(mostCurrent._spinner.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (75)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (115)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (150)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
RDebugUtils.currentLine=12124207;
 //BA.debugLineNum = 12124207;BA.debugLine="Dim btn As Button";
_btn = new anywheresoftware.b4a.objects.ButtonWrapper();
RDebugUtils.currentLine=12124208;
 //BA.debugLineNum = 12124208;BA.debugLine="btn.Initialize(\"btn\")";
_btn.Initialize(mostCurrent.activityBA,"btn");
RDebugUtils.currentLine=12124209;
 //BA.debugLineNum = 12124209;BA.debugLine="btn.Text = \"SELECT\"";
_btn.setText(BA.ObjectToCharSequence("SELECT"));
RDebugUtils.currentLine=12124210;
 //BA.debugLineNum = 12124210;BA.debugLine="btn.Gravity = Bit.Or(Gravity.CENTER_HORIZONTAL,";
_btn.setGravity(anywheresoftware.b4a.keywords.Common.Bit.Or(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_HORIZONTAL,anywheresoftware.b4a.keywords.Common.Gravity.CENTER_VERTICAL));
RDebugUtils.currentLine=12124211;
 //BA.debugLineNum = 12124211;BA.debugLine="btn.Typeface = Typeface.DEFAULT_BOLD";
_btn.setTypeface(anywheresoftware.b4a.keywords.Common.Typeface.DEFAULT_BOLD);
RDebugUtils.currentLine=12124212;
 //BA.debugLineNum = 12124212;BA.debugLine="btn.TextColor = Colors.Black";
_btn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
RDebugUtils.currentLine=12124213;
 //BA.debugLineNum = 12124213;BA.debugLine="btn.Color = Colors.White";
_btn.setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=12124214;
 //BA.debugLineNum = 12124214;BA.debugLine="pnlCard.AddView(btn, 50dip, 210dip, 200dip, 43di";
_pnlcard.AddView((android.view.View)(_btn.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (210)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (200)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (43)));
RDebugUtils.currentLine=12124217;
 //BA.debugLineNum = 12124217;BA.debugLine="bak.AddView(pnlCard, pnlLeft, pnlTop, pnlWidth,";
mostCurrent._bak.AddView((android.view.View)(_pnlcard.getObject()),_pnlleft,_pnltop,_pnlwidth,_pnlheight);
RDebugUtils.currentLine=12124220;
 //BA.debugLineNum = 12124220;BA.debugLine="Activity.AddView(bak, 0, 0, Activity.Width, Acti";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._bak.getObject()),(int) (0),(int) (0),mostCurrent._activity.getWidth(),mostCurrent._activity.getHeight());
 }else {
RDebugUtils.currentLine=12124224;
 //BA.debugLineNum = 12124224;BA.debugLine="If bak.IsInitialized Then";
if (mostCurrent._bak.IsInitialized()) { 
RDebugUtils.currentLine=12124225;
 //BA.debugLineNum = 12124225;BA.debugLine="bak.RemoveView";
mostCurrent._bak.RemoveView();
 };
 };
RDebugUtils.currentLine=12124228;
 //BA.debugLineNum = 12124228;BA.debugLine="End Sub";
return "";
}
public static String  _btn_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btn_click", null));}
String _serch_id = "";
int _i = 0;
String[] _row = null;
String _studentname = "";
String _subject = "";
String _activity11 = "";
String _attendance = "";
String _rate = "";
String[] _parts = null;
RDebugUtils.currentLine=12320768;
 //BA.debugLineNum = 12320768;BA.debugLine="Private Sub btn_Click";
RDebugUtils.currentLine=12320770;
 //BA.debugLineNum = 12320770;BA.debugLine="Dim serch_id As String = spinner.SelectedItem";
_serch_id = mostCurrent._spinner.getSelectedItem();
RDebugUtils.currentLine=12320773;
 //BA.debugLineNum = 12320773;BA.debugLine="showselectid = False";
_showselectid = anywheresoftware.b4a.keywords.Common.False;
RDebugUtils.currentLine=12320774;
 //BA.debugLineNum = 12320774;BA.debugLine="isselectingid = False";
_isselectingid = anywheresoftware.b4a.keywords.Common.False;
RDebugUtils.currentLine=12320775;
 //BA.debugLineNum = 12320775;BA.debugLine="update.Enabled = True";
mostCurrent._update.setEnabled(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=12320776;
 //BA.debugLineNum = 12320776;BA.debugLine="showselectids";
_showselectids();
RDebugUtils.currentLine=12320779;
 //BA.debugLineNum = 12320779;BA.debugLine="editRowIndex = -1";
_editrowindex = (int) (-1);
RDebugUtils.currentLine=12320780;
 //BA.debugLineNum = 12320780;BA.debugLine="For i = 0 To StudentList.Size - 1";
{
final int step7 = 1;
final int limit7 = (int) (mostCurrent._studentlist.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit7 ;_i = _i + step7 ) {
RDebugUtils.currentLine=12320781;
 //BA.debugLineNum = 12320781;BA.debugLine="Dim row() As String = StudentList.Get(i)";
_row = (String[])(mostCurrent._studentlist.Get(_i));
RDebugUtils.currentLine=12320782;
 //BA.debugLineNum = 12320782;BA.debugLine="If row(1) = serch_id Then";
if ((_row[(int) (1)]).equals(_serch_id)) { 
RDebugUtils.currentLine=12320783;
 //BA.debugLineNum = 12320783;BA.debugLine="editRowIndex = i";
_editrowindex = _i;
RDebugUtils.currentLine=12320784;
 //BA.debugLineNum = 12320784;BA.debugLine="Exit";
if (true) break;
 };
 }
};
RDebugUtils.currentLine=12320789;
 //BA.debugLineNum = 12320789;BA.debugLine="If editRowIndex > -1 Then";
if (_editrowindex>-1) { 
RDebugUtils.currentLine=12320790;
 //BA.debugLineNum = 12320790;BA.debugLine="If todelete Then";
if (_todelete) { 
RDebugUtils.currentLine=12320791;
 //BA.debugLineNum = 12320791;BA.debugLine="Dim row() As String = StudentList.Get(editRowIn";
_row = (String[])(mostCurrent._studentlist.Get(_editrowindex));
RDebugUtils.currentLine=12320792;
 //BA.debugLineNum = 12320792;BA.debugLine="Dim studentName As String = row(0)";
_studentname = _row[(int) (0)];
RDebugUtils.currentLine=12320795;
 //BA.debugLineNum = 12320795;BA.debugLine="StudentList.RemoveAt(editRowIndex)";
mostCurrent._studentlist.RemoveAt(_editrowindex);
RDebugUtils.currentLine=12320798;
 //BA.debugLineNum = 12320798;BA.debugLine="StringUtils1.SaveCSV2(File.DirInternal, \"studen";
_stringutils1.SaveCSV2(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"student.csv",BA.ObjectToChar(","),mostCurrent._studentlist,mostCurrent._csvheaders);
RDebugUtils.currentLine=12320801;
 //BA.debugLineNum = 12320801;BA.debugLine="LoadStudentData";
_loadstudentdata();
RDebugUtils.currentLine=12320803;
 //BA.debugLineNum = 12320803;BA.debugLine="xui.MsgboxAsync(\"Deleted record for \" & student";
_xui.MsgboxAsync(processBA,BA.ObjectToCharSequence("Deleted record for "+_studentname+" successfully."),BA.ObjectToCharSequence("Success"));
RDebugUtils.currentLine=12320805;
 //BA.debugLineNum = 12320805;BA.debugLine="editRowIndex = -1";
_editrowindex = (int) (-1);
RDebugUtils.currentLine=12320806;
 //BA.debugLineNum = 12320806;BA.debugLine="todelete = False";
_todelete = anywheresoftware.b4a.keywords.Common.False;
RDebugUtils.currentLine=12320807;
 //BA.debugLineNum = 12320807;BA.debugLine="Return";
if (true) return "";
 };
 };
RDebugUtils.currentLine=12320812;
 //BA.debugLineNum = 12320812;BA.debugLine="pnlmain.RemoveAllViews";
mostCurrent._pnlmain.RemoveAllViews();
RDebugUtils.currentLine=12320813;
 //BA.debugLineNum = 12320813;BA.debugLine="pnlmain.LoadLayout(\"CREAT\")";
mostCurrent._pnlmain.LoadLayout("CREAT",mostCurrent.activityBA);
RDebugUtils.currentLine=12320815;
 //BA.debugLineNum = 12320815;BA.debugLine="If editRowIndex > -1 Then";
if (_editrowindex>-1) { 
RDebugUtils.currentLine=12320816;
 //BA.debugLineNum = 12320816;BA.debugLine="Dim row() As String = StudentList.Get(editRowInd";
_row = (String[])(mostCurrent._studentlist.Get(_editrowindex));
RDebugUtils.currentLine=12320817;
 //BA.debugLineNum = 12320817;BA.debugLine="STUDENT_NAME.Text = row(0)";
mostCurrent._student_name.setText(BA.ObjectToCharSequence(_row[(int) (0)]));
RDebugUtils.currentLine=12320818;
 //BA.debugLineNum = 12320818;BA.debugLine="STUDENT_ID.Text = row(1)";
mostCurrent._student_id.setText(BA.ObjectToCharSequence(_row[(int) (1)]));
RDebugUtils.currentLine=12320821;
 //BA.debugLineNum = 12320821;BA.debugLine="loadspinner";
_loadspinner();
RDebugUtils.currentLine=12320824;
 //BA.debugLineNum = 12320824;BA.debugLine="Dim subject As String = row(2)";
_subject = _row[(int) (2)];
RDebugUtils.currentLine=12320825;
 //BA.debugLineNum = 12320825;BA.debugLine="For i = 0 To SELECTSUBJECTS.Size - 1";
{
final int step35 = 1;
final int limit35 = (int) (mostCurrent._selectsubjects.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit35 ;_i = _i + step35 ) {
RDebugUtils.currentLine=12320826;
 //BA.debugLineNum = 12320826;BA.debugLine="If SELECTSUBJECTS.GetItem(i) = subject Then";
if ((mostCurrent._selectsubjects.GetItem(_i)).equals(_subject)) { 
RDebugUtils.currentLine=12320827;
 //BA.debugLineNum = 12320827;BA.debugLine="SELECTSUBJECTS.SelectedIndex = i";
mostCurrent._selectsubjects.setSelectedIndex(_i);
RDebugUtils.currentLine=12320828;
 //BA.debugLineNum = 12320828;BA.debugLine="Exit";
if (true) break;
 };
 }
};
RDebugUtils.currentLine=12320833;
 //BA.debugLineNum = 12320833;BA.debugLine="Dim activity11 As String = row(3)";
_activity11 = _row[(int) (3)];
RDebugUtils.currentLine=12320834;
 //BA.debugLineNum = 12320834;BA.debugLine="For i = 0 To STUDENT_ACTIVITY.Size - 1";
{
final int step42 = 1;
final int limit42 = (int) (mostCurrent._student_activity.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit42 ;_i = _i + step42 ) {
RDebugUtils.currentLine=12320835;
 //BA.debugLineNum = 12320835;BA.debugLine="If STUDENT_ACTIVITY.GetItem(i) = activity11 The";
if ((mostCurrent._student_activity.GetItem(_i)).equals(_activity11)) { 
RDebugUtils.currentLine=12320836;
 //BA.debugLineNum = 12320836;BA.debugLine="STUDENT_ACTIVITY.SelectedIndex = i";
mostCurrent._student_activity.setSelectedIndex(_i);
RDebugUtils.currentLine=12320837;
 //BA.debugLineNum = 12320837;BA.debugLine="Exit";
if (true) break;
 };
 }
};
RDebugUtils.currentLine=12320842;
 //BA.debugLineNum = 12320842;BA.debugLine="Dim attendance As String = row(4)";
_attendance = _row[(int) (4)];
RDebugUtils.currentLine=12320843;
 //BA.debugLineNum = 12320843;BA.debugLine="CheckBox1.Checked = (attendance = \"present\")";
mostCurrent._checkbox1.setChecked(((_attendance).equals("present")));
RDebugUtils.currentLine=12320844;
 //BA.debugLineNum = 12320844;BA.debugLine="CheckBox2.Checked = (attendance = \"late\")";
mostCurrent._checkbox2.setChecked(((_attendance).equals("late")));
RDebugUtils.currentLine=12320845;
 //BA.debugLineNum = 12320845;BA.debugLine="CheckBox3.Checked = (attendance = \"absent\")";
mostCurrent._checkbox3.setChecked(((_attendance).equals("absent")));
RDebugUtils.currentLine=12320848;
 //BA.debugLineNum = 12320848;BA.debugLine="Dim rate As String = row(5)";
_rate = _row[(int) (5)];
RDebugUtils.currentLine=12320849;
 //BA.debugLineNum = 12320849;BA.debugLine="If rate.Contains(\"/\") Then";
if (_rate.contains("/")) { 
RDebugUtils.currentLine=12320850;
 //BA.debugLineNum = 12320850;BA.debugLine="Dim parts() As String = Regex.Split(\"/\", rate)";
_parts = anywheresoftware.b4a.keywords.Common.Regex.Split("/",_rate);
RDebugUtils.currentLine=12320851;
 //BA.debugLineNum = 12320851;BA.debugLine="If parts.Length = 2 Then";
if (_parts.length==2) { 
RDebugUtils.currentLine=12320852;
 //BA.debugLineNum = 12320852;BA.debugLine="EditText1.Text = parts(0)";
mostCurrent._edittext1.setText(BA.ObjectToCharSequence(_parts[(int) (0)]));
RDebugUtils.currentLine=12320853;
 //BA.debugLineNum = 12320853;BA.debugLine="EditText2.Text = parts(1)";
mostCurrent._edittext2.setText(BA.ObjectToCharSequence(_parts[(int) (1)]));
 }else {
RDebugUtils.currentLine=12320855;
 //BA.debugLineNum = 12320855;BA.debugLine="EditText1.Text = rate";
mostCurrent._edittext1.setText(BA.ObjectToCharSequence(_rate));
RDebugUtils.currentLine=12320856;
 //BA.debugLineNum = 12320856;BA.debugLine="EditText2.Text = \"\"";
mostCurrent._edittext2.setText(BA.ObjectToCharSequence(""));
 };
 }else {
RDebugUtils.currentLine=12320859;
 //BA.debugLineNum = 12320859;BA.debugLine="EditText1.Text = rate";
mostCurrent._edittext1.setText(BA.ObjectToCharSequence(_rate));
RDebugUtils.currentLine=12320860;
 //BA.debugLineNum = 12320860;BA.debugLine="EditText2.Text = \"\"";
mostCurrent._edittext2.setText(BA.ObjectToCharSequence(""));
 };
 };
RDebugUtils.currentLine=12320863;
 //BA.debugLineNum = 12320863;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=1703936;
 //BA.debugLineNum = 1703936;BA.debugLine="Sub showdashboard";
RDebugUtils.currentLine=1703937;
 //BA.debugLineNum = 1703937;BA.debugLine="pnlmain.RemoveAllViews";
mostCurrent._pnlmain.RemoveAllViews();
RDebugUtils.currentLine=1703938;
 //BA.debugLineNum = 1703938;BA.debugLine="pnlmain.LoadLayout(\"dashboard\")";
mostCurrent._pnlmain.LoadLayout("dashboard",mostCurrent.activityBA);
RDebugUtils.currentLine=1703939;
 //BA.debugLineNum = 1703939;BA.debugLine="generategraph";
_generategraph();
RDebugUtils.currentLine=1703940;
 //BA.debugLineNum = 1703940;BA.debugLine="End Sub";
return "";
}
public static String  _btnedit_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btnedit_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btnedit_click", null));}
RDebugUtils.currentLine=655360;
 //BA.debugLineNum = 655360;BA.debugLine="Sub btnedit_Click";
RDebugUtils.currentLine=655361;
 //BA.debugLineNum = 655361;BA.debugLine="Drawer.LeftOpen = False";
mostCurrent._drawer._setleftopen /*boolean*/ (null,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=655362;
 //BA.debugLineNum = 655362;BA.debugLine="showedit";
_showedit();
RDebugUtils.currentLine=655363;
 //BA.debugLineNum = 655363;BA.debugLine="End Sub";
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
public static String  _showgrades() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "showgrades", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "showgrades", null));}
anywheresoftware.b4a.objects.collections.List _names = null;
int _studentsize = 0;
int _totalheight = 0;
RDebugUtils.currentLine=1835008;
 //BA.debugLineNum = 1835008;BA.debugLine="Sub showgrades";
RDebugUtils.currentLine=1835009;
 //BA.debugLineNum = 1835009;BA.debugLine="pnlmain.RemoveAllViews";
mostCurrent._pnlmain.RemoveAllViews();
RDebugUtils.currentLine=1835010;
 //BA.debugLineNum = 1835010;BA.debugLine="pnlmain.LoadLayout(\"grades\")";
mostCurrent._pnlmain.LoadLayout("grades",mostCurrent.activityBA);
RDebugUtils.currentLine=1835013;
 //BA.debugLineNum = 1835013;BA.debugLine="Dim names As List = GetUniqueStudentNames";
_names = new anywheresoftware.b4a.objects.collections.List();
_names = _getuniquestudentnames();
RDebugUtils.currentLine=1835014;
 //BA.debugLineNum = 1835014;BA.debugLine="Dim studentSize As Int = names.Size";
_studentsize = _names.getSize();
RDebugUtils.currentLine=1835018;
 //BA.debugLineNum = 1835018;BA.debugLine="Dim totalHeight As Int = (studentSize * 108dip) +";
_totalheight = (int) ((_studentsize*anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (108)))+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
RDebugUtils.currentLine=1835019;
 //BA.debugLineNum = 1835019;BA.debugLine="SV1.Panel.Height = totalHeight";
mostCurrent._sv1.getPanel().setHeight(_totalheight);
RDebugUtils.currentLine=1835020;
 //BA.debugLineNum = 1835020;BA.debugLine="SV1.Panel.Width = SV1.Width";
mostCurrent._sv1.getPanel().setWidth(mostCurrent._sv1.getWidth());
RDebugUtils.currentLine=1835022;
 //BA.debugLineNum = 1835022;BA.debugLine="SV1.Panel.RemoveAllViews";
mostCurrent._sv1.getPanel().RemoveAllViews();
RDebugUtils.currentLine=1835024;
 //BA.debugLineNum = 1835024;BA.debugLine="makeshadow(studentSize, names)";
_makeshadow(_studentsize,_names);
RDebugUtils.currentLine=1835025;
 //BA.debugLineNum = 1835025;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=2621440;
 //BA.debugLineNum = 2621440;BA.debugLine="Sub showprofile";
RDebugUtils.currentLine=2621441;
 //BA.debugLineNum = 2621441;BA.debugLine="HighlightTab(profilebtn)";
_highlighttab(mostCurrent._profilebtn);
RDebugUtils.currentLine=2621442;
 //BA.debugLineNum = 2621442;BA.debugLine="ShowTable";
_showtable();
RDebugUtils.currentLine=2621443;
 //BA.debugLineNum = 2621443;BA.debugLine="End Sub";
return "";
}
public static String  _button1_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "button1_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "button1_click", null));}
RDebugUtils.currentLine=2424832;
 //BA.debugLineNum = 2424832;BA.debugLine="Private Sub Button1_Click";
RDebugUtils.currentLine=2424834;
 //BA.debugLineNum = 2424834;BA.debugLine="showedit";
_showedit();
RDebugUtils.currentLine=2424835;
 //BA.debugLineNum = 2424835;BA.debugLine="End Sub";
return "";
}
public static double  _calculatestudentgpa(String _studentname) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "calculatestudentgpa", false))
	 {return ((Double) Debug.delegate(mostCurrent.activityBA, "calculatestudentgpa", new Object[] {_studentname}));}
double _totalpoints = 0;
int _activitycount = 0;
int _i = 0;
String[] _row = null;
String _currentname = "";
String _ratestring = "";
double _pct = 0;
RDebugUtils.currentLine=12517376;
 //BA.debugLineNum = 12517376;BA.debugLine="Sub CalculateStudentGPA(studentName As String) As";
RDebugUtils.currentLine=12517377;
 //BA.debugLineNum = 12517377;BA.debugLine="Dim totalPoints As Double = 0";
_totalpoints = 0;
RDebugUtils.currentLine=12517378;
 //BA.debugLineNum = 12517378;BA.debugLine="Dim activityCount As Int = 0";
_activitycount = (int) (0);
RDebugUtils.currentLine=12517381;
 //BA.debugLineNum = 12517381;BA.debugLine="For i = 0 To StudentList.Size - 1";
{
final int step3 = 1;
final int limit3 = (int) (mostCurrent._studentlist.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit3 ;_i = _i + step3 ) {
RDebugUtils.currentLine=12517382;
 //BA.debugLineNum = 12517382;BA.debugLine="Dim row() As String = StudentList.Get(i)";
_row = (String[])(mostCurrent._studentlist.Get(_i));
RDebugUtils.currentLine=12517383;
 //BA.debugLineNum = 12517383;BA.debugLine="Dim currentName As String = row(COL_NAME)";
_currentname = _row[_col_name];
RDebugUtils.currentLine=12517386;
 //BA.debugLineNum = 12517386;BA.debugLine="If currentName = studentName Then";
if ((_currentname).equals(_studentname)) { 
RDebugUtils.currentLine=12517387;
 //BA.debugLineNum = 12517387;BA.debugLine="Dim rateString As String = row(COL_RATE)";
_ratestring = _row[_col_rate];
RDebugUtils.currentLine=12517390;
 //BA.debugLineNum = 12517390;BA.debugLine="Dim pct As Double = ComputePercentage(rateStrin";
_pct = _computepercentage(_ratestring);
RDebugUtils.currentLine=12517393;
 //BA.debugLineNum = 12517393;BA.debugLine="totalPoints = totalPoints + PercentageToGrade(p";
_totalpoints = _totalpoints+(double)(Double.parseDouble(_percentagetograde(_pct)));
RDebugUtils.currentLine=12517394;
 //BA.debugLineNum = 12517394;BA.debugLine="activityCount = activityCount + 1";
_activitycount = (int) (_activitycount+1);
 };
 }
};
RDebugUtils.currentLine=12517399;
 //BA.debugLineNum = 12517399;BA.debugLine="If activityCount < 3 Then";
if (_activitycount<3) { 
RDebugUtils.currentLine=12517400;
 //BA.debugLineNum = 12517400;BA.debugLine="Return -1.0 ' Return -1.0 as a flag meaning \"Not";
if (true) return -1.0;
 };
RDebugUtils.currentLine=12517404;
 //BA.debugLineNum = 12517404;BA.debugLine="Return totalPoints / activityCount";
if (true) return _totalpoints/(double)_activitycount;
RDebugUtils.currentLine=12517405;
 //BA.debugLineNum = 12517405;BA.debugLine="End Sub";
return 0;
}
public static double  _computepercentage(String _rate) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "computepercentage", false))
	 {return ((Double) Debug.delegate(mostCurrent.activityBA, "computepercentage", new Object[] {_rate}));}
String[] _parts = null;
double _numerator = 0;
double _denominator = 0;
RDebugUtils.currentLine=1245184;
 //BA.debugLineNum = 1245184;BA.debugLine="Sub ComputePercentage(rate As String) As Double";
RDebugUtils.currentLine=1245185;
 //BA.debugLineNum = 1245185;BA.debugLine="If rate.Contains(\"/\") = False Then Return 0";
if (_rate.contains("/")==anywheresoftware.b4a.keywords.Common.False) { 
if (true) return 0;};
RDebugUtils.currentLine=1245186;
 //BA.debugLineNum = 1245186;BA.debugLine="Dim parts() As String = Regex.Split(\"/\", rate)";
_parts = anywheresoftware.b4a.keywords.Common.Regex.Split("/",_rate);
RDebugUtils.currentLine=1245187;
 //BA.debugLineNum = 1245187;BA.debugLine="If parts.Length <> 2 Then Return 0";
if (_parts.length!=2) { 
if (true) return 0;};
RDebugUtils.currentLine=1245188;
 //BA.debugLineNum = 1245188;BA.debugLine="Dim numerator As Double = parts(0)";
_numerator = (double)(Double.parseDouble(_parts[(int) (0)]));
RDebugUtils.currentLine=1245189;
 //BA.debugLineNum = 1245189;BA.debugLine="Dim denominator As Double = parts(1)";
_denominator = (double)(Double.parseDouble(_parts[(int) (1)]));
RDebugUtils.currentLine=1245190;
 //BA.debugLineNum = 1245190;BA.debugLine="If denominator = 0 Then Return 0";
if (_denominator==0) { 
if (true) return 0;};
RDebugUtils.currentLine=1245191;
 //BA.debugLineNum = 1245191;BA.debugLine="Return (numerator / denominator) * 100";
if (true) return (_numerator/(double)_denominator)*100;
RDebugUtils.currentLine=1245192;
 //BA.debugLineNum = 1245192;BA.debugLine="End Sub";
return 0;
}
public static String  _percentagetograde(double _pct) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "percentagetograde", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "percentagetograde", new Object[] {_pct}));}
RDebugUtils.currentLine=1310720;
 //BA.debugLineNum = 1310720;BA.debugLine="Sub PercentageToGrade(pct As Double) As String";
RDebugUtils.currentLine=1310721;
 //BA.debugLineNum = 1310721;BA.debugLine="If pct >= 90 Then Return \"A\"";
if (_pct>=90) { 
if (true) return "A";};
RDebugUtils.currentLine=1310722;
 //BA.debugLineNum = 1310722;BA.debugLine="If pct >= 80 Then Return \"B\"";
if (_pct>=80) { 
if (true) return "B";};
RDebugUtils.currentLine=1310723;
 //BA.debugLineNum = 1310723;BA.debugLine="If pct >= 70 Then Return \"C\"";
if (_pct>=70) { 
if (true) return "C";};
RDebugUtils.currentLine=1310724;
 //BA.debugLineNum = 1310724;BA.debugLine="If pct >= 60 Then Return \"D\"";
if (_pct>=60) { 
if (true) return "D";};
RDebugUtils.currentLine=1310725;
 //BA.debugLineNum = 1310725;BA.debugLine="Return \"F\"";
if (true) return "F";
RDebugUtils.currentLine=1310726;
 //BA.debugLineNum = 1310726;BA.debugLine="End Sub";
return "";
}
public static String  _checkbox1_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "checkbox1_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "checkbox1_click", null));}
RDebugUtils.currentLine=10682368;
 //BA.debugLineNum = 10682368;BA.debugLine="Private Sub CheckBox1_Click";
RDebugUtils.currentLine=10682369;
 //BA.debugLineNum = 10682369;BA.debugLine="If CheckBox1.Checked Then";
if (mostCurrent._checkbox1.getChecked()) { 
RDebugUtils.currentLine=10682370;
 //BA.debugLineNum = 10682370;BA.debugLine="CheckBox2.Checked = False";
mostCurrent._checkbox2.setChecked(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=10682371;
 //BA.debugLineNum = 10682371;BA.debugLine="CheckBox3.Checked = False";
mostCurrent._checkbox3.setChecked(anywheresoftware.b4a.keywords.Common.False);
 };
RDebugUtils.currentLine=10682373;
 //BA.debugLineNum = 10682373;BA.debugLine="End Sub";
return "";
}
public static String  _checkbox2_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "checkbox2_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "checkbox2_click", null));}
RDebugUtils.currentLine=10747904;
 //BA.debugLineNum = 10747904;BA.debugLine="Private Sub CheckBox2_Click";
RDebugUtils.currentLine=10747905;
 //BA.debugLineNum = 10747905;BA.debugLine="If CheckBox2.Checked Then";
if (mostCurrent._checkbox2.getChecked()) { 
RDebugUtils.currentLine=10747906;
 //BA.debugLineNum = 10747906;BA.debugLine="CheckBox1.Checked = False";
mostCurrent._checkbox1.setChecked(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=10747907;
 //BA.debugLineNum = 10747907;BA.debugLine="CheckBox3.Checked = False";
mostCurrent._checkbox3.setChecked(anywheresoftware.b4a.keywords.Common.False);
 };
RDebugUtils.currentLine=10747909;
 //BA.debugLineNum = 10747909;BA.debugLine="End Sub";
return "";
}
public static String  _checkbox3_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "checkbox3_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "checkbox3_click", null));}
RDebugUtils.currentLine=10813440;
 //BA.debugLineNum = 10813440;BA.debugLine="Private Sub CheckBox3_Click";
RDebugUtils.currentLine=10813441;
 //BA.debugLineNum = 10813441;BA.debugLine="If CheckBox3.Checked Then";
if (mostCurrent._checkbox3.getChecked()) { 
RDebugUtils.currentLine=10813442;
 //BA.debugLineNum = 10813442;BA.debugLine="CheckBox1.Checked = False";
mostCurrent._checkbox1.setChecked(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=10813443;
 //BA.debugLineNum = 10813443;BA.debugLine="CheckBox2.Checked = False";
mostCurrent._checkbox2.setChecked(anywheresoftware.b4a.keywords.Common.False);
 };
RDebugUtils.currentLine=10813445;
 //BA.debugLineNum = 10813445;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=786432;
 //BA.debugLineNum = 786432;BA.debugLine="Private Sub dashbtn_Click";
RDebugUtils.currentLine=786433;
 //BA.debugLineNum = 786433;BA.debugLine="HighlightTab(dashbtn)";
_highlighttab(mostCurrent._dashbtn);
RDebugUtils.currentLine=786434;
 //BA.debugLineNum = 786434;BA.debugLine="showdashboard";
_showdashboard();
RDebugUtils.currentLine=786435;
 //BA.debugLineNum = 786435;BA.debugLine="End Sub";
return "";
}
public static String  _highlighttab(anywheresoftware.b4a.objects.PanelWrapper _activebtn) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "highlighttab", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "highlighttab", new Object[] {_activebtn}));}
RDebugUtils.currentLine=1048576;
 //BA.debugLineNum = 1048576;BA.debugLine="Sub HighlightTab(activeBtn As Panel)";
RDebugUtils.currentLine=1048577;
 //BA.debugLineNum = 1048577;BA.debugLine="dashbtn.Color = Colors.White";
mostCurrent._dashbtn.setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=1048578;
 //BA.debugLineNum = 1048578;BA.debugLine="grdbtn.Color = Colors.White";
mostCurrent._grdbtn.setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=1048579;
 //BA.debugLineNum = 1048579;BA.debugLine="editbtn.Color = Colors.White";
mostCurrent._editbtn.setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=1048580;
 //BA.debugLineNum = 1048580;BA.debugLine="profilebtn.Color = Colors.White";
mostCurrent._profilebtn.setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=1048581;
 //BA.debugLineNum = 1048581;BA.debugLine="activeBtn.Color = 0xFF1AEA00";
_activebtn.setColor(((int)0xff1aea00));
RDebugUtils.currentLine=1048582;
 //BA.debugLineNum = 1048582;BA.debugLine="End Sub";
return "";
}
public static String  _delete_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "delete_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "delete_click", null));}
RDebugUtils.currentLine=4325376;
 //BA.debugLineNum = 4325376;BA.debugLine="Private Sub delete_Click";
RDebugUtils.currentLine=4325377;
 //BA.debugLineNum = 4325377;BA.debugLine="showselectid = True";
_showselectid = anywheresoftware.b4a.keywords.Common.True;
RDebugUtils.currentLine=4325378;
 //BA.debugLineNum = 4325378;BA.debugLine="todelete = True";
_todelete = anywheresoftware.b4a.keywords.Common.True;
RDebugUtils.currentLine=4325379;
 //BA.debugLineNum = 4325379;BA.debugLine="showselectids";
_showselectids();
RDebugUtils.currentLine=4325380;
 //BA.debugLineNum = 4325380;BA.debugLine="End Sub";
return "";
}
public static String  _editbtn_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "editbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "editbtn_click", null));}
RDebugUtils.currentLine=917504;
 //BA.debugLineNum = 917504;BA.debugLine="Private Sub editbtn_Click";
RDebugUtils.currentLine=917505;
 //BA.debugLineNum = 917505;BA.debugLine="HighlightTab(editbtn)";
_highlighttab(mostCurrent._editbtn);
RDebugUtils.currentLine=917506;
 //BA.debugLineNum = 917506;BA.debugLine="showedit";
_showedit();
RDebugUtils.currentLine=917507;
 //BA.debugLineNum = 917507;BA.debugLine="End Sub";
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
anywheresoftware.b4a.objects.collections.List _studentavgs = null;
String[] _topnames = null;
double[] _toprates = null;
String[] _topactivities = null;
int _rank = 0;
int _bestidx = 0;
double _bestval = 0;
int _j = 0;
Object[] _entry = null;
Object[] _bestentry = null;
RDebugUtils.currentLine=1769472;
 //BA.debugLineNum = 1769472;BA.debugLine="Sub generategraph";
RDebugUtils.currentLine=1769473;
 //BA.debugLineNum = 1769473;BA.debugLine="Dim names As List = GetUniqueStudentNames";
_names = new anywheresoftware.b4a.objects.collections.List();
_names = _getuniquestudentnames();
RDebugUtils.currentLine=1769474;
 //BA.debugLineNum = 1769474;BA.debugLine="If names.Size = 0 Then Return";
if (_names.getSize()==0) { 
if (true) return "";};
RDebugUtils.currentLine=1769476;
 //BA.debugLineNum = 1769476;BA.debugLine="Dim numCols As Int = names.Size";
_numcols = _names.getSize();
RDebugUtils.currentLine=1769477;
 //BA.debugLineNum = 1769477;BA.debugLine="Dim colWidth As Int = datapnl.Width / numCols";
_colwidth = (int) (mostCurrent._datapnl.getWidth()/(double)_numcols);
RDebugUtils.currentLine=1769480;
 //BA.debugLineNum = 1769480;BA.debugLine="Dim barColors() As Int = Array As Int( _ 		Colors";
_barcolors = new int[]{anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (66),(int) (133),(int) (244)),anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (234),(int) (67),(int) (53)),anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (251),(int) (188),(int) (4)),anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (52),(int) (168),(int) (83)),anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (156),(int) (39),(int) (176))};
RDebugUtils.currentLine=1769488;
 //BA.debugLineNum = 1769488;BA.debugLine="Dim maxVal As Double = 0";
_maxval = 0;
RDebugUtils.currentLine=1769489;
 //BA.debugLineNum = 1769489;BA.debugLine="For i = 0 To names.Size - 1";
{
final int step7 = 1;
final int limit7 = (int) (_names.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit7 ;_i = _i + step7 ) {
RDebugUtils.currentLine=1769490;
 //BA.debugLineNum = 1769490;BA.debugLine="Dim avg As Double = GetStudentAvgPercentage(name";
_avg = _getstudentavgpercentage(BA.ObjectToString(_names.Get(_i)));
RDebugUtils.currentLine=1769491;
 //BA.debugLineNum = 1769491;BA.debugLine="If avg > maxVal Then maxVal = avg";
if (_avg>_maxval) { 
_maxval = _avg;};
 }
};
RDebugUtils.currentLine=1769493;
 //BA.debugLineNum = 1769493;BA.debugLine="If maxVal = 0 Then maxVal = 100";
if (_maxval==0) { 
_maxval = 100;};
RDebugUtils.currentLine=1769495;
 //BA.debugLineNum = 1769495;BA.debugLine="Dim RowHeight As Int = 40dip";
_rowheight = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40));
RDebugUtils.currentLine=1769496;
 //BA.debugLineNum = 1769496;BA.debugLine="Dim valueLabelHeight As Int = 30dip";
_valuelabelheight = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30));
RDebugUtils.currentLine=1769497;
 //BA.debugLineNum = 1769497;BA.debugLine="Dim chartAreaHeight As Int = datapnl.Height - Row";
_chartareaheight = (int) (mostCurrent._datapnl.getHeight()-_rowheight-_valuelabelheight);
RDebugUtils.currentLine=1769498;
 //BA.debugLineNum = 1769498;BA.debugLine="Dim barPadding As Int = 10dip";
_barpadding = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10));
RDebugUtils.currentLine=1769500;
 //BA.debugLineNum = 1769500;BA.debugLine="For i = 0 To numCols - 1";
{
final int step16 = 1;
final int limit16 = (int) (_numcols-1);
_i = (int) (0) ;
for (;_i <= limit16 ;_i = _i + step16 ) {
RDebugUtils.currentLine=1769501;
 //BA.debugLineNum = 1769501;BA.debugLine="Dim studentName As String = names.Get(i)";
_studentname = BA.ObjectToString(_names.Get(_i));
RDebugUtils.currentLine=1769502;
 //BA.debugLineNum = 1769502;BA.debugLine="Dim avgPct As Double = GetStudentAvgPercentage(s";
_avgpct = _getstudentavgpercentage(_studentname);
RDebugUtils.currentLine=1769505;
 //BA.debugLineNum = 1769505;BA.debugLine="Dim barHeight As Int";
_barheight = 0;
RDebugUtils.currentLine=1769506;
 //BA.debugLineNum = 1769506;BA.debugLine="If maxVal > 0 Then";
if (_maxval>0) { 
RDebugUtils.currentLine=1769507;
 //BA.debugLineNum = 1769507;BA.debugLine="barHeight = (avgPct / maxVal) * chartAreaHeight";
_barheight = (int) ((_avgpct/(double)_maxval)*_chartareaheight);
 }else {
RDebugUtils.currentLine=1769509;
 //BA.debugLineNum = 1769509;BA.debugLine="barHeight = 0";
_barheight = (int) (0);
 };
RDebugUtils.currentLine=1769512;
 //BA.debugLineNum = 1769512;BA.debugLine="Dim barTop As Int = datapnl.Height - RowHeight -";
_bartop = (int) (mostCurrent._datapnl.getHeight()-_rowheight-_barheight);
RDebugUtils.currentLine=1769514;
 //BA.debugLineNum = 1769514;BA.debugLine="Dim pnlBar As Panel";
_pnlbar = new anywheresoftware.b4a.objects.PanelWrapper();
RDebugUtils.currentLine=1769515;
 //BA.debugLineNum = 1769515;BA.debugLine="pnlBar.Initialize(\"pnlBar\")";
_pnlbar.Initialize(mostCurrent.activityBA,"pnlBar");
RDebugUtils.currentLine=1769516;
 //BA.debugLineNum = 1769516;BA.debugLine="pnlBar.Color = barColors(i Mod barColors.Length)";
_pnlbar.setColor(_barcolors[(int) (_i%_barcolors.length)]);
RDebugUtils.currentLine=1769517;
 //BA.debugLineNum = 1769517;BA.debugLine="pnlBar.Tag = i";
_pnlbar.setTag((Object)(_i));
RDebugUtils.currentLine=1769518;
 //BA.debugLineNum = 1769518;BA.debugLine="datapnl.AddView(pnlBar, _ 			(colWidth * i) + ba";
mostCurrent._datapnl.AddView((android.view.View)(_pnlbar.getObject()),(int) ((_colwidth*_i)+_barpadding),_bartop,(int) (_colwidth-(_barpadding*2)),_barheight);
RDebugUtils.currentLine=1769525;
 //BA.debugLineNum = 1769525;BA.debugLine="Dim lblValue As Label";
_lblvalue = new anywheresoftware.b4a.objects.LabelWrapper();
RDebugUtils.currentLine=1769526;
 //BA.debugLineNum = 1769526;BA.debugLine="lblValue.Initialize(\"lblValue\")";
_lblvalue.Initialize(mostCurrent.activityBA,"lblValue");
RDebugUtils.currentLine=1769527;
 //BA.debugLineNum = 1769527;BA.debugLine="lblValue.Text = Round2(avgPct, 0) & \"%\"";
_lblvalue.setText(BA.ObjectToCharSequence(BA.NumberToString(anywheresoftware.b4a.keywords.Common.Round2(_avgpct,(int) (0)))+"%"));
RDebugUtils.currentLine=1769528;
 //BA.debugLineNum = 1769528;BA.debugLine="lblValue.Gravity = Gravity.CENTER";
_lblvalue.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
RDebugUtils.currentLine=1769529;
 //BA.debugLineNum = 1769529;BA.debugLine="lblValue.TextSize = 12";
_lblvalue.setTextSize((float) (12));
RDebugUtils.currentLine=1769530;
 //BA.debugLineNum = 1769530;BA.debugLine="lblValue.TextColor = Colors.Black";
_lblvalue.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
RDebugUtils.currentLine=1769531;
 //BA.debugLineNum = 1769531;BA.debugLine="datapnl.AddView(lblValue, _ 			colWidth * i, _";
mostCurrent._datapnl.AddView((android.view.View)(_lblvalue.getObject()),(int) (_colwidth*_i),(int) (_bartop-_valuelabelheight),_colwidth,_valuelabelheight);
RDebugUtils.currentLine=1769538;
 //BA.debugLineNum = 1769538;BA.debugLine="Dim l As Label";
_l = new anywheresoftware.b4a.objects.LabelWrapper();
RDebugUtils.currentLine=1769539;
 //BA.debugLineNum = 1769539;BA.debugLine="l.Initialize(\"labels\")";
_l.Initialize(mostCurrent.activityBA,"labels");
RDebugUtils.currentLine=1769541;
 //BA.debugLineNum = 1769541;BA.debugLine="Dim displayName As String = studentName";
_displayname = _studentname;
RDebugUtils.currentLine=1769542;
 //BA.debugLineNum = 1769542;BA.debugLine="If displayName.Length > 8 Then";
if (_displayname.length()>8) { 
RDebugUtils.currentLine=1769543;
 //BA.debugLineNum = 1769543;BA.debugLine="Dim nameParts() As String = Regex.Split(\" \", di";
_nameparts = anywheresoftware.b4a.keywords.Common.Regex.Split(" ",_displayname);
RDebugUtils.currentLine=1769544;
 //BA.debugLineNum = 1769544;BA.debugLine="displayName = nameParts(0)";
_displayname = _nameparts[(int) (0)];
 };
RDebugUtils.currentLine=1769546;
 //BA.debugLineNum = 1769546;BA.debugLine="l.Text = displayName";
_l.setText(BA.ObjectToCharSequence(_displayname));
RDebugUtils.currentLine=1769547;
 //BA.debugLineNum = 1769547;BA.debugLine="l.Gravity = Gravity.CENTER";
_l.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
RDebugUtils.currentLine=1769548;
 //BA.debugLineNum = 1769548;BA.debugLine="l.TextSize = 10";
_l.setTextSize((float) (10));
RDebugUtils.currentLine=1769549;
 //BA.debugLineNum = 1769549;BA.debugLine="l.Color = 0x00ffffff";
_l.setColor(((int)0x00ffffff));
RDebugUtils.currentLine=1769550;
 //BA.debugLineNum = 1769550;BA.debugLine="l.TextColor = Colors.Black";
_l.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
RDebugUtils.currentLine=1769551;
 //BA.debugLineNum = 1769551;BA.debugLine="l.Padding = Array As Int(0dip, 5dip, 0dip, 5dip)";
_l.setPadding(new int[]{anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5))});
RDebugUtils.currentLine=1769552;
 //BA.debugLineNum = 1769552;BA.debugLine="l.Tag = i";
_l.setTag((Object)(_i));
RDebugUtils.currentLine=1769553;
 //BA.debugLineNum = 1769553;BA.debugLine="datapnl.AddView(l, _ 			colWidth * i, _ 			datap";
mostCurrent._datapnl.AddView((android.view.View)(_l.getObject()),(int) (_colwidth*_i),(int) (mostCurrent._datapnl.getHeight()-_rowheight),_colwidth,_rowheight);
 }
};
RDebugUtils.currentLine=1769561;
 //BA.debugLineNum = 1769561;BA.debugLine="Dim studentAvgs As List";
_studentavgs = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=1769562;
 //BA.debugLineNum = 1769562;BA.debugLine="studentAvgs.Initialize";
_studentavgs.Initialize();
RDebugUtils.currentLine=1769563;
 //BA.debugLineNum = 1769563;BA.debugLine="For i = 0 To names.Size - 1";
{
final int step56 = 1;
final int limit56 = (int) (_names.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit56 ;_i = _i + step56 ) {
RDebugUtils.currentLine=1769564;
 //BA.debugLineNum = 1769564;BA.debugLine="Dim avg As Double = GetStudentAvgPercentage(name";
_avg = _getstudentavgpercentage(BA.ObjectToString(_names.Get(_i)));
RDebugUtils.currentLine=1769565;
 //BA.debugLineNum = 1769565;BA.debugLine="studentAvgs.Add(Array As Object(names.Get(i), av";
_studentavgs.Add((Object)(new Object[]{_names.Get(_i),(Object)(_avg)}));
 }
};
RDebugUtils.currentLine=1769569;
 //BA.debugLineNum = 1769569;BA.debugLine="Dim topNames(3) As String";
_topnames = new String[(int) (3)];
java.util.Arrays.fill(_topnames,"");
RDebugUtils.currentLine=1769570;
 //BA.debugLineNum = 1769570;BA.debugLine="Dim topRates(3) As Double";
_toprates = new double[(int) (3)];
;
RDebugUtils.currentLine=1769571;
 //BA.debugLineNum = 1769571;BA.debugLine="Dim topActivities(3) As String";
_topactivities = new String[(int) (3)];
java.util.Arrays.fill(_topactivities,"");
RDebugUtils.currentLine=1769573;
 //BA.debugLineNum = 1769573;BA.debugLine="For rank = 0 To Min(2, studentAvgs.Size - 1)";
{
final int step63 = 1;
final int limit63 = (int) (anywheresoftware.b4a.keywords.Common.Min(2,_studentavgs.getSize()-1));
_rank = (int) (0) ;
for (;_rank <= limit63 ;_rank = _rank + step63 ) {
RDebugUtils.currentLine=1769574;
 //BA.debugLineNum = 1769574;BA.debugLine="Dim bestIdx As Int = -1";
_bestidx = (int) (-1);
RDebugUtils.currentLine=1769575;
 //BA.debugLineNum = 1769575;BA.debugLine="Dim bestVal As Double = -1";
_bestval = -1;
RDebugUtils.currentLine=1769576;
 //BA.debugLineNum = 1769576;BA.debugLine="For j = 0 To studentAvgs.Size - 1";
{
final int step66 = 1;
final int limit66 = (int) (_studentavgs.getSize()-1);
_j = (int) (0) ;
for (;_j <= limit66 ;_j = _j + step66 ) {
RDebugUtils.currentLine=1769577;
 //BA.debugLineNum = 1769577;BA.debugLine="Dim entry() As Object = studentAvgs.Get(j)";
_entry = (Object[])(_studentavgs.Get(_j));
RDebugUtils.currentLine=1769578;
 //BA.debugLineNum = 1769578;BA.debugLine="If entry(1) > bestVal Then";
if ((double)(BA.ObjectToNumber(_entry[(int) (1)]))>_bestval) { 
RDebugUtils.currentLine=1769579;
 //BA.debugLineNum = 1769579;BA.debugLine="bestVal = entry(1)";
_bestval = (double)(BA.ObjectToNumber(_entry[(int) (1)]));
RDebugUtils.currentLine=1769580;
 //BA.debugLineNum = 1769580;BA.debugLine="bestIdx = j";
_bestidx = _j;
 };
 }
};
RDebugUtils.currentLine=1769583;
 //BA.debugLineNum = 1769583;BA.debugLine="If bestIdx >= 0 Then";
if (_bestidx>=0) { 
RDebugUtils.currentLine=1769584;
 //BA.debugLineNum = 1769584;BA.debugLine="Dim bestEntry() As Object = studentAvgs.Get(bes";
_bestentry = (Object[])(_studentavgs.Get(_bestidx));
RDebugUtils.currentLine=1769585;
 //BA.debugLineNum = 1769585;BA.debugLine="topNames(rank) = bestEntry(0)";
_topnames[_rank] = BA.ObjectToString(_bestentry[(int) (0)]);
RDebugUtils.currentLine=1769586;
 //BA.debugLineNum = 1769586;BA.debugLine="topRates(rank) = bestEntry(1)";
_toprates[_rank] = (double)(BA.ObjectToNumber(_bestentry[(int) (1)]));
RDebugUtils.currentLine=1769587;
 //BA.debugLineNum = 1769587;BA.debugLine="topActivities(rank) = GetStudentTopActivity(bes";
_topactivities[_rank] = _getstudenttopactivity(BA.ObjectToString(_bestentry[(int) (0)]));
RDebugUtils.currentLine=1769588;
 //BA.debugLineNum = 1769588;BA.debugLine="studentAvgs.RemoveAt(bestIdx) ' remove so next";
_studentavgs.RemoveAt(_bestidx);
 };
 }
};
RDebugUtils.currentLine=1769593;
 //BA.debugLineNum = 1769593;BA.debugLine="If topNames(0) <> \"\" Then";
if ((_topnames[(int) (0)]).equals("") == false) { 
RDebugUtils.currentLine=1769594;
 //BA.debugLineNum = 1769594;BA.debugLine="NAME1.Text = topNames(0)";
mostCurrent._name1.setText(BA.ObjectToCharSequence(_topnames[(int) (0)]));
RDebugUtils.currentLine=1769595;
 //BA.debugLineNum = 1769595;BA.debugLine="RATE1.Text = Round2(topRates(0), 1) & \"%\"";
mostCurrent._rate1.setText(BA.ObjectToCharSequence(BA.NumberToString(anywheresoftware.b4a.keywords.Common.Round2(_toprates[(int) (0)],(int) (1)))+"%"));
RDebugUtils.currentLine=1769596;
 //BA.debugLineNum = 1769596;BA.debugLine="ACTIVITY1.Text = topActivities(0)";
mostCurrent._activity1.setText(BA.ObjectToCharSequence(_topactivities[(int) (0)]));
 };
RDebugUtils.currentLine=1769598;
 //BA.debugLineNum = 1769598;BA.debugLine="If topNames(1) <> \"\" Then";
if ((_topnames[(int) (1)]).equals("") == false) { 
RDebugUtils.currentLine=1769599;
 //BA.debugLineNum = 1769599;BA.debugLine="NAME2.Text = topNames(1)";
mostCurrent._name2.setText(BA.ObjectToCharSequence(_topnames[(int) (1)]));
RDebugUtils.currentLine=1769600;
 //BA.debugLineNum = 1769600;BA.debugLine="RATE2.Text = Round2(topRates(1), 1) & \"%\"";
mostCurrent._rate2.setText(BA.ObjectToCharSequence(BA.NumberToString(anywheresoftware.b4a.keywords.Common.Round2(_toprates[(int) (1)],(int) (1)))+"%"));
RDebugUtils.currentLine=1769601;
 //BA.debugLineNum = 1769601;BA.debugLine="ACTIVITY2.Text = topActivities(1)";
mostCurrent._activity2.setText(BA.ObjectToCharSequence(_topactivities[(int) (1)]));
 };
RDebugUtils.currentLine=1769603;
 //BA.debugLineNum = 1769603;BA.debugLine="If topNames(2) <> \"\" Then";
if ((_topnames[(int) (2)]).equals("") == false) { 
RDebugUtils.currentLine=1769604;
 //BA.debugLineNum = 1769604;BA.debugLine="NAME3.Text = topNames(2)";
mostCurrent._name3.setText(BA.ObjectToCharSequence(_topnames[(int) (2)]));
RDebugUtils.currentLine=1769605;
 //BA.debugLineNum = 1769605;BA.debugLine="RATE3.Text = Round2(topRates(2), 1) & \"%\"";
mostCurrent._rate3.setText(BA.ObjectToCharSequence(BA.NumberToString(anywheresoftware.b4a.keywords.Common.Round2(_toprates[(int) (2)],(int) (1)))+"%"));
RDebugUtils.currentLine=1769606;
 //BA.debugLineNum = 1769606;BA.debugLine="ACTIVITY3.Text = topActivities(2)";
mostCurrent._activity3.setText(BA.ObjectToCharSequence(_topactivities[(int) (2)]));
 };
RDebugUtils.currentLine=1769608;
 //BA.debugLineNum = 1769608;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=1441792;
 //BA.debugLineNum = 1441792;BA.debugLine="Sub GetUniqueStudentNames As List";
RDebugUtils.currentLine=1441793;
 //BA.debugLineNum = 1441793;BA.debugLine="Dim names As List";
_names = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=1441794;
 //BA.debugLineNum = 1441794;BA.debugLine="names.Initialize";
_names.Initialize();
RDebugUtils.currentLine=1441795;
 //BA.debugLineNum = 1441795;BA.debugLine="For i = 0 To StudentList.Size - 1";
{
final int step3 = 1;
final int limit3 = (int) (mostCurrent._studentlist.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit3 ;_i = _i + step3 ) {
RDebugUtils.currentLine=1441796;
 //BA.debugLineNum = 1441796;BA.debugLine="Dim row() As String = StudentList.Get(i)";
_row = (String[])(mostCurrent._studentlist.Get(_i));
RDebugUtils.currentLine=1441797;
 //BA.debugLineNum = 1441797;BA.debugLine="Dim name As String = row(COL_NAME)";
_name = _row[_col_name];
RDebugUtils.currentLine=1441798;
 //BA.debugLineNum = 1441798;BA.debugLine="If names.IndexOf(name) = -1 Then";
if (_names.IndexOf((Object)(_name))==-1) { 
RDebugUtils.currentLine=1441799;
 //BA.debugLineNum = 1441799;BA.debugLine="names.Add(name)";
_names.Add((Object)(_name));
 };
 }
};
RDebugUtils.currentLine=1441802;
 //BA.debugLineNum = 1441802;BA.debugLine="Return names";
if (true) return _names;
RDebugUtils.currentLine=1441803;
 //BA.debugLineNum = 1441803;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=1507328;
 //BA.debugLineNum = 1507328;BA.debugLine="Sub GetStudentAvgPercentage(studentname As String)";
RDebugUtils.currentLine=1507329;
 //BA.debugLineNum = 1507329;BA.debugLine="Dim total As Double = 0";
_total = 0;
RDebugUtils.currentLine=1507330;
 //BA.debugLineNum = 1507330;BA.debugLine="Dim count As Int = 0";
_count = (int) (0);
RDebugUtils.currentLine=1507331;
 //BA.debugLineNum = 1507331;BA.debugLine="For i = 0 To StudentList.Size - 1";
{
final int step3 = 1;
final int limit3 = (int) (mostCurrent._studentlist.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit3 ;_i = _i + step3 ) {
RDebugUtils.currentLine=1507332;
 //BA.debugLineNum = 1507332;BA.debugLine="Dim row() As String = StudentList.Get(i)";
_row = (String[])(mostCurrent._studentlist.Get(_i));
RDebugUtils.currentLine=1507333;
 //BA.debugLineNum = 1507333;BA.debugLine="If row(COL_NAME) = studentname Then";
if ((_row[_col_name]).equals(_studentname)) { 
RDebugUtils.currentLine=1507334;
 //BA.debugLineNum = 1507334;BA.debugLine="total = total + ComputePercentage(row(COL_RATE)";
_total = _total+_computepercentage(_row[_col_rate]);
RDebugUtils.currentLine=1507335;
 //BA.debugLineNum = 1507335;BA.debugLine="count = count + 1";
_count = (int) (_count+1);
 };
 }
};
RDebugUtils.currentLine=1507338;
 //BA.debugLineNum = 1507338;BA.debugLine="If count = 0 Then Return 0";
if (_count==0) { 
if (true) return 0;};
RDebugUtils.currentLine=1507339;
 //BA.debugLineNum = 1507339;BA.debugLine="Return total / count";
if (true) return _total/(double)_count;
RDebugUtils.currentLine=1507340;
 //BA.debugLineNum = 1507340;BA.debugLine="End Sub";
return 0;
}
public static String  _getstudenttopactivity(String _studentname) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "getstudenttopactivity", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "getstudenttopactivity", new Object[] {_studentname}));}
RDebugUtils.currentLine=4521984;
 //BA.debugLineNum = 4521984;BA.debugLine="Sub GetStudentTopActivity(studentName As String) A";
RDebugUtils.currentLine=4521988;
 //BA.debugLineNum = 4521988;BA.debugLine="Return getActivity(studentName)";
if (true) return _getactivity(_studentname);
RDebugUtils.currentLine=4521989;
 //BA.debugLineNum = 4521989;BA.debugLine="End Sub";
return "";
}
public static String  _getactivity(String _studentname) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "getactivity", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "getactivity", new Object[] {_studentname}));}
String _activities = "";
int _i = 0;
String[] _row = null;
String _name = "";
String _activitys = "";
RDebugUtils.currentLine=5308416;
 //BA.debugLineNum = 5308416;BA.debugLine="Sub getActivity(studentName As String) As String";
RDebugUtils.currentLine=5308417;
 //BA.debugLineNum = 5308417;BA.debugLine="Dim activities As String";
_activities = "";
RDebugUtils.currentLine=5308418;
 //BA.debugLineNum = 5308418;BA.debugLine="For i = 0 To StudentList.Size - 1";
{
final int step2 = 1;
final int limit2 = (int) (mostCurrent._studentlist.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit2 ;_i = _i + step2 ) {
RDebugUtils.currentLine=5308419;
 //BA.debugLineNum = 5308419;BA.debugLine="Dim row() As String = StudentList.Get(i)";
_row = (String[])(mostCurrent._studentlist.Get(_i));
RDebugUtils.currentLine=5308420;
 //BA.debugLineNum = 5308420;BA.debugLine="Dim name As String = row(COL_NAME) ' compare aga";
_name = _row[_col_name];
RDebugUtils.currentLine=5308421;
 //BA.debugLineNum = 5308421;BA.debugLine="If name = studentName Then";
if ((_name).equals(_studentname)) { 
RDebugUtils.currentLine=5308422;
 //BA.debugLineNum = 5308422;BA.debugLine="Dim Activitys As String = row(COL_ACTIVITY)";
_activitys = _row[_col_activity];
RDebugUtils.currentLine=5308423;
 //BA.debugLineNum = 5308423;BA.debugLine="If activities.IndexOf(Activitys) = -1 Then";
if (_activities.indexOf(_activitys)==-1) { 
RDebugUtils.currentLine=5308424;
 //BA.debugLineNum = 5308424;BA.debugLine="activities = Activitys";
_activities = _activitys;
 };
 };
 }
};
RDebugUtils.currentLine=5308428;
 //BA.debugLineNum = 5308428;BA.debugLine="Return activities";
if (true) return _activities;
RDebugUtils.currentLine=5308429;
 //BA.debugLineNum = 5308429;BA.debugLine="End Sub";
return "";
}
public static String  _getgpadisplaytext(String _studentname) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "getgpadisplaytext", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "getgpadisplaytext", new Object[] {_studentname}));}
double _gpa = 0;
RDebugUtils.currentLine=12582912;
 //BA.debugLineNum = 12582912;BA.debugLine="Sub GetGPADisplayText(studentName As String) As St";
RDebugUtils.currentLine=12582913;
 //BA.debugLineNum = 12582913;BA.debugLine="Dim gpa As Double = CalculateStudentGPA(studentNa";
_gpa = _calculatestudentgpa(_studentname);
RDebugUtils.currentLine=12582915;
 //BA.debugLineNum = 12582915;BA.debugLine="If gpa = -1.0 Then";
if (_gpa==-1.0) { 
RDebugUtils.currentLine=12582916;
 //BA.debugLineNum = 12582916;BA.debugLine="Return \"N/A (Min 3 activities required)\"";
if (true) return "N/A (Min 3 activities required)";
 }else {
RDebugUtils.currentLine=12582919;
 //BA.debugLineNum = 12582919;BA.debugLine="Return NumberFormat2(gpa, 1, 2, 2, False)";
if (true) return anywheresoftware.b4a.keywords.Common.NumberFormat2(_gpa,(int) (1),(int) (2),(int) (2),anywheresoftware.b4a.keywords.Common.False);
 };
RDebugUtils.currentLine=12582921;
 //BA.debugLineNum = 12582921;BA.debugLine="End Sub";
return "";
}
public static String  _getstudentgrade(String _studentname) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "getstudentgrade", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "getstudentgrade", new Object[] {_studentname}));}
RDebugUtils.currentLine=1572864;
 //BA.debugLineNum = 1572864;BA.debugLine="Sub GetStudentGrade(studentname As String) As Stri";
RDebugUtils.currentLine=1572865;
 //BA.debugLineNum = 1572865;BA.debugLine="Return PercentageToGrade(GetStudentAvgPercentage(";
if (true) return _percentagetograde(_getstudentavgpercentage(_studentname));
RDebugUtils.currentLine=1572866;
 //BA.debugLineNum = 1572866;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.collections.List  _getunique(anywheresoftware.b4a.objects.collections.List _source) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "getunique", false))
	 {return ((anywheresoftware.b4a.objects.collections.List) Debug.delegate(mostCurrent.activityBA, "getunique", new Object[] {_source}));}
anywheresoftware.b4a.objects.collections.Map _seen = null;
anywheresoftware.b4a.objects.collections.List _unique = null;
String _item = "";
int _i = 0;
RDebugUtils.currentLine=10354688;
 //BA.debugLineNum = 10354688;BA.debugLine="Sub GetUnique(source As List) As List";
RDebugUtils.currentLine=10354689;
 //BA.debugLineNum = 10354689;BA.debugLine="Dim seen As Map";
_seen = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=10354690;
 //BA.debugLineNum = 10354690;BA.debugLine="seen.Initialize";
_seen.Initialize();
RDebugUtils.currentLine=10354691;
 //BA.debugLineNum = 10354691;BA.debugLine="Dim unique As List";
_unique = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=10354692;
 //BA.debugLineNum = 10354692;BA.debugLine="unique.Initialize";
_unique.Initialize();
RDebugUtils.currentLine=10354693;
 //BA.debugLineNum = 10354693;BA.debugLine="Dim item As String        ' ✅ Declared outside th";
_item = "";
RDebugUtils.currentLine=10354694;
 //BA.debugLineNum = 10354694;BA.debugLine="For i = 0 To source.Size - 1";
{
final int step6 = 1;
final int limit6 = (int) (_source.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit6 ;_i = _i + step6 ) {
RDebugUtils.currentLine=10354695;
 //BA.debugLineNum = 10354695;BA.debugLine="item = source.Get(i)  ' ✅ Only assignment inside";
_item = BA.ObjectToString(_source.Get(_i));
RDebugUtils.currentLine=10354696;
 //BA.debugLineNum = 10354696;BA.debugLine="If seen.ContainsKey(item) = False Then";
if (_seen.ContainsKey((Object)(_item))==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=10354697;
 //BA.debugLineNum = 10354697;BA.debugLine="seen.Put(item, True)";
_seen.Put((Object)(_item),(Object)(anywheresoftware.b4a.keywords.Common.True));
RDebugUtils.currentLine=10354698;
 //BA.debugLineNum = 10354698;BA.debugLine="unique.Add(item)";
_unique.Add((Object)(_item));
 };
 }
};
RDebugUtils.currentLine=10354701;
 //BA.debugLineNum = 10354701;BA.debugLine="Return unique";
if (true) return _unique;
RDebugUtils.currentLine=10354702;
 //BA.debugLineNum = 10354702;BA.debugLine="End Sub";
return null;
}
public static String  _grdbtn_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "grdbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "grdbtn_click", null));}
RDebugUtils.currentLine=851968;
 //BA.debugLineNum = 851968;BA.debugLine="Private Sub grdbtn_Click";
RDebugUtils.currentLine=851969;
 //BA.debugLineNum = 851969;BA.debugLine="HighlightTab(grdbtn)";
_highlighttab(mostCurrent._grdbtn);
RDebugUtils.currentLine=851970;
 //BA.debugLineNum = 851970;BA.debugLine="showgrades";
_showgrades();
RDebugUtils.currentLine=851971;
 //BA.debugLineNum = 851971;BA.debugLine="End Sub";
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
int _i = 0;
anywheresoftware.b4a.objects.LabelWrapper _l = null;
String[] _row = null;
RDebugUtils.currentLine=2752512;
 //BA.debugLineNum = 2752512;BA.debugLine="Sub loadtable";
RDebugUtils.currentLine=2752514;
 //BA.debugLineNum = 2752514;BA.debugLine="NumberOfColumns = CSVHeaders.Size";
_numberofcolumns = mostCurrent._csvheaders.getSize();
RDebugUtils.currentLine=2752515;
 //BA.debugLineNum = 2752515;BA.debugLine="ColumnWidth = SV.Width / NumberOfColumns";
_columnwidth = (int) (mostCurrent._sv.getWidth()/(double)_numberofcolumns);
RDebugUtils.currentLine=2752518;
 //BA.debugLineNum = 2752518;BA.debugLine="For i = 0 To NumberOfColumns - 1";
{
final int step3 = 1;
final int limit3 = (int) (_numberofcolumns-1);
_i = (int) (0) ;
for (;_i <= limit3 ;_i = _i + step3 ) {
RDebugUtils.currentLine=2752519;
 //BA.debugLineNum = 2752519;BA.debugLine="Dim l As Label";
_l = new anywheresoftware.b4a.objects.LabelWrapper();
RDebugUtils.currentLine=2752520;
 //BA.debugLineNum = 2752520;BA.debugLine="l.Initialize(\"header\")";
_l.Initialize(mostCurrent.activityBA,"header");
RDebugUtils.currentLine=2752521;
 //BA.debugLineNum = 2752521;BA.debugLine="l.Text = CSVHeaders.Get(i)";
_l.setText(BA.ObjectToCharSequence(mostCurrent._csvheaders.Get(_i)));
RDebugUtils.currentLine=2752522;
 //BA.debugLineNum = 2752522;BA.debugLine="l.Gravity = Gravity.LEFT";
_l.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.LEFT);
RDebugUtils.currentLine=2752523;
 //BA.debugLineNum = 2752523;BA.debugLine="l.TextSize = FontSize - 4";
_l.setTextSize((float) (_fontsize-4));
RDebugUtils.currentLine=2752524;
 //BA.debugLineNum = 2752524;BA.debugLine="l.Color = HeaderColor";
_l.setColor(_headercolor);
RDebugUtils.currentLine=2752525;
 //BA.debugLineNum = 2752525;BA.debugLine="l.TextColor = HeaderFontColor";
_l.setTextColor(_headerfontcolor);
RDebugUtils.currentLine=2752526;
 //BA.debugLineNum = 2752526;BA.debugLine="l.Padding = Array As Int(10dip, 5dip, 0dip, 5dip";
_l.setPadding(new int[]{anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5))});
RDebugUtils.currentLine=2752527;
 //BA.debugLineNum = 2752527;BA.debugLine="l.Tag = i";
_l.setTag((Object)(_i));
RDebugUtils.currentLine=2752528;
 //BA.debugLineNum = 2752528;BA.debugLine="Table.AddView(l, ColumnWidth * i, 0, ColumnWidth";
mostCurrent._table.AddView((android.view.View)(_l.getObject()),(int) (_columnwidth*_i),(int) (0),_columnwidth,_rowheight);
 }
};
RDebugUtils.currentLine=2752532;
 //BA.debugLineNum = 2752532;BA.debugLine="For i = 0 To StudentList.Size - 1";
{
final int step15 = 1;
final int limit15 = (int) (mostCurrent._studentlist.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit15 ;_i = _i + step15 ) {
RDebugUtils.currentLine=2752533;
 //BA.debugLineNum = 2752533;BA.debugLine="Dim row() As String = StudentList.Get(i)";
_row = (String[])(mostCurrent._studentlist.Get(_i));
RDebugUtils.currentLine=2752534;
 //BA.debugLineNum = 2752534;BA.debugLine="AddTableRow(row, i + 1)  ' +1 to skip header row";
_addtablerow(_row,(int) (_i+1));
 }
};
RDebugUtils.currentLine=2752537;
 //BA.debugLineNum = 2752537;BA.debugLine="Table.Height = (StudentList.Size + 1) * RowHeight";
mostCurrent._table.setHeight((int) ((mostCurrent._studentlist.getSize()+1)*_rowheight));
RDebugUtils.currentLine=2752538;
 //BA.debugLineNum = 2752538;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=1900544;
 //BA.debugLineNum = 1900544;BA.debugLine="Sub makeshadow(numstudent As Int, studentNames As";
RDebugUtils.currentLine=1900545;
 //BA.debugLineNum = 1900545;BA.debugLine="Dim itemSpacing As Int = 108dip";
_itemspacing = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (108));
RDebugUtils.currentLine=1900546;
 //BA.debugLineNum = 1900546;BA.debugLine="Dim startTopMargin As Int = 20dip";
_starttopmargin = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20));
RDebugUtils.currentLine=1900548;
 //BA.debugLineNum = 1900548;BA.debugLine="For i = 0 To numstudent - 1";
{
final int step3 = 1;
final int limit3 = (int) (_numstudent-1);
_i = (int) (0) ;
for (;_i <= limit3 ;_i = _i + step3 ) {
RDebugUtils.currentLine=1900549;
 //BA.debugLineNum = 1900549;BA.debugLine="Dim currentTop As Int = startTopMargin + (i * it";
_currenttop = (int) (_starttopmargin+(_i*_itemspacing));
RDebugUtils.currentLine=1900552;
 //BA.debugLineNum = 1900552;BA.debugLine="Dim pnlShadow As Panel";
_pnlshadow = new anywheresoftware.b4a.objects.PanelWrapper();
RDebugUtils.currentLine=1900553;
 //BA.debugLineNum = 1900553;BA.debugLine="pnlShadow.Initialize(\"pnlShadow\")";
_pnlshadow.Initialize(mostCurrent.activityBA,"pnlShadow");
RDebugUtils.currentLine=1900555;
 //BA.debugLineNum = 1900555;BA.debugLine="Dim cd As ColorDrawable";
_cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
RDebugUtils.currentLine=1900556;
 //BA.debugLineNum = 1900556;BA.debugLine="cd.Initialize2(0xFF000000, 4dip, 0dip, 0xFF00000";
_cd.Initialize2(((int)0xff000000),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (4)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0)),((int)0xff000000));
RDebugUtils.currentLine=1900557;
 //BA.debugLineNum = 1900557;BA.debugLine="pnlShadow.Background = cd";
_pnlshadow.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
RDebugUtils.currentLine=1900558;
 //BA.debugLineNum = 1900558;BA.debugLine="pnlShadow.Elevation = 0";
_pnlshadow.setElevation((float) (0));
RDebugUtils.currentLine=1900560;
 //BA.debugLineNum = 1900560;BA.debugLine="Dim pnlWidth As Int = 330dip";
_pnlwidth = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (330));
RDebugUtils.currentLine=1900561;
 //BA.debugLineNum = 1900561;BA.debugLine="Dim pnlHeight As Int = 88dip";
_pnlheight = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (88));
RDebugUtils.currentLine=1900563;
 //BA.debugLineNum = 1900563;BA.debugLine="Dim shadowLeftPos As Int = gradepnl.Width - 13di";
_shadowleftpos = (int) (mostCurrent._gradepnl.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (13))-_pnlwidth);
RDebugUtils.currentLine=1900564;
 //BA.debugLineNum = 1900564;BA.debugLine="Dim shadowTopPos As Int = currentTop + 7dip";
_shadowtoppos = (int) (_currenttop+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (7)));
RDebugUtils.currentLine=1900566;
 //BA.debugLineNum = 1900566;BA.debugLine="SV1.Panel.AddView(pnlShadow, shadowLeftPos, shad";
mostCurrent._sv1.getPanel().AddView((android.view.View)(_pnlshadow.getObject()),_shadowleftpos,_shadowtoppos,_pnlwidth,_pnlheight);
RDebugUtils.currentLine=1900568;
 //BA.debugLineNum = 1900568;BA.debugLine="Dim name As String = studentNames.Get(i)";
_name = BA.ObjectToString(_studentnames.Get(_i));
RDebugUtils.currentLine=1900569;
 //BA.debugLineNum = 1900569;BA.debugLine="Dim grade As String = GetStudentGrade(name)";
_grade = _getstudentgrade(_name);
RDebugUtils.currentLine=1900570;
 //BA.debugLineNum = 1900570;BA.debugLine="studentgrade(name, grade, currentTop)";
_studentgrade(_name,_grade,_currenttop);
 }
};
RDebugUtils.currentLine=1900572;
 //BA.debugLineNum = 1900572;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=1966080;
 //BA.debugLineNum = 1966080;BA.debugLine="Sub studentgrade(studentName As String, grade As S";
RDebugUtils.currentLine=1966081;
 //BA.debugLineNum = 1966081;BA.debugLine="Dim pnlCard As Panel";
_pnlcard = new anywheresoftware.b4a.objects.PanelWrapper();
RDebugUtils.currentLine=1966082;
 //BA.debugLineNum = 1966082;BA.debugLine="pnlCard.Initialize(\"pnlCard\")";
_pnlcard.Initialize(mostCurrent.activityBA,"pnlCard");
RDebugUtils.currentLine=1966084;
 //BA.debugLineNum = 1966084;BA.debugLine="Dim cd As ColorDrawable";
_cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
RDebugUtils.currentLine=1966085;
 //BA.debugLineNum = 1966085;BA.debugLine="cd.Initialize2(0xFFFFD400, 2dip, 2dip, 0xFF000000";
_cd.Initialize2(((int)0xffffd400),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)),((int)0xff000000));
RDebugUtils.currentLine=1966086;
 //BA.debugLineNum = 1966086;BA.debugLine="pnlCard.Background = cd";
_pnlcard.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
RDebugUtils.currentLine=1966087;
 //BA.debugLineNum = 1966087;BA.debugLine="pnlCard.Elevation = 0";
_pnlcard.setElevation((float) (0));
RDebugUtils.currentLine=1966089;
 //BA.debugLineNum = 1966089;BA.debugLine="Dim pnlWidth As Int = 330dip";
_pnlwidth = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (330));
RDebugUtils.currentLine=1966090;
 //BA.debugLineNum = 1966090;BA.debugLine="Dim pnlHeight As Int = 88dip";
_pnlheight = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (88));
RDebugUtils.currentLine=1966091;
 //BA.debugLineNum = 1966091;BA.debugLine="Dim rightEdgeDistance As Int = 20dip";
_rightedgedistance = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20));
RDebugUtils.currentLine=1966093;
 //BA.debugLineNum = 1966093;BA.debugLine="Dim leftPos As Int = gradepnl.Width - rightEdgeDi";
_leftpos = (int) (mostCurrent._gradepnl.getWidth()-_rightedgedistance-_pnlwidth);
RDebugUtils.currentLine=1966094;
 //BA.debugLineNum = 1966094;BA.debugLine="Dim topPos As Int = currentTop";
_toppos = _currenttop;
RDebugUtils.currentLine=1966097;
 //BA.debugLineNum = 1966097;BA.debugLine="Dim lblGrade As Label";
_lblgrade = new anywheresoftware.b4a.objects.LabelWrapper();
RDebugUtils.currentLine=1966098;
 //BA.debugLineNum = 1966098;BA.debugLine="lblGrade.Initialize(\"lblGrade\")";
_lblgrade.Initialize(mostCurrent.activityBA,"lblGrade");
RDebugUtils.currentLine=1966099;
 //BA.debugLineNum = 1966099;BA.debugLine="lblGrade.Text = grade";
_lblgrade.setText(BA.ObjectToCharSequence(_grade));
RDebugUtils.currentLine=1966100;
 //BA.debugLineNum = 1966100;BA.debugLine="lblGrade.TextSize = 30";
_lblgrade.setTextSize((float) (30));
RDebugUtils.currentLine=1966101;
 //BA.debugLineNum = 1966101;BA.debugLine="lblGrade.TextColor = Colors.Black";
_lblgrade.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
RDebugUtils.currentLine=1966102;
 //BA.debugLineNum = 1966102;BA.debugLine="lblGrade.Gravity = Bit.Or(Gravity.CENTER_HORIZONT";
_lblgrade.setGravity(anywheresoftware.b4a.keywords.Common.Bit.Or(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_HORIZONTAL,anywheresoftware.b4a.keywords.Common.Gravity.CENTER_VERTICAL));
RDebugUtils.currentLine=1966103;
 //BA.debugLineNum = 1966103;BA.debugLine="lblGrade.Typeface = Typeface.CreateNew(Typeface.S";
_lblgrade.setTypeface(anywheresoftware.b4a.keywords.Common.Typeface.CreateNew(anywheresoftware.b4a.keywords.Common.Typeface.SERIF,anywheresoftware.b4a.keywords.Common.Typeface.STYLE_BOLD_ITALIC));
RDebugUtils.currentLine=1966104;
 //BA.debugLineNum = 1966104;BA.debugLine="lblGrade.SingleLine = False";
_lblgrade.setSingleLine(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1966105;
 //BA.debugLineNum = 1966105;BA.debugLine="lblGrade.Enabled = True";
_lblgrade.setEnabled(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=1966106;
 //BA.debugLineNum = 1966106;BA.debugLine="lblGrade.Visible = True";
_lblgrade.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=1966108;
 //BA.debugLineNum = 1966108;BA.debugLine="Dim gradeRightEdge As Int = 264dip";
_graderightedge = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (264));
RDebugUtils.currentLine=1966109;
 //BA.debugLineNum = 1966109;BA.debugLine="Dim gradeWidth As Int = pnlWidth - gradeRightEdge";
_gradewidth = (int) (_pnlwidth-_graderightedge);
RDebugUtils.currentLine=1966110;
 //BA.debugLineNum = 1966110;BA.debugLine="pnlCard.AddView(lblGrade, 0, 0, gradeWidth, pnlHe";
_pnlcard.AddView((android.view.View)(_lblgrade.getObject()),(int) (0),(int) (0),_gradewidth,_pnlheight);
RDebugUtils.currentLine=1966113;
 //BA.debugLineNum = 1966113;BA.debugLine="Dim lbl As Label";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
RDebugUtils.currentLine=1966114;
 //BA.debugLineNum = 1966114;BA.debugLine="lbl.Initialize(\"lbl\")";
_lbl.Initialize(mostCurrent.activityBA,"lbl");
RDebugUtils.currentLine=1966115;
 //BA.debugLineNum = 1966115;BA.debugLine="lbl.Text = studentName";
_lbl.setText(BA.ObjectToCharSequence(_studentname));
RDebugUtils.currentLine=1966116;
 //BA.debugLineNum = 1966116;BA.debugLine="lbl.Gravity = Gravity.CENTER_VERTICAL";
_lbl.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_VERTICAL);
RDebugUtils.currentLine=1966117;
 //BA.debugLineNum = 1966117;BA.debugLine="lbl.Typeface = Typeface.CreateNew(Typeface.SERIF,";
_lbl.setTypeface(anywheresoftware.b4a.keywords.Common.Typeface.CreateNew(anywheresoftware.b4a.keywords.Common.Typeface.SERIF,anywheresoftware.b4a.keywords.Common.Typeface.STYLE_BOLD_ITALIC));
RDebugUtils.currentLine=1966118;
 //BA.debugLineNum = 1966118;BA.debugLine="lbl.TextSize = 20";
_lbl.setTextSize((float) (20));
RDebugUtils.currentLine=1966119;
 //BA.debugLineNum = 1966119;BA.debugLine="lbl.TextColor = Colors.Black";
_lbl.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
RDebugUtils.currentLine=1966120;
 //BA.debugLineNum = 1966120;BA.debugLine="lbl.Padding = Array As Int(10dip, 5dip, 10dip, 5d";
_lbl.setPadding(new int[]{anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5))});
RDebugUtils.currentLine=1966121;
 //BA.debugLineNum = 1966121;BA.debugLine="pnlCard.AddView(lbl, gradeWidth, 0, pnlWidth - gr";
_pnlcard.AddView((android.view.View)(_lbl.getObject()),_gradewidth,(int) (0),(int) (_pnlwidth-_gradewidth),_pnlheight);
RDebugUtils.currentLine=1966122;
 //BA.debugLineNum = 1966122;BA.debugLine="SV1.Panel.AddView(pnlCard, leftPos, topPos, pnlWi";
mostCurrent._sv1.getPanel().AddView((android.view.View)(_pnlcard.getObject()),_leftpos,_toppos,_pnlwidth,_pnlheight);
RDebugUtils.currentLine=1966123;
 //BA.debugLineNum = 1966123;BA.debugLine="End Sub";
return "";
}
public static String  _panel2_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "panel2_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "panel2_click", null));}
RDebugUtils.currentLine=10878976;
 //BA.debugLineNum = 10878976;BA.debugLine="Private Sub Panel2_Click";
RDebugUtils.currentLine=10878977;
 //BA.debugLineNum = 10878977;BA.debugLine="updatedata";
_updatedata();
RDebugUtils.currentLine=10878978;
 //BA.debugLineNum = 10878978;BA.debugLine="End Sub";
return "";
}
public static String  _updatedata() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "updatedata", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "updatedata", null));}
String _attendance = "";
String _rate = "";
String _msg = "";
String[] _row = null;
String[] _newrow = null;
RDebugUtils.currentLine=12386304;
 //BA.debugLineNum = 12386304;BA.debugLine="Sub updatedata";
RDebugUtils.currentLine=12386306;
 //BA.debugLineNum = 12386306;BA.debugLine="If STUDENT_NAME.Text.Trim = \"\" Or STUDENT_ID.Text";
if ((mostCurrent._student_name.getText().trim()).equals("") || (mostCurrent._student_id.getText().trim()).equals("")) { 
RDebugUtils.currentLine=12386307;
 //BA.debugLineNum = 12386307;BA.debugLine="xui.MsgboxAsync(\"Please enter Student Name and S";
_xui.MsgboxAsync(processBA,BA.ObjectToCharSequence("Please enter Student Name and Student ID."),BA.ObjectToCharSequence("Validation Error"));
RDebugUtils.currentLine=12386308;
 //BA.debugLineNum = 12386308;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=12386312;
 //BA.debugLineNum = 12386312;BA.debugLine="If EditText1.Text.Trim = \"\" Or EditText2.Text.Tri";
if ((mostCurrent._edittext1.getText().trim()).equals("") || (mostCurrent._edittext2.getText().trim()).equals("")) { 
RDebugUtils.currentLine=12386313;
 //BA.debugLineNum = 12386313;BA.debugLine="xui.MsgboxAsync(\"Please enter the complete score";
_xui.MsgboxAsync(processBA,BA.ObjectToCharSequence("Please enter the complete score/rate (e.g. 15/20)."),BA.ObjectToCharSequence("Validation Error"));
RDebugUtils.currentLine=12386314;
 //BA.debugLineNum = 12386314;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=12386318;
 //BA.debugLineNum = 12386318;BA.debugLine="Dim attendance As String = \"present\"";
_attendance = "present";
RDebugUtils.currentLine=12386319;
 //BA.debugLineNum = 12386319;BA.debugLine="If CheckBox2.Checked Then";
if (mostCurrent._checkbox2.getChecked()) { 
RDebugUtils.currentLine=12386320;
 //BA.debugLineNum = 12386320;BA.debugLine="attendance = \"late\"";
_attendance = "late";
 }else 
{RDebugUtils.currentLine=12386321;
 //BA.debugLineNum = 12386321;BA.debugLine="Else If CheckBox3.Checked Then";
if (mostCurrent._checkbox3.getChecked()) { 
RDebugUtils.currentLine=12386322;
 //BA.debugLineNum = 12386322;BA.debugLine="attendance = \"absent\"";
_attendance = "absent";
 }}
;
RDebugUtils.currentLine=12386326;
 //BA.debugLineNum = 12386326;BA.debugLine="Dim rate As String = EditText1.Text.Trim & \"/\" &";
_rate = mostCurrent._edittext1.getText().trim()+"/"+mostCurrent._edittext2.getText().trim();
RDebugUtils.currentLine=12386328;
 //BA.debugLineNum = 12386328;BA.debugLine="Dim msg As String";
_msg = "";
RDebugUtils.currentLine=12386329;
 //BA.debugLineNum = 12386329;BA.debugLine="If editRowIndex > -1 Then";
if (_editrowindex>-1) { 
RDebugUtils.currentLine=12386331;
 //BA.debugLineNum = 12386331;BA.debugLine="Dim row() As String = StudentList.Get(editRowInd";
_row = (String[])(mostCurrent._studentlist.Get(_editrowindex));
RDebugUtils.currentLine=12386332;
 //BA.debugLineNum = 12386332;BA.debugLine="row(0) = STUDENT_NAME.Text.Trim";
_row[(int) (0)] = mostCurrent._student_name.getText().trim();
RDebugUtils.currentLine=12386333;
 //BA.debugLineNum = 12386333;BA.debugLine="row(1) = STUDENT_ID.Text.Trim";
_row[(int) (1)] = mostCurrent._student_id.getText().trim();
RDebugUtils.currentLine=12386334;
 //BA.debugLineNum = 12386334;BA.debugLine="row(2) = SELECTSUBJECTS.SelectedItem";
_row[(int) (2)] = mostCurrent._selectsubjects.getSelectedItem();
RDebugUtils.currentLine=12386335;
 //BA.debugLineNum = 12386335;BA.debugLine="row(3) = STUDENT_ACTIVITY.SelectedItem";
_row[(int) (3)] = mostCurrent._student_activity.getSelectedItem();
RDebugUtils.currentLine=12386336;
 //BA.debugLineNum = 12386336;BA.debugLine="row(4) = attendance";
_row[(int) (4)] = _attendance;
RDebugUtils.currentLine=12386337;
 //BA.debugLineNum = 12386337;BA.debugLine="row(5) = rate";
_row[(int) (5)] = _rate;
RDebugUtils.currentLine=12386338;
 //BA.debugLineNum = 12386338;BA.debugLine="StudentList.Set(editRowIndex, row)";
mostCurrent._studentlist.Set(_editrowindex,(Object)(_row));
RDebugUtils.currentLine=12386339;
 //BA.debugLineNum = 12386339;BA.debugLine="msg = \"Student record updated successfully!\"";
_msg = "Student record updated successfully!";
 }else {
RDebugUtils.currentLine=12386342;
 //BA.debugLineNum = 12386342;BA.debugLine="Dim newRow(6) As String";
_newrow = new String[(int) (6)];
java.util.Arrays.fill(_newrow,"");
RDebugUtils.currentLine=12386343;
 //BA.debugLineNum = 12386343;BA.debugLine="newRow(0) = STUDENT_NAME.Text.Trim";
_newrow[(int) (0)] = mostCurrent._student_name.getText().trim();
RDebugUtils.currentLine=12386344;
 //BA.debugLineNum = 12386344;BA.debugLine="newRow(1) = STUDENT_ID.Text.Trim";
_newrow[(int) (1)] = mostCurrent._student_id.getText().trim();
RDebugUtils.currentLine=12386345;
 //BA.debugLineNum = 12386345;BA.debugLine="newRow(2) = SELECTSUBJECTS.SelectedItem";
_newrow[(int) (2)] = mostCurrent._selectsubjects.getSelectedItem();
RDebugUtils.currentLine=12386346;
 //BA.debugLineNum = 12386346;BA.debugLine="newRow(3) = STUDENT_ACTIVITY.SelectedItem";
_newrow[(int) (3)] = mostCurrent._student_activity.getSelectedItem();
RDebugUtils.currentLine=12386347;
 //BA.debugLineNum = 12386347;BA.debugLine="newRow(4) = attendance";
_newrow[(int) (4)] = _attendance;
RDebugUtils.currentLine=12386348;
 //BA.debugLineNum = 12386348;BA.debugLine="newRow(5) = rate";
_newrow[(int) (5)] = _rate;
RDebugUtils.currentLine=12386349;
 //BA.debugLineNum = 12386349;BA.debugLine="StudentList.Add(newRow)";
mostCurrent._studentlist.Add((Object)(_newrow));
RDebugUtils.currentLine=12386350;
 //BA.debugLineNum = 12386350;BA.debugLine="msg = \"Student record added successfully!\"";
_msg = "Student record added successfully!";
 };
RDebugUtils.currentLine=12386354;
 //BA.debugLineNum = 12386354;BA.debugLine="StringUtils1.SaveCSV2(File.DirInternal, \"student.";
_stringutils1.SaveCSV2(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"student.csv",BA.ObjectToChar(","),mostCurrent._studentlist,mostCurrent._csvheaders);
RDebugUtils.currentLine=12386357;
 //BA.debugLineNum = 12386357;BA.debugLine="LoadStudentData";
_loadstudentdata();
RDebugUtils.currentLine=12386360;
 //BA.debugLineNum = 12386360;BA.debugLine="xui.MsgboxAsync(msg, \"Success\")";
_xui.MsgboxAsync(processBA,BA.ObjectToCharSequence(_msg),BA.ObjectToCharSequence("Success"));
RDebugUtils.currentLine=12386361;
 //BA.debugLineNum = 12386361;BA.debugLine="showedit";
_showedit();
RDebugUtils.currentLine=12386362;
 //BA.debugLineNum = 12386362;BA.debugLine="End Sub";
return "";
}
public static String  _profilebtn_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "profilebtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "profilebtn_click", null));}
RDebugUtils.currentLine=983040;
 //BA.debugLineNum = 983040;BA.debugLine="Private Sub profilebtn_Click";
RDebugUtils.currentLine=983041;
 //BA.debugLineNum = 983041;BA.debugLine="HighlightTab(profilebtn)";
_highlighttab(mostCurrent._profilebtn);
RDebugUtils.currentLine=983042;
 //BA.debugLineNum = 983042;BA.debugLine="showprofile";
_showprofile();
RDebugUtils.currentLine=983043;
 //BA.debugLineNum = 983043;BA.debugLine="End Sub";
return "";
}
public static String  _showtable() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "showtable", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "showtable", null));}
RDebugUtils.currentLine=2686976;
 //BA.debugLineNum = 2686976;BA.debugLine="Sub ShowTable";
RDebugUtils.currentLine=2686977;
 //BA.debugLineNum = 2686977;BA.debugLine="pnlmain.RemoveAllViews";
mostCurrent._pnlmain.RemoveAllViews();
RDebugUtils.currentLine=2686978;
 //BA.debugLineNum = 2686978;BA.debugLine="pnlmain.LoadLayout(\"profile\")";
mostCurrent._pnlmain.LoadLayout("profile",mostCurrent.activityBA);
RDebugUtils.currentLine=2686979;
 //BA.debugLineNum = 2686979;BA.debugLine="Table = SV.Panel";
mostCurrent._table = mostCurrent._sv.getPanel();
RDebugUtils.currentLine=2686980;
 //BA.debugLineNum = 2686980;BA.debugLine="NumberOfColumns = 6";
_numberofcolumns = (int) (6);
RDebugUtils.currentLine=2686981;
 //BA.debugLineNum = 2686981;BA.debugLine="ColumnWidth = SV.Width / NumberOfColumns";
_columnwidth = (int) (mostCurrent._sv.getWidth()/(double)_numberofcolumns);
RDebugUtils.currentLine=2686982;
 //BA.debugLineNum = 2686982;BA.debugLine="loadtable";
_loadtable();
RDebugUtils.currentLine=2686983;
 //BA.debugLineNum = 2686983;BA.debugLine="End Sub";
return "";
}
public static String  _update_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "update_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "update_click", null));}
RDebugUtils.currentLine=4259840;
 //BA.debugLineNum = 4259840;BA.debugLine="Private Sub update_Click";
RDebugUtils.currentLine=4259841;
 //BA.debugLineNum = 4259841;BA.debugLine="todelete = False";
_todelete = anywheresoftware.b4a.keywords.Common.False;
RDebugUtils.currentLine=4259842;
 //BA.debugLineNum = 4259842;BA.debugLine="showselectid = True";
_showselectid = anywheresoftware.b4a.keywords.Common.True;
RDebugUtils.currentLine=4259843;
 //BA.debugLineNum = 4259843;BA.debugLine="showselectids";
_showselectids();
RDebugUtils.currentLine=4259844;
 //BA.debugLineNum = 4259844;BA.debugLine="End Sub";
return "";
}
public static String  _updatedata_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "updatedata_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "updatedata_click", null));}
RDebugUtils.currentLine=12451840;
 //BA.debugLineNum = 12451840;BA.debugLine="Private Sub updatedata_Click";
RDebugUtils.currentLine=12451841;
 //BA.debugLineNum = 12451841;BA.debugLine="updatedata";
_updatedata();
RDebugUtils.currentLine=12451842;
 //BA.debugLineNum = 12451842;BA.debugLine="End Sub";
return "";
}
}