package com.github.nnnnusui.plotter

import com.github.nnnnusui.plotter.adapter.{SectionCreateController, SectionFindAllController}

object Plotter extends App {
  SectionCreateController.control("test")
  SectionFindAllController.control()
}
