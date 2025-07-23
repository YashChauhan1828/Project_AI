import axios from "axios";
import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import "./EcomProductView.css"; // Import the CSS file

export const EcomProductView = () => {
  const { id } = useParams();
  const [product, setProduct] = useState({});
  const [reviews, setReviews] = useState([]);
  const [rating, setRating] = useState(0);
  const [comment, setComment] = useState("");

  useEffect(() => {
    axios.get(`http://localhost:9797/api/public/userproductview/${id}`)
      .then((res) => {
        console.log(res.data)
        setProduct(res.data)
      });
  }, [id]);

  useEffect(() => {
    axios.get(`http://localhost:9797/api/public/reviews/${id}`)
      .then((res) => {
        console.log(res.data)
        setReviews(res.data)
      });
  }, [id]);

  const submitReview = () => {
    axios.post(`http://localhost:9797/review/${id}`, { rating, comment }, {
      params:{
        userId: localStorage.getItem("userId")
      },
      headers: {
        authToken: localStorage.getItem("authToken")
      }
    }).then((res) => {
      
      alert("Review submitted!");
      setRating(0);
      setComment("");
      setReviews(prev => [...prev, res.data]);
    });
  };

  return (
    <div className="product-view-container">
        <div className="product-header">
            <img src={product.productImagePath} alt={product.productName} className="product-image" />
            <div className="product-details">
                <h2 className="product-name">{product.productName}</h2>
                <p className="product-category">Category: {product.category}</p>
                <p className="product-price">₹ {product.price}</p>
            </div>
        </div>
      {/* Review Form */}
      <div className="review-form-section">
        <h3>Leave a Review</h3>
        <div className="review-form">
         <select
  value={rating}
  onChange={(e) => setRating(Number(e.target.value))}
  className="review-select"
>
  <option value="">Select Rating</option>
  <option value="5">★★★★★</option>
  <option value="4">★★★★☆</option>
  <option value="3">★★★☆☆</option>
  <option value="2">★★☆☆☆</option>
  <option value="1">★☆☆☆☆</option>
    </select>
          <textarea
            value={comment}
            onChange={(e) => setComment(e.target.value)}
            placeholder="Write your review here..."
            rows="3"
            className="input-comment"
          ></textarea>
          <button onClick={submitReview} className="submit-review-btn">Submit</button>
        </div>
      </div>

      {/* Review List */}
      <div className="review-list-section">
        <h3>Customer Reviews</h3>
        {reviews.length === 0 ? (
          <p className="no-reviews">No reviews yet. Be the first to review!</p>
        ) : (
          <div className="review-list">
            {reviews.map((r) => (
              <div key={r.reviewId} className="review-card">
                <div className="review-header">
                  <span className="review-user">{r.first_name}</span>
                  <span className="review-stars">
                    {"★".repeat(r.rating)}{"☆".repeat(5 - r.rating)}
                  </span>
                </div>
                <p className="review-comment">{r.comment}</p>
                <p className="review-date">{new Date(r.createdAt).toLocaleString()}</p>
              </div>
            ))}
          </div>
        )}
      </div>
    </div>
  );
};
