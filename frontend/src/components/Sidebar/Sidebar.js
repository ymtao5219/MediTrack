import React, { useState } from 'react';
import './Sidebar.css';
import logo from './logo_nb.png';

function Sidebar({ userType, onSidebarClick }) {
  const [activeItem, setActiveItem] = useState(null);

  const handleSidebarClick = (item) => {
    onSidebarClick(item);
    setActiveItem(item);
  };

  return (
    <aside className="sidebar">
      <div className="logo-container">
        <img src={logo} alt="Patient Tracker Logo" className="sidebar-logo" />
      </div>
      <nav className="nav">
        <ul className="nav-list">
          {userType === 'Patient' && (
            <>
              <li className={`nav-item ${activeItem === 'patientsInfo' ? 'active' : ''}`} onClick={() => handleSidebarClick('patientsInfo')}>
                <i className="fa-solid fa-user"></i>
                <a href="#">Patient Info</a>
              </li>
              <li className={`nav-item ${activeItem === 'medicalRecords' ? 'active' : ''}`} onClick={() => handleSidebarClick('medicalRecords')}>
                <i className="fa-solid fa-file-medical"></i>
                <a href="#">Medical Records</a>
              </li>
              <li className={`nav-item ${activeItem === 'makeAppointment' ? 'active' : ''}`} onClick={() => handleSidebarClick('makeAppointment')}>
                <i className="fa-solid fa-calendar-plus"></i>
                <a href="#">Make Appointment</a>
              </li>
              <li className={`nav-item ${activeItem === 'patientAppointments' ? 'active' : ''}`} onClick={() => handleSidebarClick('patientAppointments')}>
                <i className="fa-solid fa-calendar-check"></i>
                <a href="#">My Appointments</a>
              </li>
            </>
          )}
          {userType === 'Doctor' && (
            <>
              <li className={`nav-item ${activeItem === 'doctorsInfo' ? 'active' : ''}`} onClick={() => handleSidebarClick('doctorsInfo')}>
                <i className="fa-solid fa-user-md"></i>
                <a href="#">Doctor Info</a>
              </li>
              <li className={`nav-item ${activeItem === 'createMedicalRecord' ? 'active' : ''}`} onClick={() => handleSidebarClick('createMedicalRecord')}>
                <i className="fa-solid fa-notes-medical"></i>
                <a href="#">Create Medical Record</a>
              </li>
              <li className={`nav-item ${activeItem === 'doctorAppointments' ? 'active' : ''}`} onClick={() => handleSidebarClick('doctorAppointments')}>
                <i className="fa-solid fa-stethoscope"></i>
                <a href="#">Doctor Appointments</a>
              </li>
            </>
          )}
        </ul>
      </nav>
      <div className="sidebar-bottom">
        {/* Add the logout or any other bottom content here */}
      </div>
    </aside>
  );
}

export default Sidebar;