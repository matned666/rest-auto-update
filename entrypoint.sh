#!/bin/bash
docker-compose down
mvn clean install -Dmaven.test.skip=true
docker-compose up
