@echo off

set FRONT_END=./VIR-Frondend
set BACK_END=./VIR-Backend
set OUT_PUT_PATH=../VIR-Backend/src/main/resources/static
set DIR=%~dp0

:: Install the angular dependencies and the project
cd "%FRONT_END%"
echo Installing frontend dependencies
call npm install
echo Building the frontend...
call ng build -op "%OUT_PUT_PATH%"
cd /d "%DIR%"

:: Clean the maven backend and rebuild
cd "%BACK_END%"
echo Cleaning the backend...
call mvnw clean
echo Installing the backend...
call mvnw install
cd /d "%DIR%"

pause