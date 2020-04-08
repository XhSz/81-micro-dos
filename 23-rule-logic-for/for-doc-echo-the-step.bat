c: 
cd C:\win-cmd\14-file-open
for /f "delims==" %%i in ('dir  /b /s *.bat') do (
	echo %%i 
	echo 1 
	echo 2
)
pause