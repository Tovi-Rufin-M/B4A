package anywheresoftware.b4a.table;


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
			processBA = new anywheresoftware.b4a.ShellBA(this.getApplicationContext(), null, null, "anywheresoftware.b4a.table", "anywheresoftware.b4a.table.main");
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
        if (anywheresoftware.b4a.objects.ServiceHelper.StarterHelper.startFromActivity(this, processBA, wl, true))
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
		activityBA = new BA(this, layout, processBA, "anywheresoftware.b4a.table", "anywheresoftware.b4a.table.main");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "anywheresoftware.b4a.table.main", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
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

}
public anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4a.objects.StringUtils _stringutils1 = null;
public anywheresoftware.b4a.objects.ScrollViewWrapper _sv = null;
public anywheresoftware.b4a.objects.PanelWrapper _header = null;
public anywheresoftware.b4a.objects.PanelWrapper _table = null;
public static int _numberofcolumns = 0;
public static int _rowheight = 0;
public static int _columnwidth = 0;
public static int _headercolor = 0;
public static int _tablecolor = 0;
public static int _fontcolor = 0;
public static int _headerfontcolor = 0;
public static float _fontsize = 0f;
public static int _alignment = 0;
public static int _selectedrow = 0;
public static int _selectedrowcolor = 0;
public static String  _activity_create(boolean _firsttime) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_create", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_create", new Object[] {_firsttime}));}
RDebugUtils.currentLine=131072;
 //BA.debugLineNum = 131072;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
RDebugUtils.currentLine=131073;
 //BA.debugLineNum = 131073;BA.debugLine="SV.Initialize(0)";
mostCurrent._sv.Initialize(mostCurrent.activityBA,(int) (0));
RDebugUtils.currentLine=131074;
 //BA.debugLineNum = 131074;BA.debugLine="Table = SV.Panel";
mostCurrent._table = mostCurrent._sv.getPanel();
RDebugUtils.currentLine=131075;
 //BA.debugLineNum = 131075;BA.debugLine="Table.Color = TableColor";
mostCurrent._table.setColor(_tablecolor);
RDebugUtils.currentLine=131076;
 //BA.debugLineNum = 131076;BA.debugLine="Activity.AddView(SV, 5%x, 10%y, 90%x, 80%y)";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._sv.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (5),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (10),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (90),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (80),mostCurrent.activityBA));
RDebugUtils.currentLine=131077;
 //BA.debugLineNum = 131077;BA.debugLine="ColumnWidth = SV.Width / NumberOfColumns";
_columnwidth = (int) (mostCurrent._sv.getWidth()/(double)_numberofcolumns);
RDebugUtils.currentLine=131078;
 //BA.debugLineNum = 131078;BA.debugLine="SelectedRow = -1";
_selectedrow = (int) (-1);
RDebugUtils.currentLine=131079;
 //BA.debugLineNum = 131079;BA.debugLine="LoadTableFromCSV(File.DirAssets, \"citylist.csv\",";
_loadtablefromcsv(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"citylist.csv",anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=131081;
 //BA.debugLineNum = 131081;BA.debugLine="End Sub";
return "";
}
public static String  _loadtablefromcsv(String _dir,String _filename,boolean _headersexist) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "loadtablefromcsv", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "loadtablefromcsv", new Object[] {_dir,_filename,_headersexist}));}
anywheresoftware.b4a.objects.collections.List _list1 = null;
String[] _h = null;
anywheresoftware.b4a.objects.collections.List _headers = null;
int _i = 0;
String[] _firstrow = null;
String[] _row = null;
RDebugUtils.currentLine=196608;
 //BA.debugLineNum = 196608;BA.debugLine="Sub LoadTableFromCSV(Dir As String, Filename As St";
RDebugUtils.currentLine=196609;
 //BA.debugLineNum = 196609;BA.debugLine="ClearAll";
_clearall();
RDebugUtils.currentLine=196610;
 //BA.debugLineNum = 196610;BA.debugLine="Dim List1 As List";
_list1 = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=196611;
 //BA.debugLineNum = 196611;BA.debugLine="Dim h() As String";
_h = new String[(int) (0)];
java.util.Arrays.fill(_h,"");
RDebugUtils.currentLine=196612;
 //BA.debugLineNum = 196612;BA.debugLine="If HeadersExist Then";
