:loop
@echo start AceAlbastoni.

@echo off
REM Set variables
SET RCLONE_PATH=C:\rclone\rclone-v1.66.0-windows-amd64\rclone.exe
SET REMOTE_NAME=drive
SET FOLDER_ID=LinkedIn
SET LOCAL_PATH=C:\rclone\rclone-v1.66.0-windows-amd64\New folder

REM Copy local folder to Google Drive folder, ignoring existing duplicates
%RCLONE_PATH% copy "%LOCAL_PATH%" %REMOTE_NAME%:%FOLDER_ID% --ignore-existing

REM Remove the contents of the local folder after syncing
echo Deleting contents of "%LOCAL_PATH%"
del /q "%LOCAL_PATH%\*" 2>nul
for /d %%x in ("%LOCAL_PATH%\*") do @rd /s /q "%%x" 2>nul

REM Pause to see the result (optional)
:: pause


timeout /t 12 /nobreak >nul
goto loop