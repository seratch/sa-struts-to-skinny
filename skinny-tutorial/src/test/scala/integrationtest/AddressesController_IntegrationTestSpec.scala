package integrationtest

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import _root_.controller.Controllers
import model._

class AddressesController_IntegrationTestSpec extends SkinnyFlatSpec with SkinnyTestSupport with BeforeAndAfterAll with DBSettings {
  addFilter(Controllers.addresses, "/*")

  override def afterAll() {
    super.afterAll()
    Employee.deleteAll()
    Address.deleteAll()
  }

  def newAddres = FactoryGirl(Address).create()

  it should "show addresses" in {
    get("/addresses") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/addresses/") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/addresses.json") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/addresses.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show an address in detail" in {
    get(s"/addresses/${newAddres.id}") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/addresses/${newAddres.id}.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/addresses/${newAddres.id}.json") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show new entry form" in {
    get(s"/addresses/new") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "create an address" in {
    post(
      s"/addresses",
      "name" -> "dummy",
      "version" -> Int.MaxValue.toString()
    ) {
        logBodyUnless(403)
        status should equal(403)
      }

    withSession("csrf-token" -> "valid_token") {
      post(
        s"/addresses",
        "name" -> "dummy",
        "version" -> Int.MaxValue.toString(),
        "csrf-token" -> "valid_token"
      ) {
          logBodyUnless(302)
          status should equal(302)
          val id = header("Location").split("/").last.toLong
          Address.findById(id).isDefined should equal(true)
        }
    }
  }

  it should "show the edit form" in {
    get(s"/addresses/${newAddres.id}/edit") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "update an address" in {
    put(
      s"/addresses/${newAddres.id}",
      "name" -> "dummy",
      "version" -> Int.MaxValue.toString()
    ) {
        logBodyUnless(403)
        status should equal(403)
      }

    withSession("csrf-token" -> "valid_token") {
      put(
        s"/addresses/${newAddres.id}",
        "name" -> "dummy",
        "version" -> Int.MaxValue.toString(),
        "csrf-token" -> "valid_token"
      ) {
          logBodyUnless(302)
          status should equal(302)
        }
    }
  }

  it should "delete an address" in {
    delete(s"/addresses/${newAddres.id}") {
      logBodyUnless(403)
      status should equal(403)
    }
    withSession("csrf-token" -> "valid_token") {
      delete(s"/addresses/${newAddres.id}?csrf-token=valid_token") {
        logBodyUnless(200)
        status should equal(200)
      }
    }
  }

}
