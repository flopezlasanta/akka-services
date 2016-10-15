package com.codebook.akka.route

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.codebook.akka.model.SimpleMessage
import com.codebook.akka.util.Time
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

// simple system operations
trait SysRoute extends DefaultJsonProtocol with SprayJsonSupport {

  implicit val simpleMessageFormat: RootJsonFormat[SimpleMessage] = jsonFormat1(SimpleMessage)

  lazy val sysRoute: Route =
    path("health")(complete(StatusCodes.OK -> SimpleMessage("Everything is OK"))) ~
    pathPrefix("ping")(complete(StatusCodes.OK -> SimpleMessage(Time.timestamp)))
}
