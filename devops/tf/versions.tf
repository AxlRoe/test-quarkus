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
    kubernetes = {
      source = "hashicorp/kubernetes"
    }
  }
  required_version = ">= 0.13"
}
