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
public static boolean _todelete = false;
public static String _selectedstudent = "";
public anywheresoftware.b4a.objects.LabelWrapper _anttslt = null;
public anywheresoftware.b4a.objects.LabelWrapper _gpaslt = null;
public anywheresoftware.b4a.objects.LabelWrapper _hstslt = null;
public anywheresoftware.b4a.objects.LabelWrapper _studid = null;
public anywheresoftware.b4a.objects.LabelWrapper _studname = null;
public anywheresoftware.b4a.objects.LabelWrapper _top1grd = null;
public anywheresoftware.b4a.objects.LabelWrapper _top1sub = null;
public anywheresoftware.b4a.objects.LabelWrapper _top2grd = null;
public anywheresoftware.b4a.objects.LabelWrapper _top2sub = null;
public anywheresoftware.b4a.objects.LabelWrapper _top3grd = null;
public anywheresoftware.b4a.objects.LabelWrapper _top3sub = null;
public b4a.example.starter _starter = null;
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

public static boolean isAnyActivityVisible() {
    boolean vis = false;
vis = vis | (main.mostCurrent != null);
return vis;}
public static String  _activity_create(boolean _firsttime) throws Exception{
 //BA.debugLineNum = 111;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 112;BA.debugLine="Activity.LoadLayout(\"main\")";
mostCurrent._activity.LoadLayout("main",mostCurrent.activityBA);
 //BA.debugLineNum = 114;BA.debugLine="initdrawer";
_initdrawer();
 //BA.debugLineNum = 117;BA.debugLine="HeaderColor = Colors.Transparent";
_headercolor = anywheresoftware.b4a.keywords.Common.Colors.Transparent;
 //BA.debugLineNum = 118;BA.debugLine="NumberOfColumns = 6";
_numberofcolumns = (int) (6);
 //BA.debugLineNum = 119;BA.debugLine="RowHeight = 30dip";
_rowheight = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30));
 //BA.debugLineNum = 120;BA.debugLine="FontColor = Colors.Black";
_fontcolor = anywheresoftware.b4a.keywords.Common.Colors.Black;
 //BA.debugLineNum = 121;BA.debugLine="HeaderFontColor = Colors.Black";
_headerfontcolor = anywheresoftware.b4a.keywords.Common.Colors.Black;
 //BA.debugLineNum = 122;BA.debugLine="FontSize = 14";
_fontsize = (float) (14);
 //BA.debugLineNum = 123;BA.debugLine="Alignment = Gravity.LEFT";
_alignment = anywheresoftware.b4a.keywords.Common.Gravity.LEFT;
 //BA.debugLineNum = 126;BA.debugLine="LoadStudentData";
_loadstudentdata();
 //BA.debugLineNum = 128;BA.debugLine="dashbtn_Click";
_dashbtn_click();
 //BA.debugLineNum = 129;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 134;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 135;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 131;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 132;BA.debugLine="End Sub";
