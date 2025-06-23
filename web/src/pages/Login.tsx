import { Button } from "../components/ui/button";
import { Input } from "../components/ui/input";
import { AiOutlineUser, AiFillLock } from "react-icons/ai";
import { BiSolidUser } from "react-icons/bi";
import { useState, type ChangeEvent } from "react";
import UserService from "../services/UserService";
import { toast } from "sonner";
import { NavLink, useNavigate } from "react-router-dom";

interface LoginResponse {
  token: string;
}

export default function Login() {
  const [email, setEmail] = useState<string>("");
  const [password, setPassword] = useState<string>("");

  const navigate = useNavigate();

  const handleLoginSubmit = async (): Promise<void> => {
    try {
      const response: LoginResponse = await UserService.login(email, password);
      localStorage.setItem("token", response.token);
      toast.success("Login successful! You are now logged in.");
      navigate("/chat");
    } catch (error) {
      toast.error("Login failed. Please try again.");
    }
  };

  // Tipagem dos eventos de input
  const onEmailChange = (e: ChangeEvent<HTMLInputElement>) =>
    setEmail(e.target.value);
  const onPasswordChange = (e: ChangeEvent<HTMLInputElement>) =>
    setPassword(e.target.value);

  return (
    <div className="bg-gray-100 h-screen w-screen flex items-center justify-center font-poppins">
      <div className="bg-sky-300 rounded-full text-8xl p-5 absolute mt-[-300px] shadow-sm shadow-sky-600 z-20">
        <AiOutlineUser />
      </div>
      <div className="bg-white p-15 rounded-lg shadow-md shadow-sky-100 w-3/4 md:w-1/2 lg:w-1/3 z-10">
        <div className="flex items-center justify-between mt-2">
          <div className="bg-sky-100 p-1.5 text-2xl mt-1">
            <BiSolidUser />
          </div>
          <Input
            type="email"
            placeholder="Email"
            className="mt-1 bg-sky-50 rounded-none border-none"
            value={email}
            onChange={onEmailChange}
          />
        </div>
        <div className="flex items-center justify-between mt-2">
          <div className="bg-sky-100 p-1.5 text-2xl mt-1">
            <AiFillLock />
          </div>
          <Input
            type="password"
            placeholder="Password"
            className="mt-1 bg-sky-50 rounded-none border-none"
            value={password}
            onChange={onPasswordChange}
          />
        </div>

        <div className="flex items-center justify-begin mt-1 text-xs">
          <span>Não possui uma conta?</span>
            <NavLink
              to="/register"
              className="text-xs font-normal px-1.5 text-sky-500 hover:text-sky-700 hover:bg-white cursor-pointer"
            >
              Faça seu cadastro
            </NavLink>
        </div>
      </div>
      <div className="absolute flex items-center justify-center mt-73">
        <Button
          className="bg-sky-300 rounded-lg absolute w-60 h-10 z-0 hover:bg-sky-500 cursor-pointer font-extrabold text-base text-gray-800"
          onClick={handleLoginSubmit}
        >
          LOGIN
        </Button>
      </div>
    </div>
  );
}
