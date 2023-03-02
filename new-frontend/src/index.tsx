import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import NavBar from './components/NavBar/NavBar';
import { store } from './shared/Redux/store';
import { Provider } from 'react-redux';
import Login from './components/Login/Login';
import Register from './components/Register/Register';
import Logout from './components/Logout/Logout';
import UpdateProfile from './components/UpdateProfile/UpdateProfile';
import NewBankAccount from './components/NewBankAccount/NewBankAccount';

const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement
);
root.render(
  <React.StrictMode>
    <Provider store={store}>
      <BrowserRouter>
        <NavBar />
        <Routes>
          <Route path="/" element={<App />}/> 
          <Route path="/Login" element={<Login />}/> 
          <Route path="/Register" element={<Register />}/> 
          <Route path="/Logout" element={<Logout />}/>
          <Route path="/UpdateProfile" element={<UpdateProfile />}/>
          <Route path="/NewBankAccount" element={<NewBankAccount />} />
        </Routes>
      </BrowserRouter>
    </Provider>
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
