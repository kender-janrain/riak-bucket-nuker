import sbt._
import sbt.Keys._

object NukerBuild extends Build {

  lazy val nuker = Project(
    id = "nuker",
    base = file("."),
    settings = Project.defaultSettings ++ Seq(
      name := "nuker",
      organization := "com.janrain.a2",
      version := "0.1-SNAPSHOT",
      scalaVersion := "2.10.1",
      libraryDependencies ++= Seq(
        "com.scalapenos" %% "riak-scala-client" % "0.9-SNAPSHOT"
      )
    )
  )
}
