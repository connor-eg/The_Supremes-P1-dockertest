import "./Header.css";
import disney from "./Disney.png";

function Header() {
  return <>
    <header>
      <h2>The Surpremes</h2>

      <input type="checkbox" id="toggle" className="toggle" />
      <div className="burger" htmlFor="toggle"><div></div></div>
      
      <nav>
        <ul>
          <li>home</li>
          <li>About</li>
          <li>Sign in</li>
        </ul>
      </nav>
    </header>
  </>;
}
export default Header;
