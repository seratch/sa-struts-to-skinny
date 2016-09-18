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
      val result: Option[Int] = {
        for {
          arg1 <- params.getAs[Int]("arg1")
          arg2 <- params.getAs[Int]("arg2")
        } yield arg1 + arg2
      }
      set("result" -> result)
    } else {
      status = 400
    }
    render("/tutorial/add/index")
  }

}
