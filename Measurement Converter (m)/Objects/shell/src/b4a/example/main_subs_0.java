package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class main_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,33);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_create", _firsttime);}
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 33;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(1);
 BA.debugLineNum = 34;BA.debugLine="Activity.LoadLayout(\"Layout\")";
Debug.ShouldStop(2);
main.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("Layout")),main.mostCurrent.activityBA);
 BA.debugLineNum = 35;BA.debugLine="Selects.AddAll(Array As String(\"Kilometer\",\"Centi";
Debug.ShouldStop(4);
main.mostCurrent._selects.runVoidMethod ("AddAll",(Object)(main.mostCurrent.__c.runMethod(false, "ArrayToList", (Object)(RemoteObject.createNewArray("String",new int[] {6},new Object[] {BA.ObjectToString("Kilometer"),BA.ObjectToString("Centimeter"),BA.ObjectToString("Millimeter"),BA.ObjectToString("Inch"),BA.ObjectToString("Foot"),RemoteObject.createImmutable("Yard")})))));
 BA.debugLineNum = 36;BA.debugLine="End Sub";
Debug.ShouldStop(8);
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
		Debug.PushSubsStack("Activity_Pause (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,42);
if (RapidSub.canDelegate("activity_pause")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_pause", _userclosed);}
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 42;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(512);
 BA.debugLineNum = 44;BA.debugLine="End Sub";
Debug.ShouldStop(2048);
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
		Debug.PushSubsStack("Activity_Resume (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,38);
if (RapidSub.canDelegate("activity_resume")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_resume");}
 BA.debugLineNum = 38;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(32);
 BA.debugLineNum = 40;BA.debugLine="End Sub";
Debug.ShouldStop(128);
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
		Debug.PushSubsStack("Button1_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,110);
if (RapidSub.canDelegate("button1_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","button1_click");}
 BA.debugLineNum = 110;BA.debugLine="Private Sub Button1_Click";
Debug.ShouldStop(8192);
 BA.debugLineNum = 111;BA.debugLine="calculate";
Debug.ShouldStop(16384);
_calculate();
 BA.debugLineNum = 112;BA.debugLine="End Sub";
Debug.ShouldStop(32768);
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
		Debug.PushSubsStack("calculate (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,46);
if (RapidSub.canDelegate("calculate")) { return b4a.example.main.remoteMe.runUserSub(false, "main","calculate");}
RemoteObject _selected = RemoteObject.createImmutable("");
RemoteObject _sign = RemoteObject.createImmutable("");
RemoteObject _ans = RemoteObject.createImmutable(0);
RemoteObject _input = RemoteObject.createImmutable(0);
 BA.debugLineNum = 46;BA.debugLine="Private Sub calculate";
Debug.ShouldStop(8192);
 BA.debugLineNum = 47;BA.debugLine="Dim selected As String";
Debug.ShouldStop(16384);
_selected = RemoteObject.createImmutable("");Debug.locals.put("selected", _selected);
 BA.debugLineNum = 48;BA.debugLine="Dim sign As String";
Debug.ShouldStop(32768);
_sign = RemoteObject.createImmutable("");Debug.locals.put("sign", _sign);
 BA.debugLineNum = 49;BA.debugLine="Dim ans As Double";
Debug.ShouldStop(65536);
_ans = RemoteObject.createImmutable(0);Debug.locals.put("ans", _ans);
 BA.debugLineNum = 50;BA.debugLine="Dim input As Double";
Debug.ShouldStop(131072);
_input = RemoteObject.createImmutable(0);Debug.locals.put("input", _input);
 BA.debugLineNum = 51;BA.debugLine="selected = Selects.SelectedItem";
Debug.ShouldStop(262144);
_selected = main.mostCurrent._selects.runMethod(true,"getSelectedItem");Debug.locals.put("selected", _selected);
 BA.debugLineNum = 52;BA.debugLine="input = EditText1.Text";
Debug.ShouldStop(524288);
_input = BA.numberCast(double.class, main.mostCurrent._edittext1.runMethod(true,"getText"));Debug.locals.put("input", _input);
 BA.debugLineNum = 54;BA.debugLine="Select Case selected";
Debug.ShouldStop(2097152);
switch (BA.switchObjectToInt(_selected,BA.ObjectToString("Meter"),BA.ObjectToString("Kilometer"),BA.ObjectToString("Centimeter"),BA.ObjectToString("Millimeter"),BA.ObjectToString("Inch"),BA.ObjectToString("Foot"),BA.ObjectToString("Yard"))) {
case 0: {
 BA.debugLineNum = 56;BA.debugLine="ans = input * 1";
Debug.ShouldStop(8388608);
_ans = RemoteObject.solve(new RemoteObject[] {_input,RemoteObject.createImmutable(1)}, "*",0, 0);Debug.locals.put("ans", _ans);
 BA.debugLineNum = 57;BA.debugLine="Label6.Text = input & \" m\"";
Debug.ShouldStop(16777216);
main.mostCurrent._label6.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(_input,RemoteObject.createImmutable(" m"))));
 BA.debugLineNum = 58;BA.debugLine="Label7.Text = \"*\"";
Debug.ShouldStop(33554432);
main.mostCurrent._label7.runMethod(true,"setText",BA.ObjectToCharSequence("*"));
 BA.debugLineNum = 59;BA.debugLine="Label8.Text = 1";
Debug.ShouldStop(67108864);
main.mostCurrent._label8.runMethod(true,"setText",BA.ObjectToCharSequence(1));
 BA.debugLineNum = 60;BA.debugLine="sign = \"m\"";
Debug.ShouldStop(134217728);
_sign = BA.ObjectToString("m");Debug.locals.put("sign", _sign);
 break; }
case 1: {
 BA.debugLineNum = 62;BA.debugLine="ans = input / 1000";
Debug.ShouldStop(536870912);
_ans = RemoteObject.solve(new RemoteObject[] {_input,RemoteObject.createImmutable(1000)}, "/",0, 0);Debug.locals.put("ans", _ans);
 BA.debugLineNum = 63;BA.debugLine="Label6.Text = input & \" m\"";
Debug.ShouldStop(1073741824);
main.mostCurrent._label6.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(_input,RemoteObject.createImmutable(" m"))));
 BA.debugLineNum = 64;BA.debugLine="Label7.Text = \"/\"";
Debug.ShouldStop(-2147483648);
main.mostCurrent._label7.runMethod(true,"setText",BA.ObjectToCharSequence("/"));
 BA.debugLineNum = 65;BA.debugLine="Label8.Text = 1000";
Debug.ShouldStop(1);
main.mostCurrent._label8.runMethod(true,"setText",BA.ObjectToCharSequence(1000));
 BA.debugLineNum = 66;BA.debugLine="sign = \"km\"";
Debug.ShouldStop(2);
_sign = BA.ObjectToString("km");Debug.locals.put("sign", _sign);
 break; }
case 2: {
 BA.debugLineNum = 68;BA.debugLine="ans = input * 100";
Debug.ShouldStop(8);
_ans = RemoteObject.solve(new RemoteObject[] {_input,RemoteObject.createImmutable(100)}, "*",0, 0);Debug.locals.put("ans", _ans);
 BA.debugLineNum = 69;BA.debugLine="Label6.Text = input & \" m\"";
Debug.ShouldStop(16);
main.mostCurrent._label6.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(_input,RemoteObject.createImmutable(" m"))));
 BA.debugLineNum = 70;BA.debugLine="Label7.Text = \"*\"";
Debug.ShouldStop(32);
main.mostCurrent._label7.runMethod(true,"setText",BA.ObjectToCharSequence("*"));
 BA.debugLineNum = 71;BA.debugLine="Label8.Text = 100";
Debug.ShouldStop(64);
main.mostCurrent._label8.runMethod(true,"setText",BA.ObjectToCharSequence(100));
 BA.debugLineNum = 72;BA.debugLine="sign = \"cm\"";
Debug.ShouldStop(128);
_sign = BA.ObjectToString("cm");Debug.locals.put("sign", _sign);
 break; }
case 3: {
 BA.debugLineNum = 74;BA.debugLine="ans = input * 1000";
Debug.ShouldStop(512);
_ans = RemoteObject.solve(new RemoteObject[] {_input,RemoteObject.createImmutable(1000)}, "*",0, 0);Debug.locals.put("ans", _ans);
 BA.debugLineNum = 75;BA.debugLine="Label6.Text = input & \" m\"";
Debug.ShouldStop(1024);
main.mostCurrent._label6.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(_input,RemoteObject.createImmutable(" m"))));
 BA.debugLineNum = 76;BA.debugLine="Label7.Text = \"*\"";
