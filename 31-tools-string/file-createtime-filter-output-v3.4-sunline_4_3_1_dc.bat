@echo off
d: 
cd D:\81-micro-dos\31-tools-string\sunline_jar_3.1_dc\
for /f "delims==" %%i in ('dir  /b /s  *.jar') do (
	md %%i.class.pkg
	md %%i.java.pkg
	"D:\Program Files\WinRAR\WinRAR" x %%i %%i.class.pkg\
	D:\41-install\23-file-class\jad158g.win\jad -o -r -s java -d %%i.java.pkg %%i.class.pkg\**\*.class
	ROBOCOPY %%i.class.pkg %%i.java.pkg *.xml *.properties Log /XD .vs Bin obj /S
	move %%i.class.pkg D:\81-micro-dos\31-tools-string\sunline_jar_3.1_dc_class
	move %%i.java.pkg D:\81-micro-dos\31-tools-string\sunline_jar_3.1_dc_java
)
pause