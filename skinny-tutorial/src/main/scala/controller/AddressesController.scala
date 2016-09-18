package controller

import skinny._
import skinny.validator._
import _root_.controller._
import model.Address

class AddressesController extends SkinnyResource with ApplicationController {
  protectFromForgery()

  override def model = Address
  override def resourcesName = "addresses"
  override def resourceName = "address"

  override def resourcesBasePath = s"/${toSnakeCase(resourcesName)}"
  override def useSnakeCasedParamKeys = true

  override def viewsDirectoryPath = s"/${resourcesName}"

  override def createParams = Params(params)
  override def createForm = validation(
    createParams,
    paramKey("name") is required & maxLength(255),
    paramKey("version") is required & numeric & intValue
  )
  override def createFormStrongParameters = Seq(
    "name" -> ParamType.String,
    "version" -> ParamType.Int
  )

  override def updateParams = Params(params)
  override def updateForm = validation(
    updateParams,
    paramKey("name") is required & maxLength(255),
    paramKey("version") is required & numeric & intValue
  )
  override def updateFormStrongParameters = Seq(
    "name" -> ParamType.String,
    "version" -> ParamType.Int
  )

}
