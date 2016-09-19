package controller

import skinny._
import skinny.controller.AssetsController
import skinny.micro.implicits.ServletApiImplicits

object Controllers extends ServletApiImplicits {

  def mount(ctx: ServletContext): Unit = {
    employees.mount(ctx)
    departments.mount(ctx)
    addresses.mount(ctx)
    root.mount(ctx)

    index.mount(ctx)
    add.mount(ctx)
    ajax.mount(ctx)
    checkbox.mount(ctx)
    condition.mount(ctx)
    download.mount(ctx)
    foreach.mount(ctx)
    foreachUpdate.mount(ctx)
    nestedForeach.mount(ctx)
    nestedForeachUpdate.mount(ctx)
    radio.mount(ctx)
    select.mount(ctx)
    multibox.mount(ctx)
    multiselect.mount(ctx)
    textarea.mount(ctx)
    token.mount(ctx)
    validator.mount(ctx)
    redirect.mount(ctx)
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

  object index extends _root_.controller.tutorial.IndexController with Routes {
    val indexUrl = get("/sa-struts-tutorial/?")(index).as('index)
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
  object download extends _root_.controller.tutorial.DownloadController with Routes {
    val indexUrl = get("/sa-struts-tutorial/download/?")(index).as('index)
    val downloadUrl = post("/sa-struts-tutorial/download/?")(download).as('download)
  }
  object condition extends _root_.controller.tutorial.ConditionController with Routes {
    val invalidUrl = get("/sa-struts-tutorial/condition/?")(index).as('invalid)
    val validUrl = get("/sa-struts-tutorial/condition/:id")(index).as('valid)
  }
  object foreach extends _root_.controller.tutorial.ForeachController with Routes {
    val indexUrl = get("/sa-struts-tutorial/foreach/?")(index).as('index)
    val indexButtonUrl = get("/sa-struts-tutorial/foreachButton/?")(indexButton).as('indexButton)
    val resultUrl = get("/sa-struts-tutorial/foreach/result/:id")(result).as('result)
    val resultPostUrl = post("/sa-struts-tutorial/foreachButton/?")(result).as('resultPost)
  }
  object foreachUpdate extends _root_.controller.tutorial.ForeachUpdateController with Routes {
    val indexUrl = get("/sa-struts-tutorial/foreachUpdate/?")(index).as('index)
    val submitUrl = post("/sa-struts-tutorial/foreachUpdate/?")(submit).as('submit)
  }
  object nestedForeach extends _root_.controller.tutorial.NestedForeachController with Routes {
    val indexUrl = get("/sa-struts-tutorial/nestedForeach/?")(index).as('index)
  }
  object nestedForeachUpdate extends _root_.controller.tutorial.NestedForeachUpdateController with Routes {
    val indexUrl = get("/sa-struts-tutorial/nestedForeachUpdate/?")(index).as('index)
    val submitUrl = post("/sa-struts-tutorial/nestedForeachUpdate/?")(submit).as('submit)
  }
  object redirect extends _root_.controller.tutorial.RedirectController with Routes {
    val indexUrl = get("/sa-struts-tutorial/redirect/?")(index).as('index)
    val submitUrl = post("/sa-struts-tutorial/redirect/?")(showGoogle).as('submit)
  }
  object select extends _root_.controller.tutorial.SelectController with Routes {
    val indexUrl = get("/sa-struts-tutorial/select/?")(index).as('index)
    val submitUrl = post("/sa-struts-tutorial/select/?")(submit).as('submit)
  }
  object multibox extends _root_.controller.tutorial.MultiboxController with Routes {
    val indexUrl = get("/sa-struts-tutorial/multibox/?")(index).as('index)
    val submitUrl = post("/sa-struts-tutorial/multibox/?")(submit).as('submit)
  }
  object multiselect extends _root_.controller.tutorial.MultiselectController with Routes {
    val indexUrl = get("/sa-struts-tutorial/multiselect/?")(index).as('index)
    val submitUrl = post("/sa-struts-tutorial/multiselect/?")(submit).as('submit)
  }
  object radio extends _root_.controller.tutorial.RadioController with Routes {
    val indexUrl = get("/sa-struts-tutorial/radio/?")(index).as('index)
    val submitUrl = post("/sa-struts-tutorial/radio/?")(submit).as('submit)
  }
  object textarea extends _root_.controller.tutorial.TextareaController with Routes {
    val indexUrl = get("/sa-struts-tutorial/textarea/?")(index).as('index)
    val submitUrl = post("/sa-struts-tutorial/textarea/?")(submit).as('submit)
  }
  object token extends _root_.controller.tutorial.TokenController with Routes {
    val indexUrl = get("/sa-struts-tutorial/token/?")(index).as('index)
    val submitUrl = post("/sa-struts-tutorial/token/?")(submit).as('submit)
  }
  object validator extends _root_.controller.tutorial.ValidatorController with Routes {
    val indexUrl = get("/sa-struts-tutorial/validator/?")(index).as('index)
    val submitUrl = post("/sa-struts-tutorial/validator/?")(submit).as('submit)
  }
  object upload extends _root_.controller.tutorial.UploadController with Routes {
    val indexUrl = get("/?")(index).as('index)
    val uploadUrl = post("/?")(upload).as('upload)
  }

}
