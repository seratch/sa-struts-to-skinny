package controller.tutorial

import model.ForeachItem

class NestedForeachUpdateController extends SAStrutsTutorialController {

  private[this] val initialItemsSeq: Seq[Seq[ForeachItem]] = {
    (0 until 10).map { i =>
      (0 until 2).map { j =>
        ForeachItem(i * 10 + j, s"name${i}${j}")
      }
    }
  }

  def index = {
    set("itemsSeq" -> initialItemsSeq)
    render("/tutorial/nestedForeachUpdate/index")
  }

  def submit = {
    set("itemsSeq" -> buildItemsFromParams())
    render("/tutorial/nestedForeachUpdate/index")
  }

  private[this] def buildItemsFromParams(): Seq[Seq[ForeachItem]] = {
    (0 until 10).map { i =>
      for {
        j <- (0 until 2)
        id <- params.getAs[Int](s"mapItemsItems[${i}][${j}].id")
        name <- params.getAs[String](s"mapItemsItems[${i}][${j}].name")
      } yield ForeachItem(id, name)
    }
  }

}