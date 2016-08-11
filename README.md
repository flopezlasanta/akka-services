# Akka Services

[![Travis CI Status](https://travis-ci.org/flopezlasanta/akka-services.svg?branch=master)](https://travis-ci.org/flopezlasanta/akka-services) [![License](https://img.shields.io/github/license/mashape/apistatus.svg)](https://opensource.org/licenses/MIT)

## Summary
This is a study project for playing with [Akka](http://akka.io/) ecosystem:

- [Akka HTTP](http://doc.akka.io/docs/akka/2.4.8/scala/http/introduction.html)
- [Akka HTTP Spray JSON](http://doc.akka.io/docs/akka/2.4/scala/http/common/json-support.html)
- [Akka based Redis Client](https://github.com/scredis/scredis)

## Project Description

**Structure**

This project applies extensively the [Cake Pattern](http://www.cakesolutions.net/teamblogs/2011/12/19/cake-pattern-in-depth):

```scala
// launches a HTTP server
object Main extends App with Services

// initializes the actors system
trait Services extends Config with Routes

// loads configuration parameters (e.g. HTTP interface and port, eventually DB connection settings...)
trait Config

// provides the routes directives (very similar to the ones from Spray)
trait Routes extends ErrorHandler with SysRoute with UserRoute with UrlRoute

trait ErrorHandler
trait SysRoute extends DefaultJsonProtocol with SprayJsonSupport // provides network functions (only ping by now)
trait UserRoute extends DefaultJsonProtocol with SprayJsonSupport 
trait UrlRoute // provides a simple URL shortener

// provide the domain objects, later marshalled / unmarshalled thanks to the JSON Support from Akka HTTP Spray Json  
case class Ping // used in SysRoute
case class User // used in UserRoute
```

## Extras

**TODO**

- [ ] Generate fat JAR
- [ ] Make the project Docker ready
- [ ] Make the project Heroku aware
- [x] Add automated testing with [ScalaTest](http://www.scalatest.org/)
- [x] Add logging mechanism with [SLF4J](http://www.slf4j.org/) + [LogBack](http://logback.qos.ch/)
- [ ] Add Akka clustering capabilities
- [ ] Add custom marshalling (instead of the default JSON)
- [ ] Add custom versioning
- [ ] Use Slick
- [ ] Use Kafka
- [ ] Use Spark
- [ ] Interact with external system (e.g. Twitter or Google)

**Thanks to…**

- [Daniela Sfregola](https://github.com/DanielaSfregola)
