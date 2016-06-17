name := "provably-fair-scala"

organization := "com.heathmont.provablyfair"

version := "1.0.0-SNAPSHOT"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "com.roundeights" %% "hasher" % "1.2.0",
  "org.scalatest" %% "scalatest" % "2.2.6" % "test")

scalacOptions in (Compile,doc) := Seq("-groups", "-implicits")