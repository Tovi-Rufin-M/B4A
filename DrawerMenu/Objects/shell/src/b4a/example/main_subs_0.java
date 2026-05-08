package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class main_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,32);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_create", _firsttime);}
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 32;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(-2147483648);
 BA.debugLineNum = 33;BA.debugLine="Activity.LoadLayout(\"main\")";
Debug.ShouldStop(1);
main.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("main")),main.mostCurrent.activityBA);
 BA.debugLineNum = 35;BA.debugLine="Drawer.Initialize(Me, \"Drawer\", Activity, 260dip)";
Debug.ShouldStop(4);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_initialize" /*RemoteObject*/ ,main.mostCurrent.activityBA,(Object)(main.getObject()),(Object)(BA.ObjectToString("Drawer")),RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), main.mostCurrent._activity.getObject()),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 260)))));
 BA.debugLineNum = 36;BA.debugLine="Drawer.CenterPanel.BringToFront";
Debug.ShouldStop(8);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_getcenterpanel" /*RemoteObject*/ ).runVoidMethod ("BringToFront");
 BA.debugLineNum = 37;BA.debugLine="Drawer.LeftPanel.BringToFront";
Debug.ShouldStop(16);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_getleftpanel" /*RemoteObject*/ ).runVoidMethod ("BringToFront");
 BA.debugLineNum = 39;BA.debugLine="pnlMain = Drawer.CenterPanel";
Debug.ShouldStop(64);
main.mostCurrent._pnlmain = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.PanelWrapper"), main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_getcenterpanel" /*RemoteObject*/ ).getObject());
 BA.debugLineNum = 40;BA.debugLine="pnlMenu = Drawer.LeftPanel";
Debug.ShouldStop(128);
main.mostCurrent._pnlmenu = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.PanelWrapper"), main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_getleftpanel" /*RemoteObject*/ ).getObject());
 BA.debugLineNum = 42;BA.debugLine="pnlMenu.Color = Colors.RGB(33,150,243)";
Debug.ShouldStop(512);
main.mostCurrent._pnlmenu.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").runMethod(true,"RGB",(Object)(BA.numberCast(int.class, 33)),(Object)(BA.numberCast(int.class, 150)),(Object)(BA.numberCast(int.class, 243))));
 BA.debugLineNum = 44;BA.debugLine="CreateMenu";
Debug.ShouldStop(2048);
_createmenu();
 BA.debugLineNum = 45;BA.debugLine="ShowHome";
Debug.ShouldStop(4096);
_showhome();
 BA.debugLineNum = 46;BA.debugLine="End Sub";
