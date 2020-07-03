#!/bin/bash
java -jar /usr/local/bin/spring-cloud-dataflow-shell-2.5.2.RELEASE.jar --spring.shell.commandFile=\
register-loan-source-app.txt,\
create-loan-log-stream.txt,\
deploy-loan-log-stream.txt
