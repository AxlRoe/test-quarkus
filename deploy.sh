#!/bin/bash

gcloud functions deploy quarkus-example-http \
  --entry-point=io.quarkus.gcp.functions.QuarkusHttpFunction \
  --runtime=java11 --trigger-http --allow-unauthenticated --source=target/deployment \
  --set-env-vars EXCHANGE_ADDRESS="192.168.1.3"
