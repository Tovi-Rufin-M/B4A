package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class main_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,30);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_create", _firsttime);}
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 30;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(536870912);
 BA.debugLineNum = 31;BA.debugLine="Activity.LoadLayout(\"Layout\")";
Debug.ShouldStop(1073741824);
main.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("Layout")),main.mostCurrent.activityBA);
 BA.debugLineNum = 32;BA.debugLine="Spinner1.AddAll(Array As String(\"Kilometer\",\"Cent";
Debug.ShouldStop(-2147483648);
main.mostCurrent._spinner1.runVoidMethod ("AddAll",(Object)(main.mostCurrent.__c.runMethod(false, "ArrayToList", (Object)(RemoteObject.createNewArray("String",new int[] {6},new Object[] {BA.ObjectToString("Kilometer"),BA.ObjectToString("Centimeter"),BA.ObjectToString("Millimeter"),BA.ObjectToString("Inch"),BA.ObjectToString("Foot"),RemoteObject.createImmutable("Yard")})))));
 BA.debugLineNum = 33;BA.debugLine="End Sub";
Debug.ShouldStop(1);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _activity_pause(RemoteObject _userclosed) throws Exception{
try {
		Debug.PushSubsStack("Activity_Pause (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,39);
if (RapidSub.canDelegate("activity_pause")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_pause", _userclosed);}
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 39;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(64);
 BA.debugLineNum = 41;BA.debugLine="End Sub";
Debug.ShouldStop(256);
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
		Debug.PushSubsStack("Activity_Resume (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,35);
if (RapidSub.canDelegate("activity_resume")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_resume");}
 BA.debugLineNum = 35;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(4);
 BA.debugLineNum = 37;BA.debugLine="End Sub";
Debug.ShouldStop(16);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _button1_click() throws Exception{
try {
		Debug.PushSubsStack("Button1_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,82);
if (RapidSub.canDelegate("button1_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","button1_click");}
 BA.debugLineNum = 82;BA.debugLine="Sub Button1_Click";
Debug.ShouldStop(131072);
 BA.debugLineNum = 83;BA.debugLine="calculate";
Debug.ShouldStop(262144);
_calculate();
 BA.debugLineNum = 84;BA.debugLine="End Sub";
Debug.ShouldStop(524288);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _calculate() throws Exception{
try {
		Debug.PushSubsStack("calculate (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,43);
if (RapidSub.canDelegate("calculate")) { return b4a.example.main.remoteMe.runUserSub(false, "main","calculate");}
RemoteObject _selected = RemoteObject.createImmutable("");
RemoteObject _sign = RemoteObject.createImmutable("");
RemoteObject _ans = RemoteObject.createImmutable(0);
RemoteObject _input = RemoteObject.createImmutable(0);
 BA.debugLineNum = 43;BA.debugLine="Private Sub calculate";
Debug.ShouldStop(1024);
 BA.debugLineNum = 44;BA.debugLine="Dim selected As String";
Debug.ShouldStop(2048);
_selected = RemoteObject.createImmutable("");Debug.locals.put("selected", _selected);
 BA.debugLineNum = 45;BA.debugLine="Dim sign As String";
Debug.ShouldStop(4096);
_sign = RemoteObject.createImmutable("");Debug.locals.put("sign", _sign);
 BA.debugLineNum = 46;BA.debugLine="Dim ans As Double";
Debug.ShouldStop(8192);
_ans = RemoteObject.createImmutable(0);Debug.locals.put("ans", _ans);
 BA.debugLineNum = 47;BA.debugLine="Dim input As Double";
Debug.ShouldStop(16384);
_input = RemoteObject.createImmutable(0);Debug.locals.put("input", _input);
 BA.debugLineNum = 48;BA.debugLine="selected = Spinner1.SelectedItem";
Debug.ShouldStop(32768);
_selected = main.mostCurrent._spinner1.runMethod(true,"getSelectedItem");Debug.locals.put("selected", _selected);
 BA.debugLineNum = 49;BA.debugLine="input = EditText1.Text";
Debug.ShouldStop(65536);
_input = BA.numberCast(double.class, main.mostCurrent._edittext1.runMethod(true,"getText"));Debug.locals.put("input", _input);
 BA.debugLineNum = 51;BA.debugLine="Select Case selected";
Debug.ShouldStop(262144);
switch (BA.switchObjectToInt(_selected,BA.ObjectToString("Meter"),BA.ObjectToString("Kilometer"),BA.ObjectToString("Centimeter"),BA.ObjectToString("Millimeter"),BA.ObjectToString("Inch"),BA.ObjectToString("Foot"),BA.ObjectToString("Yard"))) {
case 0: {
 BA.debugLineNum = 53;BA.debugLine="ans = input * 1";
Debug.ShouldStop(1048576);
_ans = RemoteObject.solve(new RemoteObject[] {_input,RemoteObject.createImmutable(1)}, "*",0, 0);Debug.locals.put("ans", _ans);
 BA.debugLineNum = 54;BA.debugLine="sign = \"m\"";
Debug.ShouldStop(2097152);
_sign = BA.ObjectToString("m");Debug.locals.put("sign", _sign);
 break; }
case 1: {
 BA.debugLineNum = 56;BA.debugLine="ans = input / 1000";
Debug.ShouldStop(8388608);
_ans = RemoteObject.solve(new RemoteObject[] {_input,RemoteObject.createImmutable(1000)}, "/",0, 0);Debug.locals.put("ans", _ans);
 BA.debugLineNum = 57;BA.debugLine="sign = \"km\"";
Debug.ShouldStop(16777216);
_sign = BA.ObjectToString("km");Debug.locals.put("sign", _sign);
 break; }
case 2: {
 BA.debugLineNum = 59;BA.debugLine="ans = input * 1000";
Debug.ShouldStop(67108864);
_ans = RemoteObject.solve(new RemoteObject[] {_input,RemoteObject.createImmutable(1000)}, "*",0, 0);Debug.locals.put("ans", _ans);
 BA.debugLineNum = 60;BA.debugLine="sign = \"cm\"";
Debug.ShouldStop(134217728);
_sign = BA.ObjectToString("cm");Debug.locals.put("sign", _sign);
 break; }
case 3: {
 BA.debugLineNum = 62;BA.debugLine="ans = input * 1000";
Debug.ShouldStop(536870912);
_ans = RemoteObject.solve(new RemoteObject[] {_input,RemoteObject.createImmutable(1000)}, "*",0, 0);Debug.locals.put("ans", _ans);
 BA.debugLineNum = 63;BA.debugLine="sign = \"mm\"";
Debug.ShouldStop(1073741824);
_sign = BA.ObjectToString("mm");Debug.locals.put("sign", _sign);
 break; }
case 4: {
 BA.debugLineNum = 65;BA.debugLine="ans = input * 39.3701";
Debug.ShouldStop(1);
_ans = RemoteObject.solve(new RemoteObject[] {_input,RemoteObject.createImmutable(39.3701)}, "*",0, 0);Debug.locals.put("ans", _ans);
 BA.debugLineNum = 66;BA.debugLine="sign = \"in\"";
Debug.ShouldStop(2);
_sign = BA.ObjectToString("in");Debug.locals.put("sign", _sign);
 break; }
case 5: {
 BA.debugLineNum = 68;BA.debugLine="ans = input * 3.28084";
Debug.ShouldStop(8);
_ans = RemoteObject.solve(new RemoteObject[] {_input,RemoteObject.createImmutable(3.28084)}, "*",0, 0);Debug.locals.put("ans", _ans);
 BA.debugLineNum = 69;BA.debugLine="sign = \"ft\"";
Debug.ShouldStop(16);
_sign = BA.ObjectToString("ft");Debug.locals.put("sign", _sign);
 break; }
case 6: {
 BA.debugLineNum = 71;BA.debugLine="ans = input * 1.09361";
Debug.ShouldStop(64);
_ans = RemoteObject.solve(new RemoteObject[] {_input,RemoteObject.createImmutable(1.09361)}, "*",0, 0);Debug.locals.put("ans", _ans);
 BA.debugLineNum = 72;BA.debugLine="sign = \"yd\"";
Debug.ShouldStop(128);
_sign = BA.ObjectToString("yd");Debug.locals.put("sign", _sign);
 break; }
default: {
 BA.debugLineNum = 74;BA.debugLine="ans = 0";
Debug.ShouldStop(512);
_ans = BA.numberCast(double.class, 0);Debug.locals.put("ans", _ans);
 BA.debugLineNum = 75;BA.debugLine="sign = \"\"";
Debug.ShouldStop(1024);
_sign = BA.ObjectToString("");Debug.locals.put("sign", _sign);
 break; }
}
;
 BA.debugLineNum = 78;BA.debugLine="Label3.Text = ans";
Debug.ShouldStop(8192);
main.mostCurrent._label3.runMethod(true,"setText",BA.ObjectToCharSequence(_ans));
 BA.debugLineNum = 79;BA.debugLine="Label4.Text = sign";
Debug.ShouldStop(16384);
main.mostCurrent._label4.runMethod(true,"setText",BA.ObjectToCharSequence(_sign));
 BA.debugLineNum = 80;BA.debugLine="End Sub";
Debug.ShouldStop(32768);
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
		Debug.PushSubsStack("EditText1_EnterPressed (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,90);
if (RapidSub.canDelegate("edittext1_enterpressed")) { return b4a.example.main.remoteMe.runUserSub(false, "main","edittext1_enterpressed");}
 BA.debugLineNum = 90;BA.debugLine="Private Sub EditText1_EnterPressed";
Debug.ShouldStop(33554432);
 BA.debugLineNum = 91;BA.debugLine="calculate";
Debug.ShouldStop(67108864);
_calculate();
 BA.debugLineNum = 92;BA.debugLine="End Sub";
Debug.ShouldStop(134217728);
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
 //BA.debugLineNum = 23;BA.debugLine="Private Button1 As Button";
main.mostCurrent._button1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 24;BA.debugLine="Private EditText1 As EditText";
main.mostCurrent._edittext1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 25;BA.debugLine="Private Label3 As Label";
main.mostCurrent._label3 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 26;BA.debugLine="Private Label4 As Label";
main.mostCurrent._label4 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 27;BA.debugLine="Private Spinner1 As Spinner";
main.mostCurrent._spinner1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.SpinnerWrapper");
 //BA.debugLineNum = 28;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}

public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        main_subs_0._process_globals();
starter_subs_0._process_globals();
main.myClass = BA.getDeviceClass ("b4a.example.main");
starter.myClass = BA.getDeviceClass ("b4a.example.starter");
		
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
public static RemoteObject  _spinner1_itemclick(RemoteObject _position,RemoteObject _value) throws Exception{
try {
		Debug.PushSubsStack("Spinner1_ItemClick (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,86);
if (RapidSub.canDelegate("spinner1_itemclick")) { return b4a.example.main.remoteMe.runUserSub(false, "main","spinner1_itemclick", _position, _value);}
Debug.locals.put("Position", _position);
Debug.locals.put("Value", _value);
 BA.debugLineNum = 86;BA.debugLine="Private Sub Spinner1_ItemClick (Position As Int, V";
Debug.ShouldStop(2097152);
 BA.debugLineNum = 88;BA.debugLine="End Sub";
Debug.ShouldStop(8388608);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}