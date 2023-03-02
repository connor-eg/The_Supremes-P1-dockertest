import { Link } from "react-router-dom";
import "./NavBar.css"

function NavBar() {
    // Have a component given to us by react-router that allows nagivation
    return <div className="flex">
        <Link to="/">Home</Link>
        <Link to="/Register">Register</Link>
        <Link to="/Login">Log in</Link>
        <Link to="/Logout">Log out</Link>
        <Link to="/UpdateProfile">Update Profile</Link>
        <Link to="/NewBankAccount">Create a new bank account</Link>
        <Link to="/">Home</Link>
        <Link to="/">Home</Link>
        <Link to="/">Home</Link>
        <Link to="/">Home</Link>
        <Link to="/">Home</Link>
    </div>
}

export default NavBar;