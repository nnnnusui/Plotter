package com.github.nnnnusui.plotter.gateway

import com.github.nnnnusui.plotter.entity.{Section => Entity}

object Section {
  var table: Seq[Entity] = Seq[Entity]()

  def update(entity: Entity): Either[Throwable, Entity] ={
    table = table :+ entity
    Right(entity)
  }
  def list(): Either[Throwable, Seq[Entity]] = Right(table)
}
