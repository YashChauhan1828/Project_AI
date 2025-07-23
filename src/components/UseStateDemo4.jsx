import React, { useState } from "react"

export const UseStateDemo4 = () =>
{
    const [area,setArea] = useState(0)
    const [radius , setradius] = useState(0)
    const [display,setdisplay] = useState(false)
    const Increaseradius=()=>
    {
        setradius(radius+1)
    }
    const Decreaseradius=()=>
    {
        setradius(radius-1)
    }

    const CalculateArea=(radius)=>
    {
        if(radius>=0)
        {
            setArea(Math.PI*Math.pow(radius,2))
            setdisplay(false)
        }
        else
        {
            setArea(0)
            setdisplay(true)
        }
    }
    return(

        <div>
            <h1>Area of Circle</h1>
           
            <h3>Radius : {radius}</h3>
            <button onClick={()=>{Increaseradius()}}>Increment Radius +</button>&nbsp;
            <button onClick={()=>{Decreaseradius()}}>Decrement Radius -</button><br></br><br></br>
            <button onClick={()=>{CalculateArea(radius)}}>Calculate Area </button>
            <h3>Area : {area} </h3>
            {
                display && <h3>Radius cannot be negative</h3> 
            }
        </div>
    )
}