import React from 'react'
import '../css/error404.css'
import { Link } from "react-router-dom";

export default function Error404() {
  return (
    <>
      <div className="background bg-info">
        <div className="c">
          <div className="_404">404</div>
          <hr />
          <div className="_1">THE PAGE</div>
          <div className="_2">WAS NOT FOUND</div>
          <Link to="/">
            <div className="btn btn-light btn-block text-info mt-3">BACK TO HOME</div>
          </Link>
        </div>
      </div>
    </>
  );
}
