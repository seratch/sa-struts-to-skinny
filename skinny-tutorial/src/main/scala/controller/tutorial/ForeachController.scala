package controller.tutorial

import model.ForeachItem

class ForeachController extends SAStrutsTutorialController {

  private[this] val items: Seq[ForeachItem] = (0 until 10).map(i => ForeachItem(i, s"name${i}"))

  def index = {
    set("items" -> items)
    render("/tutorial/foreach/index")
  }

  def indexButton = {
    set("items" -> items)
    render("/tutorial/foreach/indexButton")
  }

  def result = {
    params.getAs[Int]("id") match {
      case Some(id) =>
        val item = ForeachItem(id, s"name${id}")
        set("item" -> item)
        render("/tutorial/foreach/result")
      case _ =>
        halt(404)
    }
  }

}
