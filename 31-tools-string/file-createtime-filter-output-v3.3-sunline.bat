@echo off
d: 
set c_year="2020"
set c_month="03"
set c_day="31"
set path_src="D:\sunline\MavenRepo\cn\sunline"
set path_src="D:\81-micro-dos\31-tools-string\"
set path_tar="D:\81-micro-dos\31-tools-string\2.0-jar-5.txt"
set todo=echo %%c
cd %path_src%
::for /f "delims==" %%i in ('dir  /b /s  *.bat') do (echo %%i)
  for /f 			%%f in ('dir /s /a-d /tc /b *.bat') do (
	
	
	echo %%~tf %%~zf %%~ff
	
	set "str=%%~tf"
	if "!str:~0,2!"=="20" (
		echo %%~ff
		if "!str:~0,4!"==%c_year% (
			if "!str:~5,2!"==%c_month% (
				if "!str:~8,2!" GEQ %c_day% (
					echo %%~ff
				)
			)
			if "!str:~5,2!" GTR %c_month% (
				echo %%~ff
			)
		)
		if "!str:~0,4!" GTR %c_year% (
			echo %%~ff
		)
	)
  )
pause