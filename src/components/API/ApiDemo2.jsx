import axios from "axios"
import React  from "react"
import { useNavigate } from "react-router-dom"
import { Bounce, toast, ToastContainer } from "react-toastify"

export const ApiDemo2 = ()=>
{
    const navigate = useNavigate()
    const addUser = async()=>
    {
        const useObj={
            name:"Rahul",
            email:"rahul@gmail.com",
            age:"22",
            isActive:true
        }
        const res = await axios.post("https://node5.onrender.com/user/user",useObj) 
        console.log(res.data)
        console.log(res.status)
        if(res.status==201)
        {
            // alert("Data added sucessfully")
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
                setTimeout(()=>{
                    navigate("/apidemo1")    
                },5000)
                
        }
    }
    return (
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
            <h1>Apidemo 2</h1>
            <button onClick={()=>{addUser()}}>Add</button>
        </div>
    )
}