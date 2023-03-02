import "./shared/general.css";
import { useAppSelector } from "./shared/Redux/hook";
import { selectSessionToken } from "./slices/SessionTokenSlice";
import { SelectUserId } from "./slices/UserIdSlice";

function App() {
  const sessionTokenSelector = useAppSelector(selectSessionToken);
  const userIdSelector = useAppSelector(SelectUserId);
  return (
    <div className="padded-left">
      <h1>Welcome!</h1>
      <p>This site is not completed, but it does demonstrate our backend functionality</p>
      <p>All content is accessible from the links in the navbar above.</p>

      {(sessionTokenSelector.token !== '' && <p>
        Your session token is {sessionTokenSelector.token}.
        Your user ID is {userIdSelector.userId}.
      </p>)}
    </div>
  );
}

export default App;
