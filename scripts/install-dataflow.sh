#!/bin/bash
helm install --name scdf-release --set server.service.type=NodePort stable/spring-cloud-data-flow
