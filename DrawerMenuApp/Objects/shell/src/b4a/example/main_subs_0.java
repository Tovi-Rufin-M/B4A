package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class main_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,41);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_create", _firsttime);}
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 41;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(256);
 BA.debugLineNum = 42;BA.debugLine="Activity.LoadLayout(\"main\")";
Debug.ShouldStop(512);
main.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("main")),main.mostCurrent.activityBA);
 BA.debugLineNum = 44;BA.debugLine="Drawer.Initialize(Me, \"Drawer\", Activity, 260dip)";
Debug.ShouldStop(2048);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_initialize" /*RemoteObject*/ ,main.mostCurrent.activityBA,(Object)(main.getObject()),(Object)(BA.ObjectToString("Drawer")),RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), main.mostCurrent._activity.getObject()),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 260)))));
 BA.debugLineNum = 45;BA.debugLine="Drawer.CenterPanel.BringToFront";
Debug.ShouldStop(4096);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_getcenterpanel" /*RemoteObject*/ ).runVoidMethod ("BringToFront");
 BA.debugLineNum = 46;BA.debugLine="Drawer.LeftPanel.BringToFront";
Debug.ShouldStop(8192);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_getleftpanel" /*RemoteObject*/ ).runVoidMethod ("BringToFront");
 BA.debugLineNum = 48;BA.debugLine="pnlMain = Drawer.CenterPanel";
Debug.ShouldStop(32768);
main.mostCurrent._pnlmain = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.PanelWrapper"), main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_getcenterpanel" /*RemoteObject*/ ).getObject());
 BA.debugLineNum = 49;BA.debugLine="pnlMenu = Drawer.LeftPanel";
Debug.ShouldStop(65536);
main.mostCurrent._pnlmenu = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.PanelWrapper"), main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_getleftpanel" /*RemoteObject*/ ).getObject());
 BA.debugLineNum = 51;BA.debugLine="SetGradient(pnlMenu, Colors.rgb(175, 71, 210), Co";
Debug.ShouldStop(262144);
_setgradient(main.mostCurrent._pnlmenu,main.mostCurrent.__c.getField(false,"Colors").runMethod(true,"RGB",(Object)(BA.numberCast(int.class, 175)),(Object)(BA.numberCast(int.class, 71)),(Object)(BA.numberCast(int.class, 210))),main.mostCurrent.__c.getField(false,"Colors").runMethod(true,"RGB",(Object)(BA.numberCast(int.class, 38)),(Object)(BA.numberCast(int.class, 53)),(Object)(BA.numberCast(int.class, 93))));
 BA.debugLineNum = 53;BA.debugLine="items.Initialize";
Debug.ShouldStop(1048576);
main.mostCurrent._items.runVoidMethod ("Initialize");
 BA.debugLineNum = 55;BA.debugLine="CreateMenu";
Debug.ShouldStop(4194304);
_createmenu();
 BA.debugLineNum = 56;BA.debugLine="ShowHome";
Debug.ShouldStop(8388608);
_showhome();
 BA.debugLineNum = 57;BA.debugLine="End Sub";
Debug.ShouldStop(16777216);
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
		Debug.PushSubsStack("Activity_KeyPress (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,457);
if (RapidSub.canDelegate("activity_keypress")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_keypress", _keycode);}
Debug.locals.put("KeyCode", _keycode);
 BA.debugLineNum = 457;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
Debug.ShouldStop(256);
 BA.debugLineNum = 458;BA.debugLine="If KeyCode = KeyCodes.KEYCODE_BACK And Drawer.Lef";
Debug.ShouldStop(512);
if (RemoteObject.solveBoolean("=",_keycode,BA.numberCast(double.class, main.mostCurrent.__c.getField(false,"KeyCodes").getField(true,"KEYCODE_BACK"))) && RemoteObject.solveBoolean(".",main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_getleftopen" /*RemoteObject*/ ))) { 
 BA.debugLineNum = 459;BA.debugLine="Drawer.LeftOpen = False";
Debug.ShouldStop(1024);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_setleftopen" /*RemoteObject*/ ,main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 460;BA.debugLine="Return True";
Debug.ShouldStop(2048);
if (true) return main.mostCurrent.__c.getField(true,"True");
 }else 
{ BA.debugLineNum = 461;BA.debugLine="Else If KeyCode = KeyCodes.KEYCODE_BACK And Not(D";
Debug.ShouldStop(4096);
if (RemoteObject.solveBoolean("=",_keycode,BA.numberCast(double.class, main.mostCurrent.__c.getField(false,"KeyCodes").getField(true,"KEYCODE_BACK"))) && RemoteObject.solveBoolean(".",main.mostCurrent.__c.runMethod(true,"Not",(Object)(main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_getleftopen" /*RemoteObject*/ ))))) { 
 BA.debugLineNum = 462;BA.debugLine="Drawer.LeftOpen = True";
Debug.ShouldStop(8192);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_setleftopen" /*RemoteObject*/ ,main.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 463;BA.debugLine="Return True";
Debug.ShouldStop(16384);
if (true) return main.mostCurrent.__c.getField(true,"True");
 }}
;
 BA.debugLineNum = 466;BA.debugLine="Return False";
Debug.ShouldStop(131072);
if (true) return main.mostCurrent.__c.getField(true,"False");
 BA.debugLineNum = 467;BA.debugLine="End Sub";
Debug.ShouldStop(262144);
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
		Debug.PushSubsStack("Activity_Pause (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,70);
if (RapidSub.canDelegate("activity_pause")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_pause", _userclosed);}
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 70;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(32);
 BA.debugLineNum = 72;BA.debugLine="End Sub";
Debug.ShouldStop(128);
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
		Debug.PushSubsStack("Activity_Resume (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,66);
if (RapidSub.canDelegate("activity_resume")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_resume");}
 BA.debugLineNum = 66;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(2);
 BA.debugLineNum = 68;BA.debugLine="End Sub";
Debug.ShouldStop(8);
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
		Debug.PushSubsStack("btnHome_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,143);
if (RapidSub.canDelegate("btnhome_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","btnhome_click");}
 BA.debugLineNum = 143;BA.debugLine="Sub btnHome_Click";
Debug.ShouldStop(16384);
 BA.debugLineNum = 144;BA.debugLine="Drawer.LeftOpen = False";
Debug.ShouldStop(32768);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_setleftopen" /*RemoteObject*/ ,main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 145;BA.debugLine="ShowHome";
Debug.ShouldStop(65536);
_showhome();
 BA.debugLineNum = 146;BA.debugLine="End Sub";
Debug.ShouldStop(131072);
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
		Debug.PushSubsStack("btnMenu_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,74);
if (RapidSub.canDelegate("btnmenu_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","btnmenu_click");}
 BA.debugLineNum = 74;BA.debugLine="Sub btnMenu_Click";
Debug.ShouldStop(512);
 BA.debugLineNum = 75;BA.debugLine="Drawer.LeftOpen = Not(Drawer.LeftOpen)";
Debug.ShouldStop(1024);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_setleftopen" /*RemoteObject*/ ,main.mostCurrent.__c.runMethod(true,"Not",(Object)(main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_getleftopen" /*RemoteObject*/ ))));
 BA.debugLineNum = 76;BA.debugLine="End Sub";
Debug.ShouldStop(2048);
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
		Debug.PushSubsStack("btnPage1_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,148);
if (RapidSub.canDelegate("btnpage1_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","btnpage1_click");}
 BA.debugLineNum = 148;BA.debugLine="Sub btnPage1_Click";
Debug.ShouldStop(524288);
 BA.debugLineNum = 149;BA.debugLine="Drawer.LeftOpen = False";
Debug.ShouldStop(1048576);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_setleftopen" /*RemoteObject*/ ,main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 150;BA.debugLine="ShowPage1";
Debug.ShouldStop(2097152);
_showpage1();
 BA.debugLineNum = 151;BA.debugLine="End Sub";
Debug.ShouldStop(4194304);
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
		Debug.PushSubsStack("btnPage2_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,153);
if (RapidSub.canDelegate("btnpage2_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","btnpage2_click");}
 BA.debugLineNum = 153;BA.debugLine="Sub btnPage2_Click";
Debug.ShouldStop(16777216);
 BA.debugLineNum = 154;BA.debugLine="Drawer.LeftOpen = False";
Debug.ShouldStop(33554432);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_setleftopen" /*RemoteObject*/ ,main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 155;BA.debugLine="ShowPage2";
Debug.ShouldStop(67108864);
_showpage2();
 BA.debugLineNum = 156;BA.debugLine="End Sub";
Debug.ShouldStop(134217728);
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
		Debug.PushSubsStack("btnPage3_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,158);
if (RapidSub.canDelegate("btnpage3_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","btnpage3_click");}
 BA.debugLineNum = 158;BA.debugLine="Sub btnPage3_Click";
Debug.ShouldStop(536870912);
 BA.debugLineNum = 159;BA.debugLine="Drawer.LeftOpen = False";
Debug.ShouldStop(1073741824);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_setleftopen" /*RemoteObject*/ ,main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 160;BA.debugLine="ShowPage3";
Debug.ShouldStop(-2147483648);
_showpage3();
 BA.debugLineNum = 161;BA.debugLine="End Sub";
Debug.ShouldStop(1);
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
		Debug.PushSubsStack("btnPage4_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,163);
if (RapidSub.canDelegate("btnpage4_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","btnpage4_click");}
 BA.debugLineNum = 163;BA.debugLine="Sub btnPage4_Click";
Debug.ShouldStop(4);
 BA.debugLineNum = 164;BA.debugLine="Drawer.LeftOpen = False";
Debug.ShouldStop(8);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_setleftopen" /*RemoteObject*/ ,main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 165;BA.debugLine="ShowPage4";
Debug.ShouldStop(16);
_showpage4();
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
public static RemoteObject  _btnpage5_click() throws Exception{
try {
		Debug.PushSubsStack("btnPage5_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,168);
if (RapidSub.canDelegate("btnpage5_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","btnpage5_click");}
 BA.debugLineNum = 168;BA.debugLine="Sub btnPage5_Click";
Debug.ShouldStop(128);
 BA.debugLineNum = 169;BA.debugLine="Drawer.LeftOpen = False";
Debug.ShouldStop(256);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_setleftopen" /*RemoteObject*/ ,main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 170;BA.debugLine="ShowPage5";
Debug.ShouldStop(512);
_showpage5();
 BA.debugLineNum = 171;BA.debugLine="End Sub";
Debug.ShouldStop(1024);
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
		Debug.PushSubsStack("btnPage6_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,173);
if (RapidSub.canDelegate("btnpage6_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","btnpage6_click");}
 BA.debugLineNum = 173;BA.debugLine="Sub btnPage6_Click";
Debug.ShouldStop(4096);
 BA.debugLineNum = 174;BA.debugLine="Drawer.LeftOpen = False";
Debug.ShouldStop(8192);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_setleftopen" /*RemoteObject*/ ,main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 175;BA.debugLine="ShowPage6";
Debug.ShouldStop(16384);
_showpage6();
 BA.debugLineNum = 176;BA.debugLine="End Sub";
Debug.ShouldStop(32768);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _convert() throws Exception{
try {
		Debug.PushSubsStack("convert (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,371);
if (RapidSub.canDelegate("convert")) { return b4a.example.main.remoteMe.runUserSub(false, "main","convert");}
RemoteObject _fromunit = RemoteObject.createImmutable("");
RemoteObject _tounit = RemoteObject.createImmutable("");
RemoteObject _result = RemoteObject.createImmutable("");
RemoteObject _ans = RemoteObject.createImmutable(0);
RemoteObject _input = RemoteObject.createImmutable(0);
RemoteObject _choice = RemoteObject.createImmutable("");
RemoteObject _tometer = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _togram = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _toml = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _toms = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _celsius = RemoteObject.createImmutable(0);
RemoteObject _tom2 = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
 BA.debugLineNum = 371;BA.debugLine="Sub convert";
Debug.ShouldStop(262144);
 BA.debugLineNum = 372;BA.debugLine="Dim fromUnit, toUnit As String";
Debug.ShouldStop(524288);
_fromunit = RemoteObject.createImmutable("");Debug.locals.put("fromUnit", _fromunit);
_tounit = RemoteObject.createImmutable("");Debug.locals.put("toUnit", _tounit);
 BA.debugLineNum = 373;BA.debugLine="Dim result As String";
Debug.ShouldStop(1048576);
_result = RemoteObject.createImmutable("");Debug.locals.put("result", _result);
 BA.debugLineNum = 374;BA.debugLine="Dim ans As Double";
Debug.ShouldStop(2097152);
_ans = RemoteObject.createImmutable(0);Debug.locals.put("ans", _ans);
 BA.debugLineNum = 375;BA.debugLine="Dim input As Double";
Debug.ShouldStop(4194304);
_input = RemoteObject.createImmutable(0);Debug.locals.put("input", _input);
 BA.debugLineNum = 376;BA.debugLine="Dim choice As String = lblTitle.Text";
Debug.ShouldStop(8388608);
_choice = main.mostCurrent._lbltitle.runMethod(true,"getText");Debug.locals.put("choice", _choice);Debug.locals.put("choice", _choice);
 BA.debugLineNum = 378;BA.debugLine="If EditText2.Text = \"\" Then";
Debug.ShouldStop(33554432);
if (RemoteObject.solveBoolean("=",main.mostCurrent._edittext2.runMethod(true,"getText"),BA.ObjectToString(""))) { 
 BA.debugLineNum = 379;BA.debugLine="input = 0";
Debug.ShouldStop(67108864);
_input = BA.numberCast(double.class, 0);Debug.locals.put("input", _input);
 }else {
 BA.debugLineNum = 381;BA.debugLine="input = EditText2.Text";
Debug.ShouldStop(268435456);
_input = BA.numberCast(double.class, main.mostCurrent._edittext2.runMethod(true,"getText"));Debug.locals.put("input", _input);
 };
 BA.debugLineNum = 384;BA.debugLine="fromUnit = Spinner2.SelectedItem";
Debug.ShouldStop(-2147483648);
_fromunit = main.mostCurrent._spinner2.runMethod(true,"getSelectedItem");Debug.locals.put("fromUnit", _fromunit);
 BA.debugLineNum = 385;BA.debugLine="toUnit = Spinner3.SelectedItem";
Debug.ShouldStop(1);
_tounit = main.mostCurrent._spinner3.runMethod(true,"getSelectedItem");Debug.locals.put("toUnit", _tounit);
 BA.debugLineNum = 387;BA.debugLine="Select choice";
Debug.ShouldStop(4);
switch (BA.switchObjectToInt(_choice,BA.ObjectToString("Length Converter"),BA.ObjectToString("Weight Converter"),BA.ObjectToString("Volume Converter"),BA.ObjectToString("Speed Converter"),BA.ObjectToString("Temperature Converter"),BA.ObjectToString("Area Converter"))) {
case 0: {
 BA.debugLineNum = 389;BA.debugLine="Dim toMeter As Map";
Debug.ShouldStop(16);
_tometer = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");Debug.locals.put("toMeter", _tometer);
 BA.debugLineNum = 390;BA.debugLine="toMeter.Initialize";
Debug.ShouldStop(32);
_tometer.runVoidMethod ("Initialize");
 BA.debugLineNum = 391;BA.debugLine="toMeter.Put(\"mm\", 0.001)      : toMeter.Put(\"cm";
Debug.ShouldStop(64);
_tometer.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("mm"))),(Object)(RemoteObject.createImmutable((0.001))));
 BA.debugLineNum = 391;BA.debugLine="toMeter.Put(\"mm\", 0.001)      : toMeter.Put(\"cm";
Debug.ShouldStop(64);
_tometer.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("cm"))),(Object)(RemoteObject.createImmutable((0.01))));
 BA.debugLineNum = 392;BA.debugLine="toMeter.Put(\"m\", 1)           : toMeter.Put(\"km";
Debug.ShouldStop(128);
_tometer.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("m"))),(Object)(RemoteObject.createImmutable((1))));
 BA.debugLineNum = 392;BA.debugLine="toMeter.Put(\"m\", 1)           : toMeter.Put(\"km";
Debug.ShouldStop(128);
_tometer.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("km"))),(Object)(RemoteObject.createImmutable((1000))));
 BA.debugLineNum = 393;BA.debugLine="toMeter.Put(\"in\", 0.0254)     : toMeter.Put(\"ft";
Debug.ShouldStop(256);
_tometer.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("in"))),(Object)(RemoteObject.createImmutable((0.0254))));
 BA.debugLineNum = 393;BA.debugLine="toMeter.Put(\"in\", 0.0254)     : toMeter.Put(\"ft";
Debug.ShouldStop(256);
_tometer.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("ft"))),(Object)(RemoteObject.createImmutable((0.3048))));
 BA.debugLineNum = 394;BA.debugLine="toMeter.Put(\"yd\", 0.9144)     : toMeter.Put(\"mi";
Debug.ShouldStop(512);
_tometer.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("yd"))),(Object)(RemoteObject.createImmutable((0.9144))));
 BA.debugLineNum = 394;BA.debugLine="toMeter.Put(\"yd\", 0.9144)     : toMeter.Put(\"mi";
