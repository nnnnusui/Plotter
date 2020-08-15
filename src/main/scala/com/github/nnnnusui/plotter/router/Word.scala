package com.github.nnnnusui.plotter.router

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.HttpResponse
import com.github.nnnnusui.plotter.repository.{Word => Repository}
import com.github.nnnnusui.plotter.entity.{Word => Entity}

import scala.concurrent.ExecutionContextExecutor
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.StandardRoute
import akka.http.scaladsl.server.directives.MethodDirectives

trait Word extends SprayJsonSupport{
  this: Repository =>
  implicit val dispatcher: ExecutionContextExecutor

  val route =
    pathPrefix("word") {
      pathEnd {
        get {
          complete(getAll)
        } ~
        post {
          entity(as[Entity]) {word =>
            createPost(word)
          } ~
          formFields("value") { value =>
            createPost(Entity(value = value))
          }
        }
      } ~
      path(IntNumber) { id =>
        get {
          complete(getById(id))
        } ~
        put {
          entity(as[Entity]) { word =>
            updatePost(id, word.value)
          } ~
          formFields("value") { value =>
            updatePost(id, value)
          }
        } ~ MethodDirectives.delete {
          complete {
            delete(id).map { result => HttpResponse(entity = "dog has been deleted successfully") }
          }
        }
      }
    }
  def createPost(entity: Entity): StandardRoute = complete {
    create(entity).map{ result => HttpResponse(entity = "word has been saved successfully") }
  }
  def updatePost(id: Int, value: String): StandardRoute = complete {
    val newEntity = Entity(Option(id), value)
    update(newEntity).map { result => HttpResponse(entity = "dog has been updated successfully") }
  }
}
