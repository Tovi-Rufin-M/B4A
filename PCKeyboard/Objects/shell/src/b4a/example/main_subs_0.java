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
 BA.debugLineNum = 43;BA.debugLine="Activity.Color = Colors.RGB(30, 30, 30)  ' Dark b";
Debug.ShouldStop(1024);
main.mostCurrent._activity.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").runMethod(true,"RGB",(Object)(BA.numberCast(int.class, 30)),(Object)(BA.numberCast(int.class, 30)),(Object)(BA.numberCast(int.class, 30))));
 BA.debugLineNum = 46;BA.debugLine="txtIP.Initialize(\"txtIP\")";
Debug.ShouldStop(8192);
main.mostCurrent._txtip.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("txtIP")));
 BA.debugLineNum = 47;BA.debugLine="txtIP.Text = \"192.168.254.105\"   ' change to your";
Debug.ShouldStop(16384);
main.mostCurrent._txtip.runMethodAndSync(true,"setText",BA.ObjectToCharSequence("192.168.254.105"));
 BA.debugLineNum = 48;BA.debugLine="txtIP.TextSize = 14";
Debug.ShouldStop(32768);
main.mostCurrent._txtip.runMethod(true,"setTextSize",BA.numberCast(float.class, 14));
 BA.debugLineNum = 49;BA.debugLine="Activity.AddView(txtIP, 5%x, 2%y, 60%x, 8%y)";
Debug.ShouldStop(65536);
main.mostCurrent._activity.runVoidMethod ("AddView",(Object)((main.mostCurrent._txtip.getObject())),(Object)(main.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 5)),main.mostCurrent.activityBA)),(Object)(main.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 2)),main.mostCurrent.activityBA)),(Object)(main.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 60)),main.mostCurrent.activityBA)),(Object)(main.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 8)),main.mostCurrent.activityBA)));
 BA.debugLineNum = 52;BA.debugLine="txtPort.Initialize(\"txtPort\")";
Debug.ShouldStop(524288);
main.mostCurrent._txtport.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("txtPort")));
 BA.debugLineNum = 53;BA.debugLine="txtPort.Text = \"5000\"";
Debug.ShouldStop(1048576);
main.mostCurrent._txtport.runMethodAndSync(true,"setText",BA.ObjectToCharSequence("5000"));
 BA.debugLineNum = 54;BA.debugLine="txtPort.TextSize = 14";
Debug.ShouldStop(2097152);
main.mostCurrent._txtport.runMethod(true,"setTextSize",BA.numberCast(float.class, 14));
 BA.debugLineNum = 55;BA.debugLine="Activity.AddView(txtPort, 68%x, 2%y, 27%x, 8%y)";
Debug.ShouldStop(4194304);
main.mostCurrent._activity.runVoidMethod ("AddView",(Object)((main.mostCurrent._txtport.getObject())),(Object)(main.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 68)),main.mostCurrent.activityBA)),(Object)(main.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 2)),main.mostCurrent.activityBA)),(Object)(main.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 27)),main.mostCurrent.activityBA)),(Object)(main.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 8)),main.mostCurrent.activityBA)));
 BA.debugLineNum = 58;BA.debugLine="btnConnect.Initialize(\"btnConnect\")";
Debug.ShouldStop(33554432);
main.mostCurrent._btnconnect.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("btnConnect")));
 BA.debugLineNum = 59;BA.debugLine="btnConnect.Text = \"Connect\"";
Debug.ShouldStop(67108864);
main.mostCurrent._btnconnect.runMethod(true,"setText",BA.ObjectToCharSequence("Connect"));
 BA.debugLineNum = 60;BA.debugLine="btnConnect.Color = Colors.RGB(0, 122, 255)";
Debug.ShouldStop(134217728);
main.mostCurrent._btnconnect.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").runMethod(true,"RGB",(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 122)),(Object)(BA.numberCast(int.class, 255))));
 BA.debugLineNum = 61;BA.debugLine="Activity.AddView(btnConnect, 5%x, 11%y, 90%x, 8%y";