Debug.ShouldStop(512);
_tometer.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("mi"))),(Object)(RemoteObject.createImmutable((1609.344))));
 BA.debugLineNum = 395;BA.debugLine="toMeter.Put(\"µm\", 0.000001)";
Debug.ShouldStop(1024);
_tometer.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("µm"))),(Object)(RemoteObject.createImmutable((0.000001))));
 BA.debugLineNum = 396;BA.debugLine="ans = input * toMeter.Get(fromUnit) / toMeter.G";
Debug.ShouldStop(2048);
_ans = RemoteObject.solve(new RemoteObject[] {_input,BA.numberCast(double.class, _tometer.runMethod(false,"Get",(Object)((_fromunit)))),BA.numberCast(double.class, _tometer.runMethod(false,"Get",(Object)((_tounit))))}, "*/",0, 0);Debug.locals.put("ans", _ans);
 break; }
case 1: {
 BA.debugLineNum = 399;BA.debugLine="Dim toGram As Map";
Debug.ShouldStop(16384);
_togram = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");Debug.locals.put("toGram", _togram);
 BA.debugLineNum = 400;BA.debugLine="toGram.Initialize";
Debug.ShouldStop(32768);
_togram.runVoidMethod ("Initialize");
 BA.debugLineNum = 401;BA.debugLine="toGram.Put(\"µg\", 0.000001)    : toGram.Put(\"mg\"";
Debug.ShouldStop(65536);
_togram.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("µg"))),(Object)(RemoteObject.createImmutable((0.000001))));
 BA.debugLineNum = 401;BA.debugLine="toGram.Put(\"µg\", 0.000001)    : toGram.Put(\"mg\"";
Debug.ShouldStop(65536);
_togram.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("mg"))),(Object)(RemoteObject.createImmutable((0.001))));
 BA.debugLineNum = 402;BA.debugLine="toGram.Put(\"g\", 1)            : toGram.Put(\"kg\"";
Debug.ShouldStop(131072);
_togram.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("g"))),(Object)(RemoteObject.createImmutable((1))));
 BA.debugLineNum = 402;BA.debugLine="toGram.Put(\"g\", 1)            : toGram.Put(\"kg\"";
Debug.ShouldStop(131072);
_togram.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("kg"))),(Object)(RemoteObject.createImmutable((1000))));
 BA.debugLineNum = 403;BA.debugLine="toGram.Put(\"t\", 1000000)      : toGram.Put(\"oz\"";
Debug.ShouldStop(262144);
_togram.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("t"))),(Object)(RemoteObject.createImmutable((1000000))));
 BA.debugLineNum = 403;BA.debugLine="toGram.Put(\"t\", 1000000)      : toGram.Put(\"oz\"";
Debug.ShouldStop(262144);
_togram.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("oz"))),(Object)(RemoteObject.createImmutable((28.3495))));
 BA.debugLineNum = 404;BA.debugLine="toGram.Put(\"lb\", 453.592)     : toGram.Put(\"st\"";
Debug.ShouldStop(524288);
_togram.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("lb"))),(Object)(RemoteObject.createImmutable((453.592))));
 BA.debugLineNum = 404;BA.debugLine="toGram.Put(\"lb\", 453.592)     : toGram.Put(\"st\"";
Debug.ShouldStop(524288);
_togram.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("st"))),(Object)(RemoteObject.createImmutable((6350.29))));
 BA.debugLineNum = 405;BA.debugLine="toGram.Put(\"ct\", 0.2)         : toGram.Put(\"ton";
Debug.ShouldStop(1048576);
_togram.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("ct"))),(Object)(RemoteObject.createImmutable((0.2))));
 BA.debugLineNum = 405;BA.debugLine="toGram.Put(\"ct\", 0.2)         : toGram.Put(\"ton";
Debug.ShouldStop(1048576);
_togram.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("ton"))),(Object)(RemoteObject.createImmutable((907185))));
 BA.debugLineNum = 406;BA.debugLine="ans = input * toGram.Get(fromUnit) / toGram.Get";
Debug.ShouldStop(2097152);
_ans = RemoteObject.solve(new RemoteObject[] {_input,BA.numberCast(double.class, _togram.runMethod(false,"Get",(Object)((_fromunit)))),BA.numberCast(double.class, _togram.runMethod(false,"Get",(Object)((_tounit))))}, "*/",0, 0);Debug.locals.put("ans", _ans);
 break; }
case 2: {
 BA.debugLineNum = 409;BA.debugLine="Dim toML As Map";
Debug.ShouldStop(16777216);
_toml = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");Debug.locals.put("toML", _toml);
 BA.debugLineNum = 410;BA.debugLine="toML.Initialize";
Debug.ShouldStop(33554432);
_toml.runVoidMethod ("Initialize");
 BA.debugLineNum = 411;BA.debugLine="toML.Put(\"mL\", 1)             : toML.Put(\"L\", 1";
Debug.ShouldStop(67108864);
_toml.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("mL"))),(Object)(RemoteObject.createImmutable((1))));
 BA.debugLineNum = 411;BA.debugLine="toML.Put(\"mL\", 1)             : toML.Put(\"L\", 1";
Debug.ShouldStop(67108864);
_toml.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("L"))),(Object)(RemoteObject.createImmutable((1000))));
 BA.debugLineNum = 412;BA.debugLine="toML.Put(\"kL\", 1000000)       : toML.Put(\"cm³\",";
Debug.ShouldStop(134217728);
_toml.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("kL"))),(Object)(RemoteObject.createImmutable((1000000))));
 BA.debugLineNum = 412;BA.debugLine="toML.Put(\"kL\", 1000000)       : toML.Put(\"cm³\",";
Debug.ShouldStop(134217728);
_toml.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("cm³"))),(Object)(RemoteObject.createImmutable((1))));
 BA.debugLineNum = 413;BA.debugLine="toML.Put(\"m³\", 1000000)       : toML.Put(\"in³\",";
Debug.ShouldStop(268435456);
_toml.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("m³"))),(Object)(RemoteObject.createImmutable((1000000))));
 BA.debugLineNum = 413;BA.debugLine="toML.Put(\"m³\", 1000000)       : toML.Put(\"in³\",";
Debug.ShouldStop(268435456);
_toml.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("in³"))),(Object)(RemoteObject.createImmutable((16.3871))));
 BA.debugLineNum = 414;BA.debugLine="toML.Put(\"ft³\", 28316.8)";
Debug.ShouldStop(536870912);
_toml.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("ft³"))),(Object)(RemoteObject.createImmutable((28316.8))));
 BA.debugLineNum = 415;BA.debugLine="ans = input * toML.Get(fromUnit) / toML.Get(toU";
Debug.ShouldStop(1073741824);
_ans = RemoteObject.solve(new RemoteObject[] {_input,BA.numberCast(double.class, _toml.runMethod(false,"Get",(Object)((_fromunit)))),BA.numberCast(double.class, _toml.runMethod(false,"Get",(Object)((_tounit))))}, "*/",0, 0);Debug.locals.put("ans", _ans);
 break; }