return "";
}
public static String  _add_click() throws Exception{
 //BA.debugLineNum = 1224;BA.debugLine="Private Sub add_Click";
 //BA.debugLineNum = 1225;BA.debugLine="editRowIndex = -1";
_editrowindex = (int) (-1);
 //BA.debugLineNum = 1226;BA.debugLine="todelete = False";
_todelete = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 1227;BA.debugLine="pnlmain.RemoveAllViews";
mostCurrent._pnlmain.RemoveAllViews();
 //BA.debugLineNum = 1228;BA.debugLine="pnlmain.LoadLayout(\"CREAT\")";
mostCurrent._pnlmain.LoadLayout("CREAT",mostCurrent.activityBA);
 //BA.debugLineNum = 1229;BA.debugLine="loadspinner";
_loadspinner();
 //BA.debugLineNum = 1232;BA.debugLine="STUDENT_NAME.Text = \"\"";
mostCurrent._student_name.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 1233;BA.debugLine="STUDENT_ID.Text = \"\"";
mostCurrent._student_id.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 1234;BA.debugLine="EditText1.Text = \"\"";
mostCurrent._edittext1.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 1235;BA.debugLine="EditText2.Text = \"\"";
mostCurrent._edittext2.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 1236;BA.debugLine="CheckBox1.Checked = True";
mostCurrent._checkbox1.setChecked(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1237;BA.debugLine="CheckBox2.Checked = False";
mostCurrent._checkbox2.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1238;BA.debugLine="CheckBox3.Checked = False";
mostCurrent._checkbox3.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1239;BA.debugLine="End Sub";
return "";
}
public static String  _addtablerow(String[] _values,int _rowindex) throws Exception{
int _i = 0;
anywheresoftware.b4a.objects.LabelWrapper _l = null;
b4a.example.main._rowcol _rc = null;
 //BA.debugLineNum = 978;BA.debugLine="Sub AddTableRow(Values() As String, rowIndex As In";
 //BA.debugLineNum = 979;BA.debugLine="If Values.Length <> NumberOfColumns Then";
if (_values.length!=_numberofcolumns) { 
 //BA.debugLineNum = 980;BA.debugLine="Log(\"Wrong number of values.\")";
anywheresoftware.b4a.keywords.Common.LogImpl("12752514","Wrong number of values.",0);
 //BA.debugLineNum = 981;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 983;BA.debugLine="For i = 0 To NumberOfColumns - 1";
{
final int step5 = 1;
final int limit5 = (int) (_numberofcolumns-1);
_i = (int) (0) ;
for (;_i <= limit5 ;_i = _i + step5 ) {
 //BA.debugLineNum = 984;BA.debugLine="Dim l As Label";
_l = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 985;BA.debugLine="l.Initialize(\"cell\")";
_l.Initialize(mostCurrent.activityBA,"cell");
 //BA.debugLineNum = 986;BA.debugLine="l.Text = Values(i)";
_l.setText(BA.ObjectToCharSequence(_values[_i]));
 //BA.debugLineNum = 987;BA.debugLine="l.Gravity = Alignment";
_l.setGravity(_alignment);
 //BA.debugLineNum = 988;BA.debugLine="l.TextSize = FontSize";
_l.setTextSize(_fontsize);
 //BA.debugLineNum = 989;BA.debugLine="l.TextColor = FontColor";
_l.setTextColor(_fontcolor);
 //BA.debugLineNum = 990;BA.debugLine="l.Padding = Array As Int(10dip, 0, 0, 0)";
_l.setPadding(new int[]{anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (0),(int) (0),(int) (0)});
 //BA.debugLineNum = 991;BA.debugLine="Dim rc As RowCol";
_rc = new b4a.example.main._rowcol();
 //BA.debugLineNum = 992;BA.debugLine="rc.Initialize";
_rc.Initialize();
 //BA.debugLineNum = 993;BA.debugLine="rc.Col = i";
_rc.Col /*int*/  = _i;
 //BA.debugLineNum = 994;BA.debugLine="rc.Row = rowIndex";
_rc.Row /*int*/  = _rowindex;
 //BA.debugLineNum = 995;BA.debugLine="l.Tag = rc";
_l.setTag((Object)(_rc));
 //BA.debugLineNum = 996;BA.debugLine="Table.AddView(l, ColumnWidth * i, RowHeight * ro";
mostCurrent._table.AddView((android.view.View)(_l.getObject()),(int) (_columnwidth*_i),(int) (_rowheight*_rowindex),_columnwidth,_rowheight);
 }
};
 //BA.debugLineNum = 998;BA.debugLine="End Sub";
return "";
}
public static String  _bak_click() throws Exception{
 //BA.debugLineNum = 1199;BA.debugLine="Private Sub bak_Click";
 //BA.debugLineNum = 1200;BA.debugLine="If isselectingid Then";
if (_isselectingid) { 
 //BA.debugLineNum = 1201;BA.debugLine="showselectid = False";
_showselectid = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 1202;BA.debugLine="isselectingid = False";
_isselectingid = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 1203;BA.debugLine="If update.IsInitialized Then update.Enabled = Tr";
if (mostCurrent._update.IsInitialized()) { 
mostCurrent._update.setEnabled(anywheresoftware.b4a.keywords.Common.True);};
 //BA.debugLineNum = 1204;BA.debugLine="showselectids";
_showselectids();
 }else {
 //BA.debugLineNum = 1206;BA.debugLine="showselectids";
_showselectids();
 //BA.debugLineNum = 1207;BA.debugLine="MsgboxAsync(\"run\", \"works\") ' Fixed warning #34";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("run"),BA.ObjectToCharSequence("works"),processBA);
 //BA.debugLineNum = 1208;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 1210;BA.debugLine="End Sub";
return "";
}
public static String  _btn_click() throws Exception{
String _serch_id = "";
int _i = 0;
String[] _row = null;
String _studentname = "";
String _subject = "";
String _activity11 = "";
String _attendance = "";
String _rate = "";
String[] _parts = null;
 //BA.debugLineNum = 1102;BA.debugLine="Private Sub btn_Click";
 //BA.debugLineNum = 1104;BA.debugLine="Dim serch_id As String = spinner.SelectedItem";
_serch_id = mostCurrent._spinner.getSelectedItem();
 //BA.debugLineNum = 1107;BA.debugLine="showselectid = False";
_showselectid = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 1108;BA.debugLine="isselectingid = False";
_isselectingid = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 1109;BA.debugLine="If update.IsInitialized Then update.Enabled = Tru";
if (mostCurrent._update.IsInitialized()) { 
mostCurrent._update.setEnabled(anywheresoftware.b4a.keywords.Common.True);};
 //BA.debugLineNum = 1110;BA.debugLine="showselectids";
_showselectids();
 //BA.debugLineNum = 1113;BA.debugLine="editRowIndex = -1";
_editrowindex = (int) (-1);
 //BA.debugLineNum = 1114;BA.debugLine="For i = 0 To StudentList.Size - 1";
{
final int step7 = 1;
final int limit7 = (int) (mostCurrent._studentlist.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit7 ;_i = _i + step7 ) {
 //BA.debugLineNum = 1115;BA.debugLine="Dim row() As String = StudentList.Get(i)";
_row = (String[])(mostCurrent._studentlist.Get(_i));
 //BA.debugLineNum = 1116;BA.debugLine="If row(1) = serch_id Then";
if ((_row[(int) (1)]).equals(_serch_id)) { 
 //BA.debugLineNum = 1117;BA.debugLine="editRowIndex = i";
_editrowindex = _i;
 //BA.debugLineNum = 1118;BA.debugLine="Exit";
if (true) break;
 };
 }
};
 //BA.debugLineNum = 1123;BA.debugLine="If editRowIndex > -1 Then";
if (_editrowindex>-1) { 
 //BA.debugLineNum = 1124;BA.debugLine="If todelete Then";
if (_todelete) { 
 //BA.debugLineNum = 1125;BA.debugLine="Dim row() As String = StudentList.Get(editRowIn";
_row = (String[])(mostCurrent._studentlist.Get(_editrowindex));
 //BA.debugLineNum = 1126;BA.debugLine="Dim studentName As String = row(0)";
_studentname = _row[(int) (0)];
 //BA.debugLineNum = 1129;BA.debugLine="StudentList.RemoveAt(editRowIndex)";
mostCurrent._studentlist.RemoveAt(_editrowindex);
 //BA.debugLineNum = 1132;BA.debugLine="StringUtils1.SaveCSV2(File.DirInternal, \"studen";
_stringutils1.SaveCSV2(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"student.csv",BA.ObjectToChar(","),mostCurrent._studentlist,mostCurrent._csvheaders);
 //BA.debugLineNum = 1135;BA.debugLine="LoadStudentData";
_loadstudentdata();
 //BA.debugLineNum = 1137;BA.debugLine="xui.MsgboxAsync(\"Deleted record for \" & student";
_xui.MsgboxAsync(processBA,BA.ObjectToCharSequence("Deleted record for "+_studentname+" successfully."),BA.ObjectToCharSequence("Success"));
 //BA.debugLineNum = 1139;BA.debugLine="editRowIndex = -1";
_editrowindex = (int) (-1);
 //BA.debugLineNum = 1140;BA.debugLine="todelete = False";
_todelete = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 1141;BA.debugLine="Return";
if (true) return "";
 };
 };
 //BA.debugLineNum = 1146;BA.debugLine="pnlmain.RemoveAllViews";
mostCurrent._pnlmain.RemoveAllViews();
 //BA.debugLineNum = 1147;BA.debugLine="pnlmain.LoadLayout(\"CREAT\")";
mostCurrent._pnlmain.LoadLayout("CREAT",mostCurrent.activityBA);
 //BA.debugLineNum = 1149;BA.debugLine="If editRowIndex > -1 Then";
if (_editrowindex>-1) { 
 //BA.debugLineNum = 1150;BA.debugLine="Dim row() As String = StudentList.Get(editRowInd";
_row = (String[])(mostCurrent._studentlist.Get(_editrowindex));
 //BA.debugLineNum = 1151;BA.debugLine="STUDENT_NAME.Text = row(0)";
mostCurrent._student_name.setText(BA.ObjectToCharSequence(_row[(int) (0)]));
 //BA.debugLineNum = 1152;BA.debugLine="STUDENT_ID.Text = row(1)";
mostCurrent._student_id.setText(BA.ObjectToCharSequence(_row[(int) (1)]));
 //BA.debugLineNum = 1155;BA.debugLine="loadspinner";
_loadspinner();
 //BA.debugLineNum = 1158;BA.debugLine="Dim subject As String = row(2)";
_subject = _row[(int) (2)];
 //BA.debugLineNum = 1159;BA.debugLine="For i = 0 To SELECTSUBJECTS.Size - 1";
{
final int step35 = 1;
final int limit35 = (int) (mostCurrent._selectsubjects.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit35 ;_i = _i + step35 ) {
 //BA.debugLineNum = 1160;BA.debugLine="If SELECTSUBJECTS.GetItem(i) = subject Then";
if ((mostCurrent._selectsubjects.GetItem(_i)).equals(_subject)) { 
 //BA.debugLineNum = 1161;BA.debugLine="SELECTSUBJECTS.SelectedIndex = i";
mostCurrent._selectsubjects.setSelectedIndex(_i);
 //BA.debugLineNum = 1162;BA.debugLine="Exit";
if (true) break;
 };
 }
};
 //BA.debugLineNum = 1167;BA.debugLine="Dim activity11 As String = row(3)";
_activity11 = _row[(int) (3)];
 //BA.debugLineNum = 1168;BA.debugLine="For i = 0 To STUDENT_ACTIVITY.Size - 1";
{
final int step42 = 1;
final int limit42 = (int) (mostCurrent._student_activity.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit42 ;_i = _i + step42 ) {
 //BA.debugLineNum = 1169;BA.debugLine="If STUDENT_ACTIVITY.GetItem(i) = activity11 The";
if ((mostCurrent._student_activity.GetItem(_i)).equals(_activity11)) { 
 //BA.debugLineNum = 1170;BA.debugLine="STUDENT_ACTIVITY.SelectedIndex = i";
mostCurrent._student_activity.setSelectedIndex(_i);
 //BA.debugLineNum = 1171;BA.debugLine="Exit";
if (true) break;
 };
 }
};
 //BA.debugLineNum = 1176;BA.debugLine="Dim attendance As String = row(4)";
_attendance = _row[(int) (4)];
 //BA.debugLineNum = 1177;BA.debugLine="CheckBox1.Checked = (attendance = \"present\")";
mostCurrent._checkbox1.setChecked(((_attendance).equals("present")));
 //BA.debugLineNum = 1178;BA.debugLine="CheckBox2.Checked = (attendance = \"late\")";
mostCurrent._checkbox2.setChecked(((_attendance).equals("late")));
 //BA.debugLineNum = 1179;BA.debugLine="CheckBox3.Checked = (attendance = \"absent\")";
mostCurrent._checkbox3.setChecked(((_attendance).equals("absent")));
 //BA.debugLineNum = 1182;BA.debugLine="Dim rate As String = row(5)";
_rate = _row[(int) (5)];
 //BA.debugLineNum = 1183;BA.debugLine="If rate.Contains(\"/\") Then";
if (_rate.contains("/")) { 
 //BA.debugLineNum = 1184;BA.debugLine="Dim parts() As String = Regex.Split(\"/\", rate)";
_parts = anywheresoftware.b4a.keywords.Common.Regex.Split("/",_rate);
 //BA.debugLineNum = 1185;BA.debugLine="If parts.Length = 2 Then";
if (_parts.length==2) { 
 //BA.debugLineNum = 1186;BA.debugLine="EditText1.Text = parts(0)";
mostCurrent._edittext1.setText(BA.ObjectToCharSequence(_parts[(int) (0)]));
 //BA.debugLineNum = 1187;BA.debugLine="EditText2.Text = parts(1)";
mostCurrent._edittext2.setText(BA.ObjectToCharSequence(_parts[(int) (1)]));
 }else {
 //BA.debugLineNum = 1189;BA.debugLine="EditText1.Text = rate";
mostCurrent._edittext1.setText(BA.ObjectToCharSequence(_rate));
 //BA.debugLineNum = 1190;BA.debugLine="EditText2.Text = \"\"";
mostCurrent._edittext2.setText(BA.ObjectToCharSequence(""));
 };
 }else {
 //BA.debugLineNum = 1193;BA.debugLine="EditText1.Text = rate";
mostCurrent._edittext1.setText(BA.ObjectToCharSequence(_rate));
 //BA.debugLineNum = 1194;BA.debugLine="EditText2.Text = \"\"";
mostCurrent._edittext2.setText(BA.ObjectToCharSequence(""));
 };
 };
 //BA.debugLineNum = 1197;BA.debugLine="End Sub";
return "";
}
public static String  _btndashboard_click() throws Exception{
 //BA.debugLineNum = 181;BA.debugLine="Sub btnDashBoard_Click";
 //BA.debugLineNum = 182;BA.debugLine="Drawer.LeftOpen = False";
mostCurrent._drawer._setleftopen /*boolean*/ (anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 183;BA.debugLine="showdashboard";
_showdashboard();
 //BA.debugLineNum = 184;BA.debugLine="End Sub";
return "";
}
public static String  _btnedit_click() throws Exception{
 //BA.debugLineNum = 191;BA.debugLine="Sub btnedit_Click";
 //BA.debugLineNum = 192;BA.debugLine="Drawer.LeftOpen = False";
mostCurrent._drawer._setleftopen /*boolean*/ (anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 193;BA.debugLine="showedit";
_showedit();
 //BA.debugLineNum = 194;BA.debugLine="End Sub";
return "";
}
public static String  _btngrades_click() throws Exception{
 //BA.debugLineNum = 186;BA.debugLine="Sub btnGrades_Click";
 //BA.debugLineNum = 187;BA.debugLine="Drawer.LeftOpen = False";
mostCurrent._drawer._setleftopen /*boolean*/ (anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 188;BA.debugLine="showgrades";
_showgrades();
 //BA.debugLineNum = 189;BA.debugLine="End Sub";
return "";
}
public static String  _btnmenu_click() throws Exception{
 //BA.debugLineNum = 141;BA.debugLine="Sub btnMenu_Click";
 //BA.debugLineNum = 142;BA.debugLine="Drawer.LeftOpen = Not(Drawer.LeftOpen)";
mostCurrent._drawer._setleftopen /*boolean*/ (anywheresoftware.b4a.keywords.Common.Not(mostCurrent._drawer._getleftopen /*boolean*/ ()));
 //BA.debugLineNum = 143;BA.debugLine="End Sub";
return "";
}
public static String  _btnprofile_click() throws Exception{
 //BA.debugLineNum = 196;BA.debugLine="Sub btnProfile_Click";
 //BA.debugLineNum = 197;BA.debugLine="Drawer.LeftOpen = False";
mostCurrent._drawer._setleftopen /*boolean*/ (anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 198;BA.debugLine="showprofile";
_showprofile();
 //BA.debugLineNum = 199;BA.debugLine="End Sub";
return "";
}
public static String  _button1_click() throws Exception{
 //BA.debugLineNum = 864;BA.debugLine="Private Sub Button1_Click";
 //BA.debugLineNum = 866;BA.debugLine="showedit";
_showedit();
 //BA.debugLineNum = 867;BA.debugLine="End Sub";
return "";
}
public static double  _calculatestudentgpa(String _studentname) throws Exception{
double _totalpoints = 0;
int _activitycount = 0;
int _i = 0;
String[] _row = null;
String _currentname = "";
String _ratestring = "";
double _pct = 0;
 //BA.debugLineNum = 311;BA.debugLine="Sub CalculateStudentGPA(studentName As String) As";
 //BA.debugLineNum = 312;BA.debugLine="Dim totalPoints As Double = 0";
_totalpoints = 0;
 //BA.debugLineNum = 313;BA.debugLine="Dim activityCount As Int = 0";
_activitycount = (int) (0);
 //BA.debugLineNum = 316;BA.debugLine="For i = 0 To StudentList.Size - 1";
{
final int step3 = 1;
final int limit3 = (int) (mostCurrent._studentlist.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit3 ;_i = _i + step3 ) {
 //BA.debugLineNum = 317;BA.debugLine="Dim row() As String = StudentList.Get(i)";
_row = (String[])(mostCurrent._studentlist.Get(_i));
 //BA.debugLineNum = 318;BA.debugLine="Dim currentName As String = row(COL_NAME)";
_currentname = _row[_col_name];
 //BA.debugLineNum = 321;BA.debugLine="If currentName = studentName Then";
if ((_currentname).equals(_studentname)) { 
 //BA.debugLineNum = 322;BA.debugLine="Dim rateString As String = row(COL_RATE)";
_ratestring = _row[_col_rate];
 //BA.debugLineNum = 325;BA.debugLine="Dim pct As Double = ComputePercentage(rateStrin";
_pct = _computepercentage(_ratestring);
 //BA.debugLineNum = 328;BA.debugLine="totalPoints = totalPoints + PercentageToGPAPoin";
_totalpoints = _totalpoints+_percentagetogpapoints(_pct);
 //BA.debugLineNum = 329;BA.debugLine="activityCount = activityCount + 1";
_activitycount = (int) (_activitycount+1);
 };
 }
};
 //BA.debugLineNum = 334;BA.debugLine="If activityCount < 3 Then";
if (_activitycount<3) { 
 //BA.debugLineNum = 335;BA.debugLine="Return -1.0 ' Return -1.0 as a flag meaning \"Not";
if (true) return -1.0;
 };
 //BA.debugLineNum = 339;BA.debugLine="Return totalPoints / activityCount";
if (true) return _totalpoints/(double)_activitycount;
 //BA.debugLineNum = 340;BA.debugLine="End Sub";
return 0;
}
public static String  _checkbox1_click() throws Exception{
 //BA.debugLineNum = 1241;BA.debugLine="Private Sub CheckBox1_Click";
 //BA.debugLineNum = 1242;BA.debugLine="If CheckBox1.Checked Then";
if (mostCurrent._checkbox1.getChecked()) { 
 //BA.debugLineNum = 1243;BA.debugLine="CheckBox2.Checked = False";
mostCurrent._checkbox2.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1244;BA.debugLine="CheckBox3.Checked = False";
mostCurrent._checkbox3.setChecked(anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 1246;BA.debugLine="End Sub";
return "";
}
public static String  _checkbox2_click() throws Exception{
 //BA.debugLineNum = 1248;BA.debugLine="Private Sub CheckBox2_Click";
 //BA.debugLineNum = 1249;BA.debugLine="If CheckBox2.Checked Then";
if (mostCurrent._checkbox2.getChecked()) { 
 //BA.debugLineNum = 1250;BA.debugLine="CheckBox1.Checked = False";
mostCurrent._checkbox1.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1251;BA.debugLine="CheckBox3.Checked = False";
mostCurrent._checkbox3.setChecked(anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 1253;BA.debugLine="End Sub";
return "";
}
public static String  _checkbox3_click() throws Exception{
 //BA.debugLineNum = 1255;BA.debugLine="Private Sub CheckBox3_Click";
 //BA.debugLineNum = 1256;BA.debugLine="If CheckBox3.Checked Then";
if (mostCurrent._checkbox3.getChecked()) { 
 //BA.debugLineNum = 1257;BA.debugLine="CheckBox1.Checked = False";
mostCurrent._checkbox1.setChecked(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1258;BA.debugLine="CheckBox2.Checked = False";
mostCurrent._checkbox2.setChecked(anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 1260;BA.debugLine="End Sub";
return "";
}
public static double  _computepercentage(String _rate) throws Exception{
String[] _parts = null;
double _numerator = 0;
double _denominator = 0;
 //BA.debugLineNum = 282;BA.debugLine="Sub ComputePercentage(rate As String) As Double";
 //BA.debugLineNum = 283;BA.debugLine="If rate.Contains(\"/\") = False Then Return 0";
if (_rate.contains("/")==anywheresoftware.b4a.keywords.Common.False) { 
if (true) return 0;};
 //BA.debugLineNum = 284;BA.debugLine="Dim parts() As String = Regex.Split(\"/\", rate)";
_parts = anywheresoftware.b4a.keywords.Common.Regex.Split("/",_rate);
 //BA.debugLineNum = 285;BA.debugLine="If parts.Length <> 2 Then Return 0";
if (_parts.length!=2) { 
if (true) return 0;};
 //BA.debugLineNum = 286;BA.debugLine="Dim numerator As Double = parts(0)";
_numerator = (double)(Double.parseDouble(_parts[(int) (0)]));
 //BA.debugLineNum = 287;BA.debugLine="Dim denominator As Double = parts(1)";
_denominator = (double)(Double.parseDouble(_parts[(int) (1)]));
 //BA.debugLineNum = 288;BA.debugLine="If denominator = 0 Then Return 0";
if (_denominator==0) { 
if (true) return 0;};
 //BA.debugLineNum = 289;BA.debugLine="Return (numerator / denominator) * 100";
if (true) return (_numerator/(double)_denominator)*100;
 //BA.debugLineNum = 290;BA.debugLine="End Sub";
return 0;
}
public static String  _createmenu() throws Exception{
anywheresoftware.b4a.objects.ButtonWrapper _btndashboard = null;
anywheresoftware.b4a.objects.ButtonWrapper _btngrades = null;
anywheresoftware.b4a.objects.ButtonWrapper _btnedit = null;
anywheresoftware.b4a.objects.ButtonWrapper _btnprofile = null;
anywheresoftware.b4a.objects.ButtonWrapper _b = null;
 //BA.debugLineNum = 157;BA.debugLine="Sub CreateMenu";
 //BA.debugLineNum = 158;BA.debugLine="Dim btnDashBoard, btnGrades, btnedit, btnProfile";
_btndashboard = new anywheresoftware.b4a.objects.ButtonWrapper();
_btngrades = new anywheresoftware.b4a.objects.ButtonWrapper();
_btnedit = new anywheresoftware.b4a.objects.ButtonWrapper();
_btnprofile = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 159;BA.debugLine="btnDashBoard.Initialize(\"btnDashBoard\")";
_btndashboard.Initialize(mostCurrent.activityBA,"btnDashBoard");
 //BA.debugLineNum = 160;BA.debugLine="btnDashBoard.Text = \"Dashboard\"";
_btndashboard.setText(BA.ObjectToCharSequence("Dashboard"));
 //BA.debugLineNum = 161;BA.debugLine="btnGrades.Initialize(\"btnGrades\")";
_btngrades.Initialize(mostCurrent.activityBA,"btnGrades");
 //BA.debugLineNum = 162;BA.debugLine="btnGrades.Text = \"Grades\"";
_btngrades.setText(BA.ObjectToCharSequence("Grades"));
 //BA.debugLineNum = 163;BA.debugLine="btnedit.Initialize(\"btnedit\")";
_btnedit.Initialize(mostCurrent.activityBA,"btnedit");
 //BA.debugLineNum = 164;BA.debugLine="btnedit.Text = \"Edit\"";
_btnedit.setText(BA.ObjectToCharSequence("Edit"));
 //BA.debugLineNum = 165;BA.debugLine="btnProfile.Initialize(\"btnProfile\")";
_btnprofile.Initialize(mostCurrent.activityBA,"btnProfile");
 //BA.debugLineNum = 166;BA.debugLine="btnProfile.Text = \"Profile\"";
_btnprofile.setText(BA.ObjectToCharSequence("Profile"));
 //BA.debugLineNum = 168;BA.debugLine="For Each b As Button In Array(btnDashBoard, btnGr";
_b = new anywheresoftware.b4a.objects.ButtonWrapper();
{
final Object[] group10 = new Object[]{(Object)(_btndashboard.getObject()),(Object)(_btngrades.getObject()),(Object)(_btnedit.getObject()),(Object)(_btnprofile.getObject())};
final int groupLen10 = group10.length
;int index10 = 0;
;
for (; index10 < groupLen10;index10++){
_b = (anywheresoftware.b4a.objects.ButtonWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ButtonWrapper(), (android.widget.Button)(group10[index10]));
 //BA.debugLineNum = 169;BA.debugLine="b.TextSize = 16";
_b.setTextSize((float) (16));
 //BA.debugLineNum = 170;BA.debugLine="b.Gravity = Gravity.LEFT + Gravity.CENTER_VERTIC";
_b.setGravity((int) (anywheresoftware.b4a.keywords.Common.Gravity.LEFT+anywheresoftware.b4a.keywords.Common.Gravity.CENTER_VERTICAL));
 //BA.debugLineNum = 171;BA.debugLine="b.Color = Colors.Transparent";
_b.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 172;BA.debugLine="b.TextColor = Colors.White";
_b.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 173;BA.debugLine="pnlmenu.AddView(b, 10dip, 0, 240dip, 50dip)";
mostCurrent._pnlmenu.AddView((android.view.View)(_b.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (240)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
 }
};
 //BA.debugLineNum = 175;BA.debugLine="btnDashBoard.Top = 120dip";
_btndashboard.setTop(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (120)));
 //BA.debugLineNum = 176;BA.debugLine="btnGrades.Top = 180dip";
_btngrades.setTop(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (180)));
 //BA.debugLineNum = 177;BA.debugLine="btnedit.Top = 240dip";
_btnedit.setTop(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (240)));
 //BA.debugLineNum = 178;BA.debugLine="btnProfile.Top = 300dip";
_btnprofile.setTop(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (300)));
 //BA.debugLineNum = 179;BA.debugLine="End Sub";
return "";
}
public static String  _dashbtn_click() throws Exception{
 //BA.debugLineNum = 201;BA.debugLine="Private Sub dashbtn_Click";
 //BA.debugLineNum = 202;BA.debugLine="HighlightTab(dashbtn)";
_highlighttab(mostCurrent._dashbtn);
 //BA.debugLineNum = 203;BA.debugLine="showdashboard";
_showdashboard();
 //BA.debugLineNum = 204;BA.debugLine="End Sub";
return "";
}
public static String  _delete_click() throws Exception{
 //BA.debugLineNum = 1218;BA.debugLine="Private Sub delete_Click";
 //BA.debugLineNum = 1219;BA.debugLine="showselectid = True";
_showselectid = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 1220;BA.debugLine="todelete = True";
_todelete = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 1221;BA.debugLine="showselectids";
_showselectids();
 //BA.debugLineNum = 1222;BA.debugLine="End Sub";
return "";
}
public static String  _displaystudentprofile(String _studentname) throws Exception{
String _id = "";
String _grade = "";
anywheresoftware.b4a.objects.collections.List _toplist = null;
Object[] _entry = null;
String _subject = "";
double _avgscore = 0;
 //BA.debugLineNum = 898;BA.debugLine="Sub DisplayStudentProfile(studentName As String)";
 //BA.debugLineNum = 899;BA.debugLine="If studentName = \"\" Then Return";
if ((_studentname).equals("")) { 
if (true) return "";};
 //BA.debugLineNum = 902;BA.debugLine="Dim id As String = GetStudentID(studentName)";
_id = _getstudentid(_studentname);
 //BA.debugLineNum = 903;BA.debugLine="Dim grade As String = GetStudentGrade(studentName";
_grade = _getstudentgrade(_studentname);
 //BA.debugLineNum = 906;BA.debugLine="studid.Text = id";
mostCurrent._studid.setText(BA.ObjectToCharSequence(_id));
 //BA.debugLineNum = 907;BA.debugLine="studname.Text = studentName";
mostCurrent._studname.setText(BA.ObjectToCharSequence(_studentname));
 //BA.debugLineNum = 910;BA.debugLine="gpaslt.Text = GetGPADisplayText(studentName)";
mostCurrent._gpaslt.setText(BA.ObjectToCharSequence(_getgpadisplaytext(_studentname)));
 //BA.debugLineNum = 911;BA.debugLine="anttslt.Text = GetAverageAttendanceScoredisplay(s";
mostCurrent._anttslt.setText(BA.ObjectToCharSequence(_getaverageattendancescoredisplay(_studentname)));
 //BA.debugLineNum = 912;BA.debugLine="hstslt.Text = grade";
mostCurrent._hstslt.setText(BA.ObjectToCharSequence(_grade));
 //BA.debugLineNum = 915;BA.debugLine="Top1sub.Text = \"-\"";
mostCurrent._top1sub.setText(BA.ObjectToCharSequence("-"));
 //BA.debugLineNum = 916;BA.debugLine="Top1grd.Text = \"-\"";
mostCurrent._top1grd.setText(BA.ObjectToCharSequence("-"));
 //BA.debugLineNum = 917;BA.debugLine="Top2sub.Text = \"-\"";
mostCurrent._top2sub.setText(BA.ObjectToCharSequence("-"));
 //BA.debugLineNum = 918;BA.debugLine="Top2grd.Text = \"-\"";
mostCurrent._top2grd.setText(BA.ObjectToCharSequence("-"));
 //BA.debugLineNum = 919;BA.debugLine="Top3sub.Text = \"-\"";
mostCurrent._top3sub.setText(BA.ObjectToCharSequence("-"));
 //BA.debugLineNum = 920;BA.debugLine="Top3grd.Text = \"-\"";
mostCurrent._top3grd.setText(BA.ObjectToCharSequence("-"));
 //BA.debugLineNum = 923;BA.debugLine="Dim topList As List = GetTop3Subjects(studentName";
_toplist = new anywheresoftware.b4a.objects.collections.List();
_toplist = _gettop3subjects(_studentname);
 //BA.debugLineNum = 925;BA.debugLine="If topList.Size > 0 Then";
if (_toplist.getSize()>0) { 
 //BA.debugLineNum = 926;BA.debugLine="Dim entry() As Object = topList.Get(0)";
_entry = (Object[])(_toplist.Get((int) (0)));
 //BA.debugLineNum = 927;BA.debugLine="Dim subject As String = entry(0)";
_subject = BA.ObjectToString(_entry[(int) (0)]);
 //BA.debugLineNum = 928;BA.debugLine="Dim avgScore As Double = entry(1)";
_avgscore = (double)(BA.ObjectToNumber(_entry[(int) (1)]));
 //BA.debugLineNum = 929;BA.debugLine="Top1sub.Text = subject";
mostCurrent._top1sub.setText(BA.ObjectToCharSequence(_subject));
 //BA.debugLineNum = 930;BA.debugLine="Top1grd.Text = PercentageToGrade(avgScore) & \" (";
mostCurrent._top1grd.setText(BA.ObjectToCharSequence(_percentagetograde(_avgscore)+" ("+anywheresoftware.b4a.keywords.Common.NumberFormat2(_avgscore,(int) (1),(int) (0),(int) (1),anywheresoftware.b4a.keywords.Common.False)+"%)"));
 };
 //BA.debugLineNum = 933;BA.debugLine="If topList.Size > 1 Then";
if (_toplist.getSize()>1) { 
 //BA.debugLineNum = 934;BA.debugLine="Dim entry() As Object = topList.Get(1)";
_entry = (Object[])(_toplist.Get((int) (1)));
 //BA.debugLineNum = 935;BA.debugLine="Dim subject As String = entry(0)";
_subject = BA.ObjectToString(_entry[(int) (0)]);
 //BA.debugLineNum = 936;BA.debugLine="Dim avgScore As Double = entry(1)";
_avgscore = (double)(BA.ObjectToNumber(_entry[(int) (1)]));
 //BA.debugLineNum = 937;BA.debugLine="Top2sub.Text = subject";
mostCurrent._top2sub.setText(BA.ObjectToCharSequence(_subject));
 //BA.debugLineNum = 938;BA.debugLine="Top2grd.Text = PercentageToGrade(avgScore) & \" (";
mostCurrent._top2grd.setText(BA.ObjectToCharSequence(_percentagetograde(_avgscore)+" ("+anywheresoftware.b4a.keywords.Common.NumberFormat2(_avgscore,(int) (1),(int) (0),(int) (1),anywheresoftware.b4a.keywords.Common.False)+"%)"));
 };
 //BA.debugLineNum = 941;BA.debugLine="If topList.Size > 2 Then";
if (_toplist.getSize()>2) { 
 //BA.debugLineNum = 942;BA.debugLine="Dim entry() As Object = topList.Get(2)";
_entry = (Object[])(_toplist.Get((int) (2)));
 //BA.debugLineNum = 943;BA.debugLine="Dim subject As String = entry(0)";
_subject = BA.ObjectToString(_entry[(int) (0)]);
 //BA.debugLineNum = 944;BA.debugLine="Dim avgScore As Double = entry(1)";
_avgscore = (double)(BA.ObjectToNumber(_entry[(int) (1)]));
 //BA.debugLineNum = 945;BA.debugLine="Top3sub.Text = subject";
mostCurrent._top3sub.setText(BA.ObjectToCharSequence(_subject));
 //BA.debugLineNum = 946;BA.debugLine="Top3grd.Text = PercentageToGrade(avgScore) & \" (";
mostCurrent._top3grd.setText(BA.ObjectToCharSequence(_percentagetograde(_avgscore)+" ("+anywheresoftware.b4a.keywords.Common.NumberFormat2(_avgscore,(int) (1),(int) (0),(int) (1),anywheresoftware.b4a.keywords.Common.False)+"%)"));
 };
 //BA.debugLineNum = 948;BA.debugLine="End Sub";
return "";
}
public static String  _editbtn_click() throws Exception{
 //BA.debugLineNum = 211;BA.debugLine="Private Sub editbtn_Click";
 //BA.debugLineNum = 212;BA.debugLine="HighlightTab(editbtn)";
_highlighttab(mostCurrent._editbtn);
 //BA.debugLineNum = 213;BA.debugLine="showedit";
_showedit();
 //BA.debugLineNum = 214;BA.debugLine="End Sub";
return "";
}
public static String  _generategraph() throws Exception{
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
 //BA.debugLineNum = 569;BA.debugLine="Sub generategraph";
 //BA.debugLineNum = 570;BA.debugLine="Dim names As List = GetUniqueStudentNames";
_names = new anywheresoftware.b4a.objects.collections.List();
_names = _getuniquestudentnames();
 //BA.debugLineNum = 571;BA.debugLine="If names.Size = 0 Then Return";
if (_names.getSize()==0) { 
if (true) return "";};
 //BA.debugLineNum = 573;BA.debugLine="Dim numCols As Int = names.Size";
_numcols = _names.getSize();
 //BA.debugLineNum = 574;BA.debugLine="Dim colWidth As Int = datapnl.Width / numCols";
_colwidth = (int) (mostCurrent._datapnl.getWidth()/(double)_numcols);
 //BA.debugLineNum = 577;BA.debugLine="Dim barColors() As Int = Array As Int( _ 		Colors";
_barcolors = new int[]{anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (66),(int) (133),(int) (244)),anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (234),(int) (67),(int) (53)),anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (251),(int) (188),(int) (4)),anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (52),(int) (168),(int) (83)),anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (156),(int) (39),(int) (176))};
 //BA.debugLineNum = 585;BA.debugLine="Dim maxVal As Double = 0";
_maxval = 0;
 //BA.debugLineNum = 586;BA.debugLine="For i = 0 To names.Size - 1";
{
final int step7 = 1;
final int limit7 = (int) (_names.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit7 ;_i = _i + step7 ) {
 //BA.debugLineNum = 587;BA.debugLine="Dim avg As Double = GetStudentAvgPercentage(name";
_avg = _getstudentavgpercentage(BA.ObjectToString(_names.Get(_i)));
 //BA.debugLineNum = 588;BA.debugLine="If avg > maxVal Then maxVal = avg";
if (_avg>_maxval) { 
_maxval = _avg;};
 }
};
 //BA.debugLineNum = 590;BA.debugLine="If maxVal = 0 Then maxVal = 100";
if (_maxval==0) { 
_maxval = 100;};
 //BA.debugLineNum = 592;BA.debugLine="Dim RowHeight As Int = 40dip";
_rowheight = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40));
 //BA.debugLineNum = 593;BA.debugLine="Dim valueLabelHeight As Int = 30dip";
_valuelabelheight = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30));
 //BA.debugLineNum = 594;BA.debugLine="Dim chartAreaHeight As Int = datapnl.Height - Row";
_chartareaheight = (int) (mostCurrent._datapnl.getHeight()-_rowheight-_valuelabelheight);
 //BA.debugLineNum = 595;BA.debugLine="Dim barPadding As Int = 10dip";
_barpadding = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10));
 //BA.debugLineNum = 597;BA.debugLine="For i = 0 To numCols - 1";
{
final int step16 = 1;
final int limit16 = (int) (_numcols-1);
_i = (int) (0) ;
for (;_i <= limit16 ;_i = _i + step16 ) {
 //BA.debugLineNum = 598;BA.debugLine="Dim studentName As String = names.Get(i)";
_studentname = BA.ObjectToString(_names.Get(_i));
 //BA.debugLineNum = 599;BA.debugLine="Dim avgPct As Double = GetStudentAvgPercentage(s";
_avgpct = _getstudentavgpercentage(_studentname);
 //BA.debugLineNum = 602;BA.debugLine="Dim barHeight As Int";
_barheight = 0;
 //BA.debugLineNum = 603;BA.debugLine="If maxVal > 0 Then";
if (_maxval>0) { 
 //BA.debugLineNum = 604;BA.debugLine="barHeight = (avgPct / maxVal) * chartAreaHeight";
_barheight = (int) ((_avgpct/(double)_maxval)*_chartareaheight);
 }else {
 //BA.debugLineNum = 606;BA.debugLine="barHeight = 0";
_barheight = (int) (0);
 };
 //BA.debugLineNum = 609;BA.debugLine="Dim barTop As Int = datapnl.Height - RowHeight -";
_bartop = (int) (mostCurrent._datapnl.getHeight()-_rowheight-_barheight);
 //BA.debugLineNum = 611;BA.debugLine="Dim pnlBar As Panel";
_pnlbar = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 612;BA.debugLine="pnlBar.Initialize(\"pnlBar\")";
_pnlbar.Initialize(mostCurrent.activityBA,"pnlBar");
 //BA.debugLineNum = 613;BA.debugLine="pnlBar.Color = barColors(i Mod barColors.Length)";
_pnlbar.setColor(_barcolors[(int) (_i%_barcolors.length)]);
 //BA.debugLineNum = 614;BA.debugLine="pnlBar.Tag = i";
_pnlbar.setTag((Object)(_i));
 //BA.debugLineNum = 615;BA.debugLine="datapnl.AddView(pnlBar, _ 			(colWidth * i) + ba";
mostCurrent._datapnl.AddView((android.view.View)(_pnlbar.getObject()),(int) ((_colwidth*_i)+_barpadding),_bartop,(int) (_colwidth-(_barpadding*2)),_barheight);
 //BA.debugLineNum = 622;BA.debugLine="Dim lblValue As Label";
_lblvalue = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 623;BA.debugLine="lblValue.Initialize(\"lblValue\")";
_lblvalue.Initialize(mostCurrent.activityBA,"lblValue");
 //BA.debugLineNum = 624;BA.debugLine="lblValue.Text = Round2(avgPct, 0) & \"%\"";
_lblvalue.setText(BA.ObjectToCharSequence(BA.NumberToString(anywheresoftware.b4a.keywords.Common.Round2(_avgpct,(int) (0)))+"%"));
 //BA.debugLineNum = 625;BA.debugLine="lblValue.Gravity = Gravity.CENTER";
_lblvalue.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 626;BA.debugLine="lblValue.TextSize = 12";
_lblvalue.setTextSize((float) (12));
 //BA.debugLineNum = 627;BA.debugLine="lblValue.TextColor = Colors.Black";
_lblvalue.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 628;BA.debugLine="datapnl.AddView(lblValue, _ 			colWidth * i, _";
mostCurrent._datapnl.AddView((android.view.View)(_lblvalue.getObject()),(int) (_colwidth*_i),(int) (_bartop-_valuelabelheight),_colwidth,_valuelabelheight);
 //BA.debugLineNum = 635;BA.debugLine="Dim l As Label";
_l = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 636;BA.debugLine="l.Initialize(\"labels\")";
_l.Initialize(mostCurrent.activityBA,"labels");
 //BA.debugLineNum = 638;BA.debugLine="Dim displayName As String = studentName";
_displayname = _studentname;
 //BA.debugLineNum = 639;BA.debugLine="If displayName.Length > 8 Then";
if (_displayname.length()>8) { 
 //BA.debugLineNum = 640;BA.debugLine="Dim nameParts() As String = Regex.Split(\" \", di";
_nameparts = anywheresoftware.b4a.keywords.Common.Regex.Split(" ",_displayname);
 //BA.debugLineNum = 641;BA.debugLine="displayName = nameParts(0)";
_displayname = _nameparts[(int) (0)];
 };
 //BA.debugLineNum = 643;BA.debugLine="l.Text = displayName";
_l.setText(BA.ObjectToCharSequence(_displayname));
 //BA.debugLineNum = 644;BA.debugLine="l.Gravity = Gravity.CENTER";
_l.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 645;BA.debugLine="l.TextSize = 10";
_l.setTextSize((float) (10));
 //BA.debugLineNum = 646;BA.debugLine="l.Color = 0x00ffffff";
_l.setColor(((int)0x00ffffff));
 //BA.debugLineNum = 647;BA.debugLine="l.TextColor = Colors.Black";
_l.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 648;BA.debugLine="l.Padding = Array As Int(0dip, 5dip, 0dip, 5dip)";
_l.setPadding(new int[]{anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5))});
 //BA.debugLineNum = 649;BA.debugLine="l.Tag = i";
_l.setTag((Object)(_i));
 //BA.debugLineNum = 650;BA.debugLine="datapnl.AddView(l, _ 			colWidth * i, _ 			datap";
mostCurrent._datapnl.AddView((android.view.View)(_l.getObject()),(int) (_colwidth*_i),(int) (mostCurrent._datapnl.getHeight()-_rowheight),_colwidth,_rowheight);
 }
};
 //BA.debugLineNum = 658;BA.debugLine="Dim studentAvgs As List";
