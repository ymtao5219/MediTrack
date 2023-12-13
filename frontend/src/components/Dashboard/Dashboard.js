import React, { useEffect, useState } from 'react';
import './Dashboard.css';
import axios from 'axios';
import PatientCreateAppointment from '../PatientCreateAppointment/PatientCreateAppointment';
import CreateMedicalRecord from '../CreateMedicalRecord/CreateMedicalRecord';

// Dashboard component
function Dashboard({ currentView }) {
  const [data, setData] = useState([]);

  const viewTitles = {
    patientsInfo: 'Patient Information',
    doctorsInfo: 'Doctor Information',
    medicalRecords: 'Medical Records',
    // add more views here
  };

  useEffect(() => {
    const fetchData = async () => {
      let url = '';
      if (currentView === 'patientsInfo') {
        url = `http://localhost:8080/patients/61d5d3e12345678912345678`; // Replace with correct endpoint
      } else if (currentView === 'doctorsInfo') {
        url = `http://localhost:8080/doctors/61a8b9e7f1b74e5f4c8b4567`; // Replace with correct endpoint
      }
      else if (currentView === 'medicalRecords') {
        url = `http://localhost:8080/patients/61d5d3e12345678912345678/medicalrecords`; // Replace with correct endpoint
      }

      try {
        const response = await axios.get(url);
        console.log('Data fetched:', response.data);
        setData(response.data);
        console.log(typeof (data));
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };

    fetchData();
  }, [currentView]);// Add currentView to the dependency array

  const renderContent = () => {
    switch (currentView) {
      case 'patientsInfo':
        return (
          <>
            <li>First Name: {data.firstName}</li>
            <li>Last Name: {data.lastName}</li>
            <li>Date of Birth: {data.dateOfBirth}</li>
            <li>Gender: {data.gender}</li>
            <li>Address: {data.address}</li>
            <li>Contact Number: {data.contactNumber}</li>
          </>
        );
      case 'doctorsInfo':
        return (
          <>
            <li>First Name: {data.firstName}</li>
            <li>Last Name: {data.lastName}</li>
            <li>Specialization: {data.specialization}</li>
            <li>Contact Number: {data.contactNumber}</li>
            <li>Email Address: {data.emailAddress}</li>
          </>
        );
      case 'medicalRecords':
        const recordsArray = Object.values(data); // Convert object to array
        return (
          <ul>
            {recordsArray.map((record, index) => (
              <li key={index}>
                {/* Example: assuming record has a 'dateOfSubmission' field */}
                Record Date: {record.dateOfSubmission}
                {/* Add more fields as needed */}
              </li>
            ))}
          </ul>
        );
      case 'makeAppointment':
        return <PatientCreateAppointment patientId={'61d5d3e12345678912345678'} />; 
        //hard coded patient id, TODO: change to dynamic
      
      case 'createMedicalRecord':
        return <CreateMedicalRecord patientId={'61d5d3e12345678912345678'} />;
        // hard coded patient id, TODO: change to dynamic
        
        default:
        return <p>No contents found.</p>;
    }
  };

  return (
    <div className="dashboard-container">
      <div className="dashboard-content">
        {/* First card: dynamic based on currentView */}
        <div className="patient-list dashboard-card">
          <div className="patient-list-header">
            <h3>{viewTitles[currentView]}</h3>
          </div>
          <div className="patient-list-content">
            <ul>
              {renderContent()}
            </ul>
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
