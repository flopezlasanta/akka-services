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
    "return John Doe for GET request to /user" in {
      Get(s"/$v1/$userSegment") ~> routes ~> check {
        status shouldBe OK
        contentType shouldBe `application/json`
        responseAs[User] shouldBe johnDoe
      }
    }
  }

}