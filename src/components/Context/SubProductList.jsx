import React, { useContext } from "react";
import { ProductContext } from "./ProductContent";

export const SubProductList=()=>
{
    const {products} = useContext(ProductContext)
    console.log("Products is sub product list ", products)
    return(
        <div>
            <h1>Sub Product List</h1>
        </div>
    )
}