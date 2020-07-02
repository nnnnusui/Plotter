package com.github.nnnnusui.plotter
package controller

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.StandardRoute
import com.github.nnnnusui.plotter.entity.{Section => Entity}
import com.github.nnnnusui.plotter.gateway.{Section => Gateway}

object Section {
  val gateway = Gateway
  def create(text: String): StandardRoute ={
    val entity = Entity(text)
    gateway.update(entity) match {
      case Left(err) => failWith(err)
      case Right(section) => complete(section.toString)
    }
  }
  def list(): StandardRoute ={
    gateway.list() match {
      case Left(err) => failWith(err)
      case Right(section) => complete(section.toString)
    }
  }
}
