@echo off
start https://www.linkedin.com
timeout /t 600 /nobreak >nul
taskkill /f /im chrome.exe