#!/bin/bash

export GOOGLE_APPLICATION_CREDENTIALS="/home/io/gcloud_home/gcp-dataflow-test/pubsub-to-bigq/data-flow-sa.json"

echo "build "
cd ..
./mvnw package

gcloud functions deploy quarkus-example-http \
  --gen2 \
  --memory="256Mi"  \
  --max-instances=2 \
  --region $_REGION \
  --vpc-connector $_VPC_CONNECTOR \
  --entry-point=io.quarkus.gcp.functions.QuarkusHttpFunction \
  --runtime=java11 --trigger-http --allow-unauthenticated --source=target/deployment
