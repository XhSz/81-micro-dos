@echo off
d: 
set c_year="2020"
set c_month="03"
set c_day="31"
set path_src="D:\sunline\MavenRepo\cn\sunline"
::set path_src="D:\81-micro-dos\31-tools-string\"
set path_tar="D:\81-micro-dos\31-tools-string\sunline_jar.txt"
set todo=echo %%c
cd %path_src%
::for /f "delims==" %%i in ('dir  /b /s  *.bat') do (echo %%i)
  for /f 			%%f in ('dir /s /a-d /tc /b *.jar') do (
	
	
	echo %%~tf %%~zf %%~ff >> %path_tar%
	
	
  )
pause