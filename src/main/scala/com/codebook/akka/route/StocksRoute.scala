package com.codebook.akka.route

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpMethods, HttpRequest}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server._
import akka.stream.{ActorMaterializer, ActorMaterializerSettings}
import com.codebook.akka.ActorEnvironment

trait StocksRoute extends ActorEnvironment {

  private val baseUrl = """https://www.quandl.com/api/v3/datasets/wiki/%s.json?limit=1&end_date=%s"""

  lazy val stocksRoute: Route = {
    path("stocks" / Segment){ company =>
      get{
        complete {
          getLatestPrice(company)
        }
      }
    }
  }

  private def getLatestPrice(company: String) = {
    val today = ZonedDateTime.now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    val url = baseUrl.format(company,today)

    // by returning the same request we made to an external resource, whatever is returned
    // by the service will be forwarded to users who use our api
    val request = HttpRequest(method = HttpMethods.GET, uri = url)
    Http().singleRequest(request)
  }

}