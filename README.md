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

1. Clone the project: `git clone git@github.com:flopezlasanta/akka-services.git`
2. Compile the code, generate a Docker image and publish locally: `$ sbt docker:publishLocal`
3. Run the container `akka-services` together with `redis` via Docker Compose: `$ docker-compose up -d`
4. Test with the different end points (see next section)
5. Stop the containers via Docker Compose: `$ docker-compose stop`

**How to Test**

*Note: when using VM / Docker Machine (this is my case) then use its corresponding IP address; otherwise localhost.*

- Check system health: `curl -X GET http://192.168.99.100:9000/sys/health | jq .`
- Ping system: `curl -X GET http://192.168.99.100:9000/sys/ping | jq .`
- ... 

Another options is to:
[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/34021b48a56eb5ffe000)

Also in root there will be simple website (made with Bootstrap 3) listing all endpoints: <img src="https://raw.githubusercontent.com/flopezlasanta/akka-services/master/page.png" width="192">

