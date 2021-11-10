# Handyman Legacy

## To Clean, Compile and build, run the below maven commands

1. Inside handyman.legacy
   > mvn clean compile install package -Dmaven.test.skip=true
2. Inside handyman.legacy/handyman
   > mvn clean compile install scala:compile install package -Dmaven.test.skip=true

## Jar

### Generated jar file available in handyman.legacy/target folder as *handyman.legacy-<version>-SNAPSHOT.jar*

# Handyman Raven

## To Clean, Compile and build, run the below maven commands

1. Inside handyman.raven
   > mvn clean antlr4:antlr4 test -Dtest=ActionGenerationTest#generate compile install

## Jar

### Generated jar file available in handyman.raven/target folder as *handyman-raven-vm-<version>-SNAPSHOT.jar*
