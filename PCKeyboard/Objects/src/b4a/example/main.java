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
public static anywheresoftware.b4a.objects.SocketWrapper _socket1 = null;
public static anywheresoftware.b4a.objects.streams.File.TextWriterWrapper _writer = null;
public static boolean _connected = false;
public anywheresoftware.b4a.objects.ButtonWrapper _btnconnect = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtip = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtport = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblstatus = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btna = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnb = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnc = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnd = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btne = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnf = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btng = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnh = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btni = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnj = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnk = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnl = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnm = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnn = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btno = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnp = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnq = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnr = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btns = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnt = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnu = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnv = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnw = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnx = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btny = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnz = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnspace = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnenter = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnbackspace = null;
public b4a.example.starter _starter = null;
public static String  _activity_create(boolean _firsttime) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_create", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_create", new Object[] {_firsttime}));}
RDebugUtils.currentLine=131072;
 //BA.debugLineNum = 131072;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
RDebugUtils.currentLine=131074;
 //BA.debugLineNum = 131074;BA.debugLine="Activity.Color = Colors.RGB(30, 30, 30)  ' Dark b";
mostCurrent._activity.setColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (30),(int) (30),(int) (30)));
RDebugUtils.currentLine=131077;
 //BA.debugLineNum = 131077;BA.debugLine="txtIP.Initialize(\"txtIP\")";
mostCurrent._txtip.Initialize(mostCurrent.activityBA,"txtIP");
RDebugUtils.currentLine=131078;
 //BA.debugLineNum = 131078;BA.debugLine="txtIP.Text = \"192.168.254.105\"   ' change to your";
mostCurrent._txtip.setText(BA.ObjectToCharSequence("192.168.254.105"));
RDebugUtils.currentLine=131079;
 //BA.debugLineNum = 131079;BA.debugLine="txtIP.TextSize = 14";
mostCurrent._txtip.setTextSize((float) (14));
RDebugUtils.currentLine=131080;
 //BA.debugLineNum = 131080;BA.debugLine="Activity.AddView(txtIP, 5%x, 2%y, 60%x, 8%y)";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._txtip.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (5),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (2),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (60),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (8),mostCurrent.activityBA));
RDebugUtils.currentLine=131083;
 //BA.debugLineNum = 131083;BA.debugLine="txtPort.Initialize(\"txtPort\")";
mostCurrent._txtport.Initialize(mostCurrent.activityBA,"txtPort");
RDebugUtils.currentLine=131084;
 //BA.debugLineNum = 131084;BA.debugLine="txtPort.Text = \"5000\"";
mostCurrent._txtport.setText(BA.ObjectToCharSequence("5000"));
RDebugUtils.currentLine=131085;
 //BA.debugLineNum = 131085;BA.debugLine="txtPort.TextSize = 14";
mostCurrent._txtport.setTextSize((float) (14));
RDebugUtils.currentLine=131086;
 //BA.debugLineNum = 131086;BA.debugLine="Activity.AddView(txtPort, 68%x, 2%y, 27%x, 8%y)";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._txtport.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (68),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (2),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (27),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (8),mostCurrent.activityBA));
RDebugUtils.currentLine=131089;
 //BA.debugLineNum = 131089;BA.debugLine="btnConnect.Initialize(\"btnConnect\")";
mostCurrent._btnconnect.Initialize(mostCurrent.activityBA,"btnConnect");
RDebugUtils.currentLine=131090;
 //BA.debugLineNum = 131090;BA.debugLine="btnConnect.Text = \"Connect\"";
mostCurrent._btnconnect.setText(BA.ObjectToCharSequence("Connect"));
RDebugUtils.currentLine=131091;
 //BA.debugLineNum = 131091;BA.debugLine="btnConnect.Color = Colors.RGB(0, 122, 255)";
