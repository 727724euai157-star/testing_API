# Automated REST API Testing Framework

Spring Boot user API and a separate REST Assured + TestNG integration suite. The API uses MySQL, BCrypt, and JWT.

## Requirements

- Java 17 and Maven 3.9+
- Docker Desktop (for MySQL)

## Run

1. Copy `.env.example` to `.env` and set a private JWT secret (at least 32 bytes). Spring does not load `.env` automatically; export the variables in your shell before starting the backend (in PowerShell, for example: `$env:JWT_SECRET='your-random-secret-of-32-bytes-or-more'; $env:DB_PASSWORD='your-local-db-password'`).
2. Start MySQL: `docker compose up -d mysql`
3. Start the API from `backend`: `mvn spring-boot:run`
4. In another terminal run the HTTP integration suite from `api-automation`: `mvn clean test`

The suite uses `base.url` (default `http://localhost:8080`) and test DB JDBC settings from environment variables. It verifies service readiness before executing. TestNG reports are written under `api-automation/target/surefire-reports`.

Override configuration with `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`, `JWT_SECRET`, `BASE_URL`. Never use a production database for this suite. OpenAPI UI: `http://localhost:8080/swagger-ui.html`.

## Run a test class

`mvn test -Dtest=RegistrationTests` (or `LoginTests`, `UserApiTests`).

The API starts with `ddl-auto=update` for a simple demo. Configure a dedicated empty test database before running automation: test setup creates unique accounts and deletes only those it creates.
