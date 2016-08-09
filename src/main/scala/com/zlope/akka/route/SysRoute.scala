package com.zlope.akka.route

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.zlope.akka.util.Time
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

case class Ping(timestamp: String)

trait SysRoute extends DefaultJsonProtocol with SprayJsonSupport {

  implicit val pingFormat: RootJsonFormat[Ping] = jsonFormat1(Ping)

  val sysRoute: Route =
    pathPrefix("ping")(complete(Ping(Time.timestamp)))
}
