import React, { useState } from 'react';
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
      <div class="container">
      
        <div className="logo-section">
        <div class="title-section">
          <div className="logo">200-OK</div>
          <div className="logo-subtext">Patient Tracker System</div>
          </div>
          <div className="login-page">
        
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
              <div className='switch-text'>Sign in as a doctor</div>
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
      </div>
      </div>
  );
};

export default Login;
