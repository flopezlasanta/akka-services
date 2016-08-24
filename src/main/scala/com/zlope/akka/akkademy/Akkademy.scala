package com.zlope.akka.akkademy

import akka.actor.{Actor, ActorLogging, Props, Status}

import scala.collection.mutable.HashMap

// TODO move exception wrappers to a dedicated file outside
case class UnknownMessage(message: String) extends Exception(message)

// TODO move case classes for messages and exception wrappers to a project shared by client and server projects
case class SetMessage(key: String, value: Object)

object Akkademy {
  def props = Props(classOf[Akkademy])
}

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
      sender ! Status.Failure(UnknownMessage(unknown.toString))
    }
  }
}
