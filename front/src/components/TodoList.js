import React, { useEffect, useState } from "react";
import { validateList } from "../helpers";
import TodoListOfLists from "./TodoListOfLists";
function TodoList() {
  const [mainListValue, setMainListValue] = useState(""); // input value
  const [todosMainList, setTodoMainList] = useState([]); // todas las listas padre
  const [errorMessage, setErrorMessage] = useState({});

  useEffect(() => {
    const getTodoMainList = async () => {
      await fetch("http://localhost:8080/api/list", {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        },
      })
        .then((data) => data.json())
        .then((info) => {
          setTodoMainList(info);
        });
    };
    getTodoMainList();
  }, []);

  const saveTodoMainList = async (todoMainListValue) => {
    await fetch("http://localhost:8080/api/todolist", {
      method: "POST",
      body: JSON.stringify({
        name: todoMainListValue,
        id: null,
      }),
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then((data) => data.json()) // tranform data to JSON object Javascript
      .then((info) => {
        const newTodoMainList = [...todosMainList, info]; // add new value to all previous values.
        setTodoMainList(newTodoMainList);
      }) // data ready for use.
      .catch((error) => console.error("Error:", error));
  };

  const deleteTodoMainList = async (idTodoMainList) => {
    await fetch(`http://localhost:8080/api/${idTodoMainList}/todolist`, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
    }).then((data) => {
      if (data.ok) {
        const newTodoMainList = todosMainList.filter(
          (item) => item.id !== idTodoMainList
        );
        setTodoMainList(newTodoMainList);
      }
    });
  };

  const handleNewMainList = (e) => {
    // create a new list
    const validate = validateList(mainListValue);
    if (validate.validate) saveTodoMainList(mainListValue);
    setErrorMessage(validate);
  };

  const handleDeleteTodoMainList = (id) => {
    //delete a existent list
    deleteTodoMainList(id);
  };
  return (
    <div>
      <div className="title">
        <h3>Dashboard</h3>
      </div>
      <div className="container">
        <div className="content">
          <input
            className="form-control"
            type="text"
            value={mainListValue}
            onChange={(e) => setMainListValue(e.target.value)}
            placeholder="lista de TO-DO"
          />
          <br />
          <button className="btn btn-success" onClick={handleNewMainList}>
            Nueva Lista
          </button>
        </div>
        {!errorMessage.validate && (
          <div style={{ color: "red" }}>{errorMessage.message}</div>
        )}
        {todosMainList.map((MainList) => {
          //Muestra todas las listas padre.
          return (
            <TodoListOfLists
              key={MainList.id}
              MainList={MainList}
              handleDeleteTodoMainList={handleDeleteTodoMainList}
            />
          );
        })}
      </div>
    </div>
  );
}

export default TodoList;
