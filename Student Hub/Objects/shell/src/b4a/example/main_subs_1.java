package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class main_subs_1 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,99);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_create", _firsttime);}
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 99;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(4);
 BA.debugLineNum = 100;BA.debugLine="Activity.LoadLayout(\"main\")";
Debug.ShouldStop(8);
main.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("main")),main.mostCurrent.activityBA);
 BA.debugLineNum = 102;BA.debugLine="initdrawer";
Debug.ShouldStop(32);
_initdrawer();
 BA.debugLineNum = 105;BA.debugLine="HeaderColor = Colors.Transparent";
Debug.ShouldStop(256);
main._headercolor = main.mostCurrent.__c.getField(false,"Colors").getField(true,"Transparent");
 BA.debugLineNum = 106;BA.debugLine="NumberOfColumns = 6";
Debug.ShouldStop(512);
main._numberofcolumns = BA.numberCast(int.class, 6);
 BA.debugLineNum = 107;BA.debugLine="RowHeight = 30dip";
Debug.ShouldStop(1024);
main._rowheight = main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 30)));
 BA.debugLineNum = 108;BA.debugLine="FontColor = Colors.Black";
Debug.ShouldStop(2048);
main._fontcolor = main.mostCurrent.__c.getField(false,"Colors").getField(true,"Black");
 BA.debugLineNum = 109;BA.debugLine="HeaderFontColor = Colors.Black";
Debug.ShouldStop(4096);
main._headerfontcolor = main.mostCurrent.__c.getField(false,"Colors").getField(true,"Black");
 BA.debugLineNum = 110;BA.debugLine="FontSize = 14";
Debug.ShouldStop(8192);
main._fontsize = BA.numberCast(float.class, 14);
 BA.debugLineNum = 111;BA.debugLine="Alignment = Gravity.LEFT";
Debug.ShouldStop(16384);
main._alignment = main.mostCurrent.__c.getField(false,"Gravity").getField(true,"LEFT");
 BA.debugLineNum = 114;BA.debugLine="LoadStudentData";
Debug.ShouldStop(131072);
_loadstudentdata();
 BA.debugLineNum = 116;BA.debugLine="showedit";
