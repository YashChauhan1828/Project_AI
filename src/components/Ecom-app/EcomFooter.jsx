import React from "react";
export const EcomFooter=()=>
{
    return(
        <div>
             <footer className="bg-dark text-white mt-5">
                <div className="container py-5">
                    <div className="row">
                        <div className="col-md-4">
                            <h5>About E-Shop</h5>
                            <p>E-Shop is your one-stop online store for electronics, fashion, and more. We deliver quality products with fast and secure service.</p>
                        </div>
                        <div className="col-md-4">
                            <h5>Quick Links</h5>
                            <ul className="list-unstyled">
                                <li><a href="/ehome" className="text-white text-decoration-none">Home</a></li>
                                <li><a href="/userproducts" className="text-white text-decoration-none">Products</a></li>
                                <li><a href="/mycart" className="text-white text-decoration-none">My Cart</a></li>
                                <li><a href="/inputmail" className="text-white text-decoration-none">Checkout</a></li>
                                <li><a href="/logout" className="text-white text-decoration-none">Logout</a></li>
                            </ul>
                        </div>
                        <div className="col-md-4">
                            <h5>Contact Us</h5>
                            <p>
                                📍 Surat, Gujarat, India<br />
                                📞 +91 98765 43210<br />
                                ✉️ support@eshop.com
                            </p>
                            <div>
                                <a href="#" className="text-white me-3">Facebook</a>
                                <a href="#" className="text-white me-3">Twitter</a>
                                <a href="#" className="text-white">Instagram</a>
                            </div>
                        </div>
                    </div>
                    <hr className="border-light mt-4" />
                    <div className="text-center">&copy; 2025 E-Shop. All rights reserved.</div>
                </div>
            </footer>
        </div>
    )
}