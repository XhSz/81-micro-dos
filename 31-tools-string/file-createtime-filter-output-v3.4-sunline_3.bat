@echo off
set path_tar="D:\81-micro-dos\31-tools-string\sunline_jar_2.0\"
for /f "tokens=1,2,4,5 delims= " %%a in (sunline_jar_2.0.txt) do (

	echo %%a %%b %%c %%d
	copy %%c "%path_tar%%%d"

)
pause

