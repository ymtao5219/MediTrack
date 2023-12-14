import React from 'react';
import './Header.css'; // Ensure your CSS file is updated accordingly
import patientAvatar from './patient-avatar.png'; // Placeholder path to patient avatar image
import doctorAvatar from './doctor-avatar.png'; // Placeholder path to doctor avatar image

function Header({ userName, userType }) {
  // Choose the avatar based on the user type
  const avatarSrc = userType === 'Doctor' ? doctorAvatar : patientAvatar;
  return (
    <header className="App-header">
      {/* Logo */}
      <div className="logo">200-OK Patient Tracker</div>

      {/* User Greeting and Avatar */}
      <div className="user-info">
      <img src={avatarSrc} alt={userType} className="user-avatar" />
        {/* Add more user details or a logout button here */}
      </div>
    </header>
  );
}

export default Header;
