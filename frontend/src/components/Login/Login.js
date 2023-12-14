import React, { useState } from 'react';
import axios from 'axios'; // Ensure axios is installed
import "./Login.css";

function Login({ onLogin, toggleView }) {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [isDoctor, setIsDoctor] = useState(false);
  const [errorMessage, setErrorMessage] = useState(''); // Add an error message state

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      // Replace with your actual backend login endpoint
      console.log(username, password, isDoctor);
      const hashedPassword = password;
      const response = await axios.post('http://localhost:8080/auth/login', {
        username,
        hashedPassword,
        userType: isDoctor ? 'Doctor' : 'Patient'
      });
      console.log(response.data)
      // Call the onLogin function with the response data
      // You might want to pass user data or a token, depending on your backend response
      onLogin(response.data);
      
      // Clear the error message if login is successful
      setErrorMessage('');
    } catch (error) {
      // If there is an error, set the error message to display it
      setErrorMessage(error.response?.data?.message || 'Login failed. Please try again.');
    }
  };

  return (
    <div className="App">
      <div className="container">
        <div className="logo-section">
          <div className="title-section">
            <div className="logo">200-OK</div>
            <div className="logo-subtext">Patient Tracker System</div>
          </div>
          <div className="login-page">
            <form className="login-form" onSubmit={handleSubmit}>
              <div className="login-title">Sign in</div>

              {errorMessage && <p className="error">{errorMessage}</p>} {/* Display any error message */}

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
