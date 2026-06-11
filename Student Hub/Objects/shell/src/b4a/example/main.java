
package b4a.example;

import java.io.IOException;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.PCBA;
import anywheresoftware.b4a.pc.RDebug;
import anywheresoftware.b4a.pc.RemoteObject;
import anywheresoftware.b4a.pc.RDebug.IRemote;
import anywheresoftware.b4a.pc.Debug;
import anywheresoftware.b4a.pc.B4XTypes.B4XClass;
import anywheresoftware.b4a.pc.B4XTypes.DeviceClass;

public class main implements IRemote{
	public static main mostCurrent;
	public static RemoteObject processBA;
    public static boolean processGlobalsRun;
    public static RemoteObject myClass;
    public static RemoteObject remoteMe;
	public main() {
		mostCurrent = this;
	}
    public RemoteObject getRemoteMe() {
        return remoteMe;    
    }
    
	public static void main (String[] args) throws Exception {
		new RDebug(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]), args[3]);
		RDebug.INSTANCE.waitForTask();

	}
    static {
        anywheresoftware.b4a.pc.RapidSub.moduleToObject.put(new B4XClass("main"), "b4a.example.main");
	}

public boolean isSingleton() {
		return true;
	}
     public static RemoteObject getObject() {
		return myClass;
	 }

	public RemoteObject activityBA;
	public RemoteObject _activity;
    private PCBA pcBA;

	public PCBA create(Object[] args) throws ClassNotFoundException{
		processBA = (RemoteObject) args[1];
		activityBA = (RemoteObject) args[2];
		_activity = (RemoteObject) args[3];
        anywheresoftware.b4a.keywords.Common.Density = (Float)args[4];
        remoteMe = (RemoteObject) args[5];
		pcBA = new PCBA(this, main.class);
        main_subs_0.initializeProcessGlobals();
		return pcBA;
	}
public static RemoteObject __c = RemoteObject.declareNull("anywheresoftware.b4a.keywords.Common");
public static RemoteObject _xui = RemoteObject.declareNull("anywheresoftware.b4a.objects.B4XViewWrapper.XUI");
public static RemoteObject _stringutils1 = RemoteObject.declareNull("anywheresoftware.b4a.objects.StringUtils");
public static RemoteObject _sv = RemoteObject.declareNull("anywheresoftware.b4a.objects.ScrollViewWrapper");
public static RemoteObject _table = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
public static RemoteObject _list1 = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
public static RemoteObject _numberofcolumns = RemoteObject.createImmutable(0);
public static RemoteObject _rowheight = RemoteObject.createImmutable(0);
public static RemoteObject _columnwidth = RemoteObject.createImmutable(0);
public static RemoteObject _alignment = RemoteObject.createImmutable(0);
public static RemoteObject _headercolor = RemoteObject.createImmutable(0);
public static RemoteObject _fontcolor = RemoteObject.createImmutable(0);
public static RemoteObject _headerfontcolor = RemoteObject.createImmutable(0);
public static RemoteObject _fontsize = RemoteObject.createImmutable(0f);
public static RemoteObject _drawer = RemoteObject.declareNull("b4a.example.b4xdrawer");
public static RemoteObject _pnlmain = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
public static RemoteObject _pnlmenu = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
public static RemoteObject _panel36 = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
public static b4a.example.starter _starter = null;
  public Object[] GetGlobals() {
		return new Object[] {"Activity",main.mostCurrent._activity,"Alignment",main._alignment,"ColumnWidth",main._columnwidth,"Drawer",main.mostCurrent._drawer,"FontColor",main._fontcolor,"FontSize",main._fontsize,"HeaderColor",main._headercolor,"HeaderFontColor",main._headerfontcolor,"List1",main.mostCurrent._list1,"NumberOfColumns",main._numberofcolumns,"Panel36",main.mostCurrent._panel36,"pnlmain",main.mostCurrent._pnlmain,"pnlmenu",main.mostCurrent._pnlmenu,"RowHeight",main._rowheight,"Starter",Debug.moduleToString(b4a.example.starter.class),"StringUtils1",main._stringutils1,"SV",main.mostCurrent._sv,"Table",main.mostCurrent._table,"xui",main._xui};
}
}