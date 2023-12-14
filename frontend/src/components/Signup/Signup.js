import React, { useState } from 'react';
import "./Signup.css";

function Signup({ onSignup, toggleView }) {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [email, setEmail] = useState('');
  const [userType, setUserType] = useState('patient'); // new state for user type
  const [specialization, setSpecialization] = useState(''); // for doctor
  const [dateOfBirth, setDateOfBirth] = useState(''); // for patient
  const [gender, setGender] = useState(''); // for patient

  const handleSubmit = (e) => {
    e.preventDefault();

    // Check if passwords match
    if (password !== confirmPassword) {
      alert('Passwords do not match.');
      return;
    }

    // Additional logic for handling signup based on user type
    // For now, we're just passing the information to onSignup
    onSignup(username, password, email, userType, { specialization, dateOfBirth, gender });
  };

  return (
    <div className="App">
      <div className="signup-page">
        <form className="signup-form" onSubmit={handleSubmit}>
          <div className="signup-title">Sign up</div>

          {/* Email Input */}
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

          {/* Username Input */}
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

          {/* Password Input */}
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

          {/* Password Confirmation Input */}
          <div className="input-container">
            <label htmlFor="confirmPassword">Confirm Password:</label>
            <input
              type="password"
              id="confirmPassword"
              value={confirmPassword}
              onChange={(e) => setConfirmPassword(e.target.value)}
              required
            />
          </div>

          {/* User Type Selection */}
          <div className="input-container">
            <label>User Type:</label>
            <select value={userType} onChange={(e) => setUserType(e.target.value)}>
              <option value="patient">Patient</option>
              <option value="doctor">Doctor</option>
            </select>
          </div>

          {/* Conditional Fields Based on User Type */}
          {userType === 'doctor' && (
            <div className="input-container">
              <label htmlFor="specialization">Specialization:</label>
              <input
                type="text"
                id="specialization"
                value={specialization}
                onChange={(e) => setSpecialization(e.target.value)}
                required
              />
            </div>
          )}
          {userType === 'patient' && (
            <>
              <div className="input-container">
                <label htmlFor="dateOfBirth">Date of Birth:</label>
                <input
                  type="date"
                  id="dateOfBirth"
                  value={dateOfBirth}
                  onChange={(e) => setDateOfBirth(e.target.value)}
                  required
                />
              </div>
              <div className="input-container">
                <label htmlFor="gender">Gender:</label>
                <input
                  type="text"
                  id="gender"
                  value={gender}
                  onChange={(e) => setGender(e.target.value)}
                  required
                />
              </div>
            </>
          )}

          {/* Sign Up Button */}
          <button type="submit" className="signup-button">Sign Up</button>

          {/* Toggle to Login View */}
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
