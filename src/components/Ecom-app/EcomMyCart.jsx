import axios from "axios";
import React, { useEffect, useState } from "react";
import { Button } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import { Bounce, toast, ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import "./EcomMyCart.css"; // ⬅️ Add a custom CSS file

export const EcomMyCart = () => {
  const [cart, setCart] = useState([]);
  const [totalPrice, settotalPrice] = useState(0);
  const navigate = useNavigate();

  useEffect(() => {
    axios
      .get("http://localhost:9797/mycart", {
        params:{
          userId: localStorage.getItem("userId")
        
      },
        headers: {
          authToken: localStorage.getItem("authToken"),
        },
      })
      .then((res) => {
        if (res.data === "please Login") {
          toast.warning("Please Login", {
            position: "top-center",
            autoClose: 5000,
            theme: "dark",
            transition: Bounce,
          });

          setTimeout(() => {
            navigate("/login");
          }, 2000);
        } else {
          // console.log(res.data.products.length)
          console.log(res.data)
          setCart(res.data);
        }
      });
  }, []);

  useEffect(() => {
    try {
      const total = cart.reduce(
        (acc, item) => acc + item.price * item.qty,
        0
      );
      settotalPrice(total);
    } catch {
      settotalPrice(0);
    }
  }, [cart]);

  const removeItem = async (id) => {
    const res = await axios.delete(`http://localhost:9797/removecartitem/${id}`, {
      params:{
        userId: localStorage.getItem("userId")
      },
      headers: {
        authToken: localStorage.getItem("authToken"),
      },
    });

    toast.success("Item removed successfully", {
      position: "top-center",
      autoClose: 3000,
      theme: "dark",
    });
    
    setCart(res.data);
  };

  const plusqty = async (id) => {
    const res = await axios.put(`http://localhost:9797/plusqty/${id}`,{}, {
      params:{
        userId: localStorage.getItem("userId")
      },
      headers: {
        authToken: localStorage.getItem("authToken"),
      },
    });
    setCart(res.data);
  };

  const minusqty = async (id) => {
    const res = await axios.put(`http://localhost:9797/minusqty/${id}`, {}, {
      params:{
        userId: localStorage.getItem("userId")
      },
      headers: {
        authToken: localStorage.getItem("authToken"),
      },
    });
    setCart(res.data);
  }
  const checkout = () => 
    {
      
      if(cart.length!=0)
      {
        navigate("/checkout",{state:{price:totalPrice}});
      }
      else
      {
          toast.warning("Please Select Product", {
            position: "top-center",
            autoClose: 5000,
            theme: "dark",
            transition: Bounce,
          });
      }
    }
  const product = () => navigate("/products");

  return (
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
              <th>Qty</th>
              <th>Price</th>
              <th>Change Quantity</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {cart?.map((p) => (
              <tr key={p.cartitemId}>
                <td>{p.productName}</td>
                <td><img src={p.productImagePath} class="product-img"/></td>
                <td>{p.category}</td>
                <td>{p.qty}</td>
                <td>₹{p.price * p.qty}</td>
                <td>
                  <button className="btn btn-outline-light btn-sm me-2" onClick={() => minusqty(p.cartitemId)}>−</button>
                  <button className="btn btn-outline-light btn-sm" onClick={() => plusqty(p.cartitemId)}>+</button>
                </td>
                <td>
                  <button className="btn btn-danger btn-sm" onClick={() => removeItem(p.cartitemId)}>Remove</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      <h4 className="text-end me-2 text-light">
        🧾 Total Price: <span className="text-success">₹{totalPrice}</span>
      </h4>

      <div className="d-flex justify-content-end gap-3 mt-3">
        <Button variant="success" onClick={checkout}>Proceed to Checkout</Button>
        <Button variant="outline-light" onClick={product}>Back to Products</Button>
      </div>
    </div>
  );
};
