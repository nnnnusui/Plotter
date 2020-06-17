package com.github.nnnnusui.plotter.adapter

import com.github.nnnnusui.cleanarchitecture.usecase.{OutputBoundary, UsesPresenter}
import com.github.nnnnusui.plotter.usecase.Section

trait HasSectionCreatePresenter extends UsesPresenter[Section.Create.Output]{
  override protected val presenter: OutputBoundary[Section.Create.Output] = SectionCreatePresenter
}
