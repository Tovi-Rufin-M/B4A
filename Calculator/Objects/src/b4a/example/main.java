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
public anywheresoftware.b4a.objects.LabelWrapper _label1 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _button0 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _button1 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _button2 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _button3 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _button4 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _button5 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _button6 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _button7 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _button8 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _button9 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _buttonback = null;
public anywheresoftware.b4a.objects.ButtonWrapper _buttondev = null;
public anywheresoftware.b4a.objects.ButtonWrapper _buttonequl = null;
public anywheresoftware.b4a.objects.ButtonWrapper _buttonmul = null;
public anywheresoftware.b4a.objects.ButtonWrapper _buttonplus = null;
public anywheresoftware.b4a.objects.ButtonWrapper _buttonsub = null;
public anywheresoftware.b4a.objects.collections.List _tokens = null;
public static boolean _isnewinput = false;
public b4a.example.b4xgifview _b4xgifview1 = null;
public b4a.example.starter _starter = null;

public static boolean isAnyActivityVisible() {
    boolean vis = false;
vis = vis | (main.mostCurrent != null);
return vis;}
public static String  _activity_create(boolean _firsttime) throws Exception{
anywheresoftware.b4a.keywords.constants.TypefaceWrapper _tf = null;
 //BA.debugLineNum = 45;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 46;BA.debugLine="Activity.LoadLayout(\"layout\")";
mostCurrent._activity.LoadLayout("layout",mostCurrent.activityBA);
 //BA.debugLineNum = 47;BA.debugLine="Dim tf As Typeface = Typeface.LoadFromAssets(\"04B";
_tf = new anywheresoftware.b4a.keywords.constants.TypefaceWrapper();
_tf = (anywheresoftware.b4a.keywords.constants.TypefaceWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.constants.TypefaceWrapper(), (android.graphics.Typeface)(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("04B_30__.ttf")));
 //BA.debugLineNum = 48;BA.debugLine="Label1.Typeface = tf";
mostCurrent._label1.setTypeface((android.graphics.Typeface)(_tf.getObject()));
 //BA.debugLineNum = 49;BA.debugLine="tokens.Initialize";
mostCurrent._tokens.Initialize();
 //BA.debugLineNum = 50;BA.debugLine="tokens.Add(\"0\")";
mostCurrent._tokens.Add((Object)("0"));
 //BA.debugLineNum = 51;BA.debugLine="Label1.Text = \"0\"";
mostCurrent._label1.setText(BA.ObjectToCharSequence("0"));
 //BA.debugLineNum = 52;BA.debugLine="isNewInput = True";
_isnewinput = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 53;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 58;BA.debugLine="Sub Activity_Pause(UserClosed As Boolean)";
 //BA.debugLineNum = 59;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 55;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 56;BA.debugLine="End Sub";
return "";
}
public static String  _appenddigit(String _digit) throws Exception{
int _lastindex = 0;
String _lasttoken = "";
 //BA.debugLineNum = 202;BA.debugLine="Private Sub AppendDigit(digit As String)";
 //BA.debugLineNum = 203;BA.debugLine="Dim lastIndex As Int";
_lastindex = 0;
 //BA.debugLineNum = 204;BA.debugLine="Dim lastToken As String";
_lasttoken = "";
 //BA.debugLineNum = 205;BA.debugLine="lastIndex = tokens.Size - 1";
_lastindex = (int) (mostCurrent._tokens.getSize()-1);
 //BA.debugLineNum = 206;BA.debugLine="lastToken = tokens.Get(lastIndex)";
_lasttoken = BA.ObjectToString(mostCurrent._tokens.Get(_lastindex));
 //BA.debugLineNum = 208;BA.debugLine="If IsOperator(lastToken) Then";
if (_isoperator(_lasttoken)) { 
 //BA.debugLineNum = 210;BA.debugLine="tokens.Add(digit)";
mostCurrent._tokens.Add((Object)(_digit));
 }else {
 //BA.debugLineNum = 212;BA.debugLine="If isNewInput Then";
if (_isnewinput) { 
 //BA.debugLineNum = 213;BA.debugLine="tokens.Set(lastIndex, digit)";
mostCurrent._tokens.Set(_lastindex,(Object)(_digit));
 //BA.debugLineNum = 214;BA.debugLine="isNewInput = False";
_isnewinput = anywheresoftware.b4a.keywords.Common.False;
 }else {
 //BA.debugLineNum = 216;BA.debugLine="If lastToken = \"0\" Then";
if ((_lasttoken).equals("0")) { 
 //BA.debugLineNum = 217;BA.debugLine="tokens.Set(lastIndex, digit)";
mostCurrent._tokens.Set(_lastindex,(Object)(_digit));
 }else {
 //BA.debugLineNum = 219;BA.debugLine="If lastToken.Length < 12 Then";
if (_lasttoken.length()<12) { 
 //BA.debugLineNum = 220;BA.debugLine="tokens.Set(lastIndex, lastToken & digit)";
mostCurrent._tokens.Set(_lastindex,(Object)(_lasttoken+_digit));
 };
 };
 };
 };
 //BA.debugLineNum = 225;BA.debugLine="UpdateLabel";
_updatelabel();
 //BA.debugLineNum = 226;BA.debugLine="End Sub";
return "";
}
public static String  _button0_click() throws Exception{
 //BA.debugLineNum = 63;BA.debugLine="Private Sub Button0_Click";
 //BA.debugLineNum = 64;BA.debugLine="AppendDigit(\"0\")";
_appenddigit("0");
 //BA.debugLineNum = 65;BA.debugLine="End Sub";
return "";
}
public static String  _button1_click() throws Exception{
 //BA.debugLineNum = 66;BA.debugLine="Private Sub Button1_Click";
 //BA.debugLineNum = 67;BA.debugLine="AppendDigit(\"1\")";
_appenddigit("1");
 //BA.debugLineNum = 68;BA.debugLine="End Sub";
return "";
}
public static String  _button2_click() throws Exception{
 //BA.debugLineNum = 69;BA.debugLine="Private Sub Button2_Click";
 //BA.debugLineNum = 70;BA.debugLine="AppendDigit(\"2\")";
_appenddigit("2");
 //BA.debugLineNum = 71;BA.debugLine="End Sub";
return "";
}
public static String  _button3_click() throws Exception{
 //BA.debugLineNum = 72;BA.debugLine="Private Sub Button3_Click";
 //BA.debugLineNum = 73;BA.debugLine="AppendDigit(\"3\")";
_appenddigit("3");
 //BA.debugLineNum = 74;BA.debugLine="End Sub";
return "";
}
public static String  _button4_click() throws Exception{
 //BA.debugLineNum = 75;BA.debugLine="Private Sub Button4_Click";
 //BA.debugLineNum = 76;BA.debugLine="AppendDigit(\"4\")";
_appenddigit("4");
 //BA.debugLineNum = 77;BA.debugLine="End Sub";
return "";
}
public static String  _button5_click() throws Exception{
 //BA.debugLineNum = 78;BA.debugLine="Private Sub Button5_Click";
 //BA.debugLineNum = 79;BA.debugLine="AppendDigit(\"5\")";
_appenddigit("5");
 //BA.debugLineNum = 80;BA.debugLine="End Sub";
return "";
}
public static String  _button6_click() throws Exception{
 //BA.debugLineNum = 81;BA.debugLine="Private Sub Button6_Click";
 //BA.debugLineNum = 82;BA.debugLine="AppendDigit(\"6\")";
_appenddigit("6");
 //BA.debugLineNum = 83;BA.debugLine="End Sub";
return "";
}
public static String  _button7_click() throws Exception{
 //BA.debugLineNum = 84;BA.debugLine="Private Sub Button7_Click";
 //BA.debugLineNum = 85;BA.debugLine="AppendDigit(\"7\")";
_appenddigit("7");
 //BA.debugLineNum = 86;BA.debugLine="End Sub";
return "";
}
public static String  _button8_click() throws Exception{
 //BA.debugLineNum = 87;BA.debugLine="Private Sub Button8_Click";
 //BA.debugLineNum = 88;BA.debugLine="AppendDigit(\"8\")";
_appenddigit("8");
 //BA.debugLineNum = 89;BA.debugLine="End Sub";
return "";
}
public static String  _button9_click() throws Exception{
 //BA.debugLineNum = 90;BA.debugLine="Private Sub Button9_Click";
 //BA.debugLineNum = 91;BA.debugLine="AppendDigit(\"9\")";
_appenddigit("9");
 //BA.debugLineNum = 92;BA.debugLine="End Sub";
return "";
}
public static String  _buttonback_click() throws Exception{
int _lastindex = 0;
String _lasttoken = "";
 //BA.debugLineNum = 166;BA.debugLine="Private Sub Buttonback_Click";
 //BA.debugLineNum = 167;BA.debugLine="B4XGifView1.mBase.Visible = False";
mostCurrent._b4xgifview1._mbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 168;BA.debugLine="If Label1.Text = \"Error\" Then";
if ((mostCurrent._label1.getText()).equals("Error")) { 
 //BA.debugLineNum = 169;BA.debugLine="tokens.Initialize";
mostCurrent._tokens.Initialize();
 //BA.debugLineNum = 170;BA.debugLine="tokens.Add(\"0\")";
mostCurrent._tokens.Add((Object)("0"));
 //BA.debugLineNum = 171;BA.debugLine="UpdateLabel";
_updatelabel();
 //BA.debugLineNum = 172;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 175;BA.debugLine="Dim lastIndex As Int";
_lastindex = 0;
 //BA.debugLineNum = 176;BA.debugLine="Dim lastToken As String";
_lasttoken = "";
 //BA.debugLineNum = 177;BA.debugLine="lastIndex = tokens.Size - 1";
_lastindex = (int) (mostCurrent._tokens.getSize()-1);
 //BA.debugLineNum = 178;BA.debugLine="lastToken = tokens.Get(lastIndex)";
_lasttoken = BA.ObjectToString(mostCurrent._tokens.Get(_lastindex));
 //BA.debugLineNum = 180;BA.debugLine="If IsOperator(lastToken) Then";
if (_isoperator(_lasttoken)) { 
 //BA.debugLineNum = 182;BA.debugLine="tokens.RemoveAt(lastIndex)";
mostCurrent._tokens.RemoveAt(_lastindex);
 }else {
 //BA.debugLineNum = 184;BA.debugLine="If lastToken.Length <= 1 Then";
if (_lasttoken.length()<=1) { 
 //BA.debugLineNum = 185;BA.debugLine="If tokens.Size > 1 Then";
if (mostCurrent._tokens.getSize()>1) { 
 //BA.debugLineNum = 186;BA.debugLine="tokens.RemoveAt(lastIndex)   ' remove empty nu";
mostCurrent._tokens.RemoveAt(_lastindex);
 }else {
 //BA.debugLineNum = 188;BA.debugLine="tokens.Set(0, \"0\")           ' always keep at";
mostCurrent._tokens.Set((int) (0),(Object)("0"));
 };
 }else {
 //BA.debugLineNum = 192;BA.debugLine="tokens.Set(lastIndex, lastToken.SubString2(0, l";
mostCurrent._tokens.Set(_lastindex,(Object)(_lasttoken.substring((int) (0),(int) (_lasttoken.length()-1))));
 };
 };
 //BA.debugLineNum = 196;BA.debugLine="UpdateLabel";
_updatelabel();
 //BA.debugLineNum = 197;BA.debugLine="End Sub";
return "";
}
public static String  _buttonback_longclick() throws Exception{
 //BA.debugLineNum = 265;BA.debugLine="Private Sub Buttonback_LongClick";
 //BA.debugLineNum = 267;BA.debugLine="tokens.Clear";
mostCurrent._tokens.Clear();
 //BA.debugLineNum = 268;BA.debugLine="tokens.Add(\"0\")";
mostCurrent._tokens.Add((Object)("0"));
 //BA.debugLineNum = 269;BA.debugLine="isNewInput = True";
_isnewinput = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 270;BA.debugLine="Label1.Text = \"0\"";
mostCurrent._label1.setText(BA.ObjectToCharSequence("0"));
 //BA.debugLineNum = 271;BA.debugLine="End Sub";
return "";
}
public static String  _buttondev_click() throws Exception{
 //BA.debugLineNum = 105;BA.debugLine="Private Sub Buttondev_Click";
 //BA.debugLineNum = 106;BA.debugLine="SetOperator(\"/\")";
_setoperator("/");
 //BA.debugLineNum = 107;BA.debugLine="End Sub";
return "";
}
public static String  _buttonequl_click() throws Exception{
String _lasttoken = "";
double _result = 0;
int _i = 0;
String _op = "";
double _num = 0;
 //BA.debugLineNum = 111;BA.debugLine="Private Sub Buttonequl_Click";
 //BA.debugLineNum = 113;BA.debugLine="If tokens.Size < 3 Then";
if (mostCurrent._tokens.getSize()<3) { 
 //BA.debugLineNum = 114;BA.debugLine="Return";
if (true) return "";
 }else {
 //BA.debugLineNum = 116;BA.debugLine="Label1.Text = \"error\"";
mostCurrent._label1.setText(BA.ObjectToCharSequence("error"));
 };
 //BA.debugLineNum = 119;BA.debugLine="Dim lastToken As String";
_lasttoken = "";
 //BA.debugLineNum = 120;BA.debugLine="lastToken = tokens.Get(tokens.Size - 1)";
_lasttoken = BA.ObjectToString(mostCurrent._tokens.Get((int) (mostCurrent._tokens.getSize()-1)));
 //BA.debugLineNum = 123;BA.debugLine="If IsOperator(lastToken) Then Return";
if (_isoperator(_lasttoken)) { 
if (true) return "";};
 //BA.debugLineNum = 126;BA.debugLine="Dim result As Double";
_result = 0;
 //BA.debugLineNum = 127;BA.debugLine="result = tokens.Get(0)";
_result = (double)(BA.ObjectToNumber(mostCurrent._tokens.Get((int) (0))));
 //BA.debugLineNum = 129;BA.debugLine="Dim i As Int";
_i = 0;
 //BA.debugLineNum = 130;BA.debugLine="For i = 1 To tokens.Size - 2 Step 2";
{
final int step12 = 2;
final int limit12 = (int) (mostCurrent._tokens.getSize()-2);
_i = (int) (1) ;
for (;_i <= limit12 ;_i = _i + step12 ) {
 //BA.debugLineNum = 131;BA.debugLine="Dim op As String";
_op = "";
 //BA.debugLineNum = 132;BA.debugLine="Dim num As Double";
_num = 0;
 //BA.debugLineNum = 133;BA.debugLine="op  = tokens.Get(i)";
_op = BA.ObjectToString(mostCurrent._tokens.Get(_i));
 //BA.debugLineNum = 134;BA.debugLine="num = tokens.Get(i + 1)";
_num = (double)(BA.ObjectToNumber(mostCurrent._tokens.Get((int) (_i+1))));
 //BA.debugLineNum = 136;BA.debugLine="Select Case op";
switch (BA.switchObjectToInt(_op,"+","-","*","/")) {
case 0: {
 //BA.debugLineNum = 138;BA.debugLine="result = result + num";
_result = _result+_num;
 break; }
case 1: {
 //BA.debugLineNum = 140;BA.debugLine="result = result - num";
_result = _result-_num;
 break; }
case 2: {
 //BA.debugLineNum = 142;BA.debugLine="result = result * num";
_result = _result*_num;
 break; }
case 3: {
 //BA.debugLineNum = 144;BA.debugLine="If num = 0 Then";
if (_num==0) { 
 //BA.debugLineNum = 145;BA.debugLine="Label1.Text = \"Error\"";
mostCurrent._label1.setText(BA.ObjectToCharSequence("Error"));
 //BA.debugLineNum = 146;BA.debugLine="tokens.Initialize";
mostCurrent._tokens.Initialize();
 //BA.debugLineNum = 147;BA.debugLine="tokens.Add(\"0\")";
mostCurrent._tokens.Add((Object)("0"));
 //BA.debugLineNum = 148;BA.debugLine="isNewInput = True";
_isnewinput = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 149;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 151;BA.debugLine="result = result / num";
_result = _result/(double)_num;
 break; }
}
;
 }
};
 //BA.debugLineNum = 156;BA.debugLine="Label1.Text = result";
mostCurrent._label1.setText(BA.ObjectToCharSequence(_result));
 //BA.debugLineNum = 159;BA.debugLine="tokens.Initialize";
mostCurrent._tokens.Initialize();
 //BA.debugLineNum = 160;BA.debugLine="tokens.Add(result)";
mostCurrent._tokens.Add((Object)(_result));
 //BA.debugLineNum = 161;BA.debugLine="isNewInput = True";
_isnewinput = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 162;BA.debugLine="End Sub";
return "";
}
public static String  _buttonequl_longclick() throws Exception{
 //BA.debugLineNum = 260;BA.debugLine="Private Sub Buttonequl_LongClick";
 //BA.debugLineNum = 261;BA.debugLine="B4XGifView1.mBase.Visible = True";
mostCurrent._b4xgifview1._mbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 262;BA.debugLine="B4XGifView1.SetGif(File.DirAssets, \"rick.gif\")";
mostCurrent._b4xgifview1._setgif /*String*/ (anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"rick.gif");
 //BA.debugLineNum = 263;BA.debugLine="End Sub";
return "";
}
public static String  _buttonmul_click() throws Exception{
 //BA.debugLineNum = 102;BA.debugLine="Private Sub Buttonmul_Click";
 //BA.debugLineNum = 103;BA.debugLine="SetOperator(\"*\")";
_setoperator("*");
 //BA.debugLineNum = 104;BA.debugLine="End Sub";
return "";
}
public static String  _buttonplus_click() throws Exception{
 //BA.debugLineNum = 96;BA.debugLine="Private Sub Buttonplus_Click";
 //BA.debugLineNum = 97;BA.debugLine="SetOperator(\"+\")";
_setoperator("+");
 //BA.debugLineNum = 98;BA.debugLine="End Sub";
return "";
}
public static String  _buttonsub_click() throws Exception{
 //BA.debugLineNum = 99;BA.debugLine="Private Sub Buttonsub_Click";
 //BA.debugLineNum = 100;BA.debugLine="SetOperator(\"-\")";
_setoperator("-");
 //BA.debugLineNum = 101;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 21;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 22;BA.debugLine="Private Label1 As Label";
mostCurrent._label1 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 23;BA.debugLine="Private Button0 As Button";
mostCurrent._button0 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 24;BA.debugLine="Private Button1 As Button";
mostCurrent._button1 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 25;BA.debugLine="Private Button2 As Button";
mostCurrent._button2 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 26;BA.debugLine="Private Button3 As Button";
mostCurrent._button3 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Private Button4 As Button";
mostCurrent._button4 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 28;BA.debugLine="Private Button5 As Button";
mostCurrent._button5 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Private Button6 As Button";
mostCurrent._button6 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 30;BA.debugLine="Private Button7 As Button";
mostCurrent._button7 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 31;BA.debugLine="Private Button8 As Button";
mostCurrent._button8 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 32;BA.debugLine="Private Button9 As Button";
mostCurrent._button9 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 33;BA.debugLine="Private Buttonback As Button";
mostCurrent._buttonback = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 34;BA.debugLine="Private Buttondev As Button";
mostCurrent._buttondev = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 35;BA.debugLine="Private Buttonequl As Button";
mostCurrent._buttonequl = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 36;BA.debugLine="Private Buttonmul As Button";
mostCurrent._buttonmul = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 37;BA.debugLine="Private Buttonplus As Button";
mostCurrent._buttonplus = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 38;BA.debugLine="Private Buttonsub As Button";
mostCurrent._buttonsub = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 40;BA.debugLine="Private tokens As List     ' stores tokens e.g. [";
mostCurrent._tokens = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 41;BA.debugLine="Private isNewInput As Boolean";
_isnewinput = false;
 //BA.debugLineNum = 42;BA.debugLine="Private B4XGifView1 As B4XGifView";
mostCurrent._b4xgifview1 = new b4a.example.b4xgifview();
 //BA.debugLineNum = 43;BA.debugLine="End Sub";
return "";
}
public static boolean  _isoperator(String _s) throws Exception{
 //BA.debugLineNum = 256;BA.debugLine="Private Sub IsOperator(s As String) As Boolean";
 //BA.debugLineNum = 257;BA.debugLine="Return s = \"+\" Or s = \"-\" Or s = \"*\" Or s = \"/\"";
if (true) return (_s).equals("+") || (_s).equals("-") || (_s).equals("*") || (_s).equals("/");
 //BA.debugLineNum = 258;BA.debugLine="End Sub";
return false;
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
 //BA.debugLineNum = 15;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 18;BA.debugLine="Private xui As XUI";
_xui = new anywheresoftware.b4a.objects.B4XViewWrapper.XUI();
 //BA.debugLineNum = 19;BA.debugLine="End Sub";
return "";
}
public static String  _setoperator(String _op) throws Exception{
int _lastindex = 0;
String _lasttoken = "";
 //BA.debugLineNum = 229;BA.debugLine="Private Sub SetOperator(op As String)";
 //BA.debugLineNum = 230;BA.debugLine="isNewInput = False";
_isnewinput = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 231;BA.debugLine="Dim lastIndex As Int";
_lastindex = 0;
 //BA.debugLineNum = 232;BA.debugLine="Dim lastToken As String";
_lasttoken = "";
 //BA.debugLineNum = 233;BA.debugLine="lastIndex = tokens.Size - 1";
_lastindex = (int) (mostCurrent._tokens.getSize()-1);
 //BA.debugLineNum = 234;BA.debugLine="lastToken = tokens.Get(lastIndex)";
_lasttoken = BA.ObjectToString(mostCurrent._tokens.Get(_lastindex));
 //BA.debugLineNum = 236;BA.debugLine="If IsOperator(lastToken) Then";
if (_isoperator(_lasttoken)) { 
 //BA.debugLineNum = 237;BA.debugLine="tokens.Set(lastIndex, op)    ' replace operator";
mostCurrent._tokens.Set(_lastindex,(Object)(_op));
 }else {
 //BA.debugLineNum = 239;BA.debugLine="tokens.Add(op)               ' append new operat";
mostCurrent._tokens.Add((Object)(_op));
 };
 //BA.debugLineNum = 241;BA.debugLine="UpdateLabel";
_updatelabel();
 //BA.debugLineNum = 242;BA.debugLine="End Sub";
return "";
}
public static String  _updatelabel() throws Exception{
String _display = "";
int _i = 0;
 //BA.debugLineNum = 245;BA.debugLine="Private Sub UpdateLabel";
 //BA.debugLineNum = 246;BA.debugLine="Dim display As String";
_display = "";
 //BA.debugLineNum = 247;BA.debugLine="display = \"\"";
_display = "";
 //BA.debugLineNum = 248;BA.debugLine="Dim i As Int";
_i = 0;
 //BA.debugLineNum = 249;BA.debugLine="For i = 0 To tokens.Size - 1";
{
final int step4 = 1;
final int limit4 = (int) (mostCurrent._tokens.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit4 ;_i = _i + step4 ) {
 //BA.debugLineNum = 250;BA.debugLine="display = display & tokens.Get(i)";
_display = _display+BA.ObjectToString(mostCurrent._tokens.Get(_i));
 }
};
 //BA.debugLineNum = 252;BA.debugLine="Label1.Text = display";
mostCurrent._label1.setText(BA.ObjectToCharSequence(_display));
 //BA.debugLineNum = 253;BA.debugLine="End Sub";
return "";
}
}
