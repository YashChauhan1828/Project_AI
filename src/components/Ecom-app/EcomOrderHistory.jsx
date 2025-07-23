import axios from "axios";
import React, { useEffect, useState } from "react";
import "./EcomOrderHistory.css"; // Import CSS file

export const EcomOrderHistory = () => {
  const [orders, setOrders] = useState([]);

  useEffect(() => {
    axios
      .get("http://localhost:9797/orderhistory", {
        params:{
          userId:localStorage.getItem("userId")
        },
        headers: {
          authToken: localStorage.getItem("authToken"),
        },
      })
      .then((res) => {
        console.log(res.data)
        setOrders(res.data);
      })
      .catch((err) => {
        console.error("Error fetching order history", err);
      });
  }, []);

  return (
    <div className="order-container">
      <h1 className="title">🧾 Order History</h1>

      {orders.length === 0 ? (
        <p className="no-orders">No orders placed yet.</p>
      ) : (
        orders.map((order, index) => (
          <div key={index} className="order-card">
            <h2 className="order-id">Order ID: #{order.orderItemId}</h2>
            <table className="order-table">
              <thead>
                <tr>
                  <th>Product</th>
                  <th>Image</th>
                  <th>Qty</th>
                  <th>Price (₹)</th>
                  <th>Total</th>
                  <th>Status</th>
                  <th>Order Date</th>
                </tr>
              </thead>
              <tbody>
               
                    <td>{order.productName}</td>
                    <td>
                      <img
                        src={order.productImagePath}
                        alt={order.productName}
                        className="product-img"
                      />
                    </td>
                    <td>{order.qty}</td>
                    <td>₹{order.price}</td>
                    <td>₹{(order.qty * order.price).toFixed(2)}</td>
                    <td>{order.status}</td>
                    <td>{order.orderDate ? order.orderDate.split("T")[0]:"N-A"}</td>
                
              </tbody>
            </table>
          </div>
        ))
      )}
    </div>
  );
};
