@echo off
REM Get the directory where this script is located
REM set "ScriptDir=%~dp0"

:loop
@echo start AceAlbastoni.
REM Close Google Chrome
taskkill /F /IM chrome.exe /T >nul 2>&1

REM Wait for Chrome to close
timeout /t 2 /nobreak >nul

REM Open LinkedIn
start https://www.linkedin.com/feed/

REM Wait for 1 equal seconed
timeout /t 60 /nobreak >nul

REM taskkill /f /im chrome.exe

REM Restart the loop
goto loop



REM taskkill /f /im chrome.exe