Debug.ShouldStop(524288);
_showedit();
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
public static RemoteObject  _activity_pause(RemoteObject _userclosed) throws Exception{
try {
		Debug.PushSubsStack("Activity_Pause (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,122);
if (RapidSub.canDelegate("activity_pause")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_pause", _userclosed);}
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 122;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(33554432);
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
public static RemoteObject  _activity_resume() throws Exception{
try {
		Debug.PushSubsStack("Activity_Resume (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,119);
if (RapidSub.canDelegate("activity_resume")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_resume");}
 BA.debugLineNum = 119;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(4194304);
 BA.debugLineNum = 120;BA.debugLine="End Sub";
Debug.ShouldStop(8388608);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _add_click() throws Exception{
try {
		Debug.PushSubsStack("add_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,995);
if (RapidSub.canDelegate("add_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","add_click");}
 BA.debugLineNum = 995;BA.debugLine="Private Sub add_Click";
Debug.ShouldStop(4);
 BA.debugLineNum = 996;BA.debugLine="editRowIndex = -1";
Debug.ShouldStop(8);
main._editrowindex = BA.numberCast(int.class, -(double) (0 + 1));
 BA.debugLineNum = 997;BA.debugLine="todelete = False";
Debug.ShouldStop(16);
main._todelete = main.mostCurrent.__c.getField(true,"False");
 BA.debugLineNum = 998;BA.debugLine="pnlmain.RemoveAllViews";
Debug.ShouldStop(32);
main.mostCurrent._pnlmain.runVoidMethod ("RemoveAllViews");
 BA.debugLineNum = 999;BA.debugLine="pnlmain.LoadLayout(\"CREAT\")";
Debug.ShouldStop(64);
main.mostCurrent._pnlmain.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("CREAT")),main.mostCurrent.activityBA);
 BA.debugLineNum = 1000;BA.debugLine="loadspinner";
Debug.ShouldStop(128);
_loadspinner();
 BA.debugLineNum = 1003;BA.debugLine="STUDENT_NAME.Text = \"\"";
Debug.ShouldStop(1024);
main.mostCurrent._student_name.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 1004;BA.debugLine="STUDENT_ID.Text = \"\"";
Debug.ShouldStop(2048);
main.mostCurrent._student_id.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 1005;BA.debugLine="EditText1.Text = \"\"";
Debug.ShouldStop(4096);
main.mostCurrent._edittext1.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 1006;BA.debugLine="EditText2.Text = \"\"";
Debug.ShouldStop(8192);
main.mostCurrent._edittext2.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 1007;BA.debugLine="CheckBox1.Checked = True";
Debug.ShouldStop(16384);
main.mostCurrent._checkbox1.runMethodAndSync(true,"setChecked",main.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 1008;BA.debugLine="CheckBox2.Checked = False";
Debug.ShouldStop(32768);
main.mostCurrent._checkbox2.runMethodAndSync(true,"setChecked",main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 1009;BA.debugLine="CheckBox3.Checked = False";
Debug.ShouldStop(65536);
main.mostCurrent._checkbox3.runMethodAndSync(true,"setChecked",main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 1010;BA.debugLine="End Sub";
Debug.ShouldStop(131072);
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
		Debug.PushSubsStack("AddTableRow (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,750);
if (RapidSub.canDelegate("addtablerow")) { return b4a.example.main.remoteMe.runUserSub(false, "main","addtablerow", _values, _rowindex);}
int _i = 0;
RemoteObject _l = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
RemoteObject _rc = RemoteObject.declareNull("b4a.example.main._rowcol");
Debug.locals.put("Values", _values);
Debug.locals.put("rowIndex", _rowindex);
 BA.debugLineNum = 750;BA.debugLine="Sub AddTableRow(Values() As String, rowIndex As In";
Debug.ShouldStop(8192);
 BA.debugLineNum = 751;BA.debugLine="If Values.Length <> NumberOfColumns Then";
Debug.ShouldStop(16384);
if (RemoteObject.solveBoolean("!",_values.getField(true,"length"),BA.numberCast(double.class, main._numberofcolumns))) { 
 BA.debugLineNum = 752;BA.debugLine="Log(\"Wrong number of values.\")";
Debug.ShouldStop(32768);
main.mostCurrent.__c.runVoidMethod ("LogImpl","82818050",RemoteObject.createImmutable("Wrong number of values."),0);
 BA.debugLineNum = 753;BA.debugLine="Return";
Debug.ShouldStop(65536);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 755;BA.debugLine="For i = 0 To NumberOfColumns - 1";
Debug.ShouldStop(262144);
{
final int step5 = 1;
final int limit5 = RemoteObject.solve(new RemoteObject[] {main._numberofcolumns,RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
for (;(step5 > 0 && _i <= limit5) || (step5 < 0 && _i >= limit5) ;_i = ((int)(0 + _i + step5))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 756;BA.debugLine="Dim l As Label";
Debug.ShouldStop(524288);
_l = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("l", _l);
 BA.debugLineNum = 757;BA.debugLine="l.Initialize(\"cell\")";
Debug.ShouldStop(1048576);
_l.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("cell")));
 BA.debugLineNum = 758;BA.debugLine="l.Text = Values(i)";
Debug.ShouldStop(2097152);
_l.runMethod(true,"setText",BA.ObjectToCharSequence(_values.getArrayElement(true,BA.numberCast(int.class, _i))));
 BA.debugLineNum = 759;BA.debugLine="l.Gravity = Alignment";
Debug.ShouldStop(4194304);
_l.runMethod(true,"setGravity",main._alignment);
 BA.debugLineNum = 760;BA.debugLine="l.TextSize = FontSize";
Debug.ShouldStop(8388608);
_l.runMethod(true,"setTextSize",main._fontsize);
 BA.debugLineNum = 761;BA.debugLine="l.TextColor = FontColor";
Debug.ShouldStop(16777216);
_l.runMethod(true,"setTextColor",main._fontcolor);
 BA.debugLineNum = 762;BA.debugLine="l.Padding = Array As Int(10dip, 0, 0, 0)";
Debug.ShouldStop(33554432);
_l.runMethod(false,"setPadding",RemoteObject.createNewArray("int",new int[] {4},new Object[] {main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10))),BA.numberCast(int.class, 0),BA.numberCast(int.class, 0),BA.numberCast(int.class, 0)}));
 BA.debugLineNum = 763;BA.debugLine="Dim rc As RowCol";
Debug.ShouldStop(67108864);
_rc = RemoteObject.createNew ("b4a.example.main._rowcol");Debug.locals.put("rc", _rc);
 BA.debugLineNum = 764;BA.debugLine="rc.Initialize";
Debug.ShouldStop(134217728);
_rc.runVoidMethod ("Initialize");
 BA.debugLineNum = 765;BA.debugLine="rc.Col = i";
Debug.ShouldStop(268435456);
_rc.setField ("Col" /*RemoteObject*/ ,BA.numberCast(int.class, _i));
 BA.debugLineNum = 766;BA.debugLine="rc.Row = rowIndex";
Debug.ShouldStop(536870912);
_rc.setField ("Row" /*RemoteObject*/ ,_rowindex);
 BA.debugLineNum = 767;BA.debugLine="l.Tag = rc";
Debug.ShouldStop(1073741824);
_l.runMethod(false,"setTag",(_rc));
 BA.debugLineNum = 768;BA.debugLine="Table.AddView(l, ColumnWidth * i, RowHeight * ro";
Debug.ShouldStop(-2147483648);
main.mostCurrent._table.runVoidMethod ("AddView",(Object)((_l.getObject())),(Object)(RemoteObject.solve(new RemoteObject[] {main._columnwidth,RemoteObject.createImmutable(_i)}, "*",0, 1)),(Object)(RemoteObject.solve(new RemoteObject[] {main._rowheight,_rowindex}, "*",0, 1)),(Object)(main._columnwidth),(Object)(main._rowheight));
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 770;BA.debugLine="End Sub";
Debug.ShouldStop(2);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _bak_click() throws Exception{
try {
		Debug.PushSubsStack("bak_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,970);
if (RapidSub.canDelegate("bak_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","bak_click");}
 BA.debugLineNum = 970;BA.debugLine="Private Sub bak_Click";
Debug.ShouldStop(512);
 BA.debugLineNum = 971;BA.debugLine="If isselectingid Then";
Debug.ShouldStop(1024);
if (main._isselectingid.<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 972;BA.debugLine="showselectid = False";
Debug.ShouldStop(2048);
main._showselectid = main.mostCurrent.__c.getField(true,"False");
 BA.debugLineNum = 973;BA.debugLine="isselectingid = False";
Debug.ShouldStop(4096);
main._isselectingid = main.mostCurrent.__c.getField(true,"False");
 BA.debugLineNum = 974;BA.debugLine="update.Enabled = True";
Debug.ShouldStop(8192);
main.mostCurrent._update.runMethod(true,"setEnabled",main.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 975;BA.debugLine="showselectids";
Debug.ShouldStop(16384);
_showselectids();
 }else {
 BA.debugLineNum = 977;BA.debugLine="showselectids";
Debug.ShouldStop(65536);
_showselectids();
 BA.debugLineNum = 978;BA.debugLine="MsgboxAsync(\"run\", \"works\") ' Fixed warning #34";
Debug.ShouldStop(131072);
main.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("run")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("works"))),main.processBA);
 BA.debugLineNum = 979;BA.debugLine="Return";
Debug.ShouldStop(262144);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 981;BA.debugLine="End Sub";
Debug.ShouldStop(1048576);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _btn_click() throws Exception{
try {
		Debug.PushSubsStack("btn_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,873);
if (RapidSub.canDelegate("btn_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","btn_click");}
RemoteObject _serch_id = RemoteObject.createImmutable("");
int _i = 0;
RemoteObject _row = null;
RemoteObject _studentname = RemoteObject.createImmutable("");
RemoteObject _subject = RemoteObject.createImmutable("");
RemoteObject _activity11 = RemoteObject.createImmutable("");
RemoteObject _attendance = RemoteObject.createImmutable("");
RemoteObject _rate = RemoteObject.createImmutable("");
RemoteObject _parts = null;
 BA.debugLineNum = 873;BA.debugLine="Private Sub btn_Click";
Debug.ShouldStop(256);
 BA.debugLineNum = 875;BA.debugLine="Dim serch_id As String = spinner.SelectedItem";
Debug.ShouldStop(1024);
_serch_id = main.mostCurrent._spinner.runMethod(true,"getSelectedItem");Debug.locals.put("serch_id", _serch_id);Debug.locals.put("serch_id", _serch_id);
 BA.debugLineNum = 878;BA.debugLine="showselectid = False";
Debug.ShouldStop(8192);
main._showselectid = main.mostCurrent.__c.getField(true,"False");
 BA.debugLineNum = 879;BA.debugLine="isselectingid = False";
Debug.ShouldStop(16384);
main._isselectingid = main.mostCurrent.__c.getField(true,"False");
 BA.debugLineNum = 880;BA.debugLine="update.Enabled = True";
Debug.ShouldStop(32768);
main.mostCurrent._update.runMethod(true,"setEnabled",main.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 881;BA.debugLine="showselectids";
Debug.ShouldStop(65536);
_showselectids();
 BA.debugLineNum = 884;BA.debugLine="editRowIndex = -1";
Debug.ShouldStop(524288);
main._editrowindex = BA.numberCast(int.class, -(double) (0 + 1));
 BA.debugLineNum = 885;BA.debugLine="For i = 0 To StudentList.Size - 1";
Debug.ShouldStop(1048576);
{
final int step7 = 1;
final int limit7 = RemoteObject.solve(new RemoteObject[] {main.mostCurrent._studentlist.runMethod(true,"getSize"),RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
for (;(step7 > 0 && _i <= limit7) || (step7 < 0 && _i >= limit7) ;_i = ((int)(0 + _i + step7))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 886;BA.debugLine="Dim row() As String = StudentList.Get(i)";
Debug.ShouldStop(2097152);
_row = (main.mostCurrent._studentlist.runMethod(false,"Get",(Object)(BA.numberCast(int.class, _i))));Debug.locals.put("row", _row);Debug.locals.put("row", _row);
 BA.debugLineNum = 887;BA.debugLine="If row(1) = serch_id Then";
Debug.ShouldStop(4194304);
if (RemoteObject.solveBoolean("=",_row.getArrayElement(true,BA.numberCast(int.class, 1)),_serch_id)) { 
 BA.debugLineNum = 888;BA.debugLine="editRowIndex = i";
Debug.ShouldStop(8388608);
main._editrowindex = BA.numberCast(int.class, _i);
 BA.debugLineNum = 889;BA.debugLine="Exit";
Debug.ShouldStop(16777216);
if (true) break;
 };
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 894;BA.debugLine="If editRowIndex > -1 Then";
Debug.ShouldStop(536870912);
if (RemoteObject.solveBoolean(">",main._editrowindex,BA.numberCast(double.class, -(double) (0 + 1)))) { 
 BA.debugLineNum = 895;BA.debugLine="If todelete Then";
Debug.ShouldStop(1073741824);
if (main._todelete.<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 896;BA.debugLine="Dim row() As String = StudentList.Get(editRowIn";
Debug.ShouldStop(-2147483648);
_row = (main.mostCurrent._studentlist.runMethod(false,"Get",(Object)(main._editrowindex)));Debug.locals.put("row", _row);Debug.locals.put("row", _row);
 BA.debugLineNum = 897;BA.debugLine="Dim studentName As String = row(0)";
Debug.ShouldStop(1);
_studentname = _row.getArrayElement(true,BA.numberCast(int.class, 0));Debug.locals.put("studentName", _studentname);Debug.locals.put("studentName", _studentname);
 BA.debugLineNum = 900;BA.debugLine="StudentList.RemoveAt(editRowIndex)";
Debug.ShouldStop(8);
main.mostCurrent._studentlist.runVoidMethod ("RemoveAt",(Object)(main._editrowindex));
 BA.debugLineNum = 903;BA.debugLine="StringUtils1.SaveCSV2(File.DirInternal, \"studen";
Debug.ShouldStop(64);
main._stringutils1.runVoidMethod ("SaveCSV2",(Object)(main.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirInternal")),(Object)(BA.ObjectToString("student.csv")),(Object)(BA.ObjectToChar(",")),(Object)(main.mostCurrent._studentlist),(Object)(main.mostCurrent._csvheaders));
 BA.debugLineNum = 906;BA.debugLine="LoadStudentData";
Debug.ShouldStop(512);
_loadstudentdata();
 BA.debugLineNum = 908;BA.debugLine="xui.MsgboxAsync(\"Deleted record for \" & student";
Debug.ShouldStop(2048);
main._xui.runVoidMethod ("MsgboxAsync",main.processBA,(Object)(BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("Deleted record for "),_studentname,RemoteObject.createImmutable(" successfully.")))),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Success"))));
 BA.debugLineNum = 910;BA.debugLine="editRowIndex = -1";
Debug.ShouldStop(8192);
main._editrowindex = BA.numberCast(int.class, -(double) (0 + 1));
 BA.debugLineNum = 911;BA.debugLine="todelete = False";
Debug.ShouldStop(16384);
main._todelete = main.mostCurrent.__c.getField(true,"False");
 BA.debugLineNum = 912;BA.debugLine="Return";
Debug.ShouldStop(32768);
if (true) return RemoteObject.createImmutable("");
 };
 };
 BA.debugLineNum = 917;BA.debugLine="pnlmain.RemoveAllViews";
Debug.ShouldStop(1048576);
main.mostCurrent._pnlmain.runVoidMethod ("RemoveAllViews");
 BA.debugLineNum = 918;BA.debugLine="pnlmain.LoadLayout(\"CREAT\")";
Debug.ShouldStop(2097152);
main.mostCurrent._pnlmain.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("CREAT")),main.mostCurrent.activityBA);
 BA.debugLineNum = 920;BA.debugLine="If editRowIndex > -1 Then";
Debug.ShouldStop(8388608);
if (RemoteObject.solveBoolean(">",main._editrowindex,BA.numberCast(double.class, -(double) (0 + 1)))) { 
 BA.debugLineNum = 921;BA.debugLine="Dim row() As String = StudentList.Get(editRowInd";
Debug.ShouldStop(16777216);
_row = (main.mostCurrent._studentlist.runMethod(false,"Get",(Object)(main._editrowindex)));Debug.locals.put("row", _row);Debug.locals.put("row", _row);
 BA.debugLineNum = 922;BA.debugLine="STUDENT_NAME.Text = row(0)";
Debug.ShouldStop(33554432);
main.mostCurrent._student_name.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(_row.getArrayElement(true,BA.numberCast(int.class, 0))));
 BA.debugLineNum = 923;BA.debugLine="STUDENT_ID.Text = row(1)";
Debug.ShouldStop(67108864);
main.mostCurrent._student_id.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(_row.getArrayElement(true,BA.numberCast(int.class, 1))));
 BA.debugLineNum = 926;BA.debugLine="loadspinner";
Debug.ShouldStop(536870912);
_loadspinner();
 BA.debugLineNum = 929;BA.debugLine="Dim subject As String = row(2)";
Debug.ShouldStop(1);
_subject = _row.getArrayElement(true,BA.numberCast(int.class, 2));Debug.locals.put("subject", _subject);Debug.locals.put("subject", _subject);
 BA.debugLineNum = 930;BA.debugLine="For i = 0 To SELECTSUBJECTS.Size - 1";
Debug.ShouldStop(2);
{
final int step35 = 1;
final int limit35 = RemoteObject.solve(new RemoteObject[] {main.mostCurrent._selectsubjects.runMethod(true,"getSize"),RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
for (;(step35 > 0 && _i <= limit35) || (step35 < 0 && _i >= limit35) ;_i = ((int)(0 + _i + step35))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 931;BA.debugLine="If SELECTSUBJECTS.GetItem(i) = subject Then";
Debug.ShouldStop(4);
if (RemoteObject.solveBoolean("=",main.mostCurrent._selectsubjects.runMethod(true,"GetItem",(Object)(BA.numberCast(int.class, _i))),_subject)) { 
 BA.debugLineNum = 932;BA.debugLine="SELECTSUBJECTS.SelectedIndex = i";
Debug.ShouldStop(8);
main.mostCurrent._selectsubjects.runMethod(true,"setSelectedIndex",BA.numberCast(int.class, _i));
 BA.debugLineNum = 933;BA.debugLine="Exit";
Debug.ShouldStop(16);
if (true) break;
 };
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 938;BA.debugLine="Dim activity11 As String = row(3)";
Debug.ShouldStop(512);
_activity11 = _row.getArrayElement(true,BA.numberCast(int.class, 3));Debug.locals.put("activity11", _activity11);Debug.locals.put("activity11", _activity11);
 BA.debugLineNum = 939;BA.debugLine="For i = 0 To STUDENT_ACTIVITY.Size - 1";
Debug.ShouldStop(1024);
{
final int step42 = 1;
final int limit42 = RemoteObject.solve(new RemoteObject[] {main.mostCurrent._student_activity.runMethod(true,"getSize"),RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
for (;(step42 > 0 && _i <= limit42) || (step42 < 0 && _i >= limit42) ;_i = ((int)(0 + _i + step42))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 940;BA.debugLine="If STUDENT_ACTIVITY.GetItem(i) = activity11 The";
Debug.ShouldStop(2048);
if (RemoteObject.solveBoolean("=",main.mostCurrent._student_activity.runMethod(true,"GetItem",(Object)(BA.numberCast(int.class, _i))),_activity11)) { 
 BA.debugLineNum = 941;BA.debugLine="STUDENT_ACTIVITY.SelectedIndex = i";
Debug.ShouldStop(4096);
main.mostCurrent._student_activity.runMethod(true,"setSelectedIndex",BA.numberCast(int.class, _i));
 BA.debugLineNum = 942;BA.debugLine="Exit";
Debug.ShouldStop(8192);
if (true) break;
 };
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 947;BA.debugLine="Dim attendance As String = row(4)";
Debug.ShouldStop(262144);
_attendance = _row.getArrayElement(true,BA.numberCast(int.class, 4));Debug.locals.put("attendance", _attendance);Debug.locals.put("attendance", _attendance);
 BA.debugLineNum = 948;BA.debugLine="CheckBox1.Checked = (attendance = \"present\")";
Debug.ShouldStop(524288);
main.mostCurrent._checkbox1.runMethodAndSync(true,"setChecked",BA.ObjectToBoolean((RemoteObject.solveBoolean("=",_attendance,RemoteObject.createImmutable("present")))));
 BA.debugLineNum = 949;BA.debugLine="CheckBox2.Checked = (attendance = \"late\")";
Debug.ShouldStop(1048576);
main.mostCurrent._checkbox2.runMethodAndSync(true,"setChecked",BA.ObjectToBoolean((RemoteObject.solveBoolean("=",_attendance,RemoteObject.createImmutable("late")))));
 BA.debugLineNum = 950;BA.debugLine="CheckBox3.Checked = (attendance = \"absent\")";
Debug.ShouldStop(2097152);
main.mostCurrent._checkbox3.runMethodAndSync(true,"setChecked",BA.ObjectToBoolean((RemoteObject.solveBoolean("=",_attendance,RemoteObject.createImmutable("absent")))));
 BA.debugLineNum = 953;BA.debugLine="Dim rate As String = row(5)";
Debug.ShouldStop(16777216);
_rate = _row.getArrayElement(true,BA.numberCast(int.class, 5));Debug.locals.put("rate", _rate);Debug.locals.put("rate", _rate);
 BA.debugLineNum = 954;BA.debugLine="If rate.Contains(\"/\") Then";
Debug.ShouldStop(33554432);
if (_rate.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("/"))).<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 955;BA.debugLine="Dim parts() As String = Regex.Split(\"/\", rate)";
Debug.ShouldStop(67108864);
_parts = main.mostCurrent.__c.getField(false,"Regex").runMethod(false,"Split",(Object)(BA.ObjectToString("/")),(Object)(_rate));Debug.locals.put("parts", _parts);Debug.locals.put("parts", _parts);
 BA.debugLineNum = 956;BA.debugLine="If parts.Length = 2 Then";
Debug.ShouldStop(134217728);
if (RemoteObject.solveBoolean("=",_parts.getField(true,"length"),BA.numberCast(double.class, 2))) { 
 BA.debugLineNum = 957;BA.debugLine="EditText1.Text = parts(0)";
Debug.ShouldStop(268435456);
main.mostCurrent._edittext1.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(_parts.getArrayElement(true,BA.numberCast(int.class, 0))));
 BA.debugLineNum = 958;BA.debugLine="EditText2.Text = parts(1)";
Debug.ShouldStop(536870912);
main.mostCurrent._edittext2.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(_parts.getArrayElement(true,BA.numberCast(int.class, 1))));
 }else {
 BA.debugLineNum = 960;BA.debugLine="EditText1.Text = rate";
Debug.ShouldStop(-2147483648);
main.mostCurrent._edittext1.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(_rate));
 BA.debugLineNum = 961;BA.debugLine="EditText2.Text = \"\"";
Debug.ShouldStop(1);
main.mostCurrent._edittext2.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(""));
 };
 }else {
 BA.debugLineNum = 964;BA.debugLine="EditText1.Text = rate";
Debug.ShouldStop(8);
main.mostCurrent._edittext1.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(_rate));
 BA.debugLineNum = 965;BA.debugLine="EditText2.Text = \"\"";
Debug.ShouldStop(16);
main.mostCurrent._edittext2.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(""));
 };
 };
 BA.debugLineNum = 968;BA.debugLine="End Sub";
Debug.ShouldStop(128);
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
		Debug.PushSubsStack("btnDashBoard_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,169);
if (RapidSub.canDelegate("btndashboard_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","btndashboard_click");}
 BA.debugLineNum = 169;BA.debugLine="Sub btnDashBoard_Click";
Debug.ShouldStop(256);
 BA.debugLineNum = 170;BA.debugLine="Drawer.LeftOpen = False";
Debug.ShouldStop(512);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_setleftopen" /*RemoteObject*/ ,main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 171;BA.debugLine="showdashboard";
Debug.ShouldStop(1024);
_showdashboard();
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
public static RemoteObject  _btnedit_click() throws Exception{
try {
		Debug.PushSubsStack("btnedit_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,179);
if (RapidSub.canDelegate("btnedit_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","btnedit_click");}
 BA.debugLineNum = 179;BA.debugLine="Sub btnedit_Click";
Debug.ShouldStop(262144);
 BA.debugLineNum = 180;BA.debugLine="Drawer.LeftOpen = False";
Debug.ShouldStop(524288);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_setleftopen" /*RemoteObject*/ ,main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 181;BA.debugLine="showedit";
Debug.ShouldStop(1048576);
_showedit();
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
public static RemoteObject  _btngrades_click() throws Exception{
try {
		Debug.PushSubsStack("btnGrades_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,174);
if (RapidSub.canDelegate("btngrades_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","btngrades_click");}
 BA.debugLineNum = 174;BA.debugLine="Sub btnGrades_Click";
Debug.ShouldStop(8192);
 BA.debugLineNum = 175;BA.debugLine="Drawer.LeftOpen = False";
Debug.ShouldStop(16384);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_setleftopen" /*RemoteObject*/ ,main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 176;BA.debugLine="showgrades";
Debug.ShouldStop(32768);
_showgrades();
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
public static RemoteObject  _btnmenu_click() throws Exception{
try {
		Debug.PushSubsStack("btnMenu_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,129);
if (RapidSub.canDelegate("btnmenu_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","btnmenu_click");}
 BA.debugLineNum = 129;BA.debugLine="Sub btnMenu_Click";
Debug.ShouldStop(1);
 BA.debugLineNum = 130;BA.debugLine="Drawer.LeftOpen = Not(Drawer.LeftOpen)";
Debug.ShouldStop(2);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_setleftopen" /*RemoteObject*/ ,main.mostCurrent.__c.runMethod(true,"Not",(Object)(main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_getleftopen" /*RemoteObject*/ ))));
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
public static RemoteObject  _btnprofile_click() throws Exception{
try {
		Debug.PushSubsStack("btnProfile_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,184);
if (RapidSub.canDelegate("btnprofile_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","btnprofile_click");}
 BA.debugLineNum = 184;BA.debugLine="Sub btnProfile_Click";
Debug.ShouldStop(8388608);
 BA.debugLineNum = 185;BA.debugLine="Drawer.LeftOpen = False";
Debug.ShouldStop(16777216);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_setleftopen" /*RemoteObject*/ ,main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 186;BA.debugLine="showprofile";
Debug.ShouldStop(33554432);
_showprofile();
 BA.debugLineNum = 187;BA.debugLine="End Sub";
Debug.ShouldStop(67108864);
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
		Debug.PushSubsStack("Button1_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,698);
if (RapidSub.canDelegate("button1_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","button1_click");}
 BA.debugLineNum = 698;BA.debugLine="Private Sub Button1_Click";
Debug.ShouldStop(33554432);
 BA.debugLineNum = 700;BA.debugLine="showedit";
Debug.ShouldStop(134217728);
_showedit();
 BA.debugLineNum = 701;BA.debugLine="End Sub";
Debug.ShouldStop(268435456);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _calculatestudentgpa(RemoteObject _studentname) throws Exception{
try {
		Debug.PushSubsStack("CalculateStudentGPA (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,267);
if (RapidSub.canDelegate("calculatestudentgpa")) { return b4a.example.main.remoteMe.runUserSub(false, "main","calculatestudentgpa", _studentname);}
RemoteObject _totalpoints = RemoteObject.createImmutable(0);
RemoteObject _activitycount = RemoteObject.createImmutable(0);
int _i = 0;
RemoteObject _row = null;
RemoteObject _currentname = RemoteObject.createImmutable("");
RemoteObject _ratestring = RemoteObject.createImmutable("");
RemoteObject _pct = RemoteObject.createImmutable(0);
Debug.locals.put("studentName", _studentname);
 BA.debugLineNum = 267;BA.debugLine="Sub CalculateStudentGPA(studentName As String) As";
Debug.ShouldStop(1024);
 BA.debugLineNum = 268;BA.debugLine="Dim totalPoints As Double = 0";
Debug.ShouldStop(2048);
_totalpoints = BA.numberCast(double.class, 0);Debug.locals.put("totalPoints", _totalpoints);Debug.locals.put("totalPoints", _totalpoints);
 BA.debugLineNum = 269;BA.debugLine="Dim activityCount As Int = 0";
Debug.ShouldStop(4096);
_activitycount = BA.numberCast(int.class, 0);Debug.locals.put("activityCount", _activitycount);Debug.locals.put("activityCount", _activitycount);
 BA.debugLineNum = 272;BA.debugLine="For i = 0 To StudentList.Size - 1";
Debug.ShouldStop(32768);
{
final int step3 = 1;
final int limit3 = RemoteObject.solve(new RemoteObject[] {main.mostCurrent._studentlist.runMethod(true,"getSize"),RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
for (;(step3 > 0 && _i <= limit3) || (step3 < 0 && _i >= limit3) ;_i = ((int)(0 + _i + step3))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 273;BA.debugLine="Dim row() As String = StudentList.Get(i)";
Debug.ShouldStop(65536);
_row = (main.mostCurrent._studentlist.runMethod(false,"Get",(Object)(BA.numberCast(int.class, _i))));Debug.locals.put("row", _row);Debug.locals.put("row", _row);
 BA.debugLineNum = 274;BA.debugLine="Dim currentName As String = row(COL_NAME)";
Debug.ShouldStop(131072);
_currentname = _row.getArrayElement(true,main._col_name);Debug.locals.put("currentName", _currentname);Debug.locals.put("currentName", _currentname);
 BA.debugLineNum = 277;BA.debugLine="If currentName = studentName Then";
Debug.ShouldStop(1048576);
if (RemoteObject.solveBoolean("=",_currentname,_studentname)) { 
 BA.debugLineNum = 278;BA.debugLine="Dim rateString As String = row(COL_RATE)";
Debug.ShouldStop(2097152);
_ratestring = _row.getArrayElement(true,main._col_rate);Debug.locals.put("rateString", _ratestring);Debug.locals.put("rateString", _ratestring);
 BA.debugLineNum = 281;BA.debugLine="Dim pct As Double = ComputePercentage(rateStrin";
Debug.ShouldStop(16777216);
_pct = _computepercentage(_ratestring);Debug.locals.put("pct", _pct);Debug.locals.put("pct", _pct);
 BA.debugLineNum = 284;BA.debugLine="totalPoints = totalPoints + PercentageToGrade(p";
Debug.ShouldStop(134217728);
_totalpoints = RemoteObject.solve(new RemoteObject[] {_totalpoints,BA.numberCast(double.class, _percentagetograde(_pct))}, "+",1, 0);Debug.locals.put("totalPoints", _totalpoints);
 BA.debugLineNum = 285;BA.debugLine="activityCount = activityCount + 1";
Debug.ShouldStop(268435456);
_activitycount = RemoteObject.solve(new RemoteObject[] {_activitycount,RemoteObject.createImmutable(1)}, "+",1, 1);Debug.locals.put("activityCount", _activitycount);
 };
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 290;BA.debugLine="If activityCount < 3 Then";
Debug.ShouldStop(2);
if (RemoteObject.solveBoolean("<",_activitycount,BA.numberCast(double.class, 3))) { 
 BA.debugLineNum = 291;BA.debugLine="Return -1.0 ' Return -1.0 as a flag meaning \"Not";
Debug.ShouldStop(4);
if (true) return BA.numberCast(double.class, -1.0);
 };
 BA.debugLineNum = 295;BA.debugLine="Return totalPoints / activityCount";
Debug.ShouldStop(64);
if (true) return RemoteObject.solve(new RemoteObject[] {_totalpoints,_activitycount}, "/",0, 0);
 BA.debugLineNum = 296;BA.debugLine="End Sub";
Debug.ShouldStop(128);
return RemoteObject.createImmutable(0);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _checkbox1_click() throws Exception{
try {
		Debug.PushSubsStack("CheckBox1_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,1012);
if (RapidSub.canDelegate("checkbox1_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","checkbox1_click");}
 BA.debugLineNum = 1012;BA.debugLine="Private Sub CheckBox1_Click";
Debug.ShouldStop(524288);
 BA.debugLineNum = 1013;BA.debugLine="If CheckBox1.Checked Then";
Debug.ShouldStop(1048576);
if (main.mostCurrent._checkbox1.runMethod(true,"getChecked").<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 1014;BA.debugLine="CheckBox2.Checked = False";
Debug.ShouldStop(2097152);
main.mostCurrent._checkbox2.runMethodAndSync(true,"setChecked",main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 1015;BA.debugLine="CheckBox3.Checked = False";
Debug.ShouldStop(4194304);
main.mostCurrent._checkbox3.runMethodAndSync(true,"setChecked",main.mostCurrent.__c.getField(true,"False"));
 };
 BA.debugLineNum = 1017;BA.debugLine="End Sub";
Debug.ShouldStop(16777216);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _checkbox2_click() throws Exception{
try {
		Debug.PushSubsStack("CheckBox2_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,1019);
if (RapidSub.canDelegate("checkbox2_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","checkbox2_click");}
 BA.debugLineNum = 1019;BA.debugLine="Private Sub CheckBox2_Click";
Debug.ShouldStop(67108864);
 BA.debugLineNum = 1020;BA.debugLine="If CheckBox2.Checked Then";
Debug.ShouldStop(134217728);
if (main.mostCurrent._checkbox2.runMethod(true,"getChecked").<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 1021;BA.debugLine="CheckBox1.Checked = False";
Debug.ShouldStop(268435456);
main.mostCurrent._checkbox1.runMethodAndSync(true,"setChecked",main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 1022;BA.debugLine="CheckBox3.Checked = False";
Debug.ShouldStop(536870912);
main.mostCurrent._checkbox3.runMethodAndSync(true,"setChecked",main.mostCurrent.__c.getField(true,"False"));
 };
 BA.debugLineNum = 1024;BA.debugLine="End Sub";
Debug.ShouldStop(-2147483648);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _checkbox3_click() throws Exception{
try {
		Debug.PushSubsStack("CheckBox3_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,1026);
if (RapidSub.canDelegate("checkbox3_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","checkbox3_click");}
 BA.debugLineNum = 1026;BA.debugLine="Private Sub CheckBox3_Click";
Debug.ShouldStop(2);
 BA.debugLineNum = 1027;BA.debugLine="If CheckBox3.Checked Then";
Debug.ShouldStop(4);
if (main.mostCurrent._checkbox3.runMethod(true,"getChecked").<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 1028;BA.debugLine="CheckBox1.Checked = False";
Debug.ShouldStop(8);
main.mostCurrent._checkbox1.runMethodAndSync(true,"setChecked",main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 1029;BA.debugLine="CheckBox2.Checked = False";
Debug.ShouldStop(16);
main.mostCurrent._checkbox2.runMethodAndSync(true,"setChecked",main.mostCurrent.__c.getField(true,"False"));
 };
 BA.debugLineNum = 1031;BA.debugLine="End Sub";
Debug.ShouldStop(64);
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
		Debug.PushSubsStack("ComputePercentage (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,247);
if (RapidSub.canDelegate("computepercentage")) { return b4a.example.main.remoteMe.runUserSub(false, "main","computepercentage", _rate);}
RemoteObject _parts = null;
RemoteObject _numerator = RemoteObject.createImmutable(0);
RemoteObject _denominator = RemoteObject.createImmutable(0);
Debug.locals.put("rate", _rate);
 BA.debugLineNum = 247;BA.debugLine="Sub ComputePercentage(rate As String) As Double";
Debug.ShouldStop(4194304);
 BA.debugLineNum = 248;BA.debugLine="If rate.Contains(\"/\") = False Then Return 0";
Debug.ShouldStop(8388608);
if (RemoteObject.solveBoolean("=",_rate.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("/"))),main.mostCurrent.__c.getField(true,"False"))) { 
if (true) return BA.numberCast(double.class, 0);};
 BA.debugLineNum = 249;BA.debugLine="Dim parts() As String = Regex.Split(\"/\", rate)";
Debug.ShouldStop(16777216);
_parts = main.mostCurrent.__c.getField(false,"Regex").runMethod(false,"Split",(Object)(BA.ObjectToString("/")),(Object)(_rate));Debug.locals.put("parts", _parts);Debug.locals.put("parts", _parts);
 BA.debugLineNum = 250;BA.debugLine="If parts.Length <> 2 Then Return 0";
Debug.ShouldStop(33554432);
if (RemoteObject.solveBoolean("!",_parts.getField(true,"length"),BA.numberCast(double.class, 2))) { 
if (true) return BA.numberCast(double.class, 0);};
 BA.debugLineNum = 251;BA.debugLine="Dim numerator As Double = parts(0)";
Debug.ShouldStop(67108864);
_numerator = BA.numberCast(double.class, _parts.getArrayElement(true,BA.numberCast(int.class, 0)));Debug.locals.put("numerator", _numerator);Debug.locals.put("numerator", _numerator);
 BA.debugLineNum = 252;BA.debugLine="Dim denominator As Double = parts(1)";
Debug.ShouldStop(134217728);
_denominator = BA.numberCast(double.class, _parts.getArrayElement(true,BA.numberCast(int.class, 1)));Debug.locals.put("denominator", _denominator);Debug.locals.put("denominator", _denominator);
 BA.debugLineNum = 253;BA.debugLine="If denominator = 0 Then Return 0";
Debug.ShouldStop(268435456);
if (RemoteObject.solveBoolean("=",_denominator,BA.numberCast(double.class, 0))) { 
if (true) return BA.numberCast(double.class, 0);};
 BA.debugLineNum = 254;BA.debugLine="Return (numerator / denominator) * 100";
Debug.ShouldStop(536870912);
if (true) return RemoteObject.solve(new RemoteObject[] {(RemoteObject.solve(new RemoteObject[] {_numerator,_denominator}, "/",0, 0)),RemoteObject.createImmutable(100)}, "*",0, 0);
 BA.debugLineNum = 255;BA.debugLine="End Sub";
Debug.ShouldStop(1073741824);
return RemoteObject.createImmutable(0);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _createmenu() throws Exception{
try {
		Debug.PushSubsStack("CreateMenu (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,145);
if (RapidSub.canDelegate("createmenu")) { return b4a.example.main.remoteMe.runUserSub(false, "main","createmenu");}
RemoteObject _btndashboard = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
RemoteObject _btngrades = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
RemoteObject _btnedit = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
RemoteObject _btnprofile = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
RemoteObject _b = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
 BA.debugLineNum = 145;BA.debugLine="Sub CreateMenu";
Debug.ShouldStop(65536);
 BA.debugLineNum = 146;BA.debugLine="Dim btnDashBoard, btnGrades, btnedit, btnProfile";
Debug.ShouldStop(131072);
_btndashboard = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");Debug.locals.put("btnDashBoard", _btndashboard);
_btngrades = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");Debug.locals.put("btnGrades", _btngrades);
_btnedit = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");Debug.locals.put("btnedit", _btnedit);
_btnprofile = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");Debug.locals.put("btnProfile", _btnprofile);
 BA.debugLineNum = 147;BA.debugLine="btnDashBoard.Initialize(\"btnDashBoard\")";
Debug.ShouldStop(262144);
_btndashboard.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("btnDashBoard")));
 BA.debugLineNum = 148;BA.debugLine="btnDashBoard.Text = \"Dashboard\"";
Debug.ShouldStop(524288);
_btndashboard.runMethod(true,"setText",BA.ObjectToCharSequence("Dashboard"));
 BA.debugLineNum = 149;BA.debugLine="btnGrades.Initialize(\"btnGrades\")";
Debug.ShouldStop(1048576);
_btngrades.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("btnGrades")));
 BA.debugLineNum = 150;BA.debugLine="btnGrades.Text = \"Grades\"";
Debug.ShouldStop(2097152);
_btngrades.runMethod(true,"setText",BA.ObjectToCharSequence("Grades"));
 BA.debugLineNum = 151;BA.debugLine="btnedit.Initialize(\"btnedit\")";
Debug.ShouldStop(4194304);
_btnedit.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("btnedit")));
 BA.debugLineNum = 152;BA.debugLine="btnedit.Text = \"Edit\"";
Debug.ShouldStop(8388608);
_btnedit.runMethod(true,"setText",BA.ObjectToCharSequence("Edit"));
 BA.debugLineNum = 153;BA.debugLine="btnProfile.Initialize(\"btnProfile\")";
Debug.ShouldStop(16777216);
_btnprofile.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("btnProfile")));
 BA.debugLineNum = 154;BA.debugLine="btnProfile.Text = \"Profile\"";
Debug.ShouldStop(33554432);
_btnprofile.runMethod(true,"setText",BA.ObjectToCharSequence("Profile"));
 BA.debugLineNum = 156;BA.debugLine="For Each b As Button In Array(btnDashBoard, btnGr";
Debug.ShouldStop(134217728);
_b = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
{
final RemoteObject group10 = RemoteObject.createNewArray("Object",new int[] {4},new Object[] {(_btndashboard.getObject()),(_btngrades.getObject()),(_btnedit.getObject()),(_btnprofile.getObject())});
final int groupLen10 = group10.getField(true,"length").<Integer>get()
;int index10 = 0;
;
for (; index10 < groupLen10;index10++){
_b = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.ButtonWrapper"), group10.getArrayElement(false,RemoteObject.createImmutable(index10)));Debug.locals.put("b", _b);
Debug.locals.put("b", _b);
 BA.debugLineNum = 157;BA.debugLine="b.TextSize = 16";
Debug.ShouldStop(268435456);
_b.runMethod(true,"setTextSize",BA.numberCast(float.class, 16));
 BA.debugLineNum = 158;BA.debugLine="b.Gravity = Gravity.LEFT + Gravity.CENTER_VERTIC";
Debug.ShouldStop(536870912);
_b.runMethod(true,"setGravity",RemoteObject.solve(new RemoteObject[] {main.mostCurrent.__c.getField(false,"Gravity").getField(true,"LEFT"),main.mostCurrent.__c.getField(false,"Gravity").getField(true,"CENTER_VERTICAL")}, "+",1, 1));
 BA.debugLineNum = 159;BA.debugLine="b.Color = Colors.Transparent";
Debug.ShouldStop(1073741824);
_b.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Transparent"));
 BA.debugLineNum = 160;BA.debugLine="b.TextColor = Colors.White";
Debug.ShouldStop(-2147483648);
_b.runMethod(true,"setTextColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 BA.debugLineNum = 161;BA.debugLine="pnlmenu.AddView(b, 10dip, 0, 240dip, 50dip)";
Debug.ShouldStop(1);
main.mostCurrent._pnlmenu.runVoidMethod ("AddView",(Object)((_b.getObject())),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))),(Object)(BA.numberCast(int.class, 0)),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 240)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 50)))));
 }
}Debug.locals.put("b", _b);
;
 BA.debugLineNum = 163;BA.debugLine="btnDashBoard.Top = 120dip";
Debug.ShouldStop(4);
_btndashboard.runMethod(true,"setTop",main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 120))));
 BA.debugLineNum = 164;BA.debugLine="btnGrades.Top = 180dip";
Debug.ShouldStop(8);
_btngrades.runMethod(true,"setTop",main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 180))));
 BA.debugLineNum = 165;BA.debugLine="btnedit.Top = 240dip";
Debug.ShouldStop(16);
_btnedit.runMethod(true,"setTop",main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 240))));
 BA.debugLineNum = 166;BA.debugLine="btnProfile.Top = 300dip";
Debug.ShouldStop(32);
_btnprofile.runMethod(true,"setTop",main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 300))));
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
public static RemoteObject  _dashbtn_click() throws Exception{
try {
		Debug.PushSubsStack("dashbtn_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,189);
if (RapidSub.canDelegate("dashbtn_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","dashbtn_click");}
 BA.debugLineNum = 189;BA.debugLine="Private Sub dashbtn_Click";
Debug.ShouldStop(268435456);
 BA.debugLineNum = 190;BA.debugLine="HighlightTab(dashbtn)";
Debug.ShouldStop(536870912);
_highlighttab(main.mostCurrent._dashbtn);
 BA.debugLineNum = 191;BA.debugLine="showdashboard";
Debug.ShouldStop(1073741824);
_showdashboard();
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
public static RemoteObject  _delete_click() throws Exception{
try {
		Debug.PushSubsStack("delete_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,989);
if (RapidSub.canDelegate("delete_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","delete_click");}
 BA.debugLineNum = 989;BA.debugLine="Private Sub delete_Click";
Debug.ShouldStop(268435456);
 BA.debugLineNum = 990;BA.debugLine="showselectid = True";
Debug.ShouldStop(536870912);
main._showselectid = main.mostCurrent.__c.getField(true,"True");
 BA.debugLineNum = 991;BA.debugLine="todelete = True";
Debug.ShouldStop(1073741824);
main._todelete = main.mostCurrent.__c.getField(true,"True");
 BA.debugLineNum = 992;BA.debugLine="showselectids";
Debug.ShouldStop(-2147483648);
_showselectids();
 BA.debugLineNum = 993;BA.debugLine="End Sub";
Debug.ShouldStop(1);
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
		Debug.PushSubsStack("editbtn_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,199);
if (RapidSub.canDelegate("editbtn_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","editbtn_click");}
 BA.debugLineNum = 199;BA.debugLine="Private Sub editbtn_Click";
Debug.ShouldStop(64);
 BA.debugLineNum = 200;BA.debugLine="HighlightTab(editbtn)";
Debug.ShouldStop(128);
_highlighttab(main.mostCurrent._editbtn);
 BA.debugLineNum = 201;BA.debugLine="showedit";
Debug.ShouldStop(256);
_showedit();
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
public static RemoteObject  _generategraph() throws Exception{
try {
		Debug.PushSubsStack("generategraph (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,449);
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
RemoteObject _studentavgs = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _topnames = null;
RemoteObject _toprates = null;
RemoteObject _topactivities = null;
int _rank = 0;
RemoteObject _bestidx = RemoteObject.createImmutable(0);
RemoteObject _bestval = RemoteObject.createImmutable(0);
int _j = 0;
RemoteObject _entry = null;
RemoteObject _bestentry = null;
 BA.debugLineNum = 449;BA.debugLine="Sub generategraph";
Debug.ShouldStop(1);
 BA.debugLineNum = 450;BA.debugLine="Dim names As List = GetUniqueStudentNames";
Debug.ShouldStop(2);
_names = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_names = _getuniquestudentnames();Debug.locals.put("names", _names);Debug.locals.put("names", _names);
 BA.debugLineNum = 451;BA.debugLine="If names.Size = 0 Then Return";
Debug.ShouldStop(4);
if (RemoteObject.solveBoolean("=",_names.runMethod(true,"getSize"),BA.numberCast(double.class, 0))) { 
if (true) return RemoteObject.createImmutable("");};
 BA.debugLineNum = 453;BA.debugLine="Dim numCols As Int = names.Size";
Debug.ShouldStop(16);
_numcols = _names.runMethod(true,"getSize");Debug.locals.put("numCols", _numcols);Debug.locals.put("numCols", _numcols);
 BA.debugLineNum = 454;BA.debugLine="Dim colWidth As Int = datapnl.Width / numCols";
Debug.ShouldStop(32);
_colwidth = BA.numberCast(int.class, RemoteObject.solve(new RemoteObject[] {main.mostCurrent._datapnl.runMethod(true,"getWidth"),_numcols}, "/",0, 0));Debug.locals.put("colWidth", _colwidth);Debug.locals.put("colWidth", _colwidth);
 BA.debugLineNum = 457;BA.debugLine="Dim barColors() As Int = Array As Int( _ 		Colors";
Debug.ShouldStop(256);
_barcolors = RemoteObject.createNewArray("int",new int[] {5},new Object[] {main.mostCurrent.__c.getField(false,"Colors").runMethod(true,"RGB",(Object)(BA.numberCast(int.class, 66)),(Object)(BA.numberCast(int.class, 133)),(Object)(BA.numberCast(int.class, 244))),main.mostCurrent.__c.getField(false,"Colors").runMethod(true,"RGB",(Object)(BA.numberCast(int.class, 234)),(Object)(BA.numberCast(int.class, 67)),(Object)(BA.numberCast(int.class, 53))),main.mostCurrent.__c.getField(false,"Colors").runMethod(true,"RGB",(Object)(BA.numberCast(int.class, 251)),(Object)(BA.numberCast(int.class, 188)),(Object)(BA.numberCast(int.class, 4))),main.mostCurrent.__c.getField(false,"Colors").runMethod(true,"RGB",(Object)(BA.numberCast(int.class, 52)),(Object)(BA.numberCast(int.class, 168)),(Object)(BA.numberCast(int.class, 83))),main.mostCurrent.__c.getField(false,"Colors").runMethod(true,"RGB",(Object)(BA.numberCast(int.class, 156)),(Object)(BA.numberCast(int.class, 39)),(Object)(BA.numberCast(int.class, 176)))});Debug.locals.put("barColors", _barcolors);Debug.locals.put("barColors", _barcolors);
 BA.debugLineNum = 465;BA.debugLine="Dim maxVal As Double = 0";
Debug.ShouldStop(65536);
_maxval = BA.numberCast(double.class, 0);Debug.locals.put("maxVal", _maxval);Debug.locals.put("maxVal", _maxval);
 BA.debugLineNum = 466;BA.debugLine="For i = 0 To names.Size - 1";
Debug.ShouldStop(131072);
{
final int step7 = 1;
final int limit7 = RemoteObject.solve(new RemoteObject[] {_names.runMethod(true,"getSize"),RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
for (;(step7 > 0 && _i <= limit7) || (step7 < 0 && _i >= limit7) ;_i = ((int)(0 + _i + step7))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 467;BA.debugLine="Dim avg As Double = GetStudentAvgPercentage(name";
Debug.ShouldStop(262144);
_avg = _getstudentavgpercentage(BA.ObjectToString(_names.runMethod(false,"Get",(Object)(BA.numberCast(int.class, _i)))));Debug.locals.put("avg", _avg);Debug.locals.put("avg", _avg);
 BA.debugLineNum = 468;BA.debugLine="If avg > maxVal Then maxVal = avg";
Debug.ShouldStop(524288);
if (RemoteObject.solveBoolean(">",_avg,_maxval)) { 
_maxval = _avg;Debug.locals.put("maxVal", _maxval);};
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 470;BA.debugLine="If maxVal = 0 Then maxVal = 100";
Debug.ShouldStop(2097152);
if (RemoteObject.solveBoolean("=",_maxval,BA.numberCast(double.class, 0))) { 
_maxval = BA.numberCast(double.class, 100);Debug.locals.put("maxVal", _maxval);};
 BA.debugLineNum = 472;BA.debugLine="Dim RowHeight As Int = 40dip";
Debug.ShouldStop(8388608);
main._rowheight = main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 40)));
 BA.debugLineNum = 473;BA.debugLine="Dim valueLabelHeight As Int = 30dip";
Debug.ShouldStop(16777216);
_valuelabelheight = main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 30)));Debug.locals.put("valueLabelHeight", _valuelabelheight);Debug.locals.put("valueLabelHeight", _valuelabelheight);
 BA.debugLineNum = 474;BA.debugLine="Dim chartAreaHeight As Int = datapnl.Height - Row";
Debug.ShouldStop(33554432);
_chartareaheight = RemoteObject.solve(new RemoteObject[] {main.mostCurrent._datapnl.runMethod(true,"getHeight"),main._rowheight,_valuelabelheight}, "--",2, 1);Debug.locals.put("chartAreaHeight", _chartareaheight);Debug.locals.put("chartAreaHeight", _chartareaheight);
 BA.debugLineNum = 475;BA.debugLine="Dim barPadding As Int = 10dip";
Debug.ShouldStop(67108864);
_barpadding = main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)));Debug.locals.put("barPadding", _barpadding);Debug.locals.put("barPadding", _barpadding);
 BA.debugLineNum = 477;BA.debugLine="For i = 0 To numCols - 1";
Debug.ShouldStop(268435456);
{
final int step16 = 1;
final int limit16 = RemoteObject.solve(new RemoteObject[] {_numcols,RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
for (;(step16 > 0 && _i <= limit16) || (step16 < 0 && _i >= limit16) ;_i = ((int)(0 + _i + step16))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 478;BA.debugLine="Dim studentName As String = names.Get(i)";
Debug.ShouldStop(536870912);
_studentname = BA.ObjectToString(_names.runMethod(false,"Get",(Object)(BA.numberCast(int.class, _i))));Debug.locals.put("studentName", _studentname);Debug.locals.put("studentName", _studentname);
 BA.debugLineNum = 479;BA.debugLine="Dim avgPct As Double = GetStudentAvgPercentage(s";
Debug.ShouldStop(1073741824);
_avgpct = _getstudentavgpercentage(_studentname);Debug.locals.put("avgPct", _avgpct);Debug.locals.put("avgPct", _avgpct);
 BA.debugLineNum = 482;BA.debugLine="Dim barHeight As Int";
Debug.ShouldStop(2);
_barheight = RemoteObject.createImmutable(0);Debug.locals.put("barHeight", _barheight);
 BA.debugLineNum = 483;BA.debugLine="If maxVal > 0 Then";
Debug.ShouldStop(4);
if (RemoteObject.solveBoolean(">",_maxval,BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 484;BA.debugLine="barHeight = (avgPct / maxVal) * chartAreaHeight";
Debug.ShouldStop(8);
_barheight = BA.numberCast(int.class, RemoteObject.solve(new RemoteObject[] {(RemoteObject.solve(new RemoteObject[] {_avgpct,_maxval}, "/",0, 0)),_chartareaheight}, "*",0, 0));Debug.locals.put("barHeight", _barheight);
 }else {
 BA.debugLineNum = 486;BA.debugLine="barHeight = 0";
Debug.ShouldStop(32);
_barheight = BA.numberCast(int.class, 0);Debug.locals.put("barHeight", _barheight);
 };
 BA.debugLineNum = 489;BA.debugLine="Dim barTop As Int = datapnl.Height - RowHeight -";
Debug.ShouldStop(256);
_bartop = RemoteObject.solve(new RemoteObject[] {main.mostCurrent._datapnl.runMethod(true,"getHeight"),main._rowheight,_barheight}, "--",2, 1);Debug.locals.put("barTop", _bartop);Debug.locals.put("barTop", _bartop);
 BA.debugLineNum = 491;BA.debugLine="Dim pnlBar As Panel";
Debug.ShouldStop(1024);
_pnlbar = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");Debug.locals.put("pnlBar", _pnlbar);
 BA.debugLineNum = 492;BA.debugLine="pnlBar.Initialize(\"pnlBar\")";
Debug.ShouldStop(2048);
_pnlbar.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("pnlBar")));
 BA.debugLineNum = 493;BA.debugLine="pnlBar.Color = barColors(i Mod barColors.Length)";
Debug.ShouldStop(4096);
_pnlbar.runVoidMethod ("setColor",_barcolors.getArrayElement(true,RemoteObject.solve(new RemoteObject[] {RemoteObject.createImmutable(_i),_barcolors.getField(true,"length")}, "%",0, 1)));
 BA.debugLineNum = 494;BA.debugLine="pnlBar.Tag = i";
Debug.ShouldStop(8192);
_pnlbar.runMethod(false,"setTag",RemoteObject.createImmutable((_i)));
 BA.debugLineNum = 495;BA.debugLine="datapnl.AddView(pnlBar, _ 			(colWidth * i) + ba";
Debug.ShouldStop(16384);
main.mostCurrent._datapnl.runVoidMethod ("AddView",(Object)((_pnlbar.getObject())),(Object)(RemoteObject.solve(new RemoteObject[] {(RemoteObject.solve(new RemoteObject[] {_colwidth,RemoteObject.createImmutable(_i)}, "*",0, 1)),_barpadding}, "+",1, 1)),(Object)(_bartop),(Object)(RemoteObject.solve(new RemoteObject[] {_colwidth,(RemoteObject.solve(new RemoteObject[] {_barpadding,RemoteObject.createImmutable(2)}, "*",0, 1))}, "-",1, 1)),(Object)(_barheight));
 BA.debugLineNum = 502;BA.debugLine="Dim lblValue As Label";
Debug.ShouldStop(2097152);
_lblvalue = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("lblValue", _lblvalue);
 BA.debugLineNum = 503;BA.debugLine="lblValue.Initialize(\"lblValue\")";
Debug.ShouldStop(4194304);
_lblvalue.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("lblValue")));
 BA.debugLineNum = 504;BA.debugLine="lblValue.Text = Round2(avgPct, 0) & \"%\"";
Debug.ShouldStop(8388608);
_lblvalue.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(main.mostCurrent.__c.runMethod(true,"Round2",(Object)(_avgpct),(Object)(BA.numberCast(int.class, 0))),RemoteObject.createImmutable("%"))));
 BA.debugLineNum = 505;BA.debugLine="lblValue.Gravity = Gravity.CENTER";
Debug.ShouldStop(16777216);
_lblvalue.runMethod(true,"setGravity",main.mostCurrent.__c.getField(false,"Gravity").getField(true,"CENTER"));
 BA.debugLineNum = 506;BA.debugLine="lblValue.TextSize = 12";
Debug.ShouldStop(33554432);
_lblvalue.runMethod(true,"setTextSize",BA.numberCast(float.class, 12));
 BA.debugLineNum = 507;BA.debugLine="lblValue.TextColor = Colors.Black";
Debug.ShouldStop(67108864);
_lblvalue.runMethod(true,"setTextColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 BA.debugLineNum = 508;BA.debugLine="datapnl.AddView(lblValue, _ 			colWidth * i, _";
Debug.ShouldStop(134217728);
main.mostCurrent._datapnl.runVoidMethod ("AddView",(Object)((_lblvalue.getObject())),(Object)(RemoteObject.solve(new RemoteObject[] {_colwidth,RemoteObject.createImmutable(_i)}, "*",0, 1)),(Object)(RemoteObject.solve(new RemoteObject[] {_bartop,_valuelabelheight}, "-",1, 1)),(Object)(_colwidth),(Object)(_valuelabelheight));
 BA.debugLineNum = 515;BA.debugLine="Dim l As Label";
Debug.ShouldStop(4);
_l = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("l", _l);
 BA.debugLineNum = 516;BA.debugLine="l.Initialize(\"labels\")";
Debug.ShouldStop(8);
_l.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("labels")));
 BA.debugLineNum = 518;BA.debugLine="Dim displayName As String = studentName";
Debug.ShouldStop(32);
_displayname = _studentname;Debug.locals.put("displayName", _displayname);Debug.locals.put("displayName", _displayname);
 BA.debugLineNum = 519;BA.debugLine="If displayName.Length > 8 Then";
Debug.ShouldStop(64);
if (RemoteObject.solveBoolean(">",_displayname.runMethod(true,"length"),BA.numberCast(double.class, 8))) { 
 BA.debugLineNum = 520;BA.debugLine="Dim nameParts() As String = Regex.Split(\" \", di";
Debug.ShouldStop(128);
_nameparts = main.mostCurrent.__c.getField(false,"Regex").runMethod(false,"Split",(Object)(BA.ObjectToString(" ")),(Object)(_displayname));Debug.locals.put("nameParts", _nameparts);Debug.locals.put("nameParts", _nameparts);
 BA.debugLineNum = 521;BA.debugLine="displayName = nameParts(0)";
Debug.ShouldStop(256);
_displayname = _nameparts.getArrayElement(true,BA.numberCast(int.class, 0));Debug.locals.put("displayName", _displayname);
 };
 BA.debugLineNum = 523;BA.debugLine="l.Text = displayName";
Debug.ShouldStop(1024);
_l.runMethod(true,"setText",BA.ObjectToCharSequence(_displayname));
 BA.debugLineNum = 524;BA.debugLine="l.Gravity = Gravity.CENTER";
Debug.ShouldStop(2048);
_l.runMethod(true,"setGravity",main.mostCurrent.__c.getField(false,"Gravity").getField(true,"CENTER"));
 BA.debugLineNum = 525;BA.debugLine="l.TextSize = 10";
Debug.ShouldStop(4096);
_l.runMethod(true,"setTextSize",BA.numberCast(float.class, 10));
 BA.debugLineNum = 526;BA.debugLine="l.Color = 0x00ffffff";
Debug.ShouldStop(8192);
_l.runVoidMethod ("setColor",BA.numberCast(int.class, ((int)0x00ffffff)));
 BA.debugLineNum = 527;BA.debugLine="l.TextColor = Colors.Black";
Debug.ShouldStop(16384);
_l.runMethod(true,"setTextColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 BA.debugLineNum = 528;BA.debugLine="l.Padding = Array As Int(0dip, 5dip, 0dip, 5dip)";
Debug.ShouldStop(32768);
_l.runMethod(false,"setPadding",RemoteObject.createNewArray("int",new int[] {4},new Object[] {main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 0))),main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 5))),main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 0))),main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 5)))}));
 BA.debugLineNum = 529;BA.debugLine="l.Tag = i";
Debug.ShouldStop(65536);
_l.runMethod(false,"setTag",RemoteObject.createImmutable((_i)));
 BA.debugLineNum = 530;BA.debugLine="datapnl.AddView(l, _ 			colWidth * i, _ 			datap";
Debug.ShouldStop(131072);
main.mostCurrent._datapnl.runVoidMethod ("AddView",(Object)((_l.getObject())),(Object)(RemoteObject.solve(new RemoteObject[] {_colwidth,RemoteObject.createImmutable(_i)}, "*",0, 1)),(Object)(RemoteObject.solve(new RemoteObject[] {main.mostCurrent._datapnl.runMethod(true,"getHeight"),main._rowheight}, "-",1, 1)),(Object)(_colwidth),(Object)(main._rowheight));
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 538;BA.debugLine="Dim studentAvgs As List";
Debug.ShouldStop(33554432);
_studentavgs = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");Debug.locals.put("studentAvgs", _studentavgs);
 BA.debugLineNum = 539;BA.debugLine="studentAvgs.Initialize";
Debug.ShouldStop(67108864);
_studentavgs.runVoidMethod ("Initialize");
 BA.debugLineNum = 540;BA.debugLine="For i = 0 To names.Size - 1";
Debug.ShouldStop(134217728);
{
final int step56 = 1;
final int limit56 = RemoteObject.solve(new RemoteObject[] {_names.runMethod(true,"getSize"),RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
for (;(step56 > 0 && _i <= limit56) || (step56 < 0 && _i >= limit56) ;_i = ((int)(0 + _i + step56))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 541;BA.debugLine="Dim avg As Double = GetStudentAvgPercentage(name";
Debug.ShouldStop(268435456);
_avg = _getstudentavgpercentage(BA.ObjectToString(_names.runMethod(false,"Get",(Object)(BA.numberCast(int.class, _i)))));Debug.locals.put("avg", _avg);Debug.locals.put("avg", _avg);
 BA.debugLineNum = 542;BA.debugLine="studentAvgs.Add(Array As Object(names.Get(i), av";
Debug.ShouldStop(536870912);
_studentavgs.runVoidMethod ("Add",(Object)((RemoteObject.createNewArray("Object",new int[] {2},new Object[] {_names.runMethod(false,"Get",(Object)(BA.numberCast(int.class, _i))),(_avg)}))));
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 546;BA.debugLine="Dim topNames(3) As String";
Debug.ShouldStop(2);
_topnames = RemoteObject.createNewArray ("String", new int[] {3}, new Object[]{});Debug.locals.put("topNames", _topnames);
 BA.debugLineNum = 547;BA.debugLine="Dim topRates(3) As Double";
Debug.ShouldStop(4);
_toprates = RemoteObject.createNewArray ("double", new int[] {3}, new Object[]{});Debug.locals.put("topRates", _toprates);
 BA.debugLineNum = 548;BA.debugLine="Dim topActivities(3) As String";
Debug.ShouldStop(8);
_topactivities = RemoteObject.createNewArray ("String", new int[] {3}, new Object[]{});Debug.locals.put("topActivities", _topactivities);
 BA.debugLineNum = 550;BA.debugLine="For rank = 0 To Min(2, studentAvgs.Size - 1)";
Debug.ShouldStop(32);
{
final int step63 = 1;
final int limit63 = (int) (0 + main.mostCurrent.__c.runMethod(true,"Min",(Object)(BA.numberCast(double.class, 2)),(Object)(BA.numberCast(double.class, RemoteObject.solve(new RemoteObject[] {_studentavgs.runMethod(true,"getSize"),RemoteObject.createImmutable(1)}, "-",1, 1)))).<Double>get().doubleValue());
_rank = 0 ;
for (;(step63 > 0 && _rank <= limit63) || (step63 < 0 && _rank >= limit63) ;_rank = ((int)(0 + _rank + step63))  ) {
Debug.locals.put("rank", _rank);
 BA.debugLineNum = 551;BA.debugLine="Dim bestIdx As Int = -1";
Debug.ShouldStop(64);
_bestidx = BA.numberCast(int.class, -(double) (0 + 1));Debug.locals.put("bestIdx", _bestidx);Debug.locals.put("bestIdx", _bestidx);
 BA.debugLineNum = 552;BA.debugLine="Dim bestVal As Double = -1";
Debug.ShouldStop(128);
_bestval = BA.numberCast(double.class, -(double) (0 + 1));Debug.locals.put("bestVal", _bestval);Debug.locals.put("bestVal", _bestval);
 BA.debugLineNum = 553;BA.debugLine="For j = 0 To studentAvgs.Size - 1";
Debug.ShouldStop(256);
{
final int step66 = 1;
final int limit66 = RemoteObject.solve(new RemoteObject[] {_studentavgs.runMethod(true,"getSize"),RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_j = 0 ;
for (;(step66 > 0 && _j <= limit66) || (step66 < 0 && _j >= limit66) ;_j = ((int)(0 + _j + step66))  ) {
Debug.locals.put("j", _j);
 BA.debugLineNum = 554;BA.debugLine="Dim entry() As Object = studentAvgs.Get(j)";
Debug.ShouldStop(512);
_entry = (_studentavgs.runMethod(false,"Get",(Object)(BA.numberCast(int.class, _j))));Debug.locals.put("entry", _entry);Debug.locals.put("entry", _entry);
 BA.debugLineNum = 555;BA.debugLine="If entry(1) > bestVal Then";
Debug.ShouldStop(1024);
if (RemoteObject.solveBoolean(">",BA.numberCast(double.class, _entry.getArrayElement(false,BA.numberCast(int.class, 1))),_bestval)) { 
 BA.debugLineNum = 556;BA.debugLine="bestVal = entry(1)";
Debug.ShouldStop(2048);
_bestval = BA.numberCast(double.class, _entry.getArrayElement(false,BA.numberCast(int.class, 1)));Debug.locals.put("bestVal", _bestval);
 BA.debugLineNum = 557;BA.debugLine="bestIdx = j";
Debug.ShouldStop(4096);
_bestidx = BA.numberCast(int.class, _j);Debug.locals.put("bestIdx", _bestidx);
 };
 }
}Debug.locals.put("j", _j);
;
 BA.debugLineNum = 560;BA.debugLine="If bestIdx >= 0 Then";
Debug.ShouldStop(32768);
if (RemoteObject.solveBoolean("g",_bestidx,BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 561;BA.debugLine="Dim bestEntry() As Object = studentAvgs.Get(bes";
Debug.ShouldStop(65536);
_bestentry = (_studentavgs.runMethod(false,"Get",(Object)(_bestidx)));Debug.locals.put("bestEntry", _bestentry);Debug.locals.put("bestEntry", _bestentry);
 BA.debugLineNum = 562;BA.debugLine="topNames(rank) = bestEntry(0)";
Debug.ShouldStop(131072);
_topnames.setArrayElement (BA.ObjectToString(_bestentry.getArrayElement(false,BA.numberCast(int.class, 0))),BA.numberCast(int.class, _rank));
 BA.debugLineNum = 563;BA.debugLine="topRates(rank) = bestEntry(1)";
Debug.ShouldStop(262144);
_toprates.setArrayElement (BA.numberCast(double.class, _bestentry.getArrayElement(false,BA.numberCast(int.class, 1))),BA.numberCast(int.class, _rank));
 BA.debugLineNum = 564;BA.debugLine="topActivities(rank) = GetStudentTopActivity(bes";
Debug.ShouldStop(524288);
_topactivities.setArrayElement (_getstudenttopactivity(BA.ObjectToString(_bestentry.getArrayElement(false,BA.numberCast(int.class, 0)))),BA.numberCast(int.class, _rank));
 BA.debugLineNum = 565;BA.debugLine="studentAvgs.RemoveAt(bestIdx) ' remove so next";
Debug.ShouldStop(1048576);
_studentavgs.runVoidMethod ("RemoveAt",(Object)(_bestidx));
 };
 }
}Debug.locals.put("rank", _rank);
;
 BA.debugLineNum = 570;BA.debugLine="If topNames(0) <> \"\" Then";
Debug.ShouldStop(33554432);
if (RemoteObject.solveBoolean("!",_topnames.getArrayElement(true,BA.numberCast(int.class, 0)),BA.ObjectToString(""))) { 
 BA.debugLineNum = 571;BA.debugLine="NAME1.Text = topNames(0)";
Debug.ShouldStop(67108864);
main.mostCurrent._name1.runMethod(true,"setText",BA.ObjectToCharSequence(_topnames.getArrayElement(true,BA.numberCast(int.class, 0))));
 BA.debugLineNum = 572;BA.debugLine="RATE1.Text = Round2(topRates(0), 1) & \"%\"";
Debug.ShouldStop(134217728);
main.mostCurrent._rate1.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(main.mostCurrent.__c.runMethod(true,"Round2",(Object)(_toprates.getArrayElement(true,BA.numberCast(int.class, 0))),(Object)(BA.numberCast(int.class, 1))),RemoteObject.createImmutable("%"))));
 BA.debugLineNum = 573;BA.debugLine="ACTIVITY1.Text = topActivities(0)";
Debug.ShouldStop(268435456);
main.mostCurrent._activity1.runMethod(true,"setText",BA.ObjectToCharSequence(_topactivities.getArrayElement(true,BA.numberCast(int.class, 0))));
 };
 BA.debugLineNum = 575;BA.debugLine="If topNames(1) <> \"\" Then";
Debug.ShouldStop(1073741824);
if (RemoteObject.solveBoolean("!",_topnames.getArrayElement(true,BA.numberCast(int.class, 1)),BA.ObjectToString(""))) { 
 BA.debugLineNum = 576;BA.debugLine="NAME2.Text = topNames(1)";
Debug.ShouldStop(-2147483648);
main.mostCurrent._name2.runMethod(true,"setText",BA.ObjectToCharSequence(_topnames.getArrayElement(true,BA.numberCast(int.class, 1))));
 BA.debugLineNum = 577;BA.debugLine="RATE2.Text = Round2(topRates(1), 1) & \"%\"";
Debug.ShouldStop(1);
main.mostCurrent._rate2.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(main.mostCurrent.__c.runMethod(true,"Round2",(Object)(_toprates.getArrayElement(true,BA.numberCast(int.class, 1))),(Object)(BA.numberCast(int.class, 1))),RemoteObject.createImmutable("%"))));
 BA.debugLineNum = 578;BA.debugLine="ACTIVITY2.Text = topActivities(1)";
Debug.ShouldStop(2);
main.mostCurrent._activity2.runMethod(true,"setText",BA.ObjectToCharSequence(_topactivities.getArrayElement(true,BA.numberCast(int.class, 1))));
 };
 BA.debugLineNum = 580;BA.debugLine="If topNames(2) <> \"\" Then";
Debug.ShouldStop(8);
if (RemoteObject.solveBoolean("!",_topnames.getArrayElement(true,BA.numberCast(int.class, 2)),BA.ObjectToString(""))) { 
 BA.debugLineNum = 581;BA.debugLine="NAME3.Text = topNames(2)";
Debug.ShouldStop(16);
main.mostCurrent._name3.runMethod(true,"setText",BA.ObjectToCharSequence(_topnames.getArrayElement(true,BA.numberCast(int.class, 2))));
 BA.debugLineNum = 582;BA.debugLine="RATE3.Text = Round2(topRates(2), 1) & \"%\"";
Debug.ShouldStop(32);
main.mostCurrent._rate3.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(main.mostCurrent.__c.runMethod(true,"Round2",(Object)(_toprates.getArrayElement(true,BA.numberCast(int.class, 2))),(Object)(BA.numberCast(int.class, 1))),RemoteObject.createImmutable("%"))));
 BA.debugLineNum = 583;BA.debugLine="ACTIVITY3.Text = topActivities(2)";
Debug.ShouldStop(64);
main.mostCurrent._activity3.runMethod(true,"setText",BA.ObjectToCharSequence(_topactivities.getArrayElement(true,BA.numberCast(int.class, 2))));
 };
 BA.debugLineNum = 585;BA.debugLine="End Sub";
Debug.ShouldStop(256);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _getactivity(RemoteObject _studentname) throws Exception{
try {
		Debug.PushSubsStack("getActivity (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,417);
if (RapidSub.canDelegate("getactivity")) { return b4a.example.main.remoteMe.runUserSub(false, "main","getactivity", _studentname);}
RemoteObject _activities = RemoteObject.createImmutable("");
int _i = 0;
RemoteObject _row = null;
RemoteObject _name = RemoteObject.createImmutable("");
RemoteObject _activitys = RemoteObject.createImmutable("");
Debug.locals.put("studentName", _studentname);
 BA.debugLineNum = 417;BA.debugLine="Sub getActivity(studentName As String) As String";
Debug.ShouldStop(1);
 BA.debugLineNum = 418;BA.debugLine="Dim activities As String";
Debug.ShouldStop(2);
_activities = RemoteObject.createImmutable("");Debug.locals.put("activities", _activities);
 BA.debugLineNum = 419;BA.debugLine="For i = 0 To StudentList.Size - 1";
Debug.ShouldStop(4);
{
final int step2 = 1;
final int limit2 = RemoteObject.solve(new RemoteObject[] {main.mostCurrent._studentlist.runMethod(true,"getSize"),RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
for (;(step2 > 0 && _i <= limit2) || (step2 < 0 && _i >= limit2) ;_i = ((int)(0 + _i + step2))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 420;BA.debugLine="Dim row() As String = StudentList.Get(i)";
Debug.ShouldStop(8);
_row = (main.mostCurrent._studentlist.runMethod(false,"Get",(Object)(BA.numberCast(int.class, _i))));Debug.locals.put("row", _row);Debug.locals.put("row", _row);
 BA.debugLineNum = 421;BA.debugLine="Dim name As String = row(COL_NAME) ' compare aga";
Debug.ShouldStop(16);
_name = _row.getArrayElement(true,main._col_name);Debug.locals.put("name", _name);Debug.locals.put("name", _name);
 BA.debugLineNum = 422;BA.debugLine="If name = studentName Then";
Debug.ShouldStop(32);
if (RemoteObject.solveBoolean("=",_name,_studentname)) { 
 BA.debugLineNum = 423;BA.debugLine="Dim Activitys As String = row(COL_ACTIVITY)";
Debug.ShouldStop(64);
_activitys = _row.getArrayElement(true,main._col_activity);Debug.locals.put("Activitys", _activitys);Debug.locals.put("Activitys", _activitys);
 BA.debugLineNum = 424;BA.debugLine="If activities.IndexOf(Activitys) = -1 Then";
Debug.ShouldStop(128);
if (RemoteObject.solveBoolean("=",_activities.runMethod(true,"indexOf",(Object)(_activitys)),BA.numberCast(double.class, -(double) (0 + 1)))) { 
 BA.debugLineNum = 425;BA.debugLine="activities = Activitys";
Debug.ShouldStop(256);
_activities = _activitys;Debug.locals.put("activities", _activities);
 };
 };
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 429;BA.debugLine="Return activities";
Debug.ShouldStop(4096);
if (true) return _activities;
 BA.debugLineNum = 430;BA.debugLine="End Sub";
Debug.ShouldStop(8192);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _getaverageattendancescore(RemoteObject _studentname) throws Exception{
try {
		Debug.PushSubsStack("GetAverageAttendanceScore (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,310);
if (RapidSub.canDelegate("getaverageattendancescore")) { return b4a.example.main.remoteMe.runUserSub(false, "main","getaverageattendancescore", _studentname);}
RemoteObject _totalpoints = RemoteObject.createImmutable(0);
RemoteObject _count = RemoteObject.createImmutable(0);
int _i = 0;
RemoteObject _row = null;
RemoteObject _name = RemoteObject.createImmutable("");
RemoteObject _status = RemoteObject.createImmutable("");
Debug.locals.put("studentName", _studentname);
 BA.debugLineNum = 310;BA.debugLine="Sub GetAverageAttendanceScore(studentName As Strin";
Debug.ShouldStop(2097152);
 BA.debugLineNum = 311;BA.debugLine="Dim totalPoints As Int = 0";
Debug.ShouldStop(4194304);
_totalpoints = BA.numberCast(int.class, 0);Debug.locals.put("totalPoints", _totalpoints);Debug.locals.put("totalPoints", _totalpoints);
 BA.debugLineNum = 312;BA.debugLine="Dim count As Int = 0";
Debug.ShouldStop(8388608);
_count = BA.numberCast(int.class, 0);Debug.locals.put("count", _count);Debug.locals.put("count", _count);
 BA.debugLineNum = 314;BA.debugLine="For i = 0 To StudentList.Size - 1";
Debug.ShouldStop(33554432);
{
final int step3 = 1;
final int limit3 = RemoteObject.solve(new RemoteObject[] {main.mostCurrent._studentlist.runMethod(true,"getSize"),RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
for (;(step3 > 0 && _i <= limit3) || (step3 < 0 && _i >= limit3) ;_i = ((int)(0 + _i + step3))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 315;BA.debugLine="Dim row() As String = StudentList.Get(i)";
Debug.ShouldStop(67108864);
_row = (main.mostCurrent._studentlist.runMethod(false,"Get",(Object)(BA.numberCast(int.class, _i))));Debug.locals.put("row", _row);Debug.locals.put("row", _row);
 BA.debugLineNum = 316;BA.debugLine="Dim name As String = row(COL_NAME)";
Debug.ShouldStop(134217728);
_name = _row.getArrayElement(true,main._col_name);Debug.locals.put("name", _name);Debug.locals.put("name", _name);
 BA.debugLineNum = 318;BA.debugLine="If name = studentName Then";
Debug.ShouldStop(536870912);
if (RemoteObject.solveBoolean("=",_name,_studentname)) { 
 BA.debugLineNum = 319;BA.debugLine="Dim status As String = row(4).Trim.ToLowerCase";
Debug.ShouldStop(1073741824);
_status = _row.getArrayElement(true,BA.numberCast(int.class, 4)).runMethod(true,"trim").runMethod(true,"toLowerCase");Debug.locals.put("status", _status);Debug.locals.put("status", _status);
 BA.debugLineNum = 322;BA.debugLine="Select Case status";
Debug.ShouldStop(2);
switch (BA.switchObjectToInt(_status,BA.ObjectToString("present"),BA.ObjectToString("late"),BA.ObjectToString("absent"))) {
case 0: {
 BA.debugLineNum = 324;BA.debugLine="totalPoints = totalPoints + 10";
Debug.ShouldStop(8);
_totalpoints = RemoteObject.solve(new RemoteObject[] {_totalpoints,RemoteObject.createImmutable(10)}, "+",1, 1);Debug.locals.put("totalPoints", _totalpoints);
 break; }
case 1: {
 BA.debugLineNum = 326;BA.debugLine="totalPoints = totalPoints + 5";
Debug.ShouldStop(32);
_totalpoints = RemoteObject.solve(new RemoteObject[] {_totalpoints,RemoteObject.createImmutable(5)}, "+",1, 1);Debug.locals.put("totalPoints", _totalpoints);
 break; }
case 2: {
 BA.debugLineNum = 328;BA.debugLine="totalPoints = totalPoints + 0";
Debug.ShouldStop(128);
_totalpoints = RemoteObject.solve(new RemoteObject[] {_totalpoints,RemoteObject.createImmutable(0)}, "+",1, 1);Debug.locals.put("totalPoints", _totalpoints);
 break; }
}
;
 BA.debugLineNum = 330;BA.debugLine="count = count + 1";
Debug.ShouldStop(512);
_count = RemoteObject.solve(new RemoteObject[] {_count,RemoteObject.createImmutable(1)}, "+",1, 1);Debug.locals.put("count", _count);
 };
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 335;BA.debugLine="If count = 0 Then Return 0";
Debug.ShouldStop(16384);
if (RemoteObject.solveBoolean("=",_count,BA.numberCast(double.class, 0))) { 
if (true) return BA.numberCast(double.class, 0);};
 BA.debugLineNum = 337;BA.debugLine="Return totalPoints / count";
Debug.ShouldStop(65536);
if (true) return RemoteObject.solve(new RemoteObject[] {_totalpoints,_count}, "/",0, 0);
 BA.debugLineNum = 338;BA.debugLine="End Sub";
Debug.ShouldStop(131072);
return RemoteObject.createImmutable(0);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _getaverageattendancescoredisplay(RemoteObject _studentname) throws Exception{
try {
		Debug.PushSubsStack("GetAverageAttendanceScoredisplay (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,340);
if (RapidSub.canDelegate("getaverageattendancescoredisplay")) { return b4a.example.main.remoteMe.runUserSub(false, "main","getaverageattendancescoredisplay", _studentname);}
RemoteObject _aas = RemoteObject.createImmutable(0);
RemoteObject _disply = RemoteObject.createImmutable("");
Debug.locals.put("studentName", _studentname);
 BA.debugLineNum = 340;BA.debugLine="Sub GetAverageAttendanceScoredisplay(studentName A";
Debug.ShouldStop(524288);
 BA.debugLineNum = 341;BA.debugLine="Dim aas As Double = GetAverageAttendanceScore(stu";
Debug.ShouldStop(1048576);
_aas = _getaverageattendancescore(_studentname);Debug.locals.put("aas", _aas);Debug.locals.put("aas", _aas);
 BA.debugLineNum = 342;BA.debugLine="Dim disply As String";
Debug.ShouldStop(2097152);
_disply = RemoteObject.createImmutable("");Debug.locals.put("disply", _disply);
 BA.debugLineNum = 344;BA.debugLine="Select Case True";
Debug.ShouldStop(8388608);
switch (BA.switchObjectToInt(main.mostCurrent.__c.getField(true,"True"),BA.ObjectToBoolean(RemoteObject.solveBoolean("=",_aas,BA.numberCast(double.class, 10))),BA.ObjectToBoolean(RemoteObject.solveBoolean("g",_aas,BA.numberCast(double.class, 9.0))),BA.ObjectToBoolean(RemoteObject.solveBoolean("g",_aas,BA.numberCast(double.class, 8.5))),BA.ObjectToBoolean(RemoteObject.solveBoolean("g",_aas,BA.numberCast(double.class, 8.0))),BA.ObjectToBoolean(RemoteObject.solveBoolean("g",_aas,BA.numberCast(double.class, 7.0))),BA.ObjectToBoolean(RemoteObject.solveBoolean("g",_aas,BA.numberCast(double.class, 6.0))),BA.ObjectToBoolean(RemoteObject.solveBoolean("g",_aas,BA.numberCast(double.class, 5.0))),BA.ObjectToBoolean(RemoteObject.solveBoolean("g",_aas,BA.numberCast(double.class, 4.0))),BA.ObjectToBoolean(RemoteObject.solveBoolean("g",_aas,BA.numberCast(double.class, 3.5))),BA.ObjectToBoolean(RemoteObject.solveBoolean("g",_aas,BA.numberCast(double.class, 3.0))),BA.ObjectToBoolean(RemoteObject.solveBoolean("g",_aas,BA.numberCast(double.class, 1.0))))) {
case 0: {
 BA.debugLineNum = 346;BA.debugLine="disply = \"A+\"";
Debug.ShouldStop(33554432);
_disply = BA.ObjectToString("A+");Debug.locals.put("disply", _disply);
 break; }
case 1: {
 BA.debugLineNum = 348;BA.debugLine="disply = \"A\"";
Debug.ShouldStop(134217728);
_disply = BA.ObjectToString("A");Debug.locals.put("disply", _disply);
 break; }
case 2: {
 BA.debugLineNum = 350;BA.debugLine="disply = \"A-\"";
Debug.ShouldStop(536870912);
_disply = BA.ObjectToString("A-");Debug.locals.put("disply", _disply);
 break; }
case 3: {
 BA.debugLineNum = 352;BA.debugLine="disply = \"B+\"";
Debug.ShouldStop(-2147483648);
_disply = BA.ObjectToString("B+");Debug.locals.put("disply", _disply);
 break; }
case 4: {
 BA.debugLineNum = 354;BA.debugLine="disply = \"B\"";
Debug.ShouldStop(2);
_disply = BA.ObjectToString("B");Debug.locals.put("disply", _disply);
 break; }
case 5: {
 BA.debugLineNum = 356;BA.debugLine="disply = \"B-\"";
Debug.ShouldStop(8);
_disply = BA.ObjectToString("B-");Debug.locals.put("disply", _disply);
 break; }
case 6: {
 BA.debugLineNum = 358;BA.debugLine="disply = \"C+\"";
Debug.ShouldStop(32);
_disply = BA.ObjectToString("C+");Debug.locals.put("disply", _disply);
 break; }
case 7: {
 BA.debugLineNum = 360;BA.debugLine="disply = \"C\"";
Debug.ShouldStop(128);
_disply = BA.ObjectToString("C");Debug.locals.put("disply", _disply);
 break; }
case 8: {
 BA.debugLineNum = 362;BA.debugLine="disply = \"D+\"";
Debug.ShouldStop(512);
_disply = BA.ObjectToString("D+");Debug.locals.put("disply", _disply);
 break; }
case 9: {
 BA.debugLineNum = 364;BA.debugLine="disply = \"D\"";
Debug.ShouldStop(2048);
_disply = BA.ObjectToString("D");Debug.locals.put("disply", _disply);
 break; }
case 10: {
 BA.debugLineNum = 366;BA.debugLine="disply = \"E\"";
Debug.ShouldStop(8192);
_disply = BA.ObjectToString("E");Debug.locals.put("disply", _disply);
 break; }
default: {
 BA.debugLineNum = 368;BA.debugLine="disply = \"F\"";
Debug.ShouldStop(32768);
_disply = BA.ObjectToString("F");Debug.locals.put("disply", _disply);
 break; }
}
;
 BA.debugLineNum = 371;BA.debugLine="Return disply";
Debug.ShouldStop(262144);
if (true) return _disply;
 BA.debugLineNum = 372;BA.debugLine="End Sub";
Debug.ShouldStop(524288);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _getgpadisplaytext(RemoteObject _studentname) throws Exception{
try {
		Debug.PushSubsStack("GetGPADisplayText (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,298);
if (RapidSub.canDelegate("getgpadisplaytext")) { return b4a.example.main.remoteMe.runUserSub(false, "main","getgpadisplaytext", _studentname);}
RemoteObject _gpa = RemoteObject.createImmutable(0);
Debug.locals.put("studentName", _studentname);
 BA.debugLineNum = 298;BA.debugLine="Sub GetGPADisplayText(studentName As String) As St";
Debug.ShouldStop(512);
 BA.debugLineNum = 299;BA.debugLine="Dim gpa As Double = CalculateStudentGPA(studentNa";
Debug.ShouldStop(1024);
_gpa = _calculatestudentgpa(_studentname);Debug.locals.put("gpa", _gpa);Debug.locals.put("gpa", _gpa);
 BA.debugLineNum = 301;BA.debugLine="If gpa = -1.0 Then";
Debug.ShouldStop(4096);
if (RemoteObject.solveBoolean("=",_gpa,BA.numberCast(double.class, -1.0))) { 
 BA.debugLineNum = 302;BA.debugLine="Return \"N/A (Min 3 activities required)\"";
Debug.ShouldStop(8192);
if (true) return BA.ObjectToString("N/A (Min 3 activities required)");
 }else {
 BA.debugLineNum = 305;BA.debugLine="Return NumberFormat2(gpa, 1, 2, 2, False)";
Debug.ShouldStop(65536);
if (true) return main.mostCurrent.__c.runMethod(true,"NumberFormat2",(Object)(_gpa),(Object)(BA.numberCast(int.class, 1)),(Object)(BA.numberCast(int.class, 2)),(Object)(BA.numberCast(int.class, 2)),(Object)(main.mostCurrent.__c.getField(true,"False")));
 };
 BA.debugLineNum = 307;BA.debugLine="End Sub";
Debug.ShouldStop(262144);
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
		Debug.PushSubsStack("GetStudentAvgPercentage (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,388);
if (RapidSub.canDelegate("getstudentavgpercentage")) { return b4a.example.main.remoteMe.runUserSub(false, "main","getstudentavgpercentage", _studentname);}
RemoteObject _total = RemoteObject.createImmutable(0);
RemoteObject _count = RemoteObject.createImmutable(0);
int _i = 0;
RemoteObject _row = null;
Debug.locals.put("studentname", _studentname);
 BA.debugLineNum = 388;BA.debugLine="Sub GetStudentAvgPercentage(studentname As String)";
Debug.ShouldStop(8);
 BA.debugLineNum = 389;BA.debugLine="Dim total As Double = 0";
Debug.ShouldStop(16);
_total = BA.numberCast(double.class, 0);Debug.locals.put("total", _total);Debug.locals.put("total", _total);
 BA.debugLineNum = 390;BA.debugLine="Dim count As Int = 0";
Debug.ShouldStop(32);
_count = BA.numberCast(int.class, 0);Debug.locals.put("count", _count);Debug.locals.put("count", _count);
 BA.debugLineNum = 391;BA.debugLine="For i = 0 To StudentList.Size - 1";
Debug.ShouldStop(64);
{
final int step3 = 1;
final int limit3 = RemoteObject.solve(new RemoteObject[] {main.mostCurrent._studentlist.runMethod(true,"getSize"),RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
for (;(step3 > 0 && _i <= limit3) || (step3 < 0 && _i >= limit3) ;_i = ((int)(0 + _i + step3))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 392;BA.debugLine="Dim row() As String = StudentList.Get(i)";
Debug.ShouldStop(128);
_row = (main.mostCurrent._studentlist.runMethod(false,"Get",(Object)(BA.numberCast(int.class, _i))));Debug.locals.put("row", _row);Debug.locals.put("row", _row);
 BA.debugLineNum = 393;BA.debugLine="If row(COL_NAME) = studentname Then";
Debug.ShouldStop(256);
if (RemoteObject.solveBoolean("=",_row.getArrayElement(true,main._col_name),_studentname)) { 
 BA.debugLineNum = 394;BA.debugLine="total = total + ComputePercentage(row(COL_RATE)";
Debug.ShouldStop(512);
_total = RemoteObject.solve(new RemoteObject[] {_total,_computepercentage(_row.getArrayElement(true,main._col_rate))}, "+",1, 0);Debug.locals.put("total", _total);
 BA.debugLineNum = 395;BA.debugLine="count = count + 1";
Debug.ShouldStop(1024);
_count = RemoteObject.solve(new RemoteObject[] {_count,RemoteObject.createImmutable(1)}, "+",1, 1);Debug.locals.put("count", _count);
 };
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 398;BA.debugLine="If count = 0 Then Return 0";
Debug.ShouldStop(8192);
if (RemoteObject.solveBoolean("=",_count,BA.numberCast(double.class, 0))) { 
if (true) return BA.numberCast(double.class, 0);};
 BA.debugLineNum = 399;BA.debugLine="Return total / count";
Debug.ShouldStop(16384);
if (true) return RemoteObject.solve(new RemoteObject[] {_total,_count}, "/",0, 0);
 BA.debugLineNum = 400;BA.debugLine="End Sub";
Debug.ShouldStop(32768);
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
		Debug.PushSubsStack("GetStudentGrade (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,403);
if (RapidSub.canDelegate("getstudentgrade")) { return b4a.example.main.remoteMe.runUserSub(false, "main","getstudentgrade", _studentname);}
Debug.locals.put("studentname", _studentname);
 BA.debugLineNum = 403;BA.debugLine="Sub GetStudentGrade(studentname As String) As Stri";
Debug.ShouldStop(262144);
 BA.debugLineNum = 404;BA.debugLine="Return PercentageToGrade(GetStudentAvgPercentage(";
Debug.ShouldStop(524288);
if (true) return _percentagetograde(_getstudentavgpercentage(_studentname));
 BA.debugLineNum = 405;BA.debugLine="End Sub";
Debug.ShouldStop(1048576);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _getstudenttopactivity(RemoteObject _studentname) throws Exception{
try {
		Debug.PushSubsStack("GetStudentTopActivity (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,432);
if (RapidSub.canDelegate("getstudenttopactivity")) { return b4a.example.main.remoteMe.runUserSub(false, "main","getstudenttopactivity", _studentname);}
Debug.locals.put("studentName", _studentname);
 BA.debugLineNum = 432;BA.debugLine="Sub GetStudentTopActivity(studentName As String) A";
Debug.ShouldStop(32768);
 BA.debugLineNum = 436;BA.debugLine="Return getActivity(studentName)";
Debug.ShouldStop(524288);
if (true) return _getactivity(_studentname);
 BA.debugLineNum = 437;BA.debugLine="End Sub";
Debug.ShouldStop(1048576);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _getunique(RemoteObject _source) throws Exception{
try {
		Debug.PushSubsStack("GetUnique (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,772);
if (RapidSub.canDelegate("getunique")) { return b4a.example.main.remoteMe.runUserSub(false, "main","getunique", _source);}
RemoteObject _seen = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _unique = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _item = RemoteObject.createImmutable("");
int _i = 0;
Debug.locals.put("source", _source);
 BA.debugLineNum = 772;BA.debugLine="Sub GetUnique(source As List) As List";
Debug.ShouldStop(8);
 BA.debugLineNum = 773;BA.debugLine="Dim seen As Map";
Debug.ShouldStop(16);
_seen = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");Debug.locals.put("seen", _seen);
 BA.debugLineNum = 774;BA.debugLine="seen.Initialize";
Debug.ShouldStop(32);
_seen.runVoidMethod ("Initialize");
 BA.debugLineNum = 775;BA.debugLine="Dim unique As List";
Debug.ShouldStop(64);
_unique = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");Debug.locals.put("unique", _unique);
 BA.debugLineNum = 776;BA.debugLine="unique.Initialize";
Debug.ShouldStop(128);
_unique.runVoidMethod ("Initialize");
 BA.debugLineNum = 777;BA.debugLine="Dim item As String        ' ✅ Declared outside th";
Debug.ShouldStop(256);
_item = RemoteObject.createImmutable("");Debug.locals.put("item", _item);
 BA.debugLineNum = 778;BA.debugLine="For i = 0 To source.Size - 1";
Debug.ShouldStop(512);
{
final int step6 = 1;
final int limit6 = RemoteObject.solve(new RemoteObject[] {_source.runMethod(true,"getSize"),RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
for (;(step6 > 0 && _i <= limit6) || (step6 < 0 && _i >= limit6) ;_i = ((int)(0 + _i + step6))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 779;BA.debugLine="item = source.Get(i)  ' ✅ Only assignment inside";
Debug.ShouldStop(1024);
_item = BA.ObjectToString(_source.runMethod(false,"Get",(Object)(BA.numberCast(int.class, _i))));Debug.locals.put("item", _item);
 BA.debugLineNum = 780;BA.debugLine="If seen.ContainsKey(item) = False Then";
Debug.ShouldStop(2048);
if (RemoteObject.solveBoolean("=",_seen.runMethod(true,"ContainsKey",(Object)((_item))),main.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 781;BA.debugLine="seen.Put(item, True)";
Debug.ShouldStop(4096);
_seen.runVoidMethod ("Put",(Object)((_item)),(Object)((main.mostCurrent.__c.getField(true,"True"))));
 BA.debugLineNum = 782;BA.debugLine="unique.Add(item)";
Debug.ShouldStop(8192);
_unique.runVoidMethod ("Add",(Object)((_item)));
 };
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 785;BA.debugLine="Return unique";
Debug.ShouldStop(65536);
if (true) return _unique;
 BA.debugLineNum = 786;BA.debugLine="End Sub";
Debug.ShouldStop(131072);
return RemoteObject.createImmutable(null);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _getuniquestudentnames() throws Exception{
try {
		Debug.PushSubsStack("GetUniqueStudentNames (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,374);
if (RapidSub.canDelegate("getuniquestudentnames")) { return b4a.example.main.remoteMe.runUserSub(false, "main","getuniquestudentnames");}
RemoteObject _names = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
int _i = 0;
RemoteObject _row = null;
RemoteObject _name = RemoteObject.createImmutable("");
 BA.debugLineNum = 374;BA.debugLine="Sub GetUniqueStudentNames As List";
Debug.ShouldStop(2097152);
 BA.debugLineNum = 375;BA.debugLine="Dim names As List";
Debug.ShouldStop(4194304);
_names = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");Debug.locals.put("names", _names);
 BA.debugLineNum = 376;BA.debugLine="names.Initialize";
Debug.ShouldStop(8388608);
_names.runVoidMethod ("Initialize");
 BA.debugLineNum = 377;BA.debugLine="For i = 0 To StudentList.Size - 1";
Debug.ShouldStop(16777216);
{
final int step3 = 1;
final int limit3 = RemoteObject.solve(new RemoteObject[] {main.mostCurrent._studentlist.runMethod(true,"getSize"),RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
for (;(step3 > 0 && _i <= limit3) || (step3 < 0 && _i >= limit3) ;_i = ((int)(0 + _i + step3))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 378;BA.debugLine="Dim row() As String = StudentList.Get(i)";
Debug.ShouldStop(33554432);
_row = (main.mostCurrent._studentlist.runMethod(false,"Get",(Object)(BA.numberCast(int.class, _i))));Debug.locals.put("row", _row);Debug.locals.put("row", _row);
 BA.debugLineNum = 379;BA.debugLine="Dim name As String = row(COL_NAME)";
Debug.ShouldStop(67108864);
_name = _row.getArrayElement(true,main._col_name);Debug.locals.put("name", _name);Debug.locals.put("name", _name);
 BA.debugLineNum = 380;BA.debugLine="If names.IndexOf(name) = -1 Then";
Debug.ShouldStop(134217728);
if (RemoteObject.solveBoolean("=",_names.runMethod(true,"IndexOf",(Object)((_name))),BA.numberCast(double.class, -(double) (0 + 1)))) { 
 BA.debugLineNum = 381;BA.debugLine="names.Add(name)";
Debug.ShouldStop(268435456);
_names.runVoidMethod ("Add",(Object)((_name)));
 };
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 384;BA.debugLine="Return names";
Debug.ShouldStop(-2147483648);
if (true) return _names;
 BA.debugLineNum = 385;BA.debugLine="End Sub";
Debug.ShouldStop(1);
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
 //BA.debugLineNum = 32;BA.debugLine="Dim student_names, student_ids, student_subjects";
main.mostCurrent._student_names = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
main.mostCurrent._student_ids = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
main.mostCurrent._student_subjects = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
 //BA.debugLineNum = 33;BA.debugLine="Dim student_activitys, student_attendance, studen";
main.mostCurrent._student_activitys = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
main.mostCurrent._student_attendance = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
main.mostCurrent._student_rate = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
 //BA.debugLineNum = 34;BA.debugLine="Dim isselectingid As Boolean";
main._isselectingid = RemoteObject.createImmutable(false);
 //BA.debugLineNum = 35;BA.debugLine="Dim showselectid As Boolean";
main._showselectid = RemoteObject.createImmutable(false);
 //BA.debugLineNum = 36;BA.debugLine="dim todelete as Boolean";
main._todelete = RemoteObject.createImmutable(false);
 //BA.debugLineNum = 39;BA.debugLine="Private datapnl As Panel";
main.mostCurrent._datapnl = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 42;BA.debugLine="Private gradepnl As Panel";
main.mostCurrent._gradepnl = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 43;BA.debugLine="Private SV1 As ScrollView";
main.mostCurrent._sv1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ScrollViewWrapper");
 //BA.debugLineNum = 46;BA.debugLine="Private SV As ScrollView";
main.mostCurrent._sv = RemoteObject.createNew ("anywheresoftware.b4a.objects.ScrollViewWrapper");
 //BA.debugLineNum = 47;BA.debugLine="Private Table As Panel";
main.mostCurrent._table = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 48;BA.debugLine="Type RowCol (Row As Int, Col As Int)";
;
 //BA.debugLineNum = 49;BA.debugLine="Dim NumberOfColumns, RowHeight, ColumnWidth As In";
main._numberofcolumns = RemoteObject.createImmutable(0);
main._rowheight = RemoteObject.createImmutable(0);
main._columnwidth = RemoteObject.createImmutable(0);
 //BA.debugLineNum = 50;BA.debugLine="Dim HeaderColor, FontColor, HeaderFontColor As In";
main._headercolor = RemoteObject.createImmutable(0);
main._fontcolor = RemoteObject.createImmutable(0);
main._headerfontcolor = RemoteObject.createImmutable(0);
 //BA.debugLineNum = 51;BA.debugLine="Dim FontSize As Float";
main._fontsize = RemoteObject.createImmutable(0f);
 //BA.debugLineNum = 52;BA.debugLine="Dim Alignment As Int";
main._alignment = RemoteObject.createImmutable(0);
 //BA.debugLineNum = 55;BA.debugLine="Private Panel1 As Panel";
main.mostCurrent._panel1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 58;BA.debugLine="Dim StudentList As List        ' Each item is a S";
main.mostCurrent._studentlist = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
 //BA.debugLineNum = 59;BA.debugLine="Dim CSVHeaders As List         ' Header row from";
main.mostCurrent._csvheaders = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
 //BA.debugLineNum = 62;BA.debugLine="Dim COL_NAME As Int : COL_NAME = 0";
main._col_name = RemoteObject.createImmutable(0);
 //BA.debugLineNum = 62;BA.debugLine="Dim COL_NAME As Int : COL_NAME = 0";
main._col_name = BA.numberCast(int.class, 0);
 //BA.debugLineNum = 63;BA.debugLine="Dim COL_RATE As Int : COL_RATE = 5";
main._col_rate = RemoteObject.createImmutable(0);
 //BA.debugLineNum = 63;BA.debugLine="Dim COL_RATE As Int : COL_RATE = 5";
main._col_rate = BA.numberCast(int.class, 5);
 //BA.debugLineNum = 64;BA.debugLine="Dim COL_ACTIVITY As Int : COL_ACTIVITY = 3";
main._col_activity = RemoteObject.createImmutable(0);
 //BA.debugLineNum = 64;BA.debugLine="Dim COL_ACTIVITY As Int : COL_ACTIVITY = 3";
main._col_activity = BA.numberCast(int.class, 3);
 //BA.debugLineNum = 67;BA.debugLine="Private NAME1 As Label";
main.mostCurrent._name1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 68;BA.debugLine="Private NAME2 As Label";
main.mostCurrent._name2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 69;BA.debugLine="Private NAME3 As Label";
main.mostCurrent._name3 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 70;BA.debugLine="Private RATE1 As Label";
main.mostCurrent._rate1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 71;BA.debugLine="Private RATE2 As Label";
main.mostCurrent._rate2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 72;BA.debugLine="Private RATE3 As Label";
main.mostCurrent._rate3 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 73;BA.debugLine="Private ACTIVITY1 As Label";
main.mostCurrent._activity1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 74;BA.debugLine="Private ACTIVITY2 As Label";
main.mostCurrent._activity2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 75;BA.debugLine="Private ACTIVITY3 As Label";
main.mostCurrent._activity3 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 79;BA.debugLine="Private CheckBox1 As CheckBox";
main.mostCurrent._checkbox1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper");
 //BA.debugLineNum = 80;BA.debugLine="Private CheckBox2 As CheckBox";
main.mostCurrent._checkbox2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper");
 //BA.debugLineNum = 81;BA.debugLine="Private CheckBox3 As CheckBox";
main.mostCurrent._checkbox3 = RemoteObject.createNew ("anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper");
 //BA.debugLineNum = 82;BA.debugLine="Private EditText1 As EditText";
main.mostCurrent._edittext1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 83;BA.debugLine="Private EditText2 As EditText";
main.mostCurrent._edittext2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 84;BA.debugLine="Private SELECTSUBJECTS As Spinner";
main.mostCurrent._selectsubjects = RemoteObject.createNew ("anywheresoftware.b4a.objects.SpinnerWrapper");
 //BA.debugLineNum = 85;BA.debugLine="Private STUDENT_ID As EditText";
main.mostCurrent._student_id = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 86;BA.debugLine="Private STUDENT_NAME As EditText";
main.mostCurrent._student_name = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 87;BA.debugLine="Private STUDENT_ACTIVITY As Spinner";
main.mostCurrent._student_activity = RemoteObject.createNew ("anywheresoftware.b4a.objects.SpinnerWrapper");
 //BA.debugLineNum = 88;BA.debugLine="Private update As Panel";
main.mostCurrent._update = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 89;BA.debugLine="Private bak As Panel";
main.mostCurrent._bak = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 90;BA.debugLine="Private spinner As Spinner";
main.mostCurrent._spinner = RemoteObject.createNew ("anywheresoftware.b4a.objects.SpinnerWrapper");
 //BA.debugLineNum = 91;BA.debugLine="Private editRowIndex As Int = -1";
main._editrowindex = BA.numberCast(int.class, -(double) (0 + 1));
 //BA.debugLineNum = 92;BA.debugLine="Private todelete As Boolean = False";
main._todelete = main.mostCurrent.__c.getField(true,"False");
 //BA.debugLineNum = 93;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _grdbtn_click() throws Exception{
try {
		Debug.PushSubsStack("grdbtn_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,194);
if (RapidSub.canDelegate("grdbtn_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","grdbtn_click");}
 BA.debugLineNum = 194;BA.debugLine="Private Sub grdbtn_Click";
Debug.ShouldStop(2);
 BA.debugLineNum = 195;BA.debugLine="HighlightTab(grdbtn)";
Debug.ShouldStop(4);
_highlighttab(main.mostCurrent._grdbtn);
 BA.debugLineNum = 196;BA.debugLine="showgrades";
Debug.ShouldStop(8);
_showgrades();
 BA.debugLineNum = 197;BA.debugLine="End Sub";
Debug.ShouldStop(16);
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
		Debug.PushSubsStack("HighlightTab (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,209);
if (RapidSub.canDelegate("highlighttab")) { return b4a.example.main.remoteMe.runUserSub(false, "main","highlighttab", _activebtn);}
Debug.locals.put("activeBtn", _activebtn);
 BA.debugLineNum = 209;BA.debugLine="Sub HighlightTab(activeBtn As Panel)";
Debug.ShouldStop(65536);
 BA.debugLineNum = 210;BA.debugLine="dashbtn.Color = Colors.White";
Debug.ShouldStop(131072);
main.mostCurrent._dashbtn.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 BA.debugLineNum = 211;BA.debugLine="grdbtn.Color = Colors.White";
Debug.ShouldStop(262144);
main.mostCurrent._grdbtn.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 BA.debugLineNum = 212;BA.debugLine="editbtn.Color = Colors.White";
Debug.ShouldStop(524288);
main.mostCurrent._editbtn.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 BA.debugLineNum = 213;BA.debugLine="profilebtn.Color = Colors.White";
Debug.ShouldStop(1048576);
main.mostCurrent._profilebtn.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 BA.debugLineNum = 214;BA.debugLine="activeBtn.Color = 0xFF1AEA00";
Debug.ShouldStop(2097152);
_activebtn.runVoidMethod ("setColor",BA.numberCast(int.class, ((int)0xff1aea00)));
 BA.debugLineNum = 215;BA.debugLine="End Sub";
Debug.ShouldStop(4194304);
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
		Debug.PushSubsStack("initdrawer (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,133);
if (RapidSub.canDelegate("initdrawer")) { return b4a.example.main.remoteMe.runUserSub(false, "main","initdrawer");}
 BA.debugLineNum = 133;BA.debugLine="Sub initdrawer";
Debug.ShouldStop(16);
 BA.debugLineNum = 134;BA.debugLine="Drawer.Initialize(Me, \"Drawer\", Activity, 260dip)";
Debug.ShouldStop(32);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_initialize" /*RemoteObject*/ ,main.mostCurrent.activityBA,(Object)(main.getObject()),(Object)(BA.ObjectToString("Drawer")),RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), main.mostCurrent._activity.getObject()),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 260)))));
 BA.debugLineNum = 135;BA.debugLine="Drawer.CenterPanel.BringToFront";
Debug.ShouldStop(64);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_getcenterpanel" /*RemoteObject*/ ).runVoidMethod ("BringToFront");
 BA.debugLineNum = 136;BA.debugLine="Drawer.LeftPanel.BringToFront";
Debug.ShouldStop(128);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_getleftpanel" /*RemoteObject*/ ).runVoidMethod ("BringToFront");
 BA.debugLineNum = 138;BA.debugLine="pnlmain = Drawer.CenterPanel";
Debug.ShouldStop(512);
main.mostCurrent._pnlmain = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.PanelWrapper"), main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_getcenterpanel" /*RemoteObject*/ ).getObject());
 BA.debugLineNum = 139;BA.debugLine="pnlmenu = Drawer.LeftPanel";
Debug.ShouldStop(1024);
main.mostCurrent._pnlmenu = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.PanelWrapper"), main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_getleftpanel" /*RemoteObject*/ ).getObject());
 BA.debugLineNum = 141;BA.debugLine="SetGradient(pnlmenu, Colors.rgb(175, 71, 210), Co";
Debug.ShouldStop(4096);
_setgradient(main.mostCurrent._pnlmenu,main.mostCurrent.__c.getField(false,"Colors").runMethod(true,"RGB",(Object)(BA.numberCast(int.class, 175)),(Object)(BA.numberCast(int.class, 71)),(Object)(BA.numberCast(int.class, 210))),main.mostCurrent.__c.getField(false,"Colors").runMethod(true,"RGB",(Object)(BA.numberCast(int.class, 38)),(Object)(BA.numberCast(int.class, 53)),(Object)(BA.numberCast(int.class, 93))));
 BA.debugLineNum = 142;BA.debugLine="CreateMenu";
Debug.ShouldStop(8192);
_createmenu();
 BA.debugLineNum = 143;BA.debugLine="End Sub";
Debug.ShouldStop(16384);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _loadspinner() throws Exception{
try {
		Debug.PushSubsStack("loadspinner (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,788);
if (RapidSub.canDelegate("loadspinner")) { return b4a.example.main.remoteMe.runUserSub(false, "main","loadspinner");}
RemoteObject _unique_subjects = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
int _i = 0;
RemoteObject _unique_activities = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
 BA.debugLineNum = 788;BA.debugLine="Sub loadspinner";
Debug.ShouldStop(524288);
 BA.debugLineNum = 789;BA.debugLine="If student_subjects.IsInitialized = False Then Re";
Debug.ShouldStop(1048576);
if (RemoteObject.solveBoolean("=",main.mostCurrent._student_subjects.runMethod(true,"IsInitialized"),main.mostCurrent.__c.getField(true,"False"))) { 
if (true) return RemoteObject.createImmutable("");};
 BA.debugLineNum = 790;BA.debugLine="SELECTSUBJECTS.Clear";
Debug.ShouldStop(2097152);
main.mostCurrent._selectsubjects.runVoidMethod ("Clear");
 BA.debugLineNum = 791;BA.debugLine="Dim unique_subjects As List = GetUnique(student_s";
Debug.ShouldStop(4194304);
_unique_subjects = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_unique_subjects = _getunique(main.mostCurrent._student_subjects);Debug.locals.put("unique_subjects", _unique_subjects);Debug.locals.put("unique_subjects", _unique_subjects);
 BA.debugLineNum = 792;BA.debugLine="For i = 0 To unique_subjects.Size - 1";
Debug.ShouldStop(8388608);
{
final int step4 = 1;
final int limit4 = RemoteObject.solve(new RemoteObject[] {_unique_subjects.runMethod(true,"getSize"),RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
for (;(step4 > 0 && _i <= limit4) || (step4 < 0 && _i >= limit4) ;_i = ((int)(0 + _i + step4))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 793;BA.debugLine="SELECTSUBJECTS.Add(unique_subjects.Get(i))";
Debug.ShouldStop(16777216);
main.mostCurrent._selectsubjects.runVoidMethod ("Add",(Object)(BA.ObjectToString(_unique_subjects.runMethod(false,"Get",(Object)(BA.numberCast(int.class, _i))))));
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 795;BA.debugLine="If student_activitys.IsInitialized = False Then R";
Debug.ShouldStop(67108864);
if (RemoteObject.solveBoolean("=",main.mostCurrent._student_activitys.runMethod(true,"IsInitialized"),main.mostCurrent.__c.getField(true,"False"))) { 
if (true) return RemoteObject.createImmutable("");};
 BA.debugLineNum = 796;BA.debugLine="STUDENT_ACTIVITY.Clear";
Debug.ShouldStop(134217728);
main.mostCurrent._student_activity.runVoidMethod ("Clear");
 BA.debugLineNum = 797;BA.debugLine="Dim unique_activities As List = GetUnique(student";
Debug.ShouldStop(268435456);
_unique_activities = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_unique_activities = _getunique(main.mostCurrent._student_activitys);Debug.locals.put("unique_activities", _unique_activities);Debug.locals.put("unique_activities", _unique_activities);
 BA.debugLineNum = 798;BA.debugLine="For i = 0 To unique_activities.Size - 1";
Debug.ShouldStop(536870912);
{
final int step10 = 1;
final int limit10 = RemoteObject.solve(new RemoteObject[] {_unique_activities.runMethod(true,"getSize"),RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
for (;(step10 > 0 && _i <= limit10) || (step10 < 0 && _i >= limit10) ;_i = ((int)(0 + _i + step10))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 799;BA.debugLine="STUDENT_ACTIVITY.Add(unique_activities.Get(i))";
Debug.ShouldStop(1073741824);
main.mostCurrent._student_activity.runVoidMethod ("Add",(Object)(BA.ObjectToString(_unique_activities.runMethod(false,"Get",(Object)(BA.numberCast(int.class, _i))))));
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 801;BA.debugLine="End Sub";
Debug.ShouldStop(1);
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
		Debug.PushSubsStack("LoadStudentData (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,221);
if (RapidSub.canDelegate("loadstudentdata")) { return b4a.example.main.remoteMe.runUserSub(false, "main","loadstudentdata");}
int _i = 0;
RemoteObject _row = null;
 BA.debugLineNum = 221;BA.debugLine="Sub LoadStudentData";
Debug.ShouldStop(268435456);
 BA.debugLineNum = 222;BA.debugLine="CSVHeaders.Initialize";
Debug.ShouldStop(536870912);
main.mostCurrent._csvheaders.runVoidMethod ("Initialize");
 BA.debugLineNum = 223;BA.debugLine="StudentList = StringUtils1.LoadCSV2(File.DirInter";
Debug.ShouldStop(1073741824);
main.mostCurrent._studentlist = main._stringutils1.runMethod(false,"LoadCSV2",(Object)(main.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirInternal")),(Object)(BA.ObjectToString("student.csv")),(Object)(BA.ObjectToChar(",")),(Object)(main.mostCurrent._csvheaders));
 BA.debugLineNum = 226;BA.debugLine="student_names.Initialize";
Debug.ShouldStop(2);
main.mostCurrent._student_names.runVoidMethod ("Initialize");
 BA.debugLineNum = 227;BA.debugLine="student_ids.Initialize";
Debug.ShouldStop(4);
main.mostCurrent._student_ids.runVoidMethod ("Initialize");
 BA.debugLineNum = 228;BA.debugLine="student_subjects.Initialize";
Debug.ShouldStop(8);
main.mostCurrent._student_subjects.runVoidMethod ("Initialize");
 BA.debugLineNum = 229;BA.debugLine="student_activitys.Initialize";
Debug.ShouldStop(16);
main.mostCurrent._student_activitys.runVoidMethod ("Initialize");
 BA.debugLineNum = 230;BA.debugLine="student_attendance.Initialize";
Debug.ShouldStop(32);
main.mostCurrent._student_attendance.runVoidMethod ("Initialize");
 BA.debugLineNum = 231;BA.debugLine="student_rate.Initialize";
Debug.ShouldStop(64);
main.mostCurrent._student_rate.runVoidMethod ("Initialize");
 BA.debugLineNum = 234;BA.debugLine="For i = 0 To StudentList.Size - 1";
Debug.ShouldStop(512);
{
final int step9 = 1;
final int limit9 = RemoteObject.solve(new RemoteObject[] {main.mostCurrent._studentlist.runMethod(true,"getSize"),RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
for (;(step9 > 0 && _i <= limit9) || (step9 < 0 && _i >= limit9) ;_i = ((int)(0 + _i + step9))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 235;BA.debugLine="Dim row() As String = StudentList.Get(i)";
Debug.ShouldStop(1024);
_row = (main.mostCurrent._studentlist.runMethod(false,"Get",(Object)(BA.numberCast(int.class, _i))));Debug.locals.put("row", _row);Debug.locals.put("row", _row);
 BA.debugLineNum = 236;BA.debugLine="student_names.Add(row(0))";
Debug.ShouldStop(2048);
main.mostCurrent._student_names.runVoidMethod ("Add",(Object)((_row.getArrayElement(true,BA.numberCast(int.class, 0)))));
 BA.debugLineNum = 237;BA.debugLine="student_ids.Add(row(1))";
Debug.ShouldStop(4096);
main.mostCurrent._student_ids.runVoidMethod ("Add",(Object)((_row.getArrayElement(true,BA.numberCast(int.class, 1)))));
 BA.debugLineNum = 238;BA.debugLine="student_subjects.Add(row(2))";
Debug.ShouldStop(8192);
main.mostCurrent._student_subjects.runVoidMethod ("Add",(Object)((_row.getArrayElement(true,BA.numberCast(int.class, 2)))));
 BA.debugLineNum = 239;BA.debugLine="student_activitys.Add(row(3))";
Debug.ShouldStop(16384);
main.mostCurrent._student_activitys.runVoidMethod ("Add",(Object)((_row.getArrayElement(true,BA.numberCast(int.class, 3)))));
 BA.debugLineNum = 240;BA.debugLine="student_attendance.Add(row(4))";
Debug.ShouldStop(32768);
main.mostCurrent._student_attendance.runVoidMethod ("Add",(Object)((_row.getArrayElement(true,BA.numberCast(int.class, 4)))));
 BA.debugLineNum = 241;BA.debugLine="student_rate.Add(row(5))";
Debug.ShouldStop(65536);
main.mostCurrent._student_rate.runVoidMethod ("Add",(Object)((_row.getArrayElement(true,BA.numberCast(int.class, 5)))));
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 243;BA.debugLine="End Sub";
Debug.ShouldStop(262144);
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
		Debug.PushSubsStack("loadtable (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,722);
if (RapidSub.canDelegate("loadtable")) { return b4a.example.main.remoteMe.runUserSub(false, "main","loadtable");}
int _i = 0;
RemoteObject _l = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
RemoteObject _row = null;
 BA.debugLineNum = 722;BA.debugLine="Sub loadtable";
Debug.ShouldStop(131072);
 BA.debugLineNum = 724;BA.debugLine="NumberOfColumns = CSVHeaders.Size";
Debug.ShouldStop(524288);
main._numberofcolumns = main.mostCurrent._csvheaders.runMethod(true,"getSize");
 BA.debugLineNum = 725;BA.debugLine="ColumnWidth = SV.Width / NumberOfColumns";
Debug.ShouldStop(1048576);
main._columnwidth = BA.numberCast(int.class, RemoteObject.solve(new RemoteObject[] {main.mostCurrent._sv.runMethod(true,"getWidth"),main._numberofcolumns}, "/",0, 0));
 BA.debugLineNum = 728;BA.debugLine="For i = 0 To NumberOfColumns - 1";
Debug.ShouldStop(8388608);
{
final int step3 = 1;
final int limit3 = RemoteObject.solve(new RemoteObject[] {main._numberofcolumns,RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
for (;(step3 > 0 && _i <= limit3) || (step3 < 0 && _i >= limit3) ;_i = ((int)(0 + _i + step3))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 729;BA.debugLine="Dim l As Label";
Debug.ShouldStop(16777216);
_l = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("l", _l);
 BA.debugLineNum = 730;BA.debugLine="l.Initialize(\"header\")";
Debug.ShouldStop(33554432);
_l.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("header")));
 BA.debugLineNum = 731;BA.debugLine="l.Text = CSVHeaders.Get(i)";
Debug.ShouldStop(67108864);
_l.runMethod(true,"setText",BA.ObjectToCharSequence(main.mostCurrent._csvheaders.runMethod(false,"Get",(Object)(BA.numberCast(int.class, _i)))));
 BA.debugLineNum = 732;BA.debugLine="l.Gravity = Gravity.LEFT";
Debug.ShouldStop(134217728);
_l.runMethod(true,"setGravity",main.mostCurrent.__c.getField(false,"Gravity").getField(true,"LEFT"));
 BA.debugLineNum = 733;BA.debugLine="l.TextSize = FontSize - 4";
Debug.ShouldStop(268435456);
_l.runMethod(true,"setTextSize",BA.numberCast(float.class, RemoteObject.solve(new RemoteObject[] {main._fontsize,RemoteObject.createImmutable(4)}, "-",1, 0)));
 BA.debugLineNum = 734;BA.debugLine="l.Color = HeaderColor";
Debug.ShouldStop(536870912);
_l.runVoidMethod ("setColor",main._headercolor);
 BA.debugLineNum = 735;BA.debugLine="l.TextColor = HeaderFontColor";
Debug.ShouldStop(1073741824);
_l.runMethod(true,"setTextColor",main._headerfontcolor);
 BA.debugLineNum = 736;BA.debugLine="l.Padding = Array As Int(10dip, 5dip, 0dip, 5dip";
Debug.ShouldStop(-2147483648);
_l.runMethod(false,"setPadding",RemoteObject.createNewArray("int",new int[] {4},new Object[] {main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10))),main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 5))),main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 0))),main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 5)))}));
 BA.debugLineNum = 737;BA.debugLine="l.Tag = i";
Debug.ShouldStop(1);
_l.runMethod(false,"setTag",RemoteObject.createImmutable((_i)));
 BA.debugLineNum = 738;BA.debugLine="Table.AddView(l, ColumnWidth * i, 0, ColumnWidth";
Debug.ShouldStop(2);
main.mostCurrent._table.runVoidMethod ("AddView",(Object)((_l.getObject())),(Object)(RemoteObject.solve(new RemoteObject[] {main._columnwidth,RemoteObject.createImmutable(_i)}, "*",0, 1)),(Object)(BA.numberCast(int.class, 0)),(Object)(main._columnwidth),(Object)(main._rowheight));
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 742;BA.debugLine="For i = 0 To StudentList.Size - 1";
Debug.ShouldStop(32);
{
final int step15 = 1;
final int limit15 = RemoteObject.solve(new RemoteObject[] {main.mostCurrent._studentlist.runMethod(true,"getSize"),RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
for (;(step15 > 0 && _i <= limit15) || (step15 < 0 && _i >= limit15) ;_i = ((int)(0 + _i + step15))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 743;BA.debugLine="Dim row() As String = StudentList.Get(i)";
Debug.ShouldStop(64);
_row = (main.mostCurrent._studentlist.runMethod(false,"Get",(Object)(BA.numberCast(int.class, _i))));Debug.locals.put("row", _row);Debug.locals.put("row", _row);
 BA.debugLineNum = 744;BA.debugLine="AddTableRow(row, i + 1)  ' +1 to skip header row";
Debug.ShouldStop(128);
_addtablerow(_row,RemoteObject.solve(new RemoteObject[] {RemoteObject.createImmutable(_i),RemoteObject.createImmutable(1)}, "+",1, 1));
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 747;BA.debugLine="Table.Height = (StudentList.Size + 1) * RowHeight";
Debug.ShouldStop(1024);
main.mostCurrent._table.runMethod(true,"setHeight",RemoteObject.solve(new RemoteObject[] {(RemoteObject.solve(new RemoteObject[] {main.mostCurrent._studentlist.runMethod(true,"getSize"),RemoteObject.createImmutable(1)}, "+",1, 1)),main._rowheight}, "*",0, 1));
 BA.debugLineNum = 748;BA.debugLine="End Sub";
Debug.ShouldStop(2048);
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
		Debug.PushSubsStack("makeshadow (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,609);
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
 BA.debugLineNum = 609;BA.debugLine="Sub makeshadow(numstudent As Int, studentNames As";
Debug.ShouldStop(1);
 BA.debugLineNum = 610;BA.debugLine="Dim itemSpacing As Int = 108dip";
Debug.ShouldStop(2);
_itemspacing = main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 108)));Debug.locals.put("itemSpacing", _itemspacing);Debug.locals.put("itemSpacing", _itemspacing);
 BA.debugLineNum = 611;BA.debugLine="Dim startTopMargin As Int = 20dip";
Debug.ShouldStop(4);
_starttopmargin = main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 20)));Debug.locals.put("startTopMargin", _starttopmargin);Debug.locals.put("startTopMargin", _starttopmargin);
 BA.debugLineNum = 613;BA.debugLine="For i = 0 To numstudent - 1";
Debug.ShouldStop(16);
{
final int step3 = 1;
final int limit3 = RemoteObject.solve(new RemoteObject[] {_numstudent,RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
for (;(step3 > 0 && _i <= limit3) || (step3 < 0 && _i >= limit3) ;_i = ((int)(0 + _i + step3))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 614;BA.debugLine="Dim currentTop As Int = startTopMargin + (i * it";
Debug.ShouldStop(32);
_currenttop = RemoteObject.solve(new RemoteObject[] {_starttopmargin,(RemoteObject.solve(new RemoteObject[] {RemoteObject.createImmutable(_i),_itemspacing}, "*",0, 1))}, "+",1, 1);Debug.locals.put("currentTop", _currenttop);Debug.locals.put("currentTop", _currenttop);
 BA.debugLineNum = 617;BA.debugLine="Dim pnlShadow As Panel";
Debug.ShouldStop(256);
_pnlshadow = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");Debug.locals.put("pnlShadow", _pnlshadow);
 BA.debugLineNum = 618;BA.debugLine="pnlShadow.Initialize(\"pnlShadow\")";
Debug.ShouldStop(512);
_pnlshadow.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("pnlShadow")));
 BA.debugLineNum = 620;BA.debugLine="Dim cd As ColorDrawable";
Debug.ShouldStop(2048);
_cd = RemoteObject.createNew ("anywheresoftware.b4a.objects.drawable.ColorDrawable");Debug.locals.put("cd", _cd);
 BA.debugLineNum = 621;BA.debugLine="cd.Initialize2(0xFF000000, 4dip, 0dip, 0xFF00000";
Debug.ShouldStop(4096);
_cd.runVoidMethod ("Initialize2",(Object)(BA.numberCast(int.class, ((int)0xff000000))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 4)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 0)))),(Object)(BA.numberCast(int.class, ((int)0xff000000))));
 BA.debugLineNum = 622;BA.debugLine="pnlShadow.Background = cd";
Debug.ShouldStop(8192);
_pnlshadow.runMethod(false,"setBackground",(_cd.getObject()));
 BA.debugLineNum = 623;BA.debugLine="pnlShadow.Elevation = 0";
Debug.ShouldStop(16384);
_pnlshadow.runMethod(true,"setElevation",BA.numberCast(float.class, 0));
 BA.debugLineNum = 625;BA.debugLine="Dim pnlWidth As Int = 330dip";
Debug.ShouldStop(65536);
_pnlwidth = main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 330)));Debug.locals.put("pnlWidth", _pnlwidth);Debug.locals.put("pnlWidth", _pnlwidth);
 BA.debugLineNum = 626;BA.debugLine="Dim pnlHeight As Int = 88dip";
Debug.ShouldStop(131072);
_pnlheight = main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 88)));Debug.locals.put("pnlHeight", _pnlheight);Debug.locals.put("pnlHeight", _pnlheight);
 BA.debugLineNum = 628;BA.debugLine="Dim shadowLeftPos As Int = gradepnl.Width - 13di";
Debug.ShouldStop(524288);
_shadowleftpos = RemoteObject.solve(new RemoteObject[] {main.mostCurrent._gradepnl.runMethod(true,"getWidth"),main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 13))),_pnlwidth}, "--",2, 1);Debug.locals.put("shadowLeftPos", _shadowleftpos);Debug.locals.put("shadowLeftPos", _shadowleftpos);
 BA.debugLineNum = 629;BA.debugLine="Dim shadowTopPos As Int = currentTop + 7dip";
Debug.ShouldStop(1048576);
_shadowtoppos = RemoteObject.solve(new RemoteObject[] {_currenttop,main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 7)))}, "+",1, 1);Debug.locals.put("shadowTopPos", _shadowtoppos);Debug.locals.put("shadowTopPos", _shadowtoppos);
 BA.debugLineNum = 631;BA.debugLine="SV1.Panel.AddView(pnlShadow, shadowLeftPos, shad";
Debug.ShouldStop(4194304);
main.mostCurrent._sv1.runMethod(false,"getPanel").runVoidMethod ("AddView",(Object)((_pnlshadow.getObject())),(Object)(_shadowleftpos),(Object)(_shadowtoppos),(Object)(_pnlwidth),(Object)(_pnlheight));
 BA.debugLineNum = 633;BA.debugLine="Dim name As String = studentNames.Get(i)";
Debug.ShouldStop(16777216);
_name = BA.ObjectToString(_studentnames.runMethod(false,"Get",(Object)(BA.numberCast(int.class, _i))));Debug.locals.put("name", _name);Debug.locals.put("name", _name);
 BA.debugLineNum = 634;BA.debugLine="Dim grade As String = GetStudentGrade(name)";
Debug.ShouldStop(33554432);
_grade = _getstudentgrade(_name);Debug.locals.put("grade", _grade);Debug.locals.put("grade", _grade);
 BA.debugLineNum = 635;BA.debugLine="studentgrade(name, grade, currentTop)";
Debug.ShouldStop(67108864);
_studentgrade(_name,_grade,_currenttop);
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 637;BA.debugLine="End Sub";
Debug.ShouldStop(268435456);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _panel2_click() throws Exception{
try {
		Debug.PushSubsStack("Panel2_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,1033);
if (RapidSub.canDelegate("panel2_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","panel2_click");}
 BA.debugLineNum = 1033;BA.debugLine="Private Sub Panel2_Click";
Debug.ShouldStop(256);
 BA.debugLineNum = 1034;BA.debugLine="updatedata";
Debug.ShouldStop(512);
_updatedata();
 BA.debugLineNum = 1035;BA.debugLine="End Sub";
Debug.ShouldStop(1024);
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
		Debug.PushSubsStack("PercentageToGrade (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,258);
if (RapidSub.canDelegate("percentagetograde")) { return b4a.example.main.remoteMe.runUserSub(false, "main","percentagetograde", _pct);}
Debug.locals.put("pct", _pct);
 BA.debugLineNum = 258;BA.debugLine="Sub PercentageToGrade(pct As Double) As String";
Debug.ShouldStop(2);
 BA.debugLineNum = 259;BA.debugLine="If pct >= 90 Then Return \"A\"";
Debug.ShouldStop(4);
if (RemoteObject.solveBoolean("g",_pct,BA.numberCast(double.class, 90))) { 
if (true) return BA.ObjectToString("A");};
 BA.debugLineNum = 260;BA.debugLine="If pct >= 80 Then Return \"B\"";
Debug.ShouldStop(8);
if (RemoteObject.solveBoolean("g",_pct,BA.numberCast(double.class, 80))) { 
if (true) return BA.ObjectToString("B");};
 BA.debugLineNum = 261;BA.debugLine="If pct >= 70 Then Return \"C\"";
Debug.ShouldStop(16);
if (RemoteObject.solveBoolean("g",_pct,BA.numberCast(double.class, 70))) { 
if (true) return BA.ObjectToString("C");};
 BA.debugLineNum = 262;BA.debugLine="If pct >= 60 Then Return \"D\"";
Debug.ShouldStop(32);
if (RemoteObject.solveBoolean("g",_pct,BA.numberCast(double.class, 60))) { 
if (true) return BA.ObjectToString("D");};
 BA.debugLineNum = 263;BA.debugLine="Return \"F\"";
Debug.ShouldStop(64);
if (true) return BA.ObjectToString("F");
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
		Debug.PushSubsStack("profilebtn_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,204);
if (RapidSub.canDelegate("profilebtn_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","profilebtn_click");}
 BA.debugLineNum = 204;BA.debugLine="Private Sub profilebtn_Click";
Debug.ShouldStop(2048);
 BA.debugLineNum = 205;BA.debugLine="HighlightTab(profilebtn)";
Debug.ShouldStop(4096);
_highlighttab(main.mostCurrent._profilebtn);
 BA.debugLineNum = 206;BA.debugLine="showprofile";
Debug.ShouldStop(8192);
_showprofile();
 BA.debugLineNum = 207;BA.debugLine="End Sub";
Debug.ShouldStop(16384);
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
		Debug.PushSubsStack("SetGradient (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,411);
if (RapidSub.canDelegate("setgradient")) { return b4a.example.main.remoteMe.runUserSub(false, "main","setgradient", _pnl, _color1, _color2);}
RemoteObject _gd = RemoteObject.declareNull("anywheresoftware.b4a.objects.drawable.GradientDrawable");
Debug.locals.put("pnl", _pnl);
Debug.locals.put("Color1", _color1);
Debug.locals.put("Color2", _color2);
 BA.debugLineNum = 411;BA.debugLine="Sub SetGradient(pnl As Panel, Color1 As Int, Color";
Debug.ShouldStop(67108864);
 BA.debugLineNum = 412;BA.debugLine="Dim gd As GradientDrawable";
Debug.ShouldStop(134217728);
_gd = RemoteObject.createNew ("anywheresoftware.b4a.objects.drawable.GradientDrawable");Debug.locals.put("gd", _gd);
 BA.debugLineNum = 413;BA.debugLine="gd.Initialize(\"BR_TL\", Array As Int(Color1, Color";
Debug.ShouldStop(268435456);
_gd.runVoidMethod ("Initialize",(Object)(BA.getEnumFromString(BA.getDeviceClass("android.graphics.drawable.GradientDrawable.Orientation"),"BR_TL")),(Object)(RemoteObject.createNewArray("int",new int[] {2},new Object[] {_color1,_color2})));
 BA.debugLineNum = 414;BA.debugLine="pnl.Background = gd";
Debug.ShouldStop(536870912);
_pnl.runMethod(false,"setBackground",(_gd.getObject()));
 BA.debugLineNum = 415;BA.debugLine="End Sub";
Debug.ShouldStop(1073741824);
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
		Debug.PushSubsStack("showdashboard (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,443);
if (RapidSub.canDelegate("showdashboard")) { return b4a.example.main.remoteMe.runUserSub(false, "main","showdashboard");}
 BA.debugLineNum = 443;BA.debugLine="Sub showdashboard";
Debug.ShouldStop(67108864);
 BA.debugLineNum = 444;BA.debugLine="pnlmain.RemoveAllViews";
Debug.ShouldStop(134217728);
main.mostCurrent._pnlmain.runVoidMethod ("RemoveAllViews");
 BA.debugLineNum = 445;BA.debugLine="pnlmain.LoadLayout(\"dashboard\")";
Debug.ShouldStop(268435456);
main.mostCurrent._pnlmain.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("dashboard")),main.mostCurrent.activityBA);
 BA.debugLineNum = 446;BA.debugLine="generategraph";
Debug.ShouldStop(536870912);
_generategraph();
 BA.debugLineNum = 447;BA.debugLine="End Sub";
Debug.ShouldStop(1073741824);
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
		Debug.PushSubsStack("showedit (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,688);
if (RapidSub.canDelegate("showedit")) { return b4a.example.main.remoteMe.runUserSub(false, "main","showedit");}
 BA.debugLineNum = 688;BA.debugLine="Sub showedit";
Debug.ShouldStop(32768);
 BA.debugLineNum = 689;BA.debugLine="pnlmain.RemoveAllViews";
Debug.ShouldStop(65536);
main.mostCurrent._pnlmain.runVoidMethod ("RemoveAllViews");
 BA.debugLineNum = 690;BA.debugLine="pnlmain.LoadLayout(\"edit\")";
Debug.ShouldStop(131072);
main.mostCurrent._pnlmain.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("edit")),main.mostCurrent.activityBA);
 BA.debugLineNum = 691;BA.debugLine="End Sub";
Debug.ShouldStop(262144);
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
		Debug.PushSubsStack("showgrades (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,591);
if (RapidSub.canDelegate("showgrades")) { return b4a.example.main.remoteMe.runUserSub(false, "main","showgrades");}
RemoteObject _names = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _studentsize = RemoteObject.createImmutable(0);
RemoteObject _totalheight = RemoteObject.createImmutable(0);
 BA.debugLineNum = 591;BA.debugLine="Sub showgrades";
Debug.ShouldStop(16384);
 BA.debugLineNum = 592;BA.debugLine="pnlmain.RemoveAllViews";
Debug.ShouldStop(32768);
main.mostCurrent._pnlmain.runVoidMethod ("RemoveAllViews");
 BA.debugLineNum = 593;BA.debugLine="pnlmain.LoadLayout(\"grades\")";
Debug.ShouldStop(65536);
main.mostCurrent._pnlmain.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("grades")),main.mostCurrent.activityBA);
 BA.debugLineNum = 596;BA.debugLine="Dim names As List = GetUniqueStudentNames";
Debug.ShouldStop(524288);
_names = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_names = _getuniquestudentnames();Debug.locals.put("names", _names);Debug.locals.put("names", _names);
 BA.debugLineNum = 597;BA.debugLine="Dim studentSize As Int = names.Size";
Debug.ShouldStop(1048576);
_studentsize = _names.runMethod(true,"getSize");Debug.locals.put("studentSize", _studentsize);Debug.locals.put("studentSize", _studentsize);
 BA.debugLineNum = 601;BA.debugLine="Dim totalHeight As Int = (studentSize * 108dip) +";
Debug.ShouldStop(16777216);
_totalheight = RemoteObject.solve(new RemoteObject[] {(RemoteObject.solve(new RemoteObject[] {_studentsize,main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 108)))}, "*",0, 1)),main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 40)))}, "+",1, 1);Debug.locals.put("totalHeight", _totalheight);Debug.locals.put("totalHeight", _totalheight);
 BA.debugLineNum = 602;BA.debugLine="SV1.Panel.Height = totalHeight";
Debug.ShouldStop(33554432);
main.mostCurrent._sv1.runMethod(false,"getPanel").runMethod(true,"setHeight",_totalheight);
 BA.debugLineNum = 603;BA.debugLine="SV1.Panel.Width = SV1.Width";
Debug.ShouldStop(67108864);
main.mostCurrent._sv1.runMethod(false,"getPanel").runMethod(true,"setWidth",main.mostCurrent._sv1.runMethod(true,"getWidth"));
 BA.debugLineNum = 605;BA.debugLine="SV1.Panel.RemoveAllViews";
Debug.ShouldStop(268435456);
main.mostCurrent._sv1.runMethod(false,"getPanel").runVoidMethod ("RemoveAllViews");
 BA.debugLineNum = 607;BA.debugLine="makeshadow(studentSize, names)";
Debug.ShouldStop(1073741824);
_makeshadow(_studentsize,_names);
 BA.debugLineNum = 608;BA.debugLine="End Sub";
Debug.ShouldStop(-2147483648);
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
		Debug.PushSubsStack("showprofile (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,708);
if (RapidSub.canDelegate("showprofile")) { return b4a.example.main.remoteMe.runUserSub(false, "main","showprofile");}
 BA.debugLineNum = 708;BA.debugLine="Sub showprofile";
Debug.ShouldStop(8);
 BA.debugLineNum = 709;BA.debugLine="HighlightTab(profilebtn)";
Debug.ShouldStop(16);
_highlighttab(main.mostCurrent._profilebtn);
 BA.debugLineNum = 710;BA.debugLine="ShowTable";
Debug.ShouldStop(32);
_showtable();
 BA.debugLineNum = 711;BA.debugLine="End Sub";
Debug.ShouldStop(64);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _showselectids() throws Exception{
try {
		Debug.PushSubsStack("showselectids (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,803);
if (RapidSub.canDelegate("showselectids")) { return b4a.example.main.remoteMe.runUserSub(false, "main","showselectids");}
RemoteObject _pnlcard = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
RemoteObject _cdcard = RemoteObject.declareNull("anywheresoftware.b4a.objects.drawable.ColorDrawable");
RemoteObject _pnlleft = RemoteObject.createImmutable(0);
RemoteObject _pnltop = RemoteObject.createImmutable(0);
RemoteObject _pnlwidth = RemoteObject.createImmutable(0);
RemoteObject _pnlheight = RemoteObject.createImmutable(0);
RemoteObject _lbl = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
RemoteObject _btn = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
 BA.debugLineNum = 803;BA.debugLine="Sub showselectids";
Debug.ShouldStop(4);
 BA.debugLineNum = 804;BA.debugLine="If showselectid Then";
Debug.ShouldStop(8);
if (main._showselectid.<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 805;BA.debugLine="isselectingid = True";
Debug.ShouldStop(16);
main._isselectingid = main.mostCurrent.__c.getField(true,"True");
 BA.debugLineNum = 806;BA.debugLine="update.Enabled = False";
Debug.ShouldStop(32);
main.mostCurrent._update.runMethod(true,"setEnabled",main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 809;BA.debugLine="If bak.IsInitialized Then";
Debug.ShouldStop(256);
if (main.mostCurrent._bak.runMethod(true,"IsInitialized").<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 810;BA.debugLine="bak.RemoveView";
Debug.ShouldStop(512);
main.mostCurrent._bak.runVoidMethod ("RemoveView");
 };
 BA.debugLineNum = 814;BA.debugLine="bak.Initialize(\"bak\")";
Debug.ShouldStop(8192);
main.mostCurrent._bak.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("bak")));
 BA.debugLineNum = 815;BA.debugLine="bak.Color = 0x68000000";
Debug.ShouldStop(16384);
main.mostCurrent._bak.runVoidMethod ("setColor",BA.numberCast(int.class, ((int)0x68000000)));
 BA.debugLineNum = 817;BA.debugLine="Dim pnlCard As Panel";
Debug.ShouldStop(65536);
_pnlcard = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");Debug.locals.put("pnlCard", _pnlcard);
 BA.debugLineNum = 818;BA.debugLine="pnlCard.Initialize(\"pnlCard\")";
Debug.ShouldStop(131072);
_pnlcard.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("pnlCard")));
 BA.debugLineNum = 821;BA.debugLine="Dim cdCard As ColorDrawable";
Debug.ShouldStop(1048576);
_cdcard = RemoteObject.createNew ("anywheresoftware.b4a.objects.drawable.ColorDrawable");Debug.locals.put("cdCard", _cdcard);
 BA.debugLineNum = 822;BA.debugLine="cdCard.Initialize2(0xFF2C2C2C, 40dip, 0dip, 0xFF";
Debug.ShouldStop(2097152);
_cdcard.runVoidMethod ("Initialize2",(Object)(BA.numberCast(int.class, ((int)0xff2c2c2c))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 40)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 0)))),(Object)(BA.numberCast(int.class, ((int)0xff000000))));
 BA.debugLineNum = 823;BA.debugLine="pnlCard.Background = cdCard";
Debug.ShouldStop(4194304);
_pnlcard.runMethod(false,"setBackground",(_cdcard.getObject()));
 BA.debugLineNum = 824;BA.debugLine="pnlCard.Elevation = 4dip";
Debug.ShouldStop(8388608);
_pnlcard.runMethod(true,"setElevation",BA.numberCast(float.class, main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 4)))));
 BA.debugLineNum = 827;BA.debugLine="Dim pnlLeft As Int = 30dip";
Debug.ShouldStop(67108864);
_pnlleft = main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 30)));Debug.locals.put("pnlLeft", _pnlleft);Debug.locals.put("pnlLeft", _pnlleft);
 BA.debugLineNum = 828;BA.debugLine="Dim pnlTop As Int = 220dip";
Debug.ShouldStop(134217728);
_pnltop = main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 220)));Debug.locals.put("pnlTop", _pnltop);Debug.locals.put("pnlTop", _pnltop);
 BA.debugLineNum = 829;BA.debugLine="Dim pnlWidth As Int = 300dip";
Debug.ShouldStop(268435456);
_pnlwidth = main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 300)));Debug.locals.put("pnlWidth", _pnlwidth);Debug.locals.put("pnlWidth", _pnlwidth);
 BA.debugLineNum = 830;BA.debugLine="Dim pnlHeight As Int = 280dip";
Debug.ShouldStop(536870912);
_pnlheight = main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 280)));Debug.locals.put("pnlHeight", _pnlheight);Debug.locals.put("pnlHeight", _pnlheight);
 BA.debugLineNum = 833;BA.debugLine="Dim lbl As Label";
Debug.ShouldStop(1);
_lbl = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("lbl", _lbl);
 BA.debugLineNum = 834;BA.debugLine="lbl.Initialize(\"lbl\")";
Debug.ShouldStop(2);
_lbl.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("lbl")));
 BA.debugLineNum = 835;BA.debugLine="lbl.Text = \"SELECT ID\"";
Debug.ShouldStop(4);
_lbl.runMethod(true,"setText",BA.ObjectToCharSequence("SELECT ID"));
 BA.debugLineNum = 836;BA.debugLine="lbl.Gravity = Bit.Or(Gravity.CENTER_HORIZONTAL,";
Debug.ShouldStop(8);
_lbl.runMethod(true,"setGravity",main.mostCurrent.__c.getField(false,"Bit").runMethod(true,"Or",(Object)(main.mostCurrent.__c.getField(false,"Gravity").getField(true,"CENTER_HORIZONTAL")),(Object)(main.mostCurrent.__c.getField(false,"Gravity").getField(true,"CENTER_VERTICAL"))));
 BA.debugLineNum = 837;BA.debugLine="lbl.Typeface = Typeface.CreateNew(Typeface.SERIF";
Debug.ShouldStop(16);
_lbl.runMethod(false,"setTypeface",main.mostCurrent.__c.getField(false,"Typeface").runMethod(false,"CreateNew",(Object)(main.mostCurrent.__c.getField(false,"Typeface").getField(false,"SERIF")),(Object)(main.mostCurrent.__c.getField(false,"Typeface").getField(true,"STYLE_BOLD_ITALIC"))));
 BA.debugLineNum = 838;BA.debugLine="lbl.TextSize = 20";
Debug.ShouldStop(32);
_lbl.runMethod(true,"setTextSize",BA.numberCast(float.class, 20));
 BA.debugLineNum = 839;BA.debugLine="lbl.TextColor = Colors.White";
Debug.ShouldStop(64);
_lbl.runMethod(true,"setTextColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 BA.debugLineNum = 840;BA.debugLine="pnlCard.AddView(lbl, 35dip, 30dip, 230dip, 40dip";
Debug.ShouldStop(128);
_pnlcard.runVoidMethod ("AddView",(Object)((_lbl.getObject())),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 35)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 30)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 230)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 40)))));
 BA.debugLineNum = 843;BA.debugLine="spinner.Initialize(\"spinner\")";
Debug.ShouldStop(1024);
main.mostCurrent._spinner.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("spinner")));
 BA.debugLineNum = 844;BA.debugLine="spinner.TextColor = Colors.Black";
Debug.ShouldStop(2048);
main.mostCurrent._spinner.runMethod(true,"setTextColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 BA.debugLineNum = 845;BA.debugLine="spinner.AddAll(GetUnique(student_ids))";
Debug.ShouldStop(4096);
main.mostCurrent._spinner.runVoidMethod ("AddAll",(Object)(_getunique(main.mostCurrent._student_ids)));
 BA.debugLineNum = 846;BA.debugLine="spinner.Color = Colors.White";
Debug.ShouldStop(8192);
main.mostCurrent._spinner.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 BA.debugLineNum = 847;BA.debugLine="pnlCard.AddView(spinner, 75dip, 115dip, 150dip,";
Debug.ShouldStop(16384);
_pnlcard.runVoidMethod ("AddView",(Object)((main.mostCurrent._spinner.getObject())),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 75)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 115)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 150)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 50)))));
 BA.debugLineNum = 850;BA.debugLine="Dim btn As Button";
Debug.ShouldStop(131072);
_btn = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");Debug.locals.put("btn", _btn);
 BA.debugLineNum = 851;BA.debugLine="btn.Initialize(\"btn\")";
Debug.ShouldStop(262144);
_btn.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("btn")));
 BA.debugLineNum = 852;BA.debugLine="btn.Text = \"SELECT\"";
Debug.ShouldStop(524288);
_btn.runMethod(true,"setText",BA.ObjectToCharSequence("SELECT"));
 BA.debugLineNum = 853;BA.debugLine="btn.Gravity = Bit.Or(Gravity.CENTER_HORIZONTAL,";
Debug.ShouldStop(1048576);
_btn.runMethod(true,"setGravity",main.mostCurrent.__c.getField(false,"Bit").runMethod(true,"Or",(Object)(main.mostCurrent.__c.getField(false,"Gravity").getField(true,"CENTER_HORIZONTAL")),(Object)(main.mostCurrent.__c.getField(false,"Gravity").getField(true,"CENTER_VERTICAL"))));
 BA.debugLineNum = 854;BA.debugLine="btn.Typeface = Typeface.DEFAULT_BOLD";
Debug.ShouldStop(2097152);
_btn.runMethod(false,"setTypeface",main.mostCurrent.__c.getField(false,"Typeface").getField(false,"DEFAULT_BOLD"));
 BA.debugLineNum = 855;BA.debugLine="btn.TextColor = Colors.Black";
Debug.ShouldStop(4194304);
_btn.runMethod(true,"setTextColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 BA.debugLineNum = 856;BA.debugLine="btn.Color = Colors.White";
Debug.ShouldStop(8388608);
_btn.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 BA.debugLineNum = 857;BA.debugLine="pnlCard.AddView(btn, 50dip, 210dip, 200dip, 43di";
Debug.ShouldStop(16777216);
_pnlcard.runVoidMethod ("AddView",(Object)((_btn.getObject())),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 50)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 210)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 200)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 43)))));
 BA.debugLineNum = 860;BA.debugLine="bak.AddView(pnlCard, pnlLeft, pnlTop, pnlWidth,";
Debug.ShouldStop(134217728);
main.mostCurrent._bak.runVoidMethod ("AddView",(Object)((_pnlcard.getObject())),(Object)(_pnlleft),(Object)(_pnltop),(Object)(_pnlwidth),(Object)(_pnlheight));
 BA.debugLineNum = 863;BA.debugLine="Activity.AddView(bak, 0, 0, Activity.Width, Acti";
Debug.ShouldStop(1073741824);
main.mostCurrent._activity.runVoidMethod ("AddView",(Object)((main.mostCurrent._bak.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(main.mostCurrent._activity.runMethod(true,"getWidth")),(Object)(main.mostCurrent._activity.runMethod(true,"getHeight")));
 }else {
 BA.debugLineNum = 867;BA.debugLine="If bak.IsInitialized Then";
Debug.ShouldStop(4);
if (main.mostCurrent._bak.runMethod(true,"IsInitialized").<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 868;BA.debugLine="bak.RemoveView";
Debug.ShouldStop(8);
main.mostCurrent._bak.runVoidMethod ("RemoveView");
 };
 };
 BA.debugLineNum = 871;BA.debugLine="End Sub";
Debug.ShouldStop(64);
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
		Debug.PushSubsStack("ShowTable (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,713);
if (RapidSub.canDelegate("showtable")) { return b4a.example.main.remoteMe.runUserSub(false, "main","showtable");}
 BA.debugLineNum = 713;BA.debugLine="Sub ShowTable";
Debug.ShouldStop(256);
 BA.debugLineNum = 714;BA.debugLine="pnlmain.RemoveAllViews";
Debug.ShouldStop(512);
main.mostCurrent._pnlmain.runVoidMethod ("RemoveAllViews");
 BA.debugLineNum = 715;BA.debugLine="pnlmain.LoadLayout(\"profile\")";
Debug.ShouldStop(1024);
main.mostCurrent._pnlmain.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("profile")),main.mostCurrent.activityBA);
 BA.debugLineNum = 716;BA.debugLine="Table = SV.Panel";
Debug.ShouldStop(2048);
main.mostCurrent._table = main.mostCurrent._sv.runMethod(false,"getPanel");
 BA.debugLineNum = 717;BA.debugLine="NumberOfColumns = 6";
Debug.ShouldStop(4096);
main._numberofcolumns = BA.numberCast(int.class, 6);
 BA.debugLineNum = 718;BA.debugLine="ColumnWidth = SV.Width / NumberOfColumns";
Debug.ShouldStop(8192);
main._columnwidth = BA.numberCast(int.class, RemoteObject.solve(new RemoteObject[] {main.mostCurrent._sv.runMethod(true,"getWidth"),main._numberofcolumns}, "/",0, 0));
 BA.debugLineNum = 719;BA.debugLine="loadtable";
Debug.ShouldStop(16384);
_loadtable();
 BA.debugLineNum = 720;BA.debugLine="End Sub";
Debug.ShouldStop(32768);
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
		Debug.PushSubsStack("studentgrade (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,639);
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
 BA.debugLineNum = 639;BA.debugLine="Sub studentgrade(studentName As String, grade As S";
Debug.ShouldStop(1073741824);
 BA.debugLineNum = 640;BA.debugLine="Dim pnlCard As Panel";
Debug.ShouldStop(-2147483648);
_pnlcard = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");Debug.locals.put("pnlCard", _pnlcard);
 BA.debugLineNum = 641;BA.debugLine="pnlCard.Initialize(\"pnlCard\")";
Debug.ShouldStop(1);
_pnlcard.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("pnlCard")));
 BA.debugLineNum = 643;BA.debugLine="Dim cd As ColorDrawable";
Debug.ShouldStop(4);
_cd = RemoteObject.createNew ("anywheresoftware.b4a.objects.drawable.ColorDrawable");Debug.locals.put("cd", _cd);
 BA.debugLineNum = 644;BA.debugLine="cd.Initialize2(0xFFFFD400, 2dip, 2dip, 0xFF000000";
Debug.ShouldStop(8);
_cd.runVoidMethod ("Initialize2",(Object)(BA.numberCast(int.class, ((int)0xffffd400))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 2)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 2)))),(Object)(BA.numberCast(int.class, ((int)0xff000000))));
 BA.debugLineNum = 645;BA.debugLine="pnlCard.Background = cd";
Debug.ShouldStop(16);
_pnlcard.runMethod(false,"setBackground",(_cd.getObject()));
 BA.debugLineNum = 646;BA.debugLine="pnlCard.Elevation = 0";
Debug.ShouldStop(32);
_pnlcard.runMethod(true,"setElevation",BA.numberCast(float.class, 0));
 BA.debugLineNum = 648;BA.debugLine="Dim pnlWidth As Int = 330dip";
Debug.ShouldStop(128);
_pnlwidth = main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 330)));Debug.locals.put("pnlWidth", _pnlwidth);Debug.locals.put("pnlWidth", _pnlwidth);
 BA.debugLineNum = 649;BA.debugLine="Dim pnlHeight As Int = 88dip";
Debug.ShouldStop(256);
_pnlheight = main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 88)));Debug.locals.put("pnlHeight", _pnlheight);Debug.locals.put("pnlHeight", _pnlheight);
 BA.debugLineNum = 650;BA.debugLine="Dim rightEdgeDistance As Int = 20dip";
Debug.ShouldStop(512);
_rightedgedistance = main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 20)));Debug.locals.put("rightEdgeDistance", _rightedgedistance);Debug.locals.put("rightEdgeDistance", _rightedgedistance);
 BA.debugLineNum = 652;BA.debugLine="Dim leftPos As Int = gradepnl.Width - rightEdgeDi";
