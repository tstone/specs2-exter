name := "specs2-exter"

scalaVersion := "2.11.2"

version := "1.0.0-pre"

organization := "org.specs2.exter"

organizationName := "Exter"

libraryDependencies ++= Seq(
  "org.specs2" %% "specs2" % "2.4.2"
)

testOptions in Test += Tests.Argument("notifier org.specs2.exter.notifier.Minimal")
