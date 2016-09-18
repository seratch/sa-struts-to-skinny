package controller.tutorial

class RedirectController extends SAStrutsTutorialController {

  def index = render("/tutorial/redirect/index")

  def showGoogle = redirect("http://www.google.co.jp")

}