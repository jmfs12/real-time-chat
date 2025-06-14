'use client'
import { Button } from '@/components/ui/button'
import { Input } from '@/components/ui/input'
import { Label } from '@/components/ui/label'
import { AiFillEye } from "react-icons/ai";
import { AiOutlineUser } from "react-icons/ai";
import { AiFillLock } from "react-icons/ai";
import { BiSolidUser } from "react-icons/bi";
import { useState } from 'react';
import { AiOutlineMail } from "react-icons/ai";

export default function Login() {
  const [isLogin, setIsLogin] = useState(true);

  const handleRegister = () => {
      setIsLogin(false);
  }
  
  const handleLogin = () => {
      setIsLogin(true);
  }
    
  return (
    <div className="bg-gray-100 h-screen w-full flex items-center justify-center font-mono">
      {isLogin && (
        <>
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
                className="mt-1 bg-sky-50 rounded-none"
              ></Input>
            </div>
            <div className="flex items-center justify-between mt-2">
              <div className="bg-sky-100 p-1.5 text-2xl mt-1">
                <AiFillLock />
              </div>
              <Input
                type="password"
                placeholder="Password"
                className="mt-1 bg-sky-50 rounded-none"
              ></Input>
            </div>

            <div className="flex items-center justify-begin mt-1 text-xs">
              <span>Não possui uma conta?</span>
              <Button
                variant="ghost"
                className="text-xs font-normal px-1.5 text-sky-500 hover:text-sky-700 hover:bg-white cursor-pointer"
                onClick={handleRegister}
              >
                Registre-se
              </Button>
            </div>
          </div>
          <div className="absolute flex items-center justify-center mt-73">
            <Button className="bg-sky-300 rounded-lg absolute w-60 h-10 z-0 hover:bg-sky-500 cursor-pointer font-extrabold text-base text-gray-800">
              LOGIN
            </Button>
          </div>
        </>
      )}

      {!isLogin && (
        <>
          <div className="bg-sky-300 rounded-full text-8xl p-5 absolute mt-[-350px] shadow-sm shadow-sky-600 z-20">
            <AiOutlineUser />
          </div>
          <div className="bg-white p-15 rounded-lg shadow-md shadow-sky-100 w-3/4 md:w-1/2 lg:w-1/3 z-10">
            <div className="flex items-center justify-between mt-2">
              <div className="bg-sky-100 p-1.5 text-2xl mt-1">
                <AiOutlineMail />
              </div>
              <Input
                type="email"
                placeholder="Email"
                className="mt-1 bg-sky-50 rounded-none"
              ></Input>
            </div>

            <div className="flex items-center justify-between mt-2">
              <div className="bg-sky-100 p-1.5 text-2xl mt-1">
                <BiSolidUser />
              </div>
              <Input
                type="username"
                placeholder="Username"
                className="mt-1 bg-sky-50 rounded-none"
              ></Input>
            </div>

            <div className="flex items-center justify-between mt-2">
              <div className="bg-sky-100 p-1.5 text-2xl mt-1">
                <AiFillLock />
              </div>
              <Input
                type="password"
                placeholder="Password"
                className="mt-1 bg-sky-50 rounded-none"
              ></Input>
            </div>

            <div className="flex items-center justify-begin mt-1 text-xs">
              <span>Já possui uma conta?</span>
              <Button
                variant="ghost"
                className="text-xs font-normal px-1.5 text-sky-500 hover:text-sky-700 hover:bg-white cursor-pointer"
                onClick={handleLogin}
              >
                Faça login
              </Button>
            </div>
          </div>
          <div className="absolute flex items-center justify-center mt-85">
            <Button className="bg-sky-300 rounded-lg absolute w-60 h-10 z-0 hover:bg-sky-500 cursor-pointer font-extrabold text-base text-gray-800">
              REGISTRAR
            </Button>
          </div>
        </>
      )}
    </div>
  );

}