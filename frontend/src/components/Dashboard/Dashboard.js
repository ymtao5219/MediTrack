import { React, useEffect, useState } from 'react';
import './Dashboard.css';
import axios from 'axios';

// Dashboard component
function Dashboard({ currentView }) {
  const [data, setData] = useState([]);
  useEffect(() => {
    const fetchData = async () => {
      let url = '';
      if (currentView === 'patientsInfo') {
        url = `http://localhost:8080/patients/61d5d3e12345678912345678`; // Replace with correct endpoint
      } else if (currentView === 'doctorsInfo') {
        url = `http://localhost:8080/doctors/61a8b9e7f1b74e5f4c8b4567`; // Replace with correct endpoint
      }

      try {
        const response = await axios.get(url);
        console.log('Data fetched:', response.data);
        setData(response.data);
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };

    fetchData();
  }, [currentView]);// Add currentView to the dependency array


  return (
    <div className="dashboard-container">
      <div className="dashboard-content">
        {/* First card: dynamic based on currentView */}
        <div className="patient-list dashboard-card">
          <div className="patient-list-header">
            <h3>{currentView === 'patientsInfo' ? 'Patient Information' : 'Doctor Information'}</h3>
          </div>
          <div className="patient-list-content">
            {data.length > 0 ? (
              <ul>
                {data.map((item) => (
                  <li key={item.id}>
                    {currentView === 'patients' ? 
                      `${item.firstName} ${item.lastName} - DOB: ${item.dateOfBirth}` :
                      `${item.firstName} ${item.lastName} - Specialization: ${item.specialization}`}
                  </li>
                ))}
              </ul>
            ) : (
              <p>No {currentView} found.</p>
            )}
          </div>
        </div>

        {/* Second card: Schedule or other fixed content */}
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