_studentavgs = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 659;BA.debugLine="studentAvgs.Initialize";
_studentavgs.Initialize();
 //BA.debugLineNum = 660;BA.debugLine="For i = 0 To names.Size - 1";
{
final int step56 = 1;
final int limit56 = (int) (_names.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit56 ;_i = _i + step56 ) {
 //BA.debugLineNum = 661;BA.debugLine="Dim avg As Double = GetStudentAvgPercentage(name";
_avg = _getstudentavgpercentage(BA.ObjectToString(_names.Get(_i)));
 //BA.debugLineNum = 662;BA.debugLine="studentAvgs.Add(Array As Object(names.Get(i), av";
_studentavgs.Add((Object)(new Object[]{_names.Get(_i),(Object)(_avg)}));
 }
};
 //BA.debugLineNum = 666;BA.debugLine="Dim topNames(3) As String";
_topnames = new String[(int) (3)];
java.util.Arrays.fill(_topnames,"");
 //BA.debugLineNum = 667;BA.debugLine="Dim topRates(3) As Double";
_toprates = new double[(int) (3)];
;
 //BA.debugLineNum = 668;BA.debugLine="Dim topActivities(3) As String";
_topactivities = new String[(int) (3)];
java.util.Arrays.fill(_topactivities,"");
 //BA.debugLineNum = 670;BA.debugLine="For rank = 0 To Min(2, studentAvgs.Size - 1)";
{
final int step63 = 1;
final int limit63 = (int) (anywheresoftware.b4a.keywords.Common.Min(2,_studentavgs.getSize()-1));
_rank = (int) (0) ;
for (;_rank <= limit63 ;_rank = _rank + step63 ) {
 //BA.debugLineNum = 671;BA.debugLine="Dim bestIdx As Int = -1";
_bestidx = (int) (-1);
 //BA.debugLineNum = 672;BA.debugLine="Dim bestVal As Double = -1";
_bestval = -1;
 //BA.debugLineNum = 673;BA.debugLine="For j = 0 To studentAvgs.Size - 1";
{
final int step66 = 1;
final int limit66 = (int) (_studentavgs.getSize()-1);
_j = (int) (0) ;
for (;_j <= limit66 ;_j = _j + step66 ) {
 //BA.debugLineNum = 674;BA.debugLine="Dim entry() As Object = studentAvgs.Get(j)";
_entry = (Object[])(_studentavgs.Get(_j));
 //BA.debugLineNum = 675;BA.debugLine="If entry(1) > bestVal Then";
if ((double)(BA.ObjectToNumber(_entry[(int) (1)]))>_bestval) { 
 //BA.debugLineNum = 676;BA.debugLine="bestVal = entry(1)";
_bestval = (double)(BA.ObjectToNumber(_entry[(int) (1)]));
 //BA.debugLineNum = 677;BA.debugLine="bestIdx = j";
_bestidx = _j;
 };
 }
};
 //BA.debugLineNum = 680;BA.debugLine="If bestIdx >= 0 Then";
if (_bestidx>=0) { 
 //BA.debugLineNum = 681;BA.debugLine="Dim bestEntry() As Object = studentAvgs.Get(bes";
_bestentry = (Object[])(_studentavgs.Get(_bestidx));
 //BA.debugLineNum = 682;BA.debugLine="topNames(rank) = bestEntry(0)";
_topnames[_rank] = BA.ObjectToString(_bestentry[(int) (0)]);
 //BA.debugLineNum = 683;BA.debugLine="topRates(rank) = bestEntry(1)";
_toprates[_rank] = (double)(BA.ObjectToNumber(_bestentry[(int) (1)]));
 //BA.debugLineNum = 684;BA.debugLine="topActivities(rank) = GetStudentTopActivity(bes";
_topactivities[_rank] = _getstudenttopactivity(BA.ObjectToString(_bestentry[(int) (0)]));
 //BA.debugLineNum = 685;BA.debugLine="studentAvgs.RemoveAt(bestIdx) ' remove so next";
_studentavgs.RemoveAt(_bestidx);
 };
 }
};
 //BA.debugLineNum = 690;BA.debugLine="If topNames(0) <> \"\" Then";
