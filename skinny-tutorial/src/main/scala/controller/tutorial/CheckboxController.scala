package controller.tutorial

class CheckboxController extends SAStrutsTutorialController {
  protectFromForgery()

  def index = render("/tutorial/checkbox/index")

  def submit = {
    set("check1" -> params.getAs[Boolean]("check1").getOrElse(false))
    set("check2" -> params.getAs[Boolean]("check2").getOrElse(false))
    render("/tutorial/checkbox/submit")
  }

}
