import axios from "axios";
import { useState } from "react";
import { Link } from "react-router-dom";
import { UserAccount } from "../../models/UserAccount";

function Login(){
    const [formUserName, setFormUserName] = useState("");
    const [formPassword, setFormPassword] = useState("");
    const [feedback, setFeedback] = useState("Please fill out the form above to log in to your account.");

    return <div>
        <h2>Log in or do not.</h2>
        <form onSubmit={onSubmit}>
            <label>Username</label>
            <input type="username" onChange={onUserNameChange} /> 
            <label>Password</label>
            <input type="password" onChange={onPasswordChange} /> 
        </form>
        <button onClick={onSubmit}>log in</button>
        <Link to={"/"}><button>do not</button></Link>
        <p>{feedback}</p>
    </div>

    function onUserNameChange(event: React.ChangeEvent<HTMLInputElement>){
        if(event.target.value != null){
            setFormUserName(event.target.value);
        }
    }

    function onPasswordChange(event: React.ChangeEvent<HTMLInputElement>){
        if(event.target.value != null){
            setFormPassword(event.target.value);
        }
    }

    function onSubmit(){
        console.log(`[${formUserName}]:[${formPassword}]`);
        //It's axiosing time
        var loginUser: UserAccount = {
            username: formUserName,
            password: formPassword
        }
        axios.post<UserAccount>("http://localhost:8080/home/login", loginUser)
        .then(response => {
            console.log(response.data);
            setFeedback("Successfully logged in.");
            

        })
        .catch(exception => {
            setFeedback("Something went wrong while logging in to your account.");
            console.log(exception);
        });
    }
}

export default Login;