package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class main_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,27);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_create", _firsttime);}
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 27;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(67108864);
 BA.debugLineNum = 28;BA.debugLine="Activity.LoadLayout(\"Layout\")";
Debug.ShouldStop(134217728);
main.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("Layout")),main.mostCurrent.activityBA);
 BA.debugLineNum = 29;BA.debugLine="chathistory.initialize";
Debug.ShouldStop(268435456);
main._chathistory.runVoidMethod ("Initialize");
 BA.debugLineNum = 30;BA.debugLine="End Sub";
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
		Debug.PushSubsStack("Activity_Pause (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,33);
if (RapidSub.canDelegate("activity_pause")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_pause", _userclosed);}
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 33;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(1);
 BA.debugLineNum = 34;BA.debugLine="End Sub";
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
		Debug.PushSubsStack("Activity_Resume (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,31);
if (RapidSub.canDelegate("activity_resume")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_resume");}
 BA.debugLineNum = 31;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(1073741824);
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
public static RemoteObject  _button1_click() throws Exception{
try {
		Debug.PushSubsStack("Button1_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,36);
if (RapidSub.canDelegate("button1_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","button1_click");}
 BA.debugLineNum = 36;BA.debugLine="Private Sub Button1_Click";
Debug.ShouldStop(8);
 BA.debugLineNum = 37;BA.debugLine="SendToGemini(EditText1.Text)";
Debug.ShouldStop(16);
_sendtogemini(main.mostCurrent._edittext1.runMethod(true,"getText"));
 BA.debugLineNum = 38;BA.debugLine="End Sub";
Debug.ShouldStop(32);
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
		Debug.PushSubsStack("EditText1_EnterPressed (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,39);
if (RapidSub.canDelegate("edittext1_enterpressed")) { return b4a.example.main.remoteMe.runUserSub(false, "main","edittext1_enterpressed");}
 BA.debugLineNum = 39;BA.debugLine="Private Sub EditText1_EnterPressed";
Debug.ShouldStop(64);
 BA.debugLineNum = 40;BA.debugLine="SendToGemini(EditText1.Text)";
Debug.ShouldStop(128);
_sendtogemini(main.mostCurrent._edittext1.runMethod(true,"getText"));
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
public static RemoteObject  _globals() throws Exception{
 //BA.debugLineNum = 21;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 23;BA.debugLine="Private EditText1 As EditText";
main.mostCurrent._edittext1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 24;BA.debugLine="Private Label1 As Label";
main.mostCurrent._label1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 25;BA.debugLine="Private EditText2 As EditText";
main.mostCurrent._edittext2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 26;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _parseresponse(RemoteObject _response) throws Exception{
try {
		Debug.PushSubsStack("ParseResponse (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,66);
if (RapidSub.canDelegate("parseresponse")) { return b4a.example.main.remoteMe.runUserSub(false, "main","parseresponse", _response);}
RemoteObject _jp = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.JSONParser");
RemoteObject _root = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _candidates = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _firstcandidate = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _content = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _parts = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _firstpart = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _bottext = RemoteObject.createImmutable("");
RemoteObject _convo = RemoteObject.createImmutable("");
RemoteObject _msg = RemoteObject.createImmutable("");
Debug.locals.put("Response", _response);
 BA.debugLineNum = 66;BA.debugLine="Sub ParseResponse (Response As String)";
Debug.ShouldStop(2);
 BA.debugLineNum = 67;BA.debugLine="Dim jp As JSONParser";
Debug.ShouldStop(4);
_jp = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.JSONParser");Debug.locals.put("jp", _jp);
 BA.debugLineNum = 68;BA.debugLine="jp.Initialize(Response)";
Debug.ShouldStop(8);
_jp.runVoidMethod ("Initialize",(Object)(_response));
 BA.debugLineNum = 69;BA.debugLine="Dim root As Map = jp.NextObject";
Debug.ShouldStop(16);
_root = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_root = _jp.runMethod(false,"NextObject");Debug.locals.put("root", _root);Debug.locals.put("root", _root);
 BA.debugLineNum = 70;BA.debugLine="Dim candidates As List = root.Get(\"candidates\")";
Debug.ShouldStop(32);
_candidates = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_candidates = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), _root.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("candidates")))));Debug.locals.put("candidates", _candidates);Debug.locals.put("candidates", _candidates);
 BA.debugLineNum = 71;BA.debugLine="Dim firstCandidate As Map = candidates.Get(0)";
Debug.ShouldStop(64);
_firstcandidate = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_firstcandidate = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), _candidates.runMethod(false,"Get",(Object)(BA.numberCast(int.class, 0))));Debug.locals.put("firstCandidate", _firstcandidate);Debug.locals.put("firstCandidate", _firstcandidate);
 BA.debugLineNum = 72;BA.debugLine="Dim content As Map = firstCandidate.Get(\"content\"";
Debug.ShouldStop(128);
_content = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_content = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), _firstcandidate.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("content")))));Debug.locals.put("content", _content);Debug.locals.put("content", _content);
 BA.debugLineNum = 73;BA.debugLine="Dim parts As List = content.Get(\"parts\")";
