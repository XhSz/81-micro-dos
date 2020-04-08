e: 
cd E:\backup\05-lenovo-pro-2\20191020-2228\D\sunline\MavenRepo\cn\sunline\ltts\cbs\cbs-lt
for /f "delims==" %%i in ('dir  /b /s  *.jar') do (
	echo %%i
	echo 2
)
pause