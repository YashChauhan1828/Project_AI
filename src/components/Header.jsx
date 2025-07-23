
import React from 'react'

export const Header = (props) => {
    const homeStyle = {
        height:"300px",
        backgroundColor:"yellow"
    }
    return (
        <div style = {homeStyle}> 
            <h1 style = {{color:"red"}}>
                Welcome to my website
            </h1>
            <h2>{props.t}</h2>
            <h3>{props.c}</h3>
        </div>
    )
}
