package co.com.sofka.crud.persistence.crud;

import co.com.sofka.crud.persistence.entity.TodoList;
import org.springframework.data.repository.CrudRepository;

public interface TodoListRepository extends CrudRepository<TodoList, Integer> {
}
