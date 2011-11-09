package com.nosyrom.scalabot

import java.io.PrintWriter
import tools.nsc.interpreter.IMain
import tools.nsc.GenericRunnerSettings

class ScalaBot(hostname: String, name: String, channel: String) extends Bot(hostname, name) {
  val out = new PrintWriter(new ChannelWriter(this, channel))
  val settings = new GenericRunnerSettings(out.println _)
  settings.usejavacp.value = true;
  val interpreter = new IMain(settings, out)

  joinChannel("#scala")

  def handle(msg: String) : Option[String] = {
    interpreter.interpret(msg)
    None
  }
}