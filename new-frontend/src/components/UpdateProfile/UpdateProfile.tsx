import axios from "axios";
import { useState } from "react";
import { Link } from "react-router-dom";
import { UserAccount } from "../../models/UserAccount";
import "../../shared/general.css"
import { useAppSelector } from "../../shared/Redux/hook";
import { selectSessionToken } from "../../slices/SessionTokenSlice";
import { SelectUserId } from "../../slices/UserIdSlice";

function UpdateProfile() {
    const sessionTokenSelector = useAppSelector(selectSessionToken);
    const userIdSelector = useAppSelector(SelectUserId);

    const [formUserName, setFormUserName] = useState("");
    const [formPassword, setFormPassword] = useState("");
    const [formFirstName, setFormFirstName] = useState("");
    const [formLastName, setFormLastName] = useState("");
    const [formMiddleName, setFormMiddleName] = useState("");
    const [formEmail, setFormEmail] = useState("");
    const [formPhoneNumber, setFormPhoneNumber] = useState("");
    const [feedback, setFeedback] = useState("You can use the above form to update your information");

    if(sessionTokenSelector.token === '') {
        return (<div className="padded-left">
        <h2>This action requires that you are logged in.</h2>
        <Link to="/Login">Click here to get to the login page</Link>
        </div>)
    } else {
        return (<div className="padded-left">
            <h2>Update your information here</h2>
            <form onSubmit={onSubmit}>
                <label>New username</label>
                <input type="text" onChange={onUserNameChange} /> <br />
                <label>New password</label>
                <input type="password" onChange={onPasswordChange} /> <br />
                <label>Your first name</label>
                <input type="text" onChange={onFirstNameChange} /> <br />
                <label>Your last name</label>
                <input type="text" onChange={onLastNameChange} /> <br />
                <label>Current email address</label>
                <input type="email" onChange={onEmailChange} /> <br />
                <label>Your phone number</label>
                <input type="tel" onChange={onPhoneNumberChange} /> <br />
            </form>
            <button onClick={onSubmit}>Update information</button>
            <p>{feedback}</p>
        </div>)
    }

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

    function onFirstNameChange(event: React.ChangeEvent<HTMLInputElement>){
        if(event.target.value != null){
            setFormFirstName(event.target.value);
        }
    }

    function onLastNameChange(event: React.ChangeEvent<HTMLInputElement>){
        if(event.target.value != null){
            setFormLastName(event.target.value);
        }
    }

    function onEmailChange(event: React.ChangeEvent<HTMLInputElement>){
        if(event.target.value != null){
            setFormEmail(event.target.value);
        }
    }

    function onPhoneNumberChange(event: React.ChangeEvent<HTMLInputElement>){
        if(event.target.value != null){
            setFormPhoneNumber(event.target.value);
        }
    }

    function onSubmit(){
        console.log(`[${formUserName}]:[${formPassword}]`);
        //It's axiosing time
        axios.put<UserAccount>("http://localhost:8080/home/update", 
            {
                userAccountId: userIdSelector.userId,
                newUsername: formUserName !== "" ? formUserName : undefined,
                newPassword: formPassword !== "" ? formPassword : undefined,
                newFirstName:  formFirstName !== "" ? formFirstName : undefined,
                newLastName:  formLastName !== "" ? formLastName : undefined,
                newEmail:  formEmail !== "" ? formEmail : undefined,
                newPhoneNumber:  formPhoneNumber !== "" ? formPhoneNumber : undefined 
            }, 
            {
                headers: {
                    "sessionToken": `${sessionTokenSelector.token}`
                }
            })
        .then(response => {
            console.log(response.data);
            setFeedback("Operation was successful, but you'll only see the changes in the browser console.");
        })
        .catch(exception => {
            setFeedback("Something went wrong while updating your information.");
            console.log(exception);
        });
    }
}

export default UpdateProfile;