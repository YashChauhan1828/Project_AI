import React from "react"
import { Subcontent } from "./Subcontent"

export const Content = ({t , c}) => {
    return (
        <div>
            <h1>Content</h1>
            <h2> title = {t}</h2>
            <h2> city = {c}</h2>
            <Subcontent t1 = {t} c1={c}></Subcontent>
        </div>
    )
}