case 3: {
 BA.debugLineNum = 418;BA.debugLine="Dim toMS As Map";
Debug.ShouldStop(2);
_toms = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");Debug.locals.put("toMS", _toms);
 BA.debugLineNum = 419;BA.debugLine="toMS.Initialize";
Debug.ShouldStop(4);
_toms.runVoidMethod ("Initialize");
 BA.debugLineNum = 420;BA.debugLine="toMS.Put(\"m/s\", 1)            : toMS.Put(\"km/h\"";
Debug.ShouldStop(8);
_toms.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("m/s"))),(Object)(RemoteObject.createImmutable((1))));
 BA.debugLineNum = 420;BA.debugLine="toMS.Put(\"m/s\", 1)            : toMS.Put(\"km/h\"";
Debug.ShouldStop(8);
_toms.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("km/h"))),(Object)(RemoteObject.createImmutable((0.277778))));
 BA.debugLineNum = 421;BA.debugLine="toMS.Put(\"mph\", 0.44704)      : toMS.Put(\"knot\"";
Debug.ShouldStop(16);
_toms.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("mph"))),(Object)(RemoteObject.createImmutable((0.44704))));
 BA.debugLineNum = 421;BA.debugLine="toMS.Put(\"mph\", 0.44704)      : toMS.Put(\"knot\"";
Debug.ShouldStop(16);
_toms.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("knot"))),(Object)(RemoteObject.createImmutable((0.514444))));
 BA.debugLineNum = 422;BA.debugLine="toMS.Put(\"ft/s\", 0.3048)";
Debug.ShouldStop(32);
_toms.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("ft/s"))),(Object)(RemoteObject.createImmutable((0.3048))));
 BA.debugLineNum = 423;BA.debugLine="ans = input * toMS.Get(fromUnit) / toMS.Get(toU";
Debug.ShouldStop(64);
_ans = RemoteObject.solve(new RemoteObject[] {_input,BA.numberCast(double.class, _toms.runMethod(false,"Get",(Object)((_fromunit)))),BA.numberCast(double.class, _toms.runMethod(false,"Get",(Object)((_tounit))))}, "*/",0, 0);Debug.locals.put("ans", _ans);
 break; }
case 4: {
 BA.debugLineNum = 426;BA.debugLine="Dim celsius As Double";
Debug.ShouldStop(512);
_celsius = RemoteObject.createImmutable(0);Debug.locals.put("celsius", _celsius);
 BA.debugLineNum = 427;BA.debugLine="Select fromUnit";
Debug.ShouldStop(1024);
switch (BA.switchObjectToInt(_fromunit,BA.ObjectToString("°C"),BA.ObjectToString("°F"),BA.ObjectToString("K"),BA.ObjectToString("°R"))) {
case 0: {
 BA.debugLineNum = 428;BA.debugLine="Case \"°C\" : celsius = input";
Debug.ShouldStop(2048);
_celsius = _input;Debug.locals.put("celsius", _celsius);
 break; }
case 1: {
 BA.debugLineNum = 429;BA.debugLine="Case \"°F\" : celsius = (input - 32) / 1.8";
Debug.ShouldStop(4096);
_celsius = RemoteObject.solve(new RemoteObject[] {(RemoteObject.solve(new RemoteObject[] {_input,RemoteObject.createImmutable(32)}, "-",1, 0)),RemoteObject.createImmutable(1.8)}, "/",0, 0);Debug.locals.put("celsius", _celsius);
 break; }
case 2: {
 BA.debugLineNum = 430;BA.debugLine="Case \"K\"  : celsius = input - 273.15";
Debug.ShouldStop(8192);
_celsius = RemoteObject.solve(new RemoteObject[] {_input,RemoteObject.createImmutable(273.15)}, "-",1, 0);Debug.locals.put("celsius", _celsius);
 break; }
case 3: {
 BA.debugLineNum = 431;BA.debugLine="Case \"°R\" : celsius = (input - 491.67) / 1.8";
Debug.ShouldStop(16384);
_celsius = RemoteObject.solve(new RemoteObject[] {(RemoteObject.solve(new RemoteObject[] {_input,RemoteObject.createImmutable(491.67)}, "-",1, 0)),RemoteObject.createImmutable(1.8)}, "/",0, 0);Debug.locals.put("celsius", _celsius);
 break; }
}
;
 BA.debugLineNum = 433;BA.debugLine="Select toUnit";
Debug.ShouldStop(65536);
switch (BA.switchObjectToInt(_tounit,BA.ObjectToString("°C"),BA.ObjectToString("°F"),BA.ObjectToString("K"),BA.ObjectToString("°R"))) {
case 0: {
 BA.debugLineNum = 434;BA.debugLine="Case \"°C\" : ans = celsius";
Debug.ShouldStop(131072);
_ans = _celsius;Debug.locals.put("ans", _ans);
 break; }
case 1: {
 BA.debugLineNum = 435;BA.debugLine="Case \"°F\" : ans = (celsius * 1.8) + 32";
Debug.ShouldStop(262144);
_ans = RemoteObject.solve(new RemoteObject[] {(RemoteObject.solve(new RemoteObject[] {_celsius,RemoteObject.createImmutable(1.8)}, "*",0, 0)),RemoteObject.createImmutable(32)}, "+",1, 0);Debug.locals.put("ans", _ans);
 break; }
case 2: {
 BA.debugLineNum = 436;BA.debugLine="Case \"K\"  : ans = celsius + 273.15";
Debug.ShouldStop(524288);
_ans = RemoteObject.solve(new RemoteObject[] {_celsius,RemoteObject.createImmutable(273.15)}, "+",1, 0);Debug.locals.put("ans", _ans);
 break; }
case 3: {
 BA.debugLineNum = 437;BA.debugLine="Case \"°R\" : ans = (celsius + 273.15) * 1.8";
Debug.ShouldStop(1048576);
_ans = RemoteObject.solve(new RemoteObject[] {(RemoteObject.solve(new RemoteObject[] {_celsius,RemoteObject.createImmutable(273.15)}, "+",1, 0)),RemoteObject.createImmutable(1.8)}, "*",0, 0);Debug.locals.put("ans", _ans);
 break; }
}
;
 break; }
case 5: {
 BA.debugLineNum = 441;BA.debugLine="Dim toM2 As Map";
Debug.ShouldStop(16777216);
_tom2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");Debug.locals.put("toM2", _tom2);
 BA.debugLineNum = 442;BA.debugLine="toM2.Initialize";
Debug.ShouldStop(33554432);
_tom2.runVoidMethod ("Initialize");
 BA.debugLineNum = 443;BA.debugLine="toM2.Put(\"mm²\", 0.000001)     : toM2.Put(\"cm²\",";
Debug.ShouldStop(67108864);
_tom2.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("mm²"))),(Object)(RemoteObject.createImmutable((0.000001))));
 BA.debugLineNum = 443;BA.debugLine="toM2.Put(\"mm²\", 0.000001)     : toM2.Put(\"cm²\",";
Debug.ShouldStop(67108864);
_tom2.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("cm²"))),(Object)(RemoteObject.createImmutable((0.0001))));
 BA.debugLineNum = 444;BA.debugLine="toM2.Put(\"m²\", 1)             : toM2.Put(\"km²\",";
Debug.ShouldStop(134217728);
_tom2.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("m²"))),(Object)(RemoteObject.createImmutable((1))));
 BA.debugLineNum = 444;BA.debugLine="toM2.Put(\"m²\", 1)             : toM2.Put(\"km²\",";
Debug.ShouldStop(134217728);
_tom2.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("km²"))),(Object)(RemoteObject.createImmutable((1000000))));
 BA.debugLineNum = 445;BA.debugLine="toM2.Put(\"in²\", 0.00064516)   : toM2.Put(\"ft²\",";
Debug.ShouldStop(268435456);
_tom2.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("in²"))),(Object)(RemoteObject.createImmutable((0.00064516))));
 BA.debugLineNum = 445;BA.debugLine="toM2.Put(\"in²\", 0.00064516)   : toM2.Put(\"ft²\",";
Debug.ShouldStop(268435456);
_tom2.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("ft²"))),(Object)(RemoteObject.createImmutable((0.092903))));
 BA.debugLineNum = 446;BA.debugLine="toM2.Put(\"yd²\", 0.836127)";
Debug.ShouldStop(536870912);
_tom2.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("yd²"))),(Object)(RemoteObject.createImmutable((0.836127))));
 BA.debugLineNum = 447;BA.debugLine="ans = input * toM2.Get(fromUnit) / toM2.Get(toU";
Debug.ShouldStop(1073741824);
_ans = RemoteObject.solve(new RemoteObject[] {_input,BA.numberCast(double.class, _tom2.runMethod(false,"Get",(Object)((_fromunit)))),BA.numberCast(double.class, _tom2.runMethod(false,"Get",(Object)((_tounit))))}, "*/",0, 0);Debug.locals.put("ans", _ans);
 break; }
default: {
 BA.debugLineNum = 450;BA.debugLine="ans = 0";
Debug.ShouldStop(2);
_ans = BA.numberCast(double.class, 0);Debug.locals.put("ans", _ans);
 break; }
}
;
 BA.debugLineNum = 453;BA.debugLine="result = NumberFormat(ans, 1, 4) & \" \" & toUnit";
Debug.ShouldStop(16);
_result = RemoteObject.concat(main.mostCurrent.__c.runMethod(true,"NumberFormat",(Object)(_ans),(Object)(BA.numberCast(int.class, 1)),(Object)(BA.numberCast(int.class, 4))),RemoteObject.createImmutable(" "),_tounit);Debug.locals.put("result", _result);
 BA.debugLineNum = 454;BA.debugLine="Label6.Text = result";
Debug.ShouldStop(32);
main.mostCurrent._label6.runMethod(true,"setText",BA.ObjectToCharSequence(_result));
 BA.debugLineNum = 455;BA.debugLine="End Sub";
Debug.ShouldStop(64);
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
		Debug.PushSubsStack("CreateMenu (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,78);
if (RapidSub.canDelegate("createmenu")) { return b4a.example.main.remoteMe.runUserSub(false, "main","createmenu");}
RemoteObject _b = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
 BA.debugLineNum = 78;BA.debugLine="Sub CreateMenu";
Debug.ShouldStop(8192);
 BA.debugLineNum = 79;BA.debugLine="Dim btnHome, btnPage1, btnPage2 As Button";
Debug.ShouldStop(16384);
main.mostCurrent._btnhome = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
main.mostCurrent._btnpage1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
main.mostCurrent._btnpage2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 BA.debugLineNum = 80;BA.debugLine="btnHome.Initialize(\"btnHome\")";
Debug.ShouldStop(32768);
main.mostCurrent._btnhome.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("btnHome")));
 BA.debugLineNum = 81;BA.debugLine="btnHome.Text = \"Home\"";
Debug.ShouldStop(65536);
main.mostCurrent._btnhome.runMethod(true,"setText",BA.ObjectToCharSequence("Home"));
 BA.debugLineNum = 82;BA.debugLine="btnPage1.Initialize(\"btnPage1\")";
Debug.ShouldStop(131072);
main.mostCurrent._btnpage1.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("btnPage1")));
 BA.debugLineNum = 83;BA.debugLine="btnPage1.Text = \"Length Converter\"";
Debug.ShouldStop(262144);
main.mostCurrent._btnpage1.runMethod(true,"setText",BA.ObjectToCharSequence("Length Converter"));
 BA.debugLineNum = 84;BA.debugLine="btnPage2.Initialize(\"btnPage2\")";
Debug.ShouldStop(524288);
main.mostCurrent._btnpage2.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("btnPage2")));
 BA.debugLineNum = 85;BA.debugLine="btnPage2.Text = \"Weight Converter\"";
Debug.ShouldStop(1048576);
main.mostCurrent._btnpage2.runMethod(true,"setText",BA.ObjectToCharSequence("Weight Converter"));
 BA.debugLineNum = 86;BA.debugLine="btnPage3.Initialize(\"btnPage3\")";
