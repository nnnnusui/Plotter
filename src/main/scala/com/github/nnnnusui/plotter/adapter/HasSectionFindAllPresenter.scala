package com.github.nnnnusui.plotter.adapter

import com.github.nnnnusui.cleanarchitecture.usecase.{OutputBoundary, UsesPresenter}
import com.github.nnnnusui.plotter.usecase.Section

trait HasSectionFindAllPresenter extends UsesPresenter[Section.FindAll.Output]{
  override protected val presenter: OutputBoundary[Section.FindAll.Output] = SectionFindAllPresenter
}
