#!/bin/bash
java -jar /usr/local/bin/spring-cloud-dataflow-shell-2.5.2.RELEASE.jar --spring.shell.commandFile=\
register-loan-source-app.txt,\
register-loan-processor-app.txt,\
register-log-app.txt