Debug.ShouldStop(2048);
_leftpos = RemoteObject.solve(new RemoteObject[] {main.mostCurrent._gradepnl.runMethod(true,"getWidth"),_rightedgedistance,_pnlwidth}, "--",2, 1);Debug.locals.put("leftPos", _leftpos);Debug.locals.put("leftPos", _leftpos);
 BA.debugLineNum = 653;BA.debugLine="Dim topPos As Int = currentTop";
Debug.ShouldStop(4096);
_toppos = _currenttop;Debug.locals.put("topPos", _toppos);Debug.locals.put("topPos", _toppos);
 BA.debugLineNum = 656;BA.debugLine="Dim lblGrade As Label";
Debug.ShouldStop(32768);
_lblgrade = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("lblGrade", _lblgrade);
 BA.debugLineNum = 657;BA.debugLine="lblGrade.Initialize(\"lblGrade\")";
Debug.ShouldStop(65536);
_lblgrade.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("lblGrade")));
 BA.debugLineNum = 658;BA.debugLine="lblGrade.Text = grade";
Debug.ShouldStop(131072);
_lblgrade.runMethod(true,"setText",BA.ObjectToCharSequence(_grade));
 BA.debugLineNum = 659;BA.debugLine="lblGrade.TextSize = 30";
Debug.ShouldStop(262144);
_lblgrade.runMethod(true,"setTextSize",BA.numberCast(float.class, 30));
 BA.debugLineNum = 660;BA.debugLine="lblGrade.TextColor = Colors.Black";
