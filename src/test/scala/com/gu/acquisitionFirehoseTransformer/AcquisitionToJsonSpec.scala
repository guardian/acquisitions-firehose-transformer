package com.gu.acquisitionFirehoseTransformer

import org.scalatest.{FlatSpec, Matchers}
import ophan.thrift.event._

class AcquisitionToJsonSpec extends FlatSpec with Matchers {
  it should "serialise an event to json" in {
    val event = Acquisition(
      product = ophan.thrift.event.Product.RecurringContribution,
      paymentFrequency = PaymentFrequency.Monthly,
      currency = "GBP",
      amount = 20d,
      paymentProvider = Some(PaymentProvider.Stripe),
      campaignCode = Some(Set("FAKE_ACQUISITION_EVENT")),
      abTests = Some(AbTestInfo(Set(AbTest("test_name", "variant_name"), AbTest("second_test", "control")))),
      countryCode = Some("US"),
      referrerPageViewId = None,
      referrerUrl = None,
      componentId = None,
      componentTypeV2 = None,
      source = None
    )

    val jsonString = AcquisitionToJson(event, 1544710504165L).noSpaces
    jsonString should be """{"frequency":"Monthly","countryCode":"US","amount":20.0,"currency":"GBP","dateTime":"2018-12-13 14:15:04"}"""
  }
}
