import React, { useState, useEffect } from 'react';
import axios from 'axios';

function PatientAppointments({ patientId }) {
  const [appointments, setAppointments] = useState([]);
  const [isEditing, setIsEditing] = useState(false);
  const [currentAppointment, setCurrentAppointment] = useState(null);
  const [editedAppointmentData, setEditedAppointmentData] = useState({});

  useEffect(() => {
    fetchAppointments();
  }, [patientId]);

  const fetchAppointments = () => {
    axios.get(`http://localhost:8080/appointments/patients/${patientId}`)
      .then(response => {
        setAppointments(response.data);
      })
      .catch(error => console.error('Error fetching appointments:', error));
  };

  const handleEditAppointment = (appointmentId) => {
    const appointmentToEdit = appointments.find(appointment => appointment.appointmentId === appointmentId);
    setCurrentAppointment(appointmentToEdit);
    setIsEditing(true);
    setEditedAppointmentData(appointmentToEdit);
  };

  const handleEditedDataChange = (e) => {
    setEditedAppointmentData({ ...editedAppointmentData, [e.target.name]: e.target.value });
  };

  const submitEditedAppointment = () => {
    axios.put(`http://localhost:8080/appointments/${currentAppointment.appointmentId}`, editedAppointmentData)
      .then(() => {
        fetchAppointments();
        setIsEditing(false);
      })
      .catch(error => console.error('Error updating appointment:', error));
  };

  const handleDeleteAppointment = (appointmentId) => {
    axios.delete(`http://localhost:8080/appointments/patients/${patientId}/${appointmentId}`)
      .then(() => {
        fetchAppointments(); // Refresh the list after deletion
      })
      .catch(error => console.error('Error deleting appointment:', error));
  };

  return (
    <div className="patient-appointments">
      <h2>My Appointments</h2>
      {isEditing ? (
        <div>
          <h3>Edit Appointment</h3>
          <label>
            Date:
            <input 
              type="date" 
              name="dateOfAppointment" 
              value={editedAppointmentData.dateOfAppointment} 
              onChange={handleEditedDataChange} 
            />
          </label>
          <label>
            Notes:
            <textarea 
              name="notes" 
              value={editedAppointmentData.notes} 
              onChange={handleEditedDataChange} 
            />
          </label>
          <button onClick={submitEditedAppointment}>Submit Changes</button>
          <button onClick={() => setIsEditing(false)}>Cancel</button>
        </div>
      ) : (
        <ul>
          {appointments.map((appointment, index) => (
            <li key={index}>
              <p>Doctor: {appointment.doctorName}</p>
              <p>Date: {appointment.dateOfAppointment}</p>
              <p>Notes: {appointment.notes}</p>
              <button className="button button-edit" onClick={() => handleEditAppointment(appointment.appointmentId)}>Edit</button>
              <button className="button button-delete" onClick={() => handleDeleteAppointment(appointment.appointmentId)}>Delete</button>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}

export default PatientAppointments;
