package controller.tutorial

import controller.ApplicationController

class TilesController extends ApplicationController {

  beforeAction() {
    layout("tiles.ssp")
  }

  def index = {
    set("title" -> "Tiles")
    render("/tutorial/tiles/index")
  }

}
