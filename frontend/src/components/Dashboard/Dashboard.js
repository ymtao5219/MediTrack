// src/components/Dashboard/Dashboard.js
import React from 'react';
import './Dashboard.css';

function Dashboard() {
  return (
    <div className="dashboard-container">
      <div className="dashboard-content">
        <section className="patient-list">
          <header>
            <h3>Patient List</h3>
            <button>Add new Patient</button>
            <button>Add new Event</button>
          </header>
          <p>No Patients</p>
          {/* Patient list data will go here */}
        </section>
        <aside className="schedule">
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
