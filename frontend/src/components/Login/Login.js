import React, { useState } from 'react';
import Header from "../Header/Header";
import axios from 'axios'; // Ensure axios is installed
import "./Login.css";

function Login({ onLogin, toggleView }) { // Accept toggleView as a prop
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [isDoctor, setIsDoctor] = useState(false);

  const handleSubmit = (e) => {
    e.preventDefault();
    onLogin(username, password, isDoctor);
  };

  return (
    <div className="App">
      <Header userName={'anonymity'} />
      <div className="login-page">
        <div className="logo-section">
          <div className="logo">200-OK</div>
          <div className="logo-subtext">Patient Tracker System</div>
        </div>
        <form className="login-form" onSubmit={handleSubmit}>
          <div className="login-title">Sign in</div>
          <div className="input-container">
            <label htmlFor="username">Username:</label>
            <input
              type="text"
              id="username"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              required
            />
            <label htmlFor="password">Password:</label>
            <input
              type="password"
              id="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
            <div className="switch-container">
              Sign in as a doctor
              <label className="switch-label">
                <input
                  type="checkbox"
                  checked={isDoctor}
                  onChange={(e) => setIsDoctor(e.target.checked)}
                />
                <span className="slider"></span>
              </label>
            </div>
            <button type="submit" className="login-button">Login</button>
          </div>
          <div className="signup-toggle">
            <button type="button" onClick={toggleView} className="toggle-button">
              Don't have an account? Sign Up
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default Login;
