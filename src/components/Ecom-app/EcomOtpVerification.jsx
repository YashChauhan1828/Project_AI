import React, { useState } from "react";
import { useForm } from "react-hook-form";
import { useLocation, useNavigate } from "react-router-dom";
import axios from "axios";
import Loading from "../API/Loading";

export const EcomOtpVerification = () => {
    const [isloading, setIsLoading] = useState(false);
    const { register, handleSubmit } = useForm();
    const navigate = useNavigate();
    const location = useLocation();

    const email = localStorage.getItem("email"); // or location.state?.email
    const price = location.state?.price;

    const submitHandler = async (data) => {
        setIsLoading(true);
        try {
            const res = await axios.post("http://localhost:9797/verifyotp", null, {
                params: {
                    email: email,
                    otp: data.otp,
                },
                headers: {
                     authToken: localStorage.getItem("authToken")
                    }
            });

            if (res.data === "Email Verified") {
                navigate("/shipping", { state: { price } });
            } else {
                alert("Invalid OTP");
            }
        } catch (err) {
            alert("Error verifying OTP");
            console.error(err);
        } finally {
            setIsLoading(false);
        }
    };

    return (
        <div>
            {isloading && <Loading />}
            <form onSubmit={handleSubmit(submitHandler)}>
                <label>Enter OTP: </label>
                <input type="text" {...register("otp")} required />
                <input type="submit" value="Verify" />
            </form>
        </div>
    );
};