Debug.ShouldStop(2097152);
main.mostCurrent._btnpage3.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("btnPage3")));
 BA.debugLineNum = 87;BA.debugLine="btnPage3.Text = \"Volume Converter\"";
Debug.ShouldStop(4194304);
main.mostCurrent._btnpage3.runMethod(true,"setText",BA.ObjectToCharSequence("Volume Converter"));
 BA.debugLineNum = 88;BA.debugLine="btnPage4.Initialize(\"btnPage4\")";
Debug.ShouldStop(8388608);
main.mostCurrent._btnpage4.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("btnPage4")));
 BA.debugLineNum = 89;BA.debugLine="btnPage4.Text = \"Speed Converter\"";
Debug.ShouldStop(16777216);
main.mostCurrent._btnpage4.runMethod(true,"setText",BA.ObjectToCharSequence("Speed Converter"));
 BA.debugLineNum = 90;BA.debugLine="btnPage5.Initialize(\"btnPage5\")";
Debug.ShouldStop(33554432);
main.mostCurrent._btnpage5.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("btnPage5")));
 BA.debugLineNum = 91;BA.debugLine="btnPage5.Text = \"Temperature Converter\"";
Debug.ShouldStop(67108864);
main.mostCurrent._btnpage5.runMethod(true,"setText",BA.ObjectToCharSequence("Temperature Converter"));
 BA.debugLineNum = 92;BA.debugLine="btnPage6.Initialize(\"btnPage6\")";
Debug.ShouldStop(134217728);
main.mostCurrent._btnpage6.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("btnPage6")));
 BA.debugLineNum = 93;BA.debugLine="btnPage6.Text = \"Area Converter\"";
Debug.ShouldStop(268435456);
main.mostCurrent._btnpage6.runMethod(true,"setText",BA.ObjectToCharSequence("Area Converter"));
 BA.debugLineNum = 95;BA.debugLine="For Each b As Button In Array(btnHome, btnPage1,";
Debug.ShouldStop(1073741824);
_b = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
{
final RemoteObject group16 = RemoteObject.createNewArray("Object",new int[] {7},new Object[] {(main.mostCurrent._btnhome.getObject()),(main.mostCurrent._btnpage1.getObject()),(main.mostCurrent._btnpage2.getObject()),(main.mostCurrent._btnpage3.getObject()),(main.mostCurrent._btnpage4.getObject()),(main.mostCurrent._btnpage5.getObject()),(main.mostCurrent._btnpage6.getObject())});
final int groupLen16 = group16.getField(true,"length").<Integer>get()
;int index16 = 0;
;
for (; index16 < groupLen16;index16++){
_b = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.ButtonWrapper"), group16.getArrayElement(false,RemoteObject.createImmutable(index16)));Debug.locals.put("b", _b);
Debug.locals.put("b", _b);
 BA.debugLineNum = 96;BA.debugLine="b.TextSize = 16";
Debug.ShouldStop(-2147483648);
_b.runMethod(true,"setTextSize",BA.numberCast(float.class, 16));
 BA.debugLineNum = 97;BA.debugLine="b.Gravity = Gravity.LEFT + Gravity.CENTER_VERTIC";
Debug.ShouldStop(1);
_b.runMethod(true,"setGravity",RemoteObject.solve(new RemoteObject[] {main.mostCurrent.__c.getField(false,"Gravity").getField(true,"LEFT"),main.mostCurrent.__c.getField(false,"Gravity").getField(true,"CENTER_VERTICAL")}, "+",1, 1));
 BA.debugLineNum = 98;BA.debugLine="b.Color = Colors.Transparent";
Debug.ShouldStop(2);
_b.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Transparent"));
 BA.debugLineNum = 99;BA.debugLine="b.TextColor = Colors.White";
Debug.ShouldStop(4);
_b.runMethod(true,"setTextColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 BA.debugLineNum = 100;BA.debugLine="pnlMenu.AddView(b, 10dip, 0, 240dip, 50dip)";
Debug.ShouldStop(8);
main.mostCurrent._pnlmenu.runVoidMethod ("AddView",(Object)((_b.getObject())),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))),(Object)(BA.numberCast(int.class, 0)),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 240)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 50)))));
 }
}Debug.locals.put("b", _b);
;
 BA.debugLineNum = 102;BA.debugLine="btnHome.Top = 120dip";
Debug.ShouldStop(32);
main.mostCurrent._btnhome.runMethod(true,"setTop",main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 120))));
 BA.debugLineNum = 103;BA.debugLine="btnPage1.Top = 180dip";
Debug.ShouldStop(64);
main.mostCurrent._btnpage1.runMethod(true,"setTop",main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 180))));
 BA.debugLineNum = 104;BA.debugLine="btnPage2.Top = 240dip";
Debug.ShouldStop(128);
main.mostCurrent._btnpage2.runMethod(true,"setTop",main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 240))));
 BA.debugLineNum = 105;BA.debugLine="btnPage3.Top = 300dip";
Debug.ShouldStop(256);
main.mostCurrent._btnpage3.runMethod(true,"setTop",main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 300))));
 BA.debugLineNum = 106;BA.debugLine="btnPage4.Top = 360dip";
Debug.ShouldStop(512);
main.mostCurrent._btnpage4.runMethod(true,"setTop",main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 360))));
 BA.debugLineNum = 107;BA.debugLine="btnPage5.Top = 420dip";
Debug.ShouldStop(1024);
main.mostCurrent._btnpage5.runMethod(true,"setTop",main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 420))));
 BA.debugLineNum = 108;BA.debugLine="btnPage6.Top = 480dip";
Debug.ShouldStop(2048);
main.mostCurrent._btnpage6.runMethod(true,"setTop",main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 480))));
 BA.debugLineNum = 109;BA.debugLine="End Sub";
