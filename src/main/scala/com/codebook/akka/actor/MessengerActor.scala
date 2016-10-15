package com.codebook.akka.actor

import akka.actor.{Actor, ActorLogging, Props, Status}
import com.codebook.akka.actor.message.{SetMessage, UnknownMessage}

import scala.collection.mutable.HashMap

object MessengerActor {
  def props = Props(classOf[MessengerActor])
}

// NOTE: not currently integrated with routes
class MessengerActor extends Actor with ActorLogging {

  val map = new HashMap[String, Object]

  override def receive: Receive = {
    case SetMessage(key, value) => {
      log.info(s"received SetMessage($key, $value)")
      map.put(key, value)
      sender ! Status.Success(key)
    }
    case unknown => {
      log.error(s"received unknown message: $unknown")
      sender ! Status.Failure(UnknownMessage(unknown.toString))
    }
  }
}