Debug.ShouldStop(8192);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _activity_keypress(RemoteObject _keycode) throws Exception{
try {
		Debug.PushSubsStack("Activity_KeyPress (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,174);
if (RapidSub.canDelegate("activity_keypress")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_keypress", _keycode);}
Debug.locals.put("KeyCode", _keycode);
 BA.debugLineNum = 174;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
Debug.ShouldStop(8192);
 BA.debugLineNum = 175;BA.debugLine="If KeyCode = KeyCodes.KEYCODE_BACK And Drawer.Lef";
Debug.ShouldStop(16384);
if (RemoteObject.solveBoolean("=",_keycode,BA.numberCast(double.class, main.mostCurrent.__c.getField(false,"KeyCodes").getField(true,"KEYCODE_BACK"))) && RemoteObject.solveBoolean(".",main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_getleftopen" /*RemoteObject*/ ))) { 
 BA.debugLineNum = 176;BA.debugLine="Drawer.LeftOpen = False";
Debug.ShouldStop(32768);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_setleftopen" /*RemoteObject*/ ,main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 177;BA.debugLine="Return True";
Debug.ShouldStop(65536);
if (true) return main.mostCurrent.__c.getField(true,"True");
 };
 BA.debugLineNum = 179;BA.debugLine="Return False";
Debug.ShouldStop(262144);
if (true) return main.mostCurrent.__c.getField(true,"False");
 BA.debugLineNum = 180;BA.debugLine="End Sub";
Debug.ShouldStop(524288);
return RemoteObject.createImmutable(false);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _activity_pause(RemoteObject _userclosed) throws Exception{
try {
		Debug.PushSubsStack("Activity_Pause (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,52);
if (RapidSub.canDelegate("activity_pause")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_pause", _userclosed);}
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 52;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(524288);
 BA.debugLineNum = 54;BA.debugLine="End Sub";
Debug.ShouldStop(2097152);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _activity_resume() throws Exception{
try {
		Debug.PushSubsStack("Activity_Resume (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,48);
if (RapidSub.canDelegate("activity_resume")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_resume");}
 BA.debugLineNum = 48;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(32768);
 BA.debugLineNum = 50;BA.debugLine="End Sub";
Debug.ShouldStop(131072);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _btnhome_click() throws Exception{
try {
		Debug.PushSubsStack("btnHome_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,95);
if (RapidSub.canDelegate("btnhome_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","btnhome_click");}
 BA.debugLineNum = 95;BA.debugLine="Sub btnHome_Click";
Debug.ShouldStop(1073741824);
 BA.debugLineNum = 96;BA.debugLine="Drawer.LeftOpen = False";
Debug.ShouldStop(-2147483648);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_setleftopen" /*RemoteObject*/ ,main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 97;BA.debugLine="ShowHome";
Debug.ShouldStop(1);
_showhome();
 BA.debugLineNum = 98;BA.debugLine="End Sub";
Debug.ShouldStop(2);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _btnmenu_click() throws Exception{
try {
		Debug.PushSubsStack("btnMenu_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,56);
if (RapidSub.canDelegate("btnmenu_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","btnmenu_click");}
 BA.debugLineNum = 56;BA.debugLine="Sub btnMenu_Click";
Debug.ShouldStop(8388608);
 BA.debugLineNum = 57;BA.debugLine="Drawer.LeftOpen = Not(Drawer.LeftOpen)";
Debug.ShouldStop(16777216);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_setleftopen" /*RemoteObject*/ ,main.mostCurrent.__c.runMethod(true,"Not",(Object)(main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_getleftopen" /*RemoteObject*/ ))));
 BA.debugLineNum = 58;BA.debugLine="End Sub";
Debug.ShouldStop(33554432);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _btnpage1_click() throws Exception{
try {
		Debug.PushSubsStack("btnPage1_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,100);
if (RapidSub.canDelegate("btnpage1_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","btnpage1_click");}
 BA.debugLineNum = 100;BA.debugLine="Sub btnPage1_Click";
Debug.ShouldStop(8);
 BA.debugLineNum = 101;BA.debugLine="Drawer.LeftOpen = False";
Debug.ShouldStop(16);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_setleftopen" /*RemoteObject*/ ,main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 102;BA.debugLine="ShowPage1";
Debug.ShouldStop(32);
_showpage1();
 BA.debugLineNum = 103;BA.debugLine="End Sub";
Debug.ShouldStop(64);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _btnpage2_click() throws Exception{
try {
		Debug.PushSubsStack("btnPage2_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,105);
if (RapidSub.canDelegate("btnpage2_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","btnpage2_click");}
 BA.debugLineNum = 105;BA.debugLine="Sub btnPage2_Click";
Debug.ShouldStop(256);
 BA.debugLineNum = 106;BA.debugLine="Drawer.LeftOpen = False";
Debug.ShouldStop(512);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_setleftopen" /*RemoteObject*/ ,main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 107;BA.debugLine="ShowPage2";
Debug.ShouldStop(1024);
_showpage2();
 BA.debugLineNum = 108;BA.debugLine="End Sub";
Debug.ShouldStop(2048);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _btnpage3_click() throws Exception{
try {
		Debug.PushSubsStack("btnPage3_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,110);
if (RapidSub.canDelegate("btnpage3_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","btnpage3_click");}
 BA.debugLineNum = 110;BA.debugLine="Sub btnPage3_Click";
Debug.ShouldStop(8192);
 BA.debugLineNum = 111;BA.debugLine="Drawer.LeftOpen = False";
Debug.ShouldStop(16384);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_setleftopen" /*RemoteObject*/ ,main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 112;BA.debugLine="ShowPage3";
Debug.ShouldStop(32768);
_showpage3();
 BA.debugLineNum = 113;BA.debugLine="End Sub";
Debug.ShouldStop(65536);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _btnpage4_click() throws Exception{
try {
		Debug.PushSubsStack("btnPage4_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,115);
if (RapidSub.canDelegate("btnpage4_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","btnpage4_click");}
 BA.debugLineNum = 115;BA.debugLine="Sub btnPage4_Click";
Debug.ShouldStop(262144);
 BA.debugLineNum = 116;BA.debugLine="Drawer.LeftOpen = False";
Debug.ShouldStop(524288);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_setleftopen" /*RemoteObject*/ ,main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 117;BA.debugLine="ShowPage4";
Debug.ShouldStop(1048576);
_showpage4();
 BA.debugLineNum = 118;BA.debugLine="End Sub";
Debug.ShouldStop(2097152);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _btnpage5_click() throws Exception{
try {
		Debug.PushSubsStack("btnPage5_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,120);
if (RapidSub.canDelegate("btnpage5_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","btnpage5_click");}
 BA.debugLineNum = 120;BA.debugLine="Sub btnPage5_Click";
Debug.ShouldStop(8388608);
 BA.debugLineNum = 121;BA.debugLine="Drawer.LeftOpen = False";
Debug.ShouldStop(16777216);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_setleftopen" /*RemoteObject*/ ,main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 122;BA.debugLine="ShowPage5";
Debug.ShouldStop(33554432);
_showpage5();
 BA.debugLineNum = 123;BA.debugLine="End Sub";
Debug.ShouldStop(67108864);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _btnpage6_click() throws Exception{
try {
		Debug.PushSubsStack("btnPage6_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,125);
if (RapidSub.canDelegate("btnpage6_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","btnpage6_click");}
 BA.debugLineNum = 125;BA.debugLine="Sub btnPage6_Click";
Debug.ShouldStop(268435456);
 BA.debugLineNum = 126;BA.debugLine="Drawer.LeftOpen = False";
Debug.ShouldStop(536870912);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_setleftopen" /*RemoteObject*/ ,main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 127;BA.debugLine="ShowPage6";
Debug.ShouldStop(1073741824);
_showpage6();
 BA.debugLineNum = 128;BA.debugLine="End Sub";
Debug.ShouldStop(-2147483648);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _createmenu() throws Exception{
try {
		Debug.PushSubsStack("CreateMenu (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,60);
if (RapidSub.canDelegate("createmenu")) { return b4a.example.main.remoteMe.runUserSub(false, "main","createmenu");}
RemoteObject _b = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
 BA.debugLineNum = 60;BA.debugLine="Sub CreateMenu";
Debug.ShouldStop(134217728);
 BA.debugLineNum = 61;BA.debugLine="Dim btnHome, btnPage1, btnPage2 As Button";
Debug.ShouldStop(268435456);
main.mostCurrent._btnhome = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
main.mostCurrent._btnpage1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
main.mostCurrent._btnpage2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 BA.debugLineNum = 62;BA.debugLine="btnHome.Initialize(\"btnHome\")";
Debug.ShouldStop(536870912);
main.mostCurrent._btnhome.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("btnHome")));
 BA.debugLineNum = 63;BA.debugLine="btnHome.Text = \"Home\"";
Debug.ShouldStop(1073741824);
main.mostCurrent._btnhome.runMethod(true,"setText",BA.ObjectToCharSequence("Home"));
 BA.debugLineNum = 64;BA.debugLine="btnPage1.Initialize(\"btnPage1\")";
Debug.ShouldStop(-2147483648);
main.mostCurrent._btnpage1.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("btnPage1")));
 BA.debugLineNum = 65;BA.debugLine="btnPage1.Text = \"Page 1\"";
Debug.ShouldStop(1);
main.mostCurrent._btnpage1.runMethod(true,"setText",BA.ObjectToCharSequence("Page 1"));
 BA.debugLineNum = 66;BA.debugLine="btnPage2.Initialize(\"btnPage2\")";
Debug.ShouldStop(2);
main.mostCurrent._btnpage2.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("btnPage2")));
 BA.debugLineNum = 67;BA.debugLine="btnPage2.Text = \"Page 2\"";
Debug.ShouldStop(4);
main.mostCurrent._btnpage2.runMethod(true,"setText",BA.ObjectToCharSequence("Page 2"));
 BA.debugLineNum = 68;BA.debugLine="btnPage3.Initialize(\"btnPage3\")";
Debug.ShouldStop(8);
main.mostCurrent._btnpage3.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("btnPage3")));
 BA.debugLineNum = 69;BA.debugLine="btnPage3.Text = \"Page 3\"";
Debug.ShouldStop(16);
main.mostCurrent._btnpage3.runMethod(true,"setText",BA.ObjectToCharSequence("Page 3"));
 BA.debugLineNum = 70;BA.debugLine="btnPage4.Initialize(\"btnPage4\")";
Debug.ShouldStop(32);
main.mostCurrent._btnpage4.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("btnPage4")));
 BA.debugLineNum = 71;BA.debugLine="btnPage4.Text = \"Page 4\"";
Debug.ShouldStop(64);
main.mostCurrent._btnpage4.runMethod(true,"setText",BA.ObjectToCharSequence("Page 4"));
 BA.debugLineNum = 72;BA.debugLine="btnPage5.Initialize(\"btnPage5\")";
Debug.ShouldStop(128);
main.mostCurrent._btnpage5.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("btnPage5")));
 BA.debugLineNum = 73;BA.debugLine="btnPage5.Text = \"Page 5\"";
Debug.ShouldStop(256);
main.mostCurrent._btnpage5.runMethod(true,"setText",BA.ObjectToCharSequence("Page 5"));
 BA.debugLineNum = 74;BA.debugLine="btnPage6.Initialize(\"btnPage6\")";
Debug.ShouldStop(512);
main.mostCurrent._btnpage6.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("btnPage6")));
 BA.debugLineNum = 75;BA.debugLine="btnPage6.Text = \"Page 6\"";
Debug.ShouldStop(1024);
main.mostCurrent._btnpage6.runMethod(true,"setText",BA.ObjectToCharSequence("Page 6"));
 BA.debugLineNum = 77;BA.debugLine="For Each b As Button In Array(btnHome, btnPage1,";
Debug.ShouldStop(4096);
_b = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
{
final RemoteObject group16 = RemoteObject.createNewArray("Object",new int[] {7},new Object[] {(main.mostCurrent._btnhome.getObject()),(main.mostCurrent._btnpage1.getObject()),(main.mostCurrent._btnpage2.getObject()),(main.mostCurrent._btnpage3.getObject()),(main.mostCurrent._btnpage4.getObject()),(main.mostCurrent._btnpage5.getObject()),(main.mostCurrent._btnpage6.getObject())});
final int groupLen16 = group16.getField(true,"length").<Integer>get()
;int index16 = 0;
;
for (; index16 < groupLen16;index16++){
_b = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.ButtonWrapper"), group16.getArrayElement(false,RemoteObject.createImmutable(index16)));Debug.locals.put("b", _b);
Debug.locals.put("b", _b);
 BA.debugLineNum = 78;BA.debugLine="b.TextSize = 16";
Debug.ShouldStop(8192);
_b.runMethod(true,"setTextSize",BA.numberCast(float.class, 16));
 BA.debugLineNum = 79;BA.debugLine="b.Gravity = Gravity.LEFT + Gravity.CENTER_VERTIC";
Debug.ShouldStop(16384);
_b.runMethod(true,"setGravity",RemoteObject.solve(new RemoteObject[] {main.mostCurrent.__c.getField(false,"Gravity").getField(true,"LEFT"),main.mostCurrent.__c.getField(false,"Gravity").getField(true,"CENTER_VERTICAL")}, "+",1, 1));
 BA.debugLineNum = 80;BA.debugLine="b.Color = Colors.Transparent";
Debug.ShouldStop(32768);
_b.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Transparent"));
 BA.debugLineNum = 81;BA.debugLine="b.TextColor = Colors.White";
Debug.ShouldStop(65536);
_b.runMethod(true,"setTextColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 BA.debugLineNum = 82;BA.debugLine="pnlMenu.AddView(b, 10dip, 0, 240dip, 50dip)";
Debug.ShouldStop(131072);
main.mostCurrent._pnlmenu.runVoidMethod ("AddView",(Object)((_b.getObject())),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))),(Object)(BA.numberCast(int.class, 0)),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 240)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 50)))));
 }
}Debug.locals.put("b", _b);
;
 BA.debugLineNum = 84;BA.debugLine="btnHome.Top = 120dip";
Debug.ShouldStop(524288);
main.mostCurrent._btnhome.runMethod(true,"setTop",main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 120))));
 BA.debugLineNum = 85;BA.debugLine="btnPage1.Top = 180dip";
Debug.ShouldStop(1048576);
main.mostCurrent._btnpage1.runMethod(true,"setTop",main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 180))));
 BA.debugLineNum = 86;BA.debugLine="btnPage2.Top = 240dip";
Debug.ShouldStop(2097152);
main.mostCurrent._btnpage2.runMethod(true,"setTop",main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 240))));
 BA.debugLineNum = 87;BA.debugLine="btnPage3.Top = 300dip";
Debug.ShouldStop(4194304);
main.mostCurrent._btnpage3.runMethod(true,"setTop",main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 300))));
 BA.debugLineNum = 88;BA.debugLine="btnPage4.Top = 360dip";
Debug.ShouldStop(8388608);
main.mostCurrent._btnpage4.runMethod(true,"setTop",main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 360))));
 BA.debugLineNum = 89;BA.debugLine="btnPage5.Top = 420dip";
