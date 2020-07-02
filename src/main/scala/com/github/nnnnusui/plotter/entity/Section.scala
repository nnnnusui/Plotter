package com.github.nnnnusui.plotter.entity

import java.util.UUID

object Section{
  case class Id(value: UUID)
  class Text(val value: String)

  def apply(str: String): Section ={
    val id = Id(UUID.randomUUID())
    val text = new Text(str)
    new Section(id, text)
  }
}
case class Section(id: Section.Id, text: Section.Text)
