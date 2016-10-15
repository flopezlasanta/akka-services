package com.codebook.akka.route

import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{ExceptionHandler, Route}

trait ErrorHandler {
  implicit def myExceptionHandler = ExceptionHandler {
    case e: NoSuchElementException =>
      extractUri { uri =>
        complete(HttpResponse(NotFound, entity = s"Invalid id: ${e.getMessage}"))
      }
  }
}

trait Routes extends ErrorHandler with SysRoute with UserRoute with UrlRoute with StocksRoute {

  val index = "public/index.html"
  val root = ""
  val sys = "sys"
  val api = "api"

  def routes: Route =
    logRequestResult("akka-services") {
      path(root)(getFromResource(index)) ~
      pathPrefix(sys)(sysRoute) ~
      pathPrefix(api)(userRoute ~ urlRoute ~ stocksRoute)
    }
}
