import axios from "axios";
import React, { useEffect, useState } from "react";
import { useForm } from "react-hook-form";
import { useNavigate, useParams } from "react-router-dom";
import { toast, ToastContainer } from "react-toastify";
export const EcomAdminUpdateProduct=()=>
{
        const {register,handleSubmit} = useForm()
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
        const submithandler=(data)=>
        {
            const formData = new FormData();
            formData.append("productName",data.productName)
            formData.append("category",data.category)
            formData.append("price",data.price)
            formData.append("qty",data.qty)
            formData.append("productImage",data.productImage[0])
            try{
            const res = axios.put(`http://localhost:9595/api/admin/updateproduct/${id}`,formData)
            if(res.status===200)
                {
                     toast.success("Item removed successfully", {
                          position: "top-center",
                          autoClose: 3000,
                          theme: "dark",
                        });
                }    
            }
            catch(error)
            {

            }
        }
        const ListProducts = ()=>
        {
            navigate("/adminproducts")
        }
        return(
            <div>
                <ToastContainer />
                <p><img src= {Product.productImagePath}/></p>
                <p>Product Name : {Product.productName}</p>
                <p>Price : {Product.price}</p>
                <p>Category : {Product.category}</p>
                
                <form onSubmit={handleSubmit(submithandler)} encType="multipart/form-data">
                    <label>
                        Product Name: <input type="text" {...register("productName")} />
                    </label><br></br>
                    <label>
                        Category: <input type="text" {...register("category")} />
                    </label><br></br>
                    <label>
                        Price: <input type="text" {...register("price")} />
                    </label><br></br>
                    <label>
                        Quantity: <input type="text" {...register("qty")} />
                    </label><br></br>
                    <label>
                        Image: <input type="file" {...register("productImage")} />
                    </label><br></br>
                    <input type="submit" value="Update Product"/>                   
                </form>
            <button onClick={()=>ListProducts()}>Back To Products</button>
            </div>
        )
}