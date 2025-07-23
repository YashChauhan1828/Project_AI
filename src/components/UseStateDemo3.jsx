import React, { useState } from "react"

export const UseStateDemo3 = () => {
   const [users , setusers] = useState({
    id:1,
    name: "John",
    age:25
   })

   const changeData = () =>
   {
    return (
        // setusers({
        // id:users.id+1,
        // name:users.name.toUpperCase(),
        // age:users.age+1
        // })
        setusers(
            {
                ...users,
                name:users.name.toUpperCase(),
                salary:1000   
            }
           )   
    )
    
   }
   // Spread Operator

 

    return (
        <div>
            <h1>Id = {users.id}</h1>

            <h1>
                Name = {users.name}
            </h1>
            <h1> Age = {users.age} </h1>
            {/* <h1> Salary = {users.salary}</h1> */}
            {
                users.salary && <h1> Salary = {users.salary}</h1> 
            }
        <button onClick={()=>{changeData()}}>Change data</button>
        </div>
    )
}
