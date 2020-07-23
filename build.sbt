ThisBuild / scalaVersion     := "2.13.2"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "io.adamnfish"
ThisBuild / organizationName := "adamnfish"


lazy val root = (project in file("."))
  .settings(
    name := "root"
  )

lazy val demo = (project in file("demo"))
  .settings(
    name := "demo",
    libraryDependencies ++= Seq(
      "com.softwaremill.sttp.client" %% "async-http-client-backend-future" % "2.2.2",
    )
  )

lazy val slowServer = (project in file("slow-server"))
  .settings(
    name := "slowServer",
    libraryDependencies ++= Seq(
      "io.javalin" % "javalin" % "3.6.0",
      "org.slf4j" % "slf4j-simple" % "1.8.0-beta4",
      "org.slf4j" % "slf4j-api" % "1.8.0-beta4",
    ),
    fork in run := true,
    connectInput in run := true,
    outputStrategy := Some(StdoutOutput),
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
