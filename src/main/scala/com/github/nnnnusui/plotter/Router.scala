package com.github.nnnnusui.plotter

import akka.http.scaladsl.server.Directives._
import com.github.nnnnusui.plotter.repository.UsesDatabase

import scala.concurrent.ExecutionContextExecutor

class Router( implicit val dispatcher: ExecutionContextExecutor
             ,implicit val repositoryImpl: UsesDatabase) {

  val word = new router.Word
  while(!word.repository.ddl.isCompleted){}

  val rest =
    pathPrefix("rest") {
      pathEndOrSingleSlash {
        get { complete("version info") }
      } ~
      pathPrefix("1") {
        pathEndOrSingleSlash {
          get { complete("available") }
        } ~
        word.route
      }
    }
  val route =
    pathSingleSlash {
      get { getFromResource("index.html") }
    } ~
    rest
}
