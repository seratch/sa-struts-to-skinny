package controller.concern

import skinny.controller.feature.{ BeforeAfterActionFeature, ScalateTemplateEngineFeature }
import skinny.micro.base.MainThreadLocalEverywhere

trait SAStrutsLayout { self: ScalateTemplateEngineFeature with BeforeAfterActionFeature with MainThreadLocalEverywhere =>

  beforeAction() {
    layout("sastruts.ssp")
  }

}
