B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Activity
Version=13.4
@EndOfDesignText@
#Region Activity Attributes
    #FullScreen: False
    #IncludeTitle: True
#End Region

Sub Process_Globals
End Sub

Sub Globals
	' Public variables used to pass data from Main activity
	Public isEdit As Boolean
	Public editId As Int

	Private sv As ScrollView
	Private edtStudentNo As EditText
	Private edtFullName As EditText
	Private edtCourse As EditText
	Private edtYearLevel As EditText
	Private edtContact As EditText
	Private edtEmail As EditText
	Private btnSave As Button
	Private btnCancel As Button
End Sub

Sub Activity_Create(FirstTime As Boolean)
	If isEdit Then
		Activity.Title = "Edit Student"
	Else
		Activity.Title = "Add Student"
	End If
	BuildLayout
	If isEdit Then
		LoadRecord
	End If
End Sub

Sub BuildLayout
	Activity.Color = Colors.White

	sv.Initialize(620dip)
	Activity.AddView(sv, 0, 0, 100%x, 100%y)
	sv.Panel.Color = Colors.White

	' Top accent strip
	Dim pnlHeader As Panel
	pnlHeader.Initialize("")
	sv.Panel.AddView(pnlHeader, 0, 0, 100%x, 10dip)
	pnlHeader.Color = 0xFF1A237E

	' --- Student Number ---
	Dim lbl1 As Label
	lbl1.Initialize("")
	sv.Panel.AddView(lbl1, 16dip, 18dip, 100%x - 32dip, 20dip)
	lbl1.Text = "STUDENT NUMBER *"
	lbl1.TextSize = 11
	lbl1.TextColor = 0xFF1A237E

	edtStudentNo.Initialize("edtStudentNo")
	sv.Panel.AddView(edtStudentNo, 16dip, 40dip, 100%x - 32dip, 46dip)
	edtStudentNo.Hint = "e.g. 2024-00001"
	edtStudentNo.Color = 0xFFF5F5F5
	edtStudentNo.TextSize = 14
	edtStudentNo.TextColor = 0xFF212121

	' --- Full Name ---
	Dim lbl2 As Label
	lbl2.Initialize("")
	sv.Panel.AddView(lbl2, 16dip, 98dip, 100%x - 32dip, 20dip)
	lbl2.Text = "FULL NAME *"
	lbl2.TextSize = 11
	lbl2.TextColor = 0xFF1A237E

	edtFullName.Initialize("edtFullName")
	sv.Panel.AddView(edtFullName, 16dip, 120dip, 100%x - 32dip, 46dip)
	edtFullName.Hint = "e.g. Juan Dela Cruz"
	edtFullName.Color = 0xFFF5F5F5
	edtFullName.TextSize = 14
	edtFullName.TextColor = 0xFF212121

	' --- Course / Program ---
	Dim lbl3 As Label
	lbl3.Initialize("")
	sv.Panel.AddView(lbl3, 16dip, 178dip, 100%x - 32dip, 20dip)
	lbl3.Text = "COURSE / PROGRAM"
	lbl3.TextSize = 11
	lbl3.TextColor = 0xFF1A237E

	edtCourse.Initialize("edtCourse")
	sv.Panel.AddView(edtCourse, 16dip, 200dip, 100%x - 32dip, 46dip)
	edtCourse.Hint = "e.g. BSIT, BSCS, BSCE"
	edtCourse.Color = 0xFFF5F5F5
	edtCourse.TextSize = 14
	edtCourse.TextColor = 0xFF212121

	' --- Year Level ---
	Dim lbl4 As Label
	lbl4.Initialize("")
	sv.Panel.AddView(lbl4, 16dip, 258dip, 100%x - 32dip, 20dip)
	lbl4.Text = "YEAR LEVEL"
	lbl4.TextSize = 11
	lbl4.TextColor = 0xFF1A237E

	edtYearLevel.Initialize("edtYearLevel")
	sv.Panel.AddView(edtYearLevel, 16dip, 280dip, 100%x - 32dip, 46dip)
	edtYearLevel.Hint = "e.g. 1st Year, 2nd Year"
	edtYearLevel.Color = 0xFFF5F5F5
	edtYearLevel.TextSize = 14
	edtYearLevel.TextColor = 0xFF212121

	' --- Contact Number ---
	Dim lbl5 As Label
	lbl5.Initialize("")
	sv.Panel.AddView(lbl5, 16dip, 338dip, 100%x - 32dip, 20dip)
	lbl5.Text = "CONTACT NUMBER"
	lbl5.TextSize = 11
	lbl5.TextColor = 0xFF1A237E

	edtContact.Initialize("edtContact")
	sv.Panel.AddView(edtContact, 16dip, 360dip, 100%x - 32dip, 46dip)
	edtContact.Hint = "e.g. 09XXXXXXXXX"
	edtContact.Color = 0xFFF5F5F5
	edtContact.TextSize = 14
	edtContact.TextColor = 0xFF212121

	' --- Email Address ---
	Dim lbl6 As Label
	lbl6.Initialize("")
	sv.Panel.AddView(lbl6, 16dip, 418dip, 100%x - 32dip, 20dip)
	lbl6.Text = "EMAIL ADDRESS"
	lbl6.TextSize = 11
	lbl6.TextColor = 0xFF1A237E

	edtEmail.Initialize("edtEmail")
	sv.Panel.AddView(edtEmail, 16dip, 440dip, 100%x - 32dip, 46dip)
	edtEmail.Hint = "e.g. student@school.edu.ph"
	edtEmail.Color = 0xFFF5F5F5
	edtEmail.TextSize = 14
	edtEmail.TextColor = 0xFF212121

	' --- Save / Cancel buttons ---
	btnSave.Initialize("btnSave")
	sv.Panel.AddView(btnSave, 16dip, 510dip, 50%x - 24dip, 54dip)
	btnSave.Text = "SAVE"
	btnSave.Color = 0xFF1A237E
	btnSave.TextColor = Colors.White
	btnSave.TextSize = 15

	btnCancel.Initialize("btnCancel")
	sv.Panel.AddView(btnCancel, 50%x + 8dip, 510dip, 50%x - 24dip, 54dip)
	btnCancel.Text = "CANCEL"
	btnCancel.Color = 0xFFB71C1C
	btnCancel.TextColor = Colors.White
	btnCancel.TextSize = 15
