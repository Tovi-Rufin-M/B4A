package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class main_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,23);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_create", _firsttime);}
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 23;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(4194304);
 BA.debugLineNum = 25;BA.debugLine="pnlBackground.Initialize(\"\")";
Debug.ShouldStop(16777216);
main.mostCurrent._pnlbackground.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 26;BA.debugLine="pnlBackground.Color = Colors.RGB(173, 226, 244)";
Debug.ShouldStop(33554432);
main.mostCurrent._pnlbackground.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").runMethod(true,"RGB",(Object)(BA.numberCast(int.class, 173)),(Object)(BA.numberCast(int.class, 226)),(Object)(BA.numberCast(int.class, 244))));
 BA.debugLineNum = 27;BA.debugLine="Activity.AddView(pnlBackground, 0, 0, 100%x, 100%";
Debug.ShouldStop(67108864);
main.mostCurrent._activity.runVoidMethod ("AddView",(Object)((main.mostCurrent._pnlbackground.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(main.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 100)),main.mostCurrent.activityBA)),(Object)(main.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 100)),main.mostCurrent.activityBA)));
 BA.debugLineNum = 29;BA.debugLine="BuildHeader";
Debug.ShouldStop(268435456);
_buildheader();
 BA.debugLineNum = 30;BA.debugLine="BuildGrid";
Debug.ShouldStop(536870912);
_buildgrid();
 BA.debugLineNum = 31;BA.debugLine="BuildBottomNav";
Debug.ShouldStop(1073741824);
_buildbottomnav();
 BA.debugLineNum = 32;BA.debugLine="End Sub";
Debug.ShouldStop(-2147483648);
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
		Debug.PushSubsStack("Activity_Pause (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,139);
if (RapidSub.canDelegate("activity_pause")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_pause", _userclosed);}
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 139;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(1024);
 BA.debugLineNum = 140;BA.debugLine="End Sub";
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
		Debug.PushSubsStack("Activity_Resume (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,136);
if (RapidSub.canDelegate("activity_resume")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_resume");}
 BA.debugLineNum = 136;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(128);
 BA.debugLineNum = 137;BA.debugLine="End Sub";
Debug.ShouldStop(256);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _buildbottomnav() throws Exception{
try {
		Debug.PushSubsStack("BuildBottomNav (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,89);
if (RapidSub.canDelegate("buildbottomnav")) { return b4a.example.main.remoteMe.runUserSub(false, "main","buildbottomnav");}
RemoteObject _pnlnav = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
RemoteObject _topborder = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
 BA.debugLineNum = 89;BA.debugLine="Sub BuildBottomNav";
Debug.ShouldStop(16777216);
 BA.debugLineNum = 90;BA.debugLine="Dim pnlNav As Panel";
Debug.ShouldStop(33554432);
_pnlnav = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");Debug.locals.put("pnlNav", _pnlnav);
 BA.debugLineNum = 91;BA.debugLine="pnlNav.Initialize(\"\")";
Debug.ShouldStop(67108864);
_pnlnav.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 92;BA.debugLine="pnlNav.Color = Colors.RGB(51, 102, 153) ' Dark bl";
Debug.ShouldStop(134217728);
_pnlnav.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").runMethod(true,"RGB",(Object)(BA.numberCast(int.class, 51)),(Object)(BA.numberCast(int.class, 102)),(Object)(BA.numberCast(int.class, 153))));
 BA.debugLineNum = 93;BA.debugLine="pnlBackground.AddView(pnlNav, 0, 100%y - 70dip, 1";
Debug.ShouldStop(268435456);
main.mostCurrent._pnlbackground.runVoidMethod ("AddView",(Object)((_pnlnav.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(RemoteObject.solve(new RemoteObject[] {main.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 100)),main.mostCurrent.activityBA),main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 70)))}, "-",1, 1)),(Object)(main.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 100)),main.mostCurrent.activityBA)),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 70)))));
 BA.debugLineNum = 96;BA.debugLine="Dim topBorder As Panel";
Debug.ShouldStop(-2147483648);
_topborder = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");Debug.locals.put("topBorder", _topborder);
 BA.debugLineNum = 97;BA.debugLine="topBorder.Initialize(\"\")";
