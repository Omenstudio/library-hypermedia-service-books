# Books library web service

Hypermedia-driven Web API that supports Hydra (http://www.hydra-cg.com/), json-ld (http://json-ld.org/) and popular vocabulary http://schema.org/


## Working example

API endpoint: https://library-service-books.herokuapp.com/api/

You can also restart DB by running https://library-service-books.herokuapp.com/resetdb. It will clean all database tables and add some simple entities.

Server on Heroku shotdowns after 30 minutes of inactivity, so you can open https://library-service-books.herokuapp.com/iamalive to check if application is running. If app is not running, it will start automatically.


## Getting started
You need locally installed Java 8 SDK, Postgres 9+ (or other DBMS), Maven 3+.

1. `git clone https://github.com/Omenstudio/library-hypermedia-service-books.git`
2. `cd library-hypermedia-service-books/`
3. `mvn install`
4. `java -jar target/service-books-1.0.0.jar`

Server will be started at localhost and you can access API.

Project use Spring (Core, Boot, MVC, Data JPA), Hibernate, AspectJ, Json-ld.

To customize application you need to change DB connection and server URL in `resources/application.properties`


## See also
- Hypermedia micro-framework, which was used in the service: https://github.com/Omenstudio/hydra-microframework
- Another hypermedia web API service, which also use Hydra and Json-ld: https://github.com/Omenstudio/library-hypermedia-service-articles
- WEB UI library application which work with articles and books services: https://github.com/Omenstudio/hypermedia-library-web-ui


Working application available at https://library-service-books.herokuapp.com/