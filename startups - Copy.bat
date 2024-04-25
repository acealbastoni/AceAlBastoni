@echo off
REM Get the directory where this script is located
set "ScriptDir=%~dp0"
REM Construct the full path to your script
set "TaskScript=%ScriptDir%startups - Copy.bat"
REM Loop to run the script every 10 seconds
:loop
REM  echo hello
start https://www.linkedin.com/feed/
timeout /t 10 /nobreak >nul
goto loop