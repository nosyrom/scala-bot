package com.nosyrom.scalabot

import java.io.Writer
import org.jibble.pircbot.PircBot

class ChannelWriter(bot: PircBot, channel: String) extends Writer {
  def write(cbuf: Array[Char], p2: Int, p3: Int) {
    val line = new String(cbuf)
    bot.sendMessage(channel, line.replaceAll("^res0: ", ""))
  }

  def flush() {}

  def close() {}
}