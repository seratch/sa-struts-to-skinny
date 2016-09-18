package controller

import skinny._
import skinny.validator._
import _root_.controller._
import model.Employee

class EmployeesController extends SkinnyResource with ApplicationController {
  protectFromForgery()

  override def model = Employee
  override def resourcesName = "employees"
  override def resourceName = "employee"

  override def resourcesBasePath = s"/${toSnakeCase(resourcesName)}"
  override def useSnakeCasedParamKeys = true

  override def viewsDirectoryPath = s"/${resourcesName}"

  override def createParams = Params(params)
  override def createForm = validation(createParams,
    paramKey("name") is required & maxLength(255),
    paramKey("job_type") is required & maxLength(30),
    paramKey("salary") is numeric & intValue,
    paramKey("department_id") is numeric & intValue,
    paramKey("address_id") is numeric & intValue,
    paramKey("version") is required & numeric & intValue
  )
  override def createFormStrongParameters = Seq(
    "name" -> ParamType.String,
    "job_type" -> ParamType.String,
    "salary" -> ParamType.Int,
    "department_id" -> ParamType.Int,
    "address_id" -> ParamType.Int,
    "version" -> ParamType.Int
  )

  override def updateParams = Params(params)
  override def updateForm = validation(updateParams,
    paramKey("name") is required & maxLength(255),
    paramKey("job_type") is required & maxLength(30),
    paramKey("salary") is numeric & intValue,
    paramKey("department_id") is numeric & intValue,
    paramKey("address_id") is numeric & intValue,
    paramKey("version") is required & numeric & intValue
  )
  override def updateFormStrongParameters = Seq(
    "name" -> ParamType.String,
    "job_type" -> ParamType.String,
    "salary" -> ParamType.Int,
    "department_id" -> ParamType.Int,
    "address_id" -> ParamType.Int,
    "version" -> ParamType.Int
  )

}
