import {React, useEffect, useState} from 'react'
import { useParams } from 'react-router-dom';
import Header from './Header'

export default function DetailsTodo() {

  /* STATES */

  let [todo, setTodo] = useState([]);
  const {id} = useParams();

  /* EFFECTS */

  const getTodo = () => {
    fetch(`http://localhost:8080/api/get/${id}`)
      .then((response) => response.json())
      .then((data) => {
        setTodo(data);
      })
      .catch((error) => {
        console.error("Une erreur est survenue", error);
      });
  };

  useEffect(() => {
    getTodo();
  }, []);

  /* VIEW */

  return (
    <>
      <Header />
      <div key={todo.id} className="card border-info mb-3 w-50 m-auto">
        <div
          className="card-header bg-info display-4"
        >
          {todo.title}
        </div>
        <div className="card-body p-5">
          <h5 className="card-title mb-4 display-6 text-decoration-underline">
            Détails
          </h5>
          <p className="card-text h3">{todo.description}</p>
        </div>
      </div>
    </>
  );
}
