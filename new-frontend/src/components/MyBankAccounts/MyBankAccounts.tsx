import "../../shared/general.css"
import { useAppSelector } from "../../shared/Redux/hook";
import { selectSessionToken } from "../../slices/SessionTokenSlice";
import axios from "axios";
import { useState } from "react";
import { BankAccount } from "../../models/BankAccount";
import NotLoggedInBox from "../NavBar/NotLoggedInBox";
import { SelectUserId } from "../../slices/UserIdSlice";
import { Link } from "react-router-dom";
import baseURL from "../../shared/config";


function MyBankAccounts(){
    const sessionTokenSelector = useAppSelector(selectSessionToken);
    const userIdSelector = useAppSelector(SelectUserId);
    const [myBankAccounts, setMyBankAccounts] = useState<BankAccount[]>([]);
    const [runOnce, setRunOnce] = useState(false);
    const [feedback, setFeedback] = useState("Loading bank accounts...");

    if (sessionTokenSelector.token === '') {
        return <NotLoggedInBox />;
    } else {
        if(!runOnce){axios.get<BankAccount[]>(baseURL + "/api/v1/bankaccount/my", {
            headers: {
                "sessionToken": `${sessionTokenSelector.token}`,
                "userId": userIdSelector.userId
            }
        })
        .then(accounts => {
            setMyBankAccounts(accounts.data);
        })
        .catch(reason => {
            setFeedback("Something went wrong while retreiving your bank accounts.");
        })
        .finally(() => {
            setRunOnce(true);
            setFeedback("You have no bank accounts!");
        })}
        if(myBankAccounts.length === 0) {
            return <div className="padded-left">
                <h2>My bank accounts</h2>
                <p>{feedback}</p></div>
        } else {
            return <div className="padded-left">
                <h2>My bank accounts</h2>
                <table>
                    <tbody>
                        <tr>
                            <th>Account ID</th>
                            <th>Account type</th>
                            <th>Balance</th>
                        </tr>
                        {myBankAccounts.map((acc) => {
                            return (<tr key={acc.id}>
                                <td><Link to={"/TransferRequest"} state={{bankAccId: acc.id}}>{acc.id}</Link></td>
                                <td>{acc.accType}</td>
                                <td>${acc.balance}</td>
                            </tr>)
                        })}
                    </tbody>
                </table>
            </div>
        }
    }
}

export default MyBankAccounts;