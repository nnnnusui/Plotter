package com.github.nnnnusui.plotter.adapter

import com.github.nnnnusui.cleanarchitecture.usecase.InputBoundary
import com.github.nnnnusui.plotter.usecase.Section

object SectionCreateController {
  private val interactor: InputBoundary[Section.Create.Input] =
    new Section.Create
      with HasSectionCreatePresenter
      with HasSectionRepository

  def control(text: String): Unit ={
    val inputData = new Section.Create.Input(text)
    interactor.input(inputData)
  }
}
