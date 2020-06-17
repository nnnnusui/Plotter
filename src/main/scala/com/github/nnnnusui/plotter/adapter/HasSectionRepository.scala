package com.github.nnnnusui.plotter.adapter

import com.github.nnnnusui.cleanarchitecture.usecase.UsesRepository
import com.github.nnnnusui.plotter.usecase.Section

trait HasSectionRepository extends UsesRepository[Section.Repository]{
  override protected val repository: Section.Repository = SectionRepository
}
