@echo off
REM 分割字符串

set str=100,200;300,400,500;n600,7,8,9
echo 对“%str%”进行分割，分割符 ",|;"
echo 取第1,2,4,5,*列

for /f "tokens=1,2,4,5,* delims=,|;" %%a in ("%str%") do (

	set c1=%%a
	set c3=%%b
	set c4=%%c
	set c5=%%d
	set c6=%%e
)

echo -
echo 输出：%c1%, %c3%, %c4%, %c5%, %c6%
echo -
pause
————————————————
版权声明：本文为CSDN博主「scimence」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/scimence/article/details/52808802