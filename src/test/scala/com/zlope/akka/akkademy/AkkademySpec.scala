package com.zlope.akka.akkademy

import akka.actor.ActorSystem
import akka.pattern.ask
import akka.testkit.TestActorRef
import akka.util.Timeout
import org.scalatest.{FunSpecLike, Matchers}

import scala.concurrent.duration._
import scala.util.{Failure, Success}

// Synchronous Unit Testing with TestActorRef
class AkkademySpec extends FunSpecLike with Matchers {

  implicit val system = ActorSystem()
  implicit val timeout = Timeout(5 seconds)

  describe("Akkademy") {
    describe("given SetMessage") {
      it("should place key/value into map") {
        val actorRef = TestActorRef(new Akkademy)
        actorRef ! SetMessage("key", "value")

        val akkademy = actorRef.underlyingActor
        akkademy.map.get("key") should equal(Some("value"))
      }
      it("should keep only the last value entered for a certain key") {
        val actorRef = TestActorRef(new Akkademy)
        actorRef ! SetMessage("key", "value1")
        actorRef ! SetMessage("key", "value2")

        val akkademy = actorRef.underlyingActor
        akkademy.map.get("key") should equal(Some("value2"))
      }
      it("should return the key received") {
        val actorRef = TestActorRef(new Akkademy)
        val future = actorRef ? SetMessage("key", "value")
        val Success(key: String) = future.value.get
        key should be("key")
      }
      it("should return the object received if unknown") {
        val actorRef = TestActorRef(new Akkademy)
        val future = actorRef ? "unknown"
        val Failure(message: UnknownMessage) = future.value.get
        message.getMessage should be("unknown")
      }
    }
  }
}
