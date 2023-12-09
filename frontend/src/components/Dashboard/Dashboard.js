import React from 'react';
import './Dashboard.css';

function Dashboard() {
  return (
    <div className="dashboard-container">
      <div className="dashboard-content">
        <div className="patient-list dashboard-card">
          <div className="patient-list-header">
            <h3>Patient List</h3>
          </div>
          <div className="patient-list-content">
            {/* Patient list data will go here */}
            <p>No Patients</p>
          </div>
          <div className="patient-list-footer">
            <button className="button">Add new Patient</button>
            <button className="button">Add new Event</button>
          </div>
        </div>
        <aside className="schedule dashboard-card">
          <header>
            <h3>Schedule</h3>
            <a href="/see-all">See all</a>
          </header>
          {/* Schedule items will go here */}
          <article>
            <h4>Sample Event</h4>
            <p>Details about the event...</p>
          </article>
          {/* More events can be added here */}
        </aside>
      </div>
    </div>
  );
}

export default Dashboard;
