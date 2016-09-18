package integrationtest

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import _root_.controller.Controllers
import model._

class DepartmentsController_IntegrationTestSpec extends SkinnyFlatSpec with SkinnyTestSupport with BeforeAndAfterAll with DBSettings {
  addFilter(Controllers.departments, "/*")

  override def afterAll() {
    super.afterAll()
    Employee.deleteAll()
    Department.deleteAll()
  }

  def newDepartment = FactoryGirl(Department).create()

  it should "show departments" in {
    get("/departments") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/departments/") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/departments.json") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/departments.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show a department in detail" in {
    get(s"/departments/${newDepartment.id}") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/departments/${newDepartment.id}.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/departments/${newDepartment.id}.json") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show new entry form" in {
    get(s"/departments/new") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "create a department" in {
    post(
      s"/departments",
      "name" -> "dummy",
      "version" -> Int.MaxValue.toString()
    ) {
        logBodyUnless(403)
        status should equal(403)
      }

    withSession("csrf-token" -> "valid_token") {
      post(
        s"/departments",
        "name" -> "dummy",
        "version" -> Int.MaxValue.toString(),
        "csrf-token" -> "valid_token"
      ) {
          logBodyUnless(302)
          status should equal(302)
          val id = header("Location").split("/").last.toLong
          Department.findById(id).isDefined should equal(true)
        }
    }
  }

  it should "show the edit form" in {
    get(s"/departments/${newDepartment.id}/edit") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "update a department" in {
    put(
      s"/departments/${newDepartment.id}",
      "name" -> "dummy",
      "version" -> Int.MaxValue.toString()
    ) {
        logBodyUnless(403)
        status should equal(403)
      }

    withSession("csrf-token" -> "valid_token") {
      put(
        s"/departments/${newDepartment.id}",
        "name" -> "dummy",
        "version" -> Int.MaxValue.toString(),
        "csrf-token" -> "valid_token"
      ) {
          logBodyUnless(302)
          status should equal(302)
        }
    }
  }

  it should "delete a department" in {
    delete(s"/departments/${newDepartment.id}") {
      logBodyUnless(403)
      status should equal(403)
    }
    withSession("csrf-token" -> "valid_token") {
      delete(s"/departments/${newDepartment.id}?csrf-token=valid_token") {
        logBodyUnless(200)
        status should equal(200)
      }
    }
  }

}
