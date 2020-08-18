package com.github.nnnnusui.plotter.usecase

import com.github.nnnnusui.plotter.entity.{Word => Entity}
import com.github.nnnnusui.plotter.input.{Word => Input}
import com.github.nnnnusui.plotter.output.{Word => Output}
import com.github.nnnnusui.plotter.repository.{Word => Repository}

import scala.concurrent.{ExecutionContext, Future}

class Word(val repository: Repository)(implicit val context: ExecutionContext){
  import repository._
  def use(input: Input.GetAll): Future[Output.GetAll] =
    getAll.map(it=> Output.GetAll(it.map(it=> (it.id, it.value))))
  def use(input: Input.GetById): Future[Option[Output.GetById]] =
    getById(input.id).map(it=> it.map(it=> Output.GetById(it.id, it.value)))
  def use(input: Input.Create): Future[Output.Create] =
    create(Entity(0, input.value)).map(it=> Output.Create(it))
  def use(input: Input.Update): Future[Output.Update] =
    update(Entity(input.id, input.value)).map(it=> Output.Update(it))
  def use(input: Input.Delete): Future[Output.Delete] =
    delete(input.id).map(it=> Output.Delete(it))
}