Debug.ShouldStop(256);
_parts = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_parts = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), _content.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("parts")))));Debug.locals.put("parts", _parts);Debug.locals.put("parts", _parts);
 BA.debugLineNum = 74;BA.debugLine="Dim firstPart As Map = parts.Get(0)";
Debug.ShouldStop(512);
_firstpart = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_firstpart = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), _parts.runMethod(false,"Get",(Object)(BA.numberCast(int.class, 0))));Debug.locals.put("firstPart", _firstpart);Debug.locals.put("firstPart", _firstpart);
 BA.debugLineNum = 76;BA.debugLine="Dim BotText As String = firstPart.Get(\"text\")";
Debug.ShouldStop(2048);
_bottext = BA.ObjectToString(_firstpart.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("text")))));Debug.locals.put("BotText", _bottext);Debug.locals.put("BotText", _bottext);
 BA.debugLineNum = 78;BA.debugLine="Dim convo As String = \"\"";
Debug.ShouldStop(8192);
_convo = BA.ObjectToString("");Debug.locals.put("convo", _convo);Debug.locals.put("convo", _convo);
 BA.debugLineNum = 79;BA.debugLine="chathistory.Add(\"Bot: \" & BotText)";
Debug.ShouldStop(16384);
main._chathistory.runVoidMethod ("Add",(Object)((RemoteObject.concat(RemoteObject.createImmutable("Bot: "),_bottext))));
 BA.debugLineNum = 80;BA.debugLine="For Each msg As String In chathistory";
Debug.ShouldStop(32768);
{
final RemoteObject group12 = main._chathistory;
final int groupLen12 = group12.runMethod(true,"getSize").<Integer>get()
;int index12 = 0;
;
for (; index12 < groupLen12;index12++){
_msg = BA.ObjectToString(group12.runMethod(false,"Get",index12));Debug.locals.put("msg", _msg);
Debug.locals.put("msg", _msg);
 BA.debugLineNum = 81;BA.debugLine="convo = convo & msg & CRLF";
Debug.ShouldStop(65536);
_convo = RemoteObject.concat(_convo,_msg,main.mostCurrent.__c.getField(true,"CRLF"));Debug.locals.put("convo", _convo);
 }
}Debug.locals.put("msg", _msg);
;
 BA.debugLineNum = 83;BA.debugLine="Label1.Text = convo";
Debug.ShouldStop(262144);
main.mostCurrent._label1.runMethod(true,"setText",BA.ObjectToCharSequence(_convo));
 BA.debugLineNum = 84;BA.debugLine="EditText2.Text = convo";
Debug.ShouldStop(524288);
main.mostCurrent._edittext2.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(_convo));
 BA.debugLineNum = 85;BA.debugLine="End Sub";
