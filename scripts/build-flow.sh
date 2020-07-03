#!/bin/bash
java -jar /usr/local/bin/spring-cloud-dataflow-shell-2.5.2.RELEASE.jar --spring.shell.commandFile=\
create-loan-processing-stream.txt,\
deploy-loan-processing-stream.txt
