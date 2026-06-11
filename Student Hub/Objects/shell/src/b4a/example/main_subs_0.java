package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class main_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,73);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_create", _firsttime);}
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 73;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(256);
 BA.debugLineNum = 74;BA.debugLine="Activity.LoadLayout(\"main\")";
Debug.ShouldStop(512);
main.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("main")),main.mostCurrent.activityBA);
 BA.debugLineNum = 76;BA.debugLine="initdrawer";
Debug.ShouldStop(2048);
_initdrawer();
 BA.debugLineNum = 79;BA.debugLine="HeaderColor = Colors.Transparent";
Debug.ShouldStop(16384);
main._headercolor = main.mostCurrent.__c.getField(false,"Colors").getField(true,"Transparent");
 BA.debugLineNum = 80;BA.debugLine="NumberOfColumns = 6";
Debug.ShouldStop(32768);
main._numberofcolumns = BA.numberCast(int.class, 6);
 BA.debugLineNum = 81;BA.debugLine="RowHeight = 30dip";
Debug.ShouldStop(65536);
main._rowheight = main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 30)));
 BA.debugLineNum = 82;BA.debugLine="FontColor = Colors.Black";
Debug.ShouldStop(131072);
main._fontcolor = main.mostCurrent.__c.getField(false,"Colors").getField(true,"Black");
 BA.debugLineNum = 83;BA.debugLine="HeaderFontColor = Colors.Black";
Debug.ShouldStop(262144);
main._headerfontcolor = main.mostCurrent.__c.getField(false,"Colors").getField(true,"Black");
 BA.debugLineNum = 84;BA.debugLine="FontSize = 14";
Debug.ShouldStop(524288);
main._fontsize = BA.numberCast(float.class, 14);
 BA.debugLineNum = 85;BA.debugLine="Alignment = Gravity.LEFT";
Debug.ShouldStop(1048576);
main._alignment = main.mostCurrent.__c.getField(false,"Gravity").getField(true,"LEFT");
 BA.debugLineNum = 88;BA.debugLine="LoadStudentData";
Debug.ShouldStop(8388608);
_loadstudentdata();
 BA.debugLineNum = 91;BA.debugLine="showgrades";
