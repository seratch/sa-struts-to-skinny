package controller.tutorial

import model.ForeachItem

class NestedForeachController extends SAStrutsTutorialController {

  private[this] val itemsSeq: Seq[Seq[ForeachItem]] = {
    (0 until 10).map { i =>
      (0 until 2).map { j =>
        ForeachItem(i * 10 + j, s"name${i}${j}")
      }
    }
  }

  def index = {
    set("itemsSeq" -> itemsSeq)
    render("/tutorial/nestedForeach/index")
  }

}
