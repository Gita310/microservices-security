@echo off

echo ===============================
echo Starting Microservices System
echo ===============================

REM Open Discovery Service (Admin)
powershell -Command "Start-Process cmd -Verb runAs -ArgumentList '/k cd /d C:\Users\paaya\SpringBootAdvance\Microservices-security\discovery-service && mvn clean &&
mvn dependency:purge-local-repository &&
mvn clean package -U '"


timeout /t 1

REM Open Config Service (Admin)
powershell -Command "Start-Process cmd -Verb runAs -ArgumentList '/k cd /d C:\Users\paaya\SpringBootAdvance\Microservices-security\config-service && mvn clean &&
mvn dependency:purge-local-repository &&
mvn clean package -U '"

timeout /t 1

REM Open Auth Service (Admin)
powershell -Command "Start-Process cmd -Verb runAs -ArgumentList '/k cd /d C:\Users\paaya\SpringBootAdvance\Microservices-security\auth-service && mvn clean &&
mvn dependency:purge-local-repository &&
mvn clean package -U '"

timeout /t 1

REM Open User Service (Admin)
powershell -Command "Start-Process cmd -Verb runAs -ArgumentList '/k cd /d C:\Users\paaya\SpringBootAdvance\Microservices-security\user-service && mvn clean &&
mvn dependency:purge-local-repository &&
mvn clean package -U '"


timeout /t 1

REM Open Order Service (Admin)
powershell -Command "Start-Process cmd -Verb runAs -ArgumentList '/k cd /d C:\Users\paaya\SpringBootAdvance\Microservices-security\order-service && mvn clean &&
mvn dependency:purge-local-repository &&
mvn clean package -U '"


timeout /t 1

REM Open Gateway Service (Admin)
powershell -Command "Start-Process cmd -Verb runAs -ArgumentList '/k cd /d C:\Users\paaya\SpringBootAdvance\Microservices-security\gateway-service mvn clean &&
mvn dependency:purge-local-repository &&
mvn clean package -U '"


echo.
echo ===============================
echo All Services Starting...
echo ===============================

pause