if ((_topnames[(int) (0)]).equals("") == false) { 
 //BA.debugLineNum = 691;BA.debugLine="NAME1.Text = topNames(0)";
mostCurrent._name1.setText(BA.ObjectToCharSequence(_topnames[(int) (0)]));
 //BA.debugLineNum = 692;BA.debugLine="RATE1.Text = Round2(topRates(0), 1) & \"%\"";
mostCurrent._rate1.setText(BA.ObjectToCharSequence(BA.NumberToString(anywheresoftware.b4a.keywords.Common.Round2(_toprates[(int) (0)],(int) (1)))+"%"));
 //BA.debugLineNum = 693;BA.debugLine="ACTIVITY1.Text = topActivities(0)";
mostCurrent._activity1.setText(BA.ObjectToCharSequence(_topactivities[(int) (0)]));
 };
 //BA.debugLineNum = 695;BA.debugLine="If topNames(1) <> \"\" Then";
if ((_topnames[(int) (1)]).equals("") == false) { 
 //BA.debugLineNum = 696;BA.debugLine="NAME2.Text = topNames(1)";
mostCurrent._name2.setText(BA.ObjectToCharSequence(_topnames[(int) (1)]));
 //BA.debugLineNum = 697;BA.debugLine="RATE2.Text = Round2(topRates(1), 1) & \"%\"";
mostCurrent._rate2.setText(BA.ObjectToCharSequence(BA.NumberToString(anywheresoftware.b4a.keywords.Common.Round2(_toprates[(int) (1)],(int) (1)))+"%"));
 //BA.debugLineNum = 698;BA.debugLine="ACTIVITY2.Text = topActivities(1)";
mostCurrent._activity2.setText(BA.ObjectToCharSequence(_topactivities[(int) (1)]));
 };
 //BA.debugLineNum = 700;BA.debugLine="If topNames(2) <> \"\" Then";
if ((_topnames[(int) (2)]).equals("") == false) { 
 //BA.debugLineNum = 701;BA.debugLine="NAME3.Text = topNames(2)";
mostCurrent._name3.setText(BA.ObjectToCharSequence(_topnames[(int) (2)]));
 //BA.debugLineNum = 702;BA.debugLine="RATE3.Text = Round2(topRates(2), 1) & \"%\"";
mostCurrent._rate3.setText(BA.ObjectToCharSequence(BA.NumberToString(anywheresoftware.b4a.keywords.Common.Round2(_toprates[(int) (2)],(int) (1)))+"%"));
 //BA.debugLineNum = 703;BA.debugLine="ACTIVITY3.Text = topActivities(2)";
mostCurrent._activity3.setText(BA.ObjectToCharSequence(_topactivities[(int) (2)]));
 };
 //BA.debugLineNum = 705;BA.debugLine="End Sub";