if (_headersexist) { 
RDebugUtils.currentLine=196613;
 //BA.debugLineNum = 196613;BA.debugLine="Dim headers As List";
_headers = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=196614;
 //BA.debugLineNum = 196614;BA.debugLine="List1 = StringUtils1.LoadCSV2(Dir, Filename, \",\"";
_list1 = _stringutils1.LoadCSV2(_dir,_filename,BA.ObjectToChar(","),_headers);
RDebugUtils.currentLine=196615;
 //BA.debugLineNum = 196615;BA.debugLine="Dim h(headers.Size) As String";
_h = new String[_headers.getSize()];
java.util.Arrays.fill(_h,"");
RDebugUtils.currentLine=196616;
 //BA.debugLineNum = 196616;BA.debugLine="For i = 0 To headers.Size - 1";
{
final int step8 = 1;
final int limit8 = (int) (_headers.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit8 ;_i = _i + step8 ) {
RDebugUtils.currentLine=196617;
 //BA.debugLineNum = 196617;BA.debugLine="h(i) = headers.Get(i)";
_h[_i] = BA.ObjectToString(_headers.Get(_i));
 }
};
 }else {
RDebugUtils.currentLine=196620;
 //BA.debugLineNum = 196620;BA.debugLine="List1 = StringUtils1.LoadCSV(Dir, Filename, \",\")";
_list1 = _stringutils1.LoadCSV(_dir,_filename,BA.ObjectToChar(","));
RDebugUtils.currentLine=196621;
 //BA.debugLineNum = 196621;BA.debugLine="Dim firstRow() As String";
_firstrow = new String[(int) (0)];
java.util.Arrays.fill(_firstrow,"");
RDebugUtils.currentLine=196622;
 //BA.debugLineNum = 196622;BA.debugLine="firstRow = List1.Get(0)";
_firstrow = (String[])(_list1.Get((int) (0)));
RDebugUtils.currentLine=196623;
 //BA.debugLineNum = 196623;BA.debugLine="Dim h(firstRow.Length)";
_h = new String[_firstrow.length];
java.util.Arrays.fill(_h,"");
RDebugUtils.currentLine=196624;
 //BA.debugLineNum = 196624;BA.debugLine="For i = 0 To firstRow.Length - 1";
{
final int step16 = 1;
final int limit16 = (int) (_firstrow.length-1);
_i = (int) (0) ;
for (;_i <= limit16 ;_i = _i + step16 ) {
RDebugUtils.currentLine=196625;
 //BA.debugLineNum = 196625;BA.debugLine="h(i) = \"Col\" & (i + 1)";
_h[_i] = "Col"+BA.NumberToString((_i+1));
 }
};
 };
RDebugUtils.currentLine=196628;
 //BA.debugLineNum = 196628;BA.debugLine="NumberOfColumns = h.Length";
_numberofcolumns = _h.length;
RDebugUtils.currentLine=196629;
 //BA.debugLineNum = 196629;BA.debugLine="ColumnWidth = SV.Width / NumberOfColumns 'update";
_columnwidth = (int) (mostCurrent._sv.getWidth()/(double)_numberofcolumns);
RDebugUtils.currentLine=196630;
 //BA.debugLineNum = 196630;BA.debugLine="SetHeader(h)";
_setheader(_h);
RDebugUtils.currentLine=196631;
 //BA.debugLineNum = 196631;BA.debugLine="For i = 0 To List1.Size - 1";
{
final int step23 = 1;
final int limit23 = (int) (_list1.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit23 ;_i = _i + step23 ) {
RDebugUtils.currentLine=196632;
 //BA.debugLineNum = 196632;BA.debugLine="Dim row() As String";
_row = new String[(int) (0)];
java.util.Arrays.fill(_row,"");
RDebugUtils.currentLine=196633;
 //BA.debugLineNum = 196633;BA.debugLine="row = List1.Get(i)";
_row = (String[])(_list1.Get(_i));
RDebugUtils.currentLine=196634;
 //BA.debugLineNum = 196634;BA.debugLine="AddRow(row)";
_addrow(_row);
 }
};
RDebugUtils.currentLine=196636;
 //BA.debugLineNum = 196636;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
RDebugUtils.currentModule="main";
RDebugUtils.currentLine=1048576;
 //BA.debugLineNum = 1048576;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
