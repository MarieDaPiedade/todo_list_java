import { Route, Routes } from "react-router-dom";
import React from "react";
import Home from './Home';
import AddTodo from './AddTodo'
import DetailsTodo from './DetailsTodo'
import Error404 from './Error404';
import 'bootstrap/dist/css/bootstrap.css';


function App() {
  return (
      <Routes>
        <Route path="/" exact element={<Home />} />
        <Route path="/save" element={<AddTodo />} />
        <Route path="/get/:id" element={<DetailsTodo />} />
        <Route path="*" element={<Error404 />} />
      </Routes>
  );
}

export default App;
