import axios from "axios";
import React from "react";
import { Button } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import { FaCheckCircle } from "react-icons/fa";
import "./EcomSucess.css"; // 👈 custom CSS file

export const EcomSuccess = () => {
  const navigate = useNavigate();

  const orderHistory = () => {
    navigate("/orderHistory");
  };

  return (
    <div className="success-container">
      <div className="success-card">
        <FaCheckCircle className="success-icon" />
        <h1 className="success-title">Order Placed Successfully!</h1>
        <p className="success-text">
          Thank you for your purchase. Your order has been placed and is being processed.
        </p>
        <Button variant="success" className="history-btn" onClick={orderHistory}>
          View Order History
        </Button>
      </div>
    </div>
  );
};
