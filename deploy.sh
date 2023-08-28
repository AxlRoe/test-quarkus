#!/bin/bash

GCP_EXCHANGE_ADDRESS=$(gcloud compute addresses describe helloweb-ip --region europe-west8 | grep address: | awk '{print $NF}')

gcloud functions deploy quarkus-example-http \
  --region=europe-west1 \
  --entry-point=io.quarkus.gcp.functions.QuarkusHttpFunction \
  --runtime=java11 --trigger-http --allow-unauthenticated --source=target/deployment \
  --set-env-vars EXCHANGE_ADDRESS=$GCP_EXCHANGE_ADDRESS
