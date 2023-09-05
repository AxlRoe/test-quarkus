#!/bin/bash

export GOOGLE_APPLICATION_CREDENTIALS="/home/io/gcloud_home/gcp-dataflow-test/pubsub-to-bigq/data-flow-sa.json"

echo "build "
cd ..
./mvnw package

