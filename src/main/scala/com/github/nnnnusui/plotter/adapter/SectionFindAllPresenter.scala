package com.github.nnnnusui.plotter.adapter

import com.github.nnnnusui.cleanarchitecture.usecase.OutputBoundary
import com.github.nnnnusui.plotter.usecase.Section

object SectionFindAllPresenter extends OutputBoundary[Section.FindAll.Output]{
  //  private val view = SampleView
  def output(outputData: Section.FindAll.Output): Unit ={
    println(outputData)
    //    val viewModel = SampleViewModel()
    //    view.show(viewModel)
  }
}
