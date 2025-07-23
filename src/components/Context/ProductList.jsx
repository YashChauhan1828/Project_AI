import React, { useContext } from "react";
import { ProductContext } from "./ProductContent";
import { SubProductList } from "./SubProductList";

export const ProductList=()=>
{
    const {products,productdispatcher}= useContext(ProductContext)
    console.log(products)
    return(
        <div>
            <h1>Product List</h1>
            <table className="table table-dark">
                <thead>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Action</th>
                </thead>
                <tbody>
                    {
                        products?.map((product)=>
                        {
                            return(
                                <tr>
                                    <td>{product.id}</td>
                                    <td>{product.name}</td>
                                    <td>{product.price}</td>
                                    <td>
                                        <button onClick={()=>{productdispatcher("Delete_product",product)}}>DELETE</button>
                                    </td>
                                    </tr>
                            )
                        })
                    }
                </tbody>
            </table>
            <SubProductList></SubProductList>
        </div>
    )
}