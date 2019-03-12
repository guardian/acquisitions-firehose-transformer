package com.gu.acquisitionFirehoseTransformer

import java.text.SimpleDateFormat
import java.util.Date

import io.circe.Json
import io.circe.generic.auto._
import io.circe.syntax._
import ophan.thrift.event.Acquisition

object AcquisitionToJson {
  private val formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

  private case class AcquisitionOutput(
    payment_frequency: String,
    country_code: String,
    amount: Double,
    currency: String,
    timestamp: String,
    componentId: String
  )

  def apply(acquisition: Acquisition, timestamp: Long): Json =
    AcquisitionOutput(
      acquisition.paymentFrequency.name,
      acquisition.countryCode.getOrElse(""),
      acquisition.amount,
      acquisition.currency,
      formatter.format(new Date(timestamp)),
      acquisition.componentId.getOrElse("")
    ).asJson
}
