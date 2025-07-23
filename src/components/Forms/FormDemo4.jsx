import React from "react"
import { useForm } from "react-hook-form"

export const FormDemo4 =()=>
{
    const {register,handleSubmit,formState:{errors}}=useForm({mode:"all"})
    const submithandler=(data)=>
    {
        console.log(data)
    }
    var validation={
        namevalidation:{
            required:{
                value:true,
                message:"Name is required"
            },
            minLength:{
                value:3,
                message:"Name should be greater than 3"
            },
            maxLength:{
                value:12,
                message:"Name should not be longer than 12"
            }
        }

    }
    return(
        <div>
            <h1>Sign Up</h1>
            <form onSubmit={handleSubmit(submithandler)}>
            <label>Name : </label><input type="text" placeholder="Enter your name" {...register("name",validation.namevalidation)}/>
            {
                errors.name && <span>{errors.name.message}</span>
            }
            <br/>
            <input type="submit" value="submit"/>
            </form>
        </div>
    )
}
