@echo off
REM �ָ��ַ���

set str=100,200;300,400,500;n600,7,8,9
echo �ԡ�%str%�����зָ�ָ�� ","
echo ȡ��3,*��

for /f "tokens=3,* delims=," %%a in ("%str%") do (

	set c9=%%a
	set c10=%%b
)

echo -
echo �����%c9%, %c10%
pause