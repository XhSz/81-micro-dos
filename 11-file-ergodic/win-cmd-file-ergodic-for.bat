rem ��������...

for /f "delims=" %%i in ('dir /b /a-d /s "*.js"') do ren "%%i" "%%~ni.txt"

rem �������

pause