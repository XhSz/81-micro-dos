@echo off
for /r C:\win-cmd %%i in (*.csv) do (
mongoimport -d taxi -c All -f ID,time,lon,lat,ang,spe,ACC,carry --type csv --file "%%~i" -upsert
)
pause