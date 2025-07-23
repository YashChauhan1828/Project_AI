import React, { useState } from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import axios from "axios";
import { useLocation, useNavigate } from "react-router-dom";

export const EcomPayment = () => {
const location = useLocation();
const price = location.state?.price;
const navigate = useNavigate()
const [formData, setFormData] = useState({
        creditcardnumber: "",
        date: "",
        cvv: ""
    });

    const [errors, setErrors] = useState({
        CardError: "",
        DateError: "",
        CVVError: ""
    });

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = async(e) => {
        e.preventDefault();
         
        // Basic Validation
        const newErrors = {};
        if (!formData.creditcardnumber.match(/^\d{15}$/)) {
            newErrors.CardError = "Invalid card number";
        }
        if (!formData.date.match(/^(0[1-9]|1[0-2])\/\d{2}$/)) {
            newErrors.DateError = "Invalid date format";
        }
        if (!formData.cvv.match(/^\d{3}$/)) {
            newErrors.CVVError = "Invalid CVV";
        }

      
        
        setErrors(newErrors);

        if (Object.keys(newErrors).length === 0) {
            // Send data to backend
            
            const finalFormData = {
            ...formData,
            price: price
            };
            // console.log("Form Data Submitted", { ...formData, price: price });

            const res = await axios.post("http://localhost:9797/epayment",finalFormData,{
                params:{
                    userId:localStorage.getItem("userId")
                },
             headers: {
                authToken: localStorage.getItem("authToken")
            }
        })
        console.log(res.data)
        navigate("/success")
        }
    };

    return (
        <div>
            {/* Main Content */}
            <div className="container mt-5">
                <h2 className="mb-4 text-center">💳 Secure Checkout</h2>

                <div className="card shadow p-4">
                    <form onSubmit={handleSubmit}>
                        <div className="mb-3">
                            <label className="form-label fw-bold">Final Amount (₹)</label>
                            <input type="text" name="price" className="form-control" value={price} readOnly />
                        </div>

                        <div className="mb-3">
                            <label className="form-label">Credit Card Number</label>
                            <input
                                type="text"
                                name="creditcardnumber"
                                className="form-control"
                                value={formData.creditcardnumber}
                                onChange={handleChange}
                                placeholder="Enter your credit card number"
                            />
                            <div className="text-danger small">{errors.CardError}</div>
                        </div>

                        <div className="row">
                            <div className="col-md-6 mb-3">
                                <label className="form-label">Expiry Date (MM/YY)</label>
                                <input
                                    type="text"
                                    name="date"
                                    className="form-control"
                                    value={formData.date}
                                    onChange={handleChange}
                                    placeholder="e.g. 08/27"
                                />
                                <div className="text-danger small">{errors.DateError}</div>
                            </div>

                            <div className="col-md-6 mb-3">
                                <label className="form-label">CVV</label>
                                <input
                                    type="text"
                                    name="cvv"
                                    className="form-control"
                                    value={formData.cvv}
                                    onChange={handleChange}
                                    placeholder="e.g. 123"
                                />
                                <div className="text-danger small">{errors.CVVError}</div>
                            </div>
                        </div>

                        <button type="submit" className="btn btn-primary w-100 mt-3">Pay Now</button>
                    </form>
                </div>
            </div>

            {/* Footer */}
                   </div>
    );
};
