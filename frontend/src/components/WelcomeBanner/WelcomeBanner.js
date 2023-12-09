import React from 'react';
import './WelcomeBanner.css';

const WelcomeBanner = ({ userName }) => {
  return (
    <div className="welcome-banner">
      <h2>Welcome back, {userName}!</h2>
      {/* Additional content if necessary */}
      <p>Today is {new Date().toLocaleDateString()}</p>
    </div>
  );
};

export default WelcomeBanner;
