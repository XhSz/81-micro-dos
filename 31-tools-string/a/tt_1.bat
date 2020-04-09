@echo off
d: 
cd D:\81-micro-dos\31-tools-string
setlocal enabledelayedexpansion
for /f "tokens=1,2,4 delims= " %%a in ('dir  /s  *.*') do (
	set "str=%%a"
	::echo,!str:~0,2! 
	if "!str:~0,4!"=="2020" (
		if "!str:~0,4!" GEQ "2020" (
			if "!str:~5,2!" GEQ "04" (
				if "!str:~8,2!" GEQ "09" (
					echo %%a,%%b,%%c
				)
			)
		)
	)
)
pause