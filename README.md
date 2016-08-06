# Akka Services

[![Travis CI Status](https://travis-ci.org/flopezlasanta/akka-services.svg?branch=master)](https://travis-ci.org/flopezlasanta/akka-services) [![License](https://img.shields.io/github/license/mashape/apistatus.svg)](https://opensource.org/licenses/MIT)

## Summary
This a study project for practicing with [Akka](http://akka.io/).

* Develop HTTP-based services with [Akka HTTP](http://doc.akka.io/docs/akka/2.4.8/scala/http/introduction.html)

## Project Description

**Structure**

In `Main` a new HTTP server is launched using the `Http` class. The `bindAndHandle()` method starts a new HTTP server and uses the given 'handler' `Flow` for processing incoming connections.

This project applies extensively the [Thin Cake Pattern](http://www.cakesolutions.net/teamblogs/2011/12/19/cake-pattern-in-depth):

- `Main` extends from `Services` trait
- `Services` initializes the actors system
- `Services` extends from `Config` and `Routes`
- `Config` loads configuration parameters (e.g. HTTP interface and port, eventually database connection settings)
- `Routes` provides the route directives (very similar to the ones from [Spray](http://spray.io/))
- `Routes` extends from `SysRoute` and `UserRoute`
- `SysRoute` relies on `Ping` case class
- `UserRoute` relies on `User` case class

## Extras

**TODO**

- [ ] Make the project Docker ready
- [ ] Make the project Heroku aware
- [ ] Add automated testing
- [ ] Add logging mechanism
- [ ] Add marshalling (now it is relying on JSON)
- [ ] Add internal database (e.g. Redis or Postgres) and use Slick
- [ ] Query external API (e.g. Twitter or Google)

**Thanks to…**

- [Daniela Sfregola](https://github.com/DanielaSfregola)
