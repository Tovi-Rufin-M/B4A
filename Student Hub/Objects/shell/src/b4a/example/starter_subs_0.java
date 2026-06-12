package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class starter_subs_0 {


public static RemoteObject  _application_error(RemoteObject _error,RemoteObject _stacktrace) throws Exception{
try {
		Debug.PushSubsStack("Application_Error (starter) ","starter",1,starter.processBA,starter.mostCurrent,32);
if (RapidSub.canDelegate("application_error")) { return b4a.example.starter.remoteMe.runUserSub(false, "starter","application_error", _error, _stacktrace);}
Debug.locals.put("Error", _error);
Debug.locals.put("StackTrace", _stacktrace);
 BA.debugLineNum = 32;BA.debugLine="Sub Application_Error (Error As Exception, StackTr";
Debug.ShouldStop(-2147483648);
 BA.debugLineNum = 33;BA.debugLine="Return True";
Debug.ShouldStop(1);
if (true) return starter.mostCurrent.__c.getField(true,"True");
 BA.debugLineNum = 34;BA.debugLine="End Sub";
Debug.ShouldStop(2);
return RemoteObject.createImmutable(false);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 10;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _service_create() throws Exception{
try {
		Debug.PushSubsStack("Service_Create (starter) ","starter",1,starter.processBA,starter.mostCurrent,12);
if (RapidSub.canDelegate("service_create")) { return b4a.example.starter.remoteMe.runUserSub(false, "starter","service_create");}
 BA.debugLineNum = 12;BA.debugLine="Sub Service_Create";
Debug.ShouldStop(2048);
 BA.debugLineNum = 17;BA.debugLine="If File.Exists(File.DirInternal, \"student.csv\") =";
Debug.ShouldStop(65536);
if (RemoteObject.solveBoolean("=",starter.mostCurrent.__c.getField(false,"File").runMethod(true,"Exists",(Object)(starter.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirInternal")),(Object)(RemoteObject.createImmutable("student.csv"))),starter.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 18;BA.debugLine="File.Copy(File.DirAssets, \"student.csv\", File.Di";
Debug.ShouldStop(131072);
starter.mostCurrent.__c.getField(false,"File").runVoidMethod ("Copy",(Object)(starter.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("student.csv")),(Object)(starter.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirInternal")),(Object)(RemoteObject.createImmutable("student.csv")));
 BA.debugLineNum = 19;BA.debugLine="Log(\"First launch: copied student.csv to DirInte";
Debug.ShouldStop(262144);
starter.mostCurrent.__c.runVoidMethod ("LogImpl","82949127",RemoteObject.createImmutable("First launch: copied student.csv to DirInternal"),0);
 };
 BA.debugLineNum = 21;BA.debugLine="End Sub";
Debug.ShouldStop(1048576);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _service_destroy() throws Exception{
try {
		Debug.PushSubsStack("Service_Destroy (starter) ","starter",1,starter.processBA,starter.mostCurrent,36);
if (RapidSub.canDelegate("service_destroy")) { return b4a.example.starter.remoteMe.runUserSub(false, "starter","service_destroy");}
 BA.debugLineNum = 36;BA.debugLine="Sub Service_Destroy";
Debug.ShouldStop(8);
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
public static RemoteObject  _service_start(RemoteObject _startingintent) throws Exception{
try {
		Debug.PushSubsStack("Service_Start (starter) ","starter",1,starter.processBA,starter.mostCurrent,23);
if (RapidSub.canDelegate("service_start")) { return b4a.example.starter.remoteMe.runUserSub(false, "starter","service_start", _startingintent);}
Debug.locals.put("StartingIntent", _startingintent);
 BA.debugLineNum = 23;BA.debugLine="Sub Service_Start (StartingIntent As Intent)";
Debug.ShouldStop(4194304);
 BA.debugLineNum = 24;BA.debugLine="Service.StopAutomaticForeground 'Starter service";
Debug.ShouldStop(8388608);
starter.mostCurrent._service.runVoidMethod ("StopAutomaticForeground");
 BA.debugLineNum = 25;BA.debugLine="End Sub";
Debug.ShouldStop(16777216);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _service_taskremoved() throws Exception{
try {
		Debug.PushSubsStack("Service_TaskRemoved (starter) ","starter",1,starter.processBA,starter.mostCurrent,27);
if (RapidSub.canDelegate("service_taskremoved")) { return b4a.example.starter.remoteMe.runUserSub(false, "starter","service_taskremoved");}
 BA.debugLineNum = 27;BA.debugLine="Sub Service_TaskRemoved";
Debug.ShouldStop(67108864);
 BA.debugLineNum = 29;BA.debugLine="End Sub";
Debug.ShouldStop(268435456);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}