package controller.tutorial

import controller.tutorial.ForeachController.Item

class ForeachController extends SAStrutsTutorialController {

  private[this] val items: Seq[Item] = (0 until 10).map(i => Item(i, s"name${i}"))

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
        val item = Item(id, s"name${id}")
        set("item" -> item)
        render("/tutorial/foreach/result")
      case _ =>
        halt(404)
    }
  }

}

object ForeachController {

  case class Item(id: Int, name: String)

}