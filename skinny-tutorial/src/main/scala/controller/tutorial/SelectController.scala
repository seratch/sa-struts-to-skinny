package controller.tutorial

class SelectController extends SAStrutsTutorialController {

  def index = {
    addParam("select", "3")
    render("/tutorial/select/index")
  }

  def submit = render("/tutorial/select/index")

}