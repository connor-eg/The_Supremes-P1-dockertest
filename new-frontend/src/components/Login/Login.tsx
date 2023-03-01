import { useState } from "react";
import { Link } from "react-router-dom";

function Login(){
    const [userName, setUserName] = useState("");
    const [password, setPassword] = useState("");

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
    </div>

    function onUserNameChange(event: React.ChangeEvent<HTMLInputElement>){
        setUserName(event.target.value);
    }

    function onPasswordChange(event: React.ChangeEvent<HTMLInputElement>){
        setPassword(event.target.value);
    }

    function onSubmit(){
        console.log(`[${userName}]:[${password}]`);

        
    }
}

export default Login;