Debug.ShouldStop(67108864);
_showgrades();
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
public static RemoteObject  _activity_pause(RemoteObject _userclosed) throws Exception{
try {
		Debug.PushSubsStack("Activity_Pause (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,97);
if (RapidSub.canDelegate("activity_pause")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_pause", _userclosed);}
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 97;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(1);
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
public static RemoteObject  _activity_resume() throws Exception{
try {
		Debug.PushSubsStack("Activity_Resume (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,94);
if (RapidSub.canDelegate("activity_resume")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_resume");}
 BA.debugLineNum = 94;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(536870912);
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
public static RemoteObject  _addtablerow(RemoteObject _values,RemoteObject _rowindex) throws Exception{
try {
		Debug.PushSubsStack("AddTableRow (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,749);
if (RapidSub.canDelegate("addtablerow")) { return b4a.example.main.remoteMe.runUserSub(false, "main","addtablerow", _values, _rowindex);}
int _i = 0;
RemoteObject _l = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
RemoteObject _rc = RemoteObject.declareNull("b4a.example.main._rowcol");
Debug.locals.put("Values", _values);
Debug.locals.put("rowIndex", _rowindex);
 BA.debugLineNum = 749;BA.debugLine="Sub AddTableRow(Values() As String, rowIndex As In";
Debug.ShouldStop(4096);
 BA.debugLineNum = 750;BA.debugLine="If Values.Length <> NumberOfColumns Then";
Debug.ShouldStop(8192);
if (RemoteObject.solveBoolean("!",_values.getField(true,"length"),BA.numberCast(double.class, main._numberofcolumns))) { 
 BA.debugLineNum = 751;BA.debugLine="Log(\"Wrong number of values.\")";
Debug.ShouldStop(16384);
main.mostCurrent.__c.runVoidMethod ("LogImpl","517432578",RemoteObject.createImmutable("Wrong number of values."),0);
 BA.debugLineNum = 752;BA.debugLine="Return";
Debug.ShouldStop(32768);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 754;BA.debugLine="For i = 0 To NumberOfColumns - 1";
Debug.ShouldStop(131072);
{
final int step5 = 1;
final int limit5 = RemoteObject.solve(new RemoteObject[] {main._numberofcolumns,RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
for (;(step5 > 0 && _i <= limit5) || (step5 < 0 && _i >= limit5) ;_i = ((int)(0 + _i + step5))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 755;BA.debugLine="Dim l As Label";
Debug.ShouldStop(262144);
_l = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("l", _l);
 BA.debugLineNum = 756;BA.debugLine="l.Initialize(\"cell\")";
Debug.ShouldStop(524288);
_l.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("cell")));
 BA.debugLineNum = 757;BA.debugLine="l.Text = Values(i)";
Debug.ShouldStop(1048576);
_l.runMethod(true,"setText",BA.ObjectToCharSequence(_values.getArrayElement(true,BA.numberCast(int.class, _i))));
 BA.debugLineNum = 758;BA.debugLine="l.Gravity = Alignment";
Debug.ShouldStop(2097152);
_l.runMethod(true,"setGravity",main._alignment);
 BA.debugLineNum = 759;BA.debugLine="l.TextSize = FontSize";
Debug.ShouldStop(4194304);
_l.runMethod(true,"setTextSize",main._fontsize);
 BA.debugLineNum = 760;BA.debugLine="l.TextColor = FontColor";
Debug.ShouldStop(8388608);
_l.runMethod(true,"setTextColor",main._fontcolor);
 BA.debugLineNum = 761;BA.debugLine="l.Padding = Array As Int(10dip, 0, 0, 0)";
Debug.ShouldStop(16777216);
_l.runMethod(false,"setPadding",RemoteObject.createNewArray("int",new int[] {4},new Object[] {main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10))),BA.numberCast(int.class, 0),BA.numberCast(int.class, 0),BA.numberCast(int.class, 0)}));
 BA.debugLineNum = 762;BA.debugLine="Dim rc As RowCol";
Debug.ShouldStop(33554432);
_rc = RemoteObject.createNew ("b4a.example.main._rowcol");Debug.locals.put("rc", _rc);
 BA.debugLineNum = 763;BA.debugLine="rc.Initialize";
Debug.ShouldStop(67108864);
_rc.runVoidMethod ("Initialize");
 BA.debugLineNum = 764;BA.debugLine="rc.Col = i";
Debug.ShouldStop(134217728);
_rc.setField ("Col" /*RemoteObject*/ ,BA.numberCast(int.class, _i));
 BA.debugLineNum = 765;BA.debugLine="rc.Row = rowIndex";
Debug.ShouldStop(268435456);
_rc.setField ("Row" /*RemoteObject*/ ,_rowindex);
 BA.debugLineNum = 766;BA.debugLine="l.Tag = rc";
Debug.ShouldStop(536870912);
_l.runMethod(false,"setTag",(_rc));
 BA.debugLineNum = 767;BA.debugLine="Table.AddView(l, ColumnWidth * i, RowHeight * ro";
Debug.ShouldStop(1073741824);
main.mostCurrent._table.runVoidMethod ("AddView",(Object)((_l.getObject())),(Object)(RemoteObject.solve(new RemoteObject[] {main._columnwidth,RemoteObject.createImmutable(_i)}, "*",0, 1)),(Object)(RemoteObject.solve(new RemoteObject[] {main._rowheight,_rowindex}, "*",0, 1)),(Object)(main._columnwidth),(Object)(main._rowheight));
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 769;BA.debugLine="End Sub";
Debug.ShouldStop(1);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _btnaddnew_click() throws Exception{
try {
		Debug.PushSubsStack("btnAddNew_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,563);
if (RapidSub.canDelegate("btnaddnew_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","btnaddnew_click");}
 BA.debugLineNum = 563;BA.debugLine="Sub btnAddNew_Click";
Debug.ShouldStop(262144);
 BA.debugLineNum = 564;BA.debugLine="EditingRowIndex = -1";
Debug.ShouldStop(524288);
main._editingrowindex = BA.numberCast(int.class, -(double) (0 + 1));
 BA.debugLineNum = 565;BA.debugLine="ShowCREATForm(\"\", \"\", \"\", \"\", False)";
Debug.ShouldStop(1048576);
_showcreatform(BA.ObjectToString(""),BA.ObjectToString(""),BA.ObjectToString(""),BA.ObjectToString(""),main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 566;BA.debugLine="End Sub";
Debug.ShouldStop(2097152);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _btndashboard_click() throws Exception{
try {
		Debug.PushSubsStack("btnDashBoard_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,144);
if (RapidSub.canDelegate("btndashboard_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","btndashboard_click");}
 BA.debugLineNum = 144;BA.debugLine="Sub btnDashBoard_Click";
Debug.ShouldStop(32768);
 BA.debugLineNum = 145;BA.debugLine="Drawer.LeftOpen = False";
Debug.ShouldStop(65536);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_setleftopen" /*RemoteObject*/ ,main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 146;BA.debugLine="showdashboard";
Debug.ShouldStop(131072);
_showdashboard();
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
public static RemoteObject  _btndeleterow_click() throws Exception{
try {
		Debug.PushSubsStack("btnDeleteRow_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,576);
if (RapidSub.canDelegate("btndeleterow_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","btndeleterow_click");}
RemoteObject _btn = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
RemoteObject _deleteindex = RemoteObject.createImmutable(0);
RemoteObject _row = null;
RemoteObject _msg = RemoteObject.createImmutable("");
RemoteObject _result = RemoteObject.createImmutable(0);
 BA.debugLineNum = 576;BA.debugLine="Sub btnDeleteRow_Click";
Debug.ShouldStop(-2147483648);
 BA.debugLineNum = 577;BA.debugLine="Dim btn As Button = Sender";
Debug.ShouldStop(1);
_btn = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
_btn = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.ButtonWrapper"), main.mostCurrent.__c.runMethod(false,"Sender",main.mostCurrent.activityBA));Debug.locals.put("btn", _btn);Debug.locals.put("btn", _btn);
 BA.debugLineNum = 578;BA.debugLine="Dim deleteIndex As Int = btn.Tag";
Debug.ShouldStop(2);
_deleteindex = BA.numberCast(int.class, _btn.runMethod(false,"getTag"));Debug.locals.put("deleteIndex", _deleteindex);Debug.locals.put("deleteIndex", _deleteindex);
 BA.debugLineNum = 579;BA.debugLine="Dim row() As String = StudentList.Get(deleteIndex";
Debug.ShouldStop(4);
_row = (main.mostCurrent._studentlist.runMethod(false,"Get",(Object)(_deleteindex)));Debug.locals.put("row", _row);Debug.locals.put("row", _row);
 BA.debugLineNum = 580;BA.debugLine="Dim msg As String = \"Delete record for \" & row(CO";
Debug.ShouldStop(8);
_msg = RemoteObject.concat(RemoteObject.createImmutable("Delete record for "),_row.getArrayElement(true,main._col_name),RemoteObject.createImmutable(" ("),_row.getArrayElement(true,main._col_subject),RemoteObject.createImmutable(")?"));Debug.locals.put("msg", _msg);Debug.locals.put("msg", _msg);
 BA.debugLineNum = 582;BA.debugLine="Dim result As Int = Msgbox2(msg, \"Confirm Delete\"";
Debug.ShouldStop(32);
_result = main.mostCurrent.__c.runMethodAndSync(true,"Msgbox2",(Object)(BA.ObjectToCharSequence(_msg)),(Object)(BA.ObjectToCharSequence("Confirm Delete")),(Object)(BA.ObjectToString("Delete")),(Object)(BA.ObjectToString("")),(Object)(BA.ObjectToString("Cancel")),(Object)((main.mostCurrent.__c.getField(false,"Null"))),main.mostCurrent.activityBA);Debug.locals.put("result", _result);Debug.locals.put("result", _result);
 BA.debugLineNum = 583;BA.debugLine="If result = DialogResponse.POSITIVE Then";
Debug.ShouldStop(64);
if (RemoteObject.solveBoolean("=",_result,BA.numberCast(double.class, main.mostCurrent.__c.getField(false,"DialogResponse").getField(true,"POSITIVE")))) { 
 BA.debugLineNum = 584;BA.debugLine="StudentList.RemoveAt(deleteIndex)";
Debug.ShouldStop(128);
main.mostCurrent._studentlist.runVoidMethod ("RemoveAt",(Object)(_deleteindex));
 BA.debugLineNum = 585;BA.debugLine="SaveStudentData";
Debug.ShouldStop(256);
_savestudentdata();
 BA.debugLineNum = 586;BA.debugLine="showedit ' refresh list";
Debug.ShouldStop(512);
_showedit();
 };
 BA.debugLineNum = 588;BA.debugLine="End Sub";
Debug.ShouldStop(2048);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _btnedit_click() throws Exception{
try {
		Debug.PushSubsStack("btnedit_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,154);
if (RapidSub.canDelegate("btnedit_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","btnedit_click");}
 BA.debugLineNum = 154;BA.debugLine="Sub btnedit_Click";
Debug.ShouldStop(33554432);
 BA.debugLineNum = 155;BA.debugLine="Drawer.LeftOpen = False";
Debug.ShouldStop(67108864);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_setleftopen" /*RemoteObject*/ ,main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 156;BA.debugLine="showedit";
Debug.ShouldStop(134217728);
_showedit();
 BA.debugLineNum = 157;BA.debugLine="End Sub";
Debug.ShouldStop(268435456);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _btneditrow_click() throws Exception{
try {
		Debug.PushSubsStack("btnEditRow_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,568);
if (RapidSub.canDelegate("btneditrow_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","btneditrow_click");}
RemoteObject _btn = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
RemoteObject _row = null;
RemoteObject _ispresent = RemoteObject.createImmutable(false);
 BA.debugLineNum = 568;BA.debugLine="Sub btnEditRow_Click";
Debug.ShouldStop(8388608);
 BA.debugLineNum = 569;BA.debugLine="Dim btn As Button = Sender";
Debug.ShouldStop(16777216);
_btn = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
_btn = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.ButtonWrapper"), main.mostCurrent.__c.runMethod(false,"Sender",main.mostCurrent.activityBA));Debug.locals.put("btn", _btn);Debug.locals.put("btn", _btn);
 BA.debugLineNum = 570;BA.debugLine="EditingRowIndex = btn.Tag";
Debug.ShouldStop(33554432);
main._editingrowindex = BA.numberCast(int.class, _btn.runMethod(false,"getTag"));
 BA.debugLineNum = 571;BA.debugLine="Dim row() As String = StudentList.Get(EditingRowI";
Debug.ShouldStop(67108864);
_row = (main.mostCurrent._studentlist.runMethod(false,"Get",(Object)(main._editingrowindex)));Debug.locals.put("row", _row);Debug.locals.put("row", _row);
 BA.debugLineNum = 572;BA.debugLine="Dim isPresent As Boolean = (row(COL_ATTENDANCE).T";
Debug.ShouldStop(134217728);
_ispresent = BA.ObjectToBoolean((RemoteObject.solveBoolean("=",_row.getArrayElement(true,main._col_attendance).runMethod(true,"toLowerCase"),RemoteObject.createImmutable("present"))));Debug.locals.put("isPresent", _ispresent);Debug.locals.put("isPresent", _ispresent);
 BA.debugLineNum = 573;BA.debugLine="ShowCREATForm(row(COL_NAME), row(COL_ID), row(COL";
Debug.ShouldStop(268435456);
_showcreatform(_row.getArrayElement(true,main._col_name),_row.getArrayElement(true,main._col_id),_row.getArrayElement(true,main._col_activity),_row.getArrayElement(true,main._col_rate),_ispresent);
 BA.debugLineNum = 574;BA.debugLine="End Sub";
Debug.ShouldStop(536870912);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _btngrades_click() throws Exception{
try {
		Debug.PushSubsStack("btnGrades_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,149);
if (RapidSub.canDelegate("btngrades_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","btngrades_click");}
 BA.debugLineNum = 149;BA.debugLine="Sub btnGrades_Click";
Debug.ShouldStop(1048576);
 BA.debugLineNum = 150;BA.debugLine="Drawer.LeftOpen = False";
Debug.ShouldStop(2097152);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_setleftopen" /*RemoteObject*/ ,main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 151;BA.debugLine="showgrades";
Debug.ShouldStop(4194304);
_showgrades();
 BA.debugLineNum = 152;BA.debugLine="End Sub";
Debug.ShouldStop(8388608);
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
		Debug.PushSubsStack("btnMenu_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,104);
if (RapidSub.canDelegate("btnmenu_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","btnmenu_click");}
 BA.debugLineNum = 104;BA.debugLine="Sub btnMenu_Click";
Debug.ShouldStop(128);
 BA.debugLineNum = 105;BA.debugLine="Drawer.LeftOpen = Not(Drawer.LeftOpen)";
Debug.ShouldStop(256);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_setleftopen" /*RemoteObject*/ ,main.mostCurrent.__c.runMethod(true,"Not",(Object)(main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_getleftopen" /*RemoteObject*/ ))));
 BA.debugLineNum = 106;BA.debugLine="End Sub";
Debug.ShouldStop(512);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _btnprofile_click() throws Exception{
try {
		Debug.PushSubsStack("btnProfile_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,159);
if (RapidSub.canDelegate("btnprofile_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","btnprofile_click");}
 BA.debugLineNum = 159;BA.debugLine="Sub btnProfile_Click";
Debug.ShouldStop(1073741824);
 BA.debugLineNum = 160;BA.debugLine="Drawer.LeftOpen = False";
Debug.ShouldStop(-2147483648);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_setleftopen" /*RemoteObject*/ ,main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 161;BA.debugLine="showprofile";
Debug.ShouldStop(1);
_showprofile();
 BA.debugLineNum = 162;BA.debugLine="End Sub";
Debug.ShouldStop(2);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _btnsave_click() throws Exception{
try {
		Debug.PushSubsStack("btnSave_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,633);
if (RapidSub.canDelegate("btnsave_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","btnsave_click");}
RemoteObject _sname = RemoteObject.createImmutable("");
RemoteObject _sid = RemoteObject.createImmutable("");
RemoteObject _sactivity = RemoteObject.createImmutable("");
RemoteObject _srate = RemoteObject.createImmutable("");
RemoteObject _sattendance = RemoteObject.createImmutable("");
RemoteObject _ssubject = RemoteObject.createImmutable("");
RemoteObject _existingrow = null;
RemoteObject _newrow = null;
 BA.debugLineNum = 633;BA.debugLine="Sub btnSave_Click";
Debug.ShouldStop(16777216);
 BA.debugLineNum = 635;BA.debugLine="Dim sName As String = EditText.Text.Trim";
Debug.ShouldStop(67108864);
_sname = main.mostCurrent._edittext.runMethod(true,"getText").runMethod(true,"trim");Debug.locals.put("sName", _sname);Debug.locals.put("sName", _sname);
 BA.debugLineNum = 636;BA.debugLine="Dim sID As String = EditText3.Text.Trim";
Debug.ShouldStop(134217728);
_sid = main.mostCurrent._edittext3.runMethod(true,"getText").runMethod(true,"trim");Debug.locals.put("sID", _sid);Debug.locals.put("sID", _sid);
 BA.debugLineNum = 637;BA.debugLine="Dim sActivity As String = EditText1.Text.Trim";
Debug.ShouldStop(268435456);
_sactivity = main.mostCurrent._edittext1.runMethod(true,"getText").runMethod(true,"trim");Debug.locals.put("sActivity", _sactivity);Debug.locals.put("sActivity", _sactivity);
 BA.debugLineNum = 638;BA.debugLine="Dim sRate As String = EditText2.Text.Trim";
Debug.ShouldStop(536870912);
_srate = main.mostCurrent._edittext2.runMethod(true,"getText").runMethod(true,"trim");Debug.locals.put("sRate", _srate);Debug.locals.put("sRate", _srate);
 BA.debugLineNum = 640;BA.debugLine="If sName.Length = 0 Then";
Debug.ShouldStop(-2147483648);
if (RemoteObject.solveBoolean("=",_sname.runMethod(true,"length"),BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 641;BA.debugLine="Msgbox(\"Please enter a Student Name.\", \"Validati";
Debug.ShouldStop(1);
main.mostCurrent.__c.runVoidMethodAndSync ("Msgbox",(Object)(BA.ObjectToCharSequence("Please enter a Student Name.")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Validation Error"))),main.mostCurrent.activityBA);
 BA.debugLineNum = 642;BA.debugLine="Return";
Debug.ShouldStop(2);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 644;BA.debugLine="If sID.Length = 0 Then";
Debug.ShouldStop(8);
if (RemoteObject.solveBoolean("=",_sid.runMethod(true,"length"),BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 645;BA.debugLine="Msgbox(\"Please enter a Student ID.\", \"Validation";
Debug.ShouldStop(16);
main.mostCurrent.__c.runVoidMethodAndSync ("Msgbox",(Object)(BA.ObjectToCharSequence("Please enter a Student ID.")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Validation Error"))),main.mostCurrent.activityBA);
 BA.debugLineNum = 646;BA.debugLine="Return";
Debug.ShouldStop(32);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 648;BA.debugLine="If sActivity.Length = 0 Then";
Debug.ShouldStop(128);
if (RemoteObject.solveBoolean("=",_sactivity.runMethod(true,"length"),BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 649;BA.debugLine="Msgbox(\"Please enter an Activity Title.\", \"Valid";
Debug.ShouldStop(256);
main.mostCurrent.__c.runVoidMethodAndSync ("Msgbox",(Object)(BA.ObjectToCharSequence("Please enter an Activity Title.")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Validation Error"))),main.mostCurrent.activityBA);
 BA.debugLineNum = 650;BA.debugLine="Return";
Debug.ShouldStop(512);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 652;BA.debugLine="If sRate.Length = 0 Or sRate.Contains(\"/\") = Fals";
Debug.ShouldStop(2048);
if (RemoteObject.solveBoolean("=",_srate.runMethod(true,"length"),BA.numberCast(double.class, 0)) || RemoteObject.solveBoolean("=",_srate.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("/"))),main.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 653;BA.debugLine="Msgbox(\"Please enter a valid Result (e.g. 11/20)";
Debug.ShouldStop(4096);
main.mostCurrent.__c.runVoidMethodAndSync ("Msgbox",(Object)(BA.ObjectToCharSequence("Please enter a valid Result (e.g. 11/20).")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Validation Error"))),main.mostCurrent.activityBA);
 BA.debugLineNum = 654;BA.debugLine="Return";
Debug.ShouldStop(8192);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 658;BA.debugLine="Dim sAttendance As String";
Debug.ShouldStop(131072);
_sattendance = RemoteObject.createImmutable("");Debug.locals.put("sAttendance", _sattendance);
 BA.debugLineNum = 659;BA.debugLine="If CheckBox1.Checked Then";
Debug.ShouldStop(262144);
if (main.mostCurrent._checkbox1.runMethod(true,"getChecked").<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 660;BA.debugLine="sAttendance = \"present\"";
Debug.ShouldStop(524288);
_sattendance = BA.ObjectToString("present");Debug.locals.put("sAttendance", _sattendance);
 }else {
 BA.debugLineNum = 662;BA.debugLine="sAttendance = \"absent\"";
Debug.ShouldStop(2097152);
_sattendance = BA.ObjectToString("absent");Debug.locals.put("sAttendance", _sattendance);
 };
 BA.debugLineNum = 666;BA.debugLine="Dim sSubject As String";
Debug.ShouldStop(33554432);
_ssubject = RemoteObject.createImmutable("");Debug.locals.put("sSubject", _ssubject);
 BA.debugLineNum = 667;BA.debugLine="If EditingRowIndex >= 0 Then";
Debug.ShouldStop(67108864);
if (RemoteObject.solveBoolean("g",main._editingrowindex,BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 668;BA.debugLine="Dim existingRow() As String = StudentList.Get(Ed";
Debug.ShouldStop(134217728);
_existingrow = (main.mostCurrent._studentlist.runMethod(false,"Get",(Object)(main._editingrowindex)));Debug.locals.put("existingRow", _existingrow);Debug.locals.put("existingRow", _existingrow);
 BA.debugLineNum = 669;BA.debugLine="sSubject = existingRow(COL_SUBJECT)";
Debug.ShouldStop(268435456);
_ssubject = _existingrow.getArrayElement(true,main._col_subject);Debug.locals.put("sSubject", _ssubject);
 }else {
 BA.debugLineNum = 672;BA.debugLine="sSubject = \"general\"";
Debug.ShouldStop(-2147483648);
_ssubject = BA.ObjectToString("general");Debug.locals.put("sSubject", _ssubject);
 };
 BA.debugLineNum = 676;BA.debugLine="Dim newRow(6) As String";
Debug.ShouldStop(8);
_newrow = RemoteObject.createNewArray ("String", new int[] {6}, new Object[]{});Debug.locals.put("newRow", _newrow);
 BA.debugLineNum = 677;BA.debugLine="newRow(COL_NAME) = sName";
Debug.ShouldStop(16);
_newrow.setArrayElement (_sname,main._col_name);
 BA.debugLineNum = 678;BA.debugLine="newRow(COL_ID) = sID";
Debug.ShouldStop(32);
_newrow.setArrayElement (_sid,main._col_id);
 BA.debugLineNum = 679;BA.debugLine="newRow(COL_SUBJECT) = sSubject";
Debug.ShouldStop(64);
_newrow.setArrayElement (_ssubject,main._col_subject);
 BA.debugLineNum = 680;BA.debugLine="newRow(COL_ACTIVITY) = sActivity";
Debug.ShouldStop(128);
_newrow.setArrayElement (_sactivity,main._col_activity);
 BA.debugLineNum = 681;BA.debugLine="newRow(COL_ATTENDANCE) = sAttendance";
Debug.ShouldStop(256);
_newrow.setArrayElement (_sattendance,main._col_attendance);
 BA.debugLineNum = 682;BA.debugLine="newRow(COL_RATE) = sRate";
Debug.ShouldStop(512);
_newrow.setArrayElement (_srate,main._col_rate);
 BA.debugLineNum = 684;BA.debugLine="If EditingRowIndex >= 0 Then";
Debug.ShouldStop(2048);
if (RemoteObject.solveBoolean("g",main._editingrowindex,BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 686;BA.debugLine="StudentList.Set(EditingRowIndex, newRow)";
Debug.ShouldStop(8192);
main.mostCurrent._studentlist.runVoidMethod ("Set",(Object)(main._editingrowindex),(Object)((_newrow)));
 BA.debugLineNum = 687;BA.debugLine="Log(\"Updated row at index \" & EditingRowIndex)";
Debug.ShouldStop(16384);
main.mostCurrent.__c.runVoidMethod ("LogImpl","517367094",RemoteObject.concat(RemoteObject.createImmutable("Updated row at index "),main._editingrowindex),0);
 }else {
 BA.debugLineNum = 690;BA.debugLine="StudentList.Add(newRow)";
Debug.ShouldStop(131072);
main.mostCurrent._studentlist.runVoidMethod ("Add",(Object)((_newrow)));
 BA.debugLineNum = 691;BA.debugLine="Log(\"Added new row, total: \" & StudentList.Size)";
Debug.ShouldStop(262144);
main.mostCurrent.__c.runVoidMethod ("LogImpl","517367098",RemoteObject.concat(RemoteObject.createImmutable("Added new row, total: "),main.mostCurrent._studentlist.runMethod(true,"getSize")),0);
 };
 BA.debugLineNum = 695;BA.debugLine="SaveStudentData";
Debug.ShouldStop(4194304);
_savestudentdata();
 BA.debugLineNum = 696;BA.debugLine="EditingRowIndex = -1";
Debug.ShouldStop(8388608);
main._editingrowindex = BA.numberCast(int.class, -(double) (0 + 1));
 BA.debugLineNum = 697;BA.debugLine="showedit";
Debug.ShouldStop(16777216);
_showedit();
 BA.debugLineNum = 698;BA.debugLine="End Sub";
Debug.ShouldStop(33554432);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _buildeditlist() throws Exception{
try {
		Debug.PushSubsStack("BuildEditList (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,479);
if (RapidSub.canDelegate("buildeditlist")) { return b4a.example.main.remoteMe.runUserSub(false, "main","buildeditlist");}
RemoteObject _sv2 = RemoteObject.declareNull("anywheresoftware.b4a.objects.ScrollViewWrapper");
RemoteObject _listpanel = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
RemoteObject _rowh = RemoteObject.createImmutable(0);
RemoteObject _spacing = RemoteObject.createImmutable(0);
RemoteObject _currenty = RemoteObject.createImmutable(0);
RemoteObject _btnadd = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
int _i = 0;
RemoteObject _row = null;
RemoteObject _pnlrow = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
RemoteObject _cd = RemoteObject.declareNull("anywheresoftware.b4a.objects.drawable.ColorDrawable");
RemoteObject _lblinfo = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
RemoteObject _lbldetail = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
RemoteObject _btnedit = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
RemoteObject _btndel = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
 BA.debugLineNum = 479;BA.debugLine="Sub BuildEditList";
Debug.ShouldStop(1073741824);
 BA.debugLineNum = 483;BA.debugLine="Dim sv2 As ScrollView";
Debug.ShouldStop(4);
_sv2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ScrollViewWrapper");Debug.locals.put("sv2", _sv2);
 BA.debugLineNum = 484;BA.debugLine="sv2.Initialize(0)";
Debug.ShouldStop(8);
_sv2.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(BA.numberCast(int.class, 0)));
 BA.debugLineNum = 485;BA.debugLine="pnlmain.AddView(sv2, 0, 50dip, pnlmain.Width, pnl";
Debug.ShouldStop(16);
main.mostCurrent._pnlmain.runVoidMethod ("AddView",(Object)((_sv2.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 50)))),(Object)(main.mostCurrent._pnlmain.runMethod(true,"getWidth")),(Object)(RemoteObject.solve(new RemoteObject[] {main.mostCurrent._pnlmain.runMethod(true,"getHeight"),main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 110)))}, "-",1, 1)));
 BA.debugLineNum = 487;BA.debugLine="Dim listPanel As Panel = sv2.Panel";
Debug.ShouldStop(64);
_listpanel = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
_listpanel = _sv2.runMethod(false,"getPanel");Debug.locals.put("listPanel", _listpanel);Debug.locals.put("listPanel", _listpanel);
 BA.debugLineNum = 488;BA.debugLine="listPanel.Width = sv2.Width";
Debug.ShouldStop(128);
_listpanel.runMethod(true,"setWidth",_sv2.runMethod(true,"getWidth"));
 BA.debugLineNum = 490;BA.debugLine="Dim rowH As Int = 80dip";
Debug.ShouldStop(512);
_rowh = main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 80)));Debug.locals.put("rowH", _rowh);Debug.locals.put("rowH", _rowh);
 BA.debugLineNum = 491;BA.debugLine="Dim spacing As Int = 5dip";
Debug.ShouldStop(1024);
_spacing = main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 5)));Debug.locals.put("spacing", _spacing);Debug.locals.put("spacing", _spacing);
 BA.debugLineNum = 492;BA.debugLine="Dim currentY As Int = 10dip";
Debug.ShouldStop(2048);
_currenty = main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)));Debug.locals.put("currentY", _currenty);Debug.locals.put("currentY", _currenty);
 BA.debugLineNum = 495;BA.debugLine="Dim btnAdd As Button";
Debug.ShouldStop(16384);
_btnadd = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");Debug.locals.put("btnAdd", _btnadd);
 BA.debugLineNum = 496;BA.debugLine="btnAdd.Initialize(\"btnAddNew\")";
Debug.ShouldStop(32768);
_btnadd.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("btnAddNew")));
 BA.debugLineNum = 497;BA.debugLine="btnAdd.Text = \"+ Add New Student Record\"";
Debug.ShouldStop(65536);
_btnadd.runMethod(true,"setText",BA.ObjectToCharSequence("+ Add New Student Record"));
 BA.debugLineNum = 498;BA.debugLine="btnAdd.TextSize = 14";
Debug.ShouldStop(131072);
_btnadd.runMethod(true,"setTextSize",BA.numberCast(float.class, 14));
 BA.debugLineNum = 499;BA.debugLine="btnAdd.TextColor = Colors.White";
Debug.ShouldStop(262144);
_btnadd.runMethod(true,"setTextColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 BA.debugLineNum = 500;BA.debugLine="btnAdd.Color = Colors.RGB(52, 168, 83)";
Debug.ShouldStop(524288);
_btnadd.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").runMethod(true,"RGB",(Object)(BA.numberCast(int.class, 52)),(Object)(BA.numberCast(int.class, 168)),(Object)(BA.numberCast(int.class, 83))));
 BA.debugLineNum = 501;BA.debugLine="listPanel.AddView(btnAdd, 10dip, currentY, sv2.Wi";
Debug.ShouldStop(1048576);
_listpanel.runVoidMethod ("AddView",(Object)((_btnadd.getObject())),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))),(Object)(_currenty),(Object)(RemoteObject.solve(new RemoteObject[] {_sv2.runMethod(true,"getWidth"),main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 20)))}, "-",1, 1)),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 45)))));
 BA.debugLineNum = 502;BA.debugLine="currentY = currentY + 55dip";
Debug.ShouldStop(2097152);
_currenty = RemoteObject.solve(new RemoteObject[] {_currenty,main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 55)))}, "+",1, 1);Debug.locals.put("currentY", _currenty);
 BA.debugLineNum = 505;BA.debugLine="For i = 0 To StudentList.Size - 1";
Debug.ShouldStop(16777216);
{
final int step17 = 1;
final int limit17 = RemoteObject.solve(new RemoteObject[] {main.mostCurrent._studentlist.runMethod(true,"getSize"),RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
for (;(step17 > 0 && _i <= limit17) || (step17 < 0 && _i >= limit17) ;_i = ((int)(0 + _i + step17))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 506;BA.debugLine="Dim row() As String = StudentList.Get(i)";
Debug.ShouldStop(33554432);
_row = (main.mostCurrent._studentlist.runMethod(false,"Get",(Object)(BA.numberCast(int.class, _i))));Debug.locals.put("row", _row);Debug.locals.put("row", _row);
 BA.debugLineNum = 509;BA.debugLine="Dim pnlRow As Panel";
Debug.ShouldStop(268435456);
_pnlrow = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");Debug.locals.put("pnlRow", _pnlrow);
 BA.debugLineNum = 510;BA.debugLine="pnlRow.Initialize(\"pnlRow\")";
Debug.ShouldStop(536870912);
_pnlrow.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("pnlRow")));
 BA.debugLineNum = 511;BA.debugLine="Dim cd As ColorDrawable";
Debug.ShouldStop(1073741824);
_cd = RemoteObject.createNew ("anywheresoftware.b4a.objects.drawable.ColorDrawable");Debug.locals.put("cd", _cd);
 BA.debugLineNum = 512;BA.debugLine="cd.Initialize2(Colors.White, 4dip, 1dip, Colors.";
Debug.ShouldStop(-2147483648);
_cd.runVoidMethod ("Initialize2",(Object)(main.mostCurrent.__c.getField(false,"Colors").getField(true,"White")),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 4)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 1)))),(Object)(main.mostCurrent.__c.getField(false,"Colors").runMethod(true,"RGB",(Object)(BA.numberCast(int.class, 200)),(Object)(BA.numberCast(int.class, 200)),(Object)(BA.numberCast(int.class, 200)))));
 BA.debugLineNum = 513;BA.debugLine="pnlRow.Background = cd";
Debug.ShouldStop(1);
_pnlrow.runMethod(false,"setBackground",(_cd.getObject()));
 BA.debugLineNum = 514;BA.debugLine="pnlRow.Tag = i";
Debug.ShouldStop(2);
_pnlrow.runMethod(false,"setTag",RemoteObject.createImmutable((_i)));
 BA.debugLineNum = 515;BA.debugLine="listPanel.AddView(pnlRow, 10dip, currentY, sv2.W";
Debug.ShouldStop(4);
_listpanel.runVoidMethod ("AddView",(Object)((_pnlrow.getObject())),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))),(Object)(_currenty),(Object)(RemoteObject.solve(new RemoteObject[] {_sv2.runMethod(true,"getWidth"),main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 20)))}, "-",1, 1)),(Object)(_rowh));
 BA.debugLineNum = 518;BA.debugLine="Dim lblInfo As Label";
Debug.ShouldStop(32);
_lblinfo = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("lblInfo", _lblinfo);
 BA.debugLineNum = 519;BA.debugLine="lblInfo.Initialize(\"\")";
Debug.ShouldStop(64);
_lblinfo.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 520;BA.debugLine="lblInfo.Text = row(COL_NAME) & \" (\" & row(COL_ID";
Debug.ShouldStop(128);
_lblinfo.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(_row.getArrayElement(true,main._col_name),RemoteObject.createImmutable(" ("),_row.getArrayElement(true,main._col_id),RemoteObject.createImmutable(")"))));
 BA.debugLineNum = 521;BA.debugLine="lblInfo.TextSize = 13";
Debug.ShouldStop(256);
_lblinfo.runMethod(true,"setTextSize",BA.numberCast(float.class, 13));
 BA.debugLineNum = 522;BA.debugLine="lblInfo.TextColor = Colors.Black";
Debug.ShouldStop(512);
_lblinfo.runMethod(true,"setTextColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 BA.debugLineNum = 523;BA.debugLine="lblInfo.Gravity = Gravity.CENTER_VERTICAL";
Debug.ShouldStop(1024);
_lblinfo.runMethod(true,"setGravity",main.mostCurrent.__c.getField(false,"Gravity").getField(true,"CENTER_VERTICAL"));
 BA.debugLineNum = 524;BA.debugLine="lblInfo.Typeface = Typeface.DEFAULT_BOLD";
Debug.ShouldStop(2048);
_lblinfo.runMethod(false,"setTypeface",main.mostCurrent.__c.getField(false,"Typeface").getField(false,"DEFAULT_BOLD"));
 BA.debugLineNum = 525;BA.debugLine="lblInfo.Padding = Array As Int(10dip, 0, 0, 0)";
Debug.ShouldStop(4096);
_lblinfo.runMethod(false,"setPadding",RemoteObject.createNewArray("int",new int[] {4},new Object[] {main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10))),BA.numberCast(int.class, 0),BA.numberCast(int.class, 0),BA.numberCast(int.class, 0)}));
 BA.debugLineNum = 526;BA.debugLine="pnlRow.AddView(lblInfo, 0, 0, sv2.Width - 180dip";
Debug.ShouldStop(8192);
_pnlrow.runVoidMethod ("AddView",(Object)((_lblinfo.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(RemoteObject.solve(new RemoteObject[] {_sv2.runMethod(true,"getWidth"),main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 180)))}, "-",1, 1)),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 40)))));
 BA.debugLineNum = 528;BA.debugLine="Dim lblDetail As Label";
Debug.ShouldStop(32768);
_lbldetail = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("lblDetail", _lbldetail);
 BA.debugLineNum = 529;BA.debugLine="lblDetail.Initialize(\"\")";
Debug.ShouldStop(65536);
_lbldetail.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 530;BA.debugLine="lblDetail.Text = row(COL_SUBJECT) & \" | \" & row(";
Debug.ShouldStop(131072);
_lbldetail.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(_row.getArrayElement(true,main._col_subject),RemoteObject.createImmutable(" | "),_row.getArrayElement(true,main._col_activity),RemoteObject.createImmutable(" | "),_row.getArrayElement(true,main._col_attendance),RemoteObject.createImmutable(" | "),_row.getArrayElement(true,main._col_rate),RemoteObject.createImmutable(" ("),_computegrade(_row.getArrayElement(true,main._col_rate)),RemoteObject.createImmutable(")"))));
 BA.debugLineNum = 531;BA.debugLine="lblDetail.TextSize = 11";
Debug.ShouldStop(262144);
_lbldetail.runMethod(true,"setTextSize",BA.numberCast(float.class, 11));
 BA.debugLineNum = 532;BA.debugLine="lblDetail.TextColor = Colors.DarkGray";
Debug.ShouldStop(524288);
_lbldetail.runMethod(true,"setTextColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"DarkGray"));
 BA.debugLineNum = 533;BA.debugLine="lblDetail.Gravity = Gravity.CENTER_VERTICAL";
Debug.ShouldStop(1048576);
_lbldetail.runMethod(true,"setGravity",main.mostCurrent.__c.getField(false,"Gravity").getField(true,"CENTER_VERTICAL"));
 BA.debugLineNum = 534;BA.debugLine="lblDetail.Padding = Array As Int(10dip, 0, 0, 0)";
Debug.ShouldStop(2097152);
_lbldetail.runMethod(false,"setPadding",RemoteObject.createNewArray("int",new int[] {4},new Object[] {main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10))),BA.numberCast(int.class, 0),BA.numberCast(int.class, 0),BA.numberCast(int.class, 0)}));
 BA.debugLineNum = 535;BA.debugLine="pnlRow.AddView(lblDetail, 0, 38dip, sv2.Width -";
Debug.ShouldStop(4194304);
_pnlrow.runVoidMethod ("AddView",(Object)((_lbldetail.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 38)))),(Object)(RemoteObject.solve(new RemoteObject[] {_sv2.runMethod(true,"getWidth"),main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 180)))}, "-",1, 1)),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 40)))));
 BA.debugLineNum = 538;BA.debugLine="Dim btnEdit As Button";
