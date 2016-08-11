package com.zlope.akka

import akka.event.NoLogging
import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.model.ContentTypes._
import akka.http.scaladsl.testkit.ScalatestRouteTest
import akka.util.Timeout
import com.zlope.akka.model.User
import org.scalatest.{Matchers, WordSpec}

import scala.concurrent.duration.DurationInt

class ServicesSpec extends WordSpec with Matchers with ScalatestRouteTest with Services {

  implicit val timeout = Timeout(1 minute)
  implicit val log = NoLogging

  "User" should {
    s"return JSON for GET request to /$v1/$userSegment" in {
      Get(s"/$v1/$userSegment") ~> routes ~> check {
        status shouldBe OK
        contentType shouldBe `application/json`
        responseAs[User] shouldBe johnDoe
      }
    }
    s"return String for POST request to /$v1/$userSegment" in {
      Post(s"/$v1/$userSegment", johnDoe) ~> routes ~> check {
        status shouldBe OK
        contentType shouldBe `text/plain(UTF-8)`
        responseAs[String].length should be > 0
      }
    }
  }

}