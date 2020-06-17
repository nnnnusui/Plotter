package com.github.nnnnusui.plotter.adapter

import com.github.nnnnusui.cleanarchitecture.usecase.OutputBoundary
import com.github.nnnnusui.plotter.usecase.Section

object SectionCreatePresenter extends OutputBoundary[Section.Create.Output]{
//  private val view = SampleView
  def output(outputData: Section.Create.Output): Unit ={
    println(outputData)
//    val viewModel = SampleViewModel()
//    view.show(viewModel)
  }
}
