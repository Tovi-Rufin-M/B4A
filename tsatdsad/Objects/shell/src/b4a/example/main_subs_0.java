package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class main_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,12);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_create", _firsttime);}
RemoteObject _numcols = RemoteObject.createImmutable(0);
RemoteObject _numrows = RemoteObject.createImmutable(0);
RemoteObject _cellwidth = RemoteObject.createImmutable(0);
RemoteObject _cellheight = RemoteObject.createImmutable(0);
RemoteObject _bordersize = RemoteObject.createImmutable(0);
RemoteObject _tabledata = null;
RemoteObject _r = RemoteObject.createImmutable(0);
RemoteObject _c = RemoteObject.createImmutable(0);
RemoteObject _pnlcell = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
RemoteObject _lblcell = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 12;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(2048);
 BA.debugLineNum = 13;BA.debugLine="Activity.LoadLayout(\"main\")";
Debug.ShouldStop(4096);
main.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("main")),main.mostCurrent.activityBA);
 BA.debugLineNum = 15;BA.debugLine="Dim NumCols As Int = 4";
Debug.ShouldStop(16384);
_numcols = BA.numberCast(int.class, 4);Debug.locals.put("NumCols", _numcols);Debug.locals.put("NumCols", _numcols);
 BA.debugLineNum = 16;BA.debugLine="Dim NumRows As Int = 3";
Debug.ShouldStop(32768);
_numrows = BA.numberCast(int.class, 3);Debug.locals.put("NumRows", _numrows);Debug.locals.put("NumRows", _numrows);
 BA.debugLineNum = 17;BA.debugLine="Dim CellWidth As Int = Activity.Width / NumCols";
Debug.ShouldStop(65536);
_cellwidth = BA.numberCast(int.class, RemoteObject.solve(new RemoteObject[] {main.mostCurrent._activity.runMethod(true,"getWidth"),_numcols}, "/",0, 0));Debug.locals.put("CellWidth", _cellwidth);Debug.locals.put("CellWidth", _cellwidth);
 BA.debugLineNum = 18;BA.debugLine="Dim CellHeight As Int = 80dip";
Debug.ShouldStop(131072);
_cellheight = main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 80)));Debug.locals.put("CellHeight", _cellheight);Debug.locals.put("CellHeight", _cellheight);
 BA.debugLineNum = 19;BA.debugLine="Dim BorderSize As Int = 1dip";
Debug.ShouldStop(262144);
_bordersize = main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 1)));Debug.locals.put("BorderSize", _bordersize);Debug.locals.put("BorderSize", _bordersize);
 BA.debugLineNum = 22;BA.debugLine="Dim TableData(NumRows, NumCols) As String";
Debug.ShouldStop(2097152);
_tabledata = RemoteObject.createNewArray ("String", new int[] {_numrows.<Integer>get().intValue(),_numcols.<Integer>get().intValue()}, new Object[]{});Debug.locals.put("TableData", _tabledata);
 BA.debugLineNum = 23;BA.debugLine="TableData(0, 0) = \"Column 1\"  : TableData(0, 1) =";
Debug.ShouldStop(4194304);
_tabledata.setArrayElement (BA.ObjectToString("Column 1"),BA.numberCast(int.class, 0),BA.numberCast(int.class, 0));
 BA.debugLineNum = 23;BA.debugLine="TableData(0, 0) = \"Column 1\"  : TableData(0, 1) =";
Debug.ShouldStop(4194304);
_tabledata.setArrayElement (BA.ObjectToString("Column 2"),BA.numberCast(int.class, 0),BA.numberCast(int.class, 1));
 BA.debugLineNum = 23;BA.debugLine="TableData(0, 0) = \"Column 1\"  : TableData(0, 1) =";