Debug.ShouldStop(524288);
_lblgrade.runMethod(true,"setTextColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 BA.debugLineNum = 661;BA.debugLine="lblGrade.Gravity = Bit.Or(Gravity.CENTER_HORIZONT";
Debug.ShouldStop(1048576);
_lblgrade.runMethod(true,"setGravity",main.mostCurrent.__c.getField(false,"Bit").runMethod(true,"Or",(Object)(main.mostCurrent.__c.getField(false,"Gravity").getField(true,"CENTER_HORIZONTAL")),(Object)(main.mostCurrent.__c.getField(false,"Gravity").getField(true,"CENTER_VERTICAL"))));
 BA.debugLineNum = 662;BA.debugLine="lblGrade.Typeface = Typeface.CreateNew(Typeface.S";
Debug.ShouldStop(2097152);
_lblgrade.runMethod(false,"setTypeface",main.mostCurrent.__c.getField(false,"Typeface").runMethod(false,"CreateNew",(Object)(main.mostCurrent.__c.getField(false,"Typeface").getField(false,"SERIF")),(Object)(main.mostCurrent.__c.getField(false,"Typeface").getField(true,"STYLE_BOLD_ITALIC"))));
 BA.debugLineNum = 663;BA.debugLine="lblGrade.SingleLine = False";
Debug.ShouldStop(4194304);
_lblgrade.runVoidMethod ("setSingleLine",main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 664;BA.debugLine="lblGrade.Enabled = True";
Debug.ShouldStop(8388608);
_lblgrade.runMethod(true,"setEnabled",main.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 665;BA.debugLine="lblGrade.Visible = True";
Debug.ShouldStop(16777216);
_lblgrade.runMethod(true,"setVisible",main.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 667;BA.debugLine="Dim gradeRightEdge As Int = 264dip";
Debug.ShouldStop(67108864);
_graderightedge = main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 264)));Debug.locals.put("gradeRightEdge", _graderightedge);Debug.locals.put("gradeRightEdge", _graderightedge);
 BA.debugLineNum = 668;BA.debugLine="Dim gradeWidth As Int = pnlWidth - gradeRightEdge";
Debug.ShouldStop(134217728);
_gradewidth = RemoteObject.solve(new RemoteObject[] {_pnlwidth,_graderightedge}, "-",1, 1);Debug.locals.put("gradeWidth", _gradewidth);Debug.locals.put("gradeWidth", _gradewidth);
 BA.debugLineNum = 669;BA.debugLine="pnlCard.AddView(lblGrade, 0, 0, gradeWidth, pnlHe";
Debug.ShouldStop(268435456);
_pnlcard.runVoidMethod ("AddView",(Object)((_lblgrade.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(_gradewidth),(Object)(_pnlheight));
 BA.debugLineNum = 672;BA.debugLine="Dim lbl As Label";
Debug.ShouldStop(-2147483648);
_lbl = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("lbl", _lbl);
 BA.debugLineNum = 673;BA.debugLine="lbl.Initialize(\"lbl\")";
Debug.ShouldStop(1);
_lbl.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("lbl")));
 BA.debugLineNum = 674;BA.debugLine="lbl.Text = studentName";
Debug.ShouldStop(2);
_lbl.runMethod(true,"setText",BA.ObjectToCharSequence(_studentname));
 BA.debugLineNum = 675;BA.debugLine="lbl.Gravity = Gravity.CENTER_VERTICAL";
Debug.ShouldStop(4);
_lbl.runMethod(true,"setGravity",main.mostCurrent.__c.getField(false,"Gravity").getField(true,"CENTER_VERTICAL"));
 BA.debugLineNum = 676;BA.debugLine="lbl.Typeface = Typeface.CreateNew(Typeface.SERIF,";
Debug.ShouldStop(8);
_lbl.runMethod(false,"setTypeface",main.mostCurrent.__c.getField(false,"Typeface").runMethod(false,"CreateNew",(Object)(main.mostCurrent.__c.getField(false,"Typeface").getField(false,"SERIF")),(Object)(main.mostCurrent.__c.getField(false,"Typeface").getField(true,"STYLE_BOLD_ITALIC"))));
 BA.debugLineNum = 677;BA.debugLine="lbl.TextSize = 20";
Debug.ShouldStop(16);
_lbl.runMethod(true,"setTextSize",BA.numberCast(float.class, 20));
 BA.debugLineNum = 678;BA.debugLine="lbl.TextColor = Colors.Black";
Debug.ShouldStop(32);
_lbl.runMethod(true,"setTextColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 BA.debugLineNum = 679;BA.debugLine="lbl.Padding = Array As Int(10dip, 5dip, 10dip, 5d";
Debug.ShouldStop(64);
_lbl.runMethod(false,"setPadding",RemoteObject.createNewArray("int",new int[] {4},new Object[] {main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10))),main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 5))),main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10))),main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 5)))}));
 BA.debugLineNum = 680;BA.debugLine="pnlCard.AddView(lbl, gradeWidth, 0, pnlWidth - gr";
