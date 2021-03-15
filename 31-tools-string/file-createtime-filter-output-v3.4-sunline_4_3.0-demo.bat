@echo off
d: 
cd D:\03-sl-107-code\04-3.0-demo-2\Demo-Back-unbuild\lib-all
for /f "delims==" %%i in ('dir  /b /s  *.jar') do (
	md %%i.class.pkg
	md %%i.java.pkg
	"D:\Program Files\WinRAR\WinRAR" x %%i %%i.class.pkg\
	D:\41-install\23-file-class\jad158g.win\jad -o -r -s java -d %%i.java.pkg %%i.class.pkg\**\*.class
	ROBOCOPY %%i.class.pkg %%i.java.pkg *.xml *.properties Log /XD .vs Bin obj /S
	move %%i.class.pkg D:\03-sl-107-code\04-3.0-demo-2\Demo-Back-unbuild-class
	move %%i.java.pkg D:\03-sl-107-code\04-3.0-demo-2\Demo-Back-unbuild-java
)
pause