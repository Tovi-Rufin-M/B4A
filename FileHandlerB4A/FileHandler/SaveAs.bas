B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Class
Version=11
@EndOfDesignText@
Sub Class_Globals
	Private Root As B4XView 'ignore
	Private xui As XUI 'ignore
	
	Private ion As Object
	Private Button1 As B4XView
End Sub

'You can add more parameters here.
Public Sub Initialize As Object
	Return Me
End Sub

'This event will be called once, before the page becomes visible.
Private Sub B4XPage_Created (Root1 As B4XView)
	Root = Root1
	'load the layout to Root
	Root.LoadLayout("SaveAs")

End Sub

Sub SaveFile (Source As InputStream, MimeType As String, Title As String) As ResumableSub
	Dim intent As Intent
	intent.Initialize("android.intent.action.CREATE_DOCUMENT", "")
	intent.AddCategory("android.intent.category.OPENABLE")
	intent.PutExtra("android.intent.extra.TITLE", Title)
	intent.SetType(MimeType)
	StartActivityForResult(intent)
	Wait For ion_Event (MethodName As String, Args() As Object)
	If -1 = Args(0) Then 'resultCode = RESULT_OK
		Dim result As Intent = Args(1)
		Dim jo As JavaObject = result
		Dim ctxt As JavaObject
		Dim out As OutputStream = ctxt.InitializeContext.RunMethodJO("getContentResolver", Null).RunMethod("openOutputStream", Array(jo.RunMethod("getData", Null)))
		File.Copy2(Source, out)
		out.Close
		Return True
	End If
	Return False
End Sub

Sub StartActivityForResult(i As Intent)
	Dim jo As JavaObject = GetBA
	ion = jo.CreateEvent("anywheresoftware.b4a.IOnActivityResult", "ion", Null)
	jo.RunMethod("startActivityForResult", Array(ion, i))
End Sub

Sub GetBA As Object
	Dim jo As JavaObject = Me
	Return jo.RunMethod("getBA", Null)
End Sub

'You can see the list of page related events in the B4XPagesManager object. The event name is B4XPage.

Private Sub Button1_Click
	Dim saDir, saFileName As String
	saDir="application/octet-stream"
	saFileName="test.txt"
	File.WriteString(File.DirInternal, saFileName, "test") 'just as example...
	Wait For (SaveFile(File.OpenInput(File.DirInternal, saFileName), saDir, saFileName)) Complete (Success As Boolean)
	Log("File saved successfully? " & Success)
End Sub