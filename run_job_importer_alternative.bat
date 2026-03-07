@echo off
title Auto Job Importer (Running Forever)
chcp 65001 >nul
color 0A

setlocal enabledelayedexpansion
set "count=0"
set "BASE_DIR=C:\rclone\rclone-v1.66.0-windows-amd64\New folder"

echo ===================================================
echo   Auto Job Importer Started
echo   Press Ctrl+C to stop
echo ===================================================
echo.
timeout /t 2 /nobreak >nul

:loop
set /a count+=1

echo.
echo ===================================================
echo   Cycle #!count! - %date% %time%
echo ===================================================

REM ============================================
REM STEP 1: Run Job Importer
REM ============================================
echo.
echo [1/2] Running Job Importer...
echo ===================================================

cd /d "C:\Users\Dell\Desktop\AceAlBastoni\simple\AceAlBastoni"

echo    Compiling Java files...
javac -encoding UTF-8 -cp .;mysql-connector-j-8.0.33.jar *.java 2>nul

if exist JobImporterApp.class (
    echo    Executing JobImporterApp...
    java -cp .;mysql-connector-j-8.0.33.jar JobImporterApp
    
    if !errorlevel! equ 0 (
        echo    Import completed successfully
        set "import_success=1"
    ) else (
        echo    Import returned error code: !errorlevel!
        set "import_success=0"
    )
) else (
    echo    Compilation failed!
    set "import_success=0"
)

REM ============================================
REM STEP 2: Move Processed Files IMMEDIATELY
REM ============================================
echo.
echo [2/2] Moving processed files...
echo ===================================================

if "!import_success!"=="0" (
    echo    Skipping file transfer due to import failure
    goto wait_next_cycle
)

cd /d "%BASE_DIR%"

set "moved=0"
set "skipped=0"

REM Process all .txt files and check for special character
for /f "delims=" %%F in ('dir /B *.txt 2^>nul') do (
    
    set "filename=%%~nF"
    set "hasMarker=0"
    
    REM Check if contains the marker (using substring search)
    echo.!filename! | findstr /C:"█" >nul 2>&1
    if !errorlevel! equ 0 set "hasMarker=1"
    
    if "!hasMarker!"=="1" (
        REM Replace marker with space
        set "tempname=!filename:█= !"
        
        REM Extract date (4th token)
        for /f "tokens=4" %%d in ("!tempname!") do (
            set "target_folder=%%d"
        )
        
        REM Validate folder name
        if not "!target_folder!"=="" (
            
            REM Create folder if needed
            if not exist "!target_folder!\" (
                mkdir "!target_folder!" 2>nul
            )
            
            REM Direct move
            move "%%F" "!target_folder!\" >nul 2>&1
            
            if !errorlevel! equ 0 (
                echo    SUCCESS: %%F moved to !target_folder!\
                set /a moved+=1
            ) else (
                echo    WARNING: %%F (locked or error)
                set /a skipped+=1
            )
        ) else (
            echo    WARNING: Invalid filename: %%F
            set /a skipped+=1
        )
    )
)

echo.
echo    Results: !moved! moved, !skipped! skipped

:wait_next_cycle
echo.
echo Waiting 20 seconds before next cycle...
echo (Press Ctrl+C to stop)

timeout /t 20 /nobreak >nul

REM Loop forever
goto loop
