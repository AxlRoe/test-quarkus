#!/bin/bash

export GOOGLE_APPLICATION_CREDENTIALS="/home/io/gcloud_home/gcp-dataflow-test/pubsub-to-bigq/data-flow-sa.json"

gcloud functions delete quarkus-example-http --region "europe-west8" --gen2
