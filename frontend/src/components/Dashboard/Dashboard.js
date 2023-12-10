import { React, useEffect, useState } from 'react';
import './Dashboard.css';
import axios from 'axios';

// Dashboard component
function Dashboard() {
  const [patients, setPatients] = useState([]);
  // doctorId = '61a8b9e7f1b74e5f4c8b4579'; // Replace with actual doctor ID
  useEffect(() => {
    const fetchPatients = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/doctors/61d5d3e12345678912345678`);
        console.log(response.data);
        setPatients(response.data); // Assuming the API returns an array of patients
      } catch (error) {
        console.error('Error fetching patient data:', error);
      }
    };

    fetchPatients();
  }, []); // The effect runs when the doctorId changes

  return (
    <div className="dashboard-container">
      <div className="dashboard-content">
        <div className="patient-list dashboard-card">
          <div className="patient-list-header">
            <h3>Patient List</h3>
          </div>
          <div className="patient-list-content">
          {patients.length > 0 ? (
              <ul>
                {patients.map((patient) => (
                  <li key={patient.id}>
                    {patient.name} - {patient.appointmentDate} {/* Adjust based on actual patient object */}
                  </li>
                ))}
              </ul>
            ) : (
              <p>No patients scheduled 1234.</p>
            )}
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
