package com.github.nnnnusui.plotter.output

import spray.json.{DefaultJsonProtocol, RootJsonFormat}

sealed trait Word
object Word extends DefaultJsonProtocol {
  case class GetAll(value: Seq[(Int)]) extends Word
  case class GetById(id: Int) extends Word
  case class Create(id: Int) extends Word
  object JsonFormat{
    implicit lazy val getAllOutput: RootJsonFormat[GetAll] = jsonFormat1(GetAll)
    implicit lazy val getByIdOutput: RootJsonFormat[GetById] = jsonFormat1(GetById)
    implicit lazy val createOutput: RootJsonFormat[Create] = jsonFormat1(Create)
  }
}
