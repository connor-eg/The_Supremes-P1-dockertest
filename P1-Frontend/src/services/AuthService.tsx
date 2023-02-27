import axios from "axios";

//better to not hardcode and use env variables
const API_URL = "http://localhost:8080/home/";
const userKey = "user";

class AuthService {
  login(username: string, password: string) {
    return axios
      .post(API_URL + "login", {
        username,
        password,
      })
      .then((response) => {
        console.log(
          "🚀 ~ file: AuthService.tsx:14 ~ AuthService ~ .then ~ response:",
          response
        );
        if (response.data.accessToken) {
          localStorage.setItem(userKey, JSON.stringify(response.data));
        }
        return response.data;
      });
  }

  logout() {
    localStorage.removeItem(userKey);
  }

  register(username: string, email: string, password: string) {
    return axios
      .post(API_URL + "register", {
        username,
        email,
        password,
      })
      .then((response) => {
        if (response.data.accessToken) {
          localStorage.setItem(userKey, JSON.stringify(response.data));
        }
        return response.data;
      });
  }

  getCurrentUser() {
    const userStr = localStorage.getItem(userKey);
    if (userStr) return JSON.parse(userStr);

    return null;
  }
}
export default new AuthService();
