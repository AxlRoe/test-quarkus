#!/bin/bash

./mvnw package
cd ..
cp -r test-quarkus source
rm -rf source/.git
cd source
zip -r /tmp/source.zip .
gsutil cp /tmp/source.zip gs://dump-bucket-4/functions/
cd ..
rm -rf source
cd test-quarkus
