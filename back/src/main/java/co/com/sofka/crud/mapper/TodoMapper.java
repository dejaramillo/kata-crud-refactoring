package co.com.sofka.crud.mapper;

import co.com.sofka.crud.model.TodoListModel;
import co.com.sofka.crud.model.TodoModel;
import co.com.sofka.crud.persistence.entity.Todo;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {TodoListModel.class})
public interface TodoMapper {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "completed", target = "completed"),
            @Mapping(source = "todoListId", target = "todoListId"),
            @Mapping(source = "todoList", target = "todoListModel")
    })
    TodoModel toTodoModel(Todo todo);

    @InheritInverseConfiguration
    Todo toTodo(TodoModel todoModel);
}
