package com.github.nnnnusui.plotter.input

import spray.json.{DefaultJsonProtocol, RootJsonFormat}

sealed trait Alias
object Alias extends DefaultJsonProtocol {
  case class GetAll() extends Alias
  case class GetById(id: Int) extends Alias
  case class Create(value: String = "") extends Alias
  case class Update(id: Int, value: String) extends Alias
  case class Delete(id: Int) extends Alias
  object JsonFormat{
    implicit lazy val getAllInput: RootJsonFormat[GetAll] = jsonFormat0(GetAll)
    implicit lazy val getByIdInput: RootJsonFormat[GetById] = jsonFormat1(GetById)
    implicit lazy val createInput: RootJsonFormat[Create] = jsonFormat1(Create)
    implicit lazy val updateInput: RootJsonFormat[Update] = jsonFormat2(Update)
    implicit lazy val deleteInput: RootJsonFormat[Delete] = jsonFormat1(Delete)
  }
}
