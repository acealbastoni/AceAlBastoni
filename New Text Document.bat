@echo off
set "downloadPath=G:\old\test"
set "chromeProfilePath=G:\old\test"

rem Ensure the custom profile directory exists
if not exist "%chromeProfilePath%" mkdir "%chromeProfilePath%"

rem Launch Chrome with the custom profile
start chrome --user-data-dir="%chromeProfilePath%" --profile-directory="Profile 1" --disable-popup-blocking --disable-extensions

rem Configure Chrome preferences
echo {
    "download": {
        "default_directory": "%downloadPath%"
    }
} > "%chromeProfilePath%\Default\Preferences"
