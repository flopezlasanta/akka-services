package com.zlope.akka.akkademy

import akka.actor.ActorSystem
import akka.testkit.TestActorRef
import com.zlope.akka.akkademy.message.SetMessage
import org.scalatest.{BeforeAndAfterEach, FunSpecLike, Matchers}

class AkkademySpec extends FunSpecLike with Matchers with BeforeAndAfterEach {
  implicit val system = ActorSystem()

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
    }
  }
}
