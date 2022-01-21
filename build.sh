#!/bin/bash

mvn install:install-file -DgroupId=in.handyman -DartifactId=handyman.parent -Dpackaging=pom -Dversion=1.0.0 -Dfile="./pom.xml" -DgeneratePom=true


# shellcheck disable=SC2164
cd handyman.legacy/

mvn clean compile install package -Dmaven.test.skip=true

# shellcheck disable=SC2164
cd handyman/

mvn clean compile install scala:compile install package -Dmaven.test.skip=true

mvn install:install-file -DgroupId=in.handyman -DartifactId=handyman.legacy.server -Dpackaging=jar -Dversion=1.0.0 -Dfile=target/handyman.legacy.server-1.0.0.jar -DgeneratePom=true

# shellcheck disable=SC2103
cd ..

# shellcheck disable=SC2103
cd ..

# shellcheck disable=SC2103
cd handyman.raven/

mvn clean antlr4:antlr4 test -Dtest=ActionGenerationTest#generate compile install

mvn install:install-file -DgroupId=in.handyman -DartifactId=handyman.raven -Dpackaging=jar -Dversion=1.0.0 -Dfile=target/handyman-raven-vm-1.0.0.jar -DgeneratePom=true


# shellcheck disable=SC2103
cd ..

# shellcheck disable=SC2103
cd handyman.server/

mvn clean install -Dmaven.test.skip=true