return "";
}
public static String  _getactivity(String _studentname) throws Exception{
String _activities = "";
int _i = 0;
String[] _row = null;
String _name = "";
String _activitys = "";
 //BA.debugLineNum = 527;BA.debugLine="Sub getActivity(studentName As String) As String";
 //BA.debugLineNum = 528;BA.debugLine="Dim activities As String = \"\"";
_activities = "";
 //BA.debugLineNum = 529;BA.debugLine="For i = 0 To StudentList.Size - 1";
{
final int step2 = 1;
final int limit2 = (int) (mostCurrent._studentlist.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit2 ;_i = _i + step2 ) {
 //BA.debugLineNum = 530;BA.debugLine="Dim row() As String = StudentList.Get(i)";
_row = (String[])(mostCurrent._studentlist.Get(_i));
 //BA.debugLineNum = 531;BA.debugLine="Dim name As String = row(COL_NAME) ' compare aga";
_name = _row[_col_name];
 //BA.debugLineNum = 532;BA.debugLine="If name = studentName Then";
if ((_name).equals(_studentname)) { 
 //BA.debugLineNum = 533;BA.debugLine="Dim Activitys As String = row(COL_ACTIVITY)";
_activitys = _row[_col_activity];
 //BA.debugLineNum = 534;BA.debugLine="If activities.IndexOf(Activitys) = -1 Then";
if (_activities.indexOf(_activitys)==-1) { 
 //BA.debugLineNum = 535;BA.debugLine="activities = Activitys";
_activities = _activitys;
 };
 };
 }
};
 //BA.debugLineNum = 539;BA.debugLine="Return activities";
if (true) return _activities;
 //BA.debugLineNum = 540;BA.debugLine="End Sub";
return "";
}
public static double  _getaverageattendancescore(String _studentname) throws Exception{
int _totalpoints = 0;
int _count = 0;
int _i = 0;
String[] _row = null;
String _name = "";
String _status = "";
 //BA.debugLineNum = 354;BA.debugLine="Sub GetAverageAttendanceScore(studentName As Strin";
 //BA.debugLineNum = 355;BA.debugLine="Dim totalPoints As Int = 0";
_totalpoints = (int) (0);
 //BA.debugLineNum = 356;BA.debugLine="Dim count As Int = 0";
_count = (int) (0);
 //BA.debugLineNum = 358;BA.debugLine="For i = 0 To StudentList.Size - 1";
{
final int step3 = 1;
final int limit3 = (int) (mostCurrent._studentlist.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit3 ;_i = _i + step3 ) {
 //BA.debugLineNum = 359;BA.debugLine="Dim row() As String = StudentList.Get(i)";
_row = (String[])(mostCurrent._studentlist.Get(_i));
 //BA.debugLineNum = 360;BA.debugLine="Dim name As String = row(COL_NAME)";
_name = _row[_col_name];
 //BA.debugLineNum = 362;BA.debugLine="If name = studentName Then";
if ((_name).equals(_studentname)) { 
 //BA.debugLineNum = 363;BA.debugLine="Dim status As String = row(4).Trim.ToLowerCase";
_status = _row[(int) (4)].trim().toLowerCase(anywheresoftware.b4a.keywords.Common.stringLocale);
 //BA.debugLineNum = 366;BA.debugLine="Select Case status";
switch (BA.switchObjectToInt(_status,"present","late","absent")) {
case 0: {
 //BA.debugLineNum = 368;BA.debugLine="totalPoints = totalPoints + 10";
_totalpoints = (int) (_totalpoints+10);
 break; }
case 1: {
 //BA.debugLineNum = 370;BA.debugLine="totalPoints = totalPoints + 5";
_totalpoints = (int) (_totalpoints+5);
 break; }
case 2: {
 //BA.debugLineNum = 372;BA.debugLine="totalPoints = totalPoints + 0";
_totalpoints = (int) (_totalpoints+0);
 break; }
}
;
 //BA.debugLineNum = 374;BA.debugLine="count = count + 1";
_count = (int) (_count+1);
 };
 }
};
 //BA.debugLineNum = 379;BA.debugLine="If count = 0 Then Return 0";
if (_count==0) { 
if (true) return 0;};
 //BA.debugLineNum = 381;BA.debugLine="Return totalPoints / count";
if (true) return _totalpoints/(double)_count;
 //BA.debugLineNum = 382;BA.debugLine="End Sub";
return 0;
}
public static String  _getaverageattendancescoredisplay(String _studentname) throws Exception{
double _aas = 0;
String _disply = "";
 //BA.debugLineNum = 384;BA.debugLine="Sub GetAverageAttendanceScoredisplay(studentName A";
 //BA.debugLineNum = 385;BA.debugLine="Dim aas As Double = GetAverageAttendanceScore(stu";
_aas = _getaverageattendancescore(_studentname);
 //BA.debugLineNum = 386;BA.debugLine="Dim disply As String";
_disply = "";
 //BA.debugLineNum = 388;BA.debugLine="Select Case True";
switch (BA.switchObjectToInt(anywheresoftware.b4a.keywords.Common.True,_aas==10,_aas>=9.0,_aas>=8.5,_aas>=8.0,_aas>=7.0,_aas>=6.0,_aas>=5.0,_aas>=4.0,_aas>=3.5,_aas>=3.0,_aas>=1.0)) {
case 0: {
 //BA.debugLineNum = 390;BA.debugLine="disply = \"A+\"";
_disply = "A+";
 break; }
case 1: {
 //BA.debugLineNum = 392;BA.debugLine="disply = \"A\"";
_disply = "A";
 break; }
case 2: {
 //BA.debugLineNum = 394;BA.debugLine="disply = \"A-\"";
_disply = "A-";
 break; }
case 3: {
 //BA.debugLineNum = 396;BA.debugLine="disply = \"B+\"";
_disply = "B+";
 break; }
case 4: {
 //BA.debugLineNum = 398;BA.debugLine="disply = \"B\"";
_disply = "B";
 break; }
case 5: {
 //BA.debugLineNum = 400;BA.debugLine="disply = \"B-\"";
_disply = "B-";
 break; }
case 6: {
 //BA.debugLineNum = 402;BA.debugLine="disply = \"C+\"";
_disply = "C+";
 break; }
case 7: {
 //BA.debugLineNum = 404;BA.debugLine="disply = \"C\"";
_disply = "C";
 break; }
case 8: {
 //BA.debugLineNum = 406;BA.debugLine="disply = \"D+\"";
_disply = "D+";
 break; }
case 9: {
 //BA.debugLineNum = 408;BA.debugLine="disply = \"D\"";
_disply = "D";
 break; }
case 10: {
 //BA.debugLineNum = 410;BA.debugLine="disply = \"E\"";
_disply = "E";
 break; }
default: {
 //BA.debugLineNum = 412;BA.debugLine="disply = \"F\"";
_disply = "F";
 break; }
}
;
 //BA.debugLineNum = 415;BA.debugLine="Return disply";
if (true) return _disply;
 //BA.debugLineNum = 416;BA.debugLine="End Sub";
return "";
}
public static String  _getgpadisplaytext(String _studentname) throws Exception{
double _gpa = 0;
 //BA.debugLineNum = 342;BA.debugLine="Sub GetGPADisplayText(studentName As String) As St";
 //BA.debugLineNum = 343;BA.debugLine="Dim gpa As Double = CalculateStudentGPA(studentNa";
_gpa = _calculatestudentgpa(_studentname);
 //BA.debugLineNum = 345;BA.debugLine="If gpa = -1.0 Then";
if (_gpa==-1.0) { 
 //BA.debugLineNum = 346;BA.debugLine="Return \"N/A (Min 3 activities required)\"";
if (true) return "N/A (Min 3 activities required)";
 }else {
 //BA.debugLineNum = 349;BA.debugLine="Return NumberFormat2(gpa, 1, 2, 2, False)";
if (true) return anywheresoftware.b4a.keywords.Common.NumberFormat2(_gpa,(int) (1),(int) (2),(int) (2),anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 351;BA.debugLine="End Sub";
return "";
}
public static double  _getstudentavgpercentage(String _studentname) throws Exception{
double _total = 0;
int _count = 0;
int _i = 0;
String[] _row = null;
 //BA.debugLineNum = 498;BA.debugLine="Sub GetStudentAvgPercentage(studentname As String)";
 //BA.debugLineNum = 499;BA.debugLine="Dim total As Double = 0";
_total = 0;
 //BA.debugLineNum = 500;BA.debugLine="Dim count As Int = 0";
_count = (int) (0);
 //BA.debugLineNum = 501;BA.debugLine="For i = 0 To StudentList.Size - 1";
{
final int step3 = 1;
final int limit3 = (int) (mostCurrent._studentlist.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit3 ;_i = _i + step3 ) {
 //BA.debugLineNum = 502;BA.debugLine="Dim row() As String = StudentList.Get(i)";
_row = (String[])(mostCurrent._studentlist.Get(_i));
 //BA.debugLineNum = 503;BA.debugLine="If row(COL_NAME) = studentname Then";
if ((_row[_col_name]).equals(_studentname)) { 
 //BA.debugLineNum = 504;BA.debugLine="total = total + ComputePercentage(row(COL_RATE)";
_total = _total+_computepercentage(_row[_col_rate]);
 //BA.debugLineNum = 505;BA.debugLine="count = count + 1";
_count = (int) (_count+1);
 };
 }
};
 //BA.debugLineNum = 508;BA.debugLine="If count = 0 Then Return 0";
if (_count==0) { 
if (true) return 0;};
 //BA.debugLineNum = 509;BA.debugLine="Return total / count";
if (true) return _total/(double)_count;
 //BA.debugLineNum = 510;BA.debugLine="End Sub";
return 0;
}
public static String  _getstudentgrade(String _studentname) throws Exception{
 //BA.debugLineNum = 513;BA.debugLine="Sub GetStudentGrade(studentname As String) As Stri";
 //BA.debugLineNum = 514;BA.debugLine="Return PercentageToGrade(GetStudentAvgPercentage(";
if (true) return _percentagetograde(_getstudentavgpercentage(_studentname));
 //BA.debugLineNum = 515;BA.debugLine="End Sub";
return "";
}
public static String  _getstudentid(String _studentname) throws Exception{
int _i = 0;
String[] _row = null;
 //BA.debugLineNum = 542;BA.debugLine="Sub GetStudentID(studentName As String) As String";
 //BA.debugLineNum = 543;BA.debugLine="For i = 0 To StudentList.Size - 1";
{
final int step1 = 1;
final int limit1 = (int) (mostCurrent._studentlist.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit1 ;_i = _i + step1 ) {
 //BA.debugLineNum = 544;BA.debugLine="Dim row() As String = StudentList.Get(i)";
_row = (String[])(mostCurrent._studentlist.Get(_i));
 //BA.debugLineNum = 545;BA.debugLine="If row(COL_NAME) = studentName Then";
if ((_row[_col_name]).equals(_studentname)) { 
 //BA.debugLineNum = 546;BA.debugLine="Return row(1) ' ID is in column 1";
if (true) return _row[(int) (1)];
 };
 }
};
 //BA.debugLineNum = 549;BA.debugLine="Return \"\"";
if (true) return "";
 //BA.debugLineNum = 550;BA.debugLine="End Sub";
return "";
}
public static String  _getstudenttopactivity(String _studentname) throws Exception{
 //BA.debugLineNum = 552;BA.debugLine="Sub GetStudentTopActivity(studentName As String) A";
 //BA.debugLineNum = 556;BA.debugLine="Return getActivity(studentName)";
if (true) return _getactivity(_studentname);
 //BA.debugLineNum = 557;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.collections.List  _gettop3subjects(String _studentname) throws Exception{
anywheresoftware.b4a.objects.collections.Map _subjectsums = null;
anywheresoftware.b4a.objects.collections.Map _subjectcounts = null;
int _i = 0;
String[] _row = null;
String _name = "";
String _subject = "";
double _pct = 0;
double _currentsum = 0;
int _currentcount = 0;
anywheresoftware.b4a.objects.collections.List _subjectavgs = null;
double _totalscore = 0;
int _count = 0;
double _avg = 0;
anywheresoftware.b4a.objects.collections.List _top3 = null;
int _rank = 0;
int _bestidx = 0;
double _bestval = 0;
int _j = 0;
Object[] _entry = null;
double _val = 0;
 //BA.debugLineNum = 420;BA.debugLine="Sub GetTop3Subjects(studentName As String) As List";
 //BA.debugLineNum = 421;BA.debugLine="Dim subjectSums As Map";
_subjectsums = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 422;BA.debugLine="Dim subjectCounts As Map";
_subjectcounts = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 423;BA.debugLine="subjectSums.Initialize";
_subjectsums.Initialize();
 //BA.debugLineNum = 424;BA.debugLine="subjectCounts.Initialize";
_subjectcounts.Initialize();
 //BA.debugLineNum = 427;BA.debugLine="For i = 0 To StudentList.Size - 1";
{
final int step5 = 1;
final int limit5 = (int) (mostCurrent._studentlist.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit5 ;_i = _i + step5 ) {
 //BA.debugLineNum = 428;BA.debugLine="Dim row() As String = StudentList.Get(i)";
_row = (String[])(mostCurrent._studentlist.Get(_i));
 //BA.debugLineNum = 429;BA.debugLine="Dim name As String = row(COL_NAME)";
_name = _row[_col_name];
 //BA.debugLineNum = 431;BA.debugLine="If name = studentName Then";
if ((_name).equals(_studentname)) { 
 //BA.debugLineNum = 432;BA.debugLine="Dim subject As String = row(2) ' Subject is at";
_subject = _row[(int) (2)];
 //BA.debugLineNum = 433;BA.debugLine="Dim pct As Double = ComputePercentage(row(5)) '";
_pct = _computepercentage(_row[(int) (5)]);
 //BA.debugLineNum = 436;BA.debugLine="Dim currentSum As Double = 0";
_currentsum = 0;
 //BA.debugLineNum = 437;BA.debugLine="If subjectSums.ContainsKey(subject) Then curren";
if (_subjectsums.ContainsKey((Object)(_subject))) { 
_currentsum = (double)(BA.ObjectToNumber(_subjectsums.Get((Object)(_subject))));};
 //BA.debugLineNum = 438;BA.debugLine="subjectSums.Put(subject, currentSum + pct)";
_subjectsums.Put((Object)(_subject),(Object)(_currentsum+_pct));
 //BA.debugLineNum = 441;BA.debugLine="Dim currentCount As Int = 0";
_currentcount = (int) (0);
 //BA.debugLineNum = 442;BA.debugLine="If subjectCounts.ContainsKey(subject) Then curr";
if (_subjectcounts.ContainsKey((Object)(_subject))) { 
_currentcount = (int)(BA.ObjectToNumber(_subjectcounts.Get((Object)(_subject))));};
 //BA.debugLineNum = 443;BA.debugLine="subjectCounts.Put(subject, currentCount + 1)";
_subjectcounts.Put((Object)(_subject),(Object)(_currentcount+1));
 };
 }
};
 //BA.debugLineNum = 448;BA.debugLine="Dim subjectAvgs As List";
_subjectavgs = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 449;BA.debugLine="subjectAvgs.Initialize";
_subjectavgs.Initialize();
 //BA.debugLineNum = 450;BA.debugLine="For Each subject As String In subjectSums.Keys";
{
final anywheresoftware.b4a.BA.IterableList group21 = _subjectsums.Keys();
final int groupLen21 = group21.getSize()
;int index21 = 0;
;
for (; index21 < groupLen21;index21++){
_subject = BA.ObjectToString(group21.Get(index21));
 //BA.debugLineNum = 451;BA.debugLine="Dim totalScore As Double = subjectSums.Get(subje";
_totalscore = (double)(BA.ObjectToNumber(_subjectsums.Get((Object)(_subject))));
 //BA.debugLineNum = 452;BA.debugLine="Dim count As Int = subjectCounts.Get(subject)";
_count = (int)(BA.ObjectToNumber(_subjectcounts.Get((Object)(_subject))));
 //BA.debugLineNum = 453;BA.debugLine="Dim avg As Double = totalScore / count";
_avg = _totalscore/(double)_count;
 //BA.debugLineNum = 454;BA.debugLine="subjectAvgs.Add(Array As Object(subject, avg))";
_subjectavgs.Add((Object)(new Object[]{(Object)(_subject),(Object)(_avg)}));
 }
};
 //BA.debugLineNum = 458;BA.debugLine="Dim top3 As List";
_top3 = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 459;BA.debugLine="top3.Initialize";
_top3.Initialize();
 //BA.debugLineNum = 461;BA.debugLine="For rank = 0 To Min(2, subjectAvgs.Size - 1)";
{
final int step29 = 1;
final int limit29 = (int) (anywheresoftware.b4a.keywords.Common.Min(2,_subjectavgs.getSize()-1));
_rank = (int) (0) ;
for (;_rank <= limit29 ;_rank = _rank + step29 ) {
 //BA.debugLineNum = 462;BA.debugLine="Dim bestIdx As Int = -1";
_bestidx = (int) (-1);
 //BA.debugLineNum = 463;BA.debugLine="Dim bestVal As Double = -1";
_bestval = -1;
 //BA.debugLineNum = 465;BA.debugLine="For j = 0 To subjectAvgs.Size - 1";
{
final int step32 = 1;
final int limit32 = (int) (_subjectavgs.getSize()-1);
_j = (int) (0) ;
for (;_j <= limit32 ;_j = _j + step32 ) {
 //BA.debugLineNum = 466;BA.debugLine="Dim entry() As Object = subjectAvgs.Get(j)";
_entry = (Object[])(_subjectavgs.Get(_j));
 //BA.debugLineNum = 467;BA.debugLine="Dim val As Double = entry(1) ' The average perc";
_val = (double)(BA.ObjectToNumber(_entry[(int) (1)]));
 //BA.debugLineNum = 468;BA.debugLine="If val > bestVal Then";
if (_val>_bestval) { 
 //BA.debugLineNum = 469;BA.debugLine="bestVal = val";
_bestval = _val;
 //BA.debugLineNum = 470;BA.debugLine="bestIdx = j";
_bestidx = _j;
 };
 }
};
 //BA.debugLineNum = 474;BA.debugLine="If bestIdx >= 0 Then";
if (_bestidx>=0) { 
 //BA.debugLineNum = 475;BA.debugLine="top3.Add(subjectAvgs.Get(bestIdx))";
_top3.Add(_subjectavgs.Get(_bestidx));
 //BA.debugLineNum = 476;BA.debugLine="subjectAvgs.RemoveAt(bestIdx) ' Remove it so th";
_subjectavgs.RemoveAt(_bestidx);
 };
 }
};
 //BA.debugLineNum = 480;BA.debugLine="Return top3";
if (true) return _top3;
 //BA.debugLineNum = 481;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.collections.List  _getunique(anywheresoftware.b4a.objects.collections.List _source) throws Exception{
anywheresoftware.b4a.objects.collections.Map _seen = null;
anywheresoftware.b4a.objects.collections.List _unique = null;
String _item = "";
int _i = 0;
 //BA.debugLineNum = 1000;BA.debugLine="Sub GetUnique(source As List) As List";
 //BA.debugLineNum = 1001;BA.debugLine="Dim seen As Map";
_seen = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 1002;BA.debugLine="seen.Initialize";
_seen.Initialize();
 //BA.debugLineNum = 1003;BA.debugLine="Dim unique As List";
_unique = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 1004;BA.debugLine="unique.Initialize";
_unique.Initialize();
 //BA.debugLineNum = 1005;BA.debugLine="Dim item As String        ' ✅ Declared outside th";
_item = "";
 //BA.debugLineNum = 1006;BA.debugLine="For i = 0 To source.Size - 1";
{
final int step6 = 1;
final int limit6 = (int) (_source.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit6 ;_i = _i + step6 ) {
 //BA.debugLineNum = 1007;BA.debugLine="item = source.Get(i)  ' ✅ Only assignment inside";
_item = BA.ObjectToString(_source.Get(_i));
 //BA.debugLineNum = 1008;BA.debugLine="If seen.ContainsKey(item) = False Then";
if (_seen.ContainsKey((Object)(_item))==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 1009;BA.debugLine="seen.Put(item, True)";
_seen.Put((Object)(_item),(Object)(anywheresoftware.b4a.keywords.Common.True));
 //BA.debugLineNum = 1010;BA.debugLine="unique.Add(item)";
_unique.Add((Object)(_item));
 };
 }
};
 //BA.debugLineNum = 1013;BA.debugLine="Return unique";
if (true) return _unique;
 //BA.debugLineNum = 1014;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.collections.List  _getuniquestudentnames() throws Exception{
anywheresoftware.b4a.objects.collections.List _names = null;
int _i = 0;
String[] _row = null;
String _name = "";
 //BA.debugLineNum = 484;BA.debugLine="Sub GetUniqueStudentNames As List";
 //BA.debugLineNum = 485;BA.debugLine="Dim names As List";
_names = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 486;BA.debugLine="names.Initialize";
_names.Initialize();
 //BA.debugLineNum = 487;BA.debugLine="For i = 0 To StudentList.Size - 1";
{
final int step3 = 1;
final int limit3 = (int) (mostCurrent._studentlist.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit3 ;_i = _i + step3 ) {
 //BA.debugLineNum = 488;BA.debugLine="Dim row() As String = StudentList.Get(i)";
_row = (String[])(mostCurrent._studentlist.Get(_i));
 //BA.debugLineNum = 489;BA.debugLine="Dim name As String = row(COL_NAME)";
_name = _row[_col_name];
 //BA.debugLineNum = 490;BA.debugLine="If names.IndexOf(name) = -1 Then";
if (_names.IndexOf((Object)(_name))==-1) { 
 //BA.debugLineNum = 491;BA.debugLine="names.Add(name)";
_names.Add((Object)(_name));
 };
 }
};
 //BA.debugLineNum = 494;BA.debugLine="Return names";
if (true) return _names;
 //BA.debugLineNum = 495;BA.debugLine="End Sub";
return null;
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 24;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 26;BA.debugLine="Private Drawer As B4XDrawer";
mostCurrent._drawer = new b4a.example.b4xdrawer();
 //BA.debugLineNum = 27;BA.debugLine="Private pnlmain As Panel";
mostCurrent._pnlmain = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 28;BA.debugLine="Private pnlmenu As Panel";
mostCurrent._pnlmenu = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Private profilebtn, dashbtn, grdbtn, editbtn As P";
mostCurrent._profilebtn = new anywheresoftware.b4a.objects.PanelWrapper();
mostCurrent._dashbtn = new anywheresoftware.b4a.objects.PanelWrapper();
mostCurrent._grdbtn = new anywheresoftware.b4a.objects.PanelWrapper();
mostCurrent._editbtn = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 32;BA.debugLine="Dim student_names, student_ids, student_subjects";
mostCurrent._student_names = new anywheresoftware.b4a.objects.collections.List();
mostCurrent._student_ids = new anywheresoftware.b4a.objects.collections.List();
mostCurrent._student_subjects = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 33;BA.debugLine="Dim student_activitys, student_attendance, studen";
mostCurrent._student_activitys = new anywheresoftware.b4a.objects.collections.List();
mostCurrent._student_attendance = new anywheresoftware.b4a.objects.collections.List();
mostCurrent._student_rate = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 34;BA.debugLine="Dim isselectingid As Boolean";
_isselectingid = false;
 //BA.debugLineNum = 35;BA.debugLine="Dim showselectid As Boolean";
_showselectid = false;
 //BA.debugLineNum = 38;BA.debugLine="Private datapnl As Panel";
mostCurrent._datapnl = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 41;BA.debugLine="Private gradepnl As Panel";
mostCurrent._gradepnl = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 42;BA.debugLine="Private SV1 As ScrollView";
mostCurrent._sv1 = new anywheresoftware.b4a.objects.ScrollViewWrapper();
 //BA.debugLineNum = 45;BA.debugLine="Private SV As ScrollView";
mostCurrent._sv = new anywheresoftware.b4a.objects.ScrollViewWrapper();
 //BA.debugLineNum = 46;BA.debugLine="Private Table As Panel";
mostCurrent._table = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 47;BA.debugLine="Type RowCol (Row As Int, Col As Int)";
;
 //BA.debugLineNum = 48;BA.debugLine="Dim NumberOfColumns, RowHeight, ColumnWidth As In";
_numberofcolumns = 0;
_rowheight = 0;
_columnwidth = 0;
 //BA.debugLineNum = 49;BA.debugLine="Dim HeaderColor, FontColor, HeaderFontColor As In";
_headercolor = 0;
_fontcolor = 0;
_headerfontcolor = 0;
 //BA.debugLineNum = 50;BA.debugLine="Dim FontSize As Float";
_fontsize = 0f;
 //BA.debugLineNum = 51;BA.debugLine="Dim Alignment As Int";
_alignment = 0;
 //BA.debugLineNum = 54;BA.debugLine="Private Panel1 As Panel";
mostCurrent._panel1 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 57;BA.debugLine="Dim StudentList As List        ' Each item is a S";
mostCurrent._studentlist = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 58;BA.debugLine="Dim CSVHeaders As List         ' Header row from";
mostCurrent._csvheaders = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 61;BA.debugLine="Dim COL_NAME As Int : COL_NAME = 0";
_col_name = 0;
 //BA.debugLineNum = 61;BA.debugLine="Dim COL_NAME As Int : COL_NAME = 0";
_col_name = (int) (0);
 //BA.debugLineNum = 62;BA.debugLine="Dim COL_RATE As Int : COL_RATE = 5";
_col_rate = 0;
 //BA.debugLineNum = 62;BA.debugLine="Dim COL_RATE As Int : COL_RATE = 5";
_col_rate = (int) (5);
 //BA.debugLineNum = 63;BA.debugLine="Dim COL_ACTIVITY As Int : COL_ACTIVITY = 3";
_col_activity = 0;
 //BA.debugLineNum = 63;BA.debugLine="Dim COL_ACTIVITY As Int : COL_ACTIVITY = 3";
_col_activity = (int) (3);
 //BA.debugLineNum = 66;BA.debugLine="Private NAME1 As Label";
mostCurrent._name1 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 67;BA.debugLine="Private NAME2 As Label";
mostCurrent._name2 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 68;BA.debugLine="Private NAME3 As Label";
mostCurrent._name3 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 69;BA.debugLine="Private RATE1 As Label";
mostCurrent._rate1 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 70;BA.debugLine="Private RATE2 As Label";
mostCurrent._rate2 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 71;BA.debugLine="Private RATE3 As Label";
mostCurrent._rate3 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 72;BA.debugLine="Private ACTIVITY1 As Label";
mostCurrent._activity1 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 73;BA.debugLine="Private ACTIVITY2 As Label";
mostCurrent._activity2 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 74;BA.debugLine="Private ACTIVITY3 As Label";
mostCurrent._activity3 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 78;BA.debugLine="Private CheckBox1 As CheckBox";
mostCurrent._checkbox1 = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
 //BA.debugLineNum = 79;BA.debugLine="Private CheckBox2 As CheckBox";
mostCurrent._checkbox2 = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
 //BA.debugLineNum = 80;BA.debugLine="Private CheckBox3 As CheckBox";
mostCurrent._checkbox3 = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
 //BA.debugLineNum = 81;BA.debugLine="Private EditText1 As EditText";
mostCurrent._edittext1 = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 82;BA.debugLine="Private EditText2 As EditText";
mostCurrent._edittext2 = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 83;BA.debugLine="Private SELECTSUBJECTS As Spinner";
mostCurrent._selectsubjects = new anywheresoftware.b4a.objects.SpinnerWrapper();
 //BA.debugLineNum = 84;BA.debugLine="Private STUDENT_ID As EditText";
mostCurrent._student_id = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 85;BA.debugLine="Private STUDENT_NAME As EditText";
mostCurrent._student_name = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 86;BA.debugLine="Private STUDENT_ACTIVITY As Spinner";
mostCurrent._student_activity = new anywheresoftware.b4a.objects.SpinnerWrapper();
 //BA.debugLineNum = 87;BA.debugLine="Private update As Panel";
mostCurrent._update = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 88;BA.debugLine="Private bak As Panel";
mostCurrent._bak = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 89;BA.debugLine="Private spinner As Spinner";
mostCurrent._spinner = new anywheresoftware.b4a.objects.SpinnerWrapper();
 //BA.debugLineNum = 90;BA.debugLine="Private editRowIndex As Int = -1";
_editrowindex = (int) (-1);
 //BA.debugLineNum = 91;BA.debugLine="Private todelete As Boolean = False";
_todelete = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 92;BA.debugLine="Dim selectedStudent As String = \"\"";
mostCurrent._selectedstudent = "";
 //BA.debugLineNum = 93;BA.debugLine="Private anttslt As Label";
mostCurrent._anttslt = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 94;BA.debugLine="Private gpaslt As Label";
mostCurrent._gpaslt = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 95;BA.debugLine="Private hstslt As Label";
mostCurrent._hstslt = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 97;BA.debugLine="Private studid As Label";
mostCurrent._studid = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 98;BA.debugLine="Private studname As Label";
mostCurrent._studname = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 99;BA.debugLine="Private Top1grd As Label";
mostCurrent._top1grd = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 100;BA.debugLine="Private Top1sub As Label";
mostCurrent._top1sub = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 101;BA.debugLine="Private Top2grd As Label";
mostCurrent._top2grd = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 102;BA.debugLine="Private Top2sub As Label";
mostCurrent._top2sub = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 103;BA.debugLine="Private Top3grd As Label";
mostCurrent._top3grd = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 104;BA.debugLine="Private Top3sub As Label";
mostCurrent._top3sub = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 105;BA.debugLine="End Sub";
return "";
}
public static String  _grdbtn_click() throws Exception{
 //BA.debugLineNum = 206;BA.debugLine="Private Sub grdbtn_Click";
 //BA.debugLineNum = 207;BA.debugLine="HighlightTab(grdbtn)";
_highlighttab(mostCurrent._grdbtn);
 //BA.debugLineNum = 208;BA.debugLine="showgrades";
_showgrades();
 //BA.debugLineNum = 209;BA.debugLine="End Sub";
return "";
}
public static String  _highlighttab(anywheresoftware.b4a.objects.PanelWrapper _activebtn) throws Exception{
 //BA.debugLineNum = 221;BA.debugLine="Sub HighlightTab(activeBtn As Panel)";
 //BA.debugLineNum = 222;BA.debugLine="dashbtn.Color = Colors.White";
mostCurrent._dashbtn.setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 223;BA.debugLine="grdbtn.Color = Colors.White";
mostCurrent._grdbtn.setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 224;BA.debugLine="editbtn.Color = Colors.White";
mostCurrent._editbtn.setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 225;BA.debugLine="profilebtn.Color = Colors.White";
mostCurrent._profilebtn.setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 226;BA.debugLine="activeBtn.Color = 0xFF1AEA00";
_activebtn.setColor(((int)0xff1aea00));
 //BA.debugLineNum = 227;BA.debugLine="End Sub";
return "";
}
public static String  _initdrawer() throws Exception{
 //BA.debugLineNum = 145;BA.debugLine="Sub initdrawer";
 //BA.debugLineNum = 146;BA.debugLine="Drawer.Initialize(Me, \"Drawer\", Activity, 260dip)";
mostCurrent._drawer._initialize /*String*/ (mostCurrent.activityBA,main.getObject(),"Drawer",(anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(mostCurrent._activity.getObject())),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (260)));
 //BA.debugLineNum = 147;BA.debugLine="Drawer.CenterPanel.BringToFront";
mostCurrent._drawer._getcenterpanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ ().BringToFront();
 //BA.debugLineNum = 148;BA.debugLine="Drawer.LeftPanel.BringToFront";
mostCurrent._drawer._getleftpanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ ().BringToFront();
 //BA.debugLineNum = 150;BA.debugLine="pnlmain = Drawer.CenterPanel";
mostCurrent._pnlmain = (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(mostCurrent._drawer._getcenterpanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ ().getObject()));
 //BA.debugLineNum = 151;BA.debugLine="pnlmenu = Drawer.LeftPanel";
mostCurrent._pnlmenu = (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(mostCurrent._drawer._getleftpanel /*anywheresoftware.b4a.objects.B4XViewWrapper*/ ().getObject()));
 //BA.debugLineNum = 153;BA.debugLine="SetGradient(pnlmenu, Colors.rgb(175, 71, 210), Co";
_setgradient(mostCurrent._pnlmenu,anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (175),(int) (71),(int) (210)),anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (38),(int) (53),(int) (93)));
 //BA.debugLineNum = 154;BA.debugLine="CreateMenu";
