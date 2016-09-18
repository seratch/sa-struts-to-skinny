package integrationtest

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import _root_.controller.Controllers
import model._

class EmployeesController_IntegrationTestSpec extends SkinnyFlatSpec with SkinnyTestSupport with BeforeAndAfterAll with DBSettings {
  addFilter(Controllers.employees, "/*")

  override def afterAll() {
    super.afterAll()
    Employee.deleteAll()
  }

  def newEmployee = FactoryGirl(Employee).create()

  it should "show employees" in {
    get("/employees") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/employees/") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/employees.json") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/employees.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show a employee in detail" in {
    get(s"/employees/${newEmployee.id}") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/employees/${newEmployee.id}.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/employees/${newEmployee.id}.json") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show new entry form" in {
    get(s"/employees/new") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "create a employee" in {
    post(s"/employees",
      "name" -> "dummy",
      "job_type" -> "dummy",
      "salary" -> Int.MaxValue.toString(),
      "department_id" -> Int.MaxValue.toString(),
      "address_id" -> Int.MaxValue.toString(),
      "version" -> Int.MaxValue.toString()) {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      post(s"/employees",
        "name" -> "dummy",
        "job_type" -> "dummy",
        "salary" -> Int.MaxValue.toString(),
        "department_id" -> Int.MaxValue.toString(),
        "address_id" -> Int.MaxValue.toString(),
        "version" -> Int.MaxValue.toString(),
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
        val id = header("Location").split("/").last.toLong
        Employee.findById(id).isDefined should equal(true)
      }
    }
  }

  it should "show the edit form" in {
    get(s"/employees/${newEmployee.id}/edit") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "update a employee" in {
    put(s"/employees/${newEmployee.id}",
      "name" -> "dummy",
      "job_type" -> "dummy",
      "salary" -> Int.MaxValue.toString(),
      "department_id" -> Int.MaxValue.toString(),
      "address_id" -> Int.MaxValue.toString(),
      "version" -> Int.MaxValue.toString()) {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      put(s"/employees/${newEmployee.id}",
        "name" -> "dummy",
        "job_type" -> "dummy",
        "salary" -> Int.MaxValue.toString(),
        "department_id" -> Int.MaxValue.toString(),
        "address_id" -> Int.MaxValue.toString(),
        "version" -> Int.MaxValue.toString(),
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
      }
    }
  }

  it should "delete a employee" in {
    delete(s"/employees/${newEmployee.id}") {
      logBodyUnless(403)
      status should equal(403)
    }
    withSession("csrf-token" -> "valid_token") {
      delete(s"/employees/${newEmployee.id}?csrf-token=valid_token") {
        logBodyUnless(200)
        status should equal(200)
      }
    }
  }

}
