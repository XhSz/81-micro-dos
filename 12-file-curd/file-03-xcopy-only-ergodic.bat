@echo off
set /p SourcePath=请输入或拖入源文件夹路径：
set TargerPath=%SourcePath%1
md "%TargerPath%">nul
ROBOCOPY "%SourcePath%" "%TargerPath%" *.git *.config *.gitattributes *.class Log /XD .vs Bin obj /S
ROBOCOPY "%SourcePath%"\.git "%TargerPath%"\.git /S /E
pause
pause
