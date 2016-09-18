package integrationtest

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import _root_.controller.Controllers
import model._

class AddresController_IntegrationTestSpec extends SkinnyFlatSpec with SkinnyTestSupport with BeforeAndAfterAll with DBSettings {
  addFilter(Controllers.addres, "/*")

  override def afterAll() {
    super.afterAll()
    Addres.deleteAll()
  }

  def newAddres = FactoryGirl(Addres).create()

  it should "show addres" in {
    get("/addres") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/addres/") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/addres.json") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/addres.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show a addres in detail" in {
    get(s"/addres/${newAddres.id}") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/addres/${newAddres.id}.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/addres/${newAddres.id}.json") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show new entry form" in {
    get(s"/addres/new") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "create a addres" in {
    post(s"/addres",
      "name" -> "dummy",
      "version" -> Int.MaxValue.toString()) {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      post(s"/addres",
        "name" -> "dummy",
        "version" -> Int.MaxValue.toString(),
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
        val id = header("Location").split("/").last.toLong
        Addres.findById(id).isDefined should equal(true)
      }
    }
  }

  it should "show the edit form" in {
    get(s"/addres/${newAddres.id}/edit") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "update a addres" in {
    put(s"/addres/${newAddres.id}",
      "name" -> "dummy",
      "version" -> Int.MaxValue.toString()) {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      put(s"/addres/${newAddres.id}",
        "name" -> "dummy",
        "version" -> Int.MaxValue.toString(),
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
      }
    }
  }

  it should "delete a addres" in {
    delete(s"/addres/${newAddres.id}") {
      logBodyUnless(403)
      status should equal(403)
    }
    withSession("csrf-token" -> "valid_token") {
      delete(s"/addres/${newAddres.id}?csrf-token=valid_token") {
        logBodyUnless(200)
        status should equal(200)
      }
    }
  }

}
