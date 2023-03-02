import "../../shared/general.css"
import { useAppSelector } from "../../shared/Redux/hook";
import { selectSessionToken } from "../../slices/SessionTokenSlice";
import "../../shared/general.css"
import { Link } from "react-router-dom";

function Template(){
    const sessionTokenSelector = useAppSelector(selectSessionToken);
    if (sessionTokenSelector.token === '') {
        return (<div className="padded-left">
        <h2>This action requires that you are logged in.</h2>
        <Link to="/Login">Click here to get to the login page</Link>
        </div>)
    } else {
        return <div className="padded-left">A form probably goes here</div>
    }
}

export default Template;