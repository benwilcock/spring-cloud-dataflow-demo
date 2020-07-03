#!/bin/bash
java -jar /usr/local/bin/spring-cloud-dataflow-shell-2.5.2.RELEASE.jar --spring.shell.commandFile=\
delete-loan-log-stream.txt,\
unregister-loan-source-app.txt
