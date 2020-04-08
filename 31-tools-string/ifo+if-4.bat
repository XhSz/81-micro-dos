@echo off
set ifo=sef-dzgr-fdol.jar
echo 原字符串（第二行为各字符的序号）：
echo %ifo%
echo 123456789012345678901234567890

echo 截取最后4个字符：
echo %ifo:~-4%
echo 判断是否=.jar：
if "%ifo:~-4%"==".jar" echo Y 
pause