RDebugUtils.currentLine=1048578;
 //BA.debugLineNum = 1048578;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_resume", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_resume", null));}
RDebugUtils.currentLine=983040;
 //BA.debugLineNum = 983040;BA.debugLine="Sub Activity_Resume";
RDebugUtils.currentLine=983042;
 //BA.debugLineNum = 983042;BA.debugLine="End Sub";
return "";
}
public static String  _addrow(String[] _values) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "addrow", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "addrow", new Object[] {_values}));}
int _lastrow = 0;
int _i = 0;
anywheresoftware.b4a.objects.LabelWrapper _l = null;
anywheresoftware.b4a.table.main._rowcol _rc = null;
RDebugUtils.currentLine=589824;
 //BA.debugLineNum = 589824;BA.debugLine="Sub AddRow(Values() As String)";
RDebugUtils.currentLine=589825;
 //BA.debugLineNum = 589825;BA.debugLine="If Values.Length <> NumberOfColumns Then";
if (_values.length!=_numberofcolumns) { 
RDebugUtils.currentLine=589826;
 //BA.debugLineNum = 589826;BA.debugLine="Log(\"Wrong number of values.\")";
anywheresoftware.b4a.keywords.Common.LogImpl("8589826","Wrong number of values.",0);
RDebugUtils.currentLine=589827;
 //BA.debugLineNum = 589827;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=589829;
 //BA.debugLineNum = 589829;BA.debugLine="Dim lastRow As Int";
_lastrow = 0;
RDebugUtils.currentLine=589830;
 //BA.debugLineNum = 589830;BA.debugLine="lastRow = NumberOfRows";
_lastrow = _numberofrows();
RDebugUtils.currentLine=589831;
 //BA.debugLineNum = 589831;BA.debugLine="For i = 0 To NumberOfColumns - 1";
{
final int step7 = 1;
final int limit7 = (int) (_numberofcolumns-1);
_i = (int) (0) ;
for (;_i <= limit7 ;_i = _i + step7 ) {
RDebugUtils.currentLine=589832;
 //BA.debugLineNum = 589832;BA.debugLine="Dim l As Label";
_l = new anywheresoftware.b4a.objects.LabelWrapper();
RDebugUtils.currentLine=589833;
 //BA.debugLineNum = 589833;BA.debugLine="l.Initialize(\"cell\")";
_l.Initialize(mostCurrent.activityBA,"cell");
RDebugUtils.currentLine=589834;
 //BA.debugLineNum = 589834;BA.debugLine="l.Text = Values(i)";
_l.setText(BA.ObjectToCharSequence(_values[_i]));
RDebugUtils.currentLine=589835;
 //BA.debugLineNum = 589835;BA.debugLine="l.Gravity = Alignment";
_l.setGravity(_alignment);
RDebugUtils.currentLine=589836;
 //BA.debugLineNum = 589836;BA.debugLine="l.TextSize = FontSize";
_l.setTextSize(_fontsize);
RDebugUtils.currentLine=589837;
 //BA.debugLineNum = 589837;BA.debugLine="l.TextColor = FontColor";
_l.setTextColor(_fontcolor);
RDebugUtils.currentLine=589838;
 //BA.debugLineNum = 589838;BA.debugLine="Dim rc As RowCol";
_rc = new anywheresoftware.b4a.table.main._rowcol();
RDebugUtils.currentLine=589839;
 //BA.debugLineNum = 589839;BA.debugLine="rc.Initialize";
_rc.Initialize();
RDebugUtils.currentLine=589840;
 //BA.debugLineNum = 589840;BA.debugLine="rc.Col = i";
_rc.Col /*int*/  = _i;
RDebugUtils.currentLine=589841;
 //BA.debugLineNum = 589841;BA.debugLine="rc.Row = lastRow";
_rc.Row /*int*/  = _lastrow;
RDebugUtils.currentLine=589842;
 //BA.debugLineNum = 589842;BA.debugLine="l.Tag = rc";
_l.setTag((Object)(_rc));
RDebugUtils.currentLine=589843;
 //BA.debugLineNum = 589843;BA.debugLine="Table.AddView(l, ColumnWidth * i, RowHeight * la";
mostCurrent._table.AddView((android.view.View)(_l.getObject()),(int) (_columnwidth*_i),(int) (_rowheight*_lastrow),_columnwidth,_rowheight);
 }
};
RDebugUtils.currentLine=589845;
 //BA.debugLineNum = 589845;BA.debugLine="Table.Height = NumberOfRows * RowHeight";
mostCurrent._table.setHeight((int) (_numberofrows()*_rowheight));
RDebugUtils.currentLine=589846;
 //BA.debugLineNum = 589846;BA.debugLine="End Sub";
return "";
}
public static int  _numberofrows() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "numberofrows", false))
	 {return ((Integer) Debug.delegate(mostCurrent.activityBA, "numberofrows", null));}
