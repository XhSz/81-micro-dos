@echo off
set ifo=sef-dzgr-fdol.jar
echo ԭ�ַ������ڶ���Ϊ���ַ�����ţ���
echo %ifo%
echo 123456789012345678901234567890

echo ��ȡ���4���ַ���
echo %ifo:~-4%
echo �ж��Ƿ�=.jar��
if "%ifo:~-4%"==".jar" echo Y 
pause