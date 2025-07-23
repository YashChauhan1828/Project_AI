import React, { useState } from "react";
import { ProductsAdd } from "./ProductsAdd";
import { ProductList } from "./ProductList";
import { ProductContext } from "./ProductContent";

export const Products=()=>
{
    const [products,setproducts]=useState([
        {
            id:1,
            name:"Product1",
            price:100
        },
        {
            id:2,
            name:"Product2",
            price:200
        },
        {
            id:3,
            name:"Product3",
            price:300
        }
    ])

    const productdispatcher=(choice,payload)=>
    {
        switch(choice)
        {
            case "Add_product":
                break;
            case "Delete_product":
                console.log(payload,"is about to delete")
                setproducts(products?.filter((product)=>{
                    return product.id !== payload.id
                }))
                break;    
        }
    }

    return(
        <div>
            <ProductContext.Provider value={{products,productdispatcher,setproducts}}>
            <ProductsAdd></ProductsAdd>
            <ProductList></ProductList>
            </ProductContext.Provider>
        </div>
    )
}