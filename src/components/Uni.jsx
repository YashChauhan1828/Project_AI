import React from "react"
import { College } from "./College"

export const Uni = ()=>
{
    const checkUni=()=>
    {
        console.log("Function uni called...")
    }
    return (
        <div>
            <h1>UniVersity</h1>
            <button onClick={checkUni}>Click me</button>
            <button onClick={()=>{checkUni()}}>Call function uni</button>
            <College status = {checkUni}></College>
        </div>
    )
}