D: 
cd D:\sunline\103-git\20190927-3\
for /f "delims==" %%i in ('dir  /b ') do (
	dir %%i\target\gen\*.java  /s 
)
pause

