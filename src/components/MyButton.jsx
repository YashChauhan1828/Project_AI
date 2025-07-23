import React from "react";

export const MyButton=(props)=>
{
    return(
        <div>
            <button className={props.class} onClick={()=>{props.funcname()}}>  {props.name || "test"}</button>
        </div>
    )
}