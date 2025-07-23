import React from "react"
import { MyButton } from "./MyButton"
export const Hello = () =>
{
    const myfunction=()=>
    {
        alert("Called!!")
    }
    return(

        <div>
            <h1>Hello World</h1>
            <MyButton name="detail" class="btn btn-info" funcname={myfunction}></MyButton>
        </div>
    )
}