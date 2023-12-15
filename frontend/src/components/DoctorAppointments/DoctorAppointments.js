import React, { useState, useEffect } from 'react';
import axios from 'axios';

function DoctorAppointments({ doctorId }) {
  const [appointments, setAppointments] = useState([]);

  const fetchPatientName = async (patientId) => {
    try {
      const response = await axios.get(`http://localhost:8080/patients/${patientId}`);
      return response.data.firstName + ' ' + response.data.lastName; // Assuming the patient object has firstName and lastName
    } catch (error) {
      console.error('Error fetching patient name:', error);
      return 'Unknown Patient'; // Fallback name
    }
  };


  useEffect(() => {
    const fetchAppointments = async () => {
        try {
            const response = await axios.get(`http://localhost:8080/appointments/doctors/${doctorId}`);
            const appointmentsWithNames = await Promise.all(response.data.map(async appointment => {
                const patientName = await fetchPatientName(appointment.patientId);
                return { ...appointment, patientName }; // Add the patientName to the appointment object
            }));
            setAppointments(appointmentsWithNames);
        } catch (error) {
            console.error('Error fetching appointments:', error);
        }
    };

    fetchAppointments();
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
