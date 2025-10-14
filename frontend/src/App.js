
import './App.css';
import {Routes, Route} from "react-router-dom";
import LoginPage from "./features/auth/LoginPage";
import DashboardPage from "./features/dashboard/DashboardPage";
import RegisterPage from "./features/auth/RegisterPage";


function App() {
  return (
      <Routes>
          <Route path="/login" element={<LoginPage />} />
          <Route path="/dashboard" element={<DashboardPage/>} />
          <Route path ="/register " element={<RegisterPage/>} />
      </Routes>
  );
}

export default App;
