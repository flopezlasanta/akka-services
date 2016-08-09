name := "akka-services"

version := "1.0"

organization := "com.zlope"

scalaVersion := "2.11.8"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8", "-language", "postfixOps")

resolvers ++= Seq("Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/")

libraryDependencies ++= {
  val akkaVersion       = "2.4.8"
  val logbackVersion    = "1.1.7"
  val scalaTestVersion  = "3.0.0"
  val scredisVersion    = "2.0.6"

  Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
    "com.typesafe.akka" %% "akka-http-core" % akkaVersion,
    "com.typesafe.akka" %% "akka-http-experimental" % akkaVersion,
    "com.typesafe.akka" %% "akka-http-spray-json-experimental" % akkaVersion,
    "com.typesafe.akka" %% "akka-http-xml-experimental" % akkaVersion,
    "com.typesafe.akka" %% "akka-http-testkit" % akkaVersion,
    "ch.qos.logback"    %  "logback-classic" % logbackVersion,
    "org.scalatest"     %% "scalatest" % scalaTestVersion % "test",
    "com.livestream"    %% "scredis" % scredisVersion
    )
}
