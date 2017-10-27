VERSION 1.0 CLASS
BEGIN
  MultiUse = -1  'True
END
Attribute VB_Name = "Word"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = False
Attribute VB_Exposed = False
Option Explicit

Private MyValue As String
Private MyCategory As String

Public Property Get Category() As Variant
    Category = MyCategory
End Property

Public Property Let Category(ByVal Category As Variant)
    MyCategory = Category
End Property

Public Property Get Value() As Variant
    Value = MyValue
End Property

Public Property Let Value(ByVal Value As Variant)
    MyValue = Value
End Property


Public Function PrintForSql() As String
    Dim validValue As String
    Dim validCategory As String
    
    validValue = Replace(Me.Value, "'", "''")
    validCategory = Replace(Me.Category, "'", "''")
    
    PrintForSql = "('" & validValue & "', '" & validCategory & "')"
End Function
