@echo off
:loop
@echo ██████████████████████████████████████████████████████████████████████████████████████████████████████████████████
@echo start AceAlbastoni.

@echo off
REM Set variables
SET RCLONE_PATH=C:\rclone\rclone-v1.66.0-windows-amd64\rclone.exe
SET REMOTE_NAME=drive
SET FOLDER_ID=LinkedIn
SET LOCAL_PATH=C:\rclone\rclone-v1.66.0-windows-amd64\New folder

REM Copy files to remote
echo Copying to %REMOTE_NAME%:%FOLDER_ID%
%RCLONE_PATH% copy "%LOCAL_PATH%" %REMOTE_NAME%:%FOLDER_ID% --ignore-existing --include "*█*"

REM Add a short delay to ensure all operations are completed
timeout /t 5 /nobreak >nul

REM Delete files based on age
powershell -Command "Get-ChildItem -Path '%LOCAL_PATH%' -Filter '*█*' | Where-Object { $_.LastWriteTime -lt (Get-Date).AddSeconds(-50) } | Remove-Item -Force"
powershell -Command "Get-ChildItem -Path '%LOCAL_PATH%' -Filter '*█*' | Where-Object { $_.LastWriteTime -lt (Get-Date).AddMinutes(-1) } | Remove-Item -Force"
powershell -Command "Get-ChildItem -Path '%LOCAL_PATH%' -Filter '*█*' | Where-Object { $_.LastWriteTime -lt (Get-Date).AddHours(-1) } | Remove-Item -Force"
powershell -Command "Get-ChildItem -Path '%LOCAL_PATH%' -Filter '*█*' | Where-Object { $_.LastWriteTime -lt (Get-Date).AddDays(-1) } | Remove-Item -Force"
powershell -Command "Get-ChildItem -Path '%LOCAL_PATH%' -Filter '*█*' | Where-Object { $_.LastWriteTime -lt (Get-Date).AddMonths(-1) } | Remove-Item -Force"
powershell -Command "Get-ChildItem -Path '%LOCAL_PATH%' -Filter '*█*' | Where-Object { $_.LastWriteTime -lt (Get-Date).AddYears(-1) } | Remove-Item -Force"

REM Refresh the folder by listing its contents
echo Current contents of %LOCAL_PATH%:
dir "%LOCAL_PATH%"

REM Pause to see the result (optional)
:: pause

REM Add a short delay before next loop iteration
timeout /t 6 /nobreak >nul

goto loop
