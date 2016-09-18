package controller.tutorial

import controller.ApplicationController

trait SAStrutsTutorialController extends ApplicationController {

  beforeAction() {
    layout("sastruts.ssp")
  }

}
