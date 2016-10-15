lazy val root = (project in file(".")).enablePlugins(JavaAppPackaging)

name := "akka-services"

version := "1.0"

organization := "flopezlasanta"

scalaVersion := "2.11.8"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8", "-language", "postfixOps")

packageName in Docker := "akka-services"

maintainer in Docker := "flopezlasanta@gmail.com"

dockerExposedPorts := Seq(9000)

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

unmanagedResourceDirectories in Compile += {
  baseDirectory.value / "src/main/resources"
}