Debug.ShouldStop(4194304);
_tabledata.setArrayElement (BA.ObjectToString("Column 3"),BA.numberCast(int.class, 0),BA.numberCast(int.class, 2));
 BA.debugLineNum = 23;BA.debugLine="TableData(0, 0) = \"Column 1\"  : TableData(0, 1) =";
Debug.ShouldStop(4194304);
_tabledata.setArrayElement (BA.ObjectToString("Column 4"),BA.numberCast(int.class, 0),BA.numberCast(int.class, 3));
 BA.debugLineNum = 24;BA.debugLine="TableData(1, 0) = \"Row 1 C1\"  : TableData(1, 1) =";
Debug.ShouldStop(8388608);
_tabledata.setArrayElement (BA.ObjectToString("Row 1 C1"),BA.numberCast(int.class, 1),BA.numberCast(int.class, 0));
 BA.debugLineNum = 24;BA.debugLine="TableData(1, 0) = \"Row 1 C1\"  : TableData(1, 1) =";
Debug.ShouldStop(8388608);
_tabledata.setArrayElement (BA.ObjectToString("Row 1 C2"),BA.numberCast(int.class, 1),BA.numberCast(int.class, 1));
 BA.debugLineNum = 24;BA.debugLine="TableData(1, 0) = \"Row 1 C1\"  : TableData(1, 1) =";
Debug.ShouldStop(8388608);
_tabledata.setArrayElement (BA.ObjectToString("Row 1 C3"),BA.numberCast(int.class, 1),BA.numberCast(int.class, 2));
 BA.debugLineNum = 24;BA.debugLine="TableData(1, 0) = \"Row 1 C1\"  : TableData(1, 1) =";
Debug.ShouldStop(8388608);
_tabledata.setArrayElement (BA.ObjectToString("Row 1 C4"),BA.numberCast(int.class, 1),BA.numberCast(int.class, 3));
 BA.debugLineNum = 25;BA.debugLine="TableData(2, 0) = \"Row 2 C1\"  : TableData(2, 1) =";
Debug.ShouldStop(16777216);
_tabledata.setArrayElement (BA.ObjectToString("Row 2 C1"),BA.numberCast(int.class, 2),BA.numberCast(int.class, 0));
 BA.debugLineNum = 25;BA.debugLine="TableData(2, 0) = \"Row 2 C1\"  : TableData(2, 1) =";
Debug.ShouldStop(16777216);
_tabledata.setArrayElement (BA.ObjectToString("Row 2 C2"),BA.numberCast(int.class, 2),BA.numberCast(int.class, 1));
 BA.debugLineNum = 25;BA.debugLine="TableData(2, 0) = \"Row 2 C1\"  : TableData(2, 1) =";
Debug.ShouldStop(16777216);
_tabledata.setArrayElement (BA.ObjectToString("Row 2 C3"),BA.numberCast(int.class, 2),BA.numberCast(int.class, 2));
 BA.debugLineNum = 25;BA.debugLine="TableData(2, 0) = \"Row 2 C1\"  : TableData(2, 1) =";
Debug.ShouldStop(16777216);
_tabledata.setArrayElement (BA.ObjectToString("Row 2 C4"),BA.numberCast(int.class, 2),BA.numberCast(int.class, 3));
 BA.debugLineNum = 28;BA.debugLine="pnlTable.Initialize(\"pnlTable\")";
Debug.ShouldStop(134217728);
main.mostCurrent._pnltable.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("pnlTable")));
 BA.debugLineNum = 29;BA.debugLine="pnlTable.Color = Colors.Black ' border color";
Debug.ShouldStop(268435456);
main.mostCurrent._pnltable.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 BA.debugLineNum = 30;BA.debugLine="Activity.AddView(pnlTable, 0, 50dip, Activity.Wid";
Debug.ShouldStop(536870912);
main.mostCurrent._activity.runVoidMethod ("AddView",(Object)((main.mostCurrent._pnltable.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 50)))),(Object)(main.mostCurrent._activity.runMethod(true,"getWidth")),(Object)(RemoteObject.solve(new RemoteObject[] {_numrows,_cellheight,_bordersize}, "*+",1, 1)));
 BA.debugLineNum = 32;BA.debugLine="Dim r As Int";
