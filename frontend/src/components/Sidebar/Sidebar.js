import React from 'react';
import './Sidebar.css';
import logo from './logo_nb.png';

function Sidebar({ userType, onSidebarClick }) {
  // Handle the logout event
  const handleLogout = () => {
    // Perform logout logic here (e.g., clearing local storage, reset user state)
    
    // Redirect to the main page (or login page)
    window.location.href = '/';
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
                <li className="nav-item" onClick={() => onSidebarClick('patientsInfo')}><a href="#">Patient Info</a></li>
                <li className="nav-item" onClick={() => onSidebarClick('medicalRecords')}><a href="#">Medical Records</a></li>
                <li className="nav-item" onClick={() => onSidebarClick('makeAppointment')}>
                  <a href="#">Make Appointment</a>
                </li>
              </>
            )}
            {userType === 'doctor' && (
              <>
                <li className="nav-item" onClick={() => onSidebarClick('doctorsInfo')}><a href="#">Doctor Info</a></li>
                <li className="nav-item" onClick={() => onSidebarClick('createMedicalRecord')}>
                  <a href="#">Create Medical Record</a>
                </li>
                <li className="nav-item" onClick={() => onSidebarClick('doctorAppointments')}>
                  <a href="#">Doctor Appointments</a>
                </li>
              </>
            )}
            <li className="nav-item" onClick={handleLogout}>
              <a href="/" onClick={(e) => {e.preventDefault(); handleLogout();}}>Log Out</a>
            </li>
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