mostCurrent._btnconnect.setColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (0),(int) (122),(int) (255)));
RDebugUtils.currentLine=131092;
 //BA.debugLineNum = 131092;BA.debugLine="Activity.AddView(btnConnect, 5%x, 11%y, 90%x, 8%y";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._btnconnect.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (5),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (11),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (90),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (8),mostCurrent.activityBA));
RDebugUtils.currentLine=131095;
 //BA.debugLineNum = 131095;BA.debugLine="lblStatus.Initialize(\"lblStatus\")";
mostCurrent._lblstatus.Initialize(mostCurrent.activityBA,"lblStatus");
RDebugUtils.currentLine=131096;
 //BA.debugLineNum = 131096;BA.debugLine="lblStatus.Text = \"Not connected\"";
mostCurrent._lblstatus.setText(BA.ObjectToCharSequence("Not connected"));
RDebugUtils.currentLine=131097;
 //BA.debugLineNum = 131097;BA.debugLine="lblStatus.TextColor = Colors.Red";
mostCurrent._lblstatus.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Red);
RDebugUtils.currentLine=131098;
 //BA.debugLineNum = 131098;BA.debugLine="lblStatus.Gravity = Gravity.CENTER";
mostCurrent._lblstatus.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
RDebugUtils.currentLine=131099;
 //BA.debugLineNum = 131099;BA.debugLine="Activity.AddView(lblStatus, 5%x, 20%y, 90%x, 5%y)";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._lblstatus.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (5),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (20),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (90),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (5),mostCurrent.activityBA));
RDebugUtils.currentLine=131102;
 //BA.debugLineNum = 131102;BA.debugLine="BuildKeyboardRow(Array As String(\"Q\",\"W\",\"E\",\"R\",";
_buildkeyboardrow(new String[]{"Q","W","E","R","T","Y","U","I","O","P"},(float) (anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (26),mostCurrent.activityBA)));
RDebugUtils.currentLine=131103;
 //BA.debugLineNum = 131103;BA.debugLine="BuildKeyboardRow(Array As String(\"A\",\"S\",\"D\",\"F\",";
_buildkeyboardrow(new String[]{"A","S","D","F","G","H","J","K","L"},(float) (anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (36),mostCurrent.activityBA)));
RDebugUtils.currentLine=131104;
 //BA.debugLineNum = 131104;BA.debugLine="BuildKeyboardRow(Array As String(\"Z\",\"X\",\"C\",\"V\",";
_buildkeyboardrow(new String[]{"Z","X","C","V","B","N","M"},(float) (anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (46),mostCurrent.activityBA)));
RDebugUtils.currentLine=131107;
 //BA.debugLineNum = 131107;BA.debugLine="btnSpace.Initialize(\"btnSpace\")";
mostCurrent._btnspace.Initialize(mostCurrent.activityBA,"btnSpace");
RDebugUtils.currentLine=131108;
 //BA.debugLineNum = 131108;BA.debugLine="btnSpace.Text = \"SPACE\"";
mostCurrent._btnspace.setText(BA.ObjectToCharSequence("SPACE"));
RDebugUtils.currentLine=131109;
 //BA.debugLineNum = 131109;BA.debugLine="btnSpace.Color = Colors.RGB(80, 80, 80)";
mostCurrent._btnspace.setColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (80),(int) (80),(int) (80)));
RDebugUtils.currentLine=131110;
 //BA.debugLineNum = 131110;BA.debugLine="btnSpace.TextColor = Colors.White";
mostCurrent._btnspace.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=131111;
 //BA.debugLineNum = 131111;BA.debugLine="Activity.AddView(btnSpace, 5%x, 56%y, 55%x, 8%y)";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._btnspace.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (5),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (56),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (55),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (8),mostCurrent.activityBA));
RDebugUtils.currentLine=131113;
 //BA.debugLineNum = 131113;BA.debugLine="btnBackspace.Initialize(\"btnBackspace\")";
