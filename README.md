# Akka Services

[![Travis CI Status](https://travis-ci.org/flopezlasanta/akka-services.svg?branch=master)](https://travis-ci.org/flopezlasanta/akka-services) [![License](https://img.shields.io/github/license/mashape/apistatus.svg)](https://opensource.org/licenses/MIT)

## Summary
This is a study project for playing with [Akka](http://akka.io/) ecosystem:

- [Akka HTTP](http://doc.akka.io/docs/akka/2.4.8/scala/http/introduction.html)
- [Akka HTTP Spray JSON](http://doc.akka.io/docs/akka/2.4/scala/http/common/json-support.html)
- [Akka based Redis Client](https://github.com/scredis/scredis)

## Project Description

**Structure**

This project applies extensively the [(Thin) Cake Pattern](http://www.cakesolutions.net/teamblogs/2011/12/19/cake-pattern-in-depth):

```scala
// launches a HTTP server
object Main extends App with Services

// initializes the actors system
trait Services extends Config with Routes

// loads configuration parameters (e.g. HTTP interface and port, eventually DB connection settings...)
trait Config

// provides the routes directives (very similar to the ones from Spray)
trait Routes extends ErrorHandler with SysRoute with UserRoute with UrlRoute with StocksRoute

trait ErrorHandler
trait SysRoute extends DefaultJsonProtocol with SprayJsonSupport // system functions
trait UserRoute extends DefaultJsonProtocol with SprayJsonSupport 
trait UrlRoute // simple URL shortener
trait StocksRoute // stocks info

// provide the domain objects, later marshalled / unmarshalled thanks to the JSON Support from Akka HTTP Spray Json  
case class SimpleMessage // used in SysRoute
case class User // used in UserRoute
```

**How to Run**

1. Clone the project and run: `$ sbt docker:publishLocal`
2. Run the container: `$ docker run -dit -p 9000:9000 --name akka-services:1.0`
3. Stop the container: `$ docker stop akka-services`

**How to Test**
