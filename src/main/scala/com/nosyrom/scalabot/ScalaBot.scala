package com.nosyrom.scalabot

import java.io.PrintWriter
import tools.nsc.interpreter.IMain
import tools.nsc.GenericRunnerSettings

class ScalaBot(hostname: String) extends Bot(hostname, "scalabot", "#scala") {
  
  val channelOut = new PrintWriter(new ChannelWriter(this))
  val settings = new GenericRunnerSettings(channelOut.println _)
  settings.usejavacp.value = true;
  val interpreter = new IMain(settings, channelOut)
  
  val InterpreterCommand = ">(.*)".r
  val NameCommand = "scalabot[,:]?(.*)".r
  
  def handle(message: String) : Option[String] = message match {
    case InterpreterCommand(scalaCode) => {
      interpreter.interpret(scalaCode)
      None // The ChannelWriter handles output to channel
    }
    case NameCommand(message) =>
      Some("Hi there! Try starting a message with '>' and I'll try to interpret it for you")
    case x => Some("Dunno")
  }
}

object ScalaBot extends ScalaBot("localhost") with App