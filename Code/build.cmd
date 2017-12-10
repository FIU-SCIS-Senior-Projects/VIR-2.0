@echo off

:: Set some of the commands
if "%1"=="prod" (
	echo Increasing build values in prod
	call mvnw.cmd build-helper:parse-version versions:set -DnewVersion=\${parsedVersion.majorVersion}.\${parsedVersion.minorVersion}.\${parsedVersion.nextIncrementalVersion} -DprocessAllModules=true -DnextSnapshot=true versions:commit
	echo Building prod
	call mvnw.cmd clean install -Pprod -Dnpm.executable=npm.cmd -Dng.executable=ng.cmd -Dng.production.flag="--prod"
) else (
	echo Building dev default
	call mvnw.cmd clean install -Dnpm.executable=npm.cmd -Dng.executable=ng.cmd
)