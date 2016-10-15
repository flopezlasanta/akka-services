package com.codebook.akka.util

import java.text.SimpleDateFormat
import java.util.Date

object Time {

  val sdf: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

  def timestamp: String = sdf.format(new Date)

}
