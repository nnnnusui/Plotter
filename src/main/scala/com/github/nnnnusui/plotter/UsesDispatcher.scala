package com.github.nnnnusui.plotter

import scala.concurrent.ExecutionContextExecutor

trait UsesDispatcher {
  implicit val dispatcher: ExecutionContextExecutor
}
