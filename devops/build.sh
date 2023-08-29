#!/bin/bash

echo "build "
cd ..
./mvnw package

echo "deploy "
cd -
terraform init
terraform apply -auto-approve
