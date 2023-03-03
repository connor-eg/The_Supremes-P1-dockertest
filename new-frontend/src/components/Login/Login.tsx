import axios from "axios";
import { useState } from "react";
import { Link } from "react-router-dom";
import { UserAccount } from "../../models/UserAccount";
import baseURL from "../../shared/config";
import "../../shared/general.css";
import { useAppDispatch } from "../../shared/Redux/hook";
import { setSessionToken } from "../../slices/SessionTokenSlice";
import { setUserId } from "../../slices/UserIdSlice";

function Login(){
    const [formUserName, setFormUserName] = useState("");
    const [formPassword, setFormPassword] = useState("");
    const [feedback, setFeedback] = useState("Please fill out the form above to log in to your account. You may also choose not to.");
    const dispatch = useAppDispatch();

    return <div className="padded-left">
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
        axios.post<UserAccount>(baseURL + "/home/login", loginUser)
        .then(response => {
            console.log(response.data);
            if(response.data.sessionToken == null || response.data.userAccountId == null){
                setFeedback("Something went wrong while logging in to your account.");
                return;
            }
            setFeedback("Successfully logged in.");
            dispatch(setSessionToken({token: response.data.sessionToken}));
            dispatch(setUserId({userId: response.data.userAccountId}));
        })
        .catch(exception => {
            setFeedback("Something went wrong while logging in to your account.");
            console.log(exception);
        });
    }
}

export default Login;