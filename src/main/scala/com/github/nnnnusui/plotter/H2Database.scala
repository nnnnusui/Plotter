package com.github.nnnnusui.plotter

import akka.stream.alpakka.slick.scaladsl.SlickSession
import com.github.nnnnusui.plotter.repository.UsesDatabase

object H2Database extends UsesDatabase {
  implicit val session: SlickSession = SlickSession.forConfig("slick-h2")
  import session.profile.api._
  val db = Database.forConfig("slick-h2.db")
  val profile = slick.jdbc.H2Profile
  val slickSessionCreatedForDbAndProfile: SlickSession = SlickSession.forDbAndProfile(db, profile)
}
