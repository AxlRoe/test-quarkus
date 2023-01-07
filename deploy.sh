#!/bin/bash

gcloud functions deploy quarkus-example-http \
  --region=europe-west1 \
  --entry-point=io.quarkus.gcp.functions.QuarkusHttpFunction \
  --runtime=java11 --trigger-http --allow-unauthenticated --source=target/deployment \
  --set-env-vars EXCHANGE_ADDRESS="34.154.201.134"
