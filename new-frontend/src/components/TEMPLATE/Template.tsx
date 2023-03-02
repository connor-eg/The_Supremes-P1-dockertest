import "../../shared/general.css"
import { useAppSelector } from "../../shared/Redux/hook";
import { selectSessionToken } from "../../slices/SessionTokenSlice";
import NotLoggedInBox from "../NavBar/NotLoggedInBox";

function Template(){
    const sessionTokenSelector = useAppSelector(selectSessionToken);
    if (sessionTokenSelector.token === '') {
       return <NotLoggedInBox/>;
    } else {
        return <div className="padded-left">A form probably goes here</div>
    }
}

export default Template;