#!/bin/bash

gcloud functions deploy quarkus-example-http \
  --zone=europe-west1-b \
  --entry-point=io.quarkus.gcp.functions.QuarkusHttpFunction \
  --runtime=java11 --trigger-http --allow-unauthenticated --source=target/deployment \
  --set-env-vars EXCHANGE_ADDRESS="192.168.1.3"
