package com.nosyrom.scalabot

import java.io.PrintWriter
import tools.nsc.interpreter.IMain
import tools.nsc.util.ClassPath
import tools.nsc.Settings
import java.net.URLClassLoader

class ScalaBot(hostname: String) extends Bot(hostname, "scalabot", "#scala") {
  val interpreter = createInterpreter
  
  val InterpreterCommand = ">(.*)".r
  val NameCommand = "scalabot[,:]?(.*)".r
  
  def handle(message: String) : Option[String] = message match {
    case InterpreterCommand(scalaCode) => {
      interpreter.interpret(scalaCode)
      None // The ChannelWriter handles output to channel
    }
    case NameCommand(message) =>
      Some("Hi there! Try starting a message with '>' and I'll try to interpret it for you.")
    case x => None // Some("I dunno what %s means".format(x))
  }

  // Create an interpreter using a configuration that plays nicely with sbt.
  def createInterpreter = {
    val settings = new Settings
    val loader = getClass.getClassLoader.asInstanceOf[URLClassLoader]
    val entries = loader.getURLs map(_.getPath)
    val sclpath = entries find(_.endsWith("scala-compiler.jar")) map(
      _.replaceAll("scala-compiler.jar", "scala-library.jar"))
    settings.classpath.value = ClassPath.join((entries ++ sclpath) : _*) 

    new IMain(settings, new PrintWriter(new ChannelWriter(this)))
  }
}

object ScalaBot extends ScalaBot("localhost") with App
