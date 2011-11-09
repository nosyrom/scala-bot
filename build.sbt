name := "Scala Bot"

version := "1.0"

scalaVersion := "2.9.1"

libraryDependencies += "junit" % "junit" % "4.8" % "test"

libraryDependencies += "pircbot" % "pircbot" % "1.4.2" % "compile"

libraryDependencies <+= scalaVersion("org.scala-lang" % "scala-compiler" % _ )