Debug.ShouldStop(2048);
main.mostCurrent._label7.runMethod(true,"setText",BA.ObjectToCharSequence("*"));
 BA.debugLineNum = 77;BA.debugLine="Label8.Text = 1000";
Debug.ShouldStop(4096);
main.mostCurrent._label8.runMethod(true,"setText",BA.ObjectToCharSequence(1000));
 BA.debugLineNum = 78;BA.debugLine="sign = \"mm\"";
Debug.ShouldStop(8192);
_sign = BA.ObjectToString("mm");Debug.locals.put("sign", _sign);
 break; }
case 4: {
 BA.debugLineNum = 80;BA.debugLine="ans = input * 39.3701";
Debug.ShouldStop(32768);
_ans = RemoteObject.solve(new RemoteObject[] {_input,RemoteObject.createImmutable(39.3701)}, "*",0, 0);Debug.locals.put("ans", _ans);
 BA.debugLineNum = 81;BA.debugLine="Label6.Text = input & \" m\"";
Debug.ShouldStop(65536);
main.mostCurrent._label6.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(_input,RemoteObject.createImmutable(" m"))));
 BA.debugLineNum = 82;BA.debugLine="Label7.Text = \"*\"";
Debug.ShouldStop(131072);
main.mostCurrent._label7.runMethod(true,"setText",BA.ObjectToCharSequence("*"));
 BA.debugLineNum = 83;BA.debugLine="Label8.Text = 39.3701";
