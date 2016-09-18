package model

import skinny.orm._
import scalikejdbc._

case class Employee(
  id: Long,
  name: String,
  jobType: String,
  salary: Option[Int] = None,
  departmentId: Option[Int] = None,
  addressId: Option[Int] = None,
  version: Int,
  address: Option[Address] = None,
  department: Option[Department] = None
)

object Employee extends SkinnyCRUDMapper[Employee] {
  override lazy val tableName = "employee"
  override lazy val defaultAlias = createAlias("e")

  lazy val addressRef = belongsTo[Address](Address, (e, a) => e.copy(address = a))

  lazy val departmentRef = belongsTo[Department](Department, (e, d) => e.copy(department = d))

  override def extract(rs: WrappedResultSet, rn: ResultName[Employee]): Employee = {
    autoConstruct(rs, rn, "address", "department")
  }
}
