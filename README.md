
# Handyman

## To get the repository clone

 > git clone https://github.com/zucisystems-dev/handyman.git

## To see the list of branch in the repo (default master)
 > git branch -a
 
## To change to the particular branch
 > git checkout {{branch}}
 
## To Clean, Compile and build, run the below maven commands 
  1. In the project root dir
     > mvn clean antlr4:antlr4 test -Dtest=ActionGenerationTest#generate compile install
  
 ### Generated jar file available in target folder as *handyman-raven-vm-{{version}}.jar*
