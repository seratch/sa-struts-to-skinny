package controller

import skinny._
import skinny.controller.AssetsController
import skinny.micro.context.RichServletContext
import skinny.micro.implicits.ServletApiImplicits

object Controllers extends ServletApiImplicits {

  def mount(ctx: ServletContext): Unit = {
    employees.mount(ctx)
    departments.mount(ctx)
    addresses.mount(ctx)
    root.mount(ctx)

    add.mount(ctx)
    ajax.mount(ctx)
    checkbox.mount(ctx)
    foreach.mount(ctx)
    ctx.mount(upload, "/sa-struts-tutorial/upload/*")

    AssetsController.mount(ctx)
  }

  object root extends RootController with Routes {
    val indexUrl = get("/?")(index).as('index)
  }

  object addresses extends _root_.controller.AddressesController with Routes {
  }

  object departments extends _root_.controller.DepartmentsController with Routes {
  }

  object employees extends _root_.controller.EmployeesController with Routes {
  }

  object add extends _root_.controller.tutorial.AddController with Routes {
    val indexUrl = get("/sa-struts-tutorial/add/?")(index).as('index)
    val submitUrl = post("/sa-struts-tutorial/add/?")(submit).as('submit)
  }
  object ajax extends _root_.controller.tutorial.AjaxController with Routes {
    val indexUrl = get("/sa-struts-tutorial/ajax/?")(index).as('index)
    val helloUrl = get("/sa-struts-tutorial/ajax/hello")(hello).as('hello)
  }
  object checkbox extends _root_.controller.tutorial.CheckboxController with Routes {
    val indexUrl = get("/sa-struts-tutorial/checkbox/?")(index).as('index)
    val submitUrl = post("/sa-struts-tutorial/checkbox/?")(submit).as('submit)
  }
  object foreach extends _root_.controller.tutorial.ForeachController with Routes {
    val indexUrl = get("/sa-struts-tutorial/foreach/?")(index).as('index)
    val resultUrl = get("/sa-struts-tutorial/foreach/result/:id")(result).as('submit)
  }

  object upload extends _root_.controller.tutorial.UploadController with Routes {
    val indexUrl = get("/?")(index).as('index)
    val uploadUrl = post("/?")(upload).as('upload)
  }

}
