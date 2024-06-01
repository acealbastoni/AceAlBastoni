@echo off
REM Set variables
SET RCLONE_PATH=C:\rclone\rclone-v1.66.0-windows-amd64\rclone.exe
SET REMOTE_NAME=drive
SET FOLDER_ID=LinkedIn
SET LOCAL_PATH="C:\rclone\rclone-v1.66.0-windows-amd64\New folder"

REM Sync local folder to Google Drive folder
%RCLONE_PATH% sync %LOCAL_PATH% %REMOTE_NAME%:%FOLDER_ID%

REM Pause to see the result (optional)
pause
