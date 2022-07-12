import { React, useState } from 'react'
import axios from "axios";
import { useNavigate } from "react-router-dom";

export default function CheckboxTodo(props) {
  let [todosChecked, setTodosChecked] = useState([]);
  let [todo, setTodo] = useState([]);
 // let [todoToUpdate, setTodoToUpdate] = useState();
  let navigate = useNavigate();

   // const { idToUpdate } = useParams();

  const { todos, setTodos, id, setId, checked, setChecked } = props;

  const handleChange = () => {
   // setId(todo.id);
  // setTodo(e);
    setChecked(!checked);
    console.log(todo.id);
    console.log(todo);
    let selectedTodos = [...todosChecked];
    if(todo.checked === true) {

      selectedTodos.push(todo.id.value);
    } else {
      selectedTodos.splice(selectedTodos.indexOf(todo.id.value), 1);
    }
    setTodosChecked(selectedTodos);
    console.log(selectedTodos);
  //   let URL = `http://localhost:8080/api/update/${id}`;
  //   // const todoToUpdate = {
  //   //    id: todo.id,
  //   //    {todo}
  //   //   //  title: todo.title,
  //   //   // state: "Completed",
  //   //   // description: todo.description,
  //   // }
  //   console.log(id);
  //   console.log(todo);
  //  // console.log(todoToUpdate);
  //   axios
  //     .put(URL, {
  //       todo: todo
  //     })
  //     .then((response) => {
  //       console.log(response.data);
  //       navigate("/");
  //     })
  //     .catch(error => {
  //       console.error("Une erreur est survenue", error);
  //     });
  }

  //  useEffect(() => {
  //    handleChange();
  //  }, []);

  // const onTodoChange = (e) => {
  //     let selectedTodos = [...todosChecked];
  //     if (e.checked) {
  //       selectedTodos.push(e.value);
  //     } else {
  //       selectedTodos.splice(selectedTodos.indexOf(e.value), 1);
  //     }
  //     setTodosChecked(selectedTodos);
  //     console.log(selectedTodos);
  // }

  return (
    <div className="form-check">
      <label className="form-check-label" htmlFor="completed">
        <input
          className="form-check-input"
          type="checkbox"
          checked={checked}
          todo={todo}
          value="Completed"
          onChange={() => {
            setId(todo.id);
            setTodo(todo);
            console.log(todo);
            handleChange();
          }}
        ></input>
        Todo terminée
      </label>
    </div>
  );
}

 



      //                 <Checkbox
      //   inputId={todo.id}
      //   value="Todo Terminée"
      //   onChange={onTodoChange}
      //   checked={todos.includes("Todo Terminée")}
      // ></Checkbox>
      // <label htmlFor={id} className="p-checkbox-label">
      //   Todo Terminée
      // </label>