mostCurrent._btnbackspace.Initialize(mostCurrent.activityBA,"btnBackspace");
RDebugUtils.currentLine=131114;
 //BA.debugLineNum = 131114;BA.debugLine="btnBackspace.Text = \"⌫\"";
mostCurrent._btnbackspace.setText(BA.ObjectToCharSequence("⌫"));
RDebugUtils.currentLine=131115;
 //BA.debugLineNum = 131115;BA.debugLine="btnBackspace.Color = Colors.RGB(180, 60, 60)";
mostCurrent._btnbackspace.setColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (180),(int) (60),(int) (60)));
RDebugUtils.currentLine=131116;
 //BA.debugLineNum = 131116;BA.debugLine="btnBackspace.TextColor = Colors.White";
mostCurrent._btnbackspace.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=131117;
 //BA.debugLineNum = 131117;BA.debugLine="Activity.AddView(btnBackspace, 63%x, 56%y, 32%x,";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._btnbackspace.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (63),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (56),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (32),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (8),mostCurrent.activityBA));
RDebugUtils.currentLine=131119;
 //BA.debugLineNum = 131119;BA.debugLine="btnEnter.Initialize(\"btnEnter\")";
mostCurrent._btnenter.Initialize(mostCurrent.activityBA,"btnEnter");
RDebugUtils.currentLine=131120;
 //BA.debugLineNum = 131120;BA.debugLine="btnEnter.Text = \"ENTER ↵\"";
mostCurrent._btnenter.setText(BA.ObjectToCharSequence("ENTER ↵"));
RDebugUtils.currentLine=131121;
 //BA.debugLineNum = 131121;BA.debugLine="btnEnter.Color = Colors.RGB(0, 180, 80)";
mostCurrent._btnenter.setColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (0),(int) (180),(int) (80)));
RDebugUtils.currentLine=131122;
 //BA.debugLineNum = 131122;BA.debugLine="btnEnter.TextColor = Colors.White";
mostCurrent._btnenter.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=131123;
 //BA.debugLineNum = 131123;BA.debugLine="Activity.AddView(btnEnter, 5%x, 66%y, 90%x, 8%y)";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._btnenter.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (5),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (66),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (90),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (8),mostCurrent.activityBA));
RDebugUtils.currentLine=131125;
 //BA.debugLineNum = 131125;BA.debugLine="connected = False";
_connected = anywheresoftware.b4a.keywords.Common.False;
RDebugUtils.currentLine=131126;
 //BA.debugLineNum = 131126;BA.debugLine="End Sub";
return "";
}
public static String  _buildkeyboardrow(String[] _keys,float _ypos) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "buildkeyboardrow", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "buildkeyboardrow", new Object[] {_keys,_ypos}));}
int _totalkeys = 0;
float _btnwidth = 0f;
float _xstart = 0f;
int _i = 0;
anywheresoftware.b4a.objects.ButtonWrapper _btn = null;
RDebugUtils.currentLine=196608;
 //BA.debugLineNum = 196608;BA.debugLine="Sub BuildKeyboardRow(keys() As String, yPos As Flo";
RDebugUtils.currentLine=196609;
 //BA.debugLineNum = 196609;BA.debugLine="Dim totalKeys As Int = keys.Length";
_totalkeys = _keys.length;
RDebugUtils.currentLine=196610;
 //BA.debugLineNum = 196610;BA.debugLine="Dim btnWidth As Float = 90%x / totalKeys";
_btnwidth = (float) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (90),mostCurrent.activityBA)/(double)_totalkeys);
RDebugUtils.currentLine=196611;
 //BA.debugLineNum = 196611;BA.debugLine="Dim xStart As Float = 5%x";
