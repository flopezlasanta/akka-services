package com.codebook.akka.actor.message

case class UnknownMessage(message: String) extends Exception(message)