Debug.ShouldStop(268435456);
main.mostCurrent._activity.runVoidMethod ("AddView",(Object)((main.mostCurrent._btnconnect.getObject())),(Object)(main.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 5)),main.mostCurrent.activityBA)),(Object)(main.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 11)),main.mostCurrent.activityBA)),(Object)(main.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 90)),main.mostCurrent.activityBA)),(Object)(main.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 8)),main.mostCurrent.activityBA)));
 BA.debugLineNum = 64;BA.debugLine="lblStatus.Initialize(\"lblStatus\")";
Debug.ShouldStop(-2147483648);
main.mostCurrent._lblstatus.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("lblStatus")));
 BA.debugLineNum = 65;BA.debugLine="lblStatus.Text = \"Not connected\"";
Debug.ShouldStop(1);
main.mostCurrent._lblstatus.runMethod(true,"setText",BA.ObjectToCharSequence("Not connected"));
 BA.debugLineNum = 66;BA.debugLine="lblStatus.TextColor = Colors.Red";
Debug.ShouldStop(2);
main.mostCurrent._lblstatus.runMethod(true,"setTextColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Red"));
 BA.debugLineNum = 67;BA.debugLine="lblStatus.Gravity = Gravity.CENTER";
Debug.ShouldStop(4);
main.mostCurrent._lblstatus.runMethod(true,"setGravity",main.mostCurrent.__c.getField(false,"Gravity").getField(true,"CENTER"));
 BA.debugLineNum = 68;BA.debugLine="Activity.AddView(lblStatus, 5%x, 20%y, 90%x, 5%y)";
Debug.ShouldStop(8);
main.mostCurrent._activity.runVoidMethod ("AddView",(Object)((main.mostCurrent._lblstatus.getObject())),(Object)(main.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 5)),main.mostCurrent.activityBA)),(Object)(main.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 20)),main.mostCurrent.activityBA)),(Object)(main.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 90)),main.mostCurrent.activityBA)),(Object)(main.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 5)),main.mostCurrent.activityBA)));
 BA.debugLineNum = 71;BA.debugLine="BuildKeyboardRow(Array As String(\"Q\",\"W\",\"E\",\"R\",";
Debug.ShouldStop(64);
_buildkeyboardrow(RemoteObject.createNewArray("String",new int[] {10},new Object[] {BA.ObjectToString("Q"),BA.ObjectToString("W"),BA.ObjectToString("E"),BA.ObjectToString("R"),BA.ObjectToString("T"),BA.ObjectToString("Y"),BA.ObjectToString("U"),BA.ObjectToString("I"),BA.ObjectToString("O"),RemoteObject.createImmutable("P")}),BA.numberCast(float.class, main.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 26)),main.mostCurrent.activityBA)));
 BA.debugLineNum = 72;BA.debugLine="BuildKeyboardRow(Array As String(\"A\",\"S\",\"D\",\"F\",";
Debug.ShouldStop(128);
_buildkeyboardrow(RemoteObject.createNewArray("String",new int[] {9},new Object[] {BA.ObjectToString("A"),BA.ObjectToString("S"),BA.ObjectToString("D"),BA.ObjectToString("F"),BA.ObjectToString("G"),BA.ObjectToString("H"),BA.ObjectToString("J"),BA.ObjectToString("K"),RemoteObject.createImmutable("L")}),BA.numberCast(float.class, main.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 36)),main.mostCurrent.activityBA)));
 BA.debugLineNum = 73;BA.debugLine="BuildKeyboardRow(Array As String(\"Z\",\"X\",\"C\",\"V\",";
Debug.ShouldStop(256);
_buildkeyboardrow(RemoteObject.createNewArray("String",new int[] {7},new Object[] {BA.ObjectToString("Z"),BA.ObjectToString("X"),BA.ObjectToString("C"),BA.ObjectToString("V"),BA.ObjectToString("B"),BA.ObjectToString("N"),RemoteObject.createImmutable("M")}),BA.numberCast(float.class, main.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 46)),main.mostCurrent.activityBA)));
 BA.debugLineNum = 76;BA.debugLine="btnSpace.Initialize(\"btnSpace\")";
