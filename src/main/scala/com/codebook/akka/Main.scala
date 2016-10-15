package com.codebook.akka

import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import akka.util.Timeout
import com.codebook.akka.config.Config
import com.codebook.akka.route.Routes

import scala.concurrent.ExecutionContextExecutor
import scala.concurrent.duration.DurationInt

trait ActorEnvironment {

  implicit val system: ActorSystem
  implicit def executor: ExecutionContextExecutor
  implicit val materializer: ActorMaterializer

}

trait Services extends Config with ActorEnvironment with Routes {

}

object Main extends App with Services {

  override implicit val system = ActorSystem("akka-services")
  override implicit val executor = system.dispatcher
  override implicit val materializer = ActorMaterializer()

  implicit val timeout = Timeout(10 seconds)
  implicit val log = Logging(system, getClass)

  log.info("Starting Akka-Services...")
  Http().bindAndHandle(routes, interface = httpHost, port = httpPort)

}
