import React, { useState } from "react"
export const UseStateDemo = ()=>
{
    var [count , setCount] = useState(0)
    // count-->set variable
    // setCount-->setter function
    // useState-->Hook
    // const [isActive , setisActive] = useState(true) // boolean
    // const [users , setusers] = useState([]) // array
    // const [user , setuser] = useState({name:"Rahul" , age:25}) // JSON object
    // const [obj , se.tobj] = useState({}) // object
    // var count = 0;

    const increment = ()=>{
        // count +=1;
        setCount(count+1)
        console.log(count);
    }
    
    const decrement = ()=>{
        // count +=1;
        setCount(count-1)
        console.log(count);
    }
    return(
        <div>
            <h1>Use State Demo</h1>
            <h2>count = {count}</h2>
            <button onClick = {()=>{increment()}}>Increment +</button>&nbsp;
            <button onClick={()=>{decrement()}}>Decrement -</button>
        </div>
    )
}