Debug.ShouldStop(2048);
main.mostCurrent._btnspace.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("btnSpace")));
 BA.debugLineNum = 77;BA.debugLine="btnSpace.Text = \"SPACE\"";
Debug.ShouldStop(4096);
main.mostCurrent._btnspace.runMethod(true,"setText",BA.ObjectToCharSequence("SPACE"));
 BA.debugLineNum = 78;BA.debugLine="btnSpace.Color = Colors.RGB(80, 80, 80)";
Debug.ShouldStop(8192);
main.mostCurrent._btnspace.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").runMethod(true,"RGB",(Object)(BA.numberCast(int.class, 80)),(Object)(BA.numberCast(int.class, 80)),(Object)(BA.numberCast(int.class, 80))));
 BA.debugLineNum = 79;BA.debugLine="btnSpace.TextColor = Colors.White";
Debug.ShouldStop(16384);
main.mostCurrent._btnspace.runMethod(true,"setTextColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 BA.debugLineNum = 80;BA.debugLine="Activity.AddView(btnSpace, 5%x, 56%y, 55%x, 8%y)";
Debug.ShouldStop(32768);
main.mostCurrent._activity.runVoidMethod ("AddView",(Object)((main.mostCurrent._btnspace.getObject())),(Object)(main.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 5)),main.mostCurrent.activityBA)),(Object)(main.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 56)),main.mostCurrent.activityBA)),(Object)(main.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 55)),main.mostCurrent.activityBA)),(Object)(main.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 8)),main.mostCurrent.activityBA)));
 BA.debugLineNum = 82;BA.debugLine="btnBackspace.Initialize(\"btnBackspace\")";
Debug.ShouldStop(131072);
main.mostCurrent._btnbackspace.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("btnBackspace")));
 BA.debugLineNum = 83;BA.debugLine="btnBackspace.Text = \"⌫\"";
Debug.ShouldStop(262144);
main.mostCurrent._btnbackspace.runMethod(true,"setText",BA.ObjectToCharSequence("⌫"));
 BA.debugLineNum = 84;BA.debugLine="btnBackspace.Color = Colors.RGB(180, 60, 60)";
Debug.ShouldStop(524288);
main.mostCurrent._btnbackspace.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").runMethod(true,"RGB",(Object)(BA.numberCast(int.class, 180)),(Object)(BA.numberCast(int.class, 60)),(Object)(BA.numberCast(int.class, 60))));
 BA.debugLineNum = 85;BA.debugLine="btnBackspace.TextColor = Colors.White";
Debug.ShouldStop(1048576);
main.mostCurrent._btnbackspace.runMethod(true,"setTextColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 BA.debugLineNum = 86;BA.debugLine="Activity.AddView(btnBackspace, 63%x, 56%y, 32%x,";
Debug.ShouldStop(2097152);
main.mostCurrent._activity.runVoidMethod ("AddView",(Object)((main.mostCurrent._btnbackspace.getObject())),(Object)(main.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 63)),main.mostCurrent.activityBA)),(Object)(main.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 56)),main.mostCurrent.activityBA)),(Object)(main.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 32)),main.mostCurrent.activityBA)),(Object)(main.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 8)),main.mostCurrent.activityBA)));
 BA.debugLineNum = 88;BA.debugLine="btnEnter.Initialize(\"btnEnter\")";
Debug.ShouldStop(8388608);
main.mostCurrent._btnenter.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("btnEnter")));
 BA.debugLineNum = 89;BA.debugLine="btnEnter.Text = \"ENTER ↵\"";
Debug.ShouldStop(16777216);
main.mostCurrent._btnenter.runMethod(true,"setText",BA.ObjectToCharSequence("ENTER ↵"));
 BA.debugLineNum = 90;BA.debugLine="btnEnter.Color = Colors.RGB(0, 180, 80)";
Debug.ShouldStop(33554432);
main.mostCurrent._btnenter.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").runMethod(true,"RGB",(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 180)),(Object)(BA.numberCast(int.class, 80))));
 BA.debugLineNum = 91;BA.debugLine="btnEnter.TextColor = Colors.White";
