package com.github.nnnnusui.plotter.router

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.directives.MethodDirectives
import com.github.nnnnusui.plotter.input.{Alias => Input}
import com.github.nnnnusui.plotter.output.{Alias => Output}
import com.github.nnnnusui.plotter.repository.{UsesDatabase, Alias => Repository}
import com.github.nnnnusui.plotter.usecase.{Alias => UseCase}

import scala.concurrent.ExecutionContext

class Alias(implicit val context: ExecutionContext, implicit val usesDatabase: UsesDatabase) extends SprayJsonSupport{
  import Input.JsonFormat._
  import Output.JsonFormat._
  val repository = new Repository()
  val useCase = new UseCase(repository)
  import useCase.use

  def route(wordId: Int) =
    pathPrefix("alias") {
      pathEnd {
        get(getAll()) ~
          post {
            entity(as[Input.Create]) { input =>
              create(input)
            } ~
              formFields("value") { value =>
                create(Input.Create(value))
              }
          }
      } ~
      path(IntNumber) { id =>
        get(getById(id)) ~
        put {
          entity(as[Input.Update]) { input =>
            update(input)
          } ~
            formFields("value") { value =>
              update(Input.Update(id, value))
            }
        } ~
        MethodDirectives.delete(delete(id))
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
  def create(input: Input.Create) =
    onSuccess(use(input)) {result=>
      complete(result)
    }
  def update(input: Input.Update) =
    onSuccess(use(input)) {result=>
      complete(result)
    }
  def delete(id: Int) =
    onSuccess(use(Input.Delete(id))) {result=>
      complete(result)
    }
}
