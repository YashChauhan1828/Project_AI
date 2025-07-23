import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";
export const EcomLogout = ()=>
{
    const navigate = useNavigate()
    useEffect(()=>
    {
        localStorage.clear()
        navigate("/login")
    },[])
    return(
        <div>
            
        </div>
    )
}