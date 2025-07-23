import axios from "axios";
import React from "react";
import { useForm } from "react-hook-form";
import { useNavigate } from "react-router-dom";
export const EcomForgetPassword=()=>
{
     const { register, handleSubmit } = useForm();
     const navigate = useNavigate();
     const submitHandler=async(data)=>
        {
            const formData = new FormData();
            formData.append("email", data.email);
            formData.append("password1",data.password1)
            formData.append("password2",data.password2)
            const res =await axios.post("http://localhost:9999/api/public/updatepassword",formData)
            console.log(res.data)
            if(res.status===200)
            {
                navigate("/login")
            }
        }    
    return(
        <div className="login-container">
      <form className="login-form shadow-lg" onSubmit={handleSubmit(submitHandler)}>
        <h2 className="text-center text-light mb-4">🔐 Forget Password</h2>
        <div className="form-group mb-3">
          <label className="text-light">Email:</label>
          <input
            type="email"
            className="form-control bg-dark text-light border-secondary"
            {...register("email")}
            placeholder="Enter your Email"
          />
        </div>
        <div className="form-group mb-4">
          <label className="text-light">New Password:</label>
          <input
            type="password"
            className="form-control bg-dark text-light border-secondary"
            {...register("password1")}
            placeholder="Enter your Password"
          />
        </div>
        <div className="form-group mb-4">
          <label className="text-light">Confirm Password:</label>
          <input
            type="password"
            className="form-control bg-dark text-light border-secondary"
            {...register("password2")}
            placeholder="Enter your Password"
          />
        </div>
        <div className="d-grid">
          <input type="submit" className="btn btn-success" value="Login" />
        </div><br></br>
      </form>
      
    </div>
    )
}