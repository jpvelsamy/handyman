#!/bin/bash

# shellcheck disable=SC2164
cd handyman.legacy/

mvn clean compile install package -Dmaven.test.skip=true

# shellcheck disable=SC2164
cd handyman/

mvn clean compile install scala:compile install package -Dmaven.test.skip=true

mvn install:install-file -DgroupId=in.handyman -DartifactId=handyman.legacy.server -Dpackaging=jar -Dversion=1.0.0-SNAPSHOT -Dfile=target/handyman.legacy.server-1.0.0-SNAPSHOT.jar -DgeneratePom=true

# shellcheck disable=SC2103
cd ..

# shellcheck disable=SC2103
cd ..

# shellcheck disable=SC2103
cd handyman.raven/

mvn clean antlr4:antlr4 test -Dtest=ActionGenerationTest#generate compile install

# shellcheck disable=SC2103
cd ..

# shellcheck disable=SC2103
cd handyman.server/

mvn clean install -Dmaven.test.skip=true
