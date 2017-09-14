@echo off

:: Set some of the commands
if "%1"=="prod" (
	echo Building prod
	call mvnw.cmd clean install -Pprod -Dnpm.executable=npm.cmd -Dng.executbale=ng.cmd -Dng.production.flag="--env=prod"
) else (
	echo Building dev default
	call mvnw.cmd clean install -Dnpm.executable=npm.cmd -Dng.executbale=ng.cmd
)