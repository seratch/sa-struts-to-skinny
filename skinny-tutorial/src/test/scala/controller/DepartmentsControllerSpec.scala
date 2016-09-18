package controller

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import model._

// NOTICE before/after filters won't be executed by default
class DepartmentsControllerSpec extends FunSpec with Matchers with BeforeAndAfterAll with DBSettings {

  override def afterAll() {
    super.afterAll()
    Department.deleteAll()
  }

  def createMockController = new DepartmentsController with MockController
  def newDepartment = FactoryGirl(Department).create()

  describe("DepartmentsController") {

    describe("shows departments") {
      it("shows HTML response") {
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/departments/index"))
        controller.contentType should equal("text/html; charset=utf-8")
      }

      it("shows JSON response") {
        implicit val format = Format.JSON
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/departments/index"))
        controller.contentType should equal("application/json; charset=utf-8")
      }
    }

    describe("shows a department") {
      it("shows HTML response") {
        val department = newDepartment
        val controller = createMockController
        controller.showResource(department.id)
        controller.status should equal(200)
        controller.getFromRequestScope[Department]("item") should equal(Some(department))
        controller.renderCall.map(_.path) should equal(Some("/departments/show"))
      }
    }

    describe("shows new resource input form") {
      it("shows HTML response") {
        val controller = createMockController
        controller.newResource()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/departments/new"))
      }
    }

    describe("creates a department") {
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
      val department = newDepartment
      val controller = createMockController
      controller.editResource(department.id)
      controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/departments/edit"))
    }

    it("updates a department") {
      val department = newDepartment
      val controller = createMockController
      controller.prepareParams(
        "name" -> "dummy",
        "version" -> Int.MaxValue.toString())
      controller.updateResource(department.id)
      controller.status should equal(200)
    }

    it("destroys a department") {
      val department = newDepartment
      val controller = createMockController
      controller.destroyResource(department.id)
      controller.status should equal(200)
    }

  }

}