Debug.ShouldStop(33554432);
_btnedit = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");Debug.locals.put("btnEdit", _btnedit);
 BA.debugLineNum = 539;BA.debugLine="btnEdit.Initialize(\"btnEditRow\")";
Debug.ShouldStop(67108864);
_btnedit.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("btnEditRow")));
 BA.debugLineNum = 540;BA.debugLine="btnEdit.Text = \"Edit\"";
Debug.ShouldStop(134217728);
_btnedit.runMethod(true,"setText",BA.ObjectToCharSequence("Edit"));
 BA.debugLineNum = 541;BA.debugLine="btnEdit.TextSize = 12";
Debug.ShouldStop(268435456);
_btnedit.runMethod(true,"setTextSize",BA.numberCast(float.class, 12));
 BA.debugLineNum = 542;BA.debugLine="btnEdit.TextColor = Colors.White";
Debug.ShouldStop(536870912);
_btnedit.runMethod(true,"setTextColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 BA.debugLineNum = 543;BA.debugLine="btnEdit.Color = Colors.RGB(66, 133, 244)";
Debug.ShouldStop(1073741824);
_btnedit.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").runMethod(true,"RGB",(Object)(BA.numberCast(int.class, 66)),(Object)(BA.numberCast(int.class, 133)),(Object)(BA.numberCast(int.class, 244))));
 BA.debugLineNum = 544;BA.debugLine="btnEdit.Tag = i";
Debug.ShouldStop(-2147483648);
_btnedit.runMethod(false,"setTag",RemoteObject.createImmutable((_i)));
 BA.debugLineNum = 545;BA.debugLine="pnlRow.AddView(btnEdit, sv2.Width - 175dip, 15di";
Debug.ShouldStop(1);
_pnlrow.runVoidMethod ("AddView",(Object)((_btnedit.getObject())),(Object)(RemoteObject.solve(new RemoteObject[] {_sv2.runMethod(true,"getWidth"),main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 175)))}, "-",1, 1)),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 15)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 70)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 45)))));
 BA.debugLineNum = 548;BA.debugLine="Dim btnDel As Button";
Debug.ShouldStop(8);
_btndel = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");Debug.locals.put("btnDel", _btndel);
 BA.debugLineNum = 549;BA.debugLine="btnDel.Initialize(\"btnDeleteRow\")";
Debug.ShouldStop(16);
_btndel.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("btnDeleteRow")));
 BA.debugLineNum = 550;BA.debugLine="btnDel.Text = \"Del\"";
Debug.ShouldStop(32);
_btndel.runMethod(true,"setText",BA.ObjectToCharSequence("Del"));
 BA.debugLineNum = 551;BA.debugLine="btnDel.TextSize = 12";
Debug.ShouldStop(64);
_btndel.runMethod(true,"setTextSize",BA.numberCast(float.class, 12));
 BA.debugLineNum = 552;BA.debugLine="btnDel.TextColor = Colors.White";
Debug.ShouldStop(128);
_btndel.runMethod(true,"setTextColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 BA.debugLineNum = 553;BA.debugLine="btnDel.Color = Colors.RGB(234, 67, 53)";
Debug.ShouldStop(256);
_btndel.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").runMethod(true,"RGB",(Object)(BA.numberCast(int.class, 234)),(Object)(BA.numberCast(int.class, 67)),(Object)(BA.numberCast(int.class, 53))));
 BA.debugLineNum = 554;BA.debugLine="btnDel.Tag = i";
Debug.ShouldStop(512);
_btndel.runMethod(false,"setTag",RemoteObject.createImmutable((_i)));
 BA.debugLineNum = 555;BA.debugLine="pnlRow.AddView(btnDel, sv2.Width - 95dip, 15dip,";
Debug.ShouldStop(1024);
_pnlrow.runVoidMethod ("AddView",(Object)((_btndel.getObject())),(Object)(RemoteObject.solve(new RemoteObject[] {_sv2.runMethod(true,"getWidth"),main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 95)))}, "-",1, 1)),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 15)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 70)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 45)))));
 BA.debugLineNum = 557;BA.debugLine="currentY = currentY + rowH + spacing";
Debug.ShouldStop(4096);
_currenty = RemoteObject.solve(new RemoteObject[] {_currenty,_rowh,_spacing}, "++",2, 1);Debug.locals.put("currentY", _currenty);
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 560;BA.debugLine="listPanel.Height = currentY + 20dip";
Debug.ShouldStop(32768);
_listpanel.runMethod(true,"setHeight",RemoteObject.solve(new RemoteObject[] {_currenty,main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 20)))}, "+",1, 1));
 BA.debugLineNum = 561;BA.debugLine="End Sub";
Debug.ShouldStop(65536);
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
		Debug.PushSubsStack("Button1_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,622);
if (RapidSub.canDelegate("button1_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","button1_click");}
 BA.debugLineNum = 622;BA.debugLine="Private Sub Button1_Click";
Debug.ShouldStop(8192);
 BA.debugLineNum = 624;BA.debugLine="showedit";
Debug.ShouldStop(32768);
_showedit();
 BA.debugLineNum = 625;BA.debugLine="End Sub";
Debug.ShouldStop(65536);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _computegrade(RemoteObject _rate) throws Exception{
try {
		Debug.PushSubsStack("ComputeGrade (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,228);
if (RapidSub.canDelegate("computegrade")) { return b4a.example.main.remoteMe.runUserSub(false, "main","computegrade", _rate);}
Debug.locals.put("rate", _rate);
 BA.debugLineNum = 228;BA.debugLine="Sub ComputeGrade(rate As String) As String";
Debug.ShouldStop(8);
 BA.debugLineNum = 229;BA.debugLine="Return PercentageToGrade(ComputePercentage(rate))";
Debug.ShouldStop(16);
if (true) return _percentagetograde(_computepercentage(_rate));
 BA.debugLineNum = 230;BA.debugLine="End Sub";
Debug.ShouldStop(32);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _computepercentage(RemoteObject _rate) throws Exception{
try {
		Debug.PushSubsStack("ComputePercentage (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,208);
if (RapidSub.canDelegate("computepercentage")) { return b4a.example.main.remoteMe.runUserSub(false, "main","computepercentage", _rate);}
RemoteObject _parts = null;
RemoteObject _numerator = RemoteObject.createImmutable(0);
RemoteObject _denominator = RemoteObject.createImmutable(0);
Debug.locals.put("rate", _rate);
 BA.debugLineNum = 208;BA.debugLine="Sub ComputePercentage(rate As String) As Double";
Debug.ShouldStop(32768);
 BA.debugLineNum = 209;BA.debugLine="If rate.Contains(\"/\") = False Then Return 0";
Debug.ShouldStop(65536);
if (RemoteObject.solveBoolean("=",_rate.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("/"))),main.mostCurrent.__c.getField(true,"False"))) { 
if (true) return BA.numberCast(double.class, 0);};
 BA.debugLineNum = 210;BA.debugLine="Dim parts() As String = Regex.Split(\"/\", rate)";
Debug.ShouldStop(131072);
_parts = main.mostCurrent.__c.getField(false,"Regex").runMethod(false,"Split",(Object)(BA.ObjectToString("/")),(Object)(_rate));Debug.locals.put("parts", _parts);Debug.locals.put("parts", _parts);
 BA.debugLineNum = 211;BA.debugLine="If parts.Length <> 2 Then Return 0";
Debug.ShouldStop(262144);
if (RemoteObject.solveBoolean("!",_parts.getField(true,"length"),BA.numberCast(double.class, 2))) { 
if (true) return BA.numberCast(double.class, 0);};
 BA.debugLineNum = 212;BA.debugLine="Dim numerator As Double = parts(0)";
Debug.ShouldStop(524288);
_numerator = BA.numberCast(double.class, _parts.getArrayElement(true,BA.numberCast(int.class, 0)));Debug.locals.put("numerator", _numerator);Debug.locals.put("numerator", _numerator);
 BA.debugLineNum = 213;BA.debugLine="Dim denominator As Double = parts(1)";
Debug.ShouldStop(1048576);
_denominator = BA.numberCast(double.class, _parts.getArrayElement(true,BA.numberCast(int.class, 1)));Debug.locals.put("denominator", _denominator);Debug.locals.put("denominator", _denominator);
 BA.debugLineNum = 214;BA.debugLine="If denominator = 0 Then Return 0";
Debug.ShouldStop(2097152);
if (RemoteObject.solveBoolean("=",_denominator,BA.numberCast(double.class, 0))) { 
if (true) return BA.numberCast(double.class, 0);};
 BA.debugLineNum = 215;BA.debugLine="Return (numerator / denominator) * 100";
Debug.ShouldStop(4194304);
if (true) return RemoteObject.solve(new RemoteObject[] {(RemoteObject.solve(new RemoteObject[] {_numerator,_denominator}, "/",0, 0)),RemoteObject.createImmutable(100)}, "*",0, 0);
 BA.debugLineNum = 216;BA.debugLine="End Sub";
Debug.ShouldStop(8388608);
return RemoteObject.createImmutable(0);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _creat_click() throws Exception{
try {
		Debug.PushSubsStack("CREAT_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,627);
if (RapidSub.canDelegate("creat_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","creat_click");}
 BA.debugLineNum = 627;BA.debugLine="Private Sub CREAT_Click";
Debug.ShouldStop(262144);
 BA.debugLineNum = 629;BA.debugLine="EditingRowIndex = -1";
Debug.ShouldStop(1048576);
main._editingrowindex = BA.numberCast(int.class, -(double) (0 + 1));
 BA.debugLineNum = 630;BA.debugLine="ShowCREATForm(\"\", \"\", \"\", \"\", False)";
Debug.ShouldStop(2097152);
_showcreatform(BA.ObjectToString(""),BA.ObjectToString(""),BA.ObjectToString(""),BA.ObjectToString(""),main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 631;BA.debugLine="End Sub";
Debug.ShouldStop(4194304);
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
		Debug.PushSubsStack("CreateMenu (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,120);
if (RapidSub.canDelegate("createmenu")) { return b4a.example.main.remoteMe.runUserSub(false, "main","createmenu");}
RemoteObject _btndashboard = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
RemoteObject _btngrades = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
RemoteObject _btnedit = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
RemoteObject _btnprofile = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
RemoteObject _b = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
 BA.debugLineNum = 120;BA.debugLine="Sub CreateMenu";
Debug.ShouldStop(8388608);
 BA.debugLineNum = 121;BA.debugLine="Dim btnDashBoard, btnGrades, btnedit, btnProfile";
Debug.ShouldStop(16777216);
_btndashboard = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");Debug.locals.put("btnDashBoard", _btndashboard);
_btngrades = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");Debug.locals.put("btnGrades", _btngrades);
_btnedit = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");Debug.locals.put("btnedit", _btnedit);
_btnprofile = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");Debug.locals.put("btnProfile", _btnprofile);
 BA.debugLineNum = 122;BA.debugLine="btnDashBoard.Initialize(\"btnDashBoard\")";
Debug.ShouldStop(33554432);
_btndashboard.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("btnDashBoard")));
 BA.debugLineNum = 123;BA.debugLine="btnDashBoard.Text = \"Dashboard\"";
Debug.ShouldStop(67108864);
_btndashboard.runMethod(true,"setText",BA.ObjectToCharSequence("Dashboard"));
 BA.debugLineNum = 124;BA.debugLine="btnGrades.Initialize(\"btnGrades\")";
Debug.ShouldStop(134217728);
_btngrades.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("btnGrades")));
 BA.debugLineNum = 125;BA.debugLine="btnGrades.Text = \"Grades\"";
Debug.ShouldStop(268435456);
_btngrades.runMethod(true,"setText",BA.ObjectToCharSequence("Grades"));
 BA.debugLineNum = 126;BA.debugLine="btnedit.Initialize(\"btnedit\")";
Debug.ShouldStop(536870912);
_btnedit.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("btnedit")));
 BA.debugLineNum = 127;BA.debugLine="btnedit.Text = \"Edit\"";
Debug.ShouldStop(1073741824);
_btnedit.runMethod(true,"setText",BA.ObjectToCharSequence("Edit"));
 BA.debugLineNum = 128;BA.debugLine="btnProfile.Initialize(\"btnProfile\")";
Debug.ShouldStop(-2147483648);
_btnprofile.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("btnProfile")));
 BA.debugLineNum = 129;BA.debugLine="btnProfile.Text = \"Profile\"";
Debug.ShouldStop(1);
_btnprofile.runMethod(true,"setText",BA.ObjectToCharSequence("Profile"));
 BA.debugLineNum = 131;BA.debugLine="For Each b As Button In Array(btnDashBoard, btnGr";
Debug.ShouldStop(4);
_b = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
{
final RemoteObject group10 = RemoteObject.createNewArray("Object",new int[] {4},new Object[] {(_btndashboard.getObject()),(_btngrades.getObject()),(_btnedit.getObject()),(_btnprofile.getObject())});
final int groupLen10 = group10.getField(true,"length").<Integer>get()
;int index10 = 0;
;
for (; index10 < groupLen10;index10++){
_b = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.ButtonWrapper"), group10.getArrayElement(false,RemoteObject.createImmutable(index10)));Debug.locals.put("b", _b);
Debug.locals.put("b", _b);
 BA.debugLineNum = 132;BA.debugLine="b.TextSize = 16";
Debug.ShouldStop(8);
_b.runMethod(true,"setTextSize",BA.numberCast(float.class, 16));
 BA.debugLineNum = 133;BA.debugLine="b.Gravity = Gravity.LEFT + Gravity.CENTER_VERTIC";
Debug.ShouldStop(16);
_b.runMethod(true,"setGravity",RemoteObject.solve(new RemoteObject[] {main.mostCurrent.__c.getField(false,"Gravity").getField(true,"LEFT"),main.mostCurrent.__c.getField(false,"Gravity").getField(true,"CENTER_VERTICAL")}, "+",1, 1));
 BA.debugLineNum = 134;BA.debugLine="b.Color = Colors.Transparent";
Debug.ShouldStop(32);
_b.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Transparent"));
 BA.debugLineNum = 135;BA.debugLine="b.TextColor = Colors.White";
Debug.ShouldStop(64);
_b.runMethod(true,"setTextColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 BA.debugLineNum = 136;BA.debugLine="pnlmenu.AddView(b, 10dip, 0, 240dip, 50dip)";
Debug.ShouldStop(128);
main.mostCurrent._pnlmenu.runVoidMethod ("AddView",(Object)((_b.getObject())),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))),(Object)(BA.numberCast(int.class, 0)),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 240)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 50)))));
 }
}Debug.locals.put("b", _b);
;
 BA.debugLineNum = 138;BA.debugLine="btnDashBoard.Top = 120dip";
