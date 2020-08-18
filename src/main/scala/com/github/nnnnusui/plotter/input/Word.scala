package com.github.nnnnusui.plotter.input

import spray.json.{DefaultJsonProtocol, RootJsonFormat}

sealed trait Word
object Word extends DefaultJsonProtocol {
  case class GetAll() extends Word
  case class GetById(id: Int) extends Word
  case class Create() extends Word
  object JsonFormat{
    implicit lazy val getByIdInput: RootJsonFormat[GetById] = jsonFormat1(GetById)
  }
}
