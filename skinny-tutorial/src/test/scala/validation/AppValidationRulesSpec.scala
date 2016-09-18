package validation

import org.scalatest.{ FunSpec, Matchers }
import validation.AppValidationRules._

class AppValidationRulesSpec extends FunSpec with Matchers {

  describe("byteValue") {
    it("validates") {
      byteValue.isValid(Byte.MinValue - 1) should equal(false)
      byteValue.isValid(Byte.MinValue) should equal(true)

      "abc".getBytes.foreach { b =>
        byteValue.isValid(b) should equal(true)
      }

      byteValue.isValid(Byte.MaxValue) should equal(true)
      byteValue.isValid(Byte.MaxValue + 1) should equal(false)
    }
  }

  describe("shortValue") {
    it("validates") {
      shortValue.isValid("123") should equal(true)
      shortValue.isValid("-1") should equal(true)
      shortValue.isValid("1.0") should equal(false)
      shortValue.isValid("abc") should equal(false)
      shortValue.isValid(Int.MaxValue.toString) should equal(false)
      shortValue.isValid(Long.MaxValue.toString) should equal(false)
    }
  }

  describe("minByteLength") {
    it("validates") {
      minByteLength(3).isValid(null) should equal(true)
      minByteLength(3).isValid(1) should equal(false)
      minByteLength(3).isValid(12) should equal(false)
      minByteLength(3).isValid(123) should equal(true)
      minByteLength(3).isValid("hello") should equal(true)
    }
  }

  describe("maxByteLength") {
    it("validates") {
      maxByteLength(3).isValid(null) should equal(true)
      maxByteLength(3).isValid(1) should equal(true)
      maxByteLength(3).isValid(12) should equal(true)
      maxByteLength(3).isValid(123) should equal(true)
      maxByteLength(3).isValid("hello") should equal(false)
    }
  }

  describe("urlValue") {
    it("validates") {
      urlValue.isValid("080-1111-2222") should equal(false)
      urlValue.isValid("www.example.com/foo") should equal(false)
      urlValue.isValid("http://www.example.com/foo") should equal(true)
      urlValue.isValid("http://www.example.com/foo?bar=baz#top") should equal(true)
      urlValue.isValid("https://www.example.com/foo") should equal(true)
    }
  }

  describe("creditCardNumber") {
    it("validates") {
      creditCardNumber.isValid("111122223333") should equal(false)
      creditCardNumber.isValid("1111222233334444") should equal(true)
      creditCardNumber.isValid("1111222233334a") should equal(false)
      creditCardNumber.isValid("11112222333344445") should equal(false)
    }
  }

  describe("phoneNumber") {
    it("validates") {
      phoneNumber.isValid("03-1111-2222") should equal(true)
      phoneNumber.isValid("080-1111-2222") should equal(true)
      phoneNumber.isValid("08011112222") should equal(false)
    }
  }

}
