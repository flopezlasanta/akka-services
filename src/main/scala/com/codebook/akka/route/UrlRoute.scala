package com.codebook.akka.route

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.codebook.akka.config.Config
import scredis.Redis

import scala.concurrent.Future
import scala.util.{Failure, Random, Success}

// simple URL shortener
trait UrlRoute extends Config {

  implicit val redis = Redis()

  lazy val urlRoute: Route =
    pathPrefix("url") {
      (get & path(Segment)) {
        url => {
          val f: Future[Option[String]] = redis.get[String](url)
          onComplete(f) {
            case Success(option) => option match {
              // TODO instead of doing this code should redirect to the new URL and perhaps return MovedPermanently
              case Some(result) => complete(StatusCodes.Found -> result)
              case None => complete(StatusCodes.NotFound -> s"$url not registered")
            }
            case Failure(e) => complete(StatusCodes.InternalServerError -> s"$e (requested: $url)")
          }
        }
      } ~ post {
        entity(as[String]) { url => {
          // TODO add URL validator; in case no valid URL then return BAD_REQUEST
          val random = Random.alphanumeric.take(7).mkString
          redis.set(url, random)
          complete(StatusCodes.Accepted -> s"$httpHost:$httpPort/$random") // no need to wait for redis thus better Accepted
        }
        }
      }
    }
}