package controller.tutorial

class TokenController extends SAStrutsTutorialController {

  private[this] val TOKEN_SESSION_ATTRIBUTE_NAME = s"token-for-${classOf[TokenController].getCanonicalName()}"

  def index = {
    saveToken()
    render("/tutorial/token/index")
  }

  def submit = try {
    set("submitted" -> true)
    (sentToken(), expectedToken()) match {
      case (Some(sent), Some(expected)) if sent == expected => // valid
      case (sent, expected) =>
        logger.warn(s"sent: ${sent}, expected: ${expected}")
        set("error" -> Some("Tokenが不正です。"))
    }
    render("/tutorial/token/index")
  } finally {
    resetToken()
  }

  private[this] def saveToken(): Unit = {
    // just re-use existing random value generator
    val tokenGenerator = skinny.micro.contrib.csrf.CSRFTokenGenerator
    val token = tokenGenerator.apply()
    session += TOKEN_SESSION_ATTRIBUTE_NAME -> token
    set("token" -> token)
  }

  private[this] def sentToken(): Option[String] = {
    params.getAs[String]("token")
  }

  private[this] def resetToken(): Unit = {
    session -= TOKEN_SESSION_ATTRIBUTE_NAME
  }

  private[this] def expectedToken(): Option[String] = {
    Option(session.getAttribute(TOKEN_SESSION_ATTRIBUTE_NAME)).map(_.toString)
  }

}
