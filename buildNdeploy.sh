#!/usr/bin/env bash
export JPDA_ADDRESS="5005"
sh $tom/bin/catalina.sh stop
rm -rf $tom/webapps/customer-care*
mvn clean install -DskipTests
cp ./target/customer-care.war $tom/webapps
sh $tom/bin/catalina.sh jpda start
tail -f $tom/logs/catalina.out
