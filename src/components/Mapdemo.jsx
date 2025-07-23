import React from "react"

export const MapDemo = () => {
    var users  = ["Raj" , "Yash" , "Rahul" , "Vijay"];
    var students = [
        {
            id:1,
            name:"Yash",
            age:20
        } , 
        {
            id:2,
            name:"Rahul",
            age:21
        } ,
        {
            id:3,
            name:"Raju",
            age:22
        }

    ];
    return (
        <div>
           {users.map((user)=>{
            return (
                <p>{user}</p>
                
            )

           }
        )}

        {
            users.map((user)=>
            {
                return (
                    <p style = {{color:user.length>3?"green":"blue"}}>{user}</p>
                )
            }
            )
        }

        <h1>STUDENTS</h1>
        {
            students.map((stu)=>{
                return (
                    <div>
                    <p>ID is {stu.id}</p>
                    <p>Name is {stu.name}</p>
                </div>
                )
            })
        }
        </div>
    )
}
