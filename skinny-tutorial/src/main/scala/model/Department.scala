package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class Department(
  id: Long,
  name: String,
  version: Int,
  employees: Seq[Employee] = Nil
)

object Department extends SkinnyCRUDMapper[Department] {
  override lazy val tableName = "department"
  override lazy val defaultAlias = createAlias("d")

  lazy val employeesRef = hasMany[Employee](
    many = Employee -> Employee.defaultAlias,
    on = (d, e) => sqls.eq(d.id, e.departmentId),
    merge = (d, es) => d.copy(employees = es)
  )

  override def extract(rs: WrappedResultSet, rn: ResultName[Department]): Department = {
    autoConstruct(rs, rn, "employees")
  }
}
