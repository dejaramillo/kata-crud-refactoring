package co.com.sofka.crud.mapper;

import co.com.sofka.crud.model.TodoListModel;
import co.com.sofka.crud.model.TodoModel;
import co.com.sofka.crud.persistence.entity.Todo;
import co.com.sofka.crud.persistence.entity.TodoList;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;


@Mapper(componentModel = "spring")
public interface TodoListMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name")
    })
    TodoListModel toTodoListModel(TodoList todoList);
    List<TodoListModel> toTodoListModels(List<TodoList> todoLists);

    @InheritInverseConfiguration
    @Mapping(target = "todo", ignore = true)
    TodoList toTodoList(TodoListModel todoListModel);
}
