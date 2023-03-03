import axios from "axios";
import { useState } from "react";
import { UserAccount } from "../../models/UserAccount";
import baseURL from "../../shared/config";
import "../../shared/general.css";

function Register(){
    const [formUserName, setFormUserName] = useState("");
    const [formPassword, setFormPassword] = useState("");
    const [feedback, setFeedback] = useState("Please fill out the form above to register an account.");

    return <div className="padded-left">
        <h2>Register here.</h2>
        <form onSubmit={onSubmit}>
            <label>Username</label>
            <input type="username" onChange={onUserNameChange} />
            <label>Password</label>
            <input type="password" onChange={onPasswordChange} />
        </form>
        <button onClick={onSubmit}>create account</button>
        <br />
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
        var newUser: UserAccount = {
            username: formUserName,
            password: formPassword
        }
        axios.post(baseURL + "/home/register", newUser)
        .then(response => {
            console.log(response);
            setFeedback("Successfully registered a new account.");
        })
        .catch(exception => {
            setFeedback("Something went wrong while registering your account.");
        });
    }
}
export default Register;