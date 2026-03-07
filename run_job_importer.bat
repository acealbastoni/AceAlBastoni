@echo off
title 🔁 Auto Job Importer (Every 20 Seconds)
setlocal enabledelayedexpansion
set "count=0"

:loop
cls
set /a count+=1
echo ==================================================
echo 🔁 Job Importer Auto Execution - Run #!count! - %date% %time%
echo ==================================================

REM Better window detection - check for specific process instead
tasklist /FI "IMAGENAME eq cmd.exe" 2>nul | find /i "cmd.exe" >nul
if errorlevel 1 (
    echo ❌ Main console window is not running. Skipping execution...
) else (
    echo ✅ Starting process...
    cd /d "C:\Users\Dell\Desktop\AceAlBastoni\simple\AceAlBastoni"
    
    echo 🔧 Compiling Java files...
    javac -encoding UTF-8 -cp .;mysql-connector-j-8.0.33.jar *.java
    
    if exist JobImporterApp.class (
        echo ▶ Running JobImporterApp...
        java -cp .;mysql-connector-j-8.0.33.jar JobImporterApp
        echo ✅ Execution complete.
    ) else (
        echo ❌ Compilation failed. Please check the source code.
    )
)

echo 🔄 Running transfer_txt_files.bat...
call "C:\rclone\rclone-v1.66.0-windows-amd64\New folder\transfer_txt_files - Copy.bat"
echo ✅ transfer_txt_files.bat execution complete.

echo 🕒 Waiting 20 seconds before next run...
timeout /t 20 /nobreak >nul
goto loop