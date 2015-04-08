enablePlugins(ScalaJSPlugin)

name := "Page-Transport"

version := "0.0.1"

scalaVersion := "2.11.6"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "0.8.0"

libraryDependencies += "com.lihaoyi" %%% "upickle" % "0.2.8"

libraryDependencies += "com.lihaoyi" %%% "utest" % "0.3.0" % "test"

testFrameworks += new TestFramework("utest.runner.Framework")

scalaJSStage in Global := FastOptStage

skip in packageJSDependencies := false

persistLauncher in Compile := true

persistLauncher in Test := false
