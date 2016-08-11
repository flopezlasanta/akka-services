package com.zlope.akka.route

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.zlope.akka.model.User
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

trait UserRoute extends DefaultJsonProtocol with SprayJsonSupport {

  implicit val userFormat: RootJsonFormat[User] = jsonFormat4(User)

  val jdoeUser = User("jdoe", "John", "Doe", 40)

  val userRoute: Route =
    pathPrefix("user") {
      pathEnd(
        get { complete(StatusCodes.OK, jdoeUser) } ~
        post(entity(as[User]) { user ⇒ complete(StatusCodes.OK, user.toString) }))
    }

}