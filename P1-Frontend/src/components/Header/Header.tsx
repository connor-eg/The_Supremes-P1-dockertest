import NavBar from "../NavBar/NavBar";
import "./Header.css";
import disney from "./Disney.png";

function Header() {
  return (
    <div className="red-text">
      This is the page header
      <div>
        <NavBar />
      </div>
    </div>
  );
}
export default Header;
