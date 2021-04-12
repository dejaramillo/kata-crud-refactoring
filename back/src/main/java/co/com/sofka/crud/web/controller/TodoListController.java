package co.com.sofka.crud.web.controller;

import co.com.sofka.crud.model.TodoListModel;
import co.com.sofka.crud.service.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class TodoListController {

    @Autowired
    TodoListService todoListService;

    @GetMapping("/all")
    public List<TodoListModel> getAll(){
        return todoListService.getAll();
    }

    @PostMapping(value = "api/list")
    public TodoListModel save(@RequestBody TodoListModel todoListModel){
        return todoListService.saveTodoList(todoListModel);
    }

    @DeleteMapping("/del/{id}")
    public void delete(@PathVariable int id){
        todoListService.delete(id);
    }
}