Debug.ShouldStop(262144);
main.mostCurrent._label8.runMethod(true,"setText",BA.ObjectToCharSequence(39.3701));
 BA.debugLineNum = 84;BA.debugLine="sign = \"in\"";
Debug.ShouldStop(524288);
_sign = BA.ObjectToString("in");Debug.locals.put("sign", _sign);
 break; }
case 5: {
 BA.debugLineNum = 86;BA.debugLine="ans = input * 3.28084";
Debug.ShouldStop(2097152);
_ans = RemoteObject.solve(new RemoteObject[] {_input,RemoteObject.createImmutable(3.28084)}, "*",0, 0);Debug.locals.put("ans", _ans);
 BA.debugLineNum = 87;BA.debugLine="Label6.Text = input & \" m\"";
Debug.ShouldStop(4194304);
main.mostCurrent._label6.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(_input,RemoteObject.createImmutable(" m"))));
 BA.debugLineNum = 88;BA.debugLine="Label7.Text = \"*\"";
Debug.ShouldStop(8388608);
main.mostCurrent._label7.runMethod(true,"setText",BA.ObjectToCharSequence("*"));
 BA.debugLineNum = 89;BA.debugLine="Label8.Text = 3.28084";
Debug.ShouldStop(16777216);
main.mostCurrent._label8.runMethod(true,"setText",BA.ObjectToCharSequence(3.28084));
 BA.debugLineNum = 90;BA.debugLine="sign = \"ft\"";
Debug.ShouldStop(33554432);
_sign = BA.ObjectToString("ft");Debug.locals.put("sign", _sign);
 break; }
