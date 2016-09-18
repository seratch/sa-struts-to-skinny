package controller.tutorial

import controller.tutorial.ForeachController.Item

class ForeachController extends SAStrutsTutorialController {

  private[this] val mapItems: Seq[Item] = (0 until 10).map(i => Item(i, s"name${i}"))

  def index = {
    set("items" -> mapItems)
    render("/tutorial/foreach/index")
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