_xstart = (float) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (5),mostCurrent.activityBA));
RDebugUtils.currentLine=196613;
 //BA.debugLineNum = 196613;BA.debugLine="For i = 0 To totalKeys - 1";
{
final int step4 = 1;
final int limit4 = (int) (_totalkeys-1);
_i = (int) (0) ;
for (;_i <= limit4 ;_i = _i + step4 ) {
RDebugUtils.currentLine=196614;
 //BA.debugLineNum = 196614;BA.debugLine="Dim btn As Button";
_btn = new anywheresoftware.b4a.objects.ButtonWrapper();
RDebugUtils.currentLine=196615;
 //BA.debugLineNum = 196615;BA.debugLine="btn.Initialize(\"btnKey\")";
_btn.Initialize(mostCurrent.activityBA,"btnKey");
RDebugUtils.currentLine=196616;
 //BA.debugLineNum = 196616;BA.debugLine="btn.Text = keys(i)";
_btn.setText(BA.ObjectToCharSequence(_keys[_i]));
RDebugUtils.currentLine=196617;
 //BA.debugLineNum = 196617;BA.debugLine="btn.Color = Colors.RGB(60, 60, 60)";
_btn.setColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (60),(int) (60),(int) (60)));
RDebugUtils.currentLine=196618;
 //BA.debugLineNum = 196618;BA.debugLine="btn.TextColor = Colors.White";
_btn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=196619;
 //BA.debugLineNum = 196619;BA.debugLine="btn.TextSize = 14";
_btn.setTextSize((float) (14));
RDebugUtils.currentLine=196620;
 //BA.debugLineNum = 196620;BA.debugLine="Activity.AddView(btn, xStart + (i * btnWidth), y";
mostCurrent._activity.AddView((android.view.View)(_btn.getObject()),(int) (_xstart+(_i*_btnwidth)),(int) (_ypos),(int) (_btnwidth-anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (1),mostCurrent.activityBA)),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (8),mostCurrent.activityBA));
 }
};
RDebugUtils.currentLine=196622;
 //BA.debugLineNum = 196622;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
RDebugUtils.currentModule="main";
RDebugUtils.currentLine=851968;
 //BA.debugLineNum = 851968;BA.debugLine="Sub Activity_Pause(UserClosed As Boolean)";
RDebugUtils.currentLine=851969;
 //BA.debugLineNum = 851969;BA.debugLine="If UserClosed Then DisconnectFromPC";
if (_userclosed) { 
_disconnectfrompc();};
RDebugUtils.currentLine=851970;
 //BA.debugLineNum = 851970;BA.debugLine="End Sub";
return "";
}
public static String  _disconnectfrompc() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "disconnectfrompc", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "disconnectfrompc", null));}
RDebugUtils.currentLine=458752;
 //BA.debugLineNum = 458752;BA.debugLine="Sub DisconnectFromPC";
RDebugUtils.currentLine=458753;
 //BA.debugLineNum = 458753;BA.debugLine="Try";
try {RDebugUtils.currentLine=458754;
 //BA.debugLineNum = 458754;BA.debugLine="writer.Close";
_writer.Close();
RDebugUtils.currentLine=458755;
 //BA.debugLineNum = 458755;BA.debugLine="socket1.Close";
_socket1.Close();
 } 
       catch (Exception e5) {
			processBA.setLastException(e5); };
RDebugUtils.currentLine=458758;
 //BA.debugLineNum = 458758;BA.debugLine="connected = False";
_connected = anywheresoftware.b4a.keywords.Common.False;
RDebugUtils.currentLine=458759;
 //BA.debugLineNum = 458759;BA.debugLine="lblStatus.Text = \"Disconnected\"";
mostCurrent._lblstatus.setText(BA.ObjectToCharSequence("Disconnected"));
RDebugUtils.currentLine=458760;
 //BA.debugLineNum = 458760;BA.debugLine="lblStatus.TextColor = Colors.Red";
mostCurrent._lblstatus.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Red);
RDebugUtils.currentLine=458761;
 //BA.debugLineNum = 458761;BA.debugLine="btnConnect.Text = \"Connect\"";
