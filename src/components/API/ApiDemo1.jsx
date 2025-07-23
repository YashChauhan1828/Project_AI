import axios from "axios"
import React, { useEffect, useState } from "react"
import { Button, Modal, ModalBody } from "react-bootstrap"
import { Bounce, toast, ToastContainer } from "react-toastify";
import { Loading } from "./Loading";
import { set } from "react-hook-form";


export const ApiDemo1 = () =>
{
   
    const [isloading,setisLoading] = useState(false)
    const [show, setShow] = useState(false);
    const [users,setusers] = useState([])
    const [user,setuser] = useState({})
    
    const getallusers = async() =>
    {
        setisLoading(true)
        const res = await axios.get("https://jsonplaceholder.typicode.com/posts")
        setusers(res.data)

        console.log(res.data)
        // console.log(res.data.message)
        // console.log(res.data[0])
        
        setisLoading(false)
    }
    useEffect(()=>
    {
        getallusers()
    },[])
    const detailUser=async(id)=>
    {
        const res = await axios.get("https://jsonplaceholder.typicode.com/posts/"+id)
        setuser(res.data)
        setShow(true)
    }
    const handleClose=()=>
    {
        setShow(false)
    }
    const deleteUser=async(id)=>
    {
        const res = await axios.delete("https://jsonplaceholder.typicode.com/posts/"+id)
        console.log(res)
        console.log(res.status)
        if(res.status===200)
        {
            toast.success('Data deleted successfully', {
                position: "top-center",
                autoClose: 5000,
                hideProgressBar: false,
                closeOnClick: false,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
                theme: "dark",
                transition: Bounce,
                });
            // getallusers() 
            setusers(users.filter(user => user.id !== id));
        }           
    }
    return(
        <div>

            <ToastContainer
position="top-center"
autoClose={5000}
hideProgressBar={false}
newestOnTop={false}
closeOnClick={false}
rtl={false}
pauseOnFocusLoss
draggable
pauseOnHover
theme="dark"
transition={Bounce}
/>
            <h1>Api demo1</h1>
            
            {/* <button onClick={()=>{getallusers()}}>Get</button> */}
           
            {/* {
                users?.map((user)=>{
                    return(
                        <li>
                            {user.name}
                        </li>
                    )
                })
            } */}
            <table className="table table-dark"> 
               
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Age</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                {
                isloading && <Loading></Loading>
                }
                        
                            {
                                users?.map((user=>{
                                    return(
                                        <tr>
                                        <td>{user.userId}</td>
                                        <td>{user.id}</td>
                                        <td>{user.title}</td>
                                        <td>{user.body}</td>
                                        <td><button className="btn btn-info" onClick={()=>{detailUser(user.id)}}>Details</button>
                                        <Button onClick={()=>deleteUser(user.id)} variant="danger">Delete</Button>
                                        </td>

                                        {/* <td><Button variant="info">Info</Button></td> */}
                                        </tr>
                                    )
                                }))
                            }
                        
                    
                </tbody>
            </table>
            <Modal show={show} onHide={handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title>User Detail</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <h1>Name:{user.title}</h1>
                    <p>Email:{user.body}</p>
                </Modal.Body>            
            </Modal>
        </div>
    )
}