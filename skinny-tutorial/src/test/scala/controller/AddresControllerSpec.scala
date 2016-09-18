package controller

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import model._

// NOTICE before/after filters won't be executed by default
class AddresControllerSpec extends FunSpec with Matchers with BeforeAndAfterAll with DBSettings {

  override def afterAll() {
    super.afterAll()
    Addres.deleteAll()
  }

  def createMockController = new AddresController with MockController
  def newAddres = FactoryGirl(Addres).create()

  describe("AddresController") {

    describe("shows addres") {
      it("shows HTML response") {
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/addres/index"))
        controller.contentType should equal("text/html; charset=utf-8")
      }

      it("shows JSON response") {
        implicit val format = Format.JSON
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/addres/index"))
        controller.contentType should equal("application/json; charset=utf-8")
      }
    }

    describe("shows a addres") {
      it("shows HTML response") {
        val addres = newAddres
        val controller = createMockController
        controller.showResource(addres.id)
        controller.status should equal(200)
        controller.getFromRequestScope[Addres]("item") should equal(Some(addres))
        controller.renderCall.map(_.path) should equal(Some("/addres/show"))
      }
    }

    describe("shows new resource input form") {
      it("shows HTML response") {
        val controller = createMockController
        controller.newResource()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/addres/new"))
      }
    }

    describe("creates a addres") {
      it("succeeds with valid parameters") {
        val controller = createMockController
        controller.prepareParams(
          "name" -> "dummy",
          "version" -> Int.MaxValue.toString())
        controller.createResource()
        controller.status should equal(200)
      }

      it("fails with invalid parameters") {
        val controller = createMockController
        controller.prepareParams() // no parameters
        controller.createResource()
        controller.status should equal(400)
        controller.errorMessages.size should be >(0)
      }
    }

    it("shows a resource edit input form") {
      val addres = newAddres
      val controller = createMockController
      controller.editResource(addres.id)
      controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/addres/edit"))
    }

    it("updates a addres") {
      val addres = newAddres
      val controller = createMockController
      controller.prepareParams(
        "name" -> "dummy",
        "version" -> Int.MaxValue.toString())
      controller.updateResource(addres.id)
      controller.status should equal(200)
    }

    it("destroys a addres") {
      val addres = newAddres
      val controller = createMockController
      controller.destroyResource(addres.id)
      controller.status should equal(200)
    }

  }

}
