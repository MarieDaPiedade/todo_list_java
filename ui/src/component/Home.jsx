import {React, useEffect, useState} from 'react'
import {Link, useNavigate} from "react-router-dom";
import { Toaster } from "react-hot-toast";
import Header from './Header'
import axios from 'axios'

export default function Home() {

/* STATES */

  let [todos, setTodos] = useState([]);
  let [id, setId] = useState();
  let navigate = useNavigate();

  const todoState = "Todo";
  const completedState = "Completed";
  const URL = "http://localhost:8080/api/";

/* EFFECTS */

const getTodos = () => {
    fetch(URL)
      .then((response) => response.json())
      .then((datas) => {
        setTodos(datas);
      })
      .catch((error) => {
        console.error("Une erreur est survenue", error);
      });
  };

        useEffect(() => {
            getTodos();
            setTodos(
              todos.map(todo => {
                return {
                  select:false,
                  id: todo.id, 
                  title: todo.title, 
                  state: todo.state,
                  description: todo.description
                }
              })
            );
        }, []);

  const updateState = (todo) => {
    if(todo.select === true) {
      axios.put(URL + `update/${todo.id}`, todo)
      .then((response) => {
        console.log(response.data);
        navigate("/");
        getTodos();
      })
      .catch(error => {
        console.error("Une erreur est survenue", error);
      });
    }
  }

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
        <div className="w-50 m-auto h3">"Aucune Todo pour le moment..."</div>
      ) : (
        <ul className="list-group list-group-flush w-50 m-auto">
          {todos
            .map(
              (todo) =>
                todo.state === todoState && (
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
                    <div>
                      <label>
                        <input
                          onChange={(event) => {
                            let checked = event.target.checked;
                            setTodos(
                              todos.map((data) => {
                                if (todo.id === data.id) {
                                  data.select = checked;
                                  updateState(todo);
                                }
                                return data;
                              })
                            );
                          }}
                          type="checkbox"
                          checked={todo.select}
                        />{" "}
                        Todo terminée
                      </label>
                    </div>
                  </div>
                )
            )
            .reverse()}
        </ul>
      )}

      <ul className="list-group list-group-flush w-50 m-auto mb-5">
        {todos
          .map(
            (todo) =>
              todo.state === completedState && (
                <div className="list-group list-group-flush">
                  <a className="list-group-item list-group-item-action p-4 text-decoration-line-through text-danger disabled h3">
                    {todo.title}
                  </a>
                </div>
              )
          )
          .reverse()}
      </ul>
    </>
  );
}
    


