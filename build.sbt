name := "generic_dao"

version := "0.1"

scalaVersion := "2.12.14"

lazy val scalaReflect = Def.setting { "org.scala-lang" % "scala-reflect" % scalaVersion.value }

lazy val core = (project in file("core")).settings(libraryDependencies += scalaReflect.value)
lazy val legacy = project in file("legacy")
lazy val mongoIntegration = (project in file("mongo")).dependsOn(core, legacy)
lazy val example = (project in file("example")).dependsOn(core, legacy, mongoIntegration)
