package controller.tutorial

class AjaxController extends SAStrutsTutorialController {

  def index = render("/tutorial/ajax/index")

  def hello = "こんにちは"

}
