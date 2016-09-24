package controller.tutorial

import skinny._
import skinny.validator._

class AddController extends SAStrutsTutorialController {
  protectFromForgery()

  def addForm = validation(
    Params(params),
    paramKey("arg1") is required & numeric & intValue,
    paramKey("arg2") is required & numeric & intValue
  )

  def index = render("/tutorial/add/index")

  def submit = {
    if (addForm.validate()) {
      val result = params("arg1").toInt + params("arg2").toInt
      set("result" -> Some(result))
    }
    render("/tutorial/add/index")
  }

}
