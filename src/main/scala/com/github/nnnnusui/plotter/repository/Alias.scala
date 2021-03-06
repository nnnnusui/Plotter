package com.github.nnnnusui.plotter.repository

import com.github.nnnnusui.plotter.entity.{Alias => Entity}
import spray.json.DefaultJsonProtocol

import scala.concurrent.Future

class Alias(implicit val usesDatabase: UsesDatabase) extends DefaultJsonProtocol {
  import usesDatabase._
  import profile.api._

  class TableInfo(tag: Tag) extends Table[Entity](tag, "alias"){
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
    def value = column[String]("value")
    def * = (id, value) <>(Entity.tupled, Entity.unapply)
  }

  protected val tableQuery = TableQuery[TableInfo]
  protected def tableAutoInc = tableQuery returning tableQuery.map(_.id)
  def create(entity: Entity): Future[Int] = db.run{
    tableAutoInc += entity
  }
  def update(entity: Entity): Future[Int] = db.run{
    tableQuery.filter(_.id === entity.id).update(entity)
  }
  def getById(id: Int): Future[Option[Entity]] = db.run {
    tableQuery.filter(_.id === id).result.headOption
  }
  def getAll: Future[List[Entity]] = db.run{
    tableQuery.to[List].result
  }
  def delete(id: Int): Future[Int] = db.run{
    tableQuery.filter(_.id === id).delete
  }
  def ddl = db.run{
    tableQuery.schema.create
  }
}
