import React from "react";
import { Child } from "./Child";

export const Parent=()=>
{
    const message = "Hello From Parent "
    return(
        <div>
            <Child mes={message}></Child>
        </div>
    )
}