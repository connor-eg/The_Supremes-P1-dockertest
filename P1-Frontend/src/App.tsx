import { useState } from "react";
import "./App.css";
import Header from "./components/Header/Header";
import Register from "./components/Register/Register";
import {
  createBrowserRouter,
  RouterProvider,
  Route,
  Link,
} from "react-router-dom";
import {
  RecoilRoot,
  atom,
  selector,
  useRecoilState,
  useRecoilValue,
} from "recoil";
import Home from "./components/Home/Home";
import Login from "./components/Login/Login";
import Profile from "./components/Profile/Profile";
import ClientError from "./components/ClientError/ClientError";
import Income from "./components/Income/Income";
import Expenses from "./components/Expenses/Expenses";
import Accounts from "./components/Accounts/Accounts";

const router = createBrowserRouter([
  {
    path: "/",
    element: <Register />,
  },
  {
    path: "/profile",
    element: <Profile />,
  },
  {
    path: "/error",
    element: <ClientError />,
  },
  {
    path: "/income",
    element: <Income />,
  },
  {
    path: "/expenses",
    element: <Expenses />,
    //
  },
  {
    path: "/accounts",
    element: <Accounts />,
    //
  },
]);

const authRouter = createBrowserRouter([
  {
    path: "/",
    element: <Register />, //home route
  },
  {
    path: "/register",
    element: <Register />,
  },
  {
    path: "/login",
    element: <Login />,
  },
]);

const userState = atom<{}>({
  key: "userState", // unique ID (with respect to other atoms/selectors)
  default: "", // default value (aka initial value)
});

function App() {
  const [authenticated, setAuthenticated] = useState(true); //initially false
  /*
  (make global state )
  Check local storage to see if user instance exists,
  if it does it would load login
  else, load register
  */
  const user = {
    userID: "",
    email: "",
    jwt: "",
  };

  return (
    <RecoilRoot>
      <div className=" mt-20">
        <Header />
        <RouterProvider router={authenticated ? router : authRouter} />
      </div>
    </RecoilRoot>
  );
}

export default App;
