@echo off
d: 
cd D:\03-sl\325-jocom\24-dev\28-ig\lib-ds
for /f "delims==" %%i in ('dir  /b /s  *.jar') do (
	md %%i.class.pkg
	md %%i.java.pkg
	"D:\Program Files\WinRAR\WinRAR" x %%i %%i.class.pkg\
	D:\41-install\23-file-class\jad158g.win\jad -o -r -s java -d %%i.java.pkg %%i.class.pkg\**\*.class
	ROBOCOPY %%i.class.pkg %%i.java.pkg *.xml *.properties Log /XD .vs Bin obj /S
	move %%i.class.pkg D:\03-sl\325-jocom\24-dev\28-ig\lib-ds\ds-class
	move %%i.java.pkg DD:\03-sl\325-jocom\24-dev\28-ig\lib-ds\ds-java
)
pause