#!/bin/bash

export GOOGLE_APPLICATION_CREDENTIALS="/home/io/gcloud_home/gcp-dataflow-test/pubsub-to-bigq/data-flow-sa.json"


gcloud functions deploy quarkus-example-http \
  --gen2 \
  --memory="256Mi"  \
  --max-instances=2 \
  --region "europe-west8" \
  --vpc-connector bet-sless-vpc \
  --entry-point=io.quarkus.gcp.functions.QuarkusHttpFunction \
  --runtime=java11 --trigger-http --allow-unauthenticated --source=../target/deployment
