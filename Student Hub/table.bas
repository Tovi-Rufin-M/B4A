B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Class
Version=13.5
@EndOfDesignText@

'version 1.10
Sub Process_Globals
	Dim StringUtils1 As StringUtils
End Sub

Sub Globals
	Dim SV As ScrollView
	Dim Header As Panel
	Dim Table As Panel
	Dim NumberOfColumns, RowHeight, ColumnWidth As Int
	Dim HeaderColor, TableColor, FontColor, HeaderFontColor As Int
	Dim FontSize As Float
	Type RowCol (Row As Int, Col As Int)
	Dim Alignment As Int
	Dim SelectedRow As Int
	Dim SelectedRowColor As Int
	
	'Table settings
	HeaderColor = Colors.Gray
	NumberOfColumns = 4 'will be overwritten when loading from CSV file.
	RowHeight = 30dip
	TableColor = Colors.White
	FontColor = Colors.Black
	HeaderFontColor = Colors.White
	FontSize = 14
	Alignment = Gravity.CENTER 'change to Gravity.LEFT or Gravity.RIGHT for other alignments.
	SelectedRowColor = Colors.Blue
End Sub

Sub Activity_Create(FirstTime As Boolean)
	SV.Initialize(0)
	Table = SV.Panel
	Table.Color = TableColor
	Activity.AddView(SV, 5%x, 10%y, 90%x, 80%y)
	ColumnWidth = SV.Width / NumberOfColumns
	SelectedRow = -1
	LoadTableFromCSV(File.DirAssets, "citylist.csv", True)
	'SaveTableToCSV(File.DirRootExternal, "1.csv")
End Sub
'Clears the previous table and loads the CSV file to the table
Sub LoadTableFromCSV(Dir As String, Filename As String, HeadersExist As Boolean)
	ClearAll
	Dim List1 As List
	Dim h() As String
	If HeadersExist Then
		Dim headers As List
		List1 = StringUtils1.LoadCSV2(Dir, Filename, ",", headers)
		Dim h(headers.Size) As String
		For i = 0 To headers.Size - 1
			h(i) = headers.Get(i)
		Next
	Else
		List1 = StringUtils1.LoadCSV(Dir, Filename, ",")
		Dim firstRow() As String
		firstRow = List1.Get(0)
		Dim h(firstRow.Length)
		For i = 0 To firstRow.Length - 1
			h(i) = "Col" & (i + 1)
		Next
	End If
	NumberOfColumns = h.Length
	ColumnWidth = SV.Width / NumberOfColumns 'update the columns widths
	SetHeader(h)
	For i = 0 To List1.Size - 1
		Dim row() As String
		row = List1.Get(i)
		AddRow(row)
	Next
End Sub
Sub SaveTableToCSV(Dir As String, Filename As String)
	Dim headers(NumberOfColumns) As String
	For i = 0 To headers.Length - 1
		Dim l As Label
		l = Header.GetView(i)
		headers(i) = l.Text
	Next
	Dim list1 As List
	list1.Initialize
	For i = 0 To NumberOfRows - 1
		Dim row(NumberOfColumns) As String
		For c = 0 To NumberOfColumns - 1
			row(c) = GetCell(i, c)
		Next
		list1.Add(row)
	Next
	StringUtils1.SaveCSV2(Dir, Filename, ",", list1, headers)
End Sub

Sub Cell_Click
	Dim rc As RowCol
	Dim l As Label
	l = Sender
	rc = l.Tag
	SelectRow(rc.Row)
	Activity.Title = "Cell clicked: (" & rc.Row & ", " & rc.Col & ")"
End Sub

Sub Header_Click
	Dim l As Label
	Dim col As Int
	l = Sender
	col = l.Tag
	Activity.Title = "Header clicked: " & col
End Sub

Sub SelectRow(Row As Int)
	'remove the color of previously selected row
	If SelectedRow > -1 Then
		For col = 0 To NumberOfColumns - 1
			GetView(SelectedRow, col).Color = Colors.Transparent
		Next
	End If
	SelectedRow = Row
	For col = 0 To NumberOfColumns - 1
		GetView(Row, col).Color = SelectedRowColor
	Next
End Sub
'Returns the label in the specific cell
Sub GetView(Row As Int, Col As Int) As Label
	Dim l As Label
	l = Table.GetView(Row * NumberOfColumns + Col)
	Return l
End Sub

'Adds a row to the table
Sub AddRow(Values() As String)
	If Values.Length <> NumberOfColumns Then
		Log("Wrong number of values.")
		Return
	End If
	Dim lastRow As Int
	lastRow = NumberOfRows
	For i = 0 To NumberOfColumns - 1
		Dim l As Label
		l.Initialize("cell")
		l.Text = Values(i)
		l.Gravity = Alignment
		l.TextSize = FontSize
		l.TextColor = FontColor
		Dim rc As RowCol
		rc.Initialize
		rc.Col = i
		rc.Row = lastRow
		l.Tag = rc
		Table.AddView(l, ColumnWidth * i, RowHeight * lastRow, ColumnWidth, RowHeight)
	Next
	Table.Height = NumberOfRows * RowHeight
End Sub
'Set the headers values
Sub SetHeader(Values() As String)
	If Header.IsInitialized Then Return 'should only be called once
	Header.Initialize("")
	For i = 0 To NumberOfColumns - 1
		Dim l As Label
		l.Initialize("header")
		l.Text = Values(i)
		l.Gravity = Gravity.CENTER
		l.TextSize = FontSize
		l.Color = HeaderColor
		l.TextColor = HeaderFontColor
		l.Tag = i
		Header.AddView(l, ColumnWidth * i, 0, ColumnWidth, RowHeight)
	Next
	Activity.AddView(Header, SV.Left, SV.Top - RowHeight, SV.Width, RowHeight)
End Sub
Sub NumberOfRows As Int
	Return Table.NumberOfViews / NumberOfColumns
End Sub
'Sets the value of the given cell
Sub SetCell(Row As Int, Col As Int, Value As String)
	GetView(Row, Col).Text = Value
End Sub
'Gets the value of the given cell
Sub GetCell(Row As Int, Col As Int) As String
	Return GetView(Row, Col).Text
End Sub
'Clears the table
Sub ClearAll
	For i = Table.NumberOfViews -1 To 0 Step -1
		Table.RemoveViewAt(i)
	Next
	Table.Height = 0
	SelectedRow = -1
End Sub