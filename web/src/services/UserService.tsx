import axios from "axios";
export const axiosInstance = axios.create({
  baseURL: "http://localhost:8080",
});

export default class UserService {
  static async login(email : string, password : string) {
    try {
      const response = await axiosInstance.post("/auth/login", {
        email,
        password,
      });
      return response.data;
    } catch (error : any) {
      throw error.response ? error.response.data : error.message;
    }
  }

  static async register(email : string, username : string,  password : string) {
    try {
      const response = await axiosInstance.post("/auth/register", {
        email,
        username,
        password,
      });
      return response.data;
    } catch (error : any) {
      throw error.response ? error.response.data : error.message;
    }
  }
}
