package com.github.nnnnusui.plotter

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.github.nnnnusui.plotter.repository.UsesDatabase

import scala.io.StdIn

object Plotter extends App {
  implicit val system = ActorSystem("my-system")
  implicit val materializer = ActorMaterializer()
  // needed for the future flatMap/onComplete in the end
  implicit val executionContext = system.dispatcher

  implicit val repositoryImpl: UsesDatabase = H2Database
  val router = new Router

  val bindingFuture = Http().newServerAt("localhost", 8080).bindFlow(router.route)
  system.registerOnTermination(() => router.repositoryImpl.session.close())

  println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
  StdIn.readLine() // let it run until user presses return
  bindingFuture
    .flatMap(_.unbind()) // trigger unbinding from the port
    .onComplete(_ => system.terminate()) // and shutdown when done

}
