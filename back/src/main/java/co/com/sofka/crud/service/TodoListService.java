package co.com.sofka.crud.service;

import co.com.sofka.crud.persistence.crud.TodoListRepository;
import co.com.sofka.crud.persistence.entity.TodoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoListService {
    @Autowired
    TodoListRepository todoListRepository;

    public Iterable<TodoList> list(){
        return todoListRepository.findAll();
    }

    public TodoList save(TodoList todoList){
        return todoListRepository.save(todoList);
    }

    public void delete(Integer id){
        todoListRepository.delete(get(id));
    }

    public TodoList get(Integer id){
        return todoListRepository.findById(id).orElseThrow();
    }



}
