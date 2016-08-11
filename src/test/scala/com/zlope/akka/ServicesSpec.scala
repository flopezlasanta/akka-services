package com.zlope.akka

import akka.event.NoLogging
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.testkit.ScalatestRouteTest
import akka.util.Timeout
import org.scalatest.{Matchers, WordSpec}

import scala.concurrent.duration.DurationInt

class ServicesSpec extends WordSpec with Matchers with ScalatestRouteTest with Services {

  implicit val timeout = Timeout(1 minute)
  implicit val log = NoLogging

  "User" should {
    "return John Doe for GET request to /user" in {
      Get() ~> routes ~> check {
        status shouldBe StatusCodes.OK
      }
    }
  }

}