Debug.ShouldStop(4096);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _edittext1_enterpressed() throws Exception{
try {
		Debug.PushSubsStack("EditText1_EnterPressed (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,485);
if (RapidSub.canDelegate("edittext1_enterpressed")) { return b4a.example.main.remoteMe.runUserSub(false, "main","edittext1_enterpressed");}
 BA.debugLineNum = 485;BA.debugLine="Private Sub EditText1_EnterPressed";
Debug.ShouldStop(16);
 BA.debugLineNum = 486;BA.debugLine="qick";
Debug.ShouldStop(32);
_qick();
 BA.debugLineNum = 487;BA.debugLine="End Sub";
Debug.ShouldStop(64);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _edittext2_enterpressed() throws Exception{
try {
		Debug.PushSubsStack("EditText2_EnterPressed (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,481);
if (RapidSub.canDelegate("edittext2_enterpressed")) { return b4a.example.main.remoteMe.runUserSub(false, "main","edittext2_enterpressed");}
 BA.debugLineNum = 481;BA.debugLine="Private Sub EditText2_EnterPressed";
Debug.ShouldStop(1);
 BA.debugLineNum = 482;BA.debugLine="convert";
Debug.ShouldStop(2);
_convert();
 BA.debugLineNum = 483;BA.debugLine="End Sub";
Debug.ShouldStop(4);
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
 //BA.debugLineNum = 31;BA.debugLine="Private EditText1 As EditText";
main.mostCurrent._edittext1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 32;BA.debugLine="Private Label1 As Label";
main.mostCurrent._label1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 34;BA.debugLine="Private items As List";
main.mostCurrent._items = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
 //BA.debugLineNum = 35;BA.debugLine="Private Spinner1, Spinner2, Spinner3 As Spinner";
main.mostCurrent._spinner1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.SpinnerWrapper");
main.mostCurrent._spinner2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.SpinnerWrapper");
main.mostCurrent._spinner3 = RemoteObject.createNew ("anywheresoftware.b4a.objects.SpinnerWrapper");
 //BA.debugLineNum = 36;BA.debugLine="Private Label3 As Label";
main.mostCurrent._label3 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 37;BA.debugLine="Private Label6 As Label";
main.mostCurrent._label6 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 38;BA.debugLine="Private EditText2 As EditText";
main.mostCurrent._edittext2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 39;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _pnl1_click() throws Exception{
try {
		Debug.PushSubsStack("pnl1_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,138);
if (RapidSub.canDelegate("pnl1_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","pnl1_click");}
 BA.debugLineNum = 138;BA.debugLine="Private Sub pnl1_Click";
Debug.ShouldStop(512);
 BA.debugLineNum = 139;BA.debugLine="Drawer.LeftOpen = False";
Debug.ShouldStop(1024);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_setleftopen" /*RemoteObject*/ ,main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 140;BA.debugLine="ShowPage1";
Debug.ShouldStop(2048);
_showpage1();
 BA.debugLineNum = 141;BA.debugLine="End Sub";
Debug.ShouldStop(4096);
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
		Debug.PushSubsStack("pnl2_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,133);
if (RapidSub.canDelegate("pnl2_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","pnl2_click");}
 BA.debugLineNum = 133;BA.debugLine="Private Sub pnl2_Click";
Debug.ShouldStop(16);
 BA.debugLineNum = 134;BA.debugLine="Drawer.LeftOpen = False";
Debug.ShouldStop(32);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_setleftopen" /*RemoteObject*/ ,main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 135;BA.debugLine="ShowPage2";
Debug.ShouldStop(64);
_showpage2();
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
public static RemoteObject  _pnl3_click() throws Exception{
try {
		Debug.PushSubsStack("pnl3_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,128);
if (RapidSub.canDelegate("pnl3_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","pnl3_click");}
 BA.debugLineNum = 128;BA.debugLine="Private Sub pnl3_Click";
Debug.ShouldStop(-2147483648);
 BA.debugLineNum = 129;BA.debugLine="Drawer.LeftOpen = False";
Debug.ShouldStop(1);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_setleftopen" /*RemoteObject*/ ,main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 130;BA.debugLine="ShowPage3";
Debug.ShouldStop(2);
_showpage3();
 BA.debugLineNum = 131;BA.debugLine="End Sub";
Debug.ShouldStop(4);
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
		Debug.PushSubsStack("pnl4_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,123);
if (RapidSub.canDelegate("pnl4_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","pnl4_click");}
 BA.debugLineNum = 123;BA.debugLine="Private Sub pnl4_Click";
Debug.ShouldStop(67108864);
 BA.debugLineNum = 124;BA.debugLine="Drawer.LeftOpen = False";
Debug.ShouldStop(134217728);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_setleftopen" /*RemoteObject*/ ,main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 125;BA.debugLine="ShowPage4";
Debug.ShouldStop(268435456);
_showpage4();
 BA.debugLineNum = 126;BA.debugLine="End Sub";
Debug.ShouldStop(536870912);
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
		Debug.PushSubsStack("pnl5_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,118);
if (RapidSub.canDelegate("pnl5_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","pnl5_click");}
 BA.debugLineNum = 118;BA.debugLine="Private Sub pnl5_Click";
Debug.ShouldStop(2097152);
 BA.debugLineNum = 119;BA.debugLine="Drawer.LeftOpen = False";
Debug.ShouldStop(4194304);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_setleftopen" /*RemoteObject*/ ,main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 120;BA.debugLine="ShowPage5";
Debug.ShouldStop(8388608);
_showpage5();
 BA.debugLineNum = 121;BA.debugLine="End Sub";
Debug.ShouldStop(16777216);
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
		Debug.PushSubsStack("pnl6_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,113);
if (RapidSub.canDelegate("pnl6_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","pnl6_click");}
 BA.debugLineNum = 113;BA.debugLine="Private Sub pnl6_Click";
Debug.ShouldStop(65536);
 BA.debugLineNum = 114;BA.debugLine="Drawer.LeftOpen = False";
Debug.ShouldStop(131072);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_setleftopen" /*RemoteObject*/ ,main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 115;BA.debugLine="ShowPage6";
Debug.ShouldStop(262144);
_showpage6();
 BA.debugLineNum = 116;BA.debugLine="End Sub";
Debug.ShouldStop(524288);
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
public static RemoteObject  _qick() throws Exception{
try {
		Debug.PushSubsStack("qick (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,264);
if (RapidSub.canDelegate("qick")) { return b4a.example.main.remoteMe.runUserSub(false, "main","qick");}
RemoteObject _spin = RemoteObject.createImmutable("");
RemoteObject _choice = RemoteObject.createImmutable("");
RemoteObject _firstinput = RemoteObject.createImmutable(0);
RemoteObject _result = RemoteObject.createImmutable("");
RemoteObject _parts = null;
RemoteObject _fromunit = RemoteObject.createImmutable("");
RemoteObject _tounit = RemoteObject.createImmutable("");
RemoteObject _ans = RemoteObject.createImmutable(0);
RemoteObject _tometer = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _togram = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _toml = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _toms = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _celsius = RemoteObject.createImmutable(0);
RemoteObject _tom2 = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
 BA.debugLineNum = 264;BA.debugLine="Sub qick";
Debug.ShouldStop(128);
 BA.debugLineNum = 265;BA.debugLine="Dim spin As String = Spinner1.SelectedItem";
Debug.ShouldStop(256);
_spin = main.mostCurrent._spinner1.runMethod(true,"getSelectedItem");Debug.locals.put("spin", _spin);Debug.locals.put("spin", _spin);
 BA.debugLineNum = 266;BA.debugLine="Dim choice As String = lblTitle.Text";
Debug.ShouldStop(512);
_choice = main.mostCurrent._lbltitle.runMethod(true,"getText");Debug.locals.put("choice", _choice);Debug.locals.put("choice", _choice);
 BA.debugLineNum = 267;BA.debugLine="Dim firstinput As Double";
Debug.ShouldStop(1024);
_firstinput = RemoteObject.createImmutable(0);Debug.locals.put("firstinput", _firstinput);
 BA.debugLineNum = 268;BA.debugLine="If EditText1.Text = \"\" Then";
Debug.ShouldStop(2048);
if (RemoteObject.solveBoolean("=",main.mostCurrent._edittext1.runMethod(true,"getText"),BA.ObjectToString(""))) { 
 BA.debugLineNum = 269;BA.debugLine="firstinput = 0";
Debug.ShouldStop(4096);
_firstinput = BA.numberCast(double.class, 0);Debug.locals.put("firstinput", _firstinput);
 }else {
 BA.debugLineNum = 271;BA.debugLine="firstinput = EditText1.Text";
Debug.ShouldStop(16384);
_firstinput = BA.numberCast(double.class, main.mostCurrent._edittext1.runMethod(true,"getText"));Debug.locals.put("firstinput", _firstinput);
 };
 BA.debugLineNum = 273;BA.debugLine="Dim result As String";
Debug.ShouldStop(65536);
_result = RemoteObject.createImmutable("");Debug.locals.put("result", _result);
 BA.debugLineNum = 274;BA.debugLine="Dim parts() As String = Regex.Split(\" to \", spin)";
Debug.ShouldStop(131072);
_parts = main.mostCurrent.__c.getField(false,"Regex").runMethod(false,"Split",(Object)(BA.ObjectToString(" to ")),(Object)(_spin));Debug.locals.put("parts", _parts);Debug.locals.put("parts", _parts);
 BA.debugLineNum = 275;BA.debugLine="Dim fromUnit As String = parts(0)";
Debug.ShouldStop(262144);
_fromunit = _parts.getArrayElement(true,BA.numberCast(int.class, 0));Debug.locals.put("fromUnit", _fromunit);Debug.locals.put("fromUnit", _fromunit);
 BA.debugLineNum = 276;BA.debugLine="Dim toUnit As String = parts(1)";
Debug.ShouldStop(524288);
_tounit = _parts.getArrayElement(true,BA.numberCast(int.class, 1));Debug.locals.put("toUnit", _tounit);Debug.locals.put("toUnit", _tounit);
 BA.debugLineNum = 277;BA.debugLine="Dim ans As Double";
Debug.ShouldStop(1048576);
_ans = RemoteObject.createImmutable(0);Debug.locals.put("ans", _ans);
 BA.debugLineNum = 279;BA.debugLine="Select choice";
Debug.ShouldStop(4194304);
switch (BA.switchObjectToInt(_choice,BA.ObjectToString("Length Converter"),BA.ObjectToString("Weight Converter"),BA.ObjectToString("Volume Converter"),BA.ObjectToString("Speed Converter"),BA.ObjectToString("Temperature Converter"),BA.ObjectToString("Area Converter"))) {
case 0: {
 BA.debugLineNum = 281;BA.debugLine="Dim toMeter As Map";
Debug.ShouldStop(16777216);
_tometer = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");Debug.locals.put("toMeter", _tometer);
 BA.debugLineNum = 282;BA.debugLine="toMeter.Initialize";
Debug.ShouldStop(33554432);
_tometer.runVoidMethod ("Initialize");
 BA.debugLineNum = 283;BA.debugLine="toMeter.Put(\"mm\", 0.001)";
Debug.ShouldStop(67108864);
_tometer.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("mm"))),(Object)(RemoteObject.createImmutable((0.001))));
 BA.debugLineNum = 284;BA.debugLine="toMeter.Put(\"cm\", 0.01)";
Debug.ShouldStop(134217728);
_tometer.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("cm"))),(Object)(RemoteObject.createImmutable((0.01))));
 BA.debugLineNum = 285;BA.debugLine="toMeter.Put(\"m\", 1)";
Debug.ShouldStop(268435456);
_tometer.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("m"))),(Object)(RemoteObject.createImmutable((1))));
 BA.debugLineNum = 286;BA.debugLine="toMeter.Put(\"km\", 1000)";
Debug.ShouldStop(536870912);
_tometer.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("km"))),(Object)(RemoteObject.createImmutable((1000))));
 BA.debugLineNum = 287;BA.debugLine="toMeter.Put(\"in\", 0.0254)";
Debug.ShouldStop(1073741824);
_tometer.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("in"))),(Object)(RemoteObject.createImmutable((0.0254))));
 BA.debugLineNum = 288;BA.debugLine="toMeter.Put(\"ft\", 0.3048)";
Debug.ShouldStop(-2147483648);
_tometer.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("ft"))),(Object)(RemoteObject.createImmutable((0.3048))));
 BA.debugLineNum = 289;BA.debugLine="toMeter.Put(\"yd\", 0.9144)";
Debug.ShouldStop(1);
_tometer.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("yd"))),(Object)(RemoteObject.createImmutable((0.9144))));
 BA.debugLineNum = 290;BA.debugLine="toMeter.Put(\"mi\", 1609.344)";
Debug.ShouldStop(2);
_tometer.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("mi"))),(Object)(RemoteObject.createImmutable((1609.344))));
 BA.debugLineNum = 291;BA.debugLine="toMeter.Put(\"µm\", 0.000001)";
Debug.ShouldStop(4);
_tometer.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("µm"))),(Object)(RemoteObject.createImmutable((0.000001))));
 BA.debugLineNum = 292;BA.debugLine="ans = firstinput * toMeter.Get(fromUnit) / toMe";
Debug.ShouldStop(8);
_ans = RemoteObject.solve(new RemoteObject[] {_firstinput,BA.numberCast(double.class, _tometer.runMethod(false,"Get",(Object)((_fromunit)))),BA.numberCast(double.class, _tometer.runMethod(false,"Get",(Object)((_tounit))))}, "*/",0, 0);Debug.locals.put("ans", _ans);
 BA.debugLineNum = 293;BA.debugLine="result = NumberFormat(ans, 1, 4) & \" \" & toUnit";
Debug.ShouldStop(16);
_result = RemoteObject.concat(main.mostCurrent.__c.runMethod(true,"NumberFormat",(Object)(_ans),(Object)(BA.numberCast(int.class, 1)),(Object)(BA.numberCast(int.class, 4))),RemoteObject.createImmutable(" "),_tounit);Debug.locals.put("result", _result);
 break; }
case 1: {
 BA.debugLineNum = 296;BA.debugLine="Dim toGram As Map";
Debug.ShouldStop(128);
_togram = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");Debug.locals.put("toGram", _togram);
 BA.debugLineNum = 297;BA.debugLine="toGram.Initialize";
Debug.ShouldStop(256);
_togram.runVoidMethod ("Initialize");
 BA.debugLineNum = 298;BA.debugLine="toGram.Put(\"µg\", 0.000001)";
Debug.ShouldStop(512);
_togram.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("µg"))),(Object)(RemoteObject.createImmutable((0.000001))));
 BA.debugLineNum = 299;BA.debugLine="toGram.Put(\"mg\", 0.001)";
Debug.ShouldStop(1024);
_togram.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("mg"))),(Object)(RemoteObject.createImmutable((0.001))));
 BA.debugLineNum = 300;BA.debugLine="toGram.Put(\"g\", 1)";
Debug.ShouldStop(2048);
_togram.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("g"))),(Object)(RemoteObject.createImmutable((1))));
 BA.debugLineNum = 301;BA.debugLine="toGram.Put(\"kg\", 1000)";
Debug.ShouldStop(4096);
_togram.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("kg"))),(Object)(RemoteObject.createImmutable((1000))));
 BA.debugLineNum = 302;BA.debugLine="toGram.Put(\"t\", 1000000)";
Debug.ShouldStop(8192);
_togram.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("t"))),(Object)(RemoteObject.createImmutable((1000000))));
 BA.debugLineNum = 303;BA.debugLine="toGram.Put(\"oz\", 28.3495)";
Debug.ShouldStop(16384);
_togram.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("oz"))),(Object)(RemoteObject.createImmutable((28.3495))));
 BA.debugLineNum = 304;BA.debugLine="toGram.Put(\"lb\", 453.592)";
Debug.ShouldStop(32768);
_togram.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("lb"))),(Object)(RemoteObject.createImmutable((453.592))));
 BA.debugLineNum = 305;BA.debugLine="toGram.Put(\"st\", 6350.29)";
Debug.ShouldStop(65536);
_togram.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("st"))),(Object)(RemoteObject.createImmutable((6350.29))));
 BA.debugLineNum = 306;BA.debugLine="toGram.Put(\"ct\", 0.2)";
Debug.ShouldStop(131072);
_togram.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("ct"))),(Object)(RemoteObject.createImmutable((0.2))));
 BA.debugLineNum = 307;BA.debugLine="toGram.Put(\"ton\", 907185)";
Debug.ShouldStop(262144);
_togram.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("ton"))),(Object)(RemoteObject.createImmutable((907185))));
 BA.debugLineNum = 308;BA.debugLine="ans = firstinput * toGram.Get(fromUnit) / toGra";
Debug.ShouldStop(524288);
_ans = RemoteObject.solve(new RemoteObject[] {_firstinput,BA.numberCast(double.class, _togram.runMethod(false,"Get",(Object)((_fromunit)))),BA.numberCast(double.class, _togram.runMethod(false,"Get",(Object)((_tounit))))}, "*/",0, 0);Debug.locals.put("ans", _ans);
 BA.debugLineNum = 309;BA.debugLine="result = NumberFormat(ans, 1, 4) & \" \" & toUnit";
Debug.ShouldStop(1048576);
_result = RemoteObject.concat(main.mostCurrent.__c.runMethod(true,"NumberFormat",(Object)(_ans),(Object)(BA.numberCast(int.class, 1)),(Object)(BA.numberCast(int.class, 4))),RemoteObject.createImmutable(" "),_tounit);Debug.locals.put("result", _result);
 break; }
case 2: {
 BA.debugLineNum = 312;BA.debugLine="Dim toML As Map";
Debug.ShouldStop(8388608);
_toml = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");Debug.locals.put("toML", _toml);
 BA.debugLineNum = 313;BA.debugLine="toML.Initialize";
Debug.ShouldStop(16777216);
_toml.runVoidMethod ("Initialize");
 BA.debugLineNum = 314;BA.debugLine="toML.Put(\"mL\", 1)";
Debug.ShouldStop(33554432);
_toml.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("mL"))),(Object)(RemoteObject.createImmutable((1))));
 BA.debugLineNum = 315;BA.debugLine="toML.Put(\"L\", 1000)";
