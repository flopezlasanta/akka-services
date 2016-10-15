package com.codebook.akka

import akka.event.NoLogging
import akka.http.scaladsl.model.ContentTypes._
import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.testkit.ScalatestRouteTest
import akka.util.Timeout
import com.codebook.akka.model.User
import org.scalatest.{Matchers, WordSpec}

import scala.concurrent.duration.DurationInt

class ServicesSpec extends WordSpec with Matchers with ScalatestRouteTest with Services {

  implicit val timeout = Timeout(1 minute)
  implicit val log = NoLogging

  "User" should {
    s"return JSON for GET request to /api/user" in {
      Get(s"/api/user") ~> routes ~> check {
        status shouldBe OK
        contentType shouldBe `application/json`
        responseAs[User] shouldBe JohnDoe
      }
    }
    s"return String for POST request to /api/user" in {
      Post(s"/api/user", JohnDoe) ~> routes ~> check {
        status shouldBe OK
        contentType shouldBe `text/plain(UTF-8)`
        responseAs[String].length should be > 0
      }
    }
  }

}