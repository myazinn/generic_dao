name := "generic_dao"

version := "0.1"

scalaVersion := "2.12.14"

lazy val core = project in file("core")
lazy val legacy = project in file("legacy")
lazy val mongoIntegration = (project in file("mongo")).dependsOn(core, legacy)
lazy val example = (project in file("example")).dependsOn(core, legacy, mongoIntegration)
