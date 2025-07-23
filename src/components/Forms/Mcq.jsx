import React, { useState } from "react"
import { useForm } from "react-hook-form"

export const Mcq=()=>
{
    const{register,handleSubmit}=useForm()
    const[display,setdisplay]=useState([])
    
    const submithandler =(data)=>
    {
        var options = []
        options.push(data.option1)
        options.push(data.option2) 
        options.push(data.option3)
        options.push(data.option4)

        var quesobj = {
            questions:data.question,
            options:options,
            correctans:data.correctoption 
        }
        // console.log(data)
        setdisplay([...display,quesobj])
    }
    return(
        <div>
            <form onSubmit={handleSubmit(submithandler)}>
                Question : <input type="text" placeholder=" Enter trhe question "{...register("question")}/><br/>
                Option1 : <input type="text" placeholder=" Enter option1 "{...register("option1")}/><br/>
                Option2 : <input type="text" placeholder=" Enter option2 "{...register("option2")}/><br/>
                Option3 : <input type="text" placeholder=" Enter option3 "{...register("option3")}/><br/>
                Option4 : <input type="text" placeholder=" Enter option4 "{...register("option4")}/><br/>
                Correct Answer : <input type="text" placeholder=" Enter correct option "{...register("correctoption")}/><br/>
                <input type="submit" value="submit"/>
            </form>
            <div>
                <h1>Result</h1>
                {
                    display.map((ques)=>{
                        return(
                            <div>
                                <h2>Question:- {ques.questions}</h2> 
                                {
                                    ques.options.map((op)=>
                                    {
                                        return(
                                            <li>{op}</li>
                                        )
                                    })
                                }   
                            </div>
                        )
                    })
                }
            </div>
        </div>
    )
}