name := """play-doobie"""

//organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.8"

libraryDependencies += filters
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test

val doobieVersion = "0.4.1"

libraryDependencies ++= Seq(
  "org.tpolecat" %% "doobie-core-cats",
  "org.tpolecat" %% "doobie-postgres-cats",
  "org.tpolecat" %% "doobie-scalatest-cats" ) map ( _ % doobieVersion ) 

//libraryDependencies += "org.tpolecat" %% "doobie-h2-cats" % doobieVersion % Test

val circeVersion = "0.6.1"

libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser") map ( _ % circeVersion ) 
// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.example.controllers._"

//scalacOptions += "-Ylog-classpath"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.example.binders._"
