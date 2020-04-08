e: 
cd E:\backup\05-lenovo-pro-2\20191020-2228\D\sunline\MavenRepo\cn\sunline
for /f "delims==" %%i in ('dir  /b /s  *.jar') do (
	md %%i.class.pkg
	md %%i.java.pkg
	D:\ProgramFiles\WinRAR\WinRAR x %%i %%i.class.pkg\
	E:\backup\05-lenovo-pro-2\20191020-2228\D\41-install\23-file-class\jad158g.win\jad -o -r -s java -d %%i.java.pkg %%i.class.pkg\**\*.class
	ROBOCOPY %%i.class.pkg %%i.java.pkg *.xml *.properties Log /XD .vs Bin obj /S
	move %%i.class.pkg E:\backup\05-lenovo-pro-2\20191020-2228\D\03-sl\111-java-res-class\
	move %%i.java.pkg E:\backup\05-lenovo-pro-2\20191020-2228\D\03-sl\111-java-res\
)