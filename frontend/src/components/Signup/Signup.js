import React, { useState } from 'react';
import axios from 'axios';
import Header from "../Header/Header";
import "./Signup.css";

function Signup({ toggleView }) {
    // Assuming you'll hash the password on the server side
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [contactNumber, setContactNumber] = useState('');
    const [address, setAddress] = useState('');
    const [userType, setUserType] = useState('Patient'); // Notice the capital 'P' to match the data model
    const [specialization, setSpecialization] = useState('');
    const [dateOfBirth, setDateOfBirth] = useState('');
    const [gender, setGender] = useState('');
    const [errorMessage, setErrorMessage] = useState('');

    const handleSubmit = async (e) => {
      e.preventDefault();
    
      if (password !== confirmPassword) {
        setErrorMessage('Passwords do not match.');
        return;
      }
    
      // First, prepare the data for the general user registration
      const userData = {
        username,
        password,
        userType
      };
    
      try {
        // Step 1: Register the user
        const userResponse = await axios.post('http://localhost:8080/auth/register/', userData);
        console.log('User registered:', userResponse.data);
        
        // Prepare the data for the specific doctor or patient registration
        const profileData = userType === 'Doctor' ? {
          userId: userResponse.data._id, 
          firstName,
          lastName,
          emailAddress: email,
          contactNumber,
          specialization
        } : {
          userId: userResponse.data._id, 
          firstName,
          lastName,
          dateOfBirth,
          gender,
          contactNumber,
          emailAddress: email,
          address
        };
    
        // Step 2: Register the specific doctor or patient profile
        const profileEndpoint = userType === 'Doctor' ? 'http://localhost:8080/doctors' : 'http://localhost:8080/patients';
        const profileResponse = await axios.post(profileEndpoint, profileData);
        console.log(`${userType} profile created:`, profileResponse.data);
    
        // navigate('/login'); // Uncomment this when navigation is required
        // Reset form or show success message
      } catch (error) {
        console.error('Error during registration:', error.response?.data?.message || error.message);
        setErrorMessage(error.response?.data?.message || 'An error occurred during registration.');
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
                <option value="Patient">Patient</option>
                <option value="Doctor">Doctor</option>
              </select>
            </div>
    
            {/* First Name Input */}
            <div className="input-container">
              <label htmlFor="firstName">First Name:</label>
              <input
                type="text"
                id="firstName"
                value={firstName}
                onChange={(e) => setFirstName(e.target.value)}
                required
              />
            </div>
    
            {/* Last Name Input */}
            <div className="input-container">
              <label htmlFor="lastName">Last Name:</label>
              <input
                type="text"
                id="lastName"
                value={lastName}
                onChange={(e) => setLastName(e.target.value)}
                required
              />
            </div>
    
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
    
            {/* Contact Number Input */}
            <div className="input-container">
              <label htmlFor="contactNumber">Contact Number:</label>
              <input
                type="tel"
                id="contactNumber"
                value={contactNumber}
                onChange={(e) => setContactNumber(e.target.value)}
                required
              />
            </div>
    
            {/* Conditional Fields Based on User Type */}
            {userType === 'Doctor' && (
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
    
            {userType === 'Patient' && (
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
                  <select
                    id="gender"
                    value={gender}
                    onChange={(e) => setGender(e.target.value)}
                    required
                  >
                    <option value="">Select Gender</option>
                    <option value="Male">Male</option>
                    <option value="Female">Female</option>
                    <option value="Other">Other</option>
                  </select>
                </div>
                <div className="input-container">
                  <label htmlFor="address">Address:</label>
                  <input
                    type="text"
                    id="address"
                    value={address}
                    onChange={(e) => setAddress(e.target.value)}
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