Debug.ShouldStop(-2147483648);
_r = RemoteObject.createImmutable(0);Debug.locals.put("r", _r);
 BA.debugLineNum = 33;BA.debugLine="Dim c As Int";
Debug.ShouldStop(1);
_c = RemoteObject.createImmutable(0);Debug.locals.put("c", _c);
 BA.debugLineNum = 35;BA.debugLine="For r = 0 To NumRows - 1";
Debug.ShouldStop(4);
{
final int step25 = 1;
final int limit25 = RemoteObject.solve(new RemoteObject[] {_numrows,RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_r = BA.numberCast(int.class, 0) ;
for (;(step25 > 0 && _r.<Integer>get().intValue() <= limit25) || (step25 < 0 && _r.<Integer>get().intValue() >= limit25) ;_r = RemoteObject.createImmutable((int)(0 + _r.<Integer>get().intValue() + step25))  ) {
Debug.locals.put("r", _r);
 BA.debugLineNum = 36;BA.debugLine="For c = 0 To NumCols - 1";
Debug.ShouldStop(8);
{
final int step26 = 1;
final int limit26 = RemoteObject.solve(new RemoteObject[] {_numcols,RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_c = BA.numberCast(int.class, 0) ;
for (;(step26 > 0 && _c.<Integer>get().intValue() <= limit26) || (step26 < 0 && _c.<Integer>get().intValue() >= limit26) ;_c = RemoteObject.createImmutable((int)(0 + _c.<Integer>get().intValue() + step26))  ) {
Debug.locals.put("c", _c);
 BA.debugLineNum = 39;BA.debugLine="Dim pnlCell As Panel";
Debug.ShouldStop(64);
_pnlcell = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");Debug.locals.put("pnlCell", _pnlcell);
 BA.debugLineNum = 40;BA.debugLine="pnlCell.Initialize(\"pnlCell\")";
Debug.ShouldStop(128);
_pnlcell.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("pnlCell")));
 BA.debugLineNum = 41;BA.debugLine="pnlCell.Color = Colors.Black";
Debug.ShouldStop(256);
_pnlcell.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 BA.debugLineNum = 42;BA.debugLine="pnlTable.AddView(pnlCell, c * CellWidth, r * Ce";
Debug.ShouldStop(512);
main.mostCurrent._pnltable.runVoidMethod ("AddView",(Object)((_pnlcell.getObject())),(Object)(RemoteObject.solve(new RemoteObject[] {_c,_cellwidth}, "*",0, 1)),(Object)(RemoteObject.solve(new RemoteObject[] {_r,_cellheight}, "*",0, 1)),(Object)(_cellwidth),(Object)(_cellheight));
 BA.debugLineNum = 45;BA.debugLine="Dim lblCell As Label";
Debug.ShouldStop(4096);
_lblcell = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("lblCell", _lblcell);
 BA.debugLineNum = 46;BA.debugLine="lblCell.Initialize(\"lblCell\")";
Debug.ShouldStop(8192);
_lblcell.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("lblCell")));
 BA.debugLineNum = 47;BA.debugLine="lblCell.Text = TableData(r, c)";
Debug.ShouldStop(16384);
_lblcell.runMethod(true,"setText",BA.ObjectToCharSequence(_tabledata.getArrayElement(true,_r,_c)));
 BA.debugLineNum = 48;BA.debugLine="lblCell.Gravity = Gravity.CENTER";
Debug.ShouldStop(32768);
_lblcell.runMethod(true,"setGravity",main.mostCurrent.__c.getField(false,"Gravity").getField(true,"CENTER"));
 BA.debugLineNum = 49;BA.debugLine="lblCell.TextSize = 14";
Debug.ShouldStop(65536);
_lblcell.runMethod(true,"setTextSize",BA.numberCast(float.class, 14));
 BA.debugLineNum = 52;BA.debugLine="If r = 0 Then";
Debug.ShouldStop(524288);
if (RemoteObject.solveBoolean("=",_r,BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 53;BA.debugLine="lblCell.Color = Colors.RGB(63, 81, 181)  ' Blu";
Debug.ShouldStop(1048576);
_lblcell.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").runMethod(true,"RGB",(Object)(BA.numberCast(int.class, 63)),(Object)(BA.numberCast(int.class, 81)),(Object)(BA.numberCast(int.class, 181))));
 BA.debugLineNum = 54;BA.debugLine="lblCell.TextColor = Colors.White";
Debug.ShouldStop(2097152);
_lblcell.runMethod(true,"setTextColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 }else {
 BA.debugLineNum = 56;BA.debugLine="If r Mod 2 = 0 Then";
Debug.ShouldStop(8388608);
if (RemoteObject.solveBoolean("=",RemoteObject.solve(new RemoteObject[] {_r,RemoteObject.createImmutable(2)}, "%",0, 1),BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 57;BA.debugLine="lblCell.Color = Colors.RGB(232, 234, 246)  '";
Debug.ShouldStop(16777216);
_lblcell.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").runMethod(true,"RGB",(Object)(BA.numberCast(int.class, 232)),(Object)(BA.numberCast(int.class, 234)),(Object)(BA.numberCast(int.class, 246))));
 }else {
 BA.debugLineNum = 59;BA.debugLine="lblCell.Color = Colors.White";
Debug.ShouldStop(67108864);
_lblcell.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 };
 BA.debugLineNum = 61;BA.debugLine="lblCell.TextColor = Colors.Black";
Debug.ShouldStop(268435456);
_lblcell.runMethod(true,"setTextColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 };
 BA.debugLineNum = 64;BA.debugLine="pnlCell.AddView(lblCell, BorderSize, BorderSize";
Debug.ShouldStop(-2147483648);
_pnlcell.runVoidMethod ("AddView",(Object)((_lblcell.getObject())),(Object)(_bordersize),(Object)(_bordersize),(Object)(RemoteObject.solve(new RemoteObject[] {_cellwidth,_bordersize,RemoteObject.createImmutable(2)}, "-*",1, 1)),(Object)(RemoteObject.solve(new RemoteObject[] {_cellheight,_bordersize,RemoteObject.createImmutable(2)}, "-*",1, 1)));
 }
}Debug.locals.put("c", _c);
;
 }
}Debug.locals.put("r", _r);
;
 BA.debugLineNum = 69;BA.debugLine="End Sub";
Debug.ShouldStop(16);
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
		Debug.PushSubsStack("Activity_Pause (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,75);
if (RapidSub.canDelegate("activity_pause")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_pause", _userclosed);}
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 75;BA.debugLine="Sub Activity_Pause(UserClosed As Boolean)";
Debug.ShouldStop(1024);
 BA.debugLineNum = 77;BA.debugLine="End Sub";
Debug.ShouldStop(4096);
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
		Debug.PushSubsStack("Activity_Resume (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,71);
if (RapidSub.canDelegate("activity_resume")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_resume");}
 BA.debugLineNum = 71;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(64);
 BA.debugLineNum = 73;BA.debugLine="End Sub";
Debug.ShouldStop(256);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _globals() throws Exception{
 //BA.debugLineNum = 7;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 9;BA.debugLine="Dim pnlTable As Panel";
main.mostCurrent._pnltable = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 10;BA.debugLine="End Sub";
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
 //BA.debugLineNum = 2;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 4;BA.debugLine="Private xui As XUI";
main._xui = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XViewWrapper.XUI");
 //BA.debugLineNum = 5;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
}