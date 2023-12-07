import React from 'react';
import './Header.css'; // Make sure to create a corresponding CSS file for styling

function Header({ userName }) {
  return (
    <header className="App-header">
      {/* You might have a logo here */}
      <div className="logo">Patient Tracker</div>

      {/* The welcome message, you can make this dynamic based on user data */}

      {/* User info and logout could also go here */}
      <div className="user-info">
      {userName}
        {/* Add more user details or a logout button here */}
      </div>
    </header>
  );
}

export default Header;