Debug.ShouldStop(1);
_topborder.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 98;BA.debugLine="topBorder.Color = Colors.Black";
Debug.ShouldStop(2);
_topborder.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 BA.debugLineNum = 99;BA.debugLine="pnlBackground.AddView(topBorder, 0, pnlNav.Top -";
Debug.ShouldStop(4);
main.mostCurrent._pnlbackground.runVoidMethod ("AddView",(Object)((_topborder.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(RemoteObject.solve(new RemoteObject[] {_pnlnav.runMethod(true,"getTop"),main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 3)))}, "-",1, 1)),(Object)(main.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 100)),main.mostCurrent.activityBA)),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 3)))));
 BA.debugLineNum = 100;BA.debugLine="End Sub";
Debug.ShouldStop(8);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _buildgrid() throws Exception{
try {
		Debug.PushSubsStack("BuildGrid (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,68);
if (RapidSub.canDelegate("buildgrid")) { return b4a.example.main.remoteMe.runUserSub(false, "main","buildgrid");}
RemoteObject _starty = RemoteObject.createImmutable(0);
RemoteObject _btnwidth = RemoteObject.createImmutable(0);
RemoteObject _btnheight = RemoteObject.createImmutable(0);
RemoteObject _spacing = RemoteObject.createImmutable(0);
RemoteObject _leftcol = RemoteObject.createImmutable(0);
RemoteObject _rightcol = RemoteObject.createImmutable(0);
 BA.debugLineNum = 68;BA.debugLine="Sub BuildGrid";
Debug.ShouldStop(8);
 BA.debugLineNum = 69;BA.debugLine="Dim startY As Int = 230dip";
Debug.ShouldStop(16);
_starty = main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 230)));Debug.locals.put("startY", _starty);Debug.locals.put("startY", _starty);
 BA.debugLineNum = 70;BA.debugLine="Dim btnWidth As Int = 42%x";
Debug.ShouldStop(32);
_btnwidth = main.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 42)),main.mostCurrent.activityBA);Debug.locals.put("btnWidth", _btnwidth);Debug.locals.put("btnWidth", _btnwidth);
 BA.debugLineNum = 71;BA.debugLine="Dim btnHeight As Int = 80dip";
Debug.ShouldStop(64);
_btnheight = main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 80)));Debug.locals.put("btnHeight", _btnheight);Debug.locals.put("btnHeight", _btnheight);
 BA.debugLineNum = 72;BA.debugLine="Dim spacing As Int = 4%x";
Debug.ShouldStop(128);
_spacing = main.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 4)),main.mostCurrent.activityBA);Debug.locals.put("spacing", _spacing);Debug.locals.put("spacing", _spacing);
 BA.debugLineNum = 73;BA.debugLine="Dim leftCol As Int = 6%x";
Debug.ShouldStop(256);
_leftcol = main.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 6)),main.mostCurrent.activityBA);Debug.locals.put("leftCol", _leftcol);Debug.locals.put("leftCol", _leftcol);
 BA.debugLineNum = 74;BA.debugLine="Dim rightCol As Int = leftCol + btnWidth + spacin";
Debug.ShouldStop(512);
_rightcol = RemoteObject.solve(new RemoteObject[] {_leftcol,_btnwidth,_spacing}, "++",2, 1);Debug.locals.put("rightCol", _rightcol);Debug.locals.put("rightCol", _rightcol);
 BA.debugLineNum = 77;BA.debugLine="CreatePixelButton(\"My Grades\", Colors.RGB(102, 20";
Debug.ShouldStop(4096);
_createpixelbutton(BA.ObjectToString("My Grades"),main.mostCurrent.__c.getField(false,"Colors").runMethod(true,"RGB",(Object)(BA.numberCast(int.class, 102)),(Object)(BA.numberCast(int.class, 204)),(Object)(BA.numberCast(int.class, 255))),_leftcol,_starty,_btnwidth,_btnheight);
 BA.debugLineNum = 78;BA.debugLine="CreatePixelButton(\"Schedule\", Colors.RGB(153, 204";
Debug.ShouldStop(8192);
_createpixelbutton(BA.ObjectToString("Schedule"),main.mostCurrent.__c.getField(false,"Colors").runMethod(true,"RGB",(Object)(BA.numberCast(int.class, 153)),(Object)(BA.numberCast(int.class, 204)),(Object)(BA.numberCast(int.class, 102))),_rightcol,_starty,_btnwidth,_btnheight);
 BA.debugLineNum = 81;BA.debugLine="CreatePixelButton(\"Assignments\", Colors.RGB(255,";
