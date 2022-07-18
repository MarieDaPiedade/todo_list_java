import { React } from 'react'

export default function CheckboxTodo(props) {

  const { id, label, value, onChange} = props;

  // const handleChange = () => {
  //  // setId(todo.id);
  // // setTodo(e);
  //   setChecked(!checked);
  //   console.log(todo.id);
  //   console.log(todo);
  //   let selectedTodos = [...todosChecked];
  //   if(todo.checked === true) {

  //     selectedTodos.push(todo.id.value);
  //   } else {
  //     selectedTodos.splice(selectedTodos.indexOf(todo.id.value), 1);
  //   }
  //   setTodosChecked(selectedTodos);
  //   console.log(selectedTodos);
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
 // }

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
      <input
        className="form-check-input"
        id={id}
        type="checkbox"
        checked={value}
        onChange={onChange}
      />
      <label className="form-check-label" htmlFor={id}>
        {label}
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
