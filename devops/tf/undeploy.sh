#!/bin/bash

REGION=$1
gcloud functions delete quarkus-example-http \
  --gen2 \
  --region ${REGION}
