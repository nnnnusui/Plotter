package com.github.nnnnusui.plotter.input

import spray.json.{DefaultJsonProtocol, RootJsonFormat}

sealed trait Word
object Word extends DefaultJsonProtocol {
  case class GetAll() extends Word
  case class Create(value: String = "") extends Word
  object JsonFormat{
    implicit lazy val getAllInput: RootJsonFormat[GetAll] = jsonFormat0(GetAll)
    implicit lazy val createInput: RootJsonFormat[Create] = jsonFormat1(Create)
  }
}