Debug.ShouldStop(512);
_btndashboard.runMethod(true,"setTop",main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 120))));
 BA.debugLineNum = 139;BA.debugLine="btnGrades.Top = 180dip";
Debug.ShouldStop(1024);
_btngrades.runMethod(true,"setTop",main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 180))));
 BA.debugLineNum = 140;BA.debugLine="btnedit.Top = 240dip";
Debug.ShouldStop(2048);
_btnedit.runMethod(true,"setTop",main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 240))));
 BA.debugLineNum = 141;BA.debugLine="btnProfile.Top = 300dip";
Debug.ShouldStop(4096);
_btnprofile.runMethod(true,"setTop",main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 300))));
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
public static RemoteObject  _dashbtn_click() throws Exception{
try {
		Debug.PushSubsStack("dashbtn_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,164);
if (RapidSub.canDelegate("dashbtn_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","dashbtn_click");}
 BA.debugLineNum = 164;BA.debugLine="Private Sub dashbtn_Click";
Debug.ShouldStop(8);
 BA.debugLineNum = 165;BA.debugLine="HighlightTab(dashbtn)";
Debug.ShouldStop(16);
_highlighttab(main.mostCurrent._dashbtn);
 BA.debugLineNum = 166;BA.debugLine="showdashboard";
Debug.ShouldStop(32);
_showdashboard();
 BA.debugLineNum = 167;BA.debugLine="End Sub";
Debug.ShouldStop(64);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _editbtn_click() throws Exception{
try {
		Debug.PushSubsStack("editbtn_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,174);
if (RapidSub.canDelegate("editbtn_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","editbtn_click");}
 BA.debugLineNum = 174;BA.debugLine="Private Sub editbtn_Click";
Debug.ShouldStop(8192);
 BA.debugLineNum = 175;BA.debugLine="HighlightTab(editbtn)";
Debug.ShouldStop(16384);
_highlighttab(main.mostCurrent._editbtn);
 BA.debugLineNum = 176;BA.debugLine="showedit";
Debug.ShouldStop(32768);
_showedit();
 BA.debugLineNum = 177;BA.debugLine="End Sub";
Debug.ShouldStop(65536);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _generategraph() throws Exception{
try {
		Debug.PushSubsStack("generategraph (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,286);
if (RapidSub.canDelegate("generategraph")) { return b4a.example.main.remoteMe.runUserSub(false, "main","generategraph");}
RemoteObject _names = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _numcols = RemoteObject.createImmutable(0);
RemoteObject _colwidth = RemoteObject.createImmutable(0);
RemoteObject _barcolors = null;
RemoteObject _maxval = RemoteObject.createImmutable(0);
int _i = 0;
RemoteObject _avg = RemoteObject.createImmutable(0);
RemoteObject _valuelabelheight = RemoteObject.createImmutable(0);
RemoteObject _chartareaheight = RemoteObject.createImmutable(0);
RemoteObject _barpadding = RemoteObject.createImmutable(0);
RemoteObject _studentname = RemoteObject.createImmutable("");
RemoteObject _avgpct = RemoteObject.createImmutable(0);
RemoteObject _barheight = RemoteObject.createImmutable(0);
RemoteObject _bartop = RemoteObject.createImmutable(0);
RemoteObject _pnlbar = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
RemoteObject _lblvalue = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
RemoteObject _l = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
RemoteObject _displayname = RemoteObject.createImmutable("");
RemoteObject _nameparts = null;
 BA.debugLineNum = 286;BA.debugLine="Sub generategraph";
Debug.ShouldStop(536870912);
 BA.debugLineNum = 287;BA.debugLine="Dim names As List = GetUniqueStudentNames";
Debug.ShouldStop(1073741824);
_names = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_names = _getuniquestudentnames();Debug.locals.put("names", _names);Debug.locals.put("names", _names);
 BA.debugLineNum = 288;BA.debugLine="If names.Size = 0 Then Return";
Debug.ShouldStop(-2147483648);
if (RemoteObject.solveBoolean("=",_names.runMethod(true,"getSize"),BA.numberCast(double.class, 0))) { 
if (true) return RemoteObject.createImmutable("");};
 BA.debugLineNum = 290;BA.debugLine="Dim numCols As Int = names.Size";
Debug.ShouldStop(2);
_numcols = _names.runMethod(true,"getSize");Debug.locals.put("numCols", _numcols);Debug.locals.put("numCols", _numcols);
 BA.debugLineNum = 291;BA.debugLine="Dim colWidth As Int = datapnl.Width / numCols";
Debug.ShouldStop(4);
_colwidth = BA.numberCast(int.class, RemoteObject.solve(new RemoteObject[] {main.mostCurrent._datapnl.runMethod(true,"getWidth"),_numcols}, "/",0, 0));Debug.locals.put("colWidth", _colwidth);Debug.locals.put("colWidth", _colwidth);
 BA.debugLineNum = 294;BA.debugLine="Dim barColors() As Int = Array As Int( _ 		Colors";
Debug.ShouldStop(32);
_barcolors = RemoteObject.createNewArray("int",new int[] {5},new Object[] {main.mostCurrent.__c.getField(false,"Colors").runMethod(true,"RGB",(Object)(BA.numberCast(int.class, 66)),(Object)(BA.numberCast(int.class, 133)),(Object)(BA.numberCast(int.class, 244))),main.mostCurrent.__c.getField(false,"Colors").runMethod(true,"RGB",(Object)(BA.numberCast(int.class, 234)),(Object)(BA.numberCast(int.class, 67)),(Object)(BA.numberCast(int.class, 53))),main.mostCurrent.__c.getField(false,"Colors").runMethod(true,"RGB",(Object)(BA.numberCast(int.class, 251)),(Object)(BA.numberCast(int.class, 188)),(Object)(BA.numberCast(int.class, 4))),main.mostCurrent.__c.getField(false,"Colors").runMethod(true,"RGB",(Object)(BA.numberCast(int.class, 52)),(Object)(BA.numberCast(int.class, 168)),(Object)(BA.numberCast(int.class, 83))),main.mostCurrent.__c.getField(false,"Colors").runMethod(true,"RGB",(Object)(BA.numberCast(int.class, 156)),(Object)(BA.numberCast(int.class, 39)),(Object)(BA.numberCast(int.class, 176)))});Debug.locals.put("barColors", _barcolors);Debug.locals.put("barColors", _barcolors);
 BA.debugLineNum = 302;BA.debugLine="Dim maxVal As Double = 0";
Debug.ShouldStop(8192);
_maxval = BA.numberCast(double.class, 0);Debug.locals.put("maxVal", _maxval);Debug.locals.put("maxVal", _maxval);
 BA.debugLineNum = 303;BA.debugLine="For i = 0 To names.Size - 1";
Debug.ShouldStop(16384);
{
final int step7 = 1;
final int limit7 = RemoteObject.solve(new RemoteObject[] {_names.runMethod(true,"getSize"),RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
for (;(step7 > 0 && _i <= limit7) || (step7 < 0 && _i >= limit7) ;_i = ((int)(0 + _i + step7))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 304;BA.debugLine="Dim avg As Double = GetStudentAvgPercentage(name";
Debug.ShouldStop(32768);
_avg = _getstudentavgpercentage(BA.ObjectToString(_names.runMethod(false,"Get",(Object)(BA.numberCast(int.class, _i)))));Debug.locals.put("avg", _avg);Debug.locals.put("avg", _avg);
 BA.debugLineNum = 305;BA.debugLine="If avg > maxVal Then maxVal = avg";
Debug.ShouldStop(65536);
if (RemoteObject.solveBoolean(">",_avg,_maxval)) { 
_maxval = _avg;Debug.locals.put("maxVal", _maxval);};
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 307;BA.debugLine="If maxVal = 0 Then maxVal = 100";
Debug.ShouldStop(262144);
if (RemoteObject.solveBoolean("=",_maxval,BA.numberCast(double.class, 0))) { 
_maxval = BA.numberCast(double.class, 100);Debug.locals.put("maxVal", _maxval);};
 BA.debugLineNum = 309;BA.debugLine="Dim rowHeight As Int = 40dip";
Debug.ShouldStop(1048576);
main._rowheight = main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 40)));
 BA.debugLineNum = 310;BA.debugLine="Dim valueLabelHeight As Int = 30dip";
Debug.ShouldStop(2097152);
_valuelabelheight = main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 30)));Debug.locals.put("valueLabelHeight", _valuelabelheight);Debug.locals.put("valueLabelHeight", _valuelabelheight);
 BA.debugLineNum = 311;BA.debugLine="Dim chartAreaHeight As Int = datapnl.Height - row";
Debug.ShouldStop(4194304);
_chartareaheight = RemoteObject.solve(new RemoteObject[] {main.mostCurrent._datapnl.runMethod(true,"getHeight"),main._rowheight,_valuelabelheight}, "--",2, 1);Debug.locals.put("chartAreaHeight", _chartareaheight);Debug.locals.put("chartAreaHeight", _chartareaheight);
 BA.debugLineNum = 312;BA.debugLine="Dim barPadding As Int = 10dip";
Debug.ShouldStop(8388608);
_barpadding = main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)));Debug.locals.put("barPadding", _barpadding);Debug.locals.put("barPadding", _barpadding);
 BA.debugLineNum = 314;BA.debugLine="For i = 0 To numCols - 1";
Debug.ShouldStop(33554432);
{
final int step16 = 1;
final int limit16 = RemoteObject.solve(new RemoteObject[] {_numcols,RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
for (;(step16 > 0 && _i <= limit16) || (step16 < 0 && _i >= limit16) ;_i = ((int)(0 + _i + step16))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 315;BA.debugLine="Dim studentName As String = names.Get(i)";
Debug.ShouldStop(67108864);
_studentname = BA.ObjectToString(_names.runMethod(false,"Get",(Object)(BA.numberCast(int.class, _i))));Debug.locals.put("studentName", _studentname);Debug.locals.put("studentName", _studentname);
 BA.debugLineNum = 316;BA.debugLine="Dim avgPct As Double = GetStudentAvgPercentage(s";
Debug.ShouldStop(134217728);
_avgpct = _getstudentavgpercentage(_studentname);Debug.locals.put("avgPct", _avgpct);Debug.locals.put("avgPct", _avgpct);
 BA.debugLineNum = 319;BA.debugLine="Dim barHeight As Int";
Debug.ShouldStop(1073741824);
_barheight = RemoteObject.createImmutable(0);Debug.locals.put("barHeight", _barheight);
 BA.debugLineNum = 320;BA.debugLine="If maxVal > 0 Then";
Debug.ShouldStop(-2147483648);
if (RemoteObject.solveBoolean(">",_maxval,BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 321;BA.debugLine="barHeight = (avgPct / maxVal) * chartAreaHeight";
Debug.ShouldStop(1);
_barheight = BA.numberCast(int.class, RemoteObject.solve(new RemoteObject[] {(RemoteObject.solve(new RemoteObject[] {_avgpct,_maxval}, "/",0, 0)),_chartareaheight}, "*",0, 0));Debug.locals.put("barHeight", _barheight);
 }else {
 BA.debugLineNum = 323;BA.debugLine="barHeight = 0";
Debug.ShouldStop(4);
_barheight = BA.numberCast(int.class, 0);Debug.locals.put("barHeight", _barheight);
 };
 BA.debugLineNum = 326;BA.debugLine="Dim barTop As Int = datapnl.Height - rowHeight -";
Debug.ShouldStop(32);
_bartop = RemoteObject.solve(new RemoteObject[] {main.mostCurrent._datapnl.runMethod(true,"getHeight"),main._rowheight,_barheight}, "--",2, 1);Debug.locals.put("barTop", _bartop);Debug.locals.put("barTop", _bartop);
 BA.debugLineNum = 328;BA.debugLine="Dim pnlBar As Panel";
Debug.ShouldStop(128);
_pnlbar = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");Debug.locals.put("pnlBar", _pnlbar);
 BA.debugLineNum = 329;BA.debugLine="pnlBar.Initialize(\"pnlBar\")";
Debug.ShouldStop(256);
_pnlbar.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("pnlBar")));
 BA.debugLineNum = 330;BA.debugLine="pnlBar.Color = barColors(i Mod barColors.Length)";
Debug.ShouldStop(512);
_pnlbar.runVoidMethod ("setColor",_barcolors.getArrayElement(true,RemoteObject.solve(new RemoteObject[] {RemoteObject.createImmutable(_i),_barcolors.getField(true,"length")}, "%",0, 1)));
 BA.debugLineNum = 331;BA.debugLine="pnlBar.Tag = i";
Debug.ShouldStop(1024);
_pnlbar.runMethod(false,"setTag",RemoteObject.createImmutable((_i)));
 BA.debugLineNum = 332;BA.debugLine="datapnl.AddView(pnlBar, _ 			(colWidth * i) + ba";
Debug.ShouldStop(2048);
main.mostCurrent._datapnl.runVoidMethod ("AddView",(Object)((_pnlbar.getObject())),(Object)(RemoteObject.solve(new RemoteObject[] {(RemoteObject.solve(new RemoteObject[] {_colwidth,RemoteObject.createImmutable(_i)}, "*",0, 1)),_barpadding}, "+",1, 1)),(Object)(_bartop),(Object)(RemoteObject.solve(new RemoteObject[] {_colwidth,(RemoteObject.solve(new RemoteObject[] {_barpadding,RemoteObject.createImmutable(2)}, "*",0, 1))}, "-",1, 1)),(Object)(_barheight));
 BA.debugLineNum = 339;BA.debugLine="Dim lblValue As Label";
Debug.ShouldStop(262144);
_lblvalue = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("lblValue", _lblvalue);
 BA.debugLineNum = 340;BA.debugLine="lblValue.Initialize(\"lblValue\")";
Debug.ShouldStop(524288);
_lblvalue.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("lblValue")));
 BA.debugLineNum = 341;BA.debugLine="lblValue.Text = Round2(avgPct, 0) & \"%\"";
Debug.ShouldStop(1048576);
_lblvalue.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(main.mostCurrent.__c.runMethod(true,"Round2",(Object)(_avgpct),(Object)(BA.numberCast(int.class, 0))),RemoteObject.createImmutable("%"))));
 BA.debugLineNum = 342;BA.debugLine="lblValue.Gravity = Gravity.CENTER";
Debug.ShouldStop(2097152);
_lblvalue.runMethod(true,"setGravity",main.mostCurrent.__c.getField(false,"Gravity").getField(true,"CENTER"));
 BA.debugLineNum = 343;BA.debugLine="lblValue.TextSize = 12";
Debug.ShouldStop(4194304);
_lblvalue.runMethod(true,"setTextSize",BA.numberCast(float.class, 12));
 BA.debugLineNum = 344;BA.debugLine="lblValue.TextColor = Colors.Black";
Debug.ShouldStop(8388608);
_lblvalue.runMethod(true,"setTextColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 BA.debugLineNum = 345;BA.debugLine="datapnl.AddView(lblValue, _ 			colWidth * i, _";
Debug.ShouldStop(16777216);
main.mostCurrent._datapnl.runVoidMethod ("AddView",(Object)((_lblvalue.getObject())),(Object)(RemoteObject.solve(new RemoteObject[] {_colwidth,RemoteObject.createImmutable(_i)}, "*",0, 1)),(Object)(RemoteObject.solve(new RemoteObject[] {_bartop,_valuelabelheight}, "-",1, 1)),(Object)(_colwidth),(Object)(_valuelabelheight));
 BA.debugLineNum = 352;BA.debugLine="Dim l As Label";
Debug.ShouldStop(-2147483648);
_l = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("l", _l);
 BA.debugLineNum = 353;BA.debugLine="l.Initialize(\"labels\")";
Debug.ShouldStop(1);
_l.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("labels")));
 BA.debugLineNum = 355;BA.debugLine="Dim displayName As String = studentName";
Debug.ShouldStop(4);
_displayname = _studentname;Debug.locals.put("displayName", _displayname);Debug.locals.put("displayName", _displayname);
 BA.debugLineNum = 356;BA.debugLine="If displayName.Length > 8 Then";
Debug.ShouldStop(8);
if (RemoteObject.solveBoolean(">",_displayname.runMethod(true,"length"),BA.numberCast(double.class, 8))) { 
 BA.debugLineNum = 357;BA.debugLine="Dim nameParts() As String = Regex.Split(\" \", di";
Debug.ShouldStop(16);
_nameparts = main.mostCurrent.__c.getField(false,"Regex").runMethod(false,"Split",(Object)(BA.ObjectToString(" ")),(Object)(_displayname));Debug.locals.put("nameParts", _nameparts);Debug.locals.put("nameParts", _nameparts);
 BA.debugLineNum = 358;BA.debugLine="displayName = nameParts(0)";
Debug.ShouldStop(32);
_displayname = _nameparts.getArrayElement(true,BA.numberCast(int.class, 0));Debug.locals.put("displayName", _displayname);
 };
 BA.debugLineNum = 360;BA.debugLine="l.Text = displayName";
Debug.ShouldStop(128);
_l.runMethod(true,"setText",BA.ObjectToCharSequence(_displayname));
 BA.debugLineNum = 361;BA.debugLine="l.Gravity = Gravity.CENTER";
Debug.ShouldStop(256);
_l.runMethod(true,"setGravity",main.mostCurrent.__c.getField(false,"Gravity").getField(true,"CENTER"));
 BA.debugLineNum = 362;BA.debugLine="l.TextSize = 10";
Debug.ShouldStop(512);
_l.runMethod(true,"setTextSize",BA.numberCast(float.class, 10));
 BA.debugLineNum = 363;BA.debugLine="l.Color = 0x00ffffff";
Debug.ShouldStop(1024);
_l.runVoidMethod ("setColor",BA.numberCast(int.class, ((int)0x00ffffff)));
 BA.debugLineNum = 364;BA.debugLine="l.TextColor = Colors.Black";
Debug.ShouldStop(2048);
_l.runMethod(true,"setTextColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 BA.debugLineNum = 365;BA.debugLine="l.Padding = Array As Int(0dip, 5dip, 0dip, 5dip)";
Debug.ShouldStop(4096);
_l.runMethod(false,"setPadding",RemoteObject.createNewArray("int",new int[] {4},new Object[] {main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 0))),main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 5))),main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 0))),main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 5)))}));
 BA.debugLineNum = 366;BA.debugLine="l.Tag = i";
