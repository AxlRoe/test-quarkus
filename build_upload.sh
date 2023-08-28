#!/bin/bash

./mvnw package
gsutil cp target/deployment/test-quarkus-1.0-SNAPSHOT-runner.jar gs://dump-bucket-4/functions/
echo "uploaded "
