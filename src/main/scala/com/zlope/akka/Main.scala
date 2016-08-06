package com.zlope.akka

import scala.concurrent.duration.DurationInt
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import akka.util.Timeout
import com.zlope.akka.config.Config
import com.zlope.akka.route.Routes

trait Services extends Config with Routes {

  implicit val system = ActorSystem("akka-services")
  implicit val executor = system.dispatcher
  implicit val materializer = ActorMaterializer()

  implicit val timeout = Timeout(10 seconds)

}

object Main extends App with Services {

  Http().bindAndHandle(routes, interface = httpInterface, port = httpPort)

}
