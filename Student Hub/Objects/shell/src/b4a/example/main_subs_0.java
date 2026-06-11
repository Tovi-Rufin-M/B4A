package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class main_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,48);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_create", _firsttime);}
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 48;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(32768);
 BA.debugLineNum = 50;BA.debugLine="Activity.LoadLayout(\"main\")";
Debug.ShouldStop(131072);
main.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("main")),main.mostCurrent.activityBA);
 BA.debugLineNum = 52;BA.debugLine="initdrawer";
Debug.ShouldStop(524288);
_initdrawer();
 BA.debugLineNum = 54;BA.debugLine="HeaderColor = Colors.Transparent";
Debug.ShouldStop(2097152);
main._headercolor = main.mostCurrent.__c.getField(false,"Colors").getField(true,"Transparent");
 BA.debugLineNum = 55;BA.debugLine="NumberOfColumns = 4 'will be overwritten when loa";
Debug.ShouldStop(4194304);
main._numberofcolumns = BA.numberCast(int.class, 4);
 BA.debugLineNum = 56;BA.debugLine="RowHeight = 30dip";
Debug.ShouldStop(8388608);
main._rowheight = main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 30)));
 BA.debugLineNum = 58;BA.debugLine="FontColor = Colors.Black";
Debug.ShouldStop(33554432);
main._fontcolor = main.mostCurrent.__c.getField(false,"Colors").getField(true,"Black");
 BA.debugLineNum = 59;BA.debugLine="HeaderFontColor = Colors.Black";
Debug.ShouldStop(67108864);
main._headerfontcolor = main.mostCurrent.__c.getField(false,"Colors").getField(true,"Black");
 BA.debugLineNum = 60;BA.debugLine="FontSize = 14";
Debug.ShouldStop(134217728);
main._fontsize = BA.numberCast(float.class, 14);
 BA.debugLineNum = 61;BA.debugLine="Alignment = Gravity.LEFT 'change to Gravity.LEFT";
Debug.ShouldStop(268435456);
main._alignment = main.mostCurrent.__c.getField(false,"Gravity").getField(true,"LEFT");
 BA.debugLineNum = 62;BA.debugLine="End Sub";
