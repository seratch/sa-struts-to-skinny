package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class Address(
  id: Long,
  name: String,
  version: Int
)

object Address extends SkinnyCRUDMapper[Address] with OptimisticLockWithVersionFeature[Address] {
  override lazy val tableName = "address"
  override lazy val defaultAlias = createAlias("a")
  override lazy val lockVersionFieldName = "version"

  override def extract(rs: WrappedResultSet, rn: ResultName[Address]): Address = {
    autoConstruct(rs, rn)
  }
}
