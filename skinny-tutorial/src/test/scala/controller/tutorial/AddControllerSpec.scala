package controller.tutorial

import org.scalatest._
import skinny._
import skinny.test._

// NOTICE before/after filters won't be executed by default
class AddControllerSpec extends FunSpec with Matchers with DBSettings {

  def createMockController = new AddController with MockController

  describe("AddController") {

    it("shows index page") {
      val controller = createMockController
      controller.index
      controller.status should equal(200)
      controller.renderCall.map(_.path) should equal(Some("/tutorial/add/index"))
      controller.contentType should equal("text/html; charset=utf-8")
    }

    it("shows 400 for empty submit") {
      val controller = createMockController
      controller.submit
      controller.status should equal(400)
      controller.contentType should equal("text/html; charset=utf-8")
    }

    it("shows 200 for valid submit") {
      val controller = createMockController
      controller.prepareParams("arg1" -> "123", "arg2" -> "234")
      controller.submit
      controller.status should equal(200)
      controller.contentType should equal("text/html; charset=utf-8")
    }

  }

}