RDebugUtils.currentLine=720896;
 //BA.debugLineNum = 720896;BA.debugLine="Sub NumberOfRows As Int";
RDebugUtils.currentLine=720897;
 //BA.debugLineNum = 720897;BA.debugLine="Return Table.NumberOfViews / NumberOfColumns";
if (true) return (int) (mostCurrent._table.getNumberOfViews()/(double)_numberofcolumns);
RDebugUtils.currentLine=720898;
 //BA.debugLineNum = 720898;BA.debugLine="End Sub";
return 0;
}
public static String  _cell_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "cell_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "cell_click", null));}
anywheresoftware.b4a.table.main._rowcol _rc = null;
anywheresoftware.b4a.objects.LabelWrapper _l = null;
RDebugUtils.currentLine=327680;
 //BA.debugLineNum = 327680;BA.debugLine="Sub Cell_Click";
RDebugUtils.currentLine=327681;
 //BA.debugLineNum = 327681;BA.debugLine="Dim rc As RowCol";
_rc = new anywheresoftware.b4a.table.main._rowcol();
RDebugUtils.currentLine=327682;
 //BA.debugLineNum = 327682;BA.debugLine="Dim l As Label";
_l = new anywheresoftware.b4a.objects.LabelWrapper();
RDebugUtils.currentLine=327683;
 //BA.debugLineNum = 327683;BA.debugLine="l = Sender";
_l = (anywheresoftware.b4a.objects.LabelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.LabelWrapper(), (android.widget.TextView)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
RDebugUtils.currentLine=327684;
 //BA.debugLineNum = 327684;BA.debugLine="rc = l.Tag";
_rc = (anywheresoftware.b4a.table.main._rowcol)(_l.getTag());
RDebugUtils.currentLine=327685;
 //BA.debugLineNum = 327685;BA.debugLine="SelectRow(rc.Row)";
_selectrow(_rc.Row /*int*/ );
RDebugUtils.currentLine=327686;
 //BA.debugLineNum = 327686;BA.debugLine="Activity.Title = \"Cell clicked: (\" & rc.Row & \",";
mostCurrent._activity.setTitle(BA.ObjectToCharSequence("Cell clicked: ("+BA.NumberToString(_rc.Row /*int*/ )+", "+BA.NumberToString(_rc.Col /*int*/ )+")"));
RDebugUtils.currentLine=327687;
 //BA.debugLineNum = 327687;BA.debugLine="End Sub";
return "";
}
public static String  _selectrow(int _row) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "selectrow", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "selectrow", new Object[] {_row}));}
int _col = 0;
RDebugUtils.currentLine=458752;
 //BA.debugLineNum = 458752;BA.debugLine="Sub SelectRow(Row As Int)";
RDebugUtils.currentLine=458754;
 //BA.debugLineNum = 458754;BA.debugLine="If SelectedRow > -1 Then";
if (_selectedrow>-1) { 
RDebugUtils.currentLine=458755;
 //BA.debugLineNum = 458755;BA.debugLine="For col = 0 To NumberOfColumns - 1";
{
final int step2 = 1;
final int limit2 = (int) (_numberofcolumns-1);
_col = (int) (0) ;
for (;_col <= limit2 ;_col = _col + step2 ) {
RDebugUtils.currentLine=458756;
 //BA.debugLineNum = 458756;BA.debugLine="GetView(SelectedRow, col).Color = Colors.Transp";
_getview(_selectedrow,_col).setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 }
};
 };
RDebugUtils.currentLine=458759;
 //BA.debugLineNum = 458759;BA.debugLine="SelectedRow = Row";
