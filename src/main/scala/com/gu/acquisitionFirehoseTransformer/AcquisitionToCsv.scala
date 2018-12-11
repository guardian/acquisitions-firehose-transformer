package com.gu.acquisitionFirehoseTransformer

import java.text.SimpleDateFormat
import java.util.Date

import ophan.thrift.event.Acquisition

object AcquisitionToCsv {
  private val formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

  def apply(acquisition: Acquisition, timestamp: Long): String = {
    val frequency = acquisition.paymentFrequency
    val countryCode = acquisition.countryCode.getOrElse("")
    val amount = acquisition.amount
    val currency = acquisition.currency
    val dateTime = formatter.format(new Date(timestamp))

    s"$frequency,$countryCode,$amount,$currency,$dateTime\n"
  }
}
