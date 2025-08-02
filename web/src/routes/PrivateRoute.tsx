import { useEffect, useState, type JSX } from "react";
import { Navigate } from "react-router-dom";
import axiosInstance from "@/utils/axiosInstance";

async function validateToken(token: string): Promise<boolean> {
  try {
    const response = await axiosInstance.get("/auth/validate-token", {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
    return response.status === 200;
  } catch (error) {
    console.error("Token validation failed:", error);
    return false;
  }
}

export default function PrivateRoute({ children }: { children: JSX.Element }) {
  const [isAuthenticated, setIsAuthenticated] = useState<boolean | null>(null);

  useEffect(() => {
    const token = localStorage.getItem("token");
    if (!token) {
      setIsAuthenticated(false);
      return;
    }

    validateToken(token).then((isValid) => {
      if (!isValid) {
        localStorage.removeItem("token");
      }
      setIsAuthenticated(isValid);
    });
  }, []);

  if (isAuthenticated === null) {
    return <div>Carregando...</div>; // ou um spinner
  }

  return isAuthenticated ? children : <Navigate to="/login" />;
}
