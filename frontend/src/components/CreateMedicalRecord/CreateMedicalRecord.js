import React, { useState } from 'react';
import axios from 'axios';
import './CreateMedicalRecord.css'; // Make sure to create this CSS file

function CreateMedicalRecord({ patientId }) {
  const [recordType, setRecordType] = useState('');
  const [recordDescription, setRecordDescription] = useState('');
  const [file, setFile] = useState(null);

  const handleSubmit = (e) => {
    e.preventDefault();

    const formData = new FormData();
    formData.append('recordType', recordType);
    formData.append('recordDescription', recordDescription);
    if (file) {
      formData.append('file', file);
    }

    axios.post(`http://localhost:8080/patients/${patientId}/medicalrecords`, formData, {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    })
    .then(response => {
      console.log('Medical record created:', response.data);
      // Handle successful creation
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
