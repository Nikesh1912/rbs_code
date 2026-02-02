# Dow Jones Stock Service

Spring Boot application for:

- Bulk CSV upload
- Query by ticker
- Add record

## Tech Stack
- Java 21
- Spring Boot
- mySQL
- Docker

## Run locally

mvn clean package
docker-compose up --build

## APIs

POST /stocks/upload
GET /stocks/{ticker}
POST /stocks
