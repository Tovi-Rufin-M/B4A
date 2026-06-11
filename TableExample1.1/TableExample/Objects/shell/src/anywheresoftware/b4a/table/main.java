
package anywheresoftware.b4a.table;

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
        anywheresoftware.b4a.pc.RapidSub.moduleToObject.put(new B4XClass("main"), "anywheresoftware.b4a.table.main");
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
public static RemoteObject _stringutils1 = RemoteObject.declareNull("anywheresoftware.b4a.objects.StringUtils");
public static RemoteObject _sv = RemoteObject.declareNull("anywheresoftware.b4a.objects.ScrollViewWrapper");
public static RemoteObject _header = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
public static RemoteObject _table = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
public static RemoteObject _numberofcolumns = RemoteObject.createImmutable(0);
public static RemoteObject _rowheight = RemoteObject.createImmutable(0);
public static RemoteObject _columnwidth = RemoteObject.createImmutable(0);
public static RemoteObject _headercolor = RemoteObject.createImmutable(0);
public static RemoteObject _tablecolor = RemoteObject.createImmutable(0);
public static RemoteObject _fontcolor = RemoteObject.createImmutable(0);
public static RemoteObject _headerfontcolor = RemoteObject.createImmutable(0);
public static RemoteObject _fontsize = RemoteObject.createImmutable(0f);
public static RemoteObject _alignment = RemoteObject.createImmutable(0);
public static RemoteObject _selectedrow = RemoteObject.createImmutable(0);
public static RemoteObject _selectedrowcolor = RemoteObject.createImmutable(0);
  public Object[] GetGlobals() {
		return new Object[] {"Activity",main.mostCurrent._activity,"Alignment",main._alignment,"ColumnWidth",main._columnwidth,"FontColor",main._fontcolor,"FontSize",main._fontsize,"Header",main.mostCurrent._header,"HeaderColor",main._headercolor,"HeaderFontColor",main._headerfontcolor,"NumberOfColumns",main._numberofcolumns,"RowHeight",main._rowheight,"SelectedRow",main._selectedrow,"SelectedRowColor",main._selectedrowcolor,"StringUtils1",main._stringutils1,"SV",main.mostCurrent._sv,"Table",main.mostCurrent._table,"TableColor",main._tablecolor};
}
}