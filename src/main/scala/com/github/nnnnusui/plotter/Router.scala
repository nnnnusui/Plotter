package com.github.nnnnusui.plotter

import akka.http.scaladsl.server.Directives._
import com.github.nnnnusui.plotter.repository.{Repository, UsesRepository}

import scala.concurrent.ExecutionContextExecutor

trait Router {
  implicit val _dispatcher: ExecutionContextExecutor
  val repositoryImpl: Repository
  trait HasRepository extends UsesRepository {
    val repository: Repository = repositoryImpl
  }
  trait HasDispatcher extends UsesDispatcher {
    implicit val dispatcher: ExecutionContextExecutor = _dispatcher
  }

  object Word
    extends usecase.Word
    with repository.Word
    with router.Word
    with HasRepository
    with HasDispatcher

  lazy val rest =
    pathPrefix("rest") {
      pathEndOrSingleSlash {
        get { complete("version info") }
      } ~
      pathPrefix("1") {
        pathEndOrSingleSlash {
          get { complete("available") }
        } ~
        Word.route
      }
    }
  lazy val route =
    pathSingleSlash {
      get { getFromResource("index.html") }
    } ~
    rest
}