Debug.ShouldStop(65536);
_createpixelbutton(BA.ObjectToString("Assignments"),main.mostCurrent.__c.getField(false,"Colors").runMethod(true,"RGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 204)),(Object)(BA.numberCast(int.class, 102))),_leftcol,RemoteObject.solve(new RemoteObject[] {_starty,_btnheight,main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 15)))}, "++",2, 1),_btnwidth,_btnheight);
 BA.debugLineNum = 82;BA.debugLine="CreatePixelButton(\"Attendance\", Colors.RGB(153, 2";
Debug.ShouldStop(131072);
_createpixelbutton(BA.ObjectToString("Attendance"),main.mostCurrent.__c.getField(false,"Colors").runMethod(true,"RGB",(Object)(BA.numberCast(int.class, 153)),(Object)(BA.numberCast(int.class, 204)),(Object)(BA.numberCast(int.class, 102))),_rightcol,RemoteObject.solve(new RemoteObject[] {_starty,_btnheight,main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 15)))}, "++",2, 1),_btnwidth,_btnheight);
 BA.debugLineNum = 85;BA.debugLine="CreatePixelButton(\"Profile\", Colors.RGB(255, 204,";
Debug.ShouldStop(1048576);
_createpixelbutton(BA.ObjectToString("Profile"),main.mostCurrent.__c.getField(false,"Colors").runMethod(true,"RGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 204)),(Object)(BA.numberCast(int.class, 102))),_leftcol,RemoteObject.solve(new RemoteObject[] {_starty,(RemoteObject.solve(new RemoteObject[] {_btnheight,RemoteObject.createImmutable(2)}, "*",0, 1)),main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 30)))}, "++",2, 1),_btnwidth,_btnheight);
 BA.debugLineNum = 86;BA.debugLine="CreatePixelButton(\"Library\", Colors.RGB(255, 128,";
Debug.ShouldStop(2097152);
_createpixelbutton(BA.ObjectToString("Library"),main.mostCurrent.__c.getField(false,"Colors").runMethod(true,"RGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 128)),(Object)(BA.numberCast(int.class, 64))),_rightcol,RemoteObject.solve(new RemoteObject[] {_starty,(RemoteObject.solve(new RemoteObject[] {_btnheight,RemoteObject.createImmutable(2)}, "*",0, 1)),main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 30)))}, "++",2, 1),_btnwidth,_btnheight);
 BA.debugLineNum = 87;BA.debugLine="End Sub";
Debug.ShouldStop(4194304);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _buildheader() throws Exception{
try {
		Debug.PushSubsStack("BuildHeader (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,34);
if (RapidSub.canDelegate("buildheader")) { return b4a.example.main.remoteMe.runUserSub(false, "main","buildheader");}
RemoteObject _pnlheader = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
RemoteObject _pnlheaderborder = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
RemoteObject _lbltitle = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
RemoteObject _lblwelcome = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
 BA.debugLineNum = 34;BA.debugLine="Sub BuildHeader";
Debug.ShouldStop(2);
 BA.debugLineNum = 36;BA.debugLine="Dim pnlHeader As Panel";
Debug.ShouldStop(8);
_pnlheader = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");Debug.locals.put("pnlHeader", _pnlheader);
 BA.debugLineNum = 37;BA.debugLine="pnlHeader.Initialize(\"\")";
Debug.ShouldStop(16);
_pnlheader.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 38;BA.debugLine="pnlHeader.Color = Colors.RGB(255, 204, 51) ' Yell";
Debug.ShouldStop(32);
_pnlheader.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").runMethod(true,"RGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 204)),(Object)(BA.numberCast(int.class, 51))));
 BA.debugLineNum = 39;BA.debugLine="pnlBackground.AddView(pnlHeader, 5%x, 5%y, 90%x,";
Debug.ShouldStop(64);
main.mostCurrent._pnlbackground.runVoidMethod ("AddView",(Object)((_pnlheader.getObject())),(Object)(main.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 5)),main.mostCurrent.activityBA)),(Object)(main.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 5)),main.mostCurrent.activityBA)),(Object)(main.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 90)),main.mostCurrent.activityBA)),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 80)))));
 BA.debugLineNum = 42;BA.debugLine="Dim pnlHeaderBorder As Panel";
