#!/bin/bash

#GCP_EXCHANGE_ADDRESS=$(gcloud compute addresses describe helloweb-ip --region europe-west8 | grep address: | awk '{print $NF}')

if [[ ! -d ${SRC} ]];then
  echo "Missing jar, build it"
  exit 1
fi

gcloud functions deploy quarkus-example-http \
  --gen2 \
  --memory="256Mi" \
  --max-instances= 2 \
  --region=europe-west8 \
  --vpc-connector bet-vpc-sless \
  --entry-point=io.quarkus.gcp.functions.QuarkusHttpFunction \
  --runtime=java11 --trigger-http --allow-unauthenticated --source=${SRC} \
  --set-env-vars EXCHANGE_ADDRESS="test" >> ${LOG_FILE} 2>&1

URI=$(gcloud functions describe quarkus-example-http --gen2 --region ${REGION} --format="value(serviceConfig.uri)")

cat << EOF > ${OUT_FILE}
uri: $URI
EOF
