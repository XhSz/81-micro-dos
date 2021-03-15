@echo off
d: 
cd D:\03-sl\325-jocom\24-dev\2\package-class\marketing-dist-3.1.2.12-BNC-RELEASE-3\lib
for /f "delims==" %%i in ('dir  /b /s  *.jar') do (
	md %%i.class.pkg
	md %%i.java.pkg
	"D:\Program Files\WinRAR\WinRAR" x %%i %%i.class.pkg\
	D:\41-install\23-file-class\jad158g.win\jad -o -r -s java -d %%i.java.pkg %%i.class.pkg\**\*.class
	ROBOCOPY %%i.class.pkg %%i.java.pkg *.xml *.properties Log /XD .vs Bin obj /S
	move %%i.class.pkg D:\03-sl\325-jocom\24-dev\2\package\mk-22-class-3
	move %%i.java.pkg D:\03-sl\325-jocom\24-dev\2\package\mk-23-java-3
)
pause