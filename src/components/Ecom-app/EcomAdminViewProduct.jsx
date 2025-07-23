import axios from "axios";
import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
export const EcomAdminViewProduct=()=>
{
    const id = useParams().id
    const [Product, setProduct] = useState({});
    const navigate = useNavigate()
    useEffect(()=>
    {
        axios.get(`http://localhost:9595/api/admin/aviewproduct/${id}`,{
        }).then((res)=>
        {
            setProduct(res.data.product);
        })
    },[id])
    const ListProducts = ()=>
    {
        navigate("/adminproducts")
    }
    return(
        <div>
            <p><img src= {Product.productImagePath}/></p>
            <p>Product Name : {Product.productName}</p>
            <p>Price : {Product.price}</p>
            <p>Category : {Product.category}</p>
            <button onClick={()=>ListProducts()}>Back To Products</button>
        </div>
    )
}