Debug.ShouldStop(536870912);
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
		Debug.PushSubsStack("Activity_Pause (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,44);
if (RapidSub.canDelegate("activity_pause")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_pause", _userclosed);}
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 44;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(2048);
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
public static RemoteObject  _activity_resume() throws Exception{
try {
		Debug.PushSubsStack("Activity_Resume (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,40);
if (RapidSub.canDelegate("activity_resume")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_resume");}
 BA.debugLineNum = 40;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(128);
 BA.debugLineNum = 42;BA.debugLine="End Sub";
Debug.ShouldStop(512);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _addrow(RemoteObject _values) throws Exception{
try {
		Debug.PushSubsStack("AddRow (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,178);
if (RapidSub.canDelegate("addrow")) { return b4a.example.main.remoteMe.runUserSub(false, "main","addrow", _values);}
RemoteObject _lastrow = RemoteObject.createImmutable(0);
int _i = 0;
RemoteObject _l = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
RemoteObject _rc = RemoteObject.declareNull("b4a.example.main._rowcol");
Debug.locals.put("Values", _values);
 BA.debugLineNum = 178;BA.debugLine="Sub AddRow(Values() As String)";
Debug.ShouldStop(131072);
 BA.debugLineNum = 179;BA.debugLine="If Values.Length <> NumberOfColumns Then";
Debug.ShouldStop(262144);
if (RemoteObject.solveBoolean("!",_values.getField(true,"length"),BA.numberCast(double.class, main._numberofcolumns))) { 
 BA.debugLineNum = 180;BA.debugLine="Log(\"Wrong number of values.\")";
Debug.ShouldStop(524288);
main.mostCurrent.__c.runVoidMethod ("LogImpl","42359298",RemoteObject.createImmutable("Wrong number of values."),0);
 BA.debugLineNum = 181;BA.debugLine="Return";
Debug.ShouldStop(1048576);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 183;BA.debugLine="Dim lastRow As Int";
Debug.ShouldStop(4194304);
_lastrow = RemoteObject.createImmutable(0);Debug.locals.put("lastRow", _lastrow);
 BA.debugLineNum = 184;BA.debugLine="lastRow = NumberOfRows";
Debug.ShouldStop(8388608);
_lastrow = _numberofrows();Debug.locals.put("lastRow", _lastrow);
 BA.debugLineNum = 185;BA.debugLine="For i = 0 To NumberOfColumns - 1";
Debug.ShouldStop(16777216);
{
final int step7 = 1;
final int limit7 = RemoteObject.solve(new RemoteObject[] {main._numberofcolumns,RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
for (;(step7 > 0 && _i <= limit7) || (step7 < 0 && _i >= limit7) ;_i = ((int)(0 + _i + step7))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 186;BA.debugLine="Dim l As Label";
Debug.ShouldStop(33554432);
_l = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("l", _l);
 BA.debugLineNum = 187;BA.debugLine="l.Initialize(\"cell\")";
Debug.ShouldStop(67108864);
_l.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("cell")));
 BA.debugLineNum = 188;BA.debugLine="l.Text = Values(i)";
Debug.ShouldStop(134217728);
_l.runMethod(true,"setText",BA.ObjectToCharSequence(_values.getArrayElement(true,BA.numberCast(int.class, _i))));
 BA.debugLineNum = 189;BA.debugLine="l.Gravity = Alignment";
Debug.ShouldStop(268435456);
_l.runMethod(true,"setGravity",main._alignment);
 BA.debugLineNum = 190;BA.debugLine="l.TextSize = FontSize";
Debug.ShouldStop(536870912);
_l.runMethod(true,"setTextSize",main._fontsize);
 BA.debugLineNum = 191;BA.debugLine="l.TextColor = FontColor";
Debug.ShouldStop(1073741824);
_l.runMethod(true,"setTextColor",main._fontcolor);
 BA.debugLineNum = 192;BA.debugLine="l.Padding = Array As Int (10dip, 0, 0, 0)";
Debug.ShouldStop(-2147483648);
_l.runMethod(false,"setPadding",RemoteObject.createNewArray("int",new int[] {4},new Object[] {main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10))),BA.numberCast(int.class, 0),BA.numberCast(int.class, 0),BA.numberCast(int.class, 0)}));
 BA.debugLineNum = 193;BA.debugLine="Dim rc As RowCol";
Debug.ShouldStop(1);
_rc = RemoteObject.createNew ("b4a.example.main._rowcol");Debug.locals.put("rc", _rc);
 BA.debugLineNum = 194;BA.debugLine="rc.Initialize";
Debug.ShouldStop(2);
_rc.runVoidMethod ("Initialize");
 BA.debugLineNum = 195;BA.debugLine="rc.Col = i";
Debug.ShouldStop(4);
_rc.setField ("Col" /*RemoteObject*/ ,BA.numberCast(int.class, _i));
 BA.debugLineNum = 196;BA.debugLine="rc.Row = lastRow";
Debug.ShouldStop(8);
_rc.setField ("Row" /*RemoteObject*/ ,_lastrow);
 BA.debugLineNum = 197;BA.debugLine="l.Tag = rc";
Debug.ShouldStop(16);
_l.runMethod(false,"setTag",(_rc));
 BA.debugLineNum = 198;BA.debugLine="Table.AddView(l, ColumnWidth * i, RowHeight * la";
Debug.ShouldStop(32);
main.mostCurrent._table.runVoidMethod ("AddView",(Object)((_l.getObject())),(Object)(RemoteObject.solve(new RemoteObject[] {main._columnwidth,RemoteObject.createImmutable(_i)}, "*",0, 1)),(Object)(RemoteObject.solve(new RemoteObject[] {main._rowheight,_lastrow}, "*",0, 1)),(Object)(main._columnwidth),(Object)(main._rowheight));
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 200;BA.debugLine="Table.Height = NumberOfRows * RowHeight";
Debug.ShouldStop(128);
main.mostCurrent._table.runMethod(true,"setHeight",RemoteObject.solve(new RemoteObject[] {_numberofrows(),main._rowheight}, "*",0, 1));
 BA.debugLineNum = 201;BA.debugLine="End Sub";
Debug.ShouldStop(256);
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
		Debug.PushSubsStack("btnHome_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,104);
if (RapidSub.canDelegate("btnhome_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","btnhome_click");}
 BA.debugLineNum = 104;BA.debugLine="Sub btnHome_Click";
Debug.ShouldStop(128);
 BA.debugLineNum = 105;BA.debugLine="Drawer.LeftOpen = False";
Debug.ShouldStop(256);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_setleftopen" /*RemoteObject*/ ,main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 106;BA.debugLine="ShowHome";
Debug.ShouldStop(512);
_showhome();
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
public static RemoteObject  _btnmenu_click() throws Exception{
try {
		Debug.PushSubsStack("btnMenu_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,64);
if (RapidSub.canDelegate("btnmenu_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","btnmenu_click");}
 BA.debugLineNum = 64;BA.debugLine="Sub btnMenu_Click";
Debug.ShouldStop(-2147483648);
 BA.debugLineNum = 65;BA.debugLine="Drawer.LeftOpen = Not(Drawer.LeftOpen)";
Debug.ShouldStop(1);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_setleftopen" /*RemoteObject*/ ,main.mostCurrent.__c.runMethod(true,"Not",(Object)(main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_getleftopen" /*RemoteObject*/ ))));
 BA.debugLineNum = 66;BA.debugLine="End Sub";
Debug.ShouldStop(2);
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
		Debug.PushSubsStack("btnPage1_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,114);
if (RapidSub.canDelegate("btnpage1_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","btnpage1_click");}
 BA.debugLineNum = 114;BA.debugLine="Sub btnPage1_Click";
Debug.ShouldStop(131072);
 BA.debugLineNum = 115;BA.debugLine="Drawer.LeftOpen = False";
Debug.ShouldStop(262144);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_setleftopen" /*RemoteObject*/ ,main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 116;BA.debugLine="ShowPage1";
Debug.ShouldStop(524288);
_showpage1();
 BA.debugLineNum = 117;BA.debugLine="End Sub";
Debug.ShouldStop(1048576);
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
		Debug.PushSubsStack("btnPage2_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,119);
if (RapidSub.canDelegate("btnpage2_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","btnpage2_click");}
 BA.debugLineNum = 119;BA.debugLine="Sub btnPage2_Click";
Debug.ShouldStop(4194304);
 BA.debugLineNum = 120;BA.debugLine="Drawer.LeftOpen = False";
Debug.ShouldStop(8388608);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_setleftopen" /*RemoteObject*/ ,main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 121;BA.debugLine="ShowPage2";
Debug.ShouldStop(16777216);
_showpage2();
 BA.debugLineNum = 122;BA.debugLine="End Sub";
Debug.ShouldStop(33554432);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _btntable_click() throws Exception{
try {
		Debug.PushSubsStack("btnTable_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,109);
if (RapidSub.canDelegate("btntable_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","btntable_click");}
 BA.debugLineNum = 109;BA.debugLine="Sub btnTable_Click";
Debug.ShouldStop(4096);
 BA.debugLineNum = 110;BA.debugLine="Drawer.LeftOpen = False";
Debug.ShouldStop(8192);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_setleftopen" /*RemoteObject*/ ,main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 111;BA.debugLine="ShowTable";
Debug.ShouldStop(16384);
_showtable();
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
public static RemoteObject  _createmenu() throws Exception{
try {
		Debug.PushSubsStack("CreateMenu (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,80);
if (RapidSub.canDelegate("createmenu")) { return b4a.example.main.remoteMe.runUserSub(false, "main","createmenu");}
RemoteObject _btnhome = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
RemoteObject _btntable = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
RemoteObject _btnpage1 = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
RemoteObject _btnpage2 = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
RemoteObject _b = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
 BA.debugLineNum = 80;BA.debugLine="Sub CreateMenu";
Debug.ShouldStop(32768);
 BA.debugLineNum = 81;BA.debugLine="Dim btnHome, btnTable, btnPage1, btnPage2 As Butt";
Debug.ShouldStop(65536);
_btnhome = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");Debug.locals.put("btnHome", _btnhome);
_btntable = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");Debug.locals.put("btnTable", _btntable);
_btnpage1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");Debug.locals.put("btnPage1", _btnpage1);
_btnpage2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");Debug.locals.put("btnPage2", _btnpage2);
 BA.debugLineNum = 82;BA.debugLine="btnHome.Initialize(\"btnHome\")";
Debug.ShouldStop(131072);
_btnhome.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("btnHome")));
 BA.debugLineNum = 83;BA.debugLine="btnHome.Text = \"Home\"";
Debug.ShouldStop(262144);
_btnhome.runMethod(true,"setText",BA.ObjectToCharSequence("Home"));
 BA.debugLineNum = 84;BA.debugLine="btnTable.Initialize(\"btnTable\")";
Debug.ShouldStop(524288);
_btntable.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("btnTable")));
 BA.debugLineNum = 85;BA.debugLine="btnTable.Text = \"City Table\"";
Debug.ShouldStop(1048576);
_btntable.runMethod(true,"setText",BA.ObjectToCharSequence("City Table"));
 BA.debugLineNum = 86;BA.debugLine="btnPage1.Initialize(\"btnPage1\")";
Debug.ShouldStop(2097152);
_btnpage1.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("btnPage1")));
 BA.debugLineNum = 87;BA.debugLine="btnPage1.Text = \"Length Converter\" 'temporary tex";
Debug.ShouldStop(4194304);
_btnpage1.runMethod(true,"setText",BA.ObjectToCharSequence("Length Converter"));
 BA.debugLineNum = 88;BA.debugLine="btnPage2.Initialize(\"btnPage2\")";
Debug.ShouldStop(8388608);
_btnpage2.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("btnPage2")));
 BA.debugLineNum = 89;BA.debugLine="btnPage2.Text = \"Weight Converter\" 'temporary tex";
Debug.ShouldStop(16777216);
_btnpage2.runMethod(true,"setText",BA.ObjectToCharSequence("Weight Converter"));
 BA.debugLineNum = 91;BA.debugLine="For Each b As Button In Array(btnHome, btnTable,";
Debug.ShouldStop(67108864);
_b = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
{
final RemoteObject group10 = RemoteObject.createNewArray("Object",new int[] {4},new Object[] {(_btnhome.getObject()),(_btntable.getObject()),(_btnpage1.getObject()),(_btnpage2.getObject())});
final int groupLen10 = group10.getField(true,"length").<Integer>get()
;int index10 = 0;
;
for (; index10 < groupLen10;index10++){
_b = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.ButtonWrapper"), group10.getArrayElement(false,RemoteObject.createImmutable(index10)));Debug.locals.put("b", _b);
Debug.locals.put("b", _b);
 BA.debugLineNum = 92;BA.debugLine="b.TextSize = 16";
Debug.ShouldStop(134217728);
_b.runMethod(true,"setTextSize",BA.numberCast(float.class, 16));
 BA.debugLineNum = 93;BA.debugLine="b.Gravity = Gravity.LEFT + Gravity.CENTER_VERTIC";
Debug.ShouldStop(268435456);
_b.runMethod(true,"setGravity",RemoteObject.solve(new RemoteObject[] {main.mostCurrent.__c.getField(false,"Gravity").getField(true,"LEFT"),main.mostCurrent.__c.getField(false,"Gravity").getField(true,"CENTER_VERTICAL")}, "+",1, 1));
 BA.debugLineNum = 94;BA.debugLine="b.Color = Colors.Transparent";
Debug.ShouldStop(536870912);
_b.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Transparent"));
 BA.debugLineNum = 95;BA.debugLine="b.TextColor = Colors.White";
Debug.ShouldStop(1073741824);
_b.runMethod(true,"setTextColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 BA.debugLineNum = 96;BA.debugLine="pnlmenu.AddView(b, 10dip, 0, 240dip, 50dip)";
Debug.ShouldStop(-2147483648);
main.mostCurrent._pnlmenu.runVoidMethod ("AddView",(Object)((_b.getObject())),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))),(Object)(BA.numberCast(int.class, 0)),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 240)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 50)))));
 }
}Debug.locals.put("b", _b);
;
 BA.debugLineNum = 98;BA.debugLine="btnHome.Top = 120dip";
Debug.ShouldStop(2);
_btnhome.runMethod(true,"setTop",main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 120))));
 BA.debugLineNum = 99;BA.debugLine="btnTable.Top = 180dip";
