e: 
cd E:\backup\05-lenovo-pro-2\20191020-2228\D\sunline\MavenRepo\cn\sunline\ltts\cbs\cbs-lt
for /f "delims==" %%i in ('dir  /b /s  *.jar') do (
	pause
	md %%i.class.pkg
	pause
	md %%i.java.pkg
	pause
	D:\ProgramFiles\WinRAR\WinRAR x %%i %%i.class.pkg\
	pause
	E:\backup\05-lenovo-pro-2\20191020-2228\D\41-install\23-file-class\jad158g.win\jad -o -r -s java -d %%i.java.pkg %%i.class.pkg\**\*.class
	pause
	ROBOCOPY %%i.class.pkg %%i.java.pkg *.xml *.properties Log /XD .vs Bin obj /S
	pause
	move %%i.class.pkg E:\backup\05-lenovo-pro-2\20191020-2228\D\03-sl\111-java-res-class\
	pause
	move %%i.java.pkg E:\backup\05-lenovo-pro-2\20191020-2228\D\03-sl\111-java-res\
)
pause