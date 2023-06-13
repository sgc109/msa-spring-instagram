# msa-spring-instagram

Simplified clone of Instagram back-end system in both monolith version and microservices architecture(MSA) version(msa).

Every server application is implemented with Spring Boot as a framework and Kotlin as a language.

Different skills(or libraries) are used in each application(e.g. some of them use Spring Webflux and the others
Spring MVC).

(TODO: Add system diagrams for each monolith and MSA generated with PlantUML)

## Directories

```
/monolith
  ㄴ Monolith version of instagram server system (developing)
/msa
  ㄴ MSA version (not start to develop yet)
/uml
  ㄴ PlantUML files
```

## APIs

* Most of the APIs were implements by analyzing the URL and parameters of the actual APIs of Instagram(Of course, some
  of them could be different from the real one for simplicity, such as post uploading).

## Features
These are features that the app supports, will support later, and don't support.

### Supported

### In-progress
* Like
* Save
* Comment(w/ threads)
* Feed
  * User feed
  * Following feed
* Follow
* Profile
* Post(only image)
* Inbox
* User tags
* Hashtags
* Search(accounts & tags)
* Push Notification(fake impl)

### Not-supported
* Story
* DM
* Reels
* Post(video)
* Search(posts)
* Ads


## Skills

### Common

* Kotlin
* Spring Boot
* Gradle
* Ktlint

### Monolith

* Spring MVC
* Spring Data JPA
* H2 DB

### MSA

#### Media Service

#### Feed Service

#### User Service

#### Comment Service

#### Like Service

#### Save Service

### TODOs

* Authentication
* Hexagonal Architecture
* Docker
* GitHub Actions
* gRPC
* Spring Webflux
* Spring Data JDBC
* Kafka
* Redis
* Kotest
* MockK
