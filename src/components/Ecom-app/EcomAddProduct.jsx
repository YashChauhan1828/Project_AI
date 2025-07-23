import axios from "axios";
import React from "react";
import { useForm } from "react-hook-form";
import { useNavigate } from "react-router-dom";
export const EcomAddProduct=()=>
{
    const {register,handleSubmit} = useForm()
    const navigate = useNavigate()
    const submitHandler=async(data)=>
    {
        const formData = new FormData()
        formData.append("productName",data.productName)
        formData.append("category",data.category)
        formData.append("price",data.price)
        formData.append("qty",data.qty)
        formData.append("productImage",data.productImage[0])
        try
        {
            const res = await axios.post("http://localhost:9595/api/admin/saveproduct",formData)
            console.log(res.data)
        }
        catch(error)
        {

        }
    }
    const ListProducts=async()=>
    {
              navigate("/adminproducts")
    }
    return(
        <div>
            <h1>Add product</h1>
            <form onSubmit={handleSubmit(submitHandler)} encType="multipart/form-data">
            <label>
                Product Name: <input type="text" {...register("productName")}/>
            </label><br></br>
            <label>
                Category: <input type="text" {...register("category")}/>
            </label><br></br>
            <label>
                Price: <input type="text" {...register("price")}/>
            </label><br></br>
            <label>
                Quantity: <input type="text" {...register("qty")}/>
            </label><br></br>
            <label>
                Product Image: <input type="file" {...register("productImage")}/>
            </label><br></br>
            <input type="submit" value="submit"/>
            </form>
            <button onClick={()=>ListProducts()}>Products</button>
        </div>
    )
}