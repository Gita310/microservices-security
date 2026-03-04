@echo off

echo ===============================
echo Stopping Microservices System
echo ===============================

:: Run in Administrator mode
powershell -Command "Start-Process cmd -Verb RunAs -ArgumentList '/k echo Stopping Services... && taskkill /F /IM java.exe && echo. && echo All Services Stopped Successfully && pause'"