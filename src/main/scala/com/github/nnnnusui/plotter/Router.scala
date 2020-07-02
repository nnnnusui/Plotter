package com.github.nnnnusui.plotter

import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpResponse}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.StandardRoute
import com.github.nnnnusui.plotter.controller.Section

object Router {
  val route =
    pathSingleSlash {
      get { index() }
    } ~
      path("section") {
        get {
          Section.list()
        } ~
        post {
          formField("text") {text =>
            Section.create(text)
          }
        }
      }

  def index(): StandardRoute = complete(
    HttpResponse(
      entity = HttpEntity(
        ContentTypes.`text/html(UTF-8)`,
        <html>
          <body>
            <h1>Welcome to <i>akka-http</i>!</h1>
          </body>
        </html>.toString
      )
    )
  )
}
