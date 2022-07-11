import {React, useEffect, useState} from 'react'
import Header from './Header'

export default function Home() {

/* STATES */

//const [error, setError] = useState(null);
 // const [isLoading, setLoading] = useState(true);
 // const [data, setData] = useState([]);
  const [todos, setTodos] = useState([]);

/* EFFECTS */


// const getDatas = () => {
//     const headers = {'Content-Type': 'application/json'};
//     const URL = "http://localhost:8080/api/";
//      fetch(URL, headers)
//         .then(response => console.log(response.json()))
//         .then(
//             json => {
//                 setIsLoaded(true);
//                 setTodos(json.todos);
//                 console.log(json);
//             },
//             error => {
//                 setIsLoaded(true);
//                 setError(error);
//             }
//             )
//         }

//  const getTodos = async () => {
//     const URL = "http://localhost:8080/api/";
//      try {
//       const response = await fetch(URL);
//       const json = await response.json();
//       setData(json.todos);
//     } catch (error) {
//       console.error(error);
//     } finally {
//       setLoading(false);
//     }
//   }

const getTodos = async () => {
    const response = await fetch(
      "http://localhost:8080/api"
    ).then((response) => response.json());
    setTodos(response.response);
    console.log(response.response);
    console.log('ok');
  };

        useEffect(() => {
            getTodos()
        }, []);


  /* VIEW */

  return (
    <>
        <Header />
        <div className="w-50 m-auto d-flex justify-content-end mb-5 mt-5">
		    <a className="btn btn-info btn-lg text-white" href="/save" role="button">CRÉER UNE TODO</a>
	    </div>

        {/* <ul className="list-group list-group-flush w-50 m-auto mb-5">
            {!isLoading ? "Chargement..." :

            <div className="list-group-item list-group-item-action p-4 d-flex justify-content-between">
				<div className="a">
                    {data.map(todo => (
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
        </ul>  */}
        <div className="app">
      {todos &&
        todos.map((todo) => (
          <div className="item-container">
            Id:{todo.id} <div className="title">Title:{todo.title}</div>
          </div>
        ))}
    </div>

     
    </>
  )}
    


