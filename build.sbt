name := "loggers-stress-test"

version := "0.1"

scalaVersion := "2.12.8"

organization in ThisBuild := "com.github.ratoshniuk.logging.stress"

val izumi_version = "0.6.34"

val logback_version = "1.2.3"

libraryDependencies ++= Seq(
  "ch.qos.logback" % "logback-classic" % logback_version,
  "com.github.pshirshov.izumi.r2" %% "logstage-core" % izumi_version
//  "com.github.pshirshov.izumi.r2" %% "logstage-sink-file" % izumi_version,
)
