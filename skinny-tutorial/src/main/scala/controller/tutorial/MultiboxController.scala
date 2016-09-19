package controller.tutorial

class MultiboxController extends SAStrutsTutorialController {

  beforeAction() {
    setCheckedValues()
  }

  def index = {
    set("defaultCheck1" -> Some(Seq("2")))
    render("/tutorial/multibox/index")
  }

  def submit = {
    set("submit1" -> params.get("submit1"))
    set("submit2" -> params.get("submit2"))
    render("/tutorial/multibox/index")
  }

  private[this] def setCheckedValues() = {
    set("check1" -> multiParams.get("check1"))
    set("check2" -> multiParams.get("check2"))
  }

}