Debug.ShouldStop(8192);
_l.runMethod(false,"setTag",RemoteObject.createImmutable((_i)));
 BA.debugLineNum = 367;BA.debugLine="datapnl.AddView(l, _ 			colWidth * i, _ 			datap";
Debug.ShouldStop(16384);
main.mostCurrent._datapnl.runVoidMethod ("AddView",(Object)((_l.getObject())),(Object)(RemoteObject.solve(new RemoteObject[] {_colwidth,RemoteObject.createImmutable(_i)}, "*",0, 1)),(Object)(RemoteObject.solve(new RemoteObject[] {main.mostCurrent._datapnl.runMethod(true,"getHeight"),main._rowheight}, "-",1, 1)),(Object)(_colwidth),(Object)(main._rowheight));
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 373;BA.debugLine="End Sub";
Debug.ShouldStop(1048576);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _getstudentavgpercentage(RemoteObject _studentname) throws Exception{
try {
		Debug.PushSubsStack("GetStudentAvgPercentage (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,247);
if (RapidSub.canDelegate("getstudentavgpercentage")) { return b4a.example.main.remoteMe.runUserSub(false, "main","getstudentavgpercentage", _studentname);}
RemoteObject _total = RemoteObject.createImmutable(0);
RemoteObject _count = RemoteObject.createImmutable(0);
int _i = 0;
RemoteObject _row = null;
Debug.locals.put("studentName", _studentname);
 BA.debugLineNum = 247;BA.debugLine="Sub GetStudentAvgPercentage(studentName As String)";
Debug.ShouldStop(4194304);
 BA.debugLineNum = 248;BA.debugLine="Dim total As Double = 0";
Debug.ShouldStop(8388608);
_total = BA.numberCast(double.class, 0);Debug.locals.put("total", _total);Debug.locals.put("total", _total);
 BA.debugLineNum = 249;BA.debugLine="Dim count As Int = 0";
Debug.ShouldStop(16777216);
_count = BA.numberCast(int.class, 0);Debug.locals.put("count", _count);Debug.locals.put("count", _count);
 BA.debugLineNum = 250;BA.debugLine="For i = 0 To StudentList.Size - 1";
Debug.ShouldStop(33554432);
{
final int step3 = 1;
final int limit3 = RemoteObject.solve(new RemoteObject[] {main.mostCurrent._studentlist.runMethod(true,"getSize"),RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
for (;(step3 > 0 && _i <= limit3) || (step3 < 0 && _i >= limit3) ;_i = ((int)(0 + _i + step3))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 251;BA.debugLine="Dim row() As String = StudentList.Get(i)";
Debug.ShouldStop(67108864);
_row = (main.mostCurrent._studentlist.runMethod(false,"Get",(Object)(BA.numberCast(int.class, _i))));Debug.locals.put("row", _row);Debug.locals.put("row", _row);
 BA.debugLineNum = 252;BA.debugLine="If row(COL_NAME) = studentName Then";
Debug.ShouldStop(134217728);
if (RemoteObject.solveBoolean("=",_row.getArrayElement(true,main._col_name),_studentname)) { 
 BA.debugLineNum = 253;BA.debugLine="total = total + ComputePercentage(row(COL_RATE)";
Debug.ShouldStop(268435456);
_total = RemoteObject.solve(new RemoteObject[] {_total,_computepercentage(_row.getArrayElement(true,main._col_rate))}, "+",1, 0);Debug.locals.put("total", _total);
 BA.debugLineNum = 254;BA.debugLine="count = count + 1";
Debug.ShouldStop(536870912);
_count = RemoteObject.solve(new RemoteObject[] {_count,RemoteObject.createImmutable(1)}, "+",1, 1);Debug.locals.put("count", _count);
 };
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 257;BA.debugLine="If count = 0 Then Return 0";
Debug.ShouldStop(1);
if (RemoteObject.solveBoolean("=",_count,BA.numberCast(double.class, 0))) { 
if (true) return BA.numberCast(double.class, 0);};
 BA.debugLineNum = 258;BA.debugLine="Return total / count";
Debug.ShouldStop(2);
if (true) return RemoteObject.solve(new RemoteObject[] {_total,_count}, "/",0, 0);
 BA.debugLineNum = 259;BA.debugLine="End Sub";
Debug.ShouldStop(4);
return RemoteObject.createImmutable(0);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _getstudentgrade(RemoteObject _studentname) throws Exception{
try {
		Debug.PushSubsStack("GetStudentGrade (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,262);
if (RapidSub.canDelegate("getstudentgrade")) { return b4a.example.main.remoteMe.runUserSub(false, "main","getstudentgrade", _studentname);}
Debug.locals.put("studentName", _studentname);
 BA.debugLineNum = 262;BA.debugLine="Sub GetStudentGrade(studentName As String) As Stri";
Debug.ShouldStop(32);
 BA.debugLineNum = 263;BA.debugLine="Return PercentageToGrade(GetStudentAvgPercentage(";
Debug.ShouldStop(64);
if (true) return _percentagetograde(_getstudentavgpercentage(_studentname));
 BA.debugLineNum = 264;BA.debugLine="End Sub";
Debug.ShouldStop(128);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _getuniquestudentnames() throws Exception{
try {
		Debug.PushSubsStack("GetUniqueStudentNames (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,233);
if (RapidSub.canDelegate("getuniquestudentnames")) { return b4a.example.main.remoteMe.runUserSub(false, "main","getuniquestudentnames");}
RemoteObject _names = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
int _i = 0;
RemoteObject _row = null;
RemoteObject _name = RemoteObject.createImmutable("");
 BA.debugLineNum = 233;BA.debugLine="Sub GetUniqueStudentNames As List";
Debug.ShouldStop(256);
 BA.debugLineNum = 234;BA.debugLine="Dim names As List";
Debug.ShouldStop(512);
_names = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");Debug.locals.put("names", _names);
 BA.debugLineNum = 235;BA.debugLine="names.Initialize";
Debug.ShouldStop(1024);
_names.runVoidMethod ("Initialize");
 BA.debugLineNum = 236;BA.debugLine="For i = 0 To StudentList.Size - 1";
Debug.ShouldStop(2048);
{
final int step3 = 1;
final int limit3 = RemoteObject.solve(new RemoteObject[] {main.mostCurrent._studentlist.runMethod(true,"getSize"),RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
for (;(step3 > 0 && _i <= limit3) || (step3 < 0 && _i >= limit3) ;_i = ((int)(0 + _i + step3))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 237;BA.debugLine="Dim row() As String = StudentList.Get(i)";
Debug.ShouldStop(4096);
_row = (main.mostCurrent._studentlist.runMethod(false,"Get",(Object)(BA.numberCast(int.class, _i))));Debug.locals.put("row", _row);Debug.locals.put("row", _row);
 BA.debugLineNum = 238;BA.debugLine="Dim name As String = row(COL_NAME)";
Debug.ShouldStop(8192);
_name = _row.getArrayElement(true,main._col_name);Debug.locals.put("name", _name);Debug.locals.put("name", _name);
 BA.debugLineNum = 239;BA.debugLine="If names.IndexOf(name) = -1 Then";
Debug.ShouldStop(16384);
if (RemoteObject.solveBoolean("=",_names.runMethod(true,"IndexOf",(Object)((_name))),BA.numberCast(double.class, -(double) (0 + 1)))) { 
 BA.debugLineNum = 240;BA.debugLine="names.Add(name)";
Debug.ShouldStop(32768);
_names.runVoidMethod ("Add",(Object)((_name)));
 };
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 243;BA.debugLine="Return names";
Debug.ShouldStop(262144);
if (true) return _names;
 BA.debugLineNum = 244;BA.debugLine="End Sub";
Debug.ShouldStop(524288);
return RemoteObject.createImmutable(null);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _globals() throws Exception{
 //BA.debugLineNum = 24;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 26;BA.debugLine="Private Drawer As B4XDrawer";
main.mostCurrent._drawer = RemoteObject.createNew ("b4a.example.b4xdrawer");
 //BA.debugLineNum = 27;BA.debugLine="Private pnlmain As Panel";
main.mostCurrent._pnlmain = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 28;BA.debugLine="Private pnlmenu As Panel";
main.mostCurrent._pnlmenu = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 29;BA.debugLine="Private profilebtn, dashbtn, grdbtn, editbtn As P";
main.mostCurrent._profilebtn = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
main.mostCurrent._dashbtn = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
main.mostCurrent._grdbtn = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
main.mostCurrent._editbtn = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 32;BA.debugLine="Private datapnl As Panel";
main.mostCurrent._datapnl = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 35;BA.debugLine="Private gradepnl As Panel";
main.mostCurrent._gradepnl = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 36;BA.debugLine="Private SV1 As ScrollView";
main.mostCurrent._sv1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ScrollViewWrapper");
 //BA.debugLineNum = 39;BA.debugLine="Private SV As ScrollView";
main.mostCurrent._sv = RemoteObject.createNew ("anywheresoftware.b4a.objects.ScrollViewWrapper");
 //BA.debugLineNum = 40;BA.debugLine="Private Table As Panel";
main.mostCurrent._table = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 41;BA.debugLine="Type RowCol (Row As Int, Col As Int)";
;
 //BA.debugLineNum = 42;BA.debugLine="Dim NumberOfColumns, RowHeight, ColumnWidth As In";
main._numberofcolumns = RemoteObject.createImmutable(0);
main._rowheight = RemoteObject.createImmutable(0);
main._columnwidth = RemoteObject.createImmutable(0);
 //BA.debugLineNum = 43;BA.debugLine="Dim HeaderColor, FontColor, HeaderFontColor As In";
main._headercolor = RemoteObject.createImmutable(0);
main._fontcolor = RemoteObject.createImmutable(0);
main._headerfontcolor = RemoteObject.createImmutable(0);
 //BA.debugLineNum = 44;BA.debugLine="Dim FontSize As Float";
main._fontsize = RemoteObject.createImmutable(0f);
 //BA.debugLineNum = 45;BA.debugLine="Dim Alignment As Int";
main._alignment = RemoteObject.createImmutable(0);
 //BA.debugLineNum = 48;BA.debugLine="Private Panel1 As Panel";
main.mostCurrent._panel1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 49;BA.debugLine="Private CheckBox1 As CheckBox";
main.mostCurrent._checkbox1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper");
 //BA.debugLineNum = 50;BA.debugLine="Private EditText As EditText   ' Student Name";
main.mostCurrent._edittext = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 51;BA.debugLine="Private EditText1 As EditText  ' Activity Title";
main.mostCurrent._edittext1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 52;BA.debugLine="Private EditText2 As EditText  ' Result (e.g. 11/";
main.mostCurrent._edittext2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 53;BA.debugLine="Private EditText3 As EditText  ' Student ID";
main.mostCurrent._edittext3 = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 56;BA.debugLine="Dim StudentList As List        ' Each item is a S";
main.mostCurrent._studentlist = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
 //BA.debugLineNum = 57;BA.debugLine="Dim CSVHeaders As List         ' Header row from";
main.mostCurrent._csvheaders = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
 //BA.debugLineNum = 58;BA.debugLine="Dim EditingRowIndex As Int     ' -1 = creating ne";
main._editingrowindex = RemoteObject.createImmutable(0);
 //BA.debugLineNum = 61;BA.debugLine="Dim COL_NAME As Int : COL_NAME = 0";
main._col_name = RemoteObject.createImmutable(0);
 //BA.debugLineNum = 61;BA.debugLine="Dim COL_NAME As Int : COL_NAME = 0";
main._col_name = BA.numberCast(int.class, 0);
 //BA.debugLineNum = 62;BA.debugLine="Dim COL_ID As Int : COL_ID = 1";
main._col_id = RemoteObject.createImmutable(0);
 //BA.debugLineNum = 62;BA.debugLine="Dim COL_ID As Int : COL_ID = 1";
main._col_id = BA.numberCast(int.class, 1);
 //BA.debugLineNum = 63;BA.debugLine="Dim COL_SUBJECT As Int : COL_SUBJECT = 2";
main._col_subject = RemoteObject.createImmutable(0);
 //BA.debugLineNum = 63;BA.debugLine="Dim COL_SUBJECT As Int : COL_SUBJECT = 2";
main._col_subject = BA.numberCast(int.class, 2);
 //BA.debugLineNum = 64;BA.debugLine="Dim COL_ACTIVITY As Int : COL_ACTIVITY = 3";
main._col_activity = RemoteObject.createImmutable(0);
 //BA.debugLineNum = 64;BA.debugLine="Dim COL_ACTIVITY As Int : COL_ACTIVITY = 3";
main._col_activity = BA.numberCast(int.class, 3);
 //BA.debugLineNum = 65;BA.debugLine="Dim COL_ATTENDANCE As Int : COL_ATTENDANCE = 4";
main._col_attendance = RemoteObject.createImmutable(0);
 //BA.debugLineNum = 65;BA.debugLine="Dim COL_ATTENDANCE As Int : COL_ATTENDANCE = 4";
main._col_attendance = BA.numberCast(int.class, 4);
 //BA.debugLineNum = 66;BA.debugLine="Dim COL_RATE As Int : COL_RATE = 5";
main._col_rate = RemoteObject.createImmutable(0);
 //BA.debugLineNum = 66;BA.debugLine="Dim COL_RATE As Int : COL_RATE = 5";
main._col_rate = BA.numberCast(int.class, 5);
 //BA.debugLineNum = 67;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _grdbtn_click() throws Exception{
try {
		Debug.PushSubsStack("grdbtn_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,169);
if (RapidSub.canDelegate("grdbtn_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","grdbtn_click");}
 BA.debugLineNum = 169;BA.debugLine="Private Sub grdbtn_Click";
Debug.ShouldStop(256);
 BA.debugLineNum = 170;BA.debugLine="HighlightTab(grdbtn)";
Debug.ShouldStop(512);
_highlighttab(main.mostCurrent._grdbtn);
 BA.debugLineNum = 171;BA.debugLine="showgrades";
Debug.ShouldStop(1024);
_showgrades();
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
public static RemoteObject  _highlighttab(RemoteObject _activebtn) throws Exception{
try {
		Debug.PushSubsStack("HighlightTab (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,184);
if (RapidSub.canDelegate("highlighttab")) { return b4a.example.main.remoteMe.runUserSub(false, "main","highlighttab", _activebtn);}
Debug.locals.put("activeBtn", _activebtn);
 BA.debugLineNum = 184;BA.debugLine="Sub HighlightTab(activeBtn As Panel)";
Debug.ShouldStop(8388608);
 BA.debugLineNum = 185;BA.debugLine="dashbtn.Color = Colors.White";
Debug.ShouldStop(16777216);
main.mostCurrent._dashbtn.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 BA.debugLineNum = 186;BA.debugLine="grdbtn.Color = Colors.White";
Debug.ShouldStop(33554432);
main.mostCurrent._grdbtn.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 BA.debugLineNum = 187;BA.debugLine="editbtn.Color = Colors.White";
Debug.ShouldStop(67108864);
main.mostCurrent._editbtn.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 BA.debugLineNum = 188;BA.debugLine="profilebtn.Color = Colors.White";
Debug.ShouldStop(134217728);
main.mostCurrent._profilebtn.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 BA.debugLineNum = 189;BA.debugLine="activeBtn.Color = 0xFF1AEA00";
Debug.ShouldStop(268435456);
_activebtn.runVoidMethod ("setColor",BA.numberCast(int.class, ((int)0xff1aea00)));
 BA.debugLineNum = 190;BA.debugLine="End Sub";
Debug.ShouldStop(536870912);
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
		Debug.PushSubsStack("initdrawer (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,108);
if (RapidSub.canDelegate("initdrawer")) { return b4a.example.main.remoteMe.runUserSub(false, "main","initdrawer");}
 BA.debugLineNum = 108;BA.debugLine="Sub initdrawer";
Debug.ShouldStop(2048);
 BA.debugLineNum = 109;BA.debugLine="Drawer.Initialize(Me, \"Drawer\", Activity, 260dip)";
Debug.ShouldStop(4096);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_initialize" /*RemoteObject*/ ,main.mostCurrent.activityBA,(Object)(main.getObject()),(Object)(BA.ObjectToString("Drawer")),RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), main.mostCurrent._activity.getObject()),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 260)))));
 BA.debugLineNum = 110;BA.debugLine="Drawer.CenterPanel.BringToFront";
Debug.ShouldStop(8192);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_getcenterpanel" /*RemoteObject*/ ).runVoidMethod ("BringToFront");
 BA.debugLineNum = 111;BA.debugLine="Drawer.LeftPanel.BringToFront";
Debug.ShouldStop(16384);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_getleftpanel" /*RemoteObject*/ ).runVoidMethod ("BringToFront");
 BA.debugLineNum = 113;BA.debugLine="pnlmain = Drawer.CenterPanel";
Debug.ShouldStop(65536);
main.mostCurrent._pnlmain = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.PanelWrapper"), main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_getcenterpanel" /*RemoteObject*/ ).getObject());
 BA.debugLineNum = 114;BA.debugLine="pnlmenu = Drawer.LeftPanel";
Debug.ShouldStop(131072);
main.mostCurrent._pnlmenu = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.PanelWrapper"), main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_getleftpanel" /*RemoteObject*/ ).getObject());
 BA.debugLineNum = 116;BA.debugLine="SetGradient(pnlmenu, Colors.rgb(175, 71, 210), Co";
Debug.ShouldStop(524288);
_setgradient(main.mostCurrent._pnlmenu,main.mostCurrent.__c.getField(false,"Colors").runMethod(true,"RGB",(Object)(BA.numberCast(int.class, 175)),(Object)(BA.numberCast(int.class, 71)),(Object)(BA.numberCast(int.class, 210))),main.mostCurrent.__c.getField(false,"Colors").runMethod(true,"RGB",(Object)(BA.numberCast(int.class, 38)),(Object)(BA.numberCast(int.class, 53)),(Object)(BA.numberCast(int.class, 93))));
 BA.debugLineNum = 117;BA.debugLine="CreateMenu";
Debug.ShouldStop(1048576);
_createmenu();
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
public static RemoteObject  _loadstudentdata() throws Exception{
try {
		Debug.PushSubsStack("LoadStudentData (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,196);
if (RapidSub.canDelegate("loadstudentdata")) { return b4a.example.main.remoteMe.runUserSub(false, "main","loadstudentdata");}
 BA.debugLineNum = 196;BA.debugLine="Sub LoadStudentData";
Debug.ShouldStop(8);
 BA.debugLineNum = 197;BA.debugLine="CSVHeaders.Initialize";
Debug.ShouldStop(16);
main.mostCurrent._csvheaders.runVoidMethod ("Initialize");
 BA.debugLineNum = 198;BA.debugLine="StudentList = StringUtils1.LoadCSV2(File.DirInter";
Debug.ShouldStop(32);
main.mostCurrent._studentlist = main._stringutils1.runMethod(false,"LoadCSV2",(Object)(main.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirInternal")),(Object)(BA.ObjectToString("citylist.csv")),(Object)(BA.ObjectToChar(",")),(Object)(main.mostCurrent._csvheaders));
 BA.debugLineNum = 199;BA.debugLine="Log(\"Loaded \" & StudentList.Size & \" student reco";
Debug.ShouldStop(64);
main.mostCurrent.__c.runVoidMethod ("LogImpl","516515075",RemoteObject.concat(RemoteObject.createImmutable("Loaded "),main.mostCurrent._studentlist.runMethod(true,"getSize"),RemoteObject.createImmutable(" student records")),0);
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
public static RemoteObject  _loadtable() throws Exception{
try {
		Debug.PushSubsStack("loadtable (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,718);
if (RapidSub.canDelegate("loadtable")) { return b4a.example.main.remoteMe.runUserSub(false, "main","loadtable");}
RemoteObject _list1 = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _headers = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
int _i = 0;
RemoteObject _l = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
RemoteObject _row = null;
 BA.debugLineNum = 718;BA.debugLine="Sub loadtable";
Debug.ShouldStop(8192);
 BA.debugLineNum = 720;BA.debugLine="Dim List1 As List";
Debug.ShouldStop(32768);
_list1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");Debug.locals.put("List1", _list1);
 BA.debugLineNum = 721;BA.debugLine="Dim headers As List";
Debug.ShouldStop(65536);
_headers = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");Debug.locals.put("headers", _headers);
 BA.debugLineNum = 722;BA.debugLine="headers.Initialize";
Debug.ShouldStop(131072);
_headers.runVoidMethod ("Initialize");
 BA.debugLineNum = 723;BA.debugLine="List1 = StringUtils1.LoadCSV2(File.DirInternal, \"";
Debug.ShouldStop(262144);
_list1 = main._stringutils1.runMethod(false,"LoadCSV2",(Object)(main.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirInternal")),(Object)(BA.ObjectToString("citylist.csv")),(Object)(BA.ObjectToChar(",")),(Object)(_headers));Debug.locals.put("List1", _list1);
 BA.debugLineNum = 724;BA.debugLine="NumberOfColumns = headers.Size";
Debug.ShouldStop(524288);
main._numberofcolumns = _headers.runMethod(true,"getSize");
 BA.debugLineNum = 725;BA.debugLine="ColumnWidth = SV.Width / NumberOfColumns";
Debug.ShouldStop(1048576);
main._columnwidth = BA.numberCast(int.class, RemoteObject.solve(new RemoteObject[] {main.mostCurrent._sv.runMethod(true,"getWidth"),main._numberofcolumns}, "/",0, 0));
 BA.debugLineNum = 727;BA.debugLine="For i = 0 To NumberOfColumns - 1";
Debug.ShouldStop(4194304);
{
final int step7 = 1;
final int limit7 = RemoteObject.solve(new RemoteObject[] {main._numberofcolumns,RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
for (;(step7 > 0 && _i <= limit7) || (step7 < 0 && _i >= limit7) ;_i = ((int)(0 + _i + step7))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 728;BA.debugLine="Dim l As Label";
Debug.ShouldStop(8388608);
_l = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("l", _l);
 BA.debugLineNum = 729;BA.debugLine="l.Initialize(\"header\")";
Debug.ShouldStop(16777216);
_l.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("header")));
 BA.debugLineNum = 730;BA.debugLine="l.Text = headers.Get(i)";
Debug.ShouldStop(33554432);
_l.runMethod(true,"setText",BA.ObjectToCharSequence(_headers.runMethod(false,"Get",(Object)(BA.numberCast(int.class, _i)))));
 BA.debugLineNum = 731;BA.debugLine="l.Gravity = Gravity.LEFT";
Debug.ShouldStop(67108864);
_l.runMethod(true,"setGravity",main.mostCurrent.__c.getField(false,"Gravity").getField(true,"LEFT"));
 BA.debugLineNum = 732;BA.debugLine="l.TextSize = FontSize";
Debug.ShouldStop(134217728);
_l.runMethod(true,"setTextSize",main._fontsize);
 BA.debugLineNum = 733;BA.debugLine="l.Color = HeaderColor";
Debug.ShouldStop(268435456);
_l.runVoidMethod ("setColor",main._headercolor);
 BA.debugLineNum = 734;BA.debugLine="l.TextColor = HeaderFontColor";
Debug.ShouldStop(536870912);
_l.runMethod(true,"setTextColor",main._headerfontcolor);
 BA.debugLineNum = 735;BA.debugLine="l.Padding = Array As Int(10dip, 5dip, 0dip, 5dip";
Debug.ShouldStop(1073741824);
_l.runMethod(false,"setPadding",RemoteObject.createNewArray("int",new int[] {4},new Object[] {main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10))),main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 5))),main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 0))),main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 5)))}));
 BA.debugLineNum = 736;BA.debugLine="l.Tag = i";
Debug.ShouldStop(-2147483648);
_l.runMethod(false,"setTag",RemoteObject.createImmutable((_i)));
 BA.debugLineNum = 737;BA.debugLine="Table.AddView(l, ColumnWidth * i, 0, ColumnWidth";
Debug.ShouldStop(1);
main.mostCurrent._table.runVoidMethod ("AddView",(Object)((_l.getObject())),(Object)(RemoteObject.solve(new RemoteObject[] {main._columnwidth,RemoteObject.createImmutable(_i)}, "*",0, 1)),(Object)(BA.numberCast(int.class, 0)),(Object)(main._columnwidth),(Object)(main._rowheight));
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 741;BA.debugLine="For i = 0 To List1.Size - 1";
Debug.ShouldStop(16);
{
final int step19 = 1;
final int limit19 = RemoteObject.solve(new RemoteObject[] {_list1.runMethod(true,"getSize"),RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
for (;(step19 > 0 && _i <= limit19) || (step19 < 0 && _i >= limit19) ;_i = ((int)(0 + _i + step19))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 742;BA.debugLine="Dim row() As String = List1.Get(i)";
Debug.ShouldStop(32);
_row = (_list1.runMethod(false,"Get",(Object)(BA.numberCast(int.class, _i))));Debug.locals.put("row", _row);Debug.locals.put("row", _row);
 BA.debugLineNum = 743;BA.debugLine="AddTableRow(row, i + 1)  ' +1 to skip header row";
Debug.ShouldStop(64);
_addtablerow(_row,RemoteObject.solve(new RemoteObject[] {RemoteObject.createImmutable(_i),RemoteObject.createImmutable(1)}, "+",1, 1));
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 746;BA.debugLine="Table.Height = (List1.Size + 1) * RowHeight";
Debug.ShouldStop(512);
main.mostCurrent._table.runMethod(true,"setHeight",RemoteObject.solve(new RemoteObject[] {(RemoteObject.solve(new RemoteObject[] {_list1.runMethod(true,"getSize"),RemoteObject.createImmutable(1)}, "+",1, 1)),main._rowheight}, "*",0, 1));
 BA.debugLineNum = 747;BA.debugLine="End Sub";
Debug.ShouldStop(1024);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _makeshadow(RemoteObject _numstudent,RemoteObject _studentnames) throws Exception{
try {
		Debug.PushSubsStack("makeshadow (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,393);
if (RapidSub.canDelegate("makeshadow")) { return b4a.example.main.remoteMe.runUserSub(false, "main","makeshadow", _numstudent, _studentnames);}
RemoteObject _itemspacing = RemoteObject.createImmutable(0);
RemoteObject _starttopmargin = RemoteObject.createImmutable(0);
int _i = 0;
RemoteObject _currenttop = RemoteObject.createImmutable(0);
RemoteObject _pnlshadow = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
RemoteObject _cd = RemoteObject.declareNull("anywheresoftware.b4a.objects.drawable.ColorDrawable");
RemoteObject _pnlwidth = RemoteObject.createImmutable(0);
RemoteObject _pnlheight = RemoteObject.createImmutable(0);
RemoteObject _shadowleftpos = RemoteObject.createImmutable(0);
RemoteObject _shadowtoppos = RemoteObject.createImmutable(0);
RemoteObject _name = RemoteObject.createImmutable("");
RemoteObject _grade = RemoteObject.createImmutable("");
Debug.locals.put("numstudent", _numstudent);
Debug.locals.put("studentNames", _studentnames);
 BA.debugLineNum = 393;BA.debugLine="Sub makeshadow(numstudent As Int, studentNames As";
Debug.ShouldStop(256);
 BA.debugLineNum = 394;BA.debugLine="Dim itemSpacing As Int = 108dip";
Debug.ShouldStop(512);
_itemspacing = main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 108)));Debug.locals.put("itemSpacing", _itemspacing);Debug.locals.put("itemSpacing", _itemspacing);
 BA.debugLineNum = 395;BA.debugLine="Dim startTopMargin As Int = 20dip";
Debug.ShouldStop(1024);
_starttopmargin = main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 20)));Debug.locals.put("startTopMargin", _starttopmargin);Debug.locals.put("startTopMargin", _starttopmargin);
 BA.debugLineNum = 397;BA.debugLine="For i = 0 To numstudent - 1";
Debug.ShouldStop(4096);
{
final int step3 = 1;
final int limit3 = RemoteObject.solve(new RemoteObject[] {_numstudent,RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
for (;(step3 > 0 && _i <= limit3) || (step3 < 0 && _i >= limit3) ;_i = ((int)(0 + _i + step3))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 398;BA.debugLine="Dim currentTop As Int = startTopMargin + (i * it";
Debug.ShouldStop(8192);
_currenttop = RemoteObject.solve(new RemoteObject[] {_starttopmargin,(RemoteObject.solve(new RemoteObject[] {RemoteObject.createImmutable(_i),_itemspacing}, "*",0, 1))}, "+",1, 1);Debug.locals.put("currentTop", _currenttop);Debug.locals.put("currentTop", _currenttop);
 BA.debugLineNum = 401;BA.debugLine="Dim pnlShadow As Panel";
Debug.ShouldStop(65536);
_pnlshadow = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");Debug.locals.put("pnlShadow", _pnlshadow);
 BA.debugLineNum = 402;BA.debugLine="pnlShadow.Initialize(\"pnlShadow\")";
Debug.ShouldStop(131072);
_pnlshadow.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("pnlShadow")));
 BA.debugLineNum = 404;BA.debugLine="Dim cd As ColorDrawable";
Debug.ShouldStop(524288);
_cd = RemoteObject.createNew ("anywheresoftware.b4a.objects.drawable.ColorDrawable");Debug.locals.put("cd", _cd);
 BA.debugLineNum = 405;BA.debugLine="cd.Initialize2(0xFF000000, 4dip, 0dip, 0xFF00000";
Debug.ShouldStop(1048576);
_cd.runVoidMethod ("Initialize2",(Object)(BA.numberCast(int.class, ((int)0xff000000))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 4)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 0)))),(Object)(BA.numberCast(int.class, ((int)0xff000000))));
 BA.debugLineNum = 406;BA.debugLine="pnlShadow.Background = cd";
Debug.ShouldStop(2097152);
_pnlshadow.runMethod(false,"setBackground",(_cd.getObject()));
 BA.debugLineNum = 407;BA.debugLine="pnlShadow.Elevation = 0";
Debug.ShouldStop(4194304);
_pnlshadow.runMethod(true,"setElevation",BA.numberCast(float.class, 0));
 BA.debugLineNum = 409;BA.debugLine="Dim pnlWidth As Int = 330dip";
Debug.ShouldStop(16777216);
_pnlwidth = main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 330)));Debug.locals.put("pnlWidth", _pnlwidth);Debug.locals.put("pnlWidth", _pnlwidth);
 BA.debugLineNum = 410;BA.debugLine="Dim pnlHeight As Int = 88dip";
Debug.ShouldStop(33554432);
_pnlheight = main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 88)));Debug.locals.put("pnlHeight", _pnlheight);Debug.locals.put("pnlHeight", _pnlheight);
 BA.debugLineNum = 412;BA.debugLine="Dim shadowLeftPos As Int = gradepnl.Width - 13di";
Debug.ShouldStop(134217728);
_shadowleftpos = RemoteObject.solve(new RemoteObject[] {main.mostCurrent._gradepnl.runMethod(true,"getWidth"),main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 13))),_pnlwidth}, "--",2, 1);Debug.locals.put("shadowLeftPos", _shadowleftpos);Debug.locals.put("shadowLeftPos", _shadowleftpos);
 BA.debugLineNum = 413;BA.debugLine="Dim shadowTopPos As Int = currentTop + 7dip";
Debug.ShouldStop(268435456);
_shadowtoppos = RemoteObject.solve(new RemoteObject[] {_currenttop,main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 7)))}, "+",1, 1);Debug.locals.put("shadowTopPos", _shadowtoppos);Debug.locals.put("shadowTopPos", _shadowtoppos);
 BA.debugLineNum = 415;BA.debugLine="gradepnl.AddView(pnlShadow, shadowLeftPos, shado";
Debug.ShouldStop(1073741824);
main.mostCurrent._gradepnl.runVoidMethod ("AddView",(Object)((_pnlshadow.getObject())),(Object)(_shadowleftpos),(Object)(_shadowtoppos),(Object)(_pnlwidth),(Object)(_pnlheight));
 BA.debugLineNum = 417;BA.debugLine="Dim name As String = studentNames.Get(i)";
Debug.ShouldStop(1);
_name = BA.ObjectToString(_studentnames.runMethod(false,"Get",(Object)(BA.numberCast(int.class, _i))));Debug.locals.put("name", _name);Debug.locals.put("name", _name);
 BA.debugLineNum = 418;BA.debugLine="Dim grade As String = GetStudentGrade(name)";
Debug.ShouldStop(2);
_grade = _getstudentgrade(_name);Debug.locals.put("grade", _grade);Debug.locals.put("grade", _grade);
 BA.debugLineNum = 419;BA.debugLine="studentgrade(name, grade, currentTop)";
Debug.ShouldStop(4);
_studentgrade(_name,_grade,_currenttop);
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 421;BA.debugLine="End Sub";
Debug.ShouldStop(16);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _percentagetograde(RemoteObject _pct) throws Exception{
try {
		Debug.PushSubsStack("PercentageToGrade (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,219);
if (RapidSub.canDelegate("percentagetograde")) { return b4a.example.main.remoteMe.runUserSub(false, "main","percentagetograde", _pct);}
Debug.locals.put("pct", _pct);
 BA.debugLineNum = 219;BA.debugLine="Sub PercentageToGrade(pct As Double) As String";
Debug.ShouldStop(67108864);
 BA.debugLineNum = 220;BA.debugLine="If pct >= 90 Then Return \"A\"";
Debug.ShouldStop(134217728);
if (RemoteObject.solveBoolean("g",_pct,BA.numberCast(double.class, 90))) { 
if (true) return BA.ObjectToString("A");};
 BA.debugLineNum = 221;BA.debugLine="If pct >= 80 Then Return \"B\"";
Debug.ShouldStop(268435456);
if (RemoteObject.solveBoolean("g",_pct,BA.numberCast(double.class, 80))) { 
if (true) return BA.ObjectToString("B");};
 BA.debugLineNum = 222;BA.debugLine="If pct >= 70 Then Return \"C\"";
Debug.ShouldStop(536870912);
if (RemoteObject.solveBoolean("g",_pct,BA.numberCast(double.class, 70))) { 
if (true) return BA.ObjectToString("C");};
 BA.debugLineNum = 223;BA.debugLine="If pct >= 60 Then Return \"D\"";
Debug.ShouldStop(1073741824);
if (RemoteObject.solveBoolean("g",_pct,BA.numberCast(double.class, 60))) { 
if (true) return BA.ObjectToString("D");};
 BA.debugLineNum = 224;BA.debugLine="Return \"F\"";
Debug.ShouldStop(-2147483648);
if (true) return BA.ObjectToString("F");
 BA.debugLineNum = 225;BA.debugLine="End Sub";
Debug.ShouldStop(1);
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
 //BA.debugLineNum = 19;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 20;BA.debugLine="Private xui As XUI";
main._xui = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XViewWrapper.XUI");
 //BA.debugLineNum = 21;BA.debugLine="Dim StringUtils1 As StringUtils";
main._stringutils1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.StringUtils");
 //BA.debugLineNum = 22;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _profilebtn_click() throws Exception{
try {
		Debug.PushSubsStack("profilebtn_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,179);
if (RapidSub.canDelegate("profilebtn_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","profilebtn_click");}
 BA.debugLineNum = 179;BA.debugLine="Private Sub profilebtn_Click";
Debug.ShouldStop(262144);
 BA.debugLineNum = 180;BA.debugLine="HighlightTab(profilebtn)";
Debug.ShouldStop(524288);
_highlighttab(main.mostCurrent._profilebtn);
 BA.debugLineNum = 181;BA.debugLine="showprofile";
Debug.ShouldStop(1048576);
_showprofile();
 BA.debugLineNum = 182;BA.debugLine="End Sub";
Debug.ShouldStop(2097152);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _savestudentdata() throws Exception{
try {
		Debug.PushSubsStack("SaveStudentData (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,202);
if (RapidSub.canDelegate("savestudentdata")) { return b4a.example.main.remoteMe.runUserSub(false, "main","savestudentdata");}
 BA.debugLineNum = 202;BA.debugLine="Sub SaveStudentData";
Debug.ShouldStop(512);
 BA.debugLineNum = 203;BA.debugLine="StringUtils1.SaveCSV2(File.DirInternal, \"citylist";
Debug.ShouldStop(1024);
main._stringutils1.runVoidMethod ("SaveCSV2",(Object)(main.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirInternal")),(Object)(BA.ObjectToString("citylist.csv")),(Object)(BA.ObjectToChar(",")),(Object)(main.mostCurrent._studentlist),(Object)(main.mostCurrent._csvheaders));
 BA.debugLineNum = 204;BA.debugLine="Log(\"Saved \" & StudentList.Size & \" student recor";
Debug.ShouldStop(2048);
main.mostCurrent.__c.runVoidMethod ("LogImpl","516580610",RemoteObject.concat(RemoteObject.createImmutable("Saved "),main.mostCurrent._studentlist.runMethod(true,"getSize"),RemoteObject.createImmutable(" student records")),0);
 BA.debugLineNum = 205;BA.debugLine="End Sub";
Debug.ShouldStop(4096);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _setgradient(RemoteObject _pnl,RemoteObject _color1,RemoteObject _color2) throws Exception{
try {
		Debug.PushSubsStack("SetGradient (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,270);
if (RapidSub.canDelegate("setgradient")) { return b4a.example.main.remoteMe.runUserSub(false, "main","setgradient", _pnl, _color1, _color2);}
RemoteObject _gd = RemoteObject.declareNull("anywheresoftware.b4a.objects.drawable.GradientDrawable");
Debug.locals.put("pnl", _pnl);
Debug.locals.put("Color1", _color1);
Debug.locals.put("Color2", _color2);
 BA.debugLineNum = 270;BA.debugLine="Sub SetGradient(pnl As Panel, Color1 As Int, Color";
Debug.ShouldStop(8192);
 BA.debugLineNum = 271;BA.debugLine="Dim gd As GradientDrawable";
Debug.ShouldStop(16384);
_gd = RemoteObject.createNew ("anywheresoftware.b4a.objects.drawable.GradientDrawable");Debug.locals.put("gd", _gd);
 BA.debugLineNum = 272;BA.debugLine="gd.Initialize(\"BR_TL\", Array As Int(Color1, Color";
Debug.ShouldStop(32768);
_gd.runVoidMethod ("Initialize",(Object)(BA.getEnumFromString(BA.getDeviceClass("android.graphics.drawable.GradientDrawable.Orientation"),"BR_TL")),(Object)(RemoteObject.createNewArray("int",new int[] {2},new Object[] {_color1,_color2})));
 BA.debugLineNum = 273;BA.debugLine="pnl.Background = gd";
Debug.ShouldStop(65536);
_pnl.runMethod(false,"setBackground",(_gd.getObject()));
 BA.debugLineNum = 274;BA.debugLine="End Sub";
Debug.ShouldStop(131072);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _showcreatform(RemoteObject _name,RemoteObject _id,RemoteObject _activitytitle,RemoteObject _rate,RemoteObject _attendance) throws Exception{
try {
		Debug.PushSubsStack("ShowCREATForm (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,594);
if (RapidSub.canDelegate("showcreatform")) { return b4a.example.main.remoteMe.runUserSub(false, "main","showcreatform", _name, _id, _activitytitle, _rate, _attendance);}
RemoteObject _btnsave = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
Debug.locals.put("name", _name);
Debug.locals.put("id", _id);
Debug.locals.put("activityTitle", _activitytitle);
Debug.locals.put("rate", _rate);
Debug.locals.put("attendance", _attendance);
 BA.debugLineNum = 594;BA.debugLine="Sub ShowCREATForm(name As String, id As String, ac";
Debug.ShouldStop(131072);
 BA.debugLineNum = 595;BA.debugLine="pnlmain.RemoveAllViews";
Debug.ShouldStop(262144);
main.mostCurrent._pnlmain.runVoidMethod ("RemoveAllViews");
 BA.debugLineNum = 596;BA.debugLine="pnlmain.LoadLayout(\"CREAT\")";
Debug.ShouldStop(524288);
main.mostCurrent._pnlmain.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("CREAT")),main.mostCurrent.activityBA);
 BA.debugLineNum = 599;BA.debugLine="EditText.Text = name         ' Student Name";
Debug.ShouldStop(4194304);
main.mostCurrent._edittext.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(_name));
 BA.debugLineNum = 600;BA.debugLine="EditText3.Text = id          ' Student ID";
Debug.ShouldStop(8388608);
main.mostCurrent._edittext3.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(_id));
 BA.debugLineNum = 601;BA.debugLine="EditText1.Text = activityTitle    ' Activity Titl";
Debug.ShouldStop(16777216);
main.mostCurrent._edittext1.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(_activitytitle));
 BA.debugLineNum = 602;BA.debugLine="EditText2.Text = rate        ' Result";
Debug.ShouldStop(33554432);
main.mostCurrent._edittext2.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(_rate));
 BA.debugLineNum = 603;BA.debugLine="CheckBox1.Checked = attendance";
Debug.ShouldStop(67108864);
main.mostCurrent._checkbox1.runMethodAndSync(true,"setChecked",_attendance);
 BA.debugLineNum = 606;BA.debugLine="Dim btnSave As Button";
Debug.ShouldStop(536870912);
_btnsave = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");Debug.locals.put("btnSave", _btnsave);
 BA.debugLineNum = 607;BA.debugLine="btnSave.Initialize(\"btnSave\")";
Debug.ShouldStop(1073741824);
_btnsave.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("btnSave")));
 BA.debugLineNum = 608;BA.debugLine="If EditingRowIndex >= 0 Then";
Debug.ShouldStop(-2147483648);
if (RemoteObject.solveBoolean("g",main._editingrowindex,BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 609;BA.debugLine="btnSave.Text = \"UPDATE RECORD\"";
Debug.ShouldStop(1);
_btnsave.runMethod(true,"setText",BA.ObjectToCharSequence("UPDATE RECORD"));
 }else {
 BA.debugLineNum = 611;BA.debugLine="btnSave.Text = \"SAVE RECORD\"";
Debug.ShouldStop(4);
_btnsave.runMethod(true,"setText",BA.ObjectToCharSequence("SAVE RECORD"));
 };
 BA.debugLineNum = 613;BA.debugLine="btnSave.TextSize = 16";
Debug.ShouldStop(16);
_btnsave.runMethod(true,"setTextSize",BA.numberCast(float.class, 16));
 BA.debugLineNum = 614;BA.debugLine="btnSave.TextColor = Colors.Black";
Debug.ShouldStop(32);
_btnsave.runMethod(true,"setTextColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 BA.debugLineNum = 615;BA.debugLine="btnSave.Color = 0xFFFFD400";
Debug.ShouldStop(64);
_btnsave.runVoidMethod ("setColor",BA.numberCast(int.class, ((int)0xffffd400)));
 BA.debugLineNum = 616;BA.debugLine="btnSave.Typeface = Typeface.DEFAULT_BOLD";
Debug.ShouldStop(128);
_btnsave.runMethod(false,"setTypeface",main.mostCurrent.__c.getField(false,"Typeface").getField(false,"DEFAULT_BOLD"));
 BA.debugLineNum = 619;BA.debugLine="pnlmain.AddView(btnSave, 20dip, pnlmain.Height -";
Debug.ShouldStop(1024);
main.mostCurrent._pnlmain.runVoidMethod ("AddView",(Object)((_btnsave.getObject())),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 20)))),(Object)(RemoteObject.solve(new RemoteObject[] {main.mostCurrent._pnlmain.runMethod(true,"getHeight"),main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 120)))}, "-",1, 1)),(Object)(RemoteObject.solve(new RemoteObject[] {main.mostCurrent._pnlmain.runMethod(true,"getWidth"),main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 40)))}, "-",1, 1)),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 50)))));
 BA.debugLineNum = 620;BA.debugLine="End Sub";
Debug.ShouldStop(2048);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _showdashboard() throws Exception{
try {
		Debug.PushSubsStack("showdashboard (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,280);
if (RapidSub.canDelegate("showdashboard")) { return b4a.example.main.remoteMe.runUserSub(false, "main","showdashboard");}
 BA.debugLineNum = 280;BA.debugLine="Sub showdashboard";
Debug.ShouldStop(8388608);
 BA.debugLineNum = 281;BA.debugLine="pnlmain.RemoveAllViews";
Debug.ShouldStop(16777216);
main.mostCurrent._pnlmain.runVoidMethod ("RemoveAllViews");
 BA.debugLineNum = 282;BA.debugLine="pnlmain.LoadLayout(\"dashboard\")";
Debug.ShouldStop(33554432);
main.mostCurrent._pnlmain.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("dashboard")),main.mostCurrent.activityBA);
 BA.debugLineNum = 283;BA.debugLine="generategraph";
Debug.ShouldStop(67108864);
_generategraph();
 BA.debugLineNum = 284;BA.debugLine="End Sub";
Debug.ShouldStop(134217728);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _showedit() throws Exception{
try {
		Debug.PushSubsStack("showedit (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,473);
if (RapidSub.canDelegate("showedit")) { return b4a.example.main.remoteMe.runUserSub(false, "main","showedit");}
 BA.debugLineNum = 473;BA.debugLine="Sub showedit";
Debug.ShouldStop(16777216);
 BA.debugLineNum = 474;BA.debugLine="pnlmain.RemoveAllViews";
Debug.ShouldStop(33554432);
main.mostCurrent._pnlmain.runVoidMethod ("RemoveAllViews");
 BA.debugLineNum = 475;BA.debugLine="pnlmain.LoadLayout(\"edit\")";
Debug.ShouldStop(67108864);
main.mostCurrent._pnlmain.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("edit")),main.mostCurrent.activityBA);
 BA.debugLineNum = 476;BA.debugLine="BuildEditList";
Debug.ShouldStop(134217728);
_buildeditlist();
 BA.debugLineNum = 477;BA.debugLine="End Sub";
Debug.ShouldStop(268435456);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _showgrades() throws Exception{
try {
		Debug.PushSubsStack("showgrades (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,379);
if (RapidSub.canDelegate("showgrades")) { return b4a.example.main.remoteMe.runUserSub(false, "main","showgrades");}
RemoteObject _names = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _studentsize = RemoteObject.createImmutable(0);
 BA.debugLineNum = 379;BA.debugLine="Sub showgrades";
Debug.ShouldStop(67108864);
 BA.debugLineNum = 380;BA.debugLine="pnlmain.RemoveAllViews";
Debug.ShouldStop(134217728);
main.mostCurrent._pnlmain.runVoidMethod ("RemoveAllViews");
 BA.debugLineNum = 381;BA.debugLine="pnlmain.LoadLayout(\"grades\")";
Debug.ShouldStop(268435456);
main.mostCurrent._pnlmain.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("grades")),main.mostCurrent.activityBA);
 BA.debugLineNum = 383;BA.debugLine="gradepnl = SV1.Panel";
Debug.ShouldStop(1073741824);
main.mostCurrent._gradepnl = main.mostCurrent._sv1.runMethod(false,"getPanel");
 BA.debugLineNum = 384;BA.debugLine="gradepnl.Width = SV1.Width";
Debug.ShouldStop(-2147483648);
main.mostCurrent._gradepnl.runMethod(true,"setWidth",main.mostCurrent._sv1.runMethod(true,"getWidth"));
 BA.debugLineNum = 386;BA.debugLine="Dim names As List = GetUniqueStudentNames";
Debug.ShouldStop(2);
_names = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_names = _getuniquestudentnames();Debug.locals.put("names", _names);Debug.locals.put("names", _names);
 BA.debugLineNum = 387;BA.debugLine="Dim studentSize As Int = names.Size";
Debug.ShouldStop(4);
_studentsize = _names.runMethod(true,"getSize");Debug.locals.put("studentSize", _studentsize);Debug.locals.put("studentSize", _studentsize);
 BA.debugLineNum = 389;BA.debugLine="gradepnl.Height = (studentSize * 108dip) + 20dip";
Debug.ShouldStop(16);
main.mostCurrent._gradepnl.runMethod(true,"setHeight",RemoteObject.solve(new RemoteObject[] {(RemoteObject.solve(new RemoteObject[] {_studentsize,main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 108)))}, "*",0, 1)),main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 20)))}, "+",1, 1));
 BA.debugLineNum = 390;BA.debugLine="makeshadow(studentSize, names)";
