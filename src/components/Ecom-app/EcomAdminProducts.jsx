import axios from "axios";
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import "./EcomAdminProducts.css";

export const EcomAdminProducts = () => {
  const [Products, setProducts] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    axios.get("http://localhost:9595/api/admin/aproducts").then((res) => {
      setProducts(res.data.products);
    });
  }, []);

  const viewProduct = (id) => {
    navigate(`/adminviewproduct/${id}`);
  };

  const updateProduct = (id) => {
    navigate(`/adminupdateproduct/${id}`);
  };

  const deleteProduct = async (id) => {
    const res = await axios.delete(
      `http://localhost:9595/api/admin/deleteproduct/${id}`
    );
    setProducts(res.data.products);
  };

  return (
    <div className="admin-products-container">
      <h1>Admin Product Dashboard</h1>
      <div className="product-grid">
        {Products.map((product) => (
          <div className="product-card" key={product.productId}>
            <img
              src={product.productImagePath}
              alt={product.productName}
              className="product-image"
            />
            <h3>{product.productName}</h3>
            <p><strong>Category:</strong> {product.category}</p>
            <p><strong>Price:</strong> ₹{product.price}</p>
            <p><strong>Qty:</strong> {product.qty}</p>
            <div className="icon-group">
              <i
                className="fas fa-eye icon view-icon"
                title="View Product"
                onClick={() => viewProduct(product.productId)}
              ></i>
              <i
                className="fas fa-pen-to-square icon update-icon"
                title="Update Product"
                onClick={() => updateProduct(product.productId)}
              ></i>
              <i
                className="fas fa-trash icon delete-icon"
                title="Delete Product"
                onClick={() => deleteProduct(product.productId)}
              ></i>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};
