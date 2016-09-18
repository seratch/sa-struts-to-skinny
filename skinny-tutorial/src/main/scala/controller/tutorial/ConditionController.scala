package controller.tutorial

class ConditionController extends SAStrutsTutorialController {

  def index = {
    set("id" -> params.get("id"))
    render("/tutorial/condition/index")
  }

}