Debug.ShouldStop(32);
_makeshadow(_studentsize,_names);
 BA.debugLineNum = 391;BA.debugLine="End Sub";
Debug.ShouldStop(64);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _showprofile() throws Exception{
try {
		Debug.PushSubsStack("showprofile (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,704);
if (RapidSub.canDelegate("showprofile")) { return b4a.example.main.remoteMe.runUserSub(false, "main","showprofile");}
 BA.debugLineNum = 704;BA.debugLine="Sub showprofile";
Debug.ShouldStop(-2147483648);
 BA.debugLineNum = 705;BA.debugLine="HighlightTab(profilebtn)";
Debug.ShouldStop(1);
_highlighttab(main.mostCurrent._profilebtn);
 BA.debugLineNum = 706;BA.debugLine="ShowTable";
Debug.ShouldStop(2);
_showtable();
 BA.debugLineNum = 707;BA.debugLine="End Sub";
Debug.ShouldStop(4);
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
		Debug.PushSubsStack("ShowTable (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,709);
if (RapidSub.canDelegate("showtable")) { return b4a.example.main.remoteMe.runUserSub(false, "main","showtable");}
 BA.debugLineNum = 709;BA.debugLine="Sub ShowTable";
Debug.ShouldStop(16);
 BA.debugLineNum = 710;BA.debugLine="pnlmain.RemoveAllViews";
Debug.ShouldStop(32);
main.mostCurrent._pnlmain.runVoidMethod ("RemoveAllViews");
 BA.debugLineNum = 711;BA.debugLine="pnlmain.LoadLayout(\"profile\")";
Debug.ShouldStop(64);
main.mostCurrent._pnlmain.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("profile")),main.mostCurrent.activityBA);
 BA.debugLineNum = 712;BA.debugLine="Table = SV.Panel";
Debug.ShouldStop(128);
main.mostCurrent._table = main.mostCurrent._sv.runMethod(false,"getPanel");
 BA.debugLineNum = 713;BA.debugLine="NumberOfColumns = 6";
Debug.ShouldStop(256);
main._numberofcolumns = BA.numberCast(int.class, 6);
 BA.debugLineNum = 714;BA.debugLine="ColumnWidth = SV.Width / NumberOfColumns";
Debug.ShouldStop(512);
main._columnwidth = BA.numberCast(int.class, RemoteObject.solve(new RemoteObject[] {main.mostCurrent._sv.runMethod(true,"getWidth"),main._numberofcolumns}, "/",0, 0));
 BA.debugLineNum = 715;BA.debugLine="loadtable";
Debug.ShouldStop(1024);
_loadtable();
 BA.debugLineNum = 716;BA.debugLine="End Sub";
Debug.ShouldStop(2048);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _studentgrade(RemoteObject _studentname,RemoteObject _grade,RemoteObject _currenttop) throws Exception{
try {
		Debug.PushSubsStack("studentgrade (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,423);
if (RapidSub.canDelegate("studentgrade")) { return b4a.example.main.remoteMe.runUserSub(false, "main","studentgrade", _studentname, _grade, _currenttop);}
RemoteObject _pnlcard = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
RemoteObject _cd = RemoteObject.declareNull("anywheresoftware.b4a.objects.drawable.ColorDrawable");
RemoteObject _pnlwidth = RemoteObject.createImmutable(0);
RemoteObject _pnlheight = RemoteObject.createImmutable(0);
RemoteObject _rightedgedistance = RemoteObject.createImmutable(0);
RemoteObject _leftpos = RemoteObject.createImmutable(0);
RemoteObject _toppos = RemoteObject.createImmutable(0);
RemoteObject _lblgrade = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
RemoteObject _graderightedge = RemoteObject.createImmutable(0);
RemoteObject _gradewidth = RemoteObject.createImmutable(0);
RemoteObject _lbl = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
Debug.locals.put("studentName", _studentname);
Debug.locals.put("grade", _grade);
Debug.locals.put("currentTop", _currenttop);
 BA.debugLineNum = 423;BA.debugLine="Sub studentgrade(studentName As String, grade As S";
Debug.ShouldStop(64);
 BA.debugLineNum = 424;BA.debugLine="Dim pnlCard As Panel";
Debug.ShouldStop(128);
_pnlcard = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");Debug.locals.put("pnlCard", _pnlcard);
 BA.debugLineNum = 425;BA.debugLine="pnlCard.Initialize(\"pnlCard\")";
Debug.ShouldStop(256);
_pnlcard.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("pnlCard")));
 BA.debugLineNum = 427;BA.debugLine="Dim cd As ColorDrawable";
Debug.ShouldStop(1024);
_cd = RemoteObject.createNew ("anywheresoftware.b4a.objects.drawable.ColorDrawable");Debug.locals.put("cd", _cd);
 BA.debugLineNum = 428;BA.debugLine="cd.Initialize2(0xFFFFD400, 2dip, 2dip, 0xFF000000";
Debug.ShouldStop(2048);
_cd.runVoidMethod ("Initialize2",(Object)(BA.numberCast(int.class, ((int)0xffffd400))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 2)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 2)))),(Object)(BA.numberCast(int.class, ((int)0xff000000))));
 BA.debugLineNum = 429;BA.debugLine="pnlCard.Background = cd";