Debug.ShouldStop(4);
_btntable.runMethod(true,"setTop",main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 180))));
 BA.debugLineNum = 100;BA.debugLine="btnPage1.Top = 240dip";
Debug.ShouldStop(8);
_btnpage1.runMethod(true,"setTop",main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 240))));
 BA.debugLineNum = 101;BA.debugLine="btnPage2.Top = 300dip";
Debug.ShouldStop(16);
_btnpage2.runMethod(true,"setTop",main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 300))));
 BA.debugLineNum = 102;BA.debugLine="End Sub";
Debug.ShouldStop(32);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _globals() throws Exception{
 //BA.debugLineNum = 22;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 24;BA.debugLine="Private SV As ScrollView";
main.mostCurrent._sv = RemoteObject.createNew ("anywheresoftware.b4a.objects.ScrollViewWrapper");
 //BA.debugLineNum = 25;BA.debugLine="Type RowCol (Row As Int, Col As Int)";
;
 //BA.debugLineNum = 26;BA.debugLine="Private Table As Panel";
main.mostCurrent._table = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 28;BA.debugLine="Dim List1 As List";
main.mostCurrent._list1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
 //BA.debugLineNum = 30;BA.debugLine="Dim NumberOfColumns, RowHeight, ColumnWidth, Alig";
main._numberofcolumns = RemoteObject.createImmutable(0);
main._rowheight = RemoteObject.createImmutable(0);
main._columnwidth = RemoteObject.createImmutable(0);
main._alignment = RemoteObject.createImmutable(0);
 //BA.debugLineNum = 31;BA.debugLine="Dim HeaderColor, FontColor, HeaderFontColor As In";
main._headercolor = RemoteObject.createImmutable(0);
main._fontcolor = RemoteObject.createImmutable(0);
main._headerfontcolor = RemoteObject.createImmutable(0);
 //BA.debugLineNum = 32;BA.debugLine="Dim FontSize As Float";
main._fontsize = RemoteObject.createImmutable(0f);
 //BA.debugLineNum = 34;BA.debugLine="Private Drawer As B4XDrawer";
main.mostCurrent._drawer = RemoteObject.createNew ("b4a.example.b4xdrawer");
 //BA.debugLineNum = 35;BA.debugLine="Private pnlmain As Panel";
main.mostCurrent._pnlmain = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 36;BA.debugLine="Private pnlmenu As Panel";
main.mostCurrent._pnlmenu = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 37;BA.debugLine="Private Panel36 As Panel";
main.mostCurrent._panel36 = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 38;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _header(RemoteObject _dir,RemoteObject _filename) throws Exception{
try {
		Debug.PushSubsStack("header (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,157);
if (RapidSub.canDelegate("header")) { return b4a.example.main.remoteMe.runUserSub(false, "main","header", _dir, _filename);}
RemoteObject _headers = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
int _i = 0;
RemoteObject _l = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
Debug.locals.put("Dir", _dir);
Debug.locals.put("Filename", _filename);
 BA.debugLineNum = 157;BA.debugLine="Sub header(Dir As String, Filename As String)";
Debug.ShouldStop(268435456);
 BA.debugLineNum = 158;BA.debugLine="Dim headers As List";
Debug.ShouldStop(536870912);
_headers = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");Debug.locals.put("headers", _headers);
 BA.debugLineNum = 159;BA.debugLine="headers.Initialize ' FIX 1: Initialize the lis";
Debug.ShouldStop(1073741824);
_headers.runVoidMethod ("Initialize");
 BA.debugLineNum = 161;BA.debugLine="List1 = StringUtils1.LoadCSV2(Dir, Filename, \"";
Debug.ShouldStop(1);
main.mostCurrent._list1 = main._stringutils1.runMethod(false,"LoadCSV2",(Object)(_dir),(Object)(_filename),(Object)(BA.ObjectToChar(",")),(Object)(_headers));
 BA.debugLineNum = 162;BA.debugLine="NumberOfColumns = headers.Size";
Debug.ShouldStop(2);
main._numberofcolumns = _headers.runMethod(true,"getSize");
 BA.debugLineNum = 163;BA.debugLine="ColumnWidth = SV.Width / NumberOfColumns ' upd";
Debug.ShouldStop(4);
main._columnwidth = BA.numberCast(int.class, RemoteObject.solve(new RemoteObject[] {main.mostCurrent._sv.runMethod(true,"getWidth"),main._numberofcolumns}, "/",0, 0));
 BA.debugLineNum = 164;BA.debugLine="For i = 0 To NumberOfColumns - 1";
Debug.ShouldStop(8);
{
final int step6 = 1;
final int limit6 = RemoteObject.solve(new RemoteObject[] {main._numberofcolumns,RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
for (;(step6 > 0 && _i <= limit6) || (step6 < 0 && _i >= limit6) ;_i = ((int)(0 + _i + step6))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 165;BA.debugLine="Dim l As Label";
Debug.ShouldStop(16);
_l = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("l", _l);
 BA.debugLineNum = 166;BA.debugLine="l.Initialize(\"header\")";
Debug.ShouldStop(32);
_l.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("header")));
 BA.debugLineNum = 167;BA.debugLine="l.Text = headers.Get(i) ' Read directly fr";
Debug.ShouldStop(64);
_l.runMethod(true,"setText",BA.ObjectToCharSequence(_headers.runMethod(false,"Get",(Object)(BA.numberCast(int.class, _i)))));
 BA.debugLineNum = 168;BA.debugLine="l.Gravity = Gravity.LEFT";
Debug.ShouldStop(128);
_l.runMethod(true,"setGravity",main.mostCurrent.__c.getField(false,"Gravity").getField(true,"LEFT"));
 BA.debugLineNum = 169;BA.debugLine="l.TextSize = FontSize";
Debug.ShouldStop(256);
_l.runMethod(true,"setTextSize",main._fontsize);
 BA.debugLineNum = 170;BA.debugLine="l.Color = HeaderColor";
Debug.ShouldStop(512);
_l.runVoidMethod ("setColor",main._headercolor);
 BA.debugLineNum = 171;BA.debugLine="l.TextColor = HeaderFontColor";
Debug.ShouldStop(1024);
_l.runMethod(true,"setTextColor",main._headerfontcolor);
 BA.debugLineNum = 172;BA.debugLine="l.Padding = Array As Int (10dip, 5dip, 0dip, 5di";
Debug.ShouldStop(2048);
_l.runMethod(false,"setPadding",RemoteObject.createNewArray("int",new int[] {4},new Object[] {main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10))),main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 5))),main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 0))),main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 5)))}));
 BA.debugLineNum = 173;BA.debugLine="l.Tag = i";
