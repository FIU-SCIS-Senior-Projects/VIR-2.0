#!/bin/bash

# Set some of the commands
if [[ $1 == 'prod' ]]
then
	if [ -z "$2"]
	then
		echo Increasing build values in prod
		./mvnw build-helper:parse-version versions:set -DnewVersion=\${parsedVersion.majorVersion}.\${parsedVersion.minorVersion}.\${parsedVersion.nextIncrementalVersion} -DprocessAllModules=true -DnextSnapshot=true versions:commit	
	else
		echo Increasing build to fixed values "$2" in prod
		./mvnw versions:set -DnewVersion="$2" -DprocessAllModules=true -DnextSnapshot=false versions:commit
	fi

	echo Building prod
	./mvnw clean install -Pprod -Dnpm.executable=npm -Dng.executable=ng -Dng.production.flag="--prod"
else
	echo 'Building dev (default)'
	./mvnw clean install -Dnpm.executable=npm -Dng.executable=ng
fi