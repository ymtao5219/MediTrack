// App.js

import React, { useState } from 'react';
import './App.css';
import Header from './components/Header/Header';
import Sidebar from './components/Sidebar/Sidebar'; 
import Login from './components/Login/Login'; 
import Signup from './components/Signup/Signup'; 
import Dashboard from './components/Dashboard/Dashboard'; 
import WelcomeBanner from './components/WelcomeBanner/WelcomeBanner';

function App() {
  const [user, setUser] = useState(null);
  const [view, setView] = useState('login'); // State to manage login or signup view
  const [currentView, setCurrentView] = useState('patients');
  
  const handleLogin = (username, password, isDoctor) => {
    // Your login logic here
    setUser({
      username: username,
      userType: isDoctor ? 'Doctor' : 'Patient'
    });
  };

  const handleSignup = (username, password, email) => {
    // Your signup logic here
    setUser(username);
  };

  const toggleView = () => {
    setView(view === 'login' ? 'signup' : 'login');
  };

  const handleSidebarClick = (view) => {
    setCurrentView(view);
  };

  // If user is not logged in, show the Login or Signup component
  if (!user) {
    return view === 'login' ?
      <Login onLogin={handleLogin} toggleView={toggleView} /> :
      <Signup onSignup={handleSignup} toggleView={toggleView} />;
  }

  // User is logged in, show the main content
  return (
    <div className="App">
      <Header userName={user ? user.username : 'anonymity'} userType={user.userType} />
      <WelcomeBanner userName={user ? user.username : 'anonymity'} />
      <div className="main-content">
      {user && <Sidebar userType={user.userType} onSidebarClick={handleSidebarClick} />}
        <div className="content">
          <Dashboard currentView={currentView} />
        </div>
      </div>
    </div>
  );
}

export default App;
