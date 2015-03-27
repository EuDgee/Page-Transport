enablePlugins(ScalaJSPlugin)

name := "Tab-Transport"

version := "0.0.1"

scalaVersion := "2.11.6"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "0.8.0"

libraryDependencies += "be.doeraene" %%% "scalajs-jquery" % "0.8.0"

libraryDependencies += "com.lihaoyi" %%% "utest" % "0.3.0" % "test"

testFrameworks += new TestFramework("utest.runner.Framework")

scalaJSStage in Global := FastOptStage

skip in packageJSDependencies := false

jsDependencies += RuntimeDOM

persistLauncher in Compile := true

persistLauncher in Test := false
