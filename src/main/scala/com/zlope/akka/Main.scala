package com.zlope.akka

import akka.actor.ActorSystem
import akka.event.{Logging, LoggingAdapter}
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import akka.util.Timeout
import com.zlope.akka.config.Config
import com.zlope.akka.route.Routes

import scala.concurrent.ExecutionContextExecutor
import scala.concurrent.duration.DurationInt

trait Services extends Config with Routes {

  implicit val system: ActorSystem
  implicit def executor: ExecutionContextExecutor
  implicit val materializer: ActorMaterializer

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
