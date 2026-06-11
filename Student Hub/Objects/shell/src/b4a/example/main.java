
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
public static RemoteObject _drawer = RemoteObject.declareNull("b4a.example.b4xdrawer");
public static RemoteObject _pnlmain = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
public static RemoteObject _pnlmenu = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
public static RemoteObject _profilebtn = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
public static RemoteObject _dashbtn = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
public static RemoteObject _grdbtn = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
public static RemoteObject _editbtn = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
public static RemoteObject _datapnl = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
public static RemoteObject _gradepnl = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
public static RemoteObject _sv1 = RemoteObject.declareNull("anywheresoftware.b4a.objects.ScrollViewWrapper");
public static RemoteObject _sv = RemoteObject.declareNull("anywheresoftware.b4a.objects.ScrollViewWrapper");
public static RemoteObject _table = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
public static RemoteObject _numberofcolumns = RemoteObject.createImmutable(0);
public static RemoteObject _rowheight = RemoteObject.createImmutable(0);
public static RemoteObject _columnwidth = RemoteObject.createImmutable(0);
public static RemoteObject _headercolor = RemoteObject.createImmutable(0);
public static RemoteObject _fontcolor = RemoteObject.createImmutable(0);
public static RemoteObject _headerfontcolor = RemoteObject.createImmutable(0);
public static RemoteObject _fontsize = RemoteObject.createImmutable(0f);
public static RemoteObject _alignment = RemoteObject.createImmutable(0);
public static RemoteObject _panel1 = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
public static RemoteObject _checkbox1 = RemoteObject.declareNull("anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper");
public static RemoteObject _edittext = RemoteObject.declareNull("anywheresoftware.b4a.objects.EditTextWrapper");
public static RemoteObject _edittext1 = RemoteObject.declareNull("anywheresoftware.b4a.objects.EditTextWrapper");
public static RemoteObject _edittext2 = RemoteObject.declareNull("anywheresoftware.b4a.objects.EditTextWrapper");
public static RemoteObject _edittext3 = RemoteObject.declareNull("anywheresoftware.b4a.objects.EditTextWrapper");
public static RemoteObject _studentlist = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
public static RemoteObject _csvheaders = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
public static RemoteObject _editingrowindex = RemoteObject.createImmutable(0);
public static RemoteObject _col_name = RemoteObject.createImmutable(0);
public static RemoteObject _col_id = RemoteObject.createImmutable(0);
public static RemoteObject _col_subject = RemoteObject.createImmutable(0);
public static RemoteObject _col_activity = RemoteObject.createImmutable(0);
public static RemoteObject _col_attendance = RemoteObject.createImmutable(0);
public static RemoteObject _col_rate = RemoteObject.createImmutable(0);
public static b4a.example.starter _starter = null;
  public Object[] GetGlobals() {
		return new Object[] {"Activity",main.mostCurrent._activity,"Alignment",main._alignment,"CheckBox1",main.mostCurrent._checkbox1,"COL_ACTIVITY",main._col_activity,"COL_ATTENDANCE",main._col_attendance,"COL_ID",main._col_id,"COL_NAME",main._col_name,"COL_RATE",main._col_rate,"COL_SUBJECT",main._col_subject,"ColumnWidth",main._columnwidth,"CSVHeaders",main.mostCurrent._csvheaders,"dashbtn",main.mostCurrent._dashbtn,"datapnl",main.mostCurrent._datapnl,"Drawer",main.mostCurrent._drawer,"editbtn",main.mostCurrent._editbtn,"EditingRowIndex",main._editingrowindex,"EditText",main.mostCurrent._edittext,"EditText1",main.mostCurrent._edittext1,"EditText2",main.mostCurrent._edittext2,"EditText3",main.mostCurrent._edittext3,"FontColor",main._fontcolor,"FontSize",main._fontsize,"gradepnl",main.mostCurrent._gradepnl,"grdbtn",main.mostCurrent._grdbtn,"HeaderColor",main._headercolor,"HeaderFontColor",main._headerfontcolor,"NumberOfColumns",main._numberofcolumns,"Panel1",main.mostCurrent._panel1,"pnlmain",main.mostCurrent._pnlmain,"pnlmenu",main.mostCurrent._pnlmenu,"profilebtn",main.mostCurrent._profilebtn,"RowHeight",main._rowheight,"Starter",Debug.moduleToString(b4a.example.starter.class),"StringUtils1",main._stringutils1,"StudentList",main.mostCurrent._studentlist,"SV",main.mostCurrent._sv,"SV1",main.mostCurrent._sv1,"Table",main.mostCurrent._table,"xui",main._xui};
}
}