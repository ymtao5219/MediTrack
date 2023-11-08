// src/components/Sidebar/Sidebar.js
import React from 'react';
import './Sidebar.css';

function Sidebar() {
  return (
    <aside className="sidebar">
      <nav className="nav">
        <ul className="nav-list">
          <li className="nav-item"><a href="/">Dashboard</a></li>
          <li className="nav-item"><a href="/payment-info">Payment Info</a></li>
          <li className="nav-item"><a href="/patient-list">Patient List</a></li>
          {/* Add the rest of your navigation items here */}
        </ul>
      </nav>
      {/* Add any other sidebar content here */}
    </aside>
  );
}

export default Sidebar;
