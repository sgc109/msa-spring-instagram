# msa-instagram

Simplified clone of Instagram back-end system in both monolith/ version and microservices architecture(MSA) version(
msa/).

Every server application is implemented with Spring Boot as a framework and Kotlin as a language.

Different skills(or libraries) are used in each application(e.g. some of them use Spring Webflux and the others
Spring MVC).

(TODO: Add system diagrams for each monolith and MSA generated with PlantUML)

## APIs

* Most of the APIs were implements by analyzing the URL and parameters of the actual APIs of Instagram(Of course, some
  of them could be different from the real one for simplicity, such as post uploading).

## Skills

### Common

* Kotlin
* Spring Boot
* Gradle
* GitHub Actions
* Ktlint

### Monolith

* Spring MVC
* Spring Data JPA
* RDB

### MSA

#### Post

#### Feed

#### User

#### Comment

#### Like

#### Save

### TODOs

* Authentication
* DDD
* Swagger
* Hexagonal Architecture
* Docker
* gRPC
* Spring Webflux
* Spring Data JDBC
* Kafka
* Redis
* Kotest
* MockK
