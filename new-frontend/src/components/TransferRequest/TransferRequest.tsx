import axios from "axios";
import { useState } from "react";
import { Link, useLocation } from "react-router-dom";
import { Transfer } from "../../models/Transfer";
import baseURL from "../../shared/config";
import "../../shared/general.css"
import { useAppSelector } from "../../shared/Redux/hook";
import { selectSessionToken } from "../../slices/SessionTokenSlice";
import NotLoggedInBox from "../NavBar/NotLoggedInBox";

function TransferRequest(){
    const location = useLocation();
    var bankAccId: number = location.state.bankAccId;
    
    const sessionTokenSelector = useAppSelector(selectSessionToken);

    const [formMonth, setFormMonth] = useState(0);
    const [formYear, setFormYear] = useState(0);
    const [amount1, setAmount1] = useState(0);
    const [amount2, setAmount2] = useState(0);
    const [isDeposit, setIsDeposit] = useState(true);
    const [transferDescription, setTransferDescription] = useState("");
    const [outboundAccountId, setOutboundAccountId] = useState(0);

    const [feedback1, setFeedback1] = useState("");
    const [feedback2, setFeedback2] = useState("");

    if (sessionTokenSelector.token === '') {
       return <NotLoggedInBox />;
    } else {
        return <div className="padded-left">
            <h2>Make a new transfer here</h2>
            <form onSubmit={(event) => {event.preventDefault();}}>
                <label>What kind of transfer is this?</label><br />
                <input type="radio" name="rad" value="radDeposit" defaultChecked={true} onChange={() => setIsDeposit(true)} />Deposit <br />
                <input type="radio" name="rad" value="radWithdraw" onChange={() => setIsDeposit(false)}/>Withdraw <br />
                <label htmlFor="inpAmount1">Amount to transfer: $</label> <input id="inpAmount1" type="number" step={".01"} onChange={onAmountChange1} />
                <label htmlFor="inpDesc">Description (optional)</label> <input id="inpDesc" type="text" onChange={onDescriptionChange} /> 
                <button onClick={postTransfer}>Post transfer</button>
                {(feedback1 !== '' && <p>{feedback1}</p>)}
                <br /><br />

                <label htmlFor="inpAmount2">Amount to send: $</label> <input id="inpAmount2" type="number" step={".01"} onChange={onAmountChange2} /> 
                <label htmlFor="outb">Account ID of recipient</label> <input id="outb" type="number" onChange={onOutboundAccountIdChange} /> 
                
                <button  onClick={sendMoney}>Send money</button>
                {(feedback2 !== '' && <p>{feedback2}</p>)}
                <br /><br />
            </form>


            <h2>Ways to view transfers for that account</h2>
            <form onSubmit={(event) => {event.preventDefault();}}>
                
                <label>Get all transfers</label>
                <Link to={"/MyTransfers"} state={{bankAccId: bankAccId, viewingType: "ALL"}}><button>Get</button></Link>
                <br /><br />

                <label>Get transfer by year and month</label><br />
                <label htmlFor="inpYear">Year</label> <input id="inpYear" type="number" onChange={onYearChange} /> 
                <label htmlFor="inpMonth">Month</label> <input id="inpMonth" type="number" onChange={onMonthChange} /> 
                <Link to={"/MyTransfers"} state={{bankAccId: bankAccId, viewingType: "MONTH", year: formYear, month: formMonth}}><button>Get</button></Link>
                <br /><br />
            
                <label>Get only the deposits</label>
                <Link to={"/MyTransfers"} state={{bankAccId: bankAccId, viewingType: "DEPOSIT"}}><button>Get</button></Link>
                <br /><br />
                
                <label>Get only the withdraws</label>
                <Link to={"/MyTransfers"} state={{bankAccId: bankAccId, viewingType: "WITHDRAW"}}><button>Get</button></Link>
                <br /><br />
            </form>
        </div>
    }

    function postTransfer(){
        var newTransfer: Transfer = {
            accountId: bankAccId,
            amount: amount1,
            isDeposit: isDeposit,
            description: transferDescription
        }
        
        //It's axiosing time
        axios.post<string>(baseURL + "/api/v1/transfer", 
            newTransfer, 
            {
                headers: {
                    "sessionToken": `${sessionTokenSelector.token}`
                }
            })
        .then(response => {
            setFeedback1(response.data);
        })
        .catch(exception => {
            setFeedback1("Something went wrong while posting that transfer.");
            console.log(exception);
        });
    }

    function sendMoney(){
        //It's axiosing time
        axios.post<string>(baseURL + "/api/v1/transfer/a2a", 
            {},
            {
                headers: {
                    "sessionToken": `${sessionTokenSelector.token}`,
                    "fromAccount": bankAccId,
                    "toAccount": outboundAccountId,
                    "amount": amount2
                }
            })
        .then(response => {
            setFeedback2(response.data);
        })
        .catch(exception => {
            setFeedback2("Something went wrong while sending your money.");
            console.log(exception);
        });
    }

    function onOutboundAccountIdChange(event: React.ChangeEvent<HTMLInputElement>){
        var x = event.target.value;
        if(x != null){
            if(isNaN(+x)) return;
            setOutboundAccountId(+x);
        }
    }

    function onDescriptionChange(event: React.ChangeEvent<HTMLInputElement>){
        if(event.target.value != null){
            setTransferDescription(event.target.value);
        }
    }

    function onYearChange(event: React.ChangeEvent<HTMLInputElement>){
        var x = event.target.value;
        if(x != null){
            if(isNaN(+x)) return;
            setFormYear(+x);
        }
    }

    function onMonthChange(event: React.ChangeEvent<HTMLInputElement>){
        var x = event.target.value;
        if(x != null){
            if(isNaN(+x)) return;
            setFormMonth(+x);
        }
    }

    function onAmountChange1(event: React.ChangeEvent<HTMLInputElement>){
        var x = event.target.value;
        if(x != null){
            if(isNaN(+x)) return;
            setAmount1(+x);
        }
    }

    function onAmountChange2(event: React.ChangeEvent<HTMLInputElement>){
        var x = event.target.value;
        if(x != null){
            if(isNaN(+x)) return;
            setAmount2(+x);
        }
    }
}

export default TransferRequest;