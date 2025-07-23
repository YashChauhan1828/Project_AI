
import React from 'react'

export const Footer = (x) => {
    const homeStyle = {
        height:"300px",
        backgroundColor:"aqua"
    }
    return (
        <div style = {homeStyle}> 
            <h1 style = {{color:"red"}}>
                Welcome to my website
            </h1>
            <h2>{x.t}</h2>
        </div>
    )
}