Debug.ShouldStop(512);
_pnlheaderborder = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");Debug.locals.put("pnlHeaderBorder", _pnlheaderborder);
 BA.debugLineNum = 43;BA.debugLine="pnlHeaderBorder.Initialize(\"\")";
Debug.ShouldStop(1024);
_pnlheaderborder.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 44;BA.debugLine="pnlHeaderBorder.Color = Colors.Black";
Debug.ShouldStop(2048);
_pnlheaderborder.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 BA.debugLineNum = 45;BA.debugLine="pnlBackground.AddView(pnlHeaderBorder, pnlHeader.";
Debug.ShouldStop(4096);
main.mostCurrent._pnlbackground.runVoidMethod ("AddView",(Object)((_pnlheaderborder.getObject())),(Object)(RemoteObject.solve(new RemoteObject[] {_pnlheader.runMethod(true,"getLeft"),main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 2)))}, "-",1, 1)),(Object)(RemoteObject.solve(new RemoteObject[] {_pnlheader.runMethod(true,"getTop"),main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 2)))}, "-",1, 1)),(Object)(RemoteObject.solve(new RemoteObject[] {_pnlheader.runMethod(true,"getWidth"),main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 4)))}, "+",1, 1)),(Object)(RemoteObject.solve(new RemoteObject[] {_pnlheader.runMethod(true,"getHeight"),main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 4)))}, "+",1, 1)));
 BA.debugLineNum = 46;BA.debugLine="pnlHeaderBorder.SendToBack";
Debug.ShouldStop(8192);
_pnlheaderborder.runVoidMethod ("SendToBack");
 BA.debugLineNum = 49;BA.debugLine="Dim lblTitle As Label";
Debug.ShouldStop(65536);
_lbltitle = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("lblTitle", _lbltitle);
 BA.debugLineNum = 50;BA.debugLine="lblTitle.Initialize(\"\")";
Debug.ShouldStop(131072);
_lbltitle.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 51;BA.debugLine="lblTitle.Text = \"STUDENT HUB\"";
Debug.ShouldStop(262144);
_lbltitle.runMethod(true,"setText",BA.ObjectToCharSequence("STUDENT HUB"));
 BA.debugLineNum = 52;BA.debugLine="lblTitle.Typeface = Typeface.DEFAULT_BOLD ' Using";
Debug.ShouldStop(524288);
_lbltitle.runMethod(false,"setTypeface",main.mostCurrent.__c.getField(false,"Typeface").getField(false,"DEFAULT_BOLD"));
 BA.debugLineNum = 53;BA.debugLine="lblTitle.TextSize = 18";
Debug.ShouldStop(1048576);
_lbltitle.runMethod(true,"setTextSize",BA.numberCast(float.class, 18));
 BA.debugLineNum = 54;BA.debugLine="lblTitle.TextColor = Colors.Black";
Debug.ShouldStop(2097152);
_lbltitle.runMethod(true,"setTextColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 BA.debugLineNum = 55;BA.debugLine="lblTitle.Gravity = Gravity.CENTER_VERTICAL";
Debug.ShouldStop(4194304);
_lbltitle.runMethod(true,"setGravity",main.mostCurrent.__c.getField(false,"Gravity").getField(true,"CENTER_VERTICAL"));
 BA.debugLineNum = 56;BA.debugLine="pnlHeader.AddView(lblTitle, 80dip, 10dip, pnlHead";
Debug.ShouldStop(8388608);
_pnlheader.runVoidMethod ("AddView",(Object)((_lbltitle.getObject())),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 80)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))),(Object)(RemoteObject.solve(new RemoteObject[] {_pnlheader.runMethod(true,"getWidth"),main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 90)))}, "-",1, 1)),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 30)))));
 BA.debugLineNum = 59;BA.debugLine="Dim lblWelcome As Label";
Debug.ShouldStop(67108864);
_lblwelcome = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("lblWelcome", _lblwelcome);
 BA.debugLineNum = 60;BA.debugLine="lblWelcome.Initialize(\"\")";
Debug.ShouldStop(134217728);
_lblwelcome.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 61;BA.debugLine="lblWelcome.Text = \"Welcome, Alex!\" & CRLF & \"SEP";
Debug.ShouldStop(268435456);
_lblwelcome.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("Welcome, Alex!"),main.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("SEP 24, 2024"))));
 BA.debugLineNum = 62;BA.debugLine="lblWelcome.Typeface = Typeface.DEFAULT_BOLD ' Usi";
