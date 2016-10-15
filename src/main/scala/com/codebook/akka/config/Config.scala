package com.codebook.akka.config

import com.typesafe.config.ConfigFactory

trait Config {

  lazy val config = ConfigFactory.load()

  // HTTP configuration
  val httpConfig = config.getConfig("http")
  val httpHost = httpConfig.getString("host")
  val httpPort = httpConfig.getInt("port")

}