package com.github.nnnnusui.plotter.usecase

import java.util.UUID

import com.github.nnnnusui.cleanarchitecture.usecase.{Repository => IRepository}
import com.github.nnnnusui.cleanarchitecture.usecase.{Interactor, UsesRepository}
import com.github.nnnnusui.plotter.entity.{Section => Entity}

object Section {
  object Create {
    class Input(val text: String)
    class Output(val id: UUID, val text: String)
    object Output{
      def apply(entity: Entity): Output = new Output(entity.id.value, entity.text.value)
    }
  }
  trait Create extends Interactor[Create.Input, Create.Output]
                  with UsesRepository[Repository]{
    import Create.{Input, Output}
    override protected val repository: Repository
    protected def handle(input: Input): Output ={
      val entity = Entity(input.text)
      repository.update(entity)
      Output(entity)
    }
  }

  object FindAll{
    class Input
    class Output(val sections: Seq[(UUID, String)])
    object Output{
      def apply(entities: Seq[Entity]): Output =
        new Output(entities.map(it=> (it.id.value, it.text.value)))
    }
  }
  trait FindAll extends Interactor[FindAll.Input, FindAll.Output]
                   with UsesRepository[Repository]{
    import FindAll.{Input, Output}
    override protected val repository: Repository
    protected def handle(input: Input): Output ={
      Output(repository.findAll)
    }
  }

  trait Repository extends IRepository{
    def update(entity: Entity): Unit
    def delete(entity: Entity): Unit
    def getFrom(id: Entity.Id): Entity
    def findAll: Seq[Entity]
  }
}
