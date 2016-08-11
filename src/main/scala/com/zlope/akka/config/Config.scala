package com.zlope.akka.config

import com.typesafe.config.ConfigFactory

trait Config {

  val config = ConfigFactory.load()
  val httpConfig = config.getConfig("http")
  val httpHost = httpConfig.getString("host")
  val httpPort = httpConfig.getInt("port")

}