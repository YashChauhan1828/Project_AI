import axios from "axios"
import React, { useEffect, useState } from "react"
import Loading from "./Loading"
import { useNavigate, useParams } from "react-router-dom"

export const Omdb1 = () =>
{
    const moviename = useParams().movie
    const [isloading,setisLoading]=useState(false)
    const [movies,setmovies] = useState([])
    const navigate = useNavigate()
    const getmovies = async() =>
    {
        
        setisLoading(true)
        try{
        const res = await axios.get("https://www.omdbapi.com/",
            {
                params: {
                    s:moviename,
                    apikey:"ff9649ad"
                }
            }
        )
         console.log(res.data.Search)
        setmovies(res.data.Search)
    }
    catch(err)
    {
        console.log(err)
    }
        setisLoading(false)
        
    }
    useEffect(()=>{
        getmovies()
    },[moviename])
    const moviehandle=(id)=>
        {
            navigate(`imdbID/${id}`)
        }
    return(
        <div style={{backgroundColor:"aqua"}}>
            {/* <button onClick={()=>{getmovies()}}>get</button> */}
            {
                isloading && <Loading></Loading>
            }
            {/* <table className="table table-dark">
                <thead>
                    <tr>Poster</tr>
                </thead>
                <tbody>
                    {
                        movies?.map((movie=>{
                            return<tr>
                                <td><img src={movie.Poster} alt=""/></td>
                            </tr>
                        }))
                    }
                </tbody>
            </table> */}
            
            <div classname="container mt-4">
                
                    <div className="row">
            {
                        movies?.map((movie=>{
                            return<div className="col-lg-4 lg-4">
                                <div className="card h-100 shawdow"> 
                            <button  onClick={()=>{moviehandle(movie.imdbID)}}>
                                        <img style={{height:"400px",width:"400px"}} src={movie.Poster} alt=""/>
                                    </button>
                                </div>
                                </div>
                                {/* <td>{movie.imdbID}</td> */}
                            
                        }))
                    }
                    </div>
            {/* <h1>movie:{moviename}</h1> */}
            </div>
        </div>
    )
}