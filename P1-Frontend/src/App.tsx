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
import Home from "./components/Home/Home";
import Login from "./components/Login/Login";
import Profile from "./components/Profile/Profile";

const router = createBrowserRouter([
  {
    path: "/",
    element: <Register />,
  },
  {
    path: "/profile",
    element: <Profile />,
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

function App() {
  const [authenticated, setAuthenticated] = useState(true);
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
    <div className="">
      <Header />
      <RouterProvider router={authenticated ? router : authRouter} />
    </div>
  );
}

export default App;
