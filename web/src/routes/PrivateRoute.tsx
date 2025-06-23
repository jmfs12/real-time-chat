import type { JSX } from "react";
import { Navigate } from "react-router-dom";

export default function PrivateRoute( { children }: { children: JSX.Element }) {
      const hasToken = !!localStorage.getItem("token");


      return hasToken ? children : <Navigate to="/login" />;
}