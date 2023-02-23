import { ChangeEventHandler } from "react";
import "./Theme.css"
// darkMode
const setDark =  () => {
    localStorage.setItem("theme", "dark");
    document.documentElement.setAttribute("data-theme", "dark");
};

const setLight = () => {
    localStorage.setItem("theme", "Light");
    document.documentElement.setAttribute("data-theme", "light");
}

const stordTheme = localStorage.getItem("theme");

const prefersDark = 
    window.matchMedia &&
    window.matchMedia("(perfers-colors-scheme: dark)").matches;

const defaultDark = 
    stordTheme === "dark" || (stordTheme === null && prefersDark);

if(defaultDark) {
    setDark();
}

const toggleTheme: ChangeEventHandler<HTMLInputElement> = (e) => {
    if(e.target.checked) {
        setDark();
    } else {
        setLight();
    }
};


function Theme(){
    return(
       <div>
            <p>Light</p>
            <label className="toggle-theme" htmlFor="checkbox">
                <input type="checkbox" id="checkbox" onChange={toggleTheme} defaultChecked={defaultDark}/>
                <div className="slider round"></div>
            </label>
            <p>Dark</p>
       </div>
    )
}

export default Theme;