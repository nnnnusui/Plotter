package com.github.nnnnusui.plotter.repository

import akka.stream.alpakka.slick.scaladsl.SlickSession
import slick.jdbc.JdbcProfile

trait UsesDatabase {
  implicit val session: SlickSession
  import session.profile.api._
  val db: Database
  val profile: JdbcProfile
}