Debug.ShouldStop(1048576);
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
httputils2service_subs_0._process_globals();
main.myClass = BA.getDeviceClass ("b4a.example.main");
starter.myClass = BA.getDeviceClass ("b4a.example.starter");
httputils2service.myClass = BA.getDeviceClass ("b4a.example.httputils2service");
httpjob.myClass = BA.getDeviceClass ("b4a.example.httpjob");
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}public static RemoteObject  _process_globals() throws Exception{
 //BA.debugLineNum = 13;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 16;BA.debugLine="Private xui As XUI";
main._xui = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XViewWrapper.XUI");
 //BA.debugLineNum = 17;BA.debugLine="Private const API_KEY As String = \"AIzaSyCAs34hbV";
main._api_key = BA.ObjectToString("AIzaSyCAs34hbVfGZ8SOicqBpxt2yNaMqUmwrjQ");
 //BA.debugLineNum = 18;BA.debugLine="Private const API_URL As String = \"https://genera";
main._api_url = BA.ObjectToString("https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash-lite:generateContent?key=");
 //BA.debugLineNum = 19;BA.debugLine="Dim chathistory As List";
main._chathistory = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
 //BA.debugLineNum = 20;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static void  _sendtogemini(RemoteObject _usertext) throws Exception{
try {
		Debug.PushSubsStack("SendToGemini (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,42);
if (RapidSub.canDelegate("sendtogemini")) { b4a.example.main.remoteMe.runUserSub(false, "main","sendtogemini", _usertext); return;}
ResumableSub_SendToGemini rsub = new ResumableSub_SendToGemini(null,_usertext);
rsub.resume(null, null);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static class ResumableSub_SendToGemini extends BA.ResumableSub {
public ResumableSub_SendToGemini(b4a.example.main parent,RemoteObject _usertext) {
this.parent = parent;
this._usertext = _usertext;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
b4a.example.main parent;
RemoteObject _usertext;
RemoteObject _j = RemoteObject.declareNull("b4a.example.httpjob");
RemoteObject _map1 = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _jsongenerator = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator");

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("SendToGemini (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,42);
Debug.locals = rsLocals;Debug.currentSubFrame.locals = rsLocals;

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
Debug.locals.put("UserText", _usertext);
 BA.debugLineNum = 43;BA.debugLine="Dim j As HttpJob";
Debug.ShouldStop(1024);
_j = RemoteObject.createNew ("b4a.example.httpjob");Debug.locals.put("j", _j);
 BA.debugLineNum = 44;BA.debugLine="j.Initialize(\"\", Me)";
Debug.ShouldStop(2048);
_j.runClassMethod (b4a.example.httpjob.class, "_initialize" /*RemoteObject*/ ,main.processBA,(Object)(BA.ObjectToString("")),(Object)(main.getObject()));
 BA.debugLineNum = 47;BA.debugLine="Dim Map1 As Map = CreateMap(\"contents\": Array(Cre";
Debug.ShouldStop(16384);
_map1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_map1 = parent.mostCurrent.__c.runMethod(false, "createMap", (Object)(new RemoteObject[] {RemoteObject.createImmutable(("contents")),(RemoteObject.createNewArray("Object",new int[] {1},new Object[] {(parent.mostCurrent.__c.runMethod(false, "createMap", (Object)(new RemoteObject[] {RemoteObject.createImmutable(("parts")),(RemoteObject.createNewArray("Object",new int[] {1},new Object[] {(parent.mostCurrent.__c.runMethod(false, "createMap", (Object)(new RemoteObject[] {RemoteObject.createImmutable(("text")),(_usertext)})).getObject())}))})).getObject())}))}));Debug.locals.put("Map1", _map1);Debug.locals.put("Map1", _map1);
 BA.debugLineNum = 48;BA.debugLine="Dim JSONGenerator As JSONGenerator";
Debug.ShouldStop(32768);
_jsongenerator = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator");Debug.locals.put("JSONGenerator", _jsongenerator);
 BA.debugLineNum = 49;BA.debugLine="JSONGenerator.Initialize(Map1)";
Debug.ShouldStop(65536);
_jsongenerator.runVoidMethod ("Initialize",(Object)(_map1));
 BA.debugLineNum = 50;BA.debugLine="chathistory.Add(\"User: \" & UserText)";
Debug.ShouldStop(131072);
parent._chathistory.runVoidMethod ("Add",(Object)((RemoteObject.concat(RemoteObject.createImmutable("User: "),_usertext))));
 BA.debugLineNum = 53;BA.debugLine="j.PostString(API_URL & API_KEY, JSONGenerator.ToS";
Debug.ShouldStop(1048576);
_j.runClassMethod (b4a.example.httpjob.class, "_poststring" /*RemoteObject*/ ,(Object)(RemoteObject.concat(parent._api_url,parent._api_key)),(Object)(_jsongenerator.runMethod(true,"ToString")));
 BA.debugLineNum = 54;BA.debugLine="j.GetRequest.SetContentType(\"application/json\")";
Debug.ShouldStop(2097152);
_j.runClassMethod (b4a.example.httpjob.class, "_getrequest" /*RemoteObject*/ ).runVoidMethod ("SetContentType",(Object)(RemoteObject.createImmutable("application/json")));
 BA.debugLineNum = 56;BA.debugLine="Wait For (j) JobDone(j As HttpJob)";
Debug.ShouldStop(8388608);
parent.mostCurrent.__c.runVoidMethod ("WaitFor","jobdone", main.processBA, anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "main", "sendtogemini"), (_j));
this.state = 7;
return;
case 7:
//C
this.state = 1;
_j = (RemoteObject) result.getArrayElement(false,RemoteObject.createImmutable(0));Debug.locals.put("j", _j);
;
 BA.debugLineNum = 57;BA.debugLine="If j.Success Then";
Debug.ShouldStop(16777216);
if (true) break;

case 1:
//if
this.state = 6;
if (_j.getField(true,"_success" /*RemoteObject*/ ).<Boolean>get().booleanValue()) { 
this.state = 3;
}else {
this.state = 5;
}if (true) break;

case 3:
//C
this.state = 6;
 BA.debugLineNum = 58;BA.debugLine="Log(j.GetString)";
Debug.ShouldStop(33554432);
parent.mostCurrent.__c.runVoidMethod ("LogImpl","4786448",_j.runClassMethod (b4a.example.httpjob.class, "_getstring" /*RemoteObject*/ ),0);
 BA.debugLineNum = 59;BA.debugLine="ParseResponse(j.GetString)";
Debug.ShouldStop(67108864);
_parseresponse(_j.runClassMethod (b4a.example.httpjob.class, "_getstring" /*RemoteObject*/ ));
 if (true) break;

case 5:
//C
this.state = 6;
 BA.debugLineNum = 61;BA.debugLine="Log(\"Error: \" & j.ErrorMessage)";
Debug.ShouldStop(268435456);
parent.mostCurrent.__c.runVoidMethod ("LogImpl","4786451",RemoteObject.concat(RemoteObject.createImmutable("Error: "),_j.getField(true,"_errormessage" /*RemoteObject*/ )),0);
 BA.debugLineNum = 62;BA.debugLine="Log(\"Full Response: \" & j.GetString)";
Debug.ShouldStop(536870912);
parent.mostCurrent.__c.runVoidMethod ("LogImpl","4786452",RemoteObject.concat(RemoteObject.createImmutable("Full Response: "),_j.runClassMethod (b4a.example.httpjob.class, "_getstring" /*RemoteObject*/ )),0);
 if (true) break;

case 6:
//C
this.state = -1;
;
 BA.debugLineNum = 64;BA.debugLine="j.Release";
Debug.ShouldStop(-2147483648);
_j.runClassMethod (b4a.example.httpjob.class, "_release" /*RemoteObject*/ );
 BA.debugLineNum = 65;BA.debugLine="End Sub";
Debug.ShouldStop(1);
if (true) break;

            }
        }
    }
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}
public static void  _jobdone(RemoteObject _j) throws Exception{
}
}