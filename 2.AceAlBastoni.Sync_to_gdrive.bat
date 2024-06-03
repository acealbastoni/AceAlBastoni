:loop
@echo ██████████████████████████████████████████████████████████████████████████████████████████████████████████████████
@echo start AceAlbastoni.

@echo off
REM Set variables
SET RCLONE_PATH=C:\rclone\rclone-v1.66.0-windows-amd64\rclone.exe
SET REMOTE_NAME=drive
SET FOLDER_ID=LinkedIn

SET A=A
SET B=B
SET C=C
SET D=D

SET LOCAL_PATH=C:\rclone\rclone-v1.66.0-windows-amd64\New folder

echo Copying to %REMOTE_NAME%:%FOLDER_ID%
%RCLONE_PATH% copy "%LOCAL_PATH%" %REMOTE_NAME%:%FOLDER_ID% --ignore-existing

:: REM Function to count files to be copied
:: for %%F in (%A% %B% %C% %D%) do (
::     echo Counting files to be copied to %REMOTE_NAME%:%FOLDER_ID%/%%F
::     for /f "tokens=1,2,3 delims= " %%i in ('%RCLONE_PATH% copy "%LOCAL_PATH%" %REMOTE_NAME%:%FOLDER_ID%/%%F --ignore-existing --dry-run --stats-one-line') do (
::         echo %%i %%j %%k
::     )
::     echo Copying to %REMOTE_NAME%:%FOLDER_ID%/%%F
::     %RCLONE_PATH% copy "%LOCAL_PATH%" %REMOTE_NAME%:%FOLDER_ID%/%%F --ignore-existing
:: )

REM Add a short delay to ensure all operations are completed
timeout /t 5 /nobreak >nul
REM Remove the contents of the local folder after syncing
echo Deleting contents of "%LOCAL_PATH%"
del /q "%LOCAL_PATH%\*" 2>nul
for /d %%x in ("%LOCAL_PATH%\*") do @rd /s /q "%%x" 2>nul

REM Pause to see the result (optional)
:: pause

timeout /t 6 /nobreak >nul
goto loop
