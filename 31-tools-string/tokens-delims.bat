@echo off
REM �ָ��ַ���

set str=100,200;300,400,500;n600,7,8,9
echo �ԡ�%str%�����зָ�ָ�� ",|;"
echo ȡ��1,2,4,5,*��

for /f "tokens=1,2,4,5,* delims=,|;" %%a in ("%str%") do (

	set c1=%%a
	set c3=%%b
	set c4=%%c
	set c5=%%d
	set c6=%%e
)

echo -
echo �����%c1%, %c3%, %c4%, %c5%, %c6%
echo -
pause
��������������������������������
��Ȩ����������ΪCSDN������scimence����ԭ�����£���ѭ CC 4.0 BY-SA ��ȨЭ�飬ת���븽��ԭ�ĳ������Ӽ���������
ԭ�����ӣ�https://blog.csdn.net/scimence/article/details/52808802