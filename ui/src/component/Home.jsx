import {React, useEffect, useState} from 'react'
import {Link} from "react-router-dom";
import toast, { Toaster } from "react-hot-toast";
import Header from './Header'

export default function Home() {

/* STATES */

  let [todos, setTodos] = useState([]);
  let [id, setId] = useState();


/* EFFECTS */

const getTodos = () => {
    fetch("http://localhost:8080/api/")
    .then(response => response.json())
    .then(datas => {
      setTodos(datas);
    })
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
        "Chargement..."
      ) : (
        <ul className="list-group list-group-flush w-50 m-auto mb-5">
          {todos.map((todo) => (
            <div className="list-group-item list-group-item-action p-4 d-flex justify-content-between h3">
              <>
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
                <div className="form-check">
                  <input
                    className="form-check-input"
                    type="checkbox"
                    value="Completed"
                    id="state"
                  ></input>
                  <label className="form-check-label" htmlFor="completed">
                    Todo terminée
                  </label>
                </div>
              </>
            </div>
          )).reverse()}
        </ul>
      )}
    </>
  );}
    


