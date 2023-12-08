// src/components/Sidebar/Sidebar.js
import React from 'react';
import './Sidebar.css';
import logo from './logo_nb.png';

function Sidebar() {
  return (
    <aside className="sidebar">
      <div className="logo-container">
        <img src={logo} alt="Patient Tracker Logo" className="sidebar-logo" />
      </div>
      <div className="sidebar-title-container">
        <nav className="nav">
          <ul className="nav-list">
            <li className="nav-item"><a href="/">Dashboard</a></li>
            <li className="nav-item"><a href="/payment-info">Payment Info</a></li>
            <li className="nav-item"><a href="/patient-list">Patient List</a></li>
            <li className="nav-item"><a href="/add-new-patient">Add New Patient</a></li>
            <li className="nav-item"><a href="/add-new-event">Add New Event</a></li>
            {/* Add the rest of your navigation items here */}
          </ul>
        </nav>
      </div>
      <div className="sidebar-bottom">
        {/* Add the logout or any other bottom content here */}
      </div>
    </aside>
  );
}
export default Sidebar;
