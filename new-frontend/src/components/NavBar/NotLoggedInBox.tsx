import { Link } from "react-router-dom";
import "./NotLoggedInBox.css";
import "../../shared/general.css";

function NotLoggedInBox(){
    return (<div className="padded-left">
    <h2>This action requires that you are logged in.</h2>
    <Link to="/Login" className="goback">Click here to get to the login page</Link>
    </div>)
}

export default NotLoggedInBox;