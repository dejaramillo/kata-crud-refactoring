import React, { useEffect, useState } from "react";
import { validateList } from "../helpers";

function TodoListOfList({ MainList, handleDeleteTodoMainList }) {
  const [listOfListValue, setListOfListValue] = useState("");
  const [isEditing, setIsEditing] = useState(false);
  const [currentListOfListEditing, setCurrentListOfListEditing] = useState({});
  const [errorMessageListOfLists, setErrorMessageListOfLists] = useState({});
  const [todosListOfLists, setTodoListOfLists] = useState([]);
  const { id, name } = MainList;

  useEffect(() => {
    const getTodoListOfList = async (idTodoMain) => {
      await fetch(`http://localhost:8080/api/${idTodoMain}/todo`, {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        },
      })
        .then((data) => data.json())
        .then((info) => {
          console.log(info);
          setTodoListOfLists(info);
        });
    };
    getTodoListOfList(id);
  }, []);

  const saveListOfLists = async (mainListId) => {
    await fetch("http://localhost:8080/api/todo", {
      method: "POST",
      body: JSON.stringify({
        name: listOfListValue,
        id: null,
        completed: false,
        todoListId: mainListId,
      }),
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then((data) => data.json()) // tranform data to JSON object Javascript
      .then((info) => {
        const newTodoListOfLists = [...todosListOfLists, info]; // add new value to all previous values.
        setTodoListOfLists(newTodoListOfLists);
      }) // data ready for use.
      .catch((error) => console.error("Error:", error));
  };

  const editTodoListOfListServer = async (currentListOfList, checkbox) => {
    const { id, completed, name, todoListId } = currentListOfList;
    await fetch("http://localhost:8080/api/todo", {
      method: "PUT",
      body: JSON.stringify({
        id: id,
        name: checkbox ? name : listOfListValue,
        completed: completed,
        todoListId: todoListId,
      }),
      headers: {
        "Content-Type": "application/json",
      },
    });
  };

  const deleteTodoListOfLists = async (deleteTodoListOfListId) => {
    await fetch(`http://localhost:8080/api/${deleteTodoListOfListId}/todo`, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
    }).then((data) => {
      if (data.ok) {
        const newtodosListOfLists = todosListOfLists.filter(
          (item) => item.id !== deleteTodoListOfListId
        );
        setTodoListOfLists(newtodosListOfLists);
      }
    });
  };

  const handleCreateTodoListOfLists = (mainListId) => {
    // create a new list
    const validate = validateList(listOfListValue);
    if (validate.validate) saveListOfLists(mainListId);
    setErrorMessageListOfLists(validate);
  };

  const handleEditTodoListOfLists = (idTodoListOfLists) => {
    const editTodoListofList = todosListOfLists.filter(
      (item) => item.id === idTodoListOfLists
    );
    setCurrentListOfListEditing(editTodoListofList); //la lista actual que se va a editar
    setIsEditing(true);
    console.log(editTodoListofList[0].name);
    setListOfListValue(editTodoListofList[0].name);
  };

  const handleEditTodoListOfListsParent = () => {
    const { id } = currentListOfListEditing[0]; // La lista que actualmente se esta editando
    editTodoListOfListServer(currentListOfListEditing[0]);

    const newListOfLists = todosListOfLists.map((list) => {
      //recorremos toda la lista actual de sub-tareas
      if (list.id === id) {
        //verificamos que el id de la lista coincida con el id que estamos editando.
        return { ...list, name: listOfListValue };
      }
      return list;
    });
    setTodoListOfLists(newListOfLists);
    setIsEditing(false);
    setListOfListValue("");
  };

  const handleCheckedTodoListOfLists = (id) => {
    const editTodoListofList = todosListOfLists.filter(
      (item) => item.id === id
    );
    const newListOfLists = todosListOfLists.map((list) => {
      //recorremos toda la lista actual de sub-tareas
      if (list.id === editTodoListofList[0].id) {
        //verificamos que el id de la lista coincida con el id que estamos editando.
        return { ...list, completed: !editTodoListofList[0].completed };
      }
      return list;
    });

    const newListOfList = newListOfLists.filter(
      (item) => item.id === editTodoListofList[0].id
    );
    editTodoListOfListServer(newListOfList[0], true);
    setTodoListOfLists(newListOfLists);
  };

  const handleDeleteTodoListOfLists = (id) => {
    deleteTodoListOfLists(id);
  };
  return (
    <fieldset className="contentList">
      <legend>
        {name || ""}
        <button
          className="btn btn-danger"
          onClick={(e) => handleDeleteTodoMainList(id)}
        >
          Eliminar
        </button>
      </legend>
      <div>
        <input
          className="form-control"
          type="text"
          value={listOfListValue}
          onChange={(e) => {
            setListOfListValue(e.target.value);
          }}
          placeholder="Que piensas hacer?"
        />
        <br />
        {isEditing ? (
          <button
            className="btn btn-primary"
            onClick={(e) => handleEditTodoListOfListsParent()}
          >
            Editar
          </button>
        ) : (
          <button
            className="btn btn-success"
            onClick={(e) => handleCreateTodoListOfLists(id)}
          >
            Crear
          </button>
        )}
        {!errorMessageListOfLists.validate && (
          <div style={{ color: "red" }}>{errorMessageListOfLists.message}</div>
        )}
      </div>
      <div>
        <div
          style={{
            display: "flex",
            justifyContent: "space-around",
            alignItems: "center",
            width: "50%",
          }}
        >
          <div>ID</div>
          <div>Tarea</div>
          <div>Completado</div>
        </div>
        {todosListOfLists.map((todo) => {
          const { id = "", name = "", completed = false } = todo;
          return (
            <div
              className="row"
              key={id}
              style={{
                display: "flex",
                justifyContent: "space-around",
                alignItems: "center",
                width: "80%",
              }}
            >
              <div className="col-sm-1">{id}</div>
              <div
                className="col-sm-3"
                style={{ color: `${completed ? "gray" : "black"}` }}
              >
                {name}
              </div>
              <div className="col-sm-3">
                <input
                  type="checkbox"
                  onChange={(e) => handleCheckedTodoListOfLists(id)}
                  checked={completed}
                />
              </div>
              <div>
                <button
                  className="btn btn-danger"
                  onClick={(e) => handleDeleteTodoListOfLists(id)}
                >
                  Eliminar
                </button>
              </div>
              <div>
                <button
                  className="btn btn-primary"
                  disabled={completed}
                  onClick={(e) => handleEditTodoListOfLists(id)}
                >
                  Editar
                </button>
              </div>
            </div>
          );
        })}
      </div>
    </fieldset>
  );
}

export default TodoListOfList;
