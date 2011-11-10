package com.nosyrom.scalabot

import java.io.Writer

class ChannelWriter(bot: Bot) extends Writer {

  def write(cbuf: Array[Char], p2: Int, p3: Int) {
    bot.sendMessage(bot.channel, cbuf.subSequence(p2, p3).toString)
  }

  def flush() {}

  def close() {}
}