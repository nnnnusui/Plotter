package com.github.nnnnusui.plotter.entity

import java.util.UUID

object Section{
  case class Id(value: UUID)
  case class Text(value: String)

  def apply(str: String): Section ={
    val id = Id(UUID.randomUUID())
    val text = Text(str)
    new Section(id, text)
  }
}
case class Section(id: Section.Id, text: Section.Text)
