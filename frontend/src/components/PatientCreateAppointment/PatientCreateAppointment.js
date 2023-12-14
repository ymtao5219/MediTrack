import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './PatientCreateAppointment.css';

function PatientCreateAppointment({ patientId }) {
  const [doctors, setDoctors] = useState([]);
  const [selectedDoctor, setSelectedDoctor] = useState('');
  const [appointmentDate, setAppointmentDate] = useState('');
  const [notes, setNotes] = useState('');

  useEffect(() => {
    // Fetch list of doctors
    axios.get('http://localhost:8080/doctors')
      .then(response => setDoctors(response.data))
      .catch(error => console.error('Error fetching doctors:', error));
    console.log(doctors.userId);
    }, []);

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log('Creating appointment...');
    console.log('Patient ID:', patientId);
    axios.post(`http://localhost:8080/appointments/patients/${patientId}`, {
      doctorId: selectedDoctor,
      dateOfAppointment: appointmentDate,
      notes
    }).then(response => {
      console.log('Doctor ID:', selectedDoctor);
      console.log('Appointment created:', response.data);
      setSelectedDoctor('');
      setAppointmentDate('');
      setNotes('');
    }).catch(error => {
      console.error('Error creating appointment:', error);
      // Handle error
    });
  };

  return (
    <form onSubmit={handleSubmit} className="create-appointment-form">
      <h2>Create Appointment</h2>
      <label>
        Select Doctor:
        <select value={selectedDoctor} onChange={(e) => 
        {
          console.log("New selected doctor:", e.target.value);
          setSelectedDoctor(e.target.value);
        }
        }>
          {doctors.map(doctor => (
            <option key={doctor.id} value={doctor.id}>{doctor.firstName} {doctor.lastName} - {doctor.specialization}</option>
          ))}
        </select>
      </label>
      <label>
        Appointment Date:
        <input type="date" value={appointmentDate} onChange={(e) => setAppointmentDate(e.target.value)} />
      </label>
      <label>
        Notes:
        <textarea value={notes} onChange={(e) => setNotes(e.target.value)} />
      </label>
      <button type="submit">Create Appointment</button>
    </form>
  );
}

export default PatientCreateAppointment;
