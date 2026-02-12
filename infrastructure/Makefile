# Makefile for managing infrastructure resources

.PHONY: help init plan apply destroy validate

# Default target
help:
	@echo "Available targets:"
	@echo "  make init     - Initialize Terraform"
	@echo "  make plan     - Show execution plan"
	@echo "  make apply    - Apply changes (auto-approve)"
	@echo "  make destroy  - Destroy resources (auto-approve)"
	@echo "  make validate - Validate HCL syntax"

# Initialize Terraform
init:
	terraform init

# Show execution plan
plan:
	terraform plan

# Apply changes
apply:
	terraform apply -auto-approve

# Destroy resources
destroy:
	terraform destroy -auto-approve

# Validate syntax
validate:
	terraform validate
