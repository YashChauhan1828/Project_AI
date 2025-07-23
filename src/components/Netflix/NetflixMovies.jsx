import React from "react"
import { Link } from "react-router-dom"

export const NetflixMovies = () =>
{
    const movies = [
        {
            id:104,
            name:"Housefull"
        },
        {
            id:105,
            name:"Housefull 2"
        },
        {
            id:105,
            name:"Housefull 3"
        },
        {
            id:106,
            name:"Housefull 4"
        },
    ];
    return (
        <div>
            <h1>Netflix Movies</h1>
            <ul>
                <li><Link to="/netflixmovies/play/101">Puspa:The Rise</Link></li>
                <li><Link to="play/102">Puspa:The Rule</Link></li>
                <li><Link to="play/103">KGF</Link></li>
            </ul>
            <ul>
                {
                    movies.map((movie)=>
                    {
                        return <li>
                                <Link to={`play/${movie.id}`}>{movie.name}</Link>
                            </li>
                        
                    })
                }
            </ul>
        </div>
    )
}