@echo off
d: 
set path = D:\sunline\MavenRepo\cn\sunline\odc\cbs\cbs-ap-base
cd D:\sunline\MavenRepo\cn\sunline\odc\cbs\cbs-ap-base
setlocal enabledelayedexpansion
for /f "tokens=1,2,4 delims= " %%a in ('dir  /s  *.jar') do (
	set "str=%%a"
	::echo,!str:~0,2! 
	if "!str:~0,4!"=="2020" (
		set curyear=!str:~0,4!
		if curyear GTR 2020 (
			echo %curyear%
			set curmonth=!str:~4,2!
			if curmonth GTR 04 (
				set curday=!str:~6,2!
				if curday GTR 07 (
					echo %%a,%%b,%%c
				)
			)
		)
	)
)
pause