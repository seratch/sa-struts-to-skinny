package controller

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import model._

// NOTICE before/after filters won't be executed by default
class AddressesControllerSpec extends FunSpec with Matchers with BeforeAndAfterAll with DBSettings {

  override def afterAll() {
    super.afterAll()
    Address.deleteAll()
  }

  def createMockController = new AddressesController with MockController
  def newAddres = FactoryGirl(Address).create()

  describe("AddressesController") {

    describe("shows address") {
      it("shows HTML response") {
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/addresses/index"))
        controller.contentType should equal("text/html; charset=utf-8")
      }

      it("shows JSON response") {
        implicit val format = Format.JSON
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/addresses/index"))
        controller.contentType should equal("application/json; charset=utf-8")
      }
    }

    describe("shows an address") {
      it("shows HTML response") {
        val address = newAddres
        val controller = createMockController
        controller.showResource(address.id)
        controller.status should equal(200)
        controller.getFromRequestScope[Address]("item") should equal(Some(address))
        controller.renderCall.map(_.path) should equal(Some("/addresses/show"))
      }
    }

    describe("shows new resource input form") {
      it("shows HTML response") {
        val controller = createMockController
        controller.newResource()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/addresses/new"))
      }
    }

    describe("creates an address") {
      it("succeeds with valid parameters") {
        val controller = createMockController
        controller.prepareParams(
          "name" -> "dummy",
          "version" -> Int.MaxValue.toString()
        )
        controller.createResource()
        controller.status should equal(200)
      }

      it("fails with invalid parameters") {
        val controller = createMockController
        controller.prepareParams() // no parameters
        controller.createResource()
        controller.status should equal(400)
        controller.errorMessages.size should be > (0)
      }
    }

    it("shows a resource edit input form") {
      val address = newAddres
      val controller = createMockController
      controller.editResource(address.id)
      controller.status should equal(200)
      controller.renderCall.map(_.path) should equal(Some("/addresses/edit"))
    }

    it("updates an address") {
      val address = newAddres
      val controller = createMockController
      controller.prepareParams(
        "name" -> "dummy",
        "version" -> Int.MaxValue.toString()
      )
      controller.updateResource(address.id)
      controller.status should equal(200)
    }

    it("destroys an address") {
      val address = newAddres
      val controller = createMockController
      controller.destroyResource(address.id)
      controller.status should equal(200)
    }

  }

}
