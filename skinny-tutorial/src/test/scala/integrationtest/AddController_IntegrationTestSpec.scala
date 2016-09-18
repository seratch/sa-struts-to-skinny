package integrationtest

import skinny.test._
import _root_.controller.Controllers

class AddController_IntegrationTestSpec extends SkinnyFlatSpec with SkinnyTestSupport {
  addFilter(Controllers.add, "/*")

  it should "show index page" in {
    get("/sa-struts-tutorial/add") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "accept submit requests" in {
    withSession("csrf-token" -> "valid_token") {

      post("/sa-struts-tutorial/add", "csrf-token" -> "valid_token") {
        status should equal(400)
      }

      post("/sa-struts-tutorial/add", "arg1" -> "abc", "arg2" -> "234", "csrf-token" -> "valid_token") {
        status should equal(400)
      }

      post("/sa-struts-tutorial/add", "arg1" -> "123", "arg2" -> "234") {
        status should equal(403)
      }

      post("/sa-struts-tutorial/add", "arg1" -> "123", "arg2" -> "234", "csrf-token" -> "valid_token") {
        status should equal(200)
        body should include("= 357")
      }
    }
  }

}
