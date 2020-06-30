#!/bin/bash
echo "Building apps using the Maven wrapper...."
cd loan-source
./mvnw clean install spring-boot:build-image
cd ..
cd loan-processor
./mvnw clean install spring-boot:build-image
cd ..