Debug.ShouldStop(4096);
_pnlcard.runMethod(false,"setBackground",(_cd.getObject()));
 BA.debugLineNum = 430;BA.debugLine="pnlCard.Elevation = 0";
Debug.ShouldStop(8192);
_pnlcard.runMethod(true,"setElevation",BA.numberCast(float.class, 0));
 BA.debugLineNum = 432;BA.debugLine="Dim pnlWidth As Int = 330dip";
Debug.ShouldStop(32768);
_pnlwidth = main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 330)));Debug.locals.put("pnlWidth", _pnlwidth);Debug.locals.put("pnlWidth", _pnlwidth);
 BA.debugLineNum = 433;BA.debugLine="Dim pnlHeight As Int = 88dip";
Debug.ShouldStop(65536);
_pnlheight = main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 88)));Debug.locals.put("pnlHeight", _pnlheight);Debug.locals.put("pnlHeight", _pnlheight);
 BA.debugLineNum = 434;BA.debugLine="Dim rightEdgeDistance As Int = 20dip";
Debug.ShouldStop(131072);
_rightedgedistance = main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 20)));Debug.locals.put("rightEdgeDistance", _rightedgedistance);Debug.locals.put("rightEdgeDistance", _rightedgedistance);
 BA.debugLineNum = 436;BA.debugLine="Dim leftPos As Int = gradepnl.Width - rightEdgeDi";
