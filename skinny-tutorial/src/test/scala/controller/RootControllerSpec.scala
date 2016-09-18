package controller

import org.scalatest._
import skinny.test.MockController

class RootControllerSpec extends FunSpec with Matchers {

  describe("RootController") {
    it("shows top page") {
      val controller = new RootController with MockController
      controller.index
    }
  }

}
