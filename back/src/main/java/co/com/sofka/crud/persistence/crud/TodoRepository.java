package co.com.sofka.crud.persistence.crud;

import co.com.sofka.crud.persistence.entity.Todo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TodoRepository extends CrudRepository<Todo, Long> {
    List<Todo> getTodoByTodoListId(int todoListId);
}