Debug.ShouldStop(524288);
_leftpos = RemoteObject.solve(new RemoteObject[] {main.mostCurrent._gradepnl.runMethod(true,"getWidth"),_rightedgedistance,_pnlwidth}, "--",2, 1);Debug.locals.put("leftPos", _leftpos);Debug.locals.put("leftPos", _leftpos);
 BA.debugLineNum = 437;BA.debugLine="Dim topPos As Int = currentTop";
Debug.ShouldStop(1048576);
_toppos = _currenttop;Debug.locals.put("topPos", _toppos);Debug.locals.put("topPos", _toppos);
 BA.debugLineNum = 439;BA.debugLine="gradepnl.AddView(pnlCard, leftPos, topPos, pnlWid";
Debug.ShouldStop(4194304);
main.mostCurrent._gradepnl.runVoidMethod ("AddView",(Object)((_pnlcard.getObject())),(Object)(_leftpos),(Object)(_toppos),(Object)(_pnlwidth),(Object)(_pnlheight));
 BA.debugLineNum = 442;BA.debugLine="Dim lblGrade As Label";
Debug.ShouldStop(33554432);
_lblgrade = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("lblGrade", _lblgrade);
 BA.debugLineNum = 443;BA.debugLine="lblGrade.Initialize(\"lblGrade\")";
Debug.ShouldStop(67108864);
_lblgrade.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("lblGrade")));
 BA.debugLineNum = 444;BA.debugLine="lblGrade.Text = grade";
Debug.ShouldStop(134217728);
_lblgrade.runMethod(true,"setText",BA.ObjectToCharSequence(_grade));
 BA.debugLineNum = 445;BA.debugLine="lblGrade.TextSize = 30";
Debug.ShouldStop(268435456);
_lblgrade.runMethod(true,"setTextSize",BA.numberCast(float.class, 30));
 BA.debugLineNum = 446;BA.debugLine="lblGrade.TextColor = Colors.Black";
Debug.ShouldStop(536870912);
_lblgrade.runMethod(true,"setTextColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 BA.debugLineNum = 447;BA.debugLine="lblGrade.Gravity = Bit.Or(Gravity.CENTER_HORIZONT";
Debug.ShouldStop(1073741824);
_lblgrade.runMethod(true,"setGravity",main.mostCurrent.__c.getField(false,"Bit").runMethod(true,"Or",(Object)(main.mostCurrent.__c.getField(false,"Gravity").getField(true,"CENTER_HORIZONTAL")),(Object)(main.mostCurrent.__c.getField(false,"Gravity").getField(true,"CENTER_VERTICAL"))));
 BA.debugLineNum = 448;BA.debugLine="lblGrade.Typeface = Typeface.CreateNew(Typeface.S";
Debug.ShouldStop(-2147483648);
_lblgrade.runMethod(false,"setTypeface",main.mostCurrent.__c.getField(false,"Typeface").runMethod(false,"CreateNew",(Object)(main.mostCurrent.__c.getField(false,"Typeface").getField(false,"SERIF")),(Object)(main.mostCurrent.__c.getField(false,"Typeface").getField(true,"STYLE_BOLD_ITALIC"))));
 BA.debugLineNum = 449;BA.debugLine="lblGrade.SingleLine = False";
Debug.ShouldStop(1);
_lblgrade.runVoidMethod ("setSingleLine",main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 450;BA.debugLine="lblGrade.Enabled = True";
Debug.ShouldStop(2);
_lblgrade.runMethod(true,"setEnabled",main.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 451;BA.debugLine="lblGrade.Visible = True";
Debug.ShouldStop(4);
_lblgrade.runMethod(true,"setVisible",main.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 453;BA.debugLine="Dim gradeRightEdge As Int = 264dip";
Debug.ShouldStop(16);
_graderightedge = main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 264)));Debug.locals.put("gradeRightEdge", _graderightedge);Debug.locals.put("gradeRightEdge", _graderightedge);
 BA.debugLineNum = 454;BA.debugLine="Dim gradeWidth As Int = pnlWidth - gradeRightEdge";
Debug.ShouldStop(32);
_gradewidth = RemoteObject.solve(new RemoteObject[] {_pnlwidth,_graderightedge}, "-",1, 1);Debug.locals.put("gradeWidth", _gradewidth);Debug.locals.put("gradeWidth", _gradewidth);
 BA.debugLineNum = 455;BA.debugLine="pnlCard.AddView(lblGrade, 0, 0, gradeWidth, pnlHe";
Debug.ShouldStop(64);
_pnlcard.runVoidMethod ("AddView",(Object)((_lblgrade.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(_gradewidth),(Object)(_pnlheight));
 BA.debugLineNum = 458;BA.debugLine="Dim lbl As Label";
Debug.ShouldStop(512);
_lbl = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("lbl", _lbl);
 BA.debugLineNum = 459;BA.debugLine="lbl.Initialize(\"lbl\")";
Debug.ShouldStop(1024);
_lbl.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("lbl")));
 BA.debugLineNum = 460;BA.debugLine="lbl.Text = studentName";
Debug.ShouldStop(2048);
_lbl.runMethod(true,"setText",BA.ObjectToCharSequence(_studentname));
 BA.debugLineNum = 461;BA.debugLine="lbl.Gravity = Gravity.CENTER_VERTICAL";
Debug.ShouldStop(4096);
_lbl.runMethod(true,"setGravity",main.mostCurrent.__c.getField(false,"Gravity").getField(true,"CENTER_VERTICAL"));
 BA.debugLineNum = 462;BA.debugLine="lbl.Typeface = Typeface.CreateNew(Typeface.SERIF,";
Debug.ShouldStop(8192);
_lbl.runMethod(false,"setTypeface",main.mostCurrent.__c.getField(false,"Typeface").runMethod(false,"CreateNew",(Object)(main.mostCurrent.__c.getField(false,"Typeface").getField(false,"SERIF")),(Object)(main.mostCurrent.__c.getField(false,"Typeface").getField(true,"STYLE_BOLD_ITALIC"))));
 BA.debugLineNum = 463;BA.debugLine="lbl.TextSize = 20";
Debug.ShouldStop(16384);
_lbl.runMethod(true,"setTextSize",BA.numberCast(float.class, 20));
 BA.debugLineNum = 464;BA.debugLine="lbl.TextColor = Colors.Black";
Debug.ShouldStop(32768);
_lbl.runMethod(true,"setTextColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 BA.debugLineNum = 465;BA.debugLine="lbl.Padding = Array As Int(10dip, 5dip, 10dip, 5d";
Debug.ShouldStop(65536);
_lbl.runMethod(false,"setPadding",RemoteObject.createNewArray("int",new int[] {4},new Object[] {main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10))),main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 5))),main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10))),main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 5)))}));
 BA.debugLineNum = 466;BA.debugLine="pnlCard.AddView(lbl, gradeWidth, 0, pnlWidth - gr";
Debug.ShouldStop(131072);
_pnlcard.runVoidMethod ("AddView",(Object)((_lbl.getObject())),(Object)(_gradewidth),(Object)(BA.numberCast(int.class, 0)),(Object)(RemoteObject.solve(new RemoteObject[] {_pnlwidth,_gradewidth}, "-",1, 1)),(Object)(_pnlheight));
 BA.debugLineNum = 467;BA.debugLine="End Sub";
Debug.ShouldStop(262144);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}