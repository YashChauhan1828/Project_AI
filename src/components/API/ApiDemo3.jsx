import axios from "axios"
import React, { useEffect, useState } from "react"
import { Button, Modal, ModalBody } from "react-bootstrap"
import { Bounce, toast, ToastContainer } from "react-toastify";
import { Loading } from "./Loading";
import { useAuthentication } from "../Hooks/useAuthentication";


export const ApiDemo3 = () =>
{
   
    const [isloading,setisLoading] = useState(false)
    const [show, setShow] = useState(false);
    const [users,setusers] = useState([])
    const [user,setuser] = useState({})
    const {isAdmin} = useAuthentication()
    
    const getallusers = async() =>
    {
        setisLoading(true)
        const res = await axios.get("https://node5.onrender.com/user/user")
        console.log(res.data)
        console.log(res.data.message)
        console.log(res.data.data[0])
        setusers(res.data.data)
        setisLoading(false)
    }
    useEffect(()=>
    {
        getallusers()
    },[])
    const detailUser=async(id)=>
    {
        const res = await axios.get("https://node5.onrender.com/user/user/"+id)
        setuser(res.data)
        setShow(true)
    }
    const handleClose=()=>
    {
        setShow(false)
    }
    const deleteUser=async(id)=>
    {
        const res = await axios.delete("https://node5.onrender.com/user/user/"+id)
        console.log(res)
        console.log(res.status)
        if(res.status==204)
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
            getallusers()
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
                                        <td>{user._id}</td>
                                        <td>{user.age}</td>
                                        <td>{user.name}</td>
                                        <td>{user.email}</td>
                                        <td><button className="btn btn-info" onClick={()=>{detailUser(user._id)}}>Details</button>
                                        { 
                                        isAdmin &&
                                        <Button onClick={()=>deleteUser(user._id)} variant="danger">Delete</Button>
                                        }
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
                    <h1>Name:{user.name}</h1>
                    <p>Email:{user.email}</p>
                </Modal.Body>            
            </Modal>
        </div>
    )
}