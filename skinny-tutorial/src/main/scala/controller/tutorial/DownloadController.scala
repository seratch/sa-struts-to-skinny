package controller.tutorial

class DownloadController extends SAStrutsTutorialController {

  private[this] val content: Array[Byte] = "こんにちは".getBytes("Shift_JIS")

  def index = render("/tutorial/download/index")

  def download = {
    val filename: String = new String("サンプル.txt".getBytes("Shift_JIS"), "ISO-8859-1")
    response.setHeader("Content-Disposition", "attachment; filename=" + filename)
    content
  }

}
