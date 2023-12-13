import React, { useState } from 'react';
import axios from 'axios';
import './CreateMedicalRecord.css'; // Make sure to create this CSS file

function CreateMedicalRecord({ patientId }) {
    const [recordType, setRecordType] = useState('');
    const [recordDescription, setRecordDescription] = useState('');
    const [file, setFile] = useState(null);
    const [dateOfSubmission, setDateOfSubmission] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();

        axios.post(`http://localhost:8080/patients/${patientId}/medicalrecords`, {
            dateOfSubmission,
            recordType,
            recordDescription,
            file
        })
            .then(response => {
                console.log('Medical record created:', response.data);
                setDateOfSubmission('');
                setRecordDescription('');
                setRecordType('');
                setFile(null);
            })
            .catch(error => {
                console.error('Error creating medical record:', error);
                // Handle error
            });
    };

    return (
        <form onSubmit={handleSubmit} className="medical-record-form">
            <h2>Add Medical Record</h2>
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
