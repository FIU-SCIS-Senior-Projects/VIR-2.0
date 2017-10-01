#!/bin/bash

# Set some of the commands
if [[ $1 == 'prod' ]]
then
	echo Increasing build values in prod
	./mvnw build-helper:parse-version versions:set -DnewVersion=\${parsedVersion.majorVersion}.\${parsedVersion.minorVersion}.\${parsedVersion.nextIncrementalVersion} -DprocessAllModules=true -DnextSnapshot=true versions:commit
	echo Building prod
	./mvnw clean install -Pprod -Dnpm.executable=npm -Dng.executable=ng -Dng.production.flag="--env=prod"
else
	echo 'Building dev (default)'
	./mvnw clean install -Dnpm.executable=npm -Dng.executable=ng
fi