Debug.ShouldStop(536870912);
_lblwelcome.runMethod(false,"setTypeface",main.mostCurrent.__c.getField(false,"Typeface").getField(false,"DEFAULT_BOLD"));
 BA.debugLineNum = 63;BA.debugLine="lblWelcome.TextSize = 14";
Debug.ShouldStop(1073741824);
_lblwelcome.runMethod(true,"setTextSize",BA.numberCast(float.class, 14));
 BA.debugLineNum = 64;BA.debugLine="lblWelcome.TextColor = Colors.Black";
Debug.ShouldStop(-2147483648);
_lblwelcome.runMethod(true,"setTextColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 BA.debugLineNum = 65;BA.debugLine="pnlBackground.AddView(lblWelcome, 5%x, pnlHeader.";
Debug.ShouldStop(1);
main.mostCurrent._pnlbackground.runVoidMethod ("AddView",(Object)((_lblwelcome.getObject())),(Object)(main.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 5)),main.mostCurrent.activityBA)),(Object)(RemoteObject.solve(new RemoteObject[] {_pnlheader.runMethod(true,"getTop"),_pnlheader.runMethod(true,"getHeight"),main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 20)))}, "++",2, 1)),(Object)(main.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 90)),main.mostCurrent.activityBA)),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 50)))));
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
public static RemoteObject  _createpixelbutton(RemoteObject _text,RemoteObject _innercolor,RemoteObject _left,RemoteObject _top,RemoteObject _width,RemoteObject _height) throws Exception{
try {
		Debug.PushSubsStack("CreatePixelButton (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,102);
if (RapidSub.canDelegate("createpixelbutton")) { return b4a.example.main.remoteMe.runUserSub(false, "main","createpixelbutton", _text, _innercolor, _left, _top, _width, _height);}
RemoteObject _pnlborder = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
RemoteObject _pnlinner = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
RemoteObject _pnlhighlight = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
RemoteObject _lbl = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
Debug.locals.put("Text", _text);
Debug.locals.put("InnerColor", _innercolor);
Debug.locals.put("Left", _left);
Debug.locals.put("Top", _top);
Debug.locals.put("Width", _width);
Debug.locals.put("Height", _height);
 BA.debugLineNum = 102;BA.debugLine="Sub CreatePixelButton(Text As String, InnerColor A";
Debug.ShouldStop(32);
 BA.debugLineNum = 104;BA.debugLine="Dim pnlBorder As Panel";
Debug.ShouldStop(128);
_pnlborder = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");Debug.locals.put("pnlBorder", _pnlborder);
 BA.debugLineNum = 105;BA.debugLine="pnlBorder.Initialize(\"PixelButton\")";
Debug.ShouldStop(256);
_pnlborder.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("PixelButton")));
 BA.debugLineNum = 106;BA.debugLine="pnlBorder.Color = Colors.Black";
Debug.ShouldStop(512);
_pnlborder.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 BA.debugLineNum = 107;BA.debugLine="pnlBackground.AddView(pnlBorder, Left, Top, Width";
Debug.ShouldStop(1024);
main.mostCurrent._pnlbackground.runVoidMethod ("AddView",(Object)((_pnlborder.getObject())),(Object)(_left),(Object)(_top),(Object)(_width),(Object)(_height));
 BA.debugLineNum = 110;BA.debugLine="Dim pnlInner As Panel";
Debug.ShouldStop(8192);
_pnlinner = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");Debug.locals.put("pnlInner", _pnlinner);
 BA.debugLineNum = 111;BA.debugLine="pnlInner.Initialize(\"\")";
Debug.ShouldStop(16384);
_pnlinner.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 112;BA.debugLine="pnlInner.Color = InnerColor";
Debug.ShouldStop(32768);
_pnlinner.runVoidMethod ("setColor",_innercolor);
 BA.debugLineNum = 113;BA.debugLine="pnlBorder.AddView(pnlInner, 3dip, 3dip, Width - 6";
Debug.ShouldStop(65536);
_pnlborder.runVoidMethod ("AddView",(Object)((_pnlinner.getObject())),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 3)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 3)))),(Object)(RemoteObject.solve(new RemoteObject[] {_width,main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 6)))}, "-",1, 1)),(Object)(RemoteObject.solve(new RemoteObject[] {_height,main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 6)))}, "-",1, 1)));
 BA.debugLineNum = 116;BA.debugLine="Dim pnlHighlight As Panel";