Debug.ShouldStop(67108864);
main.mostCurrent._btnenter.runMethod(true,"setTextColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 BA.debugLineNum = 92;BA.debugLine="Activity.AddView(btnEnter, 5%x, 66%y, 90%x, 8%y)";
Debug.ShouldStop(134217728);
main.mostCurrent._activity.runVoidMethod ("AddView",(Object)((main.mostCurrent._btnenter.getObject())),(Object)(main.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 5)),main.mostCurrent.activityBA)),(Object)(main.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 66)),main.mostCurrent.activityBA)),(Object)(main.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 90)),main.mostCurrent.activityBA)),(Object)(main.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 8)),main.mostCurrent.activityBA)));
 BA.debugLineNum = 94;BA.debugLine="connected = False";
Debug.ShouldStop(536870912);
main._connected = main.mostCurrent.__c.getField(true,"False");
 BA.debugLineNum = 95;BA.debugLine="End Sub";
Debug.ShouldStop(1073741824);
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
		Debug.PushSubsStack("Activity_Pause (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,187);
if (RapidSub.canDelegate("activity_pause")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_pause", _userclosed);}
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 187;BA.debugLine="Sub Activity_Pause(UserClosed As Boolean)";
Debug.ShouldStop(67108864);
 BA.debugLineNum = 188;BA.debugLine="If UserClosed Then DisconnectFromPC";
Debug.ShouldStop(134217728);
if (_userclosed.<Boolean>get().booleanValue()) { 
_disconnectfrompc();};
 BA.debugLineNum = 189;BA.debugLine="End Sub";
Debug.ShouldStop(268435456);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _btnbackspace_click() throws Exception{
try {
		Debug.PushSubsStack("btnBackspace_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,157);
if (RapidSub.canDelegate("btnbackspace_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","btnbackspace_click");}
 BA.debugLineNum = 157;BA.debugLine="Sub btnBackspace_Click";
Debug.ShouldStop(268435456);
 BA.debugLineNum = 158;BA.debugLine="SendKey(\"backspace\")";
Debug.ShouldStop(536870912);
_sendkey(RemoteObject.createImmutable("backspace"));
 BA.debugLineNum = 159;BA.debugLine="End Sub";
Debug.ShouldStop(1073741824);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _btnconnect_click() throws Exception{
try {
		Debug.PushSubsStack("btnConnect_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,115);
if (RapidSub.canDelegate("btnconnect_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","btnconnect_click");}
 BA.debugLineNum = 115;BA.debugLine="Sub btnConnect_Click";
Debug.ShouldStop(262144);
 BA.debugLineNum = 116;BA.debugLine="If connected Then";
Debug.ShouldStop(524288);
if (main._connected.<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 117;BA.debugLine="DisconnectFromPC";
Debug.ShouldStop(1048576);
_disconnectfrompc();
 }else {
 BA.debugLineNum = 119;BA.debugLine="ConnectToPC";
Debug.ShouldStop(4194304);
_connecttopc();
 };
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
public static RemoteObject  _btnenter_click() throws Exception{
try {
		Debug.PushSubsStack("btnEnter_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,153);
if (RapidSub.canDelegate("btnenter_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","btnenter_click");}
 BA.debugLineNum = 153;BA.debugLine="Sub btnEnter_Click";
Debug.ShouldStop(16777216);
 BA.debugLineNum = 154;BA.debugLine="SendKey(\"enter\")";
Debug.ShouldStop(33554432);
_sendkey(RemoteObject.createImmutable("enter"));
 BA.debugLineNum = 155;BA.debugLine="End Sub";
Debug.ShouldStop(67108864);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _btnkey_click() throws Exception{
try {
		Debug.PushSubsStack("btnKey_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,144);
if (RapidSub.canDelegate("btnkey_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","btnkey_click");}
RemoteObject _btn = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
 BA.debugLineNum = 144;BA.debugLine="Sub btnKey_Click";
Debug.ShouldStop(32768);
 BA.debugLineNum = 145;BA.debugLine="Dim btn As Button = Sender";
Debug.ShouldStop(65536);
_btn = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
_btn = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.ButtonWrapper"), main.mostCurrent.__c.runMethod(false,"Sender",main.mostCurrent.activityBA));Debug.locals.put("btn", _btn);Debug.locals.put("btn", _btn);
 BA.debugLineNum = 146;BA.debugLine="SendKey(btn.Text.ToLowerCase)";
Debug.ShouldStop(131072);
_sendkey(_btn.runMethod(true,"getText").runMethod(true,"toLowerCase"));
 BA.debugLineNum = 147;BA.debugLine="End Sub";
Debug.ShouldStop(262144);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _btnspace_click() throws Exception{
try {
		Debug.PushSubsStack("btnSpace_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,149);
if (RapidSub.canDelegate("btnspace_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","btnspace_click");}
 BA.debugLineNum = 149;BA.debugLine="Sub btnSpace_Click";
Debug.ShouldStop(1048576);
 BA.debugLineNum = 150;BA.debugLine="SendKey(\"space\")";
Debug.ShouldStop(2097152);
_sendkey(RemoteObject.createImmutable("space"));
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
public static RemoteObject  _buildkeyboardrow(RemoteObject _keys,RemoteObject _ypos) throws Exception{
try {
		Debug.PushSubsStack("BuildKeyboardRow (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,98);
if (RapidSub.canDelegate("buildkeyboardrow")) { return b4a.example.main.remoteMe.runUserSub(false, "main","buildkeyboardrow", _keys, _ypos);}
RemoteObject _totalkeys = RemoteObject.createImmutable(0);
RemoteObject _btnwidth = RemoteObject.createImmutable(0f);
RemoteObject _xstart = RemoteObject.createImmutable(0f);
int _i = 0;
RemoteObject _btn = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
Debug.locals.put("keys", _keys);
Debug.locals.put("yPos", _ypos);
 BA.debugLineNum = 98;BA.debugLine="Sub BuildKeyboardRow(keys() As String, yPos As Flo";
Debug.ShouldStop(2);
 BA.debugLineNum = 99;BA.debugLine="Dim totalKeys As Int = keys.Length";
Debug.ShouldStop(4);
_totalkeys = _keys.getField(true,"length");Debug.locals.put("totalKeys", _totalkeys);Debug.locals.put("totalKeys", _totalkeys);
 BA.debugLineNum = 100;BA.debugLine="Dim btnWidth As Float = 90%x / totalKeys";
Debug.ShouldStop(8);
_btnwidth = BA.numberCast(float.class, RemoteObject.solve(new RemoteObject[] {main.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 90)),main.mostCurrent.activityBA),_totalkeys}, "/",0, 0));Debug.locals.put("btnWidth", _btnwidth);Debug.locals.put("btnWidth", _btnwidth);
 BA.debugLineNum = 101;BA.debugLine="Dim xStart As Float = 5%x";
Debug.ShouldStop(16);
_xstart = BA.numberCast(float.class, main.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 5)),main.mostCurrent.activityBA));Debug.locals.put("xStart", _xstart);Debug.locals.put("xStart", _xstart);
 BA.debugLineNum = 103;BA.debugLine="For i = 0 To totalKeys - 1";
Debug.ShouldStop(64);
{
final int step4 = 1;
final int limit4 = RemoteObject.solve(new RemoteObject[] {_totalkeys,RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
for (;(step4 > 0 && _i <= limit4) || (step4 < 0 && _i >= limit4) ;_i = ((int)(0 + _i + step4))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 104;BA.debugLine="Dim btn As Button";
Debug.ShouldStop(128);
_btn = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");Debug.locals.put("btn", _btn);
 BA.debugLineNum = 105;BA.debugLine="btn.Initialize(\"btnKey\")";
Debug.ShouldStop(256);
_btn.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("btnKey")));
 BA.debugLineNum = 106;BA.debugLine="btn.Text = keys(i)";
Debug.ShouldStop(512);
_btn.runMethod(true,"setText",BA.ObjectToCharSequence(_keys.getArrayElement(true,BA.numberCast(int.class, _i))));
 BA.debugLineNum = 107;BA.debugLine="btn.Color = Colors.RGB(60, 60, 60)";
Debug.ShouldStop(1024);
_btn.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").runMethod(true,"RGB",(Object)(BA.numberCast(int.class, 60)),(Object)(BA.numberCast(int.class, 60)),(Object)(BA.numberCast(int.class, 60))));
 BA.debugLineNum = 108;BA.debugLine="btn.TextColor = Colors.White";
Debug.ShouldStop(2048);
_btn.runMethod(true,"setTextColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 BA.debugLineNum = 109;BA.debugLine="btn.TextSize = 14";
Debug.ShouldStop(4096);
_btn.runMethod(true,"setTextSize",BA.numberCast(float.class, 14));
 BA.debugLineNum = 110;BA.debugLine="Activity.AddView(btn, xStart + (i * btnWidth), y";
Debug.ShouldStop(8192);
main.mostCurrent._activity.runVoidMethod ("AddView",(Object)((_btn.getObject())),(Object)(BA.numberCast(int.class, RemoteObject.solve(new RemoteObject[] {_xstart,(RemoteObject.solve(new RemoteObject[] {RemoteObject.createImmutable(_i),_btnwidth}, "*",0, 0))}, "+",1, 0))),(Object)(BA.numberCast(int.class, _ypos)),(Object)(BA.numberCast(int.class, RemoteObject.solve(new RemoteObject[] {_btnwidth,main.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 1)),main.mostCurrent.activityBA)}, "-",1, 0))),(Object)(main.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 8)),main.mostCurrent.activityBA)));
 }
}Debug.locals.put("i", _i);
;
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
public static RemoteObject  _connecttopc() throws Exception{
try {
		Debug.PushSubsStack("ConnectToPC (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,123);
if (RapidSub.canDelegate("connecttopc")) { return b4a.example.main.remoteMe.runUserSub(false, "main","connecttopc");}
RemoteObject _ip = RemoteObject.createImmutable("");
RemoteObject _port = RemoteObject.createImmutable(0);
 BA.debugLineNum = 123;BA.debugLine="Sub ConnectToPC";
Debug.ShouldStop(67108864);
 BA.debugLineNum = 124;BA.debugLine="Dim ip As String = txtIP.Text";
Debug.ShouldStop(134217728);
_ip = main.mostCurrent._txtip.runMethod(true,"getText");Debug.locals.put("ip", _ip);Debug.locals.put("ip", _ip);
 BA.debugLineNum = 125;BA.debugLine="Dim port As Int = txtPort.Text";
Debug.ShouldStop(268435456);
_port = BA.numberCast(int.class, main.mostCurrent._txtport.runMethod(true,"getText"));Debug.locals.put("port", _port);Debug.locals.put("port", _port);
 BA.debugLineNum = 127;BA.debugLine="socket1.Initialize(\"socket1\")";
Debug.ShouldStop(1073741824);
main._socket1.runVoidMethod ("Initialize",(Object)(RemoteObject.createImmutable("socket1")));
 BA.debugLineNum = 128;BA.debugLine="socket1.Connect(ip, port, 5000)   ' 5 second time";
Debug.ShouldStop(-2147483648);
main._socket1.runVoidMethod ("Connect",main.processBA,(Object)(_ip),(Object)(_port),(Object)(BA.numberCast(int.class, 5000)));
 BA.debugLineNum = 129;BA.debugLine="End Sub";
Debug.ShouldStop(1);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _disconnectfrompc() throws Exception{
try {
		Debug.PushSubsStack("DisconnectFromPC (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,131);
if (RapidSub.canDelegate("disconnectfrompc")) { return b4a.example.main.remoteMe.runUserSub(false, "main","disconnectfrompc");}
 BA.debugLineNum = 131;BA.debugLine="Sub DisconnectFromPC";
Debug.ShouldStop(4);
 BA.debugLineNum = 132;BA.debugLine="Try";
Debug.ShouldStop(8);
try { BA.debugLineNum = 133;BA.debugLine="writer.Close";
Debug.ShouldStop(16);
main._writer.runVoidMethod ("Close");
 BA.debugLineNum = 134;BA.debugLine="socket1.Close";
Debug.ShouldStop(32);
main._socket1.runVoidMethod ("Close");
 Debug.CheckDeviceExceptions();
} 
       catch (Exception e5) {
			BA.rdebugUtils.runVoidMethod("setLastException",main.processBA, e5.toString()); };
 BA.debugLineNum = 137;BA.debugLine="connected = False";
Debug.ShouldStop(256);
main._connected = main.mostCurrent.__c.getField(true,"False");
 BA.debugLineNum = 138;BA.debugLine="lblStatus.Text = \"Disconnected\"";
Debug.ShouldStop(512);
main.mostCurrent._lblstatus.runMethod(true,"setText",BA.ObjectToCharSequence("Disconnected"));
 BA.debugLineNum = 139;BA.debugLine="lblStatus.TextColor = Colors.Red";
Debug.ShouldStop(1024);
main.mostCurrent._lblstatus.runMethod(true,"setTextColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Red"));
 BA.debugLineNum = 140;BA.debugLine="btnConnect.Text = \"Connect\"";
Debug.ShouldStop(2048);
main.mostCurrent._btnconnect.runMethod(true,"setText",BA.ObjectToCharSequence("Connect"));
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
public static RemoteObject  _globals() throws Exception{
 //BA.debugLineNum = 20;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 21;BA.debugLine="Dim btnConnect As Button";
main.mostCurrent._btnconnect = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 22;BA.debugLine="Dim txtIP As EditText";
main.mostCurrent._txtip = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 23;BA.debugLine="Dim txtPort As EditText";
main.mostCurrent._txtport = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 24;BA.debugLine="Dim lblStatus As Label";
main.mostCurrent._lblstatus = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 27;BA.debugLine="Dim btnA As Button, btnB As Button, btnC As Butto";
main.mostCurrent._btna = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
main.mostCurrent._btnb = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
main.mostCurrent._btnc = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 28;BA.debugLine="Dim btnD As Button, btnE As Button, btnF As Butto";
main.mostCurrent._btnd = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
main.mostCurrent._btne = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
main.mostCurrent._btnf = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 29;BA.debugLine="Dim btnG As Button, btnH As Button, btnI As Butto";
main.mostCurrent._btng = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
main.mostCurrent._btnh = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
main.mostCurrent._btni = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 30;BA.debugLine="Dim btnJ As Button, btnK As Button, btnL As Butto";
main.mostCurrent._btnj = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
main.mostCurrent._btnk = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
main.mostCurrent._btnl = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 31;BA.debugLine="Dim btnM As Button, btnN As Button, btnO As Butto";
main.mostCurrent._btnm = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
main.mostCurrent._btnn = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
main.mostCurrent._btno = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 32;BA.debugLine="Dim btnP As Button, btnQ As Button, btnR As Butto";
main.mostCurrent._btnp = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
main.mostCurrent._btnq = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
main.mostCurrent._btnr = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 33;BA.debugLine="Dim btnS As Button, btnT As Button, btnU As Butto";
main.mostCurrent._btns = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
main.mostCurrent._btnt = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
main.mostCurrent._btnu = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 34;BA.debugLine="Dim btnV As Button, btnW As Button, btnX As Butto";
main.mostCurrent._btnv = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
main.mostCurrent._btnw = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
main.mostCurrent._btnx = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 35;BA.debugLine="Dim btnY As Button, btnZ As Button";
main.mostCurrent._btny = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
main.mostCurrent._btnz = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 36;BA.debugLine="Dim btnSpace As Button";
main.mostCurrent._btnspace = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 37;BA.debugLine="Dim btnEnter As Button";
main.mostCurrent._btnenter = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 38;BA.debugLine="Dim btnBackspace As Button";
main.mostCurrent._btnbackspace = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 39;BA.debugLine="End Sub";
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
 //BA.debugLineNum = 14;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 15;BA.debugLine="Dim socket1 As Socket";
main._socket1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.SocketWrapper");
 //BA.debugLineNum = 16;BA.debugLine="Dim writer As TextWriter";
main._writer = RemoteObject.createNew ("anywheresoftware.b4a.objects.streams.File.TextWriterWrapper");
 //BA.debugLineNum = 17;BA.debugLine="Dim connected As Boolean";
main._connected = RemoteObject.createImmutable(false);
 //BA.debugLineNum = 18;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _sendkey(RemoteObject _key) throws Exception{
try {
		Debug.PushSubsStack("SendKey (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,163);
if (RapidSub.canDelegate("sendkey")) { return b4a.example.main.remoteMe.runUserSub(false, "main","sendkey", _key);}
Debug.locals.put("key", _key);
 BA.debugLineNum = 163;BA.debugLine="Sub SendKey(key As String)";
Debug.ShouldStop(4);
 BA.debugLineNum = 164;BA.debugLine="ToastMessageShow(\"Sending: \" & key, False)   '";
Debug.ShouldStop(8);
main.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("Sending: "),_key))),(Object)(main.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 165;BA.debugLine="If connected = False Then";
Debug.ShouldStop(16);
if (RemoteObject.solveBoolean("=",main._connected,main.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 166;BA.debugLine="ToastMessageShow(\"Not connected!\", False)";
Debug.ShouldStop(32);
main.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence("Not connected!")),(Object)(main.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 167;BA.debugLine="Return";
Debug.ShouldStop(64);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 169;BA.debugLine="Try";
Debug.ShouldStop(256);
try { BA.debugLineNum = 170;BA.debugLine="writer.WriteLine(key)";
Debug.ShouldStop(512);
main._writer.runVoidMethod ("WriteLine",(Object)(_key));
 BA.debugLineNum = 171;BA.debugLine="writer.Flush";
Debug.ShouldStop(1024);
main._writer.runVoidMethod ("Flush");
 Debug.CheckDeviceExceptions();
} 
       catch (Exception e10) {
			BA.rdebugUtils.runVoidMethod("setLastException",main.processBA, e10.toString()); BA.debugLineNum = 173;BA.debugLine="ToastMessageShow(\"Write error!\", False)";
Debug.ShouldStop(4096);
main.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence("Write error!")),(Object)(main.mostCurrent.__c.getField(true,"False")));
 };
 BA.debugLineNum = 175;BA.debugLine="End Sub";
Debug.ShouldStop(16384);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _socket1_connected(RemoteObject _successful) throws Exception{
try {
		Debug.PushSubsStack("socket1_Connected (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,177);
if (RapidSub.canDelegate("socket1_connected")) { return b4a.example.main.remoteMe.runUserSub(false, "main","socket1_connected", _successful);}
Debug.locals.put("Successful", _successful);
 BA.debugLineNum = 177;BA.debugLine="Sub socket1_Connected(Successful As Boolean)";
Debug.ShouldStop(65536);
 BA.debugLineNum = 178;BA.debugLine="If Successful Then";
Debug.ShouldStop(131072);
if (_successful.<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 179;BA.debugLine="writer.Initialize(socket1.OutputStream)";
Debug.ShouldStop(262144);
main._writer.runVoidMethod ("Initialize",(Object)(main._socket1.runMethod(false,"getOutputStream")));
 BA.debugLineNum = 180;BA.debugLine="connected = True";
Debug.ShouldStop(524288);
main._connected = main.mostCurrent.__c.getField(true,"True");
 BA.debugLineNum = 181;BA.debugLine="ToastMessageShow(\"Connected OK!\", True)";
Debug.ShouldStop(1048576);
main.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence("Connected OK!")),(Object)(main.mostCurrent.__c.getField(true,"True")));
 }else {
 BA.debugLineNum = 183;BA.debugLine="ToastMessageShow(\"FAILED to connect!\", Tru";
Debug.ShouldStop(4194304);
main.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence("FAILED to connect!")),(Object)(main.mostCurrent.__c.getField(true,"True")));
 };
 BA.debugLineNum = 185;BA.debugLine="End Sub";
Debug.ShouldStop(16777216);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}