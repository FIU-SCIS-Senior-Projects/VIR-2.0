@echo off

:: Set some of the commands
if "%1"=="prod" (
	echo Building prod
	call mvn clean install -Pprod -Dnpm.executable=npm.cmd -Dng.executbale=ng.cmd -Dng.production.flag="--env=prod"
) else (
	echo Building dev default
	call mvn clean install -Dnpm.executable=npm.cmd -Dng.executbale=ng.cmd
)