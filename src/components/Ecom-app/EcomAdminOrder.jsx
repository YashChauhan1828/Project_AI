import axios from "axios";
import React, { useEffect, useState } from "react";
import { Button } from "react-bootstrap";

export const EcomAdminOrder=()=>
{
    const [orders, setOrders] = useState([]);
    const fetchOrders=()=>
    {
        axios.get("http://localhost:9191/api/admin/allorders").then((res)=>
        {
            console.log(res.data)
            setOrders(res.data)
        })
    }
     
    const statusUpdate=(id)=>
        {
            const res = axios.put(`http://localhost:9191/api/admin/update/${id}`,{})
            fetchOrders()
        }
     useEffect(() => 
    {
        fetchOrders();
    }, []); 

    return(
       <div className="order-container">
      <h1 className="title">🧾 Order History</h1>

      <table className="order-table">
        <thead>
          <tr>
            <th>Order ID</th>
            <th>Product</th>
            <th>Image</th>
            <th>Qty</th>
            <th>Price (₹)</th>
            <th>Total</th>
            <th>Status</th>
            <th>Order Date</th>
            <th>Update Status</th>
          </tr>
        </thead>
        <tbody>
          {orders.map((item, index) => (
            <tr key={index}>
              <td>{item.orderItemId}</td>
              <td>{item?.productName}</td>
              <td>
                <img
                  src={item?.productImagePath}
                  alt={item?.productName}
                  className="product-img"
                />
              </td>
              <td>{item.qty}</td>
              <td>₹{item?.price}</td>
              <td>₹{(item.qty * item?.price).toFixed(2)}</td>
              <td>{item.status || "Pending"}</td>
              <td>{item.orderDate ? item.orderDate.split("T")[0] : "N/A"}</td>
              <td> <Button
                  onClick={() => statusUpdate(item.orderItemId)}
                  disabled={item.status === "Delivered"}
                  variant={item.status === "Delivered" ? "success" : "primary"}
                >
                  {item.status === "Delivered" ? "Delivered" : "Mark Delivered"}
                </Button></td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
    )
}