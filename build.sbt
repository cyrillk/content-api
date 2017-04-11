name := """content-api"""

version := "1.0.1"

scalaVersion := "2.12.1"

libraryDependencies ++= Seq(
  "com.typesafe" % "config" % "1.3.1",
  "com.amazonaws" % "aws-java-sdk-s3" % "1.11.118",
  "ch.qos.logback" % "logback-classic" % "1.1.7",
  "org.scalaz" %% "scalaz-core" % "7.2.10",
  "org.json4s" %% "json4s-jackson" % "3.5.0",
  "com.typesafe.akka" %% "akka-stream" % "2.4.17",
  "com.typesafe.akka" %% "akka-stream-testkit" % "2.4.17" % "test",
  "com.typesafe.akka" %% "akka-http" % "10.0.5",
  "com.typesafe.akka" %% "akka-http-testkit" % "10.0.5" % "test",
  "org.scalatest" %% "scalatest" % "3.0.1" % "test"
)

scalacOptions ++= Seq("-feature", "-unchecked", "-deprecation", "-encoding", "utf8")

mainClass in assembly := Some("org.efset.Entrypoint")

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", _*) => MergeStrategy.discard
  case "application.conf" => MergeStrategy.concat
  case "reference.conf" => MergeStrategy.concat
  case "version.conf" => MergeStrategy.concat
  case _ => MergeStrategy.first
}