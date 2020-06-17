name := "Plotter"

version := "0.1"

scalaVersion := "2.13.2"

libraryDependencies ++= Seq(
  "org.scalactic" %% "scalactic" % "3.0.8",
  "org.scalatest" %% "scalatest" % "3.0.8" % "test",
  "org.scalacheck" %% "scalacheck" % "1.14.1" % "test"
)

lazy val cleanArchitecture = RootProject(uri("https://github.com/nnnnusui/CleanArchitecture.git#master"))
lazy val root = project.in(sbt.file(".")).dependsOn(cleanArchitecture)
