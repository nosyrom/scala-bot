package com.nosyrom.scalabot

import java.io.PrintWriter
import tools.nsc.interpreter.IMain
import tools.nsc.GenericRunnerSettings

class ScalaBot(hostname: String) extends Bot(hostname, "scalabot", "#scala") {
  
  val channelOut = new PrintWriter(new ChannelWriter(this))
  val settings = new GenericRunnerSettings(channelOut.println _)
  settings.usejavacp.value = true;
  val interpreter = new IMain(settings, channelOut)

  def handle(command: String, msg: String) : Option[String] = command match {
    case ">" => {
      interpreter.interpret(msg)
      None
    }
    case "scalabot" => {
      Some("Hi there! Try starting a message with '>' and I'll try to interpret it for you")
    }
    case x => None
  }
}

object ScalaBot extends ScalaBot("localhost") with App