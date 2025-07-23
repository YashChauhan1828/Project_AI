
import React from 'react'

export const Home = (props) => {
    const homeStyle = {
        height:"300px",
        backgroundColor:"green"
    }
    return (
        <div style = {homeStyle}> 
            <h1 style = {{color:"red"}}>
                Welcome to my website
            </h1>
            <h2>Country = {props.a.country}</h2>
            <h3>State = {props.a.state}</h3>
        </div>
    )
}
