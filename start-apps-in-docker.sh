#!/bin/bash
echo "Expects the start-servers.sh script to have been run and servers are waiting."
echo "Expects that this script has not been run before"
docker run -d --name loan-source --network spring-cloud-dataflow-demo_default benwilcock/loan-source:1.0.0
docker run -d --name loan-processor --network spring-cloud-dataflow-demo_default benwilcock/loan-processor:1.0.0
echo "If you get errors because the apps already exist"
echo "Try 'docker start loan-source loan-processor'"
echo "One running, check the Rabbit console exchanges 'approved' and 'declined' for message activity at: http://localhost:15672/#/exchanges"
