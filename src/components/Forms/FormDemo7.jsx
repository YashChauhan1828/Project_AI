import axios from "axios";
import React from "react";
import { useForm } from "react-hook-form";
import { useNavigate } from "react-router-dom";
import { Bounce, toast, ToastContainer } from "react-toastify";

export const FormDemo7 = ()=>
{
    // const navigate = useNavigate()
    const {register,handleSubmit} = useForm({mode:"all"})
    const submithandler=async(data)=>
    {
        const res = await axios.post("https://node5.onrender.com/user/user",data)
        console.log(res.data.data)
        console.log(res.status)
        if(res.status==201)
        {
            toast.success('🦄Data added sucessfully', {
                position: "top-center",
                autoClose: 4000,
                hideProgressBar: false,
                closeOnClick: false,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
                theme: "dark",
                transition: Bounce,
                });  
        //         setTimeout(()=>{
        //             navigate("/apidemo1")    
        //         },5000) 
        }
    }
    return(
        <div>
            <ToastContainer
                position="top-center"
                autoClose={4000}
                hideProgressBar={false}
                newestOnTop={false}
                closeOnClick={false}
                rtl={false}
                pauseOnFocusLoss
                draggable
                pauseOnHover
                theme="dark"
                transition={Bounce}
            />
            <form onSubmit={handleSubmit(submithandler)}>
                <h1>Registration Form</h1>
                <label>Name:</label><input type="text" {...register("name")}/><br></br>
                <label>Age:</label><input type="number" {...register("age")}/><br></br>
                <label>Email:</label><input type="email" {...register("email")}/><br></br>
                <input type="submit" value="Submit"/>            
            </form>
        </div>
    )
}