Debug.ShouldStop(67108864);
_toml.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("L"))),(Object)(RemoteObject.createImmutable((1000))));
 BA.debugLineNum = 316;BA.debugLine="toML.Put(\"kL\", 1000000)";
Debug.ShouldStop(134217728);
_toml.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("kL"))),(Object)(RemoteObject.createImmutable((1000000))));
 BA.debugLineNum = 317;BA.debugLine="toML.Put(\"cm³\", 1)";
Debug.ShouldStop(268435456);
_toml.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("cm³"))),(Object)(RemoteObject.createImmutable((1))));
 BA.debugLineNum = 318;BA.debugLine="toML.Put(\"m³\", 1000000)";
Debug.ShouldStop(536870912);
_toml.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("m³"))),(Object)(RemoteObject.createImmutable((1000000))));
 BA.debugLineNum = 319;BA.debugLine="toML.Put(\"in³\", 16.3871)";
Debug.ShouldStop(1073741824);
_toml.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("in³"))),(Object)(RemoteObject.createImmutable((16.3871))));
 BA.debugLineNum = 320;BA.debugLine="toML.Put(\"ft³\", 28316.8)";
Debug.ShouldStop(-2147483648);
_toml.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("ft³"))),(Object)(RemoteObject.createImmutable((28316.8))));
 BA.debugLineNum = 321;BA.debugLine="ans = firstinput * toML.Get(fromUnit) / toML.Ge";
Debug.ShouldStop(1);
_ans = RemoteObject.solve(new RemoteObject[] {_firstinput,BA.numberCast(double.class, _toml.runMethod(false,"Get",(Object)((_fromunit)))),BA.numberCast(double.class, _toml.runMethod(false,"Get",(Object)((_tounit))))}, "*/",0, 0);Debug.locals.put("ans", _ans);
 BA.debugLineNum = 322;BA.debugLine="result = NumberFormat(ans, 1, 4) & \" \" & toUnit";
Debug.ShouldStop(2);
_result = RemoteObject.concat(main.mostCurrent.__c.runMethod(true,"NumberFormat",(Object)(_ans),(Object)(BA.numberCast(int.class, 1)),(Object)(BA.numberCast(int.class, 4))),RemoteObject.createImmutable(" "),_tounit);Debug.locals.put("result", _result);
 break; }
case 3: {
 BA.debugLineNum = 325;BA.debugLine="Dim toMS As Map";
Debug.ShouldStop(16);
_toms = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");Debug.locals.put("toMS", _toms);
 BA.debugLineNum = 326;BA.debugLine="toMS.Initialize";
Debug.ShouldStop(32);
_toms.runVoidMethod ("Initialize");
 BA.debugLineNum = 327;BA.debugLine="toMS.Put(\"m/s\", 1)";
Debug.ShouldStop(64);
_toms.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("m/s"))),(Object)(RemoteObject.createImmutable((1))));
 BA.debugLineNum = 328;BA.debugLine="toMS.Put(\"km/h\", 0.277778)";
Debug.ShouldStop(128);
_toms.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("km/h"))),(Object)(RemoteObject.createImmutable((0.277778))));
 BA.debugLineNum = 329;BA.debugLine="toMS.Put(\"mph\", 0.44704)";
Debug.ShouldStop(256);
_toms.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("mph"))),(Object)(RemoteObject.createImmutable((0.44704))));
 BA.debugLineNum = 330;BA.debugLine="toMS.Put(\"knot\", 0.514444)";
Debug.ShouldStop(512);
_toms.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("knot"))),(Object)(RemoteObject.createImmutable((0.514444))));
 BA.debugLineNum = 331;BA.debugLine="toMS.Put(\"ft/s\", 0.3048)";
Debug.ShouldStop(1024);
_toms.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("ft/s"))),(Object)(RemoteObject.createImmutable((0.3048))));
 BA.debugLineNum = 332;BA.debugLine="ans = firstinput * toMS.Get(fromUnit) / toMS.Ge";
Debug.ShouldStop(2048);
_ans = RemoteObject.solve(new RemoteObject[] {_firstinput,BA.numberCast(double.class, _toms.runMethod(false,"Get",(Object)((_fromunit)))),BA.numberCast(double.class, _toms.runMethod(false,"Get",(Object)((_tounit))))}, "*/",0, 0);Debug.locals.put("ans", _ans);
 BA.debugLineNum = 333;BA.debugLine="result = NumberFormat(ans, 1, 4) & \" \" & toUnit";
Debug.ShouldStop(4096);
_result = RemoteObject.concat(main.mostCurrent.__c.runMethod(true,"NumberFormat",(Object)(_ans),(Object)(BA.numberCast(int.class, 1)),(Object)(BA.numberCast(int.class, 4))),RemoteObject.createImmutable(" "),_tounit);Debug.locals.put("result", _result);
 break; }
case 4: {
 BA.debugLineNum = 336;BA.debugLine="Dim celsius As Double";
Debug.ShouldStop(32768);
_celsius = RemoteObject.createImmutable(0);Debug.locals.put("celsius", _celsius);
 BA.debugLineNum = 337;BA.debugLine="Select fromUnit";
Debug.ShouldStop(65536);
switch (BA.switchObjectToInt(_fromunit,BA.ObjectToString("°C"),BA.ObjectToString("°F"),BA.ObjectToString("K"),BA.ObjectToString("°R"))) {
case 0: {
 BA.debugLineNum = 338;BA.debugLine="Case \"°C\" : celsius = firstinput";
Debug.ShouldStop(131072);
_celsius = _firstinput;Debug.locals.put("celsius", _celsius);
 break; }
case 1: {
 BA.debugLineNum = 339;BA.debugLine="Case \"°F\" : celsius = (firstinput - 32) / 1.8";
Debug.ShouldStop(262144);
_celsius = RemoteObject.solve(new RemoteObject[] {(RemoteObject.solve(new RemoteObject[] {_firstinput,RemoteObject.createImmutable(32)}, "-",1, 0)),RemoteObject.createImmutable(1.8)}, "/",0, 0);Debug.locals.put("celsius", _celsius);
 break; }
case 2: {
 BA.debugLineNum = 340;BA.debugLine="Case \"K\"  : celsius = firstinput - 273.15";
Debug.ShouldStop(524288);
_celsius = RemoteObject.solve(new RemoteObject[] {_firstinput,RemoteObject.createImmutable(273.15)}, "-",1, 0);Debug.locals.put("celsius", _celsius);
 break; }
case 3: {
 BA.debugLineNum = 341;BA.debugLine="Case \"°R\" : celsius = (firstinput - 491.67) /";
Debug.ShouldStop(1048576);
_celsius = RemoteObject.solve(new RemoteObject[] {(RemoteObject.solve(new RemoteObject[] {_firstinput,RemoteObject.createImmutable(491.67)}, "-",1, 0)),RemoteObject.createImmutable(1.8)}, "/",0, 0);Debug.locals.put("celsius", _celsius);
 break; }
}
;
 BA.debugLineNum = 343;BA.debugLine="Select toUnit";
Debug.ShouldStop(4194304);
switch (BA.switchObjectToInt(_tounit,BA.ObjectToString("°C"),BA.ObjectToString("°F"),BA.ObjectToString("K"),BA.ObjectToString("°R"))) {
case 0: {
 BA.debugLineNum = 344;BA.debugLine="Case \"°C\" : ans = celsius";
Debug.ShouldStop(8388608);
_ans = _celsius;Debug.locals.put("ans", _ans);
 break; }
case 1: {
 BA.debugLineNum = 345;BA.debugLine="Case \"°F\" : ans = (celsius * 1.8) + 32";
Debug.ShouldStop(16777216);
_ans = RemoteObject.solve(new RemoteObject[] {(RemoteObject.solve(new RemoteObject[] {_celsius,RemoteObject.createImmutable(1.8)}, "*",0, 0)),RemoteObject.createImmutable(32)}, "+",1, 0);Debug.locals.put("ans", _ans);
 break; }
case 2: {
 BA.debugLineNum = 346;BA.debugLine="Case \"K\"  : ans = celsius + 273.15";
Debug.ShouldStop(33554432);
_ans = RemoteObject.solve(new RemoteObject[] {_celsius,RemoteObject.createImmutable(273.15)}, "+",1, 0);Debug.locals.put("ans", _ans);
 break; }
case 3: {
 BA.debugLineNum = 347;BA.debugLine="Case \"°R\" : ans = (celsius + 273.15) * 1.8";
Debug.ShouldStop(67108864);
_ans = RemoteObject.solve(new RemoteObject[] {(RemoteObject.solve(new RemoteObject[] {_celsius,RemoteObject.createImmutable(273.15)}, "+",1, 0)),RemoteObject.createImmutable(1.8)}, "*",0, 0);Debug.locals.put("ans", _ans);
 break; }
}
;
 BA.debugLineNum = 349;BA.debugLine="result = NumberFormat(ans, 1, 4) & \" \" & toUnit";
Debug.ShouldStop(268435456);
_result = RemoteObject.concat(main.mostCurrent.__c.runMethod(true,"NumberFormat",(Object)(_ans),(Object)(BA.numberCast(int.class, 1)),(Object)(BA.numberCast(int.class, 4))),RemoteObject.createImmutable(" "),_tounit);Debug.locals.put("result", _result);
 break; }
case 5: {
 BA.debugLineNum = 352;BA.debugLine="Dim toM2 As Map";
Debug.ShouldStop(-2147483648);
_tom2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");Debug.locals.put("toM2", _tom2);
 BA.debugLineNum = 353;BA.debugLine="toM2.Initialize";
Debug.ShouldStop(1);
_tom2.runVoidMethod ("Initialize");
 BA.debugLineNum = 354;BA.debugLine="toM2.Put(\"mm²\", 0.000001)";
Debug.ShouldStop(2);
_tom2.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("mm²"))),(Object)(RemoteObject.createImmutable((0.000001))));
 BA.debugLineNum = 355;BA.debugLine="toM2.Put(\"cm²\", 0.0001)";
Debug.ShouldStop(4);
_tom2.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("cm²"))),(Object)(RemoteObject.createImmutable((0.0001))));
 BA.debugLineNum = 356;BA.debugLine="toM2.Put(\"m²\", 1)";
Debug.ShouldStop(8);
_tom2.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("m²"))),(Object)(RemoteObject.createImmutable((1))));
 BA.debugLineNum = 357;BA.debugLine="toM2.Put(\"km²\", 1000000)";
Debug.ShouldStop(16);
_tom2.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("km²"))),(Object)(RemoteObject.createImmutable((1000000))));
 BA.debugLineNum = 358;BA.debugLine="toM2.Put(\"in²\", 0.00064516)";
Debug.ShouldStop(32);
_tom2.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("in²"))),(Object)(RemoteObject.createImmutable((0.00064516))));
 BA.debugLineNum = 359;BA.debugLine="toM2.Put(\"ft²\", 0.092903)";
Debug.ShouldStop(64);
_tom2.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("ft²"))),(Object)(RemoteObject.createImmutable((0.092903))));
 BA.debugLineNum = 360;BA.debugLine="toM2.Put(\"yd²\", 0.836127)";
Debug.ShouldStop(128);
_tom2.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("yd²"))),(Object)(RemoteObject.createImmutable((0.836127))));
 BA.debugLineNum = 361;BA.debugLine="ans = firstinput * toM2.Get(fromUnit) / toM2.Ge";
Debug.ShouldStop(256);
_ans = RemoteObject.solve(new RemoteObject[] {_firstinput,BA.numberCast(double.class, _tom2.runMethod(false,"Get",(Object)((_fromunit)))),BA.numberCast(double.class, _tom2.runMethod(false,"Get",(Object)((_tounit))))}, "*/",0, 0);Debug.locals.put("ans", _ans);
 BA.debugLineNum = 362;BA.debugLine="result = NumberFormat(ans, 1, 4) & \" \" & toUnit";