Debug.ShouldStop(16777216);
main.mostCurrent._btnpage5.runMethod(true,"setTop",main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 420))));
 BA.debugLineNum = 90;BA.debugLine="btnPage6.Top = 480dip";
Debug.ShouldStop(33554432);
main.mostCurrent._btnpage6.runMethod(true,"setTop",main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 480))));
 BA.debugLineNum = 91;BA.debugLine="End Sub";
Debug.ShouldStop(67108864);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _globals() throws Exception{
 //BA.debugLineNum = 21;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 23;BA.debugLine="Private Drawer As B4XDrawer";
main.mostCurrent._drawer = RemoteObject.createNew ("b4a.example.b4xdrawer");
 //BA.debugLineNum = 24;BA.debugLine="Private pnlMain As Panel";
main.mostCurrent._pnlmain = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 25;BA.debugLine="Private pnlMenu As Panel";
main.mostCurrent._pnlmenu = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 28;BA.debugLine="Private lblTitle As Label";
main.mostCurrent._lbltitle = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 29;BA.debugLine="Private btnHome, btnPage1, btnPage2, btnPage3, bt";
main.mostCurrent._btnhome = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
main.mostCurrent._btnpage1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
main.mostCurrent._btnpage2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
main.mostCurrent._btnpage3 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
main.mostCurrent._btnpage4 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
main.mostCurrent._btnpage5 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
main.mostCurrent._btnpage6 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 30;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _pnl1_click() throws Exception{
try {
		Debug.PushSubsStack("pnl1_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,202);
if (RapidSub.canDelegate("pnl1_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","pnl1_click");}
 BA.debugLineNum = 202;BA.debugLine="Private Sub pnl1_Click";
Debug.ShouldStop(512);
 BA.debugLineNum = 203;BA.debugLine="ShowPage1";
Debug.ShouldStop(1024);
_showpage1();
 BA.debugLineNum = 204;BA.debugLine="End Sub";
Debug.ShouldStop(2048);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _pnl2_click() throws Exception{
try {
		Debug.PushSubsStack("pnl2_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,198);
if (RapidSub.canDelegate("pnl2_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","pnl2_click");}
 BA.debugLineNum = 198;BA.debugLine="Private Sub pnl2_Click";
Debug.ShouldStop(32);
 BA.debugLineNum = 199;BA.debugLine="ShowPage2";
Debug.ShouldStop(64);
_showpage2();
 BA.debugLineNum = 200;BA.debugLine="End Sub";
Debug.ShouldStop(128);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _pnl3_click() throws Exception{
try {
		Debug.PushSubsStack("pnl3_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,194);
if (RapidSub.canDelegate("pnl3_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","pnl3_click");}
 BA.debugLineNum = 194;BA.debugLine="Private Sub pnl3_Click";
Debug.ShouldStop(2);
 BA.debugLineNum = 195;BA.debugLine="ShowPage3";
Debug.ShouldStop(4);
_showpage3();
 BA.debugLineNum = 196;BA.debugLine="End Sub";
Debug.ShouldStop(8);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _pnl4_click() throws Exception{
try {
		Debug.PushSubsStack("pnl4_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,190);
if (RapidSub.canDelegate("pnl4_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","pnl4_click");}
 BA.debugLineNum = 190;BA.debugLine="Private Sub pnl4_Click";
Debug.ShouldStop(536870912);
 BA.debugLineNum = 191;BA.debugLine="ShowPage4";
Debug.ShouldStop(1073741824);
_showpage4();
 BA.debugLineNum = 192;BA.debugLine="End Sub";
Debug.ShouldStop(-2147483648);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _pnl5_click() throws Exception{
try {
		Debug.PushSubsStack("pnl5_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,186);
if (RapidSub.canDelegate("pnl5_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","pnl5_click");}
 BA.debugLineNum = 186;BA.debugLine="Private Sub pnl5_Click";
Debug.ShouldStop(33554432);
 BA.debugLineNum = 187;BA.debugLine="ShowPage5";
Debug.ShouldStop(67108864);
_showpage5();
 BA.debugLineNum = 188;BA.debugLine="End Sub";
Debug.ShouldStop(134217728);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _pnl6_click() throws Exception{
try {
		Debug.PushSubsStack("pnl6_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,182);
if (RapidSub.canDelegate("pnl6_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","pnl6_click");}
 BA.debugLineNum = 182;BA.debugLine="Private Sub pnl6_Click";
Debug.ShouldStop(2097152);
 BA.debugLineNum = 183;BA.debugLine="ShowPage6";
Debug.ShouldStop(4194304);
_showpage6();
 BA.debugLineNum = 184;BA.debugLine="End Sub";
Debug.ShouldStop(8388608);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}

public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        main_subs_0._process_globals();
starter_subs_0._process_globals();
main.myClass = BA.getDeviceClass ("b4a.example.main");
starter.myClass = BA.getDeviceClass ("b4a.example.starter");
b4xdrawer.myClass = BA.getDeviceClass ("b4a.example.b4xdrawer");
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}public static RemoteObject  _process_globals() throws Exception{
 //BA.debugLineNum = 15;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 18;BA.debugLine="Private xui As XUI";
main._xui = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XViewWrapper.XUI");
 //BA.debugLineNum = 19;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _showhome() throws Exception{
try {
		Debug.PushSubsStack("ShowHome (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,132);
if (RapidSub.canDelegate("showhome")) { return b4a.example.main.remoteMe.runUserSub(false, "main","showhome");}
 BA.debugLineNum = 132;BA.debugLine="Sub ShowHome";
Debug.ShouldStop(8);
 BA.debugLineNum = 133;BA.debugLine="pnlMain.RemoveAllViews";
Debug.ShouldStop(16);
main.mostCurrent._pnlmain.runVoidMethod ("RemoveAllViews");
 BA.debugLineNum = 134;BA.debugLine="pnlMain.LoadLayout(\"home\")";
Debug.ShouldStop(32);
main.mostCurrent._pnlmain.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("home")),main.mostCurrent.activityBA);
 BA.debugLineNum = 135;BA.debugLine="lblTitle.Text = \"Home\"";
Debug.ShouldStop(64);
main.mostCurrent._lbltitle.runMethod(true,"setText",BA.ObjectToCharSequence("Home"));
 BA.debugLineNum = 136;BA.debugLine="End Sub";
Debug.ShouldStop(128);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _showpage1() throws Exception{
try {
		Debug.PushSubsStack("ShowPage1 (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,138);
if (RapidSub.canDelegate("showpage1")) { return b4a.example.main.remoteMe.runUserSub(false, "main","showpage1");}
 BA.debugLineNum = 138;BA.debugLine="Sub ShowPage1";
Debug.ShouldStop(512);
 BA.debugLineNum = 139;BA.debugLine="pnlMain.RemoveAllViews";
Debug.ShouldStop(1024);
main.mostCurrent._pnlmain.runVoidMethod ("RemoveAllViews");
 BA.debugLineNum = 140;BA.debugLine="pnlMain.LoadLayout(\"page1\")";
Debug.ShouldStop(2048);
main.mostCurrent._pnlmain.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("page1")),main.mostCurrent.activityBA);
 BA.debugLineNum = 141;BA.debugLine="lblTitle.Text = \"Page 1\"";
Debug.ShouldStop(4096);
main.mostCurrent._lbltitle.runMethod(true,"setText",BA.ObjectToCharSequence("Page 1"));
 BA.debugLineNum = 142;BA.debugLine="End Sub";
Debug.ShouldStop(8192);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _showpage2() throws Exception{
try {
		Debug.PushSubsStack("ShowPage2 (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,144);
if (RapidSub.canDelegate("showpage2")) { return b4a.example.main.remoteMe.runUserSub(false, "main","showpage2");}
 BA.debugLineNum = 144;BA.debugLine="Sub ShowPage2";
Debug.ShouldStop(32768);
 BA.debugLineNum = 145;BA.debugLine="pnlMain.RemoveAllViews";
Debug.ShouldStop(65536);
main.mostCurrent._pnlmain.runVoidMethod ("RemoveAllViews");
 BA.debugLineNum = 146;BA.debugLine="pnlMain.LoadLayout(\"page2\")";
Debug.ShouldStop(131072);
main.mostCurrent._pnlmain.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("page2")),main.mostCurrent.activityBA);
 BA.debugLineNum = 147;BA.debugLine="lblTitle.Text = \"Page 2\"";
Debug.ShouldStop(262144);
main.mostCurrent._lbltitle.runMethod(true,"setText",BA.ObjectToCharSequence("Page 2"));
 BA.debugLineNum = 148;BA.debugLine="End Sub";
Debug.ShouldStop(524288);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _showpage3() throws Exception{
try {
		Debug.PushSubsStack("ShowPage3 (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,150);
if (RapidSub.canDelegate("showpage3")) { return b4a.example.main.remoteMe.runUserSub(false, "main","showpage3");}
 BA.debugLineNum = 150;BA.debugLine="Sub ShowPage3";
Debug.ShouldStop(2097152);
 BA.debugLineNum = 151;BA.debugLine="pnlMain.RemoveAllViews";
Debug.ShouldStop(4194304);
main.mostCurrent._pnlmain.runVoidMethod ("RemoveAllViews");
 BA.debugLineNum = 152;BA.debugLine="pnlMain.LoadLayout(\"page3\")";
Debug.ShouldStop(8388608);
main.mostCurrent._pnlmain.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("page3")),main.mostCurrent.activityBA);
 BA.debugLineNum = 153;BA.debugLine="lblTitle.Text = \"Page 3\"";
Debug.ShouldStop(16777216);
main.mostCurrent._lbltitle.runMethod(true,"setText",BA.ObjectToCharSequence("Page 3"));
 BA.debugLineNum = 154;BA.debugLine="End Sub";
Debug.ShouldStop(33554432);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _showpage4() throws Exception{
try {
		Debug.PushSubsStack("ShowPage4 (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,156);
if (RapidSub.canDelegate("showpage4")) { return b4a.example.main.remoteMe.runUserSub(false, "main","showpage4");}
 BA.debugLineNum = 156;BA.debugLine="Sub ShowPage4";
Debug.ShouldStop(134217728);
 BA.debugLineNum = 157;BA.debugLine="pnlMain.RemoveAllViews";
Debug.ShouldStop(268435456);
main.mostCurrent._pnlmain.runVoidMethod ("RemoveAllViews");
 BA.debugLineNum = 158;BA.debugLine="pnlMain.LoadLayout(\"page4\")";
Debug.ShouldStop(536870912);
main.mostCurrent._pnlmain.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("page4")),main.mostCurrent.activityBA);
 BA.debugLineNum = 159;BA.debugLine="lblTitle.Text = \"Page 4\"";
Debug.ShouldStop(1073741824);
main.mostCurrent._lbltitle.runMethod(true,"setText",BA.ObjectToCharSequence("Page 4"));
 BA.debugLineNum = 160;BA.debugLine="End Sub";
Debug.ShouldStop(-2147483648);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _showpage5() throws Exception{
try {
		Debug.PushSubsStack("ShowPage5 (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,162);
if (RapidSub.canDelegate("showpage5")) { return b4a.example.main.remoteMe.runUserSub(false, "main","showpage5");}
 BA.debugLineNum = 162;BA.debugLine="Sub ShowPage5";
Debug.ShouldStop(2);
 BA.debugLineNum = 163;BA.debugLine="pnlMain.RemoveAllViews";
Debug.ShouldStop(4);
main.mostCurrent._pnlmain.runVoidMethod ("RemoveAllViews");
 BA.debugLineNum = 164;BA.debugLine="pnlMain.LoadLayout(\"page5\")";
Debug.ShouldStop(8);
main.mostCurrent._pnlmain.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("page5")),main.mostCurrent.activityBA);
 BA.debugLineNum = 165;BA.debugLine="lblTitle.Text = \"Page 5\"";
Debug.ShouldStop(16);
main.mostCurrent._lbltitle.runMethod(true,"setText",BA.ObjectToCharSequence("Page 5"));
 BA.debugLineNum = 166;BA.debugLine="End Sub";
Debug.ShouldStop(32);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _showpage6() throws Exception{
try {
		Debug.PushSubsStack("ShowPage6 (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,168);
if (RapidSub.canDelegate("showpage6")) { return b4a.example.main.remoteMe.runUserSub(false, "main","showpage6");}
 BA.debugLineNum = 168;BA.debugLine="Sub ShowPage6";
Debug.ShouldStop(128);
 BA.debugLineNum = 169;BA.debugLine="pnlMain.RemoveAllViews";
Debug.ShouldStop(256);
main.mostCurrent._pnlmain.runVoidMethod ("RemoveAllViews");
 BA.debugLineNum = 170;BA.debugLine="pnlMain.LoadLayout(\"page6\")";
Debug.ShouldStop(512);
main.mostCurrent._pnlmain.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("page6")),main.mostCurrent.activityBA);
 BA.debugLineNum = 171;BA.debugLine="lblTitle.Text = \"Page 6\"";
Debug.ShouldStop(1024);
main.mostCurrent._lbltitle.runMethod(true,"setText",BA.ObjectToCharSequence("Page 6"));
 BA.debugLineNum = 172;BA.debugLine="End Sub";
Debug.ShouldStop(2048);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}