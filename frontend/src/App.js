// App.js

import React, { useState,useEffect } from 'react';
import './App.css';
import Header from './components/Header/Header';
import Sidebar from './components/Sidebar/Sidebar'; 
import Login from './components/Login/Login'; 
import Signup from './components/Signup/Signup'; 
import Dashboard from './components/Dashboard/Dashboard'; 
import WelcomeBanner from './components/WelcomeBanner/WelcomeBanner';
import axios from 'axios'; 

function App() {
  const [user, setUser] = useState(null);
  const [view, setView] = useState('login'); // State to manage login or signup view
  const [currentView, setCurrentView] = useState('patients');
   
  useEffect(() => {
    // If user is set (i.e., we have the userId), fetch user details
    if (user && user.userId) {
      axios.get(`http://localhost:8080/users/${user.userId}`)
        .then(response => {
          // Update the user state with the fetched details
          setUser(prevUser => ({ ...prevUser, ...response.data }));
        })
        .catch(error => {
          console.error('Error fetching user details:', error);
        });
    }
  }, [user?.userId]);

  const handleLogin = (loginResponse) => {
    const userId = loginResponse.userId;
    const userType = loginResponse.userType;
    const referenceId = loginResponse.referenceId;

    if (userId) {
      setUser({ userId, userType });
    } else {
      // Handle error - no userId returned
      console.error('No userId returned from login API');
    }
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
  console.log('check user');
  console.log(user); 
  return (
    <div className="App">
      <Header userName={user ? user.username : 'anonymity'} userType={user.userType} />
      <WelcomeBanner userName={user ? user.username : 'anonymity'} />
      <div className="main-content">
      {user && <Sidebar userType={user.userType} onSidebarClick={handleSidebarClick} />}
        <div className="content">
          <Dashboard currentView={currentView} userId={user.referenceId} />
        </div>
      </div>
    </div>
  );
}

export default App;
