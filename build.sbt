name := "IMDB_APIs"

version := "0.1"

scalaVersion := "2.13.11"




libraryDependencies ++= Seq(

  "io.circe" %% "circe-core" % "0.14.5",
  "io.circe" %% "circe-generic" % "0.14.5",
  "org.typelevel" %% "cats-effect" % "3.6-1f95fd7",
  "org.scalatest" %% "scalatest" % "3.3.0-SNAP3" % Test,
  "org.scalatestplus" %% "scalatestplus-mockito" % "1.0.0-M2" % Test,
  "com.softwaremill.sttp.client3" %% "circe"                          % "3.1.7",
  "com.softwaremill.sttp.client3" %% "core"                           % "3.1.7",
  "com.softwaremill.sttp.client3" %% "json-common"                    % "3.1.7",
  "com.softwaremill.sttp.tapir"  %% "tapir-core"                     % "0.19.0-M5",
  "com.softwaremill.sttp.tapir"  %% "tapir-http4s-server"            % "0.19.0-M5",
  "com.softwaremill.sttp.tapir"  %% "tapir-json-circe"               % "0.19.0-M5",
  "com.softwaremill.sttp.tapir"  %% "tapir-openapi-circe-yaml"       % "0.19.0-M5",
  "com.softwaremill.sttp.tapir"  %% "tapir-openapi-docs"             % "0.19.0-M5",
  "com.softwaremill.sttp.tapir"  %% "tapir-openapi-model"            % "0.19.0-M5",
  "com.softwaremill.sttp.tapir"  %% "tapir-refined"                  % "0.19.0-M5",
  "co.fs2" %% "fs2-core" % "3.7.0-RC5",
  "co.fs2" %% "fs2-io" % "3.7.0-RC5",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.4"

)