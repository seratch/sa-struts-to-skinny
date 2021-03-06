package model

import skinny.orm._
import scalikejdbc._

case class Address(
  id: Long,
  name: String,
  version: Int
)

object Address extends SkinnyCRUDMapper[Address] {
  override lazy val tableName = "address"
  override lazy val defaultAlias = createAlias("a")

  override def extract(rs: WrappedResultSet, rn: ResultName[Address]): Address = {
    autoConstruct(rs, rn)
  }
}
