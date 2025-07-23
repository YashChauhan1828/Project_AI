import axios from "axios";
import React from "react";
import { useForm } from "react-hook-form";
import { Link, useNavigate } from "react-router-dom";
import "./EcomLogin.css"; // 🔹 Custom CSS for dark styling

export const EcomLogin = () => {
  const { register, handleSubmit } = useForm();
  const navigate = useNavigate();

  const submitHandler = async (data) => {
    try {
      const res = await axios.post("http://localhost:9797/api/public/elogin", data);
      console.log(res.data)
      const userId = res.data.userId;
      const token = res.data.token;
      const email = res.data.email
      localStorage.setItem("authToken", token);
      localStorage.setItem("userId",userId);
      localStorage.setItem("email",email);
      navigate(`/home`);
      console.log(res);
    } catch (error) {
      console.error("Login failed:", error.response?.data || error.message);
      alert("Invalid Credentials");
    }
  };

  return (
    <div className="login-container">
      <form className="login-form shadow-lg" onSubmit={handleSubmit(submitHandler)}>
        <h2 className="text-center text-light mb-4">🔐 Login</h2>
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
          <label className="text-light">Password:</label>
          <input
            type="password"
            className="form-control bg-dark text-light border-secondary"
            {...register("password")}
            placeholder="Enter your Password"
          />
        </div>
        <div className="d-grid">
          <input type="submit" className="btn btn-success" value="Login" />
        </div><br></br>
        <Link to="/EcomForgotPassword">Forgot password</Link><br></br>
      <p class="sign-up">Don't have an Account?<Link to="/EcomSignUp">SignUp</Link></p>
      </form>
      
    </div>
  );
};
