package com.zlope.akka.akkademy

import akka.actor.{Actor, ActorLogging, Status}

import scala.collection.mutable.HashMap

case class SetMessage(key: String, value: Object)

class Akkademy extends Actor with ActorLogging {

  val map = new HashMap[String, Object]

  override def receive: Receive = {
    case SetMessage(key, value) => {
      log.info(s"received SetMessage($key, $value)")
      map.put(key, value)
      sender ! Status.Success(key)
    }
    case unknown => {
      log.error(s"received unknown message: $unknown")
      sender ! Status.Failure(new Exception(unknown.toString))
    }
  }
}
