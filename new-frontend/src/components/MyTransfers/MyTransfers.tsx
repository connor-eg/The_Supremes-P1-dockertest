import "../../shared/general.css";

import { useAppSelector } from "../../shared/Redux/hook";
import { selectSessionToken } from "../../slices/SessionTokenSlice";
import axios from "axios";
import { useState } from "react";
import NotLoggedInBox from "../NavBar/NotLoggedInBox";
import { Transfer } from "../../models/Transfer";
import { useLocation } from "react-router-dom";
import baseURL from "../../shared/config";


function MyTransfers(){
    const location = useLocation();
    var viewingType: string = location.state.viewingType;
    var bankAccId: number = location.state.bankAccId;
    var year: number = location.state.year;
    var month: number = location.state.month;

    const sessionTokenSelector = useAppSelector(selectSessionToken);
    const [myTransfers, setMyTransfers] = useState<Transfer[]>([]);
    const [feedback, setFeedback] = useState("Loading bank accounts...");
    const [runOnce, setRunOnce] = useState(false);
    
    if (sessionTokenSelector.token === '') {
        return <NotLoggedInBox />;
    } else {
        if(!runOnce){
            switch(viewingType){
                case "ALL":
                    getAllTransfers();
                    break;
                case "MONTH":
                    getTransfersByMonth();
                    break;
                case "DEPOSIT":
                    getDeposits(true);
                    break;
                case "WITHDRAW":
                    getWithdraws(false);
                    break;
                default:
                    return <div>
                        <h2>Boi.</h2>
                        <p>Did you try to access this page directly?</p>
                    </div>
            }
            
        }
        if(myTransfers.length === 0) {
            return <div className="padded-left">{feedback}</div>
        } else {
            return <div className="padded-left">
                <h1>Transfers for account #{bankAccId}</h1>
                <table >
                    <tbody>
                        <tr>
                            <th>Transfer ID</th>
                            <th>Amount</th>
                            <th>Time</th>
                            <th>Description</th>
                        </tr>
                        {myTransfers.map((tra) => {
                            return (<tr key={tra.id}>
                                <td>{tra.id}</td>
                                <td>{tra.isDeposit? "+" : "-"}${tra.amount}</td>
                                <td>{tra.time}</td>
                                <td>{tra.description}</td>
                            </tr>)
                        })}
                    </tbody>
                </table>
            </div>
        }
    }

    function getAllTransfers(){
        axios.get<Transfer[]>(baseURL + "/api/v1/transfer/my", {
            headers: {
                "accountid": bankAccId,
                "sessionToken": `${sessionTokenSelector.token}`
            }
        })
        .then(accounts => {
            setMyTransfers(accounts.data);
        })
        .catch(reason => {
            setFeedback("Something went wrong while retreiving your transfers.");
        })
        .finally(() => {
            setRunOnce(true);
            setFeedback("There are no transfers for that account.");
        })
    }

    function getTransfersByMonth(){
        axios.get<Transfer[]>(baseURL + "/api/v1/transfer/my/bytime", {
            headers: {
                "accountid": bankAccId,
                "sessionToken": `${sessionTokenSelector.token}`,
                "year": year,
                "month": month
            }
        })
        .then(accounts => {
            setMyTransfers(accounts.data);
        })
        .catch(reason => {
            setFeedback("Something went wrong while retreiving your transfers.");
        })
        .finally(() => {
            setRunOnce(true);
            setFeedback("There are no transfers for that account.");
        })
    }

    function getDeposits(getDeposits: boolean){
        axios.get<Transfer[]>(baseURL + '/api/v1/transfer/my/deposits', {
            headers: {
                "accountid": bankAccId,
                "sessionToken": `${sessionTokenSelector.token}`
            }
        })
        .then(accounts => {
            setMyTransfers(accounts.data);
        })
        .catch(reason => {
            setFeedback("Something went wrong while retreiving your transfers.");
        })
        .finally(() => {
            setRunOnce(true);
            setFeedback("There are no transfers for that account.");
        })
    }

    function getWithdraws(getDeposits: boolean){
        axios.get<Transfer[]>(baseURL + '/api/v1/transfer/my/withdraws', {
            headers: {
                "accountid": bankAccId,
                "sessionToken": `${sessionTokenSelector.token}`
            }
        })
        .then(accounts => {
            setMyTransfers(accounts.data);
        })
        .catch(reason => {
            setFeedback("Something went wrong while retreiving your transfers.");
        })
        .finally(() => {
            setRunOnce(true);
            setFeedback("There are no transfers for that account.");
        })
    }
}

export default MyTransfers;