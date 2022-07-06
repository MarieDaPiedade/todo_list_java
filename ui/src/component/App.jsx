import { Route, Routes } from "react-router-dom";
import React from "react";
import Home from './Home';
import Error404 from './Error404';

function App() {
  return (
      <Routes>
        <Route path="/" exact element={<Home />} />
        <Route path="*" element={<Error404 />} />
      </Routes>
  );
}

export default App;
