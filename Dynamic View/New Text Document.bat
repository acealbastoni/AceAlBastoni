@echo off
setlocal

:: Initialize the SSID variable
set "SSID="

:: Extract the SSID (Wi-Fi name) from the netsh output
for /f "tokens=2 delims=:" %%A in ('netsh wlan show interfaces ^| findstr /c:"SSID"') do (
    set "SSID=%%A"
    goto :done
)

:done
:: Trim leading spaces from the SSID
for /f "tokens=* delims= " %%B in ("%SSID%") do set "SSID=%%B"

:: Condition to check the SSID value
if "%SSID%"=="iPhone" (
    echo Yes, you are connected to the Wi-Fi network: %SSID%.
) else (
    echo No, you are connected to a different Wi-Fi network: %SSID%.
)

pause
