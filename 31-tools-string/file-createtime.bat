@echo off
d: 
cd D:\81-micro-dos\23-rule-logic-for\for-unbuild-jar-02-for-echo-jar-sunline
setlocal enabledelayedexpansion
for /f "tokens=1,2,4 delims= " %%a in ('dir  /s  *.bat') do (
	set "str=%%a"
	::echo,!str:~0,2! 
	if "!str:~0,2!"=="20" echo %%a,%%b,%%c
)
pause