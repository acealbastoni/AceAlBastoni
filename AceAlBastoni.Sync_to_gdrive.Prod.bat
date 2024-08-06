@echo off

REM Set code page to UTF-8
chcp 65001 >nul

:loop
@echo ██████████████████████████████████████████████████████████████████████████████████████████████████████████████████
@echo start AceAlbastoni.

@echo off
REM Set variables
SET RCLONE_PATH=C:\rclone\rclone-v1.66.0-windows-amd64\rclone.exe
SET REMOTE_NAME=drive
SET FOLDER_ID=LinkedIn
SET LOCAL_PATH=C:\rclone\rclone-v1.66.0-windows-amd64\New folder
SET TEMP_PATH=C:\rclone\rclone-v1.66.0-windows-amd64\Temp
SET LOG_FILE=C:\rclone\rclone-v1.66.0-windows-amd64\logfile.txt

REM Create temporary folder if it doesn't exist
if not exist "%TEMP_PATH%" mkdir "%TEMP_PATH%"

REM Move files to temporary folder
for %%f in ("%LOCAL_PATH%\*█*") do (
    move "%%f" "%TEMP_PATH%\"
    if not errorlevel 1 (
        echo Moved to temp: %%~ff
        echo Moved to temp: %%~ff >> "%LOG_FILE%"
    ) else (
        echo Error moving %%~ff to temp
        echo Error moving %%~ff to temp >> "%LOG_FILE%"
    )
)

REM Move files from temporary folder to remote
for %%f in ("%TEMP_PATH%\*█*") do (
    echo Moving %%~ff to %REMOTE_NAME%:%FOLDER_ID%
    %RCLONE_PATH% move "%%f" %REMOTE_NAME%:%FOLDER_ID%
    if not errorlevel 1 (
        echo Moved: %%~ff to %REMOTE_NAME%:%FOLDER_ID%
        echo Moved: %%~ff to %REMOTE_NAME%:%FOLDER_ID% >> "%LOG_FILE%"
    ) else (
        echo Error moving %%~ff to %REMOTE_NAME%:%FOLDER_ID%
        echo Error moving %%~ff to %REMOTE_NAME%:%FOLDER_ID% >> "%LOG_FILE%"
    )
    timeout /t 1 /nobreak >nul
)

REM Refresh the folder by listing its contents
echo Current contents of %LOCAL_PATH%:
dir "%LOCAL_PATH%"

echo Current contents of %TEMP_PATH%:
dir "%TEMP_PATH%"

REM Pause to see the result (optional)
:: pause

REM Add a short delay before next loop iteration
timeout /t 6 /nobreak >nul

goto loop
