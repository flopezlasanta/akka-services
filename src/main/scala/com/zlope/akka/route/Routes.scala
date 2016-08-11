package com.zlope.akka.route

import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{ExceptionHandler, Route}

trait ErrorHandler {
  implicit def myExceptionHandler: ExceptionHandler = ExceptionHandler {
    case e: NoSuchElementException =>
      extractUri { uri =>
        complete(HttpResponse(NotFound, entity = s"Invalid id: ${e.getMessage}"))
      }
  }
}

trait Routes extends ErrorHandler with SysRoute with UserRoute with UrlRoute {

  val index = "public/index.html"
  val v1 = "v1"

  val routes: Route =
    logRequestResult("akka-services") {
      pathPrefix(v1)(sysRoute ~ userRoute ~ urlRoute) ~ path("")(getFromResource(index))
    }
}