Debug.ShouldStop(4096);
_l.runMethod(false,"setTag",RemoteObject.createImmutable((_i)));
 BA.debugLineNum = 174;BA.debugLine="Table.AddView(l, ColumnWidth * i, 0, ColumnWidth";
Debug.ShouldStop(8192);
main.mostCurrent._table.runVoidMethod ("AddView",(Object)((_l.getObject())),(Object)(RemoteObject.solve(new RemoteObject[] {main._columnwidth,RemoteObject.createImmutable(_i)}, "*",0, 1)),(Object)(BA.numberCast(int.class, 0)),(Object)(main._columnwidth),(Object)(main._rowheight));
 }
}Debug.locals.put("i", _i);
;
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
public static RemoteObject  _initdrawer() throws Exception{
try {
		Debug.PushSubsStack("initdrawer (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,68);
if (RapidSub.canDelegate("initdrawer")) { return b4a.example.main.remoteMe.runUserSub(false, "main","initdrawer");}
 BA.debugLineNum = 68;BA.debugLine="Sub initdrawer";
Debug.ShouldStop(8);
 BA.debugLineNum = 69;BA.debugLine="Drawer.Initialize(Me, \"Drawer\", Activity, 260dip)";
Debug.ShouldStop(16);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_initialize" /*RemoteObject*/ ,main.mostCurrent.activityBA,(Object)(main.getObject()),(Object)(BA.ObjectToString("Drawer")),RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), main.mostCurrent._activity.getObject()),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 260)))));
 BA.debugLineNum = 70;BA.debugLine="Drawer.CenterPanel.BringToFront";
Debug.ShouldStop(32);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_getcenterpanel" /*RemoteObject*/ ).runVoidMethod ("BringToFront");
 BA.debugLineNum = 71;BA.debugLine="Drawer.LeftPanel.BringToFront";
Debug.ShouldStop(64);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_getleftpanel" /*RemoteObject*/ ).runVoidMethod ("BringToFront");
 BA.debugLineNum = 73;BA.debugLine="pnlmain = Drawer.CenterPanel";
Debug.ShouldStop(256);
main.mostCurrent._pnlmain = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.PanelWrapper"), main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_getcenterpanel" /*RemoteObject*/ ).getObject());
 BA.debugLineNum = 74;BA.debugLine="pnlmenu = Drawer.LeftPanel";
Debug.ShouldStop(512);
main.mostCurrent._pnlmenu = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.PanelWrapper"), main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_getleftpanel" /*RemoteObject*/ ).getObject());
 BA.debugLineNum = 76;BA.debugLine="SetGradient(pnlmenu, Colors.rgb(175, 71, 210), Co";
