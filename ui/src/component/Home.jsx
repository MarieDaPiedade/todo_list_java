import {React, useEffect, useState} from 'react'
import Header from './Header'

export default function Home() {

/* STATES */

const [error, setError] = useState(null);
  const [isLoaded, setIsLoaded] = useState(false);
  const [todos, setTodos] = useState([]);

/* EFFECTS */


const getDatas = () => {
    const headers = {'Content-Type': 'application/json'};
        let URL = "http://localhost:8080/api/";
        fetch(URL, headers)
        .then(response => console.log(response.json()))
        .then(
            (data) => {
                setIsLoaded(true);
                setTodos(data.todos);
                console.log(data);
            },
            (error) => {
                setIsLoaded(true);
                setError(error);
            }
            )
        }

        useEffect(getDatas, []);


  /* VIEW */

  return (
    <>
        <Header />
        <div className="w-50 m-auto d-flex justify-content-end mb-5 mt-5">
		    <a className="btn btn-info btn-lg text-white" href="/save" role="button">CRÉER UNE TODO</a>
	    </div>

        <ul className="list-group list-group-flush w-50 m-auto mb-5">
            {error && error.message} 
            {!isLoaded ? "Chargement..." :

            <div className="list-group-item list-group-item-action p-4 d-flex justify-content-between">
				<div className="a">
                    {todos.map(todo => (
                        <a key={todo.title} todo={todo} href="" target='_blank' className="title text-info">
                            {todo.title} 
                        </a>
                        ))}
				</div>
				<div className="form-check">
					<input className="form-check-input" type="checkbox" value="Completed" id="state"></input>
					<label className="form-check-label" htmlFor="completed">
						Todo terminée
					</label>
				</div>
			</div>}
                
        </ul> 
    </>
  )}
    


