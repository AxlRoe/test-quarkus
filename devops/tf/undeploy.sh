#!/bin/bash

gcloud functions delete quarkus-example-http \
  --gen2 \
  --region ${REGION}
