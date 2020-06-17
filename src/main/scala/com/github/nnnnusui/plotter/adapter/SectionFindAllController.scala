package com.github.nnnnusui.plotter.adapter

import com.github.nnnnusui.cleanarchitecture.usecase.InputBoundary
import com.github.nnnnusui.plotter.usecase.Section

object SectionFindAllController {
  private val interactor: InputBoundary[Section.FindAll.Input] =
    new Section.FindAll
      with HasSectionFindAllPresenter
      with HasSectionRepository

  def control(): Unit ={
    val inputData = new Section.FindAll.Input()
    interactor.input(inputData)
  }
}
