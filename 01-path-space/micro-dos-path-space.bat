@echo off
goto start
copy 1.txt DOCUME~1\1.txt 
copy 2.txt LOCALS~1\2.txt 
::Program Files
copy 3.txt Progra~1\3.txt 
::Progra file
copy 4.txt Progra~2\4.txt
::Progra zhang 
copy 5.txt Progra~3\5.txt 
subst w: "D:\81-micro-dos\01-path-space\Documents and Settings_6"
copy 6.txt w:\6.txt 
:start
copy 7.txt "D:\81-micro-dos\01-path-space\Program Files""\7.txt"
pause