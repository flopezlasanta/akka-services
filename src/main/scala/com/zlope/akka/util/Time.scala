package com.zlope.akka.util

import java.text.SimpleDateFormat
import java.util.Date

object Time {

  val sdf: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX")

  def timestamp: String = sdf.format(new Date)

}