_createmenu();
 //BA.debugLineNum = 155;BA.debugLine="End Sub";
return "";
}
public static String  _loadspinner() throws Exception{
anywheresoftware.b4a.objects.collections.List _unique_subjects = null;
int _i = 0;
anywheresoftware.b4a.objects.collections.List _unique_activities = null;
 //BA.debugLineNum = 1016;BA.debugLine="Sub loadspinner";
 //BA.debugLineNum = 1017;BA.debugLine="If student_subjects.IsInitialized = False Then Re";
if (mostCurrent._student_subjects.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
if (true) return "";};
 //BA.debugLineNum = 1018;BA.debugLine="SELECTSUBJECTS.Clear";
mostCurrent._selectsubjects.Clear();
 //BA.debugLineNum = 1019;BA.debugLine="Dim unique_subjects As List = GetUnique(student_s";
_unique_subjects = new anywheresoftware.b4a.objects.collections.List();
_unique_subjects = _getunique(mostCurrent._student_subjects);
 //BA.debugLineNum = 1020;BA.debugLine="For i = 0 To unique_subjects.Size - 1";
{
final int step4 = 1;
final int limit4 = (int) (_unique_subjects.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit4 ;_i = _i + step4 ) {
 //BA.debugLineNum = 1021;BA.debugLine="SELECTSUBJECTS.Add(unique_subjects.Get(i))";
mostCurrent._selectsubjects.Add(BA.ObjectToString(_unique_subjects.Get(_i)));
 }
};
 //BA.debugLineNum = 1023;BA.debugLine="If student_activitys.IsInitialized = False Then R";
if (mostCurrent._student_activitys.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
if (true) return "";};
 //BA.debugLineNum = 1024;BA.debugLine="STUDENT_ACTIVITY.Clear";
mostCurrent._student_activity.Clear();
 //BA.debugLineNum = 1025;BA.debugLine="Dim unique_activities As List = GetUnique(student";
_unique_activities = new anywheresoftware.b4a.objects.collections.List();
_unique_activities = _getunique(mostCurrent._student_activitys);
 //BA.debugLineNum = 1026;BA.debugLine="For i = 0 To unique_activities.Size - 1";
{
final int step10 = 1;
final int limit10 = (int) (_unique_activities.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit10 ;_i = _i + step10 ) {
 //BA.debugLineNum = 1027;BA.debugLine="STUDENT_ACTIVITY.Add(unique_activities.Get(i))";
mostCurrent._student_activity.Add(BA.ObjectToString(_unique_activities.Get(_i)));
 }
};
 //BA.debugLineNum = 1029;BA.debugLine="End Sub";
return "";
}
public static String  _loadstudentdata() throws Exception{
anywheresoftware.b4a.objects.collections.List _headers = null;
anywheresoftware.b4a.objects.collections.List _emptylist = null;
anywheresoftware.b4a.objects.collections.List _rawlist = null;
int _i = 0;
String[] _row = null;
 //BA.debugLineNum = 233;BA.debugLine="Sub LoadStudentData";
 //BA.debugLineNum = 235;BA.debugLine="If File.Exists(File.DirInternal, \"student.csv\") =";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"student.csv")==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 236;BA.debugLine="If File.Exists(File.DirAssets, \"student.csv\") Th";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"student.csv")) { 
 //BA.debugLineNum = 237;BA.debugLine="File.Copy(File.DirAssets, \"student.csv\", File.D";
anywheresoftware.b4a.keywords.Common.File.Copy(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"student.csv",anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"student.csv");
 }else {
 //BA.debugLineNum = 240;BA.debugLine="Dim headers As List";
_headers = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 241;BA.debugLine="headers.Initialize2(Array As String(\"Name\", \"ID";
_headers.Initialize2(anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{"Name","ID","Subject","Activity","Attendance","Rate"}));
 //BA.debugLineNum = 242;BA.debugLine="Dim emptyList As List";
_emptylist = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 243;BA.debugLine="emptyList.Initialize";
_emptylist.Initialize();
 //BA.debugLineNum = 244;BA.debugLine="StringUtils1.SaveCSV2(File.DirInternal, \"studen";
_stringutils1.SaveCSV2(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"student.csv",BA.ObjectToChar(","),_emptylist,_headers);
 };
 };
 //BA.debugLineNum = 248;BA.debugLine="CSVHeaders.Initialize";
mostCurrent._csvheaders.Initialize();
 //BA.debugLineNum = 249;BA.debugLine="Dim rawList As List = StringUtils1.LoadCSV2(File.";
_rawlist = new anywheresoftware.b4a.objects.collections.List();
_rawlist = _stringutils1.LoadCSV2(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"student.csv",BA.ObjectToChar(","),mostCurrent._csvheaders);
 //BA.debugLineNum = 252;BA.debugLine="StudentList.Initialize";
mostCurrent._studentlist.Initialize();
 //BA.debugLineNum = 253;BA.debugLine="For i = 0 To rawList.Size - 1";
{
final int step15 = 1;
final int limit15 = (int) (_rawlist.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit15 ;_i = _i + step15 ) {
 //BA.debugLineNum = 254;BA.debugLine="Dim row() As String = rawList.Get(i)";
_row = (String[])(_rawlist.Get(_i));
 //BA.debugLineNum = 255;BA.debugLine="If row.Length >= 6 Then";
if (_row.length>=6) { 
 //BA.debugLineNum = 256;BA.debugLine="StudentList.Add(row)";
mostCurrent._studentlist.Add((Object)(_row));
 };
 }
};
 //BA.debugLineNum = 261;BA.debugLine="student_names.Initialize";
mostCurrent._student_names.Initialize();
 //BA.debugLineNum = 262;BA.debugLine="student_ids.Initialize";
mostCurrent._student_ids.Initialize();
 //BA.debugLineNum = 263;BA.debugLine="student_subjects.Initialize";
mostCurrent._student_subjects.Initialize();
 //BA.debugLineNum = 264;BA.debugLine="student_activitys.Initialize";
mostCurrent._student_activitys.Initialize();
 //BA.debugLineNum = 265;BA.debugLine="student_attendance.Initialize";
mostCurrent._student_attendance.Initialize();
 //BA.debugLineNum = 266;BA.debugLine="student_rate.Initialize";
mostCurrent._student_rate.Initialize();
 //BA.debugLineNum = 269;BA.debugLine="For i = 0 To StudentList.Size - 1";
{
final int step27 = 1;
final int limit27 = (int) (mostCurrent._studentlist.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit27 ;_i = _i + step27 ) {
 //BA.debugLineNum = 270;BA.debugLine="Dim row() As String = StudentList.Get(i)";
_row = (String[])(mostCurrent._studentlist.Get(_i));
 //BA.debugLineNum = 271;BA.debugLine="student_names.Add(row(0))";
mostCurrent._student_names.Add((Object)(_row[(int) (0)]));
 //BA.debugLineNum = 272;BA.debugLine="student_ids.Add(row(1))";
mostCurrent._student_ids.Add((Object)(_row[(int) (1)]));
 //BA.debugLineNum = 273;BA.debugLine="student_subjects.Add(row(2))";
mostCurrent._student_subjects.Add((Object)(_row[(int) (2)]));
 //BA.debugLineNum = 274;BA.debugLine="student_activitys.Add(row(3))";
mostCurrent._student_activitys.Add((Object)(_row[(int) (3)]));
 //BA.debugLineNum = 275;BA.debugLine="student_attendance.Add(row(4))";
mostCurrent._student_attendance.Add((Object)(_row[(int) (4)]));
 //BA.debugLineNum = 276;BA.debugLine="student_rate.Add(row(5))";
mostCurrent._student_rate.Add((Object)(_row[(int) (5)]));
 }
};
 //BA.debugLineNum = 278;BA.debugLine="End Sub";
return "";
}
public static String  _loadtable() throws Exception{
int _i = 0;
anywheresoftware.b4a.objects.LabelWrapper _l = null;
String[] _row = null;
 //BA.debugLineNum = 950;BA.debugLine="Sub loadtable";
 //BA.debugLineNum = 952;BA.debugLine="NumberOfColumns = CSVHeaders.Size";
_numberofcolumns = mostCurrent._csvheaders.getSize();
 //BA.debugLineNum = 953;BA.debugLine="ColumnWidth = SV.Width / NumberOfColumns";
_columnwidth = (int) (mostCurrent._sv.getWidth()/(double)_numberofcolumns);
 //BA.debugLineNum = 956;BA.debugLine="For i = 0 To NumberOfColumns - 1";
{
final int step3 = 1;
final int limit3 = (int) (_numberofcolumns-1);
_i = (int) (0) ;
for (;_i <= limit3 ;_i = _i + step3 ) {
 //BA.debugLineNum = 957;BA.debugLine="Dim l As Label";
_l = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 958;BA.debugLine="l.Initialize(\"header\")";
_l.Initialize(mostCurrent.activityBA,"header");
 //BA.debugLineNum = 959;BA.debugLine="l.Text = CSVHeaders.Get(i)";
_l.setText(BA.ObjectToCharSequence(mostCurrent._csvheaders.Get(_i)));
 //BA.debugLineNum = 960;BA.debugLine="l.Gravity = Gravity.LEFT";
_l.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.LEFT);
 //BA.debugLineNum = 961;BA.debugLine="l.TextSize = FontSize - 4";
_l.setTextSize((float) (_fontsize-4));
 //BA.debugLineNum = 962;BA.debugLine="l.Color = HeaderColor";
_l.setColor(_headercolor);
 //BA.debugLineNum = 963;BA.debugLine="l.TextColor = HeaderFontColor";
_l.setTextColor(_headerfontcolor);
 //BA.debugLineNum = 964;BA.debugLine="l.Padding = Array As Int(10dip, 5dip, 0dip, 5dip";
_l.setPadding(new int[]{anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5))});
 //BA.debugLineNum = 965;BA.debugLine="l.Tag = i";
_l.setTag((Object)(_i));
 //BA.debugLineNum = 966;BA.debugLine="Table.AddView(l, ColumnWidth * i, 0, ColumnWidth";
mostCurrent._table.AddView((android.view.View)(_l.getObject()),(int) (_columnwidth*_i),(int) (0),_columnwidth,_rowheight);
 }
};
 //BA.debugLineNum = 970;BA.debugLine="For i = 0 To StudentList.Size - 1";
{
final int step15 = 1;
final int limit15 = (int) (mostCurrent._studentlist.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit15 ;_i = _i + step15 ) {
 //BA.debugLineNum = 971;BA.debugLine="Dim row() As String = StudentList.Get(i)";
_row = (String[])(mostCurrent._studentlist.Get(_i));
 //BA.debugLineNum = 972;BA.debugLine="AddTableRow(row, i + 1)  ' +1 to skip header row";
_addtablerow(_row,(int) (_i+1));
 }
};
 //BA.debugLineNum = 975;BA.debugLine="Table.Height = (StudentList.Size + 1) * RowHeight";
mostCurrent._table.setHeight((int) ((mostCurrent._studentlist.getSize()+1)*_rowheight));
 //BA.debugLineNum = 976;BA.debugLine="End Sub";
return "";
}
public static String  _makeshadow(int _numstudent,anywheresoftware.b4a.objects.collections.List _studentnames) throws Exception{
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
 //BA.debugLineNum = 729;BA.debugLine="Sub makeshadow(numstudent As Int, studentNames As";
 //BA.debugLineNum = 730;BA.debugLine="Dim itemSpacing As Int = 108dip";
_itemspacing = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (108));
 //BA.debugLineNum = 731;BA.debugLine="Dim startTopMargin As Int = 20dip";
_starttopmargin = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20));
 //BA.debugLineNum = 733;BA.debugLine="For i = 0 To numstudent - 1";
{
final int step3 = 1;
final int limit3 = (int) (_numstudent-1);
_i = (int) (0) ;
for (;_i <= limit3 ;_i = _i + step3 ) {
 //BA.debugLineNum = 734;BA.debugLine="Dim currentTop As Int = startTopMargin + (i * it";
_currenttop = (int) (_starttopmargin+(_i*_itemspacing));
 //BA.debugLineNum = 737;BA.debugLine="Dim pnlShadow As Panel";
_pnlshadow = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 738;BA.debugLine="pnlShadow.Initialize(\"pnlShadow\")";
_pnlshadow.Initialize(mostCurrent.activityBA,"pnlShadow");
 //BA.debugLineNum = 740;BA.debugLine="Dim cd As ColorDrawable";
_cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 741;BA.debugLine="cd.Initialize2(0xFF000000, 4dip, 0dip, 0xFF00000";
_cd.Initialize2(((int)0xff000000),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (4)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0)),((int)0xff000000));
 //BA.debugLineNum = 742;BA.debugLine="pnlShadow.Background = cd";
_pnlshadow.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
 //BA.debugLineNum = 743;BA.debugLine="pnlShadow.Elevation = 0";
_pnlshadow.setElevation((float) (0));
 //BA.debugLineNum = 745;BA.debugLine="Dim pnlWidth As Int = 330dip";
_pnlwidth = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (330));
 //BA.debugLineNum = 746;BA.debugLine="Dim pnlHeight As Int = 88dip";
_pnlheight = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (88));
 //BA.debugLineNum = 748;BA.debugLine="Dim shadowLeftPos As Int = gradepnl.Width - 13di";
_shadowleftpos = (int) (mostCurrent._gradepnl.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (13))-_pnlwidth);
 //BA.debugLineNum = 749;BA.debugLine="Dim shadowTopPos As Int = currentTop + 7dip";
_shadowtoppos = (int) (_currenttop+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (7)));
 //BA.debugLineNum = 751;BA.debugLine="SV1.Panel.AddView(pnlShadow, shadowLeftPos, shad";
mostCurrent._sv1.getPanel().AddView((android.view.View)(_pnlshadow.getObject()),_shadowleftpos,_shadowtoppos,_pnlwidth,_pnlheight);
 //BA.debugLineNum = 753;BA.debugLine="Dim name As String = studentNames.Get(i)";
_name = BA.ObjectToString(_studentnames.Get(_i));
 //BA.debugLineNum = 754;BA.debugLine="Dim grade As String = GetStudentGrade(name)";
_grade = _getstudentgrade(_name);
 //BA.debugLineNum = 755;BA.debugLine="studentgrade(name, grade, currentTop)";
_studentgrade(_name,_grade,_currenttop);
 }
};
 //BA.debugLineNum = 757;BA.debugLine="End Sub";
