package com.zlope.akka.akkademy

import akka.actor.{Actor, ActorLogging}
import com.zlope.akka.akkademy.message.SetMessage

import scala.collection.mutable.HashMap

class Akkademy extends Actor with ActorLogging {

  val map = new HashMap[String, Object]

  override def receive: Receive = {
    case SetMessage(key, value) => {
      log.info(s"received SetMessage($key, $value)")
      map.put(key, value)
    }
    case unknown => log.error(s"received unknown message: $unknown")
  }
}
