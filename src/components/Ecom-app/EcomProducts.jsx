import axios from "axios";
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { Bounce, toast, ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import "./EcomProducts.css"; // 👈 Link to custom CSS
import { FaHeart } from "react-icons/fa";
import { useForm } from "react-hook-form";

export const EcomProducts = () => {
  const [products, setProducts] = useState([]);
  const navigate = useNavigate();
  const {register,handleSubmit}=useForm()
  useEffect(() => {
    axios.get("http://localhost:9797/api/public/userproducts").then((res) => {
      console.log(res.data)  
      setProducts(res.data);
    });
  }, []);

  const addToCart = (id) => {
    axios
      .post(`http://localhost:9797/addtocart/${id}`,{}, {
        headers: {
          authToken: localStorage.getItem("authToken"),
        },
      })
      .then((res) => {
        if (res.status === 200) {
          toast.success("🛒 Item added successfully", {
            position: "top-center",
            autoClose: 3000,
            theme: "dark",
            transition: Bounce,
          });
        }
      });
  };

  const viewProduct = (id) => {
    navigate(`/userproductview/${id}`);
  };

  const wishProduct=(id)=>
  {
    console.log(id)
    axios.post(`http://localhost:9797/addtowishcart/${id}`,{},{
      headers:{
        authToken:localStorage.getItem("authToken"),
      }
    }).then((res)=>
    {
      if(res.status===200)
      {
        toast.success("🛒 Item added successfully", {
            position: "top-center",
            autoClose: 3000,
            theme: "dark",
            transition: Bounce,
          });
      }
    })
  }
  const submithandler=async(data)=>
  {
    const res = await axios.get("http://localhost:9797/api/public/search",{
      params:{
        query:data.search
      }
    })
    setProducts(res.data);
    if(res.data.length===0)
    {
      toast.warning("🛒 No Product Found", {
            position: "top-center",
            autoClose: 3000,
            theme: "dark",
            transition: Bounce,
          });
    }
  }
  return (
    <div className="dark-container">
      <ToastContainer />
      <h1 className="dark-heading">🛍️ Product List</h1>
      <div class="container mb-4">
      <form class="form-inline d-flex justify-content-center" onSubmit={handleSubmit(submithandler)}>
          <label>
            Search: <input aria-label="Search" class="form-control mr-2 w-100" type="text" placeholder="Enter product name" {...register("search")}  />
          </label>
          <input type="submit" value="Search"/>
      </form>
      </div>
      <div className="product-grid">
        {products?.map((product) => (
          <div key={product.productId} className="product-card">
            <img src={product.productImagePath} className="product-image"/>
            <h2 className="product-name">{product.productName}</h2>
            <p className="product-category">Category: {product.category}</p>
            <p className="product-price">₹ {product.price}</p>
            <div className="button-group">
              <button onClick={() => addToCart(product.productId)} className="btn btn-primary">
                <i className="fas fa-cart-plus"></i> 
              </button>
              <button onClick={() => viewProduct(product.productId)} className="btn btn-secondary">
                <i className="fas fa-eye"></i> 
              </button>
              <button onClick={() => wishProduct(product.productId)} className="btn btn-secondary">
                <FaHeart className="me-1 text-danger" /> 
              </button>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};
