import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from "../pages/Login";
import Register from "../pages/Register";
import PrivateRoute from "./PrivateRoute";
import Chat from "../pages/Chat";

function App() {
      return (    
            <BrowserRouter>
                  <Routes>
                        <Route path="/login" element={<Login />} /> {/* Rota raiz */}
                        <Route path="/register" element={<Register />} />
                        
                        <Route path="/*" element={
                              <PrivateRoute>
                                    <Chat/>        
                              </PrivateRoute>
                        }
                        />
                  </Routes>
            </BrowserRouter>
  );
}

export default App;
