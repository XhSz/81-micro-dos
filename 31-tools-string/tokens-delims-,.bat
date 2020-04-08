@echo off
REM 分割字符串

set str=100,200;300,400,500;n600,7,8,9
echo 对“%str%”进行分割，分割符 ","
echo 取第3,*列

for /f "tokens=3,* delims=," %%a in ("%str%") do (

	set c9=%%a
	set c10=%%b
)

echo -
echo 输出：%c9%, %c10%
pause