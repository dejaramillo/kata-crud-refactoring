package co.com.sofka.crud.mapper;

import co.com.sofka.crud.model.TodoListModel;
import co.com.sofka.crud.model.TodoModel;
import co.com.sofka.crud.persistence.entity.Todo;
import co.com.sofka.crud.persistence.entity.TodoList;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface TodoListMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name")
    })
    TodoListModel toTodoListModel(TodoList todoList);

    @InheritInverseConfiguration
    @Mapping(target = "todo", ignore = true)
    TodoList toTodoList(TodoListModel todoListModel);
}