_selectedrow = _row;
RDebugUtils.currentLine=458760;
 //BA.debugLineNum = 458760;BA.debugLine="For col = 0 To NumberOfColumns - 1";
{
final int step7 = 1;
final int limit7 = (int) (_numberofcolumns-1);
_col = (int) (0) ;
for (;_col <= limit7 ;_col = _col + step7 ) {
RDebugUtils.currentLine=458761;
 //BA.debugLineNum = 458761;BA.debugLine="GetView(Row, col).Color = SelectedRowColor";
_getview(_row,_col).setColor(_selectedrowcolor);
 }
};
RDebugUtils.currentLine=458763;
 //BA.debugLineNum = 458763;BA.debugLine="End Sub";
return "";
}
public static String  _clearall() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "clearall", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "clearall", null));}
int _i = 0;
RDebugUtils.currentLine=917504;
 //BA.debugLineNum = 917504;BA.debugLine="Sub ClearAll";
RDebugUtils.currentLine=917505;
 //BA.debugLineNum = 917505;BA.debugLine="For i = Table.NumberOfViews -1 To 0 Step -1";
{
final int step1 = -1;
final int limit1 = (int) (0);
_i = (int) (mostCurrent._table.getNumberOfViews()-1) ;
for (;_i >= limit1 ;_i = _i + step1 ) {
RDebugUtils.currentLine=917506;
 //BA.debugLineNum = 917506;BA.debugLine="Table.RemoveViewAt(i)";
mostCurrent._table.RemoveViewAt(_i);
 }
};
RDebugUtils.currentLine=917508;
 //BA.debugLineNum = 917508;BA.debugLine="Table.Height = 0";
mostCurrent._table.setHeight((int) (0));
RDebugUtils.currentLine=917509;
 //BA.debugLineNum = 917509;BA.debugLine="SelectedRow = -1";
_selectedrow = (int) (-1);
RDebugUtils.currentLine=917510;
 //BA.debugLineNum = 917510;BA.debugLine="End Sub";
return "";
}
public static String  _getcell(int _row,int _col) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "getcell", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "getcell", new Object[] {_row,_col}));}
RDebugUtils.currentLine=851968;
 //BA.debugLineNum = 851968;BA.debugLine="Sub GetCell(Row As Int, Col As Int) As String";
RDebugUtils.currentLine=851969;
 //BA.debugLineNum = 851969;BA.debugLine="Return GetView(Row, Col).Text";
if (true) return _getview(_row,_col).getText();
RDebugUtils.currentLine=851970;
 //BA.debugLineNum = 851970;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.LabelWrapper  _getview(int _row,int _col) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "getview", false))
	 {return ((anywheresoftware.b4a.objects.LabelWrapper) Debug.delegate(mostCurrent.activityBA, "getview", new Object[] {_row,_col}));}
anywheresoftware.b4a.objects.LabelWrapper _l = null;
RDebugUtils.currentLine=524288;
 //BA.debugLineNum = 524288;BA.debugLine="Sub GetView(Row As Int, Col As Int) As Label";
RDebugUtils.currentLine=524289;
 //BA.debugLineNum = 524289;BA.debugLine="Dim l As Label";
_l = new anywheresoftware.b4a.objects.LabelWrapper();
RDebugUtils.currentLine=524290;
 //BA.debugLineNum = 524290;BA.debugLine="l = Table.GetView(Row * NumberOfColumns + Col)";
_l = (anywheresoftware.b4a.objects.LabelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.LabelWrapper(), (android.widget.TextView)(mostCurrent._table.GetView((int) (_row*_numberofcolumns+_col)).getObject()));
RDebugUtils.currentLine=524291;
 //BA.debugLineNum = 524291;BA.debugLine="Return l";
if (true) return _l;
RDebugUtils.currentLine=524292;
 //BA.debugLineNum = 524292;BA.debugLine="End Sub";
return null;
}
public static String  _header_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "header_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "header_click", null));}
anywheresoftware.b4a.objects.LabelWrapper _l = null;
int _col = 0;
RDebugUtils.currentLine=393216;
 //BA.debugLineNum = 393216;BA.debugLine="Sub Header_Click";
RDebugUtils.currentLine=393217;
 //BA.debugLineNum = 393217;BA.debugLine="Dim l As Label";
_l = new anywheresoftware.b4a.objects.LabelWrapper();
RDebugUtils.currentLine=393218;
 //BA.debugLineNum = 393218;BA.debugLine="Dim col As Int";
