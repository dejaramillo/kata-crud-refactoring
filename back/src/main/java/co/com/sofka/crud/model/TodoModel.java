package co.com.sofka.crud.model;


public class TodoModel {

    private Long id;
    private String name;
    private Boolean completed;
    private Integer todoListId;

    public TodoListModel getTodoListModel() {
        return todoListModel;
    }

    public void setTodoListModel(TodoListModel todoListModel) {
        this.todoListModel = todoListModel;
    }

    private TodoListModel todoListModel;

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

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Integer getTodoListId() {
        return todoListId;
    }

    public void setTodoListId(Integer todoListId) {
        this.todoListId = todoListId;
    }
}
