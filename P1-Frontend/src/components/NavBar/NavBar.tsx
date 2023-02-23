import {
  createBrowserRouter,
  RouterProvider,
  Route,
  Link,
} from "react-router-dom";

function NavBar() {
  return (
    <div>
      <header className="navbar">
        <div className="logo">
          <h2>The Surpreme</h2>
        </div>
        <input type="checkbox" id="toggle" className="toggle" />
        <div className="burger">
          <div></div>
        </div>

        <nav>
          <ul>
            <li>
              <a href="{}">Home</a>
            </li>
            <li>
              <a href="login.asp">Login</a>
            </li>
            <li>
              <a href="register.asp">Register</a>
            </li>
            <li>
              <a href="logout.asp">Logout</a>
            </li>
          </ul>
        </nav>
      </header>
    </div>
  );
}

export default NavBar;
