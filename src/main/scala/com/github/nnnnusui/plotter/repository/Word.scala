package com.github.nnnnusui.plotter.repository

import com.github.nnnnusui.plotter.entity.{Word => Entity}
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

import scala.concurrent.Future

trait Word extends UsesRepository with DefaultJsonProtocol {
  import repository._
  import profile.api._
  implicit lazy val wordFormat: RootJsonFormat[Entity] = jsonFormat2(Entity)

  class Word(tag: Tag) extends Table[Entity](tag, "word"){
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
    def value = column[String]("value")
    def * = (id.?, value) <>(Entity.tupled, Entity.unapply)
  }

  protected val tableQuery = TableQuery[Word]
  protected def tableAutoInc = tableQuery returning tableQuery.map(_.id)
  def create(entity: Entity): Future[Int] = db.run{
    tableAutoInc += entity
  }
  def update(entity: Entity): Future[Int] = db.run{
    tableQuery.filter(_.id === entity.id.get).update(entity)
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
