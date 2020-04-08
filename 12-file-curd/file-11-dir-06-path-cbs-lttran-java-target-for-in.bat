D: 
cd D:\sunline\103-git\20190927-3\
set   /p  var="please input :"
for /f "delims==" %%i in ('dir  /b ') do (
	dir %%i\target\gen\*%var%*.java  /s 
)
pause

