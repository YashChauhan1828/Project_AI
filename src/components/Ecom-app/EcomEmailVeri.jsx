import axios from "axios";
import React from "react";
import { useForm } from "react-hook-form";
import { useLocation, useNavigate } from "react-router-dom";
import "./EcomEmailVeri.css"; // External CSS

export const EcomEmailVeri = () => {
  const { register, handleSubmit } = useForm();
  const navigate = useNavigate();
  const location = useLocation();
  const price = location.state?.price;

  const submitHandler = async (data) => {
    const formData = new FormData();
    formData.append("email", data.email);

    try {
      const res = await axios.post("http://localhost:9797/sendmail", formData, {
        headers: {
          authToken: localStorage.getItem("authToken"),
        },
      });
      if (res.status === 200) {
        navigate(`/otpveri/${res.data}`, { state: { price } });
      }
    } catch (err) {
      console.error("Error sending email", err);
    }
  };

  return (
    <div className="email-verification-container">
      <h2>📧 Verify Your Email</h2>
      <form onSubmit={handleSubmit(submitHandler)} className="email-form">
        <label>Email</label>
        <input
          type="email"
          {...register("email")}
          required
          placeholder="john@example.com"
        />
        <button type="submit">Verify Email</button>
      </form>
    </div>
  );
};
