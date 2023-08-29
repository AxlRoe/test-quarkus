terraform {
  backend "gcs" {
    bucket = "dump-bucket-4"
    prefix = "terraform/state/function"
  }
  required_providers {
    google = {
      source  = "hashicorp/google"
      version = ">= 4.77.0"
    }
  }
  required_version = ">= 0.13"
}
