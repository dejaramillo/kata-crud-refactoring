package co.com.sofka.crud.service;

import co.com.sofka.crud.mapper.TodoMapper;
import co.com.sofka.crud.model.TodoModel;
import co.com.sofka.crud.persistence.crud.TodoRepository;
import co.com.sofka.crud.persistence.entity.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.module.InvalidModuleDescriptorException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository repository;

    @Autowired
    private TodoMapper todoMapper;

    public List<TodoModel> getAll(){
        return todoMapper.toTodoModels((List<Todo>) repository.findAll());
    }

    public TodoModel save(TodoModel todoModel){
        if (todoModel.getName().isEmpty() || todoModel.getName().length() < 3) {
            throw new RuntimeException("Tarea vacia o el nombre es muy corto");
        }else {
        Todo todo = todoMapper.toTodo(todoModel);
        return todoMapper.toTodoModel(repository.save(todo));}
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public TodoModel get(Long id) throws InvalidKeySpecException {
         return todoMapper.toTodoModel(repository.findById(id).orElseThrow(InvalidKeySpecException::new));
    }

}
