package co.com.sofka.crud.service;

import co.com.sofka.crud.mapper.TodoListMapper;
import co.com.sofka.crud.model.TodoListModel;
import co.com.sofka.crud.persistence.crud.TodoListRepository;
import co.com.sofka.crud.persistence.entity.TodoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TodoListService {

    @Autowired
    TodoListRepository todoListRepository;

    @Autowired
    TodoListMapper todoListMapper;

    public List<TodoListModel> getAll(){
        return todoListMapper.toTodoListModels((List<TodoList>) todoListRepository.findAll());
    }


    public TodoListModel saveTodoList(TodoListModel todoListModel) {
        if (todoListModel.getName().isEmpty() || todoListModel.getName().length() < 3) {
            throw new RuntimeException("Lista vacia o muy corta");
        }
        TodoList todoList = todoListMapper.toTodoList(todoListModel);
        return todoListMapper.toTodoListModel(todoListRepository.save(todoList));
    }

    public void delete(int id){
        todoListRepository.deleteById(id);
    }
}
