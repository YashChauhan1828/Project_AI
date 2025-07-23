import axios from "axios";
import React, { useState } from "react";
import { useForm } from "react-hook-form";
import { Link, useNavigate } from "react-router-dom";

export const EcomSignUp = () => {
  const { register, handleSubmit, reset } = useForm();
  const navigate = useNavigate();
  const [error, setError] = useState("");
  const [successMsg, setSuccessMsg] = useState("");

  const submithandler = async (data) => {
    const formData = new FormData();
    formData.append("first_name", data.first_name);
    formData.append("email", data.email);
    formData.append("password", data.password);
    formData.append("profilePicture", data.profilePicture[0]);

    try {
      const res = await axios.post("http://localhost:9797/api/public/esignup", formData);
      setSuccessMsg("✅ Registered successfully! Redirecting...");
      setError("");
      setTimeout(() => navigate("/login"), 2000);
      reset();
    } catch (error) {
      const msg = error.response?.data?.message || "Signup failed.";
      setError(msg);
      setSuccessMsg("");
    }
  };

  return (
    <div className="container d-flex align-items-center justify-content-center min-vh-100 bg-dark text-white">
      <div className="card p-4 shadow-lg rounded-4" style={{ maxWidth: "450px", width: "100%", backgroundColor: "grey" }}>
        <h2 className="text-center mb-4 fw-bold">📝 Sign Up</h2>

        {error && (
          <div className="alert alert-danger py-2 px-3 small">{error}</div>
        )}

        {successMsg && (
          <div className="alert alert-success py-2 px-3 small">{successMsg}</div>
        )}

        <form onSubmit={handleSubmit(submithandler)} encType="multipart/form-data">
          <div className="mb-3">
            <label className="form-label">First Name</label>
            <input
              type="text"
              {...register("first_name")}
              required
              className="form-control  text border-0"
              placeholder="John"
            />
          </div>

          <div className="mb-3">
            <label className="form-label">Email address</label>
            <input
              type="email"
              {...register("email")}
              required
              className="form-control  text border-0"
              placeholder="john@example.com"
            />
          </div>

          <div className="mb-3">
            <label className="form-label">Password</label>
            <input
              type="password"
              {...register("password")}
              required
              className="form-control  text border-0"
              placeholder="********"
            />
          </div>

          <div className="mb-4">
            <label className="form-label">Profile Picture</label>
            <input
              type="file"
              {...register("profilePicture")}
              accept="image/*"
              required
              className="form-control bg-secondary text border-0"
            />
          </div>

          <button
            type="submit"
            className="btn btn-primary w-100 fw-semibold"
          >
            Create Account
          </button>
        </form>

        <p className="text-center mt-3 small">
          Already have an account?{" "}
          <Link to="/login" className="text-info text-decoration-none">Login</Link>
        </p>
      </div>
    </div>
  );
};
