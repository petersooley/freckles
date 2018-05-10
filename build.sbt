scalaVersion in ThisBuild := "2.12.4"
organization in ThisBuild := "com.elmdash.freckles"
version in ThisBuild := "0.1.0-SNAPSHOT"

// using docker containers
lagomKafkaEnabled in ThisBuild := false
lagomKafkaAddress in ThisBuild := "172.19.0.4:9092"
lagomCassandraEnabled in ThisBuild := false
lagomUnmanagedServices in ThisBuild := Map("cas_native" -> "http://172.19.0.4:9042")

lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.5"
lazy val macwire = "com.softwaremill.macwire" %% "macros" % "2.3.0" % "provided"


lazy val `freckles` = (project in file("."))
  .aggregate(`contacts-api`, `contacts-impl`)

lazy val `contacts-api` = (project in file("contacts-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )

lazy val `contacts-impl` = (project in file("contacts-impl"))
  .enablePlugins(LagomScala)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslPersistenceCassandra,
      lagomScaladslKafkaBroker,
      lagomScaladslTestKit,
      macwire,
      scalaTest
    )
  )
  .settings(lagomForkedTestSettings: _*)
  .dependsOn(`contacts-api`)
