package controller.tutorial

import skinny._
import skinny.validator._
import _root_.validation.AppValidationRules._
import skinny.controller.feature.RequestScopeFeature.ATTR_ERROR_MESSAGES

class ValidatorController extends SAStrutsTutorialController {
  protectFromForgery()

  beforeAction(only = Seq('index)) {
    prepareInitialForm()
  }

  // see also: validation.AppValidationRules
  def validatorForm = validation(
    Params(params),
    paramKey("byteText") is required & byteValue,
    paramKey("shortText") is required & shortValue,
    paramKey("integerText") is required & intValue,
    paramKey("longText") is required & longValue,
    paramKey("floatText") is required & floatValue,
    paramKey("doubleText") is required & doubleValue,
    paramKey("dateText") is required & dateFormat,
    paramKey("creditcardText") is required & creditCardNumber,
    paramKey("emailText") is required & email,
    paramKey("urlText") is required & urlValue,
    paramKey("intRangeText") is required & intMinMaxValue(3, 10),
    paramKey("longRangeText") is required & longMinMaxValue(3, 10),
    paramKey("floatRangeText") is required & floatMinMaxValue(3.0F, 10.0F),
    paramKey("doubleRangeText") is required & doubleMinMaxValue(3.0D, 10.0D),
    paramKey("minlengthText") is required & minLength(3),
    paramKey("maxlengthText") is required & maxLength(10),
    paramKey("minbytelengthText") is required & minByteLength(3),
    paramKey("maxbytelengthText") is required & maxByteLength(10),
    paramKey("phoneText") is required & phoneNumber
  )

  def index = {
    render("/tutorial/validator/index")
  }

  def submit = {
    if (validatorForm.validate() && validateWithAdditionalRules()) {
      logger.info("All validations passed")
    } else {
      status = 400
    }
    render("/tutorial/validator/index")
  }

  private[this] def validateWithAdditionalRules(): Boolean = {
    val when: Boolean = (params.get("validwhen1Text"), params.get("validwhen2Text")) match {
      case (Some(v1), Some(v2)) if v1.isEmpty == false && v2.isEmpty =>
        val message: String = Messages.loadFromConfig(locale = currentLocale)
          .get("error.whenExample", Seq("validwhen2Text", "validwhen1Text"))
          .getOrElse("validwhen2Text is required when validwhen1Text exists")
        set(ATTR_ERROR_MESSAGES -> (errorMessages :+ message))

        false
      case _ => true
    }
    when
  }

  private[this] def prepareInitialForm(): Unit = {
    addParam("byteText", "1")
    addParam("shortText", "1")
    addParam("integerText", "1")
    addParam("longText", "1")
    addParam("floatText", "1.0")
    addParam("doubleText", "1.0")
    addParam("dateText", "2008/1/1")
    addParam("emailText", "higayasuo@gmail.com")
    addParam("urlText", "http://d.hatena.ne.jp/higayasuo")
    addParam("intRangeText", "7")
    addParam("longRangeText", "7")
    addParam("floatRangeText", "7.0")
    addParam("doubleRangeText", "7.0")
    addParam("minlengthText", "123")
    addParam("maxlengthText", "1234567890")
    addParam("minbytelengthText", "aaa")
    addParam("maxbytelengthText", "aaaaaaaaaa")
    addParam("phoneText", "03-9999-9999")
  }

}