Debug.ShouldStop(512);
_result = RemoteObject.concat(main.mostCurrent.__c.runMethod(true,"NumberFormat",(Object)(_ans),(Object)(BA.numberCast(int.class, 1)),(Object)(BA.numberCast(int.class, 4))),RemoteObject.createImmutable(" "),_tounit);Debug.locals.put("result", _result);
 break; }
default: {
 BA.debugLineNum = 365;BA.debugLine="result = \"0\"";
Debug.ShouldStop(4096);
_result = BA.ObjectToString("0");Debug.locals.put("result", _result);
 break; }
}
;
 BA.debugLineNum = 368;BA.debugLine="Label3.Text = result";
Debug.ShouldStop(32768);
main.mostCurrent._label3.runMethod(true,"setText",BA.ObjectToCharSequence(_result));
 BA.debugLineNum = 369;BA.debugLine="End Sub";
Debug.ShouldStop(65536);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _setgradient(RemoteObject _panel,RemoteObject _color1,RemoteObject _color2) throws Exception{
try {
		Debug.PushSubsStack("SetGradient (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,60);
if (RapidSub.canDelegate("setgradient")) { return b4a.example.main.remoteMe.runUserSub(false, "main","setgradient", _panel, _color1, _color2);}
RemoteObject _gd = RemoteObject.declareNull("anywheresoftware.b4a.objects.drawable.GradientDrawable");
Debug.locals.put("Panel", _panel);
Debug.locals.put("Color1", _color1);
Debug.locals.put("Color2", _color2);
 BA.debugLineNum = 60;BA.debugLine="Sub SetGradient(Panel As Panel, Color1 As Int, Col";
Debug.ShouldStop(134217728);
 BA.debugLineNum = 61;BA.debugLine="Dim gd As GradientDrawable";
Debug.ShouldStop(268435456);
_gd = RemoteObject.createNew ("anywheresoftware.b4a.objects.drawable.GradientDrawable");Debug.locals.put("gd", _gd);
 BA.debugLineNum = 62;BA.debugLine="gd.Initialize(\"BR_TL\", Array As Int(Color1, Color";
Debug.ShouldStop(536870912);
_gd.runVoidMethod ("Initialize",(Object)(BA.getEnumFromString(BA.getDeviceClass("android.graphics.drawable.GradientDrawable.Orientation"),"BR_TL")),(Object)(RemoteObject.createNewArray("int",new int[] {2},new Object[] {_color1,_color2})));
 BA.debugLineNum = 63;BA.debugLine="Panel.Background = gd";
Debug.ShouldStop(1073741824);
_panel.runMethod(false,"setBackground",(_gd.getObject()));
 BA.debugLineNum = 64;BA.debugLine="End Sub";
Debug.ShouldStop(-2147483648);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _setspinner(RemoteObject _item,RemoteObject _color) throws Exception{
try {
		Debug.PushSubsStack("setspinner (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,186);
if (RapidSub.canDelegate("setspinner")) { return b4a.example.main.remoteMe.runUserSub(false, "main","setspinner", _item, _color);}
RemoteObject _s = RemoteObject.declareNull("anywheresoftware.b4a.objects.SpinnerWrapper");
RemoteObject _pairitems = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
int _i = 0;
RemoteObject _cd = RemoteObject.declareNull("anywheresoftware.b4a.objects.drawable.ColorDrawable");
Debug.locals.put("item", _item);
Debug.locals.put("color", _color);
 BA.debugLineNum = 186;BA.debugLine="Sub setspinner(item As List, color As Object)";
Debug.ShouldStop(33554432);
 BA.debugLineNum = 187;BA.debugLine="Spinner2.AddAll(item)";
Debug.ShouldStop(67108864);
main.mostCurrent._spinner2.runVoidMethod ("AddAll",(Object)(_item));
 BA.debugLineNum = 188;BA.debugLine="Spinner3.AddAll(item)";
Debug.ShouldStop(134217728);
main.mostCurrent._spinner3.runVoidMethod ("AddAll",(Object)(_item));
 BA.debugLineNum = 189;BA.debugLine="For Each s As Spinner In Array(Spinner1, Spinner2";
Debug.ShouldStop(268435456);
_s = RemoteObject.createNew ("anywheresoftware.b4a.objects.SpinnerWrapper");
{
final RemoteObject group3 = RemoteObject.createNewArray("Object",new int[] {3},new Object[] {(main.mostCurrent._spinner1.getObject()),(main.mostCurrent._spinner2.getObject()),(main.mostCurrent._spinner3.getObject())});
final int groupLen3 = group3.getField(true,"length").<Integer>get()
;int index3 = 0;
;
for (; index3 < groupLen3;index3++){
_s = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.SpinnerWrapper"), group3.getArrayElement(false,RemoteObject.createImmutable(index3)));Debug.locals.put("s", _s);
Debug.locals.put("s", _s);
 BA.debugLineNum = 190;BA.debugLine="s.TextSize = 14";
Debug.ShouldStop(536870912);
_s.runMethod(true,"setTextSize",BA.numberCast(float.class, 14));
 BA.debugLineNum = 191;BA.debugLine="Dim pairItems As List";
Debug.ShouldStop(1073741824);
_pairitems = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");Debug.locals.put("pairItems", _pairitems);
 BA.debugLineNum = 192;BA.debugLine="pairItems.Initialize";
Debug.ShouldStop(-2147483648);
_pairitems.runVoidMethod ("Initialize");
 BA.debugLineNum = 193;BA.debugLine="For i = 0 To item.Size - 2";
Debug.ShouldStop(1);
{
final int step7 = 1;
final int limit7 = RemoteObject.solve(new RemoteObject[] {_item.runMethod(true,"getSize"),RemoteObject.createImmutable(2)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
for (;(step7 > 0 && _i <= limit7) || (step7 < 0 && _i >= limit7) ;_i = ((int)(0 + _i + step7))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 194;BA.debugLine="pairItems.Add(item.Get(i) & \" to \" & item.Get(i";
Debug.ShouldStop(2);
_pairitems.runVoidMethod ("Add",(Object)((RemoteObject.concat(_item.runMethod(false,"Get",(Object)(BA.numberCast(int.class, _i))),RemoteObject.createImmutable(" to "),_item.runMethod(false,"Get",(Object)(RemoteObject.solve(new RemoteObject[] {RemoteObject.createImmutable(_i),RemoteObject.createImmutable(1)}, "+",1, 1)))))));
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 197;BA.debugLine="Dim cd As ColorDrawable";
Debug.ShouldStop(16);
_cd = RemoteObject.createNew ("anywheresoftware.b4a.objects.drawable.ColorDrawable");Debug.locals.put("cd", _cd);
 BA.debugLineNum = 198;BA.debugLine="cd.Initialize(color, 10dip)";
Debug.ShouldStop(32);
_cd.runVoidMethod ("Initialize",(Object)(BA.numberCast(int.class, _color)),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))));
 BA.debugLineNum = 199;BA.debugLine="s.Background = cd";
Debug.ShouldStop(64);
_s.runMethod(false,"setBackground",(_cd.getObject()));
 }
}Debug.locals.put("s", _s);
;
 BA.debugLineNum = 201;BA.debugLine="Spinner1.AddAll(pairItems)";
Debug.ShouldStop(256);
main.mostCurrent._spinner1.runVoidMethod ("AddAll",(Object)(_pairitems));
 BA.debugLineNum = 202;BA.debugLine="End Sub";
Debug.ShouldStop(512);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _showhome() throws Exception{
try {
		Debug.PushSubsStack("ShowHome (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,180);
if (RapidSub.canDelegate("showhome")) { return b4a.example.main.remoteMe.runUserSub(false, "main","showhome");}
 BA.debugLineNum = 180;BA.debugLine="Sub ShowHome";
Debug.ShouldStop(524288);
 BA.debugLineNum = 181;BA.debugLine="pnlMain.RemoveAllViews";
Debug.ShouldStop(1048576);
main.mostCurrent._pnlmain.runVoidMethod ("RemoveAllViews");
 BA.debugLineNum = 182;BA.debugLine="pnlMain.LoadLayout(\"home\")";
Debug.ShouldStop(2097152);
main.mostCurrent._pnlmain.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("home")),main.mostCurrent.activityBA);
 BA.debugLineNum = 183;BA.debugLine="lblTitle.Text = \"Home\"";
Debug.ShouldStop(4194304);
main.mostCurrent._lbltitle.runMethod(true,"setText",BA.ObjectToCharSequence("Home"));
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
public static RemoteObject  _showpage1() throws Exception{
try {
		Debug.PushSubsStack("ShowPage1 (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,204);
if (RapidSub.canDelegate("showpage1")) { return b4a.example.main.remoteMe.runUserSub(false, "main","showpage1");}
 BA.debugLineNum = 204;BA.debugLine="Sub ShowPage1";
Debug.ShouldStop(2048);
 BA.debugLineNum = 205;BA.debugLine="pnlMain.RemoveAllViews";
Debug.ShouldStop(4096);
main.mostCurrent._pnlmain.runVoidMethod ("RemoveAllViews");
 BA.debugLineNum = 206;BA.debugLine="pnlMain.LoadLayout(\"page1\")";
Debug.ShouldStop(8192);
main.mostCurrent._pnlmain.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("page1")),main.mostCurrent.activityBA);
 BA.debugLineNum = 207;BA.debugLine="lblTitle.Text = \"Length Converter\"";
Debug.ShouldStop(16384);
main.mostCurrent._lbltitle.runMethod(true,"setText",BA.ObjectToCharSequence("Length Converter"));
 BA.debugLineNum = 209;BA.debugLine="items.Clear";
Debug.ShouldStop(65536);
main.mostCurrent._items.runVoidMethod ("Clear");
 BA.debugLineNum = 210;BA.debugLine="items.AddAll(Array As String(\"mm\",\"cm\",\"m\",\"km\",\"";
Debug.ShouldStop(131072);
main.mostCurrent._items.runVoidMethod ("AddAll",(Object)(main.mostCurrent.__c.runMethod(false, "ArrayToList", (Object)(RemoteObject.createNewArray("String",new int[] {9},new Object[] {BA.ObjectToString("mm"),BA.ObjectToString("cm"),BA.ObjectToString("m"),BA.ObjectToString("km"),BA.ObjectToString("in"),BA.ObjectToString("ft"),BA.ObjectToString("yd"),BA.ObjectToString("mi"),RemoteObject.createImmutable("µm")})))));
 BA.debugLineNum = 211;BA.debugLine="setspinner(items, Colors.ARGB(64, 255, 255, 255))";
Debug.ShouldStop(262144);
_setspinner(main.mostCurrent._items,(main.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 64)),(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 255)))));
 BA.debugLineNum = 212;BA.debugLine="End Sub";
Debug.ShouldStop(524288);
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
		Debug.PushSubsStack("ShowPage2 (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,214);
if (RapidSub.canDelegate("showpage2")) { return b4a.example.main.remoteMe.runUserSub(false, "main","showpage2");}
 BA.debugLineNum = 214;BA.debugLine="Sub ShowPage2";
Debug.ShouldStop(2097152);
 BA.debugLineNum = 215;BA.debugLine="pnlMain.RemoveAllViews";
Debug.ShouldStop(4194304);
main.mostCurrent._pnlmain.runVoidMethod ("RemoveAllViews");
 BA.debugLineNum = 216;BA.debugLine="pnlMain.LoadLayout(\"page2\")";
Debug.ShouldStop(8388608);
main.mostCurrent._pnlmain.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("page2")),main.mostCurrent.activityBA);
 BA.debugLineNum = 217;BA.debugLine="lblTitle.Text = \"Wight Converter\"";
Debug.ShouldStop(16777216);
main.mostCurrent._lbltitle.runMethod(true,"setText",BA.ObjectToCharSequence("Wight Converter"));
 BA.debugLineNum = 219;BA.debugLine="items.Clear";
Debug.ShouldStop(67108864);
main.mostCurrent._items.runVoidMethod ("Clear");
 BA.debugLineNum = 220;BA.debugLine="items.AddAll(Array As String(\"mg\",\"g\",\"kg\",\"t\",\"o";
Debug.ShouldStop(134217728);
main.mostCurrent._items.runVoidMethod ("AddAll",(Object)(main.mostCurrent.__c.runMethod(false, "ArrayToList", (Object)(RemoteObject.createNewArray("String",new int[] {10},new Object[] {BA.ObjectToString("mg"),BA.ObjectToString("g"),BA.ObjectToString("kg"),BA.ObjectToString("t"),BA.ObjectToString("oz"),BA.ObjectToString("lb"),BA.ObjectToString("st"),BA.ObjectToString("ct"),BA.ObjectToString("µg"),RemoteObject.createImmutable("ton")})))));
 BA.debugLineNum = 221;BA.debugLine="setspinner(items, Colors.ARGB(64, 255, 255, 255))";
Debug.ShouldStop(268435456);
_setspinner(main.mostCurrent._items,(main.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 64)),(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 255)))));
 BA.debugLineNum = 222;BA.debugLine="End Sub";
