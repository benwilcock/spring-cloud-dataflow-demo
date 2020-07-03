#!/bin/bash
java -jar /usr/local/bin/spring-cloud-dataflow-shell-2.5.2.RELEASE.jar --spring.shell.commandFile=\
unregister-loan-source-app.txt,\
unregister-loan-processor-app.txt,\
unregister-log-app.txt
