variable "aws_region" {
  description = "The AWS region to deploy to"
  type        = string
  default     = "us-east-1"
}

variable "project_name" {
  description = "The name of the project"
  type        = string
  default     = "edge2-mtr"
}

variable "callback_urls" {
  description = "List of allowed callback URLs for the Cognito User Pool Client"
  type        = list(string)
  default     = ["http://localhost:3000"]
}

variable "logout_urls" {
  description = "List of allowed logout URLs for the Cognito User Pool Client"
  type        = list(string)
  default     = ["http://localhost:3000"]
}

variable "admin_email" {
  description = "The email address for the initial admin user"
  type        = string
}

variable "admin_password" {
  description = "The initial password for the admin user"
  type        = string
  sensitive   = true
}
