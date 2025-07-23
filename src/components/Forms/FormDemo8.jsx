import React, { useState } from "react";


import { useForm } from "react-hook-form";
import {  useNavigate } from "react-router-dom";

export const FormDemo8 = () =>
{
    const navigate = useNavigate() 
    const{register,handleSubmit} = useForm({mode:"all"})
    const submithandler=(data)=>
    {
        console.log(data)
        navigate(`search_query=/${data.moviename}`)
    }
    return (
        <div >
            <h1>Movie Search</h1><br></br>
            <form onSubmit={handleSubmit(submithandler)}>
                <label>Movie:</label><input type="text" placeholder="Enter movie name" {...register("moviename")}/><br></br>
                {/* <Link to={`query/${movie.moviename}`}><input type="submit" value="Search"></input></Link> */}
                <input type="submit" value="submit"/>
            </form>
            {/* <h1>moviename:{movie.moviename}</h1> */}
        </div>
    )
}