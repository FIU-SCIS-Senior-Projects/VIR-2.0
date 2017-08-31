#!/bin/bash

FRONT_END=./VIR-Frondend
BACK_END=./VIR-Backend
OUT_PUT_PATH=../VIR-Backend/src/main/resources/static
ENV_MAVEN=prod
DIR=$(pwd)

# Install the angular dependencies and the project
cd $FRONT_END
echo Installing dependencies
npm install
echo Building the frontend...
ng build -op "$OUT_PUT_PATH" --env=prod
cd $DIR

# Clean the maven backend and rebuild
cd $BACK_END
echo Cleaning the backend...
mvn clean
echo Installing the backend...
mvn install -P$ENV_MAVEN
cd $DIR
