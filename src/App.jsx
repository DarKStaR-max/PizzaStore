import React from 'react';
import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';
import Menu from './components/Menu';
import CustomerProfile from './components/CustomerProfile';
import Login from './components/Login';
import Register from './components/Register';
import AdminLogin from './components/AdminLogin';
import AdminDashboard from './components/AdminDashboard';

function App() {
  return (
    <Router>
      <div>
        <nav>
          <ul>
            <li><Link to="/menu">Menu</Link></li>
            <li><Link to="/profile">Profile</Link></li>
            <li><Link to="/login">Customer Login</Link></li>
            <li><Link to="/register">Register</Link></li>
            <li><Link to="/admin/login">Admin Login</Link></li>
          </ul>
        </nav>
        <Routes>
          <Route path="/" element={<Menu />} />
          <Route path="/menu" element={<Menu />} />
          <Route path="/profile" element={<CustomerProfile />} />
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route path="/admin/login" element={<AdminLogin />} />
          <Route path="/admin/dashboard" element={<AdminDashboard />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;