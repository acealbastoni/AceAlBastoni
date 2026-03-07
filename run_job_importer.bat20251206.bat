@echo off
title 🔁 Auto Job Importer (Running Forever)
chcp 65001 >nul
color 0A

setlocal enabledelayedexpansion
set "count=0"
set "BASE_DIR=C:\rclone\rclone-v1.66.0-windows-amd64\New folder"

echo ╔══════════════════════════════════════════════════╗
echo ║  🚀 Auto Job Importer Started                    ║
echo ║  Press Ctrl+C to stop                            ║
echo ╚══════════════════════════════════════════════════╝
echo.
timeout /t 2 /nobreak >nul

:loop
set /a count+=1

echo.
echo ╔══════════════════════════════════════════════════╗
echo ║  🔁 Cycle #!count! - %date% %time%
echo ╚══════════════════════════════════════════════════╝

REM ============================================
REM STEP 1: Run Job Importer
REM ============================================
echo.
echo [1/2] 🔧 Running Job Importer...
echo ═══════════════════════════════════════════

cd /d "C:\Users\Dell\Desktop\AceAlBastoni\simple\AceAlBastoni"

echo    📦 Compiling Java files...
javac -encoding UTF-8 -cp .;mysql-connector-j-8.0.33.jar *.java 2>nul

if exist JobImporterApp.class (
    echo    ▶️  Executing JobImporterApp...
    java -cp .;mysql-connector-j-8.0.33.jar JobImporterApp
    
    if !errorlevel! equ 0 (
        echo    ✅ Import completed successfully
        set "import_success=1"
    ) else (
        echo    ⚠️  Import returned error code: !errorlevel!
        set "import_success=0"
    )
) else (
    echo    ❌ Compilation failed!
    set "import_success=0"
)

REM ============================================
REM STEP 2: Move Processed Files IMMEDIATELY
REM ============================================
echo.
echo [2/2] 📁 Moving processed files...
echo ═══════════════════════════════════════════

if "!import_success!"=="0" (
    echo    ⏭️  Skipping file transfer due to import failure
    goto wait_next_cycle
)

cd /d "%BASE_DIR%"

set "moved=0"
set "skipped=0"

REM Move ALL files with █ (they were just processed)
for /f "delims=" %%F in ('dir /B *.txt 2^>nul') do (
    
    REM Check if filename contains █
    echo %%F | findstr /C:"█" >nul
    if !errorlevel! equ 0 (
        
        REM Check if file is accessible (not locked)
        2>nul (
            >>%%F echo off
        ) && (
            set "filename=%%~nF"
            
            REM Replace █ with space to extract date
            set "tempname=!filename:█= !"
            
            REM Extract 4th token (date part like 20251121)
            for /f "tokens=4" %%d in ("!tempname!") do (
                set "target_folder=%%d"
            )
            
            REM Validate and move
            if not "!target_folder!"=="" (
                
                REM Create folder if needed
                if not exist "%BASE_DIR%\!target_folder!\" (
                    mkdir "%BASE_DIR%\!target_folder!" 2>nul
                )
                
                REM Move file
                move "%%F" "%BASE_DIR%\!target_folder!\" >nul 2>&1
                
                if !errorlevel! equ 0 (
                    echo    ✅ %%F → !target_folder!\
                    set /a moved+=1
                ) else (
                    echo    ❌ Failed: %%F
                    set /a skipped+=1
                )
            ) else (
                echo    ⚠️  Invalid folder: %%F
                set /a skipped+=1
            )
        ) || (
            echo    🔒 Locked: %%F
            set /a skipped+=1
        )
    )
)

echo.
echo    📊 Results: !moved! moved, !skipped! skipped

:wait_next_cycle
echo.
echo ⏳ Waiting 20 seconds before next cycle...
echo    (Press Ctrl+C to stop)

timeout /t 20 /nobreak >nul

REM Loop forever
goto loop