mostCurrent._btnconnect.setText(BA.ObjectToCharSequence("Connect"));
RDebugUtils.currentLine=458762;
 //BA.debugLineNum = 458762;BA.debugLine="End Sub";
return "";
}
public static String  _btnbackspace_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btnbackspace_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btnbackspace_click", null));}
RDebugUtils.currentLine=720896;
 //BA.debugLineNum = 720896;BA.debugLine="Sub btnBackspace_Click";
RDebugUtils.currentLine=720897;
 //BA.debugLineNum = 720897;BA.debugLine="SendKey(\"backspace\")";
_sendkey("backspace");
RDebugUtils.currentLine=720898;
 //BA.debugLineNum = 720898;BA.debugLine="End Sub";
return "";
}
public static String  _sendkey(String _key) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "sendkey", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "sendkey", new Object[] {_key}));}
RDebugUtils.currentLine=786432;
 //BA.debugLineNum = 786432;BA.debugLine="Sub SendKey(key As String)";
RDebugUtils.currentLine=786433;
 //BA.debugLineNum = 786433;BA.debugLine="ToastMessageShow(\"Sending: \" & key, False)   '";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Sending: "+_key),anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=786434;
 //BA.debugLineNum = 786434;BA.debugLine="If connected = False Then";
if (_connected==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=786435;
 //BA.debugLineNum = 786435;BA.debugLine="ToastMessageShow(\"Not connected!\", False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Not connected!"),anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=786436;
 //BA.debugLineNum = 786436;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=786438;
 //BA.debugLineNum = 786438;BA.debugLine="Try";
try {RDebugUtils.currentLine=786439;
 //BA.debugLineNum = 786439;BA.debugLine="writer.WriteLine(key)";
_writer.WriteLine(_key);
RDebugUtils.currentLine=786440;
 //BA.debugLineNum = 786440;BA.debugLine="writer.Flush";
_writer.Flush();
 } 
       catch (Exception e10) {
			processBA.setLastException(e10);RDebugUtils.currentLine=786442;
 //BA.debugLineNum = 786442;BA.debugLine="ToastMessageShow(\"Write error!\", False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Write error!"),anywheresoftware.b4a.keywords.Common.False);
 };
RDebugUtils.currentLine=786444;
 //BA.debugLineNum = 786444;BA.debugLine="End Sub";
return "";
}
public static String  _btnconnect_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btnconnect_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btnconnect_click", null));}
RDebugUtils.currentLine=262144;
 //BA.debugLineNum = 262144;BA.debugLine="Sub btnConnect_Click";
RDebugUtils.currentLine=262145;
 //BA.debugLineNum = 262145;BA.debugLine="If connected Then";
if (_connected) { 
RDebugUtils.currentLine=262146;
 //BA.debugLineNum = 262146;BA.debugLine="DisconnectFromPC";
_disconnectfrompc();
 }else {
RDebugUtils.currentLine=262148;
 //BA.debugLineNum = 262148;BA.debugLine="ConnectToPC";
_connecttopc();
 };
RDebugUtils.currentLine=262150;
 //BA.debugLineNum = 262150;BA.debugLine="End Sub";
return "";
}
public static String  _connecttopc() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "connecttopc", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "connecttopc", null));}
String _ip = "";
int _port = 0;
RDebugUtils.currentLine=327680;
 //BA.debugLineNum = 327680;BA.debugLine="Sub ConnectToPC";
RDebugUtils.currentLine=327681;
 //BA.debugLineNum = 327681;BA.debugLine="Dim ip As String = txtIP.Text";
_ip = mostCurrent._txtip.getText();
RDebugUtils.currentLine=327682;
 //BA.debugLineNum = 327682;BA.debugLine="Dim port As Int = txtPort.Text";
_port = (int)(Double.parseDouble(mostCurrent._txtport.getText()));
RDebugUtils.currentLine=327684;
 //BA.debugLineNum = 327684;BA.debugLine="socket1.Initialize(\"socket1\")";
