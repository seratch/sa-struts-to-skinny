package controller

import skinny._
import skinny.controller.AssetsController

object Controllers {

  def mount(ctx: ServletContext): Unit = {
    employees.mount(ctx)
    departments.mount(ctx)
    addresses.mount(ctx)
    root.mount(ctx)
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

}
