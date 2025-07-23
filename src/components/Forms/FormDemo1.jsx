import React, { useState } from "react";
import { useForm } from "react-hook-form";

export const FormDemo1 = () =>
{
    const {register,handleSubmit}= useForm()
    const [display,setdisplay] = useState({})   
    const [isSubmited,setisSubmited] = useState(false)
    const submithandler = (data) =>
    {
        // alert("Form Submitted")
        console.log(data)
        setdisplay(data)
        setisSubmited(true)
    }
    return (
        <div>
            <h1>Form Demo 1</h1>
            <form onSubmit={handleSubmit(submithandler)}>
                <label>Name : </label><input type = "text" placeholder="Enter your name" {...register("name")}/><br></br>
                <label>Age : </label><input type = "number" placeholder = "Enter the age" {...register("age")}/><br></br>
                <label>Gender : </label><br></br>
                Male : <input type="radio" value="male" {...register("gender")}/><br></br>
                Female : <input type="radio" value="female" {...register("gender")}/><br></br><br></br>
                <label>Select Country : </label><br></br>
                <select {...register("Country")}>
                    <option value="India">India</option>
                    <option value="USA">USA</option>
                    <option value="Australia">Australia</option>
                </select><br></br><br></br>
                <lable>
                    Color : <input type="color" {...register("color")}/><br></br><br></br>
                </lable>
                <input type="submit" value="submit"/>
            </form>
            {
                isSubmited &&
            <div style={{color:display.color}}>
                <h1>Result</h1>
                <h2>Name :- {display.name}</h2>
                <h2>Age :- {display.age}</h2>
                <h2>Gender :- {display.gender}</h2>
                <h2>Country :- {display.Country}</h2>
            </div>
            }
        </div>
    )
}