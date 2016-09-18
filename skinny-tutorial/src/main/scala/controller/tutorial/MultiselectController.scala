package controller.tutorial

class MultiselectController extends SAStrutsTutorialController {

  beforeAction() {
    setSelectedValues()
  }

  def index = {
    set("defaultSelect1" -> Some(Seq("2", "3")))
    render("/tutorial/multiselect/index")
  }

  def submit = {
    set("submit1" -> params.get("submit1"))
    set("submit2" -> params.get("submit2"))
    render("/tutorial/multiselect/index")
  }

  private[this] def setSelectedValues() = {
    set("select1" -> multiParams.get("select1"))
    set("select2" -> multiParams.get("select2"))
  }

}