package com.github.nnnnusui.plotter

import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpResponse}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.StandardRoute

object Router {
  val route =
    pathSingleSlash {
      get { index() }
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
