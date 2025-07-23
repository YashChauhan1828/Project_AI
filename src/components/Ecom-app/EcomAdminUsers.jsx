import axios from "axios";
import React, { useEffect, useState } from "react";
import { Button } from "react-bootstrap";

export const EcomAdminUsers=()=>
{
    const [users, setUsers] = useState([]);
    useEffect(()=>
    {
        axios.get("http://localhost:9191/api/admin/allusers").then((res)=>
        {
            console.log(res.data)
            setUsers(res.data)
        })
    },[])

    const makeAdmin=async(id)=>
    {
        const res = await axios.put(`http://localhost:9191/api/admin/makeadmin/${id}`,{})
        console.log(res.data)
        setUsers(res.data)
    }

    const deleteUser=async(id)=>
    {
        const res = await axios.delete(`http://localhost:9191/api/admin/deleteuser/${id}`,{})
        console.log(res.data)
        setUsers(res.data)
    }
    return(
        <div>
            <div className="table-responsive">
        <table className="table table-dark table-striped text-center align-middle shadow-lg rounded">
          <thead className="table-secondary text-dark">
            <tr>
              <th>Name</th>
              <th>Email</th>
              <th>Role</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {users?.map((user) => (
              <tr key={user.userId}>
                <td>{user.first_name}</td>
                <td>{user.email}</td>
                <td>{user.role}</td>
                <td><Button className="btn btn-outline-light btn-sm" onClick={()=>makeAdmin(user.userId)}>Make Admin</Button>
                <Button className="btn btn-danger btn-sm" onClick={()=>deleteUser(user.userId)}>Blockuser</Button></td>  
              </tr>
            ))}
          </tbody>
        </table>
      </div>
        </div>
    )
}