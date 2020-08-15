package com.github.nnnnusui.plotter.input

import spray.json.{DefaultJsonProtocol, RootJsonFormat}

sealed trait Word
object Word extends DefaultJsonProtocol {
  case class GetAll() extends Word
  case class GetById(id: Int) extends Word
  case class Create(value: String = "") extends Word
  case class Update(id: Int, value: String) extends Word
  case class Delete(id: Int) extends Word
  object JsonFormat{
    implicit lazy val getAllInput: RootJsonFormat[GetAll] = jsonFormat0(GetAll)
    implicit lazy val getByIdInput: RootJsonFormat[GetById] = jsonFormat1(GetById)
    implicit lazy val createInput: RootJsonFormat[Create] = jsonFormat1(Create)
    implicit lazy val updateInput: RootJsonFormat[Update] = jsonFormat2(Update)
    implicit lazy val deleteInput: RootJsonFormat[Delete] = jsonFormat1(Delete)
  }
}
