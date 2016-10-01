# Classroom Exercise 4

Commit per exercise please!

## Exercise 1
Start by fixing the `QuoteRepositoryIntegrationTest`.

## Exercise 2
Write your own implementation of `findByQuotation_OnlyReturnsQuotesByMatchingQuotes` in `QuoteRepositoryIntegrationTest`.

Watch it fail for the right reason first. Then implement production code to make your test work.

## Exercise 3
Fix the the `MemorableQuotesControllerIntegrationTest` too.

## How to run in your IDE
In `build.gradle` I included the `idea` and `eclipse` plugins, so all there is to it is `./gradlew eclipse` or `./gradlew idea` or `./gradlew.bat ...` if you're on windows.

## Docker stuff
If you're not on Linux [install Docker-Toolbox](https://www.docker.com/docker-toolbox).

### Installing on OSX
The installer might not have created a `default` machine, so you'll have to create it manually.

You can tell you don't have a `default` machine when `docker-machine ip default` returns `Error: No machine name(s) specified and no "default" machine exists.`.

Run this command `docker-machine create -d virtualbox default`.

Then run `eval $(docker-machine env)` to sync your shell with the VM.

### Docker stuff continued

Check which ip your docker VM is running on with `docker-machine ip default`.

Run `docker-compose up` to spin up two PostgreSQL database instances.

You can then use this as your jdbc url `jdbc:postgresql://<your vm's ip>:5432/postgres` in your application.

More info on [Postgres' DockerHub page](https://hub.docker.com/_/postgres/).

### IntegrationTests
In `src/test/resources/application.properties` you'll find it's pointing to `jdbc:postgresql://<your vm's ip>:5433/testdb` (note the difference in port and db name).

### Network timed out while trying to connect to ...
```
docker-machine restart default      # Restart the environment
`$(docker-machine env default)`     # Refresh your environment settings
```

## FlyWay
[FlyWay](http://flywaydb.org/) makes sure your database tables are up to date and uses simple convention over configuration to manage your versioned SQL scripts.

### Ways to use FlyWay
* Using the [spring-boot plugin](http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#howto-execute-flyway-database-migrations-on-startup)
* Using the [gradle plugin](http://flywaydb.org/documentation/gradle/)

### Validate failed. Migration Checksum mismatch for migration 1
```
Validate failed. Migration Checksum mismatch for migration 1
-> Applied to database : 812944198
-> Resolved locally    : -1906377092
```

This means you most probably changed a sql file after it was already executed.

Either fix this by running `./gradlew flywayRepair` if you're still testing out your script, or create a new migration script that only has the change necessary.