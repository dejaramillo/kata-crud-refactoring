package co.com.sofka.crud.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "todo_lists")
public class TodoList {

    @Id
    private Integer id;
    private String name;

    @OneToMany(mappedBy = "todoList")
    private List<Todo> todo;

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