_col = 0;
RDebugUtils.currentLine=393219;
 //BA.debugLineNum = 393219;BA.debugLine="l = Sender";
_l = (anywheresoftware.b4a.objects.LabelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.LabelWrapper(), (android.widget.TextView)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
RDebugUtils.currentLine=393220;
 //BA.debugLineNum = 393220;BA.debugLine="col = l.Tag";
_col = (int)(BA.ObjectToNumber(_l.getTag()));
RDebugUtils.currentLine=393221;
 //BA.debugLineNum = 393221;BA.debugLine="Activity.Title = \"Header clicked: \" & col";
mostCurrent._activity.setTitle(BA.ObjectToCharSequence("Header clicked: "+BA.NumberToString(_col)));
RDebugUtils.currentLine=393222;
 //BA.debugLineNum = 393222;BA.debugLine="End Sub";
return "";
}
public static String  _setheader(String[] _values) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "setheader", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "setheader", new Object[] {_values}));}
int _i = 0;
anywheresoftware.b4a.objects.LabelWrapper _l = null;
RDebugUtils.currentLine=655360;
 //BA.debugLineNum = 655360;BA.debugLine="Sub SetHeader(Values() As String)";
RDebugUtils.currentLine=655361;
 //BA.debugLineNum = 655361;BA.debugLine="If Header.IsInitialized Then Return 'should only";
if (mostCurrent._header.IsInitialized()) { 
if (true) return "";};
RDebugUtils.currentLine=655362;
 //BA.debugLineNum = 655362;BA.debugLine="Header.Initialize(\"\")";
mostCurrent._header.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=655363;
 //BA.debugLineNum = 655363;BA.debugLine="For i = 0 To NumberOfColumns - 1";
{
final int step3 = 1;
final int limit3 = (int) (_numberofcolumns-1);
_i = (int) (0) ;
for (;_i <= limit3 ;_i = _i + step3 ) {
RDebugUtils.currentLine=655364;
 //BA.debugLineNum = 655364;BA.debugLine="Dim l As Label";
_l = new anywheresoftware.b4a.objects.LabelWrapper();
RDebugUtils.currentLine=655365;
 //BA.debugLineNum = 655365;BA.debugLine="l.Initialize(\"header\")";
_l.Initialize(mostCurrent.activityBA,"header");
RDebugUtils.currentLine=655366;
 //BA.debugLineNum = 655366;BA.debugLine="l.Text = Values(i)";
_l.setText(BA.ObjectToCharSequence(_values[_i]));
RDebugUtils.currentLine=655367;
 //BA.debugLineNum = 655367;BA.debugLine="l.Gravity = Gravity.CENTER";
_l.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
RDebugUtils.currentLine=655368;
 //BA.debugLineNum = 655368;BA.debugLine="l.TextSize = FontSize";
_l.setTextSize(_fontsize);
RDebugUtils.currentLine=655369;
 //BA.debugLineNum = 655369;BA.debugLine="l.Color = HeaderColor";
_l.setColor(_headercolor);
RDebugUtils.currentLine=655370;
 //BA.debugLineNum = 655370;BA.debugLine="l.TextColor = HeaderFontColor";
_l.setTextColor(_headerfontcolor);
RDebugUtils.currentLine=655371;
 //BA.debugLineNum = 655371;BA.debugLine="l.Tag = i";
_l.setTag((Object)(_i));
RDebugUtils.currentLine=655372;
 //BA.debugLineNum = 655372;BA.debugLine="Header.AddView(l, ColumnWidth * i, 0, ColumnWidt";
mostCurrent._header.AddView((android.view.View)(_l.getObject()),(int) (_columnwidth*_i),(int) (0),_columnwidth,_rowheight);
 }
};
RDebugUtils.currentLine=655374;
 //BA.debugLineNum = 655374;BA.debugLine="Activity.AddView(Header, SV.Left, SV.Top - RowHei";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._header.getObject()),mostCurrent._sv.getLeft(),(int) (mostCurrent._sv.getTop()-_rowheight),mostCurrent._sv.getWidth(),_rowheight);
RDebugUtils.currentLine=655375;
 //BA.debugLineNum = 655375;BA.debugLine="End Sub";
