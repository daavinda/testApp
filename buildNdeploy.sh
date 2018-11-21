#!/usr/bin/env bash
sh $tom/bin/catalina.sh stop
rm -rf $tom/webapps/customer-care*
mvn clean install -DskipTests
cp ./target/customer-care.war $tom/webapps
sh $tom/bin/catalina.sh jpda start
tail -f $tom/logs/catalina.out