End Sub

Sub LoadRecord
	Dim cur As Cursor = Main.SQL1.ExecQuery("SELECT * FROM students WHERE id = " & editId)
	If cur.NextRow Then
		edtStudentNo.Text = cur.GetString("student_no")
		edtFullName.Text  = cur.GetString("fullname")
		edtCourse.Text    = cur.GetString("course")
		edtYearLevel.Text = cur.GetString("year_level")
		edtContact.Text   = cur.GetString("contact")
		edtEmail.Text     = cur.GetString("email")
	End If
	cur.Close
End Sub

Sub btnSave_Click
	Dim sno As String = edtStudentNo.Text.Trim
	Dim nm  As String = edtFullName.Text.Trim
	Dim crs As String = edtCourse.Text.Trim
	Dim yr  As String = edtYearLevel.Text.Trim
	Dim ct  As String = edtContact.Text.Trim
	Dim em  As String = edtEmail.Text.Trim

	' Validate required fields
	If sno.Length = 0 Or nm.Length = 0 Then
		Msgbox("Student Number and Full Name are required fields.", "Validation Error")
		Return
	End If

	If isEdit Then
		' UPDATE existing record
		Main.SQL1.ExecNonQuery2( _
            "UPDATE students SET student_no=?, fullname=?, course=?, year_level=?, contact=?, email=? WHERE id=?", _
            Array As Object(sno, nm, crs, yr, ct, em, editId))
		ToastMessageShow("Student record updated successfully.", False)
	Else
		' INSERT new record
		Main.SQL1.ExecNonQuery2( _
            "INSERT INTO students (student_no, fullname, course, year_level, contact, email) VALUES (?,?,?,?,?,?)", _
            Array As Object(sno, nm, crs, yr, ct, em))
		ToastMessageShow("New student record added successfully.", False)
	End If

	Activity.Finish
End Sub

Sub btnCancel_Click
	Activity.Finish
End Sub

Sub Activity_Pause(UserClosed As Boolean)
End Sub
