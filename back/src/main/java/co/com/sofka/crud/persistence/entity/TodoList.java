package co.com.sofka.crud.persistence.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "todo_lists")
public class TodoList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @OneToMany(mappedBy = "todoList")
    private List<Todo> todo;

    public List<Todo> getTodo() {
        return todo;
    }

    public void setTodo(List<Todo> todo) {
        this.todo = todo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
