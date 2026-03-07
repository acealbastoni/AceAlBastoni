@echo off
title 🔄 Auto Restart Chrome on LinkedIn (Every 20 Seconds)

:loop
cls
echo ==================================================
echo 🔄 Restarting Google Chrome on LinkedIn - %date% %time%
echo ==================================================

:: Kill all Chrome processes
taskkill /IM chrome.exe /F

:: Wait to ensure Chrome fully closes
timeout /t 2 /nobreak >nul

:: Launch Chrome with LinkedIn
start "" "C:\Program Files\Google\Chrome\Application\chrome.exe" "https://www.linkedin.com/feed/"

:: Wait 20 seconds before restarting
timeout /t 1000 /nobreak >nul

goto loop
