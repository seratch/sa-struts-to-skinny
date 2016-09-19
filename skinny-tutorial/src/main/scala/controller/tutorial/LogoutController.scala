package controller.tutorial

class LogoutController extends SAStrutsTutorialController {

  def index = {
    session.invalidate()
    render("/tutorial/logout/index")
  }

}
