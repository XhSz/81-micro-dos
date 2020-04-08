D: 
cd D:\sunline\103-git\20190927-3\
set   /p  var="please input :"
for /f "delims==" %%i in ('dir  /b ') do (
	for /f "delims==" %%b in ('dir %%i\target\gen\*%var%*.java  /s') do (
		echo %%b
	)
)
pause

