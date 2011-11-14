#!/bin/sh
JAVA_OPTS="$JAVA_OPTS -Xss4m"
JAVA_OPTS="$JAVA_OPTS -Xms256m"
JAVA_OPTS="$JAVA_OPTS -Xmx512m"
JAVA_OPTS="$JAVA_OPTS -XX:PermSize=128m"
JAVA_OPTS="$JAVA_OPTS -XX:MaxPermSize=256m"
JAVA_OPTS="$JAVA_OPTS -XX:+CMSClassUnloadingEnabled"
java $JAVA_OPTS -jar sbt-launch-0.11.1.jar "$@"


