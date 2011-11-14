package com.nosyrom.scalabot

import org.jibble.pircbot.PircBot

abstract class Bot(hostname: String, name: String, val channel: String) extends PircBot {
  setName(name)
  setVerbose(true)
  setEncoding("UTF-8")
  connect(hostname)

  def handle(message: String): Option[String]

  override def onMessage(channel: String, sender: String, login: String, hostname: String, message: String) {
    val response = message match {
      case "opme" => {
        this.op(channel, sender)
        //Some("I just tried to secretly op %s. (hopefully my plan worked...)".format(sender))
        None
      }
      case message => handle(message)
    }
    response.map(sendMessage(channel, _))
  }

  override def onConnect() {
    onDisconnect()
  }

  override def onDisconnect() {
    while(!isConnected) {
      Thread.sleep(20000)
      reconnect()
    }
    joinChannel(channel)
  }
}