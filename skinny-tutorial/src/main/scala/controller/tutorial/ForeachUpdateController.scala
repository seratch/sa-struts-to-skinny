package controller.tutorial

import model.ForeachItem

class ForeachUpdateController extends SAStrutsTutorialController {

  private[this] val initialItems: Seq[ForeachItem] = (0 until 10).map(i => ForeachItem(i, s"name${i}"))

  def index = {
    set("items" -> initialItems)
    render("/tutorial/foreachUpdate/index")
  }

  def submit = {
    set("items" -> buildItemsFromParams())
    render("/tutorial/foreachUpdate/index")
  }

  private[this] def buildItemsFromParams(): Seq[ForeachItem] = {
    for {
      i <- (0 until 10)
      id <- params.getAs[Int](s"mapItems[${i}].id")
      name <- params.getAs[String](s"mapItems[${i}].name")
    } yield ForeachItem(id, name)
  }

}
