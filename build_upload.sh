#!/bin/bash

zip source.zip *
gsutil cp source.zip gs://dump-bucket-4/functions/
