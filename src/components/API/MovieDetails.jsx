import axios from "axios";
import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import Loading from "./Loading";

export const MovieDetails = ()=>
{
    const [loading,setloading] = useState(false)
    const [display,setdisplay] = useState(false)
    const [ratings,setratings] = useState([])
    const [movie, setMovie] = useState([])
    const id = useParams().id
    const getmoviedetails=async()=>
    {
        setloading(true)
        const res = await axios.get("https://www.omdbapi.com/",
            {
                params:{
                    i:id,
                    apikey:"ff9649ad"
                }
            }
        )
        setloading(false)
        console.log(res.data)
        setMovie(res.data)
        setdisplay(true)    
        setratings(res.data.Ratings[0])
    }
    useEffect(()=>{
        getmoviedetails()
    },[])
    return(
        <div>
            <h1>Movie Details</h1>
           {
                loading && <Loading></Loading>
           }
           <div className="card-group">
            <div className="card">
                <img src={movie.Poster} alt=""/>
                </div>
            {
                display &&
                <div className="card" style={{width:"18rem",textAlign:"left",paddingLeft:"10px"}}>
    
             <b><p>Title : {movie.Title}</p>
                <p>Type : {movie.Type}</p>
                <p>Year : {movie.Year}</p>
                <p>IMDBID : {movie.imdbID}</p>
                <p>Rated : {movie.Rated}</p>
                <p>Actors : {movie.Actors}</p>
                <p>Awards : {movie.Awards}</p>
                <p>Country : {movie.Country}</p>
                <p>Director : {movie.Director}</p>
                <p>Genre : {movie.Genre}</p>
                <p>Language : {movie.Language}</p>
                <p>Metascore : {movie.Metascore}</p>
                <p>Plot : {movie.Plot}</p>
                <p>Source : {ratings.Source}</p>
                <p>Value : {ratings.Value}</p>
                <p>Released : {movie.Released}</p>
                <p>Response : {movie.Response}</p>
                <p>Writer : {movie.Writer}</p>
                <p>IMDBRating : {movie.imdbRating}</p>
                <p>IMDBVotes : {movie.imdbVotes}</p>
                <p>TotalSeason : {movie.totalSeasons}</p>
            </b>
    
            </div>
        }
           </div>
         
        </div>
    )
}