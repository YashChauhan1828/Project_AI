import React from "react";
import { Link } from "react-router-dom";
import { FaHome, FaBoxOpen, FaShoppingCart, FaHeart, FaSignOutAlt, FaStore } from "react-icons/fa";

export const EcomNavbar = () => {
  return (
    <>
      <nav className="navbar navbar-expand-lg navbar-dark bg-dark shadow-sm">
        <div className="container-fluid">
          <Link className="navbar-brand fw-bold d-flex align-items-center" to="/home">
            <FaStore className="me-2" /> E-Shop
          </Link>
          <button
            className="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarNav"
            aria-controls="navbarNav"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span className="navbar-toggler-icon"></span>
          </button>

          <div className="collapse navbar-collapse" id="navbarNav">
            <ul className="navbar-nav ms-auto">
              <li className="nav-item">
                <Link className="nav-link" to="/home">
                  <FaHome className="me-1" /> Home
                </Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link" to="/products">
                  <FaBoxOpen className="me-1" /> Products
                </Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link" to="/EcomMyCart">
                  <FaShoppingCart className="me-1" /> My Cart
                </Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link" to="/EcomWishCart">
                  <FaHeart className="me-1 text-danger" /> Wish Cart
                </Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link" to="/logout">
                  <FaSignOutAlt className="me-1" /> Logout
                </Link>
              </li>
            </ul>
          </div>
        </div>
      </nav>
    </>
  );
};
