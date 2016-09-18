package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class Addres(
  id: Long,
  name: String,
  version: Int
)

object Addres extends SkinnyCRUDMapper[Addres] {
  override lazy val tableName = "address"
  override lazy val defaultAlias = createAlias("a")

  override def extract(rs: WrappedResultSet, rn: ResultName[Addres]): Addres = {
    autoConstruct(rs, rn)
  }
}
