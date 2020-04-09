@echo off&title 字符串切割
set string=aaa/bbb/ccc
:split
rem 把要切割字符串按照/符号分割开，并取得分割后的第一个的值
rem 再把分割后的剩下的值重赋予环境变量string，并重新执行for
rem 第一次 %string%=aaa/bbb/cc
rem 第二次 %string%=bbb/ccc
rem 第三次 %string%=ccc
rem 第四次 %string%=空,结束goto，退出
for /f "tokens=1,* delims=/" %%i in ("%string%") do (
  echo %%i
  set string=%%j
)
if not "%string%"=="" goto split
pause