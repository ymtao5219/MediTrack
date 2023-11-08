// App.js

import React, { useState } from 'react';
import './App.css';
import Header from './components/Header/Header';
import Sidebar from './components/Sidebar/Sidebar'; 
import Login from './components/Login/Login'; 
import Dashboard from './components/Dashboard/Dashboard'; 
import WelcomeBanner from './components/WelcomeBanner/WelcomeBanner';

function App() {
  const [user, setUser] = useState(null);

  const handleLogin = (username) => {
    setUser(username);
  };

  // If user is not logged in, show the Login component
  if (!user) {
    return <Login onLogin={handleLogin} />;
  }

  // User is logged in, show the main content
  return (
    <div className="App">
      <Header userName={user} />
      <WelcomeBanner userName={user} />
      <div className="main-content">
        <Sidebar />
        <div className="content">
          <Dashboard />
        </div>
      </div>
    </div>
  );
}

export default App;
