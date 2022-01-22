#!/bin/bash

mvn clean compile install package -Dmaven.test.skip=true

# shellcheck disable=SC2164
cd handyman/

mvn clean compile install scala:compile install package -Dmaven.test.skip=true

mvn install:install-file -DgroupId=in.handyman -DartifactId=handyman.legacy.server -Dpackaging=jar -Dversion=1.0.0 -Dfile=target/handyman.legacy.server-1.0.0.jar -DgeneratePom=true

