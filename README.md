# Auto update rest

Simple test web app including 1 module for jsonplaceholder rest<br>
It is run with `docker-compose up`<br>
Wat does it do:
- simple crud manipulation of the demo rest api from https://jsonplaceholder.typicode.com/posts
----------------------------

# Run with Docker or assign datasource to application.properties file

Dockerfile and docker-compose.yml<br>
entrypoint.sh includes maven jar files generation.<br>
otherwise:<br>
`mvn clean install -Dmaven.test.skip=true`<br>
`docker-compose up`
entrypoint.sh can be executed instead.<br>

needed properties to run without docker:

`spring.datasource.username={username}
spring.datasource.password={password}
spring.datasource.url=jdbc:mysql://localhost:3306/{databaseName}?serverTimezone=Europe/Warsaw
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL8Dialect
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver`

or use H2 for testing

# Architecture from docker

rest
postgresql db

--------------------------------
# Authentication!

no security included

--------------------------------------
# Requirements

- docker, docker-compose
- maven

or datasource

--------------------------
# Usage
GET to `host:8080`<br>
shows all saved records<br>

GET to `host:8080/rest`<br>
saves a new random record from rest api and shows all saved records<br>

GET to `host:8080/single/{id}` shows chosen post<br>


POST to `host:8080/{id}` edits record with given id<br>
must have body:<br>
example:<br>
`{`
`  "title":"content"`
`  "body":"content"`
`}`


DELETE to `host:8080/{id}` removes record

------------------------------
#Schedule

application automatically updates downloaded records and adds a new random<br>
if the random do exists, it's now written.<br>
deleted and edited records are not updated<br>



------------------------------
#Tests

Due to the lack of time the tests haven't been done

--------------------------

# Made by:

Mateusz N.