import React, { useState } from 'react';
import Header from "../Header/Header";
import "./Signup.css";

function Signup({ onSignup, toggleView }) {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [email, setEmail] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    // Your signup logic here
    onSignup(username);
  };

  return (
    <div className="App">
      <Header userName={'anonymity'} />
      <div className="signup-page">
        <form className="signup-form" onSubmit={handleSubmit}>
          <div className="signup-title">Sign up</div>

          <div className="input-container">
            <label htmlFor="email">Email:</label>
            <input
              type="email"
              id="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />
          </div>

          <div className="input-container">
            <label htmlFor="username">Username:</label>
            <input
              type="text"
              id="username"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              required
            />
          </div>

          <div className="input-container">
            <label htmlFor="password">Password:</label>
            <input
              type="password"
              id="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </div>

          <button type="submit" className="signup-button">Sign Up</button>
          
          <div className="toggle-view">
            <button type="button" onClick={toggleView} className="toggle-button">
              Already have an account? Login
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}

export default Signup;
