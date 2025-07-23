import React from "react";
import { useMemo } from "react";
import { useState } from "react";

export const UseMemo=()=>
{
    const [query,setquery] = useState("")
    const [items,setitems] = useState([
        "apple",
        "banana",
        "cherry",
        "watermelon",
        "grapes"
    ])

    const filterdata=useMemo(()=>
    {
        console.log("filtering value")
        return items?.filter((i)=>{
            return i.toLowerCase().includes(query.toLowerCase())
        })
    },[query,items])
    return(
        <div>
            <input type="text" placeholder="Enter the fruit" onChange={(event)=>setquery(event.target.value)}></input>
            <div>
            {
                filterdata.map((i)=>
                {
                    return <li>{i}</li>
                })
            }
            <button onClick={()=>{setitems([...items,"kiwi"])}}>Add</button>
            </div>
        </div>
    )
}