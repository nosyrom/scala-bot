package com.nosyrom.scalabot

import org.jibble.pircbot.PircBot

abstract class Bot(hostname: String, name: String, val channel: String) extends PircBot {
  lazy val CommandExtractor = """(\S*)[,:](.*)""".r

  setName(name)
  setVerbose(true)
  setEncoding("UTF-8")
  connect(hostname)
  joinChannel(channel)

  def handle(command: String, argument: String): Option[String]

  override def onMessage(channel: String, sender: String, login: String, hostname: String, message: String) {
    val response = message match {
      case CommandExtractor(command, argument) => handle(command, argument)
      case "opme" => {
        this.op(channel, sender)
        None
      }
      case _ => None// "Message was ignored" + message // Ignore everything else
    }
    response.map(sendMessage(channel, _))
  }

  override def onDisconnect() {
    while(!isConnected) {
      Thread.sleep(20000)
      reconnect()
    }
    joinChannel(channel)
  }
}