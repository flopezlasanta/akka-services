package com.zlope.akka

import akka.http.scaladsl.model.ContentTypes._
import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.testkit.ScalatestRouteTest
import com.zlope.akka.model.User
import org.scalatest._

class ServicesSpec extends FlatSpec with Matchers with ScalatestRouteTest with Services {

  "Service" should "respond to query" in {
    Get(s"/user}") ~> routes ~> check {
      status shouldBe OK
      contentType shouldBe `application/json`
      responseAs[User] shouldBe jdoe
    }
  }

}