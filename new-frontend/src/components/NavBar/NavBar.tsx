import { Link } from "react-router-dom";
import { useAppSelector } from "../../shared/Redux/hook";
import { selectSessionToken } from "../../slices/SessionTokenSlice";
import "./NavBar.css";
import "../../shared/general.css";

function NavBar() {
    const sessionTokenSelector = useAppSelector(selectSessionToken);
    // Have a component given to us by react-router that allows nagivation
    return <div className="flex padded-left">
        <Link to="/">Home</Link>
        {(sessionTokenSelector.token === '' && <Link to="/Register">Register</Link>)}
        {(sessionTokenSelector.token === '' && <Link to="/Login">Log in</Link>)}
        {(sessionTokenSelector.token !== '' && <Link to="/Logout">Log out</Link>)}
        {(sessionTokenSelector.token !== '' && <Link to="/UpdateProfile">Update Profile</Link>)}
        {(sessionTokenSelector.token !== '' && <Link to="/NewBankAccount">Create a new bank account</Link>)}
        {(sessionTokenSelector.token !== '' && <Link to="/MyBankAccounts">View my bank accounts</Link>)}
    </div>
}

export default NavBar;