import React from "react"

export const College = (props) =>
{
    return (
        <div>
            <h1>College</h1>
            <button onClick={()=>{props.status()}}>Click me</button>
            <button onClick={props.status}>University status from college component</button>
        </div>
    )
}