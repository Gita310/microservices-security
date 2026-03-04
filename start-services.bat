@echo off

echo ===============================
echo Starting Microservices System
echo ===============================

REM Open Discovery Service (Admin)
powershell -Command "Start-Process cmd -Verb runAs -ArgumentList '/k cd /d C:\Users\paaya\SpringBootAdvance\Microservices-security\discovery-service && mvn spring-boot:run'"

timeout /t 2

REM Open Config Service (Admin)
powershell -Command "Start-Process cmd -Verb runAs -ArgumentList '/k cd /d C:\Users\paaya\SpringBootAdvance\Microservices-security\config-service && mvn spring-boot:run'"

timeout /t 2

REM Open Auth Service (Admin)
powershell -Command "Start-Process cmd -Verb runAs -ArgumentList '/k cd /d C:\Users\paaya\SpringBootAdvance\Microservices-security\auth-service && mvn spring-boot:run'"

timeout /t 2

REM Open User Service (Admin)
powershell -Command "Start-Process cmd -Verb runAs -ArgumentList '/k cd /d C:\Users\paaya\SpringBootAdvance\Microservices-security\user-service && mvn spring-boot:run'"

timeout /t 2

REM Open Order Service (Admin)
powershell -Command "Start-Process cmd -Verb runAs -ArgumentList '/k cd /d C:\Users\paaya\SpringBootAdvance\Microservices-security\order-service && mvn spring-boot:run'"

timeout /t 2

REM Open Gateway Service (Admin)
powershell -Command "Start-Process cmd -Verb runAs -ArgumentList '/k cd /d C:\Users\paaya\SpringBootAdvance\Microservices-security\gateway-service && mvn spring-boot:run'"

echo.
echo ===============================
echo All Services Starting...
echo ===============================

pause