package controller

class RootController extends ApplicationController {

  def index = redirect(url(Controllers.index.indexUrl))

}
