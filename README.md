# Akka Services

[![Travis CI Status](https://travis-ci.org/flopezlasanta/akka-services.svg?branch=master)](https://travis-ci.org/flopezlasanta/akka-services) [![License](https://img.shields.io/github/license/mashape/apistatus.svg)](https://opensource.org/licenses/MIT)

## Summary
This a study project for practicing with [Akka](http://akka.io/).

* Develop HTTP-based services with [Akka HTTP](http://doc.akka.io/docs/akka/2.4.8/scala/http/introduction.html)

## Project Description

**Structure**

This project applies extensively the [Thin Cake Pattern](http://www.cakesolutions.net/teamblogs/2011/12/19/cake-pattern-in-depth):

```scala
// launches a HTTP server
object Main extends App with Services

// initializes the actors system
trait Services extends Config with Routes

// loads configuration parameters (e.g. HTTP interface and port, eventually database connection settings...)
trait Config

// provides the routes directives (very similar to the ones from [Spray](http://spray.io/))
trait Routes extends ErrorHandler with SysRoute with UserRoute

// provide the domain objects, later marshalled / unmarshalled thanks to the JSON Support from [Akka HTTP Spray Json](http://doc.akka.io/docs/akka/2.4/scala/http/common/json-support.html)  
case class Ping
case class User
```

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
