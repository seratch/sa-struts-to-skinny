package controller.tutorial

class RadioController extends SAStrutsTutorialController {

  def index = {
    addParam("radio", "2")
    render("/tutorial/radio/index")
  }

  def submit = {
    set("radio", params.get("radio"))
    render("/tutorial/radio/index")
  }

}
