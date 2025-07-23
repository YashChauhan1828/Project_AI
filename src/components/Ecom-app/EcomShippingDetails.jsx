import axios from "axios";
import React, { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";

export const EcomShippingDetails = () => {
  const location = useLocation();
const price = location.state?.price;
  const locationData = {
    India: {
      Gujarat: ["Surat", "Ahmedabad", "Vadodara", "Rajkot", "Gandhinagar"],
      Maharashtra: ["Mumbai", "Pune", "Nagpur"],
      "Jammu and Kashmir": ["Srinagar", "Jammu"]
    },
    USA: {
      California: ["Los Angeles", "San Diego"],
      Texas: ["Houston", "Dallas"]
    },
    UAE: {
      Dubai: ["Deira", "Bur Dubai"],
      AbuDhabi: ["Al Ain", "Mussafah"]
    }
  };

  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    recipientName: "",
    address: "",
    country: "",
    state: "",
    city: "",
    zipCode: "",
    phoneNumber: ""
  });

  const [errors, setErrors] = useState({});

  const handleChange = (e) => {
    const { name, value } = e.target;
    if (name === "country") {
      setFormData({ ...formData, country: value, state: "", city: "" });
    } else if (name === "state") {
      setFormData({ ...formData, state: value, city: "" });
    } else {
      setFormData({ ...formData, [name]: value });
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const res = await axios.post("http://localhost:9797/eshipping", formData, {
      headers: {
        authToken: localStorage.getItem("authToken")
      }
    });
    if (res.status === 200) {
      navigate("/payment",{ state: { price } });
    }
  };

  const countryOptions = Object.keys(locationData);
  const stateOptions = formData.country
    ? Object.keys(locationData[formData.country])
    : [];
  const cityOptions =
    formData.country && formData.state
      ? locationData[formData.country][formData.state]
      : [];

  return (
    <div
      style={{
        backgroundColor: "#121212",
        color: "#ffffff",
        minHeight: "100vh",
        padding: "40px",
        fontFamily: "Arial, sans-serif"
      }}
    >
      <h2 style={{ textAlign: "center", marginBottom: "30px" }}>Shipping Details</h2>
      <form
        onSubmit={handleSubmit}
        style={{
          maxWidth: "600px",
          margin: "auto",
          display: "flex",
          flexDirection: "column",
          gap: "20px"
        }}
      >
        <div>
          <label>Name:</label>
          <input
            type="text"
            name="recipientName"
            placeholder="Enter your name"
            value={formData.recipientName}
            onChange={handleChange}
            style={inputStyle}
          />
          <span style={errorStyle}>{errors.recipientName}</span>
        </div>

        <div>
          <label>Address:</label>
          <textarea
            name="address"
            placeholder="Enter address"
            value={formData.address}
            onChange={handleChange}
            rows={3}
            style={{ ...inputStyle, resize: "vertical" }}
          />
          <span style={errorStyle}>{errors.address}</span>
        </div>

        <div>
          <label>Country:</label>
          <select name="country" value={formData.country} onChange={handleChange} style={inputStyle}>
            <option value="">Select Country</option>
            {countryOptions.map((c) => (
              <option key={c} value={c}>
                {c}
              </option>
            ))}
          </select>
          <span style={errorStyle}>{errors.country}</span>
        </div>

        <div>
          <label>State:</label>
          <select
            name="state"
            value={formData.state}
            onChange={handleChange}
            disabled={!formData.country}
            style={inputStyle}
          >
            <option value="">Select State</option>
            {stateOptions.map((s) => (
              <option key={s} value={s}>
                {s}
              </option>
            ))}
          </select>
          <span style={errorStyle}>{errors.state}</span>
        </div>

        <div>
          <label>City:</label>
          <select
            name="city"
            value={formData.city}
            onChange={handleChange}
            disabled={!formData.state}
            style={inputStyle}
          >
            <option value="">Select City</option>
            {cityOptions.map((c) => (
              <option key={c} value={c}>
                {c}
              </option>
            ))}
          </select>
          <span style={errorStyle}>{errors.city}</span>
        </div>

        <div>
          <label>Zip Code:</label>
          <input
            type="text"
            name="zipCode"
            value={formData.zipCode}
            onChange={handleChange}
            style={inputStyle}
          />
          <span style={errorStyle}>{errors.zipCode}</span>
        </div>

        <div>
          <label>Phone Number:</label>
          <input
            type="text"
            name="phoneNumber"
            value={formData.phoneNumber}
            onChange={handleChange}
            style={inputStyle}
          />
          <span style={errorStyle}>{errors.phoneNumber}</span>
        </div>

        <button
          type="submit"
          style={{
            backgroundColor: "#1db954",
            color: "#fff",
            border: "none",
            padding: "12px",
            borderRadius: "5px",
            cursor: "pointer",
            fontSize: "16px"
          }}
        >
          Proceed to Payment
        </button>
      </form>
    </div>
  );
};

// Styles
const inputStyle = {
  width: "100%",
  padding: "10px",
  borderRadius: "5px",
  border: "1px solid #444",
  backgroundColor: "#1e1e1e",
  color: "#fff",
  marginTop: "5px"
};

const errorStyle = {
  color: "red",
  fontSize: "12px"
};
