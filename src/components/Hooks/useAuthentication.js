import { useEffect, useState } from "react"


export const useAuthentication = ()=>
{
    const [role,setrole] = useState(null)
    useEffect(()=>{
        const userRole = localStorage.getItem("role")
        if(userRole=="admin")
        {
            setrole(userRole || "guest")
        }
    },[])
    const isAdmin = role==="ADMIN" || role==="admin"
    const isUser = role==="USER" || role==="user"

    return {isAdmin,isUser}
}