import React, { useState } from 'react';
import "./Login.css";

function Login({ onLogin }) {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    onLogin(username, password);
  };

  return (
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
          <button type="submit" className="login-button">Login</button>
        </div>
      </form>
    </div>
  );
};

export default Login;
