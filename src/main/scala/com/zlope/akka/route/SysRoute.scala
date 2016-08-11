package com.zlope.akka.route

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.zlope.akka.model.Ping
import com.zlope.akka.util.Time
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

trait SysRoute extends DefaultJsonProtocol with SprayJsonSupport {

  implicit val pingFormat: RootJsonFormat[Ping] = jsonFormat1(Ping)

  val pingSegment = "ping"

  val sysRoute: Route =
    pathPrefix(pingSegment)(complete(StatusCodes.OK -> Ping(Time.timestamp)))
}