Debug.ShouldStop(128);
_pnlcard.runVoidMethod ("AddView",(Object)((_lbl.getObject())),(Object)(_gradewidth),(Object)(BA.numberCast(int.class, 0)),(Object)(RemoteObject.solve(new RemoteObject[] {_pnlwidth,_gradewidth}, "-",1, 1)),(Object)(_pnlheight));
 BA.debugLineNum = 681;BA.debugLine="SV1.Panel.AddView(pnlCard, leftPos, topPos, pnlWi";
Debug.ShouldStop(256);
main.mostCurrent._sv1.runMethod(false,"getPanel").runVoidMethod ("AddView",(Object)((_pnlcard.getObject())),(Object)(_leftpos),(Object)(_toppos),(Object)(_pnlwidth),(Object)(_pnlheight));
 BA.debugLineNum = 682;BA.debugLine="End Sub";
Debug.ShouldStop(512);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _update_click() throws Exception{
try {
		Debug.PushSubsStack("update_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,983);
if (RapidSub.canDelegate("update_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","update_click");}
 BA.debugLineNum = 983;BA.debugLine="Private Sub update_Click";
Debug.ShouldStop(4194304);
 BA.debugLineNum = 984;BA.debugLine="todelete = False";
Debug.ShouldStop(8388608);
main._todelete = main.mostCurrent.__c.getField(true,"False");
 BA.debugLineNum = 985;BA.debugLine="showselectid = True";
Debug.ShouldStop(16777216);
main._showselectid = main.mostCurrent.__c.getField(true,"True");
 BA.debugLineNum = 986;BA.debugLine="showselectids";
Debug.ShouldStop(33554432);
_showselectids();
 BA.debugLineNum = 987;BA.debugLine="End Sub";
Debug.ShouldStop(67108864);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _updatedata() throws Exception{
try {
		Debug.PushSubsStack("updatedata (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,1041);
if (RapidSub.canDelegate("updatedata")) { return b4a.example.main.remoteMe.runUserSub(false, "main","updatedata");}
RemoteObject _attendance = RemoteObject.createImmutable("");
RemoteObject _rate = RemoteObject.createImmutable("");
RemoteObject _msg = RemoteObject.createImmutable("");
RemoteObject _row = null;
RemoteObject _newrow = null;
 BA.debugLineNum = 1041;BA.debugLine="Sub updatedata";
Debug.ShouldStop(65536);
 BA.debugLineNum = 1043;BA.debugLine="If STUDENT_NAME.Text.Trim = \"\" Or STUDENT_ID.Text";
Debug.ShouldStop(262144);
if (RemoteObject.solveBoolean("=",main.mostCurrent._student_name.runMethod(true,"getText").runMethod(true,"trim"),BA.ObjectToString("")) || RemoteObject.solveBoolean("=",main.mostCurrent._student_id.runMethod(true,"getText").runMethod(true,"trim"),BA.ObjectToString(""))) { 
 BA.debugLineNum = 1044;BA.debugLine="xui.MsgboxAsync(\"Please enter Student Name and S";
Debug.ShouldStop(524288);
main._xui.runVoidMethod ("MsgboxAsync",main.processBA,(Object)(BA.ObjectToCharSequence("Please enter Student Name and Student ID.")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Validation Error"))));
 BA.debugLineNum = 1045;BA.debugLine="Return";
Debug.ShouldStop(1048576);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 1049;BA.debugLine="If EditText1.Text.Trim = \"\" Or EditText2.Text.Tri";
Debug.ShouldStop(16777216);
if (RemoteObject.solveBoolean("=",main.mostCurrent._edittext1.runMethod(true,"getText").runMethod(true,"trim"),BA.ObjectToString("")) || RemoteObject.solveBoolean("=",main.mostCurrent._edittext2.runMethod(true,"getText").runMethod(true,"trim"),BA.ObjectToString(""))) { 
 BA.debugLineNum = 1050;BA.debugLine="xui.MsgboxAsync(\"Please enter the complete score";
Debug.ShouldStop(33554432);
main._xui.runVoidMethod ("MsgboxAsync",main.processBA,(Object)(BA.ObjectToCharSequence("Please enter the complete score/rate (e.g. 15/20).")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Validation Error"))));
 BA.debugLineNum = 1051;BA.debugLine="Return";
Debug.ShouldStop(67108864);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 1055;BA.debugLine="Dim attendance As String = \"present\"";
Debug.ShouldStop(1073741824);
_attendance = BA.ObjectToString("present");Debug.locals.put("attendance", _attendance);Debug.locals.put("attendance", _attendance);
 BA.debugLineNum = 1056;BA.debugLine="If CheckBox2.Checked Then";
Debug.ShouldStop(-2147483648);
if (main.mostCurrent._checkbox2.runMethod(true,"getChecked").<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 1057;BA.debugLine="attendance = \"late\"";
Debug.ShouldStop(1);
_attendance = BA.ObjectToString("late");Debug.locals.put("attendance", _attendance);
 }else 
{ BA.debugLineNum = 1058;BA.debugLine="Else If CheckBox3.Checked Then";
Debug.ShouldStop(2);
if (main.mostCurrent._checkbox3.runMethod(true,"getChecked").<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 1059;BA.debugLine="attendance = \"absent\"";
Debug.ShouldStop(4);
_attendance = BA.ObjectToString("absent");Debug.locals.put("attendance", _attendance);
 }}
;
 BA.debugLineNum = 1063;BA.debugLine="Dim rate As String = EditText1.Text.Trim & \"/\" &";
Debug.ShouldStop(64);
_rate = RemoteObject.concat(main.mostCurrent._edittext1.runMethod(true,"getText").runMethod(true,"trim"),RemoteObject.createImmutable("/"),main.mostCurrent._edittext2.runMethod(true,"getText").runMethod(true,"trim"));Debug.locals.put("rate", _rate);Debug.locals.put("rate", _rate);
 BA.debugLineNum = 1065;BA.debugLine="Dim msg As String";
Debug.ShouldStop(256);
_msg = RemoteObject.createImmutable("");Debug.locals.put("msg", _msg);
 BA.debugLineNum = 1066;BA.debugLine="If editRowIndex > -1 Then";
Debug.ShouldStop(512);
if (RemoteObject.solveBoolean(">",main._editrowindex,BA.numberCast(double.class, -(double) (0 + 1)))) { 
 BA.debugLineNum = 1068;BA.debugLine="Dim row() As String = StudentList.Get(editRowInd";
Debug.ShouldStop(2048);
_row = (main.mostCurrent._studentlist.runMethod(false,"Get",(Object)(main._editrowindex)));Debug.locals.put("row", _row);Debug.locals.put("row", _row);
 BA.debugLineNum = 1069;BA.debugLine="row(0) = STUDENT_NAME.Text.Trim";
Debug.ShouldStop(4096);
_row.setArrayElement (main.mostCurrent._student_name.runMethod(true,"getText").runMethod(true,"trim"),BA.numberCast(int.class, 0));
 BA.debugLineNum = 1070;BA.debugLine="row(1) = STUDENT_ID.Text.Trim";
Debug.ShouldStop(8192);
_row.setArrayElement (main.mostCurrent._student_id.runMethod(true,"getText").runMethod(true,"trim"),BA.numberCast(int.class, 1));
 BA.debugLineNum = 1071;BA.debugLine="row(2) = SELECTSUBJECTS.SelectedItem";
Debug.ShouldStop(16384);
_row.setArrayElement (main.mostCurrent._selectsubjects.runMethod(true,"getSelectedItem"),BA.numberCast(int.class, 2));
 BA.debugLineNum = 1072;BA.debugLine="row(3) = STUDENT_ACTIVITY.SelectedItem";
Debug.ShouldStop(32768);
_row.setArrayElement (main.mostCurrent._student_activity.runMethod(true,"getSelectedItem"),BA.numberCast(int.class, 3));
 BA.debugLineNum = 1073;BA.debugLine="row(4) = attendance";
Debug.ShouldStop(65536);
_row.setArrayElement (_attendance,BA.numberCast(int.class, 4));
 BA.debugLineNum = 1074;BA.debugLine="row(5) = rate";
Debug.ShouldStop(131072);
_row.setArrayElement (_rate,BA.numberCast(int.class, 5));
 BA.debugLineNum = 1075;BA.debugLine="StudentList.Set(editRowIndex, row)";
Debug.ShouldStop(262144);
main.mostCurrent._studentlist.runVoidMethod ("Set",(Object)(main._editrowindex),(Object)((_row)));
 BA.debugLineNum = 1076;BA.debugLine="msg = \"Student record updated successfully!\"";
Debug.ShouldStop(524288);
_msg = BA.ObjectToString("Student record updated successfully!");Debug.locals.put("msg", _msg);
 }else {
 BA.debugLineNum = 1079;BA.debugLine="Dim newRow(6) As String";
Debug.ShouldStop(4194304);
_newrow = RemoteObject.createNewArray ("String", new int[] {6}, new Object[]{});Debug.locals.put("newRow", _newrow);
 BA.debugLineNum = 1080;BA.debugLine="newRow(0) = STUDENT_NAME.Text.Trim";
Debug.ShouldStop(8388608);
_newrow.setArrayElement (main.mostCurrent._student_name.runMethod(true,"getText").runMethod(true,"trim"),BA.numberCast(int.class, 0));
 BA.debugLineNum = 1081;BA.debugLine="newRow(1) = STUDENT_ID.Text.Trim";
Debug.ShouldStop(16777216);
_newrow.setArrayElement (main.mostCurrent._student_id.runMethod(true,"getText").runMethod(true,"trim"),BA.numberCast(int.class, 1));
 BA.debugLineNum = 1082;BA.debugLine="newRow(2) = SELECTSUBJECTS.SelectedItem";
Debug.ShouldStop(33554432);
_newrow.setArrayElement (main.mostCurrent._selectsubjects.runMethod(true,"getSelectedItem"),BA.numberCast(int.class, 2));
 BA.debugLineNum = 1083;BA.debugLine="newRow(3) = STUDENT_ACTIVITY.SelectedItem";
Debug.ShouldStop(67108864);
_newrow.setArrayElement (main.mostCurrent._student_activity.runMethod(true,"getSelectedItem"),BA.numberCast(int.class, 3));
 BA.debugLineNum = 1084;BA.debugLine="newRow(4) = attendance";
Debug.ShouldStop(134217728);
_newrow.setArrayElement (_attendance,BA.numberCast(int.class, 4));
 BA.debugLineNum = 1085;BA.debugLine="newRow(5) = rate";
Debug.ShouldStop(268435456);
_newrow.setArrayElement (_rate,BA.numberCast(int.class, 5));
 BA.debugLineNum = 1086;BA.debugLine="StudentList.Add(newRow)";
Debug.ShouldStop(536870912);
main.mostCurrent._studentlist.runVoidMethod ("Add",(Object)((_newrow)));
 BA.debugLineNum = 1087;BA.debugLine="msg = \"Student record added successfully!\"";
Debug.ShouldStop(1073741824);
_msg = BA.ObjectToString("Student record added successfully!");Debug.locals.put("msg", _msg);
 };
 BA.debugLineNum = 1091;BA.debugLine="StringUtils1.SaveCSV2(File.DirInternal, \"student.";
Debug.ShouldStop(4);
main._stringutils1.runVoidMethod ("SaveCSV2",(Object)(main.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirInternal")),(Object)(BA.ObjectToString("student.csv")),(Object)(BA.ObjectToChar(",")),(Object)(main.mostCurrent._studentlist),(Object)(main.mostCurrent._csvheaders));
 BA.debugLineNum = 1094;BA.debugLine="LoadStudentData";
Debug.ShouldStop(32);
_loadstudentdata();
 BA.debugLineNum = 1097;BA.debugLine="xui.MsgboxAsync(msg, \"Success\")";
Debug.ShouldStop(256);
main._xui.runVoidMethod ("MsgboxAsync",main.processBA,(Object)(BA.ObjectToCharSequence(_msg)),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Success"))));
 BA.debugLineNum = 1098;BA.debugLine="showedit";
Debug.ShouldStop(512);
_showedit();
 BA.debugLineNum = 1099;BA.debugLine="End Sub";
Debug.ShouldStop(1024);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _updatedata_click() throws Exception{
try {
		Debug.PushSubsStack("updatedata_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,1037);
if (RapidSub.canDelegate("updatedata_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","updatedata_click");}
 BA.debugLineNum = 1037;BA.debugLine="Private Sub updatedata_Click";
Debug.ShouldStop(4096);
 BA.debugLineNum = 1038;BA.debugLine="updatedata";
Debug.ShouldStop(8192);
_updatedata();
 BA.debugLineNum = 1039;BA.debugLine="End Sub";
Debug.ShouldStop(16384);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}