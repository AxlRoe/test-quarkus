#!/bin/bash

export GOOGLE_APPLICATION_CREDENTIALS="/home/io/gcloud_home/gcp-dataflow-test/pubsub-to-bigq/data-flow-sa.json"

echo "build "
cd ..
./mvnw package

echo "deploy "
cd -
cd tf

terraform init
terraform apply -auto-approve
