@echo off
d: 
set c_year="2020"
set c_month="03"
set c_day="31"
set path_src="D:\sunline\MavenRepo\cn\sunline"
::set path_src="D:\81-micro-dos\31-tools-string\"
set path_src="D:\81-micro-dos\31-tools-string\sunline_jar.txt"
set path_tar="D:\81-micro-dos\31-tools-string\sunline_jar_2.0\"
set todo=echo %%c>> %path_tar%
cd %path_src%
for /f "tokens=1,2,4 delims= " %%a in path_src do (


	set "str=%%a" 
	if "!str:~0,2!"=="20" (
		if "!str:~0,4!"==%c_year% (
			if "!str:~5,2!"==%c_month% (
				if "!str:~8,2!" GEQ %c_day% (
					echo %%c
					::copy %%c %path_tar%
					
				)
			)
			if "!str:~5,2!" GTR %c_month% (
				echo %%c
				for "delims=\" %%s in %%c do(
					echo %%s
				)
			)
		)
		if "!str:~0,4!" GTR %c_year% (
			echo %%c
		)
	)

)
pause