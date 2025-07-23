import React, { useState } from "react"

export const InputDemo = ()=>
{
    const [username,setusername] = useState("")
    const [age,setage] = useState("")
    const [display,setdisplay] = useState(false)
    const nameHandler=(event)=>
    {
        console.log(event.target.value)
        setusername(event.target.value)
        
        
    }
    const submitHandler=()=>
    {
        setdisplay(true)
    }
    return(
        <div>
            <label>Name : </label><input type="text" placeholder="Enter your name" onChange={(event)=>{nameHandler(event)}}></input>
            <br></br>
            <label>Age : </label><input tye="number" placeholder="Enter your age" onChange={(event)=>{setage(event.target.value)}}></input>
            <br></br>
            <button onClick={()=>{submitHandler()}}>Add</button>

            { display &&
                <div>
                    <h1>Result</h1>
                    <h2>Name : {username} </h2>
                    <h2>Age : {age}</h2>
                </div>
            }
        
        </div>
    )
}