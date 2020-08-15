package com.github.nnnnusui.plotter.output

import spray.json.{DefaultJsonProtocol, RootJsonFormat}


sealed trait Word
object Word extends DefaultJsonProtocol {
  case class GetAll(value: Seq[(Int, String)]) extends Word
  case class GetById(id: Int, value: String) extends Word
  case class Create(id: Int) extends Word
  object JsonFormat{
    implicit lazy val getAllOutput: RootJsonFormat[GetAll] = jsonFormat1(GetAll)
    implicit lazy val getByIdOutput: RootJsonFormat[GetById] = jsonFormat2(GetById)
    implicit lazy val createOutput: RootJsonFormat[Create] = jsonFormat1(Create)
  }
}