return "";
}
public static String  _panel2_click() throws Exception{
 //BA.debugLineNum = 1262;BA.debugLine="Private Sub Panel2_Click";
 //BA.debugLineNum = 1263;BA.debugLine="updatedata";
_updatedata();
 //BA.debugLineNum = 1264;BA.debugLine="End Sub";
return "";
}
public static double  _percentagetogpapoints(double _pct) throws Exception{
 //BA.debugLineNum = 302;BA.debugLine="Sub PercentageToGPAPoints(pct As Double) As Double";
 //BA.debugLineNum = 303;BA.debugLine="If pct >= 90 Then Return 4.0";
if (_pct>=90) { 
if (true) return 4.0;};
 //BA.debugLineNum = 304;BA.debugLine="If pct >= 80 Then Return 3.0";
if (_pct>=80) { 
if (true) return 3.0;};
 //BA.debugLineNum = 305;BA.debugLine="If pct >= 70 Then Return 2.0";
if (_pct>=70) { 
if (true) return 2.0;};
 //BA.debugLineNum = 306;BA.debugLine="If pct >= 60 Then Return 1.0";
if (_pct>=60) { 
if (true) return 1.0;};
 //BA.debugLineNum = 307;BA.debugLine="Return 0.0";
if (true) return 0.0;
 //BA.debugLineNum = 308;BA.debugLine="End Sub";
return 0;
}
public static String  _percentagetograde(double _pct) throws Exception{
 //BA.debugLineNum = 293;BA.debugLine="Sub PercentageToGrade(pct As Double) As String";
 //BA.debugLineNum = 294;BA.debugLine="If pct >= 90 Then Return \"A\"";
if (_pct>=90) { 
if (true) return "A";};
 //BA.debugLineNum = 295;BA.debugLine="If pct >= 80 Then Return \"B\"";
if (_pct>=80) { 
if (true) return "B";};
 //BA.debugLineNum = 296;BA.debugLine="If pct >= 70 Then Return \"C\"";
if (_pct>=70) { 
if (true) return "C";};
 //BA.debugLineNum = 297;BA.debugLine="If pct >= 60 Then Return \"D\"";
if (_pct>=60) { 
if (true) return "D";};
 //BA.debugLineNum = 298;BA.debugLine="Return \"F\"";
if (true) return "F";
 //BA.debugLineNum = 299;BA.debugLine="End Sub";
return "";
}
public static void  _pnlcard_click() throws Exception{
ResumableSub_pnlCard_Click rsub = new ResumableSub_pnlCard_Click(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_pnlCard_Click extends BA.ResumableSub {
public ResumableSub_pnlCard_Click(b4a.example.main parent) {
this.parent = parent;
}
b4a.example.main parent;
anywheresoftware.b4a.objects.PanelWrapper _clickedpanel = null;
anywheresoftware.b4a.objects.collections.Map _details = null;
String _studentname = "";
int _result = 0;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 818;BA.debugLine="Dim clickedPanel As Panel = Sender";
_clickedpanel = new anywheresoftware.b4a.objects.PanelWrapper();
_clickedpanel = (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
 //BA.debugLineNum = 821;BA.debugLine="Dim details As Map = clickedPanel.Tag";
_details = new anywheresoftware.b4a.objects.collections.Map();
_details = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_clickedpanel.getTag()));
 //BA.debugLineNum = 822;BA.debugLine="Dim studentName As String = details.Get(\"name\")";
_studentname = BA.ObjectToString(_details.Get((Object)("name")));
 //BA.debugLineNum = 825;BA.debugLine="Msgbox2Async(\"Do you want to view the details for";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Do you want to view the details for "+_studentname+"?"),BA.ObjectToCharSequence("Confirm Selection"),"Yes","","No",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 828;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, this, null);
this.state = 5;
return;
case 5:
//C
this.state = 1;
_result = (Integer) result[0];
;
 //BA.debugLineNum = 831;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
if (true) break;

case 1:
//if
this.state = 4;
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
this.state = 3;
}if (true) break;

case 3:
//C
this.state = 4;
 //BA.debugLineNum = 832;BA.debugLine="selectedStudent = studentName";
parent.mostCurrent._selectedstudent = _studentname;
 //BA.debugLineNum = 835;BA.debugLine="pnlmain.RemoveAllViews";
parent.mostCurrent._pnlmain.RemoveAllViews();
 //BA.debugLineNum = 836;BA.debugLine="pnlmain.LoadLayout(\"profile\")";
parent.mostCurrent._pnlmain.LoadLayout("profile",mostCurrent.activityBA);
 //BA.debugLineNum = 837;BA.debugLine="HighlightTab(profilebtn)";
_highlighttab(parent.mostCurrent._profilebtn);
 //BA.debugLineNum = 840;BA.debugLine="Table = SV.Panel";
parent.mostCurrent._table = parent.mostCurrent._sv.getPanel();
 //BA.debugLineNum = 841;BA.debugLine="NumberOfColumns = 6";
parent._numberofcolumns = (int) (6);
 //BA.debugLineNum = 842;BA.debugLine="ColumnWidth = SV.Width / NumberOfColumns";
parent._columnwidth = (int) (parent.mostCurrent._sv.getWidth()/(double)parent._numberofcolumns);
 //BA.debugLineNum = 843;BA.debugLine="loadtable";
_loadtable();
 //BA.debugLineNum = 846;BA.debugLine="DisplayStudentProfile(selectedStudent)";
_displaystudentprofile(parent.mostCurrent._selectedstudent);
 if (true) break;

case 4:
//C
this.state = -1;
;
 //BA.debugLineNum = 848;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static void  _msgbox_result(int _result) throws Exception{
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
 //BA.debugLineNum = 19;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 20;BA.debugLine="Private xui As XUI";
_xui = new anywheresoftware.b4a.objects.B4XViewWrapper.XUI();
 //BA.debugLineNum = 21;BA.debugLine="Dim StringUtils1 As StringUtils";
_stringutils1 = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 22;BA.debugLine="End Sub";
return "";
}
public static String  _profilebtn_click() throws Exception{
 //BA.debugLineNum = 216;BA.debugLine="Private Sub profilebtn_Click";
 //BA.debugLineNum = 217;BA.debugLine="HighlightTab(profilebtn)";
_highlighttab(mostCurrent._profilebtn);
 //BA.debugLineNum = 218;BA.debugLine="showprofile";
_showprofile();
 //BA.debugLineNum = 219;BA.debugLine="End Sub";
return "";
}
public static String  _setgradient(anywheresoftware.b4a.objects.PanelWrapper _pnl,int _color1,int _color2) throws Exception{
anywheresoftware.b4a.objects.drawable.GradientDrawable _gd = null;
 //BA.debugLineNum = 521;BA.debugLine="Sub SetGradient(pnl As Panel, Color1 As Int, Color";
 //BA.debugLineNum = 522;BA.debugLine="Dim gd As GradientDrawable";
_gd = new anywheresoftware.b4a.objects.drawable.GradientDrawable();
 //BA.debugLineNum = 523;BA.debugLine="gd.Initialize(\"BR_TL\", Array As Int(Color1, Color";
_gd.Initialize(BA.getEnumFromString(android.graphics.drawable.GradientDrawable.Orientation.class,"BR_TL"),new int[]{_color1,_color2});
 //BA.debugLineNum = 524;BA.debugLine="pnl.Background = gd";
_pnl.setBackground((android.graphics.drawable.Drawable)(_gd.getObject()));
 //BA.debugLineNum = 525;BA.debugLine="End Sub";
return "";
}
public static String  _showdashboard() throws Exception{
 //BA.debugLineNum = 563;BA.debugLine="Sub showdashboard";
 //BA.debugLineNum = 564;BA.debugLine="pnlmain.RemoveAllViews";
mostCurrent._pnlmain.RemoveAllViews();
 //BA.debugLineNum = 565;BA.debugLine="pnlmain.LoadLayout(\"dashboard\")";
mostCurrent._pnlmain.LoadLayout("dashboard",mostCurrent.activityBA);
 //BA.debugLineNum = 566;BA.debugLine="generategraph";
_generategraph();
 //BA.debugLineNum = 567;BA.debugLine="End Sub";
return "";
}
public static String  _showedit() throws Exception{
 //BA.debugLineNum = 854;BA.debugLine="Sub showedit";
 //BA.debugLineNum = 855;BA.debugLine="pnlmain.RemoveAllViews";
mostCurrent._pnlmain.RemoveAllViews();
 //BA.debugLineNum = 856;BA.debugLine="pnlmain.LoadLayout(\"edit\")";
mostCurrent._pnlmain.LoadLayout("edit",mostCurrent.activityBA);
 //BA.debugLineNum = 857;BA.debugLine="End Sub";
return "";
}
public static String  _showgrades() throws Exception{
anywheresoftware.b4a.objects.collections.List _names = null;
int _studentsize = 0;
int _totalheight = 0;
 //BA.debugLineNum = 711;BA.debugLine="Sub showgrades";
 //BA.debugLineNum = 712;BA.debugLine="pnlmain.RemoveAllViews";
mostCurrent._pnlmain.RemoveAllViews();
 //BA.debugLineNum = 713;BA.debugLine="pnlmain.LoadLayout(\"grades\")";
mostCurrent._pnlmain.LoadLayout("grades",mostCurrent.activityBA);
 //BA.debugLineNum = 716;BA.debugLine="Dim names As List = GetUniqueStudentNames";
_names = new anywheresoftware.b4a.objects.collections.List();
_names = _getuniquestudentnames();
 //BA.debugLineNum = 717;BA.debugLine="Dim studentSize As Int = names.Size";
_studentsize = _names.getSize();
 //BA.debugLineNum = 721;BA.debugLine="Dim totalHeight As Int = (studentSize * 108dip) +";
_totalheight = (int) ((_studentsize*anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (108)))+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
 //BA.debugLineNum = 722;BA.debugLine="SV1.Panel.Height = totalHeight";
mostCurrent._sv1.getPanel().setHeight(_totalheight);
 //BA.debugLineNum = 723;BA.debugLine="SV1.Panel.Width = SV1.Width";
mostCurrent._sv1.getPanel().setWidth(mostCurrent._sv1.getWidth());
 //BA.debugLineNum = 725;BA.debugLine="SV1.Panel.RemoveAllViews";
mostCurrent._sv1.getPanel().RemoveAllViews();
 //BA.debugLineNum = 727;BA.debugLine="makeshadow(studentSize, names)";
_makeshadow(_studentsize,_names);
 //BA.debugLineNum = 728;BA.debugLine="End Sub";
return "";
}
public static String  _showprofile() throws Exception{
 //BA.debugLineNum = 874;BA.debugLine="Sub showprofile";
 //BA.debugLineNum = 875;BA.debugLine="HighlightTab(profilebtn)";
_highlighttab(mostCurrent._profilebtn);
 //BA.debugLineNum = 876;BA.debugLine="ShowTable";
_showtable();
 //BA.debugLineNum = 877;BA.debugLine="End Sub";
return "";
}
public static String  _showselectids() throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _pnlcard = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _cdcard = null;
int _pnlleft = 0;
int _pnltop = 0;
int _pnlwidth = 0;
int _pnlheight = 0;
anywheresoftware.b4a.objects.LabelWrapper _lbl = null;
anywheresoftware.b4a.objects.ButtonWrapper _btn = null;
 //BA.debugLineNum = 1031;BA.debugLine="Sub showselectids";
 //BA.debugLineNum = 1032;BA.debugLine="If showselectid Then";
if (_showselectid) { 
 //BA.debugLineNum = 1033;BA.debugLine="isselectingid = True";
_isselectingid = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 1034;BA.debugLine="If update.IsInitialized Then update.Enabled = Fa";
if (mostCurrent._update.IsInitialized()) { 
mostCurrent._update.setEnabled(anywheresoftware.b4a.keywords.Common.False);};
 //BA.debugLineNum = 1037;BA.debugLine="If bak.IsInitialized Then";
if (mostCurrent._bak.IsInitialized()) { 
 //BA.debugLineNum = 1038;BA.debugLine="bak.RemoveView";
mostCurrent._bak.RemoveView();
 };
 //BA.debugLineNum = 1042;BA.debugLine="bak.Initialize(\"bak\")";
mostCurrent._bak.Initialize(mostCurrent.activityBA,"bak");
 //BA.debugLineNum = 1043;BA.debugLine="bak.Color = 0x68000000";
mostCurrent._bak.setColor(((int)0x68000000));
 //BA.debugLineNum = 1045;BA.debugLine="Dim pnlCard As Panel";
_pnlcard = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 1046;BA.debugLine="pnlCard.Initialize(\"pnlCard\")";
_pnlcard.Initialize(mostCurrent.activityBA,"pnlCard");
 //BA.debugLineNum = 1049;BA.debugLine="Dim cdCard As ColorDrawable";
_cdcard = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 1050;BA.debugLine="cdCard.Initialize2(0xFF2C2C2C, 40dip, 0dip, 0xFF";
_cdcard.Initialize2(((int)0xff2c2c2c),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0)),((int)0xff000000));
 //BA.debugLineNum = 1051;BA.debugLine="pnlCard.Background = cdCard";
_pnlcard.setBackground((android.graphics.drawable.Drawable)(_cdcard.getObject()));
 //BA.debugLineNum = 1052;BA.debugLine="pnlCard.Elevation = 4dip";
_pnlcard.setElevation((float) (anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (4))));
 //BA.debugLineNum = 1055;BA.debugLine="Dim pnlLeft As Int = 30dip";
_pnlleft = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30));
 //BA.debugLineNum = 1056;BA.debugLine="Dim pnlTop As Int = 220dip";
_pnltop = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (220));
 //BA.debugLineNum = 1057;BA.debugLine="Dim pnlWidth As Int = 300dip";
_pnlwidth = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (300));
 //BA.debugLineNum = 1058;BA.debugLine="Dim pnlHeight As Int = 280dip";
_pnlheight = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (280));
 //BA.debugLineNum = 1061;BA.debugLine="Dim lbl As Label";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 1062;BA.debugLine="lbl.Initialize(\"lbl\")";
_lbl.Initialize(mostCurrent.activityBA,"lbl");
 //BA.debugLineNum = 1063;BA.debugLine="lbl.Text = \"SELECT ID\"";
_lbl.setText(BA.ObjectToCharSequence("SELECT ID"));
 //BA.debugLineNum = 1064;BA.debugLine="lbl.Gravity = Bit.Or(Gravity.CENTER_HORIZONTAL,";
_lbl.setGravity(anywheresoftware.b4a.keywords.Common.Bit.Or(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_HORIZONTAL,anywheresoftware.b4a.keywords.Common.Gravity.CENTER_VERTICAL));
 //BA.debugLineNum = 1065;BA.debugLine="lbl.Typeface = Typeface.CreateNew(Typeface.SERIF";
_lbl.setTypeface(anywheresoftware.b4a.keywords.Common.Typeface.CreateNew(anywheresoftware.b4a.keywords.Common.Typeface.SERIF,anywheresoftware.b4a.keywords.Common.Typeface.STYLE_BOLD_ITALIC));
 //BA.debugLineNum = 1066;BA.debugLine="lbl.TextSize = 20";
_lbl.setTextSize((float) (20));
 //BA.debugLineNum = 1067;BA.debugLine="lbl.TextColor = Colors.White";
