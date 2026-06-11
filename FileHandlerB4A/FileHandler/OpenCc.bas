B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Class
Version=11
@EndOfDesignText@
Sub Class_Globals
	Private Root As B4XView 'ignore
	Private xui As XUI 'ignore
	Private Button1 As B4XView
	Dim CC As ContentChooser 'Phone Library
End Sub

'You can add more parameters here.
Public Sub Initialize As Object
	Return Me
End Sub

'This event will be called once, before the page becomes visible.
Private Sub B4XPage_Created (Root1 As B4XView)
	Root = Root1
	'load the layout to Root
	Root.LoadLayout("OpenFile")
	CC.Initialize("CC")
End Sub

'You can see the list of page related events in the B4XPagesManager object. The event name is B4XPage.

Private Sub Button1_Click
	'xui.MsgboxAsync("Hello world!", "B4X")
	CC.Show("*/*", "Choose file")
	'CC.Show("image/*", "Choose image")
	'CC.Show("audio/*", "Choose audio file")
End Sub

Sub CC_Result (Success As Boolean, Dir As String, FileName As String)
	
	If Success = True Then
		xui.MsgboxAsync("Opened: "& Dir &"/"& FileName, "B4X")
	Else
		ToastMessageShow("No Success :(",True)
	End If
		
End Sub