Debug.ShouldStop(524288);
_pnlhighlight = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");Debug.locals.put("pnlHighlight", _pnlhighlight);
 BA.debugLineNum = 117;BA.debugLine="pnlHighlight.Initialize(\"\")";
Debug.ShouldStop(1048576);
_pnlhighlight.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 118;BA.debugLine="pnlHighlight.Color = Colors.ARGB(80, 255, 255, 25";
Debug.ShouldStop(2097152);
_pnlhighlight.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 80)),(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 255))));
 BA.debugLineNum = 119;BA.debugLine="pnlInner.AddView(pnlHighlight, 0, 0, pnlInner.Wid";
Debug.ShouldStop(4194304);
_pnlinner.runVoidMethod ("AddView",(Object)((_pnlhighlight.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(_pnlinner.runMethod(true,"getWidth")),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 4)))));
 BA.debugLineNum = 122;BA.debugLine="Dim lbl As Label";
Debug.ShouldStop(33554432);
_lbl = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("lbl", _lbl);
 BA.debugLineNum = 123;BA.debugLine="lbl.Initialize(\"\")";
Debug.ShouldStop(67108864);
_lbl.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 124;BA.debugLine="lbl.Text = Text";
Debug.ShouldStop(134217728);
_lbl.runMethod(true,"setText",BA.ObjectToCharSequence(_text));
 BA.debugLineNum = 125;BA.debugLine="lbl.Typeface = Typeface.DEFAULT_BOLD ' Using defa";
Debug.ShouldStop(268435456);
_lbl.runMethod(false,"setTypeface",main.mostCurrent.__c.getField(false,"Typeface").getField(false,"DEFAULT_BOLD"));
 BA.debugLineNum = 126;BA.debugLine="lbl.TextSize = 14";
Debug.ShouldStop(536870912);
_lbl.runMethod(true,"setTextSize",BA.numberCast(float.class, 14));
 BA.debugLineNum = 127;BA.debugLine="lbl.TextColor = Colors.Black";
Debug.ShouldStop(1073741824);
_lbl.runMethod(true,"setTextColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 BA.debugLineNum = 128;BA.debugLine="lbl.Gravity = Gravity.CENTER";
Debug.ShouldStop(-2147483648);
_lbl.runMethod(true,"setGravity",main.mostCurrent.__c.getField(false,"Gravity").getField(true,"CENTER"));
 BA.debugLineNum = 129;BA.debugLine="pnlInner.AddView(lbl, 0, pnlInner.Height - 30dip,";
Debug.ShouldStop(1);
_pnlinner.runVoidMethod ("AddView",(Object)((_lbl.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(RemoteObject.solve(new RemoteObject[] {_pnlinner.runMethod(true,"getHeight"),main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 30)))}, "-",1, 1)),(Object)(_pnlinner.runMethod(true,"getWidth")),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 30)))));
 BA.debugLineNum = 130;BA.debugLine="End Sub";
Debug.ShouldStop(2);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _globals() throws Exception{
 //BA.debugLineNum = 18;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 20;BA.debugLine="Private pnlBackground As Panel";
main.mostCurrent._pnlbackground = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 21;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _pixelbutton_click() throws Exception{
try {
		Debug.PushSubsStack("PixelButton_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,132);
if (RapidSub.canDelegate("pixelbutton_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","pixelbutton_click");}
 BA.debugLineNum = 132;BA.debugLine="Sub PixelButton_Click";
Debug.ShouldStop(8);
 BA.debugLineNum = 133;BA.debugLine="Log(\"A pure B4A Pixel Button was clicked!\")";
Debug.ShouldStop(16);
main.mostCurrent.__c.runVoidMethod ("LogImpl","0393217",RemoteObject.createImmutable("A pure B4A Pixel Button was clicked!"),0);
 BA.debugLineNum = 134;BA.debugLine="End Sub";
Debug.ShouldStop(32);
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
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}public static RemoteObject  _process_globals() throws Exception{
 //BA.debugLineNum = 14;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 16;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
}