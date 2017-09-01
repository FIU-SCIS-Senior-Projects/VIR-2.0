@echo off

set FRONT_END=./VIR-Frondend
set BACK_END=./VIR-Backend
set OUT_PUT_PATH=../VIR-Backend/src/main/resources/static
set DIR=%~dp0

:: Set environment properties
if "%1"=="prod" (
	echo Building prod
	call :buildFunction "--env=prod", "-Pprod", "-DencryptorAlgorithm=PBEWithMD5AndDES", "-DencryptorPassword=secretkey"
) else (
	echo Building dev default
	call :buildFunction
)
EXIT /B 0

:buildFunction
:: Install the angular dependencies and the project
cd "%FRONT_END%"
echo Installing frontend dependencies
call npm install
echo Building the frontend...
call ng build -op "%OUT_PUT_PATH%" "%~1"
cd /d "%DIR%"

:: Clean the maven backend and rebuild
cd "%BACK_END%"
echo Cleaning the backend...
call mvnw clean
echo Installing the backend...
echo %ENV_FLAG_MAVEN%
call mvnw install %%~2%
cd /d "%DIR%"
EXIT /B 0