Debug.ShouldStop(536870912);
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
		Debug.PushSubsStack("ShowPage3 (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,224);
if (RapidSub.canDelegate("showpage3")) { return b4a.example.main.remoteMe.runUserSub(false, "main","showpage3");}
 BA.debugLineNum = 224;BA.debugLine="Sub ShowPage3";
Debug.ShouldStop(-2147483648);
 BA.debugLineNum = 225;BA.debugLine="pnlMain.RemoveAllViews";
Debug.ShouldStop(1);
main.mostCurrent._pnlmain.runVoidMethod ("RemoveAllViews");
 BA.debugLineNum = 226;BA.debugLine="pnlMain.LoadLayout(\"page3\")";
Debug.ShouldStop(2);
main.mostCurrent._pnlmain.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("page3")),main.mostCurrent.activityBA);
 BA.debugLineNum = 227;BA.debugLine="lblTitle.Text = \"Volume Converter\"";
Debug.ShouldStop(4);
main.mostCurrent._lbltitle.runMethod(true,"setText",BA.ObjectToCharSequence("Volume Converter"));
 BA.debugLineNum = 229;BA.debugLine="items.Clear";
Debug.ShouldStop(16);
main.mostCurrent._items.runVoidMethod ("Clear");
 BA.debugLineNum = 230;BA.debugLine="items.AddAll(Array As String(\"mL\",\"L\",\"kL\",\"cm³\",";
Debug.ShouldStop(32);
main.mostCurrent._items.runVoidMethod ("AddAll",(Object)(main.mostCurrent.__c.runMethod(false, "ArrayToList", (Object)(RemoteObject.createNewArray("String",new int[] {7},new Object[] {BA.ObjectToString("mL"),BA.ObjectToString("L"),BA.ObjectToString("kL"),BA.ObjectToString("cm³"),BA.ObjectToString("m³"),BA.ObjectToString("in³"),RemoteObject.createImmutable("ft³")})))));
 BA.debugLineNum = 231;BA.debugLine="setspinner(items, Colors.ARGB(64, 255, 255, 255))";
Debug.ShouldStop(64);
_setspinner(main.mostCurrent._items,(main.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 64)),(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 255)))));
 BA.debugLineNum = 232;BA.debugLine="End Sub";
Debug.ShouldStop(128);
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
		Debug.PushSubsStack("ShowPage4 (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,234);
if (RapidSub.canDelegate("showpage4")) { return b4a.example.main.remoteMe.runUserSub(false, "main","showpage4");}
 BA.debugLineNum = 234;BA.debugLine="Sub ShowPage4";
Debug.ShouldStop(512);
 BA.debugLineNum = 235;BA.debugLine="pnlMain.RemoveAllViews";
Debug.ShouldStop(1024);
main.mostCurrent._pnlmain.runVoidMethod ("RemoveAllViews");
 BA.debugLineNum = 236;BA.debugLine="pnlMain.LoadLayout(\"page4\")";
Debug.ShouldStop(2048);
main.mostCurrent._pnlmain.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("page4")),main.mostCurrent.activityBA);
 BA.debugLineNum = 237;BA.debugLine="lblTitle.Text = \"Speed Converter\"";
Debug.ShouldStop(4096);
main.mostCurrent._lbltitle.runMethod(true,"setText",BA.ObjectToCharSequence("Speed Converter"));
 BA.debugLineNum = 239;BA.debugLine="items.Clear";
Debug.ShouldStop(16384);
main.mostCurrent._items.runVoidMethod ("Clear");
 BA.debugLineNum = 240;BA.debugLine="items.AddAll(Array As String(\"m/s\",\"km/h\",\"mph\",\"";
Debug.ShouldStop(32768);
main.mostCurrent._items.runVoidMethod ("AddAll",(Object)(main.mostCurrent.__c.runMethod(false, "ArrayToList", (Object)(RemoteObject.createNewArray("String",new int[] {5},new Object[] {BA.ObjectToString("m/s"),BA.ObjectToString("km/h"),BA.ObjectToString("mph"),BA.ObjectToString("knot"),RemoteObject.createImmutable("ft/s")})))));
 BA.debugLineNum = 241;BA.debugLine="setspinner(items, Colors.ARGB(64, 255, 255, 255))";
Debug.ShouldStop(65536);
_setspinner(main.mostCurrent._items,(main.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 64)),(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 255)))));
 BA.debugLineNum = 242;BA.debugLine="End Sub";
Debug.ShouldStop(131072);
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
		Debug.PushSubsStack("ShowPage5 (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,244);
if (RapidSub.canDelegate("showpage5")) { return b4a.example.main.remoteMe.runUserSub(false, "main","showpage5");}
 BA.debugLineNum = 244;BA.debugLine="Sub ShowPage5";
Debug.ShouldStop(524288);
 BA.debugLineNum = 245;BA.debugLine="pnlMain.RemoveAllViews";
Debug.ShouldStop(1048576);
main.mostCurrent._pnlmain.runVoidMethod ("RemoveAllViews");
 BA.debugLineNum = 246;BA.debugLine="pnlMain.LoadLayout(\"page5\")";
Debug.ShouldStop(2097152);
main.mostCurrent._pnlmain.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("page5")),main.mostCurrent.activityBA);
 BA.debugLineNum = 247;BA.debugLine="lblTitle.Text = \"Temperature Converter\"";
Debug.ShouldStop(4194304);
main.mostCurrent._lbltitle.runMethod(true,"setText",BA.ObjectToCharSequence("Temperature Converter"));
 BA.debugLineNum = 249;BA.debugLine="items.Clear";
Debug.ShouldStop(16777216);
main.mostCurrent._items.runVoidMethod ("Clear");
 BA.debugLineNum = 250;BA.debugLine="items.AddAll(Array As String(\"°C\",\"°F\",\"K\",\"°R\"))";
Debug.ShouldStop(33554432);
main.mostCurrent._items.runVoidMethod ("AddAll",(Object)(main.mostCurrent.__c.runMethod(false, "ArrayToList", (Object)(RemoteObject.createNewArray("String",new int[] {4},new Object[] {BA.ObjectToString("°C"),BA.ObjectToString("°F"),BA.ObjectToString("K"),RemoteObject.createImmutable("°R")})))));
 BA.debugLineNum = 251;BA.debugLine="setspinner(items, Colors.ARGB(64, 255, 255, 255))";
Debug.ShouldStop(67108864);
_setspinner(main.mostCurrent._items,(main.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 64)),(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 255)))));
 BA.debugLineNum = 252;BA.debugLine="End Sub";
Debug.ShouldStop(134217728);
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
		Debug.PushSubsStack("ShowPage6 (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,254);
if (RapidSub.canDelegate("showpage6")) { return b4a.example.main.remoteMe.runUserSub(false, "main","showpage6");}
 BA.debugLineNum = 254;BA.debugLine="Sub ShowPage6";
Debug.ShouldStop(536870912);
 BA.debugLineNum = 255;BA.debugLine="pnlMain.RemoveAllViews";
Debug.ShouldStop(1073741824);
main.mostCurrent._pnlmain.runVoidMethod ("RemoveAllViews");
 BA.debugLineNum = 256;BA.debugLine="pnlMain.LoadLayout(\"page6\")";
Debug.ShouldStop(-2147483648);
main.mostCurrent._pnlmain.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("page6")),main.mostCurrent.activityBA);
 BA.debugLineNum = 257;BA.debugLine="lblTitle.Text = \"Area Converter\"";
Debug.ShouldStop(1);
main.mostCurrent._lbltitle.runMethod(true,"setText",BA.ObjectToCharSequence("Area Converter"));
 BA.debugLineNum = 259;BA.debugLine="items.Clear";
Debug.ShouldStop(4);
main.mostCurrent._items.runVoidMethod ("Clear");
 BA.debugLineNum = 260;BA.debugLine="items.AddAll(Array As String(\"mm²\",\"cm²\",\"m²\",\"km";
Debug.ShouldStop(8);
main.mostCurrent._items.runVoidMethod ("AddAll",(Object)(main.mostCurrent.__c.runMethod(false, "ArrayToList", (Object)(RemoteObject.createNewArray("String",new int[] {7},new Object[] {BA.ObjectToString("mm²"),BA.ObjectToString("cm²"),BA.ObjectToString("m²"),BA.ObjectToString("km²"),BA.ObjectToString("in²"),BA.ObjectToString("ft²"),RemoteObject.createImmutable("yd²")})))));
 BA.debugLineNum = 261;BA.debugLine="setspinner(items, Colors.ARGB(64, 255, 255, 255))";
Debug.ShouldStop(16);
_setspinner(main.mostCurrent._items,(main.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 64)),(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 255)))));
 BA.debugLineNum = 262;BA.debugLine="End Sub";
Debug.ShouldStop(32);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _spinner1_itemclick(RemoteObject _position,RemoteObject _value) throws Exception{
try {
		Debug.PushSubsStack("Spinner1_ItemClick (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,477);
if (RapidSub.canDelegate("spinner1_itemclick")) { return b4a.example.main.remoteMe.runUserSub(false, "main","spinner1_itemclick", _position, _value);}
Debug.locals.put("Position", _position);
Debug.locals.put("Value", _value);
 BA.debugLineNum = 477;BA.debugLine="Private Sub Spinner1_ItemClick (Position As Int, V";
Debug.ShouldStop(268435456);
 BA.debugLineNum = 478;BA.debugLine="qick";
Debug.ShouldStop(536870912);
_qick();
 BA.debugLineNum = 479;BA.debugLine="End Sub";
Debug.ShouldStop(1073741824);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _spinner2_itemclick(RemoteObject _position,RemoteObject _value) throws Exception{
try {
		Debug.PushSubsStack("Spinner2_ItemClick (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,473);
if (RapidSub.canDelegate("spinner2_itemclick")) { return b4a.example.main.remoteMe.runUserSub(false, "main","spinner2_itemclick", _position, _value);}
Debug.locals.put("Position", _position);
Debug.locals.put("Value", _value);
 BA.debugLineNum = 473;BA.debugLine="Private Sub Spinner2_ItemClick (Position As Int, V";
Debug.ShouldStop(16777216);
 BA.debugLineNum = 474;BA.debugLine="convert";
Debug.ShouldStop(33554432);
_convert();
 BA.debugLineNum = 475;BA.debugLine="End Sub";
Debug.ShouldStop(67108864);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _spinner3_itemclick(RemoteObject _position,RemoteObject _value) throws Exception{
try {
		Debug.PushSubsStack("Spinner3_ItemClick (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,469);
if (RapidSub.canDelegate("spinner3_itemclick")) { return b4a.example.main.remoteMe.runUserSub(false, "main","spinner3_itemclick", _position, _value);}
Debug.locals.put("Position", _position);
Debug.locals.put("Value", _value);
 BA.debugLineNum = 469;BA.debugLine="Private Sub Spinner3_ItemClick (Position As Int, V";
Debug.ShouldStop(1048576);
 BA.debugLineNum = 470;BA.debugLine="convert";
Debug.ShouldStop(2097152);
_convert();
 BA.debugLineNum = 471;BA.debugLine="End Sub";
Debug.ShouldStop(4194304);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}