package controller.tutorial

import java.nio.file.{ Files, Paths }

import controller.concern.SAStrutsLayout
import skinny.SkinnyServlet
import skinny.controller.feature.FileUploadFeature
import skinny.micro.multipart.FileItem

class UploadController extends SkinnyServlet with FileUploadFeature with SAStrutsLayout {

  def index = render("/tutorial/upload/index")

  beforeAction() {
    if (Files.notExists(Paths.get("work"))) {
      Files.createDirectory(Paths.get("work"))
    }
  }

  def upload = {
    doUpload("formFile")
    doUpload("formFiles[0]")
    doUpload("formFiles[1]")
    render("/tutorial/upload/index")
  }

  private[this] def doUpload(paramName: String): Unit = {
    fileParams.get(paramName) match {
      case Some(file: FileItem) if file.size > 0 => write(file)
      case _ => logger.info(s"${paramName} is absent")
    }
  }

  private[this] def write(file: FileItem): Unit = {
    val path = Paths.get(s"work/${file.name}").toAbsolutePath
    Files.write(path, file.get())
    logger.info(s"Uploaded: ${path}")
  }

}