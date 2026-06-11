package anywheresoftware.b4a.table;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class main_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,39);
if (RapidSub.canDelegate("activity_create")) { return anywheresoftware.b4a.table.main.remoteMe.runUserSub(false, "main","activity_create", _firsttime);}
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 39;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(64);
 BA.debugLineNum = 40;BA.debugLine="SV.Initialize(0)";
Debug.ShouldStop(128);
main.mostCurrent._sv.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(BA.numberCast(int.class, 0)));
 BA.debugLineNum = 41;BA.debugLine="Table = SV.Panel";
Debug.ShouldStop(256);
main.mostCurrent._table = main.mostCurrent._sv.runMethod(false,"getPanel");
 BA.debugLineNum = 42;BA.debugLine="Table.Color = TableColor";
Debug.ShouldStop(512);
main.mostCurrent._table.runVoidMethod ("setColor",main._tablecolor);
 BA.debugLineNum = 43;BA.debugLine="Activity.AddView(SV, 5%x, 10%y, 90%x, 80%y)";
Debug.ShouldStop(1024);
main.mostCurrent._activity.runVoidMethod ("AddView",(Object)((main.mostCurrent._sv.getObject())),(Object)(main.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 5)),main.mostCurrent.activityBA)),(Object)(main.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 10)),main.mostCurrent.activityBA)),(Object)(main.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 90)),main.mostCurrent.activityBA)),(Object)(main.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 80)),main.mostCurrent.activityBA)));
 BA.debugLineNum = 44;BA.debugLine="ColumnWidth = SV.Width / NumberOfColumns";
Debug.ShouldStop(2048);
main._columnwidth = BA.numberCast(int.class, RemoteObject.solve(new RemoteObject[] {main.mostCurrent._sv.runMethod(true,"getWidth"),main._numberofcolumns}, "/",0, 0));
 BA.debugLineNum = 45;BA.debugLine="SelectedRow = -1";
Debug.ShouldStop(4096);
main._selectedrow = BA.numberCast(int.class, -(double) (0 + 1));
 BA.debugLineNum = 46;BA.debugLine="LoadTableFromCSV(File.DirAssets, \"citylist.csv\",";
