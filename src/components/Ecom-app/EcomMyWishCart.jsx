import axios from "axios";
import React, { useEffect, useState } from "react";
import { Button } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import { toast, ToastContainer } from "react-toastify";
export const EcomMyWishCart=()=>
{
    const [cart,setcart] = useState()
    const navigate = useNavigate()
    useEffect(()=>
    {
        axios.get("http://localhost:9797/mywishcart",{
          params:{
            userId:localStorage.getItem("userId")
          },
            headers:{
                authToken: localStorage.getItem("authToken")
            }
        }).then((res)=>
        {
            console.log(res.data)   
            setcart(res.data)
        })
    },[])
    const removeItem=async(id)=>
    {
        const res = await axios.delete(`http://localhost:9797/removewishcartitem/${id}`,{
          params:{
            userId:localStorage.getItem("userId")
          },
            headers:{
                authToken: localStorage.getItem("authToken")
            }
        })
          toast.success("Item removed successfully", {
              position: "top-center",
              autoClose: 3000,
              theme: "dark",
            });
            setcart(res.data)
    }
    const product=()=>
    {
        navigate("/products")
    }
    return(
        <div className="cart-container">
             <h2 className="text-center mb-4 text-light">🛒 My Cart</h2>
       
             <ToastContainer />
       
             <div className="table-responsive">
               <table className="table table-dark table-striped text-center align-middle shadow-lg rounded">
                 <thead className="table-secondary text-dark">
                   <tr>
                     <th>Name</th>
                     <th>Image</th>
                     <th>Category</th>
                     
                     <th>Price</th>
                     
                     <th>Actions</th>
                   </tr>
                 </thead>
                 <tbody>
                   {cart?.map((p) => (
                     <tr key={p.cartitemId}>
                       <td>{p.productName}</td>
                       <td><img src={p.productImagePath} class="product-img"/></td>
                       <td>{p.category}</td>
                        <td>₹{p.price}</td> 
                      
                      
                       <td>
                         <button className="btn btn-danger btn-sm" onClick={() => removeItem(p.wishcartitemId)}>Remove</button>
                       </td>
                     </tr>
                   ))}
                 </tbody>
               </table>
             </div>
       
             
       
             <div className="d-flex justify-content-end gap-3 mt-3">
             
               <Button variant="outline-light" onClick={product}>Back to Products</Button>
             </div>
           </div>
    )
}