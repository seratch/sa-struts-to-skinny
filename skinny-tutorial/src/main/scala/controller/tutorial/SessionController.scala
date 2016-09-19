package controller.tutorial

import skinny._
import skinny.validator._
import model.SessionScopeForm
import scala.util.Try

class SessionController extends SAStrutsTutorialController {

  private[this] val TOKEN_SESSION_ATTRIBUTE_NAME = s"token-for-${classOf[SessionController].getCanonicalName()}"

  // ---------------------------
  // forms

  private def secondForm = validation(
    Params(params),
    paramKey("first") is required
  )

  private def thirdForm = validation(
    Params(params),
    paramKey("first") is required,
    paramKey("second") is required
  )

  private case class DispatcherParam(
    goToSecond: Boolean,
    goToThird: Boolean,
    backToSecond: Boolean,
    clear: Boolean
  )

  // ---------------------------
  // action methods

  def index = {
    renderWithSessionScopeForm("/tutorial/session/index")
  }

  def submit = {
    val dispatcher = DispatcherParam(
      goToSecond = params.get("goSecond").isDefined,
      goToThird = params.get("goThird").isDefined,
      backToSecond = params.get("backSecond").isDefined,
      clear = params.get("clear").isDefined
    )
    dispatcher match {
      case d if d.goToSecond => goToSecond
      case d if d.goToThird => goToThird
      case d if d.backToSecond => backToSecond
      case d if d.clear => clear
      case _ => index
    }
  }

  def goToSecond = {
    if (secondForm.validate()) {
      saveSessionForm()
      renderWithSessionScopeForm("/tutorial/session/second")
    } else {
      index
    }
  }

  def backToSecond = {
    renderWithSessionScopeForm("/tutorial/session/second")
  }

  def goToThird = {
    if (thirdForm.validate()) {
      saveSessionForm()
      renderWithSessionScopeForm("/tutorial/session/third")
    } else {
      renderWithSessionScopeForm("/tutorial/session/second")
    }
  }

  def clear = {
    session -= TOKEN_SESSION_ATTRIBUTE_NAME
    renderWithSessionScopeForm("/tutorial/session/index")
  }

  // ----------------------------------
  // private methods

  def renderWithSessionScopeForm(templateName: String): String = {
    set("sessionScopeForm" -> currentSessionScopeForm())
    render(templateName)
  }

  private[this] def saveSessionForm(): Unit = {
    buildSessionScopeFormFromParams() match {
      case Some(form) => session += TOKEN_SESSION_ATTRIBUTE_NAME -> form
      case _ => logger.warn("Skipped to save form because it's absent somehow")
    }
  }

  private[this] def currentSessionScopeForm(): Option[SessionScopeForm] = {
    session.getAs[SessionScopeForm](TOKEN_SESSION_ATTRIBUTE_NAME)
  }

  private[this] def buildSessionScopeFormFromParams(): Option[SessionScopeForm] = {
    params.get("first") match {
      case Some(first) =>
        currentSessionScopeForm() match {
          case Some(form) =>
            Some(form.copy(
              first = first,
              second = params.get("second").orElse(form.second)
            ))
          case _ =>
            Some(SessionScopeForm(
              first = first,
              second = params.get("second")
            ))
        }
      case _ => Option.empty
    }
  }

}
