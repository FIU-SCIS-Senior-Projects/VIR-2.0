#!/bin/bash

FRONT_END=./VIR-Frondend
BACK_END=./VIR-Backend
OUT_PUT_PATH=../VIR-Backend/src/main/resources/static
DIR=$(pwd)

# Set environment properties
if [[ $1 == 'prod' ]]
then
	echo Building prod
	ENV_FLAG_MAVEN='-Pprod'
	ENV_FLAG_NG='--env=prod'
else
	echo 'Building dev (default)'
	ENV_FLAG_MAVEN=
	ENV_FLAG_NG=
fi

# Install the angular dependencies and the project
cd $FRONT_END
echo Installing dependencies
npm install
echo Building the frontend...
ng build -op "$OUT_PUT_PATH" $ENV_FLAG_NG
cd $DIR

# Clean the maven backend and rebuild
cd $BACK_END
echo Cleaning the backend...
./mvnw clean
echo Installing the backend...
./mvnw install $ENV_FLAG_MAVEN
cd $DIR
	