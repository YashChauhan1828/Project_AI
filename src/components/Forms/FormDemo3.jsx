import React from "react"
import { useForm } from "react-hook-form"

export const FormDemo3=()=>
{
    const {register,handleSubmit,formState:{errors}} = useForm()
    const submithandler=(data)=>
    {
        console.log(data);
    };
    var validation = {
        emailvalidation:{
            required:{
                value:true,
                message:"Email is required"
            },
            minLength:{
                value:5,
                message:"Length should be greater than 5"
            }
        }
    }
    console.log("Error : ",errors)
    return(
        <div>
            <form onSubmit={handleSubmit(submithandler)}>
                <label>Name : </label><input type="text" placeholder="Enter your name" {...register("name",{required:{value:true,message:"Name is required"}})}/>
                {/* <span>{errors.name?.message}</span> */}
                {
                    errors.name && <span>{errors.name.message}</span>
                }
                <br></br>
                <label>Email</label> : <input type="text" placeholder="Enter your email" {...register("email",validation.emailvalidation)}/>
                {/* <span>{errors.email?.message}</span> */}
                {
                    errors.email && <span>{errors.email.message}</span>
                }
                <br/>
                <input type="submit" value="submit"/>
            </form>
        </div>
    )
}