case 6: {
 BA.debugLineNum = 92;BA.debugLine="ans = input * 1.09361";
Debug.ShouldStop(134217728);
_ans = RemoteObject.solve(new RemoteObject[] {_input,RemoteObject.createImmutable(1.09361)}, "*",0, 0);Debug.locals.put("ans", _ans);
 BA.debugLineNum = 93;BA.debugLine="Label6.Text = input & \" m\"";
Debug.ShouldStop(268435456);
main.mostCurrent._label6.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(_input,RemoteObject.createImmutable(" m"))));
 BA.debugLineNum = 94;BA.debugLine="Label7.Text = \"*\"";
Debug.ShouldStop(536870912);
main.mostCurrent._label7.runMethod(true,"setText",BA.ObjectToCharSequence("*"));
 BA.debugLineNum = 95;BA.debugLine="Label8.Text = 1.09361";
Debug.ShouldStop(1073741824);
main.mostCurrent._label8.runMethod(true,"setText",BA.ObjectToCharSequence(1.09361));
 BA.debugLineNum = 96;BA.debugLine="sign = \"yd\"";
Debug.ShouldStop(-2147483648);
_sign = BA.ObjectToString("yd");Debug.locals.put("sign", _sign);
 break; }
default: {
 BA.debugLineNum = 98;BA.debugLine="ans = 0";
Debug.ShouldStop(2);
_ans = BA.numberCast(double.class, 0);Debug.locals.put("ans", _ans);
 BA.debugLineNum = 99;BA.debugLine="Label6.Text = \"\"";
Debug.ShouldStop(4);
main.mostCurrent._label6.runMethod(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 100;BA.debugLine="Label7.Text = \"\"";
Debug.ShouldStop(8);
main.mostCurrent._label7.runMethod(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 101;BA.debugLine="Label8.Text = \"\"";
Debug.ShouldStop(16);
main.mostCurrent._label8.runMethod(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 102;BA.debugLine="sign = \"\"";
Debug.ShouldStop(32);
_sign = BA.ObjectToString("");Debug.locals.put("sign", _sign);
 break; }
}
;
 BA.debugLineNum = 105;BA.debugLine="Label2.Text = ans";
Debug.ShouldStop(256);
main.mostCurrent._label2.runMethod(true,"setText",BA.ObjectToCharSequence(_ans));
 BA.debugLineNum = 106;BA.debugLine="Label4.Text = sign";
Debug.ShouldStop(512);
main.mostCurrent._label4.runMethod(true,"setText",BA.ObjectToCharSequence(_sign));
 BA.debugLineNum = 107;BA.debugLine="End Sub";
Debug.ShouldStop(1024);
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
		Debug.PushSubsStack("EditText1_EnterPressed (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,114);
if (RapidSub.canDelegate("edittext1_enterpressed")) { return b4a.example.main.remoteMe.runUserSub(false, "main","edittext1_enterpressed");}
 BA.debugLineNum = 114;BA.debugLine="Private Sub EditText1_EnterPressed";
Debug.ShouldStop(131072);
 BA.debugLineNum = 115;BA.debugLine="calculate";
Debug.ShouldStop(262144);
_calculate();
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
public static RemoteObject  _globals() throws Exception{
 //BA.debugLineNum = 21;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 23;BA.debugLine="Private Selects As Spinner";
main.mostCurrent._selects = RemoteObject.createNew ("anywheresoftware.b4a.objects.SpinnerWrapper");
 //BA.debugLineNum = 24;BA.debugLine="Private Button1 As Button";
main.mostCurrent._button1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 25;BA.debugLine="Private EditText1 As EditText";
main.mostCurrent._edittext1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 26;BA.debugLine="Private Label2 As Label";
main.mostCurrent._label2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 27;BA.debugLine="Private Label4 As Label";
main.mostCurrent._label4 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 28;BA.debugLine="Private Label6 As Label";
main.mostCurrent._label6 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 29;BA.debugLine="Private Label7 As Label";
main.mostCurrent._label7 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 30;BA.debugLine="Private Label8 As Label";
main.mostCurrent._label8 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 31;BA.debugLine="End Sub";
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
}