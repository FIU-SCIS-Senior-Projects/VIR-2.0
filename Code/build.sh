#!/bin/bash

# Set some of the commands
if [[ $1 == 'prod' ]]
then
	echo Building prod
	mvn clean install -Pprod -Dnpm.executable=npm -Dng.executbale=ng -Dng.production.flag="--env=prod"
else
	echo 'Building dev (default)'
	mvn clean install -Dnpm.executable=npm -Dng.executbale=ng
fi