import React from "react"
import { useForm } from "react-hook-form"

export const Login=()=>
{
    const {register,handleSubmit} = useForm({mode:"all"})
    const userObj=[
        {
            id:"1",
            name:"yash",
            email:"chauhanyash1804@gmail.com",
            password:"yash@123",
            role:"admin"
        },
        {
            id:"2",
            name:"ramesh",
            email:"ramesh@gmail.com",
            password:"ramesh@123",
            role:"user"
        },
        {
            id:"3",
            name:"Jadoo",
            email:"jadoo@gmail.com",
            password:"jadoo@123",
            role:"user"
        },
    ]

    const submithandler=(data)=>
    {
        console.log(data)
        const foundUser = userObj.find((user)=>{
            return (data.email == user.email && data.password == user.password)
        })
        if(foundUser)
            {
                alert("Login sucessfully")
                localStorage.setItem("role",foundUser.role)
                localStorage.setItem("name",foundUser.name)
            } 
    }
    return(
        <div>
            <form onSubmit={handleSubmit(submithandler)}>
                <label>Email:</label><input type="email" placeholder="Enter your Email" {...register("email")}/><br/>
                <label>Password:</label><input type="password" placeholder="Enter your Password" {...register("password")}/><br/>
                <input type="submit" value="submit"/>
            </form>
        </div>
    )
}