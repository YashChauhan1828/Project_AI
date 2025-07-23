import axios from "axios";
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { Bounce, toast, ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import "./EcomHome.css"; // 👈 Link to your custom CSS

export const EcomHome = () => {
  const [user, setUser] = useState({});
  const navigate = useNavigate();

  useEffect(() => {
    axios
      .get("http://localhost:9797/ehome", {
        headers: {
          authToken: localStorage.getItem("authToken"),
        },
      })
      .then((res) => {
        if (res.data === "please Login") {
          toast.warning("Please Login", {
            position: "top-center",
            autoClose: 5000,
            theme: "dark",
            transition: Bounce,
          });
          setTimeout(() => {
            navigate("/login");
          }, 2000);
        } else {
          setUser(res.data);
        }
      });
  }, []);

  const goToProducts = () => {
    navigate("/products");
  };

  return (
    <div className="home-container">
      <ToastContainer />
      <div className="user-card">
        <img
          src={user.profilePicPath}
          alt="Profile"
          className="profile-img"
        />
        <h1>Welcome, {user.firstName} 👋</h1>
        <button className="browse-btn" onClick={goToProducts}>
          <i className="fas fa-store"></i> Browse Products
        </button>
      </div>
    </div>
  );
};
