import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './CreateMedicalRecord.css';

function CreateMedicalRecord(doctorId) {
    const [patients, setPatients] = useState([]);
    const [selectedPatientId, setSelectedPatientId] = useState('');
    const [recordType, setRecordType] = useState('');
    const [recordDescription, setRecordDescription] = useState('');
    const [file, setFile] = useState(null);
    const [dateOfSubmission, setDateOfSubmission] = useState('');

    useEffect(() => {
        // Fetch patients list on component mount
        axios.get('http://localhost:8080/patients')
            .then(response => {
                setPatients(response.data);
            })
            .catch(error => console.error('Error fetching patients:', error));
    }, []);

    const handleSubmit = (e) => {
        e.preventDefault();

        // Ensure a patient is selected
        if (!selectedPatientId) {
            alert('Please select a patient.');
            return;
        }

        const formData = new FormData();
        formData.append('dateOfSubmission', dateOfSubmission);
        formData.append('recordType', recordType);
        formData.append('recordDescription', recordDescription);
        if (file) {
            formData.append('file', file);
        }
        console.log('selectedPatientId',selectedPatientId);
        console.log('docotrId',doctorId);
        const DoctorId = doctorId;
        axios.post(`http://localhost:8080/medicalrecords/patients/${selectedPatientId}`, {
            dateOfSubmission,
            recordType,
            recordDescription,
            file,
            DoctorId
        })
            .then(response => {
                console.log('Medical record created:', response.data);
                // Reset form fields
                setDateOfSubmission('');
                setRecordDescription('');
                setRecordType('');
                setFile(null);
            })
            .catch(error => {
                console.error('Error creating medical record:', error);
            });
    };


    return (
        <form onSubmit={handleSubmit} className="medical-record-form">
            <h2>Add Medical Record</h2>
            <label>
                Select Patient:
                <select value={selectedPatientId} onChange={(e) => setSelectedPatientId(e.target.value)}>
                    <option value="">Select a patient</option>
                    {patients.map(patient => (
                        <option key={patient.id} value={patient.id}>
                            {patient.firstName} {patient.lastName}
                        </option>
                    ))}
                </select>
            </label>
            <label>
                Date of Submission:
                <input type="date" value={dateOfSubmission} onChange={(e) => setDateOfSubmission(e.target.value)} />
            </label>
            <label>
                Record Type:
                <input type="text" value={recordType} onChange={(e) => setRecordType(e.target.value)} />
            </label>
            <label>
                Description:
                <textarea value={recordDescription} onChange={(e) => setRecordDescription(e.target.value)} />
            </label>
            <label>
                File (optional):
                <input type="file" onChange={(e) => setFile(e.target.files[0])} />
            </label>
            <button type="submit">Submit</button>
        </form>
    );
}

export default CreateMedicalRecord;
