import React, { useState } from "react"
import { useForm } from "react-hook-form"

export const FormDemo5=()=>
{
    const {register,handleSubmit,formState:{errors}} = useForm({mode:"onChange"})
    
    
    const submithandler=(data)=>
    {
        console.log(data)
        
    }
    
    var validation = {
        namevalidation:{
            required:{
                value:true,
                message:"Name is required"
            }
        },
        phonevalidation:{
            required:{
                value:true,
                message:"Phone is required",
                
            },
            pattern:{
                value:/[6-9]{1}[0-9]{9}/,
                message:"Invalid phone number"
            }
        },
         refcodevalidation:{
            required:{
                value:true,
                message:"Refcode is required"
            },
            match:{
                value:"pass1",
                message:"Refcode does not match"
            }
            }
    }
    
    return(
        <div>
            <form onSubmit={handleSubmit(submithandler)}> 
                <label>Name : </label><input type="text" placeholder="Enter your name" {...register("name",validation.namevalidation)}/>
                {
                    errors.name && <span>{errors.name.message}</span>
                }
                <br/>
                <label>Phone Number : </label><input type="text" placeholder="Enter your phone number" {...register("phone",validation.phonevalidation)}/>
               
                {
                    errors.phone && <span>{errors.phone.message} </span>
                }
                <br/>
                <label>Refcode : </label><input type="text" placeholder="Enter Refcode" {...register("refcode",validation.refcodevalidation)}/>
                {
                    errors.refcode && <span>{errors.refcode.message}</span>
                }
                <br/>       
                <input type="submit" value="submit"/>
            </form>
        </div>
    )
}