_socket1.Initialize("socket1");
RDebugUtils.currentLine=327685;
 //BA.debugLineNum = 327685;BA.debugLine="socket1.Connect(ip, port, 5000)   ' 5 second time";
_socket1.Connect(processBA,_ip,_port,(int) (5000));
RDebugUtils.currentLine=327686;
 //BA.debugLineNum = 327686;BA.debugLine="End Sub";
return "";
}
public static String  _btnenter_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btnenter_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btnenter_click", null));}
RDebugUtils.currentLine=655360;
 //BA.debugLineNum = 655360;BA.debugLine="Sub btnEnter_Click";
RDebugUtils.currentLine=655361;
 //BA.debugLineNum = 655361;BA.debugLine="SendKey(\"enter\")";
_sendkey("enter");
RDebugUtils.currentLine=655362;
 //BA.debugLineNum = 655362;BA.debugLine="End Sub";
return "";
}
public static String  _btnkey_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btnkey_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btnkey_click", null));}
anywheresoftware.b4a.objects.ButtonWrapper _btn = null;
RDebugUtils.currentLine=524288;
 //BA.debugLineNum = 524288;BA.debugLine="Sub btnKey_Click";
RDebugUtils.currentLine=524289;
 //BA.debugLineNum = 524289;BA.debugLine="Dim btn As Button = Sender";
_btn = new anywheresoftware.b4a.objects.ButtonWrapper();
_btn = (anywheresoftware.b4a.objects.ButtonWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ButtonWrapper(), (android.widget.Button)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
RDebugUtils.currentLine=524290;
 //BA.debugLineNum = 524290;BA.debugLine="SendKey(btn.Text.ToLowerCase)";
_sendkey(_btn.getText().toLowerCase());
RDebugUtils.currentLine=524291;
 //BA.debugLineNum = 524291;BA.debugLine="End Sub";
return "";
}
public static String  _btnspace_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btnspace_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btnspace_click", null));}
RDebugUtils.currentLine=589824;
 //BA.debugLineNum = 589824;BA.debugLine="Sub btnSpace_Click";
RDebugUtils.currentLine=589825;
 //BA.debugLineNum = 589825;BA.debugLine="SendKey(\"space\")";
_sendkey("space");
RDebugUtils.currentLine=589826;
 //BA.debugLineNum = 589826;BA.debugLine="End Sub";
return "";
}
public static String  _socket1_connected(boolean _successful) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "socket1_connected", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "socket1_connected", new Object[] {_successful}));}
RDebugUtils.currentLine=393216;
 //BA.debugLineNum = 393216;BA.debugLine="Sub socket1_Connected(Successful As Boolean)";
RDebugUtils.currentLine=393217;
 //BA.debugLineNum = 393217;BA.debugLine="If Successful Then";
if (_successful) { 
RDebugUtils.currentLine=393218;
 //BA.debugLineNum = 393218;BA.debugLine="writer.Initialize(socket1.OutputStream)";
_writer.Initialize(_socket1.getOutputStream());
RDebugUtils.currentLine=393219;
 //BA.debugLineNum = 393219;BA.debugLine="connected = True";
_connected = anywheresoftware.b4a.keywords.Common.True;
RDebugUtils.currentLine=393220;
 //BA.debugLineNum = 393220;BA.debugLine="ToastMessageShow(\"Connected OK!\", True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Connected OK!"),anywheresoftware.b4a.keywords.Common.True);
 }else {
RDebugUtils.currentLine=393222;
 //BA.debugLineNum = 393222;BA.debugLine="ToastMessageShow(\"FAILED to connect!\", Tru";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("FAILED to connect!"),anywheresoftware.b4a.keywords.Common.True);
 };
RDebugUtils.currentLine=393224;
 //BA.debugLineNum = 393224;BA.debugLine="End Sub";
return "";
}
}