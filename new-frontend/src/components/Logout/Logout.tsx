import "../../shared/general.css";
import { useAppDispatch } from "../../shared/Redux/hook";
import { resetSessionToken } from "../../slices/SessionTokenSlice";
import { resetUserId } from "../../slices/UserIdSlice";

function Logout(){
    const dispatch = useAppDispatch();
    dispatch(resetSessionToken());
    dispatch(resetUserId());
    return <div className="padded-left">
        <h2>You have successfully logged out.</h2>
        <p>Notice how you can no longer do things.</p>
    </div>
}

export default Logout;