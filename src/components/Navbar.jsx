import React from "react"
import { Link } from "react-router-dom"

export const Navbar = () => 
{
    return (
        <div>
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <Link class="navbar-brand" to = "/dashboard">Netflix</Link>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <Link class="nav-link" to="/netflixhome">Home</Link>
        
      </li>
      <li>
        <Link class="nav-link" to="/netflixmovies">Movies</Link>
      </li>

      <li>
        <Link class="nav-link" to="/formdemo1">FormDemo1</Link>
      </li>

      <li>
        <Link class="nav-link" to="/loginpage">Login</Link>
      </li>

      <li>
        <Link class="nav-link" to="/formdemo8">FormDemo8</Link>
      </li>


      <li>
        <Link class="nav-link" to="/formdemo2">FormDemo2</Link>
      </li>

      <li>
        <Link class="nav-link" to="/formdemo3">FormDemo3</Link>
      </li>

      <li>
        <Link class="nav-link" to="/formdemo4">FormDemo4</Link>
      </li>

      <li>
        <Link class="nav-link" to="/products">Product</Link>
      </li>

      <li>
        <Link class="nav-link" to="/usememo">Usememo</Link>
      </li>

      <li>
        <Link class="nav-link" to="/apidemo1">ApiDemo1</Link>
      </li>

      <li>
        <Link class="nav-link" to="/apidemo3">ApiDemo3</Link>
      </li>

      {/* <li>
        <Link class="nav-link" to="/formdemo5">FormDemo5</Link>
      </li> */}

      <li>
        <Link class="nav-link" to="/formdemo7">FormDemo7</Link>
      </li>

      <li>
        <Link class="nav-link" to="/inputdemo1">InputDemo1</Link>
      </li>

      <li>
        <Link class="nav-link" to="/hello">Mcq</Link>
      </li>
     
      <li>
        <Link class="nav-link" to="/usestatedemo2">UsestateDemo2</Link>
      </li>
     
      <li>
        <Link class="nav-link" to="/apidemo2">Apidemo2</Link>
      </li>

     

      <li>
        <Link class="nav-link" to="/omdb1demo">Omdb1</Link>
      </li>


      <li>
        <Link class="nav-link" to="/nodes">NotesApp</Link>
      </li>

   

    </ul>
  </div>
</nav>
        </div>
    )
}