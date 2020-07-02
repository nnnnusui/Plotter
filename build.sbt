name := "Plotter"

version := "0.1"

scalaVersion := "2.13.2"

libraryDependencies ++= Seq(
  "org.scalactic" %% "scalactic" % "3.0.8",
  "org.scalatest" %% "scalatest" % "3.0.8" % "test",
  "org.scalacheck" %% "scalacheck" % "1.14.1" % "test"
)

// Akka-Http
libraryDependencies += "com.typesafe.akka" %% "akka-stream" % "2.5.26"
libraryDependencies += "com.typesafe.akka" %% "akka-http" % "10.1.12"
libraryDependencies += "com.typesafe.akka" %% "akka-http-xml" % "10.1.12"
