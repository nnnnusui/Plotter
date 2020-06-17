package com.github.nnnnusui.plotter.adapter

import java.util.UUID

import com.github.nnnnusui.plotter.entity.Section
import com.github.nnnnusui.plotter.usecase.Section.Repository

import scala.collection.mutable

object SectionRepository extends Repository {
  val map = mutable.HashMap.empty[UUID, String]
  override def update(entity: Section): Unit = {
    val uuid = entity.id.value
    map.update(uuid, entity.text.value)
  }
  override def delete(entity: Section): Unit = {
    val uuid = entity.id.value
    map.remove(uuid)
  }

  override def getFrom(id: Section.Id): Section = {
    val uuid = id.value
    val text = map(uuid)
    Section(Section.Id(uuid), Section.Text(text))
  }
  override def findAll: Seq[Section] =
    map.map{case (id, text)=> (Section.Id(id), Section.Text(text))}
       .map{case (id, text)=> Section(id, text)}.toSeq
}
