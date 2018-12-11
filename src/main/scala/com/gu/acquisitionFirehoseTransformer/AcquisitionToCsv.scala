package com.gu.acquisitionFirehoseTransformer

import java.text.SimpleDateFormat
import java.util.Date

import ophan.thrift.event.Acquisition

object AcquisitionToCsv {
  private val formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

  def apply(acquisition: Acquisition, timestamp: Long): String = {
    val dateTime = formatter.format(new Date(timestamp))
    s"${acquisition.paymentFrequency},${acquisition.countryCode.getOrElse("")},${acquisition.amount},${acquisition.currency},$dateTime"
  }
}
