package co.com.sofka.crud.persistence.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "todos")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private boolean completed;

    public Integer getTodoListId() {
        return todoListId;
    }

    public void setTodoListId(Integer todoListId) {
        this.todoListId = todoListId;
    }

    public TodoList getTodoList() {
        return todoList;
    }

    public void setTodoList(TodoList todoList) {
        this.todoList = todoList;
    }

    @Column(name = "id_todo_list")
    private Integer todoListId;

    @ManyToOne
    @JoinColumn(name = "id_todo_list", insertable = false, updatable = false)
    private TodoList todoList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
