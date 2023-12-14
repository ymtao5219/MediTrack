import React, { useState } from 'react';
import './Sidebar.css';
import logo from './logo_nb.png';

function Sidebar({ userType, onSidebarClick }) {
  const [activeItem, setActiveItem] = useState(null);

  const handleSidebarClick = (item) => {
    // Call the passed in function from parent component if necessary
    onSidebarClick(item);
    // Set the clicked item as active
    setActiveItem(item);
  };

  return (
    <aside className="sidebar">
      <div className="logo-container">
        <img src={logo} alt="Patient Tracker Logo" className="sidebar-logo" />
      </div>
      <div className="sidebar-title-container">
        <nav className="nav">
          <ul className="nav-list">
            {userType === 'patient' && (
              <>
                <li className={`nav-item ${activeItem === 'patientsInfo' ? 'active' : ''}`} onClick={() => handleSidebarClick('patientsInfo')}>
                  <a href="#">Patient Info</a>
                </li>
                <li className={`nav-item ${activeItem === 'medicalRecords' ? 'active' : ''}`} onClick={() => handleSidebarClick('medicalRecords')}>
                  <a href="#">Medical Records</a>
                </li>
                <li className={`nav-item ${activeItem === 'makeAppointment' ? 'active' : ''}`} onClick={() => handleSidebarClick('makeAppointment')}>
                  <a href="#">Make Appointment</a>
                </li>
                <li className={`nav-item ${activeItem === 'patientAppointments' ? 'active' : ''}`} onClick={() => handleSidebarClick('patientAppointments')}>
                  <a href="#">My Appointments</a>
                </li>
              </>
            )}
            {userType === 'doctor' && (
              <>
                <li className={`nav-item ${activeItem === 'doctorsInfo' ? 'active' : ''}`} onClick={() => handleSidebarClick('doctorsInfo')}>
                  <a href="#">Doctor Info</a>
                </li>
                <li className={`nav-item ${activeItem === 'createMedicalRecord' ? 'active' : ''}`} onClick={() => handleSidebarClick('createMedicalRecord')}>
                  <a href="#">Create Medical Record</a>
                </li>
                <li className={`nav-item ${activeItem === 'doctorAppointments' ? 'active' : ''}`} onClick={() => handleSidebarClick('doctorAppointments')}>
                  <a href="#">Doctor Appointments</a>
                </li>
              </>
            )}
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
