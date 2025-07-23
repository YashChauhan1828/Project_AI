import React, { useState } from "react"

export const UseStateDemo2 = () => {
    
    const [isLoading , setisLoading] = useState(true)

    const setloader = () =>
    {
        setisLoading(false)
    }
    
    return (
        <div>
            <h1>UseState Demo 2</h1>
            {
                isLoading ? <h2>Loading...</h2>:""
            }
            {
                isLoading && <h2>Loader..</h2>
            }
            <button onClick={()=>setloader()}>Stop Loading</button>
            <button onClick={()=>setisLoading(false)}>Stop Loading 2</button>
        </div>
    )
}