Debug.ShouldStop(8192);
_loadtablefromcsv(main.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets"),BA.ObjectToString("citylist.csv"),main.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 48;BA.debugLine="End Sub";
Debug.ShouldStop(32768);
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
		Debug.PushSubsStack("Activity_Pause (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,198);
if (RapidSub.canDelegate("activity_pause")) { return anywheresoftware.b4a.table.main.remoteMe.runUserSub(false, "main","activity_pause", _userclosed);}
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 198;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(32);
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
public static RemoteObject  _activity_resume() throws Exception{
try {
		Debug.PushSubsStack("Activity_Resume (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,194);
if (RapidSub.canDelegate("activity_resume")) { return anywheresoftware.b4a.table.main.remoteMe.runUserSub(false, "main","activity_resume");}
 BA.debugLineNum = 194;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(2);
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
public static RemoteObject  _addrow(RemoteObject _values) throws Exception{
try {
		Debug.PushSubsStack("AddRow (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,135);
if (RapidSub.canDelegate("addrow")) { return anywheresoftware.b4a.table.main.remoteMe.runUserSub(false, "main","addrow", _values);}
RemoteObject _lastrow = RemoteObject.createImmutable(0);
int _i = 0;
RemoteObject _l = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
RemoteObject _rc = RemoteObject.declareNull("anywheresoftware.b4a.table.main._rowcol");
Debug.locals.put("Values", _values);
 BA.debugLineNum = 135;BA.debugLine="Sub AddRow(Values() As String)";
Debug.ShouldStop(64);
 BA.debugLineNum = 136;BA.debugLine="If Values.Length <> NumberOfColumns Then";
Debug.ShouldStop(128);
if (RemoteObject.solveBoolean("!",_values.getField(true,"length"),BA.numberCast(double.class, main._numberofcolumns))) { 
 BA.debugLineNum = 137;BA.debugLine="Log(\"Wrong number of values.\")";
Debug.ShouldStop(256);
main.mostCurrent.__c.runVoidMethod ("LogImpl","8589826",RemoteObject.createImmutable("Wrong number of values."),0);
 BA.debugLineNum = 138;BA.debugLine="Return";
Debug.ShouldStop(512);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 140;BA.debugLine="Dim lastRow As Int";
Debug.ShouldStop(2048);
_lastrow = RemoteObject.createImmutable(0);Debug.locals.put("lastRow", _lastrow);
 BA.debugLineNum = 141;BA.debugLine="lastRow = NumberOfRows";
Debug.ShouldStop(4096);
_lastrow = _numberofrows();Debug.locals.put("lastRow", _lastrow);
 BA.debugLineNum = 142;BA.debugLine="For i = 0 To NumberOfColumns - 1";
Debug.ShouldStop(8192);
{
final int step7 = 1;
final int limit7 = RemoteObject.solve(new RemoteObject[] {main._numberofcolumns,RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
for (;(step7 > 0 && _i <= limit7) || (step7 < 0 && _i >= limit7) ;_i = ((int)(0 + _i + step7))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 143;BA.debugLine="Dim l As Label";
Debug.ShouldStop(16384);
_l = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("l", _l);
 BA.debugLineNum = 144;BA.debugLine="l.Initialize(\"cell\")";
Debug.ShouldStop(32768);
_l.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("cell")));
 BA.debugLineNum = 145;BA.debugLine="l.Text = Values(i)";
Debug.ShouldStop(65536);
_l.runMethod(true,"setText",BA.ObjectToCharSequence(_values.getArrayElement(true,BA.numberCast(int.class, _i))));
 BA.debugLineNum = 146;BA.debugLine="l.Gravity = Alignment";
Debug.ShouldStop(131072);
_l.runMethod(true,"setGravity",main._alignment);
 BA.debugLineNum = 147;BA.debugLine="l.TextSize = FontSize";
Debug.ShouldStop(262144);
_l.runMethod(true,"setTextSize",main._fontsize);
 BA.debugLineNum = 148;BA.debugLine="l.TextColor = FontColor";
Debug.ShouldStop(524288);
_l.runMethod(true,"setTextColor",main._fontcolor);
 BA.debugLineNum = 149;BA.debugLine="Dim rc As RowCol";
Debug.ShouldStop(1048576);
_rc = RemoteObject.createNew ("anywheresoftware.b4a.table.main._rowcol");Debug.locals.put("rc", _rc);
 BA.debugLineNum = 150;BA.debugLine="rc.Initialize";
Debug.ShouldStop(2097152);
_rc.runVoidMethod ("Initialize");
 BA.debugLineNum = 151;BA.debugLine="rc.Col = i";
Debug.ShouldStop(4194304);
_rc.setField ("Col" /*RemoteObject*/ ,BA.numberCast(int.class, _i));
 BA.debugLineNum = 152;BA.debugLine="rc.Row = lastRow";
Debug.ShouldStop(8388608);
_rc.setField ("Row" /*RemoteObject*/ ,_lastrow);
 BA.debugLineNum = 153;BA.debugLine="l.Tag = rc";
Debug.ShouldStop(16777216);
_l.runMethod(false,"setTag",(_rc));
 BA.debugLineNum = 154;BA.debugLine="Table.AddView(l, ColumnWidth * i, RowHeight * la";
Debug.ShouldStop(33554432);
main.mostCurrent._table.runVoidMethod ("AddView",(Object)((_l.getObject())),(Object)(RemoteObject.solve(new RemoteObject[] {main._columnwidth,RemoteObject.createImmutable(_i)}, "*",0, 1)),(Object)(RemoteObject.solve(new RemoteObject[] {main._rowheight,_lastrow}, "*",0, 1)),(Object)(main._columnwidth),(Object)(main._rowheight));
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 156;BA.debugLine="Table.Height = NumberOfRows * RowHeight";
Debug.ShouldStop(134217728);
main.mostCurrent._table.runMethod(true,"setHeight",RemoteObject.solve(new RemoteObject[] {_numberofrows(),main._rowheight}, "*",0, 1));
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
public static RemoteObject  _cell_click() throws Exception{
try {
		Debug.PushSubsStack("Cell_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,98);
if (RapidSub.canDelegate("cell_click")) { return anywheresoftware.b4a.table.main.remoteMe.runUserSub(false, "main","cell_click");}
RemoteObject _rc = RemoteObject.declareNull("anywheresoftware.b4a.table.main._rowcol");
RemoteObject _l = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
 BA.debugLineNum = 98;BA.debugLine="Sub Cell_Click";
Debug.ShouldStop(2);
 BA.debugLineNum = 99;BA.debugLine="Dim rc As RowCol";
Debug.ShouldStop(4);
_rc = RemoteObject.createNew ("anywheresoftware.b4a.table.main._rowcol");Debug.locals.put("rc", _rc);
 BA.debugLineNum = 100;BA.debugLine="Dim l As Label";
Debug.ShouldStop(8);
_l = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("l", _l);
 BA.debugLineNum = 101;BA.debugLine="l = Sender";
Debug.ShouldStop(16);
_l = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.LabelWrapper"), main.mostCurrent.__c.runMethod(false,"Sender",main.mostCurrent.activityBA));Debug.locals.put("l", _l);
 BA.debugLineNum = 102;BA.debugLine="rc = l.Tag";
Debug.ShouldStop(32);
_rc = (_l.runMethod(false,"getTag"));Debug.locals.put("rc", _rc);
 BA.debugLineNum = 103;BA.debugLine="SelectRow(rc.Row)";
Debug.ShouldStop(64);
_selectrow(_rc.getField(true,"Row" /*RemoteObject*/ ));
 BA.debugLineNum = 104;BA.debugLine="Activity.Title = \"Cell clicked: (\" & rc.Row & \",";
Debug.ShouldStop(128);
main.mostCurrent._activity.runMethod(false,"setTitle",BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("Cell clicked: ("),_rc.getField(true,"Row" /*RemoteObject*/ ),RemoteObject.createImmutable(", "),_rc.getField(true,"Col" /*RemoteObject*/ ),RemoteObject.createImmutable(")"))));
 BA.debugLineNum = 105;BA.debugLine="End Sub";
Debug.ShouldStop(256);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _clearall() throws Exception{
try {
		Debug.PushSubsStack("ClearAll (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,187);
if (RapidSub.canDelegate("clearall")) { return anywheresoftware.b4a.table.main.remoteMe.runUserSub(false, "main","clearall");}
int _i = 0;
 BA.debugLineNum = 187;BA.debugLine="Sub ClearAll";
Debug.ShouldStop(67108864);
 BA.debugLineNum = 188;BA.debugLine="For i = Table.NumberOfViews -1 To 0 Step -1";
Debug.ShouldStop(134217728);
{
final int step1 = -1;
final int limit1 = 0;
_i = RemoteObject.solve(new RemoteObject[] {main.mostCurrent._table.runMethod(true,"getNumberOfViews"),RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue() ;
for (;(step1 > 0 && _i <= limit1) || (step1 < 0 && _i >= limit1) ;_i = ((int)(0 + _i + step1))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 189;BA.debugLine="Table.RemoveViewAt(i)";
Debug.ShouldStop(268435456);
main.mostCurrent._table.runVoidMethod ("RemoveViewAt",(Object)(BA.numberCast(int.class, _i)));
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 191;BA.debugLine="Table.Height = 0";
Debug.ShouldStop(1073741824);
main.mostCurrent._table.runMethod(true,"setHeight",BA.numberCast(int.class, 0));
 BA.debugLineNum = 192;BA.debugLine="SelectedRow = -1";
Debug.ShouldStop(-2147483648);
main._selectedrow = BA.numberCast(int.class, -(double) (0 + 1));
 BA.debugLineNum = 193;BA.debugLine="End Sub";
Debug.ShouldStop(1);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _getcell(RemoteObject _row,RemoteObject _col) throws Exception{
try {
		Debug.PushSubsStack("GetCell (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,183);
if (RapidSub.canDelegate("getcell")) { return anywheresoftware.b4a.table.main.remoteMe.runUserSub(false, "main","getcell", _row, _col);}
Debug.locals.put("Row", _row);
Debug.locals.put("Col", _col);
 BA.debugLineNum = 183;BA.debugLine="Sub GetCell(Row As Int, Col As Int) As String";
Debug.ShouldStop(4194304);
 BA.debugLineNum = 184;BA.debugLine="Return GetView(Row, Col).Text";
Debug.ShouldStop(8388608);
if (true) return _getview(_row,_col).runMethod(true,"getText");
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
public static RemoteObject  _getview(RemoteObject _row,RemoteObject _col) throws Exception{
try {
		Debug.PushSubsStack("GetView (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,128);
if (RapidSub.canDelegate("getview")) { return anywheresoftware.b4a.table.main.remoteMe.runUserSub(false, "main","getview", _row, _col);}
RemoteObject _l = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
Debug.locals.put("Row", _row);
Debug.locals.put("Col", _col);
 BA.debugLineNum = 128;BA.debugLine="Sub GetView(Row As Int, Col As Int) As Label";
Debug.ShouldStop(-2147483648);
 BA.debugLineNum = 129;BA.debugLine="Dim l As Label";
Debug.ShouldStop(1);
_l = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("l", _l);
 BA.debugLineNum = 130;BA.debugLine="l = Table.GetView(Row * NumberOfColumns + Col)";
Debug.ShouldStop(2);
_l = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.LabelWrapper"), main.mostCurrent._table.runMethod(false,"GetView",(Object)(RemoteObject.solve(new RemoteObject[] {_row,main._numberofcolumns,_col}, "*+",1, 1))).getObject());Debug.locals.put("l", _l);
 BA.debugLineNum = 131;BA.debugLine="Return l";
Debug.ShouldStop(4);
if (true) return _l;
 BA.debugLineNum = 132;BA.debugLine="End Sub";
Debug.ShouldStop(8);
return RemoteObject.createImmutable(null);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _globals() throws Exception{
 //BA.debugLineNum = 15;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 16;BA.debugLine="Dim SV As ScrollView";
main.mostCurrent._sv = RemoteObject.createNew ("anywheresoftware.b4a.objects.ScrollViewWrapper");
 //BA.debugLineNum = 17;BA.debugLine="Dim Header As Panel";
main.mostCurrent._header = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 18;BA.debugLine="Dim Table As Panel";
main.mostCurrent._table = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 19;BA.debugLine="Dim NumberOfColumns, RowHeight, ColumnWidth As In";
main._numberofcolumns = RemoteObject.createImmutable(0);
main._rowheight = RemoteObject.createImmutable(0);
main._columnwidth = RemoteObject.createImmutable(0);
 //BA.debugLineNum = 20;BA.debugLine="Dim HeaderColor, TableColor, FontColor, HeaderFon";
main._headercolor = RemoteObject.createImmutable(0);
main._tablecolor = RemoteObject.createImmutable(0);
main._fontcolor = RemoteObject.createImmutable(0);
main._headerfontcolor = RemoteObject.createImmutable(0);
 //BA.debugLineNum = 21;BA.debugLine="Dim FontSize As Float";
main._fontsize = RemoteObject.createImmutable(0f);
 //BA.debugLineNum = 22;BA.debugLine="Type RowCol (Row As Int, Col As Int)";
;
 //BA.debugLineNum = 23;BA.debugLine="Dim Alignment As Int";
main._alignment = RemoteObject.createImmutable(0);
 //BA.debugLineNum = 24;BA.debugLine="Dim SelectedRow As Int";
main._selectedrow = RemoteObject.createImmutable(0);
 //BA.debugLineNum = 25;BA.debugLine="Dim SelectedRowColor As Int";
main._selectedrowcolor = RemoteObject.createImmutable(0);
 //BA.debugLineNum = 28;BA.debugLine="HeaderColor = Colors.Gray";
main._headercolor = main.mostCurrent.__c.getField(false,"Colors").getField(true,"Gray");
 //BA.debugLineNum = 29;BA.debugLine="NumberOfColumns = 4 'will be overwritten when loa";
main._numberofcolumns = BA.numberCast(int.class, 4);
 //BA.debugLineNum = 30;BA.debugLine="RowHeight = 30dip";
main._rowheight = main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 30)));
 //BA.debugLineNum = 31;BA.debugLine="TableColor = Colors.White";
main._tablecolor = main.mostCurrent.__c.getField(false,"Colors").getField(true,"White");
 //BA.debugLineNum = 32;BA.debugLine="FontColor = Colors.Black";
main._fontcolor = main.mostCurrent.__c.getField(false,"Colors").getField(true,"Black");
 //BA.debugLineNum = 33;BA.debugLine="HeaderFontColor = Colors.White";
main._headerfontcolor = main.mostCurrent.__c.getField(false,"Colors").getField(true,"White");
 //BA.debugLineNum = 34;BA.debugLine="FontSize = 14";
main._fontsize = BA.numberCast(float.class, 14);
 //BA.debugLineNum = 35;BA.debugLine="Alignment = Gravity.CENTER 'change to Gravity.LEF";
main._alignment = main.mostCurrent.__c.getField(false,"Gravity").getField(true,"CENTER");
 //BA.debugLineNum = 36;BA.debugLine="SelectedRowColor = Colors.Blue";
main._selectedrowcolor = main.mostCurrent.__c.getField(false,"Colors").getField(true,"Blue");
 //BA.debugLineNum = 37;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _header_click() throws Exception{
try {
		Debug.PushSubsStack("Header_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,107);
if (RapidSub.canDelegate("header_click")) { return anywheresoftware.b4a.table.main.remoteMe.runUserSub(false, "main","header_click");}
RemoteObject _l = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
RemoteObject _col = RemoteObject.createImmutable(0);
 BA.debugLineNum = 107;BA.debugLine="Sub Header_Click";
Debug.ShouldStop(1024);
 BA.debugLineNum = 108;BA.debugLine="Dim l As Label";
Debug.ShouldStop(2048);
_l = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("l", _l);
 BA.debugLineNum = 109;BA.debugLine="Dim col As Int";
Debug.ShouldStop(4096);
_col = RemoteObject.createImmutable(0);Debug.locals.put("col", _col);
 BA.debugLineNum = 110;BA.debugLine="l = Sender";
Debug.ShouldStop(8192);
_l = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.LabelWrapper"), main.mostCurrent.__c.runMethod(false,"Sender",main.mostCurrent.activityBA));Debug.locals.put("l", _l);
 BA.debugLineNum = 111;BA.debugLine="col = l.Tag";
Debug.ShouldStop(16384);
_col = BA.numberCast(int.class, _l.runMethod(false,"getTag"));Debug.locals.put("col", _col);
 BA.debugLineNum = 112;BA.debugLine="Activity.Title = \"Header clicked: \" & col";
Debug.ShouldStop(32768);
main.mostCurrent._activity.runMethod(false,"setTitle",BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("Header clicked: "),_col)));
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
public static RemoteObject  _loadtablefromcsv(RemoteObject _dir,RemoteObject _filename,RemoteObject _headersexist) throws Exception{
try {
		Debug.PushSubsStack("LoadTableFromCSV (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,50);
if (RapidSub.canDelegate("loadtablefromcsv")) { return anywheresoftware.b4a.table.main.remoteMe.runUserSub(false, "main","loadtablefromcsv", _dir, _filename, _headersexist);}
RemoteObject _list1 = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _h = null;
RemoteObject _headers = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
int _i = 0;
RemoteObject _firstrow = null;
RemoteObject _row = null;
Debug.locals.put("Dir", _dir);
Debug.locals.put("Filename", _filename);
Debug.locals.put("HeadersExist", _headersexist);
 BA.debugLineNum = 50;BA.debugLine="Sub LoadTableFromCSV(Dir As String, Filename As St";
Debug.ShouldStop(131072);
 BA.debugLineNum = 51;BA.debugLine="ClearAll";
Debug.ShouldStop(262144);
_clearall();
 BA.debugLineNum = 52;BA.debugLine="Dim List1 As List";
Debug.ShouldStop(524288);
_list1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");Debug.locals.put("List1", _list1);
 BA.debugLineNum = 53;BA.debugLine="Dim h() As String";
Debug.ShouldStop(1048576);
_h = RemoteObject.createNewArray ("String", new int[] {0}, new Object[]{});Debug.locals.put("h", _h);
 BA.debugLineNum = 54;BA.debugLine="If HeadersExist Then";
Debug.ShouldStop(2097152);
if (_headersexist.<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 55;BA.debugLine="Dim headers As List";
Debug.ShouldStop(4194304);
_headers = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");Debug.locals.put("headers", _headers);
 BA.debugLineNum = 56;BA.debugLine="List1 = StringUtils1.LoadCSV2(Dir, Filename, \",\"";
Debug.ShouldStop(8388608);
_list1 = main._stringutils1.runMethod(false,"LoadCSV2",(Object)(_dir),(Object)(_filename),(Object)(BA.ObjectToChar(",")),(Object)(_headers));Debug.locals.put("List1", _list1);
 BA.debugLineNum = 57;BA.debugLine="Dim h(headers.Size) As String";
Debug.ShouldStop(16777216);
_h = RemoteObject.createNewArray ("String", new int[] {_headers.runMethod(true,"getSize").<Integer>get().intValue()}, new Object[]{});Debug.locals.put("h", _h);
 BA.debugLineNum = 58;BA.debugLine="For i = 0 To headers.Size - 1";
Debug.ShouldStop(33554432);
{
final int step8 = 1;
final int limit8 = RemoteObject.solve(new RemoteObject[] {_headers.runMethod(true,"getSize"),RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
for (;(step8 > 0 && _i <= limit8) || (step8 < 0 && _i >= limit8) ;_i = ((int)(0 + _i + step8))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 59;BA.debugLine="h(i) = headers.Get(i)";
Debug.ShouldStop(67108864);
_h.setArrayElement (BA.ObjectToString(_headers.runMethod(false,"Get",(Object)(BA.numberCast(int.class, _i)))),BA.numberCast(int.class, _i));
 }
}Debug.locals.put("i", _i);
;
 }else {
 BA.debugLineNum = 62;BA.debugLine="List1 = StringUtils1.LoadCSV(Dir, Filename, \",\")";
Debug.ShouldStop(536870912);
_list1 = main._stringutils1.runMethod(false,"LoadCSV",(Object)(_dir),(Object)(_filename),(Object)(BA.ObjectToChar(RemoteObject.createImmutable(","))));Debug.locals.put("List1", _list1);
 BA.debugLineNum = 63;BA.debugLine="Dim firstRow() As String";
Debug.ShouldStop(1073741824);
_firstrow = RemoteObject.createNewArray ("String", new int[] {0}, new Object[]{});Debug.locals.put("firstRow", _firstrow);
 BA.debugLineNum = 64;BA.debugLine="firstRow = List1.Get(0)";
Debug.ShouldStop(-2147483648);
_firstrow = (_list1.runMethod(false,"Get",(Object)(BA.numberCast(int.class, 0))));Debug.locals.put("firstRow", _firstrow);
 BA.debugLineNum = 65;BA.debugLine="Dim h(firstRow.Length)";
Debug.ShouldStop(1);
_h = RemoteObject.createNewArray ("String", new int[] {_firstrow.getField(true,"length").<Integer>get().intValue()}, new Object[]{});Debug.locals.put("h", _h);
 BA.debugLineNum = 66;BA.debugLine="For i = 0 To firstRow.Length - 1";
Debug.ShouldStop(2);
{
final int step16 = 1;
final int limit16 = RemoteObject.solve(new RemoteObject[] {_firstrow.getField(true,"length"),RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
for (;(step16 > 0 && _i <= limit16) || (step16 < 0 && _i >= limit16) ;_i = ((int)(0 + _i + step16))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 67;BA.debugLine="h(i) = \"Col\" & (i + 1)";
Debug.ShouldStop(4);
_h.setArrayElement (RemoteObject.concat(RemoteObject.createImmutable("Col"),(RemoteObject.solve(new RemoteObject[] {RemoteObject.createImmutable(_i),RemoteObject.createImmutable(1)}, "+",1, 1))),BA.numberCast(int.class, _i));
 }
}Debug.locals.put("i", _i);
;
 };
 BA.debugLineNum = 70;BA.debugLine="NumberOfColumns = h.Length";
Debug.ShouldStop(32);
main._numberofcolumns = _h.getField(true,"length");
 BA.debugLineNum = 71;BA.debugLine="ColumnWidth = SV.Width / NumberOfColumns 'update";
Debug.ShouldStop(64);
main._columnwidth = BA.numberCast(int.class, RemoteObject.solve(new RemoteObject[] {main.mostCurrent._sv.runMethod(true,"getWidth"),main._numberofcolumns}, "/",0, 0));
 BA.debugLineNum = 72;BA.debugLine="SetHeader(h)";
Debug.ShouldStop(128);
_setheader(_h);
 BA.debugLineNum = 73;BA.debugLine="For i = 0 To List1.Size - 1";
Debug.ShouldStop(256);
{
final int step23 = 1;
final int limit23 = RemoteObject.solve(new RemoteObject[] {_list1.runMethod(true,"getSize"),RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
for (;(step23 > 0 && _i <= limit23) || (step23 < 0 && _i >= limit23) ;_i = ((int)(0 + _i + step23))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 74;BA.debugLine="Dim row() As String";
Debug.ShouldStop(512);
_row = RemoteObject.createNewArray ("String", new int[] {0}, new Object[]{});Debug.locals.put("row", _row);
 BA.debugLineNum = 75;BA.debugLine="row = List1.Get(i)";
Debug.ShouldStop(1024);
_row = (_list1.runMethod(false,"Get",(Object)(BA.numberCast(int.class, _i))));Debug.locals.put("row", _row);
 BA.debugLineNum = 76;BA.debugLine="AddRow(row)";
Debug.ShouldStop(2048);
_addrow(_row);
 }
}Debug.locals.put("i", _i);
;
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
public static RemoteObject  _numberofrows() throws Exception{
try {
		Debug.PushSubsStack("NumberOfRows (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,175);
if (RapidSub.canDelegate("numberofrows")) { return anywheresoftware.b4a.table.main.remoteMe.runUserSub(false, "main","numberofrows");}
 BA.debugLineNum = 175;BA.debugLine="Sub NumberOfRows As Int";
Debug.ShouldStop(16384);
 BA.debugLineNum = 176;BA.debugLine="Return Table.NumberOfViews / NumberOfColumns";
Debug.ShouldStop(32768);
if (true) return BA.numberCast(int.class, RemoteObject.solve(new RemoteObject[] {main.mostCurrent._table.runMethod(true,"getNumberOfViews"),main._numberofcolumns}, "/",0, 0));
 BA.debugLineNum = 177;BA.debugLine="End Sub";
Debug.ShouldStop(65536);
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
main.myClass = BA.getDeviceClass ("anywheresoftware.b4a.table.main");
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}public static RemoteObject  _process_globals() throws Exception{
 //BA.debugLineNum = 11;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 12;BA.debugLine="Dim StringUtils1 As StringUtils";
main._stringutils1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.StringUtils");
 //BA.debugLineNum = 13;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _savetabletocsv(RemoteObject _dir,RemoteObject _filename) throws Exception{
try {
		Debug.PushSubsStack("SaveTableToCSV (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,79);
if (RapidSub.canDelegate("savetabletocsv")) { return anywheresoftware.b4a.table.main.remoteMe.runUserSub(false, "main","savetabletocsv", _dir, _filename);}
RemoteObject _headers = null;
int _i = 0;
RemoteObject _l = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
RemoteObject _list1 = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _row = null;
int _c = 0;
Debug.locals.put("Dir", _dir);
Debug.locals.put("Filename", _filename);
 BA.debugLineNum = 79;BA.debugLine="Sub SaveTableToCSV(Dir As String, Filename As Stri";
Debug.ShouldStop(16384);
 BA.debugLineNum = 80;BA.debugLine="Dim headers(NumberOfColumns) As String";
Debug.ShouldStop(32768);
_headers = RemoteObject.createNewArray ("String", new int[] {main._numberofcolumns.<Integer>get().intValue()}, new Object[]{});Debug.locals.put("headers", _headers);
 BA.debugLineNum = 81;BA.debugLine="For i = 0 To headers.Length - 1";
Debug.ShouldStop(65536);
{
final int step2 = 1;
final int limit2 = RemoteObject.solve(new RemoteObject[] {_headers.getField(true,"length"),RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
for (;(step2 > 0 && _i <= limit2) || (step2 < 0 && _i >= limit2) ;_i = ((int)(0 + _i + step2))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 82;BA.debugLine="Dim l As Label";
Debug.ShouldStop(131072);
_l = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("l", _l);
 BA.debugLineNum = 83;BA.debugLine="l = Header.GetView(i)";
Debug.ShouldStop(262144);
_l = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.LabelWrapper"), main.mostCurrent._header.runMethod(false,"GetView",(Object)(BA.numberCast(int.class, _i))).getObject());Debug.locals.put("l", _l);
 BA.debugLineNum = 84;BA.debugLine="headers(i) = l.Text";
Debug.ShouldStop(524288);
_headers.setArrayElement (_l.runMethod(true,"getText"),BA.numberCast(int.class, _i));
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 86;BA.debugLine="Dim list1 As List";
Debug.ShouldStop(2097152);
_list1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");Debug.locals.put("list1", _list1);
 BA.debugLineNum = 87;BA.debugLine="list1.Initialize";
Debug.ShouldStop(4194304);
_list1.runVoidMethod ("Initialize");
 BA.debugLineNum = 88;BA.debugLine="For i = 0 To NumberOfRows - 1";
Debug.ShouldStop(8388608);
{
final int step9 = 1;
final int limit9 = RemoteObject.solve(new RemoteObject[] {_numberofrows(),RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
for (;(step9 > 0 && _i <= limit9) || (step9 < 0 && _i >= limit9) ;_i = ((int)(0 + _i + step9))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 89;BA.debugLine="Dim row(NumberOfColumns) As String";
Debug.ShouldStop(16777216);
_row = RemoteObject.createNewArray ("String", new int[] {main._numberofcolumns.<Integer>get().intValue()}, new Object[]{});Debug.locals.put("row", _row);
 BA.debugLineNum = 90;BA.debugLine="For c = 0 To NumberOfColumns - 1";
Debug.ShouldStop(33554432);
{
final int step11 = 1;
final int limit11 = RemoteObject.solve(new RemoteObject[] {main._numberofcolumns,RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_c = 0 ;
for (;(step11 > 0 && _c <= limit11) || (step11 < 0 && _c >= limit11) ;_c = ((int)(0 + _c + step11))  ) {
Debug.locals.put("c", _c);
 BA.debugLineNum = 91;BA.debugLine="row(c) = GetCell(i, c)";
Debug.ShouldStop(67108864);
_row.setArrayElement (_getcell(BA.numberCast(int.class, _i),BA.numberCast(int.class, _c)),BA.numberCast(int.class, _c));
 }
}Debug.locals.put("c", _c);
;
 BA.debugLineNum = 93;BA.debugLine="list1.Add(row)";
Debug.ShouldStop(268435456);
_list1.runVoidMethod ("Add",(Object)((_row)));
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 95;BA.debugLine="StringUtils1.SaveCSV2(Dir, Filename, \",\", list1,";
Debug.ShouldStop(1073741824);
main._stringutils1.runVoidMethod ("SaveCSV2",(Object)(_dir),(Object)(_filename),(Object)(BA.ObjectToChar(",")),(Object)(_list1),(Object)(main.mostCurrent.__c.runMethod(false, "ArrayToList", (Object)(_headers))));
 BA.debugLineNum = 96;BA.debugLine="End Sub";
Debug.ShouldStop(-2147483648);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _selectrow(RemoteObject _row) throws Exception{
try {
		Debug.PushSubsStack("SelectRow (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,115);
if (RapidSub.canDelegate("selectrow")) { return anywheresoftware.b4a.table.main.remoteMe.runUserSub(false, "main","selectrow", _row);}
int _col = 0;
Debug.locals.put("Row", _row);
 BA.debugLineNum = 115;BA.debugLine="Sub SelectRow(Row As Int)";
Debug.ShouldStop(262144);
 BA.debugLineNum = 117;BA.debugLine="If SelectedRow > -1 Then";
Debug.ShouldStop(1048576);
if (RemoteObject.solveBoolean(">",main._selectedrow,BA.numberCast(double.class, -(double) (0 + 1)))) { 
 BA.debugLineNum = 118;BA.debugLine="For col = 0 To NumberOfColumns - 1";
Debug.ShouldStop(2097152);
{
final int step2 = 1;
final int limit2 = RemoteObject.solve(new RemoteObject[] {main._numberofcolumns,RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_col = 0 ;
for (;(step2 > 0 && _col <= limit2) || (step2 < 0 && _col >= limit2) ;_col = ((int)(0 + _col + step2))  ) {
Debug.locals.put("col", _col);
 BA.debugLineNum = 119;BA.debugLine="GetView(SelectedRow, col).Color = Colors.Transp";
Debug.ShouldStop(4194304);
_getview(main._selectedrow,BA.numberCast(int.class, _col)).runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Transparent"));
 }
}Debug.locals.put("col", _col);
;
 };
 BA.debugLineNum = 122;BA.debugLine="SelectedRow = Row";
Debug.ShouldStop(33554432);
main._selectedrow = _row;
 BA.debugLineNum = 123;BA.debugLine="For col = 0 To NumberOfColumns - 1";
Debug.ShouldStop(67108864);
{
final int step7 = 1;
final int limit7 = RemoteObject.solve(new RemoteObject[] {main._numberofcolumns,RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_col = 0 ;
for (;(step7 > 0 && _col <= limit7) || (step7 < 0 && _col >= limit7) ;_col = ((int)(0 + _col + step7))  ) {
Debug.locals.put("col", _col);
 BA.debugLineNum = 124;BA.debugLine="GetView(Row, col).Color = SelectedRowColor";
Debug.ShouldStop(134217728);
_getview(_row,BA.numberCast(int.class, _col)).runVoidMethod ("setColor",main._selectedrowcolor);
 }
}Debug.locals.put("col", _col);
;
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
public static RemoteObject  _setcell(RemoteObject _row,RemoteObject _col,RemoteObject _value) throws Exception{
try {
		Debug.PushSubsStack("SetCell (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,179);
if (RapidSub.canDelegate("setcell")) { return anywheresoftware.b4a.table.main.remoteMe.runUserSub(false, "main","setcell", _row, _col, _value);}
Debug.locals.put("Row", _row);
Debug.locals.put("Col", _col);
Debug.locals.put("Value", _value);
 BA.debugLineNum = 179;BA.debugLine="Sub SetCell(Row As Int, Col As Int, Value As Strin";
Debug.ShouldStop(262144);
 BA.debugLineNum = 180;BA.debugLine="GetView(Row, Col).Text = Value";
Debug.ShouldStop(524288);
_getview(_row,_col).runMethod(true,"setText",BA.ObjectToCharSequence(_value));
 BA.debugLineNum = 181;BA.debugLine="End Sub";
Debug.ShouldStop(1048576);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _setheader(RemoteObject _values) throws Exception{
try {
		Debug.PushSubsStack("SetHeader (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,159);
if (RapidSub.canDelegate("setheader")) { return anywheresoftware.b4a.table.main.remoteMe.runUserSub(false, "main","setheader", _values);}
int _i = 0;
RemoteObject _l = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
Debug.locals.put("Values", _values);
 BA.debugLineNum = 159;BA.debugLine="Sub SetHeader(Values() As String)";
Debug.ShouldStop(1073741824);
 BA.debugLineNum = 160;BA.debugLine="If Header.IsInitialized Then Return 'should only";
Debug.ShouldStop(-2147483648);
if (main.mostCurrent._header.runMethod(true,"IsInitialized").<Boolean>get().booleanValue()) { 
if (true) return RemoteObject.createImmutable("");};
 BA.debugLineNum = 161;BA.debugLine="Header.Initialize(\"\")";
Debug.ShouldStop(1);
main.mostCurrent._header.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 162;BA.debugLine="For i = 0 To NumberOfColumns - 1";
Debug.ShouldStop(2);
{
final int step3 = 1;
final int limit3 = RemoteObject.solve(new RemoteObject[] {main._numberofcolumns,RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
for (;(step3 > 0 && _i <= limit3) || (step3 < 0 && _i >= limit3) ;_i = ((int)(0 + _i + step3))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 163;BA.debugLine="Dim l As Label";
Debug.ShouldStop(4);
_l = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("l", _l);
 BA.debugLineNum = 164;BA.debugLine="l.Initialize(\"header\")";
Debug.ShouldStop(8);
_l.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("header")));
 BA.debugLineNum = 165;BA.debugLine="l.Text = Values(i)";
Debug.ShouldStop(16);
_l.runMethod(true,"setText",BA.ObjectToCharSequence(_values.getArrayElement(true,BA.numberCast(int.class, _i))));
 BA.debugLineNum = 166;BA.debugLine="l.Gravity = Gravity.CENTER";
Debug.ShouldStop(32);
_l.runMethod(true,"setGravity",main.mostCurrent.__c.getField(false,"Gravity").getField(true,"CENTER"));
 BA.debugLineNum = 167;BA.debugLine="l.TextSize = FontSize";
Debug.ShouldStop(64);
_l.runMethod(true,"setTextSize",main._fontsize);
 BA.debugLineNum = 168;BA.debugLine="l.Color = HeaderColor";
Debug.ShouldStop(128);
_l.runVoidMethod ("setColor",main._headercolor);
 BA.debugLineNum = 169;BA.debugLine="l.TextColor = HeaderFontColor";
Debug.ShouldStop(256);
_l.runMethod(true,"setTextColor",main._headerfontcolor);
 BA.debugLineNum = 170;BA.debugLine="l.Tag = i";
Debug.ShouldStop(512);
_l.runMethod(false,"setTag",RemoteObject.createImmutable((_i)));
 BA.debugLineNum = 171;BA.debugLine="Header.AddView(l, ColumnWidth * i, 0, ColumnWidt";
Debug.ShouldStop(1024);
main.mostCurrent._header.runVoidMethod ("AddView",(Object)((_l.getObject())),(Object)(RemoteObject.solve(new RemoteObject[] {main._columnwidth,RemoteObject.createImmutable(_i)}, "*",0, 1)),(Object)(BA.numberCast(int.class, 0)),(Object)(main._columnwidth),(Object)(main._rowheight));
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 173;BA.debugLine="Activity.AddView(Header, SV.Left, SV.Top - RowHei";
Debug.ShouldStop(4096);
main.mostCurrent._activity.runVoidMethod ("AddView",(Object)((main.mostCurrent._header.getObject())),(Object)(main.mostCurrent._sv.runMethod(true,"getLeft")),(Object)(RemoteObject.solve(new RemoteObject[] {main.mostCurrent._sv.runMethod(true,"getTop"),main._rowheight}, "-",1, 1)),(Object)(main.mostCurrent._sv.runMethod(true,"getWidth")),(Object)(main._rowheight));
 BA.debugLineNum = 174;BA.debugLine="End Sub";
Debug.ShouldStop(8192);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}