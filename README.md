# OIPA Security Tool Backend

This repository contains the backend service for the **OIPA Security Tool**. It is a Spring Boot application designed to manage, browse, and generate SQL delta scripts for the Oracle Insurance Policy Administration (OIPA) security configuration hierarchy.

The application operates as a **read-only manager** that inspects existing configurations and generates SQL delta scripts (inserting, updating, or deleting security privileges) to align the target database with configuration changes defined in the frontend. It **never** executes modifying DDL/DML statements directly, providing the generated scripts as outputs for manual review and execution.

---

## Features

- **Metadata Exploration**: Endpoints to list and navigate OIPA companies, plans, products, transactions, pages, buttons, web services, and inquiry screens.
- **Hierarchical Fetching**: Retrieves the complete nested security configuration for any existing Security Group.
- **Dry-Run Security Group Creation**: Generates the initial INSERT statement with a generated UUID for new security groups.
- **Delta SQL Generation**: Generates standard SQL scripts by comparing incoming state JSON payloads against the current database state for:
  - Company pages, buttons, and web services.
  - Plan pages, buttons, and inquiry screens.
  - Product pages, buttons, and transactions.
  - Transaction buttons.

---

## Tech Stack

- **Java Version**: 11
- **Framework**: Spring Boot 2.7.7 (Spring Web, Spring Data JPA)
- **Database Driver**: Oracle JDBC Driver (`ojdbc8`)
- **Connection Pool**: HikariCP
- **Build Tool**: Maven

---

## Configuration & Environment Setup

Configure the application by modifying the `src/main/resources/application.yml` file. The tool supports the database environment:

```yaml
server:
  port: 8015

spring:
  datasource:
    secondary-dev:
      jdbc-url: jdbc:oracle:thin:@//<host>:<port>/<service_name>
      username: <username>
      password: "<password>"
```

---

## Running the Application

To run the application locally in development mode:

```bash
# Windows
mvnw.cmd spring-boot:run

# Unix/Linux
./mvnw spring-boot:run
```

The server will start on port `8015` by default.

---

## API Endpoints

### 1. Metadata Browsing

* **Get Companies**: `GET /api/companies`
* **Get Pages**: `GET /api/pages`
* **Get Buttons**: `GET /api/buttons`
* **Get Web Services**: `GET /api/webservices`
* **Get Products by Company**: `GET /api/companies/{companyGuid}/products`
* **Get Plans**:
  * By Company: `GET /api/companies/{companyGuid}/plans`
  * By Company & Product: `GET /api/companies/{companyGuid}/products/{productGuid}/plans`
* **Get Inquiry Screens**: `GET /api/inquiry-screens?companyGuid={companyGuid}&planGuid={planGuid}`
* **Get Transactions**: `GET /api/transactions?productGuid={productGuid}&planGuid={planGuid}`

### 2. Security Group Management

* **List Security Groups**: `GET /api/security-groups`
* **Fetch Security Group Hierarchy**: `GET /api/security-group/{securityGroupGuid}`
* **Create Security Group (Dry-Run)**: `POST /api/security-groups/create`
  * *Body*: `CreateGroupRequestDto` (contains group name and company GUID)
  * *Returns*: The generated insert SQL statement and metadata.
* **Generate Delta SQL Scripts**: `POST /api/security-group/generate-scripts`
  * *Body*: `SecurityGroupRequestDto` (representing the configured state from UI)
  * *Returns*: `GenerateScriptsResponseDto` containing generated SQL statements.
