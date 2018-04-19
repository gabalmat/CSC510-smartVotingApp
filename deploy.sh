mvn -f pom.xml compile
mvn -f pom.xml package
cp target/ROOT.war $CATALINA_HOME/webapps
$CATALINA_HOME/bin/shutdown.sh
$CATALINA_HOME/bin/startup.sh