import {React, useEffect, useState} from 'react'
import {Link, useNavigate, useParams} from "react-router-dom";
import toast, { Toaster } from "react-hot-toast";
import Header from './Header'
import CheckboxTodo from './CheckboxTodo'

export default function Home() {

/* STATES */

  let [todos, setTodos] = useState([]);
  let [id, setId] = useState();
  let [checked, setChecked] = useState(false);

  const props = { todos, setTodos, id, setId, checked, setChecked };

/* EFFECTS */

const getTodos = () => {
    fetch("http://localhost:8080/api/")
      .then((response) => response.json())
      .then((datas) => {
        setTodos(datas);
      })
      .catch((error) => {
        console.error("Une erreur est survenue", error);
      });
  };

        useEffect(() => {
            getTodos()
        }, []);

  /* VIEW */

  return (
    <>
      <Header />
      <Toaster />
      <div className="w-50 m-auto d-flex justify-content-end mb-5 mt-5">
        <a
          className="btn btn-info btn-lg text-white"
          href="/save"
          role="button"
        >
          CRÉER UNE TODO
        </a>
      </div>

      {todos.length === 0 ? (
        "Aucune Todo pour le moment..."
      ) : (
        <ul className="list-group list-group-flush w-50 m-auto mb-5">
          {todos
            .map((todo) =>
              todo.state === "Todo" ? (
                <div className="list-group-item list-group-item-action p-4 d-flex justify-content-between h3">
                  <div className="a">
                    <Link
                      to={`get/${todo.id}`}
                      target="_blank"
                      className="text-info"
                      key={id}
                      onClick={() => {
                        setId(todo.id);
                      }}
                    >
                      {todo.title}
                    </Link>
                  </div>
                  <CheckboxTodo
                    {...props}
                    todo={todo}
                    onChange={(e) => setChecked(e.checked)}
                    checked={checked}
                  />
                </div>
              ) : (
                <div class="list-group list-group-flush remove">
                  <a class="list-group-item list-group-item-action p-4 text-decoration-line-through text-danger disabled">
                    {todo.title}
                  </a>
                </div>
              )
            )
            .reverse()}
        </ul>
      )}
    </>
  );
}
    


