package com.codebook.akka.actor

import akka.actor.{ActorSystem, Status}
import akka.testkit.{ImplicitSender, TestActors, TestKit}
import akka.util.Timeout
import com.codebook.akka.actor.message.{SetMessage, UnknownMessage}
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

import scala.concurrent.duration._

// Asynchronous Unit Testing with TestKit
class MessengerAsyncSpec extends TestKit(ActorSystem("Messenger")) with ImplicitSender
  with WordSpecLike with Matchers with BeforeAndAfterAll {

  implicit val timeout = Timeout(1 second)

  override def afterAll = TestKit.shutdownActorSystem(system)

  "Messenger" must {
    "return Success with the key received" in {
      val actor = system.actorOf(MessengerActor.props)
      actor ! SetMessage("key", "value")
      expectMsg(Status.Success("key"))
    }
    "return Failure with the object received if unknown" in {
      val actor = system.actorOf(MessengerActor.props)
      actor ! "unknown"
      expectMsg(Status.Failure(UnknownMessage("unknown")))
    }
  }

}
