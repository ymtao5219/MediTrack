import React, { useState } from 'react';
// import { useNavigate } from 'react-router-dom'; // Import useHistory hook from 'react-router-dom'
import axios from 'axios';
import Header from "../Header/Header";
import "./Signup.css";

function Signup({ toggleView }) {
  // const navigate = useNavigate(); // Instantiate the useHistory hook
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [email, setEmail] = useState('');
  const [userType, setUserType] = useState('patient');
  const [specialization, setSpecialization] = useState(''); // for doctors
  const [dateOfBirth, setDateOfBirth] = useState(''); // for patients
  const [gender, setGender] = useState(''); // for patients
  const [errorMessage, setErrorMessage] = useState(''); // State for error messages

  const handleSubmit = async (e) => {
    console.log('Form submitted');
    e.preventDefault();

    // Check if passwords match
    if (password !== confirmPassword) {
      setErrorMessage('Passwords do not match.'); // Set error message state
      return;
    }

    // Construct the signup data based on user type
    const signupData = {
      username,
      hashedPassword: password, // Note: Ensure backend expects 'hashedPassword' not 'password'
      email,
      userType,
      ...(userType === 'doctor' && { specialization }),
      ...(userType === 'patient' && { dateOfBirth, gender }),
    };

    try {
      const response = await axios.post('http://localhost:8080/auth/register', signupData);
      console.log('User registered:', response.data);
      // Redirect to login page after successful signup
      // navigate('/login'); 
    } catch (error) {
      console.error('Error during signup:', error.response?.data?.message || error.message);
      setErrorMessage(error.response?.data?.message || 'An error occurred during signup.'); // Set error message state
    }
  };

  return (
    <div className="App">
      <Header userName={'anonymity'} />
      <div className="signup-page">
        <form className="signup-form" onSubmit={handleSubmit}>
          <div className="signup-title">Sign up</div>
          {/* Display error message if any */}
          {errorMessage && <div className="error-message">{errorMessage}</div>}

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
                required={userType === 'doctor'}
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
                        required={userType === 'patient'}
                    />
                </div>
              <div className="input-container">
                <label htmlFor="gender">Gender:</label>
                <select
                  id="gender"
                  value={gender}
                  onChange={(e) => setGender(e.target.value)}
                  required={userType === 'patient'}
                >
                  <option value="">Select Gender</option>
                  <option value="Male">Male</option>
                  <option value="Female">Female</option>
                  <option value="Other">Other</option>
                </select>
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