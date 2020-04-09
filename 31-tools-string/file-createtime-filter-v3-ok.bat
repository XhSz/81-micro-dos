@echo off
d: 
set c_year="2020"
set c_month="03"
set c_day="31"
set path="D:\81-micro-dos\31-tools-string"
cd %path%
setlocal enabledelayedexpansion
for /f "tokens=1,2,4 delims= " %%a in ('dir  /s  *.*') do (
	set "str=%%a" 
	if "!str:~0,2!"=="20" (
		if "!str:~0,4!"==%c_year% (
			if "!str:~5,2!"==%c_month% (
				if "!str:~8,2!" GEQ %c_day% (
					echo %%a,%%b,%%c
				)
			)
			if "!str:~5,2!" GTR %c_month% (
				echo %%a,%%b,%%c
			)
		)
		if "!str:~0,4!" GTR %c_year% (
			echo %%a,%%b,%%c
		)
	)
)
pause