provider "google" {
  project = var.project_id
  region  = var.region
  zone    = var.zone
}

data "google_client_config" "default" {}

data "google_vpc_access_connector" "bet-sless-vpc" {
  name = "bet-sless-vpc"
}

resource "google_project_service" "cloudbuild" {
  service            = "cloudbuild.googleapis.com"
  disable_on_destroy = false
}

resource "google_project_service" "run" {
  service            = "run.googleapis.com"
  disable_on_destroy = false
}


#TODO read regional ip of exchange

resource "null_resource" "deploy_function" {

  triggers = {
    invokes_me_everytime = uuid()
    region = var.region
  }

  provisioner "local-exec" {
    command     = "/bin/bash ${path.module}/deploy.sh"
    interpreter = ["/bin/bash", "-c"]
    working_dir = path.module

    environment = {
      LOG_FILE = "${path.module}/deploy.log"
      SRC="${path.module}/../../target/deployment"
      REGION= var.region
      OUT_FILE="${path.module}/tmp.yaml"
      VPC_CONNECTOR=data.google_vpc_access_connector.bet-sless-vpc.name
    }
  }

  provisioner "local-exec" {
    #it's not possible to reference vars here, so it's necessary to force the trigger with uuid
    when = destroy
    command     = "/bin/bash ${path.module}/undeploy.sh"
    interpreter = ["/bin/bash", "-c"]
    working_dir = path.module

    environment = {
      #self reference only parent attribute etc...
      REGION= self.triggers.region
    }
  }

  depends_on = [google_project_service.cloudbuild, google_project_service.run]
}

data "local_file" "tmp" {
  filename   = "${path.module}/tmp.yaml"
  depends_on = [null_resource.deploy_function]
}

data "google_storage_bucket" "bucket" {
  name = var.bucket_name
}

resource "google_storage_bucket_object" "default" {
  name         = "functions/discover/out.yaml"
  source       = "${path.module}/tmp.yaml"
  content_type = "text/plain"
  bucket       = data.google_storage_bucket.bucket.id
}

