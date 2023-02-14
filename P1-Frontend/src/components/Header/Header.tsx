import "./Header.css";
import disney from "./Disney.png";

function Header() {
  return <>
    <header>
      <img className="logod" src={disney} alt="disney" />

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
