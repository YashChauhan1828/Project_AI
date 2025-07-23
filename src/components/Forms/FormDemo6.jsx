import React, { useState } from "react"
import { useForm } from "react-hook-form"

export const FormDemo6 = () => 
{
    const{register,handleSubmit,formState:{errors,isValid}} = useForm({mode:"all"})
    const [display,setdisplay] = useState({})
    const [Submitted,setSubmited] = useState(false)
    const submithandler=(data)=>
    {
        // console.log(data)
        setdisplay(data)
        setSubmited(true)

    }
    var validation = {
        namevalidation :{
            required: {
                value:true,
                message:"*Name is required"            
            },
            minLength:{
                value:3,
                message:"*Name should be at least 3 Characters"
            },
            maxLength:{
                value:14,
                message:"*Name should not be more than 14 Characters"
            }
        },
        phonevalidation:{
            required:{
                value:true,
                message:"Phone is required",
                
            },
            pattern:{
                value:/[6-9]{1}[0-9]{9}/,
                message:"Invalid phone number"
            },
            maxLength:{
                value:10,
                message:"*Phone should not be more than 10 Characters"
            }
        },
        emailvalidation:{
            required:{
                value:true,
                message:"Email is required"
            }
        },
        passwordvalidation:{
            required:{
                value:true,
                message:"Password is required"
            },
            minLength:{
                value:5,
                message:"*Password should be at least 5 Characters"
            },
            maxLength:{
                    value:9,
                    message:"*Password should not be more than 9 Characters"
            },
            pattern:{
                value:/[a-z0-9A-Z]{1,}['$','@']{1}[0-9]{1}/,
                message:"*Password should contain at least one Uppercase character , one special character and one number"
            }
        },
        addressvalidation:{
            required:{
                value:true,
                message:"Address is required"
            }
        },
        datevalidation:{
            required:{
                value:true,
                message:"Date is required"
            }
        },
        gendervalidation:{
            required:{
                value:true,
                message:"Gender is required"
            }
        },  
        namevalidation :{
            required: {
                value:true,
                message:"*Name is required"            
            },
            minLength:{
                value:3,
                message:"*Name should be at least 3 Characters"
            },
            maxLength:{
                value:14,
                message:"*Name should not be more than 14 Characters"
            }
        },
        confirmvalidation:{
            required:{
                value:true,
                message:"Confirm Password is required"
            },
            validate:(value,{pass1})=>
                value==pass1||"Password does not match"
            
        }
    }
    return(
        <div style={{backgroundColor:"aqua"}}>
            <marquee><h1>Sign Up</h1></marquee><br></br>
            <form onSubmit={handleSubmit(submithandler)}>
            <label>Name: </label> <input type="text" placeholder="Enter your name" {...register("name",validation.namevalidation)}></input>
            {
                errors.name && <span>{errors.name.message}</span>
            }<br></br>
            <label>Mobile: </label> <input type="number" placeholder="Enter your Mobile number" {...register("mobile",validation.phonevalidation)}></input>
            {
                errors.mobile && <span>{errors.mobile.message}</span>
            }
            <br></br>
            <label>Email: </label> <input type="email" placeholder="Enter your email" {...register("email",validation.emailvalidation)}></input>
            {
                errors.email && <span>{errors.email.message}</span>
            }<br></br>
            <label>Address: </label> <input type="textarea" placeholder="Enter your Address" {...register("address",validation.addressvalidation)}></input>
            {
                errors.address && <span>{errors.address.message}</span>
            }<br></br>
            <label>Date of Birth: </label> <input type="date"  {...register("date",validation.datevalidation)}></input>
            {
                errors.date && <span>{errors.date.message}</span>
            }<br></br>
            <label>Gender: </label> Male: <input type="radio" value="male" {...register("gender",validation.gendervalidation)}></input>&nbsp;
                                 Female: <input type="radio" value="female" {...register("gender",validation.gendervalidation)}></input>&nbsp;
                                 {
                                    errors.gender && <span>{errors.gender.message}</span>
                                 }<br></br>
            <label>UserName: </label> <input type="text" placeholder="Enter your Username" {...register("uname",validation.namevalidation)}></input>
            {
                errors.uname && <span>{errors.uname.message}</span>
            }<br></br>                     
            <label>Password: </label> <input type="password" placeholder="Enter your password" {...register("pass1",validation.passwordvalidation)}></input>
            {
                errors.pass1 && <span>{errors.pass1.message}</span>
            }
            <br></br>
            <label>Confirm Password: </label> <input type="password" placeholder="Enter your confirm password" {...register("pass2",validation.confirmvalidation)}></input>
            {
                errors.pass2 && <span>{errors.pass2.message}</span>
            }
            <br></br>
            <input type="reset" value="Reset"/>
            <input type="submit" value="Submit" disabled={!isValid}/>
            </form>
            {
                Submitted &&
            <div style={{color:"red"}}>
                <h3>Name:-{display.name}</h3>
                <h3>Mobile:-{display.mobile}</h3>
                <h3>Email:-{display.email}</h3>
                <h3>Address:-{display.address}</h3>
                <h3>Date of Birth:-{display.date}</h3>
                <h3>Gender:-{display.gender}</h3>
                <h3>UserName:-{display.uname}</h3>
                <h3>Password:-{display.pass1}</h3>
                <h3>Confirm Password:-{display.pass2}</h3>    
            </div>
            }
        </div>
    )
}