_lbl.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 1068;BA.debugLine="pnlCard.AddView(lbl, 35dip, 30dip, 230dip, 40dip";
_pnlcard.AddView((android.view.View)(_lbl.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (35)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (230)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
 //BA.debugLineNum = 1071;BA.debugLine="spinner.Initialize(\"spinner\")";
mostCurrent._spinner.Initialize(mostCurrent.activityBA,"spinner");
 //BA.debugLineNum = 1072;BA.debugLine="spinner.TextColor = Colors.Black";
mostCurrent._spinner.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 1073;BA.debugLine="spinner.AddAll(GetUnique(student_ids))";
mostCurrent._spinner.AddAll(_getunique(mostCurrent._student_ids));
 //BA.debugLineNum = 1074;BA.debugLine="spinner.Color = Colors.White";
mostCurrent._spinner.setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 1075;BA.debugLine="pnlCard.AddView(spinner, 75dip, 115dip, 150dip,";
_pnlcard.AddView((android.view.View)(mostCurrent._spinner.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (75)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (115)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (150)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
 //BA.debugLineNum = 1078;BA.debugLine="Dim btn As Button";
_btn = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 1079;BA.debugLine="btn.Initialize(\"btn\")";
_btn.Initialize(mostCurrent.activityBA,"btn");
 //BA.debugLineNum = 1080;BA.debugLine="btn.Text = \"SELECT\"";
_btn.setText(BA.ObjectToCharSequence("SELECT"));
 //BA.debugLineNum = 1081;BA.debugLine="btn.Gravity = Bit.Or(Gravity.CENTER_HORIZONTAL,";
_btn.setGravity(anywheresoftware.b4a.keywords.Common.Bit.Or(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_HORIZONTAL,anywheresoftware.b4a.keywords.Common.Gravity.CENTER_VERTICAL));
 //BA.debugLineNum = 1082;BA.debugLine="btn.Typeface = Typeface.DEFAULT_BOLD";
_btn.setTypeface(anywheresoftware.b4a.keywords.Common.Typeface.DEFAULT_BOLD);
 //BA.debugLineNum = 1083;BA.debugLine="btn.TextColor = Colors.Black";
_btn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 1084;BA.debugLine="btn.Color = Colors.White";
_btn.setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 1085;BA.debugLine="pnlCard.AddView(btn, 50dip, 210dip, 200dip, 43di";
_pnlcard.AddView((android.view.View)(_btn.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (210)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (200)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (43)));
 //BA.debugLineNum = 1088;BA.debugLine="bak.AddView(pnlCard, pnlLeft, pnlTop, pnlWidth,";
mostCurrent._bak.AddView((android.view.View)(_pnlcard.getObject()),_pnlleft,_pnltop,_pnlwidth,_pnlheight);
 //BA.debugLineNum = 1091;BA.debugLine="Activity.AddView(bak, 0, 0, Activity.Width, Acti";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._bak.getObject()),(int) (0),(int) (0),mostCurrent._activity.getWidth(),mostCurrent._activity.getHeight());
 }else {
 //BA.debugLineNum = 1095;BA.debugLine="If bak.IsInitialized Then";
if (mostCurrent._bak.IsInitialized()) { 
 //BA.debugLineNum = 1096;BA.debugLine="bak.RemoveView";
mostCurrent._bak.RemoveView();
 };
 //BA.debugLineNum = 1098;BA.debugLine="If update.IsInitialized Then update.Enabled = Tr";
if (mostCurrent._update.IsInitialized()) { 
mostCurrent._update.setEnabled(anywheresoftware.b4a.keywords.Common.True);};
 };
 //BA.debugLineNum = 1100;BA.debugLine="End Sub";
return "";
}
public static String  _showtable() throws Exception{
String[] _row = null;
 //BA.debugLineNum = 879;BA.debugLine="Sub ShowTable";
 //BA.debugLineNum = 880;BA.debugLine="pnlmain.RemoveAllViews";
mostCurrent._pnlmain.RemoveAllViews();
 //BA.debugLineNum = 881;BA.debugLine="pnlmain.LoadLayout(\"profile\")";
mostCurrent._pnlmain.LoadLayout("profile",mostCurrent.activityBA);
 //BA.debugLineNum = 882;BA.debugLine="Table = SV.Panel";
mostCurrent._table = mostCurrent._sv.getPanel();
 //BA.debugLineNum = 883;BA.debugLine="NumberOfColumns = 6";
_numberofcolumns = (int) (6);
 //BA.debugLineNum = 884;BA.debugLine="ColumnWidth = SV.Width / NumberOfColumns";
_columnwidth = (int) (mostCurrent._sv.getWidth()/(double)_numberofcolumns);
 //BA.debugLineNum = 885;BA.debugLine="loadtable";
_loadtable();
 //BA.debugLineNum = 888;BA.debugLine="If selectedStudent <> \"\" Then";
if ((mostCurrent._selectedstudent).equals("") == false) { 
 //BA.debugLineNum = 889;BA.debugLine="DisplayStudentProfile(selectedStudent)";
_displaystudentprofile(mostCurrent._selectedstudent);
 }else if(mostCurrent._studentlist.getSize()>0) { 
 //BA.debugLineNum = 891;BA.debugLine="Dim row() As String = StudentList.Get(0)";
_row = (String[])(mostCurrent._studentlist.Get((int) (0)));
 //BA.debugLineNum = 892;BA.debugLine="selectedStudent = row(COL_NAME)";
mostCurrent._selectedstudent = _row[_col_name];
 //BA.debugLineNum = 893;BA.debugLine="DisplayStudentProfile(selectedStudent)";
_displaystudentprofile(mostCurrent._selectedstudent);
 };
 //BA.debugLineNum = 895;BA.debugLine="End Sub";
return "";
}
public static String  _studentgrade(String _studentname,String _grade,int _currenttop) throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _pnlcard = null;
String _studentidval = "";
anywheresoftware.b4a.objects.collections.Map _details = null;
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
 //BA.debugLineNum = 759;BA.debugLine="Sub studentgrade(studentName As String, grade As S";
 //BA.debugLineNum = 760;BA.debugLine="Dim pnlCard As Panel";
_pnlcard = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 762;BA.debugLine="pnlCard.Initialize(\"pnlCard\")";
_pnlcard.Initialize(mostCurrent.activityBA,"pnlCard");
 //BA.debugLineNum = 765;BA.debugLine="Dim studentIdVal As String = GetStudentID(student";
_studentidval = _getstudentid(_studentname);
 //BA.debugLineNum = 767;BA.debugLine="Dim details As Map";
_details = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 768;BA.debugLine="details.Initialize";
_details.Initialize();
 //BA.debugLineNum = 769;BA.debugLine="details.Put(\"name\", studentName)";
_details.Put((Object)("name"),(Object)(_studentname));
 //BA.debugLineNum = 770;BA.debugLine="details.Put(\"grade\", grade)";
_details.Put((Object)("grade"),(Object)(_grade));
 //BA.debugLineNum = 771;BA.debugLine="details.Put(\"id\", studentIdVal) ' <--- Put it in";
_details.Put((Object)("id"),(Object)(_studentidval));
 //BA.debugLineNum = 772;BA.debugLine="pnlCard.Tag = details";
_pnlcard.setTag((Object)(_details.getObject()));
 //BA.debugLineNum = 773;BA.debugLine="Dim cd As ColorDrawable";
_cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 774;BA.debugLine="cd.Initialize2(0xFFFFD400, 2dip, 2dip, 0xFF000000";
_cd.Initialize2(((int)0xffffd400),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)),((int)0xff000000));
 //BA.debugLineNum = 775;BA.debugLine="pnlCard.Background = cd";
_pnlcard.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
 //BA.debugLineNum = 776;BA.debugLine="pnlCard.Elevation = 0";
_pnlcard.setElevation((float) (0));
 //BA.debugLineNum = 778;BA.debugLine="Dim pnlWidth As Int = 330dip";
_pnlwidth = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (330));
 //BA.debugLineNum = 779;BA.debugLine="Dim pnlHeight As Int = 88dip";
_pnlheight = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (88));
 //BA.debugLineNum = 780;BA.debugLine="Dim rightEdgeDistance As Int = 20dip";
_rightedgedistance = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20));
 //BA.debugLineNum = 782;BA.debugLine="Dim leftPos As Int = gradepnl.Width - rightEdgeDi";
_leftpos = (int) (mostCurrent._gradepnl.getWidth()-_rightedgedistance-_pnlwidth);
 //BA.debugLineNum = 783;BA.debugLine="Dim topPos As Int = currentTop";
_toppos = _currenttop;
 //BA.debugLineNum = 786;BA.debugLine="Dim lblGrade As Label";
_lblgrade = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 788;BA.debugLine="lblGrade.Initialize(\"\")";
_lblgrade.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 789;BA.debugLine="lblGrade.Text = grade";
_lblgrade.setText(BA.ObjectToCharSequence(_grade));
 //BA.debugLineNum = 790;BA.debugLine="lblGrade.TextSize = 30";
_lblgrade.setTextSize((float) (30));
 //BA.debugLineNum = 791;BA.debugLine="lblGrade.TextColor = Colors.Black";
_lblgrade.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 792;BA.debugLine="lblGrade.Gravity = Bit.Or(Gravity.CENTER_HORIZONT";
_lblgrade.setGravity(anywheresoftware.b4a.keywords.Common.Bit.Or(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_HORIZONTAL,anywheresoftware.b4a.keywords.Common.Gravity.CENTER_VERTICAL));
 //BA.debugLineNum = 793;BA.debugLine="lblGrade.Typeface = Typeface.CreateNew(Typeface.S";
_lblgrade.setTypeface(anywheresoftware.b4a.keywords.Common.Typeface.CreateNew(anywheresoftware.b4a.keywords.Common.Typeface.SERIF,anywheresoftware.b4a.keywords.Common.Typeface.STYLE_BOLD_ITALIC));
 //BA.debugLineNum = 794;BA.debugLine="lblGrade.SingleLine = False";
_lblgrade.setSingleLine(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 795;BA.debugLine="lblGrade.Enabled = True";
_lblgrade.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 796;BA.debugLine="lblGrade.Visible = True";
_lblgrade.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 798;BA.debugLine="Dim gradeRightEdge As Int = 264dip";
_graderightedge = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (264));
 //BA.debugLineNum = 799;BA.debugLine="Dim gradeWidth As Int = pnlWidth - gradeRightEdge";
_gradewidth = (int) (_pnlwidth-_graderightedge);
 //BA.debugLineNum = 800;BA.debugLine="pnlCard.AddView(lblGrade, 0, 0, gradeWidth, pnlHe";
_pnlcard.AddView((android.view.View)(_lblgrade.getObject()),(int) (0),(int) (0),_gradewidth,_pnlheight);
 //BA.debugLineNum = 803;BA.debugLine="Dim lbl As Label";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 805;BA.debugLine="lbl.Initialize(\"\")";
_lbl.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 806;BA.debugLine="lbl.Text = studentName";
_lbl.setText(BA.ObjectToCharSequence(_studentname));
 //BA.debugLineNum = 807;BA.debugLine="lbl.Gravity = Gravity.CENTER_VERTICAL";
_lbl.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_VERTICAL);
 //BA.debugLineNum = 808;BA.debugLine="lbl.Typeface = Typeface.CreateNew(Typeface.SERIF,";
_lbl.setTypeface(anywheresoftware.b4a.keywords.Common.Typeface.CreateNew(anywheresoftware.b4a.keywords.Common.Typeface.SERIF,anywheresoftware.b4a.keywords.Common.Typeface.STYLE_BOLD_ITALIC));
 //BA.debugLineNum = 809;BA.debugLine="lbl.TextSize = 20";
_lbl.setTextSize((float) (20));
 //BA.debugLineNum = 810;BA.debugLine="lbl.TextColor = Colors.Black";
_lbl.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 811;BA.debugLine="lbl.Padding = Array As Int(10dip, 5dip, 10dip, 5d";
_lbl.setPadding(new int[]{anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5))});
 //BA.debugLineNum = 812;BA.debugLine="pnlCard.AddView(lbl, gradeWidth, 0, pnlWidth - gr";
_pnlcard.AddView((android.view.View)(_lbl.getObject()),_gradewidth,(int) (0),(int) (_pnlwidth-_gradewidth),_pnlheight);
 //BA.debugLineNum = 813;BA.debugLine="SV1.Panel.AddView(pnlCard, leftPos, topPos, pnlWi";
mostCurrent._sv1.getPanel().AddView((android.view.View)(_pnlcard.getObject()),_leftpos,_toppos,_pnlwidth,_pnlheight);
 //BA.debugLineNum = 814;BA.debugLine="End Sub";
return "";
}
public static String  _update_click() throws Exception{
 //BA.debugLineNum = 1212;BA.debugLine="Private Sub update_Click";
 //BA.debugLineNum = 1213;BA.debugLine="todelete = False";
_todelete = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 1214;BA.debugLine="showselectid = True";
_showselectid = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 1215;BA.debugLine="showselectids";
_showselectids();
 //BA.debugLineNum = 1216;BA.debugLine="End Sub";
return "";
}
public static String  _updatedata() throws Exception{
String _attendance = "";
String _rate = "";
String _msg = "";
String[] _row = null;
String[] _newrow = null;
 //BA.debugLineNum = 1270;BA.debugLine="Sub updatedata";
 //BA.debugLineNum = 1272;BA.debugLine="If STUDENT_NAME.Text.Trim = \"\" Or STUDENT_ID.Text";
if ((mostCurrent._student_name.getText().trim()).equals("") || (mostCurrent._student_id.getText().trim()).equals("")) { 
 //BA.debugLineNum = 1273;BA.debugLine="xui.MsgboxAsync(\"Please enter Student Name and S";
_xui.MsgboxAsync(processBA,BA.ObjectToCharSequence("Please enter Student Name and Student ID."),BA.ObjectToCharSequence("Validation Error"));
 //BA.debugLineNum = 1274;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 1278;BA.debugLine="If EditText1.Text.Trim = \"\" Or EditText2.Text.Tri";
if ((mostCurrent._edittext1.getText().trim()).equals("") || (mostCurrent._edittext2.getText().trim()).equals("")) { 
 //BA.debugLineNum = 1279;BA.debugLine="xui.MsgboxAsync(\"Please enter the complete score";
_xui.MsgboxAsync(processBA,BA.ObjectToCharSequence("Please enter the complete score/rate (e.g. 15/20)."),BA.ObjectToCharSequence("Validation Error"));
 //BA.debugLineNum = 1280;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 1284;BA.debugLine="Dim attendance As String = \"present\"";
_attendance = "present";
 //BA.debugLineNum = 1285;BA.debugLine="If CheckBox2.Checked Then";
if (mostCurrent._checkbox2.getChecked()) { 
 //BA.debugLineNum = 1286;BA.debugLine="attendance = \"late\"";
_attendance = "late";
 }else if(mostCurrent._checkbox3.getChecked()) { 
 //BA.debugLineNum = 1288;BA.debugLine="attendance = \"absent\"";
_attendance = "absent";
 };
 //BA.debugLineNum = 1292;BA.debugLine="Dim rate As String = EditText1.Text.Trim & \"/\" &";
_rate = mostCurrent._edittext1.getText().trim()+"/"+mostCurrent._edittext2.getText().trim();
 //BA.debugLineNum = 1294;BA.debugLine="Dim msg As String";
_msg = "";
 //BA.debugLineNum = 1295;BA.debugLine="If editRowIndex > -1 Then";
if (_editrowindex>-1) { 
 //BA.debugLineNum = 1297;BA.debugLine="Dim row() As String = StudentList.Get(editRowInd";
_row = (String[])(mostCurrent._studentlist.Get(_editrowindex));
 //BA.debugLineNum = 1298;BA.debugLine="row(0) = STUDENT_NAME.Text.Trim";
_row[(int) (0)] = mostCurrent._student_name.getText().trim();
 //BA.debugLineNum = 1299;BA.debugLine="row(1) = STUDENT_ID.Text.Trim";
_row[(int) (1)] = mostCurrent._student_id.getText().trim();
 //BA.debugLineNum = 1300;BA.debugLine="row(2) = SELECTSUBJECTS.SelectedItem";
_row[(int) (2)] = mostCurrent._selectsubjects.getSelectedItem();
 //BA.debugLineNum = 1301;BA.debugLine="row(3) = STUDENT_ACTIVITY.SelectedItem";
_row[(int) (3)] = mostCurrent._student_activity.getSelectedItem();
 //BA.debugLineNum = 1302;BA.debugLine="row(4) = attendance";
_row[(int) (4)] = _attendance;
 //BA.debugLineNum = 1303;BA.debugLine="row(5) = rate";
_row[(int) (5)] = _rate;
 //BA.debugLineNum = 1304;BA.debugLine="StudentList.Set(editRowIndex, row)";
mostCurrent._studentlist.Set(_editrowindex,(Object)(_row));
 //BA.debugLineNum = 1305;BA.debugLine="msg = \"Student record updated successfully!\"";
_msg = "Student record updated successfully!";
 }else {
 //BA.debugLineNum = 1308;BA.debugLine="Dim newRow(6) As String";
_newrow = new String[(int) (6)];
java.util.Arrays.fill(_newrow,"");
 //BA.debugLineNum = 1309;BA.debugLine="newRow(0) = STUDENT_NAME.Text.Trim";
_newrow[(int) (0)] = mostCurrent._student_name.getText().trim();
 //BA.debugLineNum = 1310;BA.debugLine="newRow(1) = STUDENT_ID.Text.Trim";
_newrow[(int) (1)] = mostCurrent._student_id.getText().trim();
 //BA.debugLineNum = 1311;BA.debugLine="newRow(2) = SELECTSUBJECTS.SelectedItem";
_newrow[(int) (2)] = mostCurrent._selectsubjects.getSelectedItem();
 //BA.debugLineNum = 1312;BA.debugLine="newRow(3) = STUDENT_ACTIVITY.SelectedItem";
_newrow[(int) (3)] = mostCurrent._student_activity.getSelectedItem();
 //BA.debugLineNum = 1313;BA.debugLine="newRow(4) = attendance";
_newrow[(int) (4)] = _attendance;
 //BA.debugLineNum = 1314;BA.debugLine="newRow(5) = rate";
_newrow[(int) (5)] = _rate;
 //BA.debugLineNum = 1315;BA.debugLine="StudentList.Add(newRow)";
mostCurrent._studentlist.Add((Object)(_newrow));
 //BA.debugLineNum = 1316;BA.debugLine="msg = \"Student record added successfully!\"";
_msg = "Student record added successfully!";
 };
 //BA.debugLineNum = 1320;BA.debugLine="StringUtils1.SaveCSV2(File.DirInternal, \"student.";
_stringutils1.SaveCSV2(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"student.csv",BA.ObjectToChar(","),mostCurrent._studentlist,mostCurrent._csvheaders);
 //BA.debugLineNum = 1323;BA.debugLine="LoadStudentData";
_loadstudentdata();
 //BA.debugLineNum = 1326;BA.debugLine="xui.MsgboxAsync(msg, \"Success\")";
_xui.MsgboxAsync(processBA,BA.ObjectToCharSequence(_msg),BA.ObjectToCharSequence("Success"));
 //BA.debugLineNum = 1327;BA.debugLine="showedit";
_showedit();
 //BA.debugLineNum = 1328;BA.debugLine="End Sub";
return "";
}
public static String  _updatedata_click() throws Exception{
 //BA.debugLineNum = 1266;BA.debugLine="Private Sub updatedata_Click";
 //BA.debugLineNum = 1267;BA.debugLine="updatedata";
_updatedata();
 //BA.debugLineNum = 1268;BA.debugLine="End Sub";
return "";
}
}
