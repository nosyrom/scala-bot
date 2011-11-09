package com.nosyrom.scalabot

import org.jibble.pircbot.PircBot

abstract class Bot(hostname: String, name: String) extends PircBot {
  
  lazy val NameExtractor = (name + ":(.*)").r

  setName(name)
  setVerbose(true)
  setEncoding("UTF-8")
  connect(hostname)

  def handle(msg: String): Option[String]

  override def onMessage(channel: String, sender: String, login: String, hostname: String, message: String) {
    val response = message match {
      case NameExtractor(theRest) => handle(theRest)
      case _ => None// "Message was ignored" + message // Ignore everything else
    }
    response.map(sendMessage(channel, _))
  }
}