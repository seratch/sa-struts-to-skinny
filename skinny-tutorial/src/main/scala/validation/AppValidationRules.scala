package validation

import java.net.URL

import skinny.validator.ValidationRule

import scala.util.control.NonFatal

object AppValidationRules {

  object byteValue extends ValidationRule {
    def name = "byteValue"
    def isValid(v: Any) = isEmpty(v) || {
      try {
        v.toString.toByte
        true
      } catch { case NonFatal(e) => false }
    }
  }

  object shortValue extends ValidationRule {
    def name = "shortValue"
    def isValid(v: Any) = isEmpty(v) || {
      try {
        v.toString.toShort
        true
      } catch { case NonFatal(e) => false }
    }
  }

  object creditCardNumber extends ValidationRule {
    def name = "creditCardNumber"
    def isValid(v: Any) = isEmpty(v) || v.toString.matches("\\d{13,16}")
  }

  object urlValue extends ValidationRule {
    def name = "urlValue"
    def isValid(v: Any) = isEmpty(v) || {
      try {
        new URL(v.toString)
        true
      } catch { case NonFatal(e) => false }
    }
  }

  case class minByteLength(minLength: Int) extends ValidationRule {
    def name = "minByteLength"
    def isValid(v: Any) = isEmpty(v) || {
      v.toString.getBytes.length >= minLength
    }
  }

  case class maxByteLength(maxLength: Int) extends ValidationRule {
    def name = "maxByteLength"
    def isValid(v: Any) = isEmpty(v) || {
      v.toString.getBytes.length <= maxLength
    }
  }

  object phoneNumber extends ValidationRule {
    def name = "phoneNumber"
    def isValid(v: Any) = isEmpty(v) || v.toString.matches("\\d+-\\d+-\\d+")
  }

}
