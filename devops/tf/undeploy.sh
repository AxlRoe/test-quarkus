#!/bin/bash

echo
gcloud functions delete quarkus-example-http \
  --gen2 \
  --region ${REGION} >> ${LOG_FILE} 2>&1
