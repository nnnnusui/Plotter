package com.github.nnnnusui.plotter.router

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.HttpResponse
import com.github.nnnnusui.plotter.repository.{Word => Repository}
import com.github.nnnnusui.plotter.entity.{Word => Entity}
import com.github.nnnnusui.plotter.input.{Word => Input}
import com.github.nnnnusui.plotter.output.{Word => Output}
import com.github.nnnnusui.plotter.usecase.{Word => UseCase}

import scala.concurrent.{ExecutionContext, ExecutionContextExecutor}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.StandardRoute
import akka.http.scaladsl.server.directives.MethodDirectives
import spray.json.DefaultJsonProtocol

trait Word extends SprayJsonSupport with DefaultJsonProtocol{
  this: UseCase =>
  import Input.JsonFormat._
  import Output.JsonFormat._

  val route =
    pathPrefix("word") {
      pathEnd {
        get {
          onSuccess(use(Input.GetAll())) {result=>
            complete(result)
          }
        } ~
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
        get {
          onSuccess(use(Input.GetById(id))) {result=>
            complete(result)
          }
        } ~
        put {
          entity(as[Input.Update]) { input =>
            update(input)
          } ~
          formFields("value") { value =>
            update(Input.Update(id, value))
          }
        } ~
        MethodDirectives.delete {
          onSuccess(use(Input.Delete(id))) {result=>
            complete(result)
          }
        }
      }
    }

  def create(input: Input.Create) =
    onSuccess(use(input)) {result=>
      complete(result)
    }
  def update(input: Input.Update) =
    onSuccess(use(input)) {result=>
      complete(result)
    }
}
