mvn clean compile package install -Dmaven.test.skip=true	
cp -Rv ./src/process/root/ ./target/classes	
cp -Rv ./src/process/auth/ ./target/classes	
mvn scala:run -Dlauncher=spw
