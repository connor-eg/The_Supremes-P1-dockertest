import NavBar from "../NavBar/NavBar";
import Theme from "../Theme/Theme";
import "./Header.css";
import disney from "./Disney.png";

function Header() {
  return (
    <div className="red-text">
      This is the page header
      <div className="">
        <NavBar />
        <Theme />
      </div>
    </div>
  );
}
export default Header;
