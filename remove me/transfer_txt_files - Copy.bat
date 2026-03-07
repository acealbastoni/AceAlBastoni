
@echo off
setlocal enabledelayedexpansion

:: Set working path
set "base_dir=C:\rclone\rclone-v1.66.0-windows-amd64\New folder"

:: Change to working directory
cd /d "%base_dir%"

:: Use PowerShell to get .txt files older than 4 minutes
for /f "delims=" %%F in ('powershell -command "Get-ChildItem -Path '%base_dir%' -Filter '*.txt' | Where-Object { $_.LastWriteTime -lt (Get-Date).AddMinutes(-4) } | ForEach-Object { $_.FullName }"') do (

    :: Check if the file contains █
    findstr /m "█" "%%F" >nul
    if !errorlevel! equ 0 (
        set "filename=%%~nF"
        set "extension=%%~xF"

        :: Replace █ with space temporarily to extract the 4th part
        set "tempname=!filename:█= !"

        for /f "tokens=1-4" %%a in ("!tempname!") do (
            set "target_folder=%%d"
        )

        :: Create the folder if it does not exist
        if not exist "%base_dir%\!target_folder!\" (
            mkdir "%base_dir%\!target_folder!"
        )

        :: Move the file
        move "%%F" "%base_dir%\!target_folder!\" >nul
    )
)

endlocal