return "";
}
public static String  _savetabletocsv(String _dir,String _filename) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "savetabletocsv", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "savetabletocsv", new Object[] {_dir,_filename}));}
String[] _headers = null;
int _i = 0;
anywheresoftware.b4a.objects.LabelWrapper _l = null;
anywheresoftware.b4a.objects.collections.List _list1 = null;
String[] _row = null;
int _c = 0;
RDebugUtils.currentLine=262144;
 //BA.debugLineNum = 262144;BA.debugLine="Sub SaveTableToCSV(Dir As String, Filename As Stri";
RDebugUtils.currentLine=262145;
 //BA.debugLineNum = 262145;BA.debugLine="Dim headers(NumberOfColumns) As String";
_headers = new String[_numberofcolumns];
java.util.Arrays.fill(_headers,"");
RDebugUtils.currentLine=262146;
 //BA.debugLineNum = 262146;BA.debugLine="For i = 0 To headers.Length - 1";
{
final int step2 = 1;
final int limit2 = (int) (_headers.length-1);
_i = (int) (0) ;
for (;_i <= limit2 ;_i = _i + step2 ) {
RDebugUtils.currentLine=262147;
 //BA.debugLineNum = 262147;BA.debugLine="Dim l As Label";
_l = new anywheresoftware.b4a.objects.LabelWrapper();
RDebugUtils.currentLine=262148;
 //BA.debugLineNum = 262148;BA.debugLine="l = Header.GetView(i)";
_l = (anywheresoftware.b4a.objects.LabelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.LabelWrapper(), (android.widget.TextView)(mostCurrent._header.GetView(_i).getObject()));
RDebugUtils.currentLine=262149;
 //BA.debugLineNum = 262149;BA.debugLine="headers(i) = l.Text";
_headers[_i] = _l.getText();
 }
};
RDebugUtils.currentLine=262151;
 //BA.debugLineNum = 262151;BA.debugLine="Dim list1 As List";
_list1 = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=262152;
 //BA.debugLineNum = 262152;BA.debugLine="list1.Initialize";
_list1.Initialize();
RDebugUtils.currentLine=262153;
 //BA.debugLineNum = 262153;BA.debugLine="For i = 0 To NumberOfRows - 1";
{
final int step9 = 1;
final int limit9 = (int) (_numberofrows()-1);
_i = (int) (0) ;
for (;_i <= limit9 ;_i = _i + step9 ) {
RDebugUtils.currentLine=262154;
 //BA.debugLineNum = 262154;BA.debugLine="Dim row(NumberOfColumns) As String";
_row = new String[_numberofcolumns];
java.util.Arrays.fill(_row,"");
RDebugUtils.currentLine=262155;
 //BA.debugLineNum = 262155;BA.debugLine="For c = 0 To NumberOfColumns - 1";
{
final int step11 = 1;
final int limit11 = (int) (_numberofcolumns-1);
_c = (int) (0) ;
for (;_c <= limit11 ;_c = _c + step11 ) {
RDebugUtils.currentLine=262156;
 //BA.debugLineNum = 262156;BA.debugLine="row(c) = GetCell(i, c)";
_row[_c] = _getcell(_i,_c);
 }
};
RDebugUtils.currentLine=262158;
 //BA.debugLineNum = 262158;BA.debugLine="list1.Add(row)";
_list1.Add((Object)(_row));
 }
};
RDebugUtils.currentLine=262160;
 //BA.debugLineNum = 262160;BA.debugLine="StringUtils1.SaveCSV2(Dir, Filename, \",\", list1,";
_stringutils1.SaveCSV2(_dir,_filename,BA.ObjectToChar(","),_list1,anywheresoftware.b4a.keywords.Common.ArrayToList(_headers));
RDebugUtils.currentLine=262161;
 //BA.debugLineNum = 262161;BA.debugLine="End Sub";
return "";
}
public static String  _setcell(int _row,int _col,String _value) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "setcell", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "setcell", new Object[] {_row,_col,_value}));}
RDebugUtils.currentLine=786432;
 //BA.debugLineNum = 786432;BA.debugLine="Sub SetCell(Row As Int, Col As Int, Value As Strin";
RDebugUtils.currentLine=786433;
 //BA.debugLineNum = 786433;BA.debugLine="GetView(Row, Col).Text = Value";
_getview(_row,_col).setText(BA.ObjectToCharSequence(_value));
RDebugUtils.currentLine=786434;
 //BA.debugLineNum = 786434;BA.debugLine="End Sub";
return "";
}
}