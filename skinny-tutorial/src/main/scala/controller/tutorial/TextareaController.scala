package controller.tutorial

class TextareaController extends SAStrutsTutorialController {

  def index = {
    addParam("textarea", "initial value")
    render("/tutorial/textarea/index")
  }

  def submit = render("/tutorial/textarea/index")

}