 pushd C:\win-cmd\12-file-curd\aa
    for /r %%a in (*.xml) do (
		echo %%a
		XCOPY /Y "%%a" "C:\win-cmd\12-file-curd\result"
    )
 popd
 pause