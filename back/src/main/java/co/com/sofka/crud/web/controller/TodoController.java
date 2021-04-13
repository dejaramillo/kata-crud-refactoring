package co.com.sofka.crud.web.controller;

import co.com.sofka.crud.model.TodoModel;
import co.com.sofka.crud.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.spec.InvalidKeySpecException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class TodoController {

    @Autowired
    private TodoService service;

    @GetMapping(value = "api/todos")
    public List<TodoModel> getAll(){
        return service.getAll();
    }
    
    @PostMapping(value = "api/todo")
    public TodoModel save(@RequestBody TodoModel todoModel){
        return service.save(todoModel);
    }

    @PutMapping(value = "api/todo")
    public TodoModel update(@RequestBody TodoModel todoModel){
        if(todoModel.getId() != null){
            return service.save(todoModel);
        }
        throw new RuntimeException("No existe el id para actualziar");
    }

    @DeleteMapping(value = "api/{id}/todo")
    public void delete(@PathVariable("id")Long id){
        service.delete(id);
    }

   @GetMapping(value = "api/{id}/todo")
    public TodoModel get(@PathVariable("id") Long id) throws InvalidKeySpecException {
        return service.get(id);
    }

}