Debug.ShouldStop(2048);
_setgradient(main.mostCurrent._pnlmenu,main.mostCurrent.__c.getField(false,"Colors").runMethod(true,"RGB",(Object)(BA.numberCast(int.class, 175)),(Object)(BA.numberCast(int.class, 71)),(Object)(BA.numberCast(int.class, 210))),main.mostCurrent.__c.getField(false,"Colors").runMethod(true,"RGB",(Object)(BA.numberCast(int.class, 38)),(Object)(BA.numberCast(int.class, 53)),(Object)(BA.numberCast(int.class, 93))));
 BA.debugLineNum = 77;BA.debugLine="CreateMenu";
Debug.ShouldStop(4096);
_createmenu();
 BA.debugLineNum = 78;BA.debugLine="End Sub";
Debug.ShouldStop(8192);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _loadtable(RemoteObject _dir,RemoteObject _filename) throws Exception{
try {
		Debug.PushSubsStack("loadtable (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,148);
if (RapidSub.canDelegate("loadtable")) { return b4a.example.main.remoteMe.runUserSub(false, "main","loadtable", _dir, _filename);}
int _i = 0;
RemoteObject _row = null;
Debug.locals.put("Dir", _dir);
Debug.locals.put("Filename", _filename);
 BA.debugLineNum = 148;BA.debugLine="Sub loadtable(Dir As String, Filename As String)";
Debug.ShouldStop(524288);
 BA.debugLineNum = 149;BA.debugLine="header(Dir, Filename)";
Debug.ShouldStop(1048576);
_header(_dir,_filename);
 BA.debugLineNum = 150;BA.debugLine="For i = 0 To List1.Size - 1";
Debug.ShouldStop(2097152);
{
final int step2 = 1;
final int limit2 = RemoteObject.solve(new RemoteObject[] {main.mostCurrent._list1.runMethod(true,"getSize"),RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
for (;(step2 > 0 && _i <= limit2) || (step2 < 0 && _i >= limit2) ;_i = ((int)(0 + _i + step2))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 151;BA.debugLine="Dim row() As String";
Debug.ShouldStop(4194304);
_row = RemoteObject.createNewArray ("String", new int[] {0}, new Object[]{});Debug.locals.put("row", _row);
 BA.debugLineNum = 152;BA.debugLine="row = List1.Get(i)";
Debug.ShouldStop(8388608);
_row = (main.mostCurrent._list1.runMethod(false,"Get",(Object)(BA.numberCast(int.class, _i))));Debug.locals.put("row", _row);
 BA.debugLineNum = 153;BA.debugLine="AddRow(row)";
Debug.ShouldStop(16777216);
_addrow(_row);
 }
}Debug.locals.put("i", _i);
;
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
public static RemoteObject  _numberofrows() throws Exception{
try {
		Debug.PushSubsStack("NumberOfRows (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,203);
if (RapidSub.canDelegate("numberofrows")) { return b4a.example.main.remoteMe.runUserSub(false, "main","numberofrows");}
 BA.debugLineNum = 203;BA.debugLine="Sub NumberOfRows As Int";
Debug.ShouldStop(1024);
 BA.debugLineNum = 204;BA.debugLine="Return Table.NumberOfViews / NumberOfColumns";
Debug.ShouldStop(2048);
if (true) return BA.numberCast(int.class, RemoteObject.solve(new RemoteObject[] {main.mostCurrent._table.runMethod(true,"getNumberOfViews"),main._numberofcolumns}, "/",0, 0));
 BA.debugLineNum = 205;BA.debugLine="End Sub";
Debug.ShouldStop(4096);
return RemoteObject.createImmutable(0);
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
 //BA.debugLineNum = 19;BA.debugLine="Dim StringUtils1 As StringUtils";
main._stringutils1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.StringUtils");
 //BA.debugLineNum = 20;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _setgradient(RemoteObject _panel,RemoteObject _color1,RemoteObject _color2) throws Exception{
try {
		Debug.PushSubsStack("SetGradient (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,208);
if (RapidSub.canDelegate("setgradient")) { return b4a.example.main.remoteMe.runUserSub(false, "main","setgradient", _panel, _color1, _color2);}
RemoteObject _gd = RemoteObject.declareNull("anywheresoftware.b4a.objects.drawable.GradientDrawable");
Debug.locals.put("Panel", _panel);
Debug.locals.put("Color1", _color1);
Debug.locals.put("Color2", _color2);
 BA.debugLineNum = 208;BA.debugLine="Sub SetGradient(Panel As Panel, Color1 As Int, Col";
Debug.ShouldStop(32768);
 BA.debugLineNum = 209;BA.debugLine="Dim gd As GradientDrawable";
Debug.ShouldStop(65536);
_gd = RemoteObject.createNew ("anywheresoftware.b4a.objects.drawable.GradientDrawable");Debug.locals.put("gd", _gd);
 BA.debugLineNum = 210;BA.debugLine="gd.Initialize(\"BR_TL\", Array As Int(Color1, Color";
Debug.ShouldStop(131072);
_gd.runVoidMethod ("Initialize",(Object)(BA.getEnumFromString(BA.getDeviceClass("android.graphics.drawable.GradientDrawable.Orientation"),"BR_TL")),(Object)(RemoteObject.createNewArray("int",new int[] {2},new Object[] {_color1,_color2})));
 BA.debugLineNum = 211;BA.debugLine="Panel.Background = gd";
Debug.ShouldStop(262144);
_panel.runMethod(false,"setBackground",(_gd.getObject()));
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
public static RemoteObject  _showhome() throws Exception{
try {
		Debug.PushSubsStack("ShowHome (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,124);
if (RapidSub.canDelegate("showhome")) { return b4a.example.main.remoteMe.runUserSub(false, "main","showhome");}
 BA.debugLineNum = 124;BA.debugLine="Sub ShowHome";
Debug.ShouldStop(134217728);
 BA.debugLineNum = 125;BA.debugLine="ShowTable";
Debug.ShouldStop(268435456);
_showtable();
 BA.debugLineNum = 126;BA.debugLine="Panel36.Color = 0xFF1AEA00";
Debug.ShouldStop(536870912);
main.mostCurrent._panel36.runVoidMethod ("setColor",BA.numberCast(int.class, ((int)0xff1aea00)));
 BA.debugLineNum = 127;BA.debugLine="Msgboxasync(\"run\",\"run\")";
Debug.ShouldStop(1073741824);
main.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("run")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("run"))),main.processBA);
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
public static RemoteObject  _showpage1() throws Exception{
try {
		Debug.PushSubsStack("ShowPage1 (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,138);
if (RapidSub.canDelegate("showpage1")) { return b4a.example.main.remoteMe.runUserSub(false, "main","showpage1");}
 BA.debugLineNum = 138;BA.debugLine="Sub ShowPage1";
Debug.ShouldStop(512);
 BA.debugLineNum = 139;BA.debugLine="pnlmain.RemoveAllViews";
Debug.ShouldStop(1024);
main.mostCurrent._pnlmain.runVoidMethod ("RemoveAllViews");
 BA.debugLineNum = 140;BA.debugLine="pnlmain.LoadLayout(\"dashboard\")";
Debug.ShouldStop(2048);
main.mostCurrent._pnlmain.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("dashboard")),main.mostCurrent.activityBA);
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
public static RemoteObject  _showpage2() throws Exception{
try {
		Debug.PushSubsStack("ShowPage2 (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,143);
if (RapidSub.canDelegate("showpage2")) { return b4a.example.main.remoteMe.runUserSub(false, "main","showpage2");}
 BA.debugLineNum = 143;BA.debugLine="Sub ShowPage2";
Debug.ShouldStop(16384);
 BA.debugLineNum = 144;BA.debugLine="pnlmain.RemoveAllViews";
Debug.ShouldStop(32768);
main.mostCurrent._pnlmain.runVoidMethod ("RemoveAllViews");
 BA.debugLineNum = 145;BA.debugLine="pnlmain.LoadLayout(\"dashboard\")";
Debug.ShouldStop(65536);
main.mostCurrent._pnlmain.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("dashboard")),main.mostCurrent.activityBA);
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
public static RemoteObject  _showtable() throws Exception{
try {
		Debug.PushSubsStack("ShowTable (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,130);
if (RapidSub.canDelegate("showtable")) { return b4a.example.main.remoteMe.runUserSub(false, "main","showtable");}
 BA.debugLineNum = 130;BA.debugLine="Sub ShowTable";
Debug.ShouldStop(2);
 BA.debugLineNum = 131;BA.debugLine="pnlmain.RemoveAllViews";
Debug.ShouldStop(4);
main.mostCurrent._pnlmain.runVoidMethod ("RemoveAllViews");
 BA.debugLineNum = 132;BA.debugLine="pnlmain.LoadLayout(\"profile\") 'layout contains th";
Debug.ShouldStop(8);
main.mostCurrent._pnlmain.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("profile")),main.mostCurrent.activityBA);
 BA.debugLineNum = 133;BA.debugLine="Table = SV.Panel";
Debug.ShouldStop(16);
main.mostCurrent._table = main.mostCurrent._sv.runMethod(false,"getPanel");
 BA.debugLineNum = 134;BA.debugLine="ColumnWidth = SV.Width / NumberOfColumns";
Debug.ShouldStop(32);
main._columnwidth = BA.numberCast(int.class, RemoteObject.solve(new RemoteObject[] {main.mostCurrent._sv.runMethod(true,"getWidth"),main._numberofcolumns}, "/",0, 0));
 BA.debugLineNum = 135;BA.debugLine="loadtable(File.DirAssets, \"citylist.csv\")";
Debug.ShouldStop(64);
_loadtable(main.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets"),RemoteObject.createImmutable("citylist.csv"));
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
}