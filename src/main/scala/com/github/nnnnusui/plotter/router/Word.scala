package com.github.nnnnusui.plotter.router

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.Directives._
import com.github.nnnnusui.plotter.input.{Word => Input}
import com.github.nnnnusui.plotter.output.{Word => Output}
import com.github.nnnnusui.plotter.repository.UsesDatabase
import com.github.nnnnusui.plotter.repository.{Word => Repository}
import com.github.nnnnusui.plotter.usecase.{Word => UseCase}

import scala.concurrent.ExecutionContext

class Word(implicit val context: ExecutionContext, implicit val usesDatabase: UsesDatabase) extends SprayJsonSupport{
  import Input.JsonFormat._
  import Output.JsonFormat._
  val repository = new Repository()
  val useCase = new UseCase(repository)
  import useCase.use

  val alias = new Alias

  val route =
    pathPrefix("word") {
      pathEndOrSingleSlash {
        get(getAll()) ~
        post(create())
      } ~
      pathPrefix(IntNumber) { id =>
        pathEndOrSingleSlash {
          get(getById(id))
        } ~
        alias.route(id)
      }
    }

  def getAll() =
    onSuccess(use(Input.GetAll())) {result=>
      complete(result)
    }
  def getById(id: Int) =
    onSuccess(use(Input.GetById(id))) {result=>
      complete(result)
    }
  def create() =
    onSuccess(use(Input.Create())) {result=>
      complete(result)
    }
}
