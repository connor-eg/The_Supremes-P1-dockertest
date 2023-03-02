import "../../shared/general.css"
import { useAppSelector } from "../../shared/Redux/hook";
import { selectSessionToken } from "../../slices/SessionTokenSlice";
import "../../shared/general.css"
import { useState } from "react";
import { BankAccount } from "../../models/BankAccount";
import axios from "axios";
import { SelectUserId } from "../../slices/UserIdSlice";
import NotLoggedInBox from "../NavBar/NotLoggedInBox";

function NewBankAccount(){
    const sessionTokenSelector = useAppSelector(selectSessionToken);
    const userIdSelector = useAppSelector(SelectUserId);

    const [formAccType, setFormAccType] = useState("CHECKING");
    const [feedback, setFeedback] = useState("You can create a new bank account here.");

    if (sessionTokenSelector.token === '') {
        return <NotLoggedInBox/>;
    } else {
        return <div className="padded-left"><h2>Create a new bank account here</h2>
        <form>
            <label>What kind of account is this?</label><br />
            <input type="radio" name="rad" value="CHECKING" defaultChecked={true} onChange={() => setFormAccType("CHECKING")} />Checking <br />
            <input type="radio" name="rad" value="SAVINGS" onChange={() => setFormAccType("SAVINGS")}/>Savings <br />
        </form>
        <button onClick={onSubmit}>Submit</button>
        <p>{feedback}</p></div>
    }

    function onSubmit(){
        var newBankAccount: BankAccount = {
            userId: userIdSelector.userId,
            accType: formAccType
        }
        console.log(`[${newBankAccount.accType}]`);
        
        //It's axiosing time
        axios.post<BankAccount>("http://localhost:8080/api/v1/bankaccount", 
            newBankAccount, 
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

export default NewBankAccount;