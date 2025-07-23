import React, { useState } from "react"
import { useForm } from "react-hook-form"
export const FormDemo2 = () =>
{
    const {register,handleSubmit} = useForm()
    const [display,setdisplay] = useState({})
    const [isSubmited,setisSubmited] = useState(false)
    const submithandler = (data) =>
    {
        console.log(data)
        setdisplay(data)
        setisSubmited(true)
    }
    return(
        <div>
            <form onSubmit={handleSubmit(submithandler)}>
                <label>Name </label>: <input type="text" placeholder="Enter your name" {...register("name")}/><br/>
                Age : <input type="number" placeholder="Enter your age" {...register("age")}/><br/>
                Gender : Male <input type="radio" name="gender" value="male" {...register("gender")}/>
                        Female <input type="radio" name="gender" value ="female" {...register("gender")}/><br/>
                Hobbies : Cricket <input type="checkbox" name="hobby" value="cricket" {...register("hobby")}/>
                          VolleyBall <input type="checkbox" name="hobby" value="VolleyBall" {...register("hobby")}/>
                          Badminton <input type="checkbox" name="hobby" value="Badminton" {...register("hobby")}/><br/>
                Email : <input type="email" placeholder="Enter your email" {...register("email")}/><br/>
                Phone : <input type="tel" placeholder="Enter your phone number" {...register("phone")}/><br/>
                Color : <input type="color" {...register("color")}/><br/>
                <input type="submit" value="submit"/>
            </form>
            { isSubmited &&
            <div style={{color:display.color}}>
                <h1>Result</h1>
                <h2>Name : {display.name}</h2>
                <h2>Age : {display.age}</h2>
                <h2>Gender : {display.gender}</h2>
                <h2>Hobbies : {display.hobby}</h2>
                <h2>Email : {display.email}</h2>
                <h2>Phone : {display.phone}</h2>
            </div>
            }
        </div>
    )
}