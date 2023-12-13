import React, { useState, useEffect } from 'react';
import axios from 'axios';

function DoctorAppointments({ doctorId }) {
  const [appointments, setAppointments] = useState([]);

  useEffect(() => {
    axios.get(`http://localhost:8080/appointments/doctors/${doctorId}`)
      .then(response => {
        setAppointments(response.data);
      })
      .catch(error => {
        console.error('Error fetching appointments:', error);
      });
  }, [doctorId]);

  return (
    <div className="doctor-appointments">
      <h2>Doctor's Appointments</h2>
      {appointments.length > 0 ? (
        <ul>
          {appointments.map((appointment, index) => (
            <li key={index}>
              <p>Patient: {appointment.patientName}</p>
              <p>Date: {appointment.dateOfAppointment}</p>
              <p>Notes: {appointment.notes}</p>
            </li>
          ))}
        </ul>
      ) : (
        <p>No appointments found.</p>
      )}
    </div